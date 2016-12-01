package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersEntShareChange;

/**
*
* <p>
* description:
* T_CREEPERS_ENT_SHARE_CHANGE	爬虫信息-股权变更信息
* <p>
* @author MaXin
* @since 2016-8-2 13:44:41
* @see
*/

public interface CreepersEntShareChangeDao extends JpaRepository<TCreepersEntShareChange, Long>, JpaSpecificationExecutor<TCreepersEntShareChange> {
}