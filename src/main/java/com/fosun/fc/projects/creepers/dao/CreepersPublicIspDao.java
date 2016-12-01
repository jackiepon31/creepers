package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersPublicIsp;
/**
 * 
 *<p>
 *description:
 *简版征信电信欠费记录信息表
 *</p>
 * @author MaXin
 * @since 2016年5月25日
 * @see
 */
public interface CreepersPublicIspDao
        extends JpaRepository<TCreepersPublicIsp, Long>, JpaSpecificationExecutor<TCreepersPublicIsp> {

    @Query("select t from TCreepersPublicIsp t where t.rptNo = :rptNo")
    List<TCreepersPublicIsp> queryByRptNo(@Param("rptNo")String rptNo);

}
