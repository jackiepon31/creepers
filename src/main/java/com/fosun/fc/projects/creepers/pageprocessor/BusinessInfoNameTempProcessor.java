package com.fosun.fc.projects.creepers.pageprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.downloader.SeleniumDownloader;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 
 * <p>
 * 先抓取临时的工商抽查信息列表
 * </p>
 * 
 * @author MaXin
 * @since 2016年6月25日
 * @see
 */
@Component("businessInfoNameTempProcessor")
public class BusinessInfoNameTempProcessor implements PageProcessor {

    private Site site;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public BusinessInfoNameTempProcessor() {
        if (null == site) {
            site = Site.me().setDomain("www.sgs.gov.cn.notice.search.ent_spot_check_list")
                    .setUserAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36")
//                    .addCookie("_gscu_993197171“", "665853201vizsz21")
//                    .addCookie("JSESSIONID", "0001SnjrsQdHeokYndSsYNkOFRx:1ahbb47sb")
                    .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .addHeader("Accept-Encoding", "gzip, deflate")
                    .addHeader("Accept-Language", "zh-CN,zh;q=0.8")
                    .addHeader("Cache-Control", "max-age=0")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Content-Length", "188")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Host", "www.sgs.gov.cn")
                    .addHeader("Origin", "https://www.sgs.gov.cn")
                    .addHeader("Referer", "https://www.sgs.gov.cn/notice/search/ent_info_list")
                    .addHeader("Upgrade-Insecure-Requests", "1")
                    .setSleepTime(13000)
                    ;
        }

    }

    @Override
    public void process(Page page) {
        // logger.info(page.getHtml().xpath("//*[@class='list-table']/tbody/tr").nodes().toString());
        List<Selectable> trList = page.getHtml().xpath("//*[@class='list-table']/tbody/tr").nodes();
        boolean isFirstTR = true;
        List<Map<String , String>> resultList = new ArrayList<Map<String , String>>();
        for (Selectable selectable : trList) {
            if (isFirstTR) {
                isFirstTR = false;
                continue;
            }
            Map<String , String > map = new HashMap<String , String >();
            logger.info("===================================>>");
            logger.info("详情链接：" + selectable.xpath("a/@href").toString());
            map.put(CreepersConstant.TCreepersListColumn.MEMO.getValue(), selectable.xpath("a/@href").toString());
            logger.info("工商名称：" + selectable.xpath("tr/td[1]/allText()"));
            map.put(CreepersConstant.TCreepersListColumn.MER_NAME.getValue(), selectable.xpath("tr/td[1]/allText()").toString());
            logger.info("工商注册号：" + selectable.xpath("tr/td[2]/allText()"));
            map.put(CreepersConstant.TCreepersListColumn.MER_NO.getValue(), selectable.xpath("tr/td[2]/allText()").toString());
            resultList.add(map);
        }
        page.putField("resultList", resultList);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider = Spider.create(new BusinessInfoNameTempProcessor()).setDownloader(new SeleniumDownloader());
        for (int i = 1; i < 6; i++) {
            spider.addUrl("http://www.sgs.gov.cn/notice/search/ent_except_list?condition.pageNo=" + i
                    + "&condition.keyword=");
        }
        spider.thread(5).runAsync();
    }
}
