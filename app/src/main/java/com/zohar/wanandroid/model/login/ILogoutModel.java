package com.zohar.wanandroid.model.login;

import android.content.Context;

import com.zohar.wanandroid.base.IBaseModel;

/**
 * Created by zohar on 2019/8/30 8:55
 * Describe:
 */
public interface ILogoutModel extends IBaseModel {

    void logout(Context context, String url, OnLogoutListener onLogoutListener);
}
