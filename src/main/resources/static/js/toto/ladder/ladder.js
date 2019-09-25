document.write("<script src='/js/common.js'></script>");

$(function(){

    var heightNode = 10;
    var widthNode =  0;

    var LADDER = {};
    var row =0;
    var ladder = $('#ladder');
    var ladder_canvas = $('#ladder_canvas');
    var GLOBAL_FOOT_PRINT= {};
    var GLOBAL_CHECK_FOOT_PRINT= {};
    var GLOBAL_CHECK_DUPLE = false;
    var GLOBAL_BUTTON_IDX = 0;
    var GLOBAL_BET_CREDIT = 0;
    var GLOBAL_HAVE_CREDIT = 0;
    
    var working = false;
    function init(){
        canvasDraw();
    }

    $('#betButton').on('click', function(){
    	GLOBAL_CHECK_DUPLE = false;
    	GLOBAL_BUTTON_IDX = 0;
    	GLOBAL_BET_CREDIT = 0;
    	GLOBAL_HAVE_CREDIT = 0;
    	    
    	if($("#betButton").val() == "다시하기") {
    		location.reload();
    	}
    	
    	if($("#credit").val() < 1000) {
    		alert("1,000크레딧 이상만 입력해주세요.");
    		return false;
    	}
    	
    	GLOBAL_BET_CREDIT = parseInt($("#credit").val());
    	GLOBAL_HAVE_CREDIT = parseInt(removeComma($("#haveCredit").val()));
    	
    	$("#credit").val(numberWithCommas($("#credit").val()));
    	
    	$("#credit").attr("disabled" ,true);
    	$("#betButton").val("다시하기");
    	
    	
        var member = 5;
//        if(member < 5){
//            return alert('사다리는 5개 입니다.')
//        }
//
//        if(member > 6){
//            return alert('사다리는 5개 입니다.')   
//        }
        $('#landing').css({
            'opacity': 0
        });
        widthNode = member;
        setTimeout(function(){
            $('#landing').remove();
            init();
        }, 310)

    });

    function canvasDraw(){
        ladder.css({
            'width' :( widthNode-1) * 100 + 6,
            'height' : (heightNode -1 ) * 25 + 6,
            'background-color' : '#fff'
        });
       ladder_canvas
       .attr('width' , ( widthNode-1) * 100 + 6)
       .attr('height' , ( heightNode-1) * 25 + 6);

        setDefaultFootPrint();
        reSetCheckFootPrint();
        setDefaultRowLine();
        setRandomNodeData();
        drawDefaultLine();
        drawNodeLine();
        userSetting();
        resultSetting();
        
    }
    var userName = "";
    $(document).on('click', 'button.ladder-start', function(e){
    	if(GLOBAL_CHECK_DUPLE == true) {
    		var con = confirm("동일한 크레딧으로 선택됩니다.\n계속 하시겠습니까?");
    		
    		if(con==false) {
    			return false;
    		}
    	}
    	
    	GLOBAL_HAVE_CREDIT -= GLOBAL_BET_CREDIT;
    	$("#haveCredit").val(numberWithCommas(GLOBAL_HAVE_CREDIT));
    	
        if(working){
            return false;
        }
        $('.dim').remove();
        working = true;
        reSetCheckFootPrint();
        var _this = $(e.target);
        _this.attr('disabled' ,  true).css({
            'color' : '#000',
            'border' : '1px solid #F2F2F2',
            'opacity' : '0.3'
        })
        var node = _this.attr('data-node');
        var color =  _this.attr('data-color');
        startLineDrawing(node, color);
        
        GLOBAL_BUTTON_IDX = parseInt(node.substring(0,1))+1;
        
        userName =  $('input[data-node="'+node+'"]').val();
    })
    
    function startLineDrawing(node , color){    	
        var node = node;
        var color = color;
        
        var x = node.split('-')[0]*1;
        var y = node.split('-')[1]*1;
        var nodeInfo = GLOBAL_FOOT_PRINT[node];

        GLOBAL_CHECK_FOOT_PRINT[node] = true;
        
        var dir = 'r'
        if(y ==heightNode ){
            reSetCheckFootPrint();
            var target = $('input[data-node="'+node+'"]');
            target.css({
                //'background-color' : color
            })
            // 여기서 ajax
            
            $.ajax({
				url : "/toto/ladder/bet",
				type : "POST",
				cache : false,
				dataType : "html",
				data : "ladderRegs=" + 5 
				 + "&ladderAnswer=" + GLOBAL_BUTTON_IDX
				 + "&creditValue=" + GLOBAL_BET_CREDIT,
				success : function(data) {
					if(data < 0) {
						alert("알수없는 에러가 발생하였습니다. 새로고침 후 다시 시도해주세요.");
					} else {
						$('[data-node=' + node + ']').val(data + "배");
						
						if(data > 0) {
							GLOBAL_HAVE_CREDIT += parseInt((GLOBAL_BET_CREDIT * data));
							$("#haveCredit").val(numberWithCommas(GLOBAL_HAVE_CREDIT));
							
							$('[data-node=' + node + ']').attr("style", "background: black; color: white;");
							
							$(".ladder-start").attr('disabled' ,  true).css({
					            'color' : '#000',
					            'border' : '1px solid #F2F2F2',
					            'opacity' : '0.3'
					        })
					        
					        alert(numberWithCommas(parseInt((GLOBAL_BET_CREDIT * data))) + "크레딧을 획득했습니다.");
					        
					        if(confirm("계속하시겠습니까?") == true) {
					        	location.reload();
					        }
						}
					}
				},
				error : function(request, status, error) {
					var msg = "ERROR : " + request.status + "<br>"
					msg += +"내용 : " + request.responseText + "<br>" + error;
					console.log(msg);
				}
			});


            GLOBAL_CHECK_DUPLE  = true
             working = false;
            return false;
        }
        
        if(nodeInfo["change"] ){
            var leftNode = (x-1) + "-" +y;
            var rightNode = (x+1) + "-" +y;
            var downNode = x +"-"+ (y + 1);
            var leftNodeInfo = GLOBAL_FOOT_PRINT[leftNode];
            var rightNodeInfo = GLOBAL_FOOT_PRINT[rightNode];
                
            if(GLOBAL_FOOT_PRINT.hasOwnProperty(leftNode) && GLOBAL_FOOT_PRINT.hasOwnProperty(rightNode)){      
                var leftNodeInfo = GLOBAL_FOOT_PRINT[leftNode];
                var rightNodeInfo = GLOBAL_FOOT_PRINT[rightNode];
                if(  (leftNodeInfo["change"] &&  leftNodeInfo["draw"] && !!!GLOBAL_CHECK_FOOT_PRINT[leftNode] ) && (rightNodeInfo["change"])&&  leftNodeInfo["draw"]  && !!!GLOBAL_CHECK_FOOT_PRINT[rightNode] ){
                    //Left우선 
                    console.log("중복일때  LEFT 우선");
                    stokeLine(x, y, 'w' , 'l' , color ,3)
                     setTimeout(function(){ 
                         return startLineDrawing(leftNode, color)
                     }, 100);
                }
                else if(  (leftNodeInfo["change"] &&  !!!leftNodeInfo["draw"] && !!!GLOBAL_CHECK_FOOT_PRINT[leftNode] ) && (rightNodeInfo["change"]) && !!!GLOBAL_CHECK_FOOT_PRINT[rightNode] ){
                    console.log('RIGHT 우선')
                    stokeLine(x, y, 'w' , 'r' , color ,3)
                    console.log("right")
                    setTimeout(function(){ 
                        return startLineDrawing(rightNode, color)
                     }, 100);
                }
                else if(  (leftNodeInfo["change"] &&  leftNodeInfo["draw"] && !!!GLOBAL_CHECK_FOOT_PRINT[leftNode] ) && (!!!rightNodeInfo["change"]) ){
                    //Left우선 
                    console.log("LEFT 우선");
                    stokeLine(x, y, 'w' , 'l' , color ,3)
                     setTimeout(function(){ 
                         return startLineDrawing(leftNode, color)
                     }, 100);
                }
                 else if(  !!!leftNodeInfo["change"]  &&  (rightNodeInfo["change"]) && !!!GLOBAL_CHECK_FOOT_PRINT[rightNode] ){
                    //Right우선 
                    console.log("RIGHT 우선");
                    stokeLine(x, y, 'w' , 'r' , color ,3)
                     setTimeout(function(){ 
                         return startLineDrawing(rightNode, color)
                     }, 100);
                }
                else{
                    console.log('DOWN 우선')
                    stokeLine(x, y, 'h' , 'd' , color ,3)
                    setTimeout(function(){ 
                       return startLineDrawing(downNode, color)
                    }, 100);
                }
            }else{
                console.log('else')
               if(!!!GLOBAL_FOOT_PRINT.hasOwnProperty(leftNode) && GLOBAL_FOOT_PRINT.hasOwnProperty(rightNode)){      
                    /// 좌측라인
                    console.log('좌측라인')
                    if(  (rightNodeInfo["change"] && !!!rightNodeInfo["draw"] ) && !!!GLOBAL_CHECK_FOOT_PRINT[rightNode] ){
                        //Right우선 
                        console.log("RIGHT 우선");
                        stokeLine(x, y, 'w' , 'r' , color ,3)
                        setTimeout(function(){ 
                            return startLineDrawing(rightNode, color)
                        }, 100);
                    }else{
                        console.log('DOWN')
                        stokeLine(x, y, 'h' , 'd' , color ,3)
                        setTimeout(function(){ 
                           return startLineDrawing(downNode, color)
                        }, 100);
                    }
                    
               }else if(GLOBAL_FOOT_PRINT.hasOwnProperty(leftNode) && !!!GLOBAL_FOOT_PRINT.hasOwnProperty(rightNode)){      
                    /// 우측라인
                    console.log('우측라인')
                    if(  (leftNodeInfo["change"] && leftNodeInfo["draw"] ) && !!!GLOBAL_CHECK_FOOT_PRINT[leftNode] ){
                        //Right우선 
                        console.log("LEFT 우선");
                        stokeLine(x, y, 'w' , 'l' , color ,3)
                        setTimeout(function(){ 
                            return startLineDrawing(leftNode, color)
                        }, 100);
                    }else{
                        console.log('DOWN')
                        stokeLine(x, y, 'h' , 'd' , color ,3)
                        setTimeout(function(){ 
                           return startLineDrawing(downNode, color)
                        }, 100);
                    }
               }
            }


        }else{
            console.log("down")
            var downNode = x +"-"+ (y + 1);
            stokeLine(x, y, 'h' , 'd' , color ,3)
            setTimeout(function(){ 
                return startLineDrawing(downNode, color)
             }, 100);
        }
    }

    function userSetting(){
        var userList = LADDER[0];
        var html = '';
        for(var i=0; i <  userList.length; i++){
            var color = '#'+(function lol(m,s,c){return s[m.floor(m.random() * s.length)] + (c && lol(m,s,c-1));})(Math,'0123456789ABCDEF',4);

            var x = userList[i].split('-')[0]*1;
            var y = userList[i].split('-')[1]*1;
            var left = x * 100  -30
            
            html += '<div class="user-wrap" style="left:'+left+'px"><button class="ladder-start btn-secondary" data-color="#000000" data-node="'+userList[i]+'">선택</button>';
            html +='</div>'
        }
        ladder.append(html);
    }
    function resultSetting(){
         var resultList = LADDER[heightNode-1];
         console.log(resultList )

        var html = '';
        for(var i=0; i <  resultList.length; i++){
            
            var x = resultList[i].split('-')[0]*1;
            var y = resultList[i].split('-')[1]*1 + 1;
            var node = x + "-" + y;
            var left = x * 100  -30
            
            html += "<div class='answer-wrap' style='left:"+left+"px'>" +
            		"<input type='text' data-node='"+node+"' value='결과' readonly>" +
            				"";
            html +='<p id="'+node+'-user"></p>'
            html +='</div>'
        }
        ladder.append(html);
    }

    function drawNodeLine(){

        for(var y =0; y < heightNode; y++){
            for(var x =0; x <widthNode ; x++){
                var node = x + '-' + y;
                var nodeInfo  = GLOBAL_FOOT_PRINT[node];
                if(nodeInfo["change"] && nodeInfo["draw"] ){
                     stokeLine(x, y ,'w' , 'r' , '#ddd' , '2')
                }else{

                }
            }
        }
    }

    function stokeLine(x, y, flag , dir , color , width){
        var canvas = document.getElementById('ladder_canvas');
        var ctx = canvas.getContext('2d');
        var ctx2 = canvas.getContext('2d');
        
        var moveToStart =0, moveToEnd =0, lineToStart =0 ,lineToEnd =0; 
        var eachWidth = 100; 
        var eachHeight = 25;
        
        if(flag == "w"){
            //가로줄
            if(dir == "r"){
                ctx.beginPath();
                moveToStart = x * eachWidth ;
                moveToEnd = y * eachHeight ;
                lineToStart = (x+ 1) * eachWidth;
                lineToEnd = y * eachHeight;
                
            }else{
                // dir "l"
                 ctx.beginPath();
                moveToStart = x * eachWidth;
                moveToEnd = y * eachHeight;
                lineToStart = (x- 1) * eachWidth;
                lineToEnd = y * eachHeight;
            }
        }else{
                ctx.beginPath();
                moveToStart = x * eachWidth ;
                moveToEnd = y * eachHeight;
                lineToStart = x * eachWidth ;
                lineToEnd = (y+1) * eachHeight;
        }

        ctx.moveTo(moveToStart + 3 ,moveToEnd  + 2);
        ctx.lineTo(lineToStart  + 3 ,lineToEnd  + 2 );
        ctx.strokeStyle = color;
        ctx.lineWidth = width;
        ctx.stroke();
        ctx.closePath();
    }

    function drawDefaultLine(){
        var html = '';
        html += '<table>'
         for(var y =0; y < heightNode-1; y++){
            html += '<tr>';
            for(var x =0; x <widthNode-1 ; x++){
                html += '<td style="width:100px; height:25px; border-left:2px solid #ddd; border-right:2px solid #ddd;"></td>';
            }
            html += '</tr>';
        }
        html += '</table>'
        ladder.append(html);
    }

    function setRandomNodeData(){
         for(var y =0; y < heightNode; y++){
            for(var x =0; x <widthNode ; x++){
                var loopNode = x + "-" + y;
                var rand = Math.floor(Math.random() * 2);
                if(rand == 0){
                    GLOBAL_FOOT_PRINT[loopNode] = {"change" : false , "draw" : false}
                }else{
                    if(x == (widthNode - 1)){
                        GLOBAL_FOOT_PRINT[loopNode] = {"change" : false , "draw" : false} ;    
                    }else{
                        GLOBAL_FOOT_PRINT[loopNode] =  {"change" : true , "draw" : true} ;  ;
                        x = x + 1;
                         loopNode = x + "-" + y;
                         GLOBAL_FOOT_PRINT[loopNode] =  {"change" : true , "draw" : false} ;  ;
                    }
                }
            }
         }
    }

    function setDefaultFootPrint(){
      
        for(var r = 0; r < heightNode; r++){
            for(var column =0; column < widthNode; column++){
                GLOBAL_FOOT_PRINT[column + "-" + r] = false;
            }
        }
    }
    function reSetCheckFootPrint(){

        for(var r = 0; r < heightNode; r++){
            for(var column =0; column < widthNode; column++){
                GLOBAL_CHECK_FOOT_PRINT[column + "-" + r] = false;
            }
        }
    }

    function setDefaultRowLine(){

        for(var y =0; y < heightNode; y++){
            var rowArr = [];
            for(var x =0; x <widthNode ; x++){
                var node = x + "-"+ row;
                rowArr .push(node);
                // 노드그리기
                var left = x * 100;
                var top = row * 25;
                var node = $('<div></div>')
                .attr('class' ,'node')
                .attr('id' , node)
                .attr('data-left' , left)
                .attr('data-top' , top)
                .css({
                    'position' : 'absolute',
                    'left' : left,
                    'top' : top
                });
                ladder.append(node);
             }
             LADDER[row] =  rowArr;
             row++;
        }
    }



});
