package com.codingfairy.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by cuihao on 2017-05-15.
 * New-old customer statistic entity
 */
@Entity
@Table(name = "new_old")
public class NewOldEntity {
    private int id;
    private String wifiProb;
    private Timestamp hour;
    private Integer newCustomer;
    private Integer oldCustomer;

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
    public Timestamp getHour() {
        return hour;
    }

    public void setHour(Timestamp hour) {
        this.hour = hour;
    }

    @Basic
    @Column(name = "newCustomer")
    public Integer getNewCustomer() {
        return newCustomer;
    }

    public void setNewCustomer(Integer newCustomer) {
        this.newCustomer = newCustomer;
    }

    @Basic
    @Column(name = "oldCustomer")
    public Integer getOldCustomer() {
        return oldCustomer;
    }

    public void setOldCustomer(Integer oldCustomer) {
        this.oldCustomer = oldCustomer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewOldEntity that = (NewOldEntity) o;

        if (id != that.id) return false;
        if (hour != that.hour) return false;
        if (wifiProb != null ? !wifiProb.equals(that.wifiProb) : that.wifiProb != null) return false;
        if (newCustomer != null ? !newCustomer.equals(that.newCustomer) : that.newCustomer != null) return false;
        if (oldCustomer != null ? !oldCustomer.equals(that.oldCustomer) : that.oldCustomer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (wifiProb != null ? wifiProb.hashCode() : 0);
        result = 31 * result + (hour != null ? hour.hashCode() : 0);
        result = 31 * result + (newCustomer != null ? newCustomer.hashCode() : 0);
        result = 31 * result + (oldCustomer != null ? oldCustomer.hashCode() : 0);
        return result;
    }
}
