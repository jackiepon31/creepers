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
 * The persistent class for the T_CREEPERS_MER_PLEDGE database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_MER_PLEDGE")
@NamedQuery(name="TCreepersMerPledge.findAll", query="SELECT t FROM TCreepersMerPledge t")
public class TCreepersMerPledge extends com.fosun.fc.modules.entity.BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1350669312786407977L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_MER_PLEDGE_ID_GENERATOR", sequenceName="SEQ_CREEPERS_MER_PLEDGE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_MER_PLEDGE_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="CHANGE_INFO", length=300)
	private String changeInfo;

	@Column(name="MER_NO", length=100)
	private String merNo;

	@Column(name="PLEDGE_AMOUNT", length=50)
	private String pledgeAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="PLEDGE_DT")
	private Date pledgeDt;

	@Column(length=100)
	private String pledgee;

	@Column(name="PLEDGEE_CERT_NO", length=50)
	private String pledgeeCertNo;

	@Column(length=100)
	private String pledger;

	@Column(name="PLEDGER_CERT_NO", length=50)
	private String pledgerCertNo;

	@Column(name="REG_NO", length=200)
	private String regNo;

	@Column(name="SEQ_NO", length=20)
	private String seqNo;

	@Column(length=50)
	private String status;

	public TCreepersMerPledge() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getChangeInfo() {
		return this.changeInfo;
	}

	public void setChangeInfo(String changeInfo) {
		this.changeInfo = changeInfo;
	}

	public String getMerNo() {
		return this.merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	public String getPledgeAmount() {
		return this.pledgeAmount;
	}

	public void setPledgeAmount(String pledgeAmount) {
		this.pledgeAmount = pledgeAmount;
	}

	public Date getPledgeDt() {
		return this.pledgeDt;
	}

	public void setPledgeDt(Date pledgeDt) {
		this.pledgeDt = pledgeDt;
	}

	public String getPledgee() {
		return this.pledgee;
	}

	public void setPledgee(String pledgee) {
		this.pledgee = pledgee;
	}

	public String getPledgeeCertNo() {
		return this.pledgeeCertNo;
	}

	public void setPledgeeCertNo(String pledgeeCertNo) {
		this.pledgeeCertNo = pledgeeCertNo;
	}

	public String getPledger() {
		return this.pledger;
	}

	public void setPledger(String pledger) {
		this.pledger = pledger;
	}

	public String getPledgerCertNo() {
		return this.pledgerCertNo;
	}

	public void setPledgerCertNo(String pledgerCertNo) {
		this.pledgerCertNo = pledgerCertNo;
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