<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<br>
<form id="admin_categorySecondList_form" name="admin_categorySecondList_form"
	action="" method="post">
	<table cellSpacing="1" cellPadding="0" width="100%" align="center"
		bgColor="#f5fafe" border="0">
		<tbody>
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3"><strong>所有的二级分类</strong>
				</TD>
			</tr>
			<tr>
				<td class="ta_01" align="right">
					<button type="button" class="easyui-linkbutton"
						data-options="iconCls:'icon-reload',plain:true"
						onclick="admin_switchToCategorySecond(1)">刷新</button>
					<button type="button" class="easyui-linkbutton"
						data-options="iconCls:'icon-add',plain:true"
						onclick="admin_categorySecondAddPage()">添加</button>
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe">
					<table cellspacing="0" cellpadding="1" rules="all"
						bordercolor="gray" border="1" id="DataGrid1"
						style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
						<tr
							style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

							<td align="center" width="18%">序号</td>
							<td align="center" width="17%">二级分类名称</td>
							<td width="7%" align="center">编辑</td>
							<td width="7%" align="center">删除</td>
						</tr>
						<s:iterator var="categorySecond" value="pageBeanCategorySecond.list" status="status">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%"><s:property value="#categorySecond.csid" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:property value="#categorySecond.csname" /></td>
									<td align="center" style="HEIGHT: 22px">
									<button type="button" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="admincategorysecond_edit_do(<s:property value='#categorySecond.csid' />)">修改</button>
									</td>
									<td align="center" style="HEIGHT: 22px">
									<button type="button" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="admincategorysecond_remove_do(<s:property value='#categorySecond.csid' />)">删除</button>	
									</td>
								</tr>
							</s:iterator>
						<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
							<td colspan="4" align="center" width="18%">
								第<s:property value="pageBeanCategorySecond.currentPage"/>/<s:property value="pageBeanCategorySecond.totalPage"/>页
								<s:if test="pageBeanCategorySecond.currentPage != 1">
									<button type="button" class="easyui-linkbutton" data-options="plain:true" onclick="admin_switchToCategorySecond(1)">首页</button>
									<button type="button" class="easyui-linkbutton" data-options="plain:true" onclick="admin_switchToCategorySecond(<s:property value='pageBeanCategorySecond.currentPage'/> -1)">上一页</button>
								</s:if>
								<s:if test="pageBeanCategorySecond.currentPage != pageBeanCategorySecond.totalPage">
									<button type="button" class="easyui-linkbutton" data-options="plain:true" onclick="admin_switchToCategorySecond(<s:property value='pageBeanCategorySecond.currentPage'/> +1)">下一页</button>
									<button type="button" class="easyui-linkbutton" data-options="plain:true" onclick="admin_switchToCategorySecond(<s:property value='pageBeanCategorySecond.totalPage'/> )">尾页</button>									
								</s:if>
							</td>
						</tr>		
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</form>