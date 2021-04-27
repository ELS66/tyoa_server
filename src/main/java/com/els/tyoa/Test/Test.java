package com.els.tyoa.Test;

import com.els.tyoa.Dao.impl.UserDaoImal;
import com.els.tyoa.Entity.User;

public class Test {
    public static void main(String[] args) {
        UserDaoImal userDaoImal = new UserDaoImal();
        String username = "lsxx";
        String password =  "e10adc3949ba59abbe56e057f20f883e";
        User user = userDaoImal.queryByUsername(username);
        System.out.println(user == null);
    }
}
