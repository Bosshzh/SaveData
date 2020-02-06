package com.lanling.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.lanling.util.JDBCUtil;

public class XiuGaiServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String email = (String) request.getSession().getAttribute("email");
		String name = request.getParameter("name");//�ǳ�
		String sex = request.getParameter("sex");//�Ա�
		String province = request.getParameter("province");//ʡ��
		String city = request.getParameter("city");//����
		Connection connection = JDBCUtil.getConnection();//��ȡConnection����
		Statement statement = null;
		Statement statement2 = null;
		PrintWriter out = response.getWriter();
		try {
			statement = connection.createStatement();
			int index = 0;
			if(name != null) {//�޸��ǳ�
				index = statement.executeUpdate("update user set name = '"+name+"' where email = '"+email+"';");
			}else if(sex != null) {//�޸��Ա�
				index = statement.executeUpdate("update user set sex = '"+sex+"' where email = '"+email+"';");
			}else if(province != null){
				index = statement.executeUpdate("update user set province = '"+province+"' where email = '"+email+"';");
			}else if(city != null){
				index = statement.executeUpdate("update user set city = '"+city+"' where email = '"+email+"';");
			}else{//�޸�ͷ��
				statement2 = connection.createStatement();
				ResultSet rs = null;
				rs = statement2.executeQuery("select photo from user where email = '"+email+"';");
				String photo = "";
				if(rs.next()) {
					photo = rs.getString("photo");
				}
				DiskFileItemFactory factory = new DiskFileItemFactory();
		        ServletFileUpload sfu = new ServletFileUpload(factory);
		        sfu.setHeaderEncoding("UTF-8");//�����ַ�
		        sfu.setSizeMax(1024*1024);//�ϴ��ļ����1m
		        PreparedStatement pre=null;
	            List<FileItem> items = null;
				try {
					items = sfu.parseRequest(request);
				} catch (FileUploadException e) {
					e.printStackTrace();
				}//��ȡ�ϴ����ļ���
	            for(FileItem fileItem:items){//���ϴ��������ļ����浽mysql���ݿ�          
//	                 
//	            	String fileName = fileItem.getName();
//                    // ��ȡ�ļ�����׺, ���� "."���ļ��������ֵ�����, �����ļ���׺��
//                    String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
//                    // �洢���ļ������ݻ�ȡ��id��Ψһȷ��, �������ʹ�� "test"
//                    // id���԰󶨵�session��request�����ȵȣ��Լ�������Ҫ����չ
//                    String fileSaveName = email + "." + prefix; // id.��׺
                    // ��ȡ�ļ�������
                    InputStream inputStream = fileItem.getInputStream();
                    // �����ļ��������������ָ���ļ������ļ�д������
                    FileOutputStream fileOutputStream = new FileOutputStream("/var"+photo);
                    // ����������ȡ���ݵ���һ���ֽڣ���ĩβʱ���� -1
                    byte[] b = new byte[1024];
                    int length = 0;
                    while ((length = inputStream.read(b)) != -1) {
                        fileOutputStream.write(b,0,length);  // ��ָ���ֽ�д����ļ������
                    }
                    fileOutputStream.flush();
                    // �ر���
                    inputStream.close();
                    fileOutputStream.close();
                    fileItem.delete();
                    pre=connection.prepareStatement("update user set photo = ? where email = '"+email+"';");
	                pre.setString(1,photo);
	                pre.executeUpdate();
	                if(rs != null) {
	                	rs.close();
	                }
	                if(statement2 != null) {
	                	statement2.close();
	                }
	            }
	        	if(pre != null) {
	        		pre.close();
	        	}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(null, statement, connection);
		}
//		request.getRequestDispatcher("xiugai.jsp").forward(request, response);
		response.sendRedirect("xiugai.jsp");
	}

}
