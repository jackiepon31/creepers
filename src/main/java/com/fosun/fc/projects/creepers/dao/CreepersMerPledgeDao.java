package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersMerPledge;

/**
*
* <p>
* description:
* T_CREEPERS_MER_PLEDGE	爬虫信息-股权出质登记信息
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersMerPledgeDao extends JpaRepository<TCreepersMerPledge, Long>, JpaSpecificationExecutor<TCreepersMerPledge> {
}