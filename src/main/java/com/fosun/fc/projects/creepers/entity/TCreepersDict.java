package com.fosun.fc.projects.creepers.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the T_CREEPERS_DICT database table.
 * 
 */
@Entity
@Table(name = "T_CREEPERS_DICT")
@NamedQuery(name = "TCreepersDict.findAll", query = "SELECT t FROM TCreepersDict t")
public class TCreepersDict extends com.fosun.fc.modules.entity.BaseEntity {

    private static final long serialVersionUID = 6084725567135871253L;

    @Id
    @SequenceGenerator(name = "T_CREEPERS_DICT_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_DICT")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_DICT_ID_GENERATOR")
    private Long id;

    private String category;

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DT")
    private Date endDt;

    private String key1;

    private String key2;

    private String memo;

    @Temporal(TemporalType.DATE)
    @Column(name = "START_DT")
    private Date startDt;

    private String value1;

    private String value2;

    public TCreepersDict() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getEndDt() {
        return this.endDt;
    }

    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }

    public String getKey1() {
        return this.key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public String getKey2() {
        return this.key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getStartDt() {
        return this.startDt;
    }

    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    public String getValue1() {
        return this.value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return this.value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

}
