package com.fosun.fc.projects.creepers.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the T_CREEPERS_ACCOUNT_COMMON database table.
 * 
 */
@Entity
@Table(name = "T_CREEPERS_ACCOUNT_COMMON")
@NamedQuery(name = "TCreepersAccountCommon.findAll", query = "SELECT t FROM TCreepersAccountCommon t")
public class TCreepersAccountCommon extends com.fosun.fc.modules.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "T_CREEPERS_ACCOUNT_COMMON_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_ACCOUNT_COMMON")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_ACCOUNT_COMMON_ID_GENERATOR")
    private long id;

    private String memo;

    private String pwd;

    private String status;

    @Column(name = "\"TYPE\"")
    private String type;

    private String usr;

    public TCreepersAccountCommon() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsr() {
        return this.usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

}
