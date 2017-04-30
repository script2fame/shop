<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
		<form id="admin_categorysecondUpdate" name="admin_categorysecondUpdate" action="" method="post" >
			&nbsp;
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<strong><STRONG>修改二级分类</STRONG>
						</strong>
					</td>
				</tr>

				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						二级分类名称：
					</td>
					<td class="ta_01" bgColor="#ffffff" >
						<input  type="hidden" value="<s:property value='model.csid'/>" id="admincategorysecond_update_do_csid"/>
						<input type="text" name="cname" value="<s:property value='model.csname'/>" id="admincategorysecond_update_do_csname" class="bg"/>
					</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						一级分类名称：
					</td>
					<td class="ta_01" bgColor="#ffffff" >
					<select id="admincategorysecond_update_do_cid">
						<s:iterator var='category' value='categoryList' >
							<option   value="<s:property value='#category.cid' />" <s:if test="model.category.cid==#category.cid">selected</s:if> ><s:property value='#category.cname' /></option>
						</s:iterator>
					</select>
					</td>
				</tr>
			
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<button type="button" id="admincategorysecond_save_do_submit" value="确定" class="button_ok" onclick="admincategorysecond_updateCategorySecond_do()">
							&#30830;&#23450;
						</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
						<span id="Label1"></span>
					</td>
				</tr>
			</table>
</form>