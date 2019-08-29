package com.zohar.wanandroid.model.login;

import android.content.Context;

/**
 * Created by zohar on 2019/8/7 15:19
 * Describe:
 */
public interface ILoginModel {

    /**
     * model提供向外的接口，然后通过OnLoginListener给调用者回调信息
     *
     * @param username 用户名
     * @param password 密码
     * @param loginListener 回调接口
     */
    void login(Context context, String url, String username, String password, OnLoginListener loginListener );

    
}
