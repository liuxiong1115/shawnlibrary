package com.shuoxue.pro.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.EmptyUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.liuxiong.library.log.XLog;
import com.liuxiong.library.router.Router;
import com.liuxiong.library.weights.MyToolbar;
import com.shuoxue.pro.MyApplication;
import com.shuoxue.pro.R;
import com.shuoxue.pro.adapter.ExamAdapter;
import com.shuoxue.pro.exception.AppException;
import com.shuoxue.pro.manager.SharedPreferencesManager;
import com.shuoxue.pro.model.Exam;
import com.shuoxue.pro.model.ResultExams;
import com.shuoxue.pro.model.User;
import com.shuoxue.pro.model.UserToken;
import com.shuoxue.pro.service.JsonCallback;
import com.shuoxue.pro.service.NetApi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.liuxiong.library.Constants.HANDLE_GET_DATA_KEY;
import static com.liuxiong.library.Constants.HANDLE_GET_DATA_WHAT_FAIL;
import static com.liuxiong.library.Constants.HANDLE_GET_DATA_WHAT_SUCCESS;


/**
 * 显示当前Exam列表
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    MyToolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh)
    TwinklingRefreshLayout refresh;
    @BindView(R.id.content_main)
    RelativeLayout contentMain;


    ExamAdapter adapter;

    /**
     * 启动
     *
     * @param activity
     */
    public static void launch(Activity activity) {
        Router.newIntent()
                .from(activity)
                .to(MainActivity.class)
                .data(new Bundle())
                .launch();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onHandleMessage(Message msg) {
        refresh.finishRefreshing();
        switch (msg.what){
            case HANDLE_GET_DATA_WHAT_SUCCESS:
                ResultExams response = (ResultExams)msg.getData().getSerializable(HANDLE_GET_DATA_KEY);
                List<Exam> exams = response.getObj();
                if(exams != null) {
                    adapter.setNewData(exams);
                }
                break;

            case HANDLE_GET_DATA_WHAT_FAIL:
                getUiDelegate().toastShort((String)msg.obj);
                break;
        }

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        toolbar.setTitle(getString(R.string.title_activity_exams));
        ((AppCompatActivity)context).setSupportActionBar(toolbar);

        ProgressLayout headerView = new ProgressLayout(this);
        refresh.setHeaderView(headerView);
        View exHeader = View.inflate(this, R.layout.view_exams_header, null);
        initHeaderView(exHeader);
        refresh.addFixedExHeader(exHeader);
        refresh.setOverScrollRefreshShow(false);
        refresh.setEnableLoadmore(false);
        refresh.setEnableRefresh(true);

        adapter = new ExamAdapter();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                getUiDelegate().toastShort(adapter.getItem(position) + "");
            }
        });
        mRecyclerView.setAdapter(adapter);

        queryExams();
    }


    private void initHeaderView(View view){
        AppCompatImageView thumb = ButterKnife.findById(view , R.id.thumb);
        TextView name = ButterKnife.findById(view , R.id.name);

        UserToken token = MyApplication.getContext().getUserToken();
        if(EmptyUtils.isNotEmpty(token)){
            return ;
        }
        name.setText(token.getLogName());
    }



    @Override
    public void setListener() {
        refresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
//                super.onRefresh(refreshLayout);
                queryExams();
            }

            @Override
            public void onFinishRefresh() {
                super.onFinishRefresh();
            }
        });
    }

    private String getUserId(){
        UserToken token = MyApplication.getContext().getUserToken();
        if(EmptyUtils.isNotEmpty(token)){
            return token.getUserId();
        }
        return "";
    }

    public void queryExams(){
        NetApi.queryExams(getUserId(), new JsonCallback<ResultExams>() {
            @Override
            public void onFail(Call call, Exception e, int id) {
                AppException.getInstance().handleException(context , e);
                getUiDelegate().sendHandleSimpleMessage(getUiHadler() , "网络错误" ,HANDLE_GET_DATA_WHAT_FAIL );
            }

            @Override
            public void onResponse(ResultExams response, int id) {
                XLog.json(response+"");
                if(response.isError()){
                    AppException.getInstance().handleException(context , response);
                    getUiDelegate().sendHandleSimpleMessage(getUiHadler() , "数据加载失败-" + response.getMessage(),HANDLE_GET_DATA_WHAT_FAIL );
                }else{
                    getUiDelegate().sendHandleSerializableMessage(getUiHadler() ,HANDLE_GET_DATA_KEY ,response ,HANDLE_GET_DATA_WHAT_SUCCESS);
                }
            }
        });
    }


}
