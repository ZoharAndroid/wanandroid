package com.zohar.wanandroid.view.login;

import com.zohar.wanandroid.bean.register.RegisterData;

/**
 * Created by zohar on 2019/8/7 15:00
 * Describe:
 */
public interface ILoginView {

    /**
     * 获取用户名
     *
     * @return string 用户名
     */
    String getUsername();

    /**
     * 获取密码
     *
     * @return 密码
     */
    String getPassword();

    /**
     * 显示加载信息
     *
     */
    void showLoading();

    /**
     * 隐藏加载信息
     */
    void hideLoading();

    /**
     * 显示登录成功的信息
     */
    void showLoginSuccess(RegisterData registerData);

    /**
     * 显示登录失败的信息
     */
    void showLoginFailed(String msg);

    void emptyUsernameOrPassword();

}
