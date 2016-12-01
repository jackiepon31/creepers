package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersMerBranch;

/**
*
* <p>
* description:
* T_CREEPERS_MER_BRANCH	爬虫信息-主要人员信息
* <p>
* @author MaXin
* @since 2016-07-14 17:38:05
* @see
*/

public interface CreepersMerBranchDao extends JpaRepository<TCreepersMerBranch, Long>, JpaSpecificationExecutor<TCreepersMerBranch> {
}