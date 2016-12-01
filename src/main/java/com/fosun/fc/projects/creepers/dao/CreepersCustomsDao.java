package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersCustoms;

/**
*
* <p>
* description:
* T_CREEPERS_CUSTOMS	爬虫信息-海关信息
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersCustomsDao extends JpaRepository<TCreepersCustoms, Long>, JpaSpecificationExecutor<TCreepersCustoms> {
}