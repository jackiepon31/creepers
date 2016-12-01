package com.fosun.fc.projects.creepers.pageprocessor.CreditChina;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant;

/***
 * 
 * <p>
 * 信用中国主页面查询爬取
 * </p>
 * 
 * @author LiZhanPing 2016-8-22 00:49:33
 */
@Component("creditChinaGovProcessor")
public class CreditChinaGovProcessor extends BaseCreepersListProcessor implements PageProcessor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private Site site;

    private static final String BASE_URL = "http://www.creditchina.gov.cn/";

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0";

    private static final String ACCESS_URL_SEARCH = "credit_info_search?t=";
    private static final String ACCESS_URL_DETAIL = "credit_info_detail?";

    private static final String SYMBOL_AND = "&";
    private static final String SYMBOL_EQ = "=";
    private static final String SYMBOL_EMPTY = "";
    private static final String SYMBOL_COLON_EN = ":";
    private static final String SYMBOL_COLON_CN = "：";

    private static final String URL_PARAM_OBJECT_TYPE = "objectType";
    private static final String URL_PARAM_ENCRY_STR = "encryStr";

    private static final String REGEX_URL_CREDIT_SEARCH = "credit_info_search\\?t=\\d+";
    private static final String REGEX_URL_CREDIT_DETAIL = "credit_info_detail\\?objectType=\\d+&encryStr=\\w+";

    private static final String XPATH_DIV_TARGET_DATA = "//div[@class=\"creditsearch-tagsinfos\"]//div[@class='creditsearch-tagsinfo']";
    private static final String XPATH_TEXT = "//text()";
    private static final String XPATH_UL = "//ul";
    private static final String XPATH_LI = "//li";
    private static final String XPATH_STRONG = "//strong";

    private static final String REGEX_TIP = "平台未收录该企业的不良记录";

    private static final String SHI_XIN_COLUMN_NAME = "企业名称";
    private static final String SHI_XIN_COLUMN_CERT_NO = "组织机构代码";
    private static final String SHI_XIN_COLUMN_CASE_NO = "案号";
    private static final String SHI_XIN_COLUMN_PRIVENCE = "地域名称";
    private static final String SHI_XIN_COLUMN_EXEC_COURT = "执行法院";
    private static final String SHI_XIN_COLUMN_PERFORMANCE = "被执行人的履行情况";
    private static final String SHI_XIN_COLUMN_BEHAVIOR_DETAILS = "失信被执行人具体情形";
    private static final String SHI_XIN_COLUMN_LEGAL_REP = "企业法人姓名";
    private static final String SHI_XIN_COLUMN_CASE_DT = "立案时间";
    private static final String SHI_XIN_COLUMN_JUDGEMENT_COURT = "作出执行依据单位";
    private static final String SHI_XIN_COLUMN_DUTY = "法律生效文书确定的义务";
    private static final String SHI_XIN_COLUMN_PUBLISH_DT = "发布时间";
    private static final String SHI_XIN_COLUMN_JUDGEMENT_NO = "执行依据文号";

    private static final String JSON_KEY_RESULT = "result";
    private static final String JSON_KEY_RESULTS = "results";
    private static final String JSON_KEY_TOTALPAGECOUNT = "totalPageCount";

    @Override
    public void process(Page page) {
        logger.info("======================>>>  CreditChinaGovProcessor start!");
        try {
            if (page.getUrl().regex(BASE_URL + REGEX_URL_CREDIT_SEARCH).match()) {
                JSONObject json = page.getJson().toObject(JSONObject.class);
                logger.info("page.json:" + json.toJSONString());
                JSONArray jsonArr = json.getJSONObject(JSON_KEY_RESULT).getJSONArray(JSON_KEY_RESULTS);
                logger.info("result:" + jsonArr.toJSONString());
                for (int i = 0; i < jsonArr.size(); i++) {
                    page.addTargetRequest(BASE_URL + ACCESS_URL_DETAIL + URL_PARAM_OBJECT_TYPE + SYMBOL_EQ
                            + jsonArr.getJSONObject(i).getString(URL_PARAM_OBJECT_TYPE) + SYMBOL_AND
                            + URL_PARAM_ENCRY_STR + SYMBOL_EQ
                            + jsonArr.getJSONObject(i).getString(URL_PARAM_ENCRY_STR));
                    logger.info(BASE_URL + ACCESS_URL_DETAIL + URL_PARAM_OBJECT_TYPE + SYMBOL_EQ
                            + jsonArr.getJSONObject(i).getString(URL_PARAM_OBJECT_TYPE) + SYMBOL_AND
                            + URL_PARAM_ENCRY_STR + SYMBOL_EQ
                            + jsonArr.getJSONObject(i).getString(URL_PARAM_ENCRY_STR));
                }
                int totalPageCount = Integer
                        .parseInt(json.getJSONObject(JSON_KEY_RESULT).getString(JSON_KEY_TOTALPAGECOUNT));
                logger.info("totalPageCount:" + totalPageCount);
                // 添加分页请求,只有第一页且总页数大于1才可以添加分页请求
                if (page.getUrl().toString().contains("currentPageNo=1") && totalPageCount > 1) {
                    NameValuePair[] nameValuePairs = (NameValuePair[]) page.getRequest()
                            .getExtra(BaseConstant.POST_NAME_VALUE_PAIR);
                    for (int j = 2; j <= totalPageCount; j++) {
                        Request request = new Request(BASE_URL + ACCESS_URL_SEARCH + new Date().getTime());
                        request.setMethod(HttpConstant.Method.POST);
                        NameValuePair[] newNameValuePairs = new NameValuePair[nameValuePairs.length];
                        for (int i = 0; i < newNameValuePairs.length; i++) {
                            if ("page".equals(nameValuePairs[i].getName())) {
                                newNameValuePairs[i] = new BasicNameValuePair("page", j + SYMBOL_EMPTY);
                            } else {
                                newNameValuePairs[i] = new BasicNameValuePair(nameValuePairs[i].getName(),
                                        nameValuePairs[i].getValue());
                            }
                        }
                        request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, newNameValuePairs);
                        page.addTargetRequest(request);
                        logger.info("=============>添加request：" + request);
                    }
                }
                // 不存储数据,跳过pipeline
                page.setSkip(true);
                logger.info("======================>>>  信用查询   end!");
            } else if (page.getUrl().regex(BASE_URL + REGEX_URL_CREDIT_DETAIL).match()) {
                logger.info("======================>>>  失信信息   start!");
                List<Selectable> list = page.getHtml().xpath(XPATH_DIV_TARGET_DATA).nodes();
                for (int i = 0; i < list.size(); i++) {
                    if (0 == i) {
                        logger.info("===========暂不录入基础信息===========");
                    } else if (1 == i) {
                        logger.info("===========暂不录入优良信息===========");
                    } else if (2 == i) {
                        logger.info("===========暂不录入不良信息===========");
                    } else if (3 == i) {
                        if (!list.get(i).regex(REGEX_TIP).match())
                            getShiXinInfo(page, list.get(i));
                    }
                }
                logger.info("======================>>>  失信信息   end!");
            }
            logger.info("======================>>>  CreditChinaGovProcessor end!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("CreditChinaGovProcessor process error:", e);
            CreepersParamDTO param = page.getResultItems().get(BaseConstant.PARAM_DTO_KEY);
            param.setErrorInfo("CreditChinaGovProcessor process error:" + e.getMessage());
            param.setErrorPath(getClass().toString());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
            logger.error("======================>>>  CreditChinaGovProcessor end!");
        }
    }

    public void getShiXinInfo(Page page, Selectable divSel) {
        // 创建存储数据结构
        List<HashMap<String, Object>> tCreepersShiXin = new ArrayList<HashMap<String, Object>>();
        List<Selectable> ulList = divSel.xpath(XPATH_UL).nodes();
        for (Selectable ulSel : ulList) {
            logger.info("===========失信记录============");
            HashMap<String, Object> map = new HashMap<String, Object>();
            List<Selectable> liList = ulSel.xpath("//li").nodes();
            for (Selectable liSel : liList) {
                String name = liSel.xpath(XPATH_LI + XPATH_STRONG + XPATH_TEXT).toString()
                        .replace(SYMBOL_COLON_EN, SYMBOL_EMPTY).replace(SYMBOL_COLON_CN, SYMBOL_EMPTY).trim();
                String value = liSel.xpath(XPATH_LI + XPATH_TEXT).toString().trim();
                if (SHI_XIN_COLUMN_NAME.equals(name)) {
                    map.put(CreepersConstant.tCreepersShixinColumn.NAME.getValue(), value);
                    logger.info(SHI_XIN_COLUMN_NAME + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.tCreepersShixinColumn.NAME.getValue()));
                } else if (SHI_XIN_COLUMN_CERT_NO.equals(name)) {
                    map.put(CreepersConstant.tCreepersShixinColumn.CERT_NO.getValue(), value);
                    logger.info(SHI_XIN_COLUMN_CERT_NO + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.tCreepersShixinColumn.CERT_NO.getValue()));
                } else if (SHI_XIN_COLUMN_CASE_NO.equals(name)) {
                    map.put(CreepersConstant.tCreepersShixinColumn.CASE_NO.getValue(), value);
                    logger.info(name + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.tCreepersShixinColumn.CASE_NO.getValue()));
                } else if (SHI_XIN_COLUMN_PRIVENCE.equals(name)) {
                    map.put(CreepersConstant.tCreepersShixinColumn.PROVINCE.getValue(), value);
                    logger.info(name + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.tCreepersShixinColumn.PROVINCE.getValue()));
                } else if (SHI_XIN_COLUMN_EXEC_COURT.equals(name)) {
                    map.put(CreepersConstant.tCreepersShixinColumn.EXEC_COURT.getValue(), value);
                    logger.info(name + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.tCreepersShixinColumn.EXEC_COURT.getValue()));
                } else if (SHI_XIN_COLUMN_PERFORMANCE.equals(name)) {
                    map.put(CreepersConstant.tCreepersShixinColumn.PERFORMANCE.getValue(), value);
                    logger.info(name + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.tCreepersShixinColumn.PERFORMANCE.getValue()));
                } else if (SHI_XIN_COLUMN_BEHAVIOR_DETAILS.equals(name)) {
                    map.put(CreepersConstant.tCreepersShixinColumn.BEHAVIOR_DETAILS.getValue(), value);
                    logger.info(name + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.tCreepersShixinColumn.BEHAVIOR_DETAILS.getValue()));
                } else if (SHI_XIN_COLUMN_LEGAL_REP.equals(name)) {
                    map.put(CreepersConstant.tCreepersShixinColumn.LEGAL_REP.getValue(), value);
                    logger.info(name + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.tCreepersShixinColumn.LEGAL_REP.getValue()));
                } else if (SHI_XIN_COLUMN_CASE_DT.equals(name)) {
                    map.put(CreepersConstant.tCreepersShixinColumn.CASE_DT.getValue(), value);
                    logger.info(name + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.tCreepersShixinColumn.CASE_DT.getValue()));
                } else if (SHI_XIN_COLUMN_JUDGEMENT_COURT.equals(name)) {
                    map.put(CreepersConstant.tCreepersShixinColumn.JUDGEMENT_COURT.getValue(), value);
                    logger.info(name + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.tCreepersShixinColumn.JUDGEMENT_COURT.getValue()));
                } else if (SHI_XIN_COLUMN_DUTY.equals(name)) {
                    map.put(CreepersConstant.tCreepersShixinColumn.DUTY.getValue(), value);
                    logger.info(
                            name + SYMBOL_COLON_EN + map.get(CreepersConstant.tCreepersShixinColumn.DUTY.getValue()));
                } else if (SHI_XIN_COLUMN_PUBLISH_DT.equals(name)) {
                    map.put(CreepersConstant.tCreepersShixinColumn.PUBLISH_DT.getValue(), value);
                    logger.info(name + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.tCreepersShixinColumn.PUBLISH_DT.getValue()));
                } else if (SHI_XIN_COLUMN_JUDGEMENT_NO.equals(name)) {
                    map.put(CreepersConstant.tCreepersShixinColumn.JUDGEMENT_NO.getValue(), value);
                    logger.info(name + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.tCreepersShixinColumn.JUDGEMENT_NO.getValue()));
                } else {
                    logger.info(name + SYMBOL_COLON_CN + value + "，该值暂未录入数据库");
                }
            }
            tCreepersShiXin.add(map);
        }
        page.putField(CreepersConstant.TableNamesCreditChina.T_CREEPERS_SHIXIN.getMapKey(), tCreepersShiXin);
    }

    @Override
    public Site getSite() {
        if (null == site) {
            site = Site.me().setDomain(BASE_URL).setTimeOut(30000).setUserAgent(USER_AGENT);
        }
        return site;
    }

    public static void main(String[] args) throws IOException {
        String url = "http://www.creditchina.gov.cn/credit_info_search?t=" + new Date().getTime() + "&currentPageNo=1";
        Request request = new Request(url);
        request.setMethod(HttpConstant.Method.POST);
        NameValuePair[] nameValuePair = new NameValuePair[10];
        nameValuePair[0] = new BasicNameValuePair("keyword", "百度");
        nameValuePair[1] = new BasicNameValuePair("searchtype", "0");
        nameValuePair[2] = new BasicNameValuePair("objectType", "2");
        nameValuePair[3] = new BasicNameValuePair("areas", "");
        nameValuePair[4] = new BasicNameValuePair("creditType", "");
        nameValuePair[5] = new BasicNameValuePair("dataType", "1");
        nameValuePair[6] = new BasicNameValuePair("areaCode", "");
        nameValuePair[7] = new BasicNameValuePair("templateId", "");
        nameValuePair[8] = new BasicNameValuePair("exact", "0");
        nameValuePair[9] = new BasicNameValuePair("page", "1");
        request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, nameValuePair);
        Spider.create(new CreditChinaGovProcessor()).setDownloader(new HttpRequestDownloader()).thread(1)
                .addRequest(request).run();
    }
}
