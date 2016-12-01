package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersInsuranceSum;

/**
*
* <p>
* description:
* T_CREEPERS_INSURANCE_SUM	社保累计缴费信息表
* <p>
* @author MaXin
* @since 2016-09-06 15:26:29
* @see
*/

public interface CreepersInsuranceSumDao extends JpaRepository<TCreepersInsuranceSum, Long>, JpaSpecificationExecutor<TCreepersInsuranceSum> {
    
    @Query("select t from TCreepersInsuranceSum t where t.certNo = :certNo")
    public List<TCreepersInsuranceSum> queryListByCertNo(@Param("certNo") String certNo);
    
    @Modifying
    @Query("delete from TCreepersInsuranceSum t where t.certNo = :certNo")
    public void deleteByCertNo(@Param("certNo") String certNo);
}