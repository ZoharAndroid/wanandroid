package com.zohar.wanandroid.presenter.search;

import android.text.TextUtils;

import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.model.search.OnSearchListener;
import com.zohar.wanandroid.model.search.SearchResultModel;
import com.zohar.wanandroid.view.search.ISearchResultView;

/**
 * Created by zohar on 2019/8/30 13:50
 * Describe:
 */
public class SearchResultPresenter {

    private ISearchResultView mView;
    private SearchResultModel mModel;

    public SearchResultPresenter(ISearchResultView view) {
        mView = view;
        mModel = new SearchResultModel();
    }

    public void searchRequest(String searchContent, int pageNum){
        mView.showLoadingView();
        // 发送请求
        mModel.search(ApiAddress.SEARCH_ADDRESS(pageNum), searchContent, pageNum, new OnSearchListener() {
            @Override
            public void onSearchSuccess(Article articlesData) {
                mView.hideLoadingView();
                mView.searchSuccess(articlesData);
            }

            @Override
            public void onSearchFailed(String msg) {
                mView.hideLoadingView();
                mView.searchFailed(msg);
            }
        });
    }

    /**
     * 刷新
     *
     * @param searchContent
     */
    public void searchRefresh(String searchContent){
        // 发送请求
        mModel.search(ApiAddress.SEARCH_ADDRESS(0), searchContent, 0, new OnSearchListener() {
            @Override
            public void onSearchSuccess(Article articlesData) {
                mView.searchRefreshSuccess(articlesData);
            }

            @Override
            public void onSearchFailed(String msg) {
                mView.searchRefreshFailed(msg);
            }
        });
    }

    /**
     * 加载更多
     *
     * @param searchContent 搜索的内容
     * @param pageNum 页面
     */
    public void searchLoadMore(String searchContent, int pageNum){
        // 发送请求
        mModel.search(ApiAddress.SEARCH_ADDRESS(pageNum), searchContent, pageNum, new OnSearchListener() {
            @Override
            public void onSearchSuccess(Article articlesData) {
                mView.searchLoadMoreSuccess(articlesData);
            }

            @Override
            public void onSearchFailed(String msg) {
                mView.searchLoadMoreFailed(msg);
            }
        });
    }
}
