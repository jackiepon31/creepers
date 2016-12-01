package com.fosun.fc.projects.creepers.redis.service.Impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.redis.service.IRedisSubService;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.service.ICreepersTouristBlackListService;

public class TouristBlackListSubServiceImpl implements IRedisSubService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersTouristBlackListService creepersTouristBlackListServiceImpl;

    @Autowired
    private ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;

    @Override
    public void handleMessage(Serializable message) {

        logger.info("=============>TouristBlackListSubServiceImpl.handleMessage start!");
        // 接收消息
        String jobName = message.toString();
        // 增加异常处理
        try {
            creepersTouristBlackListServiceImpl.processByJob(jobName);
            logger.info("=============>TouristBlackListSubServiceImpl.handmleMessage end!");
        } catch (Exception e) {
            e.printStackTrace();
            // 异常处理
            CreepersParamDTO param = new CreepersParamDTO();
            param.putSearchKeyWord("jobName:"+jobName);
            param.setTaskType(BaseConstant.TaskListType.TOUR_BLACK_LIST.getValue());
            param.setErrorPath(e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorInfo(e.getMessage());
            creepersExceptionHandleServiceImpl.handleJobExceptionAndPrintLog(param);
            logger.error("=============>TouristBlackListSubServiceImpl.handleMessage end!");
        }

    }

}
