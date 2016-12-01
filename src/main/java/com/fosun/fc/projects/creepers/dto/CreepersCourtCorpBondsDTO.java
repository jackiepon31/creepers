package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: T_CREEPERS_COURT_CORP_BONDS 限制发行企业债黑名单表
 * <p>
 * 
 * @author MaXin
 * @since 2016-10-10 15:37:17
 * @see
 */

public class CreepersCourtCorpBondsDTO extends CreepersBaseDTO {
    /**
     * 
     */
    private static final long serialVersionUID = -1272419974342681154L;
    // 企业名称
    private String name;
    // 组织机构代码
    private String code;
    // 归属区域
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
