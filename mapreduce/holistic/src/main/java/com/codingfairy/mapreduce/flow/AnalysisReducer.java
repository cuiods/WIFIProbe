package com.codingfairy.mapreduce.flow;

import com.codingfairy.config.MapKeyConfig;
import com.codingfairy.to.KeyWrapper;
import com.codingfairy.to.ValueWrapper;
import com.codingfairy.vo.analysis.element.CustomerFlowElement;
import com.codingfairy.vo.analysis.element.NewOldCustomElement;
import com.google.gson.Gson;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by darxan on 17-5-20.
 */
public class AnalysisReducer extends Reducer<KeyWrapper, ValueWrapper, LongWritable, Text> {

    private Gson gson ;
    private Text text ;
    private MultipleOutputs<LongWritable, Text> multipleOutputs;


    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
        gson = new Gson();
        text = new Text();
        multipleOutputs = new MultipleOutputs<LongWritable, Text>(context);
    }

    @Override
    public void cleanup(Context cxt)throws IOException,InterruptedException{
        multipleOutputs.close();
    }



    @Override
    protected void reduce(KeyWrapper key, Iterable<ValueWrapper> values, Context context)
            throws IOException, InterruptedException {

        String type = key.getType().toString();
        Iterator<ValueWrapper> iterator = values.iterator();
        if (!iterator.hasNext()) {
            return;
        }
        ValueWrapper valueWrapper = iterator.next();

        if (type.equals(MapKeyConfig.CUSTOMER_FLOW_KEY)) {
            CustomerFlowElement first = (CustomerFlowElement)valueWrapper.getValue();
            while (iterator.hasNext()) {
                CustomerFlowElement next = (CustomerFlowElement)iterator.next().getValue();
                first.addAnother(next);
            }
        }
        else if (type.equals(MapKeyConfig.NEW_OLD_CUSTOMER)){
            NewOldCustomElement first = (NewOldCustomElement)valueWrapper.getValue();
            while (iterator.hasNext()) {
                NewOldCustomElement next = (NewOldCustomElement)iterator.next().getValue();
                first.addAnother(next);
            }
        }
        else if (type.equals(MapKeyConfig.IN_STORE_HOUR) || type.equals(MapKeyConfig.CYCLE)){
            int first = ((IntWritable)valueWrapper.getValue()).get();
            while (iterator.hasNext()) {
                int next =  ((IntWritable)iterator.next().getValue()).get();
                first+=next;
            }
            ((IntWritable)valueWrapper.getValue()).set(first);
        }
        text.set(gson.toJson(valueWrapper.getValue()));
        multipleOutputs.write(type, key.getMillisTime(), text, type);
    }
}
