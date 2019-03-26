<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@ page import = "java.util.Vector" %>
<%@ page language="java" import="app.*"%>
<%@ page language="java" import="harrier.income.com.report.*"%>
<%@ page import = "org.jfree.chart.demo.servlet.ComposeIndex" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import = "java.util.*" %>
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
AcessControl asc=new AcessControl();
boolean flag=false;
LogonForm form = null;
			if(request.isRequestedSessionIdValid())	{	
				form = (LogonForm)session.getAttribute("user");
			}
			if(form==null ||(!request.isRequestedSessionIdValid())){
				response.sendRedirect("../userlogintemp.jsp");
			}			
%>
<jsp:useBean id="IndexListReportBean" scope="session" class="harrier.income.com.report.IndexListReportForm"/>
<jsp:setProperty name="IndexListReportBean" property="userid1" value='<%=session.getAttribute("userid")%>'/>
<jsp:setProperty name="IndexListReportBean" property="roleid1" value='<%=session.getAttribute("role_id")%>'/>
<html:html>
<html:base/>
<head>

	<link rel="stylesheet" type="text/css" href="../StyleSheetM.css"  />
			<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
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
	 
<script language="javascript">
	function RowColorToOriginal(a)
	{
		// Get the last character of the row number
		var lastCharacter=a.substring(a.length-1);
		// depending on last character set the appropriate backgroundColor of the table row

		if(lastCharacter%2==0){
			document.getElementById(a).style.backgroundColor = "#FFDEAD";
		}
		else{
			document.getElementById(a).style.backgroundColor = "#FFFFFF";
		}
		//document.getElementById(a).style.color = "blue";
	}

	function ChangeRowColor(a )
	{

		//document.getElementById(a).style.color = "red";
		document.getElementById(a).style.backgroundColor = "99FFFF";
	}
	
	</script>
</head>

 <% 			 
		if(request.getParameter("FromLogin")!=null && request.getParameter("FromLogin").equals("No")){
			IndexListReportBean.reset();
			log.info("in new"+request.getParameter("new"));
	
%>

<body onload="initialize()"> 
	 <% }else{ %>
	 	<body onLoad="menuload()"> 
	 <% } %>
	 	
<html:form  action="/IndexListRpt">	
	<b>
	<br>
		
			<p align="center" class="subHeader">
		      <font size="3" face="Arial Black">
		          <bean:message key="IndexeListReportN.title"/>
		      </font>
			</p>	
<br>
	 
