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
 * The persistent class for the T_CREEPERS_COURT_DIS_INFO database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_COURT_DIS_INFO")
@NamedQuery(name="TCreepersCourtDisInfo.findAll", query="SELECT t FROM TCreepersCourtDisInfo t")
public class TCreepersCourtDisInfo extends com.fosun.fc.modules.entity.BaseEntity implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = -7104391826503258679L;

    @Id
	@SequenceGenerator(name="T_CREEPERS_COURT_DIS_INFO_ID_GENERATOR", sequenceName="SEQ_CREEPERS_COURT_DIS_INFO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_COURT_DIS_INFO_ID_GENERATOR")
	private long id;

	@Column(name="CASE_DT")
	private String caseDt;

	@Column(name="CASE_NO")
	private String caseNo;

	private String code;

	private String court;

	private String duty;

	@Column(name="IMPLEMENT_NO")
	private String implementNo;

	@Column(name="IMPLEMENT_UNIT")
	private String implementUnit;

	@Column(name="LEGAL_REP")
	private String legalRep;

	private String memo;

	private String name;

	@Column(name="PERFORM_N")
	private String performN;

	@Column(name="PERFORM_Y")
	private String performY;

	private String performance;

	private String province;

	@Column(name="PUBLISH_DT")
	private String publishDt;

	private String source;

	private String specific;

	private String type;

	public TCreepersCourtDisInfo() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCaseDt() {
		return this.caseDt;
	}

	public void setCaseDt(String caseDt) {
		this.caseDt = caseDt;
	}

	public String getCaseNo() {
		return this.caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
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

	public String getDuty() {
		return this.duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getImplementNo() {
		return this.implementNo;
	}

	public void setImplementNo(String implementNo) {
		this.implementNo = implementNo;
	}

	public String getImplementUnit() {
		return this.implementUnit;
	}

	public void setImplementUnit(String implementUnit) {
		this.implementUnit = implementUnit;
	}

	public String getLegalRep() {
		return this.legalRep;
	}

	public void setLegalRep(String legalRep) {
		this.legalRep = legalRep;
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

	public String getPerformN() {
		return this.performN;
	}

	public void setPerformN(String performN) {
		this.performN = performN;
	}

	public String getPerformY() {
		return this.performY;
	}

	public void setPerformY(String performY) {
		this.performY = performY;
	}

	public String getPerformance() {
		return this.performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPublishDt() {
		return this.publishDt;
	}

	public void setPublishDt(String publishDt) {
		this.publishDt = publishDt;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSpecific() {
		return this.specific;
	}

	public void setSpecific(String specific) {
		this.specific = specific;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}