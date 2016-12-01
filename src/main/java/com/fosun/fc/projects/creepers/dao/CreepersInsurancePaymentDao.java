package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersInsurancePayment;

/**
*
* <p>
* description:
* T_CREEPERS_INSURANCE_PAYMENT	社保应缴记录表
* <p>
* @author MaXin
* @since 2016-09-06 15:26:29
* @see
*/

public interface CreepersInsurancePaymentDao extends JpaRepository<TCreepersInsurancePayment, Long>, JpaSpecificationExecutor<TCreepersInsurancePayment> {
    
    @Query("select t from TCreepersInsurancePayment t where t.certNo = :certNo")
    public List<TCreepersInsurancePayment> queryListByCertNo(@Param("certNo") String certNo);
    
    @Modifying
    @Query("delete from TCreepersInsurancePayment t where t.certNo = :certNo")
    public void deleteByCertNo(@Param("certNo") String certNo);
}