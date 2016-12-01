package com.fosun.fc.projects.creepers.pipeline.CreditChina;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersShixin;
import com.fosun.fc.projects.creepers.pipeline.BasePipeline;
import com.fosun.fc.projects.creepers.service.ICreepersShixinService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

@Component
public class CreditChinaGovPipeline extends BasePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersShixinService creepersShixinServiceImpl;

    public CreditChinaGovPipeline() {
        setPath("/data/webmagic/");
    }

    public CreditChinaGovPipeline(String path) {
        setPath(path);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @SuppressWarnings("unchecked")
    @Override
    public void process(ResultItems resultItems, Task task) {
        logger.info("======================>>>  CreditChinaGovPipeline.Process start!");
        CreepersParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        // 增加异常处理
        try {
            // 获取爬虫解析的数据
            List<Map<String, Object>> mapList = resultItems
                    .get(CreepersConstant.TableNamesCreditChina.T_CREEPERS_SHIXIN.getMapKey());
            // 判断resultList是否为空，不为空更新数据并更新list表的状态为成功，为空更新list表的状态为失败并处理异常
            if (!CommonMethodUtils.isEmpty(mapList)) {
                // map转entity
                List<TCreepersShixin> entityList = CommonMethodUtils.mapList(mapList, new TCreepersShixin());
                Map<String, String> map = new HashMap<String, String>();
                for (TCreepersShixin entity : entityList) {
                    CommonMethodUtils.setByDT(entity);
                    String merName = entity.getName();
                    map.put(merName, merName);
                }
                Set<Entry<String, String>> entrySet = map.entrySet();
                for (Entry<String, String> entry : entrySet) {
                    creepersShixinServiceImpl.deleteByName(entry.getKey());
                }
                creepersShixinServiceImpl.saveEntity(entityList);
                logger.info("step:  ======>>  creepersShixinServiceImpl deleteAndSave entity!");

                param.setTaskStatus(BaseConstant.TaskListStatus.SUCCEED.getValue());
                creepersListServiceImpl.updateList(param);
                logger.info("step:  ======>>  creepersListServiceImpl update status to succeed !");
            } else {
                logger.info("step:  ======>>  input resultList is empty! Then update status to false!");
                param.setErrorInfo("input resultList is empty! Then update status to false!");
                logger.info("step:  ======>>  creepersListServiceImpl start!");
                creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
                logger.info("step:  ======>>  creepersListServiceImpl end!");
            }
            logger.info("======================>>>  CreditChinaGovPipeline.Process end!");
        } catch (Exception e) {
            logger.error("step:  ======>>  CreditChinaGovPipeline  save FALSE!!!");
            logger.error("write DB error", e);
            param.setErrorInfo("write DB error" + e);
            param.setErrorPath(getClass().toString());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
            logger.error("======================>>>  CreditChinaGovPipeline.Process end!");
        }

    }

}
