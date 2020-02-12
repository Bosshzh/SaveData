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
	private String verification_code = "";//验证码
       
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
		String email = request.getParameter("email");//拿到email
		String verification_code = request.getParameter("verification_code");//拿到验证码
		String type = request.getParameter("type");//判断是注册还是找回密码
		PrintWriter out = response.getWriter();
			String content = "";
			String head = "";
			if("0".equals(type)) {
				head = "【土壤施肥信息收集app】注册账号";
				content = "您好：您在【土壤施肥信息收集app】中使用"+email+"该邮箱注册账号，\n\n您此次注册的邮箱验证码为：\t【"+verification_code+"】\t\n\n"
						+ "请勿将此验证码告诉任何人";
			}else if("1".equals(type)) {
				head = "【土壤施肥信息收集app】找回密码";
				content = "您好：您在【土壤施肥信息收集app】中使用"+email+"该邮箱找回账号密码，\n\n您此次的邮箱验证码为：\t【"+verification_code+"】\t\n\n"
						+ "请勿将此验证码告诉任何人";
			}
			if(SendEmilUtil.sendEmail(email, head, content)) {
				out.write("4");//如果验证码发送成功,返回4
			}else {
				out.write("5");//如果验证码发送失败，返回5
			}
	}
}