package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersInsuranceUnit;

/**
*
* <p>
* description:
* T_CREEPERS_INSURANCE_UNIT	公积金账户信息表
* <p>
* @author MaXin
* @since 2016-09-06 15:26:29
* @see
*/

public interface CreepersInsuranceUnitDao extends JpaRepository<TCreepersInsuranceUnit, Long>, JpaSpecificationExecutor<TCreepersInsuranceUnit> {
    
    @Query("select t from TCreepersInsuranceUnit t where t.certNo = :certNo")
    public List<TCreepersInsuranceUnit> queryListByCertNo(@Param("certNo") String certNo);
    
    @Modifying
    @Query("delete from TCreepersInsuranceUnit t where t.certNo = :certNo")
    public void deleteByCertNo(@Param("certNo") String certNo);
}