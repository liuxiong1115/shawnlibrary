package com.liuxiong.library.base;

import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.Serializable;

/**
 * Created by wanglei on 2016/12/1.
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


    void sendHandleSimpleMessage(Handler uiHadler, Object value, int w);
    void sendHandleSerializableMessage(Handler uiHadler, String key, Serializable value, int w);
    void sendHandleSerializableMessageDelayed(Handler uiHadler, String key, Serializable value, int w, long delayed);
    void sendHandleSerializableMessage(Handler uiHadler, String key, Serializable value, int w, int arg1);
    void sendHandleSerializableMessage(Handler uiHadler, String key, Serializable value, int w, int arg1, int arg2);
    void sendEmptyMessageDelayed(Handler uiHadler, int w, long delayed);
    void sendHandleStringMessage(Handler uiHadler, String key, String value, int what);



}
