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
import com.fosun.fc.projects.creepers.entity.TCreepersCourtCorpBonds;
import com.fosun.fc.projects.creepers.pipeline.BasePipeline;
import com.fosun.fc.projects.creepers.service.ICreepersCourtCorpBondsService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

@Component
public class CorporateBondsBlacklistPipeline extends BasePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersCourtCorpBondsService creepersCourtCorpBondsServiceImpl;

    public CorporateBondsBlacklistPipeline() {
        setPath("/data/webmagic/");
    }

    public CorporateBondsBlacklistPipeline(String path) {
        setPath(path);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @SuppressWarnings("unchecked")
    @Override
    public void process(ResultItems resultItems, Task task) {
        logger.info("======================>>>  CorporateBondsBlacklistPipeline.Process start!");
        CreepersParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        // 增加异常处理
        try {
            // 获取爬虫解析的数据
            List<Map<String, Object>> corpBondsMapList = resultItems
                    .get(CreepersConstant.TableNamesCreditChina.T_CREEPERS_COURT_CORP_BONDS.getMapKey());
            if (!CommonMethodUtils.isEmpty(corpBondsMapList)) {
                // map转entity
                List<TCreepersCourtCorpBonds> entityList = CommonMethodUtils.mapList(corpBondsMapList,
                        new TCreepersCourtCorpBonds());
                for (TCreepersCourtCorpBonds entity : entityList) {
                    CommonMethodUtils.setByDT(entity);
                    if (StringUtils.isNotBlank(entity.getName())) {
                        creepersCourtCorpBondsServiceImpl.saveOrUpdate(entity);
                    }
                }
            }
            logger.info("step:  ======>>  creepersCorpBondsServiceImpl deleteAndSave entity!");
            logger.info("======================>>>  CorporateBondsBlacklistPipeline.Process end!");
        } catch (Exception e) {
            logger.error("step:  ======>>  CorporateBondsBlacklistPipeline  save FALSE!!!");
            logger.error("write DB error", e);
            param.setErrorInfo("write DB error" + e);
            param.setErrorPath(getClass().toString());
            creepersExceptionHandleServiceImpl.handleJobExceptionAndPrintLog(param);
            logger.error("======================>>>  CorporateBondsBlacklistPipeline.Process end!");
        }

    }

}
