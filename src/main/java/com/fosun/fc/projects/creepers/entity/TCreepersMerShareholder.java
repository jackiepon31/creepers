package com.fosun.fc.projects.creepers.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the T_CREEPERS_MER_SHAREHOLDER database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_MER_SHAREHOLDER")
@NamedQuery(name="TCreepersMerShareholder.findAll", query="SELECT t FROM TCreepersMerShareholder t")
public class TCreepersMerShareholder extends com.fosun.fc.modules.entity.BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3915714088257393381L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_MER_SHAREHOLDER_ID_GENERATOR", sequenceName="SEQ_CREEPERS_MER_SHAREHOLDER")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_MER_SHAREHOLDER_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;
	
	@Column(name="SHARE_TYPE", length=50)
	private String shareType;
	public String getShareType() {
		return shareType;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

	@Column(name="CERT_NO", length=100)
	private String certNo;

	@Column(name="CERT_TYPE", length=50)
	private String certType;

	@Column(name="MER_NO", length=100)
	private String merNo;

	@Column(length=200)
	private String name;

	public TCreepersMerShareholder() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCertNo() {
		return this.certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getCertType() {
		return this.certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getMerNo() {
		return this.merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}