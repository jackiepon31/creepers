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
 * The persistent class for the T_CREEPERS_MER_BASIC database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_MER_BASIC")
@NamedQuery(name="TCreepersMerBasic.findAll", query="SELECT t FROM TCreepersMerBasic t")
public class TCreepersMerBasic extends com.fosun.fc.modules.entity.BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4510948824756413300L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_MER_BASIC_ID_GENERATOR", sequenceName="SEQ_CREEPERS_MER_BASIC")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_MER_BASIC_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(length=300)
	private String addr;

	@Temporal(TemporalType.DATE)
	@Column(name="APPROVAL_DATE")
	private Date approvalDate;

	@Temporal(TemporalType.DATE)
	@Column(name="FOUND_DT")
	private Date foundDt;

	@Column(name="LEGAL_REP", length=50)
	private String legalRep;

	@Column(name="MER_NAME", length=200)
	private String merName;

	@Column(name="MER_NO", length=100)
	private String merNo;

	@Column(name="MER_TYPE", length=20)
	private String merType;

	@Temporal(TemporalType.DATE)
	@Column(name="OPR_END_DT")
	private Date oprEndDt;

	@Temporal(TemporalType.DATE)
	@Column(name="OPR_START_DT")
	private Date oprStartDt;

	@Column(name="REG_AUTHORITY", length=200)
	private String regAuthority;

	@Column(name="REG_CAPITAL", length=100)
	private String regCapital;

	@Column(name="REG_STATUS", length=100)
	private String regStatus;

	@Column(name="SCOPE", length=2000)
	private String scope;

	@Column(name="SHAREHOLDER_TYPE", length=100)
	private String shareholderType;

	public TCreepersMerBasic() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Date getApprovalDate() {
		return this.approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Date getFoundDt() {
		return this.foundDt;
	}

	public void setFoundDt(Date foundDt) {
		this.foundDt = foundDt;
	}

	public String getLegalRep() {
		return this.legalRep;
	}

	public void setLegalRep(String legalRep) {
		this.legalRep = legalRep;
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

	public Date getOprEndDt() {
		return this.oprEndDt;
	}

	public void setOprEndDt(Date oprEndDt) {
		this.oprEndDt = oprEndDt;
	}

	public Date getOprStartDt() {
		return this.oprStartDt;
	}

	public void setOprStartDt(Date oprStartDt) {
		this.oprStartDt = oprStartDt;
	}

	public String getRegAuthority() {
		return this.regAuthority;
	}

	public void setRegAuthority(String regAuthority) {
		this.regAuthority = regAuthority;
	}

	public String getRegCapital() {
		return this.regCapital;
	}

	public void setRegCapital(String regCapital) {
		this.regCapital = regCapital;
	}

	public String getRegStatus() {
		return this.regStatus;
	}

	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}

	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getShareholderType() {
		return this.shareholderType;
	}

	public void setShareholderType(String shareholderType) {
		this.shareholderType = shareholderType;
	}

}