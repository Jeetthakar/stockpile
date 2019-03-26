/**
 * 	Event.js: JavaScript file to sort the tables 
 *	Additional File(s):	SortedTable.js
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
 *	To implement this sorting, the files (Event.js and SortedTable.js) are to be
 *	included along with the stylesheet declarations.
 *	The table class should be "sorted" and table ID should be "sortTable".
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
 
function addEvent(obj,type,fn) {
	if (obj.addEventListener) obj.addEventListener(type,fn,false);
	else if (obj.attachEvent)	{
		obj["e"+type+fn] = fn;
		obj[type+fn] = function() {obj["e"+type+fn](window.event);}
		obj.attachEvent("on"+type, obj[type+fn]);
	}
}

function removeEvent(obj,type,fn) {
	if (obj.removeEventListener) obj.removeEventListener(type,fn,false);
	else if (obj.detachEvent) {
		obj.detachEvent("on"+type, obj[type+fn]);
		obj[type+fn] = null;
		obj["e"+type+fn] = null;
	}
}