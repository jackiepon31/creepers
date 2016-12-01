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
 * The persistent class for the T_CREEPERS_CC_DETAIL database table.
 * 
 */
@Entity
@Table(name = "T_CREEPERS_CC_DETAIL")
@NamedQuery(name = "TCreepersCcDetail.findAll", query = "SELECT t FROM TCreepersCcDetail t")
public class TCreepersCcDetail extends com.fosun.fc.modules.entity.BaseEntity {

    private static final long serialVersionUID = 4649429237790012778L;

    @Id
    @SequenceGenerator(name = "T_CREEPERS_CC_DETAIL_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_CC_DETAIL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_CC_DETAIL_ID_GENERATOR")
    private Long id;

    @Column(name = "ACCOUNT_TYPE")
    private String accountType;

    @Column(name = "CC_OVERDRAFT_NINETY")
    private BigDecimal ccOverdraftNinety;

    @Column(name = "CC_OVERDRAFT_SIXTY")
    private BigDecimal ccOverdraftSixty;

    @Column(name = "CC_STATUS")
    private String ccStatus;

    @Column(name = "CC_TYPE")
    private String ccType;

    @Column(name = "CREDIT_LIMIT")
    private String creditLimit;

    @Temporal(TemporalType.DATE)
    @Column(name = "GRANT_DT")
    private Date grantDt;

    @Column(name = "GRANT_ORG")
    private String grantOrg;

    private String memo;

    @Column(name = "OVERDRAFT_BALANCE")
    private BigDecimal overdraftBalance;

    @Column(name = "OVERDUE_AMOUNT")
    private BigDecimal overdueAmount;

    @Column(name = "RPT_NO")
    private String rptNo;

    @Temporal(TemporalType.DATE)
    @Column(name = "STATISTICAL_DT")
    private Date statisticalDt;

    @ManyToOne
    @JoinColumn(name = "RPT_NO", referencedColumnName = "RPT_NO", insertable = false, updatable = false)
    private TCreepersAccountBak TCreepersAccountBak;

    public TCreepersCcDetail() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getCcOverdraftNinety() {
        return this.ccOverdraftNinety;
    }

    public void setCcOverdraftNinety(BigDecimal ccOverdraftNinety) {
        this.ccOverdraftNinety = ccOverdraftNinety;
    }

    public BigDecimal getCcOverdraftSixty() {
        return this.ccOverdraftSixty;
    }

    public void setCcOverdraftSixty(BigDecimal ccOverdraftSixty) {
        this.ccOverdraftSixty = ccOverdraftSixty;
    }

    public String getCcStatus() {
        return this.ccStatus;
    }

    public void setCcStatus(String ccStatus) {
        this.ccStatus = ccStatus;
    }

    public String getCcType() {
        return this.ccType;
    }

    public void setCcType(String ccType) {
        this.ccType = ccType;
    }

    public String getCreditLimit() {
        return this.creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Date getGrantDt() {
        return this.grantDt;
    }

    public void setGrantDt(Date grantDt) {
        this.grantDt = grantDt;
    }

    public String getGrantOrg() {
        return this.grantOrg;
    }

    public void setGrantOrg(String grantOrg) {
        this.grantOrg = grantOrg;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public BigDecimal getOverdraftBalance() {
        return this.overdraftBalance;
    }

    public void setOverdraftBalance(BigDecimal overdraftBalance) {
        this.overdraftBalance = overdraftBalance;
    }

    public BigDecimal getOverdueAmount() {
        return this.overdueAmount;
    }

    public void setOverdueAmount(BigDecimal overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    public String getRptNo() {
        return this.rptNo;
    }

    public void setRptNo(String rptNo) {
        this.rptNo = rptNo;
    }

    public Date getStatisticalDt() {
        return this.statisticalDt;
    }

    public void setStatisticalDt(Date statisticalDt) {
        this.statisticalDt = statisticalDt;
    }

    public TCreepersAccountBak getTCreepersAccountBak() {
        return this.TCreepersAccountBak;
    }

    public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak) {
        this.TCreepersAccountBak = TCreepersAccountBak;
    }
}
