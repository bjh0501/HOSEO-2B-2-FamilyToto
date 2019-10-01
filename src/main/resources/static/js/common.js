$(document).ready(function(){
	if($("#levelInHeader").html() != undefined) {
		asyncExp();
	}
	
	$(".dropdown-menu, .show").on('click', function (event) {
	    event.stopImmediatePropagation()
	});
	
	$('[data-toggle="headerExpPercent"]').tooltip()
});

function asyncExp() {
	 $.ajax({
		url : "/header/levelInfo",
		type : "POST",
		cache : false,
		dataType : "json",
		success : function(data) {
			var totalExp = data.endExp - data.startExp;
			var currentExp = data.familyCustExp - data.startExp;
			var percent = parseInt((currentExp/totalExp)*100);
			
			$("#levelInHeader").html(data.level);
			$("#expBarInHeader").html(currentExp + "/" + totalExp);
			
			$("#expBarInHeader").animate({
			        width: percent+"%"
		    }, 300 );

			$('#expInHeader').attr('data-original-title',percent+"%");
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

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function removeComma(str) {
	n = parseInt(str.replace(/,/g,""));
	return n;
}

function setCookie(cookie_name, value, days) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + days);
	// 설정 일수만큼 현재시간에 만료값으로 지정
	
	var cookie_value = escape(value) + ((days == null) ? '' : ';    expires=' + exdate.toUTCString());
	document.cookie = cookie_name + '=' + cookie_value;
}

function getCookie(cookie_name) {
	var x, y;
	var val = document.cookie.split(';');

	for (var i = 0; i < val.length; i++) {
		x = val[i].substr(0, val[i].indexOf('='));
		y = val[i].substr(val[i].indexOf('=') + 1);
		x = x.replace(/^\s+|\s+$/g, ''); // 앞과 뒤의 공백 제거하기
		
		if (x == cookie_name) {
			return unescape(y); // unescape로 디코딩 후 값 리턴
		}
	}
}

var deleteCookie = function(name) {
	document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;';
}
