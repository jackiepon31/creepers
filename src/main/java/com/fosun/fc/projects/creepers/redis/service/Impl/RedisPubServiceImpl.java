package com.fosun.fc.projects.creepers.redis.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.projects.creepers.redis.service.IRedisPubService;
@Component
@Transactional
public class RedisPubServiceImpl implements IRedisPubService {
    @Autowired(required = true)
    private RedisTemplate<String, String> redisTemplate;
	@Override
	public void sendMsg(String channel,String message) {
		redisTemplate.convertAndSend(channel, message);
	}

}
