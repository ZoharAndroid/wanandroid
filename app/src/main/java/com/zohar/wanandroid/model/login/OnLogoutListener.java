package com.zohar.wanandroid.model.login;

import com.zohar.wanandroid.bean.register.RegisterData;

/**
 * Created by zohar on 2019/8/30 8:55
 * Describe:
 */
public interface OnLogoutListener {

    void logoutSuccess(RegisterData logoutData);
    void logoutFailed(String msg);
}
