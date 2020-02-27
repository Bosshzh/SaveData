package com.lanling.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.lanling.util.JDBCUtil;
import com.lanling.util.Util;

public class XiuGaiServlet extends HttpServlet {
	
	private String sql;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");//拿到用户名
		String openid = (String) session.getAttribute("openid");//拿到openid
		Connection connection = JDBCUtil.getConnection();//获取数据库的连接
		Statement statement = null;
		String name = request.getParameter("name");//获取用户填写的昵称
		String sex = request.getParameter("sex");//获取用户填写的性别
		String province = request.getParameter("province");//获取用户填写的省份
		String city = request.getParameter("city");//获取城市
		if(name != null) {
			sql = Util.isUsername(username, openid)?"update user set name = '"+name+"' where username = '"+username+"';":"update userqq set name = '"+name+"' where openid = '"+openid+"';";
		}else if(sex != null) {
			sql = Util.isUsername(username, openid)?"update user set sex = '"+sex+"' where username = '"+username+"';":"update userqq set sex = '"+sex+"' where openid = '"+openid+"';";
		}else if(province != null) {
			sql = Util.isUsername(username, openid)?"update user set province = '"+province+"' where username = '"+username+"';":"update userqq set province = '"+province+"' where openid = '"+openid+"';";
		}else if(city != null) {
			sql = Util.isUsername(username, openid)?"update user set city = '"+city+"' where username = '"+username+"';":"update userqq set city = '"+city+"' where openid = '"+openid+"';";
		}else {//如果以上都为空，那么就是图片
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024*2048);
			ServletFileUpload upload = new ServletFileUpload(factory);
			ArrayList<FileItem> fileitems;
			try {
				fileitems = (ArrayList<FileItem>) upload.parseRequest(request);
				FileItem fileItem = fileitems.get(0);
				String filename = fileItem.getName();
				InputStream is = fileItem.getInputStream();
				FileOutputStream fos = new FileOutputStream("/usr/images/photo/"+filename);
	            byte[] buff=new byte[1024];
	            int len=0;
	            while((len=is.read(buff))>0){
	                fos.write(buff,0,len);
	            }
	            fos.flush();
	            is.close();
	            fos.close();
	            sql = Util.isUsername(username, openid)?"update user set photo = '/images/photo/"+filename+"' where username = '"+username+"';":"update userqq set photo = '/images/photo/"+filename+"' where openid = '"+openid+"';";
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
		try {
			statement = connection.createStatement();
			if(sql != null) {
				statement.executeUpdate(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(null, statement, connection);
			response.sendRedirect("/SaveData/xiugai.jsp");
		}
	}
}

