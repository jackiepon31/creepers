package com.fosun.fc.projects.creepers.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CreepersGeneralDTO extends CreepersBaseDTO {

    private static final long serialVersionUID = -1906963202956011745L;

    private BigDecimal assetDisposalNo;

    private BigDecimal ccNo;

    private String createdBy;

    @Temporal(TemporalType.DATE)
    private Date createdDt;

    private BigDecimal guaranteeCcNo;

    private BigDecimal guaranteeHousingLoanNo;

    private BigDecimal guaranteeOtherLoanNo;

    private BigDecimal guarantorCompensationNo;

    private BigDecimal housingLoanNo;

    private BigDecimal ninetyCcNo;

    private BigDecimal ninetyHousingLoanNo;

    private BigDecimal ninetyOtherLoanNo;

    private BigDecimal otherLoanNo;

    private BigDecimal outstandingCcNo;

    private BigDecimal outstandingHousingLoanNo;

    private BigDecimal outstandingOtherLoanNo;

    private BigDecimal overdueCcNo;

    private BigDecimal overdueHousingLoanNo;

    private BigDecimal overdueOtherLoanNo;

    private String rptNo;

    private String updatedBy;

    @Temporal(TemporalType.DATE)
    private Date updatedDt;

    public CreepersGeneralDTO() {
    }

    public BigDecimal getAssetDisposalNo() {
        return this.assetDisposalNo;
    }

    public void setAssetDisposalNo(BigDecimal assetDisposalNo) {
        this.assetDisposalNo = assetDisposalNo;
    }

    public BigDecimal getCcNo() {
        return this.ccNo;
    }

    public void setCcNo(BigDecimal ccNo) {
        this.ccNo = ccNo;
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

    public BigDecimal getGuaranteeCcNo() {
        return this.guaranteeCcNo;
    }

    public void setGuaranteeCcNo(BigDecimal guaranteeCcNo) {
        this.guaranteeCcNo = guaranteeCcNo;
    }

    public BigDecimal getGuaranteeHousingLoanNo() {
        return this.guaranteeHousingLoanNo;
    }

    public void setGuaranteeHousingLoanNo(BigDecimal guaranteeHousingLoanNo) {
        this.guaranteeHousingLoanNo = guaranteeHousingLoanNo;
    }

    public BigDecimal getGuaranteeOtherLoanNo() {
        return this.guaranteeOtherLoanNo;
    }

    public void setGuaranteeOtherLoanNo(BigDecimal guaranteeOtherLoanNo) {
        this.guaranteeOtherLoanNo = guaranteeOtherLoanNo;
    }

    public BigDecimal getGuarantorCompensationNo() {
        return this.guarantorCompensationNo;
    }

    public void setGuarantorCompensationNo(BigDecimal guarantorCompensationNo) {
        this.guarantorCompensationNo = guarantorCompensationNo;
    }

    public BigDecimal getHousingLoanNo() {
        return this.housingLoanNo;
    }

    public void setHousingLoanNo(BigDecimal housingLoanNo) {
        this.housingLoanNo = housingLoanNo;
    }

    public BigDecimal getNinetyCcNo() {
        return this.ninetyCcNo;
    }

    public void setNinetyCcNo(BigDecimal ninetyCcNo) {
        this.ninetyCcNo = ninetyCcNo;
    }

    public BigDecimal getNinetyHousingLoanNo() {
        return this.ninetyHousingLoanNo;
    }

    public void setNinetyHousingLoanNo(BigDecimal ninetyHousingLoanNo) {
        this.ninetyHousingLoanNo = ninetyHousingLoanNo;
    }

    public BigDecimal getNinetyOtherLoanNo() {
        return this.ninetyOtherLoanNo;
    }

    public void setNinetyOtherLoanNo(BigDecimal ninetyOtherLoanNo) {
        this.ninetyOtherLoanNo = ninetyOtherLoanNo;
    }

    public BigDecimal getOtherLoanNo() {
        return this.otherLoanNo;
    }

    public void setOtherLoanNo(BigDecimal otherLoanNo) {
        this.otherLoanNo = otherLoanNo;
    }

    public BigDecimal getOutstandingCcNo() {
        return this.outstandingCcNo;
    }

    public void setOutstandingCcNo(BigDecimal outstandingCcNo) {
        this.outstandingCcNo = outstandingCcNo;
    }

    public BigDecimal getOutstandingHousingLoanNo() {
        return this.outstandingHousingLoanNo;
    }

    public void setOutstandingHousingLoanNo(BigDecimal outstandingHousingLoanNo) {
        this.outstandingHousingLoanNo = outstandingHousingLoanNo;
    }

    public BigDecimal getOutstandingOtherLoanNo() {
        return this.outstandingOtherLoanNo;
    }

    public void setOutstandingOtherLoanNo(BigDecimal outstandingOtherLoanNo) {
        this.outstandingOtherLoanNo = outstandingOtherLoanNo;
    }

    public BigDecimal getOverdueCcNo() {
        return this.overdueCcNo;
    }

    public void setOverdueCcNo(BigDecimal overdueCcNo) {
        this.overdueCcNo = overdueCcNo;
    }

    public BigDecimal getOverdueHousingLoanNo() {
        return this.overdueHousingLoanNo;
    }

    public void setOverdueHousingLoanNo(BigDecimal overdueHousingLoanNo) {
        this.overdueHousingLoanNo = overdueHousingLoanNo;
    }

    public BigDecimal getOverdueOtherLoanNo() {
        return this.overdueOtherLoanNo;
    }

    public void setOverdueOtherLoanNo(BigDecimal overdueOtherLoanNo) {
        this.overdueOtherLoanNo = overdueOtherLoanNo;
    }

    public String getRptNo() {
        return this.rptNo;
    }

    public void setRptNo(String rptNo) {
        this.rptNo = rptNo;
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
