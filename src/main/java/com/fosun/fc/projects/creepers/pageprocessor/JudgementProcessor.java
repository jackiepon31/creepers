package com.fosun.fc.projects.creepers.pageprocessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.downloader.AddMouseCheckSeleniumDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.pipeline.JudgementPipline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/***
 * 
 * <p>
 * 中国裁判文书网 使用Selenium做页面动态渲染。
 * </p>
 * 
 * @author MaXin 2016-5-11 15:50:12
 */
@Component("judgementProcessor")
public class JudgementProcessor extends BaseCreepersListProcessor implements PageProcessor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String PAGE_LIST = "pageList";
    private static String RESULT_LIST = "resultList";
    private Site site;

    @Override
    public void process(Page page) {

        // 单个内容解析完成。
        List<Page> pageList = page.getResultItems().get(PAGE_LIST);
        try {
            for (Page eachPage : pageList) {
                List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
                List<String> divList = eachPage.getHtml().xpath("//*[@id=\"resultList\"]//div[@class=\"dataItem\"]")
                        .all();//
                String merName = eachPage.getUrl().regex("%E5%85%A8%E6%96%87%E6%A3%80%E7%B4%A2:(.*)").toString();
                logger.info("divList.size:" + divList.size());
                for (int index = 1; index < divList.size() + 1; index++) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put(CreepersConstant.TCreepersJudgementColumn.MER_NAME.getValue(), merName);

                    map.put(CreepersConstant.TCreepersJudgementColumn.CASE_TITLE.getValue(),
                            eachPage.getHtml().xpath(
                                    "//*[@id=\"resultList\"]/div[" + index + "]/table/tbody/tr[1]/td/div/a/allText()")
                            .toString());
                    map.put(CreepersConstant.TCreepersJudgementColumn.DOC_ID.getValue(),
                            eachPage.getHtml()
                                    .xpath("//*[@id=\"resultList\"]/div[" + index + "]/table/tbody/tr[1]/td/div/a")
                                    .links().regex("DocID=(.*)&KeyWord=").toString());
                    logger.info(CreepersConstant.TCreepersJudgementColumn.CASE_TITLE.getValue()
                            + ":" + map.get(CreepersConstant.TCreepersJudgementColumn.CASE_TITLE.getValue()));
                    logger.info(CreepersConstant.TCreepersJudgementColumn.MER_NO.getValue()
                            + ":" + map.get(CreepersConstant.TCreepersJudgementColumn.MER_NO.getValue()));
                    logger.info(CreepersConstant.TCreepersJudgementColumn.DOC_ID.getValue()
                            + ":" + map.get(CreepersConstant.TCreepersJudgementColumn.DOC_ID.getValue()));
                    String values = eachPage.getHtml()
                            .xpath("//*[@id=\"resultList\"]/div[" + index + "]/table/tbody/tr[2]/td/div/text()")
                            .toString();
                    String[] stringArr = null;
                    if (values != null) {
                        stringArr = values.replaceAll("[ ]+", "|").split("\\|");
                        for (int i = 0; i < stringArr.length; i++) {
                            String string = stringArr[i];
                            // {
                            if (string != null && string.endsWith("号")) {
                                map.put(CreepersConstant.TCreepersJudgementColumn.CASE_NO.getValue(), string);
                                logger.info(CreepersConstant.TCreepersJudgementColumn.CASE_NO.getValue()
                                        + ":" + map.get(CreepersConstant.TCreepersJudgementColumn.CASE_NO.getValue()));
                            } else if (string != null && string.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")) {
                                map.put(CreepersConstant.TCreepersJudgementColumn.CASE_DT.getValue(), string);
                                logger.info(CreepersConstant.TCreepersJudgementColumn.CASE_DT.getValue()
                                        + ":" + map.get(CreepersConstant.TCreepersJudgementColumn.CASE_DT.getValue()));
                            } else if (string != null && string.matches("[\\u4e00-\\u9fa5]+")) {
                                map.put(CreepersConstant.TCreepersJudgementColumn.COURT.getValue(), string);
                                logger.info(CreepersConstant.TCreepersJudgementColumn.COURT.getValue()
                                        + ":" + map.get(CreepersConstant.TCreepersJudgementColumn.COURT.getValue()));
                            }
                        }
                    }
                    listMap.add(map);
                }
                eachPage.putField(RESULT_LIST, listMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("JudgementProcessor process error:", e);
            CreepersParamDTO param = page.getResultItems().get(BaseConstant.PARAM_DTO_KEY);
            param.setErrorInfo(
                    "JudgementProcessor process error:" + e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorPath(getClass().toString());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
        }
    }

    @Override
    public Site getSite() {
        if (null == site) {
            site = Site.me().setDomain("wenshu.court.gov.cn").setRetryTimes(3).setCycleRetryTimes(3).setTimeOut(10);
        }
        return site;
    }

    public static void main(String[] args) throws IOException {
        Spider.create(new JudgementProcessor()).thread(1).addPipeline(new JudgementPipline())
                .setDownloader(
                        new AddMouseCheckSeleniumDownloader(BaseConstant.SeleniumWebDriverPath.CHROME_PATH.getValue()))
                .addUrl("http://wenshu.court.gov.cn/list/list/?sorttype=1&conditions=searchWord+QWJS+++%E5%85%A8%E6%96%87%E6%A3%80%E7%B4%A2:山富纸业（昆山）有限公司")

        .runAsync();

    }
}
