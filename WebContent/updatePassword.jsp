<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>修改密码</title>
<!-- meta使用viewport以确保页面可自由缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 引入 jQuery Mobile 样式 -->
<link rel="stylesheet" href="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">
<!-- 引入 jQuery 库 -->
<script src="http://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
<!-- 引入 jQuery Mobile 库 -->
<script src="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<script type="text/javascript" src="js/updatePassword.js"></script>
</head>
<body>
	<div data-role="page">
  <div data-role="main" class="ui-content">
  
  <%
  	String email_test = request.getParameter("email");
  	if(email_test == null){
  		email_test = "";
  	}
  %>
  
  	<form method="post" action="verification">
		<label for="email">邮 箱:</label>
		<input type="email" name="email" id="email" placeholder="请输入忘记的邮箱账号" value="<%=email_test %>">
		<input type="text" name="verification_code" id="verication" placeholder="请输入验证码">
		<input type="hidden" name="verication_code" id="verication_code" value="<%=request.getParameter("verication")%>">
		<button data-role="button" id="verication_button">获取验证码</button>
	</form>
  	
  </div>
  <div data-role="footer">
    <h1>找回密码</h1>
  </div>
</div>
	
</body>
</html>