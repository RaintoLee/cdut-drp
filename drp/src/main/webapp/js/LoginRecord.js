endpage = 0;
page = 0;
window.onload=function(){
        var obj=null;
        $.ajax({
            type: "POST",
            data:JSON.stringify({"isdelete":0}),
            url: "/drp/search/loginRecord",
            contentType:"application/json;charset=UTF-8",
            success: function(result){
                 console.log(result);
                 if(result.datatwo / 10 == 0){
                	 endpage = result.datatwo /10;
                 }else{
                	 endpage = parseInt(result.datatwo / 10);
                 }
                 console.log("当前页:"+(page+1));
                 console.log("末页:"+(endpage+1));
                 
                 var htmlStr = template("loginHistory", {
                	 login_records: result.dataone
            	 });
                 $("#tbody").html(htmlStr);
            },
            dataType: "json"
        });
    }
function firstpage() {
	page = 0;
	$.ajax({
            type: "POST",
            data:JSON.stringify({"isdelete":0}),
            url: "/drp/search/loginRecord",
            contentType:"application/json;charset=UTF-8",
            success: function(result){
                 console.log(result);
                 $("#tbody").html("");
                 if(result.datatwo / 10 == 0){
                	 endpage = result.datatwo /10;
                 }else{
                	 endpage = parseInt(result.datatwo / 10);
                 }
                 console.log("当前页:"+(page+1));
                 console.log("末页:"+(endpage+1));

                 var htmlStr = template("loginHistory", {
                	 login_records: result.dataone
            	 });
                 $("#tbody").html(htmlStr);
            },
            dataType: "json"
        });
	
}
			
function lastpage() {
	if(page == 0){
		alert("已经是第一页了");
	}else{
		page = page - 1;;
		$.ajax({
            type: "POST",
            data:JSON.stringify({"isdelete":0,"page":page}),
            url: "/drp/search/loginRecord",
            contentType:"application/json;charset=UTF-8",
            success: function(result){
                 console.log(result);
                 console.log("当前页:"+(page+1));
                 console.log("末页:"+(endpage+1));
                 
                 var htmlStr = template("loginHistory", {
                	 login_records: result.dataone
            	 });
                 $("#tbody").html(htmlStr);
            },
            dataType: "json"
        });
     }
}

			
function nextpage() {
	if(page == endpage){
		alert("已经是最后一页了")
	}else{
		page = page + 1;
		$.ajax({
            type: "POST",
            data:JSON.stringify({"isdelete":0,"page":page}),
            url: "/drp/search/loginRecord",
            contentType:"application/json;charset=UTF-8",
            success: function(result){
                 console.log(result);
                 console.log("当前页:"+(page+1));
                 console.log("末页:"+(endpage+1));
                 
                 var htmlStr = template("loginHistory", {
                	 login_records: result.dataone
            	 });
                 $("#tbody").html(htmlStr);
            },
            dataType: "json"
        });
     }
   }


function endpages() {
		page = endpage
		$.ajax({
            type: "POST",
            data:JSON.stringify({"isdelete":0,"page":page}),
            url: "/drp/search/loginRecord",
            contentType:"application/json;charset=UTF-8",
            success: function(result){
                 console.log(result);
                 console.log("当前页:"+(page+1));
                 console.log("末页:"+(endpage+1));
                 
                 var htmlStr = template("loginHistory", {
                	 login_records: result.dataone
            	 });
                 $("#tbody").html(htmlStr);
            },
            dataType: "json"
        });
     
   }
