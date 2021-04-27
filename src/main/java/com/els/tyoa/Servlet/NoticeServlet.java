package com.els.tyoa.Servlet;

import com.els.tyoa.Dao.impl.UserDaoImal;
import com.els.tyoa.Util.GetData;
import com.els.tyoa.Util.MyUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/notice")
public class NoticeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int is = Integer.parseInt(req.getHeader("is"));
        UserDaoImal userDaoImal = new UserDaoImal();
        switch (is) {
            case 0 : {
                String mess = MyUtil.decoder(req.getHeader("mess"));
                String name = MyUtil.decoder(req.getHeader("name"));
                int res = userDaoImal.insertNoticeMess(mess,name);
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(String.valueOf(res));
                GetData.getData(req);
                break;
            }
            case 1 : {
                String mess = userDaoImal.getNotice();
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().append(mess);
                GetData.getData(req);
                break;
            }
        }
    }
}
