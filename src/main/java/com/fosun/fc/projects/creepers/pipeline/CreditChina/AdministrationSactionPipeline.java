package com.fosun.fc.projects.creepers.pipeline.CreditChina;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersSaction;
import com.fosun.fc.projects.creepers.pipeline.BasePipeline;
import com.fosun.fc.projects.creepers.service.ICreepersSactionService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

@Component
public class AdministrationSactionPipeline extends BasePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersSactionService creepersSactionServiceImpl;

    public AdministrationSactionPipeline() {
        setPath("/data/webmagic/");
    }

    public AdministrationSactionPipeline(String path) {
        setPath(path);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void process(ResultItems resultItems, Task task) {
        logger.info("======================>>>  AdministrationSactionPipeline.Process start!");
        CreepersParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        // 增加异常处理
        try {
            // 获取爬虫解析的数据
            List<Map<String, Object>> sactionMapList = resultItems
                    .get(CreepersConstant.TableNamesCreditChina.T_CREEPERS_SACTION.getMapKey());
            if (!CommonMethodUtils.isEmpty(sactionMapList)) {
                // map转entity
                List<TCreepersSaction> entityList = CommonMethodUtils.mapList(sactionMapList, new TCreepersSaction());
                for (TCreepersSaction entity : entityList) {
                    CommonMethodUtils.setByDT(entity);
                    if (StringUtils.isNotBlank(entity.getName())) {
                        creepersSactionServiceImpl.saveOrUpdate(entity);
                    }
                }
            }
            logger.info("step:  ======>>  creepersSactionServiceImpl deleteAndSave entity!");
            logger.info("======================>>>  AdministrationSactionPipeline.Process end!");
        } catch (Exception e) {
            logger.error("step:  ======>>  AdministrationSactionPipeline  save FALSE!!!");
            logger.error("write DB error", e);
            param.setErrorInfo("write DB error" + e);
            param.setErrorPath(getClass().toString());
            param.putOrderUrl(BaseConstant.OrderUrlKey.ALL_READY_URL, resultItems.get("BREACK_DOWN_URL"));
            creepersExceptionHandleServiceImpl.handleJobExceptionAndPrintLog(param);
            logger.error("======================>>>  AdministrationSactionPipeline.Process end!");
        }

    }

}
