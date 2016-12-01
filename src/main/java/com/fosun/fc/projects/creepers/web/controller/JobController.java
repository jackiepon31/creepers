package com.fosun.fc.projects.creepers.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;
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

import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.modules.web.Servlets;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersJobDTO;
import com.fosun.fc.projects.creepers.service.ICreepersJobService;
import com.fosun.fc.projects.creepers.utils.TranslateMethodUtil;

@Controller
@RequestMapping("/job")
public class JobController extends BaseController{

    @Autowired
    private ICreepersJobService creepersjobServiceImpl;
    

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(Model model) {
        logger.info("========>jobController-->init<========");
        model.addAttribute("taskTypeList", BaseConstant.getAllDataTaskListTypeList());
        return "job/jobList";
    }

    @RequestMapping(value = "/initDetail", method = RequestMethod.GET)
    public String initDetail(Model model) {
        logger.info("========>jobController-->init detail<========");
        model.addAttribute("taskTypeList", BaseConstant.getAllDataTaskListTypeList());
        return "job/jobDetail";
    }

    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public String toAdd(Model model) {
        logger.info("========>jobController-->to add<========");
        model.addAttribute("taskTypeList", BaseConstant.getAllDataTaskListTypeList());
        return "job/jobAdd";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = "10") int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
            ServletRequest request) throws Exception {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        logger.info("========>jobController-->list<========");
        logger.info("========>查询参数：" + searchParams + "<========");
        Page<CreepersJobDTO> resultList = (Page<CreepersJobDTO>) creepersjobServiceImpl.findList(searchParams, pageNumber, pageSize, sortType);
        model.addAttribute("resultList", resultList);
        model.addAttribute("sortType", sortType);
        model.addAttribute("param", TranslateMethodUtil.buildPageSearchParam(searchParams, "search_"));
        model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap(searchParams, "search_"));
        model.addAttribute("taskTypeList", BaseConstant.getAllDataTaskListTypeList());
        return "job/jobList";
    }

    @RequestMapping(value = "/queryJob")
    public String update(Model model, String jobName,String jobGroup) {
        logger.info("========>jobController-->queryJob<========");
        logger.info("========>查询参数jobName：" + jobName + "<========");
        CreepersJobDTO creepersJobDTO = new CreepersJobDTO();
		try {
			creepersJobDTO = creepersjobServiceImpl.getJob(jobName, jobGroup);
			if(StringUtils.isNotBlank(creepersJobDTO.getMemo()))
			    creepersJobDTO.setMemo(creepersJobDTO.getMemo().replace("\"", "'"));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
        model.addAllAttributes(BeanMapper.toMap(creepersJobDTO));
        return "job/jobAdd";
    }
    
    @RequestMapping(value = "/doSave", method = RequestMethod.GET)
    public String saveAndUpdateInfo(Model model, CreepersJobDTO creepersJobDTO) {
        model.addAllAttributes(BeanMapper.toMap(creepersJobDTO));
        try {
			creepersjobServiceImpl.addJob(creepersJobDTO);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
        model.addAttribute("taskTypeList", BaseConstant.getAllDataTaskListTypeList());
        return "redirect:/job/list";
    }
    

    @ResponseBody
    @RequestMapping(value = "/doPause")
    public Map<String, String> doPause(String jobName,String jobGroup) throws Exception {
        logger.info("========>jobController-->doPause<========");
        logger.info("========>暂停Job：" + jobName + "<========");
        CreepersJobDTO creepersJobDTO = new CreepersJobDTO();
        creepersJobDTO.setJobName(jobName);
        creepersJobDTO.setJobGroup(jobGroup);
        creepersjobServiceImpl.pauseJob(creepersJobDTO);
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("message", "success");
        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value = "/doResume")
    public Map<String, String> doResume(String jobName,String jobGroup) throws Exception {
        logger.info("========>jobController-->doResume<========");
        logger.info("========>恢复Job：" + jobName + "<========");
        CreepersJobDTO creepersJobDTO = new CreepersJobDTO();
        creepersJobDTO.setJobName(jobName);
        creepersJobDTO.setJobGroup(jobGroup);
        creepersjobServiceImpl.resumeJob(creepersJobDTO);
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("message", "success");
        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value = "/doDelete")
    public Map<String, String> doDelete(String jobName,String jobGroup) throws Exception {
        logger.info("========>jobController-->doDelete<========");
        logger.info("========>删除Job：" + jobName + "<========");
        CreepersJobDTO creepersJobDTO = new CreepersJobDTO();
        creepersJobDTO.setJobName(jobName);
        creepersJobDTO.setJobGroup(jobGroup);
        creepersjobServiceImpl.deleteJob(creepersJobDTO);
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("message", "success");
        return resultMap;
    }
    
    @ResponseBody
    @RequestMapping(value = "/resumeFromBreakPoint")
    public Map<String, String> resumeFromBreakPoint(String jobName,String jobGroup) throws Exception {
        logger.info("========>jobController-->doResume<========");
        logger.info("========>恢复Job：" + jobName + "<========");
        creepersListServiceImpl.addTaskByMerName(jobGroup, jobName);
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("message", "success");
        return resultMap;
    }
}
