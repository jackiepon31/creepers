package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: T_CREEPERS_LIST_CREDIT 爬虫任务队列-人行征信报告
 * <p>
 * 
 * @author MaXin
 * @since 2016-10-19 14:45:23
 * @see
 */

public class CreepersListCreditDTO extends CreepersBaseDTO {
    private static final long serialVersionUID = 1L;
    // 用户代码
    private String userCode;
    // 用户密码
    private String password;
    // 短信验证码
    private String messageCode;
    // 报告图片链接
    private String imageUrl;
    // 报告网页链接
    private String htmlUrl;

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

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

}
