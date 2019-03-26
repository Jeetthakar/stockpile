function checkdatecurrent(objName)
{
	var datefield = objName;
	alert("Todate is ..."+datefield.value);
	var strMonth;
	var strYear;
	var strDate;
	var strDateArray;
	var intElement;
	var strSeparatorArray = new Array("-"," ","/",".");
	strDate = datefield.value;
    var intday;var int_td;var int_mnth;var int_yr;
	var int_month;
	var intYear;
	var today = new Date();
	var td = today.getDate();
	var mnth = today.getMonth(); 
	mnth=mnth+1;
	var yr = today.getFullYear();
	int_td=   parseInt(td, 10); 
	int_mnth=  parseInt(mnth, 10); 
	int_yr=   parseInt(yr, 10); 
	for (intElement = 0; intElement < strSeparatorArray.length; intElement++) {
	if (strDate.indexOf(strSeparatorArray[intElement]) != -1) {
			strDateArray=strDate.split(strSeparatorArray[intElement]);
			if (strDateArray.length != 3) {
				err = 1;
				alert(" DateArray length < 1: err :" + err);
				return false;
			}
			else {
			strDay = strDateArray[0];
			alert("strDay"+strDay);
			strMonth = strDateArray[1];
			alert("strMonth"+strMonth);
			strYear = strDateArray[2];
			alert("strYear"+strYear);
			}
			}
			}
			
			intday = parseInt(strDay, 10);
			alert("intday"+intday);
			int_month=parseInt(strMonth,10);
			alert("int_month"+int_month);
			intYear= parseInt(strYear,10);
			alert("intYear"+intYear);
		if(intYear>int_yr)
		{
			alert("To Date should be less than current Date.");
			return false;
		}
		if((intYear==int_yr)&&(int_month>int_mnth))
		{
			alert("To Date should be less than current Date.");
			return false;
		}
		if((intYear==int_yr)&&(int_month==int_mnth)&&(intday>int_td))
		{
			alert("To Date should be less than current Date.");
			return false;
		}
		else {
			return true;
	   }
}	
function checkdate(objName)
{
	var datefield = objName;
	if ( datefield.length < 8) {
		datefield.select();	
		alert(" Please insert data in (dd/mm/yyyy) format. ");
		return false;
	}
	
	
	if (chkDate(objName) == false)
	 {
	 
	   alert("That date is invalid.  Please try again.");
	   datefield.select();
	   
	   datefield.focus();
	   return false;
	 }
	else {
		return true;
	     }
}


	



