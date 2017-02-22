package com.shuoxue.pro.ui;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.liuxiong.library.base.LXFragment;
import com.liuxiong.library.log.XLog;
import com.shuoxue.pro.R;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends LXFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setToolBar(Toolbar bar){
        setHasOptionsMenu(true);
        setToolBarTitle(bar , "");
        ((AppCompatActivity)context).setSupportActionBar(bar);
    }

    public void setToolBar(Toolbar bar , String title){
        setHasOptionsMenu(true);
        setToolBarTitle(bar , title);
        ((AppCompatActivity)context).setSupportActionBar(bar);
    }


    public void setToolBarTitle(Toolbar bar , String title){
        bar.setTitle("");
        ((TextView)bar.findViewById(R.id.tvTitle)).setText(title);
        if(bar.findViewById(R.id.back) != null)
            bar.findViewById(R.id.back).setVisibility(View.GONE);
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }

}
