package com.zohar.wanandroid;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zohar.wanandroid.adapter.KnowledgeListAdapter;
import com.zohar.wanandroid.adapter.OnLoadMoreListener;
import com.zohar.wanandroid.base.BaseActivity;
import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.bean.home.Data;
import com.zohar.wanandroid.config.AppConstants;
import com.zohar.wanandroid.presenter.search.SearchResultPresenter;
import com.zohar.wanandroid.utils.ToastUtils;
import com.zohar.wanandroid.view.search.ISearchResultView;

/**
 * Created by zohar on 2019/8/30 13:41
 * Describe:
 */
public class SearchResultActivity extends BaseActivity<SearchResultPresenter> implements ISearchResultView {

    private String mSearchContent;
    private Toolbar mToolbar;
    private TextView mTitle;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private KnowledgeListAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private SearchResultPresenter mPresenter;

    private int mCurrentPage = 0;
    private int mPageCount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected SearchResultPresenter createPresenter() {
        mPresenter = new SearchResultPresenter();
        return mPresenter;
    }

    @Override
    protected int setLayoutResId() {
        return R.layout.activity_search_result;
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new KnowledgeListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        // 设置标题
        mTitle.setText(mSearchContent);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initEventAndData() {
        // 发送请求
        if (mPresenter != null) {
            mPresenter.searchRequest(mSearchContent, 0);
        }

        // 下拉刷新
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.searchRefresh(mSearchContent);
            }
        });

        //加载更多
        mRecyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                // 获取总的页码
                if (++mCurrentPage < mPageCount) {
                    // 当当前页面小于总的页数的时候是可以去请求服务器
                    mAdapter.setFooterViewVisable();
                    mPresenter.searchLoadMore(mSearchContent, mCurrentPage);
                } else {
                    // 如果当前页码等于或者大于页码的时候，隐藏加载更多界面
                    mAdapter.deleteLastItem();
                    ToastUtils.toastShow(SearchResultActivity.this, "已经没有更多内容了！");
                }
            }
        });
    }

    @Override
    protected void initView() {
        mSearchContent = getIntent().getStringExtra(AppConstants.SEARCH_CONTENT_INTNT);

        mToolbar = findViewById(R.id.search_result_tool_bar);
        mTitle = findViewById(R.id.search_result_title);
        mRecyclerView = findViewById(R.id.search_result_recycler_view);
        mProgressBar = findViewById(R.id.search_result_progress_bar);
        mRefreshLayout = findViewById(R.id.search_result_swipe_refresh);

        initToolbar();
        initRecyclerView();
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
    public Context getContext() {
        return this;
    }

    @Override
    public void searchSuccess(Article articlesData) {
        if (articlesData.getErrorCode() == 0) {
            mPageCount = articlesData.getData().getPageCount();
            mAdapter.addArticle(articlesData.getData().getDatas());
        } else {
            ToastUtils.toastShow(this, articlesData.getErrorMsg());
        }
    }

    @Override
    public void searchFailed(String msg) {
        ToastUtils.toastShow(this, msg);
    }

    @Override
    public void searchRefreshSuccess(Article articlesData) {
        if (articlesData.getErrorCode() == 0) {
            // 清空
            mAdapter.clearArticle();
            // 添加
            mAdapter.addArticle(articlesData.getData().getDatas());
            mCurrentPage = 0;
            mRefreshLayout.setRefreshing(false);
        } else {
            ToastUtils.toastShow(this, articlesData.getErrorMsg());
        }
    }

    @Override
    public void searchRefreshFailed(String msg) {
        ToastUtils.toastShow(this, msg);
    }

    @Override
    public void searchLoadMoreSuccess(Article articlesData) {
        if (articlesData.getErrorCode() == 0) {
            // 没有错误
            Data homeData = articlesData.getData();
            // 新获取下来的文章数目
            // 添加到新list中
            mAdapter.addArticle(homeData.getDatas());
        } else {
            ToastUtils.toastShow(this, articlesData.getErrorMsg());
        }
    }

    @Override
    public void searchLoadMoreFailed(String msg) {
        ToastUtils.toastShow(this, msg);
    }
}
