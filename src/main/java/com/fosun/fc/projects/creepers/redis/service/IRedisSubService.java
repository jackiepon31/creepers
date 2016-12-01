package com.fosun.fc.projects.creepers.redis.service;

import java.io.Serializable;

/**
 * 
 * <p>
 * MQ消息接收服务
 * </p>
 * 
 * @author pengyk
 * @since 2016-8-03
 * @see
 */
public interface IRedisSubService {
	  public void handleMessage(Serializable message);
}
