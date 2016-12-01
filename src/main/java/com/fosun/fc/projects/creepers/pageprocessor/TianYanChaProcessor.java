package com.fosun.fc.projects.creepers.pageprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.downloader.TianYanChaSeleniumDownloader;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 
 * <p>
 * 天眼查
 * </p>
 * 
 * @author MaXin
 * @since 2016-7-18 16:03:44
 * @see
 */
@Component("tianYanChaProcessor")
public class TianYanChaProcessor implements PageProcessor {

    private Site site;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public TianYanChaProcessor() {
        if (null == site) {
            site = Site.me().setDomain("tianyancha.com").setTimeOut(60000);
        }

    }

    @Override
    public void process(Page page) {
        logger.info("currentPage:" + page.getUrl());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new TianYanChaProcessor()).setDownloader(new TianYanChaSeleniumDownloader())
                .addUrl("http://www.tianyancha.com/search/310104000610936")
                .addUrl("http://www.tianyancha.com/search/510681000083381")
                .addUrl("http://www.tianyancha.com/search/510681000010375")
                .addUrl("http://www.tianyancha.com/search/310115400135616")
                .addUrl("http://www.tianyancha.com/search/310115400148508")
                .addUrl("http://www.tianyancha.com/search/310115400184053")
                .addUrl("http://www.tianyancha.com/search/310115002022797")
                .addUrl("http://www.tianyancha.com/search/310115400180829")
                .addUrl("http://www.tianyancha.com/search/310115001991859")
                .addUrl("http://www.tianyancha.com/search/310115400180118")
                .addUrl("http://www.tianyancha.com/search/310115400156629")
                .addUrl("http://www.tianyancha.com/search/310115400179193")
                .addUrl("http://www.tianyancha.com/search/310115400147394")
                .addUrl("http://www.tianyancha.com/search/310115001829607")
                .addUrl("http://www.tianyancha.com/search/310115400044943")
                .addUrl("http://www.tianyancha.com/search/310115400156942")
                .addUrl("http://www.tianyancha.com/search/310115001999242")
                .addUrl("http://www.tianyancha.com/search/310141000002692")
                .addUrl("http://www.tianyancha.com/search/310141000019479")
                .addUrl("http://www.tianyancha.com/search/310000500529807")
                .addUrl("http://www.tianyancha.com/search/310228000764806")
                .addUrl("http://www.tianyancha.com/search/310228001380603")
                .addUrl("http://www.tianyancha.com/search/3102282065193").thread(2).runAsync();
    }
}
