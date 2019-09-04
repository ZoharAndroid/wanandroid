package com.zohar.wanandroid.presenter.login;

import android.content.Context;

import com.zohar.wanandroid.base.BasePresenter;
import com.zohar.wanandroid.base.IBaseModel;
import com.zohar.wanandroid.bean.register.RegisterData;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.model.login.ILogoutModel;
import com.zohar.wanandroid.model.login.LogoutModel;
import com.zohar.wanandroid.model.login.OnLogoutListener;
import com.zohar.wanandroid.view.login.ILoginOutView;

/**
 * Created by zohar on 2019/8/30 8:46
 * Describe:
 */
public class LogoutPresenter extends BasePresenter<LogoutModel, ILoginOutView> {


    public void logoutRequest(Context context){
        getView().showLoadingView();
        getModule().logout(context, ApiAddress.LOGOUT_ADDRESS, new OnLogoutListener() {

            @Override
            public void logoutSuccess(RegisterData logoutData) {
                getView().hideLoadingView();
                getView().logoutSuccess(logoutData);
            }

            @Override
            public void logoutFailed(String msg) {
                getView().hideLoadingView();
                getView().logoutFailed(msg);
            }
        });
    }

    @Override
    protected LogoutModel createModule() {
        return new LogoutModel();
    }
}
