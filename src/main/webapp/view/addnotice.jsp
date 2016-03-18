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
  <script type="text/javascript" src="${basePath }js/bootstrap-suggest.min.js"></script>  
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
      <div class="notice-add-form">
        <div class="form-group">
          <lable class="col-sm-2 text-right">课程通知标题</lable>
          <div class="col-sm-4 inline-block">
            <input name="addnotice-title" type="text" placeholder="请输入课程通知标题">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">课程通知</lable>
          <div class="col-sm-4 inline-block">
            <input name="addnotice-content" type="text" placeholder="请输入课程通知">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">关联课程id</lable>
          <div class="col-sm-4 inline-block">
            <input name="addnotice-course_id" type="text" placeholder="请输入关联课程id">
          </div>
          <lable class="id-error text-error" style="display: none;">请输入正确的课程id</lable>
        </div>
        <div>
          <div class="col-sm-3">
            <lable id="addnotice-success" class="text-success" style="display: none;">添加成功</lable>
          </div>
          <button id="addnotice-btn" class="base-btn" onclick="addnotice()">确认添加</button>
        </div>
      </div>
    </div>
  </div>
  <script type="text/javascript">
  function addnotice(){
	  var course_id = $("input[name='addnotice-course_id']").val();
	  if(course_id == ""){
		  $(".empty-error").show().css("display", "inline-block");
		  return;
	  }
	  $.ajax({
	  	   type: "POST",
	  	   url: "/ketao/course/checkid",
	  	   data: {course_id: course_id},
	  	   success: function(data){
	  	   	if(data.success == "true"){
	  	   	var str_data = getNoticeData();
	  	  	$.ajax({
	  	  	   type: "POST",
	  	  	   url: "/ketao/notice/add",
	  	  	   data: str_data,
	  	  	   success: function(data){
	  	  	   	if(data.success == "true"){
	  	  	   		$("#addnotice-fail").hide();
	  	  	   		$("#addnotice-success").show().css("display", "inline-block");
	  	  	   		$("input[type='text']").val("");
	  	  	   	}
	  	  	   	else{
	  	  	   		$("#addnotice-fail").show().css("display", "inline-block");
	  	  	   		$("#addnotice-success").hide();
	  	  	   	}
	  	  	   }
	  	  	});
	  	   	}
	  	   	else{
	  	   		$(".id-error").show().css("display", "inline-block");
	  	   	}
	  	   }
	  	});
  }
  </script>
</body>
</html>
