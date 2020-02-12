<%@page import="com.lanling.util.BandingUtil"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>绑定邮箱</title>
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
		String username = request.getParameter("username");//拿到用户编号
		String openid = request.getParameter("openid");//拿到openid
		if(BandingUtil.isbangding(username, openid) != null){
			//如果已经绑定邮箱了的话
	%>
			<h1 align="center">对不起，您已经绑定了邮箱</h1>
	<%		
		}else{
	%>
			<div data-role="main" class="ui-content">
	        <form id="youxiang_form" method="post" action="youxiang" data-ajax="false">
	            <div class="ui-field-contain">
	            	<input type="hidden" value="<%=username%>" name="username">
	            	<input type="hidden" value="<%=openid%>" name="openid"/>
					<label for="email">邮箱:</label>
					<%
						String value = "";
						if(request.getParameter("email") != null){
							value = request.getParameter("email");
						}
					%>
					<input type="email" name="email" id="youxiang_email" placeholder="请输入邮箱" value="<%=value %>">
					<span id="youxiang_span" ></span><br/>
	                <%
	                	if(session.getAttribute("verification") == null){
	                %>
	                		<input type="button" value="获取验证码" onclick="check();">
	                <%
	                	}else{
	                %>
	                		<input type="button" value="获取验证码" disabled="disabled">
	                <%
	                	}
	                %>
					<label for="verification">验证码:</label>
					<input type="text" name="verification" id="verification"><br>
					<%
	                	if(session.getAttribute("verification") == null){
	                %>
	                		<input type="button" value="绑定邮箱" disabled="disabled">
	                <%
	                	}else{
	                %>
	                		<input type="button" value="绑定邮箱" onclick="check();">
	                <%
	                	}
	                %>
	                
	            </div>
	        </form>
        <script type="text/javascript">
        	function check(){
        		var youxiang_form = document.getElementById("youxiang_form");
        		var youxiang_email = document.getElementById("youxiang_email");
        		var youxiang_span = document.getElementById("youxiang_span");
        		var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
       			if(youxiang_email.value === ""){ //输入不能为空
       				youxiang_span.style.color="red";
       				youxiang_span.innerHTML = "邮箱不能为空";
       		　　}else if(!reg.test(youxiang_email.value)){ //正则验证不通过，格式不对
       				youxiang_span.style.color="red";
       				youxiang_span.innerHTML = "邮箱格式验证不通过";
       		　　}else{
       				youxiang_span.style.color="green";
					youxiang_span.innerHTML = "邮箱格式验证通过";
					youxiang_form.submit();
      		　　}
 			}
        </script>
	<%		
		}
		
	%>
        
    </div>

</body>
</html>