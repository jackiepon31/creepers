package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersInsuranceBasic;

/**
*
* <p>
* description:
* T_CREEPERS_INSURANCE_BASIC	社保基础信息表
* <p>
* @author MaXin
* @since 2016-09-06 15:26:29
* @see
*/

public interface CreepersInsuranceBasicDao extends JpaRepository<TCreepersInsuranceBasic, Long>, JpaSpecificationExecutor<TCreepersInsuranceBasic> {
    
    @Query("select t from TCreepersInsuranceBasic t where t.certNo = :certNo")
    public List<TCreepersInsuranceBasic> queryListByCertNo(@Param("certNo") String certNo);
    
    @Modifying
    @Query("delete from TCreepersInsuranceBasic t where t.certNo = :certNo")
    void deleteByCertNo(@Param("certNo") String certNo);
}