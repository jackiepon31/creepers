package com.fosun.fc.projects.creepers.exception;

/**
 * Processor层公用的Exception.
 * 
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * 
 */
public class ProcessorServiceException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;

	public ProcessorServiceException() {
		super();
	}

	public ProcessorServiceException(String message) {
		super(message);
	}

	public ProcessorServiceException(Throwable cause) {
		super(cause);
	}

	public ProcessorServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
