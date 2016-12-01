package com.fosun.fc.projects.creepers.pageprocessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.downloader.SimulateLoginDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import com.fosun.fc.projects.creepers.utils.FileUtil;
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
 * Demo: 导游证信息
 * </p>
 * 
 * @author MaXin
 * @since 2016-10-31 11:38:57
 * @see
 */
@Component("touristGuideProcessor")
public class TouristGuideProcessor extends BaseCreepersListProcessor implements PageProcessor {

    private static final String SPILT_COLON = ":";

    private static final String REGEX_URL_ERROR_CAPTCHA = "http://daoyou-chaxun.cnta.gov.cn/single_info/selectlogin_1.asp";
    private static final String REGEX_URL_INFO_NOT_EXIST = "http://daoyou-chaxun.cnta.gov.cn/single_info/selectdyy.asp";

    private static final String REGEX_CONTENT_COLSPAN = "colspan";
    private static final String REGEX_CONTENT_BASE_INFO = "基本信息";
    private static final String REGEX_CONTENT_GUIDE_NO = "导游证号";
    private static final String REGEX_CONTENT_SEX = "性别";
    private static final String REGEX_CONTENT_QULIFY_NO = "资格证号";
    private static final String REGEX_CONTENT_GUIDE_LEVEL = "等级";
    private static final String REGEX_CONTENT_CARD_NO = "导游卡号";
    private static final String REGEX_CONTENT_EDUCATION = "学历";
    private static final String REGEX_CONTENT_CERT_NO = "身份证号";
    private static final String REGEX_CONTENT_LAN = "语种";
    private static final String REGEX_CONTENT_AREA = "区域名称";
    private static final String REGEX_CONTENT_PEOPLE = "民族";
    private static final String REGEX_CONTENT_CERT_DT = "发证日期";
    private static final String REGEX_CONTENT_SCORE = "分值";
    private static final String REGEX_CONTENT_PUBLISH_DT = "获惩日期";
    private static final String REGEX_CONTENT_PUBLISH_TYPE = "获惩类型";
    private static final String REGEX_CONTENT_AGENT_NAME = "旅&nbsp;行&nbsp;社";
    private static final String REGEX_CONTENT_PHONE = "联系电话";

    private static final String REGEX_CONTENT_ERROR_CAPTCHA = "验证码输入错误";
    private static final String REGEX_CONTENT_INFO_NOT_EXIST = "无此导游信息";

    private static final String REGEX_CLASS_TD_LEFT_5 = "td_left5";

    private static final String REGEX_STYLE_BACKGROUND_IMAGE_URL = "background-image=url";

    private static final String XPATH_CLASS_TD_00 = "//*[@class=\"td_00\"]";

    private static final String XPATH_ALLTEXT_FONT = "//font/allText()";
    private static final String XPATH_ALLTEXT = "//allText()";
    private static final String XPATH_DIV_CENTER = "/html/body/div";

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private Site site;

