package com.zohar.wanandroid.view.collect;

import com.zohar.wanandroid.bean.collect.CollectData;
import com.zohar.wanandroid.bean.home.Article;

/**
 * Created by zohar on 2019/8/31 16:08
 * Describe:
 */
public interface ICollectListView {

    void showLoadingView();
    void hideLoadingView();

    void collectListSuccess(CollectData collectData);
    void collectListFailed(String msg);

    void collectListRefreshSuccess(CollectData collectData);

    void collectListLoadMoreSuccess(CollectData collectData);
}
