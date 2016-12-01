package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: T_CREEPERS_TAX_EVASION_DETAIL 信用中国-偷税漏税黑名单明细
 * <p>
 * 
 * @author MaXin
 * @since 2016-11-01 11:24:29
 * @see
 */

public class CreepersTaxEvasionDetailDTO extends CreepersBaseDTO {

    private static final long serialVersionUID = 3486306353418598992L;
    // 数据来源
    private String source;
    // 数据类别
    private String type;
    // 省份
    private String province;
    // 检察机关
    private String court;
    // 纳税人名称
    private String name;
    // 纳税人识别码
    private String taxNo;
    // 身份证号/组织机构代码
    private String code;
    // 注册地址
    private String addr;
    // 法定代表人或者负责人姓名
    private String legalRep;
    // 性别
    private String legalSex;
    // 法定代表人或者负责人证件名称
    private String certName;
    // 负有直接责任的财务负责人姓名
    private String financialName;
    // 负有直接责任的财务负责人性别
    private String financialSex;
    // 负有直接责任的财务负责人证件名称
    private String financialCertName;
    // 负有直接责任的中介机构信息
    private String interInfo;
    // 案件性质
    private String caseNature;
    // 主要违法事实
    private String illegalFacts;
    // 相关法律依据及处理处罚情况
    private String punishInfo;
    // 发布级别
    private String publishLevel;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getLegalRep() {
        return legalRep;
    }

    public void setLegalRep(String legalRep) {
        this.legalRep = legalRep;
    }

    public String getLegalSex() {
        return legalSex;
    }

    public void setLegalSex(String legalSex) {
        this.legalSex = legalSex;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getFinancialName() {
        return financialName;
    }

    public void setFinancialName(String financialName) {
        this.financialName = financialName;
    }

    public String getFinancialSex() {
        return financialSex;
    }

    public void setFinancialSex(String financialSex) {
        this.financialSex = financialSex;
    }

    public String getFinancialCertName() {
        return financialCertName;
    }

    public void setFinancialCertName(String financialCertName) {
        this.financialCertName = financialCertName;
    }

    public String getInterInfo() {
        return interInfo;
    }

    public void setInterInfo(String interInfo) {
        this.interInfo = interInfo;
    }

    public String getCaseNature() {
        return caseNature;
    }

    public void setCaseNature(String caseNature) {
        this.caseNature = caseNature;
    }

    public String getIllegalFacts() {
        return illegalFacts;
    }

    public void setIllegalFacts(String illegalFacts) {
        this.illegalFacts = illegalFacts;
    }

    public String getPunishInfo() {
        return punishInfo;
    }

    public void setPunishInfo(String punishInfo) {
        this.punishInfo = punishInfo;
    }

    public String getPublishLevel() {
        return publishLevel;
    }

    public void setPublishLevel(String publishLevel) {
        this.publishLevel = publishLevel;
    }

}
