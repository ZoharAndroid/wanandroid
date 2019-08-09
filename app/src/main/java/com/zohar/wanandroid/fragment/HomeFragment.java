package com.zohar.wanandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zohar.wanandroid.R;
import com.zohar.wanandroid.adapter.HomeArticleAdapter;
import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.bean.home.Data;
import com.zohar.wanandroid.bean.home.Datas;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.presenter.HomePresenter;
import com.zohar.wanandroid.utils.ToastUtils;
import com.zohar.wanandroid.view.home.IHomeView;

import java.util.List;

/**
 * Created by zohar on 2019/8/8 9:20
 * Describe:
 */
public class HomeFragment extends Fragment implements IHomeView {

    private static final String TAG = "HomeFragment";

    //private SmartRefreshLayout mHomeRefreshLayout;
    private RecyclerView mHomeRecyclerView;
    private ProgressBar mProgressBar;
    private HomePresenter mPresenter;

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //mHomeRefreshLayout = view.findViewById(R.id.refresh_layout_home);
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
            // 请求第0页数据额
            mPresenter.sendHomeHttpRequest(ApiAddress.homeAritcleAddress(0));
        }
    }

    //初始化recyelerview的相关设置
    private void initRecyclerView() {
        mHomeRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mHomeRecyclerView.setLayoutManager(linearLayoutManager);
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
        if (data.getErrorCode() == 0){
            // 没有错误
            Data homeData = data.getData();
            // 获取下来的文章数目
            List<Datas> articles = homeData.getDatas();
            // 添加recyclerview的apter
            HomeArticleAdapter adapter = new HomeArticleAdapter(getContext(), articles);
            mHomeRecyclerView.setAdapter(adapter);

        }else{
            ToastUtils.toastShow(getContext(), data.getErrorMsg());
        }
    }

    @Override
    public void httpFailed(String msg) {
        ToastUtils.toastShow(getContext(), msg);
    }
}
