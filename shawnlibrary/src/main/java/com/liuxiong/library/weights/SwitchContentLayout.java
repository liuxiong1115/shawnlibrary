package com.liuxiong.library.weights;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;

import com.liuxiong.library.R;

/**
 *  状态切换Layout
 * 作者：${刘雄} on 2017/1/6 22:34
 * 邮箱：orange_lx0120@126.com
 *
 * SwitchContentLayout 在未指定contentView Layout的情况下默认使用内部第一个子控件作为contentView 所以SwitchContentLayout子控件只能有一个
 */
public class SwitchContentLayout extends FrameLayout {
    View loadingView;
    View errorView;
    View emptyView;
    View contentView;

    int loadingLayoutId = RES_NONE;
    int errorLayoutId = RES_NONE;
    int emptyLayoutId = RES_NONE;
    int contentLayoutId = RES_NONE;

    public static final int STATE_LOADING = 0x1;
    public static final int STATE_ERROR = 0x2;
    public static final int STATE_EMPTY = 0x3;
    public static final int STATE_CONTENT = 0x4;
    int displayState = STATE_LOADING;

    static final int RES_NONE = -1;

    public SwitchContentLayout(Context context) {
        super(context);
    }

    public SwitchContentLayout(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public SwitchContentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attr) {
        TypedArray typedArray = context.obtainStyledAttributes(attr, R.styleable.SwitchContentLayout);
        loadingLayoutId = typedArray.getResourceId(R.styleable.SwitchContentLayout_cl_loadingLayoutId, RES_NONE);
        errorLayoutId = typedArray.getResourceId(R.styleable.SwitchContentLayout_cl_errorLayoutId, RES_NONE);
        emptyLayoutId = typedArray.getResourceId(R.styleable.SwitchContentLayout_cl_emptyLayoutId, RES_NONE);
        contentLayoutId = typedArray.getResourceId(R.styleable.SwitchContentLayout_cl_contentLayoutId, RES_NONE);
        typedArray.recycle();
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        int childCount = getChildCount();
        if (childCount > 4) {
            throw new IllegalStateException("SwitchContentLayout can only host 4 elements");
        } else {
            if (loadingLayoutId != RES_NONE) {
                loadingView = inflate(getContext(), loadingLayoutId, null);
            }
            if (errorLayoutId != RES_NONE) {
                errorView = inflate(getContext(), errorLayoutId, null);
            }
            if (emptyLayoutId != RES_NONE) {
                emptyView = inflate(getContext(), emptyLayoutId, null);
            }
            if (contentLayoutId != RES_NONE) {
                contentView = inflate(getContext(), contentLayoutId, null);
            }


            if (contentView == null) {
                if (childCount == 1) {
                    contentView = getChildAt(0);
                }
            }
            if (contentView == null) {
                throw new IllegalStateException("ContentView can not be null");
            }

            bindView(loadingView, STATE_LOADING);
            bindView(errorView, STATE_ERROR);
            bindView(emptyView, STATE_EMPTY);
            bindView(contentView, STATE_CONTENT);

            if(errorView != null){
                errorView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(listener != null)
                            listener.onRefresh(v);
                    }
                });
            }

            if(emptyView != null){
                emptyView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(listener != null)
                            listener.onRefresh(v);
                    }
                });
            }


            if (loadingView != null) {
                setDisplayState(STATE_LOADING);
            } else {
                setDisplayState(STATE_CONTENT);
            }
        }
    }

    public void setDisplayState(int displayState) {
        this.displayState = displayState;

        if (loadingView != null) {
            if (displayState == STATE_LOADING) {
                loadingView.setVisibility(VISIBLE);
                loadingView.bringToFront();
            } else {
                loadingView.setVisibility(GONE);
            }
        }

        if (errorView != null) {
            if (displayState == STATE_ERROR) {
                errorView.setVisibility(VISIBLE);
                errorView.bringToFront();
            } else {
                errorView.setVisibility(GONE);
            }
        }

        if (emptyView != null) {
            if (displayState == STATE_EMPTY) {
                emptyView.setVisibility(VISIBLE);
                emptyView.bringToFront();
            } else {
                emptyView.setVisibility(GONE);
            }
        }

        if (contentView != null) {
            if (displayState == STATE_CONTENT) {
                contentView.setVisibility(VISIBLE);
                contentView.bringToFront();
            } else {
                contentView.setVisibility(GONE);
            }
        }
    }

    public void bindView(View child, int state) {
        if (child != null) {
            if (state == STATE_LOADING) {
                if (loadingView != null) {
                    removeView(loadingView);
                    loadingView = null;
                }
                loadingView = child;
            }
            if (state == STATE_EMPTY) {
                if (emptyView != null) {
                    removeView(emptyView);
                    emptyView = null;
                }
                emptyView = child;
            }
            if (state == STATE_ERROR) {
                if (errorView != null) {
                    removeView(errorView);
                    errorView = null;
                }
                errorView = child;
            }
            if (state == STATE_CONTENT) {
                if (contentView != null) {
                    removeView(contentView);
                    contentView = null;
                }
                contentView = child;
            }

            ViewParent parent = child.getParent();
            if (parent == null || parent != this) {
                super.addView(child);
            }
        }
    }


    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable = super.onSaveInstanceState();
        SwitchContentLayout.SavedState savedState = new SwitchContentLayout.SavedState(parcelable);
        savedState.state = this.displayState;
        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SwitchContentLayout.SavedState savedState = (SwitchContentLayout.SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.displayState = savedState.state;
        setDisplayState(this.displayState);
    }


    static class SavedState extends BaseSavedState {
        int state;

        SavedState(Parcelable superState) {
            super(superState);
        }

        public SavedState(Parcel source) {
            super(source);
            try {
                state = source.readInt();
            } catch (IllegalArgumentException e) {
                state = STATE_LOADING;
            }
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(state);
        }

        public static final Creator<SwitchContentLayout.SavedState> CREATOR =
                new Creator<SwitchContentLayout.SavedState>() {
                    @Override
                    public SwitchContentLayout.SavedState createFromParcel(Parcel in) {
                        return new SwitchContentLayout.SavedState(in);
                    }

                    @Override
                    public SwitchContentLayout.SavedState[] newArray(int size) {
                        return new SwitchContentLayout.SavedState[size];
                    }
                };

    }

    public void showContent() {
        setDisplayState(STATE_CONTENT);
    }

    public void showEmpty() {
        setDisplayState(STATE_EMPTY);
    }

    public void showError() {
        setDisplayState(STATE_ERROR);
    }

    public void showLoading() {
        setDisplayState(STATE_LOADING);
    }

    //-------

    public SwitchContentLayout loadingView(View loadingView) {
        bindView(loadingView, STATE_LOADING);
        return this;
    }

    public SwitchContentLayout errorView(View errorView) {
        bindView(errorView, STATE_ERROR);
        return this;
    }

    public SwitchContentLayout emptyView(View emptyView) {
        bindView(emptyView, STATE_EMPTY);
        return this;
    }

    public int getDisplayState() {
        return displayState;
    }

    public View getEmptyView() {
        return emptyView;
    }

    public View getLoadingView() {
        return loadingView;
    }

    public View getErrorView() {
        return errorView;
    }


    public interface OnRefreshCallbackListener{
        void onRefresh(View view);
    }

    OnRefreshCallbackListener listener;

    public void setListener(OnRefreshCallbackListener listener) {
        this.listener = listener;
    }

}
