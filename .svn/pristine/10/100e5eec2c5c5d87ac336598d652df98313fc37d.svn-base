<%@ page import = "java.util.Locale,java.util.*" %>
<%@ page language="java" import="app.*"%>
<%@ page language="java" import="harrier.income.com.report.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");
	AcessControl asc = ConnectInit.getAcessControl();
	//	AcessControl asc=new AcessControl();
		boolean flag=false;
		
		LogonForm form = null;
		
		if(request.isRequestedSessionIdValid())	{	
			form = (LogonForm)session.getAttribute("user");
			Vector uname=asc.getUseActivitiesId(form);	
			flag=asc.HasAcess("39",uname);
	        log.info("flag is "+flag);
		}
		if(form==null ||(!request.isRequestedSessionIdValid())){
			response.sendRedirect("../userlogintemp.jsp");
		}	
%>
<html:html>
<html:base/>
<html:errors />	
<head>
		<link rel="stylesheet" type="text/css" href="../StyleSheetM.css"  title="Default" />
		<script language="javascript" src="box_ex.js"></script>
		
		<!-- <script type="text/javascript" src="./sorttable.js"></script>
		<style type="text/css"> 
		/* Sortable tables */
		table.sortable a.sortheader 
		{
   			background-color:#eee;
   			font-size: 13px; 
    		font-family: Verdana;
    		color: black;
    		text-decoration: none;
    		display: block;
		}
		table.sortable span.sortarrow 
		{
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

<table width="100%" height="100%" >

<tr>
<td align="left" valign="top" bgcolor="#CCddee">

<DIV id="leftCol" style="DISPLAY: none; width:160; height:100%;">
<%@ include file="../tree3.jsp" %>
</div>
<IMG id="HideHandle" src="../openImage.gif" style="CURSOR: hand; position:absolute;" onclick='hideLeftCol("leftCol");' src="fold.gif" width="10" height="25">
</td>
<td align="left" valign="top">

<html:form  action="/LatestIndex">
<jsp:setProperty name="LatestIndexBean" property="userid1" value='<%=session.getAttribute("userid")%>'/>
	<!-- Heading  "LatestIndexDivisor"  -->
	<div id="PageTitle" >
		<table width="1000" cellspacing="0" cellpadding="0" >
			<td width="335" class="subHeader">&nbsp;</td>
		    <td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          	<font size="3" face="Verdana">
		          		<b><bean:message key="LatestDivisor.title" /></b>
		          	</font>
		    </td>      		
		</table>    
	</div>	          	 
	<!-- if compute ="yes" display links --> 	          
	<%
	harrier.income.com.report.LatestIndexForm li = new harrier.income.com.report.LatestIndexForm();
	Vector v = li.getVector_latestdivisor();
	Object ci2 = null;
			session.setAttribute("ci2",new Vector(v));      
	    String excel_path = "./FileDownload.jsp?type=8&filename=LatestIndexDivisor.xls";
	     String xml_path = "./FileDownloadXmlNew.jsp?type=8&filename=LatestIndexDivisor.xml";
	     String pdf_path = "./FileDownload_Pdf.jsp?type=8&filename=LatestIndexDivisor.pdf";
	  	String email_path ="./EmailReport.jsp?switch_type=8&cas=8&rname=LatestIndexDivisor.xls";
	  	%>
	<table  align="right">
           <!-- Links  -->
           <!-- Printer friendly -->
           <td nowrap="nowrap" > <font size="1" face="Verdana" color="blue"><a href="javascript:PrintThisPage();" ><bean:message key="LatestDivisor.printerf" /></font> </td>
           <!-- DownLoad Excel -->
           <td nowrap="nowrap"  > <font size="1" face="Verdana" color="blue"><a href='<%= excel_path %>'><bean:message key="LatestDivisor.downloade" /></a></font></td>
           <!-- DownLoad Xml -->
          <td nowrap="nowrap"  > <font size="1" face="Verdana" color="blue"><a href='<%= xml_path %>'>Export to Xml</a></font></td>
           <!-- Email Report -->
           <td nowrap="nowrap" ><font size="1" face="Verdana" color="blue"><a href='<%= email_path %>' ><bean:message key="LatestDivisor.emailr" /></a></font></td>
           <!-- Pdf Report -->
			<td nowrap="nowrap" ><font size="1" face="Verdana" color="blue"><a href='<%= pdf_path %>' ><bean:message key="LatestDivisor.pdfr" /></a></font></td>
	</table>   
	<br>          
	<br>
	<br>

	<!----div tag gor printer friendaly-------->
	<div id=contentstart>     
	       	
    
	        
	<table class="sorted" ID="sortTable" 
			border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
				<thead>
					<tr>
	       <!-- <table border="1" width="60%" align="center" cellpadding="5" cellspacing="0" id="t1" class="sortable" bgcolor="#EFEFEF">	 
               
                  <!-- Table Heading-- 
                  <tr>-->
              		<th nowrap="nowrap" align="center" id="LaDiv" class="gridStyle-header">
              			<span><b><bean:message key="LatestDivisor.iname" />
                  	    	</b></span>
                  	</th>
                 	<th nowrap="nowrap" align="center" id="Divi" class="gridStyle-header">
                 	    <span><b>
                  	 	<bean:message key="indcurrwise.divisor" />
                  		</b></span>
                  	</th>
                  </tr>
		</thead>
		<tbody>
               	   <%
 				int i=1;
 				String bcolor="";
 			%>
               	  <!-- Iterate over the latestdivisor -->
              	<logic:iterate id="result" property="latestdivisor" name="LatestIndexBean">
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
                     <td valign="middle"  align="left" nowrap="nowrap" axis="sstring" headers="LaDiv"
           			title='<bean:write name="result" property="indexname"/>'>
                     	 <font face="Verdana" size="2" color="blue">
                         <a href='./IndexComposeS.jsp?index=<bean:write name="result" property="id" />&compute=yes&ajax1=yes'><bean:write name="result" property="indexname"/></a></font>                              	 
                     </td>
                     
                    
      				
                     
                     <td valign="middle" axis="number" headers="Divi" align="right" nowrap="nowrap" title='<bean:write name="result" property="divisor"/>'>
                         <font face="Verdana" size="2" color="blue">
                         <bean:write name="result" property="divisor"/></font>
                     </td>
                  </tr>
                  <% i=i+1; %>
               	</logic:iterate>
     		     </tbody>  
	
     		   </table>
     		
    </div> 
</html:form> 

</td>
</tr>
</table>

<script>

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

function PrintThisPage() 
	{ 
   		var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
       	sOption+="scrollbars=yes,width=750,height=600,left=100,top=25"; 

   		var sWinHTML = document.getElementById('contentstart').innerHTML; 
   		var printHead =document.getElementById('PageTitle').innerHTML;
   		
   		var winprint=window.open("","",sOption); 
       	winprint.document.open(); 
       	winprint.document.write('<html><body>'); 
       	winprint.document.write(printHead);
       	winprint.document.write('<br>');
       	winprint.document.write(sWinHTML); 
        winprint.document.write('</body></html>'); 
       	winprint.document.close(); 
       	winprint.focus(); 
	}
</script>    

</html:html>
        
             