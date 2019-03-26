
<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.text.ParseException" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@ page import = "harrier.income.com.report.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="app.*"%>
<%@ page  import="harrier.income.com.report.*" %><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");

		LogonForm form = null;
		if(request.isRequestedSessionIdValid())	{	
			form = (LogonForm)session.getAttribute("user");
		}
		if(form==null ||(!request.isRequestedSessionIdValid())){
			response.sendRedirect("../userlogintemp.jsp");
		}
	%>

<html:html>
	<head>
	
		<SCRIPT language=javascript>
				function hideLeftCol(id)
				{
				if(this.document.getElementById(id).style.display=='none')
				{
				this.document.getElementById(id).style.display='inline';
				document.getElementById("HideHandle").src = '../closeImage.gif';
				// this.document.getElementById(id).style.width='10px';
				Set_Cookie('showLeftCol','true',30,'/','','');
				var show = Get_Cookie('showLeftCol');
				//document['HideHandle'].src = 'images/hide.gif';
				document.getElementById("HideHandle").src = '../open.gif';
				}
				else{
				// this.document.getElementById(id).style.display='none';
				this.document.getElementById(id).style.display='none';
				document.getElementById("HideHandle").src = '../openImage.gif';
				Set_Cookie('showLeftCol','false',30,'/','','');
				var show = Get_Cookie('showLeftCol');
				//document['HideHandle'].src = 'images/show.gif';
				}
				}
		</script>
	
	
		 <html:base/>
			<link href="./StyleSheet.css" rel="stylesheet" type="text/css">
			<script language="javascript" src="./codethatcalendarstd.js"></script>
			<script language="javascript" src="../ScriptI/date_script.js"></script>		 
			<script language="javascript" src="box_ex.js"></script>
			<script type="text/javascript" src="../Script/SortedTable.js"></script>
			<script type='text/javascript' src='/Stockpile/dwr/interface/GraphMethods.js'></script>
			<script type='text/javascript' src='/Stockpile/dwr/interface/MoveTable.js'></script>
			<script type='text/javascript' src='/Stockpile/dwr/engine.js'></script>
			<script type='text/javascript' src='/Stockpile/dwr/util.js'></script>
		 
		 <script language="javascript">
			var c2 = new CodeThatCalendar(caldef2);
		 </script> 
		 <script type="text/javascript" src="./sorttable.js"></script>
		 
		 <style type="text/css">	
			/* Sortable tables */
			table.sortable a.sortheader {
    			background-color:#eee;
   	 			font-size: 13px; 
    			font-family: Verdana;
    			color: black;
    			text-decoration: none;
    			display: block;
			}
			table.sortable span.sortarrow {
    			color: black;
    			text-decoration: none;
			}
			
			
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
		
	<body onload="initialize()" >
	
		<table width="100%" height="100%" >
		<tr>
		<td align="left" valign="top" bgcolor="#CCddee">
		<DIV id="leftCol" style="DISPLAY: none; width:160; height:100%;">
			<%@ include file="../tree3.jsp" %>
		</div>
		<IMG id="HideHandle" src="../openImage.gif" style="CURSOR: hand; position:absolute;" onclick='hideLeftCol("leftCol");' src="fold.gif" width="10" height="25">
		</td>
		<td align="left" valign="top">
	
			<div id="title" >
				<table width="1000" cellspacing="0" cellpadding="0" >
		        		<tr>
				          	<td width="250" class="subHeader" nowrap="nowrap">
				          		&nbsp;
				          	</td>
				          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
				          		<font size="3" face="Verdana">
				          			<b><bean:message key="CompanyWiseWeight.title" /></b>
				          		</font>
				         	</td> 
			          </tr>
				</table> 
			</div>
	
	<%
			String ajax1="no";
			try{
				ajax1=request.getParameter("ajax1");
				session.setAttribute("ajax1",ajax1);				
			}catch (Exception e) {
				// TODO: handle exception
			//	Logging.error(" Error :"+e.getMessage());
			}
	%>
	
		
	
	<html:form action="companyWiseWeightage">
	<jsp:setProperty name="CompanyWiseWeightageBean" property="userid1" value='<%=session.getAttribute("userid")%>'/>
	<html:hidden property="indexName" name="CompanyWiseWeightageBean" />
	
		<%
                       	String excel = "./FileDownload.jsp?&type=2&filename=CompanyWiseWeightage.xls";
                       	String xml = "./FileDownloadXmlNew.jsp?&type=2&filename=CompanyWiseWeightage.xml";
						String path_pdf="./FileDownload_Pdf.jsp?type=2&filename=CompanyWiseWeightage.pdf";						
						String str_url = "./EmailReport.jsp?switch_type=2&cas=1&rname=CompanyWiseWeightage.xls";
   		%>
	
	<table id="strutslinks" cellpadding="0" cellspacing="0" align="center">
   			<tr>
   				<td width="400">
   				</td>
	   			<td>
				<logic:notEmpty property="companyWeightage"  name="CompanyWiseWeightageBean">		   			
	   			
	   			<a href="javascript:PrintThisPage();" ><font size="1"><bean:message key="LatestDivisor.printerf" /></font></a> 
	   			<a href="<%=excel%>" ><font size="1" > <bean:message key="LatestDivisor.downloade" /></font></a>
	   			<a href="<%=xml%>" ><font size="1" ><beanage key="PreIndex.Export_to_Xml" /></font></a>
	   			<a href="<%=path_pdf%>" ><font size="1" ><bean:message key="LatestDivisor.pdfr" /></font></a>
	   			<a href="<%= str_url %>" > <font size="1" > <bean:message key="LatestDivisor.emailr" /></font></a>
	   					
	   			</logic:notEmpty>
	   			</td>
   			</tr>
   	</table>
	
	<table id="ajaxlinks" cellpadding="0" cellspacing="0" align="right" style="display: none">
	  	<%
	  		
	  					String excelaj = "./FileDownload.jsp?&type=2&filename=CompanyWiseWeightage.xls";
                       	String xmlaj = "./FileDownloadXmlNew.jsp?&type=2&filename=CompanyWiseWeightage.xml";
						String path_pdfaj="./FileDownload_Pdf.jsp?type=2&filename=CompanyWiseWeightage.pdf";						
						String str_urlaj = "./EmailReport.jsp?switch_type=2&cas=1&rname=CompanyWiseWeightage.xls";
   		
	  	%>
		
   			<tr>
	   			<td width="390"></td>
	   			
	   			<td>	
		   			<a href="javascript:PrintThisPage();" ><font size="1"><bean:message key="LatestDivisor.printerf" /></font></a> 
		   			<a href="<%=excel%>" ><font size="1" > <bean:message key="LatestDivisor.downloade" /></font></a>
		   			<a href="<%=xml%>" ><font size="1" >Export to Xml</font></a>
		   			<a href="<%=path_pdf%>" ><font size="1" ><bean:message key="LatestDivisor.pdfr" /></font></a>
		   			<a href="<%=str_url %>" > <font size="1" > <bean:message key="LatestDivisor.emailr" /></font></a>   					
	   			</td>
   			</tr>
   			
		</table>
		
		<br><br>
		<table width="656" >
	     	<tr>
  			   
    				<td width="202" nowrap="nowrap" align="right">
    					<font size="2" face="Verdana">
		    	      		<bean:message key="indexUpdate.selectIndex" /> 
		    	      	</font>
        	    	</td>          
			        <td width="400" nowrap="nowrap" align="left">
						<html:select property="d1"  onchange="go();">
							<html:optionsCollection property="vector_indexlist" name="CompanyWiseWeightageBean"/>
						</html:select>
				 	</td>
				 
			 </tr>
			 </table>
		
	    <table >
		
         	<tr>
         	  <td></td>
         	 <td></td>
         	  <td></td>
    	 <td></td>
			<td nowrap="nowrap" align="left">	
					   <font size="2" face="Verdana">  
					     	<bean:message key="CompanyWiseWeight.seldate" />&nbsp;:
				     </font>
			</td>
	    	
	    	<td  nowrap="nowrap" align="left">
				<html:text property="date" readonly="true"  size="10"/>
			</td>	
			
			<td  nowrap="nowrap">
            	<input onclick="c2.popup('date');" type="button" value="...">
            </td>	
            
            <td  nowrap="nowrap" align="left">
                 	 <html:hidden property="hiddenVar" name="CompanyWiseWeightageBean"/>
                 	 <%	if(ajax1.equals("yes")){
	  				 %>
                     <html:submit property="b1" onclick="return test1()"> <bean:message key="Reports.View"/></html:submit>
                     <% }else{
					 %>
                     <INPUT type="Button" name="view" value="AjaxView" onclick="getCompanyWiseWeightageDetails()"/>
                     <% } %> 
    		</td>
    		   <td></td>
			     <html:hidden property="download"  name="CompanyWiseWeightageBean" />
			    </tr>
				<tr>
					<td>
         	 			<table>
         	 				<tr>
								<td nowrap="nowrap">
									<html:checkbox property="check2"/>		     	         
								</td>
								<td nowrap="nowrap" >	
									<font size="2" face="Verdana">
										<bean:message key="IndexCompose.schart" />
									</font>
								</td>
		 		 			</tr>
		 		 		</table>
		 		 	</td>
         		</tr> 
	    	</table>
 		<p></p>
 	<div id = "Ajaxcontentstart">	
 		<table align="center">
 		<tr>
 			<img id="graph" align="top" width="500" height="270" border="0" style="display: none;">
 		</tr>
        