function chkDate(objName) {
	
	var strDatestyle = "EU";
	var strDate;
	var strDateArray;
	var strDay;
	var strMonth;
	var strYear;
	var intday;
	var int_month;
	var intYear;
	var booFound = false;
	var datefield = objName;
	
	var strSeparatorArray = new Array("-"," ","/",".");
	var intElement;
	var err = 0;
	var strMonthArraych = new Array(12);
	strMonthArraych[0] = "Jan";
	strMonthArraych[1] = "Feb";
	strMonthArraych[2] = "Mar";
	strMonthArraych[3] = "Apr";
	strMonthArraych[4] = "May";
	strMonthArraych[5] = "Jun";
	strMonthArraych[6] = "Jul";
	strMonthArraych[7] = "Aug";
	strMonthArraych[8] = "Sep";
	strMonthArraych[9] = "Oct";
	strMonthArraych[10] = "Nov";
	strMonthArraych[11] = "Dec";
	var strMonthArray = new Array(12);
	strMonthArray[0] = "01";
	strMonthArray[1] = "02";
	strMonthArray[2] = "03";
	strMonthArray[3] = "04";
	strMonthArray[4] = "05";
	strMonthArray[5] = "06";
	strMonthArray[6] = "07";
	strMonthArray[7] = "08";
	strMonthArray[8] = "09";
	strMonthArray[9] = "10";
	strMonthArray[10] = "11";
	strMonthArray[11] = "12";
	strDate = datefield.value;

	if (strDate.length < 1  ) {
				datefield.select();
	  			 alert("Enter Valid date");
	  			 datefield.focus();
				return true;
				
				}
	
	for (intElement = 0; intElement < strSeparatorArray.length; intElement++) {
		if (strDate.indexOf(strSeparatorArray[intElement]) != -1) {
			strDateArray = strDate.split(strSeparatorArray[intElement]);
			if (strDateArray.length != 3) {
				err = 1;
				//alert(" DateArray length < 1: err :" + err);
				return false;
			}
			else {
			strDay = strDateArray[0];
			strMonth = strDateArray[1];
			strYear = strDateArray[2];
			}
	 	 booFound = true;
       		 } //end if condition
	} //  end for loop
       if (booFound == false) {
		if (strDate.length>5) {
			strDay = strDate.substr(0, 2);
			strMonth = strDate.substr(2, 2);
			strYear = strDate.substr(4,4);
  		 }
	}
	//if (strYear.length == 4) {
	//	strYear = strYear.substr(2,2);
//	}
			// US style

	intday = parseInt(strDay, 10);             //day
	if (isNaN(intday)) {
			err = 2;
			//alert(" Day is NaN. err:" + err);
			return false;
	}
	int_month = parseInt(strMonth, 10);         //Month
		if (isNaN(int_month)) {
			for (i = 0;i<12;i++) {
			    if (strMonth.toUpperCase() == strMonthArraych[i].toUpperCase()) {
				int_month = i+1;
				strMonth = strMonthArray[i];
				i = 12;
  			     }
			}
			if (isNaN(int_month)) {
				err = 3;
				//alert(" Month is NaN. err:" + err);
				return false;
  			 }
		}
		
		
		if (strYear.length == 2 ) {
			strYear = "20" + strYear;
		}
		
   	intYear = parseInt(strYear, 10);		//Year
	if (isNaN(intYear)) {
		err = 4;
		//alert(" Year is NaN; err:" + err);
		return false;
	}
	if (int_month>12 || int_month<1) {
		err = 5;
		//alert(" Invalid month. err: " + err);
		return false;
	}
	if ((int_month == 1 || int_month == 3 || int_month == 5 || int_month == 7 || int_month == 8 || int_month == 10 || int_month == 12) && (intday > 31 || intday < 1)) {
		err = 6;
		//alert(" Day for Month. err: " + err);
		return false;
	}
	if ((int_month == 4 || int_month == 6 || int_month == 9 || int_month == 11) && (intday > 30 || intday < 1)) {
		err = 7;
		//alert(" Day for Month. err: " + err);
		return false;
	      }
	if (int_month == 2) {
			if (intday < 1) {
				err = 8;
				//alert(" Date error for Feb. err:" + err);
				return false;
			}
			if (LeapYear(intYear) == true) {
		 	 	 if (intday > 29) {
					err = 9;
					//alert(" Date error for Feb. err:" + err);
					return false;
				  }
			}
			else {
				if (intday > 28) {
					err = 10;
					//alert(" Date error for Feb. err:" + err);
					return false;
	         		 }
			}
	}   
	
	//end of if (int_month == 2)
    if (intday == 0)
        {
          intday ="00";
    }
    if (intday == 1)
    {
   
      intday ="01";
    }
    if (intday == 2)
        {
          intday ="02";
    }
    if (intday == 3)
        {
          intday ="03";
    }
    if (intday == 4)
        {
          intday ="04";
    }
    if (intday == 5)
        {
          intday ="05";
    }
    if (intday == 6)
        {
          intday ="06";
    }
    if (intday == 7)
        {
          intday ="07";
    }
    if (intday == 8)
        {
          intday ="08";
    }
    if (intday == 9)
        {
          intday ="09";
    }
    

datefield.value = intday + "-" + strMonthArray[int_month-1] + "-" + strYear;

return true;
}


//////    calculate if the year is a leap year    ///////////////////////

function LeapYear(intYear) {
		if (intYear % 100 == 0) {
			if (intYear % 400 == 0) { return true; }
		}
		else {
			if ((intYear % 4) == 0) { return true; }
			}
	return false;
} //end of function LeapYear(intYear)


