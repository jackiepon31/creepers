package com.fosun.fc.projects.creepers.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fosun.fc.modules.utils.JsonResult;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;

@Controller
@RequestMapping(value = "/creeperTask")
public class CreeperTaskController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * <p>
     * description:跳转到查询页面
     * </p>
     * 
     * @return
     * @author LiZhanPing
     * @see
     */
    @RequestMapping(value = "/toAddTask", method = RequestMethod.GET)
    public String toAddTask(Model model) {
        logger.info("========>CreeperTaskController-->toAddTask<========");
        model.addAttribute("taskTypeList", BaseConstant.getTaskListTypeList());
        model.addAttribute("loginTaskTypeList", BaseConstant.getLoginTaskListTypeList());
        return "creeperTask/addTask";
    }

    /**
     * <p>
     * description:增加爬取任务
     * </p>
     * 
     * @param taskType
     * @param merName
     * @return
     * @author LiZhanPing
     * @see
     */
    @SuppressWarnings("rawtypes")
    @ResponseBody
    @RequestMapping(value = "/addTask", method = RequestMethod.GET)
    public Map<String, String> addTask(String taskType, String merName) {
        logger.info("========>CreeperTaskController-->toAddTask<========");
        logger.info("========>任务队列：" + taskType + "<========");
        logger.info("========>商户名称：" + merName + "<========");
        Map<String, String> map = new HashMap<String, String>();
        JsonResult result = creepersListServiceImpl.addTaskByMerName(taskType, merName);
        map.put("message", result.getMessage());
        return map;
    }

    /**
     * <p>
     * description:增加爬取任务
     * </p>
     * 
     * @param taskType
     * @param merName
     * @return
     * @author LiZhanPing
     * @see
     */
    @SuppressWarnings("rawtypes")
    @ResponseBody
    @RequestMapping(value = "/addAccountTask")
    public Map<String, String> addAccountTask(CreepersLoginParamDTO paramDTO) {
        logger.info("========>CreeperTaskController-->toAddTask<========");
        logger.info("========>任务队列：" + paramDTO.getTaskType() + "<========");
        logger.info("========>姓名：" + paramDTO.getLoginName() + "<========");
        logger.info("========>密码：" + paramDTO.getPassword() + "<========");
        Map<String, String> map = new HashMap<String, String>();
        JsonResult result = creepersListServiceImpl.addTaskByParam(paramDTO);
        map.put("message", result.getMessage());
        return map;
    }
}
