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
        <button class="base-btn btn-normal" onclick="deletecourses()">删除</button>
        <p class="empty-error inline-block text-error" style="display: none;">请勾选要删除的项</p>
        <p class="del-error inline-block text-error" style="display: none;">删除失败</p>
      </div>
      <table class="table">
        <tr>
	      <th>
	        <input name="checkbox-selectAll" type="checkbox" onclick="isSelectAll(this)">
	      </th>
	      <th>课程id</th>
	      <th>课程名称</th>
	      <th>任课教师</th>
	      <th>上课地点</th>
	      <th>学分</th>
	      <th>课程简介</th>
	      <th>课程类型</th>
	      <th>课程收藏数</th>
	      <th>总评价数</th>
	      <th>课程总评分</th>
	      <th>更新时间</th>
	    </tr>
	    <c:if test="${!empty courses }">
		<c:forEach items="${courses }" var="item">
	    <tr>
	      <td>
	        <input class="item-ids" data-id="${item.id }" type="checkbox">
	      </td>
	      <td><a class="text-btn" href="/ketao/course/get?courseid=${item.id }">${item.id }</a></td>
	      <td>${item.name}</td>
	      <td><a class="text-btn" href="/ketao/teacher/get?teacherid=${item.teacher_id }">${item.teacher_name}</a></td>
	      <td>${item.location}</td>
	      <td>${item.credit}</td>
	      <td>${item.introduction}</td>
	      <td>
	        <c:if test='${item.type == 0}'>人文科学</c:if>
	        <c:if test='${item.type == 1}'>人文科学核心</c:if>
	        <c:if test='${item.type == 2}'>社会科学</c:if>
	        <c:if test='${item.type == 3}'>社会科学核心</c:if>
	        <c:if test='${item.type == 4}'>科学技术</c:if>
	        <c:if test='${item.type == 5}'>科学技术核心</c:if>
	      </td>
	      <td>${item.num_collect}</td>
	      <td>${item.num_evaluate}</td>
	      <td>${item.score}</td>
	      <td>${item.create_time}</td>
	    </tr>
	    </c:forEach>
	    </c:if>
	  </table>
    </div>
  </div>
  <script type="text/javascript">
    function deletecourses(){
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
   	    url: "/ketao/course/delbyids",
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
    	window.location.href = "/ketao/view/addcourse.jsp"
    }
  </script>
</body>
</html>
