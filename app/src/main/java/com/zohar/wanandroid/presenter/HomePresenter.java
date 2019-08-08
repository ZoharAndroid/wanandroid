package com.zohar.wanandroid.presenter;

import android.os.Handler;

import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.model.home.HomeModel;
import com.zohar.wanandroid.model.home.OnHttpListener;
import com.zohar.wanandroid.view.home.IHomeView;

/**
 * Created by zohar on 2019/8/8 16:39
 * Describe:
 */
public class HomePresenter {

    private IHomeView mHomeView;
    private HomeModel mHomeModel;

    private Handler mHandler = new Handler();

    public HomePresenter(IHomeView homeView) {
        mHomeView = homeView;
        mHomeModel = new HomeModel();
    }

    // 发送http请求
    public void sendHomeHttpRequest(String url){
        // 显示加载
        mHomeView.showLoading();
        // 通过pressent调用model来发送http请求
        mHomeModel.sendHomeHttp(url, new OnHttpListener() {

            @Override
            public void httpSuccess(final Article article) {

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mHomeView.hideLoading();
                        mHomeView.httpSuccess(article);
                    }
                });
            }

            @Override
            public void httpFailed(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mHomeView.hideLoading();
                        mHomeView.httpFailed(msg);
                    }
                });
            }
        });
    }
}
