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
        <p class="text-title inline-block">添加教师信息</p>
      </div>
      <div class="teacher-add-form">
        <div class="form-group">
          <lable class="col-sm-2 text-right">教师工号</lable>
          <div class="col-sm-4 inline-block">
            <input name="addteacher-id" type="text" placeholder="请输入教师工号">
          </div>
          <label class="important-sign">*</label>
          <lable class="empty-error text-error" style="display: none;">工号或密码不能为空</lable>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">密码</lable>
          <div class="col-sm-4 inline-block">
            <input name="addteacher-pwd" type="text" placeholder="请输入密码">
          </div>
          <label class="important-sign">*</label>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">姓名</lable>
          <div class="col-sm-4 inline-block">
            <input name="addteacher-name" type="text" placeholder="请输入中文姓名">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">性别</lable>
          <div class="col-sm-4 inline-block">
            <input name="addteacher_sex" type="radio" value="0" checked="">
            <label class="radio-margin">男</label>
            <input name="addteacher_sex" type="radio" value="1" >
            <label>女</label>
		  </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">学院</lable>
          <div class="col-sm-4 inline-block">
            <input name="addteacher-college" type="text" placeholder="请输入学院名称">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">头像</lable>
          <div class="col-sm-4 inline-block">
            <input name="addteacher-avatar_url" type="text" placeholder="头像链接">
          </div>
        </div>
        <div>
          <div class="col-sm-3">
            <lable id="addteacher-fail" class="text-error" style="display: none;">添加失败，请检查主键</lable>
            <lable id="addteacher-success" class="text-success" style="display: none;">添加成功</lable>
          </div>
          <button id="addteacher-btn" class="base-btn" onclick="addteacher()">确认添加</button>
        </div>
      </div>
    </div>
  </div>
  <script type="text/javascript">
  /**
   * 添加学生
   */
  function addteacher(){
  	var str_data = getTeacherData();
  	$.ajax({
  	   type: "POST",
  	   url: "/ketao/teacher/add",
  	   data: str_data,
  	   success: function(data){
  	   	if(data.success == "true"){
  	   		$("#addteacher-fail").hide();
  	   		$("#addteacher-success").show().css("display", "inline-block");
  	   		$("input[type='text']").val("");
  	   	}
  	   	else{
  	   		$("#addteacher-fail").show().css("display", "inline-block");
  	   		$("#addteacher-success").hide();
  	   	}
  	   }
  	});
  }
  </script>
</body>
</html>
