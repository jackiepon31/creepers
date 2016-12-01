package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: T_CREEPERS_CONFIG 爬虫配置信息表
 * <p>
 * 
 * @author MaXin
 * @since 2016-08-30 15:37:01
 * @see
 */

public class CreepersConfigDTO extends CreepersBaseDTO {
    private static final long serialVersionUID = -6101670501571593852L;
    // 爬虫任务类型
    private String requestType;
    // 种子URL
    private String rootUrl;
    // 域名
    private String domain;
    // 用户浏览器代理设置
    private String userAgent;
    // 网关系统接口URL
    private String cdUrl;
    // 网关系统接口类型
    private String cdRequestCode;
    // 线程数
    private String threadNum;
    // 连接超时时间(ms)
    private String timeOut;
    // 任务开关，0：关闭，1：开启
    private String switchFlag;
    // 动态代理开关，0：关闭，1：开启
    private String agentFlag;
    // 重试连接次数
    private String retryTimes;
    // 头信息
    private String headers;

    /**
     * @return the requestType
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * @param requestType
     *            the requestType to set
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    /**
     * @return the rootUrl
     */
    public String getRootUrl() {
        return rootUrl;
    }

    /**
     * @param rootUrl
     *            the rootUrl to set
     */
    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    /**
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain
     *            the domain to set
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @return the userAgent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * @param userAgent
     *            the userAgent to set
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * @return the cdUrl
     */
    public String getCdUrl() {
        return cdUrl;
    }

    /**
     * @param cdUrl
     *            the cdUrl to set
     */
    public void setCdUrl(String cdUrl) {
        this.cdUrl = cdUrl;
    }

    /**
     * @return the cdRequestCode
     */
    public String getCdRequestCode() {
        return cdRequestCode;
    }

    /**
     * @param cdRequestCode
     *            the cdRequestCode to set
     */
    public void setCdRequestCode(String cdRequestCode) {
        this.cdRequestCode = cdRequestCode;
    }

    /**
     * @return the threadNum
     */
    public String getThreadNum() {
        return threadNum;
    }

    /**
     * @param threadNum
     *            the threadNum to set
     */
    public void setThreadNum(String threadNum) {
        this.threadNum = threadNum;
    }

    /**
     * @return the timeOut
     */
    public String getTimeOut() {
        return timeOut;
    }

    /**
     * @param timeOut
     *            the timeOut to set
     */
    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * @return the switchFlag
     */
    public String getSwitchFlag() {
        return switchFlag;
    }

    /**
     * @param switchFlag
     *            the switchFlag to set
     */
    public void setSwitchFlag(String switchFlag) {
        this.switchFlag = switchFlag;
    }

    /**
     * @return the agentFlag
     */
    public String getAgentFlag() {
        return agentFlag;
    }

    /**
     * @param agentFlag
     *            the agentFlag to set
     */
    public void setAgentFlag(String agentFlag) {
        this.agentFlag = agentFlag;
    }

    /**
     * @return the retryTimes
     */
    public String getRetryTimes() {
        return retryTimes;
    }

    /**
     * @param retryTimes
     *            the retryTimes to set
     */
    public void setRetryTimes(String retryTimes) {
        this.retryTimes = retryTimes;
    }

    /**
     * @return the headers
     */
    public String getHeaders() {
        return headers;
    }

    /**
     * @param headers
     *            the headers to set
     */
    public void setHeaders(String headers) {
        this.headers = headers;
    }

}
