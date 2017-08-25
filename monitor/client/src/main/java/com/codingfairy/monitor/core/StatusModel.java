package com.codingfairy.monitor.core;

import com.codingfairy.monitor.bean.ProbeStatus;
import com.codingfairy.monitor.bean.Process;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by cuihao on 2017-08-24.
 * Probe status list model
 */
public class StatusModel {

    private TableView statusTable;

    private ObservableList<ProbeStatus> probeStatuses = FXCollections.observableArrayList();

    private ObservableList<Process> processes = FXCollections.observableArrayList();

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ObservableList<ProbeStatus> getProbeStatuses() {
        return probeStatuses;
    }

    public ObservableList<Process> getProcesses() {
        return processes;
    }

    public void startSearch() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Process process = new Process("正在执行",0,"0%",format.format(new Date()));
        processes.add(process);
        executorService.submit(new SearchTask(process, probeStatuses));
    }

    class SearchTask implements Runnable {

        private Process process;
        private ObservableList<ProbeStatus> probeStatuses;

        SearchTask(Process process, ObservableList<ProbeStatus> probeStatuses) {
            this.process = process;
            this.probeStatuses = probeStatuses;
        }

        @Override
        public void run() {
            probeStatuses.clear();
            String localIp = HttpTools.getLocalIp();
            localIp = "http://"+localIp;
            int dotIndex = localIp.lastIndexOf(".");
            localIp = localIp.substring(0,dotIndex+1);
            int count = 0;
            for (int i = 2; i < 255; i++) {
                double progress = i * 1.0 / 254;
                process.setProgress(progress);
                process.setPercent(String.format("%.2f",progress*100)+"%");
                String testIp = localIp+i;
                if (!testIp.equals(localIp)) {
                    URLConnection connection = HttpTools.sendGet(testIp);
                    if (connection!=null) {
                        probeStatuses.add(new ProbeStatus(++count,"运行中",testIp));
                        statusTable.refresh();
                    }
                }
            }
            process.setStatus("搜索完成");
        }
    }

    public void setStatusTable(TableView statusTable) {
        this.statusTable = statusTable;
    }
}
