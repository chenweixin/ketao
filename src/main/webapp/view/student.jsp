<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  request.setAttribute("basePath", basePath);
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
        <li><a href="#">教师信息管理</a></li>
      </ul>
    </div>
  </div>
  <div class="base-container">
    <div class="nav">
      <p class="nav-title">KeTao后台管理系统</p>
      <p class="nav-item"><i class="icon icon-signout"></i>退出</p>
      <p class="nav-item">XXXX</p>
      <p class="welcome">Hi，</p>
    </div>
    <div class="content">
      <div class="content-titlebar">
        <p class="text-title">搜索学生信息</p>
      </div>
      <div class="searchbar">
        <div class="searcharea">
          <input type="text" placeholder="输入学号或姓名">
        </div>
        
        <button type="submit" class="base-btn">搜索</button>
        <button type="submit" class="base-btn">高级搜索</button>
        <p class="inline-block text-error">请输入搜索内容</p>
      </div>
    </div>
      
    <div class="content">
      <div class="content-titlebar">
        <p class="text-title">添加学生信息</p>
      </div>
      <p class="inline-block">往数据库添加学生请点击</p>
      <p class="inline-block text-btn">添加</p>
      
    </div>
    
    <div class="content">
      <div class="content-titlebar">
        <p class="text-title">查看全部学生</p>
      </div>
      <p class="inline-block">查看数据库中所有的学生列表</p>
      <p class="inline-block text-btn">查看</p>
      
    </div>
  </div>
</body>
</html>
