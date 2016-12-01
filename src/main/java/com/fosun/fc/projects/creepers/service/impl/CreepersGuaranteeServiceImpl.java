package com.fosun.fc.projects.creepers.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersGuaranteeDao;
import com.fosun.fc.projects.creepers.dto.CreepersGuaranteeDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersGuarantee;
import com.fosun.fc.projects.creepers.service.ICreepersGuaranteeService;

/**
 * 
 * <p>
 * description:
 * </p>
 * 
 * @author MaXin
 * @since 2016年5月26日
 * @see
 */
@Component
@Transactional
public class CreepersGuaranteeServiceImpl implements ICreepersGuaranteeService {

    @Autowired
    private CreepersGuaranteeDao creepersGuaranteeDao;
    @Override
    public void saveTCreepersGuarantee(TCreepersGuarantee entity) {
        creepersGuaranteeDao.save(entity);
    }

    @Override
    public List<TCreepersGuarantee> queryAllGuarantee() {
        return creepersGuaranteeDao.findAll();
    }

    @Override
    public List<TCreepersGuarantee> findByRptNo(String rptNo) {
        return creepersGuaranteeDao.queryByRptNo(rptNo);
    }
    
    @Override
    public Map<String, Object> findByRptNoForMap(String rptNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<TCreepersGuarantee> entityList = creepersGuaranteeDao.queryByRptNo(rptNo);
        List<CreepersGuaranteeDTO> dtoList = BeanMapper.mapList(entityList, CreepersGuaranteeDTO.class);
        if ((!CreepersConstant.TableNamesCreditReference.T_CREEPERS_GUARANTEE.isList()) && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_GUARANTEE.getMapKey(), dtoList.get(0));
        } else
            if (CreepersConstant.TableNamesCreditReference.T_CREEPERS_GUARANTEE.isList() && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_GUARANTEE.getMapKey(), dtoList);
        } else {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_GUARANTEE.getMapKey(), null);
        }
        return result;
    }
}
