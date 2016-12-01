package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersHlDetail;
/**
 * 
 *<p>
 *description:
 *简版征信住房贷款明细信息表
 *</p>
 * @author MaXin
 * @since 2016年5月25日
 * @see
 */
public interface CreepersHlDetailDao
        extends JpaRepository<TCreepersHlDetail, Long>, JpaSpecificationExecutor<TCreepersHlDetail> {

    @Query("select t from TCreepersHlDetail t where t.rptNo = :rptNo")
    List<TCreepersHlDetail> queryByRptNo(@Param("rptNo")String rptNo);

}
