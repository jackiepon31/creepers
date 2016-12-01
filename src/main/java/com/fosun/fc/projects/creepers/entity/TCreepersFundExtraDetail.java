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
 * The persistent class for the T_CREEPERS_FUND_EXTRA_DETAIL database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_FUND_EXTRA_DETAIL")
@NamedQuery(name="TCreepersFundExtraDetail.findAll", query="SELECT t FROM TCreepersFundExtraDetail t")
public class TCreepersFundExtraDetail extends com.fosun.fc.modules.entity.BaseEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_FUND_EXTRA_DETAIL_ID_GENERATOR", sequenceName="SEQ_CREEPERS_FUND_EXTRA_DETAIL")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_FUND_EXTRA_DETAIL_ID_GENERATOR")
	private long id;

	@Column(name="LOGIN_NAME")
	private String loginName;

	private BigDecimal amount;

	@Column(name="OPERATION_DESC")
	private String operationDesc;

	@Column(name="OPERATION_DT")
	private String operationDt;

	@Column(name="OPERATION_REASON")
	private String operationReason;

	private String unit;

	public TCreepersFundExtraDetail() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getOperationDesc() {
		return this.operationDesc;
	}

	public void setOperationDesc(String operationDesc) {
		this.operationDesc = operationDesc;
	}

	public String getOperationDt() {
		return this.operationDt;
	}

	public void setOperationDt(String operationDt) {
		this.operationDt = operationDt;
	}

	public String getOperationReason() {
		return this.operationReason;
	}

	public void setOperationReason(String operationReason) {
		this.operationReason = operationReason;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}