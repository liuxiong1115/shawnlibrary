package com.shuoxue.pro.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.liuxiong.library.Constants;
import com.liuxiong.library.log.XLog;
import com.liuxiong.library.router.Router;
import com.shuoxue.pro.MyApplication;
import com.shuoxue.pro.R;
import com.shuoxue.pro.exception.AppException;
import com.shuoxue.pro.manager.SharedPreferencesManager;
import com.shuoxue.pro.model.ResultToken;
import com.shuoxue.pro.model.User;
import com.shuoxue.pro.service.JsonCallback;
import com.shuoxue.pro.service.NetApi;

import okhttp3.Call;

import static com.liuxiong.library.Constants.HANDLE_GET_DATA_KEY;
import static com.liuxiong.library.Constants.HANDLE_GET_DATA_WHAT_FAIL;
import static com.liuxiong.library.Constants.HANDLE_GET_DATA_WHAT_SUCCESS;


public class LauncherActivity extends BaseActivity {


    /**
     * 启动
     * @param activity
     */
    public static void launch(Activity activity) {
        Router.newIntent()
                .from(activity)
                .to(LauncherActivity.class)
                .data(new Bundle())
                .launch();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getUiDelegate().setFullScreen(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onHandleMessage(Message msg) {
        switch (msg.what){
            case HANDLE_GET_DATA_WHAT_SUCCESS:
                MainActivity.launch(LauncherActivity.this);
                finish();
                break;

            case HANDLE_GET_DATA_WHAT_FAIL:
                getUiDelegate().toastShort((String)msg.obj);
                LoginActivity.launch(LauncherActivity.this);
                finish();
                break;
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        User user = SharedPreferencesManager.getInstance().getUserInfo();
        XLog.d("user:" + user);
        if(null != user) {
            autoLogin(user.getAccount(), user.getPwd());
        } else {
            XLog.d("===登录===");
            LoginActivity.launch(LauncherActivity.this);
            finish();
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_launcher;
    }

    /**
     * 自动登录
     * @param account
     * @param pwd
     */
    private void autoLogin(String account , String pwd){
        NetApi.login(account, pwd, new JsonCallback<ResultToken>() {
            @Override
            public void onResponse(ResultToken response, int id) {
                XLog.d(response + "  id:" + id);
                if(response.isError()){
                    AppException.getInstance().handleException(context , response);
                    getUiDelegate().sendHandleSimpleMessage(getUiHadler() , getResources().getString(R.string.dataLoadFail) + response.getMessage(),HANDLE_GET_DATA_WHAT_FAIL );
                }else{
                    getUiDelegate().sendHandleSerializableMessage(getUiHadler() ,HANDLE_GET_DATA_KEY ,response ,HANDLE_GET_DATA_WHAT_SUCCESS);
                    MyApplication.getContext().setUserToken(response.getObj());
                }
            }

            @Override
            public void onFail(Call call, Exception e, int id) {
                AppException.getInstance().handleException(context , e);
                getUiDelegate().sendHandleSimpleMessage(getUiHadler() , getResources().getString(R.string.netError) ,HANDLE_GET_DATA_WHAT_FAIL );
            }
        });
    }

}
