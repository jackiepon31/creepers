package com.fosun.fc.projects.creepers.pipeline.CFDA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersMedical;
import com.fosun.fc.projects.creepers.pipeline.BasePipeline;
import com.fosun.fc.projects.creepers.service.ICreepersMedicalService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

/**
 * 
 * <p>
 * 医药类data统一入库
 * </p>
 * 
 * @author MaXin
 * @since 2016-11-22 13:52:12
 * @see
 */
@Component("medicalPipline")
public class MedicalPipline extends BasePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersMedicalService creepersMedicalServiceImpl;

    public MedicalPipline() {
        setPath("/data/webmagic/");
    }

    public MedicalPipline(String path) {
        setPath(path);
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        logger.info("step:  ======>>  entry MedicalPipline process!");
        // 数据入库
        List<Map<String, String>> resultList = resultItems.get(BaseConstant.RESULT_DATA_KEY);
        CreepersParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        param.setErrorPath(getClass().toString());
        try {
            if (!CommonMethodUtils.isEmpty(resultList)) {
                List<TCreepersMedical> entityList = new ArrayList<TCreepersMedical>();
                for (Map<String, String> resultMap : resultList) {
                    TCreepersMedical entity = new TCreepersMedical();
                    CommonMethodUtils.mapTransEntity(resultMap, entity);
                    CommonMethodUtils.setByDT(entity);
                    entityList.add(entity);
                }
                logger.info("step:  ======>>  creepersMedicalServiceImpl.saveEntity START!");
                creepersMedicalServiceImpl.saveEntity(entityList);
                logger.info("step:  ======>>  creepersMedicalServiceImpl.saveEntity SUCCEED!");
            } else {
                logger.info("step:  ======>>  input resultList is empty! Then update status to false!");
                param.setErrorInfo("input resultList is empty! Then update status to false!");
                logger.info("step:  ======>>  creepersExceptionHandleServiceImpl START!");
                creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
                logger.info("step:  ======>>  creepersExceptionHandleServiceImpl SUCCEED!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("step:  ======>>  MedicalPipline  save FALSE!!!");
            logger.error("write DB error", e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorInfo("write DB error" + e.getCause().getClass() + e.getCause().getMessage());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
        }
        logger.info("step:  ======>>  exit MedicalPipline process!");
    }
}
