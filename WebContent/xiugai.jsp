<%@page import="com.lanling.bean.User"%>
<%@page import="com.lanling.util.GetUploadData"%>
<%@page import="com.lanling.util.Util" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>修改资料</title>
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
		if(username == null && openid == null){//从XiuGaiServlet中重定向过来，没有username和openid
			username = (String) session.getAttribute("username");
			openid = (String) session.getAttribute("openid");
		}else{//将username和openid保存在session中
			session.setAttribute("username", username);
			session.setAttribute("openid", openid);
		}
		GetUploadData data = new GetUploadData();
		User user = data.getUser(username, openid);
		session.setAttribute("user", user);
	%>

	<div id="page1" data-role="page" class="bg01">
			<div data-role="content">
				<div id="ct-list">
					<ul data-role="listview" data-inset="true">
						<li><a href="#page2">
								<img width="100px" height="100px" src="<%=user.getPhoto() %>" align="center"/>
							</a></li>
						<li><a href="#">
								<%
									if(Util.isUsername(username, openid)){//是账号密码登陆的
								%>
										<h2>用户编号</h2>
										<p><%=user.getUsername() %></p>
								<%	
									}else{
								%>
										<h2>openid</h2>
										<p><%=user.getOpenid() %></p>
								<%
									}
								%>
							</a></li>
						<li><a href="#page3">
								<h2>昵称</h2>
								<p><%=user.getName()%></p>
							</a></li>
						<li><a href="#page4">
								<h2>性别</h2>
								<p><%=user.getSex() %></p>
							</a></li>
						<li><a href="#page5">
								<h2>省份</h2>
								<p><%=user.getProvince()%></p>
							</a></li>
						<li><a href="#page6">
								<h2>城市</h2>
								<p><%=user.getCity() %></p>
							</a></li>
						<li><a href="#">
							<h2>邮箱</h2>
							<p><%=user.getEmail() %></p>
						</a></li>
							
					</ul>
				</div>
			</div>
		</div>
		
		<!-- 头像修改 -->
		<div id="page2" data-role="page">
			<div data-role="header" data-position="inline">
	     		<h1>更换头像</h1>
	   			<a href="" data-rel="back">上一页</a> 
	  		</div>
			<form action="xiugai" method="post" data-ajax="false" enctype="multipart/form-data">
				<input type="file"   accept="image/*" name="touxiang" id="touxiang"  />
				<span id="xiugai_span"></span>
				<button type="submit" data-role="button">更换头像</button>
			</form>
			<script>
			    $('#touxiang').change(function() {
			        fileValid(this, 200, 'image', function() {
			        	xiugai_span.style.color="green";
				        xiugai_span.innerHTML = "验证通过，请点击更换头像按钮";
			        })
			    })
			    function fileValid (value_, size_, type_, callback) {
				    var file = value_.files[0];
				    var fileSize = (file.size / 1024).toFixed(0);
				    var fileType = value_.value.substring(value_.value.lastIndexOf("."));
				 	var xiugai_span = document.getElementById("xiugai_span");
				    if (fileSize > size_) {
				    	xiugai_span.style.color="red";
				        xiugai_span.innerHTML = "警告：图片大小不要超过200kb!";
				        return false;
				    }
				    callback();
			    }
			</script>
		</div>
		
		<div id="page3" data-role="page">
		<div data-role="header" data-position="inline">
	     		<h1>更改昵称</h1>
	   			<a href="" data-rel="back">上一页</a> 
	  		</div>
			<form action="xiugai" method="post" data-ajax="false">
				<input type="text" name="name">
				<button type="submit" data-role="button">更改昵称</button>
			</form>
		</div>
		<div id="page4" data-role="page">
		<div data-role="header" data-position="inline">
	     		<h1>设置性别</h1>
	   			<a href="" data-rel="back">上一页</a> 
	  		</div>
			<form action="xiugai" method="post" data-ajax="false">
				<fieldset data-role="controlgroup" data-type="horizontal">
				 <input type="radio" name="sex" id="rdo1" value="男" checked="checked"/>
				 <label for="rdo1">男</label>
				 <input type="radio" name="sex" id="rdo2" value="女"/>
				 <label for="rdo2">女</label>
				 <input type="radio" name="sex" id="rdo3" value="保密"/>
				 <label for="rdo3">保密</label>
				</fieldset>
				<button type="submit" data-role="button">设置性别</button>
			</form>
		</div>
		<div id="page5" data-role="page">
			<div data-role="header" data-position="inline">
	     		<h1>选择省份</h1>
	   			<a href="" data-rel="back">上一页</a> 
	  		</div>
			<form action="xiugai" method="post" data-ajax="false">
				<input type="text" name="province">
				<button type="submit" data-role="button">确定省份</button>
			</form>
		</div>
		<div id="page6" data-role="page">
			<div data-role="header" data-position="inline">
	     		<h1>选择城市</h1>
	   			<a href="" data-rel="back">上一页</a> 
	  		</div>
			<form action="xiugai" method="post" data-ajax="false">
				<input type="text" name="city">
				<button type="submit" data-role="button">确定城市</button>
			</form>
		</div>

</body>
</html>