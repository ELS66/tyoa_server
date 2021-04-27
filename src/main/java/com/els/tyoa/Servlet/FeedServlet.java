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
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/feed")
public class FeedServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = MyUtil.decoder(req.getHeader("name"));
        String feed = MyUtil.decoder(req.getHeader("feedback"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String date = simpleDateFormat.format(date1);
        UserDaoImal userDaoImal = new UserDaoImal();
        int res = userDaoImal.insertFeedback(name,feed,date);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().append(String.valueOf(res));
        GetData.getData(req);
    }
}
