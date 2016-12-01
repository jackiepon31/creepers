package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersMerPenalty;

/**
*
* <p>
* description:
* T_CREEPERS_MER_PENALTY	爬虫信息-行政处罚信息
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersMerPenaltyDao extends JpaRepository<TCreepersMerPenalty, Long>, JpaSpecificationExecutor<TCreepersMerPenalty> {
}