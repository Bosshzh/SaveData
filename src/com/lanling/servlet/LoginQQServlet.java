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
		String openid = request.getParameter("openid");//�û�ID
		PrintWriter out = response.getWriter();
		Connection connection = JDBCUtil.getConnection();//��ȡ���ݿ������
		Statement statement1 = null;
		ResultSet rs = null;
		Statement statement2 = null;
		try {
			statement1 = connection.createStatement();
			statement2 = connection.createStatement();
			rs = statement1.executeQuery("select * from userqq where openid = '"+openid+"';");
			String name = request.getParameter("name");//�û��ǳ�
			String sex = request.getParameter("sex");//�û��Ա�
			String province = request.getParameter("province");//ʡ��
			String city = request.getParameter("city");//����
			String photo = request.getParameter("photo");//ͷ��·��
			if(rs.next()) {//如果查询到了的话
				out.write("0&http://www.zhengzhoudaxue.cn:8080"+rs.getString("photo"));
			}else {//如果没有查询到
				int index = statement2.executeUpdate("insert into userqq(openid,name,sex,province,city,photo) values('"+openid+"','"+name+"','"+sex+"','"+province+"','"+city+"','"+photo+"');");
				if(index != 0) {
					out.write("0");//��¼�ɹ�
				}else {
					out.write("3");//��¼ʧ��
				}
			}
		} catch (SQLException e) {
			out.write("3");//��¼ʧ��
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
