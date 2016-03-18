<%@page import="com.springmvc.ketao.config.Define"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <p class="text-title inline-block">查看课程</p>
        <div class="inline-block float-right">
          <button class="info-state btn-edit-info base-btn" onclick="edit()">编辑</button>
          <button class="edit-state btn-edit-info btn-white" onclick="cancleEdit()" style="display: none">取消</button>
        </div>
      </div>
      <div class="infrom-add-form">
        <div class="form-group">
          <lable class="col-sm-2 text-right">课程通知标题</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${notice.title }</p>
            <input data-id="${notice.id }" class="edit-state" name="addnotice-name" type="text" placeholder="请输入课程通知标题" value="${notice.title }" style="display: none;">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">课程通知</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${notice.content }</p>
            <input data-id="${notice.id }" class="edit-state" name="addnotice-name" type="text" placeholder="请输入课程通知" value="${notice.content }" style="display: none;">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">课程id</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${notice.course_id }</p>
            <input class="edit-state" name="addnotice-id" type="text" placeholder="请输入课程id" value="${notice.course_id }" style="display: none;" disabled="">
          </div>
          <label class="edit-state important-sign" style="display: none;">*</label>
          <lable class="empty-error text-error" style="display: none;">课程id不能为空</lable>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">更新时间</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${notice.create_time }</p>
            <input class="edit-state" type="text" value="${notice.create_time }" style="display: none;" disabled="">
          </div>
        </div>
        <div class="edit-btn" style="display: none;">
          <div class="col-sm-3">
            <lable class="text-error" style="display: none;">修改失败</lable>
          </div>
          <button type="submit" class="base-btn" onclick="savenotice()">保存</button>
        </div>
      </div>
    </div>
  </div>
  <script type="text/javascript">
  
  /**
   * 编辑学生信息
   */
  function savenotice(){
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
	  	  	   		window.location.href=window.location.href;
	  	  	   	}
	  	  	   	else{
	  	  	   		$("#addnotice-fail").show().css("display", "inline-block");
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
