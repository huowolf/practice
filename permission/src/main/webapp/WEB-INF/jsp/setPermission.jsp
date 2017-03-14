<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="base" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分配权限</title>
</head>
<body>
	<c:forEach item="${ roles}" var="role">
		<input type="checkbox" name="roles" value=${role }> ::
		<input type="checkbox" name="permissions" value="add">
		<input type="checkbox" name="permissions" value="del">
		<input type="checkbox" name="permissions" value="update">
		<input type="checkbox" name="permissions" value="query"> </br>
	</c:forEach>
</body>
</html>