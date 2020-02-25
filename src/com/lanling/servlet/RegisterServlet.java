package com.lanling.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lanling.util.JDBCUtil;

public class RegisterServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection connection = JDBCUtil.getConnection();
		String username = req.getParameter("username");//获取用户账号
		String password = req.getParameter("password");//获取到密码
		String[] photos = {"/images/photo/xusong1.png","/images/photo/xusong2.jpg","/images/photo/xusong3.jpg",
				"/images/photo/xusong4.jpg","/images/photo/xusong5.jpeg","/images/photo/xusong6.jpg","/images/photo/xusong7.jpg"};
		String userphoto = photos[(int)(Math.random()*7)];
		Statement statement = null;
		PrintWriter out = resp.getWriter();
		if(connection != null) {
			try {
				statement = connection.createStatement();//创建statement
				ResultSet rs = statement.executeQuery("select * from user where username = '"+username+"';");
				int index = 0;
				if (!rs.next()) {
					//没有查找到，说明用户表中没有此用户
					index = statement.executeUpdate("insert into user(username,password,photo) values('"+username+"','"+password+"','"+userphoto+"');");//执行插入操作
				}
				if(index != 0) {
					out.write("0&http://www.zhengzhoudaxue.cn:8080"+userphoto);//注册成功
				}else {
					out.write("1");//注册失败，可能该账号已经被注册过了
				}
			} catch (SQLException e) {
				out.write("2");//注册失败
				e.printStackTrace();
			}finally {
				JDBCUtil.close(null, statement, connection);
			}
		}
	}
}