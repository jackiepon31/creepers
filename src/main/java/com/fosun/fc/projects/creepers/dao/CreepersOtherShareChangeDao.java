package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersOtherShareChange;

/**
*
* <p>
* description:
* T_CREEPERS_OTHER_SHARE_CHANGE	爬虫信息-司法股东变更登记信息
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersOtherShareChangeDao extends JpaRepository<TCreepersOtherShareChange, Long>, JpaSpecificationExecutor<TCreepersOtherShareChange> {
}