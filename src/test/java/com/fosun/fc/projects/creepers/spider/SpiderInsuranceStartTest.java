package com.fosun.fc.projects.creepers.spider;

import java.util.List;

import org.apache.http.NameValuePair;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dao.CreepersListInsuranceDao;
import com.fosun.fc.projects.creepers.downloader.SimulateDownloader;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersListInsurance;
import com.fosun.fc.projects.creepers.pageprocessor.InsuranceProcessor;
import com.fosun.fc.projects.creepers.pipeline.InsurancePipeline;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.utils.HttpConstant;

public class SpiderInsuranceStartTest extends SpiderBaseTest {

    @Autowired(required = true)
    private InsuranceProcessor proxyMimvpProcessor;

    @Autowired(required = true)
    private InsurancePipeline proxyMimvpPipline;

    @Autowired(required = true)
    private CreepersListInsuranceDao creepersListInsuranceDao;

    public void methodTest() {
        // 查询任务List，结果为空或状态不为1则启动爬寻
        String userCode = "123123";
        String password = "1313";
        List<TCreepersListInsurance> taskList = creepersListInsuranceDao.queryListByUserCode(userCode);
        Boolean creepFlag = false;
        if (CommonMethodUtils.isEmpty(taskList)) {
            TCreepersListInsurance task = new TCreepersListInsurance();
            task.setUserCode(userCode);
            task.setPassword(password);
            task.setFlag(BaseConstant.TaskListStatus.DEFAULT.getValue());
            CommonMethodUtils.setByDT(task);
            creepersListInsuranceDao.save(task);
            creepFlag = true;
        } else {
            TCreepersListInsurance task = (TCreepersListInsurance) taskList.get(0);
            String flag = task.getFlag();
            if (!BaseConstant.TaskListStatus.DEFAULT.getValue().equals(flag)) {
                creepFlag = true;
            }
        }
        if (creepFlag) {
            String url = "http://www.12333sh.gov.cn/sbsjb/wzb/sbsjbcx.jsp";
            Request request = new Request(url);
            request.setMethod(HttpConstant.Method.POST);
            NameValuePair[] nameValuePair = new NameValuePair[10];
            request.putExtra(BaseConstant.POST_NAME_VALUE_PAIR, nameValuePair);

            CreepersLoginParamDTO param = new CreepersLoginParamDTO();
            param.setLoginName(userCode);
            param.setPassword(password);
            param.setTaskType(BaseConstant.TaskListType.INSURANCE_LIST.getValue());
            param.setTaskStatus(BaseConstant.TaskListStatus.DEFAULT.getValue());
            Spider.create(proxyMimvpProcessor).addPipeline(proxyMimvpPipline)
                    .setDownloader(new SimulateDownloader().setParam(param)).addRequest(request).thread(1).runAsync();
        }
    }

    @Test
    public void run() {
        methodTest();
    }

}
