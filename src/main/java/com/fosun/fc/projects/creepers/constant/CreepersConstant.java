package com.fosun.fc.projects.creepers.constant;

/**
 * 
 * <p>
 * 数据库表、字段等相关常量定义
 * </p>
 * 
 * @author MaXin
 * @since 2016-7-13 15:12:00
 * @see
 */
public class CreepersConstant {

    /**
     * 
     * =========================================================================
     * 分割线以下是数据库表名及类型常量定义
     * 
     */

    public static enum TableNamesBusinessInfo {
        // 区域列表
        T_CREEPERS_AREA("tCreepersArea", false),
        // 爬虫信息-企业资产状况信息
        T_CREEPERS_ENT_ASSET("tCreepersEntAsset", false),
        // 爬虫信息-企业公示基本信息
        T_CREEPERS_ENT_BASIC("tCreepersEntBasic", false),
        // 爬虫信息-企业公示修改信息
        T_CREEPERS_ENT_CHANGE("tCreepersEntChange", true),
        // 爬虫信息-企业公示股权变更信息
        T_CREEPERS_ENT_EQUITY("tCreepersEntEquity", true),
        // 爬虫信息-企业公示知识产权出质登记信息
        T_CREEPERS_ENT_INTEL("tCreepersEntIntel", true),
        // 爬虫信息-企业公示对外投资信息
        T_CREEPERS_ENT_INVEST("tCreepersEntInvest", true),
        // 爬虫信息-企业公示行政许可信息
        T_CREEPERS_ENT_LICENSE("tCreepersEntLicense", true),
        // 爬虫信息-企业公示股东及出资信息
        T_CREEPERS_ENT_SHARE("tCreepersEntShare", true),
        // 爬虫信息-股权变更信息
        T_CREEPERS_ENT_SHARE_CHANGE("tCreepersEntShareChange", true),
        // 爬虫信息-企业公示对外提供保证担保信息
        T_CREEPERS_ENT_WARRANT("tCreepersEntWarrant", true),
        // 爬虫信息-企业公示网站网址信息
        T_CREEPERS_ENT_WEB("tCreepersEntWeb", true),
        // 爬虫信息-经营异常信息
        T_CREEPERS_MER_ABNORMAL("tCreepersMerAbnormal", true),
        // 爬虫信息-工商注册基本信息
        T_CREEPERS_MER_BASIC("tCreepersMerBasic", false),
        // 爬虫信息-分支机构信息
        T_CREEPERS_MER_BRANCH("tCreepersMerBranch", true),
        // 爬虫信息-工商变更信息
        T_CREEPERS_MER_CHANGE("tCreepersMerChange", true),
        // 爬虫信息-抽查检查信息
        T_CREEPERS_MER_CHECK("tCreepersMerCheck", true),
        // 爬虫信息-严重违法信息
        T_CREEPERS_MER_ILLEGAL("tCreepersMerIllegal", true),
        // 爬虫信息-主要人员信息
        T_CREEPERS_MER_KEYMAN("tCreepersMerKeyman", true),
        // 爬虫信息-行政处罚信息
        T_CREEPERS_MER_PENALTY("tCreepersMerPenalty", true),
        // 爬虫信息-股权出质登记信息
        T_CREEPERS_MER_PLEDGE("tCreepersMerPledge", true),
        // 爬虫信息-动产抵押登记信息信息
        T_CREEPERS_MER_PROPERTY("tCreepersMerProperty", true),
        // 爬虫信息-股东信息
        T_CREEPERS_MER_SHAREHOLDER("tCreepersMerShareholder", true),
        // 爬虫信息-工商撤销信息
        T_CREEPERS_MER_UNDO("tCreepersMerUndo", true),
        // 爬虫信息-司法股权冻结信息
        T_CREEPERS_OTHER_FREEZE("tCreepersOtherFreeze", true),
        // 爬虫信息-司法股东变更登记信息
        T_CREEPERS_OTHER_SHARE_CHANGE("tCreepersOtherShareChange", true),
        // 爬虫工商注册号临时列表
        T_CREEPERS_TEMP("tCreepersTemp", false);

        private TableNamesBusinessInfo(String string, boolean isList) {
            this.value = string;
            this.isList = isList;
        }

        private String value;

        public String getMapKey() {
            return value;
        }

        private boolean isList;

        public boolean isList() {
            return isList;
        }

    }

    public static enum TableNamesCreditReference {
        // 简版征信账号信息表
        T_CREEPERS_ACCOUNT_BAK("tCreepersAccountBak", false),
        // 简版征信资产处置信息表
        T_CREEPERS_ASSET_HANDLE("tCreepersAssetHandle", true),
        // 简版征信基本信息表
        T_CREEPERS_BASIC("tCreepersBasic", false),
        // 简版征信行用卡明细信息表
        T_CREEPERS_CC_DETAIL("tCreepersCcDetail", true),
        // 简版征信保证人代偿信息表
        T_CREEPERS_COMPENSATORY("tCreepersCompensatory", true),
        // 数据字典表
        T_CREEPERS_DICT("tCreepersDict", false),
        // 简版征信概要信息表
        T_CREEPERS_GENERAL("tCreepersGeneral", false),
        // 简版征信为他人担保信息表
        T_CREEPERS_GUARANTEE("tCreepersGuarantee", true),
        // 简版征信住房贷款明细信息表
        T_CREEPERS_HL_DETAIL("tCreepersHlDetail", true),
        // 简版征信其他贷款明细信息表
        T_CREEPERS_OL_DETAIL("tCreepersOlDetail", true),
        // 简版征信民事判决记录信息表
        T_CREEPERS_PUBLIC_CIVIL("tCreepersPublicCivil", true),
        // 简版征信强制执行记录信息表
        T_CREEPERS_PUBLIC_ENFORCEMENT("tCreepersPublicEnforcement", true),
        // 简版征信电信欠费记录信息表
        T_CREEPERS_PUBLIC_ISP("tCreepersPublicIsp", true),
        // 简版征信行政处罚记录信息表
        T_CREEPERS_PUBLIC_SANCTION("tCreepersPublicSanction", true),
        // 简版征信欠税记录信息表
        T_CREEPERS_PUBLIC_TAX("tCreepersPublicTax", true),
        // 简版征信查询记录信息表
        T_CREEPERS_QUERY_LOG("tCreepersQueryLog", true);

        private TableNamesCreditReference(String string, boolean isList) {
            this.value = string;
            this.isList = isList;
        }

        private String value;

        public String getMapKey() {
            return value;
        }

        private boolean isList;

        public boolean isList() {
            return isList;
        }
    }

    public static enum TableNamesCreditChina {
        // 失信人
        T_CREEPERS_SHIXIN("tCreepersShixin", true),
        // 法院黑名单
        T_CREEPERS_COURT_DISHONEST("tCreepersCourtDishonest", true),
        // 法院黑名单明细
        T_CREEPERS_COURT_DIS_INFO("tCreepersCourtDisInfo", true),
        // 限制发行企业债黑名单
        T_CREEPERS_COURT_CORP_BONDS("tCreepersCourtCorpBonds", true),
        // 行政处罚黑名单
        T_CREEPERS_SACTION("tCreepersSaction", true),
        // 失信被执行人黑名单
        T_CREEPERS_SHIXIN_ALL("tCreepersShixinAll", true),
        // 偷税漏税黑名单
        T_CREEPERS_TAX_EVASION("tCreepersTaxEvasion", true),
        // 偷税漏税黑名单明细
        T_CREEPERS_TAX_EVASION_DETAIL("tCreepersTaxEvasionDetail", true);

