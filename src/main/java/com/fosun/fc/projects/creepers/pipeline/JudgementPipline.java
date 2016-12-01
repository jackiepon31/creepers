package com.fosun.fc.projects.creepers.pipeline;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersJudgement;
import com.fosun.fc.projects.creepers.service.ICreepersJudgementService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

/**
 * 
 * <p>
 * 法院判决书pipeline data入库
 * </p>
 * 
 * @author maxin
 * @since 2016年6月17日
 * @see
 */
@Component("judgementPipline")
public class JudgementPipline extends BasePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersJudgementService creepersJudgementServiceImpl;

    private static String RESULT_LIST = "resultList";

    public JudgementPipline() {
        setPath("/data/webmagic/");
    }

    public JudgementPipline(String path) {
        setPath(path);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void process(ResultItems resultItems, Task task) {
        logger.info("step:  ======>>  entry JudgementPipline process!");
        // 数据入库
        List<Map<String, String>> resultList = resultItems.get(RESULT_LIST);
        CreepersParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        param.setErrorPath(getClass().toString());
        try {
            if (!CommonMethodUtils.isEmpty(resultList)) {
                List<TCreepersJudgement> entityList = CommonMethodUtils.mapList(resultList, new TCreepersJudgement());
                Map<String, String> map = new HashMap<String, String>();
                for (TCreepersJudgement tCreepersJudgement : entityList) {
                    CommonMethodUtils.setByDT(tCreepersJudgement);
                    String merName = tCreepersJudgement.getMerName();
                    if (!map.containsKey(merName)) {
                        map.put(merName, merName);
                        creepersJudgementServiceImpl.deleteByMerName(merName);
                    }
                }
                logger.info("step:  ======>>  creepersJudgementDao START!");
                creepersJudgementServiceImpl.saveEntity(entityList);
                logger.info("step:  ======>>  creepersJudgementDao SUCCEED!");

                param.setTaskStatus(BaseConstant.TaskListStatus.SUCCEED.getValue());
                logger.info("step:  ======>>  creepersListServiceImpl update status to succeed START!");
                creepersListServiceImpl.updateList(param);
                logger.info("step:  ======>>  creepersListServiceImpl update status to succeed SUCCEED!");
            } else {
                logger.info("step:  ======>>  input resultList is empty! Then update status to false!");
                param.setErrorInfo("input resultList is empty! Then update status to false!");
                logger.info("step:  ======>>  creepersListServiceImpl START!");
                creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
                logger.info("step:  ======>>  creepersListServiceImpl SUCCEED!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("step:  ======>>  JudgementPipline  save FALSE!!!");
            logger.error("write DB error", e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorInfo("write DB error" + e.getCause().getClass() + e.getCause().getMessage());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
        }
        logger.info("step:  ======>>  exit JudgementPipline process!");
    }
}
