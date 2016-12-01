package com.fosun.fc.projects.creepers.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CreepersHlDetailDTO extends CreepersBaseDTO {

    private static final long serialVersionUID = -4035770026087316663L;

    private BigDecimal balance;

    private String createdBy;

    @Temporal(TemporalType.DATE)
    private Date createdDt;

    private String currencyType;

    @Temporal(TemporalType.DATE)
    private Date grantDt;

    private String grantOrg;

    private BigDecimal hlOverdraftNinety;

    private BigDecimal hlOverdraftSixty;

    private String hlStatus;

    private BigDecimal loanAmount;

    @Temporal(TemporalType.DATE)
    private Date loanMaturityDt;

    private String loanType;

    private BigDecimal overdueAmount;

    private String rptNo;

    @Temporal(TemporalType.DATE)
    private Date statisticalDt;

    private String updatedBy;

    @Temporal(TemporalType.DATE)
    private Date updatedDt;

    public CreepersHlDetailDTO() {
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDt() {
        return this.createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
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

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDt() {
        return this.updatedDt;
    }

    public void setUpdatedDt(Date updatedDt) {
        this.updatedDt = updatedDt;
    }

}
