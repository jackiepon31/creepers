package com.fosun.fc.projects.creepers.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fosun.fc.projects.creepers.utils.PropertiesUtil;

/**
 * 
 * <p>
 * 基础常量等相关常量定义
 * </p>
 * 
 * @author MaXin
 * @since 2016-7-13 15:13:11
 * @see
 */
public class BaseConstant {
    /**
     * 
     * =========================================================================
     * 分割线以下是基础常量定义
     * 
     */
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    public static final String HEXSTR = "0123456789ABCDEF";
    public static final String SUPPER_MAN_CLIENT = "supperManClient";

    public static final String LOGIC_DELETE_FLAG__NO_DELETE = "0";
    public static final String LOGIC_DELETE_FLAG__DELETED = "1";

    public static final String SYSTEM_OS_NAME = "os.name";
    public static final String SYSTEM_TYPE_WINDOWS = "WINDOWS";

    public static final String PARAM_DTO_KEY = "creepersParamDTOMapKey";

    public static final String PARAM_EXTRA_MAP_KEY = "extraMapKey";
    public static final String PARAM_EXTRA_THREAD_NUM = "threadNum";
    public static final String PARAM_EXTRA_CURRENT_PAGE_NO = "pageNo";
    public static final String PARAM_EXTRA_TOTAL_PAGE_NO = "totalPage";

    public static final String PARAM_CHAO_REN_IMAGE_ID = "ChaoRenImageId";

    public static final String HEADER_NAME_LOCATION = "location";

    public static final String PAGE_FILE_INPUTSREAM_STRING = "pageDownloadFileInputStreamString";
    public static final String PAGE_FILE_PATH = "pageDownloadFilePath";
    public static final String PAGE_SEARCH_PARAMS = "searchParams";

    public static final String POST_NAME_VALUE_PAIR = "nameValuePair";
    public static final String POST_HEADER = "headerKey";

    public static final String SELECT_TASK_LIST_TYPE_NAME = "name";
    public static final String SELECT_TASK_LIST_TYPE_VALUE = "value";

    public static final String IMAGE_FILE_BASE_PATH = "/image";
    public static final String IMAGE_FILE_SUFFIX_JPG = ".jpg";
    public static final String FILE_PATH_SEPARATION = "/";
    public static final String FILE_NAME_UNDERLINED = "_";

    public static final String SK_UPLOAD_URL = "sk_upload_url";
    public static final String SK_DOWNLOAD_URL = "sk_download_url";
    public static final String SK_REQUESTCODE = "sk_requestcode";
    public static final String SK_INVOKERNAME = "sk_invokername";
    public static final String SK_BIZ_GROUP = "CREEPERS_IMAGE";
    public static final String SK_SYS_NAME = "projects-creepers";

    public static final String MAIL_SUBJECT = "网络爬虫系统通知邮件";

    public static final String WORD_CONTENT_KEY = "wordContentKey";
    public static final String RESULT_DATA_KEY = "dataResult";

    public static final String TABLE_COLUMN_JSON_CONTENT = "mapJsonContent";
    
    public static SerializerFeature[] features = { SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.DisableCircularReferenceDetect };

    public static List<String> IGNORE_PROPERTIES = new ArrayList<String>() {
        private static final long serialVersionUID = 5624886437735221401L;

        {
            add("class");
            add("currentAuditor");
        }
    };

