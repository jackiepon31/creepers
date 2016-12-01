package com.fosun.fc.projects.creepers.pageprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.downloader.SeleniumDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.pipeline.BusinessInfoPipline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 
 * <p>
 * 发明专利
 * </p>
 * 
 * @author MaXin
 * @since 2016年6月24日
 * @see
 */
@Component("patentProcessor")
public class PatentProcessor extends BaseCreepersListProcessor implements PageProcessor {

    private Site site;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public PatentProcessor() {
        if (null == site) {
            site = Site.me().setDomain("cpquery.sipo.gov.cn")
                    .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
        }

    }

    @Override
    public void process(Page page) {
        try {
            List<Selectable> selectableList = page.getHtml().xpath("//div[@class=\"content_listx\"]").nodes();
            String name = page.getUrl().regex("Ashenqingrxm=(.*)&select-key%3Azhuanlilx").toString();
            List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
            for (Selectable eachSelectable : selectableList) {
                Map<String, String> map = new HashMap<String, String>();
                logger.info("======>");
                logger.info("专利类型:" + eachSelectable.xpath("//*span[@name=\"record:zhuanlilx\"]/allText()"));
                map.put(CreepersConstant.TCreepersPatentColumn.PATENT_TYPE.getValue(),
                        eachSelectable.xpath("//*span[@name=\"record:zhuanlilx\"]/allText()").toString());
                logger.info("申请号/专利号:" + eachSelectable.xpath("//td[2]/allText()"));
                map.put(CreepersConstant.TCreepersPatentColumn.PATENT_NO.getValue(),
                        eachSelectable.xpath("//td[2]/allText()").toString());
                logger.info("发明名称:" + eachSelectable.xpath("//*span[@name=\"record:zhuanlimc\"]/allText()"));
                map.put(CreepersConstant.TCreepersPatentColumn.PATENT_NAME.getValue(),
                        eachSelectable.xpath("//*span[@name=\"record:zhuanlimc\"]/allText()").toString());

                logger.info("申请人:" + name);
                map.put(CreepersConstant.TCreepersPatentColumn.MER_NAME.getValue(), name);
                map.put(CreepersConstant.TCreepersPatentColumn.APPLICANT.getValue(), name);
                logger.info("申请日:" + eachSelectable.xpath("//span[@name=\"record:shenqingr\"]/@title"));
                map.put(CreepersConstant.TCreepersPatentColumn.APPLY_DT.getValue(),
                        eachSelectable.xpath("//span[@name=\"record:shenqingr\"]/@title").toString());
                logger.info("主分类号 :" + eachSelectable.xpath("//span[@name=\"record:zhufenlh\"]/@title"));
                map.put(CreepersConstant.TCreepersPatentColumn.CATEGORY_NO.getValue(),
                        eachSelectable.xpath("//span[@name=\"record:zhufenlh\"]/@title").toString());
                resultList.add(map);
            }
            page.putField("resultList", resultList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("PatentProcessor process error:", e);
            CreepersParamDTO param = page.getResultItems().get(BaseConstant.PARAM_DTO_KEY);
            param.setErrorInfo("PatentProcessor process error:" + e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorPath(getClass().toString());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        String companyName = "深圳市裕同包装科技股份有限公司";

        /*
         * String merName = "深圳市裕同包装科技股份有限公司"; String url =
         * "http://cpquery.sipo.gov.cn//txnQueryOrdinaryPatents.do"; Request
         * request = new Request(url);
         * request.setMethod(HttpConstant.Method.POST); List<NameValuePair>
         * valuePairs = new LinkedList<NameValuePair>(); valuePairs.add(new
         * BasicNameValuePair("select-key", "shenqingh:")); valuePairs.add(new
         * BasicNameValuePair("select-key", "zhuanlimc:")); valuePairs.add(new
         * BasicNameValuePair("select-key", "shenqingrxm:"+merName));
         * valuePairs.add(new BasicNameValuePair("select-key", "zhuanlilx:"));
         * valuePairs.add(new BasicNameValuePair("select-key",
         * "shenqingr_from:")); valuePairs.add(new
         * BasicNameValuePair("select-key", "shenqingr_to:"));
         * valuePairs.add(new BasicNameValuePair("attribute-node",
         * "record_start-row:1")); valuePairs.add(new
         * BasicNameValuePair("attribute-node", "record_page-row:1000"));
         * request.putExtra("nameValuePairList", valuePairs);
         */
        Spider.create(new PatentProcessor()).addPipeline(new BusinessInfoPipline())
                .setDownloader(new SeleniumDownloader())
                .addUrl("http://cpquery.sipo.gov.cn//txnQueryOrdinaryPatents.do?select-key%3Ashenqingh=&select-key%3Azhuanlimc=&select-key%3Ashenqingrxm="
                        + companyName
                        + "&select-key%3Azhuanlilx=&select-key%3Ashenqingr_from=&select-key%3Ashenqingr_to=&attribute-node:record_start-row=1&attribute-node:record_page-row=1000&")
                .thread(1).runAsync();
    }
}
