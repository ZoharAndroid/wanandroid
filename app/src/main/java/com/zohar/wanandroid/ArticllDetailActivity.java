package com.zohar.wanandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zohar.wanandroid.config.AppConstants;
import com.zohar.wanandroid.presenter.ArticleDetailPresenter;
import com.zohar.wanandroid.view.delail.IArticleDetailView;

/**
 * Created by zohar on 2019/8/25 16:04
 * Describe:
 */
public class ArticllDetailActivity extends AppCompatActivity implements IArticleDetailView {

    // 从主页获取的文章
    private String articleLink;
    private String articleTitle;

    private ArticleDetailPresenter mPresenter;

    // View
    private WebView mWebView;
    private ProgressBar mLoadProgress;
    private Toolbar mToolbar;
    private TextView mTextViewTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        articleLink = getIntent().getStringExtra(AppConstants.ARTICLE_FROM_HOME);
        articleTitle = getIntent().getStringExtra(AppConstants.ARTICLE_TITLE_FROM_HOME);

        initView();
        initToolBar();
        initEvent();

    }

    /**
     * 设置toolbar
     */
    private void initToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayShowTitleEnabled(false);
        }
        // 显示标题
        mTextViewTitle.setText(articleTitle);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 设置一些事件相关的内容
     */
    private void initEvent() {
        // 设置WebView
        settingWebView();
        mPresenter = new ArticleDetailPresenter(this);
        mPresenter.loadArticleContent();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mWebView = findViewById(R.id.content_web_view);
        mLoadProgress = findViewById(R.id.load_article_progress_bar);
        mToolbar = findViewById(R.id.article_detail_tool_bar);
        mTextViewTitle = findViewById(R.id.article_toolbar_title);
    }

    /**
     * 设置webview的相关属性
     */
    private void settingWebView(){
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        // 不显示缩放按钮
        settings.setDisplayZoomControls(false);
        // 将图片调整到合适的WEBvIEW大小
        settings.setUseWideViewPort(true);
        // 所放置置屏幕大小
        settings.setLoadWithOverviewMode(true);
        // 自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 创建菜单
        getMenuInflater().inflate(R.menu.menu_article_tool_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // menu选项
        switch (item.getItemId()){
            case R.id.item_collection_article:
                break;
            case R.id.item_share_article:
                break;
            case R.id.item_open_browser:
                break;
        }
        return true;
    }

    @Override
    public void showLoading() {
        mLoadProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mLoadProgress.setVisibility(View.GONE);
    }

    @Override
    public void loadArticleContent() {
        mWebView.loadUrl(articleLink);
    }

    @Override
    public void listenLoadingFinish() {
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                hideLoading();
            }
        });
    }
}