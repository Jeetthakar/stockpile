
var arrSelectedIndex;
var arrOldValues;
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



function SelectAllList(CONTROL){
	alert(arrSelectedIndex.length);
	for(var i = 0;i < CONTROL.length;i++){
		CONTROL.options[i].selected = true;
		arrSelectedIndex[i] = 1;
	}
}



function DeselectAllList(CONTROL){
	for(var i = 0;i < CONTROL.length;i++){
		CONTROL.options[i].selected = false;
		arrSelectedIndex[i] = 0;
	}
	//alert(CONTROL.length);
	//alert(arrSelectedIndex.length);
}


function updateSelection() {
    //alert("updateSelection");
	for(var counter = 0; counter < arrSelectedIndex.length; counter++ ) {
		arrSelectedIndex[counter] = arrOldValues[counter];
	}
}


function showFinalList() {
	var strTemp = GetSelectValues(this.document.forms[0].selectList);
	//alert(strTemp);

}


function FillListValues(CONTROL){
//alert("FillListValues");
	this.document.forms[0].cnt_sel.value = "";

	var arrNewValues;
	var intNewPos;
	var strTemp = GetSelectValues(CONTROL);
	arrNewValues = strTemp.split(",");
	for(var i=0;i<arrNewValues.length-1;i++){
		if(arrNewValues[i]==1){
			intNewPos = i;
		}
	}


/*	if(arrSelectedIndex[intNewPos] == 2) {
		CONTROL.options[intNewPos].selected = true;
		arrSelectedIndex[intNewPos] = 1;
	}

*/
	for(var i=0;i<arrOldValues.length-1;i++){
		if(arrOldValues[i]==1 && i != intNewPos ){
			if( arrSelectedIndex[i] == 2 ) {
				CONTROL.options[i].selected= false;
				arrSelectedIndex[i] = 0;
			}
			else {
				CONTROL.options[i].selected= true;
			}
		}
		else {
			if(arrOldValues[i]==0 && i != intNewPos){
				CONTROL.options[i].selected= false;
			}
		}
	}


	if(arrOldValues[intNewPos] == 1 ){
		if(( arrSelectedIndex[intNewPos] == 2) || (arrSelectedIndex[intNewPos] == 0)) {
			CONTROL.options[intNewPos].selected = true;
			arrSelectedIndex[intNewPos] = 1;
		} else {
			CONTROL.options[intNewPos].selected = false;
		}
	}
	else{
		CONTROL.options[intNewPos].selected = true;
	}
}



function GetSelectValues(CONTROL){
//alert("GetSelectValues");
	var strTemp = "";
	for(var i = 0;i < CONTROL.length;i++){
		if(CONTROL.options[i].selected == true){
			strTemp += "1,";
		}
		else{
			strTemp += "0,";
		}
	}
	return strTemp;
}

function showSelected(CONTROL) {
	var strTemp = GetSelectValues(CONTROL);
	//alert(strTemp);
	//alert("SelectedIndex : "  + arrSelectedIndex.toString() );

}

function GetCurrentListValues(CONTROL){
//alert("GetCurrentListValues");
	var strValues = "";
	strValues = GetSelectValues(CONTROL);
	arrOldValues = strValues.split(",")
	this.document.forms[0].cnt_sel.value = "";
}



function autoComplete_MulSel(field, select, property, forcematch) {
	//alert("autoComplete_MulSel");
	var found = false;
	var i;
	for (i = 0; i < select.options.length; i++) {
		if (select.options[i][property].toUpperCase().indexOf(field.value.toUpperCase()) == 0) {
			found=true; break;
		}
	}
	var intNewValue = i;
	if (found ) {

		for(var counter =0 ; counter < select.options.length; counter++ ) {
			if(arrSelectedIndex[counter] == 2) {
				select.options[counter].selected = false;
				arrSelectedIndex[counter] = 0;
			}

		}
		select.options[i].selected= true;
		arrSelectedIndex[i] = 2;				// 2 -> highlighted
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
