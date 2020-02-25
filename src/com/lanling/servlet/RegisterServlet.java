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
		String username = req.getParameter("username");//��ȡ�û��˺�
		String password = req.getParameter("password");//��ȡ������
		String[] photos = {"/images/photo/xusong1.png","/images/photo/xusong2.jpg","/images/photo/xusong3.jpg",
				"/images/photo/xusong4.jpg","/images/photo/xusong5.jpeg","/images/photo/xusong6.jpg","/images/photo/xusong7.jpg"};
		String userphoto = photos[(int)(Math.random()*7)];
		Statement statement = null;
		PrintWriter out = resp.getWriter();
		if(connection != null) {
			try {
				statement = connection.createStatement();//����statement
				ResultSet rs = statement.executeQuery("select * from user where username = '"+username+"';");
				int index = 0;
				if (!rs.next()) {
					//û�в��ҵ���˵���û�����û�д��û�
					index = statement.executeUpdate("insert into user(username,password,photo) values('"+username+"','"+password+"','"+userphoto+"');");//ִ�в������
				}
				if(index != 0) {
					out.write("0&http://www.zhengzhoudaxue.cn:8080"+userphoto);//ע��ɹ�
				}else {
					out.write("1");//ע��ʧ�ܣ����ܸ��˺��Ѿ���ע�����
				}
			} catch (SQLException e) {
				out.write("2");//ע��ʧ��
				e.printStackTrace();
			}finally {
				JDBCUtil.close(null, statement, connection);
			}
		}
	}
}