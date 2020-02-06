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
		String email = request.getParameter("email");//拿到邮箱账号
	%>

	<div data-role="main" class="ui-content">
		<form method="post" action="feedback" data-ajax="false">
			<input type="hidden" value="<%=email%>" name="email"/>
			<textarea rows="10" cols="20" name="content" placeholder="我们会在8个小时以内以邮件的方式反馈给您,请您尽量描述清楚"></textarea>
			<button type="submit" data-role="button">提交</button>
		</form>
	</div>

</body>
</html>