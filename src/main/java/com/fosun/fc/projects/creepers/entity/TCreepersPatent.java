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
 * The persistent class for the T_CREEPERS_PATENT database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_PATENT")
@NamedQuery(name="TCreepersPatent.findAll", query="SELECT t FROM TCreepersPatent t")
public class TCreepersPatent extends com.fosun.fc.modules.entity.BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7666132591647734388L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_PATENT_ID_GENERATOR", sequenceName="SEQ_CREEPERS_PATENT")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_PATENT_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(length=200)
	private String applicant;

	@Temporal(TemporalType.DATE)
	@Column(name="APPLY_DT")
	private Date applyDt;

	@Column(name="CATEGORY_NO", length=500)
	private String categoryNo;

	@Column(name="MER_NAME", length=1000)
	private String merName;

	@Column(name="MER_NO", length=100)
	private String merNo;

	@Column(name="PATENT_NAME", length=200)
	private String patentName;

	@Column(name="PATENT_NO", length=200)
	private String patentNo;

	@Column(name="PATENT_TYPE", length=200)
	private String patentType;

	public TCreepersPatent() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getApplicant() {
		return this.applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public Date getApplyDt() {
		return this.applyDt;
	}

	public void setApplyDt(Date applyDt) {
		this.applyDt = applyDt;
	}

	public String getCategoryNo() {
		return this.categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getMerName() {
		return this.merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo;
    }

    public String getPatentName() {
		return this.patentName;
	}

	public void setPatentName(String patentName) {
		this.patentName = patentName;
	}

	public String getPatentNo() {
		return this.patentNo;
	}

	public void setPatentNo(String patentNo) {
		this.patentNo = patentNo;
	}

	public String getPatentType() {
		return this.patentType;
	}

	public void setPatentType(String patentType) {
		this.patentType = patentType;
	}

}