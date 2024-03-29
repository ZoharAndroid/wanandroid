package com.zohar.wanandroid;

import android.content.Intent;
import android.net.Uri;
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

import com.zohar.wanandroid.bean.collect.CollectData;
import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.bean.home.Datas;
import com.zohar.wanandroid.config.AppConstants;
import com.zohar.wanandroid.presenter.ArticleDetailPresenter;
import com.zohar.wanandroid.presenter.collect.CancelCollectPresenter;
import com.zohar.wanandroid.presenter.collect.CollectPresenter;
import com.zohar.wanandroid.view.collect.ICancelCollectView;
import com.zohar.wanandroid.view.collect.ICollectView;
import com.zohar.wanandroid.view.delail.IArticleDetailView;

/**
 * Created by zohar on 2019/8/25 16:04
 * Describe:
 */
public class ArticllDetailActivity extends AppCompatActivity implements IArticleDetailView, ICollectView, ICancelCollectView {

    // 从主页获取的文章
    private String articleLink;
    private String articleTitle;
    private boolean isCollect;
    private int articleId;
    private Datas mArticle;

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
        isCollect = getIntent().getBooleanExtra(AppConstants.IS_COLLECT, false);
        mArticle = (Datas)getIntent().getSerializableExtra(AppConstants.ARTICLE);

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
        // 初始化设置menu的值
        if (isCollect){
            menu.findItem(R.id.item_collection_article).setIcon(R.mipmap.icon_item_collection_article_select);
        }else{
            menu.findItem(R.id.item_collection_article).setIcon(R.mipmap.icon_item_collection_article);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // menu选项
        switch (item.getItemId()){
            case R.id.item_collection_article:
                // 收藏文章
                if (isCollect){
                    // 如果已经收藏了，那么通过presenter去取消收藏
                    mArticle.setCollect(false);
                    item.setIcon(R.mipmap.icon_item_collection_article);
                    CancelCollectPresenter mCancelPresenter = new CancelCollectPresenter(ArticllDetailActivity.this, null);
                    mCancelPresenter.cancelArticleListCollectRequest(ArticllDetailActivity.this, mArticle.getId() );
                }else{
                    // 如果没有收藏，那么就通过presenter去添加到收藏
                    // 请求服务器去表示去收藏
                    mArticle.setCollect(true);
                    item.setIcon(R.mipmap.icon_item_collection_article_select);

                    CollectPresenter mPresenter = new CollectPresenter(ArticllDetailActivity.this, null);
                    mPresenter.collectRequest(ArticllDetailActivity.this, mArticle.getId());
                }
                break;
            case R.id.item_share_article:
                break;
            case R.id.item_open_browser:
                // 使用手机浏览器打开链接
                Uri uri = Uri.parse(articleLink);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
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

    @Override
    public void cancelCollectSuccess(CollectData collectData) {

    }

    @Override
    public void cancelCollectFailed(String msg) {

    }

    @Override
    public void collectSuccess(Article data) {

    }

    @Override
    public void collectFailed(String msg) {

    }

    @Override
    public void changeCollectSuccessView(View clickView) {

    }
}
