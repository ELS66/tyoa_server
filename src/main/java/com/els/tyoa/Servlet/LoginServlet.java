package com.els.tyoa.Servlet;

import com.els.tyoa.Dao.impl.UserDaoImal;
import com.els.tyoa.Entity.BaseResult;
import com.els.tyoa.Entity.User;
import com.els.tyoa.Util.GetData;
import com.els.tyoa.Util.MyUtil;
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
import java.net.URLDecoder;

import static com.alibaba.druid.util.JdbcSqlStatUtils.getData;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String result;
//        String username = URLDecoder.decode(req.getHeader("user"),"UTF-8");
//        String password = req.getHeader("pass");
//        String token = req.getHeader("token");
//        System.out.println(username);
//        UserDaoImal userDaoImal = new UserDaoImal();
//        User user = userDaoImal.queryByUsername(username);
//        System.out.println(user.toString());
//        userDaoImal.updateUserToken(token,user.getId());
//        System.out.println(user.toString());
//        if (user.getPassword().equals(password)) {
//            result = "1";
//        } else {
//            if (password.equals(user.getPassword())){
//                Gson gson = new Gson();
//                result = gson.toJson(user);
//                System.out.println(result);
//            } else {
//                result = "false";
//            }
//        }
//        System.out.println(result);
//        resp.setCharacterEncoding("utf-8");
//        resp.getWriter().append(result);
//        GetData.getData(req);
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int code = -1;
        String username = MyUtil.newdecoder(req.getParameter("user"));
        String password = req.getParameter("pass");
        String token = req.getParameter("token");
        UserDaoImal userDaoImal = new UserDaoImal();
        User user = userDaoImal.queryByUsername(username);
        if (user == null) {
            code = 1;
        } else {
            if (user.getPassword().equals(password)) {
                code = 200;
                userDaoImal.updateUserToken(token,user.getId());
            } else {
                code = 2;
            }
        }
        BaseResult<User> res = new BaseResult<>();
        res.setCode(code);
        res.setData(user);
        Gson gson = new Gson();
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().append(gson.toJson(res));
        GetData.getData(req);
    }
}
