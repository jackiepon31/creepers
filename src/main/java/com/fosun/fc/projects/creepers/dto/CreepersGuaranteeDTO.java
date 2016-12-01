package com.fosun.fc.projects.creepers.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CreepersGuaranteeDTO extends CreepersBaseDTO {

    private static final long serialVersionUID = 7767738950555899100L;

    private String createdBy;

    @Temporal(TemporalType.DATE)
    private Date createdDt;

    private BigDecimal guaranteeContractAmount;

    private BigDecimal guaranteedPrincipalBalance;

    private BigDecimal guaranteetAmount;

    private String insuredIdNo;

    private String insuredIdType;

    private String insuredName;

    private String loanOrg;

    private String rptNo;

    @Temporal(TemporalType.DATE)
    private Date statisticalDt;

    private String updatedBy;

    @Temporal(TemporalType.DATE)
    private Date updatedDt;

    public CreepersGuaranteeDTO() {
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

    public BigDecimal getGuaranteeContractAmount() {
        return this.guaranteeContractAmount;
    }

    public void setGuaranteeContractAmount(BigDecimal guaranteeContractAmount) {
        this.guaranteeContractAmount = guaranteeContractAmount;
    }

    public BigDecimal getGuaranteedPrincipalBalance() {
        return this.guaranteedPrincipalBalance;
    }

    public void setGuaranteedPrincipalBalance(BigDecimal guaranteedPrincipalBalance) {
        this.guaranteedPrincipalBalance = guaranteedPrincipalBalance;
    }

    public BigDecimal getGuaranteetAmount() {
        return this.guaranteetAmount;
    }

    public void setGuaranteetAmount(BigDecimal guaranteetAmount) {
        this.guaranteetAmount = guaranteetAmount;
    }

    public String getInsuredIdNo() {
        return this.insuredIdNo;
    }

    public void setInsuredIdNo(String insuredIdNo) {
        this.insuredIdNo = insuredIdNo;
    }

    public String getInsuredIdType() {
        return this.insuredIdType;
    }

    public void setInsuredIdType(String insuredIdType) {
        this.insuredIdType = insuredIdType;
    }

    public String getInsuredName() {
        return this.insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getLoanOrg() {
        return this.loanOrg;
    }

    public void setLoanOrg(String loanOrg) {
        this.loanOrg = loanOrg;
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
