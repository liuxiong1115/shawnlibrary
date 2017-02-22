package com.shuoxue.pro.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;

import com.hkm.ui.processbutton.iml.ActionProcessButton;
import com.liuxiong.library.log.XLog;
import com.liuxiong.library.router.Router;
import com.liuxiong.library.weights.MyToolbar;
import com.shuoxue.pro.MyApplication;
import com.shuoxue.pro.R;
import com.shuoxue.pro.exception.AppException;
import com.shuoxue.pro.kit.AppKit;
import com.shuoxue.pro.manager.SharedPreferencesManager;
import com.shuoxue.pro.model.ResultToken;
import com.shuoxue.pro.service.JsonCallback;
import com.shuoxue.pro.service.NetApi;

import butterknife.BindView;
import okhttp3.Call;

import static com.liuxiong.library.Constants.HANDLE_GET_DATA_WHAT_FAIL;
import static com.liuxiong.library.Constants.HANDLE_GET_DATA_WHAT_SUCCESS;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    MyToolbar toolbar;
    @BindView(R.id.email)
    EditText mEmailView;
    @BindView(R.id.password)
    EditText mPasswordView;
    @BindView(R.id.login)
    ActionProcessButton loginBt;
    @BindView(R.id.login_form)
    ScrollView loginForm;

    /**
     * 启动
     *
     * @param activity
     */
    public static void launch(Activity activity) {
        Router.newIntent()
                .from(activity)
                .to(LoginActivity.class)
                .data(new Bundle())
                .launch();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    public void onHandleMessage(Message msg) {
        switch (msg.what) {
            case HANDLE_GET_DATA_WHAT_SUCCESS:
                MainActivity.launch(LoginActivity.this);
                finish();
                setLoginBtState(-1);
                break;

            case HANDLE_GET_DATA_WHAT_FAIL:
//                showProgress(false);
                setLoginBtState(-2);
                break;
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        toolbar.setTitle(getString(R.string.title_activity_login));
        ((AppCompatActivity)context).setSupportActionBar(toolbar);
        loginBt.setCompleteText(getResources().getString(R.string.loginSuccess));
        loginBt.setText(getResources().getString(R.string.login));
        // set the progress mode on endless
        loginBt.setMode(ActionProcessButton.Mode.ENDLESS);
    }

    @Override
    public void setListener() {
        mEmailView.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setLoginBtState(-1);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mPasswordView.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setLoginBtState(-1);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
//                getUiDelegate().showProgressBar();
            }
        });
    }


    /**
     * 尝试登录
     */
    private void attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            loginBt.setProgress(50);
            loginBt.setText(getResources().getString(R.string.logining));
            loginBt.setEnabled(false);

            login(email, AppKit.MD5(password));
        }
    }


    /**
     * 根据message状态显示不同的状态
     *
     * @return void
     * @author 刘雄 2015-8-6 下午3:15:16
     */
    private void setLoginBtState(int message) {
        XLog.d("showState --- message --- >> " + message);
        if (message == -2) {
            loginBt.setText(getResources().getString(R.string.loginFial));
            loginBt.setEnabled(true);
            loginBt.setProgress(-2);
        } else if (message == -1) {
            loginBt.setText(getResources().getString(R.string.login));
            loginBt.setEnabled(true);
            loginBt.setProgress(0);
        }
    }


    /**
     * 验证用户名是否有效
     *
     * @param email
     * @return
     */
    private boolean isEmailValid(String email) {
        return true;
    }

    /**
     * 验证密码是否有效
     *
     * @param password
     * @return
     */
    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }


    /**
     * 保存用户数据
     *
     * @param account
     * @param pwd
     */
    private void saveUserInfo(String account, String pwd) {
        SharedPreferencesManager.getInstance().updateUserInfo(account, pwd);
    }



    /**
     * @param account
     * @param pwd
     */
    private void login(final String account, final String pwd) {
        XLog.d("login 参数：account=" + account + "   pwd=" + pwd);
        NetApi.login(account, pwd, new JsonCallback<ResultToken>() {
            @Override
            public void onResponse(ResultToken response, int id) {
                XLog.d(response + "  id:" + id);
                if (response.isError()) {
                    AppException.getInstance().handleException(context, response);
                    getUiDelegate().sendHandleSimpleMessage(getUiHadler(), getResources().getString(R.string.dataLoadFail) + response.getMessage(), HANDLE_GET_DATA_WHAT_FAIL);
                } else {
                    getUiDelegate().sendHandleSimpleMessage(getUiHadler(), response, HANDLE_GET_DATA_WHAT_SUCCESS);
                    MyApplication.getContext().setUserToken(response.getObj());
                    saveUserInfo(account, pwd);
                }
            }

            @Override
            public void onFail(Call call, Exception e, int id) {
                AppException.getInstance().handleException(context, e);
                getUiDelegate().sendHandleSimpleMessage(getUiHadler(), getResources().getString(R.string.netError), HANDLE_GET_DATA_WHAT_FAIL);
            }
        });
    }


}
