document.write("<script src='/js/jquery.cookie.min.js'></script>");

$(document).ready(function() {
	if ($("#levelInHeader").html() != undefined) {
		asyncExp();
	}

	$(".dropdown-menu, .show").on('click', function(event) {
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
			var percent = parseInt((currentExp / totalExp) * 100);

			$("#levelInHeader").html(data.level);
			$("#expBarInHeader").html(currentExp + "/" + totalExp);

			$("#expBarInHeader").animate({
				width : percent + "%"
			}, 300);

			// $('#expInHeader').attr('data-original-title', percent + "%");
			// $("#levelInHeader").html(data.level);
			// alert(data.level)
			// alert(data.familyCustExp)
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
	n = parseInt(str.replace(/,/g, ""));
	return n;
}

function setCookie(cookie_name, value, days) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + days);
	// 설정 일수만큼 현재시간에 만료값으로 지정

	var cookie_value = escape(value)
			+ ((days == null) ? '' : ';    expires=' + exdate.toUTCString());
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

$("input:text[numberOnly]").on("keyup", function() {
    $(this).val($(this).val().replace(/[^0-9]/g,""));
});

var GLOBAL_COMMENT_IDX = 0;
function helpMeCharacter(characterName) {
	$("#helpComment").html(helpCommentList[GLOBAL_COMMENT_IDX++]);

	if (helpCommentList.length == GLOBAL_COMMENT_IDX - 1) {
		GLOBAL_COMMENT_IDX = 0;
//		tip = confirm("설명을 해준 " + characterName + "에게 팁으로 1,000크레딧을 드리겠습니까?")
//
//		if (tip == true) {
//			alert(characterName + "가 감사하다고합니다.")
//		}

		$("#helpCharacter").remove();
	}
}

$('#helpButton').click(function() {
	// function
	var characterName = $("#helpButton").attr("character");
	var img = "";

	if (characterName == "리지") {
		img = "leage";
	} else if (characterName == "토순이") {
		img = "tosoon";
	} else if (characterName == "보아") {
		img = "boar";
	} else {
		img = "hana";
	}

	var makeDiv = "";
	makeDiv += '<div id="helpCharacter" onclick="helpMeCharacter(\''
			+ characterName + '\')">';
	makeDiv += '	<img class="character" src="/img/character/' + img
			+ '.png" alt="">';
	makeDiv += '	<div class="balloon" style="line-height:2.0em">';
	makeDiv += '		<span id="helpComment"></span>';
	makeDiv += '	</div>';
	makeDiv += '</div>';

	$("#page-top").prepend(makeDiv);

	helpMeCharacter();
});

// 배열쿠키
/*
 * var list = new cookieList("prefer"); list.add("foo"); list.remove("foo");
 * 
 */
var cookieList = function(cookieName) {
	var cookie = $.cookie(cookieName);
	var items = cookie ? cookie.split(/,/) : new Array();
	
	return {
		"add" : function(val) {
			$.cookie(cookieName, items, {
				path : '/'
			});
		},
		"remove" : function(val) {
			indx = items.indexOf(val);
			if (indx != -1)
				items.splice(indx, 1);
			$.cookie(cookieName, items.join(','));
		},
		"clear" : function() {
			items = null;
			$.cookie(cookieName, null);
		},
		"items" : function() {
			return items;
		}
	}
}