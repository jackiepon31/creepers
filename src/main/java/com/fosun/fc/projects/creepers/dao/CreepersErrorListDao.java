package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersErrorList;

/**
 *
 * <p>
 * description: T_CREEPERS_ERROR_LIST 爬虫异常列表
 * <p>
 * 
 * @author MaXin
 * @since 2016-07-14 17:38:05
 * @see
 */

public interface CreepersErrorListDao
        extends JpaRepository<TCreepersErrorList, Long>, JpaSpecificationExecutor<TCreepersErrorList> {
    @Query("select t from TCreepersErrorList t where t.merName = :merName and t.taskType = :taskType")
    List<TCreepersErrorList> queryListByMerName(@Param("merName") String merName, @Param("taskType") String taskType);
}
