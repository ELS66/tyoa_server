package com.els.tyoa.Servlet;

import com.els.tyoa.Dao.impl.UserDaoImal;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@WebServlet("/financial")
public class FinancialServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int is = Integer.parseInt(req.getHeader("is"));
		switch (is) {
			case 0 : {
				String name = MyUtil.decoder(req.getHeader("name"));
				String title = MyUtil.decoder(req.getHeader("title"));
				long money = Long.parseLong(req.getHeader("money"));
				String requester = MyUtil.decoder(req.getHeader("requester"));
				String project = MyUtil.decoder(req.getHeader("project"));
				Date time = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String date = dateFormat.format(time);
				UserDaoImal userDaoImal = new UserDaoImal();
				int res = userDaoImal.insertFinancial(name,project,date,money,requester,title);
				int num = userDaoImal.queryFinancialNum();
				System.out.println(res);
				String str = userDaoImal.queryProjectFinancial(project);
				String financial;
				Gson gson = new Gson();
				if (str.equals("null")) {
					List<Integer> list = new ArrayList<>();
					list.add(num);
					financial = gson.toJson(list);
				} else {
					List<Integer> list = gson.fromJson(str,new TypeToken<List<Integer>>(){}.getType());
					list.add(num);
					financial = gson.toJson(list);
				}
				userDaoImal.updateProjectFinancial(financial,project);
				resp.setCharacterEncoding("utf-8");
				resp.getWriter().append(String.valueOf(res));
				GetData.getData(req);
				break;
			}
		}
	}


}
