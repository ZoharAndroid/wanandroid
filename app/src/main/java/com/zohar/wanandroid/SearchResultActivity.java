package com.zohar.wanandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.config.AppConstants;
import com.zohar.wanandroid.presenter.SearchResultPresenter;
import com.zohar.wanandroid.utils.LogUtils;
import com.zohar.wanandroid.utils.ToastUtils;
import com.zohar.wanandroid.view.search.ISearchResultView;

/**
 * Created by zohar on 2019/8/30 13:41
 * Describe:
 */
public class SearchResultActivity extends AppCompatActivity implements ISearchResultView {

    private String mSearchContent;
    private Toolbar mToolbar;
    private TextView mTitle;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        mSearchContent = getIntent().getStringExtra(AppConstants.SEARCH_CONTENT_INTNT);
        initView();
        initToolbar();
        initEventAndData();
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

    private void initEventAndData() {
        // 发送请求
        if (!mSearchContent.isEmpty()){
            SearchResultPresenter mPresenter = new SearchResultPresenter(this);
            mPresenter.searchRequest(mSearchContent, 0);
        }
    }

    private void initView() {
        mToolbar = findViewById(R.id.search_result_tool_bar);
        mTitle = findViewById(R.id.search_result_title);
        mRecyclerView = findViewById(R.id.search_result_recycler_view);
        mProgressBar = findViewById(R.id.search_result_progress_bar);
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
    public void searchSuccess(Article articlesData) {
        if (articlesData.getErrorCode() == 0){
            LogUtils.d("获取数据的大小：" + articlesData.getData().getDatas().size());
        }else{
            ToastUtils.toastShow(this, articlesData.getErrorMsg());
        }
    }

    @Override
    public void searchFailed(String msg) {
        ToastUtils.toastShow(this, msg);
    }
}
