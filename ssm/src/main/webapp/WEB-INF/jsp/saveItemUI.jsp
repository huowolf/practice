<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="base" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>保存菜品</title>
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="${base}/js/jquery.form.js"></script>
<script type="text/javascript">
		
function submitUpload() {
	var option = {
		url : "${base}/uploadPic",
		type: "post",
		dataType : "json",
		success : function(data) {
			$("#photo").attr("src",data.realPath);
			$("#reletivePath").val(data.reletivePath)
		},
		error : function() {
			alert("上传失败");
		}
	}
	$("#form1").ajaxSubmit(option);
}
</script>
</head>
<body>
	<h1>保存菜品信息</h1>
	<form id="form1" method="post" action="${pageContext.request.contextPath }/saveItem" enctype="multipart/form-data">
	<input type="hidden" name="id" value="${item.id }"/>
		<table width="80%" border=1>
			<tr>
				<td>菜名</td>
				<td><input type="text" name="title" value="${item.title }"/></td>
			</tr>
			<tr>
				<td>菜品照片</td>
				<td>
					<img id="photo" src="${item.pic }" width="120" height="100"/></br>
					<input  type="file" name="photo" onchange="submitUpload()" />
					<input type="hidden" name="pic" id="reletivePath" value="${item.pic }" />
				</td>
			</tr>
			<tr>
				<td>菜品描述</td>
				<td><textarea  name="description" rows="5" cols="20" value="${item.description }">${item.description }</textarea></td>
			</tr>
			<tr>
				<td>售价</td>
				<td><input type="text" name="price" value="${item.price }"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="提交"/></td>
			</tr>
		</table>
	</form>
</body>
</html>