$(document).ready(function(){
	if($("#levelInHeader").html() != undefined) {
		 $.ajax({
			url : "/header/levelInfo",
			type : "POST",
			cache : false,
			dataType : "json",
			success : function(data) {
				$("#levelInHeader").html(data.level);
//				$("#levelInHeader").html(data.level);
			//	alert(data.level)
				//alert(data.familyCustExp)
			},
			error : function(request, status, error) {
				var msg = "ERROR : " + request.status + "<br>"
				msg += +"내용 : " + request.responseText + "<br>" + error;
				console.log(msg);
			}
		});
	}
});