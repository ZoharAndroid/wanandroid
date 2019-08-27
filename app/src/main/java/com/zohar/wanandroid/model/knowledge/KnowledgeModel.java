package com.zohar.wanandroid.model.knowledge;

import com.google.gson.Gson;
import com.zohar.wanandroid.bean.knowledge.Knowledge;
import com.zohar.wanandroid.http.HttpRequestUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zohar on 2019/8/27 14:10
 * Describe:
 */
public class KnowledgeModel implements IKnowledgeModel {


    @Override
    public void sendKnowledgeGetRequest(String url, final OnKnowledgeListener knowledgeListener) {
        Call call = HttpRequestUtils.sendHttpGetRequest(url);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                knowledgeListener.httpFailed("网络请求失败！");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                Knowledge knowledge = gson.fromJson(result, Knowledge.class);
                knowledgeListener.knowledgeHttpSuccess(knowledge);
            }
        });
    }
}