<!-- ============================================ For Ajax ================================================== -->
	<tr>
		<td>
			<table class ="sorted"  ID="sortTable"
				border="1" align="center" cellpadding="0" cellspacing="0"  style="display:none">    
               
				<thead >
				<tr>				
					<th width="150">Company Name</th>
					<th width="170">Mkt. Cap.(In Millions)</th>	
					<th width="150">Weightage(%)</th>												
				</tr>
				</thead>						
				<tbody id="indexMovingTable"> </tbody>								
		</table>
		</td>
		</tr>
		</table>
	</div>	
		
		<table align="center" border="0" width="600" height="300"  cellspacing="0" cellpadding="0" class="sortable">
    		     <tr>
		         	<td id = "nodata" bgcolor="#84AADE" align="center" valign="middle" nowrap="nowrap" style="display: none;">
		         		<p style="margin-left: 9">
		         			<b><font face="Verdana" color="blue" size="4">
     										<bean:message key="IndexCompareOHCL.ndata" />
     							</font>
     						</b>
     					</p>
     				</td>
				</tr>
   	        </table>			
		  
<!-- ============================================ For Ajax ================================================== -->
  		<logic:notEqual  name="CompanyWiseWeightageBean" property="hiddenVar" value="yes" >
		    <table align="center" border="0" width="600" height="300"  cellspacing="0" cellpadding="0" class="sortable">
    		      <tr>
        			  <td id = "selectindexmessage" bgcolor="#84AADE" align="center" valign="middle">
            			  <p style="margin-left: 9">
            				  	<b><font face="Verdana" color="blue" size="4">
     										<bean:message key="CompanyWiseWeight.selmessage" /> 
     							</font>
     						</b>
            				  	
            			  </p>
		        	  </td>
	    	     </tr>
   	        </table>
        </logic:notEqual>
        
        <logic:equal name="CompanyWiseWeightageBean" property="hiddenVar" value="yes">
          	<logic:empty property="companyWeightage" name="CompanyWiseWeightageBean">
			   <table  border="0" width="600" height="300" cellpadding="0" cellspacing="0" align="center" class="sortable">	
    		 			<tr></tr><tr>
		         			<td id = "noStrutsData" bgcolor="#84AADE" align="center" valign="middle" nowrap="nowrap">
		         				<p style="margin-left: 9">
		         					<b><font face="Verdana" color="blue" size="5">
     										<bean:message key="IndexCompareOHCL.ndata" />
     									</font>
     								</b>
     							</p>
     						</td>
     					</tr>
     			   </table>
  			</logic:empty>
   			

   			<logic:notEmpty property="companyWeightage"  name="CompanyWiseWeightageBean">
   						
   					<logic:equal name="CompanyWiseWeightageBean"  property="check2" value="on" >
    		  		  		<% 	
							//	GraphMethodsPf gm = new GraphMethodsPf();
								GraphMethodsPf gm = ConnectInit.getGraphMethodsPf();
								String filename =gm.getGraphChartCompany(session,new PrintWriter(out));
								String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;

							%>
							<div id="chart" >	
    		  		  			<p align="center">
									<img id="strutsgraph" src="<%= graphURL %>" width=700 height=500 border=0 usemap="#<%= filename %>">
								</p>
							</div>
				</logic:equal>	
 				<div id="table" >	
	      			<table  border="0" width="800"  cellpadding="2" cellspacing="0"  align="center" id="tabular" >
		    		  <tr>
						<td>
							<table border="1" width="80%" align="center" cellpadding="2" cellspacing="0" id="t1" class="sortable" >
								<tr>     
									<td class="gridStyle-header" width="10%"  align="center">
										<bean:message key="CompanyWiseWeight.compname" />								
									</td>
									<td class="gridStyle-header" width="10%" align="center">
										<bean:message key="IndexCompose.mcap" />					
									</td>
									<td class="gridStyle-header" width="10%" align="center">
										<bean:message key="IndexCompose.weight" />
									</td>
				    	    	</tr>
								<logic:iterate id="cw" property="companyWeightage"  name="CompanyWiseWeightageBean">
								   <tr>
				    		   		<td class="gridStyle-odd" width="10%" nowrap="nowrap" align="left">
				    				   	<font face="Verdana" color="blue" size="2">
							  			 		<bean:write name="cw" property="company_name"/>		 			
										</font>
								  	</td>
	    	   						<td class="gridStyle-odd" width="10%" nowrap="nowrap" align="right" >
    								   	<font face="Verdana" color="blue" size="2">
					  					 	<bean:write name="cw" property="mcap"/>		
										</font>
							  		</td>
								    <td class="gridStyle-odd" width="10%" nowrap="nowrap" align="right" >
    								   	<font face="Verdana" color="blue" size="2">
								  			 	<bean:write name="cw" property="weightage"/>		 				  	
										</font>
						 			 </td>
								</tr>
							</logic:iterate>
							</table>
    	 				</td>
					</tr>
				</table>
				<table border="1" width="640" align="center" cellpadding="2" cellspacing="0" id="tabular">
					<tr>
						<td width="30%" class="gridStyle-header" align="left">
		                	  <p style="margin-left: 9"><b><bean:message key="CompanyWiseWeight.total" /></b></p>
	    	           	</td>
						<td width="30%" class="gridStyle-header" align="right">
							<bean:write name="CompanyWiseWeightageBean"  property="totalMcap"/>
						</td>
						<td width="30%" class="gridStyle-header" align="right">
							<bean:write name="CompanyWiseWeightageBean"  property="totalWt"/>
						</td>
				    </tr>
				</table>
				
		  </div>
	   </logic:notEmpty>
	</logic:equal>
