package com.els.tyoa.Servlet;

import com.els.tyoa.Dao.impl.UserDaoImal;
import com.els.tyoa.Entity.BaseResult;
import com.els.tyoa.Entity.Root;
import com.els.tyoa.Util.GetData;
import com.els.tyoa.Util.MyUtil;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/me")
public class MeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int is = Integer.parseInt(req.getParameter("is"));
        switch (is) {
            case  0 : {
                String name = MyUtil.newdecoder(req.getParameter("name"));
                UserDaoImal userDaoImal = new UserDaoImal();
                Root root = userDaoImal.queryRootByName(name);
                List<Integer> list = new ArrayList<>();
                if (root.getI6() == 1) {
                    list.add(6);
                }
                if (root.getI7() == 1) {
                    list.add(7);
                }
                if (root.getI8() == 1) {
                    list.add(8);
                }
                if (root.getI9() == 1) {
                    list.add(9);
                }
                if (root.getI10() == 1) {
                    list.add(10);
                }
                if (root.getI11() == 1) {
                    list.add(11);
                }
                if (root.getI12() == 1) {
                    list.add(12);
                }
                if (root.getI13() == 1) {
                    list.add(13);
                }
                Gson gson = new Gson();
                String[] strings = new String[2];
                strings[0] = gson.toJson(list);
                strings[1] = userDaoImal.getNotice();
                BaseResult<String[]> res = new BaseResult<>();
                res.setCode(200);
                res.setData(strings);
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(gson.toJson(res));
                GetData.getData(req);
                break;
            }
        }
    }

//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int is = Integer.parseInt(req.getHeader("is"));
//        switch (is) {
//            case 0 : {
//                String name = MyUtil.decoder(req.getHeader("name"));
//                UserDaoImal userDaoImal = new UserDaoImal();
//                Root root = userDaoImal.queryRootByName(name);
//                List<Integer> list = new ArrayList<>();
//                if (root.getI6() == 1) {
//                    list.add(6);
//                }
//                if (root.getI7() == 1) {
//                    list.add(7);
//                }
//                if (root.getI8() == 1) {
//                    list.add(8);
//                }
//                if (root.getI9() == 1) {
//                    list.add(9);
//                }
//                if (root.getI10() == 1) {
//                    list.add(10);
//                }
//                if (root.getI11() == 1) {
//                    list.add(11);
//                }
//                if (root.getI12() == 1) {
//                    list.add(12);
//                }
//                if (root.getI13() == 1) {
//                    list.add(13);
//                }
//                Gson gson = new Gson();
//                String[] strings = new String[2];
//                strings[0] = gson.toJson(list);
//                strings[1] = userDaoImal.getNotice();
//                String res = gson.toJson(strings);
//                System.out.println(res);
//                resp.setCharacterEncoding("utf-8");
//                resp.getWriter().append(res);
//                GetData.getData(req);
//                break;
//            }
//        }
//    }
}
