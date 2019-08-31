package com.zohar.wanandroid.model.navi;

import android.content.Context;

/**
 * Created by zohar on 2019/8/28 16:24
 * Describe:
 */
public interface INaviModel {

    void sendHttpRequest(Context context, String url, OnHttpListener httpListener);
}
