package com.fosun.fc.projects.creepers.dto;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * <p>
 * description:
 * 
 * 登录类的爬取 用这个paramDTO
 * 
 * 登录类的目前遇到的所需参数信息的都可以在这里渠道
 * 
 * 使用protected类型便于子类继承
 * 
 * </p>
 * 
 * @author MaXin
 * @since 2016年9月21日
 * @see
 */
public class CreepersLoginParamDTO extends CreepersParamDTO {

    private static final long serialVersionUID = 1247715780978232935L;

    // 登录类的
    protected String loginName = "";// 用户登陆名
    protected String loginNameKey = "";// 用户登陆名在form表单中提交的key
    protected String password = "";// 密码
    protected String passwordKey = "";// 密码在form表单中提交的key
    protected String captchaKey = "";// 验证码在form表单中提交的key
    protected String captchaValue = "default";// 验证码值
    protected String captchaId = "";// 验证码超人打码的图片ID
    protected String captchaFilePath = "";// 验证码图片路径--用于识别成功后，对文件重命名使用。
    protected String messageCaptchaKey = "";// 短信验证码key
    protected String messageCaptchaValue = "default";// 短信验证码value

    /**
     * 用户登陆名
     * 
     * @return the loginName
     */
    public String getLoginName() {
        if (StringUtils.isBlank(loginName)) {
            return "";
        } else {
            return loginName;
        }
    }

    /**
     * 用户登陆名
     * 
     * @param loginName
     *            the loginName to set
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 用户登陆名在form表单中提交的key
     * 
     * @return the loginNameKey
     */
    public String getLoginNameKey() {
        if (StringUtils.isBlank(loginNameKey)) {
            return "";
        } else {
            return loginNameKey;
        }
    }

    /**
     * 用户登陆名在form表单中提交的key
     * 
     * @param loginNameKey
     *            the loginNameKey to set
     */
    public void setLoginNameKey(String loginNameKey) {
        this.loginNameKey = loginNameKey;
    }

    /**
     * 密码
     * 
     * @return the password
     */
    public String getPassword() {
        if (StringUtils.isBlank(password)) {
            return "";
        } else {
            return password;
        }
    }

    /**
     * 密码
     * 
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the passwordKey
     */
    public String getPasswordKey() {
        if (StringUtils.isBlank(passwordKey)) {
            return "";
        } else {
            return passwordKey;
        }
    }

    /**
     * 密码在form表单中提交的key
     * 
     * @param passwordKey
     *            the passwordKey to set
     */
    public void setPasswordKey(String passwordKey) {
        this.passwordKey = passwordKey;
    }

    /**
     * 密码在form表单中提交的key
     * 
     * @return the captchaKey
     */
    public String getCaptchaKey() {
        if (StringUtils.isBlank(captchaKey)) {
            return "";
        } else {
            return captchaKey;
        }
    }

    /**
     * 验证码在form表单中提交的key
     * 
     * @param captchaKey
     *            the captchaKey to set
     */
    public void setCaptchaKey(String captchaKey) {
        this.captchaKey = captchaKey;
    }

    /**
     * 验证码值
     * 
     * @return the captchaValue
     */
    public String getCaptchaValue() {
        if (StringUtils.isBlank(captchaValue)) {
            return "";
        } else {
            return captchaValue;
        }
    }

    /**
     * 验证码值
     * 
     * @param captchaValue
     *            the captchaValue to set
     */
    public void setCaptchaValue(String captchaValue) {
        this.captchaValue = captchaValue;
    }

    /**
     * 验证码超人打码的图片ID
     * 
     * @return the captchaId
     */
    public String getCaptchaId() {
        return captchaId;
    }

    /**
     * 验证码超人打码的图片ID
     * 
     * @param captchaId
     *            the captchaId to set
     */
    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    /**
     * 验证码图片路径
     * 
     * @return the captchaFilePath
     */
    public String getCaptchaFilePath() {
        return captchaFilePath;
    }

    /**
     * 验证码图片路径
     * 
     * @param captchaFilePath
     *            the captchaFilePath to set
     */
    public void setCaptchaFilePath(String captchaFilePath) {
        this.captchaFilePath = captchaFilePath;
    }

    /**
     * 短信验证码key
     * 
     * @return the messageCaptchaKey
     */
    public String getMessageCaptchaKey() {
        if (StringUtils.isBlank(messageCaptchaKey)) {
            return "";
        } else {
            return messageCaptchaKey;
        }
    }

    /**
     * 短信验证码key
     * 
     * @param messageCaptchaKey
     *            the messageCaptchaKey to set
     */
    public void setMessageCaptchaKey(String messageCaptchaKey) {
        this.messageCaptchaKey = messageCaptchaKey;
    }

    /**
     * 短信验证码value
     * 
     * @return the messageCaptchaValue
     */
    public String getMessageCaptchaValue() {
        if (StringUtils.isBlank(messageCaptchaValue)) {
            return "";
        } else {
            return messageCaptchaValue;
        }
    }

    /**
     * 短信验证码value
     * 
     * @param messageCaptchaValue
     *            the messageCaptchaValue to set
     */
    public void setMessageCaptchaValue(String messageCaptchaValue) {
        this.messageCaptchaValue = messageCaptchaValue;
    }

}
