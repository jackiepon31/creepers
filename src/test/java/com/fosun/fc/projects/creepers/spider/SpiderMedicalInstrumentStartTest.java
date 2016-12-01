package com.fosun.fc.projects.creepers.spider;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.downloader.HttpRequestDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.pageprocessor.CFDA.MedicalInstrumentDomesticProcessor;
import com.fosun.fc.projects.creepers.pageprocessor.CFDA.MedicalInstrumentForeignProcessor;
import com.fosun.fc.projects.creepers.pipeline.CFDA.MedicalPipline;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

public class SpiderMedicalInstrumentStartTest extends SpiderBaseTest {

    @Autowired(required = true)
    private MedicalInstrumentDomesticProcessor medicalInstrumentDomesticProcessor;

    @Autowired(required = true)
    private MedicalInstrumentForeignProcessor medicalInstrumentForeignProcessor;

    @Autowired(required = true)
    private MedicalPipline medicalPipline;

    // 国产器械
    public void methodTest1() {
        CreepersParamDTO param = new CreepersParamDTO();
        param.setTaskType(BaseConstant.TaskListType.MEDICAL_INSTRUMENT_DOMESTIC_LIST.getValue());
        param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, "http://qy1.sfda.gov.cn/datasearch/face3/search.jsp?tableId=26&bcId=118103058617027083838706701567");
        Request request = CommonMethodUtils.buildDefaultRequest(param, param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL));
        request.putExtra(BaseConstant.PARAM_EXTRA_CURRENT_PAGE_NO, "1");
        request.putExtra(BaseConstant.PARAM_EXTRA_TOTAL_PAGE_NO, "100");
        request.putExtra(BaseConstant.PARAM_EXTRA_THREAD_NUM, "1");

        Spider.create(medicalInstrumentDomesticProcessor).addPipeline(medicalPipline)
                .setDownloader(applicationContext.getBean(HttpRequestDownloader.class).setParam(param))
                .addRequest(request)
                .thread(50).run();
    }

    // 进口器械
    public void methodTest2() {
        CreepersParamDTO param = new CreepersParamDTO();
        param.setTaskType(BaseConstant.TaskListType.MEDICAL_INSTRUMENT_FOREIGN_LIST.getValue());
        param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
        param.putOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL, "http://qy1.sfda.gov.cn/datasearch/face3/search.jsp?tableId=27&bcId=118103063506935484150101953610");
        Request request = CommonMethodUtils.buildDefaultRequest(param, param.getOrderUrl(BaseConstant.OrderUrlKey.INDEX_URL));
        request.putExtra(BaseConstant.PARAM_EXTRA_CURRENT_PAGE_NO, "1");
        request.putExtra(BaseConstant.PARAM_EXTRA_TOTAL_PAGE_NO, "100");
        request.putExtra(BaseConstant.PARAM_EXTRA_THREAD_NUM, "1");

        Spider.create(medicalInstrumentForeignProcessor).addPipeline(medicalPipline)
                .setDownloader(applicationContext.getBean(HttpRequestDownloader.class).setParam(param))
                .addRequest(request)
                .thread(50).run();
    }

    @Test
    public void run() {
        methodTest1();
    }

}
