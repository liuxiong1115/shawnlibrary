package com.liuxiong.library.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 *
 */

public interface UiCallback {

    void onHandleMessage(Message msg);

    void initData(Bundle savedInstanceState);

    void setListener();

    int getLayoutId();

    /**
     * 是否使用EventBus
     * @return
     */
    boolean useEventBus();


}
