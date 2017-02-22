package com.shuoxue.pro.ui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.liuxiong.library.base.LXActivity;
import com.liuxiong.library.log.XLog;
import com.shuoxue.pro.R;

public abstract class BaseActivity extends LXActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

//        PushAgent.getInstance(this).onAppStart();
    }


    public void setToolBar(Toolbar bar){
        setToolBarTitle(bar , "");
        ((AppCompatActivity)context).setSupportActionBar(bar);
    }

    public void setToolBar(Toolbar bar , String title){
        setToolBarTitle(bar , title);
        ((AppCompatActivity)context).setSupportActionBar(bar);
    }


    public void setToolBarTitle(Toolbar bar , String title){
        bar.setTitle("");
        ((TextView)bar.findViewById(R.id.tvTitle)).setText(title);
        ((AppCompatImageView)bar.findViewById(R.id.back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XLog.d("返回");
                onBackPressed();
            }
        });
    }


    /**
     * 设置为返回按钮
     * @param toolbar
     */
    public void setToolbarUp(Toolbar toolbar , String title){
        setSupportActionBar(toolbar);
        toolbar.setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示为返回按钮
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    public void setToolbarHome(Toolbar toolbar , String title){
        setSupportActionBar(toolbar);
        toolbar.setTitle(title);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示为返回按钮
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    public void onResume() {
        super.onResume();
    }
    public void onPause() {
        super.onPause();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
