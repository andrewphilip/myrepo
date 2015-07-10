var bufMap ;
var asudataobj;
var sortcol='';
var sort='asc';
var resjsonstr;

function populatesrc(obj){
	//console.log('inside populatesrc()');
	$("#from").html('');
	return _.each(obj, function(ele, idx, list) {
		$("#from").append(Mustache.to_html(opttmpl, ele));
	});
}

function populatedest(obj){
	//console.log('inside populatedest()');
	$("#to").html('');
	return _.each(obj, function(ele, idx, list) {
		$("#to").append(Mustache.to_html(opt2tmpl, ele));
	});
}

function validateAddSub(){
	var oper=$("input[name='addsub']:checked").val();
	if(oper == '' || oper == null){
		$(".asu-mod-body").html("<p>Please select Addition/Subtraction operation for component selection.</p>");
		$(".asu-comp-modal").show();
		return false;
	}
	return true;
}



function moveAll(from, to) {

	if(validateAddSub()){
		//console.log($("input[name='addsub']:checked").val());
		loadFlag = true;
		Popup.showModal("lmodal");
		setTimeout(function(){
		    $.when( $('#' + from + ' option').remove().appendTo('#' + to) ).then(function(obj){
																						var oper=$("input[name='addsub']:checked").val();
																						if(from == 'from'){
																								_.each(obj, function(ele, idx, list){
																										if(oper == 'A'){
																											//console.log(idx+">>"+"+"+ele.value);
																											ele.value = "+" + ele.value ;
																											ele.text = "[+]"+ele.text;
																											
																										}
																										if(oper == 'S'){
																											ele.value = "-" + ele.value ;
																											ele.text = "[-]"+ele.text;
																										}
																										
																								});
																						}
																						else{
																									_.filter(obj, function(ele){
																										var tmp=ele.value;
																										var eleTxt=ele.text;
																										// console.log(tmp);
																										 if(tmp.indexOf("+") != -1 || tmp.indexOf("-") != -1){
																										   ele.value = tmp.slice(1);
																										   ele.text = eleTxt.substring(3);
																										 }
																										 return ele;
																									});
																		
																						}
	
		    																	});
			postMove();
		}, 10);	

  }

}

function moveSelected(from, to) {
	if(validateAddSub()){
				loadFlag = true;
				Popup.showModal("lmodal");

				setTimeout(function(){
					$.when($('#' + from + ' option:selected').remove().appendTo('#' + to)).then(function(obj){
						var oper=$("input[name='addsub']:checked").val();
						_.each(obj, function(ele, idx, list){
							if(from == 'from'){
								if(oper == 'A'){
									//console.log(idx+">>"+"+"+ele.value);
									ele.value = "+" + ele.value ;
									ele.text = "[+]"+ele.text;
								}
								if(oper == 'S'){
									ele.value = "-" + ele.value ;
									ele.text = "[-]"+ele.text;
								}
							}
							else{
									var tmp=ele.value;
									 ele.value = tmp.slice(1);
									 ele.text = ele.text.substring(3);
							}
						});

					});
					postMove();
				}, 10);
	}

}


function postMove(){
	Popup.hide('lmodal');
	loadFlag=false;
	var tmpWidth= $("#mlistcont").width() * 0.40;
	$("#to").width(tmpWidth);
	$("#from").width(tmpWidth);
}


function sortMoveList(objId, col) {
	loadFlag = true;
	Popup.showModal("lmodal");
	var revOrd = false;
	var sCol = objId + col;
	//console.log(sortcol +"::"+ sCol);
	setTimeout( function(){
			if (sortcol == '') {
				sortcol = sCol;
				sort = 'asc';
			} else {
				if (sortcol == sCol) {
					revOrd = true;
					if (sort == 'asc') {
						sort = 'desc';
					} else {
						sort = 'asc';
					}
				} else {
					sortcol = sCol;
					sort = 'asc';
				}
			}
			//console.log('in sortMoveList() by ' + col + ' reverse-order:' + revOrd);

			if (objId == "to") {
				selMap = $("#to option").map(function() {
					var tmp = $(this).val();
					var tmpcompcode= tmp.substring(1);
					var fndEle=_.findWhere(bufMap, {asucompcode: tmpcompcode});
					//console.log(fndEle);
					if(fndEle != null ){
						if(tmp.indexOf("+") != -1){
									fndEle.oper = "+";
						}
						else{
									fndEle.oper = "-";
						}
						//console.log("selComp:"+fndEle.asucompcode);
					}
					return fndEle;
				}).get();
				//console.log("dest-map length:" + selMap.length);

				if(col == 'asucompurate'){
					selMap = _.sortBy(selMap, function(obj){
							return parseFloat(obj.asucompurate);
					});
				}
				else{
					selMap = _.sortBy(selMap, col);
				}

				if (revOrd) {
					selMap = _.sortBy(selMap).reverse();
					if (sort == 'asc') { 
						selMap = _.sortBy(selMap).reverse();
					}
				}
				$("#to").html('');
				populatedest(selMap);
			} else {
				var srcMap = $("#from option").map(function() {
						 var tmp = $(this).val();
						 return _.findWhere(bufMap, {asucompcode: tmp});
					}).get();

				if(col == 'asucompurate'){
					srcMap = _.sortBy(srcMap, function(obj){
							return parseFloat(obj.asucompurate);
					});
				}
				else{
					srcMap = _.sortBy(srcMap, col);
				}

				if (revOrd) {
					srcMap = _.sortBy(srcMap).reverse();
					if (sort == 'asc') { 
						srcMap = _.sortBy(srcMap).reverse();
					}

				}

				$("#from").html('');
				populatesrc(srcMap);
			}
			Popup.hide('lmodal');
	}, 100);
}

