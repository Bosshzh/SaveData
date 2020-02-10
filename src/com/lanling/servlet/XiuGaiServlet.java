package com.lanling.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");//从session获取到用户编号
		String openid = (String) session.getAttribute("openid");//从session中获取到openid
		String name = request.getParameter("name");//昵称
		String sex = request.getParameter("sex");//性别
		String province = request.getParameter("province");//省份
		String city = request.getParameter("city");//城市
		Connection connection = JDBCUtil.getConnection();//获取Connection对象
		Statement statement = null;
		Statement statement2 = null;
		try {
			statement = connection.createStatement();
			if(name != null) {//修改昵称
				String sql = Util.isUsername(username, openid)?"update user set name = '"+name+"' where username = '"+username+"';":"update userqq set name = '"+name+"' where openid = '"+openid+"';";
				statement.executeUpdate(sql);
			}else if(sex != null) {//修改性别
				String sql = Util.isUsername(username, openid)?"update user set sex = '"+sex+"' where username = '"+username+"';":"update userqq set sex = '"+sex+"' where openid = '"+openid+"';";
				statement.executeUpdate(sql);
			}else if(province != null){
				String sql = Util.isUsername(username, openid)?"update user set province = '"+province+"' where username = '"+username+"';":"update userqq set province = '"+province+"' where openid = '"+openid+"';";
				statement.executeUpdate(sql);
			}else if(city != null){
				String sql = Util.isUsername(username, openid)?"update user set city = '"+city+"' where username = '"+username+"';":"update userqq set city = '"+city+"' where openid = '"+openid+"';";
				statement.executeUpdate(sql);
			}else{//修改头像
				statement2 = connection.createStatement();
				ResultSet rs = null;
				rs = statement2.executeQuery(Util.isUsername(username, openid)?"select photo from user where username = '"+username+"';":"select photo from userqq where openid = '"+openid+"';");
				String photo = "";
				if(rs.next()) {
					photo = rs.getString("photo");
				}
				DiskFileItemFactory factory = new DiskFileItemFactory();
		        ServletFileUpload sfu = new ServletFileUpload(factory);
		        sfu.setHeaderEncoding("UTF-8");//设置字符
		        sfu.setSizeMax(1024*1024);//上传文件最大1m
		        PreparedStatement pre=null;
	            List<FileItem> items = null;
				try {
					items = sfu.parseRequest(request);
				} catch (FileUploadException e) {
					e.printStackTrace();
				}//获取上传的文件名
	            for(FileItem fileItem:items){//将上传的所有文件保存到mysql数据库          
//		                 
//		            	String fileName = fileItem.getName();
//	                    // 获取文件名后缀, 返回 "."在文件名最后出现的索引, 就是文件后缀名
//	                    String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
//	                    // 存储的文件名根据获取的id来唯一确定, 这里测试使用 "test"
//	                    // id可以绑定到session或request变量等等，自己根据需要来扩展
//	                    String fileSaveName = email + "." + prefix; // id.后缀
                    // 获取文件输入流
                    InputStream inputStream = fileItem.getInputStream();
                    // 创建文件输出流，用于向指定文件名的文件写入数据
                    FileOutputStream fileOutputStream = new FileOutputStream("/var"+photo);
                    // 从输入流读取数据的下一个字节，到末尾时返回 -1
                    byte[] b = new byte[1024];
                    int length = 0;
                    while ((length = inputStream.read(b)) != -1) {
                        fileOutputStream.write(b,0,length);  // 将指定字节写入此文件输出流
                    }
                    fileOutputStream.flush();
                    // 关闭流
                    inputStream.close();
                    fileOutputStream.close();
                    fileItem.delete();
                    pre=connection.prepareStatement(Util.isUsername(username, openid)?"update user set photo = ? where username = '"+username+"';":"update userqq set photo = ? where openid = '"+openid+"';");
	                pre.setString(1,photo);
	                pre.executeUpdate();
	                if(rs != null) {
	                	rs.close();
	                }
	                if(statement2 != null) {
	                	statement2.close();
	                }
	            }
	        	if(pre != null) {
	        		pre.close();
	        	}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(null, statement, connection);
		}
		response.sendRedirect("xiugai.jsp");
	}

}
