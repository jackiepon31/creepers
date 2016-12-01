package com.fosun.fc.projects.creepers.service;

import java.util.Map;
/**
 * 
 *<p>
 *description: 人行个人征信报告LIST
 *</p>
 * @author MaXin
 * @since 2016年10月20日
 * @see
 */
public interface ICreepersListCreditService extends BaseService {

    public Map<String, Object> findByUserCodeAndMessageCode(String loginName, String messageCaptchaValue);

    public void updateImagePath(String loginName, String imagePath);

    public void updateHtmlFilePath(String loginName, String filePath);

    public void updateImageAndHtmlPath(String loginName, String imagePath, String filePath);

    public void updateTaskListStatusSucceed(String loginName);

    public void updateTaskListStatus(String loginName, String status);
}
