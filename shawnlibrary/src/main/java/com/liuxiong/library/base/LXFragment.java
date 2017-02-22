package com.liuxiong.library.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuxiong.library.event.BusFactory;
import com.liuxiong.library.kit.KnifeKit;
import com.liuxiong.library.log.XLog;

import butterknife.Unbinder;

/**
 *
 */

public abstract class LXFragment extends Fragment implements UiCallback {
    protected View rootView;
    protected LayoutInflater layoutInflater;
    protected Activity context;
    protected UiDelegate uiDelegate;
    private Unbinder unbinder;

    private Handler uiHadler = new Handler(){
        public void handleMessage(Message msg){
            onHandleMessage(msg);
        }
    };

    public Handler getUiHadler() {
        return uiHadler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutInflater = inflater;
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), null);
            unbinder = KnifeKit.bind(this, rootView);
        } else {
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        }
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (useEventBus()) {
            BusFactory.getBus().register(this);
        }
        setListener();
        initData(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.context = (Activity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BusFactory.getBus().unregister(this);
        getUiDelegate().destory();
    }

    protected UiDelegate getUiDelegate() {
        if (uiDelegate == null) {
            uiDelegate = UiDelegateBase.create(getContext());
        }
        return uiDelegate;
    }


    /**
     * Fragment 使用Toolbar时  切换Menu  覆盖一下方法
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        XLog.d("LXFragment  onCreateOptionsMenu()");
        menu.clear();
    }

    public void clearMenu(Toolbar bar){
        bar.getMenu().clear();
    }

    public void addNewMenu(Toolbar bar , int resId){
        clearMenu(bar);
        bar.inflateMenu(resId);
    }


}
