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
function admin_switchToCategory(){
	var center = $("#main").layout('panel','center');
	var url = "${pageContext.request.contextPath}/admincategory_findAllCategory.action";
	center.panel('refresh',url);
}
function admin_switchToCategorySecond(currentPage){
	var center = $('#main').layout('panel','center');
	var url = "${pageContext.request.contextPath}/admincategorysecond_findAllcategorySecond.action?currentPage="+currentPage;
	center.panel('refresh',url);
}
/**
 * 跳转到后台商品管理页面
 * @param currentPage
 */
function admin_switchToProduct(currentPage){
	var center = $('#main').layout('panel','center');
	var url = "${pageContext.request.contextPath}/adminproduct_findAllProduct.action?currentPage="+currentPage;
	center.panel('refresh',url);
}
/**
 * 跳转到订单页面
 */
function admin_switchToOrders(currentPage){
	var center = $('#main').layout('panel','center');
	var url = "${pageContext.request.contextPath}/adminorders_findAllOrdersWithPage.action?currentPage="+currentPage;
	center.panel('refresh',url);
}
/**
 * 跳转到商品分析统计页面
 */
function admin_switchToStatistics(){
	$.ajax({			
		url: "${pageContext.request.contextPath}/adminorders_statisticsPage.action?time="+new Date(),
		type:'get',
		cache : false,
		data:{},
		success: function(data){
			var center = $('#main').layout('panel','center');
			var url = "${pageContext.request.contextPath}/adminorders_statisticsPage.action?time="+new Date();
			center.panel('refresh',url);
			}
		});
}
/**
 *  跳转到商品统计页面
 */
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
/**
 * 跳转到二级分类的添加页面
 */
function admin_categorySecondAddPage(){
	$.ajax({
		url:'${pageContext.request.contextPath}/admincategorysecond_editCategorySecond.action',
		type:'post',
		data:{},
		success:function(data){
			if(data!=null){
				var center = $("#main").layout('panel','center');
				var url = "${pageContext.request.contextPath}/admincategorysecond_editCategorySecond.action";
				center.panel('refresh',url);
			}
		},
		error:function(errordata){
			console.log(errordata);
		}
	});
}
/**
 * 添加二级分类
 */
function admincategorysecond_save_do(){
	$.ajax({
		url:'${pageContext.request.contextPath}/admincategorysecond_addCategorySecond.action',
		type:'post',
		data:{
			cs_cid:$('#admincategorysecond_save_do_cid').val(),
			csname:$('#admincategorysecond_save_do_csname').val()
		},
		success:function(data){
			if(data!=null){
				var center = $("#main").layout('panel','center');
				var url = "${pageContext.request.contextPath}/admincategorysecond_findAllcategorySecond.action?currentPage=1";
				center.panel('refresh',url);
			}
		},
		error:function(errordata){
			console.log(errordata);
		}
	});
}
/**
 * 删除二级分类
 */
