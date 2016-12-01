package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: T_CREEPERS_FUND_BASIC_DETAIL 公积金账户本年度明细表
 * <p>
 * 
 * @author MaXin
 * @since 2016-09-06 14:47:06
 * @see
 */

public class CreepersFundBasicDetailDTO extends CreepersBaseDTO {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // 登录账号
    private String loginName;
    // 日期
    private String operationDt;
    // 单位名称
    private String unit;
    // 金额(元)
    private String amount;
    // 业务描述
    private String operationDesc;
    // 业务原因
    private String operationReason;
    // 备注
    private String memo;


    public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getOperationDt() {
        return operationDt;
    }

    public void setOperationDt(String operationDt) {
        this.operationDt = operationDt;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOperationDesc() {
        return operationDesc;
    }

    public void setOperationDesc(String operationDesc) {
        this.operationDesc = operationDesc;
    }

    public String getOperationReason() {
        return operationReason;
    }

    public void setOperationReason(String operationReason) {
        this.operationReason = operationReason;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
