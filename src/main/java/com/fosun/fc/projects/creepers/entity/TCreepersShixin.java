package com.fosun.fc.projects.creepers.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the T_CREEPERS_SHIXIN database table.
 * 
 */
@Entity
@Table(name = "T_CREEPERS_SHIXIN")
@NamedQuery(name = "TCreepersShixin.findAll", query = "SELECT t FROM TCreepersShixin t")
public class TCreepersShixin extends com.fosun.fc.modules.entity.BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 7117641567131984562L;

    @Id
    @SequenceGenerator(name = "T_CREEPERS_SHIXIN_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_SHIXIN")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_SHIXIN_ID_GENERATOR")
    private long id;

    @Column(name = "BEHAVIOR_DETAILS")
    private String behaviorDetails;

    @Temporal(TemporalType.DATE)
    @Column(name = "CASE_DT")
    private Date caseDt;

    @Column(name = "CASE_NO")
    private String caseNo;

    @Column(name = "CERT_NO")
    private String certNo;
    @Column(name = "DUTY")
    private String duty;

    @Column(name = "EXEC_COURT")
    private String execCourt;

    @Column(name = "JUDGEMENT_COURT")
    private String judgementCourt;

    @Column(name = "JUDGEMENT_NO")
    private String judgementNo;

    @Column(name = "LEGAL_REP")
    private String legalRep;
    
    @Column(name = "MEMO")
    private String memo;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "PERFORMANCE")
    private String performance;
    
    @Column(name = "PROVINCE")
    private String province;

    @Temporal(TemporalType.DATE)
    @Column(name = "PUBLISH_DT")
    private Date publishDt;

    public TCreepersShixin() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBehaviorDetails() {
        return this.behaviorDetails;
    }

    public void setBehaviorDetails(String behaviorDetails) {
        this.behaviorDetails = behaviorDetails;
    }

    public Date getCaseDt() {
        return this.caseDt;
    }

    public void setCaseDt(Date caseDt) {
        this.caseDt = caseDt;
    }

    public String getCaseNo() {
        return this.caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getCertNo() {
        return this.certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getDuty() {
        return this.duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getExecCourt() {
        return this.execCourt;
    }

    public void setExecCourt(String execCourt) {
        this.execCourt = execCourt;
    }

    public String getJudgementCourt() {
        return this.judgementCourt;
    }

    public void setJudgementCourt(String judgementCourt) {
        this.judgementCourt = judgementCourt;
    }

    public String getJudgementNo() {
        return this.judgementNo;
    }

    public void setJudgementNo(String judgementNo) {
        this.judgementNo = judgementNo;
    }

    public String getLegalRep() {
        return this.legalRep;
    }

    public void setLegalRep(String legalRep) {
        this.legalRep = legalRep;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerformance() {
        return this.performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Date getPublishDt() {
        return this.publishDt;
    }

    public void setPublishDt(Date publishDt) {
        this.publishDt = publishDt;
    }

}
