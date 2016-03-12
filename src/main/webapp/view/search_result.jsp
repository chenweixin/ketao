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
        <p class="text-title"><span class="text-search">XXX</span> 的搜索结果</p>
      </div>
      <div>
        <button type="submit" class="base-btn btn-normal">刷新</button>
        <button type="submit" class="base-btn btn-normal">删除</button>
        <p class="inline-block">删除成功</p>
        <p class="inline-block text-error">删除失败</p>
      </div>
      <table class="table table-hover">
        <tr>
	      <th>
	        <input type="checkbox" checked="checked">
	      </th>
	      <th>学号</th>
	      <th>密码</th>
	      <th>姓名</th>
	      <th>性别</th>
	      <th>学院</th>
	      <th>专业</th>
	      <th></th>
	    </tr>
	    <tr>
	      <td>
	        <input type="checkbox" checked="checked">
	      </td>
	    </tr>
	  </table>
    </div>
  </div>
</body>
</html>
