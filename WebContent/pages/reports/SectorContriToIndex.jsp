<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@ page import = "harrier.income.com.report.*" %>
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="app.*"%><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");
%>

<html:html>
<head>
	<link href="StyleSheet.css" rel="stylesheet" type="text/css">
	 <script language="javascript" src="../Script/codethatcalendarstd.js"></script>
	 <script language="javascript" src="box_ex.js"></script>
	 <script language="javascript">
				var c2 = new CodeThatCalendar(caldef2);
	 </script>
	 <script type="text/javascript" src="../Script/Event.js"></script>
	 <script type="text/javascript" src="../Script/SortedTable.js"></script>
	 <style type="text/css">

			/* table styles*/
			.sorted td, th {border:0;padding:2px 6px;margin:0;border-right:1px solid #336;
				border-bottom:1px solid #336;background-color: #dddddd;color: #black;
				font-size: 10px;padding-left: 2px;}
			td[axis='number'], td[axis='date'] {text-align:right;}
			th {background-color:#cacaca;padding:2px 20px;color: blue;font-size: 12px;
				vertical-align: baseline;line-height: 18px;}
			tfoot td {border-top:0px solid #003;}
			thead th {border-bottom:1px solid #003;}
			.sortedminus {background-color:#ecc;}
			.sortedplus {background-color:#cec;}


		</style>
</head>

<body onload="initialize()">
<html:form action="/sectorContriToIndexAction" >
	<div id="title">
	<table width="800" cellspacing="0" cellpadding="0" >
        	<tr>
		       	<td width="265" class="subHeader" nowrap="nowrap">
		     		&nbsp;
		       	</td>
		       	<td  class="subHeader" width="530" align="left" colspan="2" nowrap="nowrap">
		    		<font size="3" face="Arial"><b>Sector Contribution To Change In Index</b>
		          	</font>
		         </td> 
	        </tr>
	</table>
	</div>

			    
         <table border="0" width="100%"   cellspacing="0" cellpadding="3" >
         	<tr>
         		<td width="157" align="right" nowrap="nowrap">
         		     <font size="2" face="Arial">
					  	 <bean:message key="indexUpdate.selectIndex" />
					 </font>
       			 </td>
       			 <td width="489" nowrap="nowrap">
					<html:select property="index">
						<html:optionsCollection property="vectorIndexlist" name="sectorContriToIndexBean"/>
					</html:select>
      			</td>
			</tr>	
		</table>
		<table>
 			<tr>
				<td width="150"  nowrap="nowrap" align="right"><font size="2" face="Arial" > 
				   	  <bean:message key="corporate.Fdate" />
    	        </td> 
	        	<td  width="80" align="right"  nowrap="nowrap">
					<html:text property="from" size="10" readonly="true"/>
				</td>
				<td width="80" align="left" nowrap="nowrap">    
        	         <html:button property="from_button" value="..." onclick="c2.popup('from');"/>
            	</td>
			
			    <td width="50" align="right" nowrap="nowrap"><font size="2" face="Arial">
                        <bean:message key="corporate.Tdate" />
    	        </td>

        	    <td width="80" align="right" nowrap="nowrap">	
					<html:text property="to" size="10"  readonly="true" />
				</td>

				<td  width="80" align="left" nowrap="nowrap">    
        	         <html:button property="to_button" value="..." onclick="c2.popup('to');" />
            	</td>
            	<td width="80" nowrap="nowrap">
					<html:submit property="b1" onclick="return go();"> <bean:message key="Reports.View"/> </html:submit>
			    </td>
			    <td>
			    	<html:hidden property="buttonValue"  name="sectorContriToIndexBean"/>		   			  
			    </td>
 			 </tr>
		</table>
		
		<logic:notEqual value="yes" property="buttonValue" name="sectorContriToIndexBean">
				 <table border="0" align="left" width="700" height="222" cellspacing="0" cellpadding="0">
	  				<tr>
        				<td width="99">
        				</td>
						<td class="gridStyle-message" align="center" valign="middle">
          					<p style="margin-left: 9">
          						Select Index Name and Date To View Data
          					</p>          					
          				</td>
      				</tr>
      			  </table>
     	 </logic:notEqual> 
		 
		 <logic:equal value="yes" property="buttonValue" name="sectorContriToIndexBean">
		 
		 	<bean:define id="details" name="sectorContriToIndexBean" property="sectorContri"/>
	  		<bean:size id="dataCount" name="sectorContriToIndexBean" property="sectorContri"/>
	  			
	  		<logic:equal value="0" name="dataCount" >
		
			<table id = "noStrutsData" border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
	  	  	 				 <tr>
			          			<td width="99">
			          			</td>
	  		  					<td class="gridStyle-message" align="center" valign="middle">
          							<p style="margin-left: 9">
          								<bean:message key="IndexCompareOHCL.ndata" />
          							</p>
          						</td>
        	  				</tr>
      	 				</table>
        	</logic:equal>
      		<br>
        	<logic:notEqual value="0" name="dataCount"> 
        	
			<table class="sorted" ID="sortTabletable" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
				<thead>
					<tr>       	         							
										<th nowrap="nowrap" align="center" id="sname" class="gridStyle-header">
          									<span><b>Sector Name</b></span>
          								</th>
          								
          								<th align="center" nowrap="nowrap" id="swtg" class="gridStyle-header">
          									<span><b>Sector Wtg</b></span>
          								</th>
          								
		        						
        			 </tr>   		
				</thead>
				<tbody>
          			<logic:iterate id="details"  name="sectorContriToIndexBean" property="sectorContri">
							  <tr>
							  	<td valign="middle" class="gridStyle-odd" nowrap="nowrap" align="left" axis="sstring" headers="sname"
          						title='<bean:write name="details" property="sectorName"/>'>
							    <font face="Verdana" size="2" color="blue">
							      <bean:write name="details" property="sectorName"/>
							    </font>
							    </td>
							          		
							    <td align="right" class="gridStyle-odd" bgcolor="white" valign="middle" axis="number" headers="swtg" nowrap="nowrap"
          						title='<bean:write name="details" property="weightage"/>'>
							    <font face="Verdana" size="2" color="blue">
							         <bean:write name="details" property="weightage"/>
							    </font>
							    </td>	     					          		
							  </tr>							          
    				</logic:iterate>
    				</tbody>
          	   </table> 
          		</logic:notEqual>	
   			</logic:equal>
   			<br>
   			<p></p>
   			<p>
			</p>
		
			 
         
   	     <logic:equal value="yes" property="buttonValue" name="sectorContriToIndexBean">
		 <%	
		   	   	String filename=null;
   		     //   GraphMethodsPf gm=new GraphMethodsPf();
   		        GraphMethodsPf gm=ConnectInit.getGraphMethodsPf();
   	    	    filename=gm.getGraphChartSectorContri(session,new PrintWriter(out));
   	    	    
   	    	    String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
   	    	    
   	     %>	
   	     
   	     <table  border="0" align="bottom" width="631" height="222" cellspacing="0" cellpadding="0">
	  	  	 				 <tr>
			          			<td width="99">
			          			</td>
	  		  					<td class="gridStyle-message" align="center" valign="middle">
          								<img src="<%= graphURL %>" height="270"  width="500" >
          						</td>
        	  				</tr>
      	 				</table>	
   	     	
   	    </logic:equal>
		
			     
			
		    
</html:form>
</body>	
<script language="javascript">
	
function checkdatecurrent(objName)
{
	var datefield = objName;
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
			strMonth = strDateArray[1];
			strYear = strDateArray[2];
			}
		  }
		}
		intday = parseInt(strDay, 10);
		int_month=parseInt(strMonth,10);
		intYear= parseInt(strYear,10);
		if(intYear>int_yr)
		{
			return false;
		}
		if((intYear==int_yr)&&(int_month>int_mnth))
		{
			return false;
		}
		if((intYear==int_yr)&&(int_month==int_mnth)&&(intday>int_td))
		{
			return false;
		}
		else {
			return true;
	   }
}


function initialize() {
	var today = new Date();
	var td = today.getDate();
	if(td<10)
	td="0"+td;
	var mnth = today.getMonth(); 
	mnth=mnth+1;
	if(mnth<10)
	mnth="0"+mnth;
	var yr = today.getFullYear();
	var s = "-";
	if((document.forms[0].from.value)=="")
	document.forms[0].from.value= td+ s + mnth + s + yr;
	if((document.forms[0].to.value)=="")
	document.forms[0].to.value= td+ s + mnth + s + yr;
	initSort();
}		


function initSort() {
	mySorted = new SortedTable();
	mySorted.colorize = function() {
		for (var i=0;i<this.elements.length;i++) {
			if (i%2){
				this.changeClass(this.elements[i],'even','odd');
			} else {
				this.changeClass(this.elements[i],'odd','even');
			}
		}
	}
	mySorted.onsort = mySorted.colorize;
	mySorted.onmove = mySorted.colorize;
	mySorted.colorize();
}
	
function go() {
	var objTo=document.forms[0].to;
	var msg="";
	if(document.forms[0].index.value==""){
		msg=msg+"Select index names \n";
	}
	if(document.forms[0].from.value==""){
		msg=msg+"Select from date\n";
	}
	if(document.forms[0].to.value==""){
		msg=msg+"Select to date\n";
	}
	if(msg!=""){
		alert(msg);	
		document.forms[0].buttonValue.value="no";
		return false;
	}else{
		//alert(document.forms[0].buttonValue.value);
		document.forms[0].buttonValue.value="yes";
		return true;
	}
	return true;
}
	function popprinter(url)
	{
		newwindow=window.open(url,'name','height=500,width=900,scrollbars=yes,left=80,top=130,menubar=yes,status=yes');
		if (window.focus) {newwindow.focus()}
	}
		function PrintThisPage() 
	{ 

  		var ttle =document.getElementById('title').innerHTML;
   		var tble = document.getElementById('chart').innerHTML;
   		var winprint=window.open("","",""); 
      	winprint.document.open(); 
       	winprint.document.write(ttle);
      	winprint.document.write('<br>');
      	
       	winprint.document.write(tble); 
       	winprint.document.close(); 
       	winprint.focus(); 
	}
	
function getSelected(opt) {					
            var selected = new Array();
            var index = 0;
            for (var intLoop = 0; intLoop < opt.length; intLoop++) {
               if (opt[intLoop].selected) {
                  index = selected.length;                                                    
                  selected[index] = opt[intLoop].value;                  
               }
            }
            return selected;
  }	
	


</script>
</html:html>




