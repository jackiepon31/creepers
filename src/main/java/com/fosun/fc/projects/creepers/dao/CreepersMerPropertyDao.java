package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersMerProperty;

/**
*
* <p>
* description:
* T_CREEPERS_MER_PROPERTY	爬虫信息-动产抵押登记信息信息
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersMerPropertyDao extends JpaRepository<TCreepersMerProperty, Long>, JpaSpecificationExecutor<TCreepersMerProperty> {
}