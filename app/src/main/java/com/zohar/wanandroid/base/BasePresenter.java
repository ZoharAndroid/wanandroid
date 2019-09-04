package com.zohar.wanandroid.base;

import android.icu.util.IndianCalendar;
import android.view.View;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zohar on 2019/9/2 16:43
 * Describe:
 *
 *  Presenter的基类
 */
public abstract class BasePresenter<M extends IBaseModel, V extends IBaseView> implements IBasePresenter<V> {

    private V mView;
    private M module;
    private WeakReference<V> mWeakReference;

    /**
     * 绑定view
     *
     * @param view
     */
    @SuppressWarnings("unchecked")
    public void attachView(V view){
        mWeakReference = new WeakReference<>(view);
        mView = (V)Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new MvpViewHandler(mWeakReference.get()));
        if (this.module == null){
            this.module = createModule();
        }
    }

    /**
     * 分离View
     */
    public void detachView(){
        this.module = null;
        if (isViewAttached()){
            mWeakReference.clear();
            mWeakReference = null;
        }
    }

    /**
     * 判断view是否销毁
     *
     * @return
     */
    public boolean isViewAttached(){
        return mWeakReference != null && mWeakReference.get() != null;
    }


    /**
     * 获取view
     *
     * @return view
     */
    protected V getView(){
        return mView;
    }

    /**
     * 获取module
     *
     * @return model
     */
    protected M getModule(){
        return module;
    }

    protected abstract M createModule();

    /**
     * View代理类：防止页面关闭了P层异步调用V方法，造成空指针异常
     */
    private class MvpViewHandler implements InvocationHandler {

        private IBaseView mvpView;

        public MvpViewHandler(IBaseView mvpView) {
            this.mvpView = mvpView;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // 如果view层没有被销毁，那么就调用view层的方法
            if (isViewAttached()){
                return method.invoke(mvpView, args);
            }
            // P层不关心view层的返回值
            return null;
        }
    }
}
