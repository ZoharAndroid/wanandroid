package com.zohar.wanandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zohar.wanandroid.adapter.CollectListAdapter;
import com.zohar.wanandroid.adapter.HomeArticleAdapter;
import com.zohar.wanandroid.adapter.KnowledgeListAdapter;
import com.zohar.wanandroid.bean.collect.CollectData;
import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.presenter.CollectListPresenter;
import com.zohar.wanandroid.utils.ToastUtils;
import com.zohar.wanandroid.view.collect.ICollectListView;

/**
 * Created by zohar on 2019/8/31 15:49
 * Describe:
 */
public class CollectListActivity extends AppCompatActivity implements ICollectListView {


    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private Toolbar mToolbar;
    private TextView mTextViewTitle;
    private CollectListAdapter mAdapter;
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
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        mAdapter = new CollectListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
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
    }

    private void initEventAndData() {
        CollectListPresenter mPresenter = new CollectListPresenter(this, this);
        mPresenter.collectListRequest(0);
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
            mAdapter.addArticle(collectData.getData().getDatas());
        }else{
            ToastUtils.toastShow(this, collectData.getErrorMsg());
        }
    }

    @Override
    public void collectListFailed(String msg) {
        ToastUtils.toastShow(this, msg);
    }
}
