package com.zohar.wanandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zohar.wanandroid.R;
import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.bean.home.Data;
import com.zohar.wanandroid.bean.home.Datas;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.http.GetAritcleResponse;
import com.zohar.wanandroid.presenter.HomePresenter;
import com.zohar.wanandroid.utils.ToastUtils;
import com.zohar.wanandroid.view.home.IHomeView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zohar on 2019/8/8 9:20
 * Describe:
 */
public class HomeFragment extends Fragment implements IHomeView {

    private static final String TAG = "HomeFragment";

    private SmartRefreshLayout mHomeRefreshLayout;
    private RecyclerView mHomeRecyclerView;
    private ProgressBar mProgressBar;

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    private Data homeData;
    private List<Datas> articles; // 获取下来的文章数目

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mHomeRefreshLayout = view.findViewById(R.id.refresh_layout_home);
        mHomeRecyclerView = view.findViewById(R.id.home_recycler_view);
        mProgressBar = view.findViewById(R.id.home_progress_bar);
        // 初始化recyclerview
        initRecyclerView();
        // 调用presenter来请求网络
        HomePresenter presenter = new HomePresenter(this);
        // 请求第0页数据额
        presenter.sendHomeHttpRequest(ApiAddress.homeAritcleAddress(0));
        return view;
    }

    //初始化recyelerview的相关设置
    private void initRecyclerView() {
        mHomeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
            homeData = data.getData();
            articles = homeData.getDatas();
        }else{
            ToastUtils.toastShow(getContext(), data.getErrorMsg());
        }
    }

    @Override
    public void httpFailed(String msg) {
        ToastUtils.toastShow(getContext(), msg);
    }
}
