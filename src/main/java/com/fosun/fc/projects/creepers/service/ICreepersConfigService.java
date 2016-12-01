package com.fosun.fc.projects.creepers.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.fosun.fc.projects.creepers.dto.CreepersConfigDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersConfig;

/**
 * 
 * <p>
 * description: 爬虫配置信息表 T_CREEPERS_CONFIG service
 * </p>
 * 
 * @author MaXin
 * @since 2016-9-5 15:31:22
 * @see
 */
public interface ICreepersConfigService extends BaseService {

    public void updateEntity(TCreepersConfig entity);

    public Page<CreepersConfigDTO> queryForListByRequestType(Map<String, Object> searchParams, int pageNumber,
            int pageSize, String sortType) throws Exception;

    public TCreepersConfig queryEntity(String requestType);

    public long countEntity(String requestType);
}
