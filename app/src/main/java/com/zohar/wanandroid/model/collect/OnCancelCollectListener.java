package com.zohar.wanandroid.model.collect;

import com.zohar.wanandroid.bean.collect.CollectData;
import com.zohar.wanandroid.bean.home.Article;

/**
 * Created by zohar on 2019/9/1 12:09
 * Describe:
 */
public interface OnCancelCollectListener {

    void cancelCollectSuccess(CollectData data);
    void cancelCollectFailed(String msg);
}
