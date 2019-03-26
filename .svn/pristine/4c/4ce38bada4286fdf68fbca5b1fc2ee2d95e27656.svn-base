<%@ page import = "java.util.*" %>
<%@ page import = "java.io.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.text.ParseException" %>
<%@ page import="com.harrier.initializeation.ConnectInit"%>
<%@ page  import="app.*"%>
<%@ page  import="harrier.income.com.report.*"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<!-- @author lokesh -->
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
<html:errors />
<html:base/>
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
 		
 		
 			<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  />
			<script language="javascript" src="./codethatcalendarstd.js"></script>
			<script language="javascript" src="box_ex.js"></script>
			<script type='text/javascript' src='/Stockpile/dwr/interface/IndexComposeReport.js'></script>
			<script type='text/javascript' src='/Stockpile/dwr/interface/GraphMethods.js'></script>
	
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
	
		<%		
			String firEntry ="no";
			firEntry= request.getParameter("FromLogin");
	   		if(firEntry!= null && firEntry.equals("yes"))
			{
		
			String varSelectIndex="0";
	    	PortfolioReportForm Idf= new PortfolioReportForm();
	    	Idf.getTabledata();
	    	session.setAttribute("colected_vector",Idf.getVw());
	    	session.setAttribute("ci2",Idf.getVw());
	    	
	    	varSelectIndex =Idf.getIndex();
			session.removeAttribute("varIndexId");
			session.setAttribute("varIndexId",varSelectIndex);
						
			String indMulti[]=Idf.getIndMulti();
			session.setAttribute("indMulti",indMulti);
			
			session.removeAttribute("indexes");
			session.setAttribute("indexes",indMulti);
			
			String varSelectToDate =Idf.getTo();
			session.removeAttribute("to");
			session.setAttribute("to",varSelectToDate);
			
			String varSelectFromDate =Idf.getFrom();
			session.removeAttribute("from");
			session.setAttribute("from",varSelectFromDate);
	    	session.setAttribute("varChart","1");
	    	//for industry weightage
			Idf.getIndweighttable();
	    	session.setAttribute("colected_vector_iw",Idf.getVi());
	    	
	    		    	
			}	
	    %>	
		
	</head>
<body>
<jsp:useBean id="PortfolioReportBean" scope="session" class="harrier.income.com.report.PortfolioReportForm"/>

<table width="100%" height="100%" >

<tr>
<td align="left" valign="top" bgcolor="#CCddee">

<DIV id="leftCol" style="DISPLAY: none; width:160; height:100%;">
<%@ include file="../tree3.jsp" %>
</div>
<IMG id="HideHandle" src="../openImage.gif" style="CURSOR: hand; position:absolute;" onclick='hideLeftCol("leftCol");' src="fold.gif" width="10" height="25">
</td>
<td align="left" valign="top">


<html:form  action="/portfolioReportAction">
<jsp:setProperty name="PortfolioReportBean" property="index"/>

<%        if(request.isRequestedSessionIdValid()) {%>
				<jsp:setProperty name="PortfolioReportBean" property="userid1" value='<%=session.getAttribute("userid")%>'/>
				<jsp:setProperty name="PortfolioReportBean" property="roleId_port" value='<%=session.getAttribute("role_id").toString()%>'/>
		<%}%>


	
<br>
<br>
<logic:equal value="on" property="checkChart" name="PortfolioReportBean">
	<jsp:setProperty name="PortfolioReportBean" property="graph" value='<%= request.getParameter("graphURL")%>'/>
