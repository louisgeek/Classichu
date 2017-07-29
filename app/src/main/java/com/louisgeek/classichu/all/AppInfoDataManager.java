package com.louisgeek.classichu.all;


import com.louisgeek.classichu.login.bean.AuthBean;
import com.louisgeek.classichu.login.bean.UserLoginBean;

/**
 * Created by Classichu on 2017-6-5.
 */

public class AppInfoDataManager {

    /**
     * 双重校验锁 单例
     */
    private volatile static AppInfoDataManager instance;
    private AppInfoDataManager() {
    }
    public static AppInfoDataManager getInstance() {
        if (instance == null) {
            synchronized (AppInfoDataManager.class) {
                if (instance == null) {
                    instance = new AppInfoDataManager();
                }
            }
        }
        return instance;
    }
    private  UserLoginBean userLoginBean;
    public void setUserLoginBean(UserLoginBean userLoginBean) {
        this.userLoginBean=userLoginBean;
    }
    public UserLoginBean getUserLoginBean() {
        return userLoginBean;
    }

    private AuthBean authBean=new AuthBean();
    public AuthBean getAuthBean() {
        return authBean;
    }
}
