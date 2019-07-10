
$(function () {
	
	function loginSubmit(){
		var userName = $("#userNameInput").val();
		var password = $("#passwordInput").val();
		var _data = {
				'usr_nm' : userName,
				'usr_pwd' : password
		};
		if(userName == null || userName ==''){
			alert("用户ID不能为空！");
			return false;
		}else if(password == null || password == ''){
			alert("用户密码不能为空！");
			return false;
		}else{
			$.ajax({
				async:false,
				 url: '/drp/loginSubmit',
	             type: 'POST',
	             dataType: 'json',
	             contentType: "application/json;charest=utf-8",
	             data: JSON.stringify(_data),
	             success: function (data) {
	            	    if(data.usr_pwd!=password){
	            	    	  alert("用户ID或者密码错误");
	 	                     return false;
	            	    }
	            	 	if(data.usr_nm!=null && data.usr_pub_key==null){
	            	 		window.location.href="/drp/generateKey?usr_id="+data.usr_id;
	            	 	}else {
	                     //登录
	                     location.href="/drp/";
	                 } 
	             }
			});
		}
	}
	
	/*
	 * 绑定click事件 
	 */
	$("#loginBtn").click(function(){
		loginSubmit();
	});
	
	
	/*
	 * 绑定enter事件
	 */
	$("#passwordInput").bind('keyup',function(event){
		var key = event.keyCode;
		if(key == 13){
			loginSubmit();
		}
	})
})