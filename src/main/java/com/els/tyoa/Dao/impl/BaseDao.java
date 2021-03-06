package com.els.tyoa.Dao.impl;

import com.els.tyoa.Util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {

    private QueryRunner query = new QueryRunner();

    public int update(String sql, Object... args){
        Connection conn = JdbcUtil.getConn();
        try {
            return query.update(conn,sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(conn);
        }
        return -1;
    }

    public <T>T queryForone(Class<T> type,String sql,Object... args){
        Connection conn = JdbcUtil.getConn();
        try {
            return query.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(conn);
        }
        return null;
    }

    public <T>List<T> queryForList(Class<T> type,String sql,Object... args) {
        Connection conn = JdbcUtil.getConn();
        try {
            return query.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(conn);
        }
        return null;
    }


    public int insert(String sql,Object... args) {
        Connection conn = JdbcUtil.getConn();
        try {
            return query.update(conn,sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(conn);
        }
        return -1;
    }

    /*public int insertMess(String sql, Object... args){
        Connection conn = JdbcUtil.getConn();
        try {
            return query.update(conn,sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(conn);
        }
        return -1;
    }*/

    public int deleteForOne(String sql,Object... args) {
        Connection conn = JdbcUtil.getConn();
        try {
            return query.update(conn,sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(conn);
        }
        return -1;
    }

    public int queryNums(String sql) {
        Connection conn = JdbcUtil.getConn();
        try {
            Object o = query.query(conn,sql,new ScalarHandler());
            return Integer.parseInt(o.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(conn);
        }
        return -1;
    }



}
