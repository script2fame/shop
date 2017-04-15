/**
 * 后台管理员的js文件
 */
//初始化tabs组件
/*$(function(){
	$('#tabs').tabs({
		fit:true,
		border:false,
	});
});

//创建或选择用户模块的tab标签
function addTabs_user(title){
	if($('#tabs').tabs('exists',title)){
		$('#tabs').tabs('select',title);
	}else{
		$('#tabs').tabs('add',{
			title:title,
			closable:true,
			href:"#"
		});
	}
}

//创建或选择一级分类模块的tab标签

function addTabs_category(title){
	if($('#tabs').tabs('exists',title)){
		$('#tabs').tabs('select',title);
	}else{
		$('#tabs').tabs('add',{
			title:title,
			closable:true,
			href:"${pageContext.request.contextPath}/admincategory_findAllCategory.action"
		});
	}
}
function addCategory(title){
	if($('#tabs').tabs('exists',title)){
		$('#tabs').tabs('select',title);
	}else{
		$('#tabs').tabs('add',{
			title:title,
			closable:true,
			href:"${pageContext.request.contextPath}/admincategory_CategoryAddPage.action"
		});
	}
}
*/
function admin_switchToUsers(){
	
}
function admin_switchToCategory(){
	var center = $("#main").layout('panel','center');
	var url = "${pageContext.request.contextPath}/admincategory_findAllCategory.action";
	center.panel('refresh',url);
}
function admin_switchToCategorySecond(){
	
}
function admin_categoryAddPage(){
	var center = $("#main").layout('panel','center');
	var url = "${pageContext.request.contextPath}/admincategory_categoryAddPage.action";
	center.panel('refresh',url);
}
/**
 * 使用ajax异步提交数据添加一级分类
 */
function admincategory_save_do(){
	$.ajax({
		url:'${pageContext.request.contextPath}/admincategory_addCategory.action',
		type:'post',
		data:{
			cname:$('#admincategory_save_do_cname').val()
		},
		success:function(data){
			console.log(data);
			if(data!=null){
				var center = $("#main").layout('panel','center');
				var url = "${pageContext.request.contextPath}/admincategory_findAllCategory.action";
				center.panel('refresh',url);
			}
		},
		error:function(errordata){
			console.log(errordata);
		}
	});
}

/**
 * 使用ajax异步提交一级分类id移除一级分类
 */
function admincategory_remove_do(cid){
	$.messager.confirm('删除','是否确认删除该条记录?',function(flag){
		if(!flag){
			
			$.messager.show({
				width:300,
				height:160,
				title:'善意的提醒',
				msg:'您取消了删除!',
				timeout:3000
			});
			return false;
		}
		$.ajax({
			url:'${pageContext.request.contextPath}/admincategory_removeCategory.action',
			type:'post',
			data:{
				cid:cid
			},
			success:function(data){
				console.log(data);
				if(data!=null){
					$.messager.show({
						width:300,
						height:160,
						title:'么么哒',
						msg:'您的记录删除成功^_^!',
						timeout:3000
					});
					var center = $("#main").layout('panel','center');
					var url = "${pageContext.request.contextPath}/admincategory_findAllCategory.action";
					center.panel('refresh',url);
				}
			},
			error:function(errordata){
				console.log(errordata);
			}
		});
		
	});
	
}
/**
 * 跳转到编辑页面
 */
function admincategory_edit_do(cid){
	$.ajax({
		url:'${pageContext.request.contextPath}/admincategory_editCategory.action',
		type:'post',
		data:{
			cid:cid
		},
		success:function(data){
			if(data!=null){
				var center = $("#main").layout('panel','center');
				var url = "${pageContext.request.contextPath}/admincategory_editCategory.action?cid="+cid;
				center.panel('refresh',url);
				console.log("haha");
			}
		},
		error:function(errordata){
			console.log(errordata);
		}
	});
}
/**
 * 修改一级分类
 */
function admincategory_update_do(){
	$.ajax({
		url:'${pageContext.request.contextPath}/admincategory_updateCategory.action',
		type:'post',
		data:{
			cid:$('#admincategory_update_do_cid').val(),
			cname:$('#admincategory_update_do_cname').val()
		},
		success:function(data){
			console.log(data);
			if(data!=null){
				var center = $("#main").layout('panel','center');
				var url = "${pageContext.request.contextPath}/admincategory_findAllCategory.action";
				center.panel('refresh',url);
			}
		},
		error:function(errordata){
			console.log(errordata);
		}
	});
}