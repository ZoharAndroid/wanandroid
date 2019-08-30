package com.zohar.wanandroid.presenter;

import android.text.TextUtils;

import com.zohar.wanandroid.view.search.ISearchView;

/**
 * Created by zohar on 2019/8/30 13:50
 * Describe:
 */
public class SearchResultPresenter {

    private ISearchView mView;

    public SearchResultPresenter(ISearchView view) {
        mView = view;
    }

    public void searchRequest(){
        String content = mView.getSearchContent();
        if (TextUtils.isEmpty(content)){
            return;
        }
        // 发送请求也
    }
}
