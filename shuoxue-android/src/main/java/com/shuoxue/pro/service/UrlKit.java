package com.shuoxue.pro.service;

/**
 * Created by wanglei on 2016/12/9.
 */

public class UrlKit {
    public static String getUrl(String action) {
        return new StringBuilder(API_BASE_URL).append(action).toString();
    }

    //基础API  http://121.40.87.86:28080/shuoxue-web/app/login.shtml
//    public static final String API_BASE_URL = "http://121.201.63.72:8080/ghuogtest/";
//    public static final String API_BASE_URL = "http://121.40.87.86:28080/shuoxue-web/";
    public static final String API_BASE_URL = "http://106.15.37.217:28080/shuoxue/";
//    public static final String ACTION_DATA_RESULT = "data/{type}/{number}/{page}";


    //登录  e10adc3949ba59abbe56e057f20f883e

    public static final String ACTION_DATA_login = "app/login.shtml";
    //查询测试卷
    public static final String ACTION_DATA_queryExams = "app/queryExams.shtml";
    //查询测试卷试题
    public static final String ACTION_DATA_queryExamTests = "app/queryExamTests.shtml";



}
