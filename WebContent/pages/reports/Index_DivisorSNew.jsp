
<%@page import="org.apache.log4j.Logger"%><%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="app.*"%>
<%@ page import="harrier.income.com.report.*" %>
<%@ page import = "java.io.PrintWriter" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<jsp:useBean id="IndexDivisorBean" scope="session" class="harrier.income.com.report.IndexDivisorForm"/> 
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
	//	Logger	Logging =Logger.getLogger("Index_DivisorSNew.jsp");
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
			<title></title>
			<link href="../StyleSheetM.css" rel="stylesheet" type="text/css">
			<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
			<script language="javascript" src="box_ex.js"></script>
			<script language="javascript">
				var c2 = new CodeThatCalendar(caldef2);
			</script>
			
		<script type="text/javascript" src="../Script/Event.js"></script>		
		<script type="text/javascript" src="../Script/SortedTable.js"></script>
		<script type='text/javascript' src='/Stockpile/dwr/interface/MoveTable.js'></script>
		<script type='text/javascript' src='/Stockpile/dwr/engine.js'></script>
		<script type='text/javascript' src='/Stockpile/dwr/util.js'></script>
		<script type='text/javascript' src='/Stockpile/dwr/interface/GraphMethods.js'></script>
		
		
	</head>

<body  >

<table width="100%" height="100%" >
	<tr>
		<td>
	
		<html:form action="/indexDivisorAction">
			<jsp:setProperty name="IndexDivisorBean" property="userid1" value='<%=session.getAttribute("userid")%>'/>
			
		 	
      <table border="0" width="935"  cellspacing="0" cellpadding="3"  height="30"> <!-- Selectbox for displaying the Index List -->
        <tr>
        	<td align="left" width="19" nowrap="nowrap">
        		&nbsp;
          	</td>
			<td align="right" width="121" nowrap="nowrap" >
        	<font size="2" color="black" face="Verdana"><bean:message key="LatestDivisor.iname" />
        	</font>
        	</td>
        	<td align="left" nowrap="nowrap" width="771">
	      		<html:select property="selectIndex" size="1" onchange="" styleId="sIE">
					<html:optionsCollection property="selectIndexCollection" name="IndexDivisorBean"/>
				</html:select>
            </td>          	
        </tr>
      </table>
          
      <table width="886" height="40">
     	<tr>
          
	  	
	  		<td width="50" align="right" nowrap="nowrap">
        			
        	</td>
	  		<td align="left" nowrap="nowrap" width="113">
            	<font face="Verdana" size="2">
	 				<bean:message key="IndexDivisor.aispan" />
	 			</font>
          	</td>	
          	<td align="left" nowrap="nowrap" width="90">
            	<html:select property="avgSpan" size="1" styleId="sIE">
					<html:optionsCollection property="selectAvgSpan" name="IndexDivisorBean"/>
				</html:select >
            </td>	
            <td align="left" nowrap="nowrap" width="86">
            	<font face="Verdana" size="2">
	 				<bean:message key="IndexDivisor.schart" />
	 			</font>
          	</td>	
          	<td align="left" nowrap="nowrap" width="308">
            	<html:select property="selchart" size="1" onblur="return selectChart()" styleId="sIE" >
					<html:optionsCollection property="selectChart" name="IndexDivisorBean"/>
				</html:select >
            </td>
         </tr>		
	   </table>
	  
	   <table border="0" width="940"  cellspacing="0" cellpadding="3" >
        	<tr>
        		<td  width="101" align="right" nowrap="nowrap"> 
        			<html:checkbox property="check_mavg" onblur="return mavg()"/>&nbsp;
        		</td>
        		
        		<td nowrap="nowrap" align="left" width="145">	
			   		<font size="2" face="Verdana">
	  					<bean:message key="IndexDivisor.vmavg" /> 	
	  				</font>
	  			</td>
	  			<td nowrap="nowrap" width="90"><font size="2" face="Verdana" >		
	              	<bean:message key="corporate.Fdate" />
	            </td> 
            	<td nowrap="nowrap" width="80">
	             	<html:text property="from" readonly="true" size="10"/>
	            </td>
	            <td  nowrap="nowrap" width="45">  
					<html:button property="shwFrom" value="..." onclick="c2.popup('from');"/>
	          	</td>
	          	<td nowrap="nowrap" width="83">
	           		<font size="2" face="Verdana">
	                	<bean:message key="corporate.Tdate" />
	                </font>
	            </td>
	            <td nowrap="nowrap" width="78">
	             	<html:text property="to" readonly="true" size="10"/>
	            </td>
	            <td  nowrap="nowrap" width="45" >
					<html:button property="shwTo" value="..." onclick="c2.popup('to');"/>
	          	</td>
	          	<td nowrap="nowrap" width="212"> 
	          	 <%	
	          	 
	          	 String ajax1="no";
				try{
					ajax1=request.getParameter("ajax1");
				}catch (Exception e) {
				// TODO: handle exception
		//		Logging.error(" Error :"+e.getMessage());
				}
	          	 if(ajax1.equals("yes")){
	  			%>
	               	<html:submit onclick="return go1();" onkeypress="go();" ><bean:message key="Reports.View" /></html:submit>&nbsp;&nbsp;
				<% } %>
				</td>	
				<td width="4"></td>
	  		</tr>
		</table>
		
	       	
    	<div style="height: 350px; overflow: auto; "> 
     		<table border="0" width="85%"  align="left" cellpadding="2" cellspacing="0" >
     		
     		<%
							//IndexDivisorForm indformS=new IndexDivisorForm();
		     	  			
		     	  			String toDt1=IndexDivisorBean.getTo();
		     	  			String frDt1=IndexDivisorBean.getFrom();
		     	  			String indId=IndexDivisorBean.getSelectIndex();
		     	  			log.info("NOT Null"+toDt1+"  "+frDt1+"  "+indId);
		     	  			
		     	  			if(indId!=null)
		     	  			{
		     	  			StringBuffer str = null; 
		     	  			str=IndexDivisorBean.display_Divisor(indId,frDt1,toDt1);
		     	  			
		     	  			
		          	 		%>				    	
		         			<%
								try{
									if(str!=null){
									log.info("NOT Null");
							%>
		         			<%=str.toString()%>	
		         			<%		}else{
							%>
		
							<%		}
							}catch(Exception e){}
								}
							%>
     		
     		</table>
     	</div>	
     
     
       	<html:hidden property="compute" value="no"></html:hidden>
        <html:hidden property="chart"></html:hidden>
</html:form>
</td>
</tr>

</table>


</body>
 
<script language="javascript">
 	
function go1() {
 	var objTo=document.forms[0].to;
 	document.forms[0].compute.value="yes";
	var aa=document.forms[0].compute.value;
	alert("Value="+aa);
	if(document.forms[0].check_mavg.checked==true)
    document.forms[0].check_mavg.value="checked";
    else
    document.forms[0].check_mavg.value="unchecked";
    if((checkdatecurrent(objTo))==false)	
	{
	alert("ToDate should be Less Than CurrentDate.");
	objTo.focus();
	objTo.select();
	return false;
	}
	else return true;
}
   
function go() {
 	document.forms[0].compute.value="yes";
	var aa=document.forms[0].compute.value;
	alert("Value="+aa);
	//document.forms[0].clear.value ="yes";
	//document.forms[0].defaultVal.value="no";
	if(document.forms[0].check_mavg.checked==true)
    document.forms[0].check_mavg.value="checked";
    else
    document.forms[0].check_mavg.value="unchecked";
    document.forms[0].submit();
}


			
</script>	
</html:html>
