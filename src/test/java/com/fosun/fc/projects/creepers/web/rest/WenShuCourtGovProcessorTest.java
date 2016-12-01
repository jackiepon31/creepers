package com.fosun.fc.projects.creepers.web.rest;

import java.io.IOException;
import java.util.List;

import com.fosun.fc.projects.creepers.downloader.AddMouseCheckSeleniumDownloader;
import com.fosun.fc.projects.creepers.pipeline.FileTxtPageListModelPipline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/***
 * 
 *<p>
 * 中国裁判文书网
 * 使用Selenium做页面动态渲染。
 *</p>
 * @author MaXin  2016-5-11 15:50:12
 */
public class WenShuCourtGovProcessorTest implements PageProcessor {

    private Site site;

    @Override
    public void process(Page page) {

        // 单个内容解析完成。
        List<Page> pageList = page.getResultItems().get("pageList");
        for (Page eachPage : pageList) {
            List<String> divList = eachPage.getHtml().xpath("//*[@id=\"resultList\"]//div[@class=\"dataItem\"]").all();//
            System.out.println("divList。size:" + divList.size());
            for (int index = 1; index < divList.size() + 1; index++) {
                eachPage.putField("title" + index, eachPage.getHtml()
                        .xpath("//*[@id=\"resultList\"]/div[" + index + "]/table/tbody/tr[1]/td/div/a/allText()"));
                eachPage.putField("docId" + index,
                        eachPage.getHtml()
                                .xpath("//*[@id=\"resultList\"]/div[" + index + "]/table/tbody/tr[1]/td/div/a").links()
                                .regex("DocID=(.*)"));
                String values = eachPage.getHtml()
                        .xpath("//*[@id=\"resultList\"]/div[" + index + "]/table/tbody/tr[2]/td/div/text()").toString();
//                System.out.println(eachPage.getResultItems().get("title" + index));
//                System.out.println(eachPage.getResultItems().get("docId" + index));
                String[] stringArr = null;
                if (values != null) {
                    stringArr = values.replaceAll("[ ]+", "|").split("\\|");
                    for (int i = 0; i < stringArr.length; i++) {
                        String string = stringArr[i];
                        // if (string != null &&
                        // string.matches("[^\\x00-\\xff]+\\DD[^\\x00-\\xff]+"))
                        // {
                        if (string != null && string.endsWith("号")) {
                            eachPage.putField("anhao" + index, string);
//                            System.out.println(eachPage.getResultItems().get("anhao" + index));
                        } else if (string != null && string.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")) {
                            eachPage.putField("date" + index, string);
//                            System.out.println(eachPage.getResultItems().get("date" + index));
                        } else if (string != null && string.matches("[\\u4e00-\\u9fa5]+")) {
                            eachPage.putField("fayuan" + index, string);
//                            System.out.println(eachPage.getResultItems().get("fayuan" + index));
                        }
                    }
                }
            }
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

        Spider.create(new WenShuCourtGovProcessorTest()).thread(1)
                .addPipeline(new FileTxtPageListModelPipline("/webmagic/"))
                .setDownloader(new AddMouseCheckSeleniumDownloader(System.getProperty("user.dir").replaceAll("\\\\", "/") + "/bin/chromedriver.exe"))
                .addUrl("http://wenshu.court.gov.cn/list/list/?sorttype=1&conditions=searchWord+快播+AJMC++案件名称:快播")
                .runAsync();
//        System.out.println(System.getProperty("user.dir").replaceAll("\\\\", "/") + "/bin/chromedriver.exe");
    }

    /*
     * 
     * @Override public void process(Page page) {
     * page.addTargetRequests(page.getHtml().links().regex(
     * "http://huaban\\.com/.*").all()); if
     * (page.getUrl().toString().contains("pins")) { page.putField("img",
     * page.getHtml().xpath("//div[@id='pin_img']/a/img/@src").toString()); }
     * else { page.getResultItems().setSkip(true); } }
     * 
     * @Override public Site getSite() { if (null == site) { site =
     * Site.me().setDomain("huaban.com").setSleepTime(0); } return site; }
     * 
     * public static void main(String[] args) { Spider.create(new
     * HuabanProcessor()).thread(5) .addPipeline(new
     * FilePipeline("/data/webmagic/test/")) .setDownloader(new
     * SeleniumDownloader("/Users/yihua/Downloads/chromedriver"))
     * .addUrl("http://huaban.com/") .runAsync(); }
     * 
     * 
     * 
     */
}
