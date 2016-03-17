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
        <p class="text-title inline-block">查看学生信息</p>
        <div class="inline-block float-right">
          <button class="info-state btn-edit-info base-btn" onclick="edit()">编辑</button>
          <button class="edit-state btn-edit-info btn-white" onclick="cancleEdit()" style="display: none">取消</button>
        </div>
      </div>
      <div class="student-add-form">
        <div class="form-group">
          <lable class="col-sm-2 text-right">学号</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${student.id }</p>
            <input class="edit-state" name="addstudent-id" type="text" placeholder="请输入学号" value="${student.id }" style="display: none;" disabled="">
          </div>
          <label class="edit-state important-sign" style="display: none;">*</label>
          <lable class="empty-error text-error" style="display: none;">学号或密码不能为空</lable>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">密码</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${student.password }</p>
            <input class="edit-state" name="addstudent-pwd" type="text" placeholder="请输入密码" value="${student.password }" style="display: none;">
          </div>
          <label class="edit-state important-sign" style="display: none;">*</label>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">姓名</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${student.name }</p>
            <input class="edit-state" name="addstudent-name" type="text" placeholder="请输入姓名" value="${student.name }" style="display: none;">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">性别</lable>
          <div class="col-sm-4 inline-block">
            <div>
              <c:if test="${student.sex == false }">
                <p class="info-state">男</p>
                <div class="edit-state" style="display: none">
                  <input name="addstudent_sex" type="radio" value="0" checked="">
		          <label class="radio-margin">男</label>
		          <input name="addstudent_sex" type="radio" value="1" >
		          <label>女</label>
                </div>
              </c:if>
              <c:if test="${student.sex == true }">
                <p class="info-state">女</p>
                <div class="edit-state" style="display: none">
                  <input name="addstudent_sex" type="radio" value="0">
		          <label class="radio-margin">男</label>
		          <input name="addstudent_sex" type="radio" value="1" checked="">
		          <label>女</label>
                </div>
              </c:if>
            </div>
		  </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">学院</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${student.college }</p>
            <input class="edit-state" name="addstudent-college" type="text" placeholder="请输入学院" value="${student.college }" style="display: none;">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">专业</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${student.major }</p>
            <input class="edit-state" name="addstudent-major" type="text" placeholder="请输入专业" value="${student.major }" style="display: none;">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">入学年份</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${student.period }</p>
            <input class="edit-state" name="addstudent-period" type="text" placeholder="请输入入学年份" value="${student.period }" style="display: none;">
          </div>
        </div>
        <div class="edit-btn" style="display: none;">
          <div class="col-sm-3">
            <lable class="text-error" style="display: none;">修改失败</lable>
          </div>
          <button type="submit" class="base-btn" onclick="saveStudent()">保存</button>
        </div>
      </div>
    </div>
  </div>
  <script type="text/javascript">
  
  /**
   * 编辑学生信息
   */
  function saveStudent(){
  	var str_data = getStudentData();
  	$.ajax({
  	   type: "POST",
  	   url: "/ketao/student/update",
  	   data: str_data,
  	   success: function(data){
  	   	if(data.success == "true"){
  	   		window.location.href=window.location.href;
  	   	}
  	   	else{
  	   		$("#addstudent-fail").show().css("display", "inline-block");
  	   	}
  	   }
  	});
  }
  </script>
</body>
</html>
