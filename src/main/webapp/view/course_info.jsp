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
          <lable class="col-sm-2 text-right">课程id</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${course.id }</p>
            <input class="edit-state" name="addcourse-id" type="text" placeholder="请输入课程id" value="${course.id }" style="display: none;" disabled="">
          </div>
          <label class="edit-state important-sign" style="display: none;">*</label>
          <lable class="empty-error text-error" style="display: none;">课程id或教师工号不能为空</lable>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">课程名称</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${course.name }</p>
            <input data-id="${course.id }" class="edit-state" name="addcourse-name" type="text" placeholder="请输入课程名称" value="${course.name }" style="display: none;">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">任课教师工号</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${course.teacher_id }</p>
            <input class="edit-state" name="addcourse-teacher_id" type="text" placeholder="请输入教师工号" value="${course.teacher_id }" style="display: none;">
          </div>
          <label class="important-sign">*</label>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">上课地点</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${course.location }</p>
            <input class="edit-state" name="addcourse-location" type="text" placeholder="请输入上课地点" value="${course.location }" style="display: none;">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">学分</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${course.credit }</p>
            <input class="edit-state" name="addcourse-credit" type="text" placeholder="请输入学分" value="${course.credit }" style="display: none;">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">简介</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${course.introduction }</p>
            <input class="edit-state" name="addcourse-introduction" type="text" placeholder="请输入课程简介" value="${course.introduction }" style="display: none;">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">课程类型</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${course.type }</p>
            <select class="addcourse-type edit-state" value="${course.type }" style="display: none;">
              <option value="0" <c:if test='${course.type == 0}'>selected=""</c:if>>
              <option value="1" <c:if test='${course.type == 1}'>selected=""</c:if>>
              <option value="2" <c:if test='${course.type == 2}'>selected=""</c:if>>
              <option value="3" <c:if test='${course.type == 3}'>selected=""</c:if>>
              <option value="4" <c:if test='${course.type == 4}'>selected=""</c:if>>
              <option value="5" <c:if test='${course.type == 5}'>selected=""</c:if>>
            </select>
            
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">课程收藏数</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${course.num_collect }</p>
            <input class="edit-state" name="addcourse-num_collect" type="text" value="${course.num_collect }" style="display: none;" disabled="">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">总评价数</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${course.num_collect }</p>
            <input class="edit-state" type="text" value="${course.num_collect }" style="display: none;" disabled="">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">课程总评分和</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${course.num_evaluate }</p>
            <input class="edit-state" type="text" value="${course.num_evaluate }" style="display: none;" disabled="">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">更新时间</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${course.create_time }</p>
            <input class="edit-state" type="text" value="${course.create_time }" style="display: none;" disabled="">
          </div>
        </div>
        <div class="edit-btn" style="display: none;">
          <div class="col-sm-3">
            <lable class="text-error" style="display: none;">修改失败</lable>
          </div>
          <button type="submit" class="base-btn" onclick="savecourse()">保存</button>
        </div>
      </div>
    </div>
  </div>
  <script type="text/javascript">
  
  /**
   * 编辑学生信息
   */
  function savecourse(){
	  var teacher_id = $("input[name='addcourse-teacher_id']").val();
	  if(teacher_id == ""){
		  $(".empty-error").show().css("display", "inline-block");
		  return;
	  }
	  $.ajax({
	  	   type: "POST",
	  	   url: "/ketao/teacher/checkid",
	  	   data: {teacher_id: teacher_id},
	  	   success: function(data){
	  	   	if(data.success == "true"){
	  	   	var str_data = getCourseData();
	  	  	$.ajax({
	  	  	   type: "POST",
	  	  	   url: "/ketao/course/update",
	  	  	   data: str_data,
	  	  	   success: function(data){
	  	  		if(data.success == "true"){
	  	  	   		window.location.href=window.location.href;
	  	  	   	}
	  	  	   	else{
	  	  	   		$("#addcourse-fail").show().css("display", "inline-block");
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
