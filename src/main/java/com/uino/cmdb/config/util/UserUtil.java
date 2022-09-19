package com.uino.cmdb.config.util;


import com.uino.cmdb.config.platform.CurrentUser;

/**
 * Created by dell on 2019/12/5.
 * @author dell
 */
public class UserUtil {

    private static ThreadLocal<CurrentUser> userThreadLocal = new ThreadLocal<>();

    public static CurrentUser getCurrentUser() {
        CurrentUser currentUser = userThreadLocal.get();
        if (currentUser == null){
            CurrentUser defaultUser = new CurrentUser();
            defaultUser.setId("0");
            defaultUser.setUsername("default");
            return defaultUser;
        }
        return currentUser;
    }

    public static void setCurrentUser(CurrentUser username){
        userThreadLocal.set(username);
    }

}
