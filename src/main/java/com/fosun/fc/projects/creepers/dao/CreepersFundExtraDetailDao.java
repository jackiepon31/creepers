package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersFundExtraDetail;

/**
 *
 * <p>
 * description: T_CREEPERS_FUND_EXTRA_DETAIL 补充公积金账户本年度明细表
 * <p>
 * 
 * @author MaXin
 * @since 2016-09-06 14:47:24
 * @see
 */

public interface CreepersFundExtraDetailDao
        extends JpaRepository<TCreepersFundExtraDetail, Long>, JpaSpecificationExecutor<TCreepersFundExtraDetail> {
	@Query("select t from TCreepersFundExtraDetail t where t.loginName =:loginName")
    List<TCreepersFundExtraDetail> findByLoginName(@Param("loginName") String loginName);

    @Modifying
    @Query("delete  from TCreepersFundExtraDetail t where t.loginName = :loginName")
    void deleteByLoginName(@Param("loginName") String loginName);
}
