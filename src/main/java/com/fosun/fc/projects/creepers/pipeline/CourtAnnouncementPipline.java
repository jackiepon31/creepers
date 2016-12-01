package com.fosun.fc.projects.creepers.pipeline;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersCourtAnnounce;
import com.fosun.fc.projects.creepers.service.ICreepersCourtAnnounceService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

/**
 * 
 * <p>
 * 工商信息注册号名录爬取
 * </p>
 * 
 * @author maxin
 * @since 2016年6月24日
 * @see
 */
@Component("courtAnnouncementPipline")
public class CourtAnnouncementPipline extends BasePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersCourtAnnounceService creepersCourtAnnounceServiceImpl;

    public CourtAnnouncementPipline() {
        setPath("/data/webmagic/");
    }

    public CourtAnnouncementPipline(String path) {
        setPath(path);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void process(ResultItems resultItems, Task task) {
        logger.info("step: ======>> entry CourtAnnouncementPipline");
        List<Map<String, String>> resultList = resultItems.get("resultList");
        CreepersParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        param.setErrorPath(getClass().toString());
        try {

            if (!CommonMethodUtils.isEmpty(resultList)) {
                logger.info("step:  ======>>  CommonMethodUtils.mapList. START");
                List<TCreepersCourtAnnounce> entityList = CommonMethodUtils.mapList(resultList,
                        new TCreepersCourtAnnounce());
                logger.info("step:  ======>>  CommonMethodUtils.mapList. SUCCEED!");

                logger.info("step:  ======>>  CommonMethodUtils.setByDT. START");
                Map<String, String> map = new HashMap<String, String>();
                for (TCreepersCourtAnnounce tCreepersCourtAnnounce : entityList) {
                    CommonMethodUtils.setByDT(tCreepersCourtAnnounce);
                    String merName = tCreepersCourtAnnounce.getMerName();
                    if (!map.containsKey(merName)) {
                        map.put(merName, merName);
                        creepersCourtAnnounceServiceImpl.deleteByMerName(merName);
                    }
                }
                logger.info("step:  ======>>  CommonMethodUtils.setByDT. SUCCEED!");

                logger.info("step:  ======>>  creepersCourtAnnounceDao save. START");
                creepersCourtAnnounceServiceImpl.saveEntity(entityList);
                logger.info("step:  ======>>  creepersCourtAnnounceDao save SUCCEED!");

                param.setTaskStatus(BaseConstant.TaskListStatus.SUCCEED.getValue());
                logger.info("step:  ======>>  creepersListServiceImpl update status to succeed START!");
                creepersListServiceImpl.updateList(param);
                logger.info("step:  ======>>  creepersListServiceImpl update status to succeed SUCCEED!");
            } else {
                logger.info("step:  ======>>  input resultList is empty! Then update status to false!");
                param.setErrorInfo("input resultList is empty! Then update status to false!");
                logger.info("step:  ======>>  creepersListServiceImpl START!");
                creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
                logger.info("step:  ======>>  creepersListServiceImpl SUCCEED!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("step:  ======>>  CourtAnnouncementPipline save FALSE!!!");
            logger.error("write DB error", e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorInfo("write DB error" + e.getCause().getClass() + e.getCause().getMessage());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
        }
        logger.info("step: ======>> exit CourtAnnouncementPipline");
    }
}
