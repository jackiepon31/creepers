package com.fosun.fc.projects.creepers.web.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fosun.fc.modules.utils.JsonResult;
import com.fosun.fc.modules.utils.JsonResult.JSON_RESULT_TYPE;
import com.fosun.fc.modules.web.MediaTypes;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;

@RestController
@RequestMapping(value = "/api")
public class CreepersInsuranceRestController extends CreepersBaseRestController {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value = "/insurance/query", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<JsonResult<Map>> queryByMerName(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = null;
        String paramsStr = request.getParameter("bizUk");
        if (paramsStr.contains("|")) {
            String[] params = paramsStr.split("|");
            CreepersLoginParamDTO creepersParamDTO = new CreepersLoginParamDTO();
            creepersParamDTO.setTaskType(BaseConstant.TaskListType.INSURANCE_LIST.getValue());
            creepersParamDTO.setLoginName(params[0]);
            creepersParamDTO.setPassword(params[1]);
            jsonResult = creepersListServiceImpl.processByParam(creepersParamDTO);
        } else {
            Map map = new HashMap<>();
            jsonResult = new JsonResult(JSON_RESULT_TYPE.failure, "参数错误", map);
        }
        return new ResponseEntity<JsonResult<Map>>(jsonResult, HttpStatus.OK);
    }
}
