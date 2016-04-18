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
        <p class="text-title inline-block">查看公告</p>
        <div class="inline-block float-right">
          <button class="info-state btn-edit-info base-btn" onclick="edit()">编辑</button>
          <button class="edit-state btn-edit-info btn-white" onclick="cancleEdit()" style="display: none">取消</button>
        </div>
      </div>
      <div class="spread-add-form">
        <div class="form-group">
          <lable class="col-sm-2 text-right">标题</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${spread.title }</p>
            <input data-id="${spread.id }" class="edit-state" name="addspread-title" type="text" placeholder="请输入标题" value="${spread.title }" style="display: none;">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">主办方</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${spread.sponsor }</p>
            <input class="edit-state" name="addspread-sponsor" type="text" placeholder="请输入主办方名称" value="${spread.sponsor }" style="display: none;">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">活动时间</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${spread.event_date }</p>
            <input class="edit-state" name="addspread-event_date" type="text" placeholder="请输入活动时间" value="${spread.event_date }" style="display: none;">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">活动地点</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${spread.event_address }</p>
            <input class="edit-state" name="addspread-event_address" type="text" placeholder="请输入活动地点" value="${spread.event_address }" style="display: none;">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">活动地点</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${spread.event_describe }</p>
            <input class="edit-state" name="addspread-event_describe" type="text" placeholder="请输入活动描述" value="${spread.event_describe }" style="display: none;">
          </div>
        </div>
        <div style="overflow:hidden;min-height: 35px;margin-bottom: 15px;line-height: 35px">
          <lable class="col-sm-2 text-right">图片</lable>
          <div class="col-sm-4 inline-block">
            <img class="info-state" src="${spread.poster_url }" style="height: 100px"/>
            <input class="edit-state" name="addspread-poster_url" type="text" placeholder="点击上传图片" value="${spread.poster_url }" style="display: none;">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">更新时间</lable>
          <div class="col-sm-4 inline-block">
            <p class="info-state">${spread.create_time }</p>
            <input class="edit-state" type="text" placeholder="请输入内容" value="${spread.create_time }" style="display: none;" disabled="">
          </div>
        </div>
        
        
        <div class="edit-btn" style="display: none;">
          <div class="col-sm-3">
            <lable class="text-error" style="display: none;">修改失败</lable>
          </div>
          <button type="submit" class="base-btn" onclick="savespread()">保存</button>
        </div>
      </div>
    </div>
  </div>
  <script type="text/javascript">
  
  /**
   * 编辑学生信息
   */
  function savespread(){
  	var str_data = getSpreadData();
  	$.ajax({
  	   type: "POST",
  	   url: "/ketao/spread/update",
  	   data: str_data,
  	   success: function(data){
  	   	if(data.success == "true"){
  	   		window.location.href=window.location.href;
  	   	}
  	   	else{
  	   		$("#addspread-fail").show().css("display", "inline-block");
  	   	}
  	   }
  	});
  }
  </script>
</body>
</html>
