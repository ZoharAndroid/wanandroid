package com.zohar.wanandroid.model.search;

import android.content.Context;

/**
 * Created by zohar on 2019/8/30 11:37
 * Describe:
 */
public interface IHotSearchModel {

    void hotSearch(Context context, String url, OnHotSearchListener onHotSearchListener);
}
