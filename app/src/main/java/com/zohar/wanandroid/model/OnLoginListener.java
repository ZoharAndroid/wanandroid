package com.zohar.wanandroid.model;

import com.zohar.wanandroid.bean.User;

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
    void loginSuccess(User user);

    /**
     * 登录失败回调
     */
    void loginFailed();
}