function doSave(){
	var selAreas = $("#to option").map(function() {
		return $(this).val();
	}).get().join(" ");
	//console.log(selAreas);
	if(selAreas.length == 0){
		$(".asu-mod-body").html("<p>Please select component(s) for calculation.</p>");
		$(".asu-comp-modal").show();
		return false;
	}

	loadFlag = true;
	Popup.showModal("lmodal");
	
	var asucode = $("#asuCode").val();
	//$("#selCompAreas").val(selAreas);
	
	var params={asuCode: asucode , stateCode:stCode, progYear:progyear, selCompAreas:selAreas, calcVals: resjsonstr };
	$.ajax({
		type:"POST",
		url: varContextPath + '/rest/asu/saveCalc',
		data:params,
		headers:{
			/* 'Accept':'application/json', */
			'Content-Type': 'application/x-www-form-urlencoded'
		}
	})
	.done(function(data){
		//console.log("success");
		if(data != null){
			console.log(data);
		}
		Popup.hide('lmodal');
	})
	.fail(function(jqXHR, textStatus){
		Popup.hide('lmodal');
		//console.log("failure");
		//console.log("Status: "+textStatus);
	})
	;
	
}

function calculate(){
	var selAreas = $("#to option").map(function() {
		return $(this).val();
	}).get().join(" ");
	//console.log(selAreas);
	if(selAreas.length == 0){
		$(".asu-mod-body").html("<p>Please select component(s) for calculation.</p>");
		$(".asu-comp-modal").show();
		return false;
	}

	loadFlag = true;
	Popup.showModal("lmodal");
	
	var asucode = $("#asuCode").val();
	//$("#selCompAreas").val(selAreas);
	
	var params={asuCode: asucode , stateCode:stCode, progYear:progyear, selCompAreas:selAreas };
	$.ajax({
		type:"POST",
		url: varContextPath + '/rest/asu/calc',
		data:params,
		headers:{
			/* 'Accept':'application/json', */
			'Content-Type': 'application/x-www-form-urlencoded'
		}
	})
	.done(function(data){
		//console.log("success");
		if(data != null){
			asudataobj = data;
			console.log(data);
			_.each(data, function(obj){
				if(obj.refmonth == '99'){
					$("#calclf").val(obj.lf);
					$("#calcemp").val(obj.emp);
					$("#calcunemp").val(obj.unemp);
					$("#calcurate").val(obj.urate);
					$("#calccurpop").val(obj.curpop);
					$("#calccenpop").val(obj.decenpop);
					
					$("#lf").html(numeral(obj.lf).format('0,0'));
					$("#emp").html(numeral(obj.emp).format('0,0'));
					$("#unemp").html(numeral(obj.unemp).format('0,0'));
					$("#urate").html(numeral(obj.urate).format('0.0'));
					$("#curpop").html(numeral(obj.curpop).format('0,0'));
					$("#cenpop").html(numeral(obj.decenpop).format('0,0'));
					$("#popcrit").html(obj.popcrit);
					$("#ratecrit").html(obj.ratecrit);
					
				}
				
				if(obj.res2string != null ){
					resjsonstr = obj.res2string;
					console.log(resjsonstr);
				}
				
			});
			
		}
		Popup.hide('lmodal');
	})
	.fail(function(jqXHR, textStatus){
		Popup.hide('lmodal');
		//console.log("failure");
		//console.log("Status: "+textStatus);
	})
	;

}

