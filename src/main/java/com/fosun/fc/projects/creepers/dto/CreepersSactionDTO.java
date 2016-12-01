package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: T_CREEPERS_SACTION 信用中国-行政处罚黑名单表
 * <p>
 * 
 * @author MaXin
 * @since 2016-11-01 11:24:29
 * @see
 */

public class CreepersSactionDTO extends CreepersBaseDTO {
   
    private static final long serialVersionUID = 2475595894004030964L;
    // 主体名称
    private String name;
    // 公示类型
    private String type;
    // 处罚内容
    private String content;
    // 行政类别/地域（全部）
    private String province;
    // 处罚更新日期
    private String modifyDt;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getModifyDt() {
        return modifyDt;
    }

    public void setModifyDt(String modifyDt) {
        this.modifyDt = modifyDt;
    }
}
