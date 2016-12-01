package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: T_CREEPERS_LIST_INSURANCE 爬虫任务队列-社保信息
 * <p>
 * 
 * @author MaXin
 * @since 2016-09-06 15:27:25
 * @see
 */

public class CreepersListInsuranceDTO extends CreepersBaseDTO {
    /**
     * 
     */
    private static final long serialVersionUID = 4602555850080639800L;
    // 用户代码（身份证）
    private String userCode;
    
    private String password;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
