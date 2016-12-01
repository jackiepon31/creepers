package com.fosun.fc.projects.creepers.spider;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.dao.CreepersListDao;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.entity.TCreepersList;
import com.fosun.fc.projects.creepers.pageprocessor.CourtAnnouncementProcessor;
import com.fosun.fc.projects.creepers.pipeline.CourtAnnouncementPipline;

import us.codecraft.webmagic.Spider;

public class SpiderCourtAnnouncementStartTest extends SpiderBaseTest {

    @Autowired(required = true)
    private CourtAnnouncementProcessor courtAnnouncementProcessor;

    @Autowired(required = true)
    private CourtAnnouncementPipline courtAnnouncementPipline;

    @Autowired(required = true)
    private HttpRequestDownloader httpRequestDownloader;

    @Autowired(required = true)
    private CreepersListDao creepersListDao;

    public void methodTest() {
        @SuppressWarnings("unused")
        List<TCreepersList> list = creepersListDao.findAll();
        Spider.create(courtAnnouncementProcessor).addPipeline(courtAnnouncementPipline)
                .setDownloader(httpRequestDownloader).thread(2).runAsync();
    }

    @Test
    public void run() {
        // methodTest();
    }

}
