package com.fosun.fc.projects.creepers.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the T_CREEPERS_TRADE_MARK database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_TRADE_MARK")
@NamedQuery(name="TCreepersTradeMark.findAll", query="SELECT t FROM TCreepersTradeMark t")
public class TCreepersTradeMark extends com.fosun.fc.modules.entity.BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6625120453503696059L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_TRADE_MARK_ID_GENERATOR", sequenceName="SEQ_CREEPERS_TRADE_MARK")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_TRADE_MARK_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(length=500)
	private String applicant;

	@Column(name="APPLY_NO", length=200)
	private String applyNo;

	@Column(name="CATEGORY_NO", length=500)
	private String categoryNo;

	@Column(name="MER_NAME", length=1000)
	private String merName;

	@Column(name="MER_NO", length=100)
	private String merNo;
	
	@Column(name="SEQ_NO")
	private BigDecimal seqNo;

	@Column(name="TRADE_MARK_NAME", length=200)
	private String tradeMarkName;

	@Column(length=200)
	private String ware;
	
	@Column(length=200)
	private String memo;

    public TCreepersTradeMark() {
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

	public String getApplyNo() {
		return this.applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getCategoryNo() {
		return this.categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}


	public String getMerName() {
		return merName;
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

    public BigDecimal getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(BigDecimal seqNo) {
		this.seqNo = seqNo;
	}

	public String getTradeMarkName() {
		return this.tradeMarkName;
	}

	public void setTradeMarkName(String tradeMarkName) {
		this.tradeMarkName = tradeMarkName;
	}

	public String getWare() {
		return this.ware;
	}

	public void setWare(String ware) {
		this.ware = ware;
	}

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}