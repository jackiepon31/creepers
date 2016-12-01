package com.fosun.fc.projects.creepers.dto;

/**
 *
 * <p>
 * description: T_CREEPERS_FUND_BASIC 公积金账户信息表
 * <p>
 * 
 * @author MaXin
 * @since 2016-09-06 14:47:06
 * @see
 */

public class CreepersFundBasicDTO extends CreepersBaseDTO {
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
    // 绑定手机号
    private String mobile;
    // 实名认证状态
    private String certificateStatus;
    // 备注
    private String memo;
    // 逻辑删除标志，0：未删除，1：已删除
    private String flag;
    // 登录用户
    private String loginName;
    // 登录密码
    private String password;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCertificateStatus() {
        return certificateStatus;
    }

    public void setCertificateStatus(String certificateStatus) {
        this.certificateStatus = certificateStatus;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(String sumAmount) {
        this.sumAmount = sumAmount;
    }

}