        private String value;
        private boolean isList;

        private TableNamesCreditChina(String value, boolean isList) {
            this.value = value;
            this.isList = isList;
        }

        public String getMapKey() {
            return value;
        }

        public boolean isList() {
            return isList;
        }
    }

    public static enum TableNamesOthers {
        // 爬虫异常列表
        T_CREEPERS_ERROR_LIST("tCreepersErrorList", false),
        // 爬虫队列指针信息表
        T_CREEPERS_INDEX("tCreepersIndex", false),
        // 法院公告信息表
        T_CREEPERS_COURT_ANNOUNCE("tCreepersCourtAnnounce", false),
        // 爬虫信息-海关信息
        T_CREEPERS_CUSTOMS("tCreepersCustoms", false),
        // 爬虫信息-法院文书信息
        T_CREEPERS_JUDGEMENT("tCreepersJudgement", false),
        // 爬虫信息列表
        T_CREEPERS_LIST("tCreepersList", false),
        // 爬虫信息-专利信息
        T_CREEPERS_PATENT("tCreepersPatent", false),
        // 爬虫动态代理IP列表
        T_CREEPERS_PROXY_LIST("tCreepersProxyList", true),
        // 爬虫信息-商标信息
        T_CREEPERS_TRADE_MARK("tCreepersTradeMark", false),
        // 信用中国-导游信息
        T_CREEPERS_TOUR_GUIDE("tCreepersTourGuide", false),
        // 医药类信息
        T_CREEPERS_MEDICAL("tCreepersMedical", false),
        // 导游，旅行社黑名单
        T_CREEPERS_TOUR_BLACK_LIST("tCreepersTourBlackList", false);

        private TableNamesOthers(String string, boolean isList) {
            this.value = string;
            this.isList = isList;
        }

        private String value;

        public String getMapKey() {
            return value;
        }

        private boolean isList;

        public boolean isList() {
            return isList;
        }

    }

    public static enum TableNamesListName {
        // 爬虫任务队列-法院公告信息
        T_CREEPERS_LIST_COURT("tCreepersListCourt", true),
        // 爬虫任务队列-裁判文书信息
        T_CREEPERS_LIST_JUDGEMENT("tCreepersListJudgement", true),
        // 爬虫任务队列-专利信息
        T_CREEPERS_LIST_PATENT("tCreepersListPatent", true),
        // 爬虫任务队列-商标信息
        T_CREEPERS_LIST_TRADE_MARK("tCreepersListTradeMark", true),
        // 爬虫任务队列-人行个人征信报告
        T_CREEPERS_LIST_CREDIT("tCreepersListCredit", true);

        private TableNamesListName(String string, boolean isList) {
            this.value = string;
            this.isList = isList;
        }

        private String value;

        public String getMapKey() {
            return value;
        }

        private boolean isList;

        public boolean isList() {
            return isList;
        }

    }

    public static enum TableNamesFund {
        // 公积金账户信息表
        T_CREEPERS_FUND_BASIC("tCreepersFundBasic", false),
        // 公积金账户本年度明细表
        T_CREEPERS_FUND_BASIC_DETAIL("tCreepersFundBasicDetail", true),
        // 补充公积金账户信息表
        T_CREEPERS_FUND_EXTRA("tCreepersFundExtra", false),
        // 补充公积金账户本年度明细表
        T_CREEPERS_FUND_EXTRA_DETAIL("tCreepersFundExtraDetail", true),
        // 爬虫任务队列-公积金信息
        T_CREEPERS_LIST_FUND("tCreepersListFund", true);

        private TableNamesFund(String string, boolean isList) {
            this.value = string;
            this.isList = isList;
        }

        private String value;

        public String getMapKey() {
            return value;
        }

        private boolean isList;

        public boolean isList() {
            return isList;
        }

    }

    public static enum TableNamesInsurance {
        // 社保基础信息表
        T_CREEPERS_INSURANCE_BASIC("tCreepersInsuranceBasic", false),
        // 社保应缴记录表
        T_CREEPERS_INSURANCE_PAYMENT("tCreepersInsurancePayment", true),
        // 社保累计缴费信息表
        T_CREEPERS_INSURANCE_SUM("tCreepersInsuranceSum", true),
        // 公积金账户信息表
        T_CREEPERS_INSURANCE_UNIT("tCreepersInsuranceUnit", true),
        // 爬虫任务队列-社保信息
        T_CREEPERS_LIST_INSURANCE("tCreepersListInsurance", false);

        private TableNamesInsurance(String string, boolean isList) {
            this.value = string;
            this.isList = isList;
        }

        private String value;

        public String getMapKey() {
            return value;
        }

        private boolean isList;

        public boolean isList() {
            return isList;
        }

    }

    /**
     * 
     * 分割线以上是数据库表名及类型常量定义
     * =========================================================================
     * 分割线以下是数据库表字段名常量定义
     * 
     */

