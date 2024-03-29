package com.zohar.wanandroid.presenter.home;

import android.content.Context;
import android.os.Handler;

import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.bean.home.banner.Banner;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.model.home.HomeModel;
import com.zohar.wanandroid.model.home.OnBannerHttpListener;
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
    private Context context;

    public HomePresenter(Context context, IHomeView homeView) {
        this.context = context;
        mHomeView = homeView;
        mHomeModel = new HomeModel();
    }

    /**
     * 发送首页banner的网络请求
     *
     * @param url 网络地址
     */
    public void sendBannerHttpRequest(String url){
        mHomeModel.sendBannerHttp(context, url, new OnBannerHttpListener() {
            @Override
            public void httpFailed(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // 请求失败
                        mHomeView.httpFailed(msg);
                    }
                });

            }

            @Override
            public void bannerHttpSuccess(final Banner banner) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mHomeView.bannerHttpRequestSuccess(banner);
                    }
                });
            }
        });
    }

    // 发送获取文章列表http请求
    public void sendHomeHttpRequest(String url){
        // 显示加载
        mHomeView.showLoading();
        // 通过pressent调用model来发送http请求
        mHomeModel.sendHomeHttp(context, url, new OnHttpListener() {

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

    /**
     * 加载更多请求
     *
     * @param url
     */
    public void loadMoreRequest(String url){
        // 通过pressent调用model来发送http请求
        mHomeModel.sendHomeHttp(context, url, new OnHttpListener() {

            @Override
            public void httpSuccess(final Article article) {

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mHomeView.loadMoreRequestSuccess(article);
                    }
                });
            }

            @Override
            public void httpFailed(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mHomeView.loadMoreRequestFailed(msg);
                    }
                });
            }
        });
    }

    /**
     * 刷新
     */
    public void onRefresh(){
        // 通过pressent调用model来发送http请求
        mHomeModel.sendHomeHttp(context, ApiAddress.homeAritcleAddress(0), new OnHttpListener() {

            @Override
            public void httpSuccess(final Article article) {

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mHomeView.refreshRequestSuccess(article);
                    }
                });
            }

            @Override
            public void httpFailed(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mHomeView.refreshRequestFailded(msg);
                    }
                });
            }
        });
    }
}
