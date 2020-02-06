package com.lanling.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lanling.util.JDBCUtil;
import com.lanling.util.SendEmilUtil;

/**
 * Servlet implementation class FeedBackServlet
 */
@WebServlet("/FeedBackServlet")
public class FeedBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private PreparedStatement ps;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedBackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String content = request.getParameter("content");
		System.out.println("用户email为："+email+"，用户输入内容为："+content);
		Date date = new Date(System.currentTimeMillis());
		connection = JDBCUtil.getConnection();
		try {
			ps = connection.prepareStatement("insert into feedback(email,content,time) values(?,?,?)");
			ps.setString(1, email);
			ps.setString(2, content);
			ps.setDate(3, date);
			int index = ps.executeUpdate();
			if(index != 0) {
				if(SendEmilUtil.sendEmail("1757741394@qq.com","【土壤施肥信息收集app】用户反馈", "邮箱账户为："+email+"的用户向您发来用户反馈：\n\n【"+content+"】\n\n,请及时回复该用户")) {
					response.sendRedirect("/SaveData/success.jsp");
				}else {
					response.sendRedirect("/SaveData/error.jsp");
				}
			}
		} catch (SQLException e) {
			out.write("反馈出现了问题：email为："+email);
			e.printStackTrace();
		}
	}
}