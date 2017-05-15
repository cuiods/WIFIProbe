package com.codingfairy.data.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2017-05-15.
 * Visiting circle statistic entity
 */
@Entity
@Table(name = "visit_circle")
public class VisitCircleEntity {
    private int id;
    private String wifiProb;
    private int hour;
    private Integer data0;
    private Integer data1;
    private Integer data2;
    private Integer data3;
    private Integer data4;
    private Integer data5;
    private Integer data6;
    private Integer data7;
    private Integer data8;
    private Integer data9;

    @Id
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
    @Column(name = "data0")
    public Integer getData0() {
        return data0;
    }

    public void setData0(Integer data0) {
        this.data0 = data0;
    }

    @Basic
    @Column(name = "data1")
    public Integer getData1() {
        return data1;
    }

    public void setData1(Integer data1) {
        this.data1 = data1;
    }

    @Basic
    @Column(name = "data2")
    public Integer getData2() {
        return data2;
    }

    public void setData2(Integer data2) {
        this.data2 = data2;
    }

    @Basic
    @Column(name = "data3")
    public Integer getData3() {
        return data3;
    }

    public void setData3(Integer data3) {
        this.data3 = data3;
    }

    @Basic
    @Column(name = "data4")
    public Integer getData4() {
        return data4;
    }

    public void setData4(Integer data4) {
        this.data4 = data4;
    }

    @Basic
    @Column(name = "data5")
    public Integer getData5() {
        return data5;
    }

    public void setData5(Integer data5) {
        this.data5 = data5;
    }

    @Basic
    @Column(name = "data6")
    public Integer getData6() {
        return data6;
    }

    public void setData6(Integer data6) {
        this.data6 = data6;
    }

    @Basic
    @Column(name = "data7")
    public Integer getData7() {
        return data7;
    }

    public void setData7(Integer data7) {
        this.data7 = data7;
    }

    @Basic
    @Column(name = "data8")
    public Integer getData8() {
        return data8;
    }

    public void setData8(Integer data8) {
        this.data8 = data8;
    }

    @Basic
    @Column(name = "data9")
    public Integer getData9() {
        return data9;
    }

    public void setData9(Integer data9) {
        this.data9 = data9;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VisitCircleEntity that = (VisitCircleEntity) o;

        if (id != that.id) return false;
        if (hour != that.hour) return false;
        if (wifiProb != null ? !wifiProb.equals(that.wifiProb) : that.wifiProb != null) return false;
        if (data0 != null ? !data0.equals(that.data0) : that.data0 != null) return false;
        if (data1 != null ? !data1.equals(that.data1) : that.data1 != null) return false;
        if (data2 != null ? !data2.equals(that.data2) : that.data2 != null) return false;
        if (data3 != null ? !data3.equals(that.data3) : that.data3 != null) return false;
        if (data4 != null ? !data4.equals(that.data4) : that.data4 != null) return false;
        if (data5 != null ? !data5.equals(that.data5) : that.data5 != null) return false;
        if (data6 != null ? !data6.equals(that.data6) : that.data6 != null) return false;
        if (data7 != null ? !data7.equals(that.data7) : that.data7 != null) return false;
        if (data8 != null ? !data8.equals(that.data8) : that.data8 != null) return false;
        if (data9 != null ? !data9.equals(that.data9) : that.data9 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (wifiProb != null ? wifiProb.hashCode() : 0);
        result = 31 * result + hour;
        result = 31 * result + (data0 != null ? data0.hashCode() : 0);
        result = 31 * result + (data1 != null ? data1.hashCode() : 0);
        result = 31 * result + (data2 != null ? data2.hashCode() : 0);
        result = 31 * result + (data3 != null ? data3.hashCode() : 0);
        result = 31 * result + (data4 != null ? data4.hashCode() : 0);
        result = 31 * result + (data5 != null ? data5.hashCode() : 0);
        result = 31 * result + (data6 != null ? data6.hashCode() : 0);
        result = 31 * result + (data7 != null ? data7.hashCode() : 0);
        result = 31 * result + (data8 != null ? data8.hashCode() : 0);
        result = 31 * result + (data9 != null ? data9.hashCode() : 0);
        return result;
    }
}
