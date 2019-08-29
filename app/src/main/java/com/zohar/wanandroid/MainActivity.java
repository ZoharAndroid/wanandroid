package com.zohar.wanandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zohar.wanandroid.config.AppConstants;
import com.zohar.wanandroid.fragment.HomeFragment;
import com.zohar.wanandroid.fragment.KnowlegeHierachyFragment;
import com.zohar.wanandroid.fragment.NavigationFragment;
import com.zohar.wanandroid.fragment.ProjectFragment;
import com.zohar.wanandroid.fragment.WechatFragment;
import com.zohar.wanandroid.http.cookies.PersistentCookieStore;
import com.zohar.wanandroid.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Cookie;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Toolbar mToolbar;
    private DrawerLayout mMainDrawerLayout;
    private NavigationView mNavigationView;
    private FloatingActionButton mFloatingActionButton;
    private FrameLayout mContentFrameLayout;
    private BottomNavigationView mBottomNavigationView;
    private TextView mToolbarTitle;
    private TextView mUsernameTextView;
    private CircleImageView mHeaderImageView;

    private List<Fragment> mFragments;

    private int mCurrentIndex = 0; // 当前的table位置
    private HomeFragment mHomeFragment;
    private KnowlegeHierachyFragment mKnowlegeHierachyFragment;
    private WechatFragment mWechatFragment;
    private NavigationFragment mNavigationFragment;
    private ProjectFragment mProjectFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragments = new ArrayList<>();
        // 初始化View
        initView();

        setSupportActionBar(mToolbar);
        // 设置侧滑菜单
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.icon_menu);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mToolbarTitle.setText(getResources().getString(R.string.home)); // 设置标题

        //mNavigationView.setCheckedItem(R.id.collections);
        // 设置侧滑菜单中的item选择
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // 关闭侧滑菜单
                mMainDrawerLayout.closeDrawers();
                // Todo: 对选择侧滑菜单对应的item进行处理
//                switch (menuItem.getItemId()){
////                    case R.id.username_text_view:
////                        ToastUtils.toastShow(MainActivity.this, "dianjile");
//                }
                return true;
            }
        });


        // 底部导航栏BottomNavigationView
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bottom_nav_home:
                        mToolbarTitle.setText(getResources().getString(R.string.home)); // 设置标题
                        switchFragment(0);
                        break;
                    case R.id.bottom_nav_knowledge:
                        mToolbarTitle.setText(getResources().getString(R.string.knowledge_hierarchy)); // 设置标题
                        switchFragment(1);
                        break;
                    case R.id.bottom_nav_public_no:
                        mToolbarTitle.setText(getResources().getString(R.string.public_number)); // 设置标题
                        switchFragment(2);
                        break;
                    case R.id.bottom_nav_navigation:
                        mToolbarTitle.setText(getResources().getString(R.string.navigation)); // 设置标题
                        switchFragment(3);
                        break;
                    case R.id.bottom_nav_project:
                        mToolbarTitle.setText(getResources().getString(R.string.projects)); // 设置标题
                        switchFragment(4);
                        break;
                }
                return true;
            }
        });

        // 滑动点按钮
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击了", Toast.LENGTH_SHORT).show();
            }
        });

        // 初始化Fragment
        initFragment();

        // 获取sp保存的用户信息
        String username = null;
        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        List<Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.name().equals(AppConstants.LOGIN_USER_NAME)){
                username = cookie.value();
            }
        }
        if (!TextUtils.isEmpty(username)){
            // 如果用户名不为空，那么就禁止用户名点击事件
            mUsernameTextView.setClickable(false);
            mUsernameTextView.setText(username);
        }

        // 设置注册登录点击事件
        mUsernameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到登录界面
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initFragment(){
        mHomeFragment = HomeFragment.newInstance();
        mKnowlegeHierachyFragment = KnowlegeHierachyFragment.newInstance();
        mWechatFragment = WechatFragment.newInstance();
        mNavigationFragment = NavigationFragment.newInstance();
        mProjectFragment = ProjectFragment.newInstance();

        mFragments.add(mHomeFragment);
        mFragments.add(mKnowlegeHierachyFragment);
        mFragments.add(mWechatFragment);
        mFragments.add(mNavigationFragment);
        mFragments.add(mProjectFragment);

        switchFragment(mCurrentIndex);

    }

    private void switchFragment(int position){
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        Fragment targetFragment = mFragments.get(position);
        Fragment currentFragment = mFragments.get(mCurrentIndex);
        mCurrentIndex = position;
        fragmentTransaction.hide(currentFragment);
        if (!targetFragment.isAdded()){
            // 如果目标fragment没有被添加,那么就进行谈价
            getSupportFragmentManager().beginTransaction().remove(targetFragment).commitAllowingStateLoss();
            fragmentTransaction.add(R.id.fragment_container, targetFragment);
        }
        fragmentTransaction.show(targetFragment);
        fragmentTransaction.commit();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mMainDrawerLayout = findViewById(R.id.main_drawer_layout);
        mNavigationView = findViewById(R.id.navigation_container);
        mFloatingActionButton = findViewById(R.id.floating_action_button);
        mContentFrameLayout = findViewById(R.id.fragment_container);
        mBottomNavigationView = findViewById(R.id.bottom_navigation_view);
        mToolbarTitle = findViewById(R.id.toolbar_title);

        // 侧滑菜单中的header
        View headerView = mNavigationView.getHeaderView(0);
        mUsernameTextView = headerView.findViewById(R.id.header_username_text_view);
        mHeaderImageView = headerView.findViewById(R.id.header_avatar_image_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.men_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search_tool_bar:
                Toast.makeText(MainActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                mMainDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}
