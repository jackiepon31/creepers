package com.fosun.fc.projects.creepers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fosun.fc.projects.creepers.entity.TCreepersDict;
/**
 * 
 *<p>
 *description:
 *数据字典表
 *</p>
 * @author MaXin
 * @since 2016年5月25日
 * @see
 */
public interface CreepersDictDao
        extends JpaRepository<TCreepersDict, Long>, JpaSpecificationExecutor<TCreepersDict> {

}
