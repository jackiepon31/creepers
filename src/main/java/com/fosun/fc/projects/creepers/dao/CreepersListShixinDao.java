package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersListShixin;

/**
 *
 * <p>
 * description: ﻿T_CREEPERS_LIST_SHIXIN 爬虫任务队列-失信被执行人信息
 * <p>
 * 
 * @author LiZhanPing
 * @since 2016-08-31 21:42:36
 * @see
 */
public interface CreepersListShixinDao
        extends JpaRepository<TCreepersListShixin, Long>, JpaSpecificationExecutor<TCreepersListShixin> {

    @Query("select t from TCreepersListShixin t  where t.merName = :merName")
    public List<TCreepersListShixin> queryListByMerName(@Param("merName") String merName);

    @Modifying(clearAutomatically = true)
    @Query("update TCreepersListShixin t set t.flag = :flag , t.updatedDt = sysdate where t.merName = :merName")
    void updateListByMerName(@Param("merName") String merName, @Param("flag") String flag);
}
