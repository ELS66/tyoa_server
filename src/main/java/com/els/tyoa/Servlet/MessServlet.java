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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/mess")
public class MessServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = "false";
        String num = req.getHeader("num");
        int n = Integer.parseInt(num);
        int id = Integer.parseInt(req.getHeader("id"));
        UserDaoImal userDaoImal = new UserDaoImal();
        int root = userDaoImal.queryByUserid(id).getRoot();
        List<MessItem> list;
        if (root == 1) {
            list = userDaoImal.queryMessList1(id,n);
        } else {
            list = userDaoImal.queryMessList0(id);
            for (int i=n;i>0;i--) {
                list.remove(0);
            }
        }
        if (list.size() != 0) {
            Gson gson = new Gson();
            result = gson.toJson(list);
            System.out.println(result);
        }
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().append(result);
        GetData.getData(req);
    }
}
