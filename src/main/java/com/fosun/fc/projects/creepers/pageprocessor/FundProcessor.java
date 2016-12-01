package com.fosun.fc.projects.creepers.pageprocessor;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant.TableNamesFund;
import com.fosun.fc.projects.creepers.downloader.SimulateLoginDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.pipeline.FundPipline;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import com.google.common.collect.Sets;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant;

/**
 * 
 * <p>
 * 上海市公积金
 * </p>
 * 
 * @author pengyk
 * @since 2016年9月06日
 * @see
 */
@SuppressWarnings("unused")
@Component("fundProcessor")
public class FundProcessor implements PageProcessor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;

    private Site site;

    private static final String HTML_CONTENT = "htmlContent";
    private static final String REGEX_TAB_BASIC = "https://persons.shgjj.com/MainServlet?ID=1";
    private static final String REGEX_TAB_BASIC_DETAIL = "https://persons.shgjj.com/MainServlet?ID=11";
    private static final String REGEX_TAB_EXTRA = "https://persons.shgjj.com/MainServlet?ID=3";
    private static final String REGEX_TAB_EXTRA_DETAIL = "https://persons.shgjj.com/MainServlet?ID=31";
    private static final String REGEX_HTTPS_COOKIES = "https://persons.shgjj.com/cookies.jsp\\?sid=\\w+";
    private static final String REGEX_HTTP_COOKIES = "http://netloan.shgjj.com/cookies.jsp\\?sid=\\w+";
    private static final String REGEX_HTTP_CROSS_DOMAIN_SETCOOKIE = "http://bbs.shgjj.com/cross_domain_setcookie.php\\?sid=\\w+";
    private static final String REGEX_HTTP_WSWQ_COOKIES = "http://person.shgjj.com/wswq/cookies.jsp\\?sid=\\w+";
    private static final String XPATH_FUND_TABLE_BY_COLOR = "//table[@bordercolor='#999999']";
    private static final String XPATH_FUND_TABLE_DETAIL_BY_COLOR = "//table[@bordercolor='#666666']";
    private static final String XPATH_TR_BY_COLOR = "//tr[@bgcolor='#f3f1e4']";
    private static final String XPATH_ERROR_TIP = "//form[@name='loginform']//table[@width='1000']//table[@width='774']//font[@color='#CC0000']";
    private static final String XPATH_TBODY = "//tbody";
    private static final String XPATH_ALLTEXT = "//allText()";
    private static final String XPATH_TEXT = "//text()";
    private static final String XPATH_TR = "//tr";
    private static final String XPATH_TD = "//td";
    private static final String XPATH_TD_1 = "//td[1]";
    private static final String XPATH_TD_2 = "//td[2]";
    private static final String XPATH_TD_3 = "//td[3]";
    private static final String XPATH_TD_4 = "//td[4]";
    private static final String XPATH_TD_5 = "//td[5]";
    private static final String XPATH_TD_6 = "//td[6]";
    private static final String XPATH_TD_7 = "//td[7]";
    private static final String XPATH_TD_8 = "//td[8]";
    private static final String XPATH_TD_9 = "//td[9]";
    private static final String XPATH_TD_10 = "//td[10]";

    private static final String CONTENT_NAME = "姓 名";
    private static final String CONTENT_ACCOUNT_DT = "开户日期";
    private static final String CONTENT_ACCOUNT_NO = "公积金账号";
    private static final String CONTENT_UNIT = "所属单位";
    private static final String CONTENT_END_DT = "末次缴存年月";
    private static final String CONTENT_SUM_AMOUNT = "账户余额";
    private static final String CONTENT_MONTHLY_AMOUNT = "月缴存额";
    private static final String CONTENT_ACCOUNT_STATUS = "当前账户状态";
    private static final String CONTENT_MOBILE = "绑定手机号";
    private static final String CONTENT_CERTIFICATE_STATUS = "实名认证状态";
    private static final String CONTENT_EMPTY = "";
    private static final String SPILT_COLON = ":";

    public FundProcessor() {
        if (null == site) {
            site = Site.me().setDomain("persons.shgjj.com")
                    .setUserAgent(
                            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.21 Safari/537.36")
                    .setAcceptStatCode(Sets.newHashSet(200, 302)).setRetryTimes(5);
        }
    }

    @Override
    public void process(Page page) {
        try {
            logger.info("======================>>>  FundProcessor.process end!");
            ResultItems resultItems = page.getResultItems();
            CreepersLoginParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
            logger.info("loginName:"+param.getLoginName());

            int triedTimes = 1;
            if (page.getRequest().getExtras().containsKey(Request.CYCLE_TRIED_TIMES)) {
                triedTimes = (int) page.getRequest().getExtra(Request.CYCLE_TRIED_TIMES);
            }

            logger.info("url:" + page.getUrl().toString());
            if (param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL).equals(page.getUrl().toString())) {
                page.addTargetRequest(param.getOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL));
                page.setSkip(true);
            } else if (param.getOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL).equals(page.getUrl().toString())) {
                Request request = CommonMethodUtils.buildDoLoginRequest(param);
                CommonMethodUtils.checkNeedRecycle(triedTimes, request);
                page.addTargetRequest(request);
                page.setSkip(true);
            } else if (param.getOrderUrl(BaseConstant.OrderUrlKey.DO_LOGIN_URL).equals(page.getUrl().toString())) {
                if (page.getHtml().xpath("//*[@id=\"myform\"]").match()) {
                    Selectable myformSelectable = page.getHtml().xpath("//*[@id=\"myform\"]");
                    List<Selectable> paramList = myformSelectable.xpath("//form/input").nodes();
                    Map<String, String> map = new HashMap<String, String>();
                    for (Selectable input : paramList) {
                        map.put(input.xpath("//input/@name").toString(), input.xpath("//input/@value").toString());
                    }
                    String url = myformSelectable.xpath("//form/@action").toString();
                    Request request = CommonMethodUtils.buildDefaultRequest(map, url);
                    page.addTargetRequest(request);
                } else if (page.getHtml().xpath(XPATH_ERROR_TIP + XPATH_ALLTEXT).toString().contains("验证码")
                        || page.getHtml().xpath(XPATH_ERROR_TIP + XPATH_ALLTEXT).toString().equals("")) {
                    logger.error(
                            "============== >>>   " + page.getHtml().xpath(XPATH_ERROR_TIP + XPATH_ALLTEXT).toString());//
                    // param.setErrorInfo(page.getHtml().xpath(XPATH_ERROR_TIP +
                    // XPATH_ALLTEXT).toString());
                    // param.setErrorPath(getClass().toString());
                    // creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
                    Request request = new Request(param.getOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL));
                    triedTimes++;
                    if (triedTimes > getSite().getRetryTimes()) {
                        param.setErrorInfo(page.getHtml().xpath(XPATH_ERROR_TIP + XPATH_ALLTEXT).toString());
                        param.setErrorPath(getClass().toString());
                        creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
                    } else {
                        CommonMethodUtils.checkNeedRecycle(triedTimes, request);
                        page.addTargetRequest(request);
                    }
                } else {
                    param.setErrorInfo(page.getHtml().xpath(XPATH_ERROR_TIP + XPATH_ALLTEXT).toString());
                    param.setErrorPath(getClass().toString());
                    creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
                }
                page.setSkip(true);
            } else if ("http://bbs.shgjj.com/sso/sso.php?url=https://persons.shgjj.com/MainServlet?ID=1"
                    .equals(page.getUrl().toString())) {
                List<Selectable> srcList = page.getHtml().xpath("//body//div//script//@src").nodes();
                for (Selectable sel : srcList) {
                    if ("".equals(sel.toString().trim()))
                        continue;
                    Request request = new Request(sel.toString());
                    page.addTargetRequest(request);
                }
                page.setSkip(true);
            } else if (page.getUrl().regex(REGEX_HTTP_WSWQ_COOKIES).match()) {
                List<String> targetUrlList = param.getTargetUrlList();
                for (String targetUrl : targetUrlList) {
                    page.addTargetRequest(targetUrl);
                }
                page.setSkip(true);
            } else if (param.getTargetUrlList().contains(page.getUrl().toString())) {
                // 初始化超链接信息
                TableNamesFund[] getNames = CreepersConstant.TableNamesFund.values();
                for (TableNamesFund TableNamesFund : getNames) {
                    Object obj;
                    if (TableNamesFund.isList()) {
                        obj = new ArrayList<Map<String, Object>>();
                    } else {
                        obj = new HashMap<String, Object>();
                    }
                    page.putField(TableNamesFund.getMapKey(), obj);
                }
                if (REGEX_TAB_BASIC.equals(page.getUrl().toString())) {
                    logger.debug("=====>>>公积金基本信息爬取开始!");
                    if (page.getHtml().xpath(XPATH_FUND_TABLE_BY_COLOR + XPATH_TR).nodes().size() > 0)
                        methodFundBasic(page);
                    logger.debug("=====>>>公积金基本信息爬取结束!");
                } else if (REGEX_TAB_BASIC_DETAIL.equals(page.getUrl().toString())) {
                    logger.debug("=====>>>公积金明细信息爬取开始!");
                    if (page.getHtml().xpath(XPATH_FUND_TABLE_DETAIL_BY_COLOR + XPATH_TBODY + XPATH_TR_BY_COLOR).nodes()
                            .size() > 0)
                        methodFundBasicDetail(page);
                    logger.debug("=====>>>公积金明细信息爬取结束!");
                } else if (REGEX_TAB_EXTRA.equals(page.getUrl().toString())) {
                    logger.debug("=====>>>补充公积金信息爬取开始!");
                    if (page.getHtml().xpath(XPATH_FUND_TABLE_BY_COLOR + XPATH_TR).nodes().size() > 0)
                        methodFundExtra(page);
                    logger.debug("=====>>>补充公积金信息爬取结束!");
                } else if (REGEX_TAB_EXTRA_DETAIL.equals(page.getUrl().toString())) {
                    logger.debug("=====>>>补充公积金明细信息爬取开始!");
                    if (page.getHtml().xpath(XPATH_FUND_TABLE_DETAIL_BY_COLOR + XPATH_TBODY + XPATH_TR_BY_COLOR).nodes()
                            .size() > 0)
                        methodFundExtraDetail(page);
                    logger.debug("=====>>>补充公积金明细信息爬取结束!");
                }
            } else {
                page.setSkip(true);
            }
            logger.info("======================>>>  FundProcessor.process end!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("FundProcessor process error:", e);
            CreepersLoginParamDTO param = page.getResultItems().get(BaseConstant.PARAM_DTO_KEY);
            param.setErrorInfo("FundProcessor process error:" + e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorPath(getClass().toString());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
            logger.error("======================>>>  FundProcessor.process end!");
        }
    }

    private void initPageUrl(Page page) {
        List<String> urlList = new ArrayList<String>();
        urlList.add(REGEX_TAB_BASIC);
        urlList.add(REGEX_TAB_BASIC_DETAIL);
        urlList.add(REGEX_TAB_EXTRA);
        urlList.add(REGEX_TAB_EXTRA_DETAIL);
        page.addTargetRequests(urlList);
    }

    /**
     * 
     * <p>
     * description: 公积金基本信息爬取
     * </p>
     * 
     * @param page
     * @author Pengyk
     * @see 2016-7-12 13:04:31
     */
    private void methodFundBasic(Page page) {
        ResultItems resultItems = page.getResultItems();
        CreepersLoginParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        List<Selectable> tdList = page.getHtml().xpath(XPATH_FUND_TABLE_BY_COLOR + XPATH_TBODY + XPATH_TD).nodes();
        Map<String, String> map = resultItems.get(CreepersConstant.TableNamesFund.T_CREEPERS_FUND_BASIC.getMapKey());
        // 用户名
        map.put(CreepersConstant.TCreepersFundBasicColumn.LOGIN_NAME.getValue(), param.getLoginName());
        logger.info(
                CreepersConstant.TCreepersFundBasicColumn.LOGIN_NAME.getValue() + SPILT_COLON + param.getLoginName());
        // 密码
        map.put(CreepersConstant.TCreepersFundBasicColumn.PASSWORD.getValue(), param.getPassword());
        logger.info(CreepersConstant.TCreepersFundBasicColumn.PASSWORD.getValue() + SPILT_COLON + param.getPassword());
        String lastContent = CONTENT_EMPTY;
        for (Selectable tdSel : tdList) {
            if (tdSel.regex("colspan=\"2\"").match())
                continue;
            String currentContent = tdSel.xpath(XPATH_ALLTEXT).toString().trim();
            logger.info("currentContent:" + currentContent);
            if (CONTENT_NAME.equals(currentContent)) {
                lastContent = CONTENT_NAME;
                continue;
            } else if (CONTENT_NAME.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 姓名
                map.put(CreepersConstant.TCreepersFundBasicColumn.NAME.getValue(),
                        currentContent.substring(0, currentContent.indexOf(">")));
                logger.info(CreepersConstant.TCreepersFundBasicColumn.NAME.getValue() + SPILT_COLON
                        + currentContent.substring(0, currentContent.indexOf(">")));
            } else if (CONTENT_ACCOUNT_DT.equals(currentContent)) {
                lastContent = CONTENT_ACCOUNT_DT;
                continue;
            } else if (CONTENT_ACCOUNT_DT.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 开户日期
                map.put(CreepersConstant.TCreepersFundBasicColumn.ACCOUNT_DT.getValue(), currentContent);
                logger.info(
                        CreepersConstant.TCreepersFundBasicColumn.ACCOUNT_DT.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_ACCOUNT_NO.equals(currentContent)) {
                lastContent = CONTENT_ACCOUNT_NO;
                continue;
            } else if (CONTENT_ACCOUNT_NO.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 公积金账号
                map.put(CreepersConstant.TCreepersFundBasicColumn.ACCOUNT_NO.getValue(), currentContent);
                logger.info(
                        CreepersConstant.TCreepersFundBasicColumn.ACCOUNT_NO.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_UNIT.equals(currentContent)) {
                lastContent = CONTENT_UNIT;
                continue;
            } else if (CONTENT_UNIT.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 所属单位
                map.put(CreepersConstant.TCreepersFundBasicColumn.UNIT.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersFundBasicColumn.UNIT.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_END_DT.equals(currentContent)) {
                lastContent = CONTENT_END_DT;
                continue;
            } else if (CONTENT_END_DT.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 末次缴存年月
                map.put(CreepersConstant.TCreepersFundBasicColumn.END_DT.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersFundBasicColumn.END_DT.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_SUM_AMOUNT.equals(currentContent)) {
                lastContent = CONTENT_SUM_AMOUNT;
                continue;
            } else if (CONTENT_SUM_AMOUNT.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 账户余额
                map.put(CreepersConstant.TCreepersFundBasicColumn.SUM_AMOUNT.getValue(),
                        currentContent.substring(0, currentContent.indexOf("元")));
                logger.info(CreepersConstant.TCreepersFundBasicColumn.SUM_AMOUNT.getValue() + SPILT_COLON
                        + currentContent.substring(0, currentContent.indexOf("元")));
            } else if (CONTENT_MONTHLY_AMOUNT.equals(currentContent)) {
                lastContent = CONTENT_MONTHLY_AMOUNT;
                continue;
            } else if (CONTENT_MONTHLY_AMOUNT.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 月缴存额
                map.put(CreepersConstant.TCreepersFundBasicColumn.MONTHLY_AMOUNT.getValue(),
                        currentContent.substring(0, currentContent.indexOf("元")));
                logger.info(CreepersConstant.TCreepersFundBasicColumn.MONTHLY_AMOUNT.getValue() + SPILT_COLON
                        + currentContent.substring(0, currentContent.indexOf("元")));
            } else if (CONTENT_ACCOUNT_STATUS.equals(currentContent)) {
                lastContent = CONTENT_ACCOUNT_STATUS;
                continue;
            } else if (CONTENT_ACCOUNT_STATUS.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 当前账户状态
                map.put(CreepersConstant.TCreepersFundBasicColumn.ACCOUNT_STATUS.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersFundBasicColumn.ACCOUNT_STATUS.getValue() + SPILT_COLON
                        + currentContent);
            } else if (CONTENT_MOBILE.equals(currentContent)) {
                lastContent = CONTENT_MOBILE;
                continue;
            } else if (CONTENT_MOBILE.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 绑定手机号
                map.put(CreepersConstant.TCreepersFundBasicColumn.MOBILE.getValue(),
                        currentContent.substring(0, currentContent.indexOf("【")));
                logger.info(CreepersConstant.TCreepersFundBasicColumn.MOBILE.getValue() + SPILT_COLON
                        + currentContent.substring(0, currentContent.indexOf("【")));
            } else if (CONTENT_CERTIFICATE_STATUS.equals(currentContent)) {
                lastContent = CONTENT_CERTIFICATE_STATUS;
                continue;
            } else if (CONTENT_CERTIFICATE_STATUS.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 实名认证状态
                map.put(CreepersConstant.TCreepersFundBasicColumn.CERTIFICATE_STATUS.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersFundBasicColumn.CERTIFICATE_STATUS.getValue() + SPILT_COLON
                        + currentContent);
            }
        }
    }

    /**
     * 
     * <p>
     * description: 公积金明细信息爬取
     * </p>
     * 
     * @param page
     * @author Pengyk
     * @throws Exception
     * @see 2016-7-12 13:04:31
     */
    private void methodFundBasicDetail(Page page) throws Exception {
        ResultItems resultItems = page.getResultItems();
        CreepersLoginParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        List<Selectable> trList = page.getHtml()
                .xpath(XPATH_FUND_TABLE_DETAIL_BY_COLOR + XPATH_TBODY + XPATH_TR_BY_COLOR).nodes();
        List<Map<String, String>> mapList = resultItems
                .get(CreepersConstant.TableNamesFund.T_CREEPERS_FUND_BASIC_DETAIL.getMapKey());
        for (Selectable trSel : trList) {
            Map<String, String> map = new HashMap<String, String>();
            // 登录名，用于和其他表关联
            map.put(CreepersConstant.TCreepersFundBasicDetailColumn.LOGIN_NAME.getValue(), param.getLoginName());
            logger.info(CreepersConstant.TCreepersFundBasicDetailColumn.LOGIN_NAME.getValue() + SPILT_COLON
                    + param.getLoginName());
            // 日期
            String operationDt = trSel.xpath(XPATH_TD_1 + XPATH_ALLTEXT).toString().trim();
            map.put(CreepersConstant.TCreepersFundBasicDetailColumn.OPERATION_DT.getValue(), operationDt);
            logger.info(CreepersConstant.TCreepersFundBasicDetailColumn.OPERATION_DT.getValue() + SPILT_COLON
                    + operationDt);
            // 单位名称
            String unit = trSel.xpath(XPATH_TD_2 + XPATH_ALLTEXT).toString().trim();
            map.put(CreepersConstant.TCreepersFundBasicDetailColumn.UNIT.getValue(), unit);
            logger.info(CreepersConstant.TCreepersFundBasicDetailColumn.UNIT.getValue() + SPILT_COLON + unit);
            // 金额
            String amount = trSel.xpath(XPATH_TD_3 + XPATH_ALLTEXT).toString().trim();
            map.put(CreepersConstant.TCreepersFundBasicDetailColumn.AMOUNT.getValue(), amount);
            logger.info(CreepersConstant.TCreepersFundBasicDetailColumn.AMOUNT.getValue() + SPILT_COLON + amount);
            // 业务描述
            String operationDesc = trSel.xpath(XPATH_TD_4 + XPATH_ALLTEXT).toString().trim();
            map.put(CreepersConstant.TCreepersFundBasicDetailColumn.OPERATION_DESC.getValue(), operationDesc);
            logger.info(CreepersConstant.TCreepersFundBasicDetailColumn.OPERATION_DESC.getValue() + SPILT_COLON
                    + operationDesc);
            // 业务原因
            byte bytes[] = { (byte) 0xC2, (byte) 0xA0 };
            String UTFSpace = new String(bytes, "utf-8");
            String operationReason = trSel.xpath(XPATH_TD_5 + XPATH_ALLTEXT).toString().replace(UTFSpace, "").trim();
            map.put(CreepersConstant.TCreepersFundBasicDetailColumn.OPERATION_REASON.getValue(), operationReason);
            logger.info(CreepersConstant.TCreepersFundBasicDetailColumn.OPERATION_REASON.getValue() + SPILT_COLON
                    + operationReason);
            mapList.add(map);
        }
    }

    /**
     * 
     * <p>
     * description: 补充公积金基本信息爬取
     * </p>
     * 
     * @param page
     * @author Pengyk
     * @see 2016-7-12 13:04:31
     */
    private void methodFundExtra(Page page) {
        ResultItems resultItems = page.getResultItems();
        CreepersLoginParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        List<Selectable> tdList = page.getHtml().xpath(XPATH_FUND_TABLE_BY_COLOR + XPATH_TBODY + XPATH_TD).nodes();
        Map<String, String> map = resultItems.get(CreepersConstant.TableNamesFund.T_CREEPERS_FUND_EXTRA.getMapKey());
        // 登录名，用于和其他表关联
        map.put(CreepersConstant.TCreepersFundExtraColumn.LOGIN_NAME.getValue(), param.getLoginName());
        logger.info(
                CreepersConstant.TCreepersFundExtraColumn.LOGIN_NAME.getValue() + SPILT_COLON + param.getLoginName());
        String lastContent = CONTENT_EMPTY;
        for (Selectable tdSel : tdList) {
            if (tdSel.regex("colspan=\"2\"").match())
                continue;
            String currentContent = tdSel.xpath(XPATH_ALLTEXT).toString().trim();
            logger.info("currentContent:" + currentContent);
            if (CONTENT_NAME.equals(currentContent)) {
                lastContent = CONTENT_NAME;
                continue;
            } else if (CONTENT_NAME.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 姓名
                map.put(CreepersConstant.TCreepersFundExtraColumn.NAME.getValue(),
                        currentContent.substring(0, currentContent.indexOf(">")));
                logger.info(CreepersConstant.TCreepersFundExtraColumn.NAME.getValue() + SPILT_COLON
                        + currentContent.substring(0, currentContent.indexOf(">")));
            } else if (CONTENT_ACCOUNT_DT.equals(currentContent)) {
                lastContent = CONTENT_ACCOUNT_DT;
                continue;
            } else if (CONTENT_ACCOUNT_DT.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 开户日期
                map.put(CreepersConstant.TCreepersFundExtraColumn.ACCOUNT_DT.getValue(), currentContent);
                logger.info(
                        CreepersConstant.TCreepersFundExtraColumn.ACCOUNT_DT.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_ACCOUNT_NO.equals(currentContent)) {
                lastContent = CONTENT_ACCOUNT_NO;
                continue;
            } else if (CONTENT_ACCOUNT_NO.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 公积金账号
                map.put(CreepersConstant.TCreepersFundExtraColumn.ACCOUNT_NO.getValue(), currentContent);
                logger.info(
                        CreepersConstant.TCreepersFundExtraColumn.ACCOUNT_NO.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_UNIT.equals(currentContent)) {
                lastContent = CONTENT_UNIT;
                continue;
            } else if (CONTENT_UNIT.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 所属单位
                map.put(CreepersConstant.TCreepersFundExtraColumn.UNIT.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersFundExtraColumn.UNIT.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_END_DT.equals(currentContent)) {
                lastContent = CONTENT_END_DT;
                continue;
            } else if (CONTENT_END_DT.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 末次缴存年月
                map.put(CreepersConstant.TCreepersFundExtraColumn.END_DT.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersFundExtraColumn.END_DT.getValue() + SPILT_COLON + currentContent);
            } else if (CONTENT_SUM_AMOUNT.equals(currentContent)) {
                lastContent = CONTENT_SUM_AMOUNT;
                continue;
            } else if (CONTENT_SUM_AMOUNT.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 账号余额
                map.put(CreepersConstant.TCreepersFundExtraColumn.SUM_AMOUNT.getValue(),
                        currentContent.substring(0, currentContent.indexOf("元")));
                logger.info(CreepersConstant.TCreepersFundExtraColumn.SUM_AMOUNT.getValue() + SPILT_COLON
                        + currentContent.substring(0, currentContent.indexOf("元")));
            } else if (CONTENT_MONTHLY_AMOUNT.equals(currentContent)) {
                lastContent = CONTENT_MONTHLY_AMOUNT;
                continue;
            } else if (CONTENT_MONTHLY_AMOUNT.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 月缴存额
                map.put(CreepersConstant.TCreepersFundExtraColumn.MONTHLY_AMOUNT.getValue(),
                        currentContent.substring(0, currentContent.indexOf("元")));
                logger.info(CreepersConstant.TCreepersFundExtraColumn.MONTHLY_AMOUNT.getValue() + SPILT_COLON
                        + currentContent.substring(0, currentContent.indexOf("元")));
            } else if (CONTENT_ACCOUNT_STATUS.equals(currentContent)) {
                lastContent = CONTENT_ACCOUNT_STATUS;
                continue;
            } else if (CONTENT_ACCOUNT_STATUS.equals(lastContent)) {
                lastContent = CONTENT_EMPTY;
                // 当前账户状态
                map.put(CreepersConstant.TCreepersFundExtraColumn.ACCOUNT_STATUS.getValue(), currentContent);
                logger.info(CreepersConstant.TCreepersFundExtraColumn.ACCOUNT_STATUS.getValue() + SPILT_COLON
                        + currentContent);
            }
        }
    }

    /**
     * 
     * <p>
     * description: 补充公积金基本信息爬取
     * </p>
     * 
     * @param page
     * @author Pengyk
     * @throws Exception
     * @see 2016-7-12 13:04:31
     */
    private void methodFundExtraDetail(Page page) throws Exception {
        ResultItems resultItems = page.getResultItems();
        CreepersLoginParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        List<Selectable> trList = page.getHtml()
                .xpath(XPATH_FUND_TABLE_DETAIL_BY_COLOR + XPATH_TBODY + XPATH_TR_BY_COLOR).nodes();
        List<Map<String, String>> mapList = resultItems
                .get(CreepersConstant.TableNamesFund.T_CREEPERS_FUND_EXTRA_DETAIL.getMapKey());
        for (Selectable trSel : trList) {
            Map<String, String> map = new HashMap<String, String>();
            // 登录名，用于和其他表关联
            map.put(CreepersConstant.TCreepersFundExtraDetailColumn.LOGIN_NAME.getValue(), param.getLoginName());
            logger.info(CreepersConstant.TCreepersFundExtraDetailColumn.LOGIN_NAME.getValue() + SPILT_COLON
                    + param.getLoginName());
            // 日期
            String operationDt = trSel.xpath(XPATH_TD_1 + XPATH_ALLTEXT).toString().trim();
            map.put(CreepersConstant.TCreepersFundExtraDetailColumn.OPERATION_DT.getValue(), operationDt);
            logger.info(CreepersConstant.TCreepersFundExtraDetailColumn.OPERATION_DT.getValue() + SPILT_COLON
                    + operationDt);
            // 单位名称
            String unit = trSel.xpath(XPATH_TD_2 + XPATH_ALLTEXT).toString().trim();
            map.put(CreepersConstant.TCreepersFundExtraDetailColumn.UNIT.getValue(), unit);
            logger.info(CreepersConstant.TCreepersFundExtraDetailColumn.UNIT.getValue() + SPILT_COLON + unit);
            // 金额
            String amount = trSel.xpath(XPATH_TD_3 + XPATH_ALLTEXT).toString().trim();
            map.put(CreepersConstant.TCreepersFundExtraDetailColumn.AMOUNT.getValue(), amount);
            logger.info(CreepersConstant.TCreepersFundExtraDetailColumn.AMOUNT.getValue() + SPILT_COLON + amount);
            // 业务描述
            String operationDesc = trSel.xpath(XPATH_TD_4 + XPATH_ALLTEXT).toString().trim();
            map.put(CreepersConstant.TCreepersFundExtraDetailColumn.OPERATION_DESC.getValue(), operationDesc);
            logger.info(CreepersConstant.TCreepersFundExtraDetailColumn.OPERATION_DESC.getValue() + SPILT_COLON
                    + operationDesc);
            // 业务原因
            byte bytes[] = { (byte) 0xC2, (byte) 0xA0 };
            String UTFSpace = new String(bytes, "utf-8");
            String operationReason = trSel.xpath(XPATH_TD_5 + XPATH_ALLTEXT).toString().replace(UTFSpace, "").trim();
            map.put(CreepersConstant.TCreepersFundExtraDetailColumn.OPERATION_REASON.getValue(), operationReason);
            logger.info(CreepersConstant.TCreepersFundExtraDetailColumn.OPERATION_REASON.getValue() + SPILT_COLON
                    + operationReason);
            mapList.add(map);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        CreepersLoginParamDTO param = new CreepersLoginParamDTO();
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, "https://persons.shgjj.com/");
        param.putOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL, "https://persons.shgjj.com/VerifyImageServlet");
        param.putOrderUrl(BaseConstant.OrderUrlKey.DO_LOGIN_URL, "https://persons.shgjj.com/SsoLogin");
        param.setCaptchaKey("imagecode");
        param.setLoginNameKey("username");
        param.setPasswordKey("password");
        param.setLoginName("lizhanping");
        param.setPassword("123456");
        param.putTargetUrlList("https://persons.shgjj.com/MainServlet?ID=1");
        param.putTargetUrlList("https://persons.shgjj.com/MainServlet?ID=11");
        param.putTargetUrlList("https://persons.shgjj.com/MainServlet?ID=3");
        param.putTargetUrlList("https://persons.shgjj.com/MainServlet?ID=31");
        param.putNameValuePair("SUBMIT.x", "24");
        param.putNameValuePair("SUBMIT.y", "8");
        Request request = CommonMethodUtils.buildIndexRequest(param);
        Spider.create(new FundProcessor()).setDownloader(new SimulateLoginDownloader().setParam(param))
                .addPipeline(new FundPipline()).addRequest(request).thread(1).run();
        // String str = "李占平 >>>住房公积金本年度账户明细";
        // System.out.println(str.substring(0, str.indexOf(" ")));
    }
}
