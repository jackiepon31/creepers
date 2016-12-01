package com.fosun.fc.projects.creepers.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the T_CREEPERS_TAX_EVASION database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_TAX_EVASION")
@NamedQuery(name="TCreepersTaxEvasion.findAll", query="SELECT t FROM TCreepersTaxEvasion t")
public class TCreepersTaxEvasion extends com.fosun.fc.modules.entity.BaseEntity implements Serializable {

    private static final long serialVersionUID = -1006322977569595363L;

    @Id
	@SequenceGenerator(name="T_CREEPERS_TAX_EVASION_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_TAX_EVASION")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_TAX_EVASION_ID_GENERATOR")
	private long id;

	private String code;

	private String memo;

	private String name;

	private String province;

	public TCreepersTaxEvasion() {
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

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

}