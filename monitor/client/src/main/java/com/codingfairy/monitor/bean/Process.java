package com.codingfairy.monitor.bean;

import javafx.beans.property.*;

import java.util.Date;

/**
 * Created by cuihao on 2017-08-24.
 * process view object
 */
public class Process {
    private final StringProperty status;
    private final DoubleProperty progress;
    private final StringProperty percent;
    private final ObjectProperty<Date> lastUpdate;

    public Process(String status, double progress, String percent, Date lastUpdate) {
        this.status = new SimpleStringProperty(status);
        this.progress = new SimpleDoubleProperty(progress);
        this.percent = new SimpleStringProperty(percent);
        this.lastUpdate = new SimpleObjectProperty<>(lastUpdate);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public double getProgress() {
        return progress.get();
    }

    public DoubleProperty progressProperty() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress.set(progress);
    }

    public String getPercent() {
        return percent.get();
    }

    public StringProperty percentProperty() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent.set(percent);
    }

    public Date getLastUpdate() {
        return lastUpdate.get();
    }

    public ObjectProperty<Date> lastUpdateProperty() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate.set(lastUpdate);
    }
}
