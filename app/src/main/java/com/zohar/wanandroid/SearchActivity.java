package com.zohar.wanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zohar.wanandroid.bean.search.HotSearch;
import com.zohar.wanandroid.bean.search.HotSearchData;
import com.zohar.wanandroid.config.AppConstants;
import com.zohar.wanandroid.presenter.HotSearchPresenter;
import com.zohar.wanandroid.utils.LogUtils;
import com.zohar.wanandroid.utils.ScreenUtil;
import com.zohar.wanandroid.utils.ToastUtils;
import com.zohar.wanandroid.view.search.IHotSearchView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zohar on 2019/8/30 9:51
 * Describe:
 */
public class SearchActivity extends AppCompatActivity implements IHotSearchView {

    private Toolbar mToolbar;
    private EditText mSearchEditText;
    private TextView mSearchButton;
    private TagFlowLayout mTagFlowLayout;
    private List<HotSearch> mHotSearchList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initToolbar();
        initEventAndData();

    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        mTagFlowLayout = findViewById(R.id.hot_search_tag_container_tag_layout);
    }

    @Override
    public void hotSearchSuccess(HotSearchData hotSearchData) {
        if (hotSearchData.getErrorCode() == 0){
            //LogUtils.d(hotSearchData.getData().toString());
            mHotSearchList = hotSearchData.getData();
            // 设置热搜标签
            setHotSearchTag();

        }else{
            ToastUtils.toastShow(this, hotSearchData.getErrorMsg());
        }
    }

    private void setHotSearchTag() {
        mTagFlowLayout.setAdapter(new TagAdapter<HotSearch>(mHotSearchList) {
            @Override
            public View getView(FlowLayout parent, int position, HotSearch hotSearch) {
                TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flow_tv , parent, false);
                if (mHotSearchList == null){
                    return null;
                }
                String name = hotSearch.getName();
                tv.setText(name);
                tv.setTextColor(ScreenUtil.randomColor());
                tv.setPadding(ScreenUtil.dp2px(parent.getContext(), 10), ScreenUtil.dp2px(parent.getContext(),10),
                        ScreenUtil.dp2px(parent.getContext(),10), ScreenUtil.dp2px(parent.getContext(),10));
                mTagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {

//                        Intent intent = new Intent(mContext, ArticllDetailActivity.class);
//                        intent.putExtra(AppConstants.ARTICLE_FROM_HOME, details.get(position).getLink());
//                        intent.putExtra(AppConstants.ARTICLE_TITLE_FROM_HOME, details.get(position).getTitle());
//                        mContext.startActivity(intent);
                        ToastUtils.toastShow(parent.getContext(), mHotSearchList.get(position).getName());
                        return true;
                    }
                });
                return tv;
            }

        });
    }

    @Override
    public void hotSearchFailed(String msg) {
        ToastUtils.toastShow(this, msg);
    }
}
