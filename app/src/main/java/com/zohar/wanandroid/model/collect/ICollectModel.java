package com.zohar.wanandroid.model.collect;

import android.content.Context;

/**
 * Created by zohar on 2019/8/30 16:47
 * Describe:
 */
public interface ICollectModel {

    void collect(Context context, String url, int articleId, OnCollectListener onCollectListener);
}
