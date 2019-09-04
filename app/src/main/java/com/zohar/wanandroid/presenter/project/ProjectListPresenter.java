package com.zohar.wanandroid.presenter.project;

import android.content.Context;
import android.os.Handler;

import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.model.home.HomeModel;
import com.zohar.wanandroid.model.home.OnHttpListener;
import com.zohar.wanandroid.view.project.IProjectListView;

/**
 * Created by zohar on 2019/8/27 22:36
 * Describe:
 */
public class ProjectListPresenter {

    private IProjectListView mView;
    private HomeModel mModel;
    private Context context;

    private Handler mHandler = new Handler();

    public ProjectListPresenter(Context context, IProjectListView view) {
        this.context = context;
        mView = view;
        mModel = new HomeModel();
    }

    // 发送获取文章列表http请求
    public void sendHomeHttpRequest(String url) {
        // 显示加载
        mView.showLoadingView();
        // 通过pressent调用model来发送http请求
        mModel.sendHomeHttp(context, url, new OnHttpListener() {

            @Override
            public void httpSuccess(final Article article) {

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.hideLoadingView();
                        mView.httpSuccess(article);
                    }
                });
            }

            @Override
            public void httpFailed(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.hideLoadingView();
                        mView.httpFailed(msg);
                    }
                });
            }
        });
    }

    /**
     * 刷新
     */
    public void onRefresh(int id) {
        String url = ApiAddress.PROJECT_LIST_ADDRESS(0, id);
        // 通过pressent调用model来发送http请求
        mModel.sendHomeHttp(context ,url, new OnHttpListener() {

            @Override
            public void httpSuccess(final Article article) {

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.refreshRequestSuccess(article);
                    }
                });
            }

            @Override
            public void httpFailed(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.refreshRequestFailded(msg);
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
    public void loadMoreRequest(String url) {
        // 通过pressent调用model来发送http请求
        mModel.sendHomeHttp(context, url, new OnHttpListener() {

            @Override
            public void httpSuccess(final Article article) {

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.loadMoreRequestSuccess(article);
                    }
                });
            }

            @Override
            public void httpFailed(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.loadMoreRequestFailed(msg);
                    }
                });
            }
        });
    }


}
