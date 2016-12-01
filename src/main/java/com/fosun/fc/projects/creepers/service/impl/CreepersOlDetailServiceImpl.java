package com.fosun.fc.projects.creepers.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersOlDetailDao;
import com.fosun.fc.projects.creepers.dto.CreepersOlDetailDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersOlDetail;
import com.fosun.fc.projects.creepers.service.ICreepersOlDetailService;

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
public class CreepersOlDetailServiceImpl implements ICreepersOlDetailService {

    @Autowired
    private CreepersOlDetailDao creepersOlDetailDao;
    @Override
    public void saveTCreepersOlDetail(TCreepersOlDetail entity) {
        creepersOlDetailDao.save(entity);
    }

    @Override
    public List<TCreepersOlDetail> queryAllOlDetail() {
        return creepersOlDetailDao.findAll();
    }

    @Override
    public List<TCreepersOlDetail> findByRptNo(String rptNo) {
        return creepersOlDetailDao.queryByRptNo(rptNo);
    }
    
    @Override
    public Map<String, Object> findByRptNoForMap(String rptNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<TCreepersOlDetail> entityList = creepersOlDetailDao.queryByRptNo(rptNo);
        List<CreepersOlDetailDTO> dtoList = BeanMapper.mapList(entityList, CreepersOlDetailDTO.class);
        if ((!CreepersConstant.TableNamesCreditReference.T_CREEPERS_OL_DETAIL.isList()) && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_OL_DETAIL.getMapKey(), dtoList.get(0));
        } else
            if (CreepersConstant.TableNamesCreditReference.T_CREEPERS_OL_DETAIL.isList() && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_OL_DETAIL.getMapKey(), dtoList);
        } else {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_OL_DETAIL.getMapKey(), null);
        }
        return result;
    }
}
