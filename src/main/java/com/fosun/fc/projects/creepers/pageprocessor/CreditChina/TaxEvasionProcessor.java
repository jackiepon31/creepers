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
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant;

/***
 * 
 * <p>
 * 信用中国-重大税收违法案件当事人名单主页面查询爬取
 * </p>
 * 
 * @author LiZhanPing 2016-8-22 00:49:33
 */
@Component
public class TaxEvasionProcessor extends BaseCreepersListProcessor implements PageProcessor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private Site site;

    private static final String BASE_URL = "http://www.creditchina.gov.cn/";

    private static final String REGEX_URL_CREDIT_SEARCH = "credit_info_search\\?t=\\d+";

    private static final String ACCESS_URL_CREDIT_SEARCH = "credit_info_search?t=";
    private static final String ACCESS_URL_CREDIT_DETAIL = "credit_info_detail?";

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0";

    private static final String SYMBOL_EQ = "=";
    private static final String SYMBOL_AND = "&";
    private static final String SYMBOL_EMPTY = "";
    private static final String SYMBOL_COLON_EN = ":";
    private static final String SYMBOL_COLON_CN = "：";

    private static final String KEY_TOTAL_PAGE = "totalPage";
    private static final String KEY_PAGE_NO = "pageNo";
    private static final String KEY_THREAD_NUM = "threadNum";

    private static final String JSON_KEY_RESULT = "result";
    private static final String JSON_KEY_RESULTS = "results";
    private static final String JSON_KEY_TOTAL_PAGE_COUNT = "totalPageCount";
    private static final String JSON_KEY_NAME = "name";
    private static final String JSON_KEY_CODE = "idCardOrOrgCode";
    private static final String JSON_KEY_GOOD_COUNT = "goodCount";
    private static final String JSON_KEY_BAD_COUNT = "badCount";
    private static final String JSON_KEY_DISHONESTY_COUNT = "dishonestyCount";
    private static final String JSON_KEY_AREA = "area";
    private static final String JSON_KEY_OBJECT_TYPE = "objectType";
    private static final String JSON_KEY_ENCRY_STR = "encryStr";

    private static final String XPATH_DIV_TARGET_DATA = "//div[@class=\"creditsearch-tagsinfos\"]//div[@class='creditsearch-tagsinfo']";
    private static final String XPATH_TEXT = "//text()";
    private static final String XPATH_UL = "//ul";
    private static final String XPATH_LI = "//li";
    private static final String XPATH_STRONG = "//strong";

    private static final String REGEX_TIP = "平台未收录该企业的不良记录";

    private static final String SHI_XIN_COLUMN_SOURCE = "数据来源";
    private static final String SHI_XIN_COLUMN_TYPE = "数据类别";
    private static final String SHI_XIN_COLUMN_PROVINCE = "省份";
    private static final String SHI_XIN_COLUMN_COURT = "检查机关";
    private static final String SHI_XIN_COLUMN_NAME = "纳税人名称";
    private static final String SHI_XIN_COLUMN_TAX_NO = "纳税人识别码";
    private static final String SHI_XIN_COLUMN_CODE = "组织机构代码";
    private static final String SHI_XIN_COLUMN_ADDR = "注册地址";
    private static final String SHI_XIN_COLUMN_LEGAL_REP = "法定代表人或者负责人姓名";
    private static final String SHI_XIN_COLUMN_LEGAL_SEX = "性别";
    private static final String SHI_XIN_COLUMN_CERT_NAME = "法定代表人或者负责人证件名称";
    private static final String SHI_XIN_COLUMN_FINANCIAL_NAME = "负有直接责任的财务负责人姓名";
    private static final String SHI_XIN_COLUMN_FINANCIAL_SEX = "负有直接责任的财务负责人性别";
    private static final String SHI_XIN_COLUMN_FINANCIAL_CERT_NAME = "负有直接责任的财务负责人证件名称";
    private static final String SHI_XIN_COLUMN_INTER_INFO = "负有直接责任的中介机构信息";
    private static final String SHI_XIN_COLUMN_CASE_NATURE = "案件性质";
    private static final String SHI_XIN_COLUMN_ILLEGAL_FACTS = "主要违法事实";
    private static final String SHI_XIN_COLUMN_PUNISH_INFO = "相关法律依据及处理处罚情况";
    private static final String SHI_XIN_COLUMN_PUBLISH_LEVEL = "发布级别";

    @Override
    public void process(Page page) {
        logger.info("======================>>>  TaxEvasionProcessor start!");
        CreepersParamDTO param = page.getResultItems().get(BaseConstant.PARAM_DTO_KEY);
        String jsonRequest = JSON.toJSONString(page.getRequest());
        param.setBreakDownRequest(jsonRequest);
        try {
            if (page.getUrl().regex(BASE_URL + REGEX_URL_CREDIT_SEARCH).match()) {
                JSONObject json = page.getJson().toObject(JSONObject.class);
                logger.info("page.json:" + json.toJSONString());
                JSONArray jsonArr = json.getJSONObject(JSON_KEY_RESULT).getJSONArray(JSON_KEY_RESULTS);
                logger.info("results:" + jsonArr.toJSONString());
                List<HashMap<String, Object>> tCreepersTaxEvasion = new ArrayList<HashMap<String, Object>>();
                for (int i = 0; i < jsonArr.size(); i++) {
                    // 提取偷税漏税黑名单信息
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    String name = jsonArr.getJSONObject(i).getString(JSON_KEY_NAME);
                    String code = jsonArr.getJSONObject(i).getString(JSON_KEY_CODE);
                    String goodRecord = jsonArr.getJSONObject(i).getString(JSON_KEY_GOOD_COUNT);
                    String badRecord = jsonArr.getJSONObject(i).getString(JSON_KEY_BAD_COUNT);
                    String disRecord = jsonArr.getJSONObject(i).getString(JSON_KEY_DISHONESTY_COUNT);
                    String province = jsonArr.getJSONObject(i).getString(JSON_KEY_AREA);
                    String objectType = jsonArr.getJSONObject(i).getString(JSON_KEY_OBJECT_TYPE);
                    String entryStr = jsonArr.getJSONObject(i).getString(JSON_KEY_ENCRY_STR);
                    map.put(CreepersConstant.TCreepersShixinAllColumn.NAME.getValue(), name);
                    logger.info(CreepersConstant.TCreepersShixinAllColumn.NAME.getValue() + SYMBOL_COLON_EN + name);
                    map.put(CreepersConstant.TCreepersShixinAllColumn.CODE.getValue(), code);
                    logger.info(CreepersConstant.TCreepersShixinAllColumn.CODE.getValue() + SYMBOL_COLON_EN + code);
                    map.put(CreepersConstant.TCreepersShixinAllColumn.PROVINCE.getValue(), province);
                    logger.info(
                            CreepersConstant.TCreepersShixinAllColumn.PROVINCE.getValue() + SYMBOL_COLON_EN + province);
                    map.put(CreepersConstant.TCreepersShixinAllColumn.GOOD_RECORD.getValue(), goodRecord);
                    logger.info(CreepersConstant.TCreepersShixinAllColumn.GOOD_RECORD.getValue() + SYMBOL_COLON_EN
                            + goodRecord);
                    map.put(CreepersConstant.TCreepersShixinAllColumn.BAD_RECORD.getValue(), badRecord);
                    logger.info(CreepersConstant.TCreepersShixinAllColumn.BAD_RECORD.getValue() + SYMBOL_COLON_EN
                            + badRecord);
                    map.put(CreepersConstant.TCreepersShixinAllColumn.DIS_RECORD.getValue(), disRecord);
                    logger.info(CreepersConstant.TCreepersShixinAllColumn.DIS_RECORD.getValue() + SYMBOL_COLON_EN
                            + disRecord);
                    tCreepersTaxEvasion.add(map);
                    // 添加链接
                    page.addTargetRequest(BASE_URL + ACCESS_URL_CREDIT_DETAIL + JSON_KEY_OBJECT_TYPE + SYMBOL_EQ
                            + objectType + SYMBOL_AND + JSON_KEY_ENCRY_STR + SYMBOL_EQ + entryStr);
                    logger.info(BASE_URL + ACCESS_URL_CREDIT_DETAIL + JSON_KEY_OBJECT_TYPE + SYMBOL_EQ + objectType
                            + SYMBOL_AND + JSON_KEY_ENCRY_STR + SYMBOL_EQ + entryStr);

                }
                page.putField(CreepersConstant.TableNamesCreditChina.T_CREEPERS_TAX_EVASION.getMapKey(),
                        tCreepersTaxEvasion);
                int totalPageCount = null == json.getJSONObject(JSON_KEY_RESULT).getString(JSON_KEY_TOTAL_PAGE_COUNT)
                        ? 0
                        : Integer.parseInt(json.getJSONObject(JSON_KEY_RESULT).getString(JSON_KEY_TOTAL_PAGE_COUNT));
                int threadNum = null == page.getRequest().getExtra(KEY_THREAD_NUM) ? 1
                        : (int) page.getRequest().getExtra(KEY_THREAD_NUM);
                int pageNo = null == page.getRequest().getExtra(KEY_PAGE_NO) ? 1
                        : (int) page.getRequest().getExtra(KEY_PAGE_NO);
                NameValuePair[] nameValuePairs = (NameValuePair[]) page.getRequest()
                        .getExtra(BaseConstant.POST_NAME_VALUE_PAIR);
                if (pageNo == 1) {
                    for (int j = 1; j <= threadNum; j++) {
                        if (pageNo + j <= totalPageCount) {
                            Request request = new Request(BASE_URL + ACCESS_URL_CREDIT_SEARCH + new Date().getTime());
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
                        Request request = new Request(BASE_URL + ACCESS_URL_CREDIT_SEARCH + new Date().getTime());
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
                logger.info("======================>>>  偷税漏税黑名单查询   end!");
            } else if (page.getUrl().regex(BASE_URL + ACCESS_URL_CREDIT_DETAIL).match()) {
                logger.info("======================>>> 失信信息 start!");
                List<Selectable> list = page.getHtml().xpath(XPATH_DIV_TARGET_DATA).nodes();
                for (int i = 0; i < list.size(); i++) {
                    if (0 == i) {
                        logger.info("===========暂不录入基础信息===========");
                    } else if (1 == i) {
                        logger.info("===========暂不录入优良信息===========");
                    } else if (2 == i) {
                        if (!list.get(i).regex(REGEX_TIP).match())
                            getNegativeInfo(page, list.get(i));
                    } else if (3 == i) {
                        logger.info("===========暂不录入受惩黑名单信息===========");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("TaxEvasionProcessor process error:", e);
            param.setErrorInfo("TaxEvasionProcessor process error:" + e.getMessage());
            param.setErrorPath(getClass().getName().toString());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
            logger.error("======================>>>  TaxEvasionProcessor end!");
        }
    }

    public void getNegativeInfo(Page page, Selectable divSel) {
        // 创建存储数据结构
        List<HashMap<String, Object>> tCreepersTaxEvasionDetail = new ArrayList<HashMap<String, Object>>();
        List<Selectable> ulList = divSel.xpath(XPATH_UL).nodes();
        for (Selectable ulSel : ulList) {
            logger.info("===========负面记录============");
            HashMap<String, Object> map = new HashMap<String, Object>();
            List<Selectable> liList = ulSel.xpath("//li").nodes();
            for (Selectable liSel : liList) {
                String columnName = liSel.xpath(XPATH_LI + XPATH_STRONG + XPATH_TEXT).toString()
                        .replace(SYMBOL_COLON_EN, SYMBOL_EMPTY).replace(SYMBOL_COLON_CN, SYMBOL_EMPTY).trim();
                String columnValue = liSel.xpath(XPATH_LI + XPATH_TEXT).toString().trim();
                if (SHI_XIN_COLUMN_SOURCE.equals(columnName)) {
                    map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.SOURCE.getValue(), columnValue);
                    logger.info(SHI_XIN_COLUMN_SOURCE + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.SOURCE.getValue()));
                } else if (SHI_XIN_COLUMN_TYPE.equals(columnName)) {
                    map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.TYPE.getValue(), columnValue);
                    logger.info(SHI_XIN_COLUMN_TYPE + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.TYPE.getValue()));
                } else if (SHI_XIN_COLUMN_PROVINCE.equals(columnName)) {
                    map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.PROVINCE.getValue(), columnValue);
                    logger.info(SHI_XIN_COLUMN_PROVINCE + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.PROVINCE.getValue()));
                } else if (SHI_XIN_COLUMN_COURT.equals(columnName)) {
                    map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.COURT.getValue(), columnValue);
                    logger.info(SHI_XIN_COLUMN_COURT + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.COURT.getValue()));
                } else if (SHI_XIN_COLUMN_NAME.equals(columnName)) {
                    map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.NAME.getValue(), columnValue);
                    logger.info(SHI_XIN_COLUMN_NAME + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.NAME.getValue()));
                } else if (SHI_XIN_COLUMN_TAX_NO.equals(columnName)) {
                    map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.TAX_NO.getValue(), columnValue);
                    logger.info(SHI_XIN_COLUMN_TAX_NO + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.TAX_NO.getValue()));
                } else if (SHI_XIN_COLUMN_CODE.equals(columnName)) {
                    map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.CODE.getValue(), columnValue);
                    logger.info(SHI_XIN_COLUMN_CODE + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.CODE.getValue()));
                } else if (SHI_XIN_COLUMN_ADDR.equals(columnName)) {
                    map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.ADDR.getValue(), columnValue);
                    logger.info(SHI_XIN_COLUMN_ADDR + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.ADDR.getValue()));
                } else if (SHI_XIN_COLUMN_LEGAL_REP.equals(columnName)) {
                    map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.LEGAL_REP.getValue(), columnValue);
                    logger.info(SHI_XIN_COLUMN_LEGAL_REP + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.LEGAL_REP.getValue()));
                } else if (SHI_XIN_COLUMN_LEGAL_SEX.equals(columnName)) {
                    map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.LEGAL_SEX.getValue(), columnValue);
                    logger.info(SHI_XIN_COLUMN_LEGAL_SEX + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.LEGAL_SEX.getValue()));
                } else if (SHI_XIN_COLUMN_CERT_NAME.equals(columnName)) {
                    map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.CERT_NAME.getValue(), columnValue);
                    logger.info(SHI_XIN_COLUMN_CERT_NAME + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.CERT_NAME.getValue()));
                } else if (SHI_XIN_COLUMN_FINANCIAL_NAME.equals(columnName)) {
                    map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.FINANCIAL_NAME.getValue(), columnValue);
                    logger.info(SHI_XIN_COLUMN_FINANCIAL_NAME + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.FINANCIAL_NAME.getValue()));
                } else if (SHI_XIN_COLUMN_FINANCIAL_SEX.equals(columnName)) {
                    map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.FINANCIAL_SEX.getValue(), columnValue);
                    logger.info(SHI_XIN_COLUMN_FINANCIAL_SEX + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.FINANCIAL_SEX.getValue()));
                } else if (SHI_XIN_COLUMN_FINANCIAL_CERT_NAME.equals(columnName)) {
                    map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.FINANCIAL_CERT_NAME.getValue(),
                            columnValue);
                    logger.info(SHI_XIN_COLUMN_FINANCIAL_CERT_NAME + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.FINANCIAL_CERT_NAME.getValue()));
                } else if (SHI_XIN_COLUMN_INTER_INFO.equals(columnName)) {
                    map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.INTER_INFO.getValue(), columnValue);
                    logger.info(SHI_XIN_COLUMN_INTER_INFO + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.INTER_INFO.getValue()));
                } else if (SHI_XIN_COLUMN_CASE_NATURE.equals(columnName)) {
                    map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.CASE_NATURE.getValue(), columnValue);
                    logger.info(SHI_XIN_COLUMN_CASE_NATURE + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.CASE_NATURE.getValue()));
                } else if (SHI_XIN_COLUMN_ILLEGAL_FACTS.equals(columnName)) {
                    map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.ILLEGAL_FACTS.getValue(), columnValue);
                    logger.info(SHI_XIN_COLUMN_ILLEGAL_FACTS + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.ILLEGAL_FACTS.getValue()));
                } else if (SHI_XIN_COLUMN_PUNISH_INFO.equals(columnName)) {
                    map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.PUNISH_INFO.getValue(), columnValue);
                    logger.info(SHI_XIN_COLUMN_PUNISH_INFO + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.PUNISH_INFO.getValue()));
                } else if (SHI_XIN_COLUMN_PUBLISH_LEVEL.equals(columnName)) {
                    map.put(CreepersConstant.TCreepersTaxEvasionDetailColumn.PUBLISH_LEVEL.getValue(), columnValue);
                    logger.info(SHI_XIN_COLUMN_PUBLISH_LEVEL + SYMBOL_COLON_EN
                            + map.get(CreepersConstant.TCreepersTaxEvasionDetailColumn.PUBLISH_LEVEL.getValue()));
                } else {
                    logger.info(columnName + SYMBOL_COLON_CN + columnValue + "，该值暂未录入数据库");
                }
            }
            tCreepersTaxEvasionDetail.add(map);
        }
        page.putField(CreepersConstant.TableNamesCreditChina.T_CREEPERS_TAX_EVASION_DETAIL.getMapKey(),
                tCreepersTaxEvasionDetail);
    }

    public static void modifySpecNameValuePair(NameValuePair[] nameValuePairs, String key, String value) {
        if (null != nameValuePairs && nameValuePairs.length > 0 && StringUtils.isNoneBlank(key)
                && StringUtils.isNoneBlank(value)) {
            for (int i = 0; i < nameValuePairs.length; i++) {
                if (key.equals(nameValuePairs[i].getName())) {
                    nameValuePairs[i] = new BasicNameValuePair(key, value);
                }
            }
        }
    }

    public static NameValuePair[] copyAndModifyNameValuePair(NameValuePair[] nameValuePairs, String key, String value) {
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
        String url = "http://www.creditchina.gov.cn/credit_info_search?t=" + new Date().getTime();
        Request request = new Request(url);
        request.setMethod(HttpConstant.Method.POST);
        NameValuePair[] nameValuePair = new NameValuePair[10];
        nameValuePair[0] = new BasicNameValuePair("keyword", "");
        nameValuePair[1] = new BasicNameValuePair("searchtype", "0");
        nameValuePair[2] = new BasicNameValuePair("objectType", "2");
        nameValuePair[3] = new BasicNameValuePair("areas", "");
        nameValuePair[4] = new BasicNameValuePair("creditType", "4");
        nameValuePair[5] = new BasicNameValuePair("dataType", "1");
        nameValuePair[6] = new BasicNameValuePair("areaCode", "");
        nameValuePair[7] = new BasicNameValuePair("templateId", "6");
        nameValuePair[8] = new BasicNameValuePair("exact", "0");
        nameValuePair[9] = new BasicNameValuePair("page", "1");
        request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, nameValuePair);
        Spider.create(new TaxEvasionProcessor()).setDownloader(new HttpRequestDownloader()).thread(1)
                .addRequest(request).run();
    }
}
