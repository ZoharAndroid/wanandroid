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
import com.zohar.wanandroid.adapter.KnowledgeListAdapter;
import com.zohar.wanandroid.adapter.OnLoadMoreListener;
import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.bean.home.Data;
import com.zohar.wanandroid.config.AppConstants;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.presenter.ProjectListPresenter;
import com.zohar.wanandroid.presenter.WechatListPresenter;
import com.zohar.wanandroid.utils.ToastUtils;
import com.zohar.wanandroid.view.project.IProjectListView;
import com.zohar.wanandroid.view.wechat.IWechatListView;

/**
 * Created by zohar on 2019/8/27 21:42
 * Describe:
 */
public class WechatListFragment extends Fragment implements IWechatListView {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    private WechatListPresenter mPresenter;

    private int id;
    // 当前页数
    private int mCurrentPage;
    // 总的页数
    private int mPageCount;
    private KnowledgeListAdapter mAdapter;

    public static Fragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(AppConstants.ARG_PARAM1, id);
        Fragment fragment = new WechatListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wechat_list, container, false);
        mRefreshLayout = view.findViewById(R.id.wechat_list_swipe_refresh_layout);
        mRecyclerView = view.findViewById(R.id.wechat_list_recycler_view);
        mProgressBar = view.findViewById(R.id.wechat_list_progress_bar);
        mRefreshLayout.setRefreshing(false);
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

        if (id == 0) {
            return;
        }

        mCurrentPage = 0;

        mPresenter = new WechatListPresenter(this);

        mPresenter.sendHomeHttpRequest(ApiAddress.WECHAT_ARTICLE_ADDRESS(id, mCurrentPage));

        // 下拉刷新操作
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onRefresh(id);
            }
        });

        // 加载更多
        mRecyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                // 获取总的页码
                if (++mCurrentPage < mPageCount) {
                    // 当当前页面小于总的页数的时候是可以去请求服务器
                    mAdapter.setFooterViewVisable();
                    mPresenter.loadMoreRequest(ApiAddress.WECHAT_ARTICLE_ADDRESS(id, mCurrentPage));
                } else {
                    // 如果当前页码等于或者大于页码的时候，隐藏加载更多界面
                    mAdapter.deleteLastItem();
                    ToastUtils.toastShow(getContext(), "已经没有更多内容了！");
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
    public void httpSuccess(Article data) {
        mAdapter.addArticle(data.getData().getDatas());
        mRecyclerView.setAdapter(mAdapter);
        mPageCount = data.getData().getPageCount();
    }

    @Override
    public void httpFailed(String msg) {
        ToastUtils.toastShow(getContext(), msg);
    }

    @Override
    public void loadMoreRequestSuccess(Article data) {
        if (data.getErrorCode() == 0) {
            // 没有错误
            Data homeData = data.getData();
            // 新获取下来的文章数目
            // 添加到新list中
            mAdapter.addArticle(homeData.getDatas());
        } else {
            ToastUtils.toastShow(getContext(), data.getErrorMsg());
        }
    }

    @Override
    public void loadMoreRequestFailed(String msg) {
        ToastUtils.toastShow(getContext(), msg);
    }

    @Override
    public void refreshRequestSuccess(Article data) {
        // 清空
        mAdapter.clearArticle();
        // 添加
        mAdapter.addArticle(data.getData().getDatas());
        mCurrentPage = 0;
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void refreshRequestFailded(String msg) {
        ToastUtils.toastShow(getContext(), msg);
    }
}