</html:form>	


</td>
</tr>
</table>

</body>

<script language="javascript">

var Button;
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
	if((document.forms[0].date.value)=="")
	document.forms[0].date.value= td+ s + mnth + s + yr;
	//document.clear();
}	
function test1(){
var objTo=document.forms[0].date;
document.forms[0].hiddenVar.value="yes";	
var msg="";
if(document.forms[0].date.value==""){
	msg=msg+"select date"
}
if(document.forms[0].d1.value==""){
	msg=msg+"select index"
}
if(msg!=""){
	alert(msg);
	return false;
}
if((checkdatecurrent(objTo))==false)	
	{
	alert("ToDate should be Less Than CurrentDate.");
	objTo.focus();
	objTo.select();
	return false;
	}
else
return true;
}
function viewFunc(){
	document.forms[0].hiddenVar.value="no";
	document.forms[0].submit();
}
function go(){
document.forms[0].hiddenVar.value="no";
	document.forms[0].submit();
}
function popprinter(url)
{
	newwindow=window.open(url,'name','height=600,width=700,scrollbars=yes,left=170,top=130,menubar=yes,status=yes');
	if (window.focus) {newwindow.focus()}
}
	function PrintThisPage() 
	{ 
  		var chk=document.forms[0].check2.value;
  		var dt=document.forms[0].date.value;
 		var iname=document.forms[0].indexName.value;
  		var ttle =document.getElementById('title').innerHTML;
   		//var tble = document.getElementById('table').innerHTML;
   		var winprint=window.open("","",""); 
      	winprint.document.open(); 
       	winprint.document.write(ttle);
      	winprint.document.write('<br>');
      	winprint.document.write('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'); 
     	winprint.document.write('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'); 
     	winprint.document.write('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'); 
      	winprint.document.write('<bean:message key="corporate.Date"/>');
      	winprint.document.write(':&nbsp;&nbsp;&nbsp;');       	
       	winprint.document.write(dt); 
     	winprint.document.write('&nbsp;&nbsp;&nbsp;'); 
      	winprint.document.write('&nbsp;&nbsp;&nbsp;'); 
      	winprint.document.write('<bean:message key="defineIndex7"/>');
      	winprint.document.write(':&nbsp;&nbsp;&nbsp;'); 
       	winprint.document.write(iname);
	   	winprint.document.write('<br><br>');
	   	if((Button=="AjaxButton")){				
   			var tble = document.getElementById('Ajaxcontentstart').innerHTML;    			
   		}
   		else{    			
   			var tble = document.getElementById('table').innerHTML;
   			//var moreDet = document.getElementById('hiddenTable').innerHTML;
   			var chrt=document.getElementById('chart').innerHTML;            
   			//winprint.document.write(moreDet);
   			winprint.document.write(chrt); 
   		} 
	   	/*if(chk=="on"){
              	var chrt=document.getElementById('chart').innerHTML;
               	winprint.document.write(chrt); 
        }*/
       	winprint.document.write(tble);   		
       	winprint.document.close(); 
       	winprint.focus(); 
	}
	
	function downloadExcel(){
	document.forms[0].download.value="on";
	}
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

