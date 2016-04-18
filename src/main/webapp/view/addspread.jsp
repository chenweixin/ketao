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
        <p class="text-title inline-block">添加推广信息</p>
      </div>
      <div class="spread-add-form">
        <div class="form-group">
          <lable class="col-sm-2 text-right">标题</lable>
          <div class="col-sm-4 inline-block">
            <input name="addspread-title" type="text" placeholder="请输入标题">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">主办方</lable>
          <div class="col-sm-4 inline-block">
            <input name="addspread-sponsor" type="text" placeholder="请输入主办方">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">活动时间</lable>
          <div class="col-sm-4 inline-block">
            <input name="addspread-event_date" type="text" placeholder="请输入活动时间">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">活动地点</lable>
          <div class="col-sm-4 inline-block">
            <input name="addspread-event_address" type="text" placeholder="请输入活动地点">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">活动描述</lable>
          <div class="col-sm-4 inline-block">
            <input name="addspread-event_describe" type="text" placeholder="请输入活动描述">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">图片</lable>
          <div class="col-sm-4 inline-block">
            <input name="addspread-poster_url" type="text" placeholder="点击上传图片">
          </div>
        </div>
        <div>
          <div class="col-sm-3">
            <lable id="addspread-success" class="text-success" style="display: none;">添加成功</lable>
          </div>
          <button id="addspread-btn" class="base-btn" onclick="addspread()">确认添加</button>
        </div>
      </div>
    </div>
  </div>
  <script type="text/javascript">
  /**
   * 添加学生
   */
  function addspread(){
  	var str_data = getSpreadData();
  	$.ajax({
  	   type: "POST",
  	   url: "/ketao/spread/add",
  	   data: str_data,
  	   success: function(data){
  	   	if(data.success == "true"){
  	   		$("#addspread-fail").hide();
  	   		$("#addspread-success").show().css("display", "inline-block");
  	   		$("input[type='text']").val("");
  	   	}
  	   	else{
  	   		$("#addspread-fail").show().css("display", "inline-block");
  	   		$("#addspread-success").hide();
  	   	}
  	   }
  	});
  }
  </script>
</body>
</html>
