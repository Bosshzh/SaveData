package com.lanling.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanling.util.SendEmilUtil;

/**
 * Servlet implementation class GetVericationCodeServlet
 */
@WebServlet("/GetVericationCodeServlet")
public class VerificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerificationServlet() {
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
		String email = request.getParameter("email");//拿到邮箱账号
		String verification_code = request.getParameter("verification_code");//拿到验证码
		PrintWriter out = response.getWriter();
			String head = "【土壤施肥信息收集app】找回密码";
			String content = "您好：您在【土壤施肥信息收集app】中使用"+email+"该邮箱找回账号密码，\n\n您此次的邮箱验证码为： 【"+verification_code+"】\t\n\n"
					+ "请勿将此验证码告诉任何人";
			if(SendEmilUtil.sendEmail(email, head, content)) {
				out.write("4");
			}else {
				out.write("5");
			}
	}
}