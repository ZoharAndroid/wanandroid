package com.zohar.wanandroid.model.knowledge;

import com.zohar.wanandroid.bean.knowledge.Knowledge;

/**
 * Created by zohar on 2019/8/27 14:12
 * Describe:
 *  请求接口回调
 */
public interface OnKnowledgeListener {

    void httpFailed(String msg);

    void knowledgeHttpSuccess(Knowledge knowledge);
}
