package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersMerShareholder;

/**
*
* <p>
* description:
* T_CREEPERS_MER_SHAREHOLDER	爬虫信息-股东信息
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersMerShareholderDao extends JpaRepository<TCreepersMerShareholder, Long>, JpaSpecificationExecutor<TCreepersMerShareholder> {
}