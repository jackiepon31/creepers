package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersFundExtra;

/**
 *
 * <p>
 * description: T_CREEPERS_FUND_EXTRA 补充公积金账户信息表
 * <p>
 * 
 * @author MaXin
 * @since 2016-09-06 14:47:24
 * @see
 */

public interface CreepersFundExtraDao
        extends JpaRepository<TCreepersFundExtra, Long>, JpaSpecificationExecutor<TCreepersFundExtra> {
	@Query("select t from TCreepersFundExtra t where t.loginName =:loginName")
    List<TCreepersFundExtra> findByLoginName(@Param("loginName") String loginName);

    @Modifying
    @Query("delete  from TCreepersFundExtra t where t.loginName = :loginName")
    void deleteByLoginName(@Param("loginName") String loginName);
}
