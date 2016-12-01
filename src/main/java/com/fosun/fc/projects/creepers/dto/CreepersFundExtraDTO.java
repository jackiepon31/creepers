package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: T_CREEPERS_FUND_EXTRA 补充公积金账户信息表
 * <p>
 * 
 * @author MaXin
 * @since 2016-09-06 14:47:06
 * @see
 */

public class CreepersFundExtraDTO extends CreepersBaseDTO {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // 姓名
    private String name;
    // 开户日期
    private String accountDt;
    // 公积金账号
    private String accountNo;
    // 所属单位
    private String unit;
    // 末次缴存年月
    private String endDt;
    // 账户余额
    private String sumAmount;
    // 月缴存额
    private String monthlyAmount;
    // 当前账户状态
    private String accountStatus;
    // 备注
    private String memo;
    // 登录账号
    private String loginName;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountDt() {
        return accountDt;
    }

    public void setAccountDt(String accountDt) {
        this.accountDt = accountDt;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getEndDt() {
        return endDt;
    }

    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    public String getMonthlyAmount() {
        return monthlyAmount;
    }

    public void setMonthlyAmount(String monthlyAmount) {
        this.monthlyAmount = monthlyAmount;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(String sumAmount) {
        this.sumAmount = sumAmount;
    }

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

}
