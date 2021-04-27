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
import java.util.List;

@WebServlet("/root")
public class RootManageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int is = Integer.parseInt(req.getHeader("is"));
        UserDaoImal userDaoImal = new UserDaoImal();
        Gson gson = new Gson();
        switch (is) {
            case 0 : {
                String root = req.getHeader("root");
                List<String> list = userDaoImal.queryuAllIDsByItem(root);
                String res = gson.toJson(list);
                System.out.println(res);
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(res);
                GetData.getData(req);
                break;
            }
            case 1 : {
                String root = req.getHeader("root");
                List<String> list = userDaoImal.queryDelByItem(root);
                String res = gson.toJson(list);
                System.out.println(res);
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(res);
                GetData.getData(req);
                break;
            }
            case 2 : {
                String name= MyUtil.decoder(req.getHeader("name"));
                String root = req.getHeader("root");
                String str = root + " = " + 1;
                int res = userDaoImal.updateRootbyName(str  ,name);
                System.out.println(res);
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(String.valueOf(res));
                GetData.getData(req);
                break;
            }
            case 3 : {
                String name= MyUtil.decoder(req.getHeader("name"));
                String root = req.getHeader("root");
                String str = root + " = " + 0;
                int res = userDaoImal.updateRootbyName(str,name);
                System.out.println(res);
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(String.valueOf(res));
                GetData.getData(req);
                break;
            }
        }
    }
}
