package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersMerBasic;

/**
*
* <p>
* description:
* T_CREEPERS_MER_BASIC	爬虫信息-工商注册基本信息
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersMerBasicDao extends JpaRepository<TCreepersMerBasic, Long>, JpaSpecificationExecutor<TCreepersMerBasic> {
}