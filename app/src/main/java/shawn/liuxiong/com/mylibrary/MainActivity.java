package shawn.liuxiong.com.mylibrary;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.liuxiong.library.router.Router;
import com.liuxiong.library.weights.MyToolbar;

import butterknife.BindView;
import shawn.liuxiong.com.mylibrary.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.actionButton)
    Button button;
    @BindView(R.id.toolbar)
    MyToolbar toolbar;


    /**
     * 启动
     *
     * @param activity
     */
    public static void launch(Activity activity) {
        Router.newIntent()
                .from(activity)
                .to(MainActivity.class)
                .launch();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
    }

    @Override
    public void onHandleMessage(Message msg) {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        toolbar.setTitle("示例");
        ((AppCompatActivity)context).setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PLListActivity.launch(context);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
