/*
 * 浏览记录的JS
 */

$(function() {
	getDataTables();
});

function getDataTables() {
	var _data = {
			"start": 0,
			"offset": 10
	};
	$.ajax({
		async : false,
		url : '/drp/showBrowsingInfo',
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
