package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersConfig;

/**
 *
 * <p>
 * description: T_CREEPERS_CONFIG 爬虫配置信息表
 * <p>
 * 
 * @author MaXin
 * @since 2016-08-30 15:42:59
 * @see
 */

public interface CreepersConfigDao
        extends JpaRepository<TCreepersConfig, Long>, JpaSpecificationExecutor<TCreepersConfig> {

    
    TCreepersConfig findTop1ByRequestType(String requestType);

    long countByRequestType(String requestType);
}
