<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="base" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜品管理</title>
</head>
<body>
	<h1>菜品列表</h1>
	<table width="80%" border="1" cellspacing="0"  cellpadding="0">
		<tr>
			<th>菜名</th>
			<th>菜品照片</th>
			<th>售价</th>
			<th>菜品描述</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${items}" var="item">
			<tr>
				<td>${item.title}</td>
				<td><img src="${item.pic }" width="120" height="100"/></td>
				<td>${item.price}</td>	
				<td>
					<textarea rows="5" cols="20" >${item.description }</textarea>
				</td>		
				<td>
					<a href="${base}/saveItemUI?id=${item.id}">修改</a> &nbsp;&nbsp;
					<a href="${base}/deleteItem?id=${item.id}">删除</a>
				</td>
			</tr>
		
		</c:forEach>
		<a href="${base}/saveItemUI">添加菜品</a>
	</table>
</body>
</html>