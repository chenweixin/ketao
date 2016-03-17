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
</head>
<body>
  <div id="loginform" class="loginbar">
    <p class="logo-txt text-center">KeTao</p>
    <p class="loginbar-tip text-center">KeTao后台管理系统</p>
    <p id="loginfail" class="text-error" style="visibility: hidden;">用户名或密码错误</p>
    <div class="margin-bottom">
      <input id="login_username" type="text" name="username"  placeholder="用户名">
    </div>
    <div class="margin-bottom">
      <input id="login_password" type="password" name="password" placeholder="密码">
    </div>
	<div class="checkbox">
	  <input type="checkbox" checked="checked">
	  <span>记住密码</span>
	</div>
	<button class="login-btn" onclick="loginsubmit()">登录</button>
  </div>
  <script type="text/javascript">
  /**
   * 登录
   */
  function loginsubmit(){
  	var admin = $("#login_username").val();
  	var password = $("#login_password").val();
  	var urlReferrer = document.referrer;
  	var str_data = {username: admin, password: password, urlReferrer: urlReferrer}; 
  	$.ajax({
  	   type: "POST",
  	   url: "/ketao/login/login",
  	   data: str_data,
  	   success: function(data){
  	   	if(data.success == "true"){
  	   		window.location.href="/ketao";
  	   	}
  	   	else{
  	   		$("#loginfail").css("visibility","visible");
  	   	}
  	   }
  	});
  	
  }
  </script>
</body>
</html>