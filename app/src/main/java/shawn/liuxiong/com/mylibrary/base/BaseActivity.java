package shawn.liuxiong.com.mylibrary.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liuxiong.library.base.LXActivity;
import com.liuxiong.library.kit.KnifeKit;
import com.liuxiong.library.log.XLog;
import com.liuxiong.library.weights.MyToolbar;

import butterknife.ButterKnife;
import shawn.liuxiong.com.mylibrary.R;

/**
 * 文 件 名: BaseActivity
 *
 */
public abstract class BaseActivity extends LXActivity {
    /**
     * 日志输出标志getSupportActionBar().
     **/
//    private TextView title;
//    private ImageView back;
    protected final String TAG = this.getClass().getSimpleName();

//    protected void setTitle(String msg) {
//        if (title != null) {
//            title.setText(msg);
//        }
//    }
//
//    /**
//     * sometime you want to define back event
//     */
//    protected void setBackBtn() {
//        if (back != null) {
//            back.setVisibility(View.VISIBLE);
//            back.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    finish();
//                }
//            });
//        }else {
//            XLog.e("back is null ,please check out");
//        }
//
//    }

//    protected void setBackClickListener(View.OnClickListener l) {
//        if (back != null) {
//            back.setVisibility(View.VISIBLE);
//            back.setOnClickListener(l);
//        }else {
//            XLog.e("back is null ,please check out");
//        }
//
//    }





//    private ViewGroup rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 这句很关键，注意是调用父类的方法
//        super.setContentView(R.layout.activity_base);
        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
//        initToolbar();
//        initContentView(savedInstanceState);
    }

//    public void initContentView(@Nullable Bundle savedInstanceState){
//        if (getLayoutId() > 0) {
//            setContentView(getLayoutId());
//            unbinder = KnifeKit.bind(this);
//        }
//        setListener();
//        initData(savedInstanceState);
//    }


    private void initToolbar() {
        MyToolbar toolbar = ButterKnife.findById(this , R.id.toolbar);
        if (toolbar != null) {
            ((AppCompatActivity)context).setSupportActionBar(toolbar);
            toolbar.setTitle("示例");
        }
        if (getSupportActionBar() != null) {
            // Enable the Up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
//        back = (ImageView) findViewById(R.id.img_back);
//        title = (TextView) findViewById(R.id.title);
    }


//    @Override
//    public void setContentView(int layoutId) {
//        setContentView(View.inflate(this, layoutId, null));
//    }
//
//    @Override
//    public void setContentView(View view) {
//        rootLayout = ButterKnife.findById(this , R.id.rootLayout);
//        if (rootLayout == null) return;
//        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        initToolbar();
//    }
}
