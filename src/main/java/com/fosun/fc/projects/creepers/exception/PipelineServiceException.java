package com.fosun.fc.projects.creepers.exception;

/**
 * Pipeline层公用的Exception.
 * 
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * 
 */
public class PipelineServiceException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;

	public PipelineServiceException() {
		super();
	}

	public PipelineServiceException(String message) {
		super(message);
	}

	public PipelineServiceException(Throwable cause) {
		super(cause);
	}

	public PipelineServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
