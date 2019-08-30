package com.zohar.wanandroid.model.search;

/**
 * Created by zohar on 2019/8/30 14:19
 * Describe:
 */
public interface ISearchResultModel {

    void search(String url, String content, int pageNum, OnSearchListener onSearchListener);
}
