package com.fosun.fc.projects.creepers.entity;

import java.math.BigDecimal;
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
 * The persistent class for the T_CREEPERS_PUBLIC_ISP database table.
 * 
 */
@Entity
@Table(name = "T_CREEPERS_PUBLIC_ISP")
@NamedQuery(name = "TCreepersPublicIsp.findAll", query = "SELECT t FROM TCreepersPublicIsp t")
public class TCreepersPublicIsp extends com.fosun.fc.modules.entity.BaseEntity {

    private static final long serialVersionUID = -7123290368033653222L;

    @Id
    @SequenceGenerator(name = "T_CREEPERS_PUBLIC_ISP_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_PUBLIC_ISP")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_PUBLIC_ISP_ID_GENERATOR")
    private Long id;

    @Column(name = "ARREARS_AMOUNT")
    private BigDecimal arrearsAmount;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIZ_OPERATE_DT")
    private Date bizOperateDt;

    @Temporal(TemporalType.DATE)
    @Column(name = "JOURNAL_ENTRY")
    private Date journalEntry;

    private String memo;

    @Column(name = "RPT_NO")
    private String rptNo;

    @Column(name = "SERVICE_TYPE")
    private String serviceType;

    @Column(name = "TELE_OPERATORS")
    private String teleOperators;

    @ManyToOne
    @JoinColumn(name = "RPT_NO", referencedColumnName = "RPT_NO", insertable = false, updatable = false)
    private TCreepersAccountBak TCreepersAccountBak;

    public TCreepersPublicIsp() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getArrearsAmount() {
        return this.arrearsAmount;
    }

    public void setArrearsAmount(BigDecimal arrearsAmount) {
        this.arrearsAmount = arrearsAmount;
    }

    public Date getBizOperateDt() {
        return this.bizOperateDt;
    }

    public void setBizOperateDt(Date bizOperateDt) {
        this.bizOperateDt = bizOperateDt;
    }

    public Date getJournalEntry() {
        return this.journalEntry;
    }

    public void setJournalEntry(Date journalEntry) {
        this.journalEntry = journalEntry;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getRptNo() {
        return this.rptNo;
    }

    public void setRptNo(String rptNo) {
        this.rptNo = rptNo;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getTeleOperators() {
        return this.teleOperators;
    }

    public void setTeleOperators(String teleOperators) {
        this.teleOperators = teleOperators;
    }

    public TCreepersAccountBak getTCreepersAccountBak() {
        return this.TCreepersAccountBak;
    }

    public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak) {
        this.TCreepersAccountBak = TCreepersAccountBak;
    }
}
