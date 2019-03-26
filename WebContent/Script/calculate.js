function noCheck(ct)
  {
      this.ct = ct;
	  //alert("value check");
	  if(ct.value == "")
	  {
	    //alert("Blank field !!!");
	     //ct.focus();
	     ct.value = 0;
	     return true;
	  }
	  else {
	         if( ct.value.match(/^\d+$/) || ct.value.match(/^\d+\.+\d+$/))
	          {
	           return true;
	              }else{
	             alert("Enter Numeric value !!");
	             ct.value="";
	             ct.focus();
	              return false;
	     	   }
	       }
	
    } //End noCheck()
  
    var gross_total=0;
    
   function addGross()
   {
      var no=0;
      var no1=0;
      var no2=0;
       
    if(!(document.forms[0].netSales.value == ""))
     no=parseFloat(document.forms[0].netSales.value);
              
    if(!(document.forms[0].otherIncome.value == ""))
    no1=parseFloat(document.forms[0].otherIncome.value);
    
    
    if(!(document.forms[0].nonRecurringIncome.value == ""))
    no2=parseFloat(document.forms[0].nonRecurringIncome.value);
    
    var total = no + no1 + no2 ;
    total = total.toPrecision(5);
    document.forms[0].grossIncome.value = total;
    
    if(total != gross_total)
    {
       gross_total = total ;
       addProfitDep();
     }
   }
  
    var init_profitdep = 0;
    
   function addProfitDep()
   {
      var no1=0;
      var no2=0;
      var no3=0;
      var no4=0;
      var no5=0;
      var no6=0;
      var no7=0;
     // alert("from ADDGROSS !");
   if(!(document.forms[0].grossIncome.value == ""))
    no1 = parseFloat(document.forms[0].grossIncome.value);
    //else
    //document.forms[0].grossIncome.value= 0;
    
    if(!(document.forms[0].incDecStock.value == ""))
    no2 = parseFloat(document.forms[0].incDecStock.value);
    //else
    //document.forms[0].incDecStock.value= 0;
    
    if(!(document.forms[0].rawMaterial.value == ""))
    no3 = parseFloat(document.forms[0].rawMaterial.value);
    //else
    //document.forms[0].rawMaterial.value= 0;
    
    if(!(document.forms[0].empCost.value == ""))
    no4 = parseFloat(document.forms[0].empCost.value);
    //else
    //document.forms[0].empCost.value= 0;
    
    if(!(document.forms[0].otherExpenditure.value == ""))
    no5 = parseFloat(document.forms[0].otherExpenditure.value);
   // else
    //document.forms[0].otherExpenditure.value= 0;
    
    if(!(document.forms[0].nonRecurringExpenditure.value == ""))
    no6 = parseFloat(document.forms[0].nonRecurringExpenditure.value);
    //else
    //document.forms[0].nonRecurringExpenditure.value= 0;
    
    if(!(document.forms[0].interest.value == ""))
    no7 = parseFloat(document.forms[0].interest.value);
    //else
    //document.forms[0].interest.value= 0;
    
   var total_profitdep = no1 - (no2 + no3 + no4 + no5 + no6 + no7 );
    total_profitdep = total_profitdep.toPrecision(5);
    //alert(total_profitdep);
    document.forms[0].profitBeforeDepreciation.value = total_profitdep;
    if(total_profitdep != init_profitdep)
     {
        init_profitdep = total_profitdep ;
         ProfitBefTax();
     }
   
   }
   
   var init_profTax = 0;
   
   function ProfitBefTax()
   {
      var no1=0;
      var no2=0;
     
     if(!(document.forms[0].profitBeforeDepreciation.value == ""))
    no1 = parseFloat(document.forms[0].profitBeforeDepreciation.value);
    //else
    //document.forms[0].profitBeforeDepreciation.value = 0;
    
    if(!(document.forms[0].depriciation.value == ""))
    no2 = parseFloat(document.forms[0].depriciation.value);
    //else
    //document.forms[0].depriciation.value = 0;
    
    var total = no1-no2;
    total = total.toPrecision(5);
    document.forms[0].profitBeforeTax.value = total;
     
     if(total != init_profTax)
     {
       init_profTax = total;
       calc_Pat();
     }
     
    }
      
      
    function calc_Pat()
      {
        var no1 = 0;
        var no2 = 0;
        var no3 = 0;
        var no4 = 0;
        
        if(!(document.forms[0].profitBeforeTax.value == ""))
         no1 = parseFloat(document.forms[0].profitBeforeTax.value);
        // else
          // document.forms[0].profitBeforeTax.value = 0;
           
        if(!(document.forms[0].provisionTax.value == ""))
         no2 = parseFloat(document.forms[0].provisionTax.value);
        // else
         //  document.forms[0].provisionTax.value = 0;
           
           if(!(document.forms[0].fringBefTax.value == ""))
            no3 = parseFloat(document.forms[0].fringBefTax.value);
            // else
            // document.forms[0].fringBefTax.value = 0;
             
           if(!(document.forms[0].defferedTaxExpenses.value == ""))
             no4 = parseFloat(document.forms[0].defferedTaxExpenses.value);
            //  else
             //   document.forms[0].defferedTaxExpenses.value = 0;
                
        var total_pat = no1 - (no2 + no3 + no4);
        total_pat = total_pat.toPrecision(5);
       // alert(total_pat);
        document.forms[0].profitAftTax.value = total_pat;
     
      }
  
   function calc_Bookvalue()
   {
      var no1 = 0;
      var no2 = 0;
      var total_bookvalue = 0; 
      if(!(document.forms[0].netWorth.value == ""))
            no1 = parseFloat(document.forms[0].netWorth.value);
            
       if(!(document.forms[0].numberOfShares.value == ""))
            no2 = parseFloat(document.forms[0].numberOfShares.value);
             
             if(!( no1 == 0 || no2 == 0)) {
             total_bookvalue = (no1/no2);
             total_bookvalue = total_bookvalue.toPrecision(5);
             }
             else
             {
             total_bookvalue = 0;
             }
             //alert(total_bookvalue);
              document.forms[0].bookValue.value = total_bookvalue ;
   }