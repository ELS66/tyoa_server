package com.els.tyoa.Servlet;

import com.els.tyoa.Dao.impl.UserDaoImal;
import com.els.tyoa.Entity.MessItem;
import com.els.tyoa.Util.GetData;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/messlayout")
public class MessLayoutServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = "false";
        String num = req.getHeader("num");
        int n = Integer.parseInt(num);
        UserDaoImal userDaoImal = new UserDaoImal();
        MessItem messItem = userDaoImal.queryMess(n);
        if (messItem != null) {
            Gson gson = new Gson();
            result = gson.toJson(messItem);
        }
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().append(result);
        GetData.getData(req);
    }
}
