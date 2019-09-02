package com.zohar.wanandroid.presenter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.zohar.wanandroid.R;
import com.zohar.wanandroid.bean.collect.CollectData;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.model.collect.CancelCollectModel;
import com.zohar.wanandroid.model.collect.CollectModel;
import com.zohar.wanandroid.model.collect.OnCancelCollectListener;
import com.zohar.wanandroid.view.collect.ICancelCollectView;
import com.zohar.wanandroid.view.collect.ICollectView;

/**
 * Created by zohar on 2019/9/1 11:53
 * Describe:
 */
public class CancelCollectPresenter {

    private ICancelCollectView mView;
    private CancelCollectModel mModel;
    private View clickView;


    public CancelCollectPresenter(ICancelCollectView cancelCollectView, View view) {
        mView = cancelCollectView;
        clickView = view;
        mModel = new CancelCollectModel();
    }

    public CancelCollectPresenter(ICancelCollectView cancelCollectView) {
        mView = cancelCollectView;
        mModel = new CancelCollectModel();
    }

    /**
     * 请求我的收藏列表中的收藏
     *
     * @param context
     * @param articleId
     * @param originId
     */
    public void cancelCollectRequest(final Context context, int articleId, int originId){
        mModel.cancelMyCollect(context, ApiAddress.CANCEL_MY_COLLECT_ADDRESS(articleId),articleId, originId, new OnCancelCollectListener(){

            @Override
            public void cancelCollectSuccess(CollectData data) {
                mView.cancelCollectSuccess(data);
                // 设置图标
                ((ImageView)clickView).setImageResource(R.mipmap.icon_item_collection);
            }

            @Override
            public void cancelCollectFailed(String msg) {
                mView.cancelCollectFailed(msg);
            }
        });
    }

    /**
     * 请求取消文章列表中的收藏
     *
     * @param context
     * @param articleId
     */
    public void cancelArticleListCollectRequest(Context context, int articleId){
        mModel.cancelArticleListCollect(context, ApiAddress.CANCEL_ARTICLE_LIST_COLLECT_ADDRESS(articleId), articleId, new OnCancelCollectListener(){

            @Override
            public void cancelCollectSuccess(CollectData data) {
                mView.cancelCollectSuccess(data);
                // 设置图标
                if (clickView != null) {
                    ((ImageView) clickView).setImageResource(R.mipmap.icon_item_collection);
                }
            }

            @Override
            public void cancelCollectFailed(String msg) {
                mView.cancelCollectFailed(msg);
            }
        });
    }
}
