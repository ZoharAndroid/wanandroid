package com.zohar.wanandroid.presenter.collect;

import android.content.Context;
import android.view.View;

import com.zohar.wanandroid.bean.collect.CollectData;
import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.model.collect.CollectModel;
import com.zohar.wanandroid.model.collect.OnCollectListener;
import com.zohar.wanandroid.view.collect.ICollectView;

/**
 * Created by zohar on 2019/8/30 16:38
 * Describe:
 */
public class CollectPresenter {

    private ICollectView mView;
    private CollectModel mModel;
    private View clickView;

    public CollectPresenter(ICollectView view, View v) {
        mView = view;
        clickView = v;
        mModel = new CollectModel();
    }

    public CollectPresenter(ICollectView view) {
        mView = view;
        mModel = new CollectModel();
    }



    /**
     * 收藏请求
     */
    public void  collectRequest(Context context, int articleId){
        mModel.collect(context, ApiAddress.COLLECT_SITE_ADDRESS(articleId), articleId, new OnCollectListener() {
            @Override
            public void collectSuccess(Article data) {
                mView.collectSuccess(data);
                mView.changeCollectSuccessView(clickView);
            }

            @Override
            public void collectFailed(String msg) {
                mView.collectFailed(msg);
            }
        });
    }

}
