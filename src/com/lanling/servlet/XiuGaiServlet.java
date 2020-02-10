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
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.lanling.util.JDBCUtil;
import com.lanling.util.Util;

public class XiuGaiServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");//��session��ȡ���û����
		String openid = (String) session.getAttribute("openid");//��session�л�ȡ��openid
		String name = request.getParameter("name");//�ǳ�
		String sex = request.getParameter("sex");//�Ա�
		String province = request.getParameter("province");//ʡ��
		String city = request.getParameter("city");//����
		Connection connection = JDBCUtil.getConnection();//��ȡConnection����
		Statement statement = null;
		Statement statement2 = null;
		try {
			statement = connection.createStatement();
			if(name != null) {//�޸��ǳ�
				String sql = Util.isUsername(username, openid)?"update user set name = '"+name+"' where username = '"+username+"';":"update userqq set name = '"+name+"' where openid = '"+openid+"';";
				statement.executeUpdate(sql);
			}else if(sex != null) {//�޸��Ա�
				String sql = Util.isUsername(username, openid)?"update user set sex = '"+sex+"' where username = '"+username+"';":"update userqq set sex = '"+sex+"' where openid = '"+openid+"';";
				statement.executeUpdate(sql);
			}else if(province != null){
				String sql = Util.isUsername(username, openid)?"update user set province = '"+province+"' where username = '"+username+"';":"update userqq set province = '"+province+"' where openid = '"+openid+"';";
				statement.executeUpdate(sql);
			}else if(city != null){
				String sql = Util.isUsername(username, openid)?"update user set city = '"+city+"' where username = '"+username+"';":"update userqq set city = '"+city+"' where openid = '"+openid+"';";
				statement.executeUpdate(sql);
			}else{//�޸�ͷ��
				statement2 = connection.createStatement();
				ResultSet rs = null;
				rs = statement2.executeQuery(Util.isUsername(username, openid)?"select photo from user where username = '"+username+"';":"select photo from userqq where openid = '"+openid+"';");
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
//		            	String fileName = fileItem.getName();
//	                    // ��ȡ�ļ�����׺, ���� "."���ļ��������ֵ�����, �����ļ���׺��
//	                    String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
//	                    // �洢���ļ������ݻ�ȡ��id��Ψһȷ��, �������ʹ�� "test"
//	                    // id���԰󶨵�session��request�����ȵȣ��Լ�������Ҫ����չ
//	                    String fileSaveName = email + "." + prefix; // id.��׺
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
                    pre=connection.prepareStatement(Util.isUsername(username, openid)?"update user set photo = ? where username = '"+username+"';":"update userqq set photo = ? where openid = '"+openid+"';");
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
		response.sendRedirect("xiugai.jsp");
	}

}
