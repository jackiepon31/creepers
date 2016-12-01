package com.fosun.fc.projects.creepers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fosun.fc.projects.creepers.entity.TCreepersProxyList;

/**
 *
 * <p>
 * description: T_CREEPERS_PROXY_LIST 爬虫动态代理IP列表
 * <p>
 * 
 * @author MaXin
 * @since 2016-8-24 10:13:31
 * @see
 */

public interface CreepersProxyListDao
        extends JpaRepository<TCreepersProxyList, Long>, JpaSpecificationExecutor<TCreepersProxyList> {

    @Query("select t from TCreepersProxyList t where t.ip = :ip and t.port = :port")
    List<TCreepersProxyList> queryByIpAndPort(@Param("ip") String ip, @Param("port") String port);
}
