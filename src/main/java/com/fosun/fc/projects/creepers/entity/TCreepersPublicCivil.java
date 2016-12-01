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
 * The persistent class for the T_CREEPERS_PUBLIC_CIVIL database table.
 * 
 */
@Entity
@Table(name = "T_CREEPERS_PUBLIC_CIVIL")
@NamedQuery(name = "TCreepersPublicCivil.findAll", query = "SELECT t FROM TCreepersPublicCivil t")
public class TCreepersPublicCivil extends com.fosun.fc.modules.entity.BaseEntity {

    private static final long serialVersionUID = -564069723294509496L;

    @Id
    @SequenceGenerator(name = "T_CREEPERS_PUBLIC_CIVIL_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_PUBLIC_CIVIL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_PUBLIC_CIVIL_ID_GENERATOR")
    private Long id;

    @Column(name = "CASE_NO")
    private String caseNo;

    @Column(name = "CAUSE_ACTION")
    private String causeAction;

    @Column(name = "CLOSED_WAY")
    private String closedWay;

    @Temporal(TemporalType.DATE)
    @Column(name = "DECISION_DT")
    private Date decisionDt;

    @Column(name = "DECISION_RSLT")
    private String decisionRslt;

    @Column(name = "FILING_COURT")
    private String filingCourt;

    @Temporal(TemporalType.DATE)
    @Column(name = "FILING_DT")
    private Date filingDt;

    private String memo;

    @Column(name = "RPT_NO")
    private String rptNo;

    @Column(name = "SBUJECT_AMOUNT")
    private BigDecimal sbujectAmount;

    @Column(name = "SUBJECT_ACTION")
    private String subjectAction;

    @ManyToOne
    @JoinColumn(name = "RPT_NO", referencedColumnName = "RPT_NO", insertable = false, updatable = false)
    private TCreepersAccountBak TCreepersAccountBak;

    public TCreepersPublicCivil() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseNo() {
        return this.caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getCauseAction() {
        return this.causeAction;
    }

    public void setCauseAction(String causeAction) {
        this.causeAction = causeAction;
    }

    public String getClosedWay() {
        return this.closedWay;
    }

    public void setClosedWay(String closedWay) {
        this.closedWay = closedWay;
    }

    public Date getDecisionDt() {
        return this.decisionDt;
    }

    public void setDecisionDt(Date decisionDt) {
        this.decisionDt = decisionDt;
    }

    public String getDecisionRslt() {
        return this.decisionRslt;
    }

    public void setDecisionRslt(String decisionRslt) {
        this.decisionRslt = decisionRslt;
    }

    public String getFilingCourt() {
        return this.filingCourt;
    }

    public void setFilingCourt(String filingCourt) {
        this.filingCourt = filingCourt;
    }

    public Date getFilingDt() {
        return this.filingDt;
    }

    public void setFilingDt(Date filingDt) {
        this.filingDt = filingDt;
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

    public BigDecimal getSbujectAmount() {
        return this.sbujectAmount;
    }

    public void setSbujectAmount(BigDecimal sbujectAmount) {
        this.sbujectAmount = sbujectAmount;
    }

    public String getSubjectAction() {
        return this.subjectAction;
    }

    public void setSubjectAction(String subjectAction) {
        this.subjectAction = subjectAction;
    }

    public TCreepersAccountBak getTCreepersAccountBak() {
        return this.TCreepersAccountBak;
    }

    public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak) {
        this.TCreepersAccountBak = TCreepersAccountBak;
    }
}
