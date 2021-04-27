package com.els.tyoa.Servlet;

import com.els.tyoa.Dao.impl.UserDaoImal;
import com.els.tyoa.Entity.BaseResult;
import com.els.tyoa.Entity.Leave;
import com.els.tyoa.Entity.User;
import com.els.tyoa.Util.GetData;
import com.els.tyoa.Util.MyUtil;
import com.els.tyoa.Util.PushAppUtil;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/leave")
public class LeaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int is = Integer.parseInt(req.getParameter("is"));
        UserDaoImal userDaoImal = new UserDaoImal();
        Gson gson = new Gson();
        Date time = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = dateFormat.format(time);
        switch (is) {
            case 0 : {
                int code = -1;
                String name = MyUtil.newdecoder(req.getParameter("name"));
                String begin = req.getParameter("begin");
                String end = req.getParameter("end");
                int requester = Integer.parseInt(req.getParameter("requester"));
                String title = "请假";
                String content = name + "请假日期在" + begin +"到" + end;
                ArrayList<String> tokenlist = new ArrayList<>();
                List<User> list = userDaoImal.queryTokenByRoot();
                for (int i=0;i<list.size();i++) {
                    if (!list.get(i).equals("null")){
                        tokenlist.add(list.get(i).getToken());
                    }
                }
                int num = userDaoImal.queryMessNum();
                userDaoImal.insertMess(title,content,date,name,requester,-1);
                userDaoImal.insertLeave(name,begin,end);
                PushAppUtil.pushapp(title,content,tokenlist,String.valueOf(num+1),"0");
                BaseResult<String> res = new BaseResult<>();
                res.setCode(200);
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(gson.toJson(res));
                GetData.getData(req);
                break;
            }
            case 1 : {
                List<Leave> list = userDaoImal.queryLeaves();
                BaseResult<List<Leave>> res = new BaseResult<>();
                res.setCode(200);
                res.setData(list);
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(gson.toJson(res));
                GetData.getData(req);
                break;
            }
            case 2 : {
                int id = Integer.parseInt(req.getParameter("id"));
                String handler = MyUtil.newdecoder(req.getParameter("handler"));
                int handlerid = userDaoImal.queryIdByName(handler);
                Leave leave = userDaoImal.queryLeaveById(id);
                User user = userDaoImal.queryByUsername(leave.getName());
                int n = userDaoImal.updateLeaveIslook(id,handler);
                BaseResult<String> res = new BaseResult<>();
                res.setCode(-1);
                res.setData(String.valueOf(n));
                if (n == 1) {
                    res.setCode(200);
                    String token = user.getToken();
                    ArrayList<String> list = new ArrayList<>();
                    int num = userDaoImal.queryMessNum();
                    list.add(token);
                    String name = handler;
                    userDaoImal.insertMess("请假已通过","批准人: "+ name,date,name,handlerid,user.getId());
                    PushAppUtil.pushapp("请假已通过","批准人: "+ name,list,String.valueOf(num+1),name);
                }
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(gson.toJson(res));
                GetData.getData(req);
                break;
            }
            case 3 : {
                int id = Integer.parseInt(req.getParameter("id"));
                String handler = MyUtil.newdecoder(req.getParameter("handler"));
                int handlerid = userDaoImal.queryIdByName(handler);
                String mess = MyUtil.newdecoder(req.getParameter("mess"));
                System.out.println(mess);
                Leave leave = userDaoImal.queryLeaveById(id);
                User user = userDaoImal.queryByUsername(leave.getName());
                int n = userDaoImal.updateLeaveIslookFalse(id,handler,mess);
                BaseResult<String> res = new BaseResult<>();
                res.setCode(-1);
                res.setData(String.valueOf(n));
                if (n == 1) {
                    res.setCode(200);
                    String token = user.getToken();
                    ArrayList<String> list = new ArrayList<>();
                    list.add(token);
                    int num = userDaoImal.queryMessNum();
                    String name = handler;
                    userDaoImal.insertMess("请假未通过","理由： " + mess,date,name,handlerid,user.getId());
                    PushAppUtil.pushapp("请假未通过","理由： " + mess,list,String.valueOf(num+1),name);
                }
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
//        UserDaoImal userDaoImal = new UserDaoImal();
//        Gson gson = new Gson();
//        Date time = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        String date = dateFormat.format(time);
//        switch (is) {
//            case 0 : {
//                int code = -1;
//                String name = MyUtil.decoder(req.getParameter("name"));
//                String begin = req.getParameter("begin");
//                String end = req.getParameter("end");
//                int requester = Integer.parseInt(req.getParameter("requester"));
//                System.out.println(name+begin+end);
//                GetData.getData(req);
//                String title = "请假";
//                String content = name + "请假日期在" + begin +"到" + end;
//                ArrayList<String> tokenlist = new ArrayList<>();
//                List<User> list = userDaoImal.queryTokenByRoot();
//                for (int i=0;i<list.size();i++) {
//                    if (!list.get(i).equals("null")){
//                        tokenlist.add(list.get(i).getToken());
//                    }
//                }
//                int num = userDaoImal.queryMessNum();
//                userDaoImal.insertMess(title,content,date,name,requester,-1);
//                userDaoImal.insertLeave(name,begin,end);
//                PushAppUtil.pushapp(title,content,tokenlist,String.valueOf(num+1),"0");
//                BaseResult<String> res = new BaseResult<>();
//                res.setCode(200);
//                resp.setCharacterEncoding("utf-8");
//                resp.getWriter().append(gson.toJson(res));
//                GetData.getData(req);
//                break;
//            }
//            case 1 : {
//                List<Leave> list = userDaoImal.queryLeaves();
//                String res = gson.toJson(list);
//                resp.setCharacterEncoding("utf-8");
//                resp.getWriter().append(res);
//                GetData.getData(req);
//                break;
//            }
//            case 2 : {
//                int id = Integer.parseInt(req.getHeader("id"));
//                String handler = MyUtil.decoder(req.getHeader("handler"));
//                int handlerid = userDaoImal.queryIdByName(handler);
//                Leave leave = userDaoImal.queryLeaveById(id);
//                User user = userDaoImal.queryByUsername(leave.getName());
//                int res = userDaoImal.updateLeaveIslook(id,handler);
//                if (res == 1) {
//                    String token = user.getToken();
//                    ArrayList<String> list = new ArrayList<>();
//                    int num = userDaoImal.queryMessNum();
//                    list.add(token);
//                    String name = handler;
//                    userDaoImal.insertMess("请假已通过","批准人: "+ name,date,name,handlerid,user.getId());
//                    PushAppUtil.pushapp("请假已通过","批准人: "+ name,list,String.valueOf(num+1),name);
//                }
//                resp.setCharacterEncoding("utf-8");
//                resp.getWriter().append(String.valueOf(res));
//                GetData.getData(req);
//                break;
//            }
//            case 3 : {
//                int id = Integer.parseInt(req.getHeader("id"));
//                String handler = MyUtil.decoder(req.getHeader("handler"));
//                int handlerid = userDaoImal.queryIdByName(handler);
//                String mess = MyUtil.decoder(req.getHeader("mess"));
//                Leave leave = userDaoImal.queryLeaveById(id);
//                User user = userDaoImal.queryByUsername(leave.getName());
//                int res = userDaoImal.updateLeaveIslookFalse(id,handler,mess);
//                if (res == 1) {
//                    String token = user.getToken();
//                    ArrayList<String> list = new ArrayList<>();
//                    list.add(token);
//                    int num = userDaoImal.queryMessNum();
//                    String name = handler;
//                    userDaoImal.insertMess("请假未通过","理由： " + mess,date,name,handlerid,user.getId());
//                    PushAppUtil.pushapp("请假未通过","理由： " + mess,list,String.valueOf(num+1),name);
//                }
//                resp.setCharacterEncoding("utf-8");
//                resp.getWriter().append(String.valueOf(res));
//                GetData.getData(req);
//                break;
//            }
//        }
//
//
//    }


}
