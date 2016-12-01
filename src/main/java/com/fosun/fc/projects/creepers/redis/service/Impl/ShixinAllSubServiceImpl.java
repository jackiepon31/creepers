package com.fosun.fc.projects.creepers.redis.service.Impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.redis.service.IRedisSubService;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.service.ICreepersShixinAllService;

public class ShixinAllSubServiceImpl implements IRedisSubService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersShixinAllService creepersShixinAllServiceImpl;

    @Autowired
    private ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;

    @Override
    public void handleMessage(Serializable message) {
        
        logger.info("=============>ShixinAllSubServiceImpl.handleMessage start!");
        //接收消息
        String jobName = message.toString();
        //增加异常处理
        try {
            creepersShixinAllServiceImpl.processByJob(jobName);
            logger.info("=============>ShixinAllSubServiceImpl.handleMessage end!");
        } catch (Exception e) {
            e.printStackTrace();
            //异常处理
            CreepersParamDTO paramDTO = new CreepersParamDTO();
            paramDTO.putSearchKeyWord(jobName);
            paramDTO.setTaskType(BaseConstant.TaskListType.SHI_XIN_ALL_LIST.getValue());
            paramDTO.setErrorPath(e.getCause().getClass() + e.getCause().getMessage());
            paramDTO.setErrorInfo(e.getMessage());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(paramDTO);
            logger.error("=============>ShixinAllSubServiceImpl.handleMessage end!");
        }

    }

}
