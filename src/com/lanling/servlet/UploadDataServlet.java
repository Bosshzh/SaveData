package com.lanling.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.lanling.util.JDBCUtil;
import java.sql.Statement;

/**
 * 用来接收用户上传过来的发数据
 * @author Administrator
 *
 */
public class UploadDataServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Connection connection = JDBCUtil.getConnection();//获取数据库的连接
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024*2048);//文件大小不能超过2M
		ServletFileUpload upload = new ServletFileUpload(factory);
		int index = 0;
		try {
			ArrayList<FileItem> fileitems = (ArrayList<FileItem>) upload.parseRequest(request);
			for(FileItem fileItem : fileitems) {//如果是普通的参数的话
				if(fileItem.isFormField()) {
					String sql = URLDecoder.decode(fileItem.getString(),"utf-8");//拿到值
					out.println("sql语句为："+sql);
					try {
						index = statement.executeUpdate(sql);//直接执行插入语句
					} catch (SQLException e) {
						out.println("插入sql语句出错  ："+e.getMessage());
					}
				}else {//如果是文件
					String filename = fileItem.getName();//文件名
					InputStream is = fileItem.getInputStream();
					FileOutputStream ous = new FileOutputStream("/usr/images/uploaddata/"+filename);
					byte[] buff = new byte[1024];
					int len = 0;
					while((len = is.read(buff)) > 0) {
						ous.write(buff, 0, len);
					}
					ous.flush();
					is.close();
					ous.close();
				}
			}
		} catch (FileUploadException e) {
			out.println("获取ArrayList文件集合出错  ： "+e.getMessage());
		}finally {
			JDBCUtil.close(null, statement, connection);
		}
		if(index != 0) {
			out.write(1);//上传成功
		}else {
			out.write(2);//上传失败
		}
	}
}