package com.els.tyoa.Dao.impl;

import com.els.tyoa.Dao.UserDao;
import com.els.tyoa.Entity.MessItem;
import com.els.tyoa.Entity.User;

import java.sql.Timestamp;
import java.util.List;

public class UserDaoImal extends BaseDao implements UserDao {
    @Override
    public User queryByUsername(String username) {
        String sql = "select id,username,password,root,day from table_user where username = ?";
        return queryForone(User.class,sql,username);
    }

    @Override
    public User queryByUsernameAndPassword(String username, String password) {
        String sql = "select id,username,password,root,day from table_user where username = ? and password = ?";
        return queryForone(User.class,sql,username,password);
    }

    @Override
    public int insertUser(String username, String password) {
        String sql = "insert into table_user (username,password) values (?,?)";
        return insertUser(sql,username,password);
    }

    @Override
    public int insertMess(String title,String content,String date) {
        String sql = "insert into table_mess (title,content,date) values (?,?,?)";
        return insertMess(sql,title,content,date);
    }

    @Override
    public List<MessItem> queryMess(int num) {
        String sql = "select id,title,content,date from table_mess where id > ?";
        return queryForList(MessItem.class,sql,num);
    }

    @Override
    public int updateUserToken(String token,int id) {
        String sql = "update table_user set token = ? where id = ?";
        return update(sql,token,id);
    }

    @Override
    public List<User> queryToken() {
        String sql = "select token from table_user where root = 1";
        return queryForList(User.class,sql);
    }

}
