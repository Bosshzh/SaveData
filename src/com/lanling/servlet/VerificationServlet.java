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
	private String verification_code = "";//��֤��
       
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
		String email = request.getParameter("email");//�õ�email
		String verification_code = request.getParameter("verification_code");//�õ���֤��
		String type = request.getParameter("type");//�ж���ע�ỹ���һ�����
		PrintWriter out = response.getWriter();
			String content = "";
			String head = "";
			if("0".equals(type)) {
				head = "������ʩ����Ϣ�ռ�app��ע���˺�";
				content = "���ã����ڡ�����ʩ����Ϣ�ռ�app����ʹ��"+email+"������ע���˺ţ�\n\n���˴�ע���������֤��Ϊ��\t��"+verification_code+"��\t\n\n"
						+ "���𽫴���֤������κ���";
			}else if("1".equals(type)) {
				head = "������ʩ����Ϣ�ռ�app���һ�����";
				content = "���ã����ڡ�����ʩ����Ϣ�ռ�app����ʹ��"+email+"�������һ��˺����룬\n\n���˴ε�������֤��Ϊ��\t��"+verification_code+"��\t\n\n"
						+ "���𽫴���֤������κ���";
			}
			if(SendEmilUtil.sendEmail(email, head, content)) {
				out.write("4");//�����֤�뷢�ͳɹ�,����4
			}else {
				out.write("5");//�����֤�뷢��ʧ�ܣ�����5
			}
	}
}