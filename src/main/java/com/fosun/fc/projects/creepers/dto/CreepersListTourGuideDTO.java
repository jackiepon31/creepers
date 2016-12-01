package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: T_CREEPERS_LIST_TOUR_GUIDE 爬虫任务队列-导游信息
 * <p>
 * 
 * @author MaXin
 * @since 2016-10-31 17:22:25
 * @see
 */

public class CreepersListTourGuideDTO extends CreepersBaseDTO {
    private static final long serialVersionUID = 1L;
    // 导游证号
    private String guideNo;
    // 导游卡号
    private String cardNo;
    // 身份证号
    private String certNo;

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
     * @return the cardNo
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * @param cardNo
     *            the cardNo to set
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * @return the certNo
     */
    public String getCertNo() {
        return certNo;
    }

    /**
     * @param certNo
     *            the certNo to set
     */
    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

}
