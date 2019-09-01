package com.zohar.wanandroid.view.collect;

import com.zohar.wanandroid.bean.collect.CollectData;

/**
 * Created by zohar on 2019/9/1 11:46
 * Describe:
 */
public interface ICancelCollectView {

    void cancelCollectSuccess(CollectData collectData);
    void cancelCollectFailed(String msg);
}
