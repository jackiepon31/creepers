package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersPublicTax;
/**
 * 
 *<p>
 *description:
 *简版征信欠税记录信息表
 *</p>
 * @author MaXin
 * @since 2016年5月25日
 * @see
 */
public interface CreepersPublicTaxDao
        extends JpaRepository<TCreepersPublicTax, Long>, JpaSpecificationExecutor<TCreepersPublicTax> {

    @Query("select t from TCreepersPublicTax t where t.rptNo = :rptNo")
    List<TCreepersPublicTax> queryByRptNo(@Param("rptNo") String rptNo);

}
