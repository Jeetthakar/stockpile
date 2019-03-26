
var arrSelectedIndex_2;
var arrOldValues_2;
//To be added in each JSP page separately
 /*function initialize() {
	var length = document.forms[0].D2.length;
	arrSelectedIndex = new Array( length) ;
	arrOldValues = new Array(length);
	for (var counter= 0; counter < length; counter++) {
		arrSelectedIndex[counter] = 0;
		arrOldValues[counter] = 0;
	}
}	*/



function SelectAllList_2(CONTROL){
	//alert(arrSelectedIndex_2.length);
	//alert(CONTROL.length);
	for(var i = 0;i < CONTROL.length;i++){
		CONTROL.options[i].selected = true;
		arrSelectedIndex_2[i] = 1;
	}
	//document.forms[0].sall.value="Go";
}



function DeselectAllList_2(CONTROL){
	//alert(arrSelectedIndex_2.length);
	//alert(CONTROL.length);
	for(var i = 0;i < CONTROL.length;i++){
		CONTROL.options[i].selected = false;
		arrSelectedIndex_2[i] = 0;
	}
	//alert(CONTROL.length);
	
}


function updateSelection_2() {
    //alert("updateSelection");
	for(var counter = 0; counter < arrSelectedIndex_2.length; counter++ ) {
		arrSelectedIndex_2[counter] = arrOldValues_2[counter];
	}

}


function showFinalList_2() {
	var strTemp = GetSelectValues_2(this.document.forms[0].selectList);
	//alert(strTemp);

}


function FillListValues_2(CONTROL){
//alert("FillListValues");
	this.document.forms[0].cnt_sel_2.value = "";

	var arrNewValues_2;
	var intNewPos_2;
	var strTemp_2 = GetSelectValues_2(CONTROL);
	arrNewValues_2 = strTemp_2.split(",");
	for(var i=0;i<arrNewValues_2.length-1;i++){
		if(arrNewValues_2[i]==1){
			intNewPos_2 = i;
		}
	}


/*	if(arrSelectedIndex[intNewPos] == 2) {
		CONTROL.options[intNewPos_2].selected = true;
		arrSelectedIndex_2[intNewPos_2] = 1;
	}

*/
	for(var i=0;i<arrOldValues_2.length-1;i++){

		if(arrOldValues_2[i]==1 && i != intNewPos_2 ){
			if( arrSelectedIndex_2[i] == 2 ) {
				CONTROL.options[i].selected= false;
				arrSelectedIndex_2[i] = 0;
			}
			else {
				CONTROL.options[i].selected= true;
			}

		}
		else {
			if(arrOldValues_2[i]==0 && i != intNewPos_2){
				CONTROL.options[i].selected= false;
			}

		}
	}


	if(arrOldValues_2[intNewPos_2] == 1 ){

		if(( arrSelectedIndex_2[intNewPos_2] == 2) || (arrSelectedIndex_2[intNewPos_2] == 0)) {
			CONTROL.options[intNewPos_2].selected = true;
			arrSelectedIndex_2[intNewPos_2] = 1;

		} else {
			CONTROL.options[intNewPos_2].selected = false;

		}

	}
	else{
		CONTROL.options[intNewPos_2].selected = true;

	}


}



function GetSelectValues_2(CONTROL){
//alert("GetSelectValues");
	var strTemp_2 = "";
	for(var i = 0;i < CONTROL.length;i++){
		if(CONTROL.options[i].selected == true){
			strTemp_2 += "1,";
		}
		else{
			strTemp_2 += "0,";
		}
	}
	return strTemp_2;
}

function showSelected_2(CONTROL) {
	var strTemp_2 = GetSelectValues_2(CONTROL);
	//alert(strTemp);
	//alert("SelectedIndex : "  + arrSelectedIndex.toString() );

}

function GetCurrentListValues_2(CONTROL){
//alert("GetCurrentListValues");
	var strValues_2 = "";
	strValues_2 = GetSelectValues_2(CONTROL);
	arrOldValues_2 = strValues_2.split(",")
	this.document.forms[0].cnt_sel_2.value = "";
}



function autoComplete_MulSel_2(field, select, property, forcematch) {
	//alert("autoComplete_MulSel");
	var found = false;
	var i;
	for (i = 0; i < select.options.length; i++) {
		if (select.options[i][property].toUpperCase().indexOf(field.value.toUpperCase()) == 0) {
			found=true; break;
		}
	}
	var intNewValue_2 = i;
	if (found ) {

		for(var counter =0 ; counter < select.options.length; counter++ ) {
			if(arrSelectedIndex_2[counter] == 2) {
				select.options[counter].selected = false;
				arrSelectedIndex_2[counter] = 0;
			}

		}
		select.options[i].selected= true;
		arrSelectedIndex_2[i] = 2;				// 2 -> highlighted
	}

/*	if (found) { select.selectedIndex = i; }
	else { select.selectedIndex = -1; }
*/


	if (field.createTextRange) {
		if (forcematch && !found) {
			field.value=field.value.substring(0,field.value.length-1);
			return;
		}
		var cursorKeys ="8;46;37;38;39;40;33;34;35;36;45;";
		if (cursorKeys.indexOf(event.keyCode+";") == -1) {
			var r1_2 = field.createTextRange();
			var oldValue_2 = r1_2.text;
			var newValue_2 = found ? select.options[i][property] : oldValue;
			if (newValue_2 != field.value) {
				field.value = newValue_2;
				var rNew_2 = field.createTextRange();
				rNew_2.moveStart('character', oldValue_2.length) ;
				rNew_2.select();
			}
		}
	}


}


function autoComplete (field, select, property, forcematch) {
	var found = false;
	for (var i = 0; i < select.options.length; i++) {
	if (select.options[i][property].toUpperCase().indexOf(field.value.toUpperCase()) == 0) {
		found=true; break;
		}
	}
	
	if (found) { select.selectedIndex = i; }
	else { select.selectedIndex = -1; }
	if (field.createTextRange) {
		if (forcematch && !found) {
			field.value=field.value.substring(0,field.value.length-1); 
			return;
			}
		var cursorKeys ="8;46;37;38;39;40;33;34;35;36;45;";
		if (cursorKeys.indexOf(event.keyCode+";") == -1) {
			var r1 = field.createTextRange();
			var oldValue = r1.text;
			var newValue = found ? select.options[i][property] : oldValue;
			if (newValue != field.value) {
				field.value = newValue;
			var rNew = field.createTextRange();
			rNew.moveStart('character', oldValue.length) ;
				rNew.select();
				}
			}
		}
	}
