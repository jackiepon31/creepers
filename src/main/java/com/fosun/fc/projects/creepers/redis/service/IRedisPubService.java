package com.fosun.fc.projects.creepers.redis.service;
/**
 * 
 * <p>
 * MQ消息发布服务,controller中不能直接调用redis接口
 * </p>
 * 
 * @author pengyk
 * @since 2016-8-03
 * @see
 */
public interface IRedisPubService {
	public void sendMsg(String channel,String message);
}
