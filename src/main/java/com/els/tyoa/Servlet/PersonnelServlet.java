package com.els.tyoa.Servlet;

import com.els.tyoa.Dao.impl.UserDaoImal;
import com.els.tyoa.Util.GetData;
import com.els.tyoa.Util.MyUtil;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/personnel")
public class PersonnelServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int is = Integer.parseInt(req.getHeader("is"));
        switch (is){
            case 0 : {
                String name = MyUtil.decoder(req.getHeader("name"));
                int root = Integer.parseInt(req.getHeader("root"));
                String password = "e10adc3949ba59abbe56e057f20f883e";
                UserDaoImal userDaoImal = new UserDaoImal();
                int res = userDaoImal.insertUser(name,root,password);
                if (res != -1){
                    res = userDaoImal.queryIdByName(name);
                }
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(String.valueOf(res));
                GetData.getData(req);
                break;
            }
            case 1 : {
                UserDaoImal userDaoImal = new UserDaoImal();
                String[] strings = userDaoImal.queryUserNameAll();
                getNameList(req,resp,strings);
                break;
            }
            case 2 : {
                String name = MyUtil.decoder(req.getHeader("name"));
                UserDaoImal userDaoImal = new UserDaoImal();
                int res = userDaoImal.deleteUserByName(name);
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(String.valueOf(res));
                GetData.getData(req);
                break;
            }
            case 3 : {
                UserDaoImal userDaoImal = new UserDaoImal();
                String[] strings = userDaoImal.queryUserNameByRoot(2);
                getNameList(req,resp,strings);
                break;
            }
            case 4 : {
                UserDaoImal userDaoImal = new UserDaoImal();
                String[] strings = userDaoImal.queryUserNameByRoot(1);
                getNameList(req,resp,strings);
                break;
            }
            case 5 : {
                updateroot(req,resp,0);
                break;
            }
            case 6 : {
                updateroot(req,resp,1);
                break;
            }
            case 7 : {
                UserDaoImal userDaoImal = new UserDaoImal();
                String[] strings = userDaoImal.queryUserNameByRoot(0);
                getNameList(req,resp,strings);
                break;
            }
            case 8 : {
                updateroot(req,resp,2);
                break;
            }
        }
    }

    private void getNameList(HttpServletRequest req, HttpServletResponse resp,String[] strings) throws IOException {
        Gson gson = new Gson();
        String res = gson.toJson(strings);
        System.out.println(res);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().append(res);
        GetData.getData(req);
    }

    private void updateroot(HttpServletRequest req, HttpServletResponse resp,int i) throws IOException {
        UserDaoImal userDaoImal = new UserDaoImal();
        String name = MyUtil.decoder(req.getHeader("name"));
        int res = userDaoImal.updateUserRoot(name,i);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().append(String.valueOf(res));
        GetData.getData(req);
    }
}
