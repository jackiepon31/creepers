package com.fosun.fc.projects.creepers.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CreepersPublicEnforcementDTO extends CreepersBaseDTO {

    private static final long serialVersionUID = 4727133650994494220L;

    private String caseStatus;

    private String cause;

    private String closeWay;

    @Temporal(TemporalType.DATE)
    private Date closingDt;

    @Temporal(TemporalType.DATE)
    private Date compulsoryDt;

    private String createdBy;

    @Temporal(TemporalType.DATE)
    private Date createdDt;

    private String enforcementCase;

    private String enforcementCourt;

    private String enforcementSubject;

    private String executedSubject;

    private BigDecimal executedSubjectAmout;

    private String rptNo;

    private BigDecimal subjectAmount;

    private String updatedBy;

    @Temporal(TemporalType.DATE)
    private Date updatedDt;

    public CreepersPublicEnforcementDTO() {
    }

    public String getCaseStatus() {
        return this.caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getCause() {
        return this.cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getCloseWay() {
        return this.closeWay;
    }

    public void setCloseWay(String closeWay) {
        this.closeWay = closeWay;
    }

    public Date getClosingDt() {
        return this.closingDt;
    }

    public void setClosingDt(Date closingDt) {
        this.closingDt = closingDt;
    }

    public Date getCompulsoryDt() {
        return this.compulsoryDt;
    }

    public void setCompulsoryDt(Date compulsoryDt) {
        this.compulsoryDt = compulsoryDt;
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

    public String getEnforcementCase() {
        return this.enforcementCase;
    }

    public void setEnforcementCase(String enforcementCase) {
        this.enforcementCase = enforcementCase;
    }

    public String getEnforcementCourt() {
        return this.enforcementCourt;
    }

    public void setEnforcementCourt(String enforcementCourt) {
        this.enforcementCourt = enforcementCourt;
    }

    public String getEnforcementSubject() {
        return this.enforcementSubject;
    }

    public void setEnforcementSubject(String enforcementSubject) {
        this.enforcementSubject = enforcementSubject;
    }

    public String getExecutedSubject() {
        return this.executedSubject;
    }

    public void setExecutedSubject(String executedSubject) {
        this.executedSubject = executedSubject;
    }

    public BigDecimal getExecutedSubjectAmout() {
        return this.executedSubjectAmout;
    }

    public void setExecutedSubjectAmout(BigDecimal executedSubjectAmout) {
        this.executedSubjectAmout = executedSubjectAmout;
    }

    public String getRptNo() {
        return this.rptNo;
    }

    public void setRptNo(String rptNo) {
        this.rptNo = rptNo;
    }

    public BigDecimal getSubjectAmount() {
        return this.subjectAmount;
    }

    public void setSubjectAmount(BigDecimal subjectAmount) {
        this.subjectAmount = subjectAmount;
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
