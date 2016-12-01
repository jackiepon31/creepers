package com.fosun.fc.projects.creepers.pageprocessor.CreditChina;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.pageprocessor.BaseCreepersListProcessor;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

/***
 * 
 * <p>
 * 限制发行企业债黑名单
 * </p>
 * 
 * @author MaXin
 * @since 2016-9-23 09:35:25
 */
@Component("corporateBondsBlacklistProcessor")
public class CorporateBondsBlacklistProcessor extends BaseCreepersListProcessor implements PageProcessor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private Site site;

    private static final String BASE_URL = "http://www.creditchina.gov.cn";
    private static final String TARGET_URL = "/uploads/creditinfo/";
    private static final String URL_SPEC_SYMPOL = "?_=";

    private static final String JSON_KEY_INAME = "INAME";
    private static final String JSON_KEY_CARDNUMBER = "CARDNUMBER";
    private static final String JSON_KEY_AREA_NAME = "AREA_NAME";

    private static final String KEY_TOTAL_PAGE = "totalPage";
    private static final String KEY_PAGE_NO = "pageNo";
    private static final String KEY_THREAD_NUM = "threadNum";

    private static final String T_KEY_NAME = "name";
    private static final String T_KEY_CODE = "code";
    private static final String T_KEY_PROVINCE = "province";

    public CorporateBondsBlacklistProcessor() {
        site = Site.me().setCharset("UTF-8").setDomain(BASE_URL).setTimeOut(30000)
                .setUserAgent(
                        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36")
                .setSleepTime(1000);
    }

    @Override
    public void process(Page page) {
        logger.info("======================>>>  CorporateBondsBlacklistProcessor start!");
        ResultItems resultItems = page.getResultItems();
        CreepersParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        String jsonRequest = JSON.toJSONString(page.getRequest());
        param.setBreakDownRequest(jsonRequest);
        try {
            if (param.getOrderUrl(BaseConstant.OrderUrlKey.ALL_READY_URL).equals(page.getUrl().toString())) {
                String jsonStr = page.getJson().toString();
                JSONArray jsonArr = JSON.parseArray(jsonStr);
                JSONObject jsonObj = jsonArr.getJSONObject(0);
                int totalPage = null == jsonObj.getInteger(KEY_TOTAL_PAGE) ? 0 : jsonObj.getInteger(KEY_TOTAL_PAGE);
                int threadNum = null == page.getRequest().getExtra(KEY_THREAD_NUM) ? 0
                        : (int) page.getRequest().getExtra(KEY_THREAD_NUM);
                for (int i = 1; i <= threadNum; i++) {
                    if (i <= totalPage) {
                        String targetUrl = BASE_URL + TARGET_URL + i + URL_SPEC_SYMPOL + new Date().getTime();
                        Request targetRequest = new Request(targetUrl);
                        targetRequest.putExtra(KEY_TOTAL_PAGE, totalPage);
                        targetRequest.putExtra(KEY_PAGE_NO, i);
                        targetRequest.putExtra(KEY_THREAD_NUM, threadNum);
                        page.addTargetRequest(targetRequest);
                    }
                }
                page.setSkip(true);
            } else if (page.getUrl().toString().contains(BASE_URL + TARGET_URL)) {
                List<HashMap<String, Object>> tCreepersCourtCorpBonds = new ArrayList<HashMap<String, Object>>();
                String jsonStr = page.getJson().toString();
                if (jsonStr.contains("\\")) {
                    jsonStr = jsonStr.replaceAll("\\\\", "\\\\\\\\");
                }
                logger.info("JSONArray:" + jsonStr);
                JSONArray jsonArr = JSON.parseArray(jsonStr);
                for (int i = 0; i < jsonArr.size(); i++) {
                    JSONObject jsonObj = jsonArr.getJSONObject(i);
                    String name = jsonObj.getString(JSON_KEY_INAME);
                    String code = jsonObj.getString(JSON_KEY_CARDNUMBER);
                    String province = jsonObj.getString(JSON_KEY_AREA_NAME);
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put(T_KEY_NAME, name);
                    map.put(T_KEY_CODE, code);
                    map.put(T_KEY_PROVINCE, province);
                    tCreepersCourtCorpBonds.add(map);
                }
                page.putField(CreepersConstant.TableNamesCreditChina.T_CREEPERS_COURT_CORP_BONDS.getMapKey(),
                        tCreepersCourtCorpBonds);
                int totalPage = null == page.getRequest().getExtra(KEY_TOTAL_PAGE) ? 0
                        : (int) page.getRequest().getExtra(KEY_TOTAL_PAGE);
                int pageNo = null == page.getRequest().getExtra(KEY_PAGE_NO) ? 1
                        : (int) page.getRequest().getExtra(KEY_PAGE_NO);
                int threadNum = null == page.getRequest().getExtra(KEY_THREAD_NUM) ? 1
                        : (int) page.getRequest().getExtra(KEY_THREAD_NUM);
                pageNo = pageNo + threadNum;
                if (pageNo <= totalPage) {
                    String targetUrl = BASE_URL + TARGET_URL + pageNo + URL_SPEC_SYMPOL + new Date().getTime();
                    Request nextRequest = new Request(targetUrl);
                    nextRequest.putExtra(KEY_TOTAL_PAGE, totalPage);
                    nextRequest.putExtra(KEY_PAGE_NO, pageNo);
                    nextRequest.putExtra(KEY_THREAD_NUM, threadNum);
                    page.addTargetRequest(nextRequest);
                }
            }
            logger.info("======================>>>  CorporateBondsBlacklistProcessor end!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("CorporateBondsBlacklistProcessor error:", e);
            param.setErrorInfo("CorporateBondsBlacklistProcessor error:" + e.getMessage());
            param.setErrorPath(getClass().toString());
            creepersExceptionHandleServiceImpl.handleJobExceptionAndPrintLog(param);
            logger.error("======================>>>  CorporateBondsBlacklistProcessor end!");
        }
    }

    public static String convertEncodingFormat(String str, String formatFrom, String FormatTo) {
        String result = null;
        if (!(str == null || str.length() == 0)) {
            try {
                result = new String(str.getBytes(formatFrom), FormatTo);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws IOException {
        CreepersParamDTO param = new CreepersParamDTO();
        param.putOrderUrl(BaseConstant.OrderUrlKey.ALL_READY_URL,
                "http://www.creditchina.gov.cn/uploads/creditinfo/data_info?_=" + new Date().getTime());
        Request request = CommonMethodUtils.buildAllReadyRequest(param, HttpConstant.Method.GET);
        Spider.create(new CorporateBondsBlacklistProcessor()).setDownloader(new HttpRequestDownloader().setParam(param))
                .thread(1).addRequest(request).run();
    }
}
