package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersArea;

/**
*
* <p>
* description:
* T_CREEPERS_AREA	区域列表
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersAreaDao extends JpaRepository<TCreepersArea, Long>, JpaSpecificationExecutor<TCreepersArea> {
}