package com.fosun.fc.projects.creepers.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fosun.fc.projects.creepers.constant.BaseConstant.OrderUrlKey;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;

/**
 * 
 * <p>
 * description:
 *
 * 简单的爬取所需的参数都在这里可以找到
 *
 * 对于有特殊需求的爬取，可以扩展为子类添加属性再使用，以满足定制化的需求。
 *
 * 使用protected类型便于子类继承
 *
 * </p>
 * 
 * @author MaXin
 * @since 2016年9月21日
 * @see
 */
public class CreepersParamDTO extends CreepersBaseDTO {

    private static final long serialVersionUID = 8279993922622840245L;

    private static final String SEARCH_KEY_WORD = "searchKeyWord";
    // 记录状态的基础信息
    protected String taskType = "";// 任务类型
    protected String errorInfo = "";// 错误信息
    protected String errorPath = "";// 错误发生位置
    protected String taskStatus = "";// 任务状态
    protected boolean doRedirect = false;// 是否做返回信息的重定向 -- 默认不作重定向请求
    // 查询的关键字
    protected Map<String, String> searchKeyWord = new HashMap<String, String>();// 查询关键字

    // 链接集合
    protected List<String> targetUrlList = new ArrayList<String>();// 需要爬取的目标页面链接列表

    // 按步骤执行的url
    // 对于登陆类的，查询数据之前的链接都需要在这里设置，直到访问准备就绪页面成功。
    // 对于非登陆类的，只需要设定ALL_READY_URL 即在这个页面的基础上开始执行targetList的请求
    protected Map<OrderUrlKey, String> orderUrl = new HashMap<OrderUrlKey, String>();

    // post请求类的参数
    protected Map<String, String> nameValuePair = new HashMap<String, String>();// post请求参数

    private String breakDownRequest = "";

    public CreepersParamDTO() {
    }

    /**
     * 任务类型
     * 
     * @return the taskType
     */
    public String getTaskType() {
        if (StringUtils.isBlank(taskType)) {
            return StringUtils.EMPTY;
        } else {
            return taskType;
        }
    }

    /**
     * 任务类型
     * 
     * @param taskType
     *            the taskType to set
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    /**
     * 错误信息
     * 
     * @return the errorInfo
     */
    public String getErrorInfo() {
        if (StringUtils.isBlank(errorInfo)) {
            return StringUtils.EMPTY;
        } else {
            return errorInfo;
        }
    }

    /**
     * 错误信息
     * 
     * @param errorInfo
     *            the errorInfo to set
     */
    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    /**
     * 错误发生位置
     * 
     * @return the errorPath
     */
    public String getErrorPath() {
        if (StringUtils.isBlank(errorPath)) {
            return StringUtils.EMPTY;
        } else {
            return errorPath;
        }
    }

    /**
     * 错误发生位置
     * 
     * @param errorPath
     *            the errorPath to set
     */
    public void setErrorPath(String errorPath) {
        this.errorPath = errorPath;
    }

    /**
     * 任务状态
     * 
     * @return the taskStatus
     */
    public String getTaskStatus() {
        if (StringUtils.isBlank(taskStatus)) {
            return StringUtils.EMPTY;
        } else {
            return taskStatus;
        }
    }

    /**
     * 任务状态
     * 
     * @param taskStatus
     *            the taskStatus to set
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * 是否做返回信息的重定向 -- 默认不作重定向请求
     * 
     * @return the doRedirect
     */
    public boolean isDoRedirect() {
        return doRedirect;
    }

    /**
     * 是否做返回信息的重定向 -- 默认不作重定向请求
     * 
     * @param doRedirect
     *            the doRedirect to set
     */
    public void setDoRedirect(boolean doRedirect) {
        this.doRedirect = doRedirect;
    }

    /**
     * 查询关键字
     * 
     * 获取查询关键字 -- by Map<String ,String > searchKeyWord.get("searchKeyWord")
     * 
     * @return the searchKeyWord
     */
    public Map<String, String> getSearchKeyWord() {
        if (CommonMethodUtils.isEmpty(searchKeyWord)) {
            return Collections.emptyMap();
        } else {
            return searchKeyWord;
        }
    }

    /**
     * 查询关键字
     * 
     * 获取查询关键字 -- by Map<String ,String > searchKeyWord
     * 
     * @return the searchKeyWord
     */
    public String getSearchKeyWordForString() {
        if (CommonMethodUtils.isEmpty(searchKeyWord)
                || !searchKeyWord.containsKey(SEARCH_KEY_WORD) || (searchKeyWord.containsKey(SEARCH_KEY_WORD)
                        && StringUtils.isBlank(searchKeyWord.get(SEARCH_KEY_WORD)))) {
            return StringUtils.EMPTY;
        } else {
            return searchKeyWord.get(SEARCH_KEY_WORD);
        }
    }

    /**
     * 查询关键字
     * 
     * 获取查询关键字 -- by Map<String ,String > searchKeyWord.get(key)
     * 
     * @param key
     *            参数在map中的key
     * @return the searchKeyWord
     */
    public String getSearchKeyWord(String key) {
        if (CommonMethodUtils.isEmpty(searchKeyWord)
                || StringUtils.isBlank(key) || !searchKeyWord.containsKey(key)
                || (searchKeyWord.containsKey(key) && StringUtils.isBlank(searchKeyWord.get(key)))) {
            return StringUtils.EMPTY;
        } else {
            return searchKeyWord.get(key);
        }
    }

