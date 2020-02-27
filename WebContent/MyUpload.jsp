<%@page import="java.util.ArrayList"%>
<%@page import="com.lanling.util.GetUploadData"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.lanling.util.JDBCUtil"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.lanling.bean.UploadData" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width" />
<title>我的上传</title>
<!-- meta使用viewport以确保页面可自由缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 引入 jQuery Mobile 样式 -->
<link rel="stylesheet" href="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">
<!-- 引入 jQuery 库 -->
<script src="http://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
<!-- 引入 jQuery Mobile 库 -->
<script src="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<style type="text/css">
	img{
	    width: 84vw;//占宽度的84%
	}
</style>
</head>
<body>
	
	
	<%
		String username = request.getParameter("username");//获取到用户传过来的用户编号
		String openid = request.getParameter("openid");//获取到用户传过来的openid
		GetUploadData data = new GetUploadData();
		ArrayList<UploadData> lists = data.getUploadData(username,openid);
		session.setAttribute("lists", lists);
	%>
		
	<div data-role="page" id="pageone">
  
  	<div data-role="main" class="ui-content">
   
	    <table data-role="table" class="ui-responsive">
				<thead>
					<tr>
						<th>次数</th>
						<th>上传时间</th>
						<th>地理位置</th>
						<th>省级</th>
						<th>市级</th>
						<th>县级/区级</th>
						<th>纬度</th>
						<th>经度</th>
						<th>土地类型</th>
						<th>作物类型</th>
						<th>产量</th>
						<th>肥料种类</th>
						<th>氮肥百分比</th>
						<th>磷肥百分比</th>
						<th>钾肥百分比</th>
						<th>其它元素占比</th>
						<th>动物粪便</th>
						<th>农业废弃物</th>
						<th>工业废弃物</th>
						<th>生活垃圾</th>
						<th>泥垢</th>
						<th>浇水次数</th>
						<th>第一次浇水时间</th>
						<th>第二次浇水时间</th>
						<th>第三次浇水时间</th>
						<th>第一次浇水量</th>
						<th>第二次浇水量</th>
						<th>第三次浇水量</th>
						<th>施肥次数</th>
						<th>第一次施肥时间</th>
						<th>第二次施肥时间</th>
						<th>第三次施肥时间</th>
						<th>第一次施肥量</th>
						<th>第二次施肥量</th>
						<th>第三次施肥量</th>
						<th>是否打药</th>
						<th>是否除草</th>
						<th>土地景观图1</th>
						<th>土地景观图2</th>
						<th>现场访谈图</th>
					</tr>
				</thead>
				<tbody>
				
					<%
						int i=1;
						for(UploadData uploadData:lists){
					%>		
							<tr>
								<td><strong>第<%=(i++) %>次上传</strong></td>
								<td><%=uploadData.getDate() %></td>
								<td><%=uploadData.getLocation() %></td>
								<td><%=uploadData.getProvince() %></td>
								<td><%=uploadData.getCity() %></td>
								<td><%=uploadData.getDistrict() %></td>
								<td><%=uploadData.getLatitude() %></td>
								<td><%=uploadData.getLongitude() %></td>
								<td><%=uploadData.getLand_sort() %></td>
								<td><%=uploadData.getCrop_sort() %></td>
								<td><%=uploadData.getHarvest() %></td>
								<td><%=uploadData.getManure_sort() %></td>
								<td><%=uploadData.getDanfei() %></td>
								<td><%=uploadData.getLinfei() %></td>
								<td><%=uploadData.getJiafei() %></td>
								<td><%=uploadData.getQita() %></td>
								<td><%=uploadData.getDongwufenbian() %></td>
								<td><%=uploadData.getNongyefeiqiwu() %></td>
								<td><%=uploadData.getGongyefeiqiwu() %></td>
								<td><%=uploadData.getShenghuolaji() %></td>
								<td><%=uploadData.getNigou() %></td>
								<td><%=uploadData.getWater_number() %></td>
								<td><%=uploadData.getWaterDate_first() %></td>
								<td><%=uploadData.getWaterDate_second() %></td>
								<td><%=uploadData.getWaterDate_third() %></td>
								<td><%=uploadData.getWaterNumber_first() %></td>
								<td><%=uploadData.getWaterNumber_second() %></td>
								<td><%=uploadData.getWaterNumber_third() %></td>
								<td><%=uploadData.getManure_number() %></td>
								<td><%=uploadData.getManureDate_first() %></td>
								<td><%=uploadData.getManureDate_second() %></td>
								<td><%=uploadData.getManureDate_third() %></td>
								<td><%=uploadData.getManureNumber_first() %></td>
								<td><%=uploadData.getManureNumber_second() %></td>
								<td><%=uploadData.getManureNumber_third() %></td>
								<td><%=uploadData.getSpray() %></td>
								<td><%=uploadData.getWeed() %></td>
								<td><img src="<%=uploadData.getLand_Iamge1_String() %>"></td>
								<td><img src="<%=uploadData.getLand_Iamge1_String() %>"></td>
								<td><img src="<%=uploadData.getLand_Iamge1_String() %>"></td>
								<!--  width="350px" height="350px"  -->
							</tr>
					<%	
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>