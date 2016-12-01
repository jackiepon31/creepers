package com.fosun.fc.projects.creepers.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CreepersPublicTaxDTO extends CreepersBaseDTO {

    private static final long serialVersionUID = -6902038761180263005L;

    private String competentTaxAuthority;

    private String createdBy;

    @Temporal(TemporalType.DATE)
    private Date createdDt;

    private String rptNo;

    private BigDecimal taxAmount;

    @Temporal(TemporalType.DATE)
    private Date taxStatisticDt;

    private String taxpayerNo;

    private String updatedBy;

    @Temporal(TemporalType.DATE)
    private Date updatedDt;

    public CreepersPublicTaxDTO() {
    }

    public String getCompetentTaxAuthority() {
        return this.competentTaxAuthority;
    }

    public void setCompetentTaxAuthority(String competentTaxAuthority) {
        this.competentTaxAuthority = competentTaxAuthority;
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

    public String getRptNo() {
        return this.rptNo;
    }

    public void setRptNo(String rptNo) {
        this.rptNo = rptNo;
    }

    public BigDecimal getTaxAmount() {
        return this.taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Date getTaxStatisticDt() {
        return this.taxStatisticDt;
    }

    public void setTaxStatisticDt(Date taxStatisticDt) {
        this.taxStatisticDt = taxStatisticDt;
    }

    public String getTaxpayerNo() {
        return this.taxpayerNo;
    }

    public void setTaxpayerNo(String taxpayerNo) {
        this.taxpayerNo = taxpayerNo;
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
