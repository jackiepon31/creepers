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
import com.fosun.fc.projects.creepers.entity.TCreepersTaxEvasion;
import com.fosun.fc.projects.creepers.entity.TCreepersTaxEvasionDetail;
import com.fosun.fc.projects.creepers.pipeline.BasePipeline;
import com.fosun.fc.projects.creepers.service.ICreepersTaxEvasionDetailService;
import com.fosun.fc.projects.creepers.service.ICreepersTaxEvasionService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

@Component
public class TaxEvasionPipeline extends BasePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersTaxEvasionService creepersTaxEvasionServiceImpl;

    @Autowired
    private ICreepersTaxEvasionDetailService creepersTaxEvasionDetailServiceImpl;

    public TaxEvasionPipeline() {
        setPath("/data/webmagic/");
    }

    public TaxEvasionPipeline(String path) {
        setPath(path);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @SuppressWarnings("unchecked")
    @Override
    public void process(ResultItems resultItems, Task task) {
        logger.info("======================>>>  TaxEvasionPipeline.Process start!");
        CreepersParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        // 增加异常处理
        try {
            // 获取爬虫解析的数据
            List<Map<String, Object>> taxEvasionMapList = resultItems
                    .get(CreepersConstant.TableNamesCreditChina.T_CREEPERS_TAX_EVASION.getMapKey());
            if (!CommonMethodUtils.isEmpty(taxEvasionMapList)) {
                // map转entity
                List<TCreepersTaxEvasion> entityList = CommonMethodUtils.mapList(taxEvasionMapList,
                        new TCreepersTaxEvasion());
                for (TCreepersTaxEvasion entity : entityList) {
                    CommonMethodUtils.setByDT(entity);
                    if (entity.getName() != null) {
                        creepersTaxEvasionServiceImpl.saveOrUpdate(entity);
                    }
                }
            }
            logger.info("step:  ======>>  CreepersTaxEvasionServiceImpl deleteAndSave entity!");
            // 获取爬虫解析的数据
            List<Map<String, Object>> taxEvasionDetailMapList = resultItems
                    .get(CreepersConstant.TableNamesCreditChina.T_CREEPERS_TAX_EVASION_DETAIL.getMapKey());
            if (!CommonMethodUtils.isEmpty(taxEvasionDetailMapList)) {
                // map转entity
                List<TCreepersTaxEvasionDetail> entityList = CommonMethodUtils.mapList(taxEvasionDetailMapList,
                        new TCreepersTaxEvasionDetail());
                Map<String, String> map = new HashMap<String, String>();
                for (TCreepersTaxEvasionDetail entity : entityList) {
                    CommonMethodUtils.setByDT(entity);
                    String name = entity.getName();
                    map.put(name, name);
                }
                Set<Entry<String, String>> entrySet = map.entrySet();
                for (Entry<String, String> entry : entrySet) {
                    creepersTaxEvasionDetailServiceImpl.deleteByName(entry.getKey());
                }
                creepersTaxEvasionDetailServiceImpl.saveEntity(entityList);
            }
            logger.info("step:  ======>>  CreepersTaxEvasionDetailServiceImpl deleteAndSave entity!");
            logger.info("======================>>>  TaxEvasionPipeline.Process end!");
        } catch (Exception e) {
            logger.error("step:  ======>>  TaxEvasionPipeline  save FALSE!!!");
            logger.error("write DB error", e);
            param.setErrorInfo("write DB error" + e);
            param.setErrorPath(getClass().toString());
            param.putOrderUrl(BaseConstant.OrderUrlKey.ALL_READY_URL, resultItems.get("BREACK_DOWN_URL"));
            creepersExceptionHandleServiceImpl.handleJobExceptionAndPrintLog(param);
            logger.error("======================>>>  TaxEvasionPipeline.Process end!");
        }

    }

}
