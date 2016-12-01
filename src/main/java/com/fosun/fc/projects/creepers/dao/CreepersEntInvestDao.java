package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersEntInvest;

/**
*
* <p>
* description:
* T_CREEPERS_ENT_INVEST	爬虫信息-企业公示对外投资信息
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersEntInvestDao extends JpaRepository<TCreepersEntInvest, Long>, JpaSpecificationExecutor<TCreepersEntInvest> {
}