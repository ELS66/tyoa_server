package com.els.tyoa.Dao;

import com.els.tyoa.Entity.*;

import java.util.ArrayList;
import java.util.List;

public interface UserDao {
    public User queryByUsername(String username);

    int updateLeaveIslookFalse(int id, String handler, String mess);

    int updateLeaveIslook(int id, String handler);

    Leave queryLeaveById(int id);

    List<Leave> queryLeaves();

    int insertLeave(String name, String begin, String end);

    String getNotice();

    int insertNoticeMess(String mess, String name);

    int insertFeedback(String name, String feedback, String date);

    int updatePassByName(String name, String pass);

    Root queryRootByName(String name);

    List<String> queryuAllIDsByItem(String item);

    List<String> queryDelByItem(String item);

    int updateRootbyName(String root, String name);

    public User queryByUserid(int id);

    public User queryByUsernameAndPassword(String username,String password);

    int insertFinancial(String name, String project, String date, long money, String requester, String title);

    int insertUser(String username, int root, String password);

    public int insertMess(String title, String content, String date, String param, int requester, int root);

    int insertProject(String uid, String name, String management, String employee, String equipment, String startdate,String enddate,String address,String longitude,String latitude);

    int insertEquipment(int id, String name, String model);

    String queryProjectFinancial(String project);

    int updateProjectFinancial(String financial, String name);

    Financial queryFinancialById(int id);

    public MessItem queryMess(int num);

    public List<MessItem> queryMessList(int num,int root);

    public List<MessItem> queryMessList0(int root);

    List<MessItem> queryMessList1(int root, int num);

    List<Equipment> queryEquipmentList();

    String[] queryUserNameAll();

    String[] queryUserNameByRoot(int root);

    int deleteUserByName(String name);

    Project queryProjectByuid(int uid);

    Project queryProjectByName(String name);

    List<Project> queryProjectList();

    Equipment queryEquipmentByUid(int uid);

    Equipment queryEquipmentByName(String name);

    int updateEquipmentProjectByName(String project, String name);

    String queryProjectEquipment(String name);

    int updateProjectEquipment(String equipment, String name);

    int updateEquipmentProject(String project, int uid);

    public int updateUserToken(String token, int id);

    int updateUserRoot(String name, int i);

    public int updateMessItemIslook(int islook, int handler, int requester, int id);

    public int updateMessItemfeedback(String feedback,int handler,int requester,int id);

    public List<User> queryTokenByRoot();

    public ArrayList<String> queryTokenAll();

    public String[] queryNameAll();

    public int queryIdByName(String name);

    public String queryTokenByName(String name);

    public int queryMessNum();

    public int queryProjectNum();

    public List<String> queryNameByRoot(int root);

    int queryFinancialNum();
}
