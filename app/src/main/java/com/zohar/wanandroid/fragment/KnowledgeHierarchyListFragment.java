package com.zohar.wanandroid.fragment;

import android.os.Bundle;
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
import com.zohar.wanandroid.adapter.HomeArticleAdapter;
import com.zohar.wanandroid.adapter.KnowledgeListAdapter;
import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.config.AppConstants;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.presenter.KnowledgeListPresenter;
import com.zohar.wanandroid.utils.LogUtils;
import com.zohar.wanandroid.utils.ToastUtils;
import com.zohar.wanandroid.view.knowledge.list.IKnowledgeListView;

/**
 * Created by zohar on 2019/8/27 21:42
 * Describe:
 */
public class KnowledgeHierarchyListFragment extends Fragment implements IKnowledgeListView {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    private KnowledgeListPresenter mPresenter;

    private int id;
    // 当前页数
    private int mCurrentPage;
    private KnowledgeListAdapter mAdapter;

    public static Fragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(AppConstants.ARG_PARAM1, id);
        Fragment fragment = new KnowledgeHierarchyListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_knowledge_list, container, false);
        mRefreshLayout = view.findViewById(R.id.knowledge_list_swipe_refresh_layout);
        mRecyclerView = view.findViewById(R.id.knowledge_list_recycler_view);
        mProgressBar = view.findViewById(R.id.knowledge_list_progress_bar);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        // 添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        // 设置manager
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        // 新建Adapter
        mAdapter = new KnowledgeListAdapter(getContext());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEventAndData();
    }

    private void initEventAndData() {
        Bundle args = getArguments();
        id = args.getInt(AppConstants.ARG_PARAM1, 0);
        LogUtils.d(" 当前的ID：" + id);

        if (id == 0) {
            return;
        }

        mCurrentPage = 0;


        mPresenter = new KnowledgeListPresenter(this);
        mPresenter.sendHomeHttpRequest(ApiAddress.KNOWLEDGE_TREE_ARTICLE(mCurrentPage, id));

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
    public void httpSuccess(Article data) {
        mAdapter.addArticle(data.getData().getDatas());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void httpFailed(String msg) {
        ToastUtils.toastShow(getContext(), msg);
    }

    @Override
    public void loadMoreRequestSuccess(Article data) {

    }

    @Override
    public void loadMoreRequestFailed(String msg) {
        ToastUtils.toastShow(getContext(), msg);
    }

    @Override
    public void refreshRequestSuccess(Article data) {

    }

    @Override
    public void refreshRequestFailded(String msg) {
        ToastUtils.toastShow(getContext(), msg);
    }
}
