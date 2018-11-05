package com.liuxiong.library.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
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
import com.orhanobut.logger.Logger;

import butterknife.Unbinder;

/**
 *
 */

public abstract class LXFragment extends Fragment implements UiCallback {
    protected View rootView;
    protected LayoutInflater layoutInflater;
    protected Activity context;
    protected UiDelegate uiDelegate;
    public boolean isNeedUnbinder = true;
    private Unbinder unbinder;

    private Handler uiHadler = new Handler(){
        public void handleMessage(Message msg){
            if(isDetached()){
                return;
            }
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

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    /*
     * Deprecated on API 23
     * Use onAttachToContext instead
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    /*
     * Called when the fragment attaches to the context
     */
    protected void onAttachToContext(Context context) {
        if (context instanceof Activity) {
            this.context = (Activity) context;
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
       // context = null;
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
        if(unbinder != null && isNeedUnbinder) {
            KnifeKit.unbind(unbinder);
        }
    }


    public void onDestroy() {
        super.onDestroy();
    }

    protected UiDelegate getUiDelegate() {
        if (uiDelegate == null) {
            uiDelegate = UiDelegateBase.create(context);
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
        Logger.d("LXFragment  onCreateOptionsMenu()");
        menu.clear();
    }

    public void clearMenu(Toolbar bar){
        bar.getMenu().clear();
    }

    public void addNewMenu(Toolbar bar , int resId){
        clearMenu(bar);
        bar.inflateMenu(resId);
    }

//    private void showPopupWindow(View view) {
//
//        // 一个自定义的布局，作为显示的内容
//        View contentView = LayoutInflater.from(context).inflate(
//                R.layout.pop_window, null);
//        // 设置按钮的点击事件
////        Button button = (Button) contentView.findViewById(R.id.button1);
//
//        final PopupWindow popupWindow = new PopupWindow(contentView,
//                LP, LayoutParams.WRAP_CONTENT, true);
//
//        popupWindow.setTouchable(true);
//
//        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//
//                return false;
//                // 这里如果返回true的话，touch事件将被拦截
//                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
//            }
//        });
//
//        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
//        // 我觉得这里是API的一个bug
////        popupWindow.setBackgroundDrawable(getResources().getDrawable(
////                R.drawable.selectmenu_bg_downward));
//
//        // 设置好参数之后再show
//        popupWindow.showAsDropDown(view);
//
//    }


}
