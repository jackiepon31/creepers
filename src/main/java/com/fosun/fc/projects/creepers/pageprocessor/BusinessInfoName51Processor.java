package com.fosun.fc.projects.creepers.pageprocessor;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.downloader.SeleniumDownloader;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 
 * <p>
 * Demo: 四川省工商名称查询
 * </p>
 * 
 * @author MaXin
 * @since 2016年6月25日
 * @see
 */
@Component("businessInfoName51Processor")
public class BusinessInfoName51Processor implements PageProcessor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private Site site;

    public BusinessInfoName51Processor() {
        if (null == site) {
            site = Site.me().setDomain("gsxt.saic.gov.cn")
                    .setUserAgent(
                            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36")
                    .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .addHeader("Accept-Encoding", "gzip, deflate").addHeader("Accept-Language", "zh-CN,zh;q=0.8")
                    .addHeader("Cache-Control", "max-age=0").addHeader("Connection", "keep-alive")
                    .addHeader("Content-Length", "115").addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Host", "gsxt.scaic.gov.cn").addHeader("Origin", "http://gsxt.scaic.gov.cn")
                    .addHeader("Referer",
                            "http://gsxt.scaic.gov.cn/ztxy.do?method=list&djjg=&random=" + new Date().getTime())
                    .addHeader("Upgrade-Insecure-Requests", "1").setSleepTime(15000)
                    .setUserAgent("Mozilla/5.0 (iPad; CPU OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1")
                    .addCookie("JSESSIONID", "00004wJIgEz5Q9Dx3fx62gFCQQo:1ahbb47sb");
        }

    }

    @Override
    public void process(Page page) {

        logger.info(page.getHtml().toString());
        /*if (page.getHtml().xpath("/html/body/form/div[5]/div[2]").regex("您搜索的条件无查询结果。").match()) {// 无数据
            logger.info("查无数据！");
            page.putField("status", BaseConstant.BusinessInfoStatus.EXIST.getValue());
        } else {
            page.putField("status", BaseConstant.BusinessInfoStatus.SUCCEED.getValue());
            String merName = page.getHtml().xpath("/html/body/form/div[5]/div[2]/ul/li[1]/allText()").toString();
            logger.info("商户名称：" + merName);
            page.putField("merName", merName);
            String tempNo = page.getHtml().xpath("/html/body/form/div[5]/div[2]/ul/li[2]/span[1]/allText()").toString();
            StringBuffer urlDetail = new StringBuffer();
            urlDetail.append("http://gsxt.scaic.gov.cn/ztxy.do?method=qyInfo&djjg=&maent.pripid=").append(tempNo)
                    .append("&maent.entbigtype=50&random=").append(new Date().getTime());
            logger.info(urlDetail.toString());
            page.putField("memo", urlDetail.toString());
            logger.info("工商信用统一号：" + tempNo);
            String merNo = page.getUrl().regex("&yzmYesOrNo=no&maent.entname=(.*)&random=").toString();
            logger.info("工商注册号：" + merNo);
            page.putField("merNo", merNo);

        }*/
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new BusinessInfoName51Processor()).setDownloader(new SeleniumDownloader(BaseConstant.SeleniumWebDriverPath.CHROME_PATH.getValue()))
                //.addPipeline(new BusinessInfoPipline())
                .addUrl("https://www.sgs.gov.cn/notice/search/ent_info_list?searchType=1&captcha=6&session.token=d74b1675-c037-440d-8ad3-404f16e717a6&condition.keyword=310115002090738")
                .thread(1).runAsync();
    }
}
