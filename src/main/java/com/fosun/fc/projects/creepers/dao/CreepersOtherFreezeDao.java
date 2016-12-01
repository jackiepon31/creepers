package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersOtherFreeze;

/**
*
* <p>
* description:
* T_CREEPERS_OTHER_FREEZE	爬虫信息-司法股权冻结信息
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersOtherFreezeDao extends JpaRepository<TCreepersOtherFreeze, Long>, JpaSpecificationExecutor<TCreepersOtherFreeze> {
}