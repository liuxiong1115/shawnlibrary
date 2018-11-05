package com.liuxiong.library.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.liuxiong.library.event.BusFactory;
import com.liuxiong.library.kit.KnifeKit;
import com.orhanobut.logger.Logger;

import butterknife.Unbinder;

/**
 *
 */

public abstract class LXActivity extends AppCompatActivity implements UiCallback{
    protected Activity context;
    protected UiDelegate uiDelegate;
    private Unbinder unbinder;
    //是否仅竖屏
    private boolean isPoriratt = true;
    //是否销毁
    private boolean isDestroy = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        if(isPoriratt)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设定为竖屏
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            unbinder = KnifeKit.bind(this);
        }
        setListener();
        initData(savedInstanceState);
//        initContentView(savedInstanceState);
    }

    public boolean isPoriratt() {
        return isPoriratt;
    }

    public void setPoriratt(boolean poriratt) {
        isPoriratt = poriratt;
    }


    //    public void initContentView(@Nullable Bundle savedInstanceState){
//        if (getLayoutId() > 0) {
//            setContentView(getLayoutId());
//            unbinder = KnifeKit.bind(this);
//        }
//        setListener();
//        initData(savedInstanceState);
//    }


   private Handler uiHadler = new Handler(){
        public void handleMessage(Message msg){
            if(isDestroyed()){
                return;
            }
            onHandleMessage(msg);
        }
    };


    public Handler getUiHadler() {
        return uiHadler;
    }


    protected UiDelegate getUiDelegate() {
        if (uiDelegate == null) {
            uiDelegate = UiDelegateBase.create(this);
        }
        return uiDelegate;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (useEventBus()) {
            BusFactory.getBus().register(this);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        getUiDelegate().resume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        getUiDelegate().pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestroy = true;
        getUiDelegate().destory();
        BusFactory.getBus().unregister(this);
        if(unbinder != null) {
            unbinder.unbind();
        }
    }
}
