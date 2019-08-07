package com.zohar.wanandroid.presenter;

import android.os.Handler;

import com.zohar.wanandroid.bean.User;
import com.zohar.wanandroid.model.LoginModel;
import com.zohar.wanandroid.model.OnLoginListener;
import com.zohar.wanandroid.view.ILoginView;

/**
 * Created by zohar on 2019/8/7 15:27
 * Describe:
 */
public class LoginPresenter {

    private ILoginView mLoginView;
    private LoginModel mLoginModel;


    private Handler mHandler = new Handler();

    public LoginPresenter(ILoginView loginView) {
        mLoginView = loginView;
        mLoginModel = new LoginModel();
    }

    /**
     * 处理View发送过来的登录操作
     */
    public void login(){
        mLoginView.showLoading();
        // 调用model的login操作去验证登录信息
        mLoginModel.login(mLoginView.getUsername(), mLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mLoginView.hideLoading();
                        mLoginView.showLoginSuccess();
                    }
                });
            }

            @Override
            public void loginFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mLoginView.hideLoading();
                        mLoginView.showLoginFailed();
                    }
                });
            }
        });

    }

    /**
     *  创建destory
     */

    public void destory(){
        mLoginView = null;
        if (mLoginModel != null){
            mLoginModel.cancelTasks();
            mLoginModel = null;
        }
    }
}
