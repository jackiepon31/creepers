package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersEntShare;

/**
*
* <p>
* description:
* T_CREEPERS_ENT_SHARE	爬虫信息-企业公示股东及出资信息
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersEntShareDao extends JpaRepository<TCreepersEntShare, Long>, JpaSpecificationExecutor<TCreepersEntShare> {
}