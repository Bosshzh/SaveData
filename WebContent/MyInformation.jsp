<%@page import="com.lanling.bean.User"%>
<%@page import="com.lanling.util.GetUploadData"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>我的资料</title>
<!-- meta使用viewport以确保页面可自由缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 引入 jQuery Mobile 样式 -->
<link rel="stylesheet" href="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">
<!-- 引入 jQuery 库 -->
<script src="http://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
<!-- 引入 jQuery Mobile 库 -->
<script src="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
</head>
<body>

	<%
		String username = request.getParameter("username");//拿到邮箱账号
		String openid = request.getParameter("openid");//拿到openid
		GetUploadData data = new GetUploadData();
		User user = data.getUser(username, openid);
		session.setAttribute("user", user);
	%>
	
	<div data-role="main" class="ui-content">
    	<table data-role="table" class="ui-responsive">
			<tbody>
				<thead>
					<tr>
						<th>头像</th>
						<th>用户账号</th>
						<th>昵称</th>
						<th>性别</th>
						<th>省份</th>
						<th>城市</th>
						<th>邮箱</th>
						<th>登录方式</th>
					</tr>
				</thead>
					<tr>
						<td><img width="100px" height="100px" src="<%=user.getPhoto() %>"></td>
						<td><%=user.getUsername() %></td>
						<td><%=user.getName() %></td>
						<td><%=user.getSex() %></td>
						<td><%=user.getProvince() %></td>
						<td><%=user.getCity() %></td>
						<td><%=user.getEmail() %></td>
						<td><%=user.isUsername()?"账号密码登录":"第三方QQ登录" %></td>
					</tr>
			</tbody>
		</table>
  	</div>
  	<div data-role="footer">
    	<h1>我的资料</h1>
  	</div>
	
</body>
</html>