package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersOlDetail;
/**
 * 
 *<p>
 *description:
 *简版征信其他贷款明细信息表
 *</p>
 * @author MaXin
 * @since 2016年5月25日
 * @see
 */
public interface CreepersOlDetailDao
        extends JpaRepository<TCreepersOlDetail, Long>, JpaSpecificationExecutor<TCreepersOlDetail> {

    @Query("select t from TCreepersOlDetail t where t.rptNo = :rptNo")
    List<TCreepersOlDetail> queryByRptNo(@Param("rptNo")String rptNo);

}
