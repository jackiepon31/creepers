package com.fosun.fc.projects.creepers.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CreepersPublicIspDTO extends CreepersBaseDTO {

    private static final long serialVersionUID = 2555079653075829374L;

    private BigDecimal arrearsAmount;

    @Temporal(TemporalType.DATE)
    private Date bizOperateDt;

    private String createdBy;

    @Temporal(TemporalType.DATE)
    private Date createdDt;

    @Temporal(TemporalType.DATE)
    private Date journalEntry;

    private String rptNo;

    private String serviceType;

    private String teleOperators;

    private String updatedBy;

    @Temporal(TemporalType.DATE)
    private Date updatedDt;

    public CreepersPublicIspDTO() {
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

    public Date getJournalEntry() {
        return this.journalEntry;
    }

    public void setJournalEntry(Date journalEntry) {
        this.journalEntry = journalEntry;
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
