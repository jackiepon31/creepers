package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersListInsurance;

/**
 *
 * <p>
 * description: T_CREEPERS_LIST_INSURANCE 爬虫任务队列-社保信息
 * <p>
 * 
 * @author lizhanping
 * @since 2016-09-06 15:26:29
 * @see
 */

public interface CreepersListInsuranceDao
        extends JpaRepository<TCreepersListInsurance, Long>, JpaSpecificationExecutor<TCreepersListInsurance> {

    @Query("select t from TCreepersListInsurance t where t.userCode = :userCode")
    List<TCreepersListInsurance> queryListByUserCode(@Param("userCode") String userCode);

    @Modifying(clearAutomatically = true)
    @Query("update TCreepersListInsurance t set t.flag = :flag, t.updatedDt = sysdate where t.userCode = :userCode")
    void updateListByUserCode(@Param("userCode") String userCode, @Param("flag") String flag);

    TCreepersListInsurance findTop1ByUserCode(String userCode);

}
