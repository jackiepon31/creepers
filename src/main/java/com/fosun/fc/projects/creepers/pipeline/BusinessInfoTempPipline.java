package com.fosun.fc.projects.creepers.pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dao.CreepersListDao;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersList;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

/**
 * 
 * <p>
 * 工商信息注册号名录爬取异常信息列表
 * </p>
 * 
 * @author maxin
 * @since 2016年6月27日
 * @see
 */
@Component("businessInfoTempPipline")
public class BusinessInfoTempPipline extends BasePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CreepersListDao creepersListDao;

    public BusinessInfoTempPipline() {
        setPath("/data/webmagic/");
    }

    public BusinessInfoTempPipline(String path) {
        setPath(path);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void process(ResultItems resultItems, Task task) {
        logger.info("step: ======>> entry BusinessInfoPipline");
        List<Map<String, String>> resultList = resultItems.get("resultList");
        List<TCreepersList> entityList = new ArrayList<TCreepersList>();
        try {
            logger.info("step:  ======>>  entry CommonMethodUtils.mapList ");
            entityList = CommonMethodUtils.mapList(resultList, new TCreepersList());
            logger.info("step:  ======>>  entry CommonMethodUtils.mapList SUCCEED!");
            for (TCreepersList entity : entityList) {
                CommonMethodUtils.setByDT(entity);
            }
            logger.info("step:  ======>>  entry creepersListDao save.");
            creepersListDao.save(entityList);
            logger.info("step:  ======>>  entry creepersListDao save SUCCEED!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("step:  ======>>  entry creepersListDao save FALSE!!!");
            logger.error("write DB error", e.getCause().getClass() + e.getCause().getMessage());
            CreepersParamDTO param =  resultItems.get(BaseConstant.PARAM_DTO_KEY);
            param.setErrorInfo("write DB error" + e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorPath(getClass().toString());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
        }
    }
}
