package com.fosun.fc.projects.creepers.pipeline;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersProxyListDao;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersProxyList;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

/**
 * 
 * <p>
 * 动态代理IP入库
 * </p>
 * 
 * @author maxin
 * @since 2016-8-24 10:19:03
 * @see
 */
@Component("proxyMimvpPipline")
public class ProxyMimvpPipline extends BasePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CreepersProxyListDao creepersProxyListDao;

    public ProxyMimvpPipline() {
        setPath("/data/webmagic/");
    }

    public ProxyMimvpPipline(String path) {
        setPath(path);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void process(ResultItems resultItems, Task task) {
        logger.info("step:  ======>>  entry ProxyMivmpPipline process!");
        // 数据入库
        List<Map<String, String>> resultList = resultItems
                .get(CreepersConstant.TableNamesOthers.T_CREEPERS_PROXY_LIST.getMapKey());
        CreepersParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        param.setErrorPath(getClass().toString());
        try {
            if (!CommonMethodUtils.isEmpty(resultList)) {
                List<TCreepersProxyList> entityList = CommonMethodUtils.mapList(resultList, new TCreepersProxyList());
                for (TCreepersProxyList entity : entityList) {
                    CommonMethodUtils.setByDT(entity);
                    List<TCreepersProxyList> proxyList = creepersProxyListDao.queryByIpAndPort(entity.getIp(),
                            entity.getPort());
                    if (!CommonMethodUtils.isEmpty(proxyList)) {
                        entity.setId(proxyList.get(0).getId());
                        entity.setVersion(proxyList.get(0).getVersion());
                    }
                    try {
                        logger.info("step:  ======>>  creepersProxyListDao START!");
                        creepersProxyListDao.saveAndFlush(entity);
                        logger.info("step:  ======>>  creepersProxyListDao SUCCEED!");
                    } catch (Exception e) {
                        logger.error("step:  ======>>  creepersProxyListDao .saveAndFlush(entity)  FALSE!!!");
                        logger.error("step:  ======>>  but it will still run to saveAndFlush!");
                        StringBuffer errorMessage = new StringBuffer(
                                "creepersProxyListDao .saveAndFlush(entity)  FALSE!!! \n");
                        errorMessage.append(e);
                        errorMessage.append("\n");
                        errorMessage.append("entity:").append(entity.toString());
                        param.setErrorInfo(errorMessage.toString());
                        creepersExceptionHandleServiceImpl.insertOrUpdateErrorList(param);
                    }
                }

            } else {
                logger.info("step:  ======>>  input resultList is empty! Then update status to false!");
                param.setErrorInfo("input resultList is empty! Then update status to false!");
                param.setTaskStatus(BaseConstant.TaskListStatus.FALSE.getValue());
                logger.info("step:  ======>>  creepersListServiceImpl START!");
                creepersExceptionHandleServiceImpl.insertOrUpdateErrorList(param);
                logger.info("step:  ======>>  creepersListServiceImpl SUCCEED!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("step:  ======>>  ProxyMivmpPipline  save FALSE!!!");
            logger.error("write DB error", e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorInfo("write DB error" + e.getCause().getClass() + e.getCause().getMessage());
            creepersExceptionHandleServiceImpl.insertOrUpdateErrorList(param);
        }
        logger.info("step:  ======>>  exit ProxyMivmpPipline process!");
    }
}
