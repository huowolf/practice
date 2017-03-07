<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="base" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>
	<h1>用户列表</h1>
	<table width="80%" border="1" cellspacing="0"  cellpadding="0">
		<tr>
			<th>用户编号</th>
			<th>用户名</th>
			<th>性别</th>
			<th>年龄</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td><c:if test="${user.sex==1}">男</c:if><c:if test="${user.sex==0}">女</c:if></td>
				<td>${user.age}</td>			
				<td>
					<a href="${base}/saveUserUI?id=${user.id}">修改</a> &nbsp;&nbsp;
					<a href="${base}/deleteUser?id=${user.id}">删除</a>
				</td>
			</tr>
		
		</c:forEach>
		<a href="${base}/saveUserUI">添加用户</a>
	</table>
</body>
</html>