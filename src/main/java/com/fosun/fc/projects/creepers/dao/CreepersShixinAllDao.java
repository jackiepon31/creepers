package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersShixinAll;

/**
 *
 * <p>
 * description: T_CREEPERS_SHIXIN_ALL 信用中国-失信人被执行人黑名单主表
 * <p>
 * 
 * @author MaXin
 * @since 2016-11-01 10:58:23
 * @see
 */

public interface CreepersShixinAllDao
        extends JpaRepository<TCreepersShixinAll, Long>, JpaSpecificationExecutor<TCreepersShixinAll> {
    
    List<TCreepersShixinAll> findByName(String name);

    TCreepersShixinAll findTopByName(String name);
}
