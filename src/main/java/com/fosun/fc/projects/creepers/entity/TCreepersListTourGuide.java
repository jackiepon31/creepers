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
 * The persistent class for the T_CREEPERS_LIST_TOUR_GUIDE database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_LIST_TOUR_GUIDE")
@NamedQuery(name="TCreepersListTourGuide.findAll", query="SELECT t FROM TCreepersListTourGuide t")
public class TCreepersListTourGuide extends com.fosun.fc.modules.entity.BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_LIST_TOUR_GUIDE_ID_GENERATOR", sequenceName="SEQ_CREEPERS_LIST_TOUR_GUIDE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_LIST_TOUR_GUIDE_ID_GENERATOR")
	private long id;

	@Column(name="CARD_NO")
	private String cardNo;

	@Column(name="CERT_NO")
	private String certNo;

	@Column(name="GUIDE_NO")
	private String guideNo;

	public TCreepersListTourGuide() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCertNo() {
		return this.certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getGuideNo() {
		return this.guideNo;
	}

	public void setGuideNo(String guideNo) {
		this.guideNo = guideNo;
	}

}