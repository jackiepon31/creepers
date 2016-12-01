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
 * The persistent class for the T_CREEPERS_HL_DETAIL database table.
 * 
 */
@Entity
@Table(name = "T_CREEPERS_HL_DETAIL")
@NamedQuery(name = "TCreepersHlDetail.findAll", query = "SELECT t FROM TCreepersHlDetail t")
public class TCreepersHlDetail extends com.fosun.fc.modules.entity.BaseEntity {

    private static final long serialVersionUID = -4489173912012149678L;

    @Id
    @SequenceGenerator(name = "T_CREEPERS_HL_DETAIL_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_HL_DETAIL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_HL_DETAIL_ID_GENERATOR")
    private Long id;

    private BigDecimal balance;

    @Column(name = "CURRENCY_TYPE")
    private String currencyType;

    @Temporal(TemporalType.DATE)
    @Column(name = "GRANT_DT")
    private Date grantDt;

    @Column(name = "GRANT_ORG")
    private String grantOrg;

    @Column(name = "HL_OVERDRAFT_NINETY")
    private BigDecimal hlOverdraftNinety;

    @Column(name = "HL_OVERDRAFT_SIXTY")
    private BigDecimal hlOverdraftSixty;

    @Column(name = "HL_STATUS")
    private String hlStatus;

    @Column(name = "LOAN_AMOUNT")
    private BigDecimal loanAmount;

    @Temporal(TemporalType.DATE)
    @Column(name = "LOAN_MATURITY_DT")
    private Date loanMaturityDt;

    @Column(name = "LOAN_TYPE")
    private String loanType;

    private String memo;

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

    public TCreepersHlDetail() {
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

    public String getCurrencyType() {
        return this.currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
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

    public BigDecimal getHlOverdraftNinety() {
        return this.hlOverdraftNinety;
    }

    public void setHlOverdraftNinety(BigDecimal hlOverdraftNinety) {
        this.hlOverdraftNinety = hlOverdraftNinety;
    }

    public BigDecimal getHlOverdraftSixty() {
        return this.hlOverdraftSixty;
    }

    public void setHlOverdraftSixty(BigDecimal hlOverdraftSixty) {
        this.hlOverdraftSixty = hlOverdraftSixty;
    }

    public String getHlStatus() {
        return this.hlStatus;
    }

    public void setHlStatus(String hlStatus) {
        this.hlStatus = hlStatus;
    }

    public BigDecimal getLoanAmount() {
        return this.loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Date getLoanMaturityDt() {
        return this.loanMaturityDt;
    }

    public void setLoanMaturityDt(Date loanMaturityDt) {
        this.loanMaturityDt = loanMaturityDt;
    }

    public String getLoanType() {
        return this.loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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
