<%@page import="com.nami.jw.base.JwQuery"%>
<%@page import="com.nami.jw.base.JwBaseUrl"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("utf-8");
String   currentPath=request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+request.getRequestURI();
%>
<!DOCTYPE html>
<html>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=basePath+"/css/default.css"%>" />
<style type="text/css">
* {
    background: none repeat scroll 0 0 transparent;
    border: 0 none;
    margin: 0;
    padding: 0;
    vertical-align: baseline;
	font-family:微软雅黑;
}
#navi{
	width:100%;
	position:relative;
	word-wrap:break-word;
	border-bottom:1px solid #065FB9;
	margin:0;
	padding:0;
	height:40px;
	line-height:40px;
	vertical-align:middle;
    background-image: -moz-linear-gradient(top,#EBEBEB, #BFBFBF);
    background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #EBEBEB),color-stop(1, 
#BFBFBF));
}
#naviDiv{
	font-size:14px;
	color:#333;
	padding-left:10px;
}
#tips{
	margin-top:10px;
	width:100%;
	height:40px;
}
#buttonGroup{
	padding-left:10px;
	float:left;
	height:35px;
}
.button{
	float:left;
	margin-right:10px;
	padding-left:10px;
	padding-right:10px;
	font-size:14px;
	width:70px;
	height:30px;
	line-height:30px;
	vertical-align:middle;
	text-align:center;
	cursor:pointer;
    border-color: #77D1F6;
    border-width: 1px;
    border-style: solid;
    border-radius: 6px 6px;
    -moz-box-shadow: 2px 2px 4px #282828;
    -webkit-box-shadow: 2px 2px 4px #282828;
    background-image: -moz-linear-gradient(top,#EBEBEB, #BFBFBF);
    background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #EBEBEB),color-stop(1, #BFBFBF));
}


</style>
<body>

<%
	String urlcode = (String)session.getAttribute("urlcode");
	String usernumber = (String)session.getAttribute("usernumber");
	String username = (String)session.getAttribute("username");
	String gradeUrl = JwBaseUrl.getAllGradeUrl(urlcode, usernumber, username);
	String mainHeader = JwBaseUrl.getMainHeader(urlcode, usernumber);
	String viewstate = JwQuery.getViewstate(gradeUrl, mainHeader);
	List<Map<String, String>> list = JwQuery.getGrade(gradeUrl, mainHeader, viewstate, "", "", "");
 %>

<div id="navi">
	<div id='naviDiv'>
		<span><img src="<%=basePath+"images/arror.gif"%>" width="7" height="11" border="0" alt=""></span>&nbsp;个人信息<span>&nbsp;
	</div>
</div>
<div id="mainContainer">

	<table class="default" width="100%">
		<col width="10%">
		<col width="20%">
		<col width="5%">
		<col width="20%">
		<col width="30%">
		<tr class="title">
			<td>学年</td>
			<td>学期</td>
			<td>课程名称</td>
			<td>成绩</td>
			<td>补考</td>
		</tr>
		<%
			for(int i=1; i<list.size(); i++){
				Map<String, String> map = list.get(i);
				
		 %>
		<!-- 遍历开始 -->
		<tr class="list">
			<td><%=map.get("td0") %></td>
			<td><%=map.get("td1") %></td>
			<td><%=map.get("td3") %></td>
			<td><%=map.get("td8") %></td>
			<td><%=map.get("td10") %></td>
		</tr>
		<%
			}	
		 %>
		<!-- 遍历结束 -->
	</table>

</div>

</body>
</html>