package com.fosun.fc.projects.creepers.dto;

import java.util.Date;


public class CreepersJobDTO extends CreepersBaseDTO {

	public static final String STATUS_RUNNING = "1";       
    public static final String STATUS_NOT_RUNNING = "0";       
    public static final String CONCURRENT_IS = "1";       
    public static final String CONCURRENT_NOT = "0";       
    
	private static final long serialVersionUID = 5221858952782258278L;

	private String description;

	private String isConcurrent;

	private String jobClass;

	private String jobName;

	private String memo;

	private String methodName;

	private Date nextTime;

	private Date previousTime;

	private String springId;

	private Date startTime;

	private String status;

	private String jobGroup;

	private String cronExpression;
	
	private String indexUrl;
	
	private int threadNum;	

	public CreepersJobDTO() {
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsConcurrent() {
		return isConcurrent;
	}

	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Date getNextTime() {
		return nextTime;
	}

	public void setNextTime(Date nextTime) {
		this.nextTime = nextTime;
	}

	public Date getPreviousTime() {
		return previousTime;
	}

	public void setPreviousTime(Date previousTime) {
		this.previousTime = previousTime;
	}

	public String getSpringId() {
		return springId;
	}

	public void setSpringId(String springId) {
		this.springId = springId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}


	public String getIndexUrl() {
		return indexUrl;
	}


	public void setIndexUrl(String indexUrl) {
		this.indexUrl = indexUrl;
	}


	public int getThreadNum() {
		return threadNum;
	}


	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}


}