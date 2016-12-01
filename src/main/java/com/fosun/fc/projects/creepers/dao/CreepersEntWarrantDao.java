package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersEntWarrant;

/**
*
* <p>
* description:
* T_CREEPERS_ENT_WARRANT	爬虫信息-企业公示对外提供保证担保信息
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersEntWarrantDao extends JpaRepository<TCreepersEntWarrant, Long>, JpaSpecificationExecutor<TCreepersEntWarrant> {
}