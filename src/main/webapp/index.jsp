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
  <script type="text/javascript" src="${basePath }js/jquery.min.js"></script>    
  <script type="text/javascript" src="${basePath }js/jquery.easyui.min.js"></script>    
  <script type="text/javascript" src="${basePath }js/easyui-lang-zh_CN.js"></script> 
</head>
<body>
   
</body>
</html>
