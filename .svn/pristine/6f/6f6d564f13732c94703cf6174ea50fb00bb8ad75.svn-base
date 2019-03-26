<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="app.*"%>
<%@ page language="java" import="harrier.income.com.*"%><%@page import="org.apache.log4j.Logger"%>
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
		 	<html:base/>
			<title></title>
			<link href="../StyleSheetM.css" rel="stylesheet" type="text/css">
			<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
			<script language="javascript" src="box_ex.js"></script>
			<script type="text/javascript" src="../Script/SortedTable.js"></script>
			<script type='text/javascript' src='/Stockpile/dwr/interface/MoveTable.js'></script>
			<script type='text/javascript' src='/Stockpile/dwr/engine.js'></script>
			<script type='text/javascript' src='/Stockpile/dwr/util.js'></script>
			
			<script language="javascript">
				var c2 = new CodeThatCalendar(caldef2);
			</script>
			<!-- <script type="text/javascript" src="./sorttable.js"></script>
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
				</style> -->
<script type="text/javascript" src="../Script/Event.js"></script>
		<script type="text/javascript" src="../Script/SortedTable.js"></script>

		<style type="text/css">

			/* table styles*/
			.sorted td, th {border:0;padding:2px 6px;margin:0;border-right:1px solid #336;
				border-bottom:1px solid #336;
				font-size: 10px;padding-left: 2px;}
			td[axis='number'], td[axis='date'] {text-align:right;}
			th {background-color:#cacaca;padding:2px 20px;color: blue;font-size: 12px;
				vertical-align: baseline;line-height: 18px;}
			tfoot td {border-top:0px solid #003;}
			thead th {border-bottom:1px solid #003;}
			.sortedminus {background-color:#ecc;}
			.sortedplus {background-color:#cec;}


		</style>	
		
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
<body onload="initSort()" >
<html:form action="/IndexReturnsVolatilityAction">
	
	
<%        if(request.isRequestedSessionIdValid()) {%>
				<jsp:setProperty name="IndexReturnsVolatilityBean" property="userid1" value='<%=session.getAttribute("userid")%>'/>	
				<jsp:setProperty name="IndexReturnsVolatilityBean" property="roleId_ret" value='<%=session.getAttribute("role_id").toString()%>'/>
		<%}%>
		
	<table width="100%" height="100%" >
	<tr>
		<td align="left" valign="top" bgcolor="#CCddee">
			<DIV id="leftCol" style="DISPLAY: none; width:160; height:100%;">
				<%@ include file="../tree3.jsp" %>
			</div>
			<IMG id="HideHandle" src="../openImage.gif" style="CURSOR: hand; position:absolute;" onclick='hideLeftCol("leftCol");' src="fold.gif" width="10" height="25">
		</td>
		<td align="left" valign="top">
			
			<div id="PageTitle">
				<table width="1000" cellspacing="0" cellpadding="0" > <!-- Table for Displaying Title -->
        			<tr>
		          		<td width="335" class="subHeader" nowrap="nowrap">
		          			&nbsp;
		          		</td>
		          		<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          				<font size="3" face="Verdana">
		          					<b><bean:message key="IndexReturnVolatility.title" /></b>
		          				</font>
		         		</td> 
	          		</tr>
				</table>
			</div>
	
	<!-- if compute ="yes" display links -->
	<%
			String ajax1="no";
			try{
				ajax1=request.getParameter("ajax1");
								
			}catch (Exception e) {
				// TODO: handle exception
			//	Logging.error(" Error :"+e.getMessage());
			}
			String email_url = "./EmailReport.jsp?switch_type=14&cas=14&rname=IndexReturn.xls";
		    String path_pdf = "./FileDownload_Pdf.jsp?&type=14&filename=IndexReturn.pdf";
            String excel_path = "./FileDownload.jsp?&type=14&filename=IndexReturn.xls";
            String xml_path = "./FileDownloadXmlNew.jsp?&type=14&filename=IndexReturn.xml";
	%>
<logic:equal  value="yes" parameter="compute" >	
	<logic:notEmpty property="final_Vector" name="IndexReturnsVolatilityBean">
	<table id=" strutslinks" align="right" > 
	 	<tr>
	 		<td valign="baseline">
	 			<a href="javascript:PrintThisPage();"> 
	 				<font size="1" face="Verdana" color="blue"><bean:message key="IndexPerformance.printerf"/></font>
	 			</a>
	 		</td>
	 		<!-- Excel Links  -->		
	 		<%
   				session.setAttribute("indexList",request.getParameterValues("indexList"));
   				session.setAttribute("from",request.getParameter("from"));
   				session.setAttribute("to",request.getParameter("to"));
		        
			%>								
	 			<td valign="baseline"><a href="<%= excel_path %>" ><font size="1" face="Verdana" color="blue" ><bean:message key="IndexPerformance.downloade" /></font></a></td>
	 			<td valign="baseline"><a href="<%= xml_path %>" ><font size="1" face="Verdana" color="blue" >Export to Xml</font></a></td>
	 			<td valign="baseline" > <a href="<%= email_url %>" ><font size="1" face="Verdana" color="blue" ><bean:message key="IndexPerformance.emailr" /></font></a></td>
	 			<td valign="baseline" > <a href="<%= path_pdf %>" ><font size="1" face="Verdana" color="blue" ><bean:message key="LatestDivisor.pdfr" /></font></a></td>	
		</tr>	
	</table>
	
	<br>
	</logic:notEmpty>
</logic:equal>
	
	<table id="ajaxlinks" align="right" style="display:none">
	<tr>
	  <td valign="baseline">
	 			<a href="javascript:PrintThisPage();"> 
	 				<font size="1" face="Verdana" color="blue"><bean:message key="IndexPerformance.printerf"/></font>
	 			</a>
	 </td>
	 <td valign="baseline"><a href="<%= excel_path %>" ><font size="1" face="Verdana" color="blue" ><bean:message key="IndexPerformance.downloade" /></font></a></td>
	 			<td valign="baseline"><a href="<%= xml_path %>" ><font size="1" face="Verdana" color="blue" >Export to Xml</font></a></td>
	 			<td valign="baseline" > <a href="<%= email_url %>" ><font size="1" face="Verdana" color="blue" ><bean:message key="IndexPerformance.emailr" /></font></a></td>
	 			<td valign="baseline" > <a href="<%= path_pdf %>" ><font size="1" face="Verdana" color="blue" ><bean:message key="LatestDivisor.pdfr" /></font></a></td>	
	</tr>
	</table>
	<br>
	<table width="656"> <!-- Selectbox for displaying the Index List -->
         	<tr>
         	<td width="260" nowrap="nowrap" align="right">	  
	    		<font size="2" face="Verdana">
           		<bean:message key="indexUpdate.selectIndex" />:
	        </td>    
			<td width="400" nowrap="nowrap" align="left">
       			<html:select size="3" property="indexList" multiple="multiple">
        			<html:optionsCollection property="selectIndexCollection1" />
        		</html:select> 
        	</td>   
        	</tr>       	
     </table>
     <!-- Table for To and From date --> 	
   <!--  <table width="500" align="center"> 
         		<td  align="right" nowrap="nowrap">
         			<html:checkbox property="check" onclick="return test1()" />
         		</td>
         		
         		<td align="left" nowrap="nowrap">	
	   				<font size="2" face="Verdana">  
	  				<bean:message key="IndexReturnVolatility.Show" /></font>
	  			</td>
	  </table> -->
	  <table width="400" align="center">
	  			<tr>
	  			<td width="80" nowrap="nowrap" align="center"><font size="2" face="Verdana" >
            		<bean:message key="corporate.Fdate" />:
            	</td> 
            
            	<td nowrap="nowrap" width="80"><!-- width="78" -->
             		<html:text property="from" readonly="true" size="10"/>
            	</td>
            
            	<td  nowrap="nowrap" width="45">  <!-- width="37" -->
					<html:button property="shwFrom" value="..." onclick="c2.popup('from');"/>
          		</td>
          	
            	<td nowrap="nowrap" width="83"><!-- width="58" -->
           			<font size="2" face="Verdana">
                		<bean:message key="corporate.Tdate" />
                	</font>
            	</td>
            
            	<td nowrap="nowrap" width="78"><!-- width="78" -->
             		<html:text property="to" readonly="true" size="10"/>
            	</td>
            
            	<td  nowrap="nowrap" width="45" >
					<html:button property="shwTo" value="..." onclick="c2.popup('to');"/>
          		</td>
          		<%	if(ajax1.equals("yes")){
	  			%>
             	<td nowrap="nowrap" width="212"> <!-- width="162" -->
               		<html:submit onclick="go();" onkeypress="go();" ><bean:message key="Reports.View" /></html:submit>&nbsp;&nbsp;
				</td>
				<% }else{
				%>
				<td>
					<INPUT type="Button" name="View" value="AjaxView" onclick = "getIndexReturnsVolatality(this.form.indexList.options)"/>
				</td>
				<% } %>	
				</tr>
		</table>
<br>     
	
	 <!-- ========================== for ajax ======================================= -->
 	 <div id="AjaxContentstart">
	  	 <table class ="sorted"  ID="sortTable" style="display:none"
			border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#84AADE">    
          <!--  <table border="1"> -->      
			
				<thead >
				<tr>				
					
					<th class="gridStyle-header">Index Name</th>
					<th class="gridStyle-header">Periodic Retrurns</th>
					<th class="gridStyle-header">Volatality Of Returns</th>				
				</tr>
				</thead>						
				<tbody id="indexMovingTable" align="center" bgcolor="#84AADE"> </tbody>								
		</table>
		</div>
		
		<table  id = "nodata" border="0" align="center" width="631" height="222" cellspacing="0" cellpadding="0" style="display: none;">	  		
          			<tr>          				
          				<td  bgcolor="#84AADE" align="center" valign="middle" >
          					<p style="margin-left: 9"><font face="Verdana" color="blue" size="5">
          						<bean:message key="IndexReturnVolatility.Nodata" /></font></p>
          				</td>
          			</tr>
         </table>
		
		
 <!-- ========================== for ajax ======================================= -->
	  
	<logic:notEqual value="yes" parameter="compute" >	  	
	 		                      
		<table id = "selectindex" border="0" align="center" width="631" height="222" cellspacing="0" cellpadding="0">
  		<tr>
	 		
	 		<td  bgcolor="#84AADE" align="center" valign="middle">	
              <p style="margin-left: 9"><font face="Verdana" color="blue" size="5">
              <bean:message key="StockPerformance.selData" /></font></p>
      
            </td>
		</tr>
     	</table>
     			
      	</logic:notEqual >
      
    
      <logic:equal  value="yes" parameter="compute" >
     
      
	  		
	  		<logic:empty property="final_Vector" name="IndexReturnsVolatilityBean">
	  			<table id = "noStrutsData" border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">	  		
          			<tr>
          				<td width="120" nowrap="nowrap">&nbsp;</td>	  		
          				<td  bgcolor="#84AADE" align="center" valign="middle">
          					<p style="margin-left: 9"><font face="Verdana" color="blue" size="5">
          						<bean:message key="IndexReturnVolatility.Nodata" /></p>
          				</td>
          			</tr>
          		</table>
           </logic:empty>
   		     		            
            <logic:notEmpty property="final_Vector" name="IndexReturnsVolatilityBean">        
            <div id="contentstart"> 
            
            <table align="center">	  		
            	<tr>
	      			<td>
	      				 <table class="sorted" ID="sortTabletable" 
			border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
				<thead>
					<tr>
      					<!-- <table border="1" width="60%" align="center" cellpadding="3" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">	      		 			     			       				
							<tr> -->
								<!-- Table Heading -->
				            	<th  nowrap="nowrap" align="center" id="indexname" class="gridStyle-header">
				            	<span><b><bean:message key="indexcompose.indexname" /></b></span></th>
				            	<th  nowrap="nowrap" align="center" id="Periodic" class="gridStyle-header">
				            	<span><b><bean:message key="StockPerformance.Periodic" /></b></span></th>
				           		<th  nowrap="nowrap" align="center" id="Volatility" class="gridStyle-header">
				           		<span><b><bean:message key="IndexReturnVolatility.Volatility" /></b></span></th>
	         			   </tr>
	         		</thead>
					<tbody>
					 <%
 				int i=1;
 				String bcolor="";
 			%>
	           		<!-- Iterate over the table data -->		
	      			<logic:iterate id="details" name="IndexReturnsVolatilityBean" property="final_Vector">
		     			 <%if(i%2==0)
				{
					bcolor="#84AADE";
				}
				else
				{
					bcolor="#DEE3EF";
				}
				
				%>
				<tr bgcolor=<%=bcolor %>>				
			      			<td nowrap="nowrap" align="center"  axis="sstring" headers="indexname"
           			title='<bean:write name="details" property="strr2"/>'>
			          			<font face="Verdana" color="black" size="2">  
			          				<bean:write name="details" property="strr2"/>
			       				</font>
			    			</td>
			          							
			      			<td  nowrap="nowrap" align="center"  axis="number" headers="Periodic"   
	  		title='<bean:write name="details" property="strr3"/>'>
			          			<font face="Verdana" color="black" size="2">  
			          				<bean:write name="details" property="strr3"/>
			          			</font>
			    			</td>
			          							
			    			<td  nowrap="nowrap" align="center"  axis="number" headers="Volatility"   
	  		title='<bean:write name="details" property="strr4"/>'>
			      				<font face="Verdana" color="black" size="2">  
			          				<bean:write name="details" property="strr4"/>  
			          			</font>
			    			</td>
			          			
						</tr>
						<% i=i+1; %>
	   				</logic:iterate>
	   				</tbody>
	   			</table>
	   			</td>
          		</tr> 
          	</table>	
          	 </div> 
          	 </logic:notEmpty>
    	
      </logic:equal>
           	
      <div id="hiddenTable">		
	  		<table width="100%"  align="left" >
	      		
					
					<tr>
						<td width="125" align="right">
							<font size="2" face="Verdana"><b>
    							<bean:message key="corporate.Fdate" />&nbsp;:</b>
        					</font>
   						</td>
   						
   						<td>
   							<bean:write name="IndexReturnsVolatilityBean" property="from"/>
   						</td> 
   						
						</tr>
						<tr>
						<td width="125" align="right">
							<font size="2" face="Verdana"><b>
    							<bean:message key="corporate.Tdate" />&nbsp;:</b>
         					</font>
   						</td>
   			<td>
   			<bean:write name="IndexReturnsVolatilityBean" property="to"/> </td> 
			</tr>						     			       				
	          	 			
          	</table>
        
        </div>
		
        <html:hidden property="compute" value="no"></html:hidden>
             
  </html:form>  
  
  
</td>
</tr>
</table>
         
</body>	
 <script language="javascript">
 	var Button;
 	var man1=document.getElementById("hiddenTable");
	man1.style.display= "none";
	
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
	
	
	
 	function test1(){
		document.forms[0].submit();
	}
	
	function go() {
	 	
	 	//var list=document.forms[0].indexList.value;
	 	//if(list==null)
    		//if(list.equals(""))
    			//alert('Please select an Index');    
		document.forms[0].compute.value="yes";
		//document.forms[0].clear.value ="yes";
		document.forms[0].defaultVal.value="no";
		document.forms[0].submit();
		
	}
	
	function popprinter(url){
	newwindow=window.open(url,'name','height=500,width=900,scrollbars=yes,left=80,top=130,menubar=yes,status=yes');
	if (window.focus) {newwindow.focus()}
	}

	function PrintThisPage() 
	{ 
				
		var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
    	sOption+="scrollbars=yes,width=850,height=550,left=100,top=25"; 

   		//var sWinHTML = document.getElementById('contentstart').innerHTML; 
   		var printHead =document.getElementById('PageTitle').innerHTML;
	   	var moreDet = document.getElementById('hiddenTable').innerHTML;
   		
    	var winprint=window.open("","",sOption); 
    	winprint.document.open(); 
    	winprint.document.write('<html><body>'); 
    	winprint.document.write(printHead);
    	
    	winprint.document.write('<br>');
    	winprint.document.write('<br>');
    	winprint.document.write('<br>');
    	
    	if(Button=="AjaxButton"){
    		var from = DWRUtil.getValue("from"); 
	  		var to = DWRUtil.getValue("to");
	  		winprint.document.write('<br><b>From Date:');
	  		winprint.document.write(from); 
 			winprint.document.write('<br>To Date:</b>');
 			winprint.document.write(to); 
 			winprint.document.write('<br>');
   			var sWinHTML = document.getElementById('Ajaxcontentstart').innerHTML;   
   		} else{ 
   			winprint.document.write('<br>');
    		winprint.document.write(moreDet); 
 			winprint.document.write('<br>');
   			var sWinHTML = document.getElementById('contentstart').innerHTML; 
   		}
    	winprint.document.write(sWinHTML);         
    
    	winprint.document.write('</body></html>'); 
   		winprint.document.close(); 
    	winprint.focus(); 
	}
	
	
 /*===================================== For DWR ========================================================================*/
 
 function getSelected(opt) {
            var selected = new Array();
            var index = 0;
            for (var intLoop = 0; intLoop < opt.length; intLoop++) {
               if ((opt[intLoop].selected) ||
                   (opt[intLoop].checked)) {
                  index = selected.length;
                  selected[index] = new Object;
                  selected[intLoop] = opt[intLoop].value;
                  //selected[index].index = intLoop;
               }
            }
            return selected;
  }
 
function getIndexReturnsVolatality(opt) {  
 	  DWRUtil.useLoadingMessage();
	  var selectIndex;  
	 // var sel  = getSelected(opt);  
	  selectIndex = getSelected(opt);  
	 /* var strSel = "";
	            for (var item in sel)        
	               strSel += sel[item].value + "\n";                              
	            alert("Selected Items:\n" + strSel);           
	           
	            for (var item in sel)        
	               strSel += sel[item].value + "\n";
	 */                                             
	  //alert(selectIndex);                      	
	  var from = DWRUtil.getValue("from"); 
	  var to = DWRUtil.getValue("to");
	  MoveTable.getIndexReturnsVolatility(fillTable, selectIndex, from, to);
  
}
	var IndexName = function(returnVol) { return '<font face="Verdana" color="blue" size="2">'+returnVol.strr2+'</font>' }; // if we return to using dates, add .toLocaleDateString()
	var PeriodicReturns = function(returnVol) { return '<font face="Verdana" color="blue" size="2">'+returnVol.strr3+'</font>'};
	var VolatalityOfReturns = function(returnVol) { return '<font face="Verdana" color="blue" size="2">'+returnVol.strr4+'</font>'};
	//alert(stockId);
function fillTable(indexMovingTable) {
 var a = new Array();
	 a = indexMovingTable;   
 if(a.length){
 	  Button = "AjaxButton"; 	
	  changeDisplay("sortTable","inline");
	  changeDisplay("ajaxlinks","inline");
	  changeDisplay("strutslinks","none");
	  changeDisplay("nodata","none");
	  changeDisplay("noStrutsData","none");	
	  changeDisplay("sortTabletable","none");
	  changeDisplay("selectindex","none");
	  DWRUtil.removeAllRows("indexMovingTable");
	  DWRUtil.addRows("indexMovingTable",indexMovingTable, [ IndexName, PeriodicReturns, VolatalityOfReturns ]);
	  //alert("I got it right");
  }
 else{
 	 changeDisplay("sortTable","none");
	 changeDisplay("sortTabletable","none");
	 changeDisplay("noStrutsData","none");	
	 changeDisplay("selectindex","none");
	 changeDisplay("ajaxlinks","none");
	 changeDisplay("strutslinks","none");
     changeDisplay("nodata","inline");
 } 
}

</script>

</html:html>
