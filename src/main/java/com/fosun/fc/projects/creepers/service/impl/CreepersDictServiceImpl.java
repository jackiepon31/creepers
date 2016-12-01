package com.fosun.fc.projects.creepers.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.projects.creepers.entity.TCreepersDict;
import com.fosun.fc.projects.creepers.service.ICreepersDictService;

/**
 * 
 * <p>
 * description:
 * </p>
 * 
 * @author Maxin
 * @since 2016年5月26日
 * @see
 */
@Component
@Transactional
public class CreepersDictServiceImpl implements ICreepersDictService {

    @Override
    public void saveTCreepersDict(TCreepersDict dto) {

    }

    @Override
    public List<TCreepersDict> queryAllDict(TCreepersDict dto) {
        return null;
    }
}
