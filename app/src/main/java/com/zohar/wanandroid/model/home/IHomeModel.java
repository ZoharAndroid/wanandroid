package com.zohar.wanandroid.model.home;

/**
 * Created by zohar on 2019/8/8 16:29
 * Describe:
 */
public interface IHomeModel {

    // 去发送请求到服务器
    void sendHomeHttp(String url, OnHttpListener httpListener);
}
