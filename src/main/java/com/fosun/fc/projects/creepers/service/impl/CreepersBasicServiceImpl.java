package com.fosun.fc.projects.creepers.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersBasicDao;
import com.fosun.fc.projects.creepers.dto.CreepersBasicDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersBasic;
import com.fosun.fc.projects.creepers.service.ICreepersBasicService;

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
public class CreepersBasicServiceImpl implements ICreepersBasicService {

    @Autowired
    private CreepersBasicDao creepersBasicDao;
    @Override
    public void saveTCreepersBasic(TCreepersBasic entity) {
        creepersBasicDao.save(entity);
    }

    @Override
    public List<TCreepersBasic> queryAllBasic() {
        return creepersBasicDao.findAll();
    }

    @Override
    public List<TCreepersBasic> findByRptNo(String rptNo) {
        return creepersBasicDao.queryByRptNo(rptNo);
    }

    @Override
    public List<TCreepersBasic> findByName(String name) {
        return creepersBasicDao.queryByName(name);
    }

    @Override
    public List<TCreepersBasic> findByIdNo(String idNo) {
        return creepersBasicDao.queryByIdNo(idNo);
    }

    @Override
    public Map<String, Object> findByRptNoForMap(String rptNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<TCreepersBasic> entityList = creepersBasicDao.queryByRptNo(rptNo);
        List<CreepersBasicDTO> dtoList = BeanMapper.mapList(entityList, CreepersBasicDTO.class);
        if ((!CreepersConstant.TableNamesCreditReference.T_CREEPERS_BASIC.isList()) && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_BASIC.getMapKey(), dtoList.get(0));
        } else
            if (CreepersConstant.TableNamesCreditReference.T_CREEPERS_BASIC.isList() && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_BASIC.getMapKey(), dtoList);
        } else {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_BASIC.getMapKey(), null);
        }
        return result;
    }
}
