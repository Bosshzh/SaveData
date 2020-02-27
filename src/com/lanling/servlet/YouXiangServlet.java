package com.lanling.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lanling.util.BandingUtil;
import com.lanling.util.JDBCUtil;
import com.lanling.util.SendEmilUtil;
import com.lanling.util.Util;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * Servlet implementation class YouXiangServlet
 */
@WebServlet("/YouXiangServlet")
public class YouXiangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YouXiangServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();//获取session
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");//获取username
		String openid = request.getParameter("openid");//获取openid
		String email = request.getParameter("email");//获取email
		PrintWriter out = response.getWriter();//获取PrintWriter对象
		String id = Util.isUsername(username, openid)?username:openid;
		if(session.getAttribute("verification") == null) {
			//如果验证码为空的话，说明是第一次需要发送验证码
			String verification_code = "";
			Random ra =new Random();
            for (int i=0;i<6;i++){
                verification_code+=ra.nextInt(10);
            }
			String head = "【土壤施肥信息收集app】绑定邮箱";
			String content = "您好：您在【土壤施肥信息收集app】中为账号为：【"+id+"】的用户绑定【"+email+"】该邮箱账号，\n\n您此次的邮箱验证码为： 【"+verification_code+"】\t\n\n"
					+ "请勿将此验证码告诉任何人";
			if(SendEmilUtil.sendEmail(email, head, content)) {
				session.setAttribute("verification", verification_code);//验证码存入session中
				request.getRequestDispatcher("/youxiang.jsp").forward(request, response);
			}else {
				out.write("<h2>验证码发送失败</h2>");
			}
		}else {
			String verification_yanzheng = request.getParameter("verification");
			if(verification_yanzheng.equals(session.getAttribute("verification"))) {//用来对比session中的验证码
				session.removeAttribute("verification");//移除session中的验证码
				Connection connection = JDBCUtil.getConnection();//获取Connection对象
				Statement statement;
				try {
					statement = connection.createStatement();//获取statement对象
					int index = 0;
					String sql = Util.isUsername(username, openid)?"update user set email = '"+email+"' where username = '"+username+"';":"update userqq set email = '"+email+"'where openid = '"+openid+"';";
					index = statement.executeUpdate(sql);
					if(index == 0) {//����ʧ��
						out.write("<h2>邮箱绑定失败，<a href='/SaveData/youxiang.jsp?username="+username+"&openid="+openid+"'>请重试</a></h2>");
					}else {
						out.write("<h2>恭喜，绑定邮箱成功！<a href='/SaveData/feedback.jsp?username="+username+"&openid="+openid+"'>进入反馈界面！</a></h2>");
					}
				}catch (SQLException e) {
					out.write("<h2>邮箱绑定失败，服务器出问题了！<a href='/SaveData/youxiang.jsp?username="+username+"&openid="+openid+"'>请重试</a></h2>\"</h2>");
				}finally {
					
				}
			}else {
				session.removeAttribute("verification");//移除验证码
				out.write("<h2>验证码验证错误，<a href='/SaveData/youxiang.jsp?username="+username+"&openid="+openid+"'>请重试</a></h2>");
			}
		}
		
		
		
	}

}
