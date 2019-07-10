/**
 * 作者主页JavaScript
 *
 */
$(function() {
	var usr_nm = getQueryString("usr_nm");
	usr_nm = decodeURI(decodeURI(usr_nm));
	$.ajax({
		 async :false,
		 url : '/drp/authorPage',
         type : 'POST',
         dataType : 'json',
         contentType : "application/json;charest=utf-8",
         data : JSON.stringify({"usr_nm":usr_nm}),
         success : function (retValue) {
        	 var html = template("test1", {
        		 users_detail: retValue.dataone
        	 })
        $("#user").html(html);
        	 
        	 var htmlStr = template("test", {
        		 works_detail: retValue.datatwo
        	 })
        $("#temp").html(htmlStr);
         }
	});
	
});
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}