package com.zohar.wanandroid.view.wechat;

import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.bean.knowledge.Knowledge;

/**
 * Created by zohar on 2019/8/27 22:26
 * Describe:
 */
public interface IWechatView {

    void showLoadingView();
    void hideLoadingView();

    void httpSuccess(Knowledge knowledge);
    void httpFailed(String msg);

    void loadMoreRequestSuccess(Knowledge knowledge);
    void loadMoreRequestFailed(String msg);

    void refreshRequestSuccess(Knowledge knowledge);
    void refreshRequestFailded(String msg);

}
