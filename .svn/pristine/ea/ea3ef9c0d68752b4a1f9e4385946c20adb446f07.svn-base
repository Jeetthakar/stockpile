<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@ page import = "harrier.income.com.report.*" %>
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
<link href="../StyleSheetM.css" rel="stylesheet" type="text/css">
	 <script language="javascript" src="../Script/codethatcalendarstd.js"></script>
	 <script language="javascript" src="box_ex.js"></script>
	 <script type='text/javascript' src='/Stockpile/dwr/interface/GraphMethods.js'></script>
	 <script type='text/javascript' src='/Stockpile/dwr/engine.js'></script>
	 <script type='text/javascript' src='/Stockpile/dwr/util.js'></script>
	 
	 <script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script>
			<style type="text/css">	
			/* Sortable tables */
			table.sortable a.sortheader {
    			background-color:#eee;
   	 			font-size: 13px; 
    			font-family: Arial;
    			color: black;
    			text-decoration: none;
    			display: block;
			}
			table.sortable span.sortarrow {
    			color: black;
    			text-decoration: none;
			}
		</style>
	<%-- Following code is added by Ganesh --%>
	<%-- ====================START:=====================================================--%>
	<%-- Code is added so that each time this page loaded from menu, then page should be reset --%>
	<%-- Else if page is loaded from compare button(which is on the same page) then page is not reset--%>	
	<%
//	Logger Logging = Logger.getLogger("IndexCompare1S.jsp");
		String parameter=request.getParameter("FlagForReset");
		String ajax1="";
			try{
				ajax1=request.getParameter("ajax1");
								
			}catch (Exception e) {
				// TODO: handle exception
			//	Logging.error(" Error :"+e.getMessage());
			}
		log.info("parameter================================----->"+parameter);
		if(parameter!=null){
			if(parameter.equals("yes")){
			//log.info("----befor reset-----");
				IndexCompareForm fb=(IndexCompareForm)session.getAttribute("IndexCompareBean");
				if(fb!=null){
							fb.reset();
				}
				session.setAttribute("IndexCompareBean",fb);
				//log.info("----after reset-----");
			
			}
		
		}
	%>
	<%--==================================END:code added=================================--%>
		
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
	</head>
	
	
	
<body onload="initialize()">
<table width="100%" height="100%" >

<tr>
<td align="left" valign="top" bgcolor="#CCddee">

<DIV id="leftCol" style="DISPLAY: none; width:160; height:100%;">
<%@ include file="../tree3.jsp" %>
</div>
<IMG id="HideHandle" src="../openImage.gif" style="CURSOR: hand; position:absolute;" onclick='hideLeftCol("leftCol");' src="fold.gif" width="10" height="25">
</td>
<td align="left" valign="top">

<html:form action="/indexCompare" >

<%        if(request.isRequestedSessionIdValid()) {%>
				<jsp:setProperty name="IndexCompareBean" property="userid1" value='<%=session.getAttribute("userid")%>'/>
				<jsp:setProperty name="IndexCompareBean" property="roleId_com" value='<%=session.getAttribute("role_id").toString()%>'/>
		<%}%>


<div id="title">
	<table width="1000" cellspacing="0" cellpadding="0" >
        		<tr>
		          	<td width="335" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          			<font size="3" face="Arial">
		          				<b><bean:message key="IndexComparision.title" /> </b>
		          			</font>
		         	</td> 
	          </tr>
</table>
</div>
				 
			     <logic:equal value="yes" property="buttonValue" name="IndexCompareBean">		
			     	     <table align="right">
			     	     	<td>
			        	           <a href="javascript:PrintThisPage();" >
				        	           <font size="1" face="Verdana" color="blue">
				        	           		<bean:message key="LatestDivisor.printerf" />
				        	           	</font>
				       	           	</a>
				       	      </td>
				         </table>
				  </logic:equal>
         <table width="100%" >
         	<tr>
         		<td width="220" align="right" nowrap="nowrap">
         		           <font size="2" face="Arial">
					         	 <bean:message key="indexUpdate.selectIndex" />
					        </font>
       			 </td>
       			 <td width="489" nowrap="nowrap">
					<html:select property="d2" size="3" multiple="true" >
						<html:optionsCollection property="vector_indexlist" name="IndexCompareBean"/>
					</html:select>
      			</td>
			</tr>	
         </table>        
    
	<!-- 	<table>
         	<tr>
        		<td width="190" align="right" nowrap="nowrap">
					<html:checkbox property = "check"  value = "checked" onclick = "test1();"/> &nbsp;
				</td>
		  	    <td width="144" nowrap="nowrap">	
				   <font size="2" face="Arial">  
						  <bean:message key="IndexComparision.showsi" />
				  </font>
 			  	</td>
 			  </tr>
 			 </table> -->
 			 <table align="center">
 			  <tr>
				 <td width="80"  nowrap="nowrap" align="right"><font size="2" face="Arial" > 
				
        	    	  <bean:message key="corporate.Fdate" />
    	        </td> 
	        	<td  width="80" align="right"  nowrap="nowrap">
					<html:text property="from" size="10" readonly="true"/>
				</td>
				<td width="80" nowrap="nowrap">    
        	         <html:button property="frombut" value="..." onclick="c2.popup('from');"/>
            	</td>
			
			    <td width="50" nowrap="nowrap"><font size="2" face="Arial">
                        <bean:message key="corporate.Tdate" />
    	        </td>

        	    <td width="80" nowrap="nowrap">	
					<html:text property="to" size="10"  readonly="true" />
				</td>

				<td  width="80" nowrap="nowrap">    
        	         <html:button property="tobut" value="..." onclick="c2.popup('to');" />
            	</td>
				<%	if(ajax1.equals("yes")){
				%>
	            <td width="80" nowrap="nowrap">
					<html:submit property="b1" onclick="return go();"> <bean:message key="Reports.Compare"/> </html:submit>
			    </td>
			    <td>
			    	<html:hidden property="buttonValue"  name="IndexCompareBean"/>		   			  
			    </td>
			    <%}else{ %>
			    <td>
			    	<INPUT type="Button" name="View" value="AjaxView" onclick="getGraphChart(this.form.d2.options)"/>
			    </td>
			    <%} %>
			    </tr>
			    
			    </table>
			    <p>
			    </p>
			    
			    <table align="center">
			    	<img id="graph" align="top" width="500" height="270" border="0" style="display: none;">
			    </table>
			     <%	
			     		    
			     	   	   	String filename=null;
   		    		      //  GraphMethodsPf gm=new GraphMethodsPf();
   	    				    GraphMethodsPf gm=ConnectInit.getGraphMethodsPf();
   	    				    filename=gm.getGraphChartIndexCompare01(session,new PrintWriter(out));
   	    				    String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
   	    				    log.info("graph url is :"+graphURL);
   	    		  %>
   	    	<logic:notEqual value="yes" property="buttonValue" name="IndexCompareBean">
 			   <table  border="0" width="600" height="300" cellpadding="0" cellspacing="0" align="center" class="sortable">	
    		 		<tr></tr><tr>
		         			<td  id = "selectindex" bgcolor="#84AADE" align="center" valign="middle" nowrap="nowrap">
		         				<p style="margin-left: 9">
		         					<b><font face="Arial" color="blue" size="5">
							              <bean:message key="IndexComparision.messaged" />     									
     								</font></b>
     							</p>
     						</td>
     				   </tr>
     			</table>
   	    	</logic:notEqual>
			     	
		     <logic:equal value="yes" property="buttonValue" name="IndexCompareBean">		
			    <br><br> <!-- printer friendly downloadExcel Email -->

				    <div id="chart">
				  	  <table align="center">
						   <tr> <td id = "strutsGraph" width="162" nowrap="nowrap">
					    		<img src="<%= graphURL %>" height="270"  width="500" >
						    </td></tr>
					   </table> 
				   </div>
		    </logic:equal>
		    
</html:form>
</td>
</tr>
</table>
</body>	
<script language="javascript">

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
	
	
	if((document.forms[0].from.value)==""){
	document.forms[0].from.value= td+ s + mnth + s + yr;
	}
	
	if((document.forms[0].to.value)==""){
	document.forms[0].to.value= td+ s + mnth + s + yr;
	}
	
	
}	
function test1(){
		document.forms[0].buttonValue.value="no";
		document.forms[0].submit();
	}
	
function go() {
	
	
	var objTo=document.forms[0].to;
	var msg="";
	if(document.forms[0].d2.value==""){
		msg=msg+"Select two index names \n";
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
	}
	if((checkdatecurrent(objTo))==false)	
	{
	alert("ToDate should be Less Than CurrentDate.");
	objTo.focus();
	objTo.select();
	return false;
	}
	else{
		document.forms[0].buttonValue.value="yes";
		return true;
    }
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
	function popprinter(url)
	{
		newwindow=window.open(url,'name','height=500,width=900,scrollbars=yes,left=80,top=130,menubar=yes,status=yes');
		if (window.focus) {newwindow.focus()}
	}
		function PrintThisPage() 
	{ 

  		var fromDate=document.forms[0].from.value;
 		var toDate=document.forms[0].to.value;
  		var ttle =document.getElementById('title').innerHTML;
   		var tble = document.getElementById('chart').innerHTML;
   		var winprint=window.open("","",""); 
      	winprint.document.open(); 
       	winprint.document.write(ttle);
      	winprint.document.write('<br>');
      	winprint.document.write('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'); 
      	winprint.document.write('<bean:message key="corporate.Fdate" />');
      	winprint.document.write('&nbsp;&nbsp;');
       	winprint.document.write(fromDate); 
     	winprint.document.write('&nbsp;&nbsp;&nbsp;'); 
      	winprint.document.write('&nbsp;&nbsp;&nbsp;'); 
      	winprint.document.write('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'); 
      	winprint.document.write('<bean:message key="corporate.Tdate" />');
      	winprint.document.write('&nbsp;&nbsp;');
       	winprint.document.write(toDate); 
	   	winprint.document.write('<br><br>');
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
	
function getGraphChart(opt) {
  DWRUtil.useLoadingMessage();	  
  var from = DWRUtil.getValue("from");
  var to = DWRUtil.getValue("to");
  var selectIndex = getSelected(opt);  
  GraphMethods.getGraphChartIndexCompare(showGraph,selectIndex, from, to); 
}

function showGraph(graphURL){
	changeDisplay("graph","inline");
	var imgid = $("graph"); 		
 	imgid.setAttribute("src",graphURL);
 	changeDisplay("selectindex","none");
 	changeDisplay("strutsGraph","none");	
}
	
</script>
</html:html>



