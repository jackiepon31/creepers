package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: T_CREEPERS_INSURANCE_BASIC 社保基础信息表
 * <p>
 * 
 * @author MaXin
 * @since 2016-09-06 15:27:25
 * @see
 */

public class CreepersInsuranceBasicDTO extends CreepersBaseDTO {
    /**
     * 
     */
    private static final long serialVersionUID = 3529969378186305190L;
    // 姓名
    private String name;
    // 证件号
    private String certNo;
    // 92年底前连续工龄
    private String workTime;
    // 密码
    private String password;

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

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
