package com.zohar.wanandroid.presenter.search;

import android.content.Context;

import com.zohar.wanandroid.bean.search.HotSearchData;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.model.search.HotSearchModel;
import com.zohar.wanandroid.model.search.OnHotSearchListener;
import com.zohar.wanandroid.view.search.IHotSearchView;

/**
 * Created by zohar on 2019/8/30 11:34
 * Describe:
 */
public class HotSearchPresenter {

    private IHotSearchView mView;
    private HotSearchModel mModel;
    private Context context;

    public HotSearchPresenter(Context context, IHotSearchView view) {
        this.context = context;
        mView = view;
        mModel = new HotSearchModel();
    }

    public void hotSearchRequest(){
        mModel.hotSearch(context, ApiAddress.HOT_SEARCH_ADDRESS, new OnHotSearchListener() {
            @Override
            public void hotSearchSuccess(HotSearchData hotSearchData) {
                mView.hotSearchSuccess(hotSearchData);
            }

            @Override
            public void hotSearchFailed(String msg) {
                mView.hotSearchFailed(msg);
            }
        });
    }
}
