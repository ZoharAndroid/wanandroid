package com.zohar.wanandroid.model.home;

/**
 * Created by zohar on 2019/8/8 16:29
 * Describe:
 */
public interface IHomeModel {

    /**
     * 去发送请求到服务器
     * @param url
     * @param httpListener
     */
    void sendHomeHttp(String url, OnHttpListener httpListener);

    /**
     *
     * @param url
     * @param bannerListener
     */
    void sendBannerHttp(String url, OnBannerHttpListener bannerListener);
}
