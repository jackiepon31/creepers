package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: T_CREEPERS_COURT_DIS_INFO 最高法失信人黑名单明细表
 * <p>
 * 
 * @author MaXin
 * @since 2016-10-10 15:37:17
 * @see
 */

public class CreepersCourtDisInfoDTO extends CreepersBaseDTO {
    /**
     * 
     */
    private static final long serialVersionUID = -7239558918839840878L;
    // 个人/企业名称
    private String name;
    // 数据类别
    private String type;
    // 身份证号/组织机构代码
    private String code;
    // 地域名称
    private String province;
    // 来源
    private String source;
    // 案件号
    private String caseNo;
    // 企业法人姓名
    private String legalRep;
    // 执行法院
    private String court;
    // 法律生效文书确定的义务
    private String duty;
    // 被执行人的履行情况
    private String performance;
    // 失信被执行人具体情形
    private String specific;
    // 发布时间
    private String publishDt;
    // 执行依据文号
    private String implementNo;
    // 立案时间
    private String caseDt;
    // 作出执行依据单位
    private String implementUnit;
    // 已履行部分
    private String performY;
    // 未履行部分
    private String performN;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getLegalRep() {
        return legalRep;
    }

    public void setLegalRep(String legalRep) {
        this.legalRep = legalRep;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
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

    public String getSpecific() {
        return specific;
    }

    public void setSpecific(String specific) {
        this.specific = specific;
    }

    public String getPublishDt() {
        return publishDt;
    }

    public void setPublishDt(String publishDt) {
        this.publishDt = publishDt;
    }

    public String getImplementNo() {
        return implementNo;
    }

    public void setImplementNo(String implementNo) {
        this.implementNo = implementNo;
    }

    public String getCaseDt() {
        return caseDt;
    }

    public void setCaseDt(String caseDt) {
        this.caseDt = caseDt;
    }

    public String getImplementUnit() {
        return implementUnit;
    }

    public void setImplementUnit(String implementUnit) {
        this.implementUnit = implementUnit;
    }

    public String getPerformY() {
        return performY;
    }

    public void setPerformY(String performY) {
        this.performY = performY;
    }

    public String getPerformN() {
        return performN;
    }

    public void setPerformN(String performN) {
        this.performN = performN;
    }

}
