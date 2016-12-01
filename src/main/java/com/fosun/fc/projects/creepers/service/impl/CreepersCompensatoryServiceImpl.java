package com.fosun.fc.projects.creepers.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersCompensatoryDao;
import com.fosun.fc.projects.creepers.dto.CreepersCompensatoryDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersCompensatory;
import com.fosun.fc.projects.creepers.service.ICreepersCompensatoryService;

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
public class CreepersCompensatoryServiceImpl implements ICreepersCompensatoryService {

    @Autowired
    private CreepersCompensatoryDao creepersCompensatoryDao;

    @Override
    public void saveTCreepersCompensatory(TCreepersCompensatory entity) {
        creepersCompensatoryDao.save(entity);
    }

    @Override
    public List<TCreepersCompensatory> queryAllCompensatory() {
        return creepersCompensatoryDao.findAll();
    }

    @Override
    public List<TCreepersCompensatory> findByRptNo(String rptNo) {
        return creepersCompensatoryDao.queryByRptNo(rptNo);
    }

    @Override
    public Map<String, Object> findByRptNoForMap(String rptNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<TCreepersCompensatory> entityList = creepersCompensatoryDao.queryByRptNo(rptNo);
        List<CreepersCompensatoryDTO> dtoList = BeanMapper.mapList(entityList, CreepersCompensatoryDTO.class);
        if ((!CreepersConstant.TableNamesCreditReference.T_CREEPERS_COMPENSATORY.isList()) && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_COMPENSATORY.getMapKey(), dtoList.get(0));
        } else
            if (CreepersConstant.TableNamesCreditReference.T_CREEPERS_COMPENSATORY.isList() && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_COMPENSATORY.getMapKey(), dtoList);
        } else {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_COMPENSATORY.getMapKey(), null);
        }
        return result;
    }
}
