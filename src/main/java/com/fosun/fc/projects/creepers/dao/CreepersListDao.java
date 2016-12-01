package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersList;

/**
*
* <p>
* description:
* T_CREEPERS_LIST	爬虫信息列表
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersListDao extends JpaRepository<TCreepersList, Long>, JpaSpecificationExecutor<TCreepersList> {
}