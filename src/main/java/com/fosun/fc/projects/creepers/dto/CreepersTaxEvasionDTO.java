package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: T_CREEPERS_TAX_EVASION 信用中国-行政处罚黑名单
 * <p>
 * 
 * @author MaXin
 * @since 2016-11-01 11:24:29
 * @see
 */

public class CreepersTaxEvasionDTO extends CreepersBaseDTO {

    private static final long serialVersionUID = 457352750581770496L;
    // 主体名称
    private String name;
    // 组织机构/身份证号
    private String code;
    // 行政类别/地域（全部）
    private String province;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
