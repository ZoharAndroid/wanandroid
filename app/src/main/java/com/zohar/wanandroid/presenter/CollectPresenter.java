package com.zohar.wanandroid.presenter;

import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.model.collect.CollectModel;
import com.zohar.wanandroid.model.collect.OnCollectListener;
import com.zohar.wanandroid.view.home.ICollectView;

/**
 * Created by zohar on 2019/8/30 16:38
 * Describe:
 */
public class CollectPresenter {

    private ICollectView mView;
    private CollectModel mModel;

    public CollectPresenter(ICollectView view) {
        mView = view;
        mModel = new CollectModel();
    }

    /**
     * 收藏请求
     */
    public void  collectRequest(int articleId){
        mModel.collect(ApiAddress.COLLECT_SITE_ADDRESS(articleId), articleId, new OnCollectListener() {
            @Override
            public void collectSuccess(Article data) {
                mView.collectSuccess(data);
            }

            @Override
            public void collectFailed(String msg) {
                mView.collectFailed(msg);
            }
        });
    }
}
