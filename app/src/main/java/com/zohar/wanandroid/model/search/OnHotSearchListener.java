package com.zohar.wanandroid.model.search;

import com.zohar.wanandroid.bean.search.HotSearchData;

/**
 * Created by zohar on 2019/8/30 11:38
 * Describe:
 */
public interface OnHotSearchListener {

    void hotSearchSuccess(HotSearchData hotSearchData);
    void hotSearchFailed(String msg);
}
