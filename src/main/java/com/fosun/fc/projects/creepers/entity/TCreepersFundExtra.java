package com.fosun.fc.projects.creepers.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the T_CREEPERS_FUND_EXTRA database table.
 * 
 */
@Entity
@Table(name = "T_CREEPERS_FUND_EXTRA")
@NamedQuery(name = "TCreepersFundExtra.findAll", query = "SELECT t FROM TCreepersFundExtra t")
public class TCreepersFundExtra extends com.fosun.fc.modules.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "T_CREEPERS_FUND_EXTRA_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_FUND_EXTRA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_FUND_EXTRA_ID_GENERATOR")
    private long id;

    @Column(name = "ACCOUNT_DT")
    private String accountDt;

    @Column(name = "ACCOUNT_NO")
    private String accountNo;

    @Column(name = "ACCOUNT_STATUS")
    private String accountStatus;

    @Column(name = "END_DT")
    private String endDt;

    @Column(name = "MONTHLY_AMOUNT")
    private BigDecimal monthlyAmount;

    private String name;

    @Column(name = "SUM_AMOUNT")
    private BigDecimal sumAmount;

    private String unit;

    @Column(name = "LOGIN_NAME")
    private String loginName;
    
    public TCreepersFundExtra() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountDt() {
        return this.accountDt;
    }

    public void setAccountDt(String accountDt) {
        this.accountDt = accountDt;
    }

    public String getAccountNo() {
        return this.accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountStatus() {
        return this.accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getEndDt() {
        return this.endDt;
    }

    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    public BigDecimal getMonthlyAmount() {
        return this.monthlyAmount;
    }

    public void setMonthlyAmount(BigDecimal monthlyAmount) {
        this.monthlyAmount = monthlyAmount;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(BigDecimal sumAmount) {
        this.sumAmount = sumAmount;
    }

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

}
