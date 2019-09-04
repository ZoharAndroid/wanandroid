package com.zohar.wanandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zohar.wanandroid.adapter.CollectListAdapter;
import com.zohar.wanandroid.adapter.OnLoadMoreListener;
import com.zohar.wanandroid.bean.collect.CollectData;
import com.zohar.wanandroid.presenter.collect.CollectListPresenter;
import com.zohar.wanandroid.utils.ToastUtils;
import com.zohar.wanandroid.view.collect.ICollectListView;

/**
 * Created by zohar on 2019/8/31 15:49
 * Describe:
 */
public class CollectListActivity extends AppCompatActivity implements ICollectListView {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private Toolbar mToolbar;
    private TextView mTextViewTitle;
    private CollectListAdapter mAdapter;

    private int mCurrentPage = 0;
    private int mCountPage = 0 ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_list);
        initView();
        initToolbar();
        initRecyclerView();
        initEventAndData();
    }

    private void initRecyclerView() {
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        mAdapter = new CollectListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout.setRefreshing(false);

    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mTextViewTitle.setText("收藏");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.collect_list_recycler_view);
        mProgressBar = findViewById(R.id.collect_list_progress_bar);
        mToolbar = findViewById(R.id.collect_list_tool_bar);
        mTextViewTitle = findViewById(R.id.collect_list_title);
        mRefreshLayout = findViewById(R.id.collect_list_swipe_refresh);
    }

    private void initEventAndData() {
        // 请求首页
        final CollectListPresenter mPresenter = new CollectListPresenter(this, this);
        mPresenter.collectListRequest(0);

        // 刷新
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.collectListRefreshRequest();
            }
        });

        // 加载更多
        mRecyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (++mCurrentPage < mCountPage){
                    // 如果页数小于总的页数，则去加载更多
                    mPresenter.collectListLoadMoreRequest(mCurrentPage);
                }else{
                    ToastUtils.toastShow(CollectListActivity.this , "没有更多内容了！");
                }
            }
        });
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
    public void collectListSuccess(CollectData collectData) {
        if (collectData.getErrorCode() == 0){
            mCountPage = collectData.getData().getPageCount();
            mAdapter.addArticle(collectData.getData().getDatas());
        }else{
            ToastUtils.toastShow(this, collectData.getErrorMsg());
        }
    }

    @Override
    public void collectListFailed(String msg) {
        ToastUtils.toastShow(this, msg);
    }

    @Override
    public void collectListRefreshSuccess(CollectData collectData) {
        if (collectData.getErrorCode() == 0) {
            // 清空list
            mAdapter.clearArticle();
            mAdapter.addArticle(collectData.getData().getDatas());
            // 重新设置页面
            mCurrentPage = 0;
            // 停止刷新
            mRefreshLayout.setRefreshing(false);
        }else{
            ToastUtils.toastShow(this, collectData.getErrorMsg());
        }
    }

    @Override
    public void collectListLoadMoreSuccess(CollectData collectData) {
        if (collectData.getErrorCode() == 0){
            mAdapter.addArticle(collectData.getData().getDatas());
        }else{
            ToastUtils.toastShow(this, collectData.getErrorMsg());
        }
    }
}
