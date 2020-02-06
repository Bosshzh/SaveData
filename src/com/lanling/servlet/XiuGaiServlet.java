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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.lanling.util.JDBCUtil;

public class XiuGaiServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String email = (String) request.getSession().getAttribute("email");
		String name = request.getParameter("name");//昵称
		String sex = request.getParameter("sex");//性别
		String province = request.getParameter("province");//省份
		String city = request.getParameter("city");//城市
		Connection connection = JDBCUtil.getConnection();//获取Connection对象
		Statement statement = null;
		Statement statement2 = null;
		PrintWriter out = response.getWriter();
		try {
			statement = connection.createStatement();
			int index = 0;
			if(name != null) {//修改昵称
				index = statement.executeUpdate("update user set name = '"+name+"' where email = '"+email+"';");
			}else if(sex != null) {//修改性别
				index = statement.executeUpdate("update user set sex = '"+sex+"' where email = '"+email+"';");
			}else if(province != null){
				index = statement.executeUpdate("update user set province = '"+province+"' where email = '"+email+"';");
			}else if(city != null){
				index = statement.executeUpdate("update user set city = '"+city+"' where email = '"+email+"';");
			}else{//修改头像
				statement2 = connection.createStatement();
				ResultSet rs = null;
				rs = statement2.executeQuery("select photo from user where email = '"+email+"';");
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
//	            	String fileName = fileItem.getName();
//                    // 获取文件名后缀, 返回 "."在文件名最后出现的索引, 就是文件后缀名
//                    String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
//                    // 存储的文件名根据获取的id来唯一确定, 这里测试使用 "test"
//                    // id可以绑定到session或request变量等等，自己根据需要来扩展
//                    String fileSaveName = email + "." + prefix; // id.后缀
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
                    pre=connection.prepareStatement("update user set photo = ? where email = '"+email+"';");
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
//		request.getRequestDispatcher("xiugai.jsp").forward(request, response);
		response.sendRedirect("xiugai.jsp");
	}

}
