package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: T_CREEPERS_COURT_DISHONEST 最高法失信人黑名单主表
 * <p>
 * 
 * @author MaXin
 * @since 2016-10-10 15:37:17
 * @see
 */

public class CreepersCourtDishonestDTO extends CreepersBaseDTO {
    /**
     * 
     */
    private static final long serialVersionUID = 6434247508358327202L;
    // 个人/企业
    private String name;
    // 身份证号/代码
    private String code;
    // 省份
    private String province;
    // 来源
    private String source;

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
