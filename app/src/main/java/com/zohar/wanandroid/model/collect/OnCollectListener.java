package com.zohar.wanandroid.model.collect;

import com.zohar.wanandroid.bean.home.Article;

/**
 * Created by zohar on 2019/8/30 16:48
 * Describe:
 */
public interface OnCollectListener {

    void collectSuccess(Article data);
    void collectFailed(String msg);
}
