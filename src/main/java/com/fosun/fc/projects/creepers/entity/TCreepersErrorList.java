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

import com.fosun.fc.modules.entity.BaseEntity;

/**
 * The persistent class for the T_CREEPERS_ERROR_LIST database table.
 * 
 */
@Entity
@Table(name = "T_CREEPERS_ERROR_LIST")
@NamedQuery(name = "TCreepersErrorList.findAll", query = "SELECT t FROM TCreepersErrorList t")
public class TCreepersErrorList extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "T_CREEPERS_ERROR_LIST_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_ERROR_LIST")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_ERROR_LIST_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	private long id;

	@Column(name = "MER_NAME", length = 500)
	private String merName;

	@Column(name = "TASK_TYPE", length = 100)
	private String taskType;

	@Column(name = "ERROR_DESC", length = 2000)
	private String errorDesc;

	private String memo;

	public TCreepersErrorList() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMerName() {
		return this.merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	
}