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
import com.fosun.fc.projects.creepers.dto.CreepersErrorListDTO;
import com.fosun.fc.projects.creepers.dto.CreepersListPatentDTO;
import com.fosun.fc.projects.creepers.dto.CreepersPatentDTO;
import com.fosun.fc.projects.creepers.service.ICreepersListPatentService;
import com.fosun.fc.projects.creepers.service.ICreepersPatentService;
import com.fosun.fc.projects.creepers.utils.TranslateMethodUtil;

@Controller
@RequestMapping(value = "/patent")
public class PatentController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ICreepersPatentService patentService;
    @Autowired
    private ICreepersListPatentService creepersListPatentServiceImpl;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init() {
        logger.info("========>patentController-->init<========");
        return "patent/patentList";
    }

    @RequestMapping(value = "/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = "5") int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
            ServletRequest request) throws Exception {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        logger.info("========>patentController-->list<========");
        logger.info("========>查询参数：" + searchParams + "<========");
        Page<CreepersListPatentDTO> resultList = creepersListPatentServiceImpl.queryListPatentList(searchParams, pageNumber,
                pageSize, sortType);
        model.addAttribute("resultList", resultList);
        model.addAttribute("sortType", sortType);
        model.addAttribute("param", TranslateMethodUtil.buildPageSearchParam(searchParams, "search_"));
        model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap(searchParams, "search_"));
        return "patent/patentList";
    }

    @RequestMapping(value = "/queryInfo")
    public String queryInfo(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = "5") int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model, String merName) {
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("EQ_merName", merName);
        logger.info("========>patentController-->queryInfo<========");
        logger.info("========>查询参数：" + searchParams + "<========");
        Page<CreepersPatentDTO> resultList = patentService.queryPatentList(searchParams, pageNumber, pageSize,
                sortType);
        model.addAttribute("resultList", resultList);
        model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap("merName", merName));
        return "patent/patentDetail";
    }

    @ResponseBody
    @RequestMapping(value = "/doRecycle")
    public Map<String, String> doRecycle(String merName) {
        logger.info("========>patentController-->doRecycle<========");
        logger.info("========>查询参数：merName=" + merName + "<========");
        creepersListServiceImpl.doRecycleByMerName(BaseConstant.TaskListType.PATENT_LIST.getValue(), merName);
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("message", "success");
        return resultMap;
    }

    @RequestMapping(value = "/queryLog")
    public String queryLog(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = "5") int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model, String merName) {
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("EQ_merName", merName);
        searchParams.put("EQ_taskType", BaseConstant.TaskListType.PATENT_LIST.getValue());
        logger.info("========>patentController-->queryLog<========");
        logger.info("========>查询参数：" + searchParams + "<========");
        Page<CreepersErrorListDTO> resultList = creepersErrorListServiceImpl.queryErrorList(searchParams, pageNumber, pageSize,
                sortType);
        model.addAttribute("resultList", resultList);
        model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap("merName", merName));
        return "creeperTask/errorLog";
    }
}
