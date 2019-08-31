package com.zohar.wanandroid.model.collect;

/**
 * Created by zohar on 2019/8/30 16:47
 * Describe:
 */
public interface ICollectModel {

    void collect(String url, int articleId, OnCollectListener onCollectListener);
}
