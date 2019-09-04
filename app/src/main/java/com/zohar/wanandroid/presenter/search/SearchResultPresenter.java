package com.zohar.wanandroid.presenter.search;

import android.text.TextUtils;

import com.zohar.wanandroid.base.BasePresenter;
import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.model.search.OnSearchListener;
import com.zohar.wanandroid.model.search.SearchResultModel;
import com.zohar.wanandroid.view.search.ISearchResultView;

/**
 * Created by zohar on 2019/8/30 13:50
 * Describe:
 */
public class SearchResultPresenter extends BasePresenter<SearchResultModel, ISearchResultView> {


    public void searchRequest(String searchContent, int pageNum){
        getView().showLoadingView();
        // 发送请求
        getModule().search(ApiAddress.SEARCH_ADDRESS(pageNum), searchContent, pageNum, new OnSearchListener() {
            @Override
            public void onSearchSuccess(Article articlesData) {
                getView().hideLoadingView();
                getView().searchSuccess(articlesData);
            }

            @Override
            public void onSearchFailed(String msg) {
                getView().hideLoadingView();
                getView().searchFailed(msg);
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
        getModule().search(ApiAddress.SEARCH_ADDRESS(0), searchContent, 0, new OnSearchListener() {
            @Override
            public void onSearchSuccess(Article articlesData) {
                getView().searchRefreshSuccess(articlesData);
            }

            @Override
            public void onSearchFailed(String msg) {
                getView().searchRefreshFailed(msg);
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
        getModule().search(ApiAddress.SEARCH_ADDRESS(pageNum), searchContent, pageNum, new OnSearchListener() {
            @Override
            public void onSearchSuccess(Article articlesData) {
                getView().searchLoadMoreSuccess(articlesData);
            }

            @Override
            public void onSearchFailed(String msg) {
                getView().searchLoadMoreFailed(msg);
            }
        });
    }

    @Override
    protected SearchResultModel createModule() {
        return new SearchResultModel();
    }
}
