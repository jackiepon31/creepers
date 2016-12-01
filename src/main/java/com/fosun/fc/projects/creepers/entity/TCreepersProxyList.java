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
 * The persistent class for the T_CREEPERS_PROXY_LIST database table.
 * 
 */
@Entity
@Table(name = "T_CREEPERS_PROXY_LIST")
@NamedQuery(name = "TCreepersProxyList.findAll", query = "SELECT t FROM TCreepersProxyList t")
public class TCreepersProxyList extends com.fosun.fc.modules.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "T_CREEPERS_PROXY_LIST_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_PROXY_LIST")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_PROXY_LIST_ID_GENERATOR")
    private long id;

    private String ip;

    @Column(name = "IP_TYPE")
    private String ipType;

    private String memo;

    private String port;

    public TCreepersProxyList() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIpType() {
        return this.ipType;
    }

    public void setIpType(String ipType) {
        this.ipType = ipType;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPort() {
        return this.port;
    }

    public void setPort(String port) {
        this.port = port;
    }

}
