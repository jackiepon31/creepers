package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersShixin;

/**
 *
 * <p>
 * description: ﻿T_CREEPERS_SHIXIN 爬虫信息-失信被执行人信息
 * <p>
 * 
 * @author LiZhanPing
 * @since 2016-08-29 17:42:26
 * @see
 */

public interface CreepersShixinDao
        extends JpaRepository<TCreepersShixin, Long>, JpaSpecificationExecutor<TCreepersShixin> {

    @Query("select t from TCreepersShixin t where t.name = :name")
    List<TCreepersShixin> findByName(@Param("name") String name);

    @Modifying(clearAutomatically = true)
    @Query("delete  from TCreepersShixin t where t.name = :name")
    void deleteByName(@Param("name") String name);

    TCreepersShixin findTopByName(String name);
}
