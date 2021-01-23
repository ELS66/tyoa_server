package com.els.tyoa.Servlet;

import com.els.tyoa.Dao.impl.UserDaoImal;
import com.els.tyoa.Entity.User;
import com.els.tyoa.Util.GetData;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.alibaba.druid.util.JdbcSqlStatUtils.getData;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result;
        String username = req.getHeader("user");
        String password = req.getHeader("pass");
        String token = req.getHeader("token");
        UserDaoImal userDaoImal = new UserDaoImal();
        User user = userDaoImal.queryByUsername(username);
        userDaoImal.updateUserToken(token,user.getId());
        System.out.println(user.toString());
        if (user == null) {
            result = "1";
        } else {
            if (password.equals(user.getPassword())){
                Gson gson = new Gson();
                result = gson.toJson(user);
                System.out.println(result);
            } else {
                result = "false";
            }
        }
        System.out.println(result);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().append(result);
        GetData getData = new GetData();
        getData.getData(req);
    }


}
