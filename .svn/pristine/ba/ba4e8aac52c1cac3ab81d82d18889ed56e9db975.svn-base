/**
 * 	SortedTable1: JavaScript file to sort the tables 
 *	Additional File(s):	Event.js
 *	alphanumerically, numerically, or date wise.
 *  author: Abhijit Balki
 *	Last Modified: 10-08-2006
 *
 *	The file implements the sorting algorithm for tables that are used for 
 *	displaying records in varios reports. The sorting can be done alphanumerically,
 *	numerically or date wise.
 * 	The alphanumerical sorting implements plain string sorting for the assigned table
 *	head. Numeric sorting supports numeric format that includes comma. Date wise 
 *	sorting implements sorting according to the date in format dd-mm-yyyy.
 *	
 *	To implement this sorting, the files (Event.js and SortedTable1.js) are to be
 *	included along with the stylesheet declarations.
 *	The table class should be "sorted1" and table ID should be "sortTable".
 *	The <thead> tag declares the headers along with the unique ids.
 *	The <tbody> tag declares the table data where 'axis' specifies the sorting type
 *	(string, sstring, number, date) and 'header' specifies the header id to be 
 *	associated with.
 *	The <tfoot>tag declares the footers of the table.
 * 	The sorting is done only on the rows of <tbody> tag.
 * 	
 *	The initSort() function should be called onload of the respective page for 
 *	some initiallization processes.
 *
 *  Changes		Date 	Modified By			Reason
 *		
 **/
SortedTable1 = function(id) {
	this.table = null;
	if (!document.getElementById || !document.getElementsByTagName) return false;
	if (id) this.init(document.getElementById(id));
	else this.init(this.findTable());
	this.prep();
	if (!id && this.findTable()) new SortedTable1();
}

// static
SortedTable1.tables = new Array();

SortedTable1.move = function(d,elm) {
	var st = SortedTable1.getSortedTable(elm);
//	alert('inside SortedTable1.move');
	if (st) st.move(d,elm);
}
SortedTable1.moveSelected = function(d,elm) {
//	alert('SortedTable1.moveselected');
	var st = SortedTable1.getSortedTable(elm);
	if (st) st.move(d);
}
SortedTable1.findParent = function(elm,tag) {
//	alert('SortedTable1.findparent');
	while (elm && elm.tagName && elm.tagName.toLowerCase()!=tag) elm = elm.parentNode;
	return elm;
}
SortedTable1.getEventElement = function(e) {
//	alert('SortedTable1.getEventElement');
	if (!e) e = window.event;
	return (e.target)? e.target : e.srcElement;
}
SortedTable1.getSortedTable = function(elm) {
//	alert('SortedTable1.getSortedtable');
	elm = SortedTable1.findParent(elm,'table');
	for (var i=0;i<SortedTable1.tables.length;i++) {
		var t = SortedTable1.tables[i].table;
		if (t==elm) return SortedTable1.tables[i];
	}
	return null;
}
SortedTable1.gecko = (navigator.product=="Gecko");
SortedTable1.removeBeforeSort = SortedTable1.gecko;

