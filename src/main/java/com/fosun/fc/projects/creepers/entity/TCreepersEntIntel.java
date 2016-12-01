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
 * The persistent class for the T_CREEPERS_ENT_INTEL database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_ENT_INTEL")
@NamedQuery(name="TCreepersEntIntel.findAll", query="SELECT t FROM TCreepersEntIntel t")
public class TCreepersEntIntel extends com.fosun.fc.modules.entity.BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8520482579644338639L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_ENT_INTEL_ID_GENERATOR", sequenceName="SEQ_CREEPERS_ENT_INTEL")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_ENT_INTEL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(length=200)
	private String detail;

	@Column(name="MER_NO", length=100)
	private String merNo;

	@Column(length=200)
	private String name;

	@Column(name="PLEDGEE_NAME", length=50)
	private String pledgeeName;

	@Column(name="PLEDGEE_PERIOD", length=50)
	private String pledgeePeriod;

	@Column(name="QUALITY_NAME", length=50)
	private String qualityName;

	@Column(name="SEQ_NO", length=5)
	private String seqNo;

	@Column(length=50)
	private String status;

	@Column(name="\"TYPE\"", length=20)
	private String type;

	public TCreepersEntIntel() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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

	public String getPledgeeName() {
		return this.pledgeeName;
	}

	public void setPledgeeName(String pledgeeName) {
		this.pledgeeName = pledgeeName;
	}

	public String getPledgeePeriod() {
		return this.pledgeePeriod;
	}

	public void setPledgeePeriod(String pledgeePeriod) {
		this.pledgeePeriod = pledgeePeriod;
	}

	public String getQualityName() {
		return this.qualityName;
	}

	public void setQualityName(String qualityName) {
		this.qualityName = qualityName;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}