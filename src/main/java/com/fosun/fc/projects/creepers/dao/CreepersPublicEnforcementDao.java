package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersPublicEnforcement;
/**
 * 
 *<p>
 *description:
 *简版征信强制执行记录信息表
 *</p>
 * @author MaXin
 * @since 2016年5月25日
 * @see
 */
public interface CreepersPublicEnforcementDao
        extends JpaRepository<TCreepersPublicEnforcement, Long>, JpaSpecificationExecutor<TCreepersPublicEnforcement> {

    @Query("select t from TCreepersPublicEnforcement t where t.rptNo = :rptNo")
    List<TCreepersPublicEnforcement> queryByRptNo(@Param("rptNo")String rptNo);

}
