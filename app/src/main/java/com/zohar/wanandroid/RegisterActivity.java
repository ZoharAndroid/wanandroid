package com.zohar.wanandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zohar.wanandroid.bean.register.RegisterData;
import com.zohar.wanandroid.http.cookies.PersistentCookieStore;
import com.zohar.wanandroid.presenter.RegisterPresenter;
import com.zohar.wanandroid.utils.LogUtils;
import com.zohar.wanandroid.utils.ToastUtils;
import com.zohar.wanandroid.view.register.IRegisterView;

import java.util.List;

import okhttp3.Cookie;

/**
 * Created by zohar on 2019/8/29 9:40
 * Describe:
 * 注册界面
 */
public class RegisterActivity extends AppCompatActivity implements IRegisterView {

    private ProgressBar mLoadProgress;
    private Toolbar mToolbar;
    private TextView mTextViewTitle;
    private Button mRegisterButton;
    private TextInputEditText mUsernameEdit;
    private TextInputEditText mPasswordEdit;
    private TextInputEditText mAgainPasswordEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initToolBar();
        initEventAndData();
    }

    private void initEventAndData() {

        final RegisterPresenter mPresenter = new RegisterPresenter(this);

        // 注册按钮监听事件
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 通过presenter发送注册请求
                mPresenter.registerRequest(RegisterActivity.this, getUsername(), getPassword(), getAgainPassword());
            }
        });
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        // 显示标题
        mTextViewTitle.setText("注册");
        // 点击标题栏中的返回按钮
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 初始化View
     */
    private void initView() {
        mLoadProgress = findViewById(R.id.register_login_progress_bar);
        mToolbar = findViewById(R.id.register_tool_bar);
        mTextViewTitle = findViewById(R.id.register_toolbar_title);
        mRegisterButton = findViewById(R.id.register_button);
        mUsernameEdit = findViewById(R.id.register_user_name_edit_text);
        mPasswordEdit = findViewById(R.id.register_password_edit_text);
        mAgainPasswordEdit = findViewById(R.id.again_register_password_edit_text);
    }

    @Override
    public void showLoadingView() {
        mLoadProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingView() {
        mLoadProgress.setVisibility(View.GONE);
    }

    @Override
    public String getUsername() {
        return mUsernameEdit.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPasswordEdit.getText().toString();
    }

    @Override
    public String getAgainPassword() {
        return mAgainPasswordEdit.getText().toString();
    }

    @Override
    public void emptyUsername() {
        ToastUtils.toastShow(this, "用户名不能为空!");
    }

    @Override
    public void emptyPassword() {
        ToastUtils.toastShow(this, "密码不能为空!");
    }

    @Override
    public void emptyAgainPassword() {
        ToastUtils.toastShow(this, "确认密码不能为空!");
    }

    @Override
    public void passwordDifferent() {
        ToastUtils.toastShow(this, "两次密码不一致！");
    }

    @Override
    public void registerRequestSuccess(RegisterData registerData) {
        if (registerData.getErrorCode() == 0){
            // TODO:注册成功
            PersistentCookieStore cookieStore = new PersistentCookieStore(this);
            List<Cookie> cookies = cookieStore.getCookies();
            for (Cookie cookie : cookies){
                LogUtils.d("注册：" + cookie.name());
            }
            LogUtils.d(registerData.getData().toString());
        }else if (registerData.getErrorCode() == -1){
            ToastUtils.toastShow(this, registerData.getErrorMsg());
        }
    }

    @Override
    public void registerRequestFailed(String msg) {
        ToastUtils.toastShow(this, msg);
    }


}
