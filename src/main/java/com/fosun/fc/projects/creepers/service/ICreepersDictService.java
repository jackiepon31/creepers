package com.fosun.fc.projects.creepers.service;

import java.util.List;

import com.fosun.fc.projects.creepers.entity.TCreepersDict;

/**
 * 
 * <p>
 * description: 数据字典表Service
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月26日
 * @see
 */
public interface ICreepersDictService extends BaseService {

    public void saveTCreepersDict(TCreepersDict entity);

    public List<TCreepersDict> queryAllDict(TCreepersDict entity);
}
