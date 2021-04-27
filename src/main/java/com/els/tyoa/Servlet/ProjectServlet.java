package com.els.tyoa.Servlet;

import com.els.tyoa.Dao.impl.UserDaoImal;
import com.els.tyoa.Entity.BaseResult;
import com.els.tyoa.Entity.Equipment;
import com.els.tyoa.Entity.Financial;
import com.els.tyoa.Entity.Project;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@WebServlet("/project")
public class ProjectServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int is = Integer.parseInt(req.getParameter("is"));
        UserDaoImal userDaoImal = new UserDaoImal();
        Gson gson = new Gson();
        switch (is) {
            case 0 : {
                List<String> list0 = userDaoImal.queryNameByRoot(2);
                List<String> list1 = userDaoImal.queryNameByRoot(1);
                List<Equipment> equipmentList = userDaoImal.queryEquipmentList();
                List<String> list3 = new ArrayList<>();
                for (Equipment e : equipmentList) {
                    if (e.getProject().equals("null")) {
                        list3.add(e.getName());
                    } else {
                        list3.add(e.getName() + "     " + e.getProject());
                    }
                }
                List<List<String>> list = new ArrayList<>();
                list.add(list0);
                list.add(list1);
                list.add(list3);
                BaseResult<List<List<String>>> res = new BaseResult<>();
                res.setCode(200);
                res.setData(list);
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(gson.toJson(res));
                GetData.getData(req);
                break;
            }
            case 1 : {
                String management = MyUtil.newdecoder(req.getParameter("management"));
                String employee = MyUtil.newdecoder(req.getParameter("employee"));
                String name = MyUtil.newdecoder(req.getParameter("name"));
                String address = MyUtil.newdecoder(req.getParameter("address"));
                String equipment = MyUtil.newdecoder(req.getParameter("equipment"));
                String longitude = MyUtil.newdecoder(req.getParameter("longitude"));
                String latitude = MyUtil.newdecoder(req.getParameter("latitude"));
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMdd");
                String startdate = simpleDateFormat.format(date);
                int num = userDaoImal.queryProjectNum();
                String uid = simpleDateFormat1.format(date) + num;
                List<String> managementlist = gson.fromJson(management,new TypeToken<List<String>>(){}.getType());
                List<String> employeelist = gson.fromJson(employee,new TypeToken<List<String>>(){}.getType());
                ArrayList<String> managementTokens = new ArrayList<>();
                ArrayList<String> employeeTokens = new ArrayList<>();
                StringBuilder stringBuilder_manage = new StringBuilder();
                StringBuilder stringBuilder_employee = new StringBuilder();
                for (String s : managementlist) {
                    //managementTokens.add(userDaoImal.queryTokenByName(s));
                    stringBuilder_manage.append(s + "，");
                }
                for (String s : employeelist) {
                    //employeeTokens.add(userDaoImal.queryTokenByName(s));
                    stringBuilder_employee.append(s + "，");
                }
                System.out.println(stringBuilder_employee);
                stringBuilder_manage.deleteCharAt(stringBuilder_manage.length()-1);
                stringBuilder_manage.append("。");
                stringBuilder_employee.deleteCharAt(stringBuilder_employee.length()-1);
                stringBuilder_employee.append("。");
                int r = userDaoImal.insertProject(uid,name,management,employee,equipment,startdate,"null",address,longitude,latitude);
                String content_manage = "新工程：" + name + "已经建立，工程编号为" + uid + "。您是管理人员，该工程工作人员有：" + stringBuilder_employee;
                String content_employee = "新工程：" + name + "已经建立，工程编号为" + uid + "。您是工作人员，管理人员为" + stringBuilder_manage;
                Date time = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String date_mess = dateFormat.format(time);
                for (String s : managementlist) {
                    managementTokens.add(userDaoImal.queryTokenByName(s));
                    int num_mess = userDaoImal.queryMessNum();
                    userDaoImal.insertMess("通知",content_manage,date_mess,"null",0,userDaoImal.queryIdByName(s));
                    PushAppUtil.pushapp("通知",content_manage,managementTokens,String.valueOf(num_mess),"null");
                }
                for (String s : employeelist) {
                    employeeTokens.add(userDaoImal.queryTokenByName(s));
                    int num_mess = userDaoImal.queryMessNum();
                    userDaoImal.insertMess("通知",content_employee,date_mess,"null",0,userDaoImal.queryIdByName(s));
                    PushAppUtil.pushapp("通知",content_employee,employeeTokens,String.valueOf(num_mess),"null");
                }
                if (!equipment.equals("null")) {
                    List<String> equipmetnlist = gson.fromJson(equipment,new TypeToken<List<String>>(){}.getType());
                    for (String s : equipmetnlist) {
                        userDaoImal.updateEquipmentProjectByName(name,s);
                    }
                }
                BaseResult<String> res = new BaseResult<>();
                res.setCode(-1);
                res.setData(uid);
                if (r == 1) {
                    res.setCode(200);
                }
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(gson.toJson(res));
                GetData.getData(req);
                break;
            }
            case 2 : {
                List<Project> list = userDaoImal.queryProjectList();
                BaseResult<List<Project>> res = new BaseResult<>();
                res.setCode(200);
                res.setData(list);
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(gson.toJson(res));
                GetData.getData(req);
                break;
            }
            case 3 : {
                String name = MyUtil.newdecoder(req.getParameter("name"));
                Project project = userDaoImal.queryProjectByName(name);
                String string1 = gson.toJson(project);
                List<String> list = gson.fromJson(project.getEquipment(),new TypeToken<List<String>>(){}.getType());
                List<Equipment> equipmentList = new ArrayList<>();
                for (String s : list) {
                    equipmentList.add(userDaoImal.queryEquipmentByName(s));
                }
                String string2 = gson.toJson(equipmentList);
                String string3;
                if (project.getFinancial().equals("null")) {
                    string3 = "null";
                } else {
                    List<Financial> financialList = new ArrayList<>();
                    List<Integer> integerList = gson.fromJson(project.getFinancial(),new TypeToken<List<Integer>>(){}.getType());
                    for (int i : integerList) {
                        financialList.add(userDaoImal.queryFinancialById(i));
                    }
                    string3 = gson.toJson(financialList);
                }
                String[] strings = {string1,string2,string3};
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
//                UserDaoImal userDaoImal = new UserDaoImal();
//                List<String> list0 = userDaoImal.queryNameByRoot(2);
//                List<String> list1 = userDaoImal.queryNameByRoot(1);
//                List<Equipment> equipmentList = userDaoImal.queryEquipmentList();
//                List<String> list3 = new ArrayList<>();
//                for (Equipment e : equipmentList) {
//                    if (e.getProject().equals("null")) {
//                        list3.add(e.getName());
//                    } else {
//                        list3.add(e.getName() + "     " + e.getProject());
//                    }
//                }
//                List<List<String>> list = new ArrayList<>();
//                list.add(list0);
//                list.add(list1);
//                list.add(list3);
//                Gson gson = new Gson();
//                BaseResult<List<List<String>>> res = new BaseResult<>();
//                res.setCode(200);
//                res.setData(list);
//                resp.setCharacterEncoding("utf-8");
//                resp.getWriter().append(gson.toJson(res));
//                GetData.getData(req);
//                break;
//            }
//            case 1 : {
//                UserDaoImal userDaoImal = new UserDaoImal();
//                String management = MyUtil.newdecoder(req.getParameter("management"));
//                String employee = MyUtil.newdecoder(req.getParameter("employee"));
//                String name = MyUtil.newdecoder(req.getParameter("name"));
//                String address = MyUtil.newdecoder(req.getParameter("address"));
//                String equipment = MyUtil.newdecoder(req.getParameter("equipment"));
//                String longitude = MyUtil.newdecoder(req.getParameter("longitude"));
//                String latitude = MyUtil.newdecoder(req.getParameter("latitude"));
//                Date date = new Date();
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMdd");
//                String startdate = simpleDateFormat.format(date);
//                int num = userDaoImal.queryProjectNum();
//                String uid = simpleDateFormat1.format(date) + num;
//                resp.setCharacterEncoding("utf-8");
//                resp.getWriter().append(uid);
//                GetData.getData(req);
//                Gson gson = new Gson();
//                List<String> managementlist = gson.fromJson(management,new TypeToken<List<String>>(){}.getType());
//                List<String> employeelist = gson.fromJson(employee,new TypeToken<List<String>>(){}.getType());
//                ArrayList<String> managementTokens = new ArrayList<>();
//                ArrayList<String> employeeTokens = new ArrayList<>();
//                StringBuilder stringBuilder_manage = new StringBuilder();
//                StringBuilder stringBuilder_employee = new StringBuilder();
//                for (String s : managementlist) {
//                    //managementTokens.add(userDaoImal.queryTokenByName(s));
//                    stringBuilder_manage.append(s + "，");
//                }
//                for (String s : employeelist) {
//                    //employeeTokens.add(userDaoImal.queryTokenByName(s));
//                    stringBuilder_employee.append(s + "，");
//                }
//                System.out.println(stringBuilder_employee);
//                stringBuilder_manage.deleteCharAt(stringBuilder_manage.length()-1);
//                stringBuilder_manage.append("。");
//                stringBuilder_employee.deleteCharAt(stringBuilder_employee.length()-1);
//                stringBuilder_employee.append("。");
//                int r = userDaoImal.insertProject(uid,name,management,employee,equipment,startdate,"null",address,longitude,latitude);
//                String content_manage = "新工程：" + name + "已经建立，工程编号为" + uid + "。您是管理人员，该工程工作人员有：" + stringBuilder_employee;
//                String content_employee = "新工程：" + name + "已经建立，工程编号为" + uid + "。您是工作人员，管理人员为" + stringBuilder_manage;
//                Date time = new Date();
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//                String date_mess = dateFormat.format(time);
//                for (String s : managementlist) {
//                    managementTokens.add(userDaoImal.queryTokenByName(s));
//                    int num_mess = userDaoImal.queryMessNum();
//                    userDaoImal.insertMess("通知",content_manage,date_mess,"null",0,userDaoImal.queryIdByName(s));
//                    PushAppUtil.pushapp("通知",content_manage,managementTokens,String.valueOf(num_mess),"null");
//                }
//                for (String s : employeelist) {
//                    employeeTokens.add(userDaoImal.queryTokenByName(s));
//                    int num_mess = userDaoImal.queryMessNum();
//                    userDaoImal.insertMess("通知",content_employee,date_mess,"null",0,userDaoImal.queryIdByName(s));
//                    PushAppUtil.pushapp("通知",content_employee,employeeTokens,String.valueOf(num_mess),"null");
//                }
//                if (!equipment.equals("null")) {
//                    List<String> equipmetnlist = gson.fromJson(equipment,new TypeToken<List<String>>(){}.getType());
//                    for (String s : equipmetnlist) {
//                        userDaoImal.updateEquipmentProjectByName(name,s);
//                    }
//                }
//                BaseResult<String> res = new BaseResult<>();
//                res.setCode(-1);
//                res.setData("");
//                if (r == 1) {
//                    res.setCode(200);
//                }
//                resp.setCharacterEncoding("utf-8");
//                resp.getWriter().append(gson.toJson(res));
//                GetData.getData(req);
//                break;
//            }
//            case 2 : {
//                UserDaoImal userDaoImal = new UserDaoImal();
//                List<Project> list = userDaoImal.queryProjectList();
//                Gson gson = new Gson();
//                BaseResult<List<Project>> res = new BaseResult<>();
//                res.setCode(200);
//                res.setData(list);
//                resp.setCharacterEncoding("utf-8");
//                resp.getWriter().append(gson.toJson(res));
//                GetData.getData(req);
//                break;
//            }
//            case 3 : {
//                UserDaoImal userDaoImal = new UserDaoImal();
//                String name = MyUtil.newdecoder(req.getParameter("name"));
//                Project project = userDaoImal.queryProjectByName(name);
//                Gson gson = new Gson();
//                String string1 = gson.toJson(project);
//                List<String> list = gson.fromJson(project.getEquipment(),new TypeToken<List<String>>(){}.getType());
//                List<Equipment> equipmentList = new ArrayList<>();
//                for (String s : list) {
//                	equipmentList.add(userDaoImal.queryEquipmentByName(s));
//				}
//                String string2 = gson.toJson(equipmentList);
//                String string3;
//                if (project.getFinancial().equals("null")) {
//                    string3 = "null";
//                } else {
//                    List<Financial> financialList = new ArrayList<>();
//                    List<Integer> integerList = gson.fromJson(project.getFinancial(),new TypeToken<List<Integer>>(){}.getType());
//                    for (int i : integerList) {
//                        financialList.add(userDaoImal.queryFinancialById(i));
//                    }
//                    string3 = gson.toJson(financialList);
//                }
//                String[] strings = {string1,string2,string3};
//                BaseResult<String[]> res = new BaseResult<>();
//                res.setCode(200);
//                res.setData(strings);
//                resp.setCharacterEncoding("utf-8");
//                resp.getWriter().append(gson.toJson(res));
//                GetData.getData(req);
//                break;
//            }
//            case 4 : {
//
//            }
//        }
//    }
}
