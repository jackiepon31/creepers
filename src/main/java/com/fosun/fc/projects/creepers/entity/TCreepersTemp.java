package com.fosun.fc.projects.creepers.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the T_CREEPERS_TEMP database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_TEMP")
@NamedQuery(name="TCreepersTemp.findAll", query="SELECT t FROM TCreepersTemp t")
public class TCreepersTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_TEMP_ID_GENERATOR", sequenceName="SEQ_CREEPERS_TEMP")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_TEMP_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="MER_NO", length=100)
	private String merNo;
	@Column(name="MER_NAME", length=100)
	private String merName;
	@Column(name="STATUS", length=100)
	private String status;	

	public TCreepersTemp() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMerNo() {
		return this.merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	protected String getMerName() {
		return merName;
	}

	protected void setMerName(String merName) {
		this.merName = merName;
	}

	protected String getStatus() {
		return status;
	}

	protected void setStatus(String status) {
		this.status = status;
	}

}