    public static String[] USER_AGENT_ARRAY = { "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0;",
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)",
            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:2.0.1) Gecko/20100101 Firefox/4.0.1",
            "Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1",
            "Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; en) Presto/2.8.131 Version/11.11",
            "Opera/9.80 (Windows NT 6.1; U; en) Presto/2.8.131 Version/11.11",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon 2.0)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; TencentTraveler 4.0)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; The World)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SE 2.X MetaSr 1.0; SE 2.X MetaSr 1.0; .NET CLR 2.0.50727; SE 2.X MetaSr 1.0)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; 360SE)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Avant Browser)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)",
            "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5",
            "Mozilla/5.0 (iPod; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5",
            "Mozilla/5.0 (iPad; U; CPU OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5",
            "Mozilla/5.0 (Linux; U; Android 2.3.7; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",
            "MQQBrowser/26 Mozilla/5.0 (Linux; U; Android 2.3.7; zh-cn; MB200 Build/GRJ22; CyanogenMod-7) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",
            "Opera/9.80 (Android 2.3.4; Linux; Opera Mobi/build-1107180945; U; en-GB) Presto/2.8.149 Version/11.10",
            "Mozilla/5.0 (Linux; U; Android 3.0; en-us; Xoom Build/HRI39) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13",
            "Mozilla/5.0 (BlackBerry; U; BlackBerry 9800; en) AppleWebKit/534.1+ (KHTML, like Gecko) Version/6.0.0.337 Mobile Safari/534.1+",
            "Mozilla/5.0 (hp-tablet; Linux; hpwOS/3.0.0; U; en-US) AppleWebKit/534.6 (KHTML, like Gecko) wOSBrowser/233.70 Safari/534.6 TouchPad/1.0",
            "Mozilla/5.0 (SymbianOS/9.4; Series60/5.0 NokiaN97-1/20.0.019; Profile/MIDP-2.1 Configuration/CLDC-1.1) AppleWebKit/525 (KHTML, like Gecko) BrowserNG/7.1.18124",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0; HTC; Titan)",
            "UCWEB7.0.2.37/28/999",
            "NOKIA5700/ UCWEB7.0.2.37/28/999",
            "Openwave/ UCWEB7.0.2.37/28/999",
            "Mozilla/4.0 (compatible; MSIE 6.0; ) Opera/UCWEB7.0.2.37/28/999" };

    /**
     * 
     * 分割线以上是基础常量定义
     * =========================================================================
     * 分割线以下是枚举常量定义
     * 
     */

    public static enum JudgeType {

        YES("Y"), NO("N");

        private String code;

        private JudgeType(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return String.valueOf(this.code);
        }
    }

    public static enum LockType {

        YES("1"), NO("0");

        private String code;

        public String getCode() {
            return code;
        }

        private LockType(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return String.valueOf(this.code);
        }
    }

    public static enum PageSizeNum {

        PAGE_FIVE("5"), PAGE_TEN("10");

        private String code;

        private PageSizeNum(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return String.valueOf(this.code);
        }
    }

    public static enum BusinessInfoStatus {
        SUCCEED("0"), ERROR("1"), EXIST("2");
        private String value;

        private BusinessInfoStatus(String str) {
            this.value = str;
        }

        public String getValue() {
            return value;
        }
    }

    public static enum ErrorListFlag {
        SUCCEED("1"), ERROR("2"), DEFAULT("0");
        private String value;

        private ErrorListFlag(String str) {
            this.value = str;
        }

        public String getValue() {
            return value;
        }
    }

    public static enum ImageAnalyticalType {
        URL_PATH("A"), FILE_PATH("B");

        private String value;

        private ImageAnalyticalType(String str) {
            this.value = str;
        }

        public String getValue() {
            return value;
        }
    }

    public static List<Map<String, String>> getTaskListTypeList() {
        return getTaskListTypeList(TaskListParentType.SINGLE_SEARCH);
    }

    public static List<Map<String, String>> getLoginTaskListTypeList() {
        return getTaskListTypeList(TaskListParentType.SINGLE_LOGIN_AND_TARGET_URL);
    }

    public static List<Map<String, String>> getAllDataTaskListTypeList() {
        return getTaskListTypeList(TaskListParentType.SINGLE_SEARCH_ALL_DATA);
    }

    public static List<Map<String, String>> getTaskListTypeList(TaskListParentType type) {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        TaskListType[] taskListType = TaskListType.values();
        for (TaskListType eachType : taskListType) {
            if (eachType.isDisplay && eachType.type.equals(type)) {
                Map<String, String> map = new HashMap<String, String>();
                map.put(SELECT_TASK_LIST_TYPE_NAME, eachType.nameChinese);
                map.put(SELECT_TASK_LIST_TYPE_VALUE, eachType.value);
                result.add(map);
            }
        }
        return result;
    }

    public static enum TaskListParentType {
        // 只查询--参数查询
        SINGLE_SEARCH,
        // 直接爬取全量，无需登录跳转。
        SINGLE_SEARCH_ALL_DATA,
        // 需登陆直接访问目标的url
        SINGLE_LOGIN_AND_TARGET_URL,
        // 需登陆后，到成功登陆后的页面做查询--参数查询
        DOUBLE_LOGIN_SEARCH,
        // 需登陆后，再做查询--参数查询，并且还有访问目标url --复杂逻辑，可以拆分成DOUBLE_LOGIN_SEARCH
        // 和SINGLE_LOGIN_AND_TARGET_URL
        TREBLE_LOGIN_SEARCH_TARGET_URL,
        // 需登陆后，再做查询--参数查询，并且还进入其他目标url进行查询--参数查询
        // --复杂逻辑，可以拆分成DOUBLE_LOGIN_SEARCH 和SINGLE_LOGIN_AND_TARGET_URL
        TREBLE_LOGIN_SEARCH_TARGET_URL_SEARCH,
        // 需登陆后，再做查询--参数查询，并且还进入其他目标url进行查询--参数查询，并且还要访问其他目标url进行查询。
        // --复杂逻辑，可以拆分成两个DOUBLE_LOGIN_SEARCH和一个SINGLE_LOGIN_AND_TARGET_URL
        QUADRUPLE_LOGIN_SEARCH_TARGET_URL_SEARCH;

    }

    // 爬取任务列表，列表类型
    public static enum TaskListType {
        // 法院公告信息
        COURT_LIST("court_announce_list", "法院公告信息"),
        // 法院判决书
        JUDGEMENT_LIST("judgement_list", "法院判决书"),
        // 发明专利信息
        PATENT_LIST("patent_list", "发明专利信息"),
        // 商标信息
        TRADE_MARK_LIST("trade_mark_list", "商标信息"),
        // 失信人
        SHI_XIN_LIST("shi_xin_list", "失信人信息"),
        // 工商注册信息
        BUSI_INFO_LIST("busi_info_list", "工商注册信息", false),
        // 动态代理IP爬取
        PROXY_IP_LIST("proxy_ip_list", "动态代理IP爬取", false),
        // 社保爬取
        INSURANCE_LIST("insurance_list", "上海社保爬取", TaskListParentType.SINGLE_LOGIN_AND_TARGET_URL),
        // 上海公积金爬取
        FUND_LIST("fund_list", "上海公积金爬取", TaskListParentType.SINGLE_LOGIN_AND_TARGET_URL),
        // 限制发行企业债黑名单信息
        COURT_CORP_BONDS_LIST("court_corp_bonds_list", "限制发行企业债黑名单", TaskListParentType.SINGLE_SEARCH_ALL_DATA),
        // 最高法院黑名单信息
        COURT_DISHONESTY_LIST("court_dishonesty_list", "最高法失信人黑名单", TaskListParentType.SINGLE_SEARCH_ALL_DATA),
        // 人行个人征信报告
        CREDIT_REFERENCE_LIST("credit_reference_list", "人行个人征信报告", TaskListParentType.DOUBLE_LOGIN_SEARCH),
        // 导游证信息
        TOUR_GUIDE_LIST("tour_guide_list", "导游证信息"),
        // 导游旅行社黑名单-- task组，不显示
        TOUR_BLACK_LIST("tour_black_list", "导游旅行社黑名单-- task组", TaskListParentType.SINGLE_SEARCH_ALL_DATA),
        // 导游旅行社黑名单
        TOUR_GUIDE_BLACK_LIST("tour_guide_black_list", "导游黑名单", false, TaskListParentType.SINGLE_SEARCH_ALL_DATA),
        // 导游旅行社黑名单
        TOUR_AGENCY_BLACK_LIST("tour_agency_black_list", "旅行社黑名单", false, TaskListParentType.SINGLE_SEARCH_ALL_DATA),
        // 行政处罚信息
        SACTION_LIST("saction_list", "行政处罚黑名单", TaskListParentType.SINGLE_SEARCH_ALL_DATA),
        // 失信被执行人全量信息
        SHI_XIN_ALL_LIST("shi_xin_all_list", "失信被执行人黑名单", TaskListParentType.SINGLE_SEARCH_ALL_DATA),
        // 药品经营许可证
        MEDICAL_OPERATE_LIST("medical_operate_list", "药品经营许可证", TaskListParentType.SINGLE_SEARCH_ALL_DATA),
        // 国家药监局信息-- task组 -- 不显示
        MEDICAL_GROUP("medical_group", "国家药监局信息--  task组", false, TaskListParentType.SINGLE_SEARCH_ALL_DATA),
        // 药品GSP认证
        MEDICAL_GSP_LIST("medical_GSP_list", "药品GSP认证", TaskListParentType.SINGLE_SEARCH_ALL_DATA),
        // 药品GMP认证
        MEDICAL_GMP_LIST("medical_GMP_list", "药品GMP认证", TaskListParentType.SINGLE_SEARCH_ALL_DATA),
        // 偷税漏税信息
        TAX_EVASION_LIST("tax_evasion_list", "偷税漏税黑名单", TaskListParentType.SINGLE_SEARCH_ALL_DATA),
        // 医疗器械注册信息---国产
        MEDICAL_INSTRUMENT_DOMESTIC_LIST("medical_instrument_domestic_list", "国产医疗器械注册信息", TaskListParentType.SINGLE_SEARCH_ALL_DATA),
        // 医疗器械注册信息---进口
        MEDICAL_INSTRUMENT_FOREIGN_LIST("medical_instrument_foreign_list", "进口医疗器械注册信息", TaskListParentType.SINGLE_SEARCH_ALL_DATA);
        // 任务列表类型
        private String value;
        // 管理界面下拉框显示中文名字
        private String nameChinese;
        // 管理界面任务下拉框是否显示
        private boolean isDisplay;
        // 爬取任务分类总大类
        private TaskListParentType type;

        private TaskListType(String str, String name) {
            this.value = str;
            this.nameChinese = name;
            this.isDisplay = true;
            this.type = TaskListParentType.SINGLE_SEARCH;
        }

        private TaskListType(String str, String name, boolean display) {
            this.value = str;
            this.nameChinese = name;
            this.isDisplay = display;
            this.type = TaskListParentType.SINGLE_SEARCH;
        }

        private TaskListType(String str, String name, TaskListParentType type) {
            this.value = str;
            this.nameChinese = name;
            this.isDisplay = true;
            this.type = type;
        }

        private TaskListType(String str, String name, boolean display, TaskListParentType type) {
            this.value = str;
            this.nameChinese = name;
            this.isDisplay = display;
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public String getNameChinese() {
            return nameChinese;
        }

        public boolean getIsDisplay() {
            return isDisplay;
        }

        public TaskListParentType getType() {
            return type;
        }
    }

    // 任务列表任务状态
    public static enum TaskListStatus {
        // 默认状态
        DEFAULT("0"),
        // 爬取成功
        SUCCEED("1"),
        // 爬取失败
        FALSE("2"),
        // 数据删除
        DELETE("3");

        private String value;

        private TaskListStatus(String str) {
            this.value = str;
        }

        public String getValue() {
            return value;
        }
    }

    // CreepersParamDTO中按步骤执行的url的key值
    public static enum OrderUrlKey {
        // 初始爬取页URL-获取cookie用
        INDEX_URL,
        // 获取验证码URL
        CAPTCHA_URL,
        // 执行登录请求的URL
        DO_LOGIN_URL,

        // 中转页面URL--显示中转页面，且页面必须带有信息url
        // 这种链接最多支持5个 超过5个时就不适合用httpClient的post方式模拟请求
        // 需切换webDriver方式进行操作
        TRANSIT_URL_1, TRANSIT_URL_2, TRANSIT_URL_3, TRANSIT_URL_4, TRANSIT_URL_5,
        // 中转后需要请求的最后一个URL
        LAST_COOKIE_URL,
        // Download File url 1
        DOWNLOAD_FILE_URL_1_REGEX,
        // Download File url 2
        DOWNLOAD_FILE_URL_2_REGEX,
        // Download File url 3
        DOWNLOAD_FILE_URL_3_REGEX,
        // Download File url 4
        DOWNLOAD_FILE_URL_4_REGEX,
        // Download File url 5
        DOWNLOAD_FILE_URL_5_REGEX,

        // 所有信息准备好的页面URL--
        // 对于登陆类的，这个页面即登陆成功之后的提示页面
        // 对于非登陆类的，即在这个页面的基础上开始执行targetList的请求
        ALL_READY_URL, END_URL, BREAK_DOWN_URL

    }

    public static enum SupperMan {
        // 识别状态对应表:
        // 识别状态 说明 处理逻辑
        // 0 超时无人打码 重新请求识别或丢弃
        // 1 识别成功 如果验证码识别错误,调用报告错误接口
        // -1 识别失败,超时或者没有传放正确的参数或其它原因 重新请求识别或丢弃
        // -2 余额不足 充值
        // -3 未绑定或者未在绑定机器上运行 登录平台后台,查看是否绑定软件ID
        // -4 时间过期 联系客服
        // -5 用户校验失败 检查用户及密码是否正确
        // -6 文件格式错误,不是图片格式 检查文件,是否为图片格式

        // 解析状态
        SUPPER_MAN_STATUS,
        // 解析结果
        SUPPER_MAN_RESULT,
        // 错误原因
        SUPPER_MAN_ERROR_REASON,
        // 验证码ID
        SUPPER_MAN_IMAGE_ID,
        // 软件ID
        SUPPER_MAN_SOFT_ID,
        // 用户名
        SUPPER_MAN_USER_NAME,
        // 密码
        SUPPER_MAN_PASS_WORD;
    }

    public static enum SupperManType {

        SUCCEED("1"), FAIL("0");

        private String code;

        public String getCode() {
            return code;
        }

        private SupperManType(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return String.valueOf(this.code);
        }
    }

    // 导游、旅行社黑名单类型
    public static enum TouristBlackListType {
        // 旅行社
        TRAVEL_AGENCY("1", "旅行社"),
        // 导游
        TOURIST_GUIDE("2", "导游");

        private String value;
        private String name;

        private TouristBlackListType(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

    // 导游、旅行社黑名单类型下拉框
    public static List<Map<String, String>> getTouristBlackListType() {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        TouristBlackListType[] taskListType = TouristBlackListType.values();
        for (TouristBlackListType eachType : taskListType) {
            Map<String, String> map = new HashMap<String, String>();
            map.put(SELECT_TASK_LIST_TYPE_NAME, eachType.name);
            map.put(SELECT_TASK_LIST_TYPE_VALUE, eachType.value);
            result.add(map);
        }
        return result;
    }

    /**
     * 
     * 分割线以上是枚举常量定义
     * =========================================================================
     * 分割线以下是时间格式化常量定义
     * 
     */
    public static enum DateFormatPatternType {
        // yyyy-MM-dd HH:mm:ss
        TYPE_ONE("yyyy-MM-dd HH:mm:ss"),
        // yyyy.MM.dd HH:mm:ss
        TYPE_TWO("yyyy.MM.dd HH:mm:ss"),
        // yyyy-MM-dd hh:mm:ss
        TYPE_THR("yyyy-MM-dd hh:mm:ss"),
        // yyyy.MM.dd hh:mm:ss
        TYPE_FOR("yyyy.MM.dd hh:mm:ss");
        public String getValue() {
            return value;
        }

        private String value;

        private DateFormatPatternType(String string) {
            this.value = string;
        }
    }

    public static enum DateFormatPatternTypeArr {
        TYPE_ARRAY(new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy.MM.dd HH:mm:ss", "yyyy-MM-dd hh:mm:ss", "yyyy.MM.dd hh:mm:ss", "yyyy-MM-dd", "yyyy年MM月dd日",
                "yyyyMMdd", "yyyy-MM-dd", "yyyy-MM" });
        private String[] arrayString;

        private DateFormatPatternTypeArr(String[] arr) {
            this.arrayString = arr;
        }

        public String[] getArr() {
            return arrayString;
        }

        public String getOne(int i) {
            return arrayString[i];
        }
    }

    /**
     * 
     * 分割线以上是时间格式化常量定义
     * =========================================================================
     * 分割线以下是文件路径常量定义
     * 
     */

    public static enum SeleniumWebDriverPath {
        // config.ini linux
        CONFIG_INI_PATH_LINUX(PropertiesUtil.getApplicationValue("selenium.config.linux")),
        // config.ini windows
        CONFIG_INI_PATH(PropertiesUtil.getApplicationValue("selenium.config.windows")),
        // chrome windows
        CHROME_PATH(PropertiesUtil.getApplicationValue("selenium.chrome.windows")),
        // phantomJS windows
        PHANTOM_JS_PATH(PropertiesUtil.getApplicationValue("selenium.phantomJs.windows")),

        // fireFox linux
        PHANTOM_JS_PATH_LINUX(PropertiesUtil.getApplicationValue("selenium.phantomJs.windows")),

        // chrome linux
        CHROME_PATH_LINUX(PropertiesUtil.getApplicationValue("selenium.phantomJs.linux")),
        // firefox windows
        FIREFOX_PATH(PropertiesUtil.getApplicationValue("selenium.firefox.windows")),
        // firefox windows
        FIREFOX_PATH_LINUX(PropertiesUtil.getApplicationValue("selenium.firefox.linux"));
        private String value;

        private SeleniumWebDriverPath(String str) {
            this.value = str;
        }

        public String getValue() {
            return value;
        }
    }

    public static enum JavaFilePath {
        // CREEPERS_CONSTANT.java 路径
        CREEPERS_CONSTANT(System.getProperty("user.dir").replaceAll("\\\\", "/") + "/src/main/java/com/fosun/fc/projects/creepers/constant/CreepersConstant.java"),

        // dao路径
        CREEPERS_DAO_FILE_PATH(System.getProperty("user.dir").replaceAll("\\\\", "/") + "/src/main/java/com/fosun/fc/projects/creepers/dao/"),

        // dto路径
        CREEPERS_DTO_FILE_PATH(System.getProperty("user.dir").replaceAll("\\\\", "/") + "/src/main/java/com/fosun/fc/projects/creepers/dto/");

        private String value;

        public String getValue() {
            return value;
        }

        private JavaFilePath(String string) {
            this.value = string;
        }
    }

    public static enum FileSuffix {
        // java
        JAVA(".java"),
        // dom文件xml
        XML(".xml"),
        // 页面jsp
        JSP(".jsp"),
        // 页面html
        HTML(".html"),
        // java配置文件properties
        PROPERTIES(".properties"),
        // 词库字典dic
        DIC(".dic"),
        // java打包文件jar
        JAR(".jar"),
        // word文档2003
        DOC(".doc"),
        // word文档2007
        DOCX(".docx"),
        // 数据库导出文件csv
        CSV(".csv"),
        // excel 2007
        XLSX(".xlsx"),
        // excel 2003
        XLS(".xls"),
        // excel 2007 默认带宏命令
        XLSM(".xlsm"),
        // 图片 jpg
        JPG(".jpg"),
        // 图片bmp
        BMP(".bmp");
        private String value;

        public String getValue() {
            return value;
        }

        private FileSuffix(String string) {
            this.value = string;
        }
    }
    /**
     * 
     * 分割线以上是文件路径常量定义
     * =========================================================================
     * 
     * 暂时划分为以上几种类型使用，后续若有其他类型，可参照这个分割格式分割，以确保Constant的代码区分明确。 谢谢合作。 MaXin
     * 2016-7-12 14:14:55
     * 
     */
}
