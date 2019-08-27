package com.zohar.wanandroid.view.knowledge;

import com.zohar.wanandroid.bean.knowledge.Knowledge;

/**
 * Created by zohar on 2019/8/27 13:56
 * Describe:
 */
public interface IKnowledgeView {

    // 显示隐藏ProgressView
    void showLoadingView();
    void hideLoadingView();

    // 请求数据成功
    void httpSuccess(Knowledge knowledge);
    void httpFailed(String msg);


    void refreshHttpSuccess(Knowledge knowledge);
    void refreshHttpFailed(String msg);
}
