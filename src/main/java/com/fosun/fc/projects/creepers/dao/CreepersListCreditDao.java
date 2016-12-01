package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersListCredit;

/**
 *
 * <p>
 * description: T_CREEPERS_LIST_CREDIT 爬虫任务队列-人行征信报告
 * <p>
 * 
 * @author MaXin
 * @since 2016-10-19 10:36:31
 * @see
 */

public interface CreepersListCreditDao
        extends JpaRepository<TCreepersListCredit, Long>, JpaSpecificationExecutor<TCreepersListCredit> {
    
    TCreepersListCredit findTop1ByUserCode(String userCode);

    long countByUserCode(String UserCode);
    
    List<TCreepersListCredit> findByUserCode(String userCode);
    
    List<TCreepersListCredit> findTop1ByUserCodeAndMessageCode(String userCode,String messageCode);
}
