package com.fosun.fc.projects.creepers.pipeline.CreditChina;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersShixinAll;
import com.fosun.fc.projects.creepers.pipeline.BasePipeline;
import com.fosun.fc.projects.creepers.service.ICreepersShixinAllService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

@Component
public class DishonestyAllPipeline extends BasePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersShixinAllService creepersShixinAllServiceImpl;

    public DishonestyAllPipeline() {
        setPath("/data/webmagic/");
    }

    public DishonestyAllPipeline(String path) {
        setPath(path);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @SuppressWarnings("unchecked")
    @Override
    public void process(ResultItems resultItems, Task task) {
        logger.info("======================>>>  DishonestyAllPipeline.Process start!");
        CreepersParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        // 增加异常处理
        try {
            // 获取爬虫解析的数据
            List<Map<String, Object>> shixinAllMapList = resultItems
                    .get(CreepersConstant.TableNamesCreditChina.T_CREEPERS_SHIXIN_ALL.getMapKey());
            if (!CommonMethodUtils.isEmpty(shixinAllMapList)) {
                // map转entity
                List<TCreepersShixinAll> entityList = CommonMethodUtils.mapList(shixinAllMapList,
                        new TCreepersShixinAll());
                for (TCreepersShixinAll entity : entityList) {
                    CommonMethodUtils.setByDT(entity);
                    if (entity.getName() != null) {
                        creepersShixinAllServiceImpl.saveOrUpdate(entity);
                    }
                }
            }
            logger.info("step:  ======>>  CreepersShixinAllServiceImpl deleteAndSave entity!");
            logger.info("======================>>>  DishonestyAllPipeline.Process end!");
        } catch (Exception e) {
            logger.error("step:  ======>>  DishonestyAllPipeline  save FALSE!!!");
            logger.error("write DB error", e);
            param.setErrorInfo("write DB error" + e);
            param.setErrorPath(getClass().toString());
            param.putOrderUrl(BaseConstant.OrderUrlKey.ALL_READY_URL, resultItems.get("BREACK_DOWN_URL"));
            creepersExceptionHandleServiceImpl.handleJobExceptionAndPrintLog(param);
            logger.error("======================>>>  DishonestyAllPipeline.Process end!");
        }

    }

}