<!-- ============================================ For Ajax ================================================== -->  
	
	<!-- <table class="sorted" ID="sortTable" border="3" align="center" cellpadding="10" cellspacing="0" bgcolor="white">
		-->
		<table ID="sortTabletable" 
			border="1" align="center" cellpadding="3" cellspacing="0">
		<thead>
		<tr>
	        <th align="center"  nowrap="nowrap" id="indName" class="gridStyle-header"><span><b><bean:message key="indexcompose.indexname"/>
           	</b></span></th>
           	<th align="center"  nowrap="nowrap" id="checkind" class="gridStyle-header"><span><b><bean:message key="IndexListReport.checkind"/>
          	</b></span></th>
           	<th align="center"  nowrap="nowrap" id="last" class="gridStyle-header"><span><b><bean:message key="indexUpdate.Value"/>
          	</b></span></th>
           	<th align="center" nowrap="nowrap" id="minmax" class="gridStyle-header"><span><b><bean:message key="IndexeListReport.MinMax"/>
         	</b></span></th>
			<!--  <th align="center" nowrap="nowrap" id="status" class="gridStyle-header"></th> -->
           	<th align="center"  nowrap="nowrap" id="change"  class="gridStyle-header"><span><b><bean:message key="IndexListReportN.Change"/>
        	</b></span></th>
         	<th align="center"  nowrap="nowrap" id="date" class="gridStyle-header"><span><b><bean:message key="corporate.Date"/>
          	</b></span></th>
         	<th  align="center" nowrap="nowrap" id="oneweek" class="gridStyle-header"><span><b><bean:message key="IndexListReport.oneweek"/>
         	</b></span></th>
          	<th align="center"  nowrap="nowrap" id="onemonth" class="gridStyle-header"><span><b><bean:message key="IndexListReport.onemonth"/>
          	</b></span></th>
          	<th align="center"  nowrap="nowrap" id="fwh" class="gridStyle-header"><span><b><bean:message key="IndexListReportN.FWH"/>
          	</b></span></th> 
          	<th align="center"  nowrap="nowrap" id="fwl" class="gridStyle-header"><span><b><bean:message key="IndexListReportN.FWL"/>
          	</b></span></th> 
        </tr>
		</thead>
		<tbody	>
			
			<% 	int i=1;
				int cnt=1;
						String Bgcolor=""; // use to give alternate bands of color to table row
						String RowLabel="";// use as a ID value for the table row
			%>
			
		 			
 		<logic:iterate id="try2" property="details" name="IndexListReportBean">
 			<html:hidden property="index" name="IndexListReportBean" />
 			<%
					RowLabel="row"+i ; // identify the table row as row1, row2, .. etc
					/* if row number is even the table row Bgcolor="#FFDEAD" else Bgcolor="#C0C0C0" */
					if (i%2==0){
						Bgcolor="#84AADE";
					}else{
						Bgcolor="#DEE3EF";
					}
						i++;
					%>
		<tr id="<%=RowLabel %>" bgcolor="<%=Bgcolor %>" > 		
			
			 <td valign="middle" align="left" nowrap="nowrap" axis="sstring" headers="indName"
           			title='<bean:write name="try2" property="indexname"/>'>
            	
            	<font face="Verdana" size="2" color="blue">
          		 <!--	 <a href='./IndexGraph.jsp?index=<bean:write name="try2" property="indexid"/>&compute=yes&ajax1=yes''> -->
          			
          			<bean:write name="try2" property="indexname"/></a>
          		</font> 
         	</td>
         	
         	<%
         	String f=null;
         	
         	try 
         	{
				ArrayList list =new ArrayList();
				
				list=IndexListReportBean.getDetails();
				//log.info("lisssst"+list);
				if(list!= null)
				{
						
					Iterator it=list.iterator();
					int j=1;	
					while(it.hasNext())
					{
						IndexListReportDetails d=(IndexListReportDetails)it.next();
						f=d.getIndexid();
						if(cnt==j)
						{
							%>			
							<td align="center">
             					<input type="checkbox" name="indvalue"  value="<%=f%>" onclick='indCom("<%=f%>");'>                                 
            				</td>
							<%	 
							//log.info("hello"+f);
						}
						j=j+1;
						//log.info("j="+j);
					}
						
					
				}
			} 
			catch(Exception e)
			{
				log.info("Error in IndexList Report"+e);
			}
			%>
			
			
      	        	 	
         	<td valign="middle" align="right" axis="number" headers="last" nowrap="nowrap" 
         			title='<bean:write name="try2" property="current"/>'>
          		<font face="Verdana" size="2" color="blue">
          			<bean:write name="try2" property="current"/>
      			</font>
          	</td>
          	
          	<td valign="middle" align="right" axis="number" headers="minmax" nowrap="nowrap"
          			title='<bean:write name="try2" property="minmax"/>'>
       			<font face="Verdana" size="2" color="blue">
          			<bean:write name="try2" property="minmax"/>
          		</font>
          	</td>
          	
               
            <td valign="middle" align="right" axis="number" headers="change" nowrap="nowrap"
          			title='<bean:write name="try2" property="vachange"/>'>
          		<font face="Verdana" size="2" color="blue">
          			<bean:write name="try2" property="vachange"/>
          		</font>
       
          		
          		<logic:equal value="up" name="try2" property="status">
          			<img border="0" src="images/up1.GIF" width="10" align="middle" height="11">
          		</logic:equal>          		
          		<logic:equal value="down" name="try2" property="status">
          			<img border="0" src="images/down1.GIF" width="10" align="middle" height="11">
          		</logic:equal>
          		<logic:equal value="mid" name="try2" property="status">
          			<img border="0" src="images/mid.gif" width="10" align="middle" height="11">
          		</logic:equal>
          		
          	</td>  
          	    	
          
          	
          	<td  valign="middle" align="right" nowrap="nowrap" axis="date" headers="date"
          			title='<bean:write name="try2" property="indexdate"/>'>
          		<font face="Verdana" size="2" color="blue">
          			<bean:write name="try2" property="indexdate"/>
          		</font> 
          	</td>
          	
          	<td  valign="middle" align="right" axis="number" headers="oneweek" nowrap="nowrap"
          			title='<bean:write name="try2" property="oneweek"/>'>
        		<font face="Verdana" size="2" color="blue">
         			<bean:write name="try2" property="oneweek"/>
         		</font>
         		
         		<logic:equal value="up1W" name="try2" property="status1W">
          			<img border="0" src="images/up1.GIF" width="10" align="middle" height="11">
          		</logic:equal>          		
          		<logic:equal value="down1W" name="try2" property="status1W">
          			<img border="0" src="images/down1.GIF" width="10" align="middle" height="11">
          		</logic:equal>
          		<logic:equal value="mid1W" name="try2" property="status1W">
          			<img border="0" src="images/mid.gif" width="10" align="middle" height="11">
          		</logic:equal>
          	
          	</td>
          	
          	<td valign="middle" align="right" axis="number" headers="onemonth" nowrap="nowrap"
          			title='<bean:write name="try2" property="onemonth"/>'>
          		<font face="Verdana" size="2" color="blue">
          			<bean:write name="try2" property="onemonth"/>
          		</font>
          		
          		<logic:equal value="up1M" name="try2" property="status1M">
          			<img border="0" src="images/up1.GIF" width="10" align="middle" height="11">
          		</logic:equal>          		
          		<logic:equal value="down1M" name="try2" property="status1M">
          			<img border="0" src="images/down1.GIF" width="10" align="middle" height="11">
          		</logic:equal>
          		<logic:equal value="mid1M" name="try2" property="status1M">
          			<img border="0" src="images/mid.gif" width="10" align="middle" height="11">
          		</logic:equal>
          	
          	</td>  
          	
          	<td valign="middle" align="right" axis="number" headers="fwh" nowrap="nowrap"
          			title='<bean:write name="try2" property="fwh"/>'>
       			<font face="Verdana" size="2" color="blue">
          			<bean:write name="try2" property="fwh"/>
          		</font>
          	</td>
          	
          	<td valign="middle" align="right" axis="number" headers="fwl" nowrap="nowrap"
          			title='<bean:write name="try2" property="fwl"/>'>
       			<font face="Verdana" size="2" color="blue">
          			<bean:write name="try2" property="fwl"/>
          		</font>
          	</td>  
          	<!-- 
          	<td  valign="middle" align="right" nowrap="nowrap" axis="date" headers="indexids"
          			title='<bean:write name="try2" property="indexid"/>'>
          		<font face="Verdana" size="2" color="blue">
          			<bean:write name="try2" property="indexid"/>
          		</font> 
          	</td>
                   	-->
          </tr>
         <%
         	cnt=cnt+1;
         	//log.info("cnt="+cnt);
         %>
          </logic:iterate>
  			
        </tbody>  
	</table>
	

</html:form>

<script language="javascript">
var Button;
var sourceTable, destTable;
function initSort() {
//	alert('initSort');
	
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
//	alert('initSort end');
}

function initialize() {
//	alert('initialize');
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
	initSort();
}	

function menuload(){
//	alert('menu Load');
	top.topFrame.location.reload();	
	top.treeFrame.location.reload();
	initSort();	
}

function indCom(f1){

//alert(f1);
var myScript1 =  window.open('./IndexGraph.jsp?FromLogin=yes&index='+f1+'',"subWindow",'height=380, width=530,resizable= no, scrollbars=yes, titlebar=no');

}

</script>

</body>
</html:html>