/*============================================== for dwr =========================================================== */	
function getCompanyWiseWeightageDetails() {
  DWRUtil.useLoadingMessage();    
  var index = DWRUtil.getValue("d1"); 
  var date = DWRUtil.getValue("date"); 
  var check2 = DWRUtil.getValue("check2");  
 
  MoveTable.getCompanyWeightage(fillTable, index, date );  

}
	var company_name = function(StockDetails) { return '<font face="Verdana" color="blue" size="2">'+StockDetails.company_name+'</font>' };			
	var mcap = function(StockDetails) { return '<font face="Verdana" color="blue" size="2">'+StockDetails.mcap+'</font>'};				
	var weightage = function(StockDetails) { return '<font face="Verdana" color="blue" size="2">'+StockDetails.weightage+'</font>'};								
	//alert(stockId);
function fillTable(indexMovingTable) { 
   var index = DWRUtil.getValue("d1"); 
   var date = DWRUtil.getValue("date"); 
   var check2 = DWRUtil.getValue("check2");  
   var a = new Array();
	   a = indexMovingTable; 
	   var length = a.length;  
 if(length!=1){
   	  //alert("");
   	  Button = "AjaxButton";
   	  changeDisplay("selectindexmessage","none");
 	  changeDisplay("nodata","none");
 	  changeDisplay("noStrutsData","none");
 	  changeDisplay("tabular","none");
 	  changeDisplay("strutslinks","none");
 	  changeDisplay("ajaxlinks","inline");
 	  changeDisplay("sortTable","inline"); 	  
 	  changeDisplay("strutsgraph","none");
  	  changeDisplay("graph","none");
 	   if(check2){
  		
  		changeDisplay("strutsgraph","none");
  		changeDisplay("graph","inline");
  		GraphMethods.getGraphChartCompany1(showGraph,index,date,"2");
	    }	  	
	  DWRUtil.removeAllRows("indexMovingTable");
	  DWRUtil.addRows("indexMovingTable",indexMovingTable, [ company_name, mcap, weightage ]);
 }
 else{ 
 	 changeDisplay("sortTable","none");
 	 changeDisplay("noStrutsData","none");
 	 changeDisplay("tabular","none");
 	 changeDisplay("selectindexmessage","none");
 	 changeDisplay("strutslinks","none");
 	 changeDisplay("ajaxlinks","none");
	 changeDisplay("nodata","inline");	 
 }
  
}
function showGraph(graphURL){
//alert(graphURL);
	var index = DWRUtil.getValue("d1"); 
    var date = DWRUtil.getValue("date");   
	changeDisplay("graph","inline");
	var imgid = $("graph");
	imgid.setAttribute("src",graphURL);
	var check2 = DWRUtil.getValue("check2");
	//MoveTable.getCompanyWeightage(fillTable,index,date);
}


/*============================================== for dwr =========================================================== */

</script>
</html:html>
