package com.zohar.wanandroid.model.register;

/**
 * Created by zohar on 2019/8/29 10:53
 * Describe:
 */
public interface IRegisterModel {

    void register(String url, String username, String password, String againPassword, OnRegisterListener registerListener);
}
