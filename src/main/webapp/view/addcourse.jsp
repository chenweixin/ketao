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
        <li><a href="#">教师信息管理</a></li>
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
        <p class="text-title inline-block">添加课程</p>
      </div>
      <div class="course-add-form">
        <div class="form-group">
          <lable class="col-sm-2 text-right">课程名称</lable>
          <div class="col-sm-4 inline-block">
            <input name="addcourse-name" type="text" placeholder="请输入课程名称">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">上课地点</lable>
          <div class="col-sm-4 inline-block">
            <input name="addcourse-location" type="text" placeholder="请输入上课地点">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">学分</lable>
          <div class="col-sm-4 inline-block">
            <input name="addcourse-credit" type="text" placeholder="请输入学分">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">课程类型</lable>
          <div class="col-sm-4 inline-block">
            <select class="addcourse-type">
              <option value="0">
              <option value="1">
              <option value="2">
              <option value="3">
              <option value="4">
              <option value="5">
              <option value="6">
            </select>
          </div>
        </div>
        <div>
          <div class="col-sm-3">
            <lable id="addcourse-success" class="text-success" style="display: none;">添加成功</lable>
          </div>
          <button id="addcourse-btn" class="base-btn" onclick="addcourse()">确认添加</button>
        </div>
      </div>
    </div>
  </div>
  <script type="text/javascript">
  function addcourse(){
  	var str_data = getCourseData();
  	$.ajax({
  	   type: "POST",
  	   url: "/ketao/course/add",
  	   data: str_data,
  	   success: function(data){
  	   	if(data.success == "true"){
  	   		$("#addcourse-fail").hide();
  	   		$("#addcourse-success").show().css("display", "inline-block");
  	   		$("input[type='text']").val("");
  	   	}
  	   	else{
  	   		$("#addcourse-fail").show().css("display", "inline-block");
  	   		$("#addcourse-success").hide();
  	   	}
  	   }
  	});
  }
  </script>
</body>
</html>
