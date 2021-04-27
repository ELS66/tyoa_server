package com.els.tyoa.Servlet;

import com.els.tyoa.Dao.impl.UserDaoImal;
import com.els.tyoa.Util.GetData;
import com.els.tyoa.Util.MyUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/changepass")
public class ChangePassServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = MyUtil.decoder(req.getHeader("name"));
        String pass = req.getHeader("pass");
        UserDaoImal userDaoImal = new UserDaoImal();
        int res = userDaoImal.updatePassByName(username,pass);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().append(String.valueOf(res));
        GetData.getData(req);
    }
}
