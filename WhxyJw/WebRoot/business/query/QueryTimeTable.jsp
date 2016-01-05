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

.blacktab {
    border: 1px solid #000;
}

.blacktab {
    border-collapse: collapse;
    width: 100%;
    margin: 2px auto;
}

.blacktab th, .blacktab td {
    border: 1px solid #000;
    color: #000;
}

</style>
<body>

<%
	String urlcode = (String)session.getAttribute("urlcode");
	String usernumber = (String)session.getAttribute("usernumber");
	String username = (String)session.getAttribute("username");
	String timeTableUrl = JwBaseUrl.getTimeTableUrl(urlcode, usernumber, username);
	String mainHeader = JwBaseUrl.getMainHeader(urlcode, usernumber);
	String viewstate = JwQuery.getViewstate(timeTableUrl, mainHeader);
	List<Map<String, String>> list = JwQuery.getTimeTable(timeTableUrl, mainHeader, viewstate);
 %>

<div id="navi">
	<div id='naviDiv'>
		<span><img src="<%=basePath+"images/arror.gif"%>" width="7" height="11" border="0" alt=""></span>&nbsp;<a href="StudentsList.jsp">学生列表</a><span>&nbsp;
	</div>
</div>
<div id="mainContainer">

<%-- <table class="default" width="100%">
	<col width="5%">
	<col width="20%">
	<col width="20%">
	<col width="20%">
	<col width="20%">
	<col width="20%">
	<col width="5%">
	<col width="5%">
	<tr class="title">
		<td>时间</td>
		<td>周一</td>
		<td>周二</td>
		<td>周三</td>
		<td>周四</td>
		<td>周五</td>
		<td>周六</td>
		<td>周日</td>
	</tr>
	<%
		for(int i=0; i<list.size(); i++){
			Map<String, String> map = list.get(i);
			
	 %>
	<!-- 遍历开始 -->
	<tr class="list">
		<td>时间</td>
		<% for(int j=0; j<map.size(); j++){ %>
		<td><% String[] content = map.get("td"+j).split(" ");
				for(String s:content) out.print(s+"</br>"); %></td>
		<% } %>
	</tr>
	<%
		}	
	 %>
	<!-- 遍历结束 -->
</table> --%>

	<table id="Table1" class="default" bordercolor="Black" border="0" width="100%">
	<tr class="title">
		<td colspan="2" rowspan="1" width="2%">时间</td><td align="Center" width="14%">星期一</td><td align="Center" width="14%">星期二</td><td align="Center" width="14%">星期三</td><td align="Center" width="14%">星期四</td><td align="Center" width="14%">星期五</td><td class="noprint" align="Center" width="14%">星期六</td><td class="noprint" align="Center" width="14%">星期日</td>
	</tr><tr>
		<td colspan="2">早晨</td><td align="Center">&nbsp;</td><td align="Center">&nbsp;</td><td align="Center">&nbsp;</td><td align="Center">&nbsp;</td><td align="Center">&nbsp;</td><td class="noprint" align="Center">&nbsp;</td><td class="noprint" align="Center">&nbsp;</td>
	</tr><tr>
		<td rowspan="4" width="1%">上午</td><td width="1%">第1节</td>
		<% for(int j=0; j<list.get(0).size(); j++){ %>
			<td align="Center" rowspan="2" width="7%">
			<% String[] content = list.get(0).get("td"+j).split(" ");
				for(int i=0; i<content.length; i++){
					out.print(content[i]+"</br>");
					if(i%4 == 3){out.print("</br>");}
				} %></td>
		<% } %>
	</tr><tr>
		<td>第2节</td><td class="noprint" align="Center">&nbsp;</td><td class="noprint" align="Center">&nbsp;</td>
	</tr><tr>
		<td>第3节</td>
		<% for(int j=0; j<list.get(1).size(); j++){ %>
		<td align="Center" rowspan="2" width="7%">
			<% String[] content = list.get(1).get("td"+j).split(" ");
				for(int i=0; i<content.length; i++){
					out.print(content[i]+"</br>");
					if(i%4 == 3){out.print("</br>");}
				} %></td>
		<% } %>
	</tr><tr>
		<td>第4节</td><td class="noprint" align="Center">&nbsp;</td><td class="noprint" align="Center">&nbsp;</td>
	</tr><tr>
		<td rowspan="4" width="1%">下午</td><td>第5节</td>
		<% for(int j=0; j<list.get(2).size(); j++){ %>
		<td align="Center" rowspan="2" width="7%">
			<% String[] content = list.get(2).get("td"+j).split(" ");
				for(int i=0; i<content.length; i++){
					out.print(content[i]+"</br>");
					if(i%4 == 3){out.print("</br>");}
				} %></td>
		<% } %>		
	</tr><tr>
		<td>第6节</td><td class="noprint" align="Center">&nbsp;</td><td class="noprint" align="Center">&nbsp;</td>
	</tr><tr>
		<td>第7节</td>
		<% for(int j=0; j<list.get(3).size(); j++){ %>
		<td align="Center" rowspan="2" width="7%">
			<% String[] content = list.get(3).get("td"+j).split(" ");
				for(int i=0; i<content.length; i++){
					out.print(content[i]+"</br>");
					if(i%4 == 3){out.print("</br>");}
				} %></td>
		<% } %>	
	</tr><tr>
		<td>第8节</td><td class="noprint" align="Center">&nbsp;</td><td class="noprint" align="Center">&nbsp;</td>
	</tr><tr>
		<td rowspan="4" width="1%">晚上</td><td>第9节</td><td align="Center">&nbsp;</td><td align="Center">&nbsp;</td><td align="Center">&nbsp;</td><td align="Center">&nbsp;</td><td align="Center">&nbsp;</td><td class="noprint" align="Center">&nbsp;</td><td class="noprint" align="Center">&nbsp;</td>
	</tr><tr>
		<td>第10节</td><td align="Center">&nbsp;</td><td align="Center">&nbsp;</td><td align="Center">&nbsp;</td><td align="Center">&nbsp;</td><td align="Center">&nbsp;</td><td class="noprint" align="Center">&nbsp;</td><td class="noprint" align="Center">&nbsp;</td>
	</tr><tr>
		<td>第11节</td><td align="Center">&nbsp;</td><td align="Center">&nbsp;</td><td align="Center">&nbsp;</td><td align="Center">&nbsp;</td><td align="Center">&nbsp;</td><td class="noprint" align="Center">&nbsp;</td><td class="noprint" align="Center">&nbsp;</td>
	</tr><tr>
		<td>第12节</td><td align="Center">&nbsp;</td><td align="Center">&nbsp;</td><td align="Center">&nbsp;</td><td align="Center">&nbsp;</td><td align="Center">&nbsp;</td><td class="noprint" align="Center">&nbsp;</td><td class="noprint" align="Center">&nbsp;</td>
	</tr>
</table>


</div>

</body>
</html>