<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<table border="1" width="100%" cellpadding="0" cellspacing="0">
<tr>
<td nowrap="true" ><b><font size="2" face="Arial">&nbsp;
<bean:message key="corporate.Srno" /></font></b></td>
<td nowrap="true" ><b><font size="2" face="Arial">&nbsp;
<bean:message key="corporate.Stkexname" /></font></b></td>
<td nowrap="true" ><b><font size="2" face="Arial">&nbsp;
<bean:message key="corporate.Stkname" /></font></b></td>
<td nowrap="true" ><b><font size="2" face="Arial">&nbsp;
<bean:message key="corporate.Apply" /></font></b></td>
<td nowrap="true" ><b><font size="2" face="Arial">&nbsp;
<bean:message key="corporate.Amount" /></font></b></td>
<td nowrap="true" ><b><font size="2" face="Arial">&nbsp;
<bean:message key="corporate.Percent" /></font></b></td>
<td nowrap="true" ><b><font size="2" face="Arial">&nbsp;
<bean:message key="corporate.Ratioshare" /></font></b></td>
<td nowrap="true" ><b><font size="2" face="Arial">&nbsp;
<bean:message key="corporate.Shareoffer" /></font></b></td>
<td nowrap="true" ><b><font size="2" face="Arial">&nbsp;
<bean:message key="corporate.Desc" /></font></b></td>
<td nowrap="true" ><b><font size="2" face="Arial">&nbsp;
<bean:message key="corporate.Value" /></font></b></td>
</tr>

 <logic:iterate id="stklist" name="corporate" property="alllist" >
 <%String bcol=null;
   bcol="Black";   %>
		 <logic:equal value="<%=app.UpdateCorp.accept_date()%>" property="apply" name="stklist">
		 <%bcol="Red"; %>
		 </logic:equal>
 
 <tr>
<td nowrap="true" align="center">
<%if(bcol!=null & bcol.equals("Red")){%>
<html:radio property="c_Cad" value="id" idName="stklist" style="border-top-style:solid;border-top-color: RED;border-top-width: 1px;border-bottom-style: solid;border-bottom-color: RED;border-bottom-width: 1px;border-left-style: solid;border-left-color: RED;border-left-width: 1px;border-right-style: solid;border-right-color: RED;border-right-width: 1PX; "/>
<%}if(bcol!=null & bcol.equals("Black")){%> 
<html:radio property="c_Cad" value="id" idName="stklist" />
<%}%>
</td>
<td nowrap="true" ><font size="2" face="Arial" color="<%=bcol %>">&nbsp;<bean:write name="stklist" property="stkexname"/></font></td>				
<td nowrap="true" ><font size="2" face="Arial" color="<%=bcol %>">&nbsp;<bean:write name="stklist" property="sname"/></font></td>	
<td nowrap="true"><font size="2" face="Arial" color="<%=bcol %>">&nbsp;<bean:write name="stklist" property="apply"/></font></td>			  
<td nowrap="true" ><font size="2" face="Arial" color="<%=bcol %>">&nbsp;<bean:write name="stklist" property="amount"/></font></td>	
<td nowrap="true" ><font size="2" face="Arial" color="<%=bcol %>">&nbsp;<bean:write name="stklist" property="percent"/></font></td>			
<td nowrap="true" ><font size="2" face="Arial" color="<%=bcol %>">&nbsp;<bean:write name="stklist" property="ratio_shr"/></font></td>			
<td nowrap="true" ><font size="2" face="Arial" color="<%=bcol %>">&nbsp;<bean:write name="stklist" property="shr_offer"/></font></td>	
<td nowrap="true" ><font size="2" face="Arial" color="<%=bcol %>">&nbsp;<bean:write name="stklist" property="desc"/></font></td>			
<td nowrap="true" ><font size="2" face="Arial" color="<%=bcol %>">&nbsp;<bean:write name="stklist" property="value"/></font></td>			
</tr>
</logic:iterate>
</table>

 