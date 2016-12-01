package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersEntIntel;

/**
*
* <p>
* description:
* T_CREEPERS_ENT_INTEL	爬虫信息-企业公示知识产权出质登记信息
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersEntIntelDao extends JpaRepository<TCreepersEntIntel, Long>, JpaSpecificationExecutor<TCreepersEntIntel> {
}