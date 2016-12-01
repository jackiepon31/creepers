package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersListFund;

/**
 *
 * <p>
 * description: T_CREEPERS_LIST_FUND 爬虫任务队列-上海公积金
 * <p>
 * 
 * @author pengyk
 * @since 2016-09-07 10:45:18
 * @see
 */

public interface CreepersListFundDao
        extends JpaRepository<TCreepersListFund, Long>, JpaSpecificationExecutor<TCreepersListFund> {
    @Query("select t from TCreepersListFund t where t.userCode = :userCode")
    List<TCreepersListFund> queryListByUserCode(@Param("userCode") String userCode);

    @Modifying(clearAutomatically = true)
    @Query("update TCreepersListFund t set t.flag = :flag, t.updatedDt = sysdate where t.userCode = :userCode")
    void updateListByUserCode(@Param("userCode") String userCode, @Param("flag") String flag);

    TCreepersListFund findTop1ByUserCode(String userCode);
}
