package com.fosun.fc.projects.creepers.redis.service.Impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.redis.service.IRedisSubService;
import com.fosun.fc.projects.creepers.service.ICreepersCourtDishonestyService;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;

public class CourtDishonestySubServiceImpl implements IRedisSubService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersCourtDishonestyService creepersCourtDishonestyServiceImpl;

    @Autowired
    private ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;

    @Override
    public void handleMessage(Serializable message) {
        
        logger.info("=============>CourtDishonestySubServiceImpl.handleMessage start!");
        //接收消息
        String jobName = message.toString();
        //增加异常处理
        try {
            creepersCourtDishonestyServiceImpl.processByJob(jobName);
            logger.info("=============>CourtDishonestySubServiceImpl.handleMessage end!");
        } catch (Exception e) {
            e.printStackTrace();
            //异常处理
            CreepersParamDTO paramDTO = new CreepersParamDTO();
            paramDTO.putSearchKeyWord(jobName);
            paramDTO.setTaskType(BaseConstant.TaskListType.COURT_DISHONESTY_LIST.getValue());
            paramDTO.setErrorPath(e.getCause().getClass() + e.getCause().getMessage());
            paramDTO.setErrorInfo(e.getMessage());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(paramDTO);
            logger.error("=============>CourtDishonestySubServiceImpl.handleMessage end!");
        }

    }

}
