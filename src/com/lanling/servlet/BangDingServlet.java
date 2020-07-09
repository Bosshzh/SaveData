package com.lanling.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanling.util.BandingUtil;
import com.lanling.util.JDBCUtil;
import com.lanling.util.Util;

public class BangDingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    public BangDingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");//�õ��û����
		String openid = request.getParameter("openid");//�õ�openid
		PrintWriter out = response.getWriter();
		Connection connection = JDBCUtil.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			String sql = Util.isUsername(username, openid)?"select email from user where username = '"+username+"';":"select email from userqq where openid = '"+openid+"';";
			rs = statement.executeQuery(sql);
			if(rs.next()) {
				if(rs.getString("email").equals("δ֪")) {
					out.write("1");//û�а�����
				}else {
					out.write(rs.getString("email"));//�Ѿ���������
				}
			}
		} catch (SQLException e) {
			out.write("2");//����
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, statement, connection);
		}
		
		
	}

}
