endpage = 0;
page = 0;
window.onload=function(){
        load()
    }

function load(){
	$.ajax({
        type: "POST",
        data:JSON.stringify({"isdelete":0,"page":page}),
        url: "/drp/search/buyRecord",
        contentType:"application/json;charset=UTF-8",
        success: function(result){
             console.log(result);
             if(result.datatwo % 10 == 0){
            	 endpage = result.datatwo /10;
             }else{
            	 endpage = parseInt(result.datatwo / 10);
             }
             console.log("当前页:"+(page+1));
             console.log("末页:"+(endpage+1));
             
             var htmlStr = template("History", {
            	 records: result.dataone
        	 });
             $("#tbody").html(htmlStr);
        },
        dataType: "json"
    });
}

function firstpage() {
	page = 0;
	load()
}
			
function lastpage() {
	if(page == 0){
		alert("已经是第一页了");
	}else{
		page = page - 1;;
		load()
     }
}

			
function nextpage() {
	if(page == endpage){
		alert("已经是最后一页了")
	}else{
		page = page + 1;
		load()
     }
   }


function endpages() {
		page = endpage;
		load();
   }
