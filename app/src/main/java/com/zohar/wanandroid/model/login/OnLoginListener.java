package com.zohar.wanandroid.model.login;

import com.zohar.wanandroid.bean.User;
import com.zohar.wanandroid.bean.register.RegisterData;

/**
 * Created by zohar on 2019/8/7 15:19
 * Describe:
 *
 *  用户登录回调接口
 *
 */
public interface OnLoginListener {
    /**
     * 登录成功回调
     */
    void loginSuccess(RegisterData loginData);

    /**
     * 登录失败回调
     */
    void loginFailed(String msg);
}
