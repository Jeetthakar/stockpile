<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>

<table border="1" width="100%" cellpadding="0" cellspacing="0" >
<tr>
<td nowrap="true" class="gridStyle-header" ><b><font size="2" face="Arial"  >&nbsp;<bean:message key="corporate.Srno" /></font></b></td>
<td nowrap="true" class="gridStyle-header"><b><font size="2" face="Arial" >&nbsp;<bean:message key="corporate.Action" /></font></b></td>
<td nowrap="true" class="gridStyle-header"><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Stkexname" /></font></b></td>
<td nowrap="true" class="gridStyle-header"><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Stkname" /></font></b></td>
<td nowrap="true" class="gridStyle-header"><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Apply" /></font></b></td>
<td nowrap="true" class="gridStyle-header"><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Applied" /></font></b></td>
<td nowrap="true" class="gridStyle-header"><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Amount" /></font></b></td>
<td nowrap="true" class="gridStyle-header"><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Percent" /></font></b></td>
<td nowrap="true" class="gridStyle-header"><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Ratioshare" /></font></b></td>
<td nowrap="true" class="gridStyle-header"><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Shareoffer" /></font></b></td>
<td nowrap="true" class="gridStyle-header"><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.NoShares" /></font></b></td>
<td nowrap="true" class="gridStyle-header"><b><font size="2" face="Arial">&nbsp;<bean:message key="corporate.Status" /></font></b></td>
</tr>

 <logic:iterate id="stklist" name="corporate" property="alllist" >
 
 <%String bcol=null;
   bcol="Black";   %>
 <logic:notEqual value="Undo" property="button" name="corporate">
	 <logic:equal value="n" name="stklist" property="status">
		 <logic:equal value="<%=app.UpdateCorp.accept_date()%>" property="apply" name="stklist">
		 <%bcol="Red"; %>
		 </logic:equal>
	 </logic:equal>
 </logic:notEqual> 
 
 <tr>
<td width="4%">&nbsp;
<logic:equal value="Undo" property="button" name="corporate">
<logic:equal value="n" name="stklist" property="status">
<html:multibox property="c_Cad" disabled="true"><bean:write name="stklist" property="id"/></html:multibox>
</logic:equal>
<logic:equal value="y" name="stklist" property="status">
<html:multibox property="c_Cad"><bean:write name="stklist" property="id"/></html:multibox>
</logic:equal>
</logic:equal>

<logic:notEqual value="Undo" property="button" name="corporate">
<logic:equal value="n" name="stklist" property="status">
<%if(bcol!=null & bcol.equals("Red")){%>
<html:multibox property="c_Cad" style="border-top-style:solid;border-top-color: RED;border-top-width: 1px;border-bottom-style: solid;border-bottom-color: RED;border-bottom-width: 1px;border-left-style: solid;border-left-color: RED;border-left-width: 1px;border-right-style: solid;border-right-color: RED;border-right-width: 1PX; ">
<bean:write name="stklist" property="id"/>
</html:multibox>
<%}if(bcol!=null & bcol.equals("Black")){%>
<html:multibox property="c_Cad">
<bean:write name="stklist" property="id"/>
</html:multibox>
<%}%>
</logic:equal>

<logic:equal value="y" name="stklist" property="status">
<html:multibox property="c_Cad" disabled="true"><bean:write name="stklist" property="id"/></html:multibox>
</logic:equal>
</logic:notEqual>

</td>
<td nowrap="true" ><font size="2" face="Arial" color="<%=bcol%>">&nbsp;<bean:write name="stklist" property="action"/></font></td>
<td nowrap="true" ><font size="2" face="Arial" color="<%=bcol%>">&nbsp;<bean:write name="stklist" property="stkexname"/></font></td>				
<td nowrap="true" ><font size="2" face="Arial" color="<%=bcol%>">&nbsp;<bean:write name="stklist" property="sname"/></font></td>	
<td nowrap="true"><font size="2" face="Arial" color="<%=bcol%>">&nbsp;<bean:write name="stklist" property="apply"/></font></td>			  
<td nowrap="true" ><font size="2" face="Arial" color="<%=bcol%>">&nbsp;<bean:write name="stklist" property="applied"/></font></td>			
<td nowrap="true" ><font size="2" face="Arial" color="<%=bcol%>">&nbsp;<bean:write name="stklist" property="amount"/></font></td>	
<td nowrap="true" ><font size="2" face="Arial" color="<%=bcol%>">&nbsp;<bean:write name="stklist" property="percent"/></font></td>			
<td nowrap="true" ><font size="2" face="Arial" color="<%=bcol%>">&nbsp;<bean:write name="stklist" property="ratio_shr"/></font></td>			
<td nowrap="true" ><font size="2" face="Arial" color="<%=bcol%>">&nbsp;<bean:write name="stklist" property="shr_offer"/></font></td>	
<td nowrap="true" ><font size="2" face="Arial" color="<%=bcol%>">&nbsp;<bean:write name="stklist" property="no_share"/></font></td>			
<td nowrap="true" ><font size="2" face="Arial" color="<%=bcol%>">&nbsp;<bean:write name="stklist" property="status"/></font></td>			
</tr>		
 </logic:iterate>

</table>