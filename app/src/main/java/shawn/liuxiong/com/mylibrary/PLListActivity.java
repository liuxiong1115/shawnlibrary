package shawn.liuxiong.com.mylibrary;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.liuxiong.library.router.Router;

import shawn.liuxiong.com.mylibrary.base.BaseActivity;

public class PLListActivity extends BaseActivity {

    /**
     * 启动
     *
     * @param activity
     */
    public static void launch(Activity activity) {
        Router.newIntent()
                .from(activity)
                .to(PLListActivity.class)
                .launch();
    }

    @Override
    public void onHandleMessage(Message msg) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pllist;
    }
}
