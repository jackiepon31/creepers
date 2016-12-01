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
 * The persistent class for the T_CREEPERS_CUSTOMS database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_CUSTOMS")
@NamedQuery(name="TCreepersCustoms.findAll", query="SELECT t FROM TCreepersCustoms t")
public class TCreepersCustoms extends com.fosun.fc.modules.entity.BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2834371697980066569L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_CUSTOMS_ID_GENERATOR", sequenceName="SEQ_CREEPERS_CUSTOMS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_CUSTOMS_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="CUSTOM_CODE", length=200)
	private String customCode;

	@Column(name="DECLARE_TYPE", length=100)
	private String declareType;

	@Column(name="MER_NAME", length=200)
	private String merName;

	@Column(name="MER_NO", length=100)
	private String merNo;

	@Column(name="MER_TYPE", length=200)
	private String merType;

	@Temporal(TemporalType.DATE)
	@Column(name="REG_VALID_DT")
	private Date regValidDt;

	public TCreepersCustoms() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustomCode() {
		return this.customCode;
	}

	public void setCustomCode(String customCode) {
		this.customCode = customCode;
	}

	public String getDeclareType() {
		return this.declareType;
	}

	public void setDeclareType(String declareType) {
		this.declareType = declareType;
	}

	public String getMerName() {
		return this.merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getMerNo() {
		return this.merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	public String getMerType() {
		return this.merType;
	}

	public void setMerType(String merType) {
		this.merType = merType;
	}

	public Date getRegValidDt() {
		return this.regValidDt;
	}

	public void setRegValidDt(Date regValidDt) {
		this.regValidDt = regValidDt;
	}

}