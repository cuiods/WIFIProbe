package com.codingfairy.data.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2017-05-15.
 * probe entity
 */
@Entity
@Table(name = "probe")
public class ProbeEntity {
    private int id;
    private String probeId;

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
    @Column(name = "probeId")
    public String getProbeId() {
        return probeId;
    }

    public void setProbeId(String probeId) {
        this.probeId = probeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProbeEntity that = (ProbeEntity) o;

        if (id != that.id) return false;
        if (probeId != null ? !probeId.equals(that.probeId) : that.probeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (probeId != null ? probeId.hashCode() : 0);
        return result;
    }
}
