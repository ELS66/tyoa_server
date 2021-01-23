package com.els.tyoa.Test;

import com.els.tyoa.Dao.impl.UserDaoImal;
import com.els.tyoa.Entity.MessItem;
import com.els.tyoa.Entity.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JdbcTest {
    public static void main(String[] args) {
        UserDaoImal userDaoImal = new UserDaoImal();
        List<User> list = userDaoImal.queryToken();

        System.out.println(list.get(0).getToken());
    }
}
