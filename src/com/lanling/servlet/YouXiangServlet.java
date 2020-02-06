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

import com.lanling.util.JDBCUtil;
import com.lanling.util.SendEmilUtil;
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
		HttpSession session = request.getSession();//��ȡHttpSession����
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String openid = request.getParameter("openid");//�õ�openid
		String email = request.getParameter("email");//�õ�email
		PrintWriter out = response.getWriter();//��ȡPrintWriter����
		System.out.println(openid+"   "+email);
		if(session.getAttribute("verification") == null) {
			String verification_code = "";
			Random ra =new Random();
            for (int i=0;i<6;i++){
                verification_code+=ra.nextInt(10);
            }
			String head = "������ʩ����Ϣ�ռ�app��������";
			String content = "���ã����ڡ�����ʩ����Ϣ�ռ�app����Ϊ����"+openid+"�����û�ID�󶨡�"+email+"���������˺ţ�\n\n���˴ε�������֤��Ϊ��\t��"+verification_code+"��\t\n\n"
					+ "���𽫴���֤������κ���";
			if(SendEmilUtil.sendEmail(email, head, content)) {
				session.setAttribute("verification", verification_code);
				request.getRequestDispatcher("/youxiang.jsp").forward(request, response);
			}else {
				out.write("������֤�뷢��ʧ�ܣ�������");
			}
		}else {
			String verification_yanzheng = request.getParameter("verification");
			if(verification_yanzheng.equals(session.getAttribute("verification"))) {
				//��֤����֤�ɹ�
				Connection connection = JDBCUtil.getConnection();//��ȡConnection����
				Statement statement;
				try {
					statement = connection.createStatement();//��ȡStatement����
					int index = statement.executeUpdate("update userqq set email='"+email+"'where openid = '"+openid+"';");
					if(index != 0) {
						//���³ɹ�
						out.write("������ɹ�����ϲ");
					}else {
						out.write("������ʧ�ܣ�������");
					}
				}catch (SQLException e) {
					out.write("������ʧ�ܣ�������");
					e.printStackTrace();
				}
			}else {
				out.write("��֤����֤ʧ�ܣ�������");
			}
		}
		
		
		
	}

}
