package com.fosun.fc.projects.creepers.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the T_CREEPERS_ENT_SHARE database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_ENT_SHARE")
@NamedQuery(name="TCreepersEntShare.findAll", query="SELECT t FROM TCreepersEntShare t")
public class TCreepersEntShare extends com.fosun.fc.modules.entity.BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7385116819131409722L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_ENT_SHARE_ID_GENERATOR", sequenceName="SEQ_CREEPERS_ENT_SHARE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_ENT_SHARE_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="MER_NO", length=100)
	private String merNo;

	@Column(name="REAL_CAPITAL", length=100)
	private String realCapital;

	@Temporal(TemporalType.DATE)
	@Column(name="REAL_DT")
	private Date realDt;

	@Column(name="REAL_TYPE", length=30)
	private String realType;

	@Column(length=100)
	private String shareholder;

	@Column(name="SUB_CAPITAL", length=100)
	private String subCapital;

	@Temporal(TemporalType.DATE)
	@Column(name="SUB_DT")
	private Date subDt;

	@Column(name="SUB_TYPE", length=30)
	private String subType;

	public TCreepersEntShare() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMerNo() {
		return this.merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	public String getRealCapital() {
		return this.realCapital;
	}

	public void setRealCapital(String realCapital) {
		this.realCapital = realCapital;
	}

	public Date getRealDt() {
		return this.realDt;
	}

	public void setRealDt(Date realDt) {
		this.realDt = realDt;
	}

	public String getRealType() {
		return this.realType;
	}

	public void setRealType(String realType) {
		this.realType = realType;
	}

	public String getShareholder() {
		return this.shareholder;
	}

	public void setShareholder(String shareholder) {
		this.shareholder = shareholder;
	}

	public String getSubCapital() {
		return this.subCapital;
	}

	public void setSubCapital(String subCapital) {
		this.subCapital = subCapital;
	}

	public Date getSubDt() {
		return this.subDt;
	}

	public void setSubDt(Date subDt) {
		this.subDt = subDt;
	}

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

}