/**
 * <p>
 * Copyright(c) @2016 Fortune Credit Management Co., Ltd.
 * </p>
 */
package com.fosun.fc.projects.creepers.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dao.CreepersTourGuideDao;
import com.fosun.fc.projects.creepers.downloader.SimulateLoginDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersTourGuide;
import com.fosun.fc.projects.creepers.pageprocessor.TouristGuideProcessor;
import com.fosun.fc.projects.creepers.pipeline.TouristGuidePipline;
import com.fosun.fc.projects.creepers.service.ICreepersTouristGuideService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.utils.HttpConstant;

/**
 * <p>
 * description: 导游证信息Service
 * </p>
 * 
 * @author MaXin
 * @since 2016年11月2日
 * @see
 */
@Service("creepersTouristGuideServiceImpl")
public class CreepersTouristGuideServiceImpl extends CreepersBaseServiceImpl implements ICreepersTouristGuideService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CreepersTourGuideDao creepersTourGuideDao;

    @Autowired
    private TouristGuideProcessor touristGuideProcessor;

    @Autowired
    private TouristGuidePipline touristGuidePipline;

    @Autowired
    private SimulateLoginDownloader simulateLoginDownloader;

    /*
     * @see com.fosun.fc.projects.creepers.service.ICreepersTouristGuideService#
     * saveEntity(com.fosun.fc.projects.creepers.entity.TCreepersTourGuide)
     */
    @Override
    public void saveEntity(TCreepersTourGuide entity) {

        TCreepersTourGuide oldEntity = creepersTourGuideDao.findTop1ByGuideNo(entity.getGuideNo());
        if (oldEntity != null) {
            entity.setId(oldEntity.getId());
            entity.setVersion(oldEntity.getVersion());
        }
        creepersTourGuideDao.saveAndFlush(entity);
    }

    @Override
    public void processByParam(CreepersLoginParamDTO param) {
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL,
                "http://daoyou-chaxun.cnta.gov.cn/single_info/selectlogin_1.asp?11");
        param.putOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL,
                "http://daoyou-chaxun.cnta.gov.cn/single_info/validatecode.asp");
        param.putOrderUrl(BaseConstant.OrderUrlKey.DO_LOGIN_URL,
                "http://daoyou-chaxun.cnta.gov.cn/single_info/selectlogin_1.asp");
        param.setTaskType(BaseConstant.TaskListType.TOUR_GUIDE_LIST.getValue());
        param.putNameValuePair("text_dyzh",
                param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue()));
        param.putNameValuePair("text_dykh",
                param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue()));
        //不提供身份证号查询，因身份证号在导游证信息中可能为空。
        param.putNameValuePair("text_dysfzh", "");
        param.putNameValuePair("useCapture", "false");
        param.putNameValuePair("passive", "false");
        param.setCaptchaKey("vcode");
        param.putNameValuePair("x", String.valueOf(RandomUtils.nextInt(30) + 1));
        param.putNameValuePair("y", String.valueOf(RandomUtils.nextInt(19) + 1));
        param.setDoRedirect(true);
        Request request = CommonMethodUtils.buildIndexRequest(param);
        request.setMethod(HttpConstant.Method.GET);
        Spider.create(touristGuideProcessor).addPipeline(touristGuidePipline).addRequest(request)
                .setDownloader(simulateLoginDownloader.setParam(param)).thread(1).run();

    }

    @Override
    public List<TCreepersTourGuide> findByEntityForMap(TCreepersTourGuide entity) {

        return null;
    }

    @Override
    public Map<String, Object> findByParamForMap(CreepersLoginParamDTO param) {

        List<TCreepersTourGuide> list = new ArrayList<TCreepersTourGuide>();

        if (StringUtils
                .isNotBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue()))
                && StringUtils.isNotBlank(
                        param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue()))
                && StringUtils.isNotBlank(
                        param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()))) {
            list = creepersTourGuideDao.findByGuideNoAndCardNoAndCertNo(
                    param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue()),
                    param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue()),
                    param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()));

        } else if (StringUtils
                .isNotBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue()))
                && StringUtils.isNotBlank(
                        param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue()))
                && StringUtils.isBlank(
                        param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()))) {
            list = creepersTourGuideDao.findByGuideNoAndCardNo(
                    param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue()),
                    param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue()));

        } else if ((StringUtils
                .isBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue()))
                || StringUtils
                        .isBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue())))
                && StringUtils.isBlank(
                        param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()))) {
            list = creepersTourGuideDao.findByGuideNoOrCertNo(
                    param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue()),
                    param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()));

        } else if ((StringUtils
                .isBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue()))
                || StringUtils
                        .isBlank(param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue())))
                && StringUtils.isNotBlank(
                        param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()))) {
            list = creepersTourGuideDao.findByGuideNoAndCertNoOrCardNoAndCertNo(
                    param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue()),
                    param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()),
                    param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue()),
                    param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()));

        }
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            if (!CommonMethodUtils.isEmpty(list)) {
                resultList = CommonMethodUtils.entityList(list);
                map.put(CreepersConstant.TableNamesOthers.T_CREEPERS_TOUR_GUIDE.getMapKey(), resultList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("CreepersTouristGuideServiceImpl findByParamForMap error:", e);
            param.setErrorInfo(
                    "CreepersTouristGuideServiceImpl findByParamForMap error:" + e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorPath(getClass().toString());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
            logger.error("======================>>>  CreepersTouristGuideServiceImpl.findByParamForMap end!");
        }
        return map;
    }

}
