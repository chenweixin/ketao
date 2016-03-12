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
        <p class="text-title">添加学生信息</p>
      </div>
      <div class="student-add-form">
        <div class="form-group">
          <lable class="col-sm-2 text-right">学号</lable>
          <div class="col-sm-4 inline-block">
            <input type="text" placeholder="请输入学号">
          </div>
          <label class="important-sign">*</label>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">密码</lable>
          <div class="col-sm-4 inline-block">
            <input type="text" placeholder="请输入密码">
          </div>
          <label class="important-sign">*</label>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">姓名</lable>
          <div class="col-sm-4 inline-block">
            <input type="text" placeholder="请输入中文姓名">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">性别</lable>
          <div class="col-sm-4 inline-block">
            <select>
		      <option>男</option>
		      <option>女</option>
		  </select>
		  </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">学院</lable>
          <div class="col-sm-4 inline-block">
            <input type="text" placeholder="请输入学院名称">
          </div>
        </div>
        <div class="form-group">
          <lable class="col-sm-2 text-right">专业</lable>
          <div class="col-sm-4 inline-block">
            <input type="text" placeholder="请输入专业名称">
          </div>
        </div>
        <div>
          <lable class="col-sm-3 text-error">添加失败</lable>
          <button type="submit" class="base-btn">确认添加</button>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
