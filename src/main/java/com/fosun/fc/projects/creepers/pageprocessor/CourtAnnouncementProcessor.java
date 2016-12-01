package com.fosun.fc.projects.creepers.pageprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant;

/**
 * 
 * <p>
 * 法院公告
 * </p>
 * 
 * @author MaXin
 * @since 2016年6月24日
 * @see
 */
@Component("courtAnnouncementProcessor")
public class CourtAnnouncementProcessor extends BaseCreepersListProcessor implements PageProcessor {

    private Site site;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public CourtAnnouncementProcessor() {
        if (null == site) {
            site = Site.me().setDomain("rmfygg.court.gov.cn/").setTimeOut(90000).setRetryTimes(3);
        }

    }

    @Override
    public void process(Page page) {
        try {
            List<String> linksList = page.getHtml().xpath("//div[@class=\"page tac\"]").links().all();
            for (String url : linksList) {
                if (StringUtils.isBlank(url)) {
                    continue;
                }
                Request request = new Request(url);
                request.setMethod(HttpConstant.Method.POST);
                page.addTargetRequest(request);
            }
            logger.info("currentPage:" + page.getUrl());
            String merName = page.getUrl().regex("http://rmfygg.court.gov.cn/psca/lgnot/bulletin/(.*)_0_").toString();
            List<Selectable> list = page.getHtml().xpath("//div[@class=\"contentDiv\"]/table/tbody/tr").nodes();
            List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
            boolean isFirstLine = true;
            for (Selectable string : list) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                Map<String, String> map = new HashMap<String, String>();
                logger.info("======>");
                // 商户名称
                logger.info(CreepersConstant.TCreepersCourtAnnounceColumn.MER_NAME.getValue() + ":" + merName);
                map.put(CreepersConstant.TCreepersCourtAnnounceColumn.MER_NAME.getValue(), merName);
                // 公告类型
                logger.info(CreepersConstant.TCreepersCourtAnnounceColumn.ANNOUNCE_TYPE.getValue()
                        + ":" + string.xpath("//td[1]/allText()").toString());
                map.put(CreepersConstant.TCreepersCourtAnnounceColumn.ANNOUNCE_TYPE.getValue(),
                        string.xpath("//td[1]/allText()").toString());
                // 法院
                logger.info(CreepersConstant.TCreepersCourtAnnounceColumn.COURT_NAME.getValue()
                        + ":" + string.xpath("//td[2]/allText()").toString());
                map.put(CreepersConstant.TCreepersCourtAnnounceColumn.COURT_NAME.getValue(),
                        string.xpath("//td[2]/allText()").toString());
                // 备注信息:当事人
                logger.info(CreepersConstant.TCreepersCourtAnnounceColumn.MEMO.getValue()
                        + ":" + string.xpath("//td[3]/allText()").toString());
                map.put(CreepersConstant.TCreepersCourtAnnounceColumn.MEMO.getValue(),
                        string.xpath("//td[3]/allText()").toString());
                // 公告日期
                logger.info(CreepersConstant.TCreepersCourtAnnounceColumn.ANNOUNCE_DT.getValue()
                        + ":" + string.xpath("//td[4]/allText()").toString());
                map.put(CreepersConstant.TCreepersCourtAnnounceColumn.ANNOUNCE_DT.getValue(),
                        string.xpath("//td[4]/allText()").toString());
                // 公告内容
                logger.info(CreepersConstant.TCreepersCourtAnnounceColumn.ANNOUNCE_CONTENT.getValue()
                        + ":" + string.xpath("//td[5]").regex("href=\"(.*)\" class").toString());
                map.put(CreepersConstant.TCreepersCourtAnnounceColumn.ANNOUNCE_CONTENT.getValue(),
                        string.xpath("//td[5]").regex("href=\"(.*)\" class").toString());
                // logger.info(string);
                resultList.add(map);
            }
            page.putField("resultList", resultList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("CourtAnnouncementProcessor process error:", e);
            CreepersParamDTO param = page.getResultItems().get(BaseConstant.PARAM_DTO_KEY);
            param.setErrorInfo(
                    "CourtAnnouncementProcessor process error:" + e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorPath(getClass().toString());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Request req = new Request("http://rmfygg.court.gov.cn/psca/lgnot/bulletin/福建泰盛进发实业有限公司_0_0.html");
        req.setMethod(HttpConstant.Method.POST);
        Spider.create(new CourtAnnouncementProcessor()).setDownloader(new HttpRequestDownloader()).addRequest(req)
                .thread(5).runAsync();
    }
}
