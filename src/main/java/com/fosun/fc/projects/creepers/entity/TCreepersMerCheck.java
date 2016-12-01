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
 * The persistent class for the T_CREEPERS_MER_CHECK database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_MER_CHECK")
@NamedQuery(name="TCreepersMerCheck.findAll", query="SELECT t FROM TCreepersMerCheck t")
public class TCreepersMerCheck extends com.fosun.fc.modules.entity.BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6812463706895462281L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_MER_CHECK_ID_GENERATOR", sequenceName="SEQ_CREEPERS_MER_CHECK")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_MER_CHECK_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(length=100)
	private String authority;

	@Temporal(TemporalType.DATE)
	@Column(name="CHECK_DT")
	private Date checkDt;

	@Column(name="CHECK_RESULT", length=100)
	private String checkResult;

	@Column(name="CHECK_TYPE", length=20)
	private String checkType;

	@Column(name="MER_NO", length=100)
	private String merNo;

	@Column(name="SEQ_NO", length=20)
	private String seqNo;

	public TCreepersMerCheck() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Date getCheckDt() {
		return this.checkDt;
	}

	public void setCheckDt(Date checkDt) {
		this.checkDt = checkDt;
	}

	public String getCheckResult() {
		return this.checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getCheckType() {
		return this.checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getMerNo() {
		return this.merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	public String getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

}