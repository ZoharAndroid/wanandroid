package com.zohar.wanandroid.model.collect;

import android.content.Context;

/**
 * Created by zohar on 2019/9/1 11:55
 * Describe:
 */
public interface ICancelCollectModel {

    void cancelCollect(Context context, String url, int pageNum, OnCollectListener onCollectListener);
}
