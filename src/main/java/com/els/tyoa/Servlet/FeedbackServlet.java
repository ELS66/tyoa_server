package com.els.tyoa.Servlet;

import com.els.tyoa.Dao.impl.UserDaoImal;
import com.els.tyoa.Util.PushAppUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/feedback")
public class FeedbackServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String feedback = req.getHeader("feedback");
        UserDaoImal userDaoImal = new UserDaoImal();
        Date time = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = dateFormat.format(time);
        if (feedback.equals("true")) {
            int id = Integer.parseInt(req.getHeader("id"));
            int handler = Integer.parseInt(req.getHeader("handler"));
            int requester = Integer.parseInt(req.getHeader("requester"));
            int res = userDaoImal.updateMessItemIslook(1,handler,requester,id);
            if (res == 1) {
                String token = userDaoImal.queryByUserid(requester).getToken();
                System.out.println(requester);
                System.out.println(token);
                ArrayList<String> list = new ArrayList<>();
                int num = userDaoImal.queryMessNum();
                list.add(token);
                String name = userDaoImal.queryByUserid(handler).getUsername();
                userDaoImal.insertMess("请假已通过","批准人: "+ name,date,name,handler,requester);
                PushAppUtil.pushapp("请假已通过","批准人: "+ name,list,String.valueOf(num+1),name);
            }
            System.out.println(555);
        } else if (feedback.equals("false")){
            int id = Integer.parseInt(req.getHeader("id"));
            int handler = Integer.parseInt(req.getHeader("handler"));
            int requester = Integer.parseInt(req.getHeader("requester"));
            String text = req.getHeader("text");
            /*int res = userDaoImal.updateMessItemfeedback(text,handler,requester,id);
            if (res == 1) {

            }*/
            String token = userDaoImal.queryByUserid(requester).getToken();
            ArrayList<String> list = new ArrayList<>();
            list.add(token);
            int num = userDaoImal.queryMessNum();
            String name = userDaoImal.queryByUserid(handler).getUsername();
            userDaoImal.insertMess("请假未通过","理由： " + text,date,name,handler,requester);
            PushAppUtil.pushapp("请假未通过","理由： " + text,list,String.valueOf(num+1),name);
        }
    }
}