</logic:equal>
<html:hidden property="indexName" name="PortfolioReportBean" />	
		<table width="1024" bgcolor="#84AADE" border="1">
        		<tr>
    				<td width="33%" align="center">
    					<font size="1" face="Verdana">
         				Portfolio
       					</font>
       					<font size="1" face="Verdana">
          				<html:select property="index" size="1"  styleId="sIE" onchange="document.forms[0].submit();">
          				<html:optionsCollection name="PortfolioReportBean" property="indexcollection"/>
          				</html:select>
          				</font>	
    				</td>
    			
    				 <td width="33%" valign="top" align="center">
    				 	<table><tr><td>
    				 	<font size="1" face="Verdana">
    				 		Benchmark
    				 	</font>
    				 	</td>
    				 	<td><br>
    					<html:select property="indMulti" size="3" multiple="true" >
							<html:optionsCollection name="PortfolioReportBean" property="indexcollection"/>
						</html:select>
						</td>
						</tr>
						</table>
      				</td>
    				<td width="34%" align="left">
    					<font size="1" face="Verdana">
    						Time Period
    					</font>
    					
    					<html:select property="days" size="1"  styleId="sIE" onchange="document.forms[0].submit();">
          				<html:optionsCollection name="PortfolioReportBean" property="daysCollection"/>
          				</html:select>
    				</td>
  				</tr>
		</table>	
	   
		
  			<%				String filenameCompare=null;
   		    		     //   GraphMethodsPf gm3=new GraphMethodsPf();
   	    				    GraphMethodsPf gm3=ConnectInit.getGraphMethodsPf();
   	    				    filenameCompare=gm3.getGraphChartIndexCompare(session,new PrintWriter(out));
   	    				    String graphURLCompare = request.getContextPath() + "/servlet/DisplayChart?filename=" + filenameCompare;
   	    				    log.info("1 : "+graphURLCompare);
   	    	%>	 
   	    	 <!-- index movement from-->
        	
        	<%
        			
        			//	GraphMethodsPf objGM = new GraphMethodsPf();
        				GraphMethodsPf objGM=ConnectInit.getGraphMethodsPf();
        				objGM.getGraphChart1(session,new PrintWriter(out),"maverage");
        		 	 	String filename1=objGM.getFilename();
     			  		//String graphURL=objGM.getGraphURL();
     			  		String graphURL1= request.getContextPath() + "/servlet/DisplayChart?filename=" + filename1; 
     			  		log.info("2 : "+graphURL1);
     	  
        	%>		
<!-- Industrywise comparision  -->
	   	<%
	   		
        			//	GraphMethodsPf objSect = new GraphMethodsPf();
        				GraphMethodsPf objSect=ConnectInit.getGraphMethodsPf();
        			//	log.info("3 : Got the GraphMethodsPf objSect"+objSect);
        				objSect.getGraphSectorPortfolio(session,new PrintWriter(out));
        		 	 //	log.info("3 :  objSect.getGraphSectorPortfolio() over...");
        		 	 	String fileSect=objSect.getFilenameSect();     			  		
     			  		String graphURLSect= request.getContextPath() + "/servlet/DisplayChart?filename=" + fileSect;  
     			  		log.info("3 : "+graphURLSect);
     	 %>
	     
	     <% 
				GraphMethodsPf.ReaddataCompose(request);
			//	GraphMethodsPf gm = new GraphMethodsPf();
				GraphMethodsPf gm=ConnectInit.getGraphMethodsPf();
				String fileIndCompose =gm.getGraphChartCompany1(session,new PrintWriter(out));
				String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + fileIndCompose; 
				log.info("4 : "+graphURL);
			%>
     	   <table  border="4" align="center" cellpadding="0" cellspacing="0">
     	   			<tr> 						
    						<td width="50%" align="center" bgcolor="#84AADE">
    						<font size="1" face="Verdana">--Portfolio Comparision--</font>
    						</td>
    						<td width="50%" align="center" bgcolor="#84AADE">
    						<font size="1" face="Verdana">--Portfolio Movement--</font>
    						</td>
    				</tr>
    				
    				
					<tr> 
						
						<td  width="50%" nowrap="nowrap" align="center"  bgcolor="#84AADE">
					  		<img src="<%= graphURLCompare %>" height="270"  width="500" >
						</td>
						<td width="50%" nowrap="nowrap" align="center"  bgcolor="#84AADE">
    			   			<img src="<%= graphURL1 %>" width="500" height="270" >
    			     
    					</td>
					</tr>		
     	   			<tr> 	
     	   					<td width="500" align="left" bgcolor="#84AADE">
    						<font size="1" face="Verdana">
    						<a href="javascript:indCompare();"> 
					 				Read Comparision Data
					 			</a>
    						</font>
    						</td>
    						<td width="500" align="left" bgcolor="#84AADE">
    						<font size="1" face="Verdana">
    						<a href="javascript:indMove();"> 
					 				Read Movement Data
					 			</a>
    						</font>
    						</td>					
    						
    				</tr>
    			
        	
        	<tr>
        		
    			<td width="50%" align="center">
				 		<img src="<%= graphURLSect %>" width="500" height="270" border="0" align="right">
				</td>
				<td width="50%" align="center">
				 		<img src="<%= graphURL %>" width="500"  height="270" border="0" align="right" usemap="#<%//= filename %>">
				</td>
	 		</tr>
	 		<tr>						
    				<td width="500" align="left" bgcolor="#84AADE">
    						<font size="1" face="Verdana">
    						<a href="javascript:sectComp();"> 
					 				Read Industrywise Comparision Data
					 			</a>
    						</font>
    				</td>
    				<td width="500" align="left" bgcolor="#84AADE">
    						<font size="1" face="Verdana">
    						<a href="javascript:indCom();">Read Composition Data</a>
    						</font>
    				</td>
    						 
  			</tr>
  			
        	</table>

	  
