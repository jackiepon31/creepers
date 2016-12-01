package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: T_CREEPERS_INSURANCE_UNIT 公积金账户信息表
 * <p>
 * 
 * @author MaXin
 * @since 2016-09-06 15:27:25
 * @see
 */

public class CreepersInsuranceUnitDTO extends CreepersBaseDTO {
    /**
     * 
     */
    private static final long serialVersionUID = -5288458513306160171L;
    // 姓名
    private String name;
    // 身份证号
    private String certNo;
    // 所属单位
    private String unit;
    // 起始日期
    private String period;

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
