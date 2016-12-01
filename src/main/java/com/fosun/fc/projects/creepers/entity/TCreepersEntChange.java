package com.fosun.fc.projects.creepers.entity;

import java.io.Serializable;
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
 * The persistent class for the T_CREEPERS_ENT_CHANGE database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_ENT_CHANGE")
@NamedQuery(name="TCreepersEntChange.findAll", query="SELECT t FROM TCreepersEntChange t")
public class TCreepersEntChange extends com.fosun.fc.modules.entity.BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9165357400405320755L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_ENT_CHANGE_ID_GENERATOR", sequenceName="SEQ_CREEPERS_ENT_CHANGE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_ENT_CHANGE_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="CHANGE_DT")
	private Date changeDt;

	@Column(length=100)
	private String item;

	@Column(name="MER_NO", length=100)
	private String merNo;

	@Column(name="POST_INFO", length=50)
	private String postInfo;

	@Column(name="PRE_INFO", length=50)
	private String preInfo;

	@Column(name="SEQ_NO", length=5)
	private String seqNo;

	public TCreepersEntChange() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getChangeDt() {
		return this.changeDt;
	}

	public void setChangeDt(Date changeDt) {
		this.changeDt = changeDt;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getMerNo() {
		return this.merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	public String getPostInfo() {
		return this.postInfo;
	}

	public void setPostInfo(String postInfo) {
		this.postInfo = postInfo;
	}

	public String getPreInfo() {
		return this.preInfo;
	}

	public void setPreInfo(String preInfo) {
		this.preInfo = preInfo;
	}

	public String getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

}