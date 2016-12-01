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
 * The persistent class for the T_CREEPERS_QUERY_LOG database table.
 * 
 */
@Entity
@Table(name = "T_CREEPERS_QUERY_LOG")
@NamedQuery(name = "TCreepersQueryLog.findAll", query = "SELECT t FROM TCreepersQueryLog t")
public class TCreepersQueryLog extends com.fosun.fc.modules.entity.BaseEntity {

    private static final long serialVersionUID = 5292107136214232026L;

    @Id
    @SequenceGenerator(name = "T_CREEPERS_QUERY_LOG_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_QUERY_LOG")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_QUERY_LOG_ID_GENERATOR")
    private Long id;

    private String memo;

    @Column(name = "QUERY_BY")
    private String queryBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "QUERY_DT")
    private Date queryDt;

    @Column(name = "QUERY_REASON")
    private String queryReason;

    @Column(name = "RPT_NO")
    private String rptNo;

    @ManyToOne
    @JoinColumn(name = "RPT_NO", referencedColumnName = "RPT_NO", insertable = false, updatable = false)
    private TCreepersAccountBak TCreepersAccountBak;

    public TCreepersQueryLog() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getQueryBy() {
        return this.queryBy;
    }

    public void setQueryBy(String queryBy) {
        this.queryBy = queryBy;
    }

    public Date getQueryDt() {
        return this.queryDt;
    }

    public void setQueryDt(Date queryDt) {
        this.queryDt = queryDt;
    }

    public String getQueryReason() {
        return this.queryReason;
    }

    public void setQueryReason(String queryReason) {
        this.queryReason = queryReason;
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
