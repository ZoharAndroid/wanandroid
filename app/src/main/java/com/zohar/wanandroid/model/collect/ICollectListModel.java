package com.zohar.wanandroid.model.collect;

import android.content.Context;

/**
 * Created by zohar on 2019/9/1 9:40
 * Describe:
 */
public interface ICollectListModel {

    void collectList(Context context, String url, int pageNum, OnCollectListener onCollectListener);

}
