package com.fosun.fc.projects.creepers.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * <p>
 * description: ﻿T_CREEPERS_SHIXIN 爬虫信息-失信被执行人信息
 * <p>
 * 
 * @author LiZhanPing
 * @since 2016-08-31 22:27:48
 * @see
 */

public class CreepersShixinDTO extends CreepersBaseDTO {

    private static final long serialVersionUID = -7749541630297161545L;
    // 被执行人姓名/名称
    private String name;
    // 身份证号码/组织机构代码证/工商注册号
    private String certNo;
    // 法定代表人或者负责人姓名
    private String legalRep;
    // 执行法院
    private String execCourt;
    // 省份
    private String province;
    // 执行依据文号
    private String judgementNo;
    // 立案时间
    @Temporal(TemporalType.DATE)
    private Date caseDt;
    // 案号
    private String caseNo;
    // 作出执行依据单位
    private String judgementCourt;
    // 生效法律文书确定的义务
    private String duty;
    // 被执行人的履行情况
    private String performance;
    // 失信被执行人行为具体情形
    private String behaviorDetails;
    // 发布日期
    @Temporal(TemporalType.DATE)
    private Date publishDt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getLegalRep() {
        return legalRep;
    }

    public void setLegalRep(String legalRep) {
        this.legalRep = legalRep;
    }

    public String getExecCourt() {
        return execCourt;
    }

    public void setExecCourt(String execCourt) {
        this.execCourt = execCourt;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getJudgementNo() {
        return judgementNo;
    }

    public void setJudgementNo(String judgementNo) {
        this.judgementNo = judgementNo;
    }

    public Date getCaseDt() {
        return caseDt;
    }

    public void setCaseDt(Date caseDt) {
        this.caseDt = caseDt;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getJudgementCourt() {
        return judgementCourt;
    }

    public void setJudgementCourt(String judgementCourt) {
        this.judgementCourt = judgementCourt;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getBehaviorDetails() {
        return behaviorDetails;
    }

    public void setBehaviorDetails(String behaviorDetails) {
        this.behaviorDetails = behaviorDetails;
    }

    public Date getPublishDt() {
        return publishDt;
    }

    public void setPublishDt(Date publishDt) {
        this.publishDt = publishDt;
    }

}
