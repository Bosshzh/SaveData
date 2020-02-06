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

public class LoginServlet extends HttpServlet {
	
	private String userid;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");//�õ��û��˺�
		String password = req.getParameter("password");//�õ�����
		Connection connection = JDBCUtil.getConnection();//�õ����ݿ������
		PrintWriter out = resp.getWriter();//�õ�PrintWriter����
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from user where username = '"+username+"';");
			if(rs.next()) {
				if(rs.getString("password").equals(password)) {
					out.write("0&http://www.zhengzhoudaxue.cn:8080"+rs.getString("photo"));//��¼�ɹ�
				}else {
					out.write("1");//�������
				}
			}else {
				//û�и������˺�
				out.write("2");
			}
		} catch (SQLException e2) {
			out.write("3");//��¼����������
			e2.printStackTrace();
		}finally {
			JDBCUtil.close(rs, statement, connection);
		}
	}
}