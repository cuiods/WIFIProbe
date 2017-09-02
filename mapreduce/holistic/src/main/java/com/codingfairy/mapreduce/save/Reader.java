package com.codingfairy.mapreduce.save;

import com.codingfairy.config.MapKeyConfig;
import com.codingfairy.mapreduce.config.ActivenessJudge;
import com.codingfairy.mock.GsonTool;
import com.codingfairy.vo.Tuple;
import com.codingfairy.vo.analysis.element.*;
import com.google.gson.Gson;
import org.apache.hadoop.io.IntWritable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by darxan on 2017/8/27.
 */
public class Reader {


    List<CustomerFlowElement> customerFlows = new ArrayList<CustomerFlowElement>();
    List<NewOldCustomElement> newOldCustoms = new ArrayList<NewOldCustomElement>();
    List<CustomerActivenessElement> activeness = new ArrayList<CustomerActivenessElement>();
    InStoreHoursElement inStoreHours = new InStoreHoursElement();
    VisitingCycleElement visitingCycles = new VisitingCycleElement();

    private void _readFlow(String filename, BufferedReader reader) throws IOException {
        if (filename.startsWith(MapKeyConfig.CUSTOMER_FLOW_KEY)) {
            for (String line; (line=reader.readLine())!=null; ) {
                String[] temp = splitKeyValue(line);
                if (temp.length==2) {
                    CustomerFlowElement customerFlowElement = new Gson().fromJson(
                            temp[1], CustomerFlowElement.class);
                    customerFlowElement.setHour(Long.parseLong(temp[0])/(1000L*60L*60L));
                    customerFlows.add(customerFlowElement);
                }
            }

        }
    }

    private void _readCycle(String filename, BufferedReader reader) throws IOException {
        if (filename.startsWith(MapKeyConfig.CYCLE)) {
            for (String line; (line=reader.readLine())!=null; ) {
                Tuple<Long, Integer> tuple = _parseTuple(line);
                if (tuple!=null) {
                    visitingCycles.add(tuple);
                }
            }
        }
    }

    private void _readNewOld(String filename, BufferedReader reader) throws IOException {
        if (filename.startsWith(MapKeyConfig.NEW_OLD_CUSTOMER)) {
            for (String line; (line=reader.readLine())!=null; ) {

                String[] temp = splitKeyValue(line);
                if (temp.length==2) {

                    NewOldCustomElement newOldCustomElement = new Gson().fromJson(
                            temp[1], NewOldCustomElement.class);

                    newOldCustomElement.setHour(Long.parseLong(temp[0])/(1000L*60L*60L));
                    newOldCustoms.add(newOldCustomElement);
                }
            }
        }
    }

    private void _readInStore(String filename, BufferedReader reader) throws IOException {
        if (filename.startsWith(MapKeyConfig.IN_STORE_HOUR)) {
            for (String line; (line=reader.readLine())!=null; ) {
                Tuple<Long, Integer> tuple = _parseTuple(line);
                if (tuple!=null) {
                    inStoreHours.add(tuple);
                }
            }
        }
    }

    public void readStream(String filename, InputStream inputStream) throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream));

        _readFlow(filename, reader);
        _readCycle(filename, reader);
        _readInStore(filename, reader);
        _readNewOld(filename, reader);

        reader.close();

    }

    public void summary(Long executeHourTime, String wifiProb) {

        executeHourTime /= (1000L*60L*60L);

        visitingCycles.setHour(executeHourTime);
        visitingCycles.setWifiProb(wifiProb);

        inStoreHours.setHour(executeHourTime);
        inStoreHours.setWifiProb(wifiProb);

        CustomerActivenessElement activeness = new CustomerActivenessElement();
        this.activeness.add(activeness);

        activeness.setHour(executeHourTime);
        activeness.setWifiProb(wifiProb);

        newOldCustoms.forEach(e->e.setWifiProb(wifiProb));
        customerFlows.forEach(e->e.setWifiProb(wifiProb));
        customerFlows.forEach(e->{

            int sum = e.getInAndOutStore()+e.getInNoOutStore()+e.getOutNoInStore();
            int sumWifi = e.getInAndOutWifi()+e.getInNoOutWifi()+e.getOutNoInWifi();

            e.setDeepVisit(sum==0?0:e.getDeepVisit()/sum);
            e.setJumpRate(sum==0?0:(e.getInAndOutStore()+e.getOutNoInStore())/(sum+0.1));
            e.setInStoreRate(sumWifi==0?0:(e.getInAndOutStore()+e.getInNoOutStore())/(sumWifi+0.1));

        });

        for (Tuple<Long, Integer> tuple : visitingCycles.getStatistic()) {

            if (ActivenessJudge.isHigh(tuple.getX())) {
                activeness.setNumOfHighActive(activeness.getNumOfHighActive()+tuple.getY());
            } else if (ActivenessJudge.isMedian(tuple.getX())) {
                activeness.setNumOfMedianActive(activeness.getNumOfMedianActive()+tuple.getY());
            } else if (ActivenessJudge.isLow(tuple.getX())) {
                activeness.setNumOfLowActive(activeness.getNumOfLowActive()+tuple.getY());
            } else {
                activeness.setNumOfSleepActive(activeness.getNumOfSleepActive()+tuple.getY());
            }
        }
    }

    private String[] splitKeyValue(String line) {
        return line.split("\t", 2);
    }

    private Tuple<Long, Integer> _parseTuple(String line) {
        try {
            String[] lines = splitKeyValue(line);
            return new Tuple<>(
                    Long.parseLong(lines[0]),
                    GsonTool.getGson().fromJson(lines[1], IntWritable.class).get()
            );
        }catch (Exception e) {
            return null;
        }
    }

}

