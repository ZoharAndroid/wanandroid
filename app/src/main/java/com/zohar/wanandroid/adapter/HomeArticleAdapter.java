package com.zohar.wanandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.zohar.wanandroid.ArticllDetailActivity;
import com.zohar.wanandroid.R;
import com.zohar.wanandroid.adapter.viewholder.FooterViewHolder;
import com.zohar.wanandroid.adapter.viewholder.ArticleViewHolder;
import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.bean.home.Datas;
import com.zohar.wanandroid.bean.home.banner.BannerData;
import com.zohar.wanandroid.config.AppConstants;
import com.zohar.wanandroid.presenter.CollectPresenter;
import com.zohar.wanandroid.utils.ToastUtils;
import com.zohar.wanandroid.view.home.ICollectView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zohar on 2019/8/8 17:08
 * Describe:
 */
public class HomeArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ICollectView {

    private List<Datas> articles = new ArrayList<>();
    private Context mContext;

    private List<BannerData> mBannerData = new ArrayList<>();



    @Override
    public void collectSuccess(Article data) {
        // 收藏成功
        if (data.getErrorCode() == 0){
            // 变换图标颜色

        }else{
            ToastUtils.toastShow(mContext, data.getErrorMsg());
        }
    }

    @Override
    public void collectFailed(String msg) {
        ToastUtils.toastShow(mContext, msg);
    }

    @Override
    public void changeCollectSuccessView(View clickView) {
        // 变换图标颜色
        ((ImageView)clickView).setImageResource(R.mipmap.icon_collect_select);
    }


    // 正常内容
    private final static int TYPE_CONTENT = 0;
    // footer
    private final static int TYPE_FOOTER = 1;
    // header
    private final static int TYPE_HEADER = 2;
    private HeaderViewHolder mHeaderViewHolder;

    public HomeArticleAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == articles.size() + 1) {
            return TYPE_FOOTER;
        } else if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_CONTENT;
        }
    }

    /**
     * 更新头布局
     *
     */
    public void updateHeaderView(List<BannerData> banners) {
        this.mBannerData = banners;

        // 插入到顶部
        notifyItemChanged(0);

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_CONTENT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_article, viewGroup, false);
            final ArticleViewHolder holder = new ArticleViewHolder(view);
            // 设置点击事件，进入链接文件文章详情页面
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition(); // 获取当前点击的位置
                    final Datas article = articles.get(position - 1);
                    // 跳转到内容详情页面
                    String articleLink = article.getLink();
                    Intent intent = new Intent(mContext, ArticllDetailActivity.class);
                    intent.putExtra(AppConstants.ARTICLE_FROM_HOME, articleLink); // 传递链接
                    intent.putExtra(AppConstants.ARTICLE_TITLE_FROM_HOME, article.getTitle()); // 传递标题
                    mContext.startActivity(intent);
                }
            });
            // 收藏按钮点击事件
            holder.collectImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 获取点击的数据
                    int position = holder.getAdapterPosition(); // 获取当前点击的位置
                    final Datas article = articles.get(position - 1);
                    // 请求服务器去表示去收藏
                    CollectPresenter mPresenter = new CollectPresenter(HomeArticleAdapter.this, v);
                    mPresenter.collectRequest(mContext, article.getId());
                }
            });
            return holder;
        } else if (viewType == TYPE_FOOTER) {
            // footer
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_article_footer, viewGroup, false);
            return new FooterViewHolder(view);
        } else {
            View headerView = LayoutInflater.from(mContext).inflate( R.layout.item_banner_header, viewGroup, false);
            mHeaderViewHolder = new HeaderViewHolder(headerView);
            // 设置广告图片的点击事件
            mHeaderViewHolder.banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                @Override
                public void onItemClick(XBanner banner, Object model, View view, int position) {
                    //ToastUtils.toastShow(mContext, "点击了第" + (position + 1) + "图片");
                    BannerData bannerData = (BannerData) model;
                    Intent intent = new Intent(mContext, ArticllDetailActivity.class);
                    intent.putExtra(AppConstants.ARTICLE_TITLE_FROM_HOME, bannerData.getTitle());
                    intent.putExtra(AppConstants.ARTICLE_FROM_HOME, bannerData.getUrl());
                    mContext.startActivity(intent);
                }
            });
            // 加载图片
            mHeaderViewHolder.banner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    BannerData bannerData = (BannerData) model;
                    // 设置标题
                    // 架子啊图片
                    Glide.with(mContext).load(bannerData.getImagePath()).into((ImageView) view);
                }
            });
            return mHeaderViewHolder;
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_FOOTER) {
            // 上拉加载

        } else if (getItemViewType(position) == TYPE_CONTENT) {
            // 正常内容
            // viewholder绑定的数据
            ArticleViewHolder articleViewHolder = (ArticleViewHolder) viewHolder;
            Datas article = articles.get(position - 1);

            articleViewHolder.title.setText(article.getTitle());
            articleViewHolder.author.setText(article.getAuthor());
            articleViewHolder.date.setText(article.getNiceDate());
            articleViewHolder.chapterName.setText(article.getChapterName());
            articleViewHolder.superChapterName.setText(article.getSuperChapterName());
            if (article.isFresh()) {
                articleViewHolder.refresh.setVisibility(View.VISIBLE);
            }
            // 处理tag
            if (article.getTags().size() != 0) {
                articleViewHolder.tag.setVisibility(View.VISIBLE);
                articleViewHolder.tag.setText(article.getTags().get(0).getName());
            }
            // 设置收藏图片
            if (article.isCollect()) {
                articleViewHolder.collectImageView.setImageResource(R.mipmap.icon_collect_select);
            }
        } else {
            // header
            HeaderViewHolder holder = (HeaderViewHolder)viewHolder;
            //刷新数据之后，需要重新设置是否支持自动轮播
            holder.banner.setAutoPlayAble(mBannerData.size() > 1);
            holder.banner.setBannerData(mBannerData);
            // 开始自动播放
            holder.banner.startAutoPlay();
        }

    }

    @Override
    public int getItemCount() {
        return articles == null ? 2 : articles.size() + 2;
    }


    /**
     * header View Holder
     */
    class HeaderViewHolder extends RecyclerView.ViewHolder {

        XBanner banner;

        public HeaderViewHolder( View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }


    /**
     * 添加内容，然后更新
     *
     * @param addArticle
     */
    public void addArticle(List<Datas> addArticle) {
        articles.addAll(addArticle);
        notifyItemRangeChanged(articles.size() - addArticle.size() + 1, addArticle.size());
    }

    /**
     * 清空list
     */
    public void clearArticle() {
        articles.clear();
    }

}
