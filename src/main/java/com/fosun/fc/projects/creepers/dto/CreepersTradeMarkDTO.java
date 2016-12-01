package com.fosun.fc.projects.creepers.dto;

import java.math.BigDecimal;


public class CreepersTradeMarkDTO extends CreepersBaseDTO {


	/**
	 * 
	 */
	private static final long serialVersionUID = 91519179682430935L;

	private Long id;

	private String applicant;

	private String applyNo;

	private String categoryNo;

	private String merName;

	private String merNo;
	
	private BigDecimal seqNo;

	private String tradeMarkName;

	private String ware;

	public CreepersTradeMarkDTO() {
	}

	public Long getId() {
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

}