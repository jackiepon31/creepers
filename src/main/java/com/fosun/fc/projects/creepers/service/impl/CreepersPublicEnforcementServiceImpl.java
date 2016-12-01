package com.fosun.fc.projects.creepers.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersPublicEnforcementDao;
import com.fosun.fc.projects.creepers.dto.CreepersPublicEnforcementDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicEnforcement;
import com.fosun.fc.projects.creepers.service.ICreepersPublicEnforcementService;

/**
 * <p>
 * description:
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月26日
 * @see
 */
@Service
public class CreepersPublicEnforcementServiceImpl implements ICreepersPublicEnforcementService {

    @Autowired
    private CreepersPublicEnforcementDao creepersPublicEnforcementDao;

    @Override
    public void saveTCreepersPublicEnforcement(TCreepersPublicEnforcement entity) {
        creepersPublicEnforcementDao.save(entity);
    }

    @Override
    public List<TCreepersPublicEnforcement> queryAllPublicEnforcement() {
        return creepersPublicEnforcementDao.findAll();
    }

    @Override
    public List<TCreepersPublicEnforcement> findByRptNo(String rptNo) {
        return creepersPublicEnforcementDao.queryByRptNo(rptNo);
    }

    @Override
    public Map<String, Object> findByRptNoForMap(String rptNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<TCreepersPublicEnforcement> entityList = creepersPublicEnforcementDao.queryByRptNo(rptNo);
        List<CreepersPublicEnforcementDTO> dtoList = BeanMapper.mapList(entityList, CreepersPublicEnforcementDTO.class);
        if ((!CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_ENFORCEMENT.isList())
                && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_ENFORCEMENT.getMapKey(),
                    dtoList.get(0));
        } else if (CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_ENFORCEMENT.isList()
                && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_ENFORCEMENT.getMapKey(), dtoList);
        } else {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_ENFORCEMENT.getMapKey(), null);
        }
        return result;
    }
}
