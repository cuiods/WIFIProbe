package com.codingfairy.data.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2017-05-15.
 * Customer activeness entity
 */
@Entity
@Table(name = "activeness")
public class ActivenessEntity {
    private int id;
    private String wifiProb;
    private int hour;
    private Integer numOfHighActive;
    private Integer numOfMedianActive;
    private Integer numOfLowActive;
    private Integer numOfSleepActive;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "wifiProb")
    public String getWifiProb() {
        return wifiProb;
    }

    public void setWifiProb(String wifiProb) {
        this.wifiProb = wifiProb;
    }

    @Basic
    @Column(name = "hour")
    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Basic
    @Column(name = "numOfHighActive")
    public Integer getNumOfHighActive() {
        return numOfHighActive;
    }

    public void setNumOfHighActive(Integer numOfHighActive) {
        this.numOfHighActive = numOfHighActive;
    }

    @Basic
    @Column(name = "numOfMedianActive")
    public Integer getNumOfMedianActive() {
        return numOfMedianActive;
    }

    public void setNumOfMedianActive(Integer numOfMedianActive) {
        this.numOfMedianActive = numOfMedianActive;
    }

    @Basic
    @Column(name = "numOfLowActive")
    public Integer getNumOfLowActive() {
        return numOfLowActive;
    }

    public void setNumOfLowActive(Integer numOfLowActive) {
        this.numOfLowActive = numOfLowActive;
    }

    @Basic
    @Column(name = "numOfSleepActive")
    public Integer getNumOfSleepActive() {
        return numOfSleepActive;
    }

    public void setNumOfSleepActive(Integer numOfSleepActive) {
        this.numOfSleepActive = numOfSleepActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActivenessEntity that = (ActivenessEntity) o;

        if (id != that.id) return false;
        if (hour != that.hour) return false;
        if (wifiProb != null ? !wifiProb.equals(that.wifiProb) : that.wifiProb != null) return false;
        if (numOfHighActive != null ? !numOfHighActive.equals(that.numOfHighActive) : that.numOfHighActive != null)
            return false;
        if (numOfMedianActive != null ? !numOfMedianActive.equals(that.numOfMedianActive) : that.numOfMedianActive != null)
            return false;
        if (numOfLowActive != null ? !numOfLowActive.equals(that.numOfLowActive) : that.numOfLowActive != null)
            return false;
        if (numOfSleepActive != null ? !numOfSleepActive.equals(that.numOfSleepActive) : that.numOfSleepActive != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (wifiProb != null ? wifiProb.hashCode() : 0);
        result = 31 * result + hour;
        result = 31 * result + (numOfHighActive != null ? numOfHighActive.hashCode() : 0);
        result = 31 * result + (numOfMedianActive != null ? numOfMedianActive.hashCode() : 0);
        result = 31 * result + (numOfLowActive != null ? numOfLowActive.hashCode() : 0);
        result = 31 * result + (numOfSleepActive != null ? numOfSleepActive.hashCode() : 0);
        return result;
    }
}
