package com.example.zhengdong.base.APIManager;

import java.security.PublicKey;

public class UrlUtils {

    /**
     * 静态字段
     */
    public static String APP_TOKEN = "APP_TOKEN";
    public static String APP_NAME = "APP_NAME";
    public static String APP_ORANGE_ID = "APP_ORANGE_ID";
    public static String APP_DEPT_ID = "APP_DEPT_ID";
    public static String APP_PARENT_DEPT_ID = "APP_PARENT_DEPT_ID";
    public static String APP_USERNAME = "APP_USERNAME";
    public static String APP_PASSWORD = "APP_PASSWORD";

    /**
     * 接口文档
     */
    public static String BASE_PIC_URL = "http://106.14.154.136:20000";
    public static String BASE_URL = "http://172.16.3.5:8085/rest-api";
    // 用户登陆
    public static String LOGIN_URL = BASE_URL + "/api/token/getToken";
    // 注册
    public static String REGISTER_URL = BASE_URL + "/api/user/register";
    // 获取短信校验码
    public static String GET_VERIFY_CODE = BASE_URL + "/api/sms/getSmsCode";
    // 忘记密码
    public static String FORGET_PAW_URL = BASE_URL + "/api/user/forgetPassword";
    // 设置机构ID
    public static String SETTING_ORGAN_ID_URL = BASE_URL + "/api/token/setOrg";
    // 获取部门列表
    public static String GET_PARTMENT_LIST_URL = BASE_URL + "/api/dept/query";
    // 根据父类id获取部门
    public static String GET_PARENT_PARTMENT_LIST_URL = BASE_URL + "/api/dept/queryByParentId";
    // 添加部门
    public static String ADD_PARTMENT_URL = BASE_URL + "/api/dept/add";
    // 钣金大师
    public static String IRON_MASTER_URL = "http://106.14.154.136:8081";
    public static String IRON_NEW_MASTER_URL = "http://172.16.18.244:8085/GCGL/html/web/html/index1.html?token=";
    // 查询部门和部门人数
    public static String QUERY_MENBER_AND_DEPT_LIST_URL = BASE_URL + "/admin/dept/queryDeptAndMember";
    // 删除部门
    public static String DELETE_PARTMENT_URL = BASE_URL + "/api/dept/delete";
    // 新增部门员工
    public static String ADD_PARTMENT_USER_URL = BASE_URL + "/api/dept/addUsers";

    /**
     * 新闻模块
     */
    // 新闻标题
    public static String NEWS_TITLE_LIST = BASE_URL + "/api/information/query";
    // 新闻列表
    public static String NEWS_LIST_URL = BASE_URL + "/api/information/queryNewsList";

    /**
     * 我的报价/询价
     */
    // 我的报价 列表
    public static String MINE_OFFER_LIST_SPEAK_URL = BASE_URL + "/api/enquiry/queryMyIsToEnquiry"; //0 待报价 1 已报价
    // 我的询价
    public static String MINE_OFFER_LIST_REQUIRE_URL = BASE_URL + "/api/enquiry/queryMyEnquiry";  // 0 待报价 1 已报价
    // 我的询价-带询价详情
    public static String MINE_REQUIRE_DETAIL_LIST_URL = BASE_URL + "/api/enquiry/queryEnquItemInfo";
    // 询价单详情
    public static String MINE_OFFER_DETAIL_LIST_URL = BASE_URL + "/api/enquiry/queryItemInfo";
    // 立即报价
    public static String MINE_SPEAK_PRICE_URL = BASE_URL + "/api/enquiry/addEquiryResponse";
    // 查询已报价的商家列表
    public static String MINE_QUIRE_COMPANY_LIST_URL = BASE_URL + "/api/enquiry/mathOrg";

    /**
     * 精品模块
     * */
    // 精品头部
    public static String BOUTIQUE_ITEM_LIST_URL = BASE_URL + "/api/delicate/quiryFanType";
    // 查询精品列表
    public static String BOUTIQUE_LIST_URL = BASE_URL + "/api/delicate/enquiryFancyEx";
    // 查询精品详情
    public static String BOUTIQUE_DETAIL_URL = BASE_URL +"/api/delicate/enquiryFanExInformation";
}
