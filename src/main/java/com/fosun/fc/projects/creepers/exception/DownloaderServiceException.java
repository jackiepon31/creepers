package com.fosun.fc.projects.creepers.exception;

/**
 * Downloader层用Exception.
 * 
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * 
 */
public class DownloaderServiceException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;

	public DownloaderServiceException() {
		super();
	}

	public DownloaderServiceException(String message) {
		super(message);
	}

	public DownloaderServiceException(Throwable cause) {
		super(cause);
	}

	public DownloaderServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
