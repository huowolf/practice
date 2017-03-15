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
	<form action="${base }/searchUser" method="post">
		姓名：<input type="text" name="name" value="${userQuery.name }">  &nbsp;
		性别：<input type="radio" name="sex" value="1" ${(userQuery.sex == 1)?"checked":""}/>男
			<input type="radio" name="sex" value="0" ${(userQuery.sex == 0)?"checked":""}/>女    
			<input type="radio" name="sex" value="-1" ${(userQuery.sex == -1)?"checked":""}/>所有  </br>
		最小年龄：<input type="text" name="minAge" value="${userQuery.minAge }"/>
		最大年龄：	<input type="text" name="maxAge" value="${userQuery.maxAge }"/></br>
		<input type="submit" value="查询">
	</form>
	<h1>用户列表</h1>
	<form action="${base}/importUser" method="post" enctype="multipart/form-data">
		<input type="file" name="execl">
		<input type="submit" value="导入数据">
	</form>
	<a href="${base }/exportUser">导出数据</a>
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
	</table>
	<a href="${base}/saveUserUI">添加用户</a>
</body>
</html>