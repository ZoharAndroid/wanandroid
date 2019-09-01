package com.zohar.wanandroid.view.collect;

import android.view.View;

import com.zohar.wanandroid.bean.home.Article;

/**
 * Created by zohar on 2019/8/30 16:39
 * Describe:
 */
public interface ICollectView {

    void collectSuccess(Article data);
    void collectFailed(String msg);

    void changeCollectSuccessView(View clickView);
}
