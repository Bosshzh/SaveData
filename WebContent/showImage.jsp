<%@ page contentType="text/html; charset=gbk" %>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*, javax.sql.*" %>
<%@ page import="java.util.*"%>
<%@ page import="java.math.*"%>
<%@ page import="com.lanling.bean.UploadData" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>չʾͼƬ</title>
</head>
<body>

	<%
		try{
			int type = Integer.parseInt(request.getParameter("type"));//�õ�����
			int index = Integer.parseInt(request.getParameter("index"));//�õ��ڼ���
			UploadData uploadData = ((ArrayList<UploadData>)session.getAttribute("lists")).get(index);
			Blob blob = null;
			long size = 0l;
			byte[] bs = null;
			OutputStream outs;
			if(type == 1){//���ؾ���ͼ1
				blob = uploadData.getLand_image1();//���ؾ���ͼ1
				size = blob.length();
				bs = blob.getBytes(1, (int)size);
			}else if(type == 2){//���ؾ���ͼ2
				blob = uploadData.getLand_image2();//���ؾ���ͼ2
				size = blob.length();
				bs = blob.getBytes(1, (int)size);
			}else if(type == 3){//�ֳ���̸ͼ
				blob = uploadData.getInterview_image();//�ֳ���̸ͼ
				size = blob.length();
				bs = blob.getBytes(1, (int)size);
			}
			response.setContentType("image/jpeg");
			outs = response.getOutputStream();
			outs.write(bs);
			outs.flush();
			out.clear(); 
			out = pageContext.pushBody();
		}catch(Exception e){
			response.sendRedirect("images/error.jpg");
			//System.out.println("�������쳣");
		}
	%>

</body>
</html>