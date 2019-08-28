package com.zohar.wanandroid.model.navi;

import com.zohar.wanandroid.bean.navi.NaviData;

/**
 * Created by zohar on 2019/8/28 16:25
 * Describe:
 */
public interface OnHttpListener {

    void httpSuccess(NaviData naviData);

    void httpFailed(String msg);
}
