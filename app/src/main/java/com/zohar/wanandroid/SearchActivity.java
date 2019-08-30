package com.zohar.wanandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;


import com.zohar.wanandroid.bean.search.HotSearchData;
import com.zohar.wanandroid.presenter.HotSearchPresenter;
import com.zohar.wanandroid.utils.LogUtils;
import com.zohar.wanandroid.utils.ToastUtils;
import com.zohar.wanandroid.view.search.IHotSearchView;

/**
 * Created by zohar on 2019/8/30 9:51
 * Describe:
 */
public class SearchActivity extends AppCompatActivity implements IHotSearchView {

    private Toolbar mToolbar;
    private EditText mSearchEditText;
    private TextView mSearchButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initEventAndData();

    }

    private void initEventAndData() {
        // 请求热搜数据
        HotSearchPresenter mPresenter = new HotSearchPresenter(this);
        mPresenter.hotSearchRequest();
    }

    private void initView() {
        mToolbar = findViewById(R.id.search_tool_bar);
        mSearchEditText = findViewById(R.id.search_edit_text);
        mSearchButton = findViewById(R.id.search_start_button);
    }

    @Override
    public void hotSearchSuccess(HotSearchData hotSearchData) {
        if (hotSearchData.getErrorCode() == 0){
            LogUtils.d(hotSearchData.getData().toString());
        }else{
            ToastUtils.toastShow(this, hotSearchData.getErrorMsg());
        }
    }

    @Override
    public void hotSearchFailed(String msg) {
        ToastUtils.toastShow(this, msg);
    }
}
