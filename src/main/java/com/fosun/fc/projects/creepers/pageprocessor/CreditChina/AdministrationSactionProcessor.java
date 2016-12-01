package com.fosun.fc.projects.creepers.pageprocessor.CreditChina;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

/***
 * 
 * <p>
 * 信用中国-行政处罚全量主页面查询爬取
 * </p>
 * 
 * @author LiZhanPing 2016-8-22 00:49:33
 */
@Component("administrationSactionProcessor")
public class AdministrationSactionProcessor extends BaseCreepersListProcessor implements PageProcessor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private Site site;

    private static final String BASE_URL = "http://www.creditchina.gov.cn/";

    private static final String REGEX_URL_PUBLICITY = "publicity_info_search\\?t=\\d+";

    private static final String ACCESS_URL_PUBLICITY = "publicity_info_search?t=";
    private static final String ACCESS_URL_PUBLICITY_DETAIL = "publicity_info_detail/";

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0";

    private static final String SYMBOL_EMPTY = "";
    private static final String SYMBOL_COLON_EN = ":";
    private static final String SYMBOL_COLON_SLASH = "/";

    private static final String KEY_TOTAL_PAGE = "totalPage";
    private static final String KEY_PAGE_NO = "pageNo";
    private static final String KEY_THREAD_NUM = "threadNum";

    private static final String JSON_KEY_RESULTS = "results";
    private static final String JSON_KEY_TOTAL_PAGE_COUNT = "totalPageCount";
    private static final String JSON_KEY_NAME = "name";
    private static final String JSON_KEY_FULL_NAME = "fullname";
    private static final String JSON_KEY_TIME = "time";
    private static final String JSON_KEY_RECORD_SOURCE = "recordSource";
    private static final String JSON_KEY_DATATYPE = "dataType";
    private static final String JSON_KEY_ENCRY_STR = "encryStr";

    @Override
    public void process(Page page) {
        logger.info("======================>>>  AdministrationSactionProcessor start!");
        CreepersParamDTO param = page.getResultItems().get(BaseConstant.PARAM_DTO_KEY);
        String jsonRequest = JSON.toJSONString(page.getRequest());
        param.setBreakDownRequest(jsonRequest);
        try {
            if (page.getUrl().regex(BASE_URL + REGEX_URL_PUBLICITY).match()) {
                JSONObject json = page.getJson().toObject(JSONObject.class);
                logger.info("page.json:" + json.toJSONString());
                JSONArray jsonArr = json.getJSONArray(JSON_KEY_RESULTS);
                logger.info("results:" + jsonArr.toJSONString());
                List<HashMap<String, Object>> tCreepersSaction = new ArrayList<HashMap<String, Object>>();
                // 添加公司信用详情查看请求
                for (int i = 0; i < jsonArr.size(); i++) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    String name = jsonArr.getJSONObject(i).getString(JSON_KEY_NAME);
                    String type = jsonArr.getJSONObject(i).getString(JSON_KEY_FULL_NAME);
                    String province = jsonArr.getJSONObject(i).getString(JSON_KEY_RECORD_SOURCE);
                    String modifyTime = jsonArr.getJSONObject(i).getString(JSON_KEY_TIME);
                    String dataType = jsonArr.getJSONObject(i).getString(JSON_KEY_DATATYPE);
                    String entryStr = jsonArr.getJSONObject(i).getString(JSON_KEY_ENCRY_STR);
                    String content = BASE_URL + ACCESS_URL_PUBLICITY_DETAIL + dataType + SYMBOL_COLON_SLASH + entryStr;
                    map.put(CreepersConstant.TCreepersSactionColumn.NAME.getValue(), name);
                    logger.info(CreepersConstant.TCreepersSactionColumn.NAME.getValue() + SYMBOL_COLON_EN + name);
                    map.put(CreepersConstant.TCreepersSactionColumn.TYPE.getValue(), type);
                    logger.info(CreepersConstant.TCreepersSactionColumn.TYPE.getValue() + SYMBOL_COLON_EN + type);
                    map.put(CreepersConstant.TCreepersSactionColumn.PROVINCE.getValue(), province);
                    logger.info(
                            CreepersConstant.TCreepersSactionColumn.PROVINCE.getValue() + SYMBOL_COLON_EN + province);
                    map.put(CreepersConstant.TCreepersSactionColumn.MODIFY_DT.getValue(), modifyTime);
                    logger.info(CreepersConstant.TCreepersSactionColumn.MODIFY_DT.getValue() + SYMBOL_COLON_EN
                            + modifyTime);
                    map.put(CreepersConstant.TCreepersSactionColumn.CONTENT.getValue(), content);
                    logger.info(CreepersConstant.TCreepersSactionColumn.CONTENT.getValue() + SYMBOL_COLON_EN + content);
                    tCreepersSaction.add(map);
                }
                page.putField(CreepersConstant.TableNamesCreditChina.T_CREEPERS_SACTION.getMapKey(), tCreepersSaction);
                int totalPageCount = null == json.getString(JSON_KEY_TOTAL_PAGE_COUNT) ? 0
                        : Integer.parseInt(json.getString(JSON_KEY_TOTAL_PAGE_COUNT));
                int threadNum = null == page.getRequest().getExtra(KEY_THREAD_NUM) ? 1
                        : (int) page.getRequest().getExtra(KEY_THREAD_NUM);
                int pageNo = null == page.getRequest().getExtra(KEY_PAGE_NO) ? 1
                        : (int) page.getRequest().getExtra(KEY_PAGE_NO);
                NameValuePair[] nameValuePairs = (NameValuePair[]) page.getRequest()
                        .getExtra(BaseConstant.POST_NAME_VALUE_PAIR);
                if (pageNo == 1) {
                    for (int j = 1; j <= threadNum; j++) {
                        if (pageNo + j <= totalPageCount) {
                            Request request = new Request(BASE_URL + ACCESS_URL_PUBLICITY + new Date().getTime());
                            request.setMethod(HttpConstant.Method.POST);
                            NameValuePair[] newNameValuePairs = copyAndModifyNameValuePair(nameValuePairs, "page",
                                    pageNo + j + "");
                            request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, newNameValuePairs);
                            request.putExtra(KEY_TOTAL_PAGE, totalPageCount);
                            request.putExtra(KEY_PAGE_NO, pageNo + j);
                            request.putExtra(KEY_THREAD_NUM, threadNum);
                            page.addTargetRequest(request);
                            logger.info("=============>添加request：" + request);
                        }
                    }
                } else {
                    if (pageNo + threadNum <= totalPageCount) {
                        Request request = new Request(BASE_URL + ACCESS_URL_PUBLICITY + new Date().getTime());
                        request.setMethod(HttpConstant.Method.POST);
                        modifySpecNameValuePair(nameValuePairs, "page", pageNo + threadNum + "");
                        request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, nameValuePairs);
                        request.putExtra(KEY_TOTAL_PAGE, totalPageCount);
                        request.putExtra(KEY_PAGE_NO, pageNo + threadNum);
                        request.putExtra(KEY_THREAD_NUM, threadNum);
                        page.addTargetRequest(request);
                        logger.info("=============>添加request：" + request);
                    }
                }
                logger.info("======================>>>  行政处罚查询   end!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AdministrationSactionProcessor process error:", e);
            param.setErrorInfo("AdministrationSactionProcessor process error:" + e.getMessage());
            param.setErrorPath(getClass().toString());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
            logger.error("======================>>>  AdministrationSactionProcessor end!");
        }
    }

    public void modifySpecNameValuePair(NameValuePair[] nameValuePairs, String key, String value) {
        if (null != nameValuePairs && nameValuePairs.length > 0 && StringUtils.isNoneBlank(key)
                && StringUtils.isNoneBlank(value)) {
            for (int i = 0; i < nameValuePairs.length; i++) {
                if (key.equals(nameValuePairs[i].getName())) {
                    nameValuePairs[i] = new BasicNameValuePair(key, value);
                }
            }
        }
    }

    public NameValuePair[] copyAndModifyNameValuePair(NameValuePair[] nameValuePairs, String key, String value) {
        if (null != nameValuePairs && nameValuePairs.length > 0 && StringUtils.isNoneBlank(key)
                && StringUtils.isNoneBlank(value)) {
            NameValuePair[] newNameValuePairs = new NameValuePair[nameValuePairs.length];
            for (int i = 0; i < newNameValuePairs.length; i++) {
                if (key.equals(nameValuePairs[i].getName())) {
                    newNameValuePairs[i] = new BasicNameValuePair(key, value + SYMBOL_EMPTY);
                } else {
                    newNameValuePairs[i] = new BasicNameValuePair(nameValuePairs[i].getName(),
                            nameValuePairs[i].getValue());
                }
            }
            return newNameValuePairs;
        } else {
            return nameValuePairs;
        }
    }

    @Override
    public Site getSite() {
        if (null == site) {
            site = Site.me().setDomain(BASE_URL).setTimeOut(30000).setUserAgent(USER_AGENT);
        }
        return site;
    }

    public static void main(String[] args) throws IOException {
        String url = "http://www.creditchina.gov.cn/publicity_info_search?t=" + new Date().getTime();
        Request request = new Request(url);
        request.setMethod(HttpConstant.Method.POST);
        NameValuePair[] nameValuePair = new NameValuePair[10];
        nameValuePair[0] = new BasicNameValuePair("keyword", "");
        nameValuePair[1] = new BasicNameValuePair("searchtype", "1");
        nameValuePair[2] = new BasicNameValuePair("objectType", "2");
        nameValuePair[3] = new BasicNameValuePair("areas", "");
        nameValuePair[4] = new BasicNameValuePair("creditType", "");
        nameValuePair[5] = new BasicNameValuePair("dataType", "0");
        nameValuePair[6] = new BasicNameValuePair("areaCode", "");
        nameValuePair[7] = new BasicNameValuePair("templateId", "");
        nameValuePair[8] = new BasicNameValuePair("exact", "0");
        nameValuePair[9] = new BasicNameValuePair("page", "1");
        request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, nameValuePair);
        Spider.create(new AdministrationSactionProcessor()).setDownloader(new HttpRequestDownloader()).thread(1)
                .addRequest(request).run();
    }
}
