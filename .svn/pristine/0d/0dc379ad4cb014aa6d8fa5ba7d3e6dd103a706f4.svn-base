
<%@page import="app.LogonForm"%>
<%@page import="org.apache.log4j.Logger"%><%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page  import="harrier.income.com.report.*"%>
<%@ page  import="java.util.*"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
			LogonForm form = (app.LogonForm)session.getAttribute("user");
			  if(form == null)
			response.sendRedirect("../login1.jsp");
%>
<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">

</head>

<body bgcolor="#C0C0C0">
<jsp:useBean id="email" scope="session" class="app.EmailReportForm"/>
<html:form action="/email" onsubmit="return split_all();">
	<p style="margin-top: 0; margin-bottom: 0"><b>
	<font face="Arial" color="#3399FF">&nbsp;&nbsp;&nbsp; MAIL</font></b></p>
	<hr>
	<table border="0" width="100%" cellspacing="0" cellpadding="0" height="299">
		
		<%  //Logger Logging = Logger.getLogger("EmailReportBatchFinal.jsp");
			String r_type = request.getParameter("switch_type").toString();
			int r_typeVal = Integer.parseInt(r_type);
			String name = request.getParameter("rname"); 
		    String var = request.getParameter("varid"); 
		    String usern = form.getUsername();
			String cas_from = request.getParameter("cas");
			
			String from = request.getParameter("from");
			String to_date = request.getParameter("to");
			
			Vector vec = (Vector) session.getAttribute("ci2"); 
			String var1 = null;
			
			if(r_typeVal==1){
				session.removeAttribute("ci2");
				vec=(Vector)session.getAttribute("ci2Composition");
				session.setAttribute("ci2",vec);
				
				
			}
			
			if(r_typeVal == 10)
			{
			
				session.removeAttribute("ci2");
				vec=(Vector)session.getAttribute("ci2h");
				session.setAttribute("ci2",vec);
				
			
			}
			
			if(r_typeVal==20){
				
				session.removeAttribute("ci2");
				vec=(Vector)session.getAttribute("ci2pepb"); 
				session.setAttribute("ci2",vec);
					
			}
			if(r_typeVal==19){
				session.removeAttribute("ci2");
				vec=(Vector)session.getAttribute("ci2divisor"); 
				session.setAttribute("ci2",vec);
				
			}
			if(r_typeVal==25){
				session.removeAttribute("ci2");
				vec=(Vector)session.getAttribute("ci2Tr"); 
				session.setAttribute("ci2",vec);
				
			}
			if(r_typeVal==22){
				session.removeAttribute("ci2");
				vec=(Vector)session.getAttribute("ci2SD"); 
				session.setAttribute("ci2",vec);
				
			}
			if(r_typeVal==16){
				session.removeAttribute("ci2");
				vec=(Vector)session.getAttribute("ci2Cap"); 
				session.setAttribute("ci2",vec);
				
			}
			if(r_typeVal==3){
				session.removeAttribute("ci2");
				vec=(Vector)session.getAttribute("ci2Iww"); 
				session.setAttribute("ci2",vec);
				
			}
			if(r_typeVal==6){
				session.removeAttribute("ci2");
				vec=(Vector)session.getAttribute("ci2SDetails"); 
				session.setAttribute("ci2",vec);
				
			}
			if(r_typeVal==5){
				session.removeAttribute("ci2");
				vec=(Vector)session.getAttribute("ci2Scc"); 
				session.setAttribute("ci2",vec);
				
			}
			if(r_typeVal==14){
				session.removeAttribute("ci2");
				vec=(Vector)session.getAttribute("ci2IRV"); 
				session.setAttribute("ci2",vec);
				
			}
			if(r_typeVal==2){
				session.removeAttribute("ci2");
				vec=(Vector)session.getAttribute("ci2Cwv"); 
				session.setAttribute("ci2",vec);
				
			}
			
			switch (r_typeVal) {
				case 2 :	
					// Companywise weightage	
					
					cas_from = request.getParameter("cas");
					name = request.getParameter("rname"); 
					var1=(String)session.getAttribute("var");
					email.setVec(vec);
					break;
					
				case 10 :	
					// Index compare ohlc	
										
					cas_from = request.getParameter("cas");
					name = request.getParameter("rname"); 
					//var1=(String)session.getAttribute("var");
					email.setVec(vec);
					break;	
				
				case 12 :
					// Index Performance
					
			   		cas_from = request.getParameter("cas");
					name = request.getParameter("rname"); 
					from=request.getParameter("from"); 
					email.setVec(vec);
					break;
					
				case 14:
					// Index Returns Volatility 
					session.setAttribute("from",from);
					session.setAttribute("to",to_date);
					cas_from = request.getParameter("cas");
					name = request.getParameter("rname"); 
					vec = (Vector) session.getAttribute("ci2"); 
					email.setVec(vec);	
				
				case 17:
					// Inactive Stock List
					
					cas_from = request.getParameter("cas");
					name = request.getParameter("rname"); 
					email.setVec(vec);									
					break;
					
				case 19:
					// Index Divisor
					cas_from = request.getParameter("cas");
			   		name = request.getParameter("rname"); 
			   		var1=(String)session.getAttribute("selectIndex19");
					from=request.getParameter("from"); 
					to_date=request.getParameter("to");
					email.setVec(vec);
					break;	

				case 20 :
					// Index performance PE, PB
					
			   		cas_from = request.getParameter("cas");
					name = request.getParameter("rname"); 
					from=request.getParameter("from"); 
					to_date=request.getParameter("to");
					email.setVec(vec);
					break;
					
				case 22 :
					// Stock divident
					try	{
						String indExch = request.getParameter("indExch");
						session.setAttribute("varr",indExch);
					}catch(Exception e) {
					//	Logging.error(" Error = "+ e);
					}
					break;
				
				case 23:
					// Stock details from date
					
			   		cas_from = request.getParameter("cas");
					name = request.getParameter("rname"); 
					email.setVec(vec);									
					break;
					
				case 24:
					// Moving Index
					cas_from = request.getParameter("cas");
			   		name = request.getParameter("rname"); 
			   		var1=(String)session.getAttribute("varIndexId");
					vec = (Vector) session.getAttribute("ci2"); 
					email.setVec(vec);
					break;
					
				case 15:
					cas_from = request.getParameter("cas");
					name = request.getParameter("rname"); 
					vec = (Vector) session.getAttribute("ci2"); 
					email.setVec(vec);		
					break;
					
				case 25:
					// Traded Volume
					try	{
						String indExch = request.getParameter("indExch");
						session.setAttribute("varr",indExch);
					}catch(Exception e) {
					//	Logging.error(" Error = "+ e);
					}
					break;
				
			}
			
	   %>
		
		<input type="hidden" value="" name="rname">
		<input type="hidden" value="<%= r_type%>" name="switch_type">
		<input type="hidden" value="<%= name%>" name="att">
		<input type="hidden" value="<%= var%>" name="varid">
		<input type="hidden" value="<%= var1%>" name="var1">
		<input type="hidden" value="<%= from%>" name="from">
		<input type="hidden" value="<%= to_date%>" name="to_date">
		<input type="hidden" value="<%= usern%>" name="username">
		<input type="hidden" value="<%= cas_from%>" name="cas">
		
		<tr>
			<td width="12%"><b><font face="Arial" size="2">&nbsp;&nbsp;&nbsp; To :</font></b></td>
			<td colspan="2"><input type="text" name="to" size="49"></td>
		</tr>
		<tr>
			<td width="12%" height="26"><b><font face="Arial" size="2">&nbsp;&nbsp;&nbsp; Cc :</font></b></td>
			<td height="26" colspan="2"><input type="text" name="cc" size="49"></td>
		</tr>
		<tr>
			<td width="12%" height="33"><b><font face="Arial" size="2">&nbsp;&nbsp;&nbsp; Subject :</font></b></td>
			<td height="33" colspan="2"><input type="text" name="subject" size="49"></td>
		</tr>
		<tr>
			<td width="12%" height="34"><b><font face="Arial" size="2">&nbsp;&nbsp;&nbsp; Attachment :</font></b></td>
			<td height="34" colspan="2"><input type="text" name="attachment" disabled size="49" value="<%= name%>"></td>
		</tr>
		<tr>
			<td width="12%" align="left" valign="top" height="147">
			<b>
			<font face="Arial" size="2">&nbsp;&nbsp;&nbsp; Description :</font></b></td>
			<td height="147" valign="top" colspan="2">
			<textarea rows="8" name="desc" cols="37"></textarea></td>
		</tr>
		<tr>
			<td width="12%" align="left" valign="top" height="26">
			<p align="right">&nbsp;</td>
			<td valign="top" height="26" width="7%">
			<input type="submit" value="Send" name="B1"></td>
			<td valign="top" height="26" width="81%">
			<input type="reset" value="Cancel" name="B2" onClick="history.back()"> 
			</td>
		</tr>
	</table>
	<hr>
	<p>&nbsp;</p>
