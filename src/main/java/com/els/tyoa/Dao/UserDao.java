package com.els.tyoa.Dao;

import com.els.tyoa.Entity.MessItem;
import com.els.tyoa.Entity.User;

import java.sql.Timestamp;
import java.util.List;

public interface UserDao {
    public User queryByUsername(String username);

    public User queryByUsernameAndPassword(String username,String password);

    public int insertUser(String username,String password);

    public int insertMess( String title, String content,String date);

    public List<MessItem> queryMess(int num);

    public int updateUserToken(String token,int id);

    public List<User> queryToken();
}
