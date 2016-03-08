<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  request.setAttribute("basePath", basePath);
%>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="${basePath }css/easyui.css"/>
  <link rel="stylesheet" type="text/css" href="${basePath }css/icon.css"/>    
  <link rel="stylesheet" type="text/css" href="${basePath }css/login.css"/>
  <script type="text/javascript" src="${basePath }js/jquery.min.js"></script>    
  <script type="text/javascript" src="${basePath }js/jquery.easyui.min.js"></script>    
  <script type="text/javascript" src="${basePath }js/easyui-lang-zh_CN.js"></script> 
</head>
<body>
  <div class="loginbar">
    <p class="logo-txt text-center">KeTao</p>
    <p class="loginbar-tip text-center">KeTao后台管理系统</p>
	<div style="margin-bottom:10px">
	  <input class="easyui-textbox" style="width:100%;height:40px;padding:12px" data-options="prompt:'Username',iconCls:'icon-man',iconWidth:38">
	</div>
	<div style="margin-bottom:20px">
	  <input class="easyui-textbox" type="password" style="width:100%;height:40px;padding:12px" data-options="prompt:'Password',iconCls:'icon-lock',iconWidth:38">
	</div>
	<div style="margin-bottom:20px">
	  <input type="checkbox" checked="checked">
	<span>Remember me</span>
	</div>
	<div>
	  <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="padding:5px 0px;width:100%;">
	    <span style="font-size:14px;">Login</span>
	  </a>
	</div>
  </div>
</body>
</html>