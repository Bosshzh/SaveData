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
		String openid = request.getParameter("openid");//拿到openid
		PrintWriter out = response.getWriter();
		System.out.println(openid);
		Connection connection = JDBCUtil.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select email from userqq where openid = '"+openid+"';");
			if(rs.next()) {
				if(rs.getString("email") == null) {
					out.write("1");//没有绑定邮箱
				}else {
					out.write(rs.getString("email"));//已经绑定了邮箱
				}
			}
		} catch (SQLException e) {
			out.write("2");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, statement, connection);
		}
		
		
	}

}
