package com.fosun.fc.projects.creepers.pipeline;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersTourGuide;
import com.fosun.fc.projects.creepers.service.ICreepersTouristGuideService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

/**
 * 
 * <p>
 * 导游证信息
 * </p>
 * 
 * @author MaXin
 * @since 2016-11-2 12:16:39
 * @see
 */
@Component("touristGuidePipline")
public class TouristGuidePipline extends BasePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersTouristGuideService creepersTouristGuideServiceImpl;

    public TouristGuidePipline() {
        setPath("/data/webmagic/");
    }

    public TouristGuidePipline(String path) {
        setPath(path);
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        logger.info("step: ======>> entry TouristGuidePipline");
        Map<String, String> resultMap = resultItems
                .get(CreepersConstant.TableNamesOthers.T_CREEPERS_TOUR_GUIDE.getMapKey());
        CreepersLoginParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        param.setErrorPath(getClass().toString());
        // 增加异常处理
        try {
            // 判断resultList是否为空，不为空更新数据并更新list表的状态为成功，为空更新list表的状态为失败并处理异常
            if (!CommonMethodUtils.isEmpty(resultMap)) {
                logger.info("step:  ======>>  CommonMethodUtils.mapTransEntity. START!");
                TCreepersTourGuide entity = new TCreepersTourGuide();
                CommonMethodUtils.mapTransEntity(resultMap, entity);
                logger.info("step:  ======>>  CommonMethodUtils.mapTransEntity. SUCCEED!");

                logger.info("step:  ======>>  CommonMethodUtils.setByDT.  START!");
                CommonMethodUtils.setByDT(entity);
                logger.info("step:  ======>>  CommonMethodUtils.setByDT. SUCCEED!");

                logger.info("step:  ======>>   creepersTouristGuideServiceImpl save.  START!");
                creepersTouristGuideServiceImpl.saveEntity(entity);
                logger.info("step:  ======>>   creepersTouristGuideServiceImpl save SUCCEED!");
            } else {
                logger.info("step:  ======>>  input resultList is empty! Then update status to false!");
                param.setErrorInfo("input resultList is empty! Then update status to false!");
                logger.info("step:  ======>>  creepersExceptionHandleServiceImpl START!");
                creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
                logger.info("step:  ======>>  creepersExceptionHandleServiceImpl SUCCEED!");
            }
            // 查到数据将标记置为成功，未查到数据，也将标记置为成功。若需重爬，需到管理系统进行手动重爬！
            param.setTaskStatus(BaseConstant.TaskListStatus.SUCCEED.getValue());
            logger.info("step:  ======>>  creepersListServiceImpl update status to succeed START!");
            creepersListServiceImpl.updateList(param);
            logger.info("step:  ======>>  creepersListServiceImpl update status to succeed SUCCEED!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("step:  ======>>  TouristGuidePipline save FALSE!!!");
            logger.error("write DB error",e.getMessage());
            param.setErrorInfo("write DB error" + e.getMessage());
            if (StringUtils.isNotBlank(
                    param.getSearchKeyWord(CreepersConstant.TCreepersListTourGuideColumn.GUIDE_NO.getValue()))) {
                param.setLoginName(
                        param.getSearchKeyWord(CreepersConstant.TCreepersListTourGuideColumn.GUIDE_NO.getValue()));
            } else {
                param.setLoginName(
                        param.getSearchKeyWord(CreepersConstant.TCreepersListTourGuideColumn.CARD_NO.getValue()));
            }
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
        }
        logger.info("step: ======>> exit TouristGuidePipline");
    }
}
