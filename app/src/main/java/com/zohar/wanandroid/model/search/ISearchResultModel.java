package com.zohar.wanandroid.model.search;

import com.zohar.wanandroid.base.IBaseModel;

/**
 * Created by zohar on 2019/8/30 14:19
 * Describe:
 */
public interface ISearchResultModel extends IBaseModel {

    void search(String url, String content, int pageNum, OnSearchListener onSearchListener);
}
