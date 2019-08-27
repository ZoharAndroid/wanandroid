package com.zohar.wanandroid.view.knowledge.list;

import com.zohar.wanandroid.bean.home.Article;

/**
 * Created by zohar on 2019/8/27 22:26
 * Describe:
 */
public interface IKnowledgeListView {

    void showLoadingView();
    void hideLoadingView();

    void httpSuccess(Article data);
    void httpFailed(String msg);

    void loadMoreRequestSuccess(Article data);
    void loadMoreRequestFailed(String msg);

    void refreshRequestSuccess(Article data);
    void refreshRequestFailded(String msg);

}
