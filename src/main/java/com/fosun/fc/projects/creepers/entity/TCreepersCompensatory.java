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
 * The persistent class for the T_CREEPERS_COMPENSATORY database table.
 * 
 */
@Entity
@Table(name = "T_CREEPERS_COMPENSATORY")
@NamedQuery(name = "TCreepersCompensatory.findAll", query = "SELECT t FROM TCreepersCompensatory t")
public class TCreepersCompensatory extends com.fosun.fc.modules.entity.BaseEntity {

    private static final long serialVersionUID = 6892713985173515422L;

    @Id
    @SequenceGenerator(name = "T_CREEPERS_COMPENSATORY_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_COMPENSATORY")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_COMPENSATORY_ID_GENERATOR")
    private Long id;

    private BigDecimal balance;

    @Column(name = "COMPENSATION_AMOUNT")
    private BigDecimal compensationAmount;

    @Column(name = "COMPENSATORY_ORG")
    private String compensatoryOrg;

    @Column(name = "LAST_BACK_DT")
    private BigDecimal lastBackDt;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_COMPENSATORY_DT")
    private Date lastCompensatoryDt;

    private String memo;

    @Column(name = "RPT_NO")
    private String rptNo;

    @ManyToOne
    @JoinColumn(name = "RPT_NO", referencedColumnName = "RPT_NO", insertable = false, updatable = false)
    private TCreepersAccountBak TCreepersAccountBak;

    public TCreepersCompensatory() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getCompensationAmount() {
        return this.compensationAmount;
    }

    public void setCompensationAmount(BigDecimal compensationAmount) {
        this.compensationAmount = compensationAmount;
    }

    public String getCompensatoryOrg() {
        return this.compensatoryOrg;
    }

    public void setCompensatoryOrg(String compensatoryOrg) {
        this.compensatoryOrg = compensatoryOrg;
    }

    public BigDecimal getLastBackDt() {
        return this.lastBackDt;
    }

    public void setLastBackDt(BigDecimal lastBackDt) {
        this.lastBackDt = lastBackDt;
    }

    public Date getLastCompensatoryDt() {
        return this.lastCompensatoryDt;
    }

    public void setLastCompensatoryDt(Date lastCompensatoryDt) {
        this.lastCompensatoryDt = lastCompensatoryDt;
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

    public TCreepersAccountBak getTCreepersAccountBak() {
        return this.TCreepersAccountBak;
    }

    public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak) {
        this.TCreepersAccountBak = TCreepersAccountBak;
    }
}
