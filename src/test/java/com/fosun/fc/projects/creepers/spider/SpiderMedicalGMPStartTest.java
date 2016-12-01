package com.fosun.fc.projects.creepers.spider;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.pageprocessor.CFDA.MedicalGMPGDProcessor;
import com.fosun.fc.projects.creepers.pipeline.CFDA.MedicalPipline;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

public class SpiderMedicalGMPStartTest extends SpiderBaseTest {

    @Autowired(required = true)
    private MedicalGMPGDProcessor medicalGMPGDProcessor;

    @Autowired(required = true)
    private MedicalPipline medicalPipline;

    private static final String URL_PAGE_TEMPLATE = "http://www.sda.gov.cn/wbpp/generalsearch?sort=true&sortId=CTIME&columnid=CLID%7CBCID&relation=MUST%7CMUST&tableName=Region&colNum=2&qryNum=2&qryidstr=CLID%7CBCID&qryValue=cl0885%7C0019&record=20&mytarget=~blank&dateFormat=yyyy%C4%EAMM%D4%C2dd%C8%D5&titleLength=-1&subTitleFlag=0&classStr=%7C-1%7C-1%7CListColumnClass15%7CLawListSub15%7Clistnew15%7Clisttddate15%7Clistmore15%7Cdistance15%7Cclasstab15%7Cclasstd15%7CpageTdSTR15%7CpageTdSTR15%7CpageTd15%7CpageTdF15%7CpageTdE15%7CpageETd15%7CpageTdGO15%7CpageTdGOTB15%7CpageGOButton15%7CpageDatespan15%7Cpagestab15%7CpageGOText15&curPage=";

    // 广东GMP
    public void methodTest1() {
        CreepersParamDTO param = new CreepersParamDTO();
        param.setTaskType(BaseConstant.TaskListType.MEDICAL_GMP_LIST.getValue());
        param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
        // 验证下载
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, "http://www.sda.gov.cn/WS01/CL0885/145901.html");
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, URL_PAGE_TEMPLATE + "1&index");
        param.putOrderUrl(BaseConstant.OrderUrlKey.DOWNLOAD_FILE_URL_1_REGEX, "http://www.sda.gov.cn/directory/web/WS01/images/(.*).xls");
        Request request = CommonMethodUtils.buildGetRequestCarryMap(param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL));
        request.putExtra(BaseConstant.PARAM_EXTRA_CURRENT_PAGE_NO, "1");
        request.putExtra(BaseConstant.PARAM_EXTRA_TOTAL_PAGE_NO, "100");
        request.putExtra(BaseConstant.PARAM_EXTRA_THREAD_NUM, "1");
        Spider.create(medicalGMPGDProcessor).addPipeline(medicalPipline)
                .setDownloader(applicationContext.getBean(HttpRequestDownloader.class).setParam(param))
                .addRequest(request)
                .thread(1).run();
    }

    @Test
    public void run() {
        methodTest1();
    }

}
