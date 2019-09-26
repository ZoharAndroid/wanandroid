package com.zohar.wanandroid.presenter.search;

import android.content.Context;

import com.zohar.wanandroid.base.BasePresenter;
import com.zohar.wanandroid.bean.search.HotSearchData;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.model.search.HotSearchModel;
import com.zohar.wanandroid.model.search.OnHotSearchListener;
import com.zohar.wanandroid.view.search.IHotSearchView;

/**
 * Created by zohar on 2019/8/30 11:34
 * Describe:
 */
public class HotSearchPresenter extends BasePresenter<HotSearchModel, IHotSearchView> {

    public void hotSearchRequest(){
        getModule().hotSearch(getView().getContext(), ApiAddress.HOT_SEARCH_ADDRESS, new OnHotSearchListener() {
            @Override
            public void hotSearchSuccess(HotSearchData hotSearchData) {
                getView().hotSearchSuccess(hotSearchData);
            }

            @Override
            public void hotSearchFailed(String msg) {
                getView().hotSearchFailed(msg);
            }
        });
    }

    @Override
    protected HotSearchModel createModule() {
        return new HotSearchModel();
    }
}
