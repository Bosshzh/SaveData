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

public class LoginQQServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String openid = request.getParameter("openid");//用户ID
		PrintWriter out = response.getWriter();
		Connection connection = JDBCUtil.getConnection();//获取数据库的连接
		Statement statement1 = null;
		ResultSet rs = null;
		Statement statement2 = null;
		try {
			statement1 = connection.createStatement();
			statement2 = connection.createStatement();
			rs = statement1.executeQuery("select * from userqq where openid = '"+openid+"';");
			String name = request.getParameter("name");//用户昵称
			String sex = request.getParameter("sex");//用户性别
			String province = request.getParameter("province");//省份
			String city = request.getParameter("city");//城市
			String photo = request.getParameter("photo");//头像路径
			if(rs.next()) {
				int index = statement2.executeUpdate("update userqq set name = '"+name+"',sex = '"+sex+"',province = '"+province+"',city = '"+city+"',photo = '"+photo+"' where openid = '"+openid+"';");
				if(index != 0) {
					out.write("0");//登录成功
				}else {
					out.write("3");//登录失败
				}
			}else {
				int index = statement2.executeUpdate("insert into userqq(openid,name,sex,province,city,photo) values('"+openid+"','"+name+"','"+sex+"','"+province+"','"+city+"','"+photo+"');");
				if(index != 0) {
					out.write("0");//登录成功
				}else {
					out.write("3");//登录失败
				}
			}
		} catch (SQLException e) {
			out.write("3");//登录失败
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(statement2 != null) {
				try {
					statement2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(statement1 != null) {
				try {
					statement1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

}
