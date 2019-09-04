package com.zohar.wanandroid.presenter.collect;

import android.content.Context;

import com.zohar.wanandroid.bean.collect.CollectData;
import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.model.collect.CollectListModel;
import com.zohar.wanandroid.model.collect.OnCollectListListener;
import com.zohar.wanandroid.model.collect.OnCollectListener;
import com.zohar.wanandroid.view.collect.ICollectListView;

/**
 * Created by zohar on 2019/8/31 16:11
 * Describe:
 */
public class CollectListPresenter {

    private ICollectListView mView;
    private Context context;
    private CollectListModel mModel;

    public CollectListPresenter(Context context, ICollectListView view) {
        mView = view;
        this.context = context;
        mModel = new CollectListModel();
    }

    public void collectListRequest(int pageNum){
        mView.showLoadingView();
        mModel.collectList(context, ApiAddress.COLLECT_LIST_ADDRESS(pageNum), pageNum, new OnCollectListListener(){

            @Override
            public void collectSuccess(CollectData data) {
                mView.hideLoadingView();
                mView.collectListSuccess(data);
            }

            @Override
            public void collectFailed(String msg) {
                mView.hideLoadingView();
                mView.collectListFailed(msg);
            }
        });
    }

    /**
     * 刷新请求
     */
    public void collectListRefreshRequest(){
        mModel.collectList(context, ApiAddress.COLLECT_LIST_ADDRESS(0), 0, new OnCollectListListener(){

            @Override
            public void collectSuccess(CollectData data) {
                mView.collectListRefreshSuccess(data);
            }

            @Override
            public void collectFailed(String msg) {
                mView.collectListFailed(msg);
            }
        });
    }

    /**
     * 加载更多
     *
     * @param pageNum
     */
    public void collectListLoadMoreRequest(int pageNum){
        mModel.collectList(context, ApiAddress.COLLECT_LIST_ADDRESS(pageNum), pageNum, new OnCollectListListener(){

            @Override
            public void collectSuccess(CollectData data) {
                mView.collectListLoadMoreSuccess(data);
            }

            @Override
            public void collectFailed(String msg) {
                mView.collectListFailed(msg);
            }
        });
    }
}
