package com.zohar.wanandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.flyco.tablayout.SlidingTabLayout;
import com.zohar.wanandroid.R;
import com.zohar.wanandroid.bean.knowledge.Knowledge;
import com.zohar.wanandroid.bean.knowledge.SubKnowledge;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.presenter.project.ProjectPresenter;
import com.zohar.wanandroid.utils.ToastUtils;
import com.zohar.wanandroid.view.project.IProjectView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zohar on 2019/8/8 9:22
 * Describe:
 */
public class ProjectFragment extends Fragment implements IProjectView {

    public static ProjectFragment newInstance(){
        return new ProjectFragment();
    }
    
    private SlidingTabLayout mTabLayout;
    private ViewPager mViewPager;
    private View mView;
    private ProgressBar mProgressBar;

    private List<Fragment> mFragments = new ArrayList<>();
    private ProjectPresenter mPresenter;
    private List<SubKnowledge> mProjectList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_project, container, false);

        initView();

        return mView;
    }

    private void initView() {
        mTabLayout = mView.findViewById(R.id.project_article_tab_layout);
        mViewPager = mView.findViewById(R.id.project_article_view_pager);
        mProgressBar = mView.findViewById(R.id.project_article_progress_bar);
    }


    private void initEventAndData() {
        // 通过presenter来请求公众号列表数据
        mPresenter = new ProjectPresenter(getContext(), this);
        mPresenter.sendProjectRequest(ApiAddress.PROJECT_CATEGORIES_ADDRESS);
    }

    /**
     * 初始化ViewPager和Tablayout
     */
    private void initViewPagerAndTabLayout() {
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()){

            @Override
            public int getCount() {
                return mFragments == null ? 0 : mFragments.size();
            }

            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return  mProjectList.get(position).getName();
            }
        });
        mTabLayout.setViewPager(mViewPager);
    }



    /**
     * 装载多个列表的公众号文章页面
     */
    private void startProjectListPager() {
        if (mProjectList == null) {
            return;
        }

        for (SubKnowledge data : mProjectList) {
            mFragments.add(ProjectListFragment.newInstance(data.getId()));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initEventAndData();
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
    public void httpSuccess(Knowledge knowledge) {
        if (knowledge.getErrorCode() == 0){
            // 获取数据，里面主要用到的是id和name，到时候是根据这个id来获取
            // 该id公众号下的文章的
            mProjectList.addAll(knowledge.getData());
            startProjectListPager();
            initViewPagerAndTabLayout();
        }
    }

    @Override
    public void httpFailed(String msg) {
        ToastUtils.toastShow(getContext(), msg);
    }

    @Override
    public void loadMoreRequestSuccess(Knowledge knowledge) {

    }

    @Override
    public void loadMoreRequestFailed(String msg) {
        ToastUtils.toastShow(getContext(), msg);
    }

    @Override
    public void refreshRequestSuccess(Knowledge knowledge) {

    }

    @Override
    public void refreshRequestFailded(String msg) {
        ToastUtils.toastShow(getContext(), msg);
    }
}
