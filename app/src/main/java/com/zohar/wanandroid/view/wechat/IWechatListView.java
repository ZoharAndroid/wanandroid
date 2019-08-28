package com.zohar.wanandroid.view.wechat;

import com.zohar.wanandroid.bean.home.Article;

/**
 * Created by zohar on 2019/8/28 14:55
 * Describe:
 */
public interface IWechatListView {

    void showLoadingView();
    void hideLoadingView();

    void httpSuccess(Article data);
    void httpFailed(String msg);

    void loadMoreRequestSuccess(Article data);
    void loadMoreRequestFailed(String msg);

    void refreshRequestSuccess(Article data);
    void refreshRequestFailded(String msg);

}
