package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersCompensatory;
/**
 * 
 *<p>
 *description:
 *简版征信保证人代偿信息表
 *</p>
 * @author MaXin
 * @since 2016年5月25日
 * @see
 */
public interface CreepersCompensatoryDao
        extends JpaRepository<TCreepersCompensatory, Long>, JpaSpecificationExecutor<TCreepersCompensatory> {

    @Query("select t from TCreepersCompensatory t where t.rptNo = :rptNo")
    List<TCreepersCompensatory> queryByRptNo(@Param("rptNo") String rptNo);

}
