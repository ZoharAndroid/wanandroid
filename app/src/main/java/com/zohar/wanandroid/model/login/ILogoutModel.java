package com.zohar.wanandroid.model.login;

import android.content.Context;

/**
 * Created by zohar on 2019/8/30 8:55
 * Describe:
 */
public interface ILogoutModel {

    void logout(Context context, String url, OnLogoutListener onLogoutListener);
}
