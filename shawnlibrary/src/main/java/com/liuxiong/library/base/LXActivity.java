package com.liuxiong.library.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.liuxiong.library.event.BusFactory;
import com.liuxiong.library.kit.KnifeKit;
import com.liuxiong.library.log.XLog;

import butterknife.Unbinder;

/**
 *
 */

public abstract class LXActivity extends AppCompatActivity implements UiCallback{
    protected Activity context;
    protected UiDelegate uiDelegate;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;

        if (getLayoutId() > 0) {
            XLog.d("绑定View");
            setContentView(getLayoutId());
            unbinder = KnifeKit.bind(this);
        }
        setListener();
        initData(savedInstanceState);
//        initContentView(savedInstanceState);
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
        BusFactory.getBus().unregister(this);
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getUiDelegate().destory();
    }
}
