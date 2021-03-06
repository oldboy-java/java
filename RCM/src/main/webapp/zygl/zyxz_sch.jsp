<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.qtrmoon.com/tags-dictionary" prefix="dc"%>
<!DOCTYPE html>
<html>
<head>
	<title>资源下载表查询</title><!--zyxz-->
	<%@include file="/common/include.jsp" %>

<script>
var winToggle;
$(function(){
	winToggle=new QWinToggle().regist(["zyxzEditWin","zyxzViewWin"]);
	var pager = $('#zyxzGrid').datagrid().datagrid('getPager');	// get the pager of datagrid
	pager.pagination({
		buttons:[],
	});			
})

<%-- 执行查询，点击查询按钮时调用 --%>
function doZyxzSearch(){
	if(!$("#_form").form("validate")){
		return;
	}
	$('#zyxzGrid').datagrid('load',{
		zyid:zyidDict.getValue(),
		xzrid:$('#xzrid').val(),
		xzsjBeg:$('#xzsjBeg').datetimebox("getValue"), 
		xzsjEnd:$('#xzsjEnd').datetimebox("getValue")/* ,
		xxid:$('#xxid').val() */
	});
}

<%-- 查询列表datagrid的"操作"列的内容渲染，若有其他操作请修改此方法 --%>
function formatZyxzOper(val,row,index){  
    return "&emsp;<a href='javascript:void(0)' onclick='updZyxz("+val+")' class='_oper' title='修改'>&nbsp;<i class='icon-edit'></i>&nbsp;</a>"
    			+"<a href='javascript:void(0)' onclick='vieZyxz("+val+")' class='_oper' title='查看'>&nbsp;<i class='icon-info-sign'></i>&nbsp;</a>&emsp;"; 
} 

<%-- 打开修改页面，点击记录行的"修改"链接时调用 --%>
function updZyxz(id){
	winToggle.open("zyxzEditWin");
	$('#zyxzEditWin').get(0).contentWindow.load(id);
} 

<%-- 打开查看页面，点击记录行的"查看"链接时调用 --%>
function vieZyxz(id){
	winToggle.open("zyxzViewWin");
	$('#zyxzViewWin').get(0).contentWindow.load(id);
}

<%-- 删除记录 --%>
function delZyxz(){
	var checkedItems = $('#zyxzGrid').datagrid('getChecked');
	var ids = [];
	$.each(checkedItems, function(index, item){
		ids.push(item.id);
	});
	if(ids.length==0){
		$.messager.alert('敬告','请选择删除的记录？');
		return;
	}
	$.messager.confirm('敬告','删除选定记录？',function(r){
		if(r){
			$.get("/${projectName}/zygl/zyxz/delZyxz.action",{ids:ids},function(data){
				$('#zyxzGrid').datagrid('reload');
			});
		}
	});
}

<%-- 刷新数据，当添加或修改了数据后调用。从upd页中调用。 --%>
function reloadData(){
	$('#zyxzEditWin').dialog('close');
	$('#zyxzGrid').datagrid('reload');
}
</script>
</head>
<body>
<table id="zyxzGrid" title="" fitcolumns=true
	data-options="singleSelect:false,pagination:true,rownumbers:true,fit:true,striped:true,toolbar:'#zyxzGridToolbar',collapsible:true,url:'/${projectName}/zygl/zyxz/schZyxz.action',method:'get'">
	<thead>
		<tr>
			<th data-options="field:'checkbox',checkbox:true" ></th>
			<th data-options="field:'zyid',width:80,sortable:true">资源</th>
			<th data-options="field:'xzrid',sortable:true">下载人</th>
			<th data-options="field:'xzsj',sortable:true">下载时间</th>
			<!-- <th data-options="field:'xxid',sortable:true">学校ID</th> -->

			<th data-options="field:'_oper',align:'center',formatter:formatZyxzOper">操作</th>
		</tr>
	</thead>
</table>
<%-- 查询条件设定栏 --%>
<div id="zyxzGridToolbar">
	<div class="opline">
		<a href="javascript:updZyxz(0)" class="btn btn-success"><span class="icon-plus" style="font-size:16px"></span> <span style="font-size:14px">添加</span></a>&emsp;
		<a href="javascript:delZyxz()" class="btn btn-danger"><span class="icon-trash" style="font-size:16px"></span> <span style="font-size:14px">删除</span></a>
	</div>
	<form id="_form" class="shline">
	资源：<dc:insertList name="zyid" dictId="ZD_ZYMC" style="combo_normal" />
	下载人：<input name='xzrid' id="xzrid" class='easyui-textbox' value='' data-options="iconWidth:16,
icons: [{iconCls:'icon-clear',handler: function(e){$(e.data.target).textbox('setValue', '');}
}]"/>

下载时间：<input name="xzsjBeg" id="xzsjBeg" class="easyui-datetimebox" style="width:100px;" data-options="iconWidth:16,
icons: [{iconCls:'icon-clear',handler: function(e){$(e.data.target).textbox('setValue', '');}
}]"/>
&gt;&gt;
	<input name="xzsjEnd" id="xzsjEnd" class="easyui-datetimebox" style="width:100px;" data-options="iconWidth:16,
icons: [{iconCls:'icon-clear',handler: function(e){$(e.data.target).textbox('setValue', '');}}],
validType:['compareDate[xzsjBeg]']"/>
	<!-- 学校ID：<input name='xxid' id="xxid" class='easyui-textbox' value='' data-options="iconWidth:16,
icons: [{iconCls:'icon-clear',handler: function(e){$(e.data.target).textbox('setValue', '');}
}]"/> -->

	<a href="javascript:void(0)" onclick="doZyxzSearch()" class="btn btn-info"><span class="icon-search" style="font-size:16px"></span> <span style="font-size:14px">查询</span></a>
	</form>
</div>


<%-- 修改or添加面板 --%>
<iframe id="zyxzEditWin" src="/${projectName}/zygl/zyxz/page.action?page=zyxz_upd" class="easyui-dialog" data-options="iconCls:'icon-save',buttons: '#zyxzEditButton'" title="编辑资源下载表" style="width:500px;height:500px;padding:5px;top:5000px" scrolling="auto" frameborder="0"></iframe>
<div id="zyxzEditButton">
	<a href="javascript:void(0)" onclick="$('#zyxzEditWin').get(0).contentWindow.submitForm()" class="easyui-linkbutton" iconCls="icon-ok">保 存</a>
	<a href="javascript:void(0)" onclick="$('#zyxzEditWin').dialog('close');" class="easyui-linkbutton" iconCls="icon-remove" >取 消</a>
</div>
<%-- 查看面板 --%>
<iframe id="zyxzViewWin" src="/${projectName}/zygl/zyxz/page.action?page=zyxz_vie" class="easyui-dialog" data-options="iconCls:'icon-save',buttons: '#zyxzViewButton'" title="查看资源下载表" style="width:500px;height:500px;padding:5px;top:5000px" scrolling="auto" frameborder="0"></iframe>
<div id="zyxzViewButton">
	<a href="javascript:$('#zyxzViewWin').dialog('close');" class="easyui-linkbutton" iconCls="icon-remove" >关 闭</a>
</div>
</body>
</html>
