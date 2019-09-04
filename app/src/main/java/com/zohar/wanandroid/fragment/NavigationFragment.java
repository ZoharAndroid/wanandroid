package com.zohar.wanandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.zohar.wanandroid.R;
import com.zohar.wanandroid.adapter.NaviAdapter;
import com.zohar.wanandroid.bean.navi.NaviArticles;
import com.zohar.wanandroid.bean.navi.NaviData;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.presenter.navi.NaviPresenter;
import com.zohar.wanandroid.utils.ToastUtils;
import com.zohar.wanandroid.view.navi.INaviView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zohar on 2019/8/8 9:23
 * Describe:
 */
public class NavigationFragment extends Fragment implements INaviView {

    private RecyclerView mRecyclerView;
    private View mView;
    private ProgressBar mProgressBar;

    private NaviPresenter mPresenter;

    private List<NaviArticles> mNaviArticles = new ArrayList<>();

    public static NavigationFragment newInstance(){
        return new NavigationFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_navigation, container, false);
        initView();
        return mView;
    }

    private void initView() {
        mRecyclerView = mView.findViewById(R.id.navi_recycler_view);
        mProgressBar = mView.findViewById(R.id.navi_progress_bar);
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEventAndData();
    }

    private void initEventAndData() {
        // 请求presenter 去发送请求
        mPresenter = new NaviPresenter(getContext(), this);
        mPresenter.sendNaviRequest(ApiAddress.NAVI_ADDRESS);
    }

    @Override
    public void showLoadingView() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingView() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void httpSuccess(NaviData naviData) {
        // 从服务端获取数据
        if (naviData.getErrorCode() == 0){
            // 调用adapter
            NaviAdapter mAdapter = new NaviAdapter(getContext(), naviData.getData());
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void httpFailed(String msg) {
        ToastUtils.toastShow(getContext(), msg);
    }
}
