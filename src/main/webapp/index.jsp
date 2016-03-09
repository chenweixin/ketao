<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  request.setAttribute("basePath", basePath);
%>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="${basePath }css/easyui.css"/>    
  <link rel="stylesheet" type="text/css" href="${basePath }css/icon.css"/>      
  <link rel="stylesheet" type="text/css" href="${basePath }css/index.css"/>   
  <link rel="stylesheet" type="text/css" href="${basePath }css/sidebar.css"/>  
  <script type="text/javascript" src="${basePath }js/jquery.min.js"></script>    
  <script type="text/javascript" src="${basePath }js/jquery.easyui.min.js"></script>    
  <script type="text/javascript" src="${basePath }js/easyui-lang-zh_CN.js"></script> 
</head>
<body>
  <div class="sidebar-nav">
    <p class="sidebar-logo">KeTao</p>
    <div class="item">
      <ul>
        <li><a href="#" class="active">首页</a></li>
        <li><a href="#">港式早茶</a></li>
        <li><a href="#">虾饺</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav">
      111
    </div>
  </div>
</body>
</html>
