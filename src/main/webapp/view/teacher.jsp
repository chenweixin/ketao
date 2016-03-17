<%@page import="com.springmvc.ketao.config.Define"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  
  String username = (String) session.getAttribute(Define.SESSTION_LOGIN_NAME);
  
  request.setAttribute("basePath", basePath);
  request.setAttribute("username", username);
%>
<html>
<head>  
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
  <div class="sidebar-nav">
    <p class="sidebar-logo">KeTao</p>
    <div class="item">
      <ul>
        <li><a href="/ketao">首页</a></li>
        <li><a href="/ketao/student" class="active">学生信息管理</a></li>
        <li><a href="/ketao/teacher">教师信息管理</a></li>
      </ul>
    </div>
  </div>
  <div class="base-container">
    <div class="nav">
      <p class="nav-title">KeTao后台管理系统</p>
      <a href="/ketao/login/logout" class="nav-item"><i class="icon icon-signout"></i>退出</a>
      <a class="nav-item">${username }</a>
      <p class="welcome">Hi，</p>
    </div>
    <div class="content">
      <div class="content-titlebar">
        <p class="text-title inline-block">搜索教师信息</p>
      </div>
      <form class="searchbar" action="/ketao/teacher/getteachers" onsubmit="return checkFields()">
        <div class="searcharea">
          <input name="search" type="text" placeholder="输入教师工号或姓名">
        </div>
        
        <button type="submit" class="base-btn">搜索</button>
        <!-- <button class="base-btn">高级搜索</button> -->
        <p class="inline-block text-error" style="display: none">请输入搜索内容</p>
      </form>
    </div>
      
    <div class="content">
      <div class="content-titlebar">
        <p class="text-title inline-block">添加教师信息</p>
      </div>
      <p class="inline-block">往数据库添加教师请点击</p>
      <a href="/ketao/view/addteacher.jsp" class="inline-block text-btn">添加</a>
      
    </div>
    
    <div class="content">
      <div class="content-titlebar">
        <p class="text-title inline-block">查看全部教师</p>
      </div>
      <p class="inline-block">查看数据库中所有的教师列表</p>
      <a href="/ketao/teacher/getall" class="inline-block text-btn">查看</a>
      
    </div>
  </div>
  <script type="text/javascript">
    function checkFields(){
    	var value = $("input[name='search']").val();
    	if(value == ""){
    		$(".text-error").show().css("display", "inline-block");
    		return false;
    	}
    	return true;
    }
  </script>
</body>
</html>
