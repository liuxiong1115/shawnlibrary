package com.liuxiong.library.weights;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.WindowInsets;
import android.widget.LinearLayout;

/**
 * 作者： Shawn on 2018/5/17 15:17
 * 邮箱： orange_lx0120@126.com
 * 说明： 自定义根布局，主要解决设置状态栏后，adjustResize 无效，  设置  android:fitsSystemWindows="true"  布局下移问题
 */

public class RootLinearLayout extends LinearLayout {

    private int[] mInsets = new int[4];

    public RootLinearLayout(Context context) {
        super(context);
    }

    public RootLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RootLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RootLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected final boolean fitSystemWindows(Rect insets) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mInsets[0] = insets.left;
            mInsets[1] = insets.top;
            mInsets[2] = insets.right;
            insets.left = 0;
            insets.top = 0;
            insets.right = 0;
        }
        return super.fitSystemWindows(insets);
    }

    @Override
    public final WindowInsets onApplyWindowInsets(WindowInsets insets) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            mInsets[0] = insets.getSystemWindowInsetLeft();
            Log.e("mInsets[0]",""+mInsets[0]);
            mInsets[1] = insets.getSystemWindowInsetTop();
            Log.e("mInsets[1]",""+mInsets[1]);
            mInsets[2] = insets.getSystemWindowInsetRight();
            Log.e("mInsets[2]",""+mInsets[2]);
            return super.onApplyWindowInsets(insets.replaceSystemWindowInsets(0, 0, 0,
                    insets.getSystemWindowInsetBottom()));
        } else {
            return insets;
        }
    }

}
