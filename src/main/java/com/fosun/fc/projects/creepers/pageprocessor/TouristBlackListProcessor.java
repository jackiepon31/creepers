package com.fosun.fc.projects.creepers.pageprocessor;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.google.common.collect.Sets;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 
 * <p>
 * Demo: 导游旅行社黑名单信息
 * </p>
 * 
 * @author MaXin
 * @since 2016-11-10 15:29:38
 * @see
 */
@Component("touristBlackListProcessor")
public class TouristBlackListProcessor extends BaseCreepersListProcessor implements PageProcessor {

    private static final String SPILT_COLON = ":";

    private static final String TYPE_CONTENT_1 = "旅行社";
    private static final String URL_TYPE_CONTENT_1 = "http://qualitytourism.cnta.gov.cn/badinfo/show-";

    private static final String TYPE_CONTENT_2 = "导游";
    private static final String URL_TYPE_CONTENT_2 = "http://qualitytourism.cnta.gov.cn/badpinfo/show-";

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private Site site;

    public TouristBlackListProcessor() {
        if (null == site) {
            site = Site.me().setDomain("qualitytourism.cnta.gov.cn")
                    .setUserAgent(
                            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36")
                    .setAcceptStatCode(Sets.newHashSet(200, 302)).setSleepTime(5000);
        }

    }

    @Override
    public void process(Page page) {
        ResultItems resultItem = page.getResultItems();
        CreepersParamDTO param = resultItem.get(BaseConstant.PARAM_DTO_KEY);
        logger.info("======>>>开始爬取黑名单信息明细！");
        if (param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL).equals(page.getUrl().toString())) {
            logger.debug("======>>>进入初始页面，添加targetUrl，开始进行爬取。");
            page.addTargetRequests(param.getTargetUrlList());
            page.setSkip(true);
        } else if (page.getUrl().regex("http://qualitytourism.cnta.gov.cn/tools/submit_ajax.ashx").match()) {
            logger.debug("======>>>爬取targetUrl页面完整信息开始：");
            Json json = page.getJson();
            logger.debug(json.toString());
            try {
                // {"ID":12,"CaseNumber":"2015R00002","Name":"张晶晶","CodeTypeNum":"D-4301-512893","UnitName":"湖南省导游服务管理中心挂…","InputDate":"2015-10","LegalName":"R00002","Position":"导游","Address":null,"Facts":"私自将导游证上…","PunishContent":"罚款1000元的","StarTime":"2015-10-16T","EndTime":"2017-10-15T","IsPerson":"1","Types":"001003001","SortName":"导游","RowNumber":1}
                JSONObject jsonObject = json.toObject(JSONObject.class);
                JSONArray d1JsonArr = jsonObject.getJSONArray("d1");
                // List<Map<String, String>> list = new ArrayList<Map<String,
                // String>>();
                for (Object eachD1Json : d1JsonArr) {
                    // Map<String, String> map = new HashMap<String, String>();
                    String sortName = ((JSONObject) eachD1Json).getString("SortName");
                    String id = ((JSONObject) eachD1Json).getString("ID");
                    StringBuffer detailUrl = new StringBuffer();
                    // 判类型
                    if (TYPE_CONTENT_1.equals(sortName)) {
                        detailUrl.append("http://qualitytourism.cnta.gov.cn/badinfo/show-").append(id).append(".html");
                    } else if (TYPE_CONTENT_2.equals(sortName)) {
                        detailUrl.append("http://qualitytourism.cnta.gov.cn/badpinfo/show-").append(id).append(".html");
                    }
                    logger.info("===============>>> add url："+detailUrl.toString());
                    page.addTargetRequest(detailUrl.toString());

                    continue;
                    /**
                     * logger.info(
                     * "==============================================");
                     * list.add(map);
                     */
                }
                page.setSkip(true);
                logger.debug("======>>>爬取targetUrl页面完整信息结束！");
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("TouristBlackListProcessor process error:", e);
                param.setErrorInfo("TouristBlackListProcessor process error:"
                        + e.getCause().getClass() + e.getCause().getMessage());
                param.setErrorPath(getClass().toString());
                creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
                logger.error("======================>>>  TouristBlackListProcessor.process end!");
            }
        } else {
//            logger.info("page:"+page.getHtml().toString());
//            <div class="detailed"> 
            Selectable divSelectable = page.getHtml().xpath("//div[@class=\"detailed\"]");
            logger.info("detailed:"+divSelectable.toString());
            String type = "";
            String detailUrl = page.getUrl().toString();
            // 档案号
            String docNo = "";
            // 执业证号
            String guideNo = "";
            // 单位名称
            String agentName = "";
            // 姓名（导游名称、企业法人）
            String name = "";
            // 违法事项
            String issue = "";
            // 行政处罚决定
            String decision = "";
            // 起止日期
            String period = "";

            Map<String, String> map = new HashMap<String, String>();

            if (page.getUrl().regex(URL_TYPE_CONTENT_2).match()) {
                type = "2";
                // 档案号
                docNo = divSelectable.xpath("//*[@id=\"per_num\"]/allText()").toString();
                // 执业证号
                guideNo = divSelectable.xpath("//*[@id=\"per_practicenumber\"]/allText()").toString();
                // 单位名称
                agentName = divSelectable.xpath("//*[@id=\"per_companyname\"]/allText()").toString();
                // 姓名（导游名称、企业法人）
                name = divSelectable.xpath("//*[@id=\"per_name\"]/allText()").toString();
                // 违法事项
                issue = divSelectable.xpath("//*[@id=\"per_reason\"]/allText()").toString();
                // 行政处罚决定
                decision = divSelectable.xpath("//*[@id=\"per_penaltycontent\"]/allText()").toString();
                // 起止日期
                StringBuffer periodDate = new StringBuffer();
                periodDate.append(divSelectable.xpath("//*[@id=\"per_sy\"]/allText()")).append("年")
                        .append(divSelectable.xpath("//*[@id=\"per_sm\"]/allText()")).append("月")
                        .append(divSelectable.xpath("//*[@id=\"per_sd\"]/allText()")).append("日至")
                        .append(divSelectable.xpath("//*[@id=\"per_ey\"]/allText()")).append("年")
                        .append(divSelectable.xpath("//*[@id=\"per_em\"]/allText()")).append("月")
                        .append(divSelectable.xpath("//*[@id=\"per_ed\"]/allText()")).append("日");
                period = periodDate.toString();
            } else if (page.getUrl().regex(URL_TYPE_CONTENT_1).match()) {
                type = "1";
                // 档案号
                docNo = divSelectable.xpath("//*[@id=\"cop_num\"]/allText()").toString();
                // 执业证号
                guideNo = divSelectable.xpath("//*[@id=\"cop_permitnumber\"]/allText()").toString();
                // 单位名称
                agentName = divSelectable.xpath("//*[@id=\"cop_name\"]/allText()").toString();
                // 姓名（导游名称、企业法人）
                name = divSelectable.xpath("//*[@id=\"cop_LegalPerson\"]/allText()").toString();
                // 违法事项//*[@id="cop_Reason"]
                issue = divSelectable.xpath("//*[@id=\"cop_Reason\"]/allText()").toString();
                // 行政处罚决定//*[@id="cop_PenaltyContent"]
                decision = divSelectable.xpath("//*[@id=\"cop_PenaltyContent\"]/allText()").toString();
                // 起止日期
                StringBuffer periodDate = new StringBuffer();
                periodDate.append(divSelectable.xpath("//*[@id=\"cop_sy\"]/allText()")).append("年")
                        .append(divSelectable.xpath("//*[@id=\"cop_sm\"]/allText()")).append("月")
                        .append(divSelectable.xpath("//*[@id=\"cop_sd\"]/allText()")).append("日至")
                        .append(divSelectable.xpath("//*[@id=\"cop_ey\"]/allText()")).append("年")
                        .append(divSelectable.xpath("//*[@id=\"cop_em\"]/allText()")).append("月")
                        .append(divSelectable.xpath("//*[@id=\"cop_ed\"]/allText()")).append("日");
                period = periodDate.toString();
            }

            // 明细链接
            map.put(CreepersConstant.TCreepersTourBlackListColumn.DETAIL_URL.getValue(), detailUrl);
            logger.info(CreepersConstant.TCreepersTourBlackListColumn.DETAIL_URL.getValue() + SPILT_COLON + detailUrl);

            // 1：旅行社、2：导游
            map.put(CreepersConstant.TCreepersTourBlackListColumn.TYPE.getValue(), type);
            logger.info(CreepersConstant.TCreepersTourBlackListColumn.TYPE.getValue() + SPILT_COLON + type);

            // 档案号
            map.put(CreepersConstant.TCreepersTourBlackListColumn.DOC_NO.getValue(), docNo);
            logger.info(CreepersConstant.TCreepersTourBlackListColumn.DOC_NO.getValue() + SPILT_COLON + docNo);

            // 执业证号
            map.put(CreepersConstant.TCreepersTourBlackListColumn.GUIDE_NO.getValue(), guideNo);
            logger.info(CreepersConstant.TCreepersTourBlackListColumn.GUIDE_NO.getValue() + SPILT_COLON + guideNo);

            // 单位名称
            map.put(CreepersConstant.TCreepersTourBlackListColumn.AGENT_NAME.getValue(), agentName);
            logger.info(CreepersConstant.TCreepersTourBlackListColumn.AGENT_NAME.getValue() + SPILT_COLON + agentName);

            // 姓名（导游名称、企业法人）
            map.put(CreepersConstant.TCreepersTourBlackListColumn.NAME.getValue(), name);
            logger.info(CreepersConstant.TCreepersTourBlackListColumn.NAME.getValue() + SPILT_COLON + name);

            // 违法事项
            map.put(CreepersConstant.TCreepersTourBlackListColumn.ISSUE.getValue(), issue);
            logger.info(CreepersConstant.TCreepersTourBlackListColumn.ISSUE.getValue() + SPILT_COLON + issue);

            // 行政处罚决定
            map.put(CreepersConstant.TCreepersTourBlackListColumn.DECISION.getValue(), decision);
            logger.info(CreepersConstant.TCreepersTourBlackListColumn.DECISION.getValue() + SPILT_COLON + decision);

            // 起止日期
            map.put(CreepersConstant.TCreepersTourBlackListColumn.PERIOD.getValue(), period);
            logger.info(CreepersConstant.TCreepersTourBlackListColumn.PERIOD.getValue() + SPILT_COLON + period);
            page.putField(CreepersConstant.TableNamesOthers.T_CREEPERS_TOUR_BLACK_LIST.getMapKey(),map);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        CreepersParamDTO param = new CreepersParamDTO();
        param.setTaskType(BaseConstant.TaskListType.TOUR_BLACK_LIST.getValue());
        Spider.create(new TouristBlackListProcessor()).setDownloader(new HttpRequestDownloader().setParam(param))
                .addUrl("http://qualitytourism.cnta.gov.cn/tools/submit_ajax.ashx?action=searchbadinfo&type=旅行社&bdate=&edate=&key=")
                .addUrl("http://qualitytourism.cnta.gov.cn/tools/submit_ajax.ashx?action=searchbadinfo&type=导%20游&bdate=&edate=&key=")
                .thread(2).runAsync();
    }
}
