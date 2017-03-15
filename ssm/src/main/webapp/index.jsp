<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="base" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link rel="stylesheet" type="text/css" href="static/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="static/jquery-easyui/themes/icon.css">

<script type="text/javascript" src="static/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="static/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="static/js/main.js"></script>
<script type="text/javascript">


</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',split:true" href="" style="height:110px;">
		<div align="left">
			<h1>XX管理系统</h1>
		</div>
	</div>

	<div data-options="region:'west',title:'导航菜单',split:true" style="width:150px">
		<div id="aa" class="easyui-accordion" fit="true">   
		    <div title="用户管理" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">   
		       <ul id="tree"></ul>     
		       <a href="${base}/findusers">用户列表</a></br>
				<a href="${base}/finditems">菜品列表</a></br>
				<a href="${base}/itemManager">菜品管理</a></br>  
		    </div>   
		    <div title="岗位管理" data-options="selected:true" style="padding:10px;">   
		        content2    
		    </div>   
		    <div title="权限管理">   
		        content3    
		    </div>   
		</div>  
	
	</div>
	
	<div data-options="region:'center',title:'首页' "
		style="padding:5px; background:#eee; overflow:hidden;" >
		 
		<div class="easyui-tabs" fit="true" border="false" id="tt"  style="width:150px">
				<div align="center" data-options="title:'首页'">
					<h1>欢迎使用该系统</h1>
				</div>				
		</div>

	</div>
	
	<div region="east" style="width:100px">右侧内容XXXXXXX</div>
</body>
</html>