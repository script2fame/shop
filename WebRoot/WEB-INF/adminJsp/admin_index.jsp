<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>后台管理主界面</title>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin.css">

</head>
<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height:100px;background:#eee">
		<div class="log">网上商城后台管理系统</div>
		<div class='logout'>您好，<span id='username'></span>|<a href="#">退出系统</a></div>
	</div>
	
	<div data-options="region:'west',title:'功能导航',split:false" style="width:200px;">
    	<ul class="easyui-tree">
    		<li>
    			<span>网上商城管理系统</span>
    			<ul>
    				<li>
    					<span><div >用户管理</div></span>
    					<ul>
    						<li><span><div onclick="addTabs_users('用户管理')">用户管理</div></span></li>
    					</ul>
    				</li>
    				<li>
    					<span><div >一级分类管理</div></span>
    					<ul>
    						<li><span><div onclick="addTabs_category('一级分类管理')">一级分类管理</div></span></li>
    					</ul>
    				</li>
    				<li>
    					<span><div>二级分类管理</div></span>
    					<ul>
    						<li><span><div>二级分类管理</div></span></li>
    					</ul>
    				</li>
    			</ul>
    		</li>
    	</ul>
    </div> 
    
    <div data-options="region:'center',title:'桌面',noheader:true" style="padding:0 0;background:#eee;">
    	<div id="tabs">
    		<div title="初始界面" style="padding:0 5px;display:block;">
    			管理员主界面
    		</div>
    	</div>
    </div>
    <div data-options="region:'south',title:'footer',split:false,noheader:true" style="height:50px;text-align:center;padding:15px;">临川大葱哥网上商城&nbsp;&nbsp;&nbsp;&nbsp;版权所有 翻版必究</div>    
</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.params.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin.js"></script>
</html>