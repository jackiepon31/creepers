package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersMerAbnormal;

/**
*
* <p>
* description:
* T_CREEPERS_MER_ABNORMAL	爬虫信息-经营异常信息
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersMerAbnormalDao extends JpaRepository<TCreepersMerAbnormal, Long>, JpaSpecificationExecutor<TCreepersMerAbnormal> {
}