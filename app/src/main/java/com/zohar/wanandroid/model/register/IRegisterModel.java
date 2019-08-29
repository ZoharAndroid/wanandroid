package com.zohar.wanandroid.model.register;

import android.content.Context;

/**
 * Created by zohar on 2019/8/29 10:53
 * Describe:
 */
public interface IRegisterModel {

    void register(Context context, String url, String username, String password, String againPassword, OnRegisterListener registerListener);
}
