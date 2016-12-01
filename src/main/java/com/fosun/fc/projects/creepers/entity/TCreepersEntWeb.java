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
 * The persistent class for the T_CREEPERS_ENT_WEB database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_ENT_WEB")
@NamedQuery(name="TCreepersEntWeb.findAll", query="SELECT t FROM TCreepersEntWeb t")
public class TCreepersEntWeb extends com.fosun.fc.modules.entity.BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5082212644838002671L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_ENT_WEB_ID_GENERATOR", sequenceName="SEQ_CREEPERS_ENT_WEB")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_ENT_WEB_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(name="MER_NO", length=100)
	private String merNo;

	@Column(length=100)
	private String name;

	@Column(name="\"TYPE\"", length=50)
	private String type;

	@Column(length=300)
	private String url;

	public TCreepersEntWeb() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}