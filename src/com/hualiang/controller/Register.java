package com.hualiang.controller;

import com.hualiang.common.utils.EmailUtils;
import com.hualiang.domain.User;

public class Register implements Runnable{

    User user;
    // 重试次数
    int count = 3;

    Register(User user){
        this.user = user;
    }

    @Override
    public void run() {
        try {
            EmailUtils.sendEmail(user);
        } catch (Exception e){
            e.printStackTrace();
            if(--count > 0){
                run();
            }
        }
    }
}
