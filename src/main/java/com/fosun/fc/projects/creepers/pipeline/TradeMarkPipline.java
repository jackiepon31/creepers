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
import com.fosun.fc.projects.creepers.entity.TCreepersTradeMark;
import com.fosun.fc.projects.creepers.service.ICreepersTradeMarkService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

/**
 * 
 * <p>
 * 商标爬取
 * </p>
 * 
 * @author pengyk
 * @since 2016年8月2日
 * @see
 */
@Component("tradeMarkPipline")
public class TradeMarkPipline extends BasePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICreepersTradeMarkService creepersTradeMarkServiceImpl;

    public TradeMarkPipline() {
        setPath("/data/webmagic/");
    }

    public TradeMarkPipline(String path) {
        setPath(path);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void process(ResultItems resultItems, Task task) {
        logger.info("step: ======>> entry TradeMarkPipline");
        List<Map<String, String>> resultList = resultItems.get("resultList");
        CreepersParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        param.setErrorPath(getClass().toString());
        //增加异常处理
        try {
            //判断resultList是否为空，不为空更新数据并更新list表的状态为成功，为空更新list表的状态为失败并处理异常
            if (!CommonMethodUtils.isEmpty(resultList)) {
                logger.info("step:  ======>>  CommonMethodUtils.mapList.  START!");
                List<TCreepersTradeMark> entityList = CommonMethodUtils.mapList(resultList, new TCreepersTradeMark());
                logger.info("step:  ======>>  CommonMethodUtils.mapList. SUCCEED!");

                logger.info("step:  ======>>  CommonMethodUtils.setByDT.  START!");
                Map<String,String> map = new HashMap<String,String>();
                for (TCreepersTradeMark tCreepersTradeMark : entityList) {
                    CommonMethodUtils.setByDT(tCreepersTradeMark);
                    String merName = tCreepersTradeMark.getMerName();
                    if(!map.containsKey(merName)){
                        map.put(merName, merName);
                        creepersTradeMarkServiceImpl.deleteByMerName(merName);
                    }
                }
                logger.info("step:  ======>>  CommonMethodUtils.setByDT. SUCCEED!");

                logger.info("step:  ======>>  creepersTradeMarkDao save.  START!");
                creepersTradeMarkServiceImpl.saveEntity(entityList);
                logger.info("step:  ======>>  creepersTradeMarkDao save SUCCEED!");

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
            logger.info("step:  ======>>  creepersTradeMarkDao save FALSE!!!");
            logger.error("write DB error", e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorInfo("write DB error" + e.getCause().getClass() + e.getCause().getMessage());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
        }
        logger.info("step: ======>> exit TradeMarkPipline");
    }
}
