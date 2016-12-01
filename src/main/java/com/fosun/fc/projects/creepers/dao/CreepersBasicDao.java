package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersBasic;

/**
 * 
 *<p>
 *description:
 *简版征信基本信息表
 *</p>
 * @author MaXin
 * @since 2016年5月25日
 * @see
 */

public interface CreepersBasicDao
        extends JpaRepository<TCreepersBasic, Long>, JpaSpecificationExecutor<TCreepersBasic> {

    @Query("select t from TCreepersBasic t where t.rptNo = :rptNo")
    List<TCreepersBasic> queryByRptNo(@Param("rptNo")String rptNo);

    @Query("select t from TCreepersBasic t where t.name = :name")
    List<TCreepersBasic> queryByName(@Param("name")String name);

    @Query("select t from TCreepersBasic t where t.idNo = :idNo")
    List<TCreepersBasic> queryByIdNo(@Param("idNo")String idNo);

}
