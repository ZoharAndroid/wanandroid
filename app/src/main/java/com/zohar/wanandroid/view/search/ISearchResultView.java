package com.zohar.wanandroid.view.search;

import com.zohar.wanandroid.base.IBaseView;
import com.zohar.wanandroid.bean.home.Article;

/**
 * Created by zohar on 2019/8/30 13:47
 * Describe:
 */
public interface ISearchResultView extends IBaseView {

    void showLoadingView();
    void hideLoadingView();

    void searchSuccess(Article articlesData);
    void searchFailed(String msg);

    void searchRefreshSuccess(Article articlesData);
    void searchRefreshFailed(String msg);

    void searchLoadMoreSuccess(Article articlesData);
    void searchLoadMoreFailed(String msg);

}
