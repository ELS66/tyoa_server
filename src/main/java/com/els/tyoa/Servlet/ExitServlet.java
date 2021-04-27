package com.els.tyoa.Servlet;

import com.els.tyoa.Dao.impl.UserDaoImal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/exit")
public class ExitServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        String token = "null";
        System.out.println(token);
        id = Integer.parseInt(req.getHeader("id"));
        System.out.println(id);
        UserDaoImal userDaoImal = new UserDaoImal();
        userDaoImal.updateUserToken(token,id);
        System.out.println("exit ok");
    }
}
