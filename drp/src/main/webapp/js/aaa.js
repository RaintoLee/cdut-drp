endpage = 0;
page = 0;
window.onload=function(){
        var obj=null;
        $.ajax({
            type: "POST",
            data:JSON.stringify({"isdelete":0}),
            url: "/drp/search/login",
            contentType:"application/json;charset=UTF-8",
            success: function(result){
                 console.log(result);
                 endpage = result.datatwo;
                $.each(result.dataone,function(i,n){
                    $("#tbody").append(
                        "<tr class='info'>" +
                        "<td>"+ n.userId +"</td>" +
                        "<td>"+ n.Location +"</td>" +
                        "<td>"+ n.loginTime +"</td>" +
                        "</tr>"
                    );
                });
            },
            dataType: "json"
        });
    }
$("#firstpage").click(function () {
	page = 0;
	$.ajax({
            type: "POST",
            data:JSON.stringify({"isdelete":0}),
            url: "/drp/search/login",
            contentType:"application/json;charset=UTF-8",
            success: function(result){
                 console.log(result);
                 endpage = result.datatwo;
                $.each(result.dataone,function(i,n){
                    $("#tbody").append(
                        "<tr class='info'>" +
                        "<td>"+ n.userId +"</td>" +
                        "<td>"+ n.Location +"</td>" +
                        "<td>"+ n.loginTime +"</td>" +
                        "</tr>"
                    );
                });
            },
            dataType: "json"
        });
     }
}
			
$("#lastpage").click(function () {
	if(page == 0){
		alert("已经是第一页了");
		location.reload(true);
    	setTimeout('parent.location.reload()',2000);
	}else{
		page = page - 1;;
		$.ajax({
            type: "POST",
            data:JSON.stringify({"isdelete":0,"page":page}),
            url: "/drp/search/login",
            contentType:"application/json;charset=UTF-8",
            success: function(result){
                 console.log(result);
                $.each(result.dataone,function(i,n){
                    $("#tbody").append(
                        "<tr class='info'>" +
                        "<td>"+ n.userId +"</td>" +
                        "<td>"+ n.Location +"</td>" +
                        "<td>"+ n.loginTime +"</td>" +
                        "</tr>"
                    );
                });
            },
            dataType: "json"
        });
     }
}

			
$("#nextpage").click(function () {
	if(page == endpage){
		alert("已经是最后一页了")
	}else{
		page = page - 1;
		$.ajax({
            type: "POST",
            data:JSON.stringify({"isdelete":0,"page":page}),
            url: "/drp/search/login",
            contentType:"application/json;charset=UTF-8",
            success: function(result){
                 console.log(result);
                $.each(result.dataone,function(i,n){
                    $("#tbody").append(
                        "<tr class='info'>" +
                        "<td>"+ n.userId +"</td>" +
                        "<td>"+ n.Location +"</td>" +
                        "<td>"+ n.loginTime +"</td>" +
                        "</tr>"
                    );
                });
            },
            dataType: "json"
        });
     }
   }
}

$("#endpage").click(function () {
	
		page = endpage;
		$.ajax({
            type: "POST",
            data:JSON.stringify({"isdelete":0,"page":page}),
            url: "/drp/search/login",
            contentType:"application/json;charset=UTF-8",
            success: function(result){
                 console.log(result);
                $.each(result.dataone,function(i,n){
                    $("#tbody").append(
                        "<tr class='info'>" +
                        "<td>"+ n.userId +"</td>" +
                        "<td>"+ n.Location +"</td>" +
                        "<td>"+ n.loginTime +"</td>" +
                        "</tr>"
                    );
                });
            },
            dataType: "json"
        });
     }
   
}