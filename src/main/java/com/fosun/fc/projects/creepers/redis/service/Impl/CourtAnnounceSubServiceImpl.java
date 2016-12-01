package com.fosun.fc.projects.creepers.redis.service.Impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dao.CreepersListCourtDao;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersListCourt;
import com.fosun.fc.projects.creepers.redis.service.IRedisSubService;
import com.fosun.fc.projects.creepers.service.ICreepersCourtAnnounceService;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;
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
public class CourtAnnounceSubServiceImpl implements IRedisSubService {
    @Autowired
    private CreepersListCourtDao creepersListCourtDao;
    @Autowired
    private ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;
    @Autowired
    private ICreepersCourtAnnounceService creepersCourtAnnounceServiceImpl;

    @Override
    public void handleMessage(Serializable message) {
        String merName = message.toString();
        // 初始化param DTO
        CreepersParamDTO param = new CreepersParamDTO();
        param.putSearchKeyWord(merName);
        param.setTaskType(BaseConstant.TaskListType.COURT_LIST.getValue());
        param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());

        try {
            List<TCreepersListCourt> tradeMarkList = creepersListCourtDao.queryListByMerName(merName);
            if (CommonMethodUtils.isEmpty(tradeMarkList)) {
                // 往任务队列表中插入一条记录，然后开始爬取
                TCreepersListCourt tCreepersListCourt = new TCreepersListCourt();
                tCreepersListCourt.setMerName(merName);
                tCreepersListCourt.setFlag(BaseConstant.TaskListStatus.DEFAULT.getValue());
                CommonMethodUtils.setByDT(tCreepersListCourt);
                creepersListCourtDao.save(tCreepersListCourt);
            }
            creepersCourtAnnounceServiceImpl.processByMerName(merName);
        } catch (Exception e) {
            e.printStackTrace();
            param.setErrorPath(e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorInfo(e.getMessage());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
        }
    }

}
