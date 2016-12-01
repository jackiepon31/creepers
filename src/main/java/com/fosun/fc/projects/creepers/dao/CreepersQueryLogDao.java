package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersQueryLog;
/**
 * 
 *<p>
 *description:
 *简版征信查询记录信息表
 *</p>
 * @author MaXin
 * @since 2016年5月25日
 * @see
 */
public interface CreepersQueryLogDao
        extends JpaRepository<TCreepersQueryLog, Long>, JpaSpecificationExecutor<TCreepersQueryLog> {

    @Query("select t from TCreepersQueryLog t where t.rptNo = :rptNo")
    List<TCreepersQueryLog> queryByRptNo(@Param("rptNo")String rptNo);

}
