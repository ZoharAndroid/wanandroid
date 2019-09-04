package com.zohar.wanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zohar.wanandroid.bean.register.RegisterData;
import com.zohar.wanandroid.config.AppConstants;
import com.zohar.wanandroid.presenter.login.LoginPresenter;
import com.zohar.wanandroid.utils.ToastUtils;
import com.zohar.wanandroid.view.login.ILoginView;

/**
 * Created by zohar on 2019/8/5 21:46
 * Describe:
 */
public class LoginActivity extends AppCompatActivity implements ILoginView {


    private TextInputEditText mUsernameEditText;
    private TextInputEditText mPasswordEditText;
    private Button mLoginButton;
    private Button mRegisterButton;
    private ProgressBar mLoginProgressbar;
    private Toolbar mToolbar;
    private TextView mTextViewTitle;

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initToolBar();
        initEventAndData();

    }

    private void initEventAndData() {
        mLoginPresenter = new LoginPresenter(this);

        // 登录按钮
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 调用presenter去请求登录操作
                mLoginPresenter.loginRequest(LoginActivity.this, getUsername(), getPassword());
            }
        });

        // 注册按钮
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到注册界面
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                // 消除登录界面
                finish();
            }
        });
    }


    private void initToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayShowTitleEnabled(false);
        }
        // 显示标题
        mTextViewTitle.setText("登录");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 初始化view
     */
    private void initView() {
        mUsernameEditText = findViewById(R.id.user_name_edit_text);
        mPasswordEditText = findViewById(R.id.password_edit_text);
        mLoginButton = findViewById(R.id.login_button);
        mRegisterButton = findViewById(R.id.register_button);
        mLoginProgressbar = findViewById(R.id.login_progress_bar);
        mToolbar = findViewById(R.id.login_tool_bar);
        mTextViewTitle = findViewById(R.id.login_toolbar_title);
    }

    @Override
    public String getUsername() {
        return mUsernameEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPasswordEditText.getText().toString();
    }

    @Override
    public void showLoading() {
        mLoginProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mLoginProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void showLoginSuccess(RegisterData registerData) {
        if (registerData.getErrorCode() == 0){
            ToastUtils.toastShow(LoginActivity.this, "登录成功");
            // 将数据返回到主界面
            Intent intent = new Intent();
            intent.putExtra(AppConstants.LOGIN_USER_NAME_INTENT, getUsername());
            setResult(RESULT_OK, intent);
            // 销毁这个界面
            finish();
        }else {
            ToastUtils.toastShow(LoginActivity.this, registerData.getErrorMsg());
        }
    }

    @Override
    public void showLoginFailed(String msg) {
        ToastUtils.toastShow(this, msg);
    }

    @Override
    public void emptyUsernameOrPassword() {
        ToastUtils.toastShow(this, "用户名或密码不能为空！");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.destory();
        mLoginPresenter = null;
    }
}
