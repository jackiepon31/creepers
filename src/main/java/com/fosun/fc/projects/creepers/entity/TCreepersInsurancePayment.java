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
 * The persistent class for the T_CREEPERS_INSURANCE_PAYMENT database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_INSURANCE_PAYMENT")
@NamedQuery(name="TCreepersInsurancePayment.findAll", query="SELECT t FROM TCreepersInsurancePayment t")
public class TCreepersInsurancePayment extends com.fosun.fc.modules.entity.BaseEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_INSURANCE_PAYMENT_ID_GENERATOR", sequenceName="SEQ_CREEPERS_INSURANCE_PAYMENT")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_INSURANCE_PAYMENT_ID_GENERATOR")
	private long id;

	@Column(name="CERT_NO")
	private String certNo;

	private BigDecimal endowment;

	private BigDecimal medical;

	private String name;

	@Column(name="PAYMENT_BASE")
	private BigDecimal paymentBase;

	@Column(name="PAYMENT_DT")
	private String paymentDt;

	private BigDecimal unemployment;

	public TCreepersInsurancePayment() {
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

	public BigDecimal getEndowment() {
		return this.endowment;
	}

	public void setEndowment(BigDecimal endowment) {
		this.endowment = endowment;
	}

	public BigDecimal getMedical() {
		return this.medical;
	}

	public void setMedical(BigDecimal medical) {
		this.medical = medical;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPaymentBase() {
		return this.paymentBase;
	}

	public void setPaymentBase(BigDecimal paymentBase) {
		this.paymentBase = paymentBase;
	}

	public String getPaymentDt() {
		return this.paymentDt;
	}

	public void setPaymentDt(String paymentDt) {
		this.paymentDt = paymentDt;
	}

	public BigDecimal getUnemployment() {
		return this.unemployment;
	}

	public void setUnemployment(BigDecimal unemployment) {
		this.unemployment = unemployment;
	}

}