<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜品列表</title>
<style type="text/css"> 
* { 
margin:0; 
padding:0; 
font-size:12px; 
text-decoration:none; 
} 
#products { 
width:560px; 
margin:50px auto; 
} 
#products li { 
width:110px; 
height:154px; 
float:left; 
margin-left:30px; 
display:inline; 
} 
#products li a { 
display:block; 
} 
#products li a img { 
border:1px solid #666; 
padding:1px; 
} 
#products li span a { 
width:110px; 
height:30px; 
line-height:24px; 
text-align:center; 
white-space:nowrap; 
text-overflow:ellipsis; 
overflow: hidden; 
} 
</style> 
</head> 
<body> 
<ul id="products"> 
<c:forEach items="${items }" var="item">
	<li><a href="#"><img src="${item.pic }" title="${item.description }" width="120" height="100"/></a> 
		<span>${item.title }</br>
			售价:${item.price }
		</span> 
	</li>
</c:forEach>
 
</ul> 

</body>
</html>