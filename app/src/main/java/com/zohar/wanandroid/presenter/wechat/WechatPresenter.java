package com.zohar.wanandroid.presenter.wechat;


import android.content.Context;
import android.os.Handler;

import com.zohar.wanandroid.bean.knowledge.Knowledge;
import com.zohar.wanandroid.model.knowledge.KnowledgeModel;
import com.zohar.wanandroid.model.knowledge.OnKnowledgeListener;
import com.zohar.wanandroid.view.wechat.IWechatView;

/**
 * Created by zohar on 2019/8/27 14:02
 * Describe:
 */
public class WechatPresenter {

   private IWechatView mView;
    private KnowledgeModel mModel;
    private Context context;

    private Handler mHandler = new Handler();

    public WechatPresenter(Context context, IWechatView wechatView) {
        this.context = context;
        mView = wechatView;
        mModel = new KnowledgeModel();
    }

    // presenter 通过 model 发送http请求
    public void sendWechatRequest(String url){
        mView.showLoadingView();
        mModel.sendKnowledgeGetRequest(context, url, new OnKnowledgeListener() {
            @Override
            public void httpFailed(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.hideLoadingView();
                        mView.httpFailed(msg);
                    }
                });

            }

            @Override
            public void knowledgeHttpSuccess(final Knowledge knowledge) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.hideLoadingView();
                        mView.httpSuccess(knowledge);
                    }
                });
            }
        });

    }

    /**
     * 刷新请求
     */
    public void onRefresh(){
//        mKnowledgeModel.sendKnowledgeGetRequest(ApiAddress.KNOWLEDGE_TREE, new OnKnowledgeListener() {
//            @Override
//            public void httpFailed(final String msg) {
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mKnowledgeView.refreshHttpFailed(msg);
//                    }
//                });
//
//            }
//
//            @Override
//            public void knowledgeHttpSuccess(final Knowledge knowledge) {
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mKnowledgeView.refreshHttpSuccess(knowledge);
//                    }
//                });
//            }
//        });
    }
}
