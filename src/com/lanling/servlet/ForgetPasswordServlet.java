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
		String email = req.getParameter("email");//�õ������˺�
		String password = req.getParameter("password");//�õ�����
		Connection connection = JDBCUtil.getConnection();//�õ����ݿ������
		Statement statement = null;
		PrintWriter out = resp.getWriter();//��android��д����
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from user where email = '"+email+"';");//�Ȳ��ҿ����ݿ����Ƿ��и������˺�
			if(rs.next()) {
				String username = rs.getString("username");//�õ��û���
				String photouser = "http://www.zhengzhoudaxue.cn:8080"+rs.getString("photo");
				int index = statement.executeUpdate("update user set password = '"+password+"' where username = '"+username+"';");
				if(index != 0) {
					out.write("0&"+username+"&"+photouser);//��������ɹ�
				}else {
					out.write("1");//����ʧ��
				}
			}else {
				out.print(2);//�˺Ų�����
			}
		} catch (SQLException e) {
			out.write("3");//����������������
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
