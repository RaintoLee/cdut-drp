/**
 * 作品注册管理JavaScript
 *
 *（目前使用协议表中的数据作为展示）
 */

$(function() {
	getDataTables();
});


//显示我申请的代理
function getDataTables() { 
	var _data = {
			"start": 0,
			"offset": 10
	};
	$.ajax({
		async : false,
		url : '/drp/admin/worksManageList',
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
			"works_id": $(obj).attr("value"),
			"works_isvalid_flg": 1
	};
	$.ajax({
		async : false,
		url : '/drp/admin/worksPass',
		type : 'POST',
		dataType : 'json',
		contentType : "application/json;charest=utf-8",
		data : JSON.stringify(_data),
		success : function(retValue) {
			if(retValue.result==1){
				alert("已通过改作品审核");
				window.location.reload();
			}
		}
	});
}

//不通过审核
function disAgree(obj){
	var _data = {
			"works_id": $(obj).attr("value"),
			"works_isvalid_flg": 2
	};
	$.ajax({
		async : false,
		url : '/drp/admin/worksPass',
		type : 'POST',
		dataType : 'json',
		contentType : "application/json;charest=utf-8",
		data : JSON.stringify(_data),
		success : function(retValue) {
			if(retValue.result==1){
				alert("已拒绝该作品审核");
				window.location.reload();
			}
		}
	});
}
