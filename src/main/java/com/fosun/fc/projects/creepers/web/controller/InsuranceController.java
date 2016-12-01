package com.fosun.fc.projects.creepers.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fosun.fc.modules.web.Servlets;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dto.CreepersErrorListDTO;
import com.fosun.fc.projects.creepers.dto.CreepersListInsuranceDTO;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.service.ICreepersInsuranceService;
import com.fosun.fc.projects.creepers.utils.TranslateMethodUtil;

@Controller
@RequestMapping("/insurance")
public class InsuranceController extends BaseController {

    @Autowired
    private ICreepersInsuranceService creepersInsuranceServiceImpl;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init() {
        logger.info("========>InsuranceController-->init<========");
        return "insurance/insuranceList";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = "5") int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
            ServletRequest request) throws Exception {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        logger.info("========>InsuranceController-->list<========");
        logger.info("========>查询参数：" + searchParams + "<========");
        Page<CreepersListInsuranceDTO> resultList = (Page<CreepersListInsuranceDTO>) creepersListServiceImpl.findList(
                searchParams, pageNumber, pageSize, sortType, BaseConstant.TaskListType.INSURANCE_LIST.getValue());
        model.addAttribute("resultList", resultList);
        model.addAttribute("sortType", sortType);
        model.addAttribute("param", TranslateMethodUtil.buildPageSearchParam(searchParams, "search_"));
        model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap(searchParams, "search_"));
        return "insurance/insuranceList";
    }

    @RequestMapping(value = "/queryInfo")
    public String queryInfo(Model model, String userCode) {
        logger.info("========>InsuranceController-->queryInfo<========");
        logger.info("========>查询参数userCode：" + userCode + "<========");
        Map<String, Object> resultMap = creepersInsuranceServiceImpl.findListByCertNoForMap(userCode);
        model.addAttribute("basicResultList",
                resultMap.get(CreepersConstant.TableNamesInsurance.T_CREEPERS_INSURANCE_BASIC.getMapKey()));
        model.addAttribute("paymentResultList",
                resultMap.get(CreepersConstant.TableNamesInsurance.T_CREEPERS_INSURANCE_PAYMENT.getMapKey()));
        model.addAttribute("unitResultList",
                resultMap.get(CreepersConstant.TableNamesInsurance.T_CREEPERS_INSURANCE_UNIT.getMapKey()));
        model.addAttribute("sumResultList",
                resultMap.get(CreepersConstant.TableNamesInsurance.T_CREEPERS_INSURANCE_SUM.getMapKey()));
        return "insurance/insuranceDetail";
    }

    @ResponseBody
    @RequestMapping(value = "/doRecycle")
    public Map<String, String> doRecycle(String userCode) throws Exception {
        logger.info("========>InsuranceController-->doRecycle<========");
        logger.info("========>重爬参数userCode：" + userCode + "<========");
        CreepersLoginParamDTO param = new CreepersLoginParamDTO();
        param.setTaskType(BaseConstant.TaskListType.INSURANCE_LIST.getValue());
        param.setLoginName(userCode);
        creepersListServiceImpl.doRecycleByParam(param);
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("message", "success");
        return resultMap;
    }

    @RequestMapping(value = "/queryLog")
    public String queryLog(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = "4") int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model, String userCode) {
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("EQ_merName", userCode);
        searchParams.put("EQ_taskType", BaseConstant.TaskListType.INSURANCE_LIST.getValue());
        logger.info("========>InsuranceController-->queryLog<========");
        logger.info("========>查询参数：" + searchParams + "<========");
        Page<CreepersErrorListDTO> resultList = creepersErrorListServiceImpl.queryErrorList(searchParams, pageNumber,
                pageSize, sortType);
        model.addAttribute("resultList", resultList);
        model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap("userCode", userCode));
        return "creeperTask/errorLog";
    }
}
