package com.fosun.fc.projects.creepers.spider;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.downloader.SimulateLoginDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.pageprocessor.TouristGuideProcessor;
import com.fosun.fc.projects.creepers.pipeline.TouristGuidePipline;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.utils.HttpConstant;

public class SpiderTouristGuideStartTest extends SpiderBaseTest {

    @Autowired(required = true)
    private TouristGuideProcessor touristGuideProcessor;

    @Autowired(required = true)
    private TouristGuidePipline touristGuidePipline;

    @Autowired(required = true)
    private SimulateLoginDownloader simulateLoginDownloader;

    public void methodTest() {
        CreepersLoginParamDTO param = new CreepersLoginParamDTO();
        param.putSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue(), "D-4502-009024");
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
        param.putNameValuePair("text_dysfzh",
                param.getSearchKeyWord(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()));
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

    @Test
    public void run() {
        methodTest();
    }

}
