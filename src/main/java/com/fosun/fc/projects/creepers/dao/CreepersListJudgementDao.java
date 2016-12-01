package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersListJudgement;

/**
 *
 * <p>
 * description: T_CREEPERS_LIST_JUDGEMENT 爬虫任务队列-裁判文书信息
 * <p>
 * 
 * @author MaXin
 * @since 2016-08-05 10:45:18
 * @see
 */

public interface CreepersListJudgementDao
        extends JpaRepository<TCreepersListJudgement, Long>, JpaSpecificationExecutor<TCreepersListJudgement> {
    @Query("select t from TCreepersListJudgement t where t.merName = :merName")
    List<TCreepersListJudgement> queryListByMerName(@Param("merName") String merName);

    @Modifying(clearAutomatically = true)
    @Query("update TCreepersListJudgement t set t.flag = :flag, t.updatedDt = sysdate where t.merName = :merName")
    void updateListByMerName(@Param("merName") String merName, @Param("flag") String flag);
}
