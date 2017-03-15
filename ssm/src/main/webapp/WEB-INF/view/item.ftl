<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情页</title>
</head>
<h1>商品详情</h1>
<body>
	<#list items as item>
		商品名称：${item.title}           	</br>
		商品描述：${item.description}		</br>	
		商品图片：<img src="${item.pic}" width="120" height="100"/>				</br>
		商品价格：${item.price}			</br>
	</#list>
</body>
</html>