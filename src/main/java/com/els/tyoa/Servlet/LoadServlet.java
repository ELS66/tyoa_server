package com.els.tyoa.Servlet;

import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

import com.els.tyoa.Util.GetData;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/load")
public class LoadServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	public void destroy() {
		super.destroy();
	}


	@SuppressWarnings("deprecation")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ELSELS");
		req.setCharacterEncoding("utf-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletContext context = this.getServletContext();
		String path = context.getRealPath("/upload");
		File file=new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		factory.setRepository(new File(path));
		//设置 缓存的大小
		factory.setSizeThreshold(1024*1024) ;
		//文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			//可以上传多个文件
			List<FileItem> list = (List<FileItem>)upload.parseRequest(req);
			for(FileItem item : list){
				//获取属性名字
				String name = item.getFieldName();
				//如果获取的 表单信息是普通的 文本 信息
				if(item.isFormField()){
					//获取用户具体输入的字符串,因为表单提交过来的是 字符串类型的
					String value = item.getString() ;
					req.setAttribute(name, value);
				}else{
					//获取路径名
					String value = item.getName() ;
					System.out.println(value);
					//索引到最后一个反斜杠
					int start = value.lastIndexOf("\\");
					//截取 上传文件的 字符串名字，加1是 去掉反斜杠，
					String filename = value.substring(start+1);
					System.out.println(filename);
					req.setAttribute(name, filename);
					//写到磁盘上
					//item.write( new File(path,filename) );//第三方提供的
					//System.out.println(filename);
					//resp.setCharacterEncoding("utf-8");
					//resp.getWriter().print(filename);//将路径返回给客户端
					//GetData.getData(req);
					item.write(new File(path,filename) );//第三方提供的
					System.out.println("上传成功："+filename);
					resp.setCharacterEncoding("utf-8");
					resp.getWriter().print(filename);//将路径返回给客户端
				}
			}

		} catch (Exception e) {
			resp.getWriter().print("上传失败："+e.getMessage());
		}
	}


}
