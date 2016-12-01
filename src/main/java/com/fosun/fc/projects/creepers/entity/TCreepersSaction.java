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
 * The persistent class for the T_CREEPERS_SACTION database table.
 * 
 */
@Entity
@Table(name="T_CREEPERS_SACTION")
@NamedQuery(name="TCreepersSaction.findAll", query="SELECT t FROM TCreepersSaction t")
public class TCreepersSaction extends com.fosun.fc.modules.entity.BaseEntity implements Serializable {

    private static final long serialVersionUID = 2436869944250637839L;

    @Id
	@SequenceGenerator(name="T_CREEPERS_SACTION_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_COURT_SACTION")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CREEPERS_SACTION_ID_GENERATOR")
	private long id;

	private String content;

	private String memo;

	@Column(name="MODIFY_DT")
	private String modifyDt;

	private String name;

	private String province;

	private String type;

	public TCreepersSaction() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getModifyDt() {
		return this.modifyDt;
	}

	public void setModifyDt(String modifyDt) {
		this.modifyDt = modifyDt;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}