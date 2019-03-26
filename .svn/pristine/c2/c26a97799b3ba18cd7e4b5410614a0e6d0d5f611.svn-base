
<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.text.ParseException" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page  import="app.*"%>
<%@ page  import="harrier.income.com.report.*"%>
<%@ page import = "java.util.*" %>
<%@ page import = "java.io.*" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<!-- @author Manoj Adekar -->
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
<html:base/>
 	<head>
 		<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  />
		<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
		<script language="javascript" src="box_ex.js"></script>
		<script type='text/javascript' src='/Stockpile/dwr/interface/IndexComposeReport.js'></script>
		<script type='text/javascript' src='/Stockpile/dwr/interface/GraphMethods.js'></script>
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
	
		<%		
			String firEntry ="no";
			firEntry= request.getParameter("FromLogin");
	   		if(firEntry!= null && firEntry.equals("yes"))
			{		
				String varSelectIndex="0";
	    		IndexGraphForm Igf= new IndexGraphForm();
	       		  				
				varSelectIndex =request.getParameter("index");
				session.removeAttribute("varIndexId");
				session.setAttribute("varIndexId",varSelectIndex);
				
				//log.info("varSelectIndex"+varSelectIndex);
				
				Igf.setIndex(varSelectIndex);			
				String varSelectToDate =Igf.getTo();
				session.removeAttribute("to");
				session.setAttribute("to",varSelectToDate);

				String varSelectFromDate =Igf.getFrom();
				session.removeAttribute("from");
				session.setAttribute("from",varSelectFromDate);
	    		session.setAttribute("varChart","1");
	    	
	    		//out.println("indexid==="+varSelectIndex);
	    	}
		 %>	
<script language="JavaScript">

function closewin()
{
   document.forms[0].days.value = 5;
   var d=document.forms[0].days.value;
  //alert('Days='+d);
 document.forms[0].submit();  
  window.close();

}

function setSelmonth()
{
	document.forms[0].days.value = 5;
	alert('Days='+d);
	
}
</script>  
	<title>Index Graph </title>
	</head>
<body>
<jsp:useBean id="IndexGraphBean" scope="session" class="harrier.income.com.report.IndexGraphForm"/>
 
<html:form  action="/IndexGraphAct">
<jsp:setProperty name="IndexGraphBean" property="index"/>




<br>
<br>

<logic:equal value="on" property="checkChart" name="IndexGraphBean">
	<jsp:setProperty name="IndexGraphBean" property="graph" value='<%= request.getParameter("graphURL")%>'/>
</logic:equal>

<html:hidden property="indexName" name="IndexGraphBean" />	
<html:hidden property="index" name="IndexGraphBean" />

		
		<table width="525" bgcolor="#BFBFBF" border="1">
        		<tr>
    			<!-- 	<td width="33%" align="center">
    					<font size="2" face="Verdana">
         				Index Graph
       					</font>
       					<font size="1" face="Verdana">
          				<html:select property="index" size="1"  styleId="sIE" onchange="document.forms[0].submit();">
          				<html:optionsCollection name="IndexGraphBean" property="indexcollection"/>
          				</html:select>
          				</font>	
    				</td> -->
    			
    				<td width="34%" align="center">
    					<font size="1" face="Verdana">Time Period</font>
    					<html:select property="days" size="1"  styleId="sIE" onchange="document.forms[0].submit();">
          				<html:optionsCollection name="IndexGraphBean" property="daysCollection"/>
          				</html:select>
    				</td>
    				
    				
  					<td>
     						<input type="Button" name="clswin" value="Close" onClick="closewin();">
  					</td> 			
  				</tr>
		</table>	
  			
  			 <!-- index movement from-->
        	
        	<%
        			
        			//	GraphMethodsPf objGM = new GraphMethodsPf();
        				GraphMethodsPf objGM = ConnectInit.getGraphMethodsPf();
        				objGM.getGraphChart1(session,new PrintWriter(out),"maverage");
        		 	 	String filename1=objGM.getFilename();
     			  		//String graphURL=objGM.getGraphURL();
     			  		String graphURL1= request.getContextPath() + "/servlet/DisplayChart?filename=" + filename1; 
     			  	
     	  	%>
     	<table  border="4" align="center" cellpadding="0" cellspacing="0">
     	   		<tr> 						
    				<td width="50%" align="center" bgcolor="#BFBFBF">
    						<font size="1" face="Verdana">--Index Movement--</font>
    				</td>
    			</tr>
    				
				<tr> 
					<td  width="50%" nowrap="nowrap" align="center"  bgcolor="#BFBFBF">
						<img src="<%= graphURL1 %>" height="270"  width="500" >
					</td>	
				</tr>		
		</table>

</html:form> 
</body>

</html:html>
