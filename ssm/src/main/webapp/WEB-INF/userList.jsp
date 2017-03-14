<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.4.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.4.1/themes/icon.css">

<script type="text/javascript" src="../jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

var flag;

$(function(){
	$("#userdg").datagrid({
		fit: true,
		url: "../UserServlet?method=userList",
		fitColumns: true,
		singleSelect: true,
		pagination: true,
		pageSize: 5,
		pageList: [5,10,15,20,25],
		toolbar: [{
			text: "新增用户",
			iconCls: 'icon-add',
			handler: function(){
				flag = "add"
				$("#userDialog").dialog({
					title: "新增用户"
				});
				$("#userform").form("reset");
				$("#userDialog").dialog('open');
			}
		},'-',{
			text: "修改用户",
			iconCls: 'icon-edit',
			handler: function(){
				flag = "update"
				var userSelected = $("#userdg").datagrid("getSelected");
				if(userSelected==null){
					$.messager.alert("提示","请选择你要修改的信息","error");
				}else{		
					$("#userDialog").dialog({
						title: "修改用户"
					});
					//打开窗口
					$("#userDialog").dialog('open');
					//重置
					$("#userform").form("reset");
					//回显数据
					$("#userform").form('load',{
						id: userSelected.id,
						name: userSelected.name,
						age: userSelected.age,
						pwd: userSelected.pwd,
						description: userSelected.description
					});
				}
			}
		},'-',{
			text: "删除用户",
			iconCls: 'icon-remove',
			handler: function(){
				


				var userSelected = $("#userdg").datagrid("getSelected");
				if(userSelected != null){
					$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
					    if (r){    
					    	$.post("../UserServlet?method=userDelete",{"id":userSelected.id},function(data){
								$('#userdg').datagrid('reload');
								$.messager.show({
						        	title: "提示信息",
						        	msg: "操作成功"
						        });
							});
					    }    
					});  
					
				}else{
					$.messager.alert("提示","请选择你要删除的信息","error");
				}
			}
		},'-',{
			text: "查询用户",
			iconCls: 'icon-search',
			handler: function(){
				var panel = $("#lay").layout('panel','north');
				if(panel.panel('options').collapsed){
					$("#lay").layout('expand','north')
				}	
			}
		}]

	});
	
	
	
/* 	//保存按钮
	$("#saveBtn").click(function(){
		
		//重置表单数据
		//$("#userform").form("reset");
		//提交表单
		$("#userform").form('submit', {    
		    url: flag=="add" ? "../UserServlet?method=userAdd" : "../UserServlet?method=userUpdate",    
		    onSubmit: function(){    
				var isValid = $(this).form('validate');
				if(!isValid){
					$.messager.show({
						title: "提示信息",
						msg: "数据验证失败,不能保存"
					});
					return isValid;	// 返回false终止表单提交
				}
		    },    
		    success:function(data){    
		        //关闭窗口
		        $("#userDialog").dialog('close');
		        //刷新datagrid
		        $("#userdg").datagrid('reload');
		        //提示信息
		        var data = eval('(' + data + ')');
		        $.messager.show({
		        	title: data.status,
		        	msg: data.message
		        });
		    }    
		});
		
	}); */
	
	
	//保存按钮
	$('#saveBtn').click(function(){
		if($('#userform').form('validate')){
			$.ajax({
				type: 'post' ,
				url: flag=='add'?'../UserServlet?method=userAdd':'../UserServlet?method=userUpdate' ,
				cache:false ,
				data:$('#userform').serialize() ,
				dataType:'json' ,
				success:function(result){
					//1 关闭窗口
					$('#userDialog').dialog('close');
					//2刷新datagrid 
					$('#userdg').datagrid('reload');
					//3 提示信息
					$.messager.show({
						title:result.status , 
						msg:result.message
					});
				} ,
				error:function(result){
					$.messager.show({
						title:result.status , 
						msg:result.message
					});
				}
			});
		} else {
			$.messager.show({
				title:'提示信息' ,
				msg:'数据验证不通过,不能保存!'
			});
		}
	});

		
	//重置按钮
	$("#redoBtn").click(function(){
		$("#userform").form("reset");
	})
	
	//查询按钮
	$("#searchBtn").click(function(){
		$("#userdg").datagrid('load',serializeForm($("#userSearch")));
	});
	
	//清空按钮
	$("#clearBtn").click(function(){
		$('#userSearch').form('clear');
		$("#userdg").datagrid('load',{});
	})
	
	
	//js方法：序列化表单 			
	function serializeForm(form){
		var obj = {};
		$.each(form.serializeArray(),function(index){
			if(obj[this['name']]){
				obj[this['name']] = obj[this['name']] + ','+this['value'];
			} else {
				obj[this['name']] =this['value'];
			}
		});
		return obj;
	}

});
	

</script>
</head>
<body>
	<div id="lay" class="easyui-layout" data-options="fit:true">
		<div region="north" title="用户查询" style="height:90px"  collapsed="true"> 
			<form id="userSearch" method="post">
				<div align="center" style="padding:20px">
					用户名:<input name="name"type="text">&nbsp;&nbsp;&nbsp;
					年龄:<input name="age"type="text">&nbsp;&nbsp;&nbsp;
					
					<a id="searchBtn" class="easyui-linkbutton">查询</a>&nbsp;
					<a id="clearBtn" class="easyui-linkbutton">清空</a>
				</div>
				
			</form>
		</div>
		<div region="center">	
			<table id="userdg" class="easyui-datagrid">
				<thead>
					<tr>
						<th field="name" width="100">用户名</th>	
						<th field="age" width="100">年龄</th>
						<th field="pwd" width="100">密码</th>
						<th field="description" width="100">个人简介</th>
						
					</tr>
				</thead>
			</table>
		</div>
	</div>

	
	
	<div id="userDialog" class="easyui-dialog" title="新增用户" cache:false model=true draggable=false closed=true style="width:350px">
		<form id="userform"  method="post">
			<input type="hidden" name="id">
			<table>
				<tr>
					<td>用户名：</td>
					<td>
						<input type="text" name="name" class="easyui-validatebox" required=true>
					</td>
				</tr>
				<tr>
					<td>年龄：</td>
					<td>
						<input type="text" name="age" class="easyui-numberbox" data-options="min:0">
					</td>
				</tr>
				<tr>
					<td>密码：</td>
					<td>
						<input type="password" name="pwd" class="easyui-validatebox" required=true>
					</td>
				</tr>
				<tr>
					<td>个人简介：</td>
					<td>
						<textarea name="description" rows="3" ></textarea>
					</td>
				</tr>
				<tr align="right">
					<td colspan="2">
						<a id="saveBtn" class="easyui-linkbutton" iconCls='icon-save'>保存</a>
						<a id="redoBtn" class="easyui-linkbutton" iconCls='icon-redo'>重置</a>
					</td>
	    		</tr>   				
			</table>
		</form>
	
	</div>
	
</body>
</html>