<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  String loginStatus = request.getParameter("loginStatus");
  if(loginStatus==null || "".equals(loginStatus)){
	  response.sendRedirect(path+"/business/users/Users_login.jsp");
  }else{
	  response.sendRedirect(path+"/business/users/Users_login.jsp?loginStatus="+loginStatus);
  }
%>
