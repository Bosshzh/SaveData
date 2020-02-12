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

public class ForgetPasswordServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");//拿到邮箱账号
		String password = req.getParameter("password");//拿到密码
		Connection connection = JDBCUtil.getConnection();//拿到数据库的连接
		Statement statement = null;
		PrintWriter out = resp.getWriter();//向android端写数据
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from user where email = '"+email+"';");//先查找看数据库中是否有该邮箱账号
			if(rs.next()) {
				String username = rs.getString("username");//拿到用户名
				String photouser = "http://www.zhengzhoudaxue.cn:8080"+rs.getString("photo");
				int index = statement.executeUpdate("update user set password = '"+password+"' where username = '"+username+"';");
				if(index != 0) {
					out.write("0&"+username+"&"+photouser);//更改密码成功
				}else {
					out.write("1");//插入失败
				}
			}else {
				out.print(2);//账号不存在
			}
		} catch (SQLException e) {
			out.write("3");//服务器报错，请重试
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
