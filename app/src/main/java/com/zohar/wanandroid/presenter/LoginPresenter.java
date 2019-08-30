package com.zohar.wanandroid.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.zohar.wanandroid.bean.register.RegisterData;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.model.login.LoginModel;
import com.zohar.wanandroid.model.login.OnLoginListener;
import com.zohar.wanandroid.view.login.ILoginView;

/**
 * Created by zohar on 2019/8/7 15:27
 * Describe:
 */
public class LoginPresenter {

    private ILoginView mView;
    private LoginModel mModel;

    public LoginPresenter(ILoginView loginView) {
        mView = loginView;
        mModel = new LoginModel();
    }

    /**
     * 处理View发送过来的登录操作
     */
    public void loginRequest(Context context, String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            mView.emptyUsernameOrPassword();
            return;
        }

        mView.showLoading();
        // 调用model的login操作去验证登录信息
        mModel.login(context, ApiAddress.LOGIN_ADDRESS, username, password, new OnLoginListener() {
            @Override
            public void loginSuccess(RegisterData registerData) {
                mView.hideLoading();
                mView.showLoginSuccess(registerData);
            }

            @Override
            public void loginFailed(String msg) {
                mView.hideLoading();
                mView.showLoginFailed(msg);

            }
        });

    }

    /**
     * 创建destory
     */

    public void destory() {
        mView = null;
        if (mModel != null) {
            mModel.cancelTasks();
            mModel = null;
        }
    }
}
