package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: T_CREEPERS_TOUR_BLACK_LIST 信用中国-旅游黑名单明细
 * <p>
 * 
 * @author MaXin
 * @since 2016-10-31 17:22:26
 * @see
 */

public class CreepersTourBlackListDTO extends CreepersBaseDTO {
    private static final long serialVersionUID = 1L;
    // 1：旅行社、2：导游
    private String type;
    // 档案号
    private String docNo;
    // 执业证号
    private String guideNo;
    // 单位名称
    private String agentName;
    // 姓名（导游名称、企业法人）
    private String name;
    // 违法事项
    private String issue;
    // 行政处罚决定
    private String decision;
    // 起止日期
    private String period;
    // 明细链接
    private String detailUrl;

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
     * @return the docNo
     */
    public String getDocNo() {
        return docNo;
    }

    /**
     * @param docNo
     *            the docNo to set
     */
    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    /**
     * @return the guideNo
     */
    public String getGuideNo() {
        return guideNo;
    }

    /**
     * @param guideNo
     *            the guideNo to set
     */
    public void setGuideNo(String guideNo) {
        this.guideNo = guideNo;
    }

    /**
     * @return the agentName
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * @param agentName
     *            the agentName to set
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the issue
     */
    public String getIssue() {
        return issue;
    }

    /**
     * @param issue
     *            the issue to set
     */
    public void setIssue(String issue) {
        this.issue = issue;
    }

    /**
     * @return the decision
     */
    public String getDecision() {
        return decision;
    }

    /**
     * @param decision
     *            the decision to set
     */
    public void setDecision(String decision) {
        this.decision = decision;
    }

    /**
     * @return the period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * @param period
     *            the period to set
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * @return the detailUrl
     */
    public String getDetailUrl() {
        return detailUrl;
    }

    /**
     * @param detailUrl the detailUrl to set
     */
    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

}