//
//  
////////////////////////////////////////////////////////////////////////////////////////////\\\\\\\\\\\\\\\\

/////  Check that dateTo is greater than dateFrom   //////////////


function checkValidDates(objNameFrom,objNameTo)
{
	var datefieldFrom = objNameFrom;
	var datefieldTo = objNameTo;
	
	if (chkValidDates(objNameFrom,objNameTo) == false)
	 {
	   datefieldTo.select();
	   alert("That date is invalid!!!.  Please try again.");
	   datefieldTo.focus();
	   return false;
	 }
	else {
		return true;
     }

}

function chkValidDates(objNameFrom ,objNameTo) {
	var strDatestyle = "EU";
	var strDateFrom;
	var strDateTo;
	var strDateArrayFrom;
	var strDateArrayTo;
	var strDateFrom;
	var strDateTo;
	var strDayFrom;
	var strDayTo;
	var strMonthFrom;
	var strMonthTo;
	var strYearFrom;
	var strYearTo;
	var intdayFrom;
	var intdayTo;
	var intdayFrom;
	var int_monthTo;
	var int_monthFrom;
	var intYearFrom;
	var intYearTo;
	var booFoundFrom = false;
	var booFoundTo = false;
	var flage = true;
	var datefieldFrom = objNameFrom;
	var datefieldTo = objNameTo;
	var strSeparatorArray = new Array("-"," ","/",".");
	var intElementFrom;
	var intElementTo;
	var errFrom = 0;
	var errTo = 0;
	var strMonthArraych = new Array(12);
	strMonthArraych[0] = "Jan";
	strMonthArraych[1] = "Feb";
	strMonthArraych[2] = "Mar";
	strMonthArraych[3] = "Apr";
	strMonthArraych[4] = "May";
	strMonthArraych[5] = "Jun";
	strMonthArraych[6] = "Jul";
	strMonthArraych[7] = "Aug";
	strMonthArraych[8] = "Sep";
	strMonthArraych[9] = "Oct";
	strMonthArraych[10] = "Nov";
	strMonthArraych[11] = "Dec";
	var strMonthArray = new Array(12);
	strMonthArray[0] = "01";
	strMonthArray[1] = "02";
	strMonthArray[2] = "03";
	strMonthArray[3] = "04";
	strMonthArray[4] = "05";
	strMonthArray[5] = "06";
	strMonthArray[6] = "07";
	strMonthArray[7] = "08";
	strMonthArray[8] = "09";
	strMonthArray[9] = "10";
	strMonthArray[10] = "11";
	strMonthArray[11] = "12";
	strDateFrom = datefieldFrom.value;
        strDateTo= datefieldTo.value;
	if(strDateFrom.length < 1 || strDateFrom.length > 10) {
	 	alert("Enter Valid To Date");
	        return  true;
	}
						
	
	////////////////////////////////////
	///	Parse dateTo
	
	for (intElementTo = 0; intElementTo < strSeparatorArray.length; intElementTo++) {
			if (strDateTo.indexOf(strSeparatorArray[intElementTo]) != -1) {
				strDateArrayTo = strDateTo.split(strSeparatorArray[intElementTo]);
				if (strDateArrayTo.length != 3) {
					errTo = 1;
					return false;
				}
				else {
				strDayTo = strDateArrayTo[0];
				strMonthTo = strDateArrayTo[1];
				strYearTo = strDateArrayTo[2];
				}
		 	 booFoundTo = true;
		 	 //alert("Day " + strDayTo + " Month " + strMonthTo + " Year " + strYearTo);
	       		 } //end if condition
	} //  end for loop
	/////////////////////////////////////
	///	Parse dateFrom
	
	for (intElementFrom = 0; intElementFrom < strSeparatorArray.length; intElementFrom++) {
				if (strDateFrom.indexOf(strSeparatorArray[intElementFrom]) != -1) {
					strDateArrayFrom = strDateFrom.split(strSeparatorArray[intElementFrom]);
					if (strDateArrayFrom.length != 3) {
						errFrom = 1;
						return false;
					}
					else {
					strDayFrom = strDateArrayFrom[0];
					strMonthFrom = strDateArrayFrom[1];
					strYearFrom = strDateArrayFrom[2];
					}
			 	 booFoundFrom = true;
			 	 //alert("Day " + strDayFrom + " Month " + strMonthFrom + " Year " + strYearFrom);
		       		 } //end if condition
	} //  end for loop
	/////////////////////////////////////////////////
	// //  		check for dateTo and dateFrom
	
       if (booFoundFrom == false) {
		if (strDateFrom.length>5) {
			strDayFrom = strDateFrom.substr(0, 2);
			strMonthFrom = strDateFrom.substr(2, 2);
			strYearFrom = strDateFrom.substr(4,4);
  		 }
	}
	if (booFoundTo == false) {
			if (strDateTo.length>5) {
				strDayTo = strDateTo.substr(0, 2);
				strMonthTo = strDateTo.substr(2, 2);
				strYearTo = strDateTo.substr(4,4);
	  		 }
	}
	
	intdayFrom = parseInt(strDayFrom, 10);             //day
	
	intdayTo = parseInt(strDayTo, 10);             //day to
		
	
	int_monthFrom = parseInt(strMonthFrom, 10);         //Month
		
	int_monthTo = parseInt(strMonthTo, 10);         //Month To
		
      intYearFrom = parseInt(strYearFrom, 10);	
      intYearTo = parseInt(strYearTo, 10);		 //Year
	  
	          if(intYearTo < intYearFrom )          // check condition 
	           {
	               
	               flage = false;
	           } else { if(intYearTo == intYearFrom)
	                       { if (int_monthTo < int_monthFrom)
				  	{				                  
			            flage = false;
		                    }
		               		 else { if (int_monthTo == int_monthFrom)
	            		                 {
	            		        		if(intdayTo < intdayFrom)
				             			{
				                		      flage = false;
					        		}
				                 }		
					}
				}	
			  }
			  
		//alert(" strDateFrom= " + strDateFrom + " strDateTo= " + strDateTo );
			
		  if (intdayTo == 0)
        {
          intdayTo ="00";
    }
    if (intdayTo == 1)
    {
   
      intdayTo ="01";
    }
    if (intdayTo == 2)
        {
          intdayTo ="02";
    }
    if (intdayTo == 3)
        {
          intdayTo ="03";
    }
    if (intdayTo == 4)
        {
          intdayTo ="04";
    }
    if (intdayTo == 5)
        {
          intdayTo ="05";
    }
    if (intdayTo == 6)
        {
          intdayTo ="06";
    }
    if (intdayTo == 7)
        {
          intdayTo ="07";
    }
    if (intdayTo == 8)
        {
          intdayTo ="08";
    }
    if (intdayTo == 9)
        {
          intdayTo ="09";
    }
    	
    
      if (intdayFrom == 0)
        {
          intdayFrom ="00";
    }
    if (intdayFrom == 1)
    {
   
      intdayFrom ="01";
    }
    if (intdayFrom == 2)
        {
          intdayFrom ="02";
    }
    if (intdayFrom == 3)
        {
          intdayFrom ="03";
    }
    if (intdayFrom == 4)
        {
          intdayFrom ="04";
    }
    if (intdayFrom == 5)
        {
          intdayFrom ="05";
    }
    if (intdayFrom == 6)
        {
          intdayFrom ="06";
    }
    if (intdayFrom == 7)
        {
          intdayFrom ="07";
    }
    if (intdayFrom == 8)
        {
          intdayFrom ="08";
    }
    if (intdayFrom == 9)
        {
          intdayFrom ="09";
    }
    
    
    
		if(flage == false ) {
			//alert(datefieldFrom.value + " To " + datefieldTo.value );
			return false;
		}
		
	datefieldFrom.value = intdayFrom + "-" + strMonthArray[int_monthFrom-1] + "-" + strYearFrom;
	
	datefieldTo.value = intdayTo + "-" + strMonthArray[int_monthTo-1] + "-" + strYearTo;
  				
    
       return true;
	  
	
}


