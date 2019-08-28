package com.zohar.wanandroid.view.project;

import com.zohar.wanandroid.bean.knowledge.Knowledge;

/**
 * Created by zohar on 2019/8/27 22:26
 * Describe:
 */
public interface IProjectView {

    void showLoadingView();
    void hideLoadingView();

    void httpSuccess(Knowledge knowledge);
    void httpFailed(String msg);

    void loadMoreRequestSuccess(Knowledge knowledge);
    void loadMoreRequestFailed(String msg);

    void refreshRequestSuccess(Knowledge knowledge);
    void refreshRequestFailded(String msg);

}
