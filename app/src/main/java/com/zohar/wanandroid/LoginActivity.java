package com.zohar.wanandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zohar.wanandroid.presenter.LoginPresenter;
import com.zohar.wanandroid.view.ILoginView;

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

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        mLoginPresenter = new LoginPresenter(this);

        // 登录按钮
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 调用presenter去请求登录操作
                mLoginPresenter.login();
            }
        });

        // 注册按钮
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
    public void showLoginSuccess() {
        Toast.makeText(LoginActivity.this, R.string.login_success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginFailed() {
        Toast.makeText(LoginActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.destory();
        mLoginPresenter = null;
    }
}
