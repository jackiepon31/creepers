package com.fosun.fc.projects.creepers.pipeline;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersTourBlackList;
import com.fosun.fc.projects.creepers.service.ICreepersTouristBlackListService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

/**
 * 
 * <p>
 * 
 * </p>
 * 
 * @author MaXin
 * @since 2016-11-2 12:16:39
 * @see
 */
@Component("touristBlackListPipline")
public class TouristBlackListPipline extends BasePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersTouristBlackListService creepersTouristBlackListServiceImpl;

    public TouristBlackListPipline() {
        setPath("/data/webmagic/");
    }

    public TouristBlackListPipline(String path) {
        setPath(path);
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        logger.info("step: ======>> entry TouristBlackListPipline");
        Map<String, String> resultList = resultItems
                .get(CreepersConstant.TableNamesOthers.T_CREEPERS_TOUR_BLACK_LIST.getMapKey());
        CreepersParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        param.setErrorPath(getClass().toString());
        // 增加异常处理
        try {
            // 判断resultList是否为空，不为空更新数据并更新list表的状态为成功，为空更新list表的状态为失败并处理异常
            if (!CommonMethodUtils.isEmpty(resultList)) {
                logger.info("step:  ======>>  CommonMethodUtils.mapTransEntity. START!");
                TCreepersTourBlackList entity = new TCreepersTourBlackList();
                CommonMethodUtils.mapTransEntity(resultList, entity);
                logger.info("step:  ======>>  CommonMethodUtils.mapTransEntity. SUCCEED!");

                logger.info("step:  ======>>  CommonMethodUtils.setByDT.  START!");
                CommonMethodUtils.setByDT(entity);
                logger.info("step:  ======>>  CommonMethodUtils.setByDT. SUCCEED!");
                logger.info("step:  ======>>   creepersTouristGuideServiceImpl save.  START!");
                creepersTouristBlackListServiceImpl.saveEntity(entity);
                logger.info("step:  ======>>   creepersTouristGuideServiceImpl save SUCCEED!");
            } else {
                logger.info("step:  ======>>  input resultList is empty! Then update status to false!");
                param.setErrorInfo("input resultList is empty! Then update status to false!");
                logger.info("step:  ======>>  creepersExceptionHandleServiceImpl START!");
                creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
                logger.info("step:  ======>>  creepersExceptionHandleServiceImpl SUCCEED!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("step:  ======>>  touristBlackListPipline save FALSE!!!");
            logger.error("write DB error", e.getMessage());
            param.setErrorInfo("write DB error" + e.getMessage());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
        }
        logger.info("step: ======>> exit touristBlackListPipline");
    }
}
