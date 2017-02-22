package com.liuxiong.library.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.liuxiong.library.log.XLog;

import java.io.Serializable;


/**
 * Created by wanglei on 2016/12/1.
 */

public class UiDelegateBase implements UiDelegate {

    public Context context;

    private UiDelegateBase(Context context) {
        this.context = context;

    }

    public static UiDelegateBase create(Context context) {
        return new UiDelegateBase(context);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destory() {

    }

    @Override
    public void visible(boolean flag, View view) {
        if (flag) view.setVisibility(View.VISIBLE);
    }

    @Override
    public void gone(boolean flag, View view) {
        if (flag) view.setVisibility(View.GONE);
    }

    @Override
    public void inVisible(View view) {
        view.setVisibility(View.INVISIBLE);
    }

    @Override
    public void toastShort(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastLong(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
    @Override
    public void addNewToolBarView(Toolbar bar , int contentRes, View view ) {
        ((ViewGroup)bar.findViewById(contentRes)).removeAllViews();
        ((ViewGroup)bar.findViewById(contentRes)).addView(view);
    }


    /**
     * 设置是否全屏
     * @param enable
     */
    public void setFullScreen(boolean enable){
        if (enable) {
            WindowManager.LayoutParams lp =  ((Activity)context).getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            ((Activity)context).getWindow().setAttributes(lp);
            ((Activity)context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            WindowManager.LayoutParams attr = ((Activity)context).getWindow().getAttributes();
            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            ((Activity)context).getWindow().setAttributes(attr);
            ((Activity)context).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }




    public void showInputSoft(View view) {
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInputFromInputMethod(view.getWindowToken(), InputMethodManager.SHOW_FORCED);
        InputMethodManager imm = (InputMethodManager) ((Activity)context).getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 隐藏键盘
     */
    public void hideInputWindow(View view) {
        InputMethodManager imm = (InputMethodManager) ((Activity)context).getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }


    public void inFullScreen(){
        ((Activity)context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void outFullScreen(){
        ((Activity)context).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    /**
     * 发送简单通知消息
     *
     * @param value
     * @param w
     */
    public void sendHandleSimpleMessage(Handler uiHadler ,Object value, int w) {
        XLog.d("sendHandleSimpleMessage -- >> " + "    value -- >> " + value + "  w -- >> " + w);
        Message msg = new Message();
        msg.what = w;
        msg.obj = value;
        if (uiHadler != null) {
            uiHadler.sendMessage(msg);
        } else {
            XLog.e("uiHadler is null !!! ");
        }
    }


    public void sendHandleSerializableMessage(Handler uiHadler ,String key, Serializable value, int w) {
        XLog.d("sendHandleSerializableMessage -- >> " + key + "    value -- >> " + value + "  w -- >> " + w);
        Bundle bundle = new Bundle();
        Message msg = new Message();
        bundle.putSerializable(key, value);
        msg.what = w;
        msg.setData(bundle);
        if (uiHadler != null) {
            uiHadler.sendMessage(msg);
        } else {
            XLog.e("uiHadler is null !!! ");
        }
    }


    public void sendHandleSerializableMessageDelayed(Handler uiHadler ,String key, Serializable value, int w , long delayed) {
        XLog.d("sendHandleSerializableMessage -- >> " + key + "    value -- >> " + value + "  w -- >> " + w);
        Bundle bundle = new Bundle();
        Message msg = new Message();
        bundle.putSerializable(key, value);
        msg.what = w;
        msg.setData(bundle);
        if (uiHadler != null) {
            uiHadler.sendMessageDelayed(msg , delayed);
        } else {
            XLog.e("uiHadler is null !!! ");
        }
    }





    public void sendHandleSerializableMessage(Handler uiHadler ,String key, Serializable value, int w, int arg1) {
        XLog.d("sendHandleSerializableMessage -- >> " + key + "    value -- >> " + value + "   arg1 -- >> " + arg1);
        Bundle bundle = new Bundle();
        Message msg = new Message();
        bundle.putSerializable(key, value);
        msg.what = w;
        msg.arg1 = arg1;
        msg.setData(bundle);
        if (uiHadler != null) {
            uiHadler.sendMessage(msg);
        } else {
            XLog.e("uiHadler is null !!! ");
        }
    }


    public void sendHandleSerializableMessage(Handler uiHadler ,String key, Serializable value, int w, int arg1, int arg2) {
        XLog.d("sendHandleSerializableMessage -- >> " + key + "    value -- >> " + value +
                " w -- >> " + w + " -- >> " + arg1 + " arg2 -- >> " + arg2);
        Bundle bundle = new Bundle();
        Message msg = new Message();
        bundle.putSerializable(key, value);
        msg.what = w;
        msg.arg1 = arg1;
        msg.arg2 = arg2;
        msg.setData(bundle);
        if (uiHadler != null) {
            uiHadler.sendMessage(msg);
        } else {
            XLog.e("uiHadler is null !!! ");
        }
    }


    public void sendEmptyMessageDelayed(Handler uiHadler ,int w, long delayed) {
        XLog.d("sendEmptyMessageDelayed -- >> " + " w -- >> " + w + " -- >> "  + " delayed -- >> " + delayed);
        if (uiHadler != null) {
            uiHadler.sendEmptyMessageDelayed(w , delayed);
        } else {
            XLog.e("uiHadler is null !!! ");
        }
    }




    public void sendHandleStringMessage(Handler uiHadler ,String key, String value, int what) {
        XLog.d("sendHandleStringMessage -- key -- >> " + key + "    value -- >> " + value);
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        msg.setData(bundle);
        msg.what = what;
        if (uiHadler != null) {
            uiHadler.sendMessage(msg);
        } else {
            XLog.e("uiHadler is null !!! ");
        }
    }


}