    public TouristGuideProcessor() {
        if (null == site) {
            site = Site.me().setDomain("daoyou-chaxun.cnta.gov.cn")
                    .setUserAgent(
                            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36")
                    .setAcceptStatCode(Sets.newHashSet(200, 302)).setSleepTime(5000);
        }

    }

    @Override
    public void process(Page page) {
        ResultItems resultItem = page.getResultItems();
        CreepersLoginParamDTO param = resultItem.get(BaseConstant.PARAM_DTO_KEY);
        int triedTimes = 1;
        if (page.getRequest().getExtras().containsKey(Request.CYCLE_TRIED_TIMES)) {
            triedTimes = (int) page.getRequest().getExtra(Request.CYCLE_TRIED_TIMES);
        }

        if (page.getUrl().toString().equals(param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL))) {
            page.addTargetRequest(param.getOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL));
            page.setSkip(true);
        } else if (page.getUrl().toString().equals(param.getOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL))) {
            Request request = CommonMethodUtils.buildDoLoginRequest(param);
            page.addTargetRequest(request);
            page.setSkip(true);
        } else if (page.getUrl().toString().equals(REGEX_URL_ERROR_CAPTCHA)
                && page.getHtml().xpath(XPATH_DIV_CENTER).regex(REGEX_CONTENT_ERROR_CAPTCHA).match()) {
            CommonMethodUtils.imageAnalyticalErrorHttp(param.getCaptchaId());
            logger.error("============== >>>   " + REGEX_CONTENT_ERROR_CAPTCHA);//
            Request request = new Request(param.getOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL));
            triedTimes++;
            if (triedTimes > getSite().getRetryTimes()) {
                param.setErrorInfo(REGEX_CONTENT_ERROR_CAPTCHA);
                param.setErrorPath(getClass().toString());
                creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
            } else {
                CommonMethodUtils.checkNeedRecycle(triedTimes, request);
                page.addTargetRequest(request);
            }
            page.addTargetRequest(param.getOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL));
            page.setSkip(true);
        } else if (page.getUrl().toString().equals(REGEX_URL_INFO_NOT_EXIST)
                && page.getHtml().xpath(XPATH_DIV_CENTER).regex(REGEX_CONTENT_INFO_NOT_EXIST).match()) {
            page.putField(CreepersConstant.TableNamesOthers.T_CREEPERS_TOUR_GUIDE.getMapKey(),
                    new HashMap<String, String>());
        } else {
            logger.info("======>>>开始爬取导游信息明细！");
            logger.debug("======>>>爬取页面完整信息开始：");
            logger.debug("url:" + page.getUrl().toString());
            logger.debug("content:\n" + page.getHtml().toString());
            logger.debug("======>>>爬取页面完整信息结束！");
            try {
                FileUtil.renameCaptchaImageFileName(param);
                String lastContent = StringUtils.EMPTY;
                String currentContent = StringUtils.EMPTY;
                Map<String, String> map = new HashMap<String, String>();
                List<Selectable> tdList = page.getHtml().xpath(XPATH_CLASS_TD_00).nodes();
                for (Selectable eachTd : tdList) {
                    logger.debug("eachTD:" + eachTd.toString());
                    if (eachTd.regex(REGEX_STYLE_BACKGROUND_IMAGE_URL).match()) {
                        String name = eachTd.xpath(XPATH_ALLTEXT_FONT).toString();
                        map.put(CreepersConstant.TCreepersTourGuideColumn.NAME.getValue(), name);
                        logger.info(CreepersConstant.TCreepersTourGuideColumn.NAME.getValue() + SPILT_COLON + name);
                    } else if (eachTd.regex(REGEX_CONTENT_BASE_INFO).match()
                            || eachTd.regex(REGEX_CLASS_TD_LEFT_5).match()
                            || eachTd.regex(REGEX_CONTENT_COLSPAN).match()) {
                        continue;
                    } else if (eachTd.regex(REGEX_CONTENT_GUIDE_NO).match()) {
                        // 导游证号
                        lastContent = REGEX_CONTENT_GUIDE_NO;
                    } else if (REGEX_CONTENT_GUIDE_NO.equals(lastContent)) {
                        lastContent = StringUtils.EMPTY;
                        currentContent = eachTd.xpath(XPATH_ALLTEXT).toString();
                        map.put(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue(), currentContent);
                        logger.info(CreepersConstant.TCreepersTourGuideColumn.GUIDE_NO.getValue()
                                + SPILT_COLON + currentContent);
                    } else if (eachTd.regex(REGEX_CONTENT_SEX).match()) {
                        // 性别
                        lastContent = REGEX_CONTENT_SEX;
                    } else if (REGEX_CONTENT_SEX.equals(lastContent)) {
                        lastContent = StringUtils.EMPTY;
                        currentContent = eachTd.xpath(XPATH_ALLTEXT).toString();
                        map.put(CreepersConstant.TCreepersTourGuideColumn.SEX.getValue(), currentContent);
                        logger.info(CreepersConstant.TCreepersTourGuideColumn.SEX.getValue()
                                + SPILT_COLON + currentContent);
                    } else if (eachTd.regex(REGEX_CONTENT_QULIFY_NO).match()) {
                        // 资格证号
                        lastContent = REGEX_CONTENT_QULIFY_NO;
                    } else if (REGEX_CONTENT_QULIFY_NO.equals(lastContent)) {
                        lastContent = StringUtils.EMPTY;
                        currentContent = eachTd.xpath(XPATH_ALLTEXT).toString();
                        map.put(CreepersConstant.TCreepersTourGuideColumn.QULIFY_NO.getValue(), currentContent);
                        logger.info(CreepersConstant.TCreepersTourGuideColumn.QULIFY_NO.getValue()
                                + SPILT_COLON + currentContent);
                    } else if (eachTd.regex(REGEX_CONTENT_GUIDE_LEVEL).match()) {
                        // 等级
                        lastContent = REGEX_CONTENT_GUIDE_LEVEL;
                    } else if (REGEX_CONTENT_GUIDE_LEVEL.equals(lastContent)) {
                        lastContent = StringUtils.EMPTY;
                        currentContent = eachTd.xpath(XPATH_ALLTEXT).toString();
                        map.put(CreepersConstant.TCreepersTourGuideColumn.GUIDE_LEVEL.getValue(), currentContent);
                        logger.info(CreepersConstant.TCreepersTourGuideColumn.GUIDE_LEVEL.getValue()
                                + SPILT_COLON + currentContent);
                    } else if (eachTd.regex(REGEX_CONTENT_CARD_NO).match()) {
                        // 导游卡号
                        lastContent = REGEX_CONTENT_CARD_NO;
                    } else if (REGEX_CONTENT_CARD_NO.equals(lastContent)) {
                        lastContent = StringUtils.EMPTY;
                        currentContent = eachTd.xpath(XPATH_ALLTEXT).toString();
                        map.put(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue(), currentContent);
                        logger.info(CreepersConstant.TCreepersTourGuideColumn.CARD_NO.getValue()
                                + SPILT_COLON + currentContent);
                    } else if (eachTd.regex(REGEX_CONTENT_EDUCATION).match()) {
                        // 学历
                        lastContent = REGEX_CONTENT_EDUCATION;
                    } else if (REGEX_CONTENT_EDUCATION.equals(lastContent)) {
                        lastContent = StringUtils.EMPTY;
                        currentContent = eachTd.xpath(XPATH_ALLTEXT).toString();
                        map.put(CreepersConstant.TCreepersTourGuideColumn.EDUCATION.getValue(), currentContent);
                        logger.info(CreepersConstant.TCreepersTourGuideColumn.EDUCATION.getValue()
                                + SPILT_COLON + currentContent);
                    } else if (eachTd.regex(REGEX_CONTENT_CERT_NO).match()) {
                        // 身份证号
                        lastContent = REGEX_CONTENT_CERT_NO;
                    } else if (REGEX_CONTENT_CERT_NO.equals(lastContent)) {
                        lastContent = StringUtils.EMPTY;
                        currentContent = eachTd.xpath(XPATH_ALLTEXT).toString();
                        map.put(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue(), currentContent);
                        logger.info(CreepersConstant.TCreepersTourGuideColumn.CERT_NO.getValue()
                                + SPILT_COLON + currentContent);
                    } else if (eachTd.regex(REGEX_CONTENT_LAN).match()) {
                        // 语种
                        lastContent = REGEX_CONTENT_LAN;
                    } else if (REGEX_CONTENT_LAN.equals(lastContent)) {
                        lastContent = StringUtils.EMPTY;
                        currentContent = eachTd.xpath(XPATH_ALLTEXT).toString();
                        map.put(CreepersConstant.TCreepersTourGuideColumn.LAN.getValue(), currentContent);
                        logger.info(CreepersConstant.TCreepersTourGuideColumn.LAN.getValue()
                                + SPILT_COLON + currentContent);
                    } else if (eachTd.regex(REGEX_CONTENT_AREA).match()) {
                        // 区域名称
                        lastContent = REGEX_CONTENT_AREA;
                    } else if (REGEX_CONTENT_AREA.equals(lastContent)) {
                        lastContent = StringUtils.EMPTY;
                        currentContent = eachTd.xpath(XPATH_ALLTEXT).toString();
                        map.put(CreepersConstant.TCreepersTourGuideColumn.AREA.getValue(), currentContent);
                        logger.info(CreepersConstant.TCreepersTourGuideColumn.AREA.getValue()
                                + SPILT_COLON + currentContent);
                    } else if (eachTd.regex(REGEX_CONTENT_PEOPLE).match()) {
                        // 民族
                        lastContent = REGEX_CONTENT_PEOPLE;
                    } else if (REGEX_CONTENT_PEOPLE.equals(lastContent)) {
                        lastContent = StringUtils.EMPTY;
                        currentContent = eachTd.xpath(XPATH_ALLTEXT).toString();
                        map.put(CreepersConstant.TCreepersTourGuideColumn.PEOPLE.getValue(), currentContent);
                        logger.info(CreepersConstant.TCreepersTourGuideColumn.PEOPLE.getValue()
                                + SPILT_COLON + currentContent);
                    } else if (eachTd.regex(REGEX_CONTENT_CERT_DT).match()) {
                        // 发证日期
                        lastContent = REGEX_CONTENT_CERT_DT;
                    } else if (REGEX_CONTENT_CERT_DT.equals(lastContent)) {
                        lastContent = StringUtils.EMPTY;
                        currentContent = eachTd.xpath(XPATH_ALLTEXT).toString();
                        map.put(CreepersConstant.TCreepersTourGuideColumn.CERT_DT.getValue(), currentContent);
                        logger.info(CreepersConstant.TCreepersTourGuideColumn.CERT_DT.getValue()
                                + SPILT_COLON + currentContent);
                    } else if (eachTd.regex(REGEX_CONTENT_SCORE).match()) {
                        // 分值
                        lastContent = REGEX_CONTENT_SCORE;
                    } else if (REGEX_CONTENT_SCORE.equals(lastContent)) {
                        lastContent = StringUtils.EMPTY;
                        currentContent = eachTd.xpath(XPATH_ALLTEXT).toString();
                        map.put(CreepersConstant.TCreepersTourGuideColumn.SCORE.getValue(), currentContent);
                        logger.info(CreepersConstant.TCreepersTourGuideColumn.SCORE.getValue()
                                + SPILT_COLON + currentContent);
                    } else if (eachTd.regex(REGEX_CONTENT_PUBLISH_DT).match()) {
                        // 获惩日期
                        lastContent = REGEX_CONTENT_PUBLISH_DT;
                    } else if (REGEX_CONTENT_PUBLISH_DT.equals(lastContent)) {
                        lastContent = StringUtils.EMPTY;
                        currentContent = eachTd.xpath(XPATH_ALLTEXT).toString();
                        map.put(CreepersConstant.TCreepersTourGuideColumn.PUBLISH_DT.getValue(), currentContent);
                        logger.info(CreepersConstant.TCreepersTourGuideColumn.PUBLISH_DT.getValue()
                                + SPILT_COLON + currentContent);
                    } else if (eachTd.regex(REGEX_CONTENT_PUBLISH_TYPE).match()) {
                        // 获惩类型
                        lastContent = REGEX_CONTENT_PUBLISH_TYPE;
                    } else if (REGEX_CONTENT_PUBLISH_TYPE.equals(lastContent)) {
                        lastContent = StringUtils.EMPTY;
                        currentContent = eachTd.xpath(XPATH_ALLTEXT).toString();
                        map.put(CreepersConstant.TCreepersTourGuideColumn.PUBLISH_TYPE.getValue(), currentContent);
                        logger.info(CreepersConstant.TCreepersTourGuideColumn.PUBLISH_TYPE.getValue()
                                + SPILT_COLON + currentContent);
                    } else if (eachTd.regex(REGEX_CONTENT_AGENT_NAME).match()) {
                        // 旅&nbsp;行&nbsp;社
                        lastContent = REGEX_CONTENT_AGENT_NAME;
                    } else if (REGEX_CONTENT_AGENT_NAME.equals(lastContent)) {
                        lastContent = StringUtils.EMPTY;
                        currentContent = eachTd.xpath(XPATH_ALLTEXT).toString();
                        map.put(CreepersConstant.TCreepersTourGuideColumn.AGENT_NAME.getValue(), currentContent);
                        logger.info(CreepersConstant.TCreepersTourGuideColumn.AGENT_NAME.getValue()
                                + SPILT_COLON + currentContent);
                    } else if (eachTd.regex(REGEX_CONTENT_PHONE).match()) {
                        // 联系电话
                        lastContent = REGEX_CONTENT_PHONE;
                    } else if (REGEX_CONTENT_PHONE.equals(lastContent)) {
                        lastContent = StringUtils.EMPTY;
                        currentContent = eachTd.xpath(XPATH_ALLTEXT).toString();
                        map.put(CreepersConstant.TCreepersTourGuideColumn.PHONE.getValue(), currentContent);
                        logger.info(CreepersConstant.TCreepersTourGuideColumn.PHONE.getValue()
                                + SPILT_COLON + currentContent);
                    }
                }
                page.putField(CreepersConstant.TableNamesOthers.T_CREEPERS_TOUR_GUIDE.getMapKey(), map);
                logger.info("导游信息明细爬取完毕。");
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("TouristGuideProcessor process error:", e);
                param.setErrorInfo(
                        "TouristGuideProcessor process error:" + e.getCause().getClass() + e.getCause().getMessage());
                param.setErrorPath(getClass().toString());
                if (StringUtils.isNotBlank(
                        param.getSearchKeyWord(CreepersConstant.TCreepersListTourGuideColumn.GUIDE_NO.getValue()))) {
                    param.setLoginName(
                            param.getSearchKeyWord(CreepersConstant.TCreepersListTourGuideColumn.GUIDE_NO.getValue()));
                } else {
                    param.setLoginName(
                            param.getSearchKeyWord(CreepersConstant.TCreepersListTourGuideColumn.CARD_NO.getValue()));
                }
                creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
                logger.error("======================>>>  TouristGuideProcessor.process end!");
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
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
        Spider.create(new TouristGuideProcessor()).setDownloader(new SimulateLoginDownloader().setParam(param))
                .addRequest(request).thread(1).runAsync();
    }
}
