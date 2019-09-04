package com.zohar.wanandroid.presenter.login;

import android.content.Context;

import com.zohar.wanandroid.bean.register.RegisterData;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.model.login.LogoutModel;
import com.zohar.wanandroid.model.login.OnLogoutListener;
import com.zohar.wanandroid.view.login.ILoginOutView;

/**
 * Created by zohar on 2019/8/30 8:46
 * Describe:
 */
public class LogoutPresenter {

    private ILoginOutView mView;
    private LogoutModel mModel;

    public LogoutPresenter(ILoginOutView view) {
        mView = view;
        mModel = new LogoutModel();
    }

    public void logoutRequest(Context context){
        mView.showLoadingView();
        mModel.logout(context, ApiAddress.LOGOUT_ADDRESS, new OnLogoutListener() {

            @Override
            public void logoutSuccess(RegisterData logoutData) {
                mView.hideLoadingView();
                mView.logoutSuccess(logoutData);
            }

            @Override
            public void logoutFailed(String msg) {
                mView.hideLoadingView();
                mView.logoutFailed(msg);
            }
        });
    }
}
