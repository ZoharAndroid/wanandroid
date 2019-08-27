package com.zohar.wanandroid.presenter;


import android.os.Handler;

import com.zohar.wanandroid.bean.knowledge.Knowledge;
import com.zohar.wanandroid.http.HttpRequestUtils;
import com.zohar.wanandroid.model.knowledge.KnowledgeModel;
import com.zohar.wanandroid.model.knowledge.OnKnowledgeListener;
import com.zohar.wanandroid.view.knowledge.IKnowledgeView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zohar on 2019/8/27 14:02
 * Describe:
 */
public class KnowledgePresenter {

    private IKnowledgeView mKnowledgeView;
    private KnowledgeModel mKnowledgeModel;

    private Handler mHandler = new Handler();

    public KnowledgePresenter(IKnowledgeView knowledgeView) {
        mKnowledgeView = knowledgeView;
        mKnowledgeModel = new KnowledgeModel();
    }

    // presenter 通过 model 发送http请求
    public void sendKnowledgeRequest(String url){
        mKnowledgeView.showLoadingView();
        mKnowledgeModel.sendKnowledgeGetRequest(url, new OnKnowledgeListener() {
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
}
