package com.fosun.fc.projects.creepers.dto;

import java.io.Serializable;

public class CreepersErrorListDTO extends CreepersBaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5724643775983895438L;

	private String merName;

	private String taskType;

	private String errorDesc;

	public CreepersErrorListDTO() {
	}

	public String getMerName() {
		return this.merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
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