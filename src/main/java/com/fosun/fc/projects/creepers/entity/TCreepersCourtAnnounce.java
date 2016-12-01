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
 * The persistent class for the T_CREEPERS_COURT_ANNOUNCE database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_COURT_ANNOUNCE")
@NamedQuery(name="TCreepersCourtAnnounce.findAll", query="SELECT t FROM TCreepersCourtAnnounce t")
public class TCreepersCourtAnnounce extends com.fosun.fc.modules.entity.BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6101469808982097172L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_COURT_ANNOUNCE_ID_GENERATOR", sequenceName="SEQ_CREEPERS_COURT_ANNOUNCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_COURT_ANNOUNCE_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="ANNOUNCE_CONTENT", nullable=false, length=1000)
	private String announceContent;

	@Temporal(TemporalType.DATE)
	@Column(name="ANNOUNCE_DT", nullable=false)
	private Date announceDt;

	@Column(name="ANNOUNCE_TYPE", nullable=false, length=30)
	private String announceType;

	@Column(name="COURT_NAME", nullable=false, length=100)
	private String courtName;

	@Column(length=500)
	private String memo;

	@Column(name="MER_NAME", nullable=false, length=1000)
	private String merName;

	@Column(name="MER_NO", nullable=false, length=100)
    private String merNo;
	
	public TCreepersCourtAnnounce() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAnnounceContent() {
		return this.announceContent;
	}

	public void setAnnounceContent(String announceContent) {
		this.announceContent = announceContent;
	}

	public Date getAnnounceDt() {
		return this.announceDt;
	}

	public void setAnnounceDt(Date announceDt) {
		this.announceDt = announceDt;
	}

	public String getAnnounceType() {
		return this.announceType;
	}

	public void setAnnounceType(String announceType) {
		this.announceType = announceType;
	}

	public String getCourtName() {
		return this.courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

    
}