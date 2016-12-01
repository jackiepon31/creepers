package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: T_CREEPERS_SHIXIN_ALL 信用中国-失信人被执行人黑名单主表
 * <p>
 * 
 * @author MaXin
 * @since 2016-11-01 11:24:29
 * @see
 */

public class CreepersShixinAllDTO extends CreepersBaseDTO {
    
    private static final long serialVersionUID = -3077910704607227949L;
    // 主体类型（法人）
    private String name;
    // 组织机构/身份证号
    private String code;
    // 归属地址
    private String province;
    // 优良记录数
    private String goodRecord;
    // 不良记录数
    private String badRecord;
    // 失信记录数
    private String disRecord;
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
    public String getGoodRecord() {
        return goodRecord;
    }
    public void setGoodRecord(String goodRecord) {
        this.goodRecord = goodRecord;
    }
    public String getBadRecord() {
        return badRecord;
    }
    public void setBadRecord(String badRecord) {
        this.badRecord = badRecord;
    }
    public String getDisRecord() {
        return disRecord;
    }
    public void setDisRecord(String disRecord) {
        this.disRecord = disRecord;
    }
}
