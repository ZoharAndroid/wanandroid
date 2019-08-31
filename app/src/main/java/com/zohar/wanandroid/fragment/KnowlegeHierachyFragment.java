package com.zohar.wanandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.zohar.wanandroid.R;
import com.zohar.wanandroid.adapter.KnowledgeAdapter;
import com.zohar.wanandroid.bean.knowledge.Knowledge;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.presenter.KnowledgePresenter;
import com.zohar.wanandroid.utils.LogUtils;
import com.zohar.wanandroid.utils.ToastUtils;
import com.zohar.wanandroid.view.knowledge.IKnowledgeView;

/**
 * Created by zohar on 2019/8/8 9:22
 * Describe:
 *  知识体系Fragment
 */
public class KnowlegeHierachyFragment extends Fragment implements IKnowledgeView {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private View mView;

    private KnowledgePresenter mPresenter;
    private KnowledgeAdapter mKnowledgeAdapter;

    public static KnowlegeHierachyFragment newInstance(){
        return new KnowlegeHierachyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_knowlege_hierachy, container, false);
        initView();
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (mPresenter == null){
            mPresenter = new KnowledgePresenter(getContext(),this);
            // 调用presenter去发送请求
            mPresenter.sendKnowledgeRequest(ApiAddress.KNOWLEDGE_TREE);
        }

        // 下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onRefresh();
            }
        });
    }

    /**
     * 初始化View
     */
    private void initView() {
        mSwipeRefreshLayout = mView.findViewById(R.id.swipe_refresh_knowledge);
        mRecyclerView = mView.findViewById(R.id.recycler_view_knowledge);
        mProgressBar = mView.findViewById(R.id.progress_bar_knowledge);

        initRecyclerView();
    }


    private void initRecyclerView() {
        mSwipeRefreshLayout.setRefreshing(false);
        // 设置下拉刷新的图标颜色
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_dark, android.R.color.holo_blue_dark, android.R.color.holo_orange_dark);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        //设置adapter
        mKnowledgeAdapter = new KnowledgeAdapter(getContext());
        mRecyclerView.setAdapter(mKnowledgeAdapter);

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
    public void httpSuccess(Knowledge knowledge) {
        // 把数据传到adapter
        mKnowledgeAdapter.addData(knowledge.getData());
       // LogUtils.d(knowledge.getData().toString());
    }

    @Override
    public void httpFailed(String msg) {
        ToastUtils.toastShow(getContext(), msg);
    }

    @Override
    public void refreshHttpSuccess(Knowledge knowledge) {
        // 清空list
        mKnowledgeAdapter.clearData();
        // 重新加载数据
        mKnowledgeAdapter.addData(knowledge.getData());
        // 停止刷新
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void refreshHttpFailed(String msg) {
        ToastUtils.toastShow(getContext(), msg);
    }
}
