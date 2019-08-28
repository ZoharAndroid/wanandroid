package com.zohar.wanandroid.view.navi;

import com.zohar.wanandroid.bean.navi.NaviData;

/**
 * Created by zohar on 2019/8/28 16:16
 * Describe:
 */
public interface INaviView {

    void showLoadingView();
    void hideLoadingView();

    void httpSuccess(NaviData naviData);
    void httpFailed(String msg);
}
