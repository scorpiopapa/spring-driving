var appName = '';	// app root context

// tab容器的id
var jtabId = '#tab';

/**
 * 用户信息
 * resendPage - 要重发消息的页面标识
 * userId - 当前登录用户id
 */ 
var UC = UC ? UC : new Object();

function showErrorMessage(text, title){
	var t = typeof(title) != 'undefined' && title != null ? title : '错误';
	showMessage(text, t, 'error');
}

function showInformationMessage(text, title){
	var t = typeof(title) != 'undefined' && title != null ? title : '提示';
	showMessage(text, t, 'info');
}

function showQuestionMessage(text, title){
	var t = typeof(title) != 'undefined' && title != null ? title : '确认';
	showMessage(text, t, 'question');
}

function showWarningMessage(text, title){
	var t = typeof(title) != 'undefined' && title != null ? title : '警告';
	showMessage(text, t, 'warning');
}

function showMessage(text, title, icon){
	$.messager.alert(title, text, icon);
}

function showConfirmMessage(text, callback, title){
	var t = typeof(title) != 'undefined' && title != null ? title : '确认';
	$.messager.confirm(t, text , callback);
}

function initDataGrid(jid, url, pageSize, pageList) {
	console.log(url);
	var size = 30;
	var list = [ 20, 30, 40, 50 ];

	if (pageSize && pageSize > 0) {
		size = pageSize;
	}

	if (pageList) {
		list = pageList;
	}

	$(jid).datagrid({
		loadFilter : function(data) {
			if (data.code == 0) {
				return data.record;
			} else {
				alert('列表数据加载失败');
				return "";
			}
		},
		url : url,
		pageSize : size,
		pageList : list,
		pagination : true,
		fit : true,
		singleSelect : false,
		method : 'post'
	});
}


function searchDataGrid(jid, json) {
	$(jid).datagrid('load', json);
}


function refreshDataGrid(jid, json){
	$(jid).datagrid('load', json);
}

function showAddDialog(jformId, jdlgId, title){
	$(jformId).form('clear');

	$(jdlgId).dialog({
		title: title ? title : '添加',
		modal: true
	});
	
	$(jdlgId).dialog('open');
}

function showEditDialog(jgridId, jformId, jdlgId, title, callback){
	var rows = $(jgridId).datagrid('getSelections');

	if(!rows || rows.length == 0){
		showWarningMessage('请选择要编辑的行');
	}else if(rows.length > 1){
		showWarningMessage('只能选择一行进行编辑');
	}else{
		$(jformId).form('load',rows[0]);
			$(jdlgId).dialog({
			title: title ? title : '编辑',
			modal: true
		});
		$(jdlgId).dialog('open');
		
		(callback && typeof(callback) === "function") && callback(rows[0]);
	} 
}

function closeDialog(jdlgId) {
	$(jdlgId).dialog('close')
}

function saveItem(jgridId, jformId, jdlgId, tableName) {
	//console.log(tableName);
	$(jformId).form('submit', {
		url : appName + 'save/' + tableName + '.json',
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(data) {
			console.log(data);
			var view = JSON.parse(data);
			console.log(view);
			if (view.code == 0) {
				// save success
				closeDialog(jdlgId);
				refreshDataGrid(jgridId);
			} else {
				showErrorMessage('保存失败');
			}
		}
	});
}

function deleteItem(jgridId, tableName){
    var rows = $(jgridId).datagrid('getSelections');
    
    if(rows && rows.length > 0){
		showConfirmMessage('是否删除选中的数据？', function(r){
			if(r){
		        var ids = '';
		        for(var i = 0; i < rows.length; i++){
		        	ids += rows[i].id + ',';
		        }
		        ids = ids.substring(0, ids.length - 1);
		        console.log(ids);
		        
	            $.post(appName + 'delete/' + tableName + '.json', {id:ids}, function(data){
	            console.log(data.code);
	                if (data.code != 0){
	                	showErrorMessage('删除失败');
	                }else{
	                	refreshDataGrid(jgridId);
	                }
	            }, 'json');
		    }
		});
    }else{
    	showWarningMessage('请选择要删除的行');
    }
}

function clearCriteria(jparentId){
	var pattern = jparentId + ' :input';
	var inputs = $(pattern);

	for(var i = 0; i < inputs.length; i++){
		inputs[i].value = '';
	}
}

function clearInput(jinputId){
	$(jinputId).val('');
}

function checkAndPush(form, url){
	if(!form || !form.title){
		showErrorMessage('通知标题不能为空');
		return;
	}
	
	if(!form || !form.text){
		showErrorMessage("通知内容不能为空");
		return;
	}

	push(form, url);
}

function push(form, url){
	$.ajax({
		type : "post",
		url : appName + url,
		//async: false,
		data : form,
		success : function(data) {
			//showInformationMessage('发送完毕');
		},
		error: function(){
			showErrorMessage('发送失败');
		}
	});
	
	showInformationMessage('发送完毕');
}

function getIdDescOrder(){
	var order = new Object();
	order.name = 'id';
	order.order = 'desc';
	
	var orderBy = new Array();
	orderBy.push(order);
	
	return JSON.stringify(orderBy);
}

function formatGender(val, row){
	var value = val;
	if(val == 'F'){
		value = '女'
	}else if(val == 'M'){
		value = '男'
	}
	return value;
}