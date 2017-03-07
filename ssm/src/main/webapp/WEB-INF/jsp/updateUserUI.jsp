<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户</title>
</head>
<body>
	<h1>修改用户信息</h1>
	<form method="post" action="${pageContext.request.contextPath }/updateUser">
	<input type="hidden" name="id" value="${user.id }"/>
		<table width="80%" border=1>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="name" value="${user.name }"/></td>
			</tr>
			<tr>
				<td>性别</td>
				<td>
					<input type="radio" name="sex" value="1" checked="${user.sex==1?'checked':''}"/>男
					<input type="radio" name="sex" value="0" checked="${user.sex==0?'checked':''}"/>女
				</td>
			</tr>
			<tr>
				<td>年龄</td>
				<td><input type="text" name="age" value="${user.age }"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="提交"/></td>
			</tr>
		</table>
	</form>
</body>
</html>