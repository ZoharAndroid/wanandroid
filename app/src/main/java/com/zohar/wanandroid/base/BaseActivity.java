package com.zohar.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ZHANGZHIHONG$ on 2019/9/4 16:11
 * Describe:
 *  Activity的基类
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView{


    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResId());
        initView();
        initEventAndData();
        // 创建Presenter
        presenter = createPresenter();
        if (presenter != null){
            presenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null){
            presenter.detachView();
            presenter = null;
        }
    }

    /**
     * 创建Presenter
     *
     * @return P Presenter对象
     */
    protected abstract P createPresenter();

    /**
     * 插入布局文件
     *
     * @return
     */
    protected abstract int setLayoutResId();

    /**
     * 初始化布局View
     */
    protected abstract void initView();

    /**
     * 初始哈化事件和数据
     */
    protected abstract void initEventAndData();
}
