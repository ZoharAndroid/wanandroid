package com.zohar.wanandroid.presenter;

import android.view.View;

import com.zohar.wanandroid.model.collect.CollectModel;
import com.zohar.wanandroid.view.collect.ICancelCollectView;
import com.zohar.wanandroid.view.collect.ICollectView;

/**
 * Created by zohar on 2019/9/1 11:53
 * Describe:
 */
public class CancelCollectPresenter {

    private ICancelCollectView mView;
    private CollectModel mModel;
    private View clickView;


    public CancelCollectPresenter(ICancelCollectView cancelCollectView, View view) {
        mView = cancelCollectView;
        clickView = view;
        mModel = new CollectModel();
    }
}
