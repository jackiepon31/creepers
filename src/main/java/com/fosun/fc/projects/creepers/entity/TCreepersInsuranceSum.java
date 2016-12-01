package com.fosun.fc.projects.creepers.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the T_CREEPERS_INSURANCE_SUM database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_INSURANCE_SUM")
@NamedQuery(name="TCreepersInsuranceSum.findAll", query="SELECT t FROM TCreepersInsuranceSum t")
public class TCreepersInsuranceSum extends com.fosun.fc.modules.entity.BaseEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_INSURANCE_SUM_ID_GENERATOR", sequenceName="SEQ_CREEPERS_INSURANCE_SUM")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_INSURANCE_SUM_ID_GENERATOR")
	private long id;

	@Column(name="CERT_NO")
	private String certNo;

	@Column(name="END_DT")
	private String endDt;

	@Column(name="ENDOWMENT_SUM")
	private BigDecimal endowmentSum;

	@Column(name="ENDOWMENT_SUM_PRIVATE")
	private BigDecimal endowmentSumPrivate;


	private String memo;

	@Column(name="MONTHS")
	private BigDecimal months;

	private String name;

	public TCreepersInsuranceSum() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCertNo() {
		return this.certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getEndDt() {
		return this.endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public BigDecimal getEndowmentSum() {
		return this.endowmentSum;
	}

	public void setEndowmentSum(BigDecimal endowmentSum) {
		this.endowmentSum = endowmentSum;
	}

	public BigDecimal getEndowmentSumPrivate() {
		return this.endowmentSumPrivate;
	}

	public void setEndowmentSumPrivate(BigDecimal endowmentSumPrivate) {
		this.endowmentSumPrivate = endowmentSumPrivate;
	}


	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public BigDecimal getMonths() {
		return this.months;
	}

	public void setMonths(BigDecimal months) {
		this.months = months;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDt() {
		return this.updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

}