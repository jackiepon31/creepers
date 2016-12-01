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
 * The persistent class for the T_CREEPERS_ENT_EQUITY database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_ENT_EQUITY")
@NamedQuery(name="TCreepersEntEquity.findAll", query="SELECT t FROM TCreepersEntEquity t")
public class TCreepersEntEquity extends com.fosun.fc.modules.entity.BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4212816568250032078L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_ENT_EQUITY_ID_GENERATOR", sequenceName="SEQ_CREEPERS_ENT_EQUITY")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_ENT_EQUITY_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="CHANGE_DT")
	private Date changeDt;

	@Column(name="MER_NO", length=100)
	private String merNo;

	@Column(name="POST_RATE", length=50)
	private String postRate;

	@Column(name="PRE_RATE", length=50)
	private String preRate;

	@Column(name="SHARE_HOLDER", length=100)
	private String shareHolder;

	public TCreepersEntEquity() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getChangeDt() {
		return this.changeDt;
	}

	public void setChangeDt(Date changeDt) {
		this.changeDt = changeDt;
	}

	public String getMerNo() {
		return this.merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	public String getPostRate() {
		return this.postRate;
	}

	public void setPostRate(String postRate) {
		this.postRate = postRate;
	}

	public String getPreRate() {
		return this.preRate;
	}

	public void setPreRate(String preRate) {
		this.preRate = preRate;
	}

	public String getShareHolder() {
		return this.shareHolder;
	}

	public void setShareHolder(String shareHolder) {
		this.shareHolder = shareHolder;
	}

}