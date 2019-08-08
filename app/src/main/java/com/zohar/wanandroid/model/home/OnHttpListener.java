package com.zohar.wanandroid.model.home;

import com.zohar.wanandroid.bean.home.Article;

/**
 * Created by zohar on 2019/8/8 16:30
 * Describe:
 */
public interface OnHttpListener {

    void httpSuccess(Article article);
    void httpFailed(String msg);

}
