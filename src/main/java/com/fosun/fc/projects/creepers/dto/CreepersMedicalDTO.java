package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: T_CREEPERS_MEDICAL 医药类信息
 * <p>
 * 
 * @author MaXin
 * @since 2016-11-22 13:27:43
 * @see
 */

public class CreepersMedicalDTO extends CreepersBaseDTO {
    private static final long serialVersionUID = 1L;
    // 信息类型（gmc_list:GMC认证、gsc_list:GSC认证、medical_instrument_list:药品器械注册、medical_operate_list:药品经营许可）
    private String type;
    // 唯一标识号
    private String key;
    // 信息内容（JSON格式）
    private String content;

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

}
