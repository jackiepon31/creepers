package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersMerUndo;

/**
*
* <p>
* description:
* T_CREEPERS_MER_UNDO	爬虫信息-工商撤销信息
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersMerUndoDao extends JpaRepository<TCreepersMerUndo, Long>, JpaSpecificationExecutor<TCreepersMerUndo> {
}