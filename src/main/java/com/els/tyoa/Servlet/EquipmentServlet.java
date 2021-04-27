package com.els.tyoa.Servlet;

import com.els.tyoa.Dao.impl.UserDaoImal;
import com.els.tyoa.Entity.BaseResult;
import com.els.tyoa.Entity.Equipment;
import com.els.tyoa.Entity.Project;
import com.els.tyoa.Util.GetData;
import com.els.tyoa.Util.MyUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/equipment")
public class EquipmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int is = Integer.parseInt(req.getParameter("is"));
        Gson gson = new Gson();
        switch (is) {
            case 0 : {
                int id = Integer.parseInt(req.getParameter("id"));
                String name = MyUtil.newdecoder(req.getParameter("name"));
                String model = MyUtil.newdecoder(req.getParameter("model"));
                UserDaoImal userDaoImal = new UserDaoImal();
                BaseResult<String> res = new BaseResult<>();
                res.setCode(-1);
                int num = userDaoImal.insertEquipment(id,name,model);
                if (num == 1) {
                    res.setCode(200);
                }
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(gson.toJson(res));
                GetData.getData(req);
                break;
            }
            case 1 : {
                UserDaoImal userDaoImal = new UserDaoImal();
                List<Equipment> equipments = userDaoImal.queryEquipmentList();
                BaseResult<List<Equipment>> res = new BaseResult<>();
                res.setData(equipments);
                res.setCode(200);
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(gson.toJson(res));
                GetData.getData(req);
                break;
            }
            case 2 : {
                int uid = Integer.parseInt(req.getParameter("uid"));
                UserDaoImal userDaoImal = new UserDaoImal();
                Equipment equipment = userDaoImal.queryEquipmentByUid(uid);
                List<Project> projects = userDaoImal.queryProjectList();
                List<String> stringList = new ArrayList<>();
                for (Project p : projects ) {
                    stringList.add(p.getName());
                }
                String[] strings = new String[2];
                strings[0] = gson.toJson(equipment);
                strings[1] = gson.toJson(stringList);
                BaseResult<String[]> res = new BaseResult<>();
                res.setData(strings);
                res.setCode(200);
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(gson.toJson(res));
                GetData.getData(req);
                break;
            }
            case 3 : {
                String name = MyUtil.newdecoder(req.getParameter("name"));
                String equipment = MyUtil.newdecoder(req.getParameter("equipment"));
                int uid = Integer.parseInt(req.getParameter("uid"));
                UserDaoImal userDaoImal = new UserDaoImal();
                userDaoImal.updateEquipmentProject(name,uid);
                String strequipment = userDaoImal.queryProjectEquipment(name);
                List<String> list;
                if (strequipment.equals("null")){
                    list = new ArrayList<>();
                    list.add(equipment);
                } else {
                    list = gson.fromJson(strequipment,new TypeToken<List<String>>(){}.getType());
                    list.add(equipment);
                }
                String newequipment = gson.toJson(list);
                userDaoImal.updateProjectEquipment(newequipment,name);
                String old = MyUtil.newdecoder(req.getParameter("old"));
                if (!old.equals("null")) {
                    String str = userDaoImal.queryProjectEquipment(old);
                    List<String> list1 = gson.fromJson(str,new TypeToken<List<String>>(){}.getType());
                    list1.remove(equipment);
                    if (list1.size() == 0){
                        userDaoImal.updateProjectEquipment("null",old);
                    }else {
                        userDaoImal.updateProjectEquipment(gson.toJson(list1),old);
                    }
                }
                BaseResult<String> res = new BaseResult<>();
                res.setData("");
                res.setCode(200);
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(gson.toJson(res));
                GetData.getData(req);
                break;
            }
        }
    }

//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int is = Integer.parseInt(req.getParameter("is"));
//        Gson gson = new Gson();
//        switch (is) {
//            case 0 : {
//                int id = Integer.parseInt(req.getParameter("id"));
//                String name = MyUtil.newdecoder(req.getParameter("name"));
//                String model = MyUtil.newdecoder(req.getParameter("model"));
//                UserDaoImal userDaoImal = new UserDaoImal();
//                BaseResult<String> res = new BaseResult<>();
//                res.setCode(-1);
//                int num = userDaoImal.insertEquipment(id,name,model);
//                if (num == 1) {
//                    res.setCode(200);
//                }
//                resp.setCharacterEncoding("utf-8");
//                resp.getWriter().append(gson.toJson(res));
//                GetData.getData(req);
//                break;
//            }
//            case 1 : {
//                UserDaoImal userDaoImal = new UserDaoImal();
//                List<Equipment> equipments = userDaoImal.queryEquipmentList();
//                BaseResult<List<Equipment>> res = new BaseResult<>();
//                res.setData(equipments);
//                res.setCode(200);
//                String res = gson.toJson(equipments);
//                resp.setCharacterEncoding("utf-8");
//                resp.getWriter().append(res);
//                GetData.getData(req);
//                break;
//            }
//            case 2 : {
//                int uid = Integer.parseInt(req.getHeader("uid"));
//                UserDaoImal userDaoImal = new UserDaoImal();
//                Equipment equipment = userDaoImal.queryEquipmentByUid(uid);
//                Gson gson = new Gson();
//                List<Project> projects = userDaoImal.queryProjectList();
//                List<String> stringList = new ArrayList<>();
//                for (Project p : projects ) {
//                    stringList.add(p.getName());
//                }
//                String[] strings = new String[2];
//                strings[0] = gson.toJson(equipment);
//                strings[1] = gson.toJson(stringList);
//                String res = gson.toJson(strings);
//                resp.setCharacterEncoding("utf-8");
//                resp.getWriter().append(res);
//                GetData.getData(req);
//                break;
//            }
//            case 3 : {
//                String name = MyUtil.decoder(req.getHeader("name"));
//                String equipment = MyUtil.decoder(req.getHeader("equipment"));
//                int uid = Integer.parseInt(req.getHeader("uid"));
//                UserDaoImal userDaoImal = new UserDaoImal();
//                userDaoImal.updateEquipmentProject(name,uid);
//                String strequipment = userDaoImal.queryProjectEquipment(name);
//                Gson gson = new Gson();
//                List<String> list;
//                if (strequipment.equals("null")){
//                    list = new ArrayList<>();
//                    list.add(equipment);
//                } else {
//                    list = gson.fromJson(strequipment,new TypeToken<List<String>>(){}.getType());
//                    list.add(equipment);
//                }
//                String newequipment = gson.toJson(list);
//                userDaoImal.updateProjectEquipment(newequipment,name);
//                String old = MyUtil.decoder(req.getHeader("old"));
//                System.out.println(old);
//                if (!old.equals("null")) {
//                    String str = userDaoImal.queryProjectEquipment(old);
//                    List<String> list1 = gson.fromJson(str,new TypeToken<List<String>>(){}.getType());
//                    list1.remove(equipment);
//                    if (list1.size() == 0){
//                        userDaoImal.updateProjectEquipment("null",old);
//                    }else {
//                        userDaoImal.updateProjectEquipment(gson.toJson(list1),old);
//                    }
//                }
//                resp.setCharacterEncoding("utf-8");
//                resp.getWriter().append("true");
//                GetData.getData(req);
//                break;
//            }
//        }
//    }
}
