package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: T_CREEPERS_LIST_FUND 爬虫任务队列-公积金信息
 * <p>
 * 
 * @author MaXin
 * @since 2016-09-06 14:47:06
 * @see
 */

public class CreepersListFundDTO extends CreepersBaseDTO {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // 用户代码（公积金账号）
    private String userCode;
    // 备注信息
    private String memo;
    //密码
    private String password;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
