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
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.stx.xhb.xbanner.XBanner;
import com.zohar.wanandroid.R;
import com.zohar.wanandroid.adapter.HomeArticleAdapter;
import com.zohar.wanandroid.adapter.OnLoadMoreListener;
import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.bean.home.Data;
import com.zohar.wanandroid.bean.home.banner.Banner;
import com.zohar.wanandroid.bean.home.banner.BannerData;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.presenter.HomePresenter;
import com.zohar.wanandroid.utils.LogUtils;
import com.zohar.wanandroid.utils.ScreenUtil;
import com.zohar.wanandroid.utils.ToastUtils;
import com.zohar.wanandroid.view.home.IHomeView;

import java.util.List;

/**
 * Created by zohar on 2019/8/8 9:20
 * Describe:
 */
public class HomeFragment extends Fragment implements IHomeView {

    private static final String TAG = "HomeFragment";

    private SwipeRefreshLayout mHomeRefreshLayout;
    private RecyclerView mHomeRecyclerView;
    private ProgressBar mProgressBar;

    private HomePresenter mPresenter;

    // 当前需要加载的页面
    private int mPageIndex = 0;
    private HomeArticleAdapter mArticleAdapter;

    // 首页banner数据list
    private List<BannerData> mBannerDatas;


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mHomeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout_home);
        mHomeRecyclerView = view.findViewById(R.id.home_recycler_view);
        mProgressBar = view.findViewById(R.id.home_progress_bar);

        // 初始化recyclerview
        initRecyclerView();

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (mPresenter == null) {
            // 调用presenter来请求网络
            mPresenter = new HomePresenter(this);
            // 请求文章列表数据
            mPresenter.sendHomeHttpRequest(ApiAddress.homeAritcleAddress(mPageIndex));
            // 请求首页banner数据
            mPresenter.sendBannerHttpRequest(ApiAddress.HOME_BANNER);
        }

        // 添加更多数据
        mHomeRecyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadMoreRequest(ApiAddress.homeAritcleAddress(++mPageIndex));
            }
        });

        // 设置下拉刷新
        mHomeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onRefresh();
            }
        });
    }

    //初始化recyelerview的相关设置
    private void initRecyclerView() {
        //SwipeRefreshLayout设置
        // 初始状态不显示刷新图标
        mHomeRefreshLayout.setRefreshing(false);
        // 设置下拉刷新的图标颜色
        mHomeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_dark, android.R.color.holo_blue_dark, android.R.color.holo_orange_dark);

        mHomeRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mHomeRecyclerView.setLayoutManager(linearLayoutManager);

        mArticleAdapter = new HomeArticleAdapter(getContext());
    }


    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void httpSuccess(Article data) {
        if (data.getErrorCode() == 0) {
            // 没有错误
            Data homeData = data.getData();
            // 添加recyclerview的apter
            mArticleAdapter.addArticle(homeData.getDatas());
            mHomeRecyclerView.setAdapter(mArticleAdapter);

        } else {
            ToastUtils.toastShow(getContext(), data.getErrorMsg());
        }
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
            mArticleAdapter.addArticle(homeData.getDatas());
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
        // 清空list
        mArticleAdapter.clearArticle();
        mArticleAdapter.addArticle(data.getData().getDatas());
        // 重新设置页面
        mPageIndex = 0;
        // 停止刷新
        mHomeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void refreshRequestFailded(String msg) {
        ToastUtils.toastShow(getContext(), msg);
    }

    @Override
    public void bannerHttpRequestSuccess(Banner banner) {
        if (banner.getErrorCode() == 0) {
            // 获取广告图片内容
            mBannerDatas = banner.getData();
           mArticleAdapter.updateHeaderView(mBannerDatas);
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        mArticleAdapter.getBannerView().stopAutoPlay();
    }
}
