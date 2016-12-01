package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: T_CREEPERS_INSURANCE_PAYMENT 社保应缴记录表
 * <p>
 * 
 * @author MaXin
 * @since 2016-09-06 15:27:25
 * @see
 */

public class CreepersInsurancePaymentDTO extends CreepersBaseDTO {
    /**
     * 
     */
    private static final long serialVersionUID = -7007004184005485934L;
    // 姓名
    private String name;
    // 证件号
    private String certNo;
    // 年月
    private String paymentDt;
    // 缴费基数
    private String paymentBase;
    // 养保个人缴费额
    private String endowment;
    // 医保个人缴费额
    private String medical;
    // 失保个人缴费额
    private String unemployment;

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

    public String getPaymentDt() {
        return paymentDt;
    }

    public void setPaymentDt(String paymentDt) {
        this.paymentDt = paymentDt;
    }

    public String getPaymentBase() {
        return paymentBase;
    }

    public void setPaymentBase(String paymentBase) {
        this.paymentBase = paymentBase;
    }

    public String getEndowment() {
        return endowment;
    }

    public void setEndowment(String endowment) {
        this.endowment = endowment;
    }

    public String getMedical() {
        return medical;
    }

    public void setMedical(String medical) {
        this.medical = medical;
    }

    public String getUnemployment() {
        return unemployment;
    }

    public void setUnemployment(String unemployment) {
        this.unemployment = unemployment;
    }

}
