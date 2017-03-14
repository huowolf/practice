<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="base" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户列表</title>
</head>
<body>
	<h1>${message }</h1>
	<h1>
		用户列表--<a href="/user/add">添加用户</a>---<a href="${base }/logout">退出登录</a>
	</h1>
	<h2>权限列表</h2>
	<shiro:authenticated>用户已经登录显示此内容</shiro:authenticated>      </br>
	<shiro:hasRole name="manager">manager角色登录显示此内容</shiro:hasRole>   </br>
	<shiro:hasRole name="admin">admin角色登录显示此内容</shiro:hasRole>		 </br>	
	<shiro:hasRole name="normal">normal角色登录显示此内容</shiro:hasRole>	 </br>	

	<shiro:hasAnyRoles name="manager,admin">**manager or admin 角色用户登录显示此内容**</shiro:hasAnyRoles>    </br>
	显示当前登录用户名       <shiro:principal />          </br>       
	<shiro:hasPermission name="add">add权限用户显示此内容</shiro:hasPermission>   </br>
	<shiro:hasPermission name="user:query">query权限用户显示此内容<shiro:principal />   </br>
	</shiro:hasPermission>
	<shiro:lacksPermission name="user:del"> 不具有user:del权限的用户显示此内容 </shiro:lacksPermission>
	
</body>
</html>
