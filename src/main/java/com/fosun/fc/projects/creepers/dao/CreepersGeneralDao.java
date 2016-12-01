package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersGeneral;
/**
 * 
 *<p>
 *description:
 *简版征信概要信息表
 *</p>
 * @author MaXin
 * @since 2016年5月25日
 * @see
 */
public interface CreepersGeneralDao
        extends JpaRepository<TCreepersGeneral, Long>, JpaSpecificationExecutor<TCreepersGeneral> {

    @Query("select t from TCreepersGeneral t where t.rptNo = :rptNo")
    List<TCreepersGeneral> queryByRptNo(@Param("rptNo") String rptNo);

}
