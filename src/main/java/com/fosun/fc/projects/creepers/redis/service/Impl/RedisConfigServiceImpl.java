package com.fosun.fc.projects.creepers.redis.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.dao.CreepersConfigDao;
import com.fosun.fc.projects.creepers.dto.CreepersConfigDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersConfig;

@Component
public class RedisConfigServiceImpl extends AbstractRedisCacheService<String, CreepersConfigDTO> {
	@Autowired
	private CreepersConfigDao creepersConfigDao; 
	@Override
	public void refresh() {
		// 刷新全部缓存
		List<TCreepersConfig> entityList = (List<TCreepersConfig>)creepersConfigDao.findAll();
		List<CreepersConfigDTO> dtoList = BeanMapper.mapList(entityList, CreepersConfigDTO.class);
		for (CreepersConfigDTO creepersConfigDTO : dtoList) {
			set(creepersConfigDTO.getRequestType(), creepersConfigDTO);
		}
	}
}
