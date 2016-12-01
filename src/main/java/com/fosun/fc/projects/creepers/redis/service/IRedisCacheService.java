package com.fosun.fc.projects.creepers.redis.service;
/**
 * 
 * <p>
 * 缓存服务
 * </p>
 * 
 * @author pengyk
 * @since 2016-8-29       
 * @see
 */
public interface IRedisCacheService<K,V> {
	public void set(K key,V value);
}
