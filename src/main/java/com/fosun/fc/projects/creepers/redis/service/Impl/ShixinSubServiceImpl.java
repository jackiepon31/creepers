package com.fosun.fc.projects.creepers.redis.service.Impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dao.CreepersListShixinDao;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersListShixin;
import com.fosun.fc.projects.creepers.redis.service.IRedisSubService;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.service.ICreepersShixinService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

public class ShixinSubServiceImpl implements IRedisSubService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private CreepersListShixinDao creepersListShixinDao;

    @Autowired
    private ICreepersShixinService creepersShixinServiceImpl;

    @Autowired
    private ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;

    @Override
    public void handleMessage(Serializable message) {
        
        logger.info("=============>RedisShixinSubServiceImpl.handleMessage start!");
        //接收消息
        String merName = message.toString();
        //增加异常处理
        try {
            //查询任务List，结果为空或状态不为1则启动爬寻
            List<TCreepersListShixin> taskList = creepersListShixinDao.queryListByMerName(merName);
            if (CommonMethodUtils.isEmpty(taskList)) {
                TCreepersListShixin task = new TCreepersListShixin();
                task.setMerName(merName);
                task.setFlag(BaseConstant.TaskListStatus.DEFAULT.getValue());
                CommonMethodUtils.setByDT(task);
                creepersListShixinDao.save(task);
            }
            creepersShixinServiceImpl.processByMerName(merName);
            logger.info("=============>RedisShixinSubServiceImpl.handleMessage end!");
        } catch (Exception e) {
            e.printStackTrace();
            //异常处理
            CreepersParamDTO paramDTO = new CreepersParamDTO();
            paramDTO.putSearchKeyWord(merName);
            paramDTO.setTaskType(BaseConstant.TaskListType.SHI_XIN_LIST.getValue());
            paramDTO.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
            paramDTO.setErrorPath(e.getCause().getClass() + e.getCause().getMessage());
            paramDTO.setErrorInfo(e.getMessage());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(paramDTO);
            logger.error("=============>RedisShixinSubServiceImpl.handleMessage end!");
        }

    }

}
