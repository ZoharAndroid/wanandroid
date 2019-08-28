package com.zohar.wanandroid.model.navi;

/**
 * Created by zohar on 2019/8/28 16:24
 * Describe:
 */
public interface INaviModel {

    void sendHttpRequest(String url, OnHttpListener httpListener);
}
