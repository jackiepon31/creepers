package com.fosun.fc.projects.creepers.pipeline.CreditChina;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
import com.fosun.fc.projects.creepers.entity.TCreepersCourtDisInfo;
import com.fosun.fc.projects.creepers.entity.TCreepersCourtDishonest;
import com.fosun.fc.projects.creepers.pipeline.BasePipeline;
import com.fosun.fc.projects.creepers.service.ICreepersCourtDisInfoService;
import com.fosun.fc.projects.creepers.service.ICreepersCourtDishonestyService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

@Component
public class SupremeCourtDishonestyBlackListPipeline extends BasePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersCourtDishonestyService creepersCourtDishonestyServiceImpl;
    @Autowired
    private ICreepersCourtDisInfoService creepersCourtDisInfoServiceImpl;

    public SupremeCourtDishonestyBlackListPipeline() {
        setPath("/data/webmagic/");
    }

    public SupremeCourtDishonestyBlackListPipeline(String path) {
        setPath(path);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @SuppressWarnings("unchecked")
    @Override
    public void process(ResultItems resultItems, Task task) {
        logger.info("======================>>>  SupremeCourtDishonestyBlackListPipeline.Process start!");
        CreepersParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        // 增加异常处理
        try {
            // 获取爬虫解析的数据
            List<Map<String, Object>> dishonestyMapList = resultItems
                    .get(CreepersConstant.TableNamesCreditChina.T_CREEPERS_COURT_DISHONEST.getMapKey());
            List<Map<String, Object>> disInfoMapList = resultItems
                    .get(CreepersConstant.TableNamesCreditChina.T_CREEPERS_COURT_DIS_INFO.getMapKey());
            if (!CommonMethodUtils.isEmpty(dishonestyMapList)) {
                // map转entity
                List<TCreepersCourtDishonest> entityList = CommonMethodUtils.mapList(dishonestyMapList,
                        new TCreepersCourtDishonest());
                for (TCreepersCourtDishonest entity : entityList) {
                    CommonMethodUtils.setByDT(entity);
                    if (StringUtils.isNotBlank(entity.getName())) {
                        creepersCourtDishonestyServiceImpl.saveOrUpdate(entity);
                    }
                }
            }
            logger.info("step:  ======>>  creepersCourtDishonestyServiceImpl deleteAndSave entity!");
            if (!CommonMethodUtils.isEmpty(disInfoMapList)) {
                // map转entity
                List<TCreepersCourtDisInfo> entityList = CommonMethodUtils.mapList(disInfoMapList,
                        new TCreepersCourtDisInfo());
                Map<String, String> map = new HashMap<String, String>();
                for (TCreepersCourtDisInfo entity : entityList) {
                    CommonMethodUtils.setByDT(entity);
                    String name = entity.getName();
                    map.put(name, name);
                }
                Set<Entry<String, String>> entrySet = map.entrySet();
                for (Entry<String, String> entry : entrySet) {
                    creepersCourtDisInfoServiceImpl.deleteByName(entry.getKey());
                }
                creepersCourtDisInfoServiceImpl.saveEntity(entityList);
            }
            logger.info("step:  ======>>  creepersCourtDisInfoServiceImpl deleteAndSave entity!");
            logger.info("======================>>>  SupremeCourtDishonestyBlackListPipeline.Process end!");
        } catch (Exception e) {
            logger.error("step:  ======>>  SupremeCourtDishonestyBlackListPipeline  save FALSE!!!");
            logger.error("write DB error", e);
            param.setErrorInfo("write DB error" + e);
            param.setErrorPath(getClass().toString());
            param.putOrderUrl(BaseConstant.OrderUrlKey.ALL_READY_URL, resultItems.get("BREACK_DOWN_URL"));
            creepersExceptionHandleServiceImpl.handleJobExceptionAndPrintLog(param);
            logger.error("======================>>>  SupremeCourtDishonestyBlackListPipeline.Process end!");
        }

    }

}
