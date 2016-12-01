package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersPublicSanction;
/**
 * 
 *<p>
 *description:
 *简版征信行政处罚记录信息表
 *</p>
 * @author MaXin
 * @since 2016年5月25日
 * @see
 */
public interface CreepersPublicSanctionDao
        extends JpaRepository<TCreepersPublicSanction, Long>, JpaSpecificationExecutor<TCreepersPublicSanction> {

    @Query("select t from TCreepersPublicSanction t where t.rptNo = :rptNo")
    List<TCreepersPublicSanction> queryByRptNo(@Param("rptNo")String rptNo);

}
