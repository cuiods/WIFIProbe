package com.codingfairy.data.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2017-05-15.
 * Customer flow entity
 */
@Entity
@Table(name = "flow")
public class FlowEntity {
    private int id;
    private String wifiProb;
    private int hour;
    private Integer inNoOutWifi;
    private Integer inNoOutStore;
    private Integer outNoInWifi;
    private Integer outNoInStore;
    private Integer inAndOutWifi;
    private Integer intAndOutStore;
    private Integer stayInWifi;
    private Integer stayInStore;
    private Double jumpRate;
    private Double deepVisit;
    private Double inStoreRate;

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
    @Column(name = "inNoOutWifi")
    public Integer getInNoOutWifi() {
        return inNoOutWifi;
    }

    public void setInNoOutWifi(Integer inNoOutWifi) {
        this.inNoOutWifi = inNoOutWifi;
    }

    @Basic
    @Column(name = "inNoOutStore")
    public Integer getInNoOutStore() {
        return inNoOutStore;
    }

    public void setInNoOutStore(Integer inNoOutStore) {
        this.inNoOutStore = inNoOutStore;
    }

    @Basic
    @Column(name = "outNoInWifi")
    public Integer getOutNoInWifi() {
        return outNoInWifi;
    }

    public void setOutNoInWifi(Integer outNoInWifi) {
        this.outNoInWifi = outNoInWifi;
    }

    @Basic
    @Column(name = "outNoInStore")
    public Integer getOutNoInStore() {
        return outNoInStore;
    }

    public void setOutNoInStore(Integer outNoInStore) {
        this.outNoInStore = outNoInStore;
    }

    @Basic
    @Column(name = "inAndOutWifi")
    public Integer getInAndOutWifi() {
        return inAndOutWifi;
    }

    public void setInAndOutWifi(Integer inAndOutWifi) {
        this.inAndOutWifi = inAndOutWifi;
    }

    @Basic
    @Column(name = "intAndOutStore")
    public Integer getIntAndOutStore() {
        return intAndOutStore;
    }

    public void setIntAndOutStore(Integer intAndOutStore) {
        this.intAndOutStore = intAndOutStore;
    }

    @Basic
    @Column(name = "stayInWifi")
    public Integer getStayInWifi() {
        return stayInWifi;
    }

    public void setStayInWifi(Integer stayInWifi) {
        this.stayInWifi = stayInWifi;
    }

    @Basic
    @Column(name = "stayInStore")
    public Integer getStayInStore() {
        return stayInStore;
    }

    public void setStayInStore(Integer stayInStore) {
        this.stayInStore = stayInStore;
    }

    @Basic
    @Column(name = "jumpRate")
    public Double getJumpRate() {
        return jumpRate;
    }

    public void setJumpRate(Double jumpRate) {
        this.jumpRate = jumpRate;
    }

    @Basic
    @Column(name = "deepVisit")
    public Double getDeepVisit() {
        return deepVisit;
    }

    public void setDeepVisit(Double deepVisit) {
        this.deepVisit = deepVisit;
    }

    @Basic
    @Column(name = "inStoreRate")
    public Double getInStoreRate() {
        return inStoreRate;
    }

    public void setInStoreRate(Double inStoreRate) {
        this.inStoreRate = inStoreRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlowEntity that = (FlowEntity) o;

        if (id != that.id) return false;
        if (hour != that.hour) return false;
        if (wifiProb != null ? !wifiProb.equals(that.wifiProb) : that.wifiProb != null) return false;
        if (inNoOutWifi != null ? !inNoOutWifi.equals(that.inNoOutWifi) : that.inNoOutWifi != null) return false;
        if (inNoOutStore != null ? !inNoOutStore.equals(that.inNoOutStore) : that.inNoOutStore != null) return false;
        if (outNoInWifi != null ? !outNoInWifi.equals(that.outNoInWifi) : that.outNoInWifi != null) return false;
        if (outNoInStore != null ? !outNoInStore.equals(that.outNoInStore) : that.outNoInStore != null) return false;
        if (inAndOutWifi != null ? !inAndOutWifi.equals(that.inAndOutWifi) : that.inAndOutWifi != null) return false;
        if (intAndOutStore != null ? !intAndOutStore.equals(that.intAndOutStore) : that.intAndOutStore != null)
            return false;
        if (stayInWifi != null ? !stayInWifi.equals(that.stayInWifi) : that.stayInWifi != null) return false;
        if (stayInStore != null ? !stayInStore.equals(that.stayInStore) : that.stayInStore != null) return false;
        if (jumpRate != null ? !jumpRate.equals(that.jumpRate) : that.jumpRate != null) return false;
        if (deepVisit != null ? !deepVisit.equals(that.deepVisit) : that.deepVisit != null) return false;
        if (inStoreRate != null ? !inStoreRate.equals(that.inStoreRate) : that.inStoreRate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (wifiProb != null ? wifiProb.hashCode() : 0);
        result = 31 * result + hour;
        result = 31 * result + (inNoOutWifi != null ? inNoOutWifi.hashCode() : 0);
        result = 31 * result + (inNoOutStore != null ? inNoOutStore.hashCode() : 0);
        result = 31 * result + (outNoInWifi != null ? outNoInWifi.hashCode() : 0);
        result = 31 * result + (outNoInStore != null ? outNoInStore.hashCode() : 0);
        result = 31 * result + (inAndOutWifi != null ? inAndOutWifi.hashCode() : 0);
        result = 31 * result + (intAndOutStore != null ? intAndOutStore.hashCode() : 0);
        result = 31 * result + (stayInWifi != null ? stayInWifi.hashCode() : 0);
        result = 31 * result + (stayInStore != null ? stayInStore.hashCode() : 0);
        result = 31 * result + (jumpRate != null ? jumpRate.hashCode() : 0);
        result = 31 * result + (deepVisit != null ? deepVisit.hashCode() : 0);
        result = 31 * result + (inStoreRate != null ? inStoreRate.hashCode() : 0);
        return result;
    }
}
