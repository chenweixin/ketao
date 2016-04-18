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
        <p class="text-title inline-block">添加学生信息</p>
      </div>
      <div class="student-add-form">
        <div class="form-group">
          <lable class="col-sm-2 text-right">学号</lable>
          <div class="col-sm-4 inline-block">
            <input name="addstudent-id" type="text" placeholder="请输入学号">
          </div>
          <label class="important-sign">*</label>
          <lable class="empty-error text-error" style="display: none;">学号或密码不能为空</lable>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">密码</lable>
          <div class="col-sm-4 inline-block">
            <input name="addstudent-pwd" type="text" placeholder="请输入密码">
          </div>
          <label class="important-sign">*</label>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">姓名</lable>
          <div class="col-sm-4 inline-block">
            <input name="addstudent-name" type="text" placeholder="请输入中文姓名">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">性别</lable>
          <div class="col-sm-4 inline-block">
            <input name="addstudent_sex" type="radio" value="0" checked="">
            <label class="radio-margin">男</label>
            <input name="addstudent_sex" type="radio" value="1" >
            <label>女</label>
		  </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">学院</lable>
          <div class="col-sm-4 inline-block">
            <input name="addstudent-college" type="text" placeholder="请输入学院名称">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">专业</lable>
          <div class="col-sm-4 inline-block">
            <input name="addstudent-major" type="text" placeholder="请输入专业名称">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">入学年份</lable>
          <div class="col-sm-4 inline-block">
            <input name="addstudent-period" type="text" placeholder="请输入入学年份">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">头像</lable>
          <div class="col-sm-4 inline-block">
            <input name="addstudent-avatar_url" type="text" placeholder="头像链接">
          </div>
        </div>
        <div>
          <div class="col-sm-3">
            <lable id="addstudent-fail" class="text-error" style="display: none;">添加失败，请检查主键</lable>
            <lable id="addstudent-success" class="text-success" style="display: none;">添加成功</lable>
          </div>
          <button id="addstudent-btn" class="base-btn" onclick="addstudent()">确认添加</button>
        </div>
      </div>
    </div>
  </div>
  <script type="text/javascript">
  /**
   * 添加学生
   */
  function addstudent(){
  	var str_data = getStudentData();
  	$.ajax({
  	   type: "POST",
  	   url: "/ketao/student/add",
  	   data: str_data,
  	   success: function(data){
  	   	if(data.success == "true"){
  	   		$("#addstudent-fail").hide();
  	   		$("#addstudent-success").show().css("display", "inline-block");
  	   		$("input[type='text']").val("");
  	   	}
  	   	else{
  	   		$("#addstudent-fail").show().css("display", "inline-block");
  	   		$("#addstudent-success").hide();
  	   	}
  	   }
  	});
  }
  </script>
</body>
</html>
