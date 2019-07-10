/**
 * 代理商主页JavaScript
 *
 */
$(function() {
	var usr_nm = decodeURI(decodeURI(usr_nm));
	$.ajax({
		 async :false,
		 url : '/drp/',
         type : 'POST',
         dataType : 'json',
         contentType : "application/json;charest=utf-8",
         data : JSON.stringify({"usr_nm":usr_nm}),
         success : function (retValue) {
        	 var html = template("test", {
        		 users_detail: retValue.users_detail
        	 })
	        $("#user").html(html);
	        	 
	        	 var htmlStr = template("test", {
	        		 works_detail: retValue.works_detail
	        	 })
	        $("#temp").html(htmlStr);
         }
	});
	
});
