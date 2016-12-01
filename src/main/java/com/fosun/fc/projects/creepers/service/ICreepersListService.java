package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fosun.fc.modules.utils.JsonResult;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;

public interface ICreepersListService extends BaseService {
    
    /**
     * @param requestType
     *            任务队列类型
     * @param merName
     *            企业名称
     */
    @SuppressWarnings("rawtypes")
    public JsonResult processByMerName(String requestType, String merName);
    @SuppressWarnings("rawtypes")
    public JsonResult processByParam(CreepersLoginParamDTO param);
    
    /**
     * @param requestType
     *            任务队列类型
     * @param merName
     *            企业名称
     */
    @SuppressWarnings("rawtypes")
    public List queryListByKey(String requestType, String key);
    @SuppressWarnings("rawtypes")
    public List queryListByKey(CreepersLoginParamDTO param);
    
    public Page<?> findList(Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType,
            String taskType) throws Exception;
    
    @SuppressWarnings("rawtypes")
    public JsonResult addTaskByMerName(String requestType, String merName);
    @SuppressWarnings("rawtypes")
    public JsonResult addTaskByParam(CreepersLoginParamDTO param);

    public void doRecycleByMerName(String requestType, String merName);
    public void doRecycleByParam(CreepersLoginParamDTO param);

    public void insertList(CreepersParamDTO param);
    public void insertList(CreepersLoginParamDTO param);

    public void deleteList(CreepersParamDTO param);
    public void deleteList(CreepersLoginParamDTO param);
    
    public void updateList(CreepersParamDTO param);
    public void updateList(CreepersLoginParamDTO param);

    @SuppressWarnings("rawtypes")
    public List selectList(CreepersParamDTO param);
    @SuppressWarnings("rawtypes")
    public List selectList(CreepersLoginParamDTO param);
}
