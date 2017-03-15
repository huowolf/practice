$(function() {
	
	$("#tree").tree({
		//data : treeData,
		url: "loadTree",
		lines: true,
		onClick: function(node){
			if(node.attributes.url != null){
				openTab(node.text,node.attributes.url,node.iconCls);
			}
		}

	});
	
	function openTab(title, url,iconCls){
		if ($('#tt').tabs('exists', title)){
			$('#tt').tabs('select', title);
		} else {
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
			$('#tt').tabs('add',{
				title:title,
				content:content,
				iconCls:iconCls,
				closable:true
			});
		}
	}
});