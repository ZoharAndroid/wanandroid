package com.zohar.wanandroid.presenter;

import com.zohar.wanandroid.view.delail.IArticleDetailView;

/**
 * Created by zohar on 2019/8/25 16:32
 * Describe:
 */
public class ArticleDetailPresenter {


    private IArticleDetailView mView;

    public ArticleDetailPresenter(IArticleDetailView view) {
        mView = view;
    }

    public void loadArticleContent(){
        mView.showLoading();
        mView.loadArticleContent();
        mView.listenLoadingFinish();
    }


}
