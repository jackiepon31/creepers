package com.fosun.fc.projects.creepers.dto;

import java.math.BigDecimal;

/**
 *
 * <p>
 * description: T_CREEPERS_INSURANCE_SUM 社保累计缴费信息表
 * <p>
 * 
 * @author MaXin
 * @since 2016-09-06 15:27:25
 * @see
 */

public class CreepersInsuranceSumDTO extends CreepersBaseDTO {
    /**
     * 
     */
    private static final long serialVersionUID = -5004117196473056418L;
    // 姓名
    private String name;
    // 证件号
    private String certNo;
    // 截止年月
    private String endDt;
    // 累计缴费月数
    private BigDecimal months;
    // 养老金本息总额
    private String endowmentSum;
    // 养老金总额个人部分
    private String endowmentSumPrivate;

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

    public String getEndDt() {
        return endDt;
    }

    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    public BigDecimal getMonths() {
        return months;
    }

    public void setMonths(BigDecimal months) {
        this.months = months;
    }

    public String getEndowmentSum() {
        return endowmentSum;
    }

    public void setEndowmentSum(String endowmentSum) {
        this.endowmentSum = endowmentSum;
    }

    public String getEndowmentSumPrivate() {
        return endowmentSumPrivate;
    }

    public void setEndowmentSumPrivate(String endowmentSumPrivate) {
        this.endowmentSumPrivate = endowmentSumPrivate;
    }

}
