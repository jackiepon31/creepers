package com.fosun.fc.projects.creepers.redis.service.Impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dao.CreepersListJudgementDao;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersListJudgement;
import com.fosun.fc.projects.creepers.redis.service.IRedisSubService;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.service.ICreepersJudgementService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

/**
 * <p>
 * description:商标消息的消费端实现
 * </p>
 * 
 * @author pengyk
 * @since 2016年8月9日
 * @see
 */
@Service
public class JudgementSubServiceImpl implements IRedisSubService {
    @Autowired
    private CreepersListJudgementDao creepersListJudgementDao;
    @Autowired
    private ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;
    @Autowired
    private ICreepersJudgementService creepersJudgementServiceImpl;
    
    @Override
    public void handleMessage(Serializable message) {
        String merName = message.toString();
        // 初始化param DTO
        CreepersParamDTO param = new CreepersParamDTO();
        param.putSearchKeyWord(merName);
        param.setTaskType(BaseConstant.TaskListType.JUDGEMENT_LIST.getValue());
        param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());

        try {
            List<TCreepersListJudgement> judgementList = creepersListJudgementDao.queryListByMerName(merName);
            if (CommonMethodUtils.isEmpty(judgementList)) {
                // 往任务队列表中插入一条记录，然后开始爬取
                TCreepersListJudgement tCreepersListJudgement = new TCreepersListJudgement();
                tCreepersListJudgement.setMerName(merName);
                tCreepersListJudgement.setFlag(BaseConstant.TaskListStatus.DEFAULT.getValue());
                CommonMethodUtils.setByDT(tCreepersListJudgement);
                creepersListJudgementDao.save(tCreepersListJudgement);
            }
            creepersJudgementServiceImpl.processByMerName(merName);
        } catch (Exception e) {
            e.printStackTrace();
            param.setErrorPath(e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorInfo(e.getMessage());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
        }
    }

}
