package com.lanling.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
		ServletFileUpload upload = new ServletFileUpload();
		int index = 0;
		try {
			ArrayList<FileItem> fileitems = (ArrayList<FileItem>) upload.parseRequest(request);
			for(FileItem fileItem : fileitems) {//如果是普通的参数的话
				if(fileItem.isFormField()) {
					String sql = fileItem.getString();//拿到值
					try {
						index = statement.executeUpdate(sql);//直接执行插入语句
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}else {//如果是文件
					String value = fileItem.getName();
					int start = value.lastIndexOf("/");
					String filename = value.substring(start+1);
					File file = new File("/images/uploaddata/"+filename);
					try {
						fileItem.write(file);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
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