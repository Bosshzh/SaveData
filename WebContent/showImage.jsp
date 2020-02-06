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
<title>展示图片</title>
</head>
<body>

	<%
		try{
			int type = Integer.parseInt(request.getParameter("type"));//拿到类型
			int index = Integer.parseInt(request.getParameter("index"));//拿到第几个
			UploadData uploadData = ((ArrayList<UploadData>)session.getAttribute("lists")).get(index);
			Blob blob = null;
			long size = 0l;
			byte[] bs = null;
			OutputStream outs;
			if(type == 1){//土地景观图1
				blob = uploadData.getLand_image1();//土地景观图1
				size = blob.length();
				bs = blob.getBytes(1, (int)size);
			}else if(type == 2){//土地景观图2
				blob = uploadData.getLand_image2();//土地景观图2
				size = blob.length();
				bs = blob.getBytes(1, (int)size);
			}else if(type == 3){//现场访谈图
				blob = uploadData.getInterview_image();//现场访谈图
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
			//System.out.println("这里有异常");
		}
	%>

</body>
</html>