package com.zohar.wanandroid.presenter;

import android.os.Handler;

import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.bean.navi.NaviData;
import com.zohar.wanandroid.model.home.HomeModel;
import com.zohar.wanandroid.model.navi.NaviModel;
import com.zohar.wanandroid.model.navi.OnHttpListener;
import com.zohar.wanandroid.view.navi.INaviView;


/**
 * Created by zohar on 2019/8/28 16:18
 * Describe:
 */
public class NaviPresenter {

    private INaviView mView;
    private NaviModel mModel;

    private Handler mHandler = new Handler();

    public NaviPresenter(INaviView view) {
        mView = view;
        mModel = new NaviModel();
    }

    public void sendNaviRequest(String url) {
        mView.showLoadingView();
        mModel.sendHttpRequest(url, new OnHttpListener() {
            @Override
            public void httpSuccess(final NaviData naviData) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.hideLoadingView();
                        mView.httpSuccess(naviData);
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
}
