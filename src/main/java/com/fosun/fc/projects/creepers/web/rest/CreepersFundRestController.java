package com.fosun.fc.projects.creepers.web.rest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fosun.fc.modules.utils.JsonResult;
import com.fosun.fc.modules.web.MediaTypes;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;

@RestController
@RequestMapping(value = "/api")
public class CreepersFundRestController extends CreepersBaseRestController {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/fund/query", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<JsonResult<Map>> queryByAccount(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = null;
        String principle = request.getParameter("bizUk");
        String userCode = principle.split("|")[0];
        String password = principle.split("|")[1];
        CreepersLoginParamDTO param = new CreepersLoginParamDTO();
        param.setLoginName(userCode);
        param.setPassword(password);
        param.setTaskType(BaseConstant.TaskListType.FUND_LIST.getValue());
        jsonResult = creepersListServiceImpl.processByParam(param);
        return new ResponseEntity<JsonResult<Map>>(jsonResult, HttpStatus.OK);
    }
}
