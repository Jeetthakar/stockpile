<!-- Report Preferences------------------------- -->
<!---- Neha -----------23-12-2006---------------------- -->

<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@page import="org.apache.log4j.Logger"%>
<%@ page language="java" import="java.text.*" %>
<%@ page import="java.util.*" %>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>


<html:html>
<html:errors />
<head></head>
<%
     Date curr=new Date();
     SimpleDateFormat ft=new SimpleDateFormat("dd-MM-yyyy");
     String str=ft.format(curr);
 %>
<body bgcolor="#aabbcc"  locale="true">
<html:form  action="" focus="selectreport">
<p>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 </p>
<p>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font face="arial-helvitica" size="4" color="black" ><b><i><bean:message key="report.head" /></b></i></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 </p>
<p>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</p>
     <table  border="0" cellspacing="0" cellpadding="0" width="100%">
<tr>
<td width="25%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font face="arial-helvitica" size="3"><b><bean:message key="report.select" /></b></font>  </td>
<td width="25%" >
<html:select property="selectreport" name="" style="width=175">
	<html:optionsCollection property="reportCollection" name=""/>
</html:select> 
</td>
</tr>
<tr>
<td width="25%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font face="arial-helvitica" size="3"><b><bean:message key="report.selectdate" /></b></font>  </td>
<td width="25%">&nbsp;&nbsp;<html:radio property="date" value="" />&nbsp;&nbsp; <font face="arial-helvitica" size="3"><b><bean:message key="report.day" /></b></font>&nbsp;&nbsp;<html:radio property="date" value="" />&nbsp;&nbsp;<font face="arial-helvitica" size="3"><b><bean:message key="report.month" /></b></font> 
&nbsp;&nbsp;<html:radio property="date" value="" />&nbsp;&nbsp; <font face="arial-helvitica" size="3"><b><bean:message key="report.year" /></b></font> </td> 
<td width="25%" >
<html:select property="difference" name="" style="">
	<html:optionsCollection property="diffCollection" name="" />
</html:select>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font face="arial-helvitica" size="3"><b><bean:message key="report.currentdate" /></b></font>	
</td>	
 <td width="25%" ><html:text property="currdate" name=""  size="10"  readonly="readonly"  value="<%=str%>"  /></td>
</tr>
<tr>
<td width="25%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font face="arial-helvitica" size="3"><b><bean:message key="report.filter" /></b></font>  </td>
<td width="25%" >
<html:select property="selectfilter" name="" style="width=175">
		<html:optionsCollection property="filterCollection" name=""/>
</html:select>
 </td>
</tr>
<tr>
<td width="25%" >&nbsp;&nbsp;&nbsp;&nbsp; </td>
<td width="25%" >&nbsp;&nbsp;&nbsp;&nbsp; </td>
</tr>
<tr>
<td width="25%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font face="arial-helvitica" size="3"><b>Select Exchange/Index </b></font>  </td>
<td width="25%" >
<html:select property="selecttype" name="" style="width=175" size="3" >
	<html:optionsCollection property="typeCollection" name=""/>
 </html:select>			
 </td>
</tr>
<tr>
<td width="25%" >&nbsp;&nbsp;&nbsp;&nbsp; </td>
<td width="25%" >&nbsp;&nbsp;&nbsp;&nbsp; </td>
</tr>
<tr>
<td width="25%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  </td>
<td width="25%" >&nbsp;&nbsp;&nbsp;&nbsp;<html:submit  property="submit" value="submit" onclick="" />
<html:button property="exit" value="exit" /></td>
</tr>
<tr>
<td width="25%" >&nbsp;&nbsp;&nbsp;&nbsp; </td>
<td width="25%" >&nbsp;&nbsp;&nbsp;&nbsp; </td>
</tr>
</table>
</html:form>
</body>
</html:html>








