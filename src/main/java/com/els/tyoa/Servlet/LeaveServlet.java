package com.els.tyoa.Servlet;

import com.els.tyoa.Dao.impl.UserDaoImal;
import com.els.tyoa.Entity.User;
import com.els.tyoa.Util.GetData;
import com.els.tyoa.Util.PushAppUtil;
import com.tencent.xinge.XingeApp;
import com.tencent.xinge.bean.*;
import com.tencent.xinge.push.app.PushAppRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@WebServlet("/leave")
public class LeaveServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = "true";
        String name = req.getHeader("name");
        String begin = req.getHeader("begin");
        String end = req.getHeader("end");
        System.out.println(name+begin+end);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().append(result);
        GetData getData = new GetData();
        getData.getData(req);
        String title = "请假";
        String content = name + "请假日期在" + begin +"到" + end;
        ArrayList<String> tokenlist = new ArrayList<>();
        UserDaoImal userDaoImal = new UserDaoImal();
        List<User> list = userDaoImal.queryToken();
        for (int i=0;i<list.size();i++) {
            tokenlist.add(list.get(i).getToken());
        }

        Date time = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = dateFormat.format(time);
        PushAppUtil.pushapp(title,content,tokenlist);

        userDaoImal.insertMess(title,content,date);
    }
}
