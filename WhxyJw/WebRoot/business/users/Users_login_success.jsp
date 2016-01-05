<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("utf-8");

String   currentPath=request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+request.getRequestURI();   

  //uri=uri.substring(uri.lastIndexOf("/")+1);   

%>

<!DOCTYPE html>
<html>
<head>
	<title>后台管理</title>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=basePath+"css/index.css"%>" />
</head>
<body>
<div id="wrapper">
	<div id="header">
		<div id="logo">LOGO</div>
		<div id="title">后台管理系统</div>
		<div id="menu">
			<div id="menu_container">
				<ul id="menu_items">
					<li class="menu_item on" style="border-radius:8px 0 0 8px" onmouseout="this.style.backgroundColor=''" onmouseover="this.style.backgroundColor='#77D1F6';this.style.borderRadius='8px 0 0 8px'"><a>系统管理</a></li>
				</ul>
			</div>
		</div>
		<div id="user_info">
			<div id="welcome">欢迎${sessionScope.loginUserName}使用本系统</div>
			<div id="logout"><a href="<%=basePath+"index.jsp"%>">安全退出</a></div>
		</div>
	</div>
	<div id="navigator">
		<iframe src="<%=basePath+"tree.jsp"%>"></iframe>
        </div>
	<div id="main">
		<iframe name="MainFrame" src="Users_login_main.jsp"></iframe>
	</div>
	<div id="footer">Copyright © Tel:18120570432  By： 最爱娜美注孤生</div>
</div>
</body>
<script type="text/javascript">
function screenAdapter(){
	document.getElementById('footer').style.top=document.documentElement.scrollTop+document.documentElement.clientHeight- document.getElementById('footer').offsetHeight+"px";
		document.getElementById('navigator').style.height=document.documentElement.clientHeight-100+"px";
		document.getElementById('main').style.height=document.documentElement.clientHeight-100+"px";
		document.getElementById('main').style.width=window.screen.width-230+"px";
}

window.onscroll=function(){screenAdapter();};
window.onresize=function(){screenAdapter();};
window.onload=function(){screenAdapter();};
</script>
</html>