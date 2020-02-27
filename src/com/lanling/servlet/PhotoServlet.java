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

public class PhotoServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		Connection connection = JDBCUtil.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if("1".equals(type)) {
			String openid = request.getParameter("openid");
			String sql = "select photo from userqq where openid = '"+openid+"';";
			try {
				rs = statement.executeQuery(sql);
				if(rs.next()) {
					out.write(rs.getString("photo"));
				}else {
					out.write(0);
				}
			} catch (SQLException e) {
				out.write(0);
				e.printStackTrace();
			}finally {
				JDBCUtil.close(rs, statement, connection);
			}
		}else if("2".equals(type)) {
			String username = request.getParameter("username");
			String sql = "select photo from user where username = '"+username+"';";
			try {
				rs = statement.executeQuery(sql);
				if(rs.next()) {
					out.write(rs.getString("photo"));
				}else {
					out.write(0);
				}
			} catch (SQLException e) {
				out.write(0);
				e.printStackTrace();
			}finally {
				JDBCUtil.close(rs, statement, connection);
			}
		}
	}
}