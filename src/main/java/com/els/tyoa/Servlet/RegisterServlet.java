package com.els.tyoa.Servlet;

import com.els.tyoa.Dao.impl.UserDaoImal;
import com.els.tyoa.Entity.User;
import com.els.tyoa.Util.GetData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import static com.alibaba.druid.util.JdbcSqlStatUtils.getData;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = "null";
        String username = URLDecoder.decode(req.getHeader("user"),"UTF-8");
        System.out.println(username);
        String password = req.getHeader("pass");
        UserDaoImal userDaoImal = new UserDaoImal();
        User user = userDaoImal.queryByUsername(username);
        if (user != null) {
            result = "you";
        } else {
            int n = userDaoImal.insertUser(username,password);
            System.out.println(n);
            if (n == 1) {
                result = "true";
            } else {
                result = "false";
            }
        }
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().append(result);
        GetData getData = new GetData();
        getData.getData(req);
    }
}
