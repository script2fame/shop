/**
 * 后台管理员的js文件
 */

//初始化tabs组件
$(function(){
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