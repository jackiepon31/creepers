package com.fosun.fc.projects.creepers.utils;

import org.nlpcn.commons.lang.util.StringUtil;

import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;

/**
 * 
 * <p>
 * description: 日志格式统一格式化
 * </p>
 * 
 * @author MaXin
 * @since 2016年8月11日
 * @see
 */
public class LogInfoFormat {

    private static final String NEW_LINE = "\n";
    private static final String SPILT_LINE = "=====================================";
    private static final String LOG_START = "=======>>> error Log begin <<<=======";
    private static final String LOG_END = "=======>>> error Log  end  <<<=======";
    private static final String TASK_TYPE = "======>>> task type : ";
    private static final String ERROR_PATH = "=======>>> error occur path : ";
    private static final String ERROR_INFO = "======>>> error Info : ";
    private static final String PARAM_SEARCH_KEY_WORD = "======>>> param searchKeyWord : ";
    private static final String VALUE_LEFT = "======>>> value : ( ";
    private static final String VALUE_RIGHT = " ) <<<=======";

    public static String errorLog(CreepersParamDTO param) {
        StringBuffer errorInfo = new StringBuffer();
        errorInfo.append(NEW_LINE);
        errorInfo.append(SPILT_LINE).append(NEW_LINE);
        errorInfo.append(LOG_START).append(NEW_LINE);
        errorInfo.append(SPILT_LINE).append(NEW_LINE).append(NEW_LINE);
        errorInfo.append(ERROR_PATH).append(param.getErrorPath()).append(NEW_LINE).append(NEW_LINE);
        errorInfo.append(TASK_TYPE).append(param.getTaskType()).append(NEW_LINE).append(NEW_LINE);
        errorInfo.append(ERROR_INFO).append(param.getErrorInfo()).append(NEW_LINE).append(NEW_LINE);
        if (!StringUtil.isBlank(param.getSearchKeyWordForString())) {
            errorInfo.append(PARAM_SEARCH_KEY_WORD).append(NEW_LINE);
            errorInfo.append(VALUE_LEFT).append(param.getSearchKeyWord()).append(VALUE_RIGHT).append(NEW_LINE);
            errorInfo.append(NEW_LINE);
        }
        errorInfo.append(SPILT_LINE).append(NEW_LINE);
        errorInfo.append(LOG_END).append(NEW_LINE);
        errorInfo.append(SPILT_LINE).append(NEW_LINE);
        return errorInfo.toString();
    }
    
    public static String errorLog(CreepersLoginParamDTO param) {
        StringBuffer errorInfo = new StringBuffer();
        errorInfo.append(NEW_LINE);
        errorInfo.append(SPILT_LINE).append(NEW_LINE);
        errorInfo.append(LOG_START).append(NEW_LINE);
        errorInfo.append(SPILT_LINE).append(NEW_LINE).append(NEW_LINE);
        errorInfo.append(ERROR_PATH).append(param.getErrorPath()).append(NEW_LINE).append(NEW_LINE);
        errorInfo.append(TASK_TYPE).append(param.getTaskType()).append(NEW_LINE).append(NEW_LINE);
        errorInfo.append(ERROR_INFO).append(param.getErrorInfo()).append(NEW_LINE).append(NEW_LINE);
        if (!StringUtil.isBlank(param.getSearchKeyWordForString())) {
            errorInfo.append(PARAM_SEARCH_KEY_WORD).append(NEW_LINE);
            errorInfo.append(VALUE_LEFT).append(param.getLoginName()).append(VALUE_RIGHT).append(NEW_LINE);
            errorInfo.append(NEW_LINE);
        }
        errorInfo.append(SPILT_LINE).append(NEW_LINE);
        errorInfo.append(LOG_END).append(NEW_LINE);
        errorInfo.append(SPILT_LINE).append(NEW_LINE);
        return errorInfo.toString();
    }

    public static void main(String[] args) {
        CreepersParamDTO dto = new CreepersParamDTO();
        dto.putSearchKeyWord("searchKeyWord");
        dto.setErrorPath("errorPath");
        dto.setErrorInfo("errorInfo");
        dto.setTaskType("taskType");
        System.out.println(LogInfoFormat.errorLog(dto));

    }

}
