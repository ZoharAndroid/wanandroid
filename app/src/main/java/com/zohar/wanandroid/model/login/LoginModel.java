package com.zohar.wanandroid.model.login;

import com.zohar.wanandroid.bean.User;

/**
 * Created by zohar on 2019/8/7 15:22
 * Describe:
 */
public class LoginModel implements ILoginModel {


    @Override
    public void login(final String username, final String password, final OnLoginListener loginListener) {
        // 开线程发送到服务端去判断用户名密码是否正确
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if ("zzh@qq.com".equals(username) && "123".equals(password)){
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    loginListener.loginSuccess(user);
                }else{
                    loginListener.loginFailed();
                }

            }
        }).start();
    }

    public void cancelTasks(){
        // TODO 终止线程池ThreadPool.shutDown()，AsyncTask.cancle()，或者调用框架的取消任务api
    }
}
