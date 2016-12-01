package com.fosun.fc.projects.creepers.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersQueryLogDao;
import com.fosun.fc.projects.creepers.dto.CreepersQueryLogDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersQueryLog;
import com.fosun.fc.projects.creepers.service.ICreepersQueryLogService;

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
public class CreepersQueryLogServiceImpl implements ICreepersQueryLogService {

    @Autowired
    private CreepersQueryLogDao creepersQueryLogDao;

    @Override
    public void saveTCreepersQueryLog(TCreepersQueryLog entity) {
        creepersQueryLogDao.save(entity);
    }

    @Override
    public List<TCreepersQueryLog> queryAllQueryLog() {
        return creepersQueryLogDao.findAll();
    }

    @Override
    public List<TCreepersQueryLog> findByRptNo(String rptNo) {
        // TODO Auto-generated method stub
        return creepersQueryLogDao.queryByRptNo(rptNo);
    }

    @Override
    public Map<String, Object> findByRptNoForMap(String rptNo) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<TCreepersQueryLog> entityList = creepersQueryLogDao.queryByRptNo(rptNo);
        List<CreepersQueryLogDTO> dtoList = BeanMapper.mapList(entityList, CreepersQueryLogDTO.class);
        if ((!CreepersConstant.TableNamesCreditReference.T_CREEPERS_QUERY_LOG.isList())
                && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_QUERY_LOG.getMapKey(), dtoList.get(0));
        } else if (CreepersConstant.TableNamesCreditReference.T_CREEPERS_QUERY_LOG.isList()
                && dtoList != null && dtoList.size() > 0) {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_QUERY_LOG.getMapKey(), dtoList);
        } else {
            result.put(CreepersConstant.TableNamesCreditReference.T_CREEPERS_QUERY_LOG.getMapKey(), null);
        }
        return result;
    }
}
