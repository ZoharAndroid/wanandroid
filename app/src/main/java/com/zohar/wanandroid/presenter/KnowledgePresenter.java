package com.zohar.wanandroid.presenter;


import android.content.Context;
import android.os.Handler;

import com.zohar.wanandroid.bean.knowledge.Knowledge;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.model.knowledge.KnowledgeModel;
import com.zohar.wanandroid.model.knowledge.OnKnowledgeListener;
import com.zohar.wanandroid.view.knowledge.IKnowledgeView;

/**
 * Created by zohar on 2019/8/27 14:02
 * Describe:
 */
public class KnowledgePresenter {

    private IKnowledgeView mKnowledgeView;
    private KnowledgeModel mKnowledgeModel;
    private Context context;

    private Handler mHandler = new Handler();

    public KnowledgePresenter(Context context,IKnowledgeView knowledgeView) {
        this.context = context;
        mKnowledgeView = knowledgeView;
        mKnowledgeModel = new KnowledgeModel();
    }

    // presenter 通过 model 发送http请求
    public void sendKnowledgeRequest(String url){
        mKnowledgeView.showLoadingView();
        mKnowledgeModel.sendKnowledgeGetRequest(context, url, new OnKnowledgeListener() {
            @Override
            public void httpFailed(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mKnowledgeView.hideLoadingView();
                        mKnowledgeView.httpFailed(msg);
                    }
                });

            }

            @Override
            public void knowledgeHttpSuccess(final Knowledge knowledge) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mKnowledgeView.hideLoadingView();
                        mKnowledgeView.httpSuccess(knowledge);
                    }
                });
            }
        });

    }

    /**
     * 刷新请求
     */
    public void onRefresh(){
        mKnowledgeModel.sendKnowledgeGetRequest(context, ApiAddress.KNOWLEDGE_TREE, new OnKnowledgeListener() {
            @Override
            public void httpFailed(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mKnowledgeView.refreshHttpFailed(msg);
                    }
                });

            }

            @Override
            public void knowledgeHttpSuccess(final Knowledge knowledge) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mKnowledgeView.refreshHttpSuccess(knowledge);
                    }
                });
            }
        });
    }
}
