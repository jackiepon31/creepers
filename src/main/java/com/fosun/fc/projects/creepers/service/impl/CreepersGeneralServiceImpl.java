/**
 * <p>
 * Copyright(c) @2016 Fortune Credit Management Co., Ltd.
 * </p>
 */
package com.fosun.fc.projects.creepers.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersGeneralDao;
import com.fosun.fc.projects.creepers.dto.CreepersGeneralDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersGeneral;
import com.fosun.fc.projects.creepers.service.ICreepersGeneralService;

/**
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
public class CreepersGeneralServiceImpl implements ICreepersGeneralService {

    @Autowired
    private CreepersGeneralDao creepersGeneralDao;
    @Override
    public void saveTCreepersGeneral(TCreepersGeneral entity) {
        creepersGeneralDao.save(entity);
    }

    @Override
    public List<TCreepersGeneral> queryAllGeneral() {
        return creepersGeneralDao.findAll();
    }

    @Override
    public List<TCreepersGeneral> findByRptNo(String rptNo) {
        return creepersGeneralDao.queryByRptNo(rptNo);
    }
    
    @Override
    public Map<String, Object> findByRptNoForMap(String rptNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<TCreepersGeneral> entityList = creepersGeneralDao.queryByRptNo(rptNo);
        List<CreepersGeneralDTO> dtoList = BeanMapper.mapList(entityList, CreepersGeneralDTO.class);
        if ((!CreepersConstant.TableNamesCreditReference.T_CREEPERS_GENERAL.isList()) && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_GENERAL.getMapKey(), dtoList.get(0));
        } else
            if (CreepersConstant.TableNamesCreditReference.T_CREEPERS_GENERAL.isList() && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_GENERAL.getMapKey(), dtoList);
        } else {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_GENERAL.getMapKey(), null);
        }
        return result;
    }
}
