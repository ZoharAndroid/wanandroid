package com.zohar.wanandroid.model.search;

import com.zohar.wanandroid.bean.home.Article;

/**
 * Created by zohar on 2019/8/30 14:20
 * Describe:
 */
public interface OnSearchListener {

    void onSearchSuccess(Article articlesData);
    void onSearchFailed(String msg);
}
