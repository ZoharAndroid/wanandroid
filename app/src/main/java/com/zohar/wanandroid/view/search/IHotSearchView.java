package com.zohar.wanandroid.view.search;

import com.zohar.wanandroid.bean.search.HotSearchData;

/**
 * Created by zohar on 2019/8/30 11:30
 * Describe:
 */
public interface IHotSearchView {

    void hotSearchSuccess(HotSearchData hotSearchData);
    void hotSearchFailed(String msg);
}
