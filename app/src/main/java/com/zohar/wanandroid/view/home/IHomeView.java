package com.zohar.wanandroid.view.home;

import com.zohar.wanandroid.bean.home.Article;

/**
 * Created by zohar on 2019/8/8 16:26
 * Describe:
 */
public interface IHomeView {

    void showLoading();
    void hideLoading();

    void httpSuccess(Article data);
    void httpFailed(String msg);
}
