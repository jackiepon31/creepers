package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersCourtDisInfo;

/**
*
* <p>
* description:
* T_CREEPERS_COURT_DIS_INFO	最高法失信人黑名单明细表
* <p>
* @author MaXin
* @since 2016-10-10 15:40:55
* @see
*/

public interface CreepersCourtDisInfoDao extends JpaRepository<TCreepersCourtDisInfo, Long>, JpaSpecificationExecutor<TCreepersCourtDisInfo> {

    @Query("select t from TCreepersCourtDisInfo t where t.name = :name")
    List<TCreepersCourtDisInfo> findByName(@Param("name") String name);

    @Modifying(clearAutomatically = true)
    @Query("delete  from TCreepersCourtDisInfo t where t.name = :name")
    void deleteByName(@Param("name") String name);
}