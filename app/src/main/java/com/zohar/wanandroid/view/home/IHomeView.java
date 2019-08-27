package com.zohar.wanandroid.view.home;

import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.bean.home.banner.Banner;

/**
 * Created by zohar on 2019/8/8 16:26
 * Describe:
 *  View中相关的接口
 */
public interface IHomeView {

    void showLoading();
    void hideLoading();

    void httpSuccess(Article data);
    void httpFailed(String msg);

    void loadMoreRequestSuccess(Article data);
    void loadMoreRequestFailed(String msg);

    void refreshRequestSuccess(Article data);
    void refreshRequestFailded(String msg);

    void bannerHttpRequestSuccess(Banner banner);
}
