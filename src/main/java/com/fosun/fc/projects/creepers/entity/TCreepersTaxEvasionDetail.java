package com.fosun.fc.projects.creepers.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the T_CREEPERS_TAX_EVASION_DETAIL database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_TAX_EVASION_DETAIL")
@NamedQuery(name="TCreepersTaxEvasionDetail.findAll", query="SELECT t FROM TCreepersTaxEvasionDetail t")
public class TCreepersTaxEvasionDetail extends com.fosun.fc.modules.entity.BaseEntity implements Serializable {

    private static final long serialVersionUID = 5050035789659307833L;

    @Id
	@SequenceGenerator(name="T_CREEPERS_TAX_EVASION_DETAIL_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_TAX_EVASION_DET")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_TAX_EVASION_DETAIL_ID_GENERATOR")
	private long id;

	private String addr;

	@Column(name="CASE_NATURE")
	private String caseNature;

	@Column(name="CERT_NAME")
	private String certName;

	private String code;

	private String court;

	@Column(name="FINANCIAL_CERT_NAME")
	private String financialCertName;

	@Column(name="FINANCIAL_NAME")
	private String financialName;

	@Column(name="FINANCIAL_SEX")
	private String financialSex;

	@Column(name="ILLEGAL_FACTS")
	private String illegalFacts;

	@Column(name="INTER_INFO")
	private String interInfo;

	@Column(name="LEGAL_REP")
	private String legalRep;

	@Column(name="LEGAL_SEX")
	private String legalSex;

	private String memo;

	private String name;

	private String province;

	@Column(name="PUBLISH_LEVEL")
	private String publishLevel;

	@Column(name="PUNISH_INFO")
	private String punishInfo;

	private String source;

	@Column(name="TAX_NO")
	private String taxNo;

	private String type;

	public TCreepersTaxEvasionDetail() {
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

	public String getCaseNature() {
		return this.caseNature;
	}

	public void setCaseNature(String caseNature) {
		this.caseNature = caseNature;
	}

	public String getCertName() {
		return this.certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCourt() {
		return this.court;
	}

	public void setCourt(String court) {
		this.court = court;
	}

	public String getFinancialCertName() {
		return this.financialCertName;
	}

	public void setFinancialCertName(String financialCertName) {
		this.financialCertName = financialCertName;
	}

	public String getFinancialName() {
		return this.financialName;
	}

	public void setFinancialName(String financialName) {
		this.financialName = financialName;
	}

	public String getFinancialSex() {
		return this.financialSex;
	}

	public void setFinancialSex(String financialSex) {
		this.financialSex = financialSex;
	}

	public String getIllegalFacts() {
		return this.illegalFacts;
	}

	public void setIllegalFacts(String illegalFacts) {
		this.illegalFacts = illegalFacts;
	}

	public String getInterInfo() {
		return this.interInfo;
	}

	public void setInterInfo(String interInfo) {
		this.interInfo = interInfo;
	}

	public String getLegalRep() {
		return this.legalRep;
	}

	public void setLegalRep(String legalRep) {
		this.legalRep = legalRep;
	}

	public String getLegalSex() {
		return this.legalSex;
	}

	public void setLegalSex(String legalSex) {
		this.legalSex = legalSex;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPublishLevel() {
		return this.publishLevel;
	}

	public void setPublishLevel(String publishLevel) {
		this.publishLevel = publishLevel;
	}

	public String getPunishInfo() {
		return this.punishInfo;
	}

	public void setPunishInfo(String punishInfo) {
		this.punishInfo = punishInfo;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTaxNo() {
		return this.taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}