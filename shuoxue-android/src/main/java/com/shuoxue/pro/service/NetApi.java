package com.shuoxue.pro.service;

import android.text.TextUtils;

import com.liuxiong.library.log.XLog;
import com.shuoxue.pro.model.ResultExams;
import com.shuoxue.pro.model.ResultToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by liuxiong on 2016/12/9.
 */

public class NetApi {

    private static void invokeGet(String url, Map<String, String> params, Callback callback) {
        OkHttpUtils.get().url(url).addHeader("Cookie", "JSESSIONID=" + getToken())
                .params(params == null ? new HashMap<String, String>() : params)
                .build()
                .execute(callback);
    }


    private static void invokePost(String url, Map<String, String> params, Callback callback) {
        XLog.d("url:" + url);
//        ?key=z1zkey&code=MTJCNDgyOTIxOTk4QjUzQzM2QTlFN0ZFMzY0MDNEMjQ=
//        url = url + "?key=z1zkey&code=MTJCNDgyOTIxOTk4QjUzQzM2QTlFN0ZFMzY0MDNEMjQ=";
        OkHttpUtils.post().url(url).addHeader("Cookie", "JSESSIONID=" + getToken())
                .params(params == null ? new HashMap<String, String>() : params)
                .build()
                .execute(callback);
    }


    /**
     * 获取Token
     * @return
     */
    private static String getToken(){
//        UserToken token = MyApplication.getContext().getUserToken();
//
//        if (token != null) {
//            XLog.d("JSESSIONID=" + token.getToken());
//            return token.getToken();
//        }
//        XLog.e("token 为空：" + token);
        return "MTJCNDgyOTIxOTk4QjUzQzM2QTlFN0ZFMzY0MDNEMjQ=";
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////                                          ////////////////////////////
    ////////////////////////////                                      //////////////////////////////
    //////////////////////////////                                 /////////////////////////////////
    ////////////////////////////////                            ////////////////////////////////////
    /////////////////////////////////                        ///////////////////////////////////////
    ///////////////////////////////////                   //////////////////////////////////////////
    /////////////////////////////////////               ////////////////////////////////////////////
    ///////////////////////////////////////           //////////////////////////////////////////////
    /////////////////////////////////////////       ////////////////////////////////////////////////
    //////////////////////////////////////////    //////////////////////////////////////////////////
    ///////////////////////////////////////////  ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////




    /**
     * 登录
     * @param name
     * @param pwd
     * @param callback
     */
    public static void login(String name, String pwd , JsonCallback<ResultToken> callback) {
        String url = UrlKit.getUrl(UrlKit.ACTION_DATA_login);
        RequestParameters requestParameters = new RequestParameters();
        if(!TextUtils.isEmpty(name))
            requestParameters.add("username" , name);
        if(!TextUtils.isEmpty(pwd))
            requestParameters.add("password" , pwd);
        invokePost(url, requestParameters.getMapParameters() , callback);
    }


    /**
     * 请求分类列表
     * @param callback
     */
    public static void queryExams(String studentID ,JsonCallback<ResultExams> callback){
        String url = UrlKit.getUrl(UrlKit.ACTION_DATA_queryExams);
        RequestParameters requestParameters = new RequestParameters();
        if(studentID != null)
            requestParameters.add("studentId" , studentID);
        invokePost(url, requestParameters.getMapParameters() , callback);
    }

}
