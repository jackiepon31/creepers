package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersMerCheck;

/**
*
* <p>
* description:
* T_CREEPERS_MER_CHECK	爬虫信息-抽查检查信息
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersMerCheckDao extends JpaRepository<TCreepersMerCheck, Long>, JpaSpecificationExecutor<TCreepersMerCheck> {
}