// dynamic
SortedTable1.prototype = {
// before init finished
	init:function(elm) {
//		alert('inside init');
		if (!elm) return false;
		// main DOM properties
		this.table = elm;
		this.head = elm.getElementsByTagName('thead')[0];
		this.body = elm.getElementsByTagName('tbody')[0];
		this.foot = elm.getElementsByTagName('tfoot')[0];
		if (this.hasClass(this.table,'regroup')) this.regroup();
		this.elements = this.body.getElementsByTagName('tr');
		// other properties
		this.allowMultiple = true; // set this to false to disallow multiple selection
		this.allowDeselect = true; // set this to false to disallow deselection
		// prepare the table
		this.parseCols();
		this.selectedElements = new Array();
	},
	findTable:function() {
//		alert('find Table of class = sorted1');
		var elms = document.getElementsByTagName('table');
		for (var i=0;i<elms.length;i++) {
			if (this.hasClass(elms[i],'sorted1') && 
					!SortedTable1.getSortedTable(elms[i])) 
				return elms[i];
		}
		return null;
	},
	parseCols:function() {
//		alert('parseCols');
		if (!this.table) return;
		this.cols = new Array();
		var ths = this.head.getElementsByTagName('th');
		for (var i=0;i<ths.length;i++) {
			this.cols[ths[i].id] = new Array();
			
		}
		for (var i=0;i<this.elements.length;i++) {
			var tds = this.elements[i].getElementsByTagName('td');
			for (var j=0;j<tds.length;j++) {
				var headers = tds[j].headers.split(' ');
				for (var k=0;k<headers.length;k++) {
					if (this.cols[headers[k]]) this.cols[headers[k]].push(tds[j]);
				}
			}
		}
	},
	prep:function() {
		if (!this.table || SortedTable1.getSortedTable(this.table)) return;
		this.register();
		this.prepBody();
		//this.prepHeader(); //change by manoj adekar for hearder sorting disable
	},
	register:function() {
		SortedTable1.tables.push(this);
	},
	regroup:function() {
//		alert('inside regroup');
		var tbs = this.table.getElementsByTagName('tbody');
		for (var i=tbs.length-1;i>0;i--) {
			var trs = tbs[i].getElementsByTagName('tr');
			for (var j=trs.length-1;j>=0;j--) {
				this.body.appendChild(trs[j]);
			}
			this.table.removeChild(tbs[i]);
		}
	},
// helpers
	trim:function(str) {
//		alert('trim');
		while (str.substr(0,1)==' ') str = str.substr(1);
		while (str.substr(str.length-1,1)==' ') str = str.substr(0,str.length-1);
		return str;
	},
	hasClass:function(elm,findclass) {
//		alert('inside hasClass');
		if (!elm) return null;
		return (' '+elm.className+' ').indexOf(' '+findclass+' ')+1;
	},
	changeClass:function(elm,oldclass,newclass) {
//		alert('changeClass');
		if (!elm) return null;
		var c = elm.className.split(' ');
		for (var i=0;i<c.length;i++) {
			c[i] = this.trim(c[i]);
			if (c[i]==oldclass || c[i]==newclass || c[i]=='') c.splice(i,1);
		}
		c.push(newclass);
		elm.className = this.trim(c.join(' '));
	},
	elementIndex:function(elm) {
//		alert('elementIndex');
		for (var i=0;i<this.elements.length;i++) {
			if (this.elements[i]==elm) return i;
		}
		return -1;
	},
	findParent:SortedTable1.findParent,
// events
	callBodyClick:function(e) {
//		alert('callBodyClick');
		var elm = SortedTable1.getEventElement(e);
		var st = SortedTable1.getSortedTable(elm);
		
		clearHeaderLabel();
		
		if (!st) return false;
		if (typeof(st.onbodyclick)=='function') st.onbodyclick(elm,e);
		var elm = st.findParent(elm,'tr');
		if (e.shiftKey && st.allowMultiple) st.selectRange(elm);
		else {
			if (st.selected(elm)) {
				if  (st.allowDeselect) st.deselect(elm);
			} else {
				if (!e.ctrlKey || !st.allowMultiple) st.cleanselect();
				st.select(elm);
			}
		}
		return false;
	},
	callBodyDblClick:function(e) {
//		alert('callBodyDblClick');
		var elm = SortedTable1.getEventElement(e);
		var st = SortedTable1.getSortedTable(elm);
		if (!st) return false;
		if (typeof(st.onbodydblclick)=='function') st.onbodydblclick(elm,e);
		return false;
	},
/*	//change by manoj adekar for hearder sorting disable
callHeadClick:function(e) {
		//alert('callHeadClick' );
		//alert("checking the header");
		var elem = "th";
			   		
		
		
		var elm = SortedTable1.getEventElement(e);
		var st = SortedTable1.getSortedTable(elm);
		
		if (!st) return false;
		if (typeof(st.onheadclick)=='function') st.onheadclick(elm,e);
		var elm = st.findParent(elm,'th');
		st.resort(elm);
		return false;
	},
	callHeadDblClick:function(e) {
		var elm = SortedTable1.getEventElement(e);
		var st = SortedTable1.getSortedTable(elm);
		if (!st) return false;
		if (typeof(st.onheaddblclick)=='function') st.onheaddblclick(elm,e);
		return false;
	}, 
// inited
	prepHeader:function() {
		var ths = this.head.getElementsByTagName('th');
		for (var i=0;i<ths.length;i++) {
			if (this.hasClass(ths[i],'nosort')) continue;
			ths[i].style.cursor = 'pointer';
			addEvent(ths[i],'click',this.callHeadClick);
			addEvent(ths[i],'dblclick',this.callHeadDblClick);
			if (this.hasClass(ths[i],'sortedplus') || this.hasClass(ths[i],'sortedminus')) this.sort(ths[i]);
		}
	},*/
	prepBody:function() {
		var elm = this.body.lastChild;
		var pelm;
		while (elm) {
			pelm = elm.previousSibling;
			if (elm.nodeType!=1) this.body.removeChild(elm);
			elm = pelm;
		}
		var trs = this.body.getElementsByTagName('tr');
		for (var i=0;i<trs.length;i++) {
			trs[i].style.cursor = 'pointer';
//			addEvent(trs[i],'click',this.callBodyClick);
//			addEvent(trs[i],'dblclick',this.callBodyDblClick);
		}
	},
// selecting
	selected:function(elm) {
//		alert('inside selected');
		return this.hasClass(elm,'selrow');
	},
	select:function(elm) {
//		alert('inside select');
		this.changeClass(elm,'','selrow');
		this.selectedElements.push(elm);
		if (typeof(this.onselect)=='function') this.onselect(elm);
	},
	deselect:function(elm) {
//		alert('inside deselect');
		this.changeClass(elm,'selrow','');
		for (var i=0;i<this.selectedElements.length;i++) {
			if (this.selectedElements[i]==elm) this.selectedElements.splice(i,1);
		}
		if (typeof(this.ondeselect)=='function') this.ondeselect(elm);
	},
	selectRange:function(elm1) {
//		alert('inside selectRange');
		if (this.selectedElements.length==0) {
			this.select(elm1);
			return false;
		}
		var elm0 = this.selectedElements[this.selectedElements.length-1];
		var d = (this.elementIndex(elm0) < this.elementIndex(elm1));
		var elm = elm0;
		if (this.selected(elm1)) {if (this.selected(elm0)) this.deselect(elm0);}
		else {if (!this.selected(elm0)) this.select(elm0);}
		do {
			elm = (d)? elm.nextSibling : elm.previousSibling;
			if (this.selected(elm)) this.deselect(elm);
			else this.select(elm);
		} while (elm!=elm1);
		return true;
	},
	cleanselect:function() {
//		alert('cleanselect');
		for (var i=0;i<this.elements.length;i++) {
			if (this.selected(this.elements[i])) this.deselect(this.elements[i]);
		}
		this.selectedElements = new Array();
	},
// sorting
	compareSmart:function(v1,v2) {
	//	alert('compareSmart');
		v1 = (v1)? v1.split(' ') : [];
		v2 = (v2)? v2.split(' ') : [];
		
		l = Math.max(v1.length,v2.length);
		var r = 0;
		for (var i=0;i<l;i++) {
			if (v1[i]==v2[i]) continue;
			if (!v1[i]) v1[i] = "";
			if (!v2[i]) v2[i] = "";
			if (!isNaN(parseFloat(v1[i]))) v1[i] = parseFloat(v1[i]);
			if (!isNaN(parseFloat(v2[i]))) v2[i] = parseFloat(v2[i]);
			if (isNaN(v1[i])&&!isNaN(v2[i])) return 1;
			else if (!isNaN(v1[i])&&isNaN(v2[i])) return -1;
			else if (v1[i]>v2[i]) return 1;
			else if (v1[i]<v2[i]) return -1;
		}
		return 0;
	},
	compare:function(v1,v2) {
	//	alert('compare');
		var st = SortedTable1.getSortedTable(v1);
		if (v1==null || v2==null) return 0;
		var axis = v1.axis.toLowerCase();
		var v1s = (v1.title)? v1.title : (v1.innerHTML)? v1.innerHTML : '';
		var v2s = (v2.title)? v2.title : (v2.innerHTML)? v2.innerHTML : '';
		if (axis=='string') {
			return st.compareSmart(v1s.toLowerCase(),v2s.toLowerCase());
		} else if (axis=='sstring') {
			return st.compareSmart(v1s,v2s);
		} else if (axis=='number') {
			//alert('v1 = '+v1s.toString());
			v1s = v1s.toString().replace(/,/g,'');
			v1 = parseFloat(v1s);
			//alert('after: v1 ='+ v1);
			if (isNaN(v1)) v1 = Infinity;
			
			//alert('v2 = '+v2s.toString());
			v2s = v2s.toString().replace(/,/g,'');
			v2 = parseFloat(v2s);
			//alert('after: v2 ='+ v2);
			if (isNaN(v2)) v2 = Infinity;
		} else if(axis=='date') {			
		
		//Original date format	dd-mm-yyyy
		//			0  1  2
		// new 	date format	yyyymmdd	2+1+0
		
			//alert('before '+ v1s);
			tempArray = v1s.toString().split('-');
			v1s = tempArray[2].concat(tempArray[1],tempArray[0]);
			v1 = parseFloat(v1s);
			if (isNaN(v1)) v1 = Infinity;
			//alert('after '+ v1);

			tempArray = v2s.toString().split('-');
			v2s = tempArray[2].concat(tempArray[1],tempArray[0]);
			v2 = parseFloat(v2s);

			if (isNaN(v2)) v2 = Infinity;
		} else {
			v1 = (v1s!='')? v1s : v1;
			v2 = (v2s!='')? v2s : v2;
		}
		if (v1==null || v2==null) return 0;
		else if (v1>v2) return 1
		else if (v1<v2) return -1;
		return 0;
	},
	findSort:function() {
		var ths = this.head.getElementsByTagName('th');
		for (var i=0;i<ths.length;i++) {
			if (this.hasClass(ths[i],'sortedminus') || this.hasClass(ths[i],'sortedplus')) return ths[i];
		}
		return null;
	},
	sort:function(elm,reverseonly) {
	//	alert('sort');
		if (!elm) elm = this.findSort();
		if (!elm) return false;
		var col = this.cols[elm.id];
		if (!reverseonly) col.sort(this.compare);
		if (this.hasClass(elm,'sortedminus') || reverseonly) col.reverse();
		var b_sibling,b_parent;
		if (SortedTable1.removeBeforeSort) {
			b_sibling = this.body.nextSibling;
			b_parent = this.body.parentNode;
			b_parent.removeChild(this.body);
		}
		for (var i=0;i<col.length;i++) {
			this.body.appendChild(this.findParent(col[i],'tr'));
		}
		if (SortedTable1.removeBeforeSort) {
			b_parent.insertBefore(this.body,b_sibling);
		}
		if (typeof(this.onsort)=='function') this.onsort(elm);
	},
	resort:function(elm) {
	//	alert('inside resort');
		if (!elm) return false;
		this.cleansort(elm);
		var reverseonly = false;
		ARROW = "";
		if (this.hasClass(elm,'sortedplus')) {
			this.changeClass(elm,'sortedplus','sortedminus');
			ARROW = '&nbsp;&nbsp;&uarr;';
			reverseonly = true;
		} else if (this.hasClass(elm,'sortedminus')) {
			this.changeClass(elm,'sortedminus','sortedplus');
			ARROW = '&nbsp;&nbsp;&darr;';
			reverseonly = true;
		} else {
			this.changeClass(elm,'sortedminus','sortedplus');
			ARROW = '&nbsp;&nbsp;&darr;';
			
		}
		this.sort(elm,reverseonly);
		
		//delete old arrows
		var tbs = document.getElementById ('sortTable').getElementsByTagName('thead');
		var trs = tbs[0].getElementsByTagName('th');

		for (var i=0 ;i<trs.length;i++) {
			var tempStr = trs[i].innerHTML.toString();
			SEPERATOR = '&nbsp;&nbsp;';
			temp123 = tempStr.split(SEPERATOR);

			trs[i].innerHTML = temp123[0];
			 
		}
		
		temp = elm.innerHTML.toString().split('&nbsp;&nbsp;');
		//alert(temp.length);
		//elm.innerHTML.toString = '<span>' + temp[1].toString();
		//for( i=1;i < temp.length -1; i++) {
		//	elm.innerHTML = elm.innerHTML + temp[i];
		//}
		//alert(temp.length);
		elm.innerHTML = temp[0] + ARROW;
		//elm.innerHTML = elm.id + ARROW;
		//alert(elm.id);
		//alert(elm.innerHTML);
		
	},
	cleansort:function(except) {
//		alert('cleansort');
		var ths = this.head.getElementsByTagName('th');
		for (var i=0;i<ths.length;i++) {
			if (ths[i]==except) continue;
			if (this.hasClass(ths[i],'sortedminus')) this.changeClass(ths[i],'sortedminus','');
			else if (this.hasClass(ths[i],'sortedplus')) this.changeClass(ths[i],'sortedplus','');
		}
	},
// movement
	compareindex:function(v1,v2) {
//		alert('compareindex');
		var st = SortedTable1.getSortedTable(v1);
		if (!st) return 0;
		v1 = st.elementIndex(v1);
		v2 = st.elementIndex(v2);
		if (v1==null || v2==null) return 0;
		else if (v1<v2) return 1
		else if (v2<v1) return -1;
		return 0;
	},
	move:function(d,elm) {
//		alert('inside move');
		if (elm) this.moverow(d,elm);
		else {
			var m = true;
			for (var i=0;i<this.selectedElements.length;i++) {
				if (!this.canMove(d,this.selectedElements[i])) m = false;
			}
			if (m) {
				var moving = this.selectedElements.slice(0,this.selectedElements.length);
				moving.sort(this.compareindex);
				if (d>0) moving.reverse();
				for (var i=0;i<moving.length;i++) {
					this.moverow(d,moving[i]);
				}
			}
		}
		if (typeof(this.onmove)=='function') this.onmove(d,elm);
	},
	moverow:function(d,elm) {
//		alert('moverow');
		this.cleansort();
		var parent = elm.parentNode;
		var sibling = this.canMove(d,elm);
		if (!sibling) return false;
		if (d>0) {
			parent.removeChild(elm);
			parent.insertBefore(elm,sibling);
		} else {
			parent.removeChild(elm);
			if (sibling.nextSibling) parent.insertBefore(elm,sibling.nextSibling);
			else parent.appendChild(elm);
		}
	},
	canMove:function(d,elm) {
//		alert('canMove');
		if (d>0) return elm.previousSibling;
		else return elm.nextSibling;
	},
	clearHeaderLabel:function() {
		alert('inside clearHeaderLabel');
		if (!this.table) return;
		this.cols = new Array();
		var ths = this.head.getElementsByTagName('th');
		alert(ths.toString());
		for (var i=0;i<ths.length;i++) {
			this.cols[ths[i].id] = new Array();
		//	alert(ths[i].id); 
		}	
	}
	

}		