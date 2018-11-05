package com.liuxiong.library.base;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.Serializable;

/**
 *
 */

public interface UiDelegate {

    void resume();
    void pause();
    void destory();

    void visible(boolean flag, View view);
    void gone(boolean flag, View view);
    void inVisible(View view);


    void toastShort(String msg);
    void toastLong(String msg);
    void addNewToolBarView(Toolbar bar, int contentRes, View view);

    void setFullScreen(boolean enable);
    void showInputSoft(View view);
    void hideInputWindow(View view);
    void inFullScreen();
    void outFullScreen();

    void showBaseTopbar(Activity activity , int rBarId , boolean isShow);

    void showBaseTopbar(View view , int rBarId , boolean isShow);

    void sendHandleSimpleMessage(Handler uiHadler, Object value, int w);
    void sendHandleSimpleMessage(Handler uiHadler, Object value, int w, int arg1);
    void sendHandleSerializableMessage(Handler uiHadler, String key, Serializable value, int w);
    void sendHandleSerializableMessageDelayed(Handler uiHadler, String key, Serializable value, int w, long delayed);
    void sendHandleSerializableMessage(Handler uiHadler, String key, Serializable value, int w, int arg1);
    void sendHandleSerializableMessage(Handler uiHadler, String key, Serializable value, int w, int arg1, int arg2);
    void sendEmptyMessageDelayed(Handler uiHadler, int w, long delayed);
    void removeMessages(Handler uiHadler, int w);
    void sendHandleStringMessage(Handler uiHadler, String key, String value, int what);

    KProgressHUD showLoadingHUD(String detailsLabel);
    void dismissLoadingHUD(KProgressHUD kProgressHUD);
}
