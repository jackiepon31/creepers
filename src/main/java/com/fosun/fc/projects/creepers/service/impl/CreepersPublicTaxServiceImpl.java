package com.fosun.fc.projects.creepers.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersPublicTaxDao;
import com.fosun.fc.projects.creepers.dto.CreepersPublicTaxDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicTax;
import com.fosun.fc.projects.creepers.service.ICreepersPublicTaxService;

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
public class CreepersPublicTaxServiceImpl implements ICreepersPublicTaxService {

    @Autowired
    private CreepersPublicTaxDao creepersPublicTaxDao;

    @Override
    public void saveTCreepersPublicTax(TCreepersPublicTax entity) {
        creepersPublicTaxDao.save(entity);
    }

    @Override
    public List<TCreepersPublicTax> queryAllPublicTax() {
        return creepersPublicTaxDao.findAll();
    }

    @Override
    public List<TCreepersPublicTax> findByRptNo(String rptNo) {
        return creepersPublicTaxDao.queryByRptNo(rptNo);
    }

    @Override
    public Map<String, Object> findByRptNoForMap(String rptNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<TCreepersPublicTax> entityList = creepersPublicTaxDao.queryByRptNo(rptNo);
        List<CreepersPublicTaxDTO> dtoList = BeanMapper.mapList(entityList, CreepersPublicTaxDTO.class);
        if ((!CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_TAX.isList())
                && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_TAX.getMapKey(), dtoList.get(0));
        } else if (CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_TAX.isList()
                && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_TAX.getMapKey(), dtoList);
        } else {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_PUBLIC_TAX.getMapKey(), null);
        }
        return result;
    }
}
