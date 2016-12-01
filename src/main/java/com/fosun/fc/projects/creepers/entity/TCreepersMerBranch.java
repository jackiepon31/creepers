package com.fosun.fc.projects.creepers.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the T_CREEPERS_MER_BRANCH database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_MER_BRANCH")
@NamedQuery(name="TCreepersMerBranch.findAll", query="SELECT t FROM TCreepersMerBranch t")
public class TCreepersMerBranch extends com.fosun.fc.modules.entity.BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7066337032001706904L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_MER_BRANCH_ID_GENERATOR", sequenceName="SEQ_CREEPERS_MER_BRANCH")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_MER_BRANCH_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="CLEAR_INFO", length=500)
	private String clearInfo;

	@Column(name="MER_NO", length=100)
	private String merNo;

	@Column(length=200)
	private String name;

	@Column(name="REG_AUTHORITY", length=200)
	private String regAuthority;

	@Column(name="REG_NO", length=200)
	private String regNo;

	@Column(name="SEQ_NO", length=20)
	private String seqNo;

	public TCreepersMerBranch() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClearInfo() {
		return this.clearInfo;
	}

	public void setClearInfo(String clearInfo) {
		this.clearInfo = clearInfo;
	}

	public String getMerNo() {
		return this.merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegAuthority() {
		return this.regAuthority;
	}

	public void setRegAuthority(String regAuthority) {
		this.regAuthority = regAuthority;
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

}