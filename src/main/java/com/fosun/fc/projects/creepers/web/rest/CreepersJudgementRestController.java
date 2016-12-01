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

@RestController
@RequestMapping(value = "/api")
public class CreepersJudgementRestController extends CreepersBaseRestController {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/judgement/query", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
    public ResponseEntity<JsonResult<Map>> queryByMerNo(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = null;
        String merName = request.getParameter("bizUk");
        jsonResult = creepersListServiceImpl.processByMerName(BaseConstant.TaskListType.JUDGEMENT_LIST.getValue(),
                merName);
        return new ResponseEntity<JsonResult<Map>>(jsonResult, HttpStatus.OK);
    }
}
