package com.zohar.wanandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.zohar.wanandroid.bean.knowledge.SubKnowledge;
import com.zohar.wanandroid.config.AppConstants;
import com.zohar.wanandroid.fragment.KnowledgeHierarchyListFragment;
import com.zohar.wanandroid.utils.LogUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zohar on 2019/8/27 16:34
 * Describe:
 */
public class KnowledgeDetailActivity extends AppCompatActivity {

    private ArrayList<SubKnowledge> mKnowledgeList;
    private String title;

    // toolbar
    private Toolbar mToolbar;
    // 标题
    private TextView mTextViewTitle;
    private SlidingTabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_detail);
        title = getIntent().getStringExtra(AppConstants.KNOWLEDGE_TITLE);
        mKnowledgeList = getIntent().getParcelableArrayListExtra(AppConstants.KNOWLEDGE_CHILDREN_DATA);

        initView();
        initToolbar();
        initEventAndData();
    }

    private void initEventAndData() {
        initViewPagerAndTabLayout();
    }

    private void initViewPagerAndTabLayout() {
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments == null ? 0 : mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mKnowledgeList.get(position).getName();
            }
        });
        mTabLayout.setViewPager(mViewPager);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        // 显示标题
        mTextViewTitle.setText(title);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        startNormalKnowledgeListPager();
    }

    private void initView() {
        mToolbar = findViewById(R.id.knowledge_detail_tool_bar);
        mTextViewTitle = findViewById(R.id.knowledge_toolbar_title);
        mTabLayout = findViewById(R.id.knowledge_tab_layout);
        mViewPager = findViewById(R.id.knowledge_view_pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    /**
     * 装载多个列表的知识体系页面（knowledge进入）
     */
    private void startNormalKnowledgeListPager() {
        if (mKnowledgeList == null) {
            return;
        }

        for (SubKnowledge data : mKnowledgeList) {
            mFragments.add(KnowledgeHierarchyListFragment.newInstance(data.getId(), KnowledgeHierarchyListFragment.TYPE_KNOWLEDGE));
        }
    }
}
