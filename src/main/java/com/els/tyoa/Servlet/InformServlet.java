package com.els.tyoa.Servlet;

import com.els.tyoa.Dao.impl.UserDaoImal;
import com.els.tyoa.Util.GetData;
import com.els.tyoa.Util.MyUtil;
import com.els.tyoa.Util.PushAppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/inform")
public class InformServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getHeader("is"));
        int is = Integer.parseInt(req.getHeader("is"));
        System.out.println(is);
        switch (is) {
            case 0: {
                System.out.println(0);
                int id = Integer.parseInt(req.getHeader("id"));
                String name = MyUtil.decoder(req.getHeader("name"));
                String content = MyUtil.decoder(req.getHeader("content"));
                String token = req.getHeader("token");
                if (token.equals("all")){
                    UserDaoImal userDaoImal = new UserDaoImal();
                    ArrayList<String> list = userDaoImal.queryTokenAll();
                    System.out.println(list);
                    int num = userDaoImal.queryMessNum();
                    Date time = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    String date = dateFormat.format(time);
                    userDaoImal.insertMess("通知",content,date,name,id,0);
                    PushAppUtil.pushapp("通知",content,list,String.valueOf(num+1),name);
                }
                String result = "true";
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(result);
                GetData.getData(req);
                break;
            }
            case 1: {
                System.out.println(1);
                UserDaoImal userDaoImal = new UserDaoImal();
                String[] strings = userDaoImal.queryNameAll();
                Gson gson = new Gson();
                String res = gson.toJson(strings);
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(res);
                GetData.getData(req);
                break;
            }
            case 2: {
                System.out.println(2);
                String liststr = MyUtil.decoder(req.getHeader("list"));
                System.out.println(liststr);
                String content = req.getHeader("content");
                String name = MyUtil.decoder(req.getHeader("name"));
                int id = Integer.parseInt(req.getHeader("id"));
                Gson gson = new Gson();
                List<String> list = gson.fromJson(liststr,new TypeToken<List<String>>(){}.getType());
                System.out.println(list);

                UserDaoImal userDaoImal = new UserDaoImal();
                Date time = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String date = dateFormat.format(time);
                for (String s : list) {
                    ArrayList<String> tokens = new ArrayList<>();
                    tokens.add(userDaoImal.queryTokenByName(s));
                    userDaoImal.insertMess("通知",content,date,name,id,userDaoImal.queryIdByName(s));
                    int num = userDaoImal.queryMessNum();
                    PushAppUtil.pushapp("通知",content,tokens,String.valueOf(num),name);
                }
                /*int num = userDaoImal.queryMessNum();
                PushAppUtil.pushapp("通知",content,tokens,String.valueOf(num),name);*/
                String result = "true";
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(result);
                GetData.getData(req);
                break;
            }
            default: {
                break;
            }
        }
    }
}