    /**
     * 查询关键字
     * 
     * 设置查询关键字 -- by Map<String ,String > searchKeyWord = searchMap
     * 
     * @param searchKeyWord
     *            the searchKeyWord to set
     */
    public void setSearchKeyWord(Map<String, String> searchMap) {
        this.searchKeyWord = searchMap;
    }

    /**
     * 查询关键字
     * 
     * 设置查询关键字 -- by Map<String ,String >
     * searchKeyWord.put("searchKeyWord",value)
     * 
     * @param searchKeyWord
     *            the searchKeyWord to set
     */
    public void putSearchKeyWord(String searchKeyWord) {
        this.searchKeyWord.put(SEARCH_KEY_WORD, searchKeyWord);
    }

    /**
     * 查询关键字
     * 
     * 增加查询关键字 -- by Map<String ,String > searchKeyWord.putAll(searchMap)
     * 
     * @param searchMap
     *            填充好数值的Map<String ,String >类型的MAP
     * 
     */
    public void putSearchKeyWord(Map<String, String> searchMap) {
        this.searchKeyWord.putAll(searchMap);
    }

    /**
     * 查询关键字
     * 
     * 增加查询关键字 -- by Map<String ,String > searchKeyWord.put(key,value)
     * 
     * @param key
     * @param value
     */
    public void putSearchKeyWord(String key, String value) {
        searchKeyWord.put(key, value);
    }

    /**
     * 需要爬取的目标页面链接列表
     * 
     * @return the targetUrlList
     */
    public List<String> getTargetUrlList() {
        if (CommonMethodUtils.isEmpty(targetUrlList)) {
            return new ArrayList<String>();
        } else {
            return targetUrlList;
        }
    }

    /**
     * 需要爬取的目标页面链接列表
     * 
     * @param targetUrlList
     *            the targetUrlList to set
     */
    public void setTargetUrlList(List<String> targetUrlList) {
        if (CommonMethodUtils.isEmpty(targetUrlList)) {
            return;
        }
        this.targetUrlList = targetUrlList;
    }

    /**
     * 需要爬取的目标页面链接列表
     * 
     * @param targetUrlList
     *            the targetUrlList to put
     */
    public void putTargetUrlList(String targetUrl) {
        if (StringUtils.isBlank(targetUrl)) {
            return;
        }
        this.targetUrlList.add(targetUrl);
    }

    /**
     * post请求参数
     * 
     * @return the nameValuePair
     */
    public Map<String, String> getNameValuePair() {
        if (CommonMethodUtils.isEmpty(nameValuePair)) {
            return new HashMap<String, String>();
        } else {
            return nameValuePair;
        }
    }

    /**
     * post请求参数
     * 
     * @param nameValuePair
     *            the nameValuePair to set
     */
    public void setNameValuePair(Map<String, String> nameValuePair) {
        if (CommonMethodUtils.isEmpty(nameValuePair)) {
            return;
        }
        this.nameValuePair = nameValuePair;
    }

    /**
     * post请求参数
     * 
     * @param nameValuePair
     *            the nameValuePair to put
     */
    public void putNameValuePair(String key, String value) {
        this.nameValuePair.put(key, value);
    }

    /**
     * post请求参数
     * 
     * @return the nameValuePair value
     */
    public String getNameValuePair(String key) {
        String value = this.nameValuePair.get(key);
        if (StringUtils.isBlank(value)) {
            return StringUtils.EMPTY;
        } else {
            return value;
        }
    }

    /**
     * 按步骤执行的url
     * 
     * @return the orderUrl
     */
    public Map<OrderUrlKey, String> getOrderUrl() {
        if (CommonMethodUtils.isEmpty(orderUrl)) {
            return new HashMap<OrderUrlKey, String>();
        } else {
            return orderUrl;
        }
    }

    /**
     * 按步骤执行的url
     * 
     * @param orderUrl
     *            the orderUrl to set
     */
    public void setOrderUrl(Map<OrderUrlKey, String> orderUrl) {
        if (CommonMethodUtils.isEmpty(orderUrl)) {
            return;
        }
        this.orderUrl = orderUrl;
    }

    /**
     * 按步骤执行的url
     * 
     * @param orderUrl
     *            the orderUrl to put
     */
    public void putOrderUrl(OrderUrlKey order, String url) {
        orderUrl.put(order, url);
    }

    /**
     * 按步骤执行的url
     * 
     * @return the orderUrl
     */
    public String getOrderUrl(OrderUrlKey order) {
        String url = orderUrl.get(order);
        if (StringUtils.isBlank(url)) {
            return StringUtils.EMPTY;
        } else {
            return url;
        }
    }

    public String getBreakDownRequest() {
        return breakDownRequest;
    }

    public void setBreakDownRequest(String breakDownRequest) {
        this.breakDownRequest = breakDownRequest;
    }

}
