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
        <p class="text-title inline-block"><span class="text-search">${search }</span> 的搜索结果</p>
      </div>
      <div>
        <button class="base-btn btn-normal" onclick="turnToAdd()">添加</button>
        <button class="base-btn btn-normal" onclick="deleteTeachers()">删除</button>
        <p class="empty-error inline-block text-error" style="display: none;">请勾选要删除的项</p>
        <p class="del-error inline-block text-error" style="display: none;">删除失败</p>
      </div>
      <table class="table">
        <tr>
	      <th>
	        <input name="checkbox-selectAll" type="checkbox" onclick="isSelectAll(this)">
	      </th>
	      <th>教师工号</th>
	      <th>密码</th>
	      <th>姓名</th>
	      <th>性别</th>
	      <th>学院</th>
	      <th>头像</th>
	    </tr>
	    <c:if test="${!empty teachers }">
		<c:forEach items="${teachers }" var="item">
	    <tr>
	      <td>
	        <input class="item-ids" data-id="${item.id }" type="checkbox">
	      </td>
	      <td><a class="text-btn" href="/ketao/teacher/get?teacherid=${item.id }">${item.id }</a></td>
	      <td>${item.password}</td>
	      <td>${item.name}</td>
	      <c:if test="${item.sex == false }">
	      <td>男</td>
	      </c:if>
	      <c:if test="${item.sex == true }">
	      <td>女</td>
	      </c:if>
	      <td>${item.college}</td>
	      <td><img src="${item.avatar_url}" style="height: 20px"/></td>
	    </tr>
	    </c:forEach>
	    </c:if>
	  </table>
    </div>
  </div>
  <script type="text/javascript">
    function deleteTeachers(){
  	  var items = $(".item-ids");
  	  var ids = [];
  	  for(var i = 0; i < items.length; i++){
  		  if(items[i].checked){
  			  ids.push($(items[i]).data("id"));
  		  }
  	  }
  	  if(ids.length == 0){
  		  $(".empty-error").show().css("display", "inline-block");
  		  return ;
  	  }
  	  var str_data = {ids: ids};
  	  $.ajax({
   	    type: "POST",
   	    url: "/ketao/teacher/delbyids",
   	    data: str_data,
   	    success: function(data){
   	   	  if(data.success == "true"){
   	   		window.location.href=window.location.href;
   	   	  }
   	   	  else{
   	  		  $(".del-error").show().css("display", "inline-block");
   	   	  }
   	   }
   	});
    }
    /**
     * 跳转
     */
    function turnToAdd(){
    	window.location.href = "/ketao/view/addteacher.jsp"
    }
  </script>
</body>
</html>
