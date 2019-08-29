package com.zohar.wanandroid.view.register;

import com.zohar.wanandroid.bean.register.RegisterData;

/**
 * Created by zohar on 2019/8/29 10:33
 * Describe:
 */
public interface IRegisterView {

    void showLoadingView();
    void hideLoadingView();

    String getUsername();
    String getPassword();
    String getAgainPassword();

    void emptyUsername();
    void emptyPassword();
    void emptyAgainPassword();
    void passwordDifferent();

    void registerRequestSuccess(RegisterData registerData);
    void registerRequestFailed(String msg);
}
