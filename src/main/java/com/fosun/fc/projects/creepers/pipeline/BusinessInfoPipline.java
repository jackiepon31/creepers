package com.fosun.fc.projects.creepers.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dao.CreepersErrorListDao;
import com.fosun.fc.projects.creepers.dao.CreepersListDao;
import com.fosun.fc.projects.creepers.entity.TCreepersErrorList;
import com.fosun.fc.projects.creepers.entity.TCreepersList;
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
 * @since 2016年6月17日
 * @see
 */
@Component("businessInfoPipline")
public class BusinessInfoPipline extends BasePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CreepersErrorListDao creepersErrorListDao;
    
    @Autowired
    private CreepersListDao creepersListDao;

    public BusinessInfoPipline() {
        setPath("/data/webmagic/");
    }

    public BusinessInfoPipline(String path) {
        setPath(path);
    }

    
    @Override
    public void process(ResultItems resultItems, Task task) {
        logger.info("step: ======>> entry BusinessInfoPipline");
        String status = resultItems.get("status");
        String merNo = resultItems.get("merNo");
        String merName = resultItems.get("merName");
        String memo = resultItems.get("memo");
//        String detailURL = resultItems.get("detailURL");
        if (BaseConstant.BusinessInfoStatus.ERROR.getValue().equals(status)) {
            logger.info("step:  ======>>  entry BusinessInfoStatus ERROR");
            TCreepersErrorList entityErrorList = new TCreepersErrorList();
//            entityErrorList.setMerNo(merNo);
            entityErrorList.setMerName(merName);
            entityErrorList.setMemo(memo);
            entityErrorList.setFlag(BaseConstant.ErrorListFlag.DEFAULT.getValue());
            entityErrorList.setTaskType(BaseConstant.TaskListType.BUSI_INFO_LIST.getValue());
            CommonMethodUtils.setByDT(entityErrorList);
            try {
                logger.info("step:  ======>>  entry creepersErrorListDao save.");
                creepersErrorListDao.save(entityErrorList);
                logger.info("step:  ======>>  creepersErrorListDao save SUCCEED!");
            } catch (Exception e) {
                logger.info("step:  ======>>  creepersErrorListDao save FALSE!!!");
                logger.error("write DB error", e);
            }
        } else if (BaseConstant.BusinessInfoStatus.SUCCEED.getValue().equals(status)) {
            logger.info("step:  ======>>  entry BusinessInfoStatus SUCCEED");
            TCreepersList entityList = new TCreepersList();
            entityList.setMerNo(merNo);
            entityList.setMerName(merName);
            entityList.setMemo(memo);
            entityList.setFlag(BaseConstant.ErrorListFlag.DEFAULT.getValue());
            CommonMethodUtils.setByDT(entityList);
            try {
                logger.info("step:  ======>>  entry creepersListDao save.");
                creepersListDao.save(entityList);
                logger.info("step:  ======>>  entry creepersListDao save SUCCEED!");
            } catch (Exception e) {
                logger.info("step:  ======>>  entry creepersListDao save FALSE!!!");
                logger.error("write DB error", e);
            }
        } else if(BaseConstant.BusinessInfoStatus.EXIST.getValue().equals(status)){
            logger.info("step:  ======>>  entry BusinessInfoStatus EXIST  then do nothing!");
        }
    }
}
