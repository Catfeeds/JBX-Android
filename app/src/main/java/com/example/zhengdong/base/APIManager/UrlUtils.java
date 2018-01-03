package com.example.zhengdong.base.APIManager;

import java.security.PublicKey;

public class UrlUtils {

    /**
     * 静态字段
     * */
    public static String APP_TOKEN = "APP_TOKEN";
    public static String APP_NAME = "APP_NAME";
    public static String APP_ORANGE_ID = "APP_ORANGE_ID";
    public static String APP_DEPT_ID = "APP_DEPT_ID";
    public static String APP_PARENT_DEPT_ID = "APP_PARENT_DEPT_ID";
    /**
     * 接口文档
     */
    public static String BASE_URL = "http://172.16.3.5:8085/rest-api";
    // 用户登陆
    public static String LOGIN_URL = BASE_URL + "/api/token/getToken";
    // 注册
    public static String REGISTER_URL = BASE_URL + "/wap/user/register";
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
    public static  String IRON_MASTER_URL = "http://106.14.154.136:8081";
    // 查询部门和部门人数
    public static String QUERY_MENBER_AND_DEPT_LIST_URL = BASE_URL + "/admin/dept/queryDeptAndMember";
    // 删除部门
    public static String DELETE_PARTMENT_URL = BASE_URL + "/api/dept/delete";
    // 新增部门员工
    public static String ADD_PARTMENT_USER_URL = BASE_URL + "/api/dept/addUsers";


    /**
     * 新闻模块
     * */
    // 新闻标题
    public static String NEWS_TITLE_LIST = BASE_URL + "/api/information/query";
    // 新闻列表
    public static String NEWS_LIST_URL = BASE_URL + "/api/information/queryNewsList";
}
