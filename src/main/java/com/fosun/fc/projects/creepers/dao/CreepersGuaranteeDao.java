package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersGuarantee;
/**
 * 
 *<p>
 *description:
 *简版征信为他人担保信息表
 *</p>
 * @author MaXin
 * @since 2016年5月25日
 * @see
 */
public interface CreepersGuaranteeDao
        extends JpaRepository<TCreepersGuarantee, Long>, JpaSpecificationExecutor<TCreepersGuarantee> {

    @Query("select t from TCreepersGuarantee t where t.rptNo = :rptNo")
    List<TCreepersGuarantee> queryByRptNo(@Param("rptNo")String rptNo);

}
