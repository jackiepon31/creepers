package com.fosun.fc.projects.creepers.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CreepersJudgementDTO extends CreepersBaseDTO {

    private static final long serialVersionUID = 2064864931232315923L;
    @Temporal(TemporalType.DATE)
	private Date caseDt;

	private String caseNo;

	private String caseTitle;

	private String merNo;
	
	private String docId;
	
	private String court;

	private String merName;


	public CreepersJudgementDTO() {
	}

	public Date getCaseDt() {
		return this.caseDt;
	}

	public void setCaseDt(Date caseDt) {
		this.caseDt = caseDt;
	}

	public String getCaseNo() {
		return this.caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public String getCaseTitle() {
		return this.caseTitle;
	}

	public void setCaseTitle(String caseTitle) {
		this.caseTitle = caseTitle;
	}

	public String getMerNo() {
		return this.merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getCourt() {
		return court;
	}

	public void setCourt(String court) {
		this.court = court;
	}

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }
}
