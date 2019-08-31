package com.zohar.wanandroid.model.home;

import android.content.Context;

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
    void sendHomeHttp(Context context, String url, OnHttpListener httpListener);

    /**
     *
     * @param url
     * @param bannerListener
     */
    void sendBannerHttp(Context context, String url, OnBannerHttpListener bannerListener);
}
