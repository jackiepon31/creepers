package com.fosun.fc.projects.creepers.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the T_CREEPERS_BASIC database table.
 * 
 */
@Entity
@Table(name = "T_CREEPERS_BASIC")
@NamedQuery(name = "TCreepersBasic.findAll", query = "SELECT t FROM TCreepersBasic t")
public class TCreepersBasic extends com.fosun.fc.modules.entity.BaseEntity {

    private static final long serialVersionUID = 1243092679541493758L;

    @Id
    @SequenceGenerator(name = "T_CREEPERS_BASIC_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_BASIC")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_BASIC_ID_GENERATOR")
    private Long id;

    @Column(name = "ID_NO")
    private String idNo;

    @Column(name = "ID_TYPE")
    private String idType;

    @Column(name = "MARITAL_STATUS")
    private String maritalStatus;

    private String memo;

    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "QUERY_DT")
    private Date queryDt;

    @Temporal(TemporalType.DATE)
    @Column(name = "RPT_DT")
    private Date rptDt;

    @Column(name = "RPT_NO")
    private String rptNo;

    @ManyToOne
    @JoinColumn(name = "RPT_NO", referencedColumnName = "RPT_NO", insertable = false, updatable = false)
    private TCreepersAccountBak TCreepersAccountBak;

    public TCreepersBasic() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdNo() {
        return this.idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdType() {
        return this.idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getMaritalStatus() {
        return this.maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getQueryDt() {
        return this.queryDt;
    }

    public void setQueryDt(Date queryDt) {
        this.queryDt = queryDt;
    }

    public Date getRptDt() {
        return this.rptDt;
    }

    public void setRptDt(Date rptDt) {
        this.rptDt = rptDt;
    }

    public String getRptNo() {
        return this.rptNo;
    }

    public void setRptNo(String rptNo) {
        this.rptNo = rptNo;
    }

    public TCreepersAccountBak getTCreepersAccountBak() {
        return this.TCreepersAccountBak;
    }

    public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak) {
        this.TCreepersAccountBak = TCreepersAccountBak;
    }
}
