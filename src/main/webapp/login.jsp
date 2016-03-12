<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  request.setAttribute("basePath", basePath);
%>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="${basePath }css/login.css"/>
  <link rel="stylesheet" type="text/css" href="${basePath }css/bootstrap.min.css"/> 
  <link rel="stylesheet" type="text/css" href="${basePath }css/bootstrap-theme.min.css"/> 
  <link rel="stylesheet" type="text/css" href="${basePath }css/icon.css"/>      
  <link rel="stylesheet" type="text/css" href="${basePath }css/base.css"/>   
  <link rel="stylesheet" type="text/css" href="${basePath }css/sidebar.css"/>  
  <script type="text/javascript" src="${basePath }js/jquery.min.js"></script>    
  <script type="text/javascript" src="${basePath }js/bootstrap.min.js"></script>    
  <script type="text/javascript" src="${basePath }js/base.js"></script>  
</head>
<body>
  <form class="loginbar">
    <p class="logo-txt text-center">KeTao</p>
    <p class="loginbar-tip text-center">KeTao后台管理系统</p>
    <div class="margin-bottom">
      <input type="text" name="username"  placeholder="用户名">
    </div>
    <div class="margin-bottom">
      <input type="password" name="password" placeholder="密码">
    </div>
	<div class="checkbox">
	  <input type="checkbox" checked="checked">
	  <span>记住密码</span>
	</div>
	<button type="submit" class="login-btn">登录</button>
  </form>
</body>
</html>