<html:hidden property="defaultVal" />
<html:hidden property="calc" value="no"></html:hidden>
<html:hidden property="compute" value="no"></html:hidden>
<html:hidden property="rep_path" value=""></html:hidden>
</html:form> 

</td>
</tr>
</table>

</body>

<script  language="javascript">

var Button;
var man1=document.getElementById("hiddenTable");
	man1.style.display= "none"; 

//var reqpath;
	
function bustOut(){
//var rep_path = document.getElementById("rep_path");
//alert("PP "+rep_path);
//if(rep_path=="/pages/reports/"){
//}
//lse{
//	rep_path="";
//}

//var path = rep_path+"PortfolioIndWtg.jsp";
//alert(path);
var newWin = window.open("./PortfolioIndWtg.jsp", "subWindow","height=500,width=550,resizable=yes,scrollbars=yes");
//var newWin = window.open(path, "subWindow","height=500,width=500,resizable=yes,scrollbars=yes");

}
	function indCom(){
//var newWin = window.open("PortfolioIndCompose.jsp", "subWindow","height=500,width=700,resizable=yes,scrollbars=yes");
var newWin = window.open("./PortfolioIndCompose.jsp", "subWindow","height=500,width=700,resizable=yes,scrollbars=yes");

}
function indMove(){
//var newWin = window.open("PortfolioIndMove.jsp", "subWindow","height=500,width=700,resizable=yes,scrollbars=yes");
var newWin = window.open("./PortfolioIndMove.jsp", "subWindow","height=500,width=520,resizable=yes,scrollbars=yes");
}
function indCompare(){
//var newWin1 = window.open("PortfolioCompare.jsp", "subWindow","height=500,width=700,resizable=yes,scrollbars=yes");
var newWin1 = window.open("./PortfolioCompare.jsp", "subWindow","height=500,width=530,resizable=yes,scrollbars=yes");
}
function sectComp(){
//var newWin1 = window.open("PortfolioCompare.jsp", "subWindow","height=500,width=700,resizable=yes,scrollbars=yes");
var newWin1 = window.open("./PortfolioSector.jsp", "subWindow","height=500,width=530,resizable=yes,scrollbars=yes");
}


</script>    
</html:html>
