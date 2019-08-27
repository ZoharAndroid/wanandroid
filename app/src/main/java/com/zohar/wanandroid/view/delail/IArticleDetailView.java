package com.zohar.wanandroid.view.delail;

/**
 * Created by zohar on 2019/8/25 16:29
 * Describe:
 *  文章详情页View
 */
public interface IArticleDetailView {

    // 显示加载View
    void showLoading();
    // 隐藏加载View
    void hideLoading();

    // 加载网页
    void loadArticleContent();
    // 加载网页监听
    void listenLoadingFinish();

    //
}
