package com.shuoxue.pro;
import android.app.Application;

import com.liuxiong.library.log.XLog;
import com.shuoxue.pro.model.UserToken;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

/**
 * Created by orang on 2016/12/21.
 */

public class MyApplication extends Application {
    private static MyApplication context;
    private UserToken userToken;

    @Override
    public void onCreate() {
        super.onCreate();
        if(context == null)
            context = this;

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    public static MyApplication getContext() {
        return context;
    }


    public UserToken getUserToken() {
//        if(userToken == null){
//            userToken = SharedPreferencesManager.getInstance().getToken();
//        }
        return userToken;
    }

    public void setUserToken(UserToken userToken) {
        XLog.d("setUserToken=" + userToken);
//        if(userToken != null) {
//            SharedPreferencesManager.getInstance().updateToken(userToken.getUserId() , userToken.getUserId() , System.currentTimeMillis());
//        }
        this.userToken = userToken;
    }


}
