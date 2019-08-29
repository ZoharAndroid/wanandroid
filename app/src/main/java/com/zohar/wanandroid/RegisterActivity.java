package com.zohar.wanandroid;

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

/**
 * Created by zohar on 2019/8/29 9:40
 * Describe:
 *  注册界面
 */
public class RegisterActivity extends AppCompatActivity {

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
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayShowTitleEnabled(false);
        }
        // 显示标题
        mTextViewTitle.setText("注册");
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
}
