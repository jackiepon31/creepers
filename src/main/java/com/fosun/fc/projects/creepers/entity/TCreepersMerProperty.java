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
 * The persistent class for the T_CREEPERS_MER_PROPERTY database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_MER_PROPERTY")
@NamedQuery(name="TCreepersMerProperty.findAll", query="SELECT t FROM TCreepersMerProperty t")
public class TCreepersMerProperty extends com.fosun.fc.modules.entity.BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6453181186009331708L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_MER_PROPERTY_ID_GENERATOR", sequenceName="SEQ_CREEPERS_MER_PROPERTY")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_MER_PROPERTY_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="CLAIM_AMOUNT", length=50)
	private String claimAmount;

	@Column(length=300)
	private String details;

	@Column(name="MER_NO", length=100)
	private String merNo;

	@Column(name="REG_AUTHORITY", length=200)
	private String regAuthority;

	@Temporal(TemporalType.DATE)
	@Column(name="REG_DT")
	private Date regDt;

	@Column(name="REG_NO", length=200)
	private String regNo;

	@Column(name="SEQ_NO", length=20)
	private String seqNo;

	@Column(length=50)
	private String status;

	public TCreepersMerProperty() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClaimAmount() {
		return this.claimAmount;
	}

	public void setClaimAmount(String claimAmount) {
		this.claimAmount = claimAmount;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getMerNo() {
		return this.merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	public String getRegAuthority() {
		return this.regAuthority;
	}

	public void setRegAuthority(String regAuthority) {
		this.regAuthority = regAuthority;
	}

	public Date getRegDt() {
		return this.regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public String getRegNo() {
		return this.regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}