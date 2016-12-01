package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersIndex;

/**
*
* <p>
* description:
* T_CREEPERS_INDEX	爬虫队列指针信息表
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersIndexDao extends JpaRepository<TCreepersIndex, Long>, JpaSpecificationExecutor<TCreepersIndex> {
}