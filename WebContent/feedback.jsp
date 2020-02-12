<%@page import="com.lanling.util.BandingUtil"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>用户反馈</title>
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
		String username = request.getParameter("username");//拿到用户账号
		String openid = request.getParameter("openid");//拿到openid
		String email = BandingUtil.isbangding(username, openid);//拿到邮箱
		if(email == null){//如果没有绑定邮箱的话，需要提醒用户
	%>
			<a href="youxiang.jsp?username=<%=username %>&openid=<%=openid %>"><h3>对不起，您未绑定邮箱，点此绑定邮箱账号</h3></a>
	<%	
		}else{
	%>
			<div data-role="main" class="ui-content">
				<form method="post" action="feedback" data-ajax="false">
					<input type="hidden" value="<%=username%>" name="username"/>
					<input type="hidden" value="<%=openid%>" name="openid"/>
					<input type="hidden" value="<%=email%>" name="email"/>
					<textarea rows="10" cols="20" name="content" placeholder="我们会在8个小时以内以邮件的方式反馈给您,请您尽量描述清楚"></textarea>
					<button type="submit" data-role="button">提交</button>
				</form>
			</div>
	<%
		}
		
		
	%>

	

</body>
</html>