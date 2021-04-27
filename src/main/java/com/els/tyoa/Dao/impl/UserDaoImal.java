package com.els.tyoa.Dao.impl;

import com.els.tyoa.Dao.UserDao;
import com.els.tyoa.Entity.*;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImal extends BaseDao implements UserDao {
    @Override
    public User queryByUsername(String username) {
        String sql = "select id,username,password,root,day,token from table_user where username = ?";
        return queryForone(User.class,sql,username);
    }

    @Override
    public int updateLeaveIslookFalse(int id,String handler,String mess) {
        String sql = "update table_leave set islook = true, handler = ?,mess = ? where id = ?";
        return update(sql,handler,mess,id);
    }

    @Override
    public int updateLeaveIslook(int id,String handler) {
        String sql = "update table_leave set islook = true, handler = ? where id = ?";
        return update(sql,handler,id);
    }

    @Override
    public Leave queryLeaveById(int id) {
        String sql = "select id,name,begin,end,islook,handler from table_leave where id = ?";
        return queryForone(Leave.class,sql,id);
    }

    @Override
    public List<Leave> queryLeaves() {
        String sql = "select id,name,begin,end,islook,handler from table_leave where islook = false";
        return queryForList(Leave.class,sql);
    }

    @Override
    public int insertLeave(String name, String begin, String end) {
        String sql = "insert into table_leave (name,begin,end) values (?,?,?)";
        return insert(sql,name,begin,end);
    }

    @Override
    public String getNotice() {
        String sql = "select mess from table_notice order by id desc limit 1";
        Notice notice =  queryForone(Notice.class,sql);
        return notice.getMess();
    }

    @Override
    public int insertNoticeMess(String mess, String name) {
        String sql = "insert into table_notice (mess,name) values (?,?)";
        return insert(sql,mess,name);
    }

    @Override
    public int insertFeedback(String name, String feedback, String date) {
        String sql = "insert into table_feed (name,feedback,date) values (?,?,?)";
        return insert(sql,name,feedback,date);
    }

    @Override
    public int updatePassByName(String name, String pass) {
        String sql = "update table_user set password = ? where username = ? ";
        return update(sql,pass,name);
    }

    @Override
    public Root queryRootByName(String name) {
        String sql = "select id,i6,i7,i8,i9,i10,i11,i12,i13 from table_root where name = ?";
        return queryForone(Root.class,sql,name);
    }

    @Override
    public List<String> queryuAllIDsByItem(String item) {
        String sql = "select name from table_root where " + item + " = 0";
        List<Root> list = queryForList(Root.class,sql);
        List<String> res = new ArrayList<>();
        for (int i=0;i<list.size();i++) {
            res.add(list.get(i).getName());
        }
        return res;
    }

    @Override
    public List<String> queryDelByItem(String item) {
        String sql = "select name from table_root where " + item + " = 1";
        List<Root> list = queryForList(Root.class,sql);
        List<String> res = new ArrayList<>();
        for (int i=0;i<list.size();i++) {
            res.add(list.get(i).getName());
        }
        return res;
    }

    @Override
    public int updateRootbyName(String item, String name) {
        String sql = "update table_root set " + item +" where name = ?";
        return update(sql,name);
    }

    @Override
    public User queryByUserid(int id) {
        String sql = "select id,username,password,root,day,token from table_user where id = ?";
        return queryForone(User.class,sql,id);
    }

    @Override
    public User queryByUsernameAndPassword(String username, String password) {
        String sql = "select id,username,password,root,day from table_user where username = ? and password = ?";
        return queryForone(User.class,sql,username,password);
    }

    @Override
    public int insertFinancial(String name, String project, String date, long money, String requester, String title) {
        String sql = "insert into table_financial (name,project,date,money,requester,title) values (?,?,?,?,?,?)";
        return insert(sql,name,project,date,money,requester,title);
    }

    @Override
    public int insertUser(String username, int root,String password) {
        String sql = "insert into table_user (username,root,password) values (?,?,?)";
        return insert(sql,username,root,password);
    }

    @Override
    public int insertMess(String title,String content,String date,String param ,int requester,int root) {
        String sql = "insert into table_mess (title,content,date,param,requester,root) values (?,?,?,?,?,?)";
        return insert(sql,title,content,date,param,requester,root);
    }

    @Override
    public int insertProject(String uid, String name, String management, String employee, String equipment, String startdate, String enddate, String address, String longitude, String latitude) {
        String sql = "insert into table_project (uid,name,management,employee,equipment,startdate,enddate,address,longitude,latitude) values (?,?,?,?,?,?,?,?,?,?)";
        return insert(sql,uid,name,management,employee,equipment,startdate,enddate,address,longitude,latitude);
    }

    @Override
    public int insertEquipment(int id,String name,String model) {
        String sql = "insert into table_equipment (uid,name,model) values (?,?,?)";
        return insert(sql,id,name,model);
    }

    @Override
    public String queryProjectFinancial(String project) {
        String sql = "select financial from table_project where name = ?";
        return queryForone(Project.class,sql,project).getFinancial();
    }

    @Override
    public int updateProjectFinancial(String financial, String name) {
        String sql = "update table_project set financial = ? where name = ?";
        return update(sql,financial,name);
    }

    @Override
    public Financial queryFinancialById(int id) {
        String sql = "select id,name,project,date,ispass,money,requester,title from table_financial where id = ?";
        return queryForone(Financial.class,sql,id);
    }

    @Override
    public MessItem queryMess(int num) {
        String sql = "select id,title,content,date,islook,param,handler,requester from table_mess where id = ?";
        return queryForone(MessItem.class,sql,num);
    }

    @Override
    public List<MessItem> queryMessList(int num, int root) {
        String sql = "select id,title,content,date,islook,param,handler,requester from table_mess where id > ? and root = ?";
        return queryForList(MessItem.class,sql,num,root);
    }

    @Override
    public List<MessItem> queryMessList0(int root) {
        String sql = "select id,title,content,date,islook,param,handler,requester from table_mess where (root = ? or root = 0)";
        return queryForList(MessItem.class,sql,root);
    }
    @Override
    public List<MessItem> queryMessList1(int root, int num) {
        String sql = "select id,title,content,date,islook,param,handler,requester from table_mess where (root = ? or root = 0 or root = -1) and id > ?";
        return queryForList(MessItem.class,sql,root,num);
    }

    @Override
    public List<Equipment> queryEquipmentList() {
        String sql = "select id,uid,name,model,project from table_equipment";
        return queryForList(Equipment.class,sql);
    }

    @Override
    public String[] queryUserNameAll() {
        String sql = "select username from table_user where root > 0";
        List<User> userList = queryForList(User.class,sql);
        String[] res = new String[userList.size()];
        for(int i = 0; i< userList.size();i++){
            res[i] = userList.get(i).getUsername();
        }
        return res;
    }

    @Override
    public String[] queryUserNameByRoot(int root) {
        String sql = "select username from table_user where root = ?";
        List<User> userList = queryForList(User.class,sql,root);
        String[] res = new String[userList.size()];
        for(int i = 0; i< userList.size();i++){
            res[i] = userList.get(i).getUsername();
        }
        return res;
    }


    @Override
    public int deleteUserByName(String name) {
        String sql = "delete from table_user where username = ?";
        return deleteForOne(sql,name);
    }

    @Override
    public Project queryProjectByuid(int uid) {
        String sql = "select uid,name,management,employee,equipment,startdate,enddate,address,longitude,latitude from table_project where uid = ?";
        return queryForone(Project.class,sql,uid);
    }

    @Override
    public Project queryProjectByName(String name) {
        String sql = "select uid,name,management,employee,equipment,startdate,enddate,address,longitude,latitude,financial from table_project where name = ?";
        return queryForone(Project.class,sql,name);
    }

    @Override
    public List<Project> queryProjectList() {
        String sql = "select uid,name,management,employee,equipment,startdate,address,longitude,latitude from table_project where enddate = 'null'";
        return queryForList(Project.class,sql);
    }
    @Override
    public Equipment queryEquipmentByUid(int uid) {
        String sql = "select id,uid,name,model,project from table_equipment where uid = ?";
        return queryForone(Equipment.class,sql,uid);
    }

    @Override
    public Equipment queryEquipmentByName(String name) {
        String sql = "select id,uid,name,model,project from table_equipment where name = ?";
        return queryForone(Equipment.class,sql,name);
    }

    @Override
    public int updateEquipmentProjectByName(String project,String name) {
        String sql = "update table_equipment set project = ? where name = ?";
        return update(sql,project,name);
    }

    @Override
    public String queryProjectEquipment(String name) {
        String sql = "select equipment from table_project where name = ?";
        Project project = queryForone(Project.class,sql,name);
        return project.getEquipment();
    }

    @Override
    public int updateProjectEquipment(String equipment, String name) {
        String sql = "update table_project set equipment = ? where name = ?";
        return update(sql,equipment,name);
    }

    @Override
    public int updateEquipmentProject(String project, int uid) {
        String sql = "update table_equipment set project = ? where uid = ?";
        return update(sql,project,uid);
    }

    @Override
    public int updateUserToken(String token,int id) {
        String sql = "update table_user set token = ? where id = ?";
        return update(sql,token,id);
    }

    @Override
    public int updateUserRoot(String name, int i) {
        String sql;
        switch (i) {
            case 0 : {
                sql = "update table_user set root = 1 where username = ?";
                break;
            }
            case 1 : {
                sql = "update table_user set root = 2 where username = ?";
                break;
            }
            case 2 : {
                sql = "update table_user set root = 0 where username = ?";
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + i);
        }
        return update(sql,name);
    }

    @Override
    public int updateMessItemIslook(int islook,int handler,int requester,int id) {
        String sql = "update table_mess set islook = ?,handler = ?,requester = ? where id = ?";
        return update(sql,islook,handler,requester,id);
    }

    @Override
    public int updateMessItemfeedback(String feedback,int handler,int requester,int id){
        String sql = "update table_mess set feedback = ?,handler = ?,requester = ? where id = ?";
        return update(sql,feedback,handler,requester,id);
    }

    @Override
    public List<User> queryTokenByRoot() {
        String sql = "select token from table_user where root = 1";
        return queryForList(User.class,sql);
    }

    @Override
    public ArrayList<String> queryTokenAll() {
        String sql = "select token from table_user";
        List<User> list = queryForList(User.class,sql);
        ArrayList<String> stringlist = new ArrayList<>();
        for (User user : list){
            if(user.getToken() != null) {
                stringlist.add(user.getToken());
            }
        }
        return stringlist;
    }

    @Override
    public String[] queryNameAll() {
        String sql = "select username from table_user";
        List<User> list = queryForList(User.class,sql);
        String[] strings = new String[list.size()];
        for (int i=0;i<list.size();i++){
            strings[i] = list.get(i).getUsername();
        }
        return strings;
    }

    @Override
    public List<String> queryNameByRoot(int root) {
        String sql = "select username from table_user where root = ?";
        List<User> userList = queryForList(User.class,sql,root);
        List<String> strings = new ArrayList<>();
        for (int i =0;i<userList.size();i++) {
            strings.add(userList.get(i).getUsername());
        }
        return strings;
    }

    @Override
    public int queryIdByName(String name) {
        String sql = "select id from table_user where username = ?";
        User user = queryForone(User.class,sql,name);
        return user.getId();
    }

    @Override
    public String queryTokenByName(String name) {
        String sql = "select token from table_user where username = ?";
        return queryForone(User.class,sql,name).getToken();
    }


    @Override
    public int queryMessNum() {
        String sql = "select  count(*) from table_mess";
        return queryNums(sql);
    }

    @Override
    public int queryProjectNum() {
        String sql = "select  count(*) from table_project";
        return queryNums(sql);
    }

    @Override
    public int queryFinancialNum() {
        String sql = "select  count(*) from table_financial";
        return queryNums(sql);
    }


}
