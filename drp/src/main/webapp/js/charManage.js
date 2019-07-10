/**
 * 角色申请管理JavaScript
 *
 */

$(function() {
	getDataTables();
});


//显示角色申请
function getDataTables() { 
	var _data = {
			"start": 0,
			"offset": 10
	};
	$.ajax({
		async : false,
		url : '/drp/admin/charManageList',
		type : 'POST',
		dataType : 'json',
		contentType : "application/json;charest=utf-8",
		data : JSON.stringify(_data),
		success : function(retValue) {
			console.log(retValue);	
			alert("查看");
			}
		}
	);
	
}

//通过审核
function agree(obj){
	var _data = {
			"usr2role_id": $(obj).attr("value"),
			"data_isvalid_flg": 1
	};
	$.ajax({
		async : false,
		url : '/drp/admin/pass',
		type : 'POST',
		dataType : 'json',
		contentType : "application/json;charest=utf-8",
		data : JSON.stringify(_data),
		success : function(retValue) {
			if(retValue.result==1){
				alert("已通过改角色审核");
				window.location.reload();
			}
		}
	});
}
//不通过审核
function disAgree(obj){
	alert("可以触发");
	var _data = {
			"usr2role_id": $(obj).attr("value"),
			"data_isvalid_flg": 2
	};
	$.ajax({
		async : false,
		url : '/drp/admin/pass',
		type : 'POST',
		dataType : 'json',
		contentType : "application/json;charest=utf-8",
		data : JSON.stringify(_data),
		success : function(retValue) {
			if(retValue.result==1){
				alert("已拒绝该角色审核");
				window.location.reload();
			}
		}
	});
}
