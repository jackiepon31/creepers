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
 * The persistent class for the T_CREEPERS_MER_CHANGE database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_MER_CHANGE")
@NamedQuery(name="TCreepersMerChange.findAll", query="SELECT t FROM TCreepersMerChange t")
public class TCreepersMerChange extends com.fosun.fc.modules.entity.BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2737310558980620004L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_MER_CHANGE_ID_GENERATOR", sequenceName="SEQ_CREEPERS_MER_CHANGE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_MER_CHANGE_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="CHANGE_DT")
	private Date changeDt;

	@Column(name="CHANGE_ITEM", length=200)
	private String changeItem;

	@Column(name="CHANGE_NEW", length=100)
	private String changeNew;

	@Column(name="CHANGE_OLD", length=50)
	private String changeOld;

	@Column(name="MER_NO", length=100)
	private String merNo;

	public TCreepersMerChange() {
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

	public String getChangeItem() {
		return this.changeItem;
	}

	public void setChangeItem(String changeItem) {
		this.changeItem = changeItem;
	}

	public String getChangeNew() {
		return this.changeNew;
	}

	public void setChangeNew(String changeNew) {
		this.changeNew = changeNew;
	}

	public String getChangeOld() {
		return this.changeOld;
	}

	public void setChangeOld(String changeOld) {
		this.changeOld = changeOld;
	}

	public String getMerNo() {
		return this.merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

}