package com.els.tyoa.Servlet;

import com.els.tyoa.Entity.BaseResult;
import com.els.tyoa.Util.GetData;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String is = req.getParameter("is");
        String mess = req.getParameter("mess");
        System.out.println(is);
        System.out.println(mess);
        BaseResult<String> res = new BaseResult<>();
        res.setData(mess);
        res.setCode(200);
        Gson gson = new Gson();
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().append(gson.toJson(res));
        GetData.getData(req);
    }
}
