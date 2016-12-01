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
 * The persistent class for the T_CREEPERS_AREA database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_AREA")
@NamedQuery(name="TCreepersArea.findAll", query="SELECT t FROM TCreepersArea t")
public class TCreepersArea extends com.fosun.fc.modules.entity.BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4635286610708676900L;

	@Id
	@SequenceGenerator(name="T_CREEPERS_AREA_ID_GENERATOR", sequenceName="SEQ_CREEPERS_AREA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_AREA_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private long id;

	@Column(length=2)
	private String code;

	@Column(length=100)
	private String name;

	public TCreepersArea() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}