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
<link href="StyleSheet.css" rel="stylesheet" type="text/css">
	 <script language="javascript" src="../Script/codethatcalendarstd.js"></script>
	 <script language="javascript" src="box_ex.js"></script>
	 <script type='text/javascript' src='/Stockpile/dwr/interface/GraphMethods.js'></script>
	 <script type='text/javascript' src='/Stockpile/dwr/engine.js'></script>
	 <script type='text/javascript' src='/Stockpile/dwr/util.js'></script>
	 
	 
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
		String parameter=request.getParameter("FlagForReset");
		
		if(parameter!=null){
			if(parameter.equals("yes")){
			//log.info("----befor reset-----");
				SectorwiseForm fb=(SectorwiseForm)session.getAttribute("SectorwiseBean");
				if(fb!=null){
							fb.reset();
				}
				session.setAttribute("SectorwiseBean",fb);
				
			}
		
		}
	%>
	<%--==================================END:code added=================================--%>
		
	</head>
	
	
	
<body onload="initialize()">
<html:form action="/sectorwiseAction" >
	<div id="title">
	<table width="1000" cellspacing="0" cellpadding="0" >
        	<tr>
		       	<td width="335" class="subHeader" nowrap="nowrap">
		     		&nbsp;
		       	</td>
		       	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		    		<font size="3" face="Arial"><b>Sectorwise Comparision</b>
		          	</font>
		         </td> 
	        </tr>
	</table>
	</div>

			    
         <table width="656" >
         	<tr>
         		<td width="157" align="right" nowrap="nowrap">
         		           <font size="2" face="Arial">
					         	 <bean:message key="indexUpdate.selectIndex" />
					        </font>
       			 </td>
       			 <td width="489" nowrap="nowrap">
					<html:select property="d2" size="3" multiple="true" >
						<html:optionsCollection property="vectorIndexlist" name="SectorwiseBean"/>
					</html:select>
      			</td>
			</tr>	
         </table>        
    
		
 			 <table align="center">
 			  <tr>
				 
				
	            <td width="80" nowrap="nowrap">
					<html:submit property="b1" onclick="return go();"> <bean:message key="Reports.Compare"/> </html:submit>
			    </td>
			    <td>
			    	<html:hidden property="buttonValue"  name="SectorwiseBean"/>		   			  
			    </td>
			   
			    </tr>
			    
			    </table>
			      <%	
			     		    
			     	   	   	String filename=null;
   		    		       // GraphMethodsPf gm=new GraphMethodsPf();
   	    				    GraphMethodsPf gm=ConnectInit.getGraphMethodsPf();
   	    				    filename=gm.getGraphChartSectorwise(session,new PrintWriter(out));
   	    				    String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
   	    				    log.info("graph url is :"+graphURL);
   	    		  %>
			    <p>
			    </p>
			    
			    <table align="center">
			    	<img id="graph" align="top" width="500" height="270" border="0" style="display: none;">
			    </table>
			     
   	    	<logic:notEqual value="yes" property="buttonValue" name="SectorwiseBean">
 			   <table  border="0" width="600" height="300" cellpadding="0" cellspacing="0" align="center" class="sortable">	
    		 		<tr></tr><tr>
		         			<td  id = "selectindex" bgcolor="#cacaca" align="center" valign="middle" nowrap="nowrap">
		         				<p style="margin-left: 9">
		         					<b><font face="Arial" color="blue" size="5">
							             Select Indices To Show Data    									
     								</font></b>
     							</p>
     						</td>
     				   </tr>
     			</table>
   	    	</logic:notEqual>
			 
   	    	<logic:equal value="yes" property="buttonValue" name="SectorwiseBean">
				    <div id="chart">
				  	  <table align="center">
						   <tr> <td id = "strutsGraph" width="162" nowrap="nowrap">
					    		<img src="<%= graphURL %>" height="270"  width="500" >
						    </td></tr>
					   </table> 
				   </div>
		    </logic:equal>
		    
</html:form>
</body>	
<script language="javascript">

function initialize() {
	
	
	
}	
function test1(){
		document.forms[0].buttonValue.value="no";
		document.forms[0].submit();
	}
	
function go() {
	
	
	
	var msg="";
	if(document.forms[0].d2.value==""){
		msg=msg+"Select two index names \n";
	}
	
	if(msg!=""){
		alert(msg);	
		document.forms[0].buttonValue.value="no";
		return false;
	}else{
		document.forms[0].buttonValue.value="yes";
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
	
function getGraphChart(opt) {
  DWRUtil.useLoadingMessage();	  
 
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