    public static enum TCreepersAccountBakColumn {// 简版征信账号信息表
        // 主键
        ID("id"),
        // 报告编码
        RPT_NO("rptNo"),
        // 用户名
        USR("usr"),
        // 密码
        PWD("pwd"),
        // 验证码
        CDE("cde"),
        // 执行状态
        STATUS("status"),
        // html文件路径
        FILE_PATH("filePath"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersAccountBakColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersAreaColumn {// 区域列表
        // 主键
        ID("id"),
        // 区域编码
        CODE("code"),
        // 区域名称
        NAME("name"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersAreaColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersAssetHandleColumn {// 简版征信资产处置信息表
        // 主键
        ID("id"),
        // 报告编码
        RPT_NO("rptNo"),
        // 资产处置日期
        ASSET_DISPOSAL_DT("assetDisposalDt"),
        // 资产处置机构
        ASSET_DISPOSAL_ORG("assetDisposalOrg"),
        // 资产处置金额
        ASSET_DISPOSAL_AMOUNT("assetDisposalAmount"),
        // 最近一次还款日期
        LAST_BACK_DT("lastBackDt"),
        // 余额
        BALANCE("balance"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersAssetHandleColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersBasicColumn {// 简版征信基本信息表
        // 主键
        ID("id"),
        // 报告编码
        RPT_NO("rptNo"),
        // 查询时间
        QUERY_DT("queryDt"),
        // 报告时间
        RPT_DT("rptDt"),
        // 姓名
        NAME("name"),
        // 证件类型
        ID_TYPE("idType"),
        // 证件号码
        ID_NO("idNo"),
        // 婚姻状况
        MARITAL_STATUS("maritalStatus"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersBasicColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersCcDetailColumn {// 简版征信行用卡明细信息表
        // 主键
        ID("id"),
        // 报告编码
        RPT_NO("rptNo"),
        // 信用卡类型
        CC_TYPE("ccType"),
        // 账户类型
        ACCOUNT_TYPE("accountType"),
        // 发放日期
        GRANT_DT("grantDt"),
        // 发放机构
        GRANT_ORG("grantOrg"),
        // 统计日期
        STATISTICAL_DT("statisticalDt"),
        // 状态
        CC_STATUS("ccStatus"),
        // 信用额度
        CREDIT_LIMIT("creditLimit"),
        // 已使用额度/透支余额
        OVERDRAFT_BALANCE("overdraftBalance"),
        // 逾期金额
        OVERDUE_AMOUNT("overdueAmount"),
        // 最近5年逾期月份数/准贷记卡透支60的月份数
        CC_OVERDRAFT_SIXTY("ccOverdraftSixty"),
        // 最近5年逾期超过90天的月份数
        CC_OVERDRAFT_NINETY("ccOverdraftNinety"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersCcDetailColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersCompensatoryColumn {// 简版征信保证人代偿信息表
        // 主键
        ID("id"),
        // 报告编码
        RPT_NO("rptNo"),
        // 最近一次保证人代偿日期
        LAST_COMPENSATORY_DT("lastCompensatoryDt"),
        // 保证人代偿机构
        COMPENSATORY_ORG("compensatoryOrg"),
        // 累积代偿金额
        COMPENSATION_AMOUNT("compensationAmount"),
        // 最近一次还款日期
        LAST_BACK_DT("lastBackDt"),
        // 余额
        BALANCE("balance"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersCompensatoryColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersCourtAnnounceColumn {// 法院公告信息表
        // 主键
        ID("id"),
        // 商户名称
        MER_NAME("merName"),
        // 公告类型
        ANNOUNCE_TYPE("announceType"),
        // 法院
        COURT_NAME("courtName"),
        // 公告内容
        ANNOUNCE_CONTENT("announceContent"),
        // 公告日期
        ANNOUNCE_DT("announceDt"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersCourtAnnounceColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersCustomsColumn {// 爬虫信息-海关信息
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo"),
        // 企业名称
        MER_NAME("merName"),
        // 海关编码
        CUSTOM_CODE("customCode"),
        // 报关类别
        DECLARE_TYPE("declareType"),
        // 企业管理类别
        MER_TYPE("merType"),
        // 注册有效日期
        REG_VALID_DT("regValidDt"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersCustomsColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersDictColumn {// 数据字典表
        // 主键
        ID("id"),
        // 定义key1值
        KEY1("key1"),
        // 定义value1值
        VALUE1("value1"),
        // 定义key2值
        KEY2("key2"),
        // 定义value2值
        VALUE2("value2"),
        // 字典分类识别码
        CATEGORY("category"),
        // 开始日期
        START_DT("startDt"),
        // 结束日期
        END_DT("endDt"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersDictColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersEntAssetColumn {// 爬虫信息-企业资产状况信息
        // 主键
        ID("id"),
        // 注册号/统一社会信用代码
        MER_NO("merNo"),
        // 企业资产状况信息
        STATUS("status"),
        // 资产总额
        TOTAL_ASSET("totalAsset"),
        // 营业总收入
        TOTAL_INCOME("totalIncome"),
        // 营业总收入中主营业务收入
        TOTAL_BUS_INCOME("totalBusIncome"),
        // 纳税总额
        TOTAL_TAX("totalTax"),
        // 所有者权益合计
        TOTAL_EQUITY("totalEquity"),
        // 利润总额
        TOTAL_AVENUE("totalAvenue"),
        // 净利润
        NET_PROFIT("netProfit"),
        // 负债总额
        TOTAL_LIABILITIES("totalLiabilities"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersEntAssetColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersEntBasicColumn {// 爬虫信息-企业公示基本信息
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo"),
        // 公司名称
        MER_NAME("merName"),
        // 企业联系电话
        PHONE("phone"),
        // 邮政编码
        POST_CODE("postCode"),
        // 企业通信地址
        ADDR("addr"), EMAIL("email"),
        // 有限责任公司本年度是否发生股东股权转让
        IS_TRANSFER("isTransfer"),
        // 企业经营状态
        STATUS("status"),
        // 是否有网站或网点
        IS_WEBSITE("isWebsite"),
        // 企业是否有投资信息或购买其他公司股权
        IS_INVEST("isInvest"),
        // 从业人数
        CREW_NUMBER("crewNumber"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersEntBasicColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersEntChangeColumn {// 爬虫信息-企业公示修改信息
        // 主键
        ID("id"),
        // 注册号/统一社会信用代码
        MER_NO("merNo"),
        // 序号
        SEQ_NO("seqNo"),
        // 修改事项
        ITEM("item"),
        // 修改前
        PRE_INFO("preInfo"),
        // 修改后
        POST_INFO("postInfo"),
        // 修改时间
        CHANGE_DT("changeDt"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersEntChangeColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersEntEquityColumn {// 爬虫信息-企业公示股权变更信息
        // 主键
        ID("id"),
        // 注册号/统一社会信用代码
        MER_NO("merNo"),
        // 股东
        SHARE_HOLDER("shareHolder"),
        // 变更前股权比例
        PRE_RATE("preRate"),
        // 变更后股权比例
        POST_RATE("postRate"),
        // 股权变更日期
        CHANGE_DT("changeDt"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersEntEquityColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersEntIntelColumn {// 爬虫信息-企业公示知识产权出质登记信息
        // 主键
        ID("id"),
        // 注册号/统一社会信用代码
        MER_NO("merNo"),
        // 序号
        SEQ_NO("seqNo"),
        // 名称
        NAME("name"),
        // 种类
        TYPE("type"),
        // 出质人名称
        QUALITY_NAME("qualityName"),
        // 质权人名称
        PLEDGEE_NAME("pledgeeName"),
        // 质权登记期限
        PLEDGEE_PERIOD("pledgeePeriod"),
        // 状态
        STATUS("status"),
        // 详情
        DETAIL("detail"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersEntIntelColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersEntInvestColumn {// 爬虫信息-企业公示对外投资信息
        // 主键
        ID("id"),
        // 注册号/统一社会信用代码
        MER_NO("merNo"),
        // 投资设立企业或购买股权企业名称
        INVESTED_NAME("investedName"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersEntInvestColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersEntLicenseColumn {// 爬虫信息-企业公示行政许可信息
        // 主键
        ID("id"),
        // 注册号/统一社会信用代码
        MER_NO("merNo"),
        // 序号
        SEQ_NO("seqNo"),
        // 许可文件编号
        LICENSE_NO("licenseNo"),
        // 许可文件名称
        LICENSE_NAME("licenseName"),
        // 开始时间
        EFF_DT("effDt"),
        // 结束时间
        EXP_DT("expDt"),
        // 许可机关
        AUTHORITY("authority"),
        // 许可内容
        CONTENT("content"),
        // 状态
        STATUS("status"),
        // 详情
        DETAIL("detail"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersEntLicenseColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersEntShareColumn {// 爬虫信息-企业公示股东及出资信息
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo"),
        // 股东
        SHAREHOLDER("shareholder"),
        // 认缴出资额
        SUB_CAPITAL("subCapital"),
        // 认缴出资类型
        SUB_TYPE("subType"),
        // 认缴出资时间
        SUB_DT("subDt"),
        // 实缴出资额
        REAL_CAPITAL("realCapital"),
        // 实缴出资时间
        REAL_DT("realDt"),
        // 实缴出资方式
        REAL_TYPE("realType"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersEntShareColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersEntShareChangeColumn {// 爬虫信息-股权变更信息
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo"),
        // 商户名称
        MER_NAME("merName"),
        // 序号
        SEQ_NO("seqNo"),
        // 变更事宜
        CHANGE_CONTENT("changeContent"),
        // 变更前
        CHANGE_OLD("changeOld"),
        // 变更后
        CHANGE_NEW("changeNew"),
        // 版本变更日期
        CHANGE_DT("changeDt"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersEntShareChangeColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersEntWarrantColumn {// 爬虫信息-企业公示对外提供保证担保信息
        // 主键
        ID("id"),
        // 注册号/统一社会信用代码
        MER_NO("merNo"),
        // 债权人
        CREDITOR("creditor"),
        // 债务人
        OBLIGOR("obligor"),
        // 主债权种类
        RIGHTS_TYPE("rightsType"),
        // 主债权数额
        RIGHTS_AMOUNT("rightsAmount"),
        // 履行债务的期限
        DEBT_PERIOD("debtPeriod"),
        // 保证的期间
        WARRANT_PERIOD("warrantPeriod"),
        // 保证的方式
        WARRANT_TYPE("warrantType"),
        // 保证担保的范围
        WARRANT_SCOPE("warrantScope"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersEntWarrantColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersEntWebColumn {// 爬虫信息-企业公示网站网址信息
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo"),
        // 类型
        TYPE("type"),
        // 名称
        NAME("name"),
        // 网址
        URL("url"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersEntWebColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersErrorListColumn {// 爬虫异常列表
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo"),
        // 商户名称
        MER_NAME("merName"),
        // 备注信息
        MEMO("memo"),
        // 默认：0；成功：1；失败：2
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersErrorListColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersGeneralColumn {// 简版征信概要信息表
        // 主键
        ID("id"),
        // 报告编码
        RPT_NO("rptNo"),
        // 资产处置笔数
        ASSET_DISPOSAL_NO("assetDisposalNo"),
        // 保证人代偿笔数
        GUARANTOR_COMPENSATION_NO("guarantorCompensationNo"),
        // 信用卡账户数
        CC_NO("ccNo"),
        // 住房贷款账户数
        HOUSING_LOAN_NO("housingLoanNo"),
        // 其它贷款账户数
        OTHER_LOAN_NO("otherLoanNo"),
        // 未结清/未销户信用卡账户数
        OUTSTANDING_CC_NO("outstandingCcNo"),
        // 未结清/未销户住房贷款账户数
        OUTSTANDING_HOUSING_LOAN_NO("outstandingHousingLoanNo"),
        // 未结清/未销户其它贷款账户数
        OUTSTANDING_OTHER_LOAN_NO("outstandingOtherLoanNo"),
        // 发生过逾期的信用卡账户数
        OVERDUE_CC_NO("overdueCcNo"),
        // 发生过逾期的住房贷款账户数
        OVERDUE_HOUSING_LOAN_NO("overdueHousingLoanNo"),
        // 发生过逾期的其它贷款账户数
        OVERDUE_OTHER_LOAN_NO("overdueOtherLoanNo"),
        // 发生过90天以上逾期的信用卡账户数
        NINETY_CC_NO("ninetyCcNo"),
        // 发生过90天以上逾期的住房贷款账户数
        NINETY_HOUSING_LOAN_NO("ninetyHousingLoanNo"),
        // 发生过90天以上逾期的其它贷款账户数
        NINETY_OTHER_LOAN_NO("ninetyOtherLoanNo"),
        // 为他人担保的信用卡笔数
        GUARANTEE_CC_NO("guaranteeCcNo"),
        // 为他人担保的住房贷款笔数
        GUARANTEE_HOUSING_LOAN_NO("guaranteeHousingLoanNo"),
        // 为他人担保的其它贷款笔数
        GUARANTEE_OTHER_LOAN_NO("guaranteeOtherLoanNo"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersGeneralColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersGuaranteeColumn {// 简版征信为他人担保信息表
        // 主键
        ID("id"),
        // 报告编码
        RPT_NO("rptNo"),
        // 被担保人姓名
        INSURED_NAME("insuredName"),
        // 被担保人证件类型
        INSURED_ID_TYPE("insuredIdType"),
        // 被担保人证件号码
        INSURED_ID_NO("insuredIdNo"),
        // 贷款机构
        LOAN_ORG("loanOrg"),
        // 担保合同金额
        GUARANTEE_CONTRACT_AMOUNT("guaranteeContractAmount"),
        // 担保金额
        GUARANTEET_AMOUNT("guaranteetAmount"),
        // 统计日期
        STATISTICAL_DT("statisticalDt"),
        // 担保本金余额
        GUARANTEED_PRINCIPAL_BALANCE("guaranteedPrincipalBalance"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersGuaranteeColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersHlDetailColumn {// 简版征信住房贷款明细信息表
        // 主键
        ID("id"),
        // 报告编码
        RPT_NO("rptNo"),
        // 发放日期
        GRANT_DT("grantDt"),
        // 发放机构
        GRANT_ORG("grantOrg"),
        // 贷款类型
        LOAN_TYPE("loanType"),
        // 贷款金额
        LOAN_AMOUNT("loanAmount"),
        // 货币类型
        CURRENCY_TYPE("currencyType"),
        // 贷款到期日期
        LOAN_MATURITY_DT("loanMaturityDt"),
        // 统计日期
        STATISTICAL_DT("statisticalDt"),
        // 状态
        HL_STATUS("hlStatus"),
        // 余额
        BALANCE("balance"),
        // 逾期金额
        OVERDUE_AMOUNT("overdueAmount"),
        // 最近5年逾期月份数/准贷记卡透支60的月份数
        HL_OVERDRAFT_SIXTY("hlOverdraftSixty"),
        // 最近5年逾期超过90天的月份数
        HL_OVERDRAFT_NINETY("hlOverdraftNinety"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersHlDetailColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersIndexColumn {// 爬虫队列指针信息表
        // 主键
        ID("id"),
        // 当前号码
        SEQ_NO("seqNo");
        private TCreepersIndexColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersJudgementColumn {// 爬虫信息-法院文书信息
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo"),
        // 工商注册名称
        MER_NAME("merName"),
        // 归档编号
        DOC_ID("docId"),
        // 审理法院
        COURT("court"),
        // 案件编号
        CASE_NO("caseNo"),
        // 案件标题
        CASE_TITLE("caseTitle"),
        // 案件日期
        CASE_DT("caseDt"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersJudgementColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersListColumn {// 爬虫信息列表
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo"),
        // 商户名称
        MER_NAME("merName"),
        // 备注信息
        MEMO("memo"),
        // 默认：0；成功：1；失败：2
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersListColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersMerAbnormalColumn {// 爬虫信息-经营异常信息
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo"),
        // 序号
        SEQ_NO("seqNo"),
        // 列入经营异常名录原因
        ABNORMAL_REASON("abnormalReason"),
        // 列入日期
        ABNORMAL_DT("abnormalDt"),
        // 移出日期
        REMOVE_DT("removeDt"),
        // 移出原因
        REMOVE_REASON("removeReason"),
        // 作出决定机关
        AUTHORITY("authority"), CLAIM_AMOUNT("claimAmount"), STATUS("status"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersMerAbnormalColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersMerBasicColumn {// 爬虫信息-工商注册基本信息
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo"),
        // 公司名称
        MER_NAME("merName"),
        // 法定代表人
        LEGAL_REP("legalRep"),
        // 类型
        MER_TYPE("merType"),
        // 成立日期
        FOUND_DT("foundDt"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt"),
        // 注册资本
        REG_CAPITAL("regCapital"),
        // 住所
        ADDR("addr"),
        // 经营开始日期
        OPR_START_DT("oprStartDt"),
        // 经营结束日期
        OPR_END_DT("oprEndDt"),
        // 经营范围
        SCOPE("scope"),
        // 登记机关
        REG_AUTHORITY("regAuthority"),
        // 核准日期
        APPROVAL_DATE("approvalDate"),
        // 登记状态
        REG_STATUS("regStatus"),
        // 股东类型
        SHAREHOLDER_TYPE("shareholderType");
        private TCreepersMerBasicColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersMerBranchColumn {// 爬虫信息-主要人员信息
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo"),
        // 序号
        SEQ_NO("seqNo"),
        // 注册号
        REG_NO("regNo"),
        // 名称
        NAME("name"),
        // 登记机关
        REG_AUTHORITY("regAuthority"),
        // 清算信息
        CLEAR_INFO("clearInfo"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersMerBranchColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersMerChangeColumn {// 爬虫信息-工商变更信息
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo"),
        // 变更事项
        CHANGE_ITEM("changeItem"),
        // 变更前内容
        CHANGE_OLD("changeOld"),
        // 变更后内容
        CHANGE_NEW("changeNew"),
        // 变更日期
        CHANGE_DT("changeDt"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersMerChangeColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersMerCheckColumn {// 爬虫信息-抽查检查信息
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo"),
        // 序号
        SEQ_NO("seqNo"),
        // 检查日期
        CHECK_DT("checkDt"),
        // 检查类型
        CHECK_TYPE("checkType"),
        // 检查实施机关
        AUTHORITY("authority"),
        // 结果
        CHECK_RESULT("checkResult"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersMerCheckColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersMerIllegalColumn {// 爬虫信息-严重违法信息
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo"),
        // 序号
        SEQ_NO("seqNo"),
        // 列入严重违法名录原因
        ILLEGAL_REASON("illegalReason"),
        // 列入日期
        ILLEGAL_DT("illegalDt"),
        // 移出日期
        REMOVE_DT("removeDt"),
        // 移出原因
        REMOVE_REASON("removeReason"),
        // 作出决定机关
        AUTHORITY("authority"), CLAIM_AMOUNT("claimAmount"), STATUS("status"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersMerIllegalColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersMerKeymanColumn {// 爬虫信息-主要人员信息
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo"),
        // 序号
        SEQ_NO("seqNo"),
        // 姓名
        NAME("name"),
        // 职务
        POSITION("position"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersMerKeymanColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersMerPenaltyColumn {// 爬虫信息-行政处罚信息
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo"),
        // 序号
        SEQ_NO("seqNo"),
        // 行政处罚决定书文号
        PENALTY_NO("penaltyNo"),
        // 作出行政处罚决定日期
        PENALTY_DT("penaltyDt"),
        // 行政处罚类型
        PENALTY_TYPE("penaltyType"),
        // 行政处罚内容
        PENALTY_CONTENT("penaltyContent"),
        // 行政处罚机关
        PENALTY_AUTHORITY("penaltyAuthority"), CLAIM_AMOUNT("claimAmount"), STATUS("status"),
        // 详情
        DETAILS("details"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersMerPenaltyColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersMerPledgeColumn {// 爬虫信息-股权出质登记信息
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo"),
        // 序号
        SEQ_NO("seqNo"),
        // 登记编号
        REG_NO("regNo"),
        // 出质人
        PLEDGER("pledger"),
        // 出质人证件号
        PLEDGER_CERT_NO("pledgerCertNo"),
        // 出质股权数额
        PLEDGE_AMOUNT("pledgeAmount"),
        // 质权人
        PLEDGEE("pledgee"),
        // 质权人证件号
        PLEDGEE_CERT_NO("pledgeeCertNo"),
        // 股权出质设立登记日期
        PLEDGE_DT("pledgeDt"),
        // 状态
        STATUS("status"),
        // 变更信息
        CHANGE_INFO("changeInfo"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersMerPledgeColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersMerPropertyColumn {// 爬虫信息-动产抵押登记信息信息
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo"),
        // 序号
        SEQ_NO("seqNo"),
        // 登记编号
        REG_NO("regNo"),
        // 登记日期
        REG_DT("regDt"),
        // 登记机关
        REG_AUTHORITY("regAuthority"),
        // 被担保债权数额
        CLAIM_AMOUNT("claimAmount"),
        // 状态
        STATUS("status"),
        // 详情
        DETAILS("details"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersMerPropertyColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersMerShareholderColumn {// 爬虫信息-股东信息
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo"),
        // 股东类型
        SHARE_TYPE("shareType"),
        // 股东名称
        NAME("name"),
        // 证照/证件类型
        CERT_TYPE("certType"),
        // 证照/证件号码
        CERT_NO("certNo"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersMerShareholderColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersMerUndoColumn {// 爬虫信息-工商撤销信息
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo"),
        // 撤销事项
        UNDO_ITEM("undoItem"),
        // 撤销前内容
        UNDO_OLD("undoOld"),
        // 撤销后内容
        UNDO_NEW("undoNew"),
        // 撤销日期
        UNDO_DT("undoDt"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersMerUndoColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersOlDetailColumn {// 简版征信其他贷款明细信息表
        // 主键
        ID("id"),
        // 报告编码
        RPT_NO("rptNo"),
        // 发放日期
        GRANT_DT("grantDt"),
        // 发放机构
        GRANT_ORG("grantOrg"),
        // 贷款类型
        LOAN_TYPE("loanType"),
        // 贷款金额
        LOAN_AMOUNT("loanAmount"),
        // 货币类型
        CURRENCY_TYPE("currencyType"),
        // 贷款到期日期
        LOAN_MATURITY_DT("loanMaturityDt"),
        // 统计日期
        STATISTICAL_DT("statisticalDt"),
        // 状态
        HL_STATUS("hlStatus"),
        // 余额
        BALANCE("balance"),
        // 逾期金额
        OVERDUE_AMOUNT("overdueAmount"),
        // 最近5年逾期月份数/准贷记卡透支60的月份数
        OL_OVERDRAFT_SIXTY("olOverdraftSixty"),
        // 最近5年逾期超过90天的月份数
        OLL_OVERDRAFT_NINETY("ollOverdraftNinety"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersOlDetailColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersOtherFreezeColumn {// 爬虫信息-司法股权冻结信息
        // 主键
        ID("id"),
        // 注册号/统一社会信用代码
        MER_NO("merNo"),
        // 序号
        SEQ_NO("seqNo"),
        // 被执行人
        EXEC_MAN("execMan"),
        // 股权数额
        EQUITY_AMOUNT("equityAmount"),
        // 执行法院
        EXEC_COURT("execCourt"),
        // 协助公示通知书文号
        NOTICE_NO("noticeNo"),
        // 状态
        STATUS("status"),
        // 详情
        DETAIL("detail"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersOtherFreezeColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersOtherShareChangeColumn {// 爬虫信息-司法股东变更登记信息
        // 主键
        ID("id"),
        // 注册号/统一社会信用代码
        MER_NO("merNo"),
        // 序号
        SEQ_NO("seqNo"),
        // 被执行人
        EXEC_MAN("execMan"),
        // 股权数额
        EQUITY_AMOUNT("equityAmount"), EXEC_COURT("execCourt"),
        // 受让人
        ASSIGNEE("assignee"),
        // 详情
        DETAIL("detail"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersOtherShareChangeColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersPatentColumn {// 爬虫信息-专利信息
        // 主键
        ID("id"),
        // 商户名称
        MER_NAME("merName"),
        // 专利类型
        PATENT_TYPE("patentType"),
        // 专利号/申请号
        PATENT_NO("patentNo"),
        // 发明名称
        PATENT_NAME("patentName"),
        // 申请人
        APPLICANT("applicant"),
        // 申请日期
        APPLY_DT("applyDt"),
        // 主分类号
        CATEGORY_NO("categoryNo"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersPatentColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersPublicCivilColumn {// 简版征信民事判决记录信息表
        // 主键
        ID("id"),
        // 报告编码
        RPT_NO("rptNo"),
        // 民事判决立案法院
        FILING_COURT("filingCourt"),
        // 案号
        CASE_NO("caseNo"),
        // 案由
        CAUSE_ACTION("causeAction"),
        // 立案时间
        FILING_DT("filingDt"),
        // 诉讼标的
        SUBJECT_ACTION("subjectAction"),
        // 诉讼标的金额
        SBUJECT_AMOUNT("sbujectAmount"),
        // 结案方式
        CLOSED_WAY("closedWay"),
        // 判决/调解结果
        DECISION_RSLT("decisionRslt"),
        // 判决/调解生效时间
        DECISION_DT("decisionDt"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersPublicCivilColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersPublicEnforcementColumn {// 简版征信强制执行记录信息表
        // 主键
        ID("id"),
        // 报告编码
        RPT_NO("rptNo"),
        // 强制执行法院
        ENFORCEMENT_COURT("enforcementCourt"),
        // 强制执行案号
        ENFORCEMENT_CASE("enforcementCase"),
        // 执行案由
        CAUSE("cause"),
        // 强制执行立案时间
        COMPULSORY_DT("compulsoryDt"),
        // 申请执行标的
        ENFORCEMENT_SUBJECT("enforcementSubject"),
        // 申请执行标的金额
        SUBJECT_AMOUNT("subjectAmount"),
        // 结案时间
        CLOSING_DT("closingDt"),
        // 结案方式
        CLOSE_WAY("closeWay"),
        // 案件状态
        CASE_STATUS("caseStatus"),
        // 已执行标的
        EXECUTED_SUBJECT("executedSubject"),
        // 已执行标的金额
        EXECUTED_SUBJECT_AMOUT("executedSubjectAmout"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersPublicEnforcementColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersPublicIspColumn {// 简版征信电信欠费记录信息表
        // 主键
        ID("id"),
        // 报告编码
        RPT_NO("rptNo"),
        // 电信运营商
        TELE_OPERATORS("teleOperators"),
        // 业务类型
        SERVICE_TYPE("serviceType"),
        // 记账年月
        JOURNAL_ENTRY("journalEntry"),
        // 业务开通时间
        BIZ_OPERATE_DT("bizOperateDt"),
        // 欠费金额
        ARREARS_AMOUNT("arrearsAmount"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersPublicIspColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersPublicSanctionColumn {// 简版征信行政处罚记录信息表
        // 主键
        ID("id"),
        // 报告编码
        RPT_NO("rptNo"),
        // 行政处罚机构
        PUNISHMENT_ORG("punishmentOrg"),
        // 文书编号
        DOC_NO("docNo"),
        // 处罚内容
        PUNISHMENT_CONTENT("punishmentContent"),
        // 处罚金额
        PUNISHMENT_AMOUNT("punishmentAmount"),
        // 处罚生效时间
        PUNISHMENT_ISSUE_DT("punishmentIssueDt"),
        // 是否行政复议
        RECONSIDERATION_FLAG("reconsiderationFlag"),
        // 行政复议结果
        RECONSIDERATION_RSLT("reconsiderationRslt"),
        // 处罚截止时间
        PUNISH_DT("punishDt"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersPublicSanctionColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersPublicTaxColumn {// 简版征信欠税记录信息表
        // 主键
        ID("id"),
        // 报告编码
        RPT_NO("rptNo"),
        // 主管税务机关
        COMPETENT_TAX_AUTHORITY("competentTaxAuthority"),
        // 欠税统计日期
        TAX_STATISTIC_DT("taxStatisticDt"),
        // 欠税总额
        TAX_AMOUNT("taxAmount"),
        // 纳税人识别号
        TAXPAYER_NO("taxpayerNo"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersPublicTaxColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersQueryLogColumn {// 简版征信查询记录信息表
        // 主键
        ID("id"),
        // 报告编码
        RPT_NO("rptNo"),
        // 查询日期
        QUERY_DT("queryDt"),
        // 查询操作员
        QUERY_BY("queryBy"),
        // 查询原因
        QUERY_REASON("queryReason"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersQueryLogColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersTempColumn {// 爬虫工商注册号临时列表
        // 主键
        ID("id"),
        // 工商注册号
        MER_NO("merNo");
        private TCreepersTempColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersTradeMarkColumn {// 爬虫信息-商标信息
        // 主键
        ID("id"),
        // 工商企业名称
        MER_NAME("merName"),
        // 序列号
        SEQ_NO("seqNo"),
        // 注册号/申请号
        APPLY_NO("applyNo"),
        // 类号
        CATEGORY_NO("categoryNo"),
        // 商标名称
        TRADE_MARK_NAME("tradeMarkName"),
        // 申请人名称
        APPLICANT("applicant"),
        // 商品
        WARE("ware"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersTradeMarkColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersListCourtColumn {// 爬虫任务队列-法院公告信息
        // 主键
        ID("id"),
        // 商户名称
        MER_NAME("merName"),
        // 备注信息
        MEMO("memo"),
        // 默认-0；成功-1；失败-2
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersListCourtColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersListJudgementColumn {// 爬虫任务队列-裁判文书信息
        // 主键
        ID("id"),
        // 商户名称
        MER_NAME("merName"),
        // 备注信息
        MEMO("memo"),
        // 默认-0；成功-1；失败-2
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersListJudgementColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersListPatentColumn {// 爬虫任务队列-专利信息
        // 主键
        ID("id"),
        // 商户名称
        MER_NAME("merName"),
        // 备注信息
        MEMO("memo"),
        // 默认-0；成功-1；失败-2
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersListPatentColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersListTradeMarkColumn {// 爬虫任务队列-商标信息
        // 主键
        ID("id"),
        // 商户名称
        MER_NAME("merName"),
        // 备注信息
        MEMO("memo"),
        // 默认-0；成功-1；失败-2
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersListTradeMarkColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersProxyListColumn {// 爬虫动态代理IP列表
        // 主键
        ID("id"),
        // IP地址
        IP("ip"),
        // 端口号
        PORT("port"),
        // IP类型
        IP_TYPE("ipType"),
        // 备注信息
        MEMO("memo"),
        // 默认-0；成功-1；失败-2
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersProxyListColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersConfigColumn {// 爬虫配置信息表
        // 主键
        ID("id"),
        // 爬虫任务类型
        REQUEST_TYPE("requestType"),
        // 种子URL
        ROOT_URL("rootUrl"),
        // 域名
        DOMAIN("domain"),
        // 用户浏览器代理设置
        USER_AGENT("userAgent"),
        // 网关系统接口URL
        CD_URL("cdUrl"),
        // 网关系统接口类型
        CD_REQUEST_CODE("cdRequestCode"),
        // 线程数
        THREAD_NUM("threadNum"),
        // 连接超时时间(ms)
        TIME_OUT("timeOut"),
        // 任务开关，0：关闭，1：开启
        SWITCH_FLAG("switchFlag"),
        // 动态代理开关，0：关闭，1：开启
        AGENT_FLAG("agentFlag"),
        // 重试连接次数
        RETRY_TIMES("retryTimes"),
        // 头信息
        HEADERS("headers"),
        // 备注
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersConfigColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersListShixinColumn {// 爬虫任务队列-失信被执行人信息
        // 主键
        ID("id"),
        // 商户名称AA
        MER_NAME("merName"),
        // 备注信息
        MEMO("memo"),
        // 默认-0；成功-1；失败-2
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersListShixinColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum tCreepersShixinColumn {// 爬虫信息-失信被执行人信息
        // 主键
        ID("id"),
        // 被执行人姓名/名称
        NAME("name"),
        // 身份证号码/组织机构代码证/工商注册号
        CERT_NO("certNo"),
        // 法定代表人或者负责人姓名
        LEGAL_REP("legalRep"),
        // 执行法院
        EXEC_COURT("execCourt"),
        // 省份
        PROVINCE("province"),
        // 执行依据文号
        JUDGEMENT_NO("judgementNo"),
        // 立案时间
        CASE_DT("caseDt"),
        // 案号
        CASE_NO("caseNo"),
        // 作出执行依据单位
        JUDGEMENT_COURT("judgementCourt"),
        // 生效法律文书确定的义务
        DUTY("duty"),
        // 被执行人的履行情况
        PERFORMANCE("performance"),
        // 失信被执行人行为具体情形
        BEHAVIOR_DETAILS("behaviorDetails"),
        // 发布日期
        PUBLISH_DT("publishDt"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private tCreepersShixinColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersFundBasicColumn {// 公积金账户信息表
        // 主键
        ID("id"),
        // 姓名
        NAME("name"),
        // 开户日期
        ACCOUNT_DT("accountDt"),
        // 公积金账号
        ACCOUNT_NO("accountNo"),
        // 所属单位
        UNIT("unit"),
        // 末次缴存年月
        END_DT("endDt"),
        // 账户余额
        SUM_AMOUNT("sumAmount"),
        // 月缴存额
        MONTHLY_AMOUNT("monthlyAmount"),
        // 当前账户状态
        ACCOUNT_STATUS("accountStatus"),
        // 绑定手机号
        MOBILE("mobile"),
        // 实名认证状态
        CERTIFICATE_STATUS("certificateStatus"),
        // 备注
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 登录用户
        LOGIN_NAME("loginName"),
        // 登录密码
        PASSWORD("password");
        private TCreepersFundBasicColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersFundBasicDetailColumn {// 公积金账户本年度明细表
        // 主键
        ID("id"),
        // 登录账号
        LOGIN_NAME("loginName"),
        // 日期
        OPERATION_DT("operationDt"),
        // 单位名称
        UNIT("unit"),
        // 金额(元)
        AMOUNT("amount"),
        // 业务描述
        OPERATION_DESC("operationDesc"),
        // 业务原因
        OPERATION_REASON("operationReason"),
        // 备注
        MEMO("memo");
        private TCreepersFundBasicDetailColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersFundExtraColumn {// 补充公积金账户信息表
        // 主键
        ID("id"),
        // 姓名
        NAME("name"),
        // 开户日期
        ACCOUNT_DT("accountDt"),
        // 公积金账号
        ACCOUNT_NO("accountNo"),
        // 登录用户
        LOGIN_NAME("loginName"),
        // 所属单位
        UNIT("unit"),
        // 末次缴存年月
        END_DT("endDt"),
        // 账户余额
        SUM_AMOUNT("sumAmount"),
        // 月缴存额
        MONTHLY_AMOUNT("monthlyAmount"),
        // 当前账户状态
        ACCOUNT_STATUS("accountStatus"),
        // 备注
        MEMO("memo");
        private TCreepersFundExtraColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersFundExtraDetailColumn {// 补充公积金账户本年度明细表
        // 主键
        ID("id"),
        // 登录用户
        LOGIN_NAME("loginName"),
        // 日期
        OPERATION_DT("operationDt"),
        // 单位名称
        UNIT("unit"),
        // 金额(元)
        AMOUNT("amount"),
        // 业务描述
        OPERATION_DESC("operationDesc"),
        // 业务原因
        OPERATION_REASON("operationReason"),
        // 备注
        MEMO("memo");
        private TCreepersFundExtraDetailColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersListFundColumn {// 爬虫任务队列-公积金信息
        // 主键
        ID("id"),
        // 用户代码（公积金账号）
        USER_CODE("userCode"),
        // 备注信息
        MEMO("memo");
        private TCreepersListFundColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersInsuranceBasicColumn {// 社保基础信息表
        // 主键
        ID("id"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt"),
        // 姓名
        NAME("name"),
        // 证件号
        CERT_NO("certNo"),
        // 92年底前连续工龄
        WORK_TIME("workTime"),
        // 密码
        PASSWORD("password");
        private TCreepersInsuranceBasicColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersInsurancePaymentColumn {// 社保应缴记录表
        // 主键
        ID("id"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt"),
        // 姓名
        NAME("name"),
        // 证件号
        CERT_NO("certNo"),
        // 年月
        PAYMENT_DT("paymentDt"),
        // 缴费基数
        PAYMENT_BASE("paymentBase"),
        // 养保个人缴费额
        ENDOWMENT("endowment"),
        // 医保个人缴费额
        MEDICAL("medical"),
        // 失保个人缴费额
        UNEMPLOYMENT("unemployment");
        private TCreepersInsurancePaymentColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersInsuranceSumColumn {// 社保累计缴费信息表
        // 主键
        ID("id"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt"),
        // 姓名
        NAME("name"),
        // 证件号
        CERT_NO("certNo"),
        // 截止年月
        END_DT("endDt"),
        // 累计缴费月数
        MONTHS("months"),
        // 养老金本息总额
        ENDOWMENT_SUM("endowmentSum"),
        // 养老金总额个人部分
        ENDOWMENT_SUM_PRIVATE("endowmentSumPrivate");
        private TCreepersInsuranceSumColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersInsuranceUnitColumn {// 公积金账户信息表
        // 主键
        ID("id"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt"),
        // 姓名
        NAME("name"),
        // 身份证号
        CERT_NO("certNo"),
        // 所属单位
        UNIT("unit"),
        // 起始日期
        PERIOD("period"), USER_NAME("userName"), USER_PWD("userPwd");
        private TCreepersInsuranceUnitColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersListInsuranceColumn {// 爬虫任务队列-社保信息
        // 主键
        ID("id"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt"),
        // 用户代码（身份证）
        USER_CODE("userCode");
        private TCreepersListInsuranceColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersCourtCorpBondsColumn {// 限制发行企业债黑名单表
        // 主键
        ID("id"),
        // 企业名称
        NAME("name"),
        // 组织机构代码
        CODE("code"),
        // 归属区域
        PROVINCE("province"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersCourtCorpBondsColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersCourtDishonestColumn {// 最高法失信人黑名单主表
        // 主键
        ID("id"),
        // 个人/企业
        NAME("name"),
        // 身份证号/代码
        CODE("code"),
        // 省份
        PROVINCE("province"),
        // 来源
        SOURCE("source"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersCourtDishonestColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersCourtDisInfoColumn {// 最高法失信人黑名单明细表
        // 主键
        ID("id"),
        // 个人/企业名称
        NAME("name"),
        // 数据类别
        TYPE("type"),
        // 身份证号/组织机构代码
        CODE("code"),
        // 地域名称
        PROVINCE("province"),
        // 来源
        SOURCE("source"),
        // 案件号
        CASE_NO("caseNo"),
        // 企业法人姓名
        LEGAL_REP("legalRep"),
        // 执行法院
        COURT("court"),
        // 法律生效文书确定的义务
        DUTY("duty"),
        // 被执行人的履行情况
        PERFORMANCE("performance"),
        // 失信被执行人具体情形
        SPECIFIC("specific"),
        // 发布时间
        PUBLISH_DT("publishDt"),
        // 执行依据文号
        IMPLEMENT_NO("implementNo"),
        // 立案时间
        CASE_DT("caseDt"),
        // 作出执行依据单位
        IMPLEMENT_UNIT("implementUnit"),
        // 已履行部分
        PERFORM_Y("performY"),
        // 未履行部分
        PERFORM_N("performN"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersCourtDisInfoColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersListCreditColumn {// 爬虫任务队列-人行征信报告
        // 主键
        ID("id"),
        // 用户代码
        USER_CODE("userCode"),
        // 用户密码
        PASSWORD("password"),
        // 短信验证码
        MESSAGE_CODE("messageCode"),
        // 报告图片链接
        IMAGE_URL("imageUrl"),
        // 报告网页链接
        HTML_URL("htmlUrl"),
        // 备注信息
        MEMO("memo"),
        // 默认-0；成功-1；失败-2
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersListCreditColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersListTourGuideColumn {// 爬虫任务队列-导游信息
        // 主键
        ID("id"),
        // 导游证号
        GUIDE_NO("guideNo"),
        // 导游卡号
        CARD_NO("cardNo"),
        // 身份证号
        CERT_NO("certNo"),
        // 备注信息
        MEMO("memo"),
        // 默认-0；成功-1；失败-2
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersListTourGuideColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersTourBlackListColumn {// 信用中国-旅游黑名单明细
        // 主键
        ID("id"),
        // 1：旅行社、2：导游
        TYPE("type"),
        // 档案号
        DOC_NO("docNo"),
        // 执业证号
        GUIDE_NO("guideNo"),
        // 单位名称
        AGENT_NAME("agentName"),
        // 姓名（导游名称、企业法人）
        NAME("name"),
        // 违法事项
        ISSUE("issue"),
        // 行政处罚决定
        DECISION("decision"),
        // 起止日期
        PERIOD("period"),
        // 明细链接
        DETAIL_URL("detailUrl"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersTourBlackListColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersTourGuideColumn {// 信用中国-导游信息
        // 主键
        ID("id"),
        // 导游姓名
        NAME("name"),
        // 导游证号
        GUIDE_NO("guideNo"),
        // 性别
        SEX("sex"),
        // 资格证号
        QULIFY_NO("qulifyNo"),
        // 等级
        GUIDE_LEVEL("guideLevel"),
        // 导游卡号
        CARD_NO("cardNo"),
        // 学历
        EDUCATION("education"),
        // 身份证号
        CERT_NO("certNo"),
        // 语言
        LAN("lan"),
        // 区域
        AREA("area"),
        // 名族
        PEOPLE("people"),
        // 发证日期
        CERT_DT("certDt"),
        // 分值
        SCORE("score"),
        // 受惩日期
        PUBLISH_DT("publishDt"),
        // 受惩类型
        PUBLISH_TYPE("publishType"),
        // 旅行社
        AGENT_NAME("agentName"),
        // 联系电话
        PHONE("phone"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersTourGuideColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersSactionColumn {// 信用中国-行政处罚黑名单表
        // 主键
        ID("id"),
        // 主体名称
        NAME("name"),
        // 公示类型
        TYPE("type"),
        // 处罚内容
        CONTENT("content"),
        // 行政类别/地域（全部）
        PROVINCE("province"),
        // 处罚更新日期
        MODIFY_DT("modifyDt"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersSactionColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }
    }

    public static enum TCreepersShixinAllColumn {// 信用中国-失信人被执行人黑名单主表
        // 主键
        ID("id"),
        // 主体类型（法人）
        NAME("name"),
        // 组织机构/身份证号
        CODE("code"),
        // 归属地址
        PROVINCE("province"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt"),
        // 优良记录数
        GOOD_RECORD("goodRecord"),
        // 不良记录数
        BAD_RECORD("badRecord"),
        // 失信记录数
        DIS_RECORD("disRecord");
        private TCreepersShixinAllColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersTaxEvasionColumn {// 信用中国-行政处罚黑名单
        // 主键
        ID("id"),
        // 主体名称
        NAME("name"),
        // 组织机构/身份证号
        CODE("code"),
        // 行政类别/地域（全部）
        PROVINCE("province"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersTaxEvasionColumn(String string)

        {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersTaxEvasionDetailColumn {// 信用中国-偷税漏税黑名单明细
        // 主键
        ID("id"),
        // 数据来源
        SOURCE("source"),
        // 数据类别
        TYPE("type"),
        // 省份
        PROVINCE("province"),
        // 检察机关
        COURT("court"),
        // 纳税人名称
        NAME("name"),
        // 纳税人识别码
        TAX_NO("taxNo"),
        // 身份证号/组织机构代码
        CODE("code"),
        // 注册地址
        ADDR("addr"),
        // 法定代表人或者负责人姓名
        LEGAL_REP("legalRep"),
        // 性别
        LEGAL_SEX("legalSex"),
        // 法定代表人或者负责人证件名称
        CERT_NAME("certName"),
        // 负有直接责任的财务负责人姓名
        FINANCIAL_NAME("financialName"),
        // 负有直接责任的财务负责人性别
        FINANCIAL_SEX("financialSex"),
        // 负有直接责任的财务负责人证件名称
        FINANCIAL_CERT_NAME("financialCertName"),
        // 负有直接责任的中介机构信息
        INTER_INFO("interInfo"),
        // 案件性质
        CASE_NATURE("caseNature"),
        // 主要违法事实
        ILLEGAL_FACTS("illegalFacts"),
        // 相关法律依据及处理处罚情况
        PUNISH_INFO("punishInfo"),
        // 发布级别
        PUBLISH_LEVEL("publishLevel"),
        // 备注信息
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersTaxEvasionDetailColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    public static enum TCreepersMedicalColumn {// 医药类信息
        // 主键
        ID("id"),
        // 信息类型（gmc_list:GMC认证、gsc_list:GSC认证、medical_instrument_list:药品器械注册、medical_operate_list:药品经营许可）
        TYPE("type"),
        // 唯一标识号
        KEY("key"),
        // 信息内容（JSON格式）
        CONTENT("content"),
        // 备注
        MEMO("memo"),
        // 逻辑删除标志，0：未删除，1：已删除
        FLAG("flag"),
        // 乐观锁
        VERSION("version"),
        // 创建者
        CREATED_BY("createdBy"),
        // 创建时间
        CREATED_DT("createdDt"),
        // 修改者
        UPDATED_BY("updatedBy"),
        // 修改时间
        UPDATED_DT("updatedDt");
        private TCreepersMedicalColumn(String string) {
            this.value = string;
        }

        private String value;

        public String getValue() {
            return value;
        }

    }

    /**
     * 
     * 分割线以上是数据库表字段名常量定义
     * =========================================================================
     * 
     * 暂时划分为以上几种类型使用，后续若有其他类型，可参照这个分割格式分割，以确保Constant的代码区分明确。 谢谢合作。 MaXin
     * 2016-7-12 14:14:55
     * 
     */
    public static void main(String[] args) {
        // System.out.println(CreepersConstant.i);
    }
}