function admincategorysecond_remove_do(csid){
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
		url:'${pageContext.request.contextPath}/admincategorysecond_deleteCategorySecond.action',
		type:'post',
		data:{
			csid:csid,
		},
		success:function(data){
			if(data!=null){
				$.messager.show({
					width:300,
					height:160,
					title:'么么哒',
					msg:'您的记录删除成功^_^!',
					timeout:3000
				});
				var center = $("#main").layout('panel','center');
				var url = "${pageContext.request.contextPath}/admincategorysecond_findAllcategorySecond.action?currentPage=1";
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
 * 跳转到修改二级分类的页面
 * @param csid
 */
function admincategorysecond_edit_do(csid){
	$.ajax({
		url:'${pageContext.request.contextPath}/admincategorysecond_updateCategorySecond_page.action',
		type:'post',
		data:{
			csid:csid
		},
		success:function(data){
			if(data!=null){
				var center = $("#main").layout('panel','center');
				var url = "${pageContext.request.contextPath}/admincategorysecond_updateCategorySecond_page.action?csid="+csid;
				center.panel('refresh',url);
			}
		},
		error:function(errordata){
			console.log(errordata);
		}
	});
}
/**
 * 修改二级分类
 */
function admincategorysecond_updateCategorySecond_do(){
	$.ajax({
		url:'${pageContext.request.contextPath}/admincategorysecond_updateCategorySecond_do.action',
		type:'post',
		data:{
			csid:$('#admincategorysecond_update_do_csid').val(),
			csname:$('#admincategorysecond_update_do_csname').val(),
			cs_id_update:$('#admincategorysecond_update_do_cid').val()
		},
		success:function(data){
			if(data!=null){
				var center = $("#main").layout('panel','center');
				var url = "${pageContext.request.contextPath}/admincategorysecond_findAllcategorySecond.action?currentPage=1";
				center.panel('refresh',url);
			}
		},
		error:function(errordata){
			console.log(errordata);
		}
	});
}
/**
 * 跳转到添加商品页面
 */
function admin_productAddPage(){
	$.ajax({
		url:'${pageContext.request.contextPath}/adminproduct_addProduct_page.action',
		type:'post',
		data:{
		},
		success:function(data){
			if(data!=null){
				var center = $("#main").layout('panel','center');
				var url = "${pageContext.request.contextPath}/adminproduct_addProduct_page.action";
				center.panel('refresh',url);
			}
		},
		error:function(errordata){
			console.log(errordata);
		}
	});
}

/**
 * 添加商品
 */
function adminproduct_save_do(){
	$(document).ready(
			function(){
				var options = {
						url:"${pageContext.request.contextPath}/adminproduct_addProduct_do.action",
						type:"post",
						dataType:"script",
						success:function (data){
							if(data!=null){
								var center = $("#main").layout('panel','center');
								var url = "${pageContext.request.contextPath}/adminproduct_findAllProduct.action?currentPage=1";
								center.panel('refresh',url);
							}
						},
						error:function(data){
							console.log(data);
						}
				};//options结束
				$('#admin_productAdd_form').ajaxSubmit(options);
				return false;
			});
}
/**
 * 删除二级分类
 */
function adminproduct_remove_do(pid){
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
		url:'${pageContext.request.contextPath}/adminproduct_remove_do.action',
		type:'post',
		data:{
			pid:pid
		},
		success:function(data){
			if(data!=null){
				$.messager.show({
					width:300,
					height:160,
					title:'么么哒',
					msg:'您的记录删除成功^_^!',
					timeout:3000
				});
				var center = $("#main").layout('panel','center');
				var url = "${pageContext.request.contextPath}/adminproduct_findAllProduct.action?currentPage=1";
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
 * 跳转到修改的编辑页面
 * @param pid
 */
function adminproduct_edit_page(pid){
	$.ajax({
		url:'${pageContext.request.contextPath}/adminproduct_edit_page.action',
		type:'post',
		data:{
			pid:pid
		},
		success:function(data){
				var center = $("#main").layout('panel','center');
				var url = "${pageContext.request.contextPath}/adminproduct_edit_page.action?pid="+pid;
				center.panel('refresh',url);
		},
		error:function(errordata){
			console.log(errordata);
		}
	});
}

/**
 * 修改商品信息
 */
function adminproduct_update_do(){
	$(document).ready(
			function(){
				var options = {
						url:"${pageContext.request.contextPath}/adminproduct_updateProduct_do.action",
						type:"post",
						dataType:"script",
						success:function (data){
							if(data!=null){
								var center = $("#main").layout('panel','center');
								var url = "${pageContext.request.contextPath}/adminproduct_findAllProduct.action?currentPage=1";
								center.panel('refresh',url);
							}
						},
						error:function(data){
							console.log(data);
						}
				};//options结束
				$('#admin_productUpdate_form').ajaxSubmit(options);
				return false;
			});
}

/**
 * 异步加载订单项
 */
function adminOrders_viewOrderItem(oid){
	var btn = $('#btn_adminOrders_viewOrderItem');
	if(btn.val() == 'adminOrders_viewOrderItem'){
		$.ajax({
			url:'${pageContext.request.contextPath}/adminorders_viewOrderItem.action',
			type:'post',
			data:{
				oid:oid
			},
			success:function(data){
				$('#div_viewOrderItem_'+oid).html(data);
			},
			error:function(errordata){
				console.log(errordata);
			}
		});
		btn.val('close');
	}else{
		btn.val('adminOrders_viewOrderItem');
		$('#div_viewOrderItem_'+oid).html('');
	}
}
function adminorders_updateStatus(oid){
	$.ajax({
		url:'${pageContext.request.contextPath}/adminorders_updateStatus.action',
		type:'post',
		data:{
			oid:oid
		},
		success:function(data){
			if(data!=null){
				var center = $("#main").layout('panel','center');
				var url = "${pageContext.request.contextPath}/adminorders_findAllOrdersWithPage.action?currentPage=1";
				center.panel('refresh',url);
			}
		},
		error:function(errordata){
			console.log(errordata);
		}
	});
}
/**
 * 销售统计柱状图
 */
function admin_statistics_bar(){
	var myChart=echarts.init(document.getElementById('ProductStatistics_bar'));
	var option={
		title : {
			text : '周销售前五商品柱状图',
			x:'center'
		},
		tooltip : {},
		legend : {
			orient: 'vertical',
	        left: 'left',
			data : ['数量']
		},
		xAxis : {
			data : ["12", "56", "63", "89", "15"]
		},
		yAxis : {},
		series : [ {
			name : '数量',
			type : 'bar',
			data : [1180, 1088, 1590, 1589, 1491]
		} ]
	};
	myChart.setOption(option);  	
}
/**
 * 销售统计饼状图
 */
function admin_statistics_pie(){
	var myChart=echarts.init(document.getElementById('ProductStatistics_pie'));
	option = {
		    title : {
		        text: '周销售前五商品饼状图',
		        x:'center'
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: ['12','56','63','89','15']
		    },
		    series : [
		        {
		            name: '周销售前五商品饼状图',
		            type: 'pie',
		            radius : '90%',
		            center: ['50%', '55%'],
		            data:[
		                {value:1180, name:'12'},
		                {value:1088, name:'56'},
		                {value:1590, name:'63'},
		                {value:1589, name:'89'},
		                {value:1491, name:'15'}
		            ],
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
		if (option && typeof option == "object") {
		    myChart.setOption(option, true);
		} 	
}
/*
 * 条件查询商品
 */
function admin_search_product(currentPage){
	var productType = $("#admin_inp_search_product").val();
	$.ajax({
		url:'${pageContext.request.contextPath}/adminproduct_findBySearch.action',
		type:'post',
		data:{
			currentPage:currentPage,
			productType:productType
		},
		success:function(data){
			if(data!=null){
				var center = $("#main").layout('panel','center');
				var url = "${pageContext.request.contextPath}/adminproduct_findBySearch.action?currentPage="+currentPage+"&productType="+productType;
				center.panel('refresh',url);
			}
		},
		error:function(errordata){
			console.log(errordata);
		}
	});
}

/**
 * 条件查询订单
 */
function admin_search_orders(){
	var orders_search_news = $("#admin_inp_search_orders").val();
	$.ajax({
		url:'${pageContext.request.contextPath}/adminorders_findOrdersBySearch.action?currentPage=1',
		type:'post',
		data:{
			orders_search_news:orders_search_news
		},
		success:function(data){
			if(data!=null){
				var center = $("#main").layout('panel','center');
				var url = "${pageContext.request.contextPath}/adminorders_findOrdersBySearch.action?currentPage=1&orders_search_news="+orders_search_news;
				center.panel('refresh',url);
			}
		},
		error:function(errordata){
			console.log(errordata);
		}
	});
}
/**
 * 条件查询商品下一页或者上一页
 * @param currentPage
 * @param productType
 */
function admin_search_product_productType(currentPage,productType){
	$.ajax({
		url:'${pageContext.request.contextPath}/adminproduct_findBySearch.action',
		type:'post',
		data:{
			currentPage:currentPage,
			productType:productType
		},
		success:function(data){
			if(data!=null){
				var center = $("#main").layout('panel','center');
				var url = "${pageContext.request.contextPath}/adminproduct_findBySearch.action?currentPage="+currentPage+"&productType="+productType;
				center.panel('refresh',url);
			}
		},
		error:function(errordata){
			console.log(errordata);
		}
	});
}