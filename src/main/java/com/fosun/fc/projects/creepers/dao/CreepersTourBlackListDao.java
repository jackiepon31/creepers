package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersTourBlackList;

/**
 *
 * <p>
 * description: T_CREEPERS_TOUR_BLACK_LIST 信用中国-旅游黑名单明细
 * <p>
 * 
 * @author MaXin
 * @since 2016-10-31 17:22:04
 * @see
 */

public interface CreepersTourBlackListDao extends JpaRepository<TCreepersTourBlackList, Long>, JpaSpecificationExecutor<TCreepersTourBlackList> {

    TCreepersTourBlackList findTop1ByGuideNo(String guideNo);

    List<TCreepersTourBlackList> findByGuideNo(String guideNo);

    List<TCreepersTourBlackList> findByAgentNameAndType(String merName, String type);
}