</html:form>
<script language="JavaScript">
function split_all()
{
	var strEmail1 = document.forms[0].to.value;
	var strcc = document.forms[0].cc.value;
	var com = ",";
	if(strEmail1)
	{
		var to_array = strEmail1.split(",");
		for(i=0; i < to_array.length; i++)
		{
			var ret = validateEmail(to_array[i]);
			if(ret == false)
			{
				alert('Invalid email address.');
				document.forms[0].to.value.focus;
				return false;
			}
		}
		if(strcc)
		{
			var to_strcc = strcc.split(",");
			for(i=0; i < to_strcc.length; i++)
			{
				var ret = validateEmail(to_strcc[i]);
				if(ret == false)
				{
					alert('Invalid email in Cc address.');
					document.forms[0].to.value.focus;
					return false;
				}
			}		
		}
		return true;
	}	
	alert('Please enter email address.');
	return false;
}

function validateEmail(strEmail) 
{ 
   var at="@" ;
   var dot="." ;
   var lat=strEmail.indexOf(at) ;
   var lstr=strEmail.length ;
   var ldot=strEmail.indexOf(dot) ;
   if (strEmail.indexOf(at)==-1)
   {
      return false ;
   }
   if (strEmail.indexOf(at)==-1 || strEmail.indexOf(at)==0 || strEmail.indexOf(at)==lstr)
   {
       return false ;
   } 
   if (strEmail.indexOf(dot)==-1 || strEmail.indexOf(dot)==0 || strEmail.indexOf(dot)==lstr)
   {
      return false;
   } 
   if (strEmail.indexOf(at,(lat+1))!=-1)
   {
     return false;
   } 
  if (strEmail.substring(lat-1,lat)==dot || strEmail.substring(lat+1,lat+2)==dot)
  { 
     return false ;
  } 
  if (strEmail.indexOf(dot,(lat+2))==-1)
  { 
    return false;
  }
  if (strEmail.indexOf(" ")!=-1)
  { 
  	return false;
  }
  return true;
} 


</script>
</body>

</html>
