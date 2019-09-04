package com.zohar.wanandroid.presenter.register;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.zohar.wanandroid.bean.register.RegisterData;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.model.register.OnRegisterListener;
import com.zohar.wanandroid.model.register.RegisterModel;
import com.zohar.wanandroid.view.register.IRegisterView;

/**
 * Created by zohar on 2019/8/29 10:41
 * Describe:
 */
public class RegisterPresenter {

    private IRegisterView mView;
    private RegisterModel mModel;

    private Handler mHandler = new Handler();

    public RegisterPresenter(IRegisterView view) {
        mView = view;
        mModel = new RegisterModel();
    }

    /**
     * 注册请求
     *
     * @param username      用户名
     * @param password      密码
     * @param againPassword 确认密码
     */
    public void registerRequest(Context context, String username, String password, String againPassword) {
        if (TextUtils.isEmpty(username)) {
            mView.emptyUsername();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            mView.emptyPassword();
            return;
        }

        if (TextUtils.isEmpty(againPassword)) {
            mView.emptyAgainPassword();
            return;
        }

        // 两次密码不一致
        if (!againPassword.equals(password)) {
            mView.passwordDifferent();
            return;
        }

        // 然后去请求服务器
        // 显示请求加载
        mView.showLoadingView();
        mModel.register(context, ApiAddress.REGISTER_ADDRESS, username, password, againPassword, new OnRegisterListener() {
            @Override
            public void registerSuccess(final RegisterData registerData) {
                mView.hideLoadingView();
                mView.registerRequestSuccess(registerData);
            }

            @Override
            public void registerFailed(final String msg) {
                mView.hideLoadingView();
                mView.registerRequestFailed(msg);
            }
        });
    }
}
