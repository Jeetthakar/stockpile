
<%@page import="com.harrier.initializeation.ConnectInit"%> 
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page language="java" import="app.*,java.sql.*,java.util.*,java.text.*" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<% 
		String var=request.getParameter("index_id"); 
		String option=null; 
		option=request.getParameter("opt");
		log.info(option);	
		String locale=session.getAttribute("locale").toString();
		//AcessControl asc=new AcessControl();
		AcessControl asc = ConnectInit.getAcessControl();
		asc.setLocale(locale);
%>
<html>
<jsp:useBean id="indexCalculatorForm" scope="session" class="app.IndexCalculatorForm"/> 
 <jsp:useBean id="indexCalculatorCollection" scope="session" class="app.IndexCalculatorCollection"/> 
 <html:form  action="/portpolioTisCalculatorAction"> 
<link href="./StyleSheet.css" rel="stylesheet" type="text/css">
<table width="1000" cellspacing="0" cellpadding="0" >
        		<tr>
		          	<td width="335" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          			<font size="3" face="Arial">
		          				<b><bean:message key="PortTCalc.title" /></b>
		          			</font>
		         	</td> 
	          </tr>
	</table>
<p></p>



 
<html:errors/>
 <table border="0" width="100%" class="gridStyle" bordercolorlight="#000000" cellspacing="0" cellpadding="0" bordercolordark="#000000">
 	<tr>
 		 <%  		
   		if(var==null){
   			var="1";
   		}  
   		String tis_value="";   		
    	%>
    	 <table width="656">
         	<tr>
         		<td width="81" nowrap="nowrap" align="left" height="30">
				</td>
         		<td width="155" nowrap="nowrap" align="right" height="40">
				   <font size="2" face="Arial">
           				<bean:message key="port.selectIndex" />
           			</font>
        		</td>   
           		<td width="407" nowrap="nowrap" align="left" height="40">
	                	<html:select property="index_id"  size="1" onchange="document.forms[0].submit();return true">
              			<html:optionsCollection name="indexCalculatorForm" property="indexListCollection" />
            		</html:select>	
           </td>
         	</tr>
         </table>  
                       <%                      
                        if(var!=null)
                        {
                        	log.info("var is "+var);
                        	indexCalculatorCollection.addStocksInTisCalculatorTable(var,request,option);
                        }
                         %>  
    
 
 	<table border="0" width="662" class="gridStyle" bordercolorlight="#000000" cellspacing="0" cellpadding="0" bordercolordark="#000000">
 	<tr>
           <td width="245" align="right" nowrap="nowrap">
            <font size="2" face="Arial">
            <%
            	if(option!=null && option.equals("Reset")){
            		tis_value="0.00";
            	}else{
            		tis_value=indexCalculatorForm.getTmcv();
            	}
            	log.info("tis_value "+tis_value);
            	
            %>
           	<bean:message key="port.enterTmcv" />&nbsp;
           	</font>
          	</td>
 			<td width="266" nowrap="nowrap">
	          	<html:text property="tis_value" size="20"  />
	        </td>
 			<td width="65" nowrap="nowrap">
           <!-- <input type= text name ="tis_value" size='20' align='right' value="<%= tis_value %>"></font>           -->
            <html:submit property="b1" ><bean:message key="defineIndex30"/></html:submit>
             </td>
 			<td width="86" nowrap="nowrap">                     
            <html:submit  property="b1" ><bean:message key="indexUpdate.reset"/></html:submit>
         
            </td>
         </tr>
   </table>
   <p></p>
     <table  border="0" width="900" cellpadding="0" cellspacing="0">
  <tr>
	 <td width="75" nowrap="nowrap">&nbsp;</td>
	 <td width="770" nowrap="nowrap">	
	 
      <table border="1" width="100%" class="gridStyle" cellspacing="0" cellpadding="0">
      	
         <tr>   
         <td width="10%" align="center" height="1" class="gridStyle-headertab"><b><bean:message key="port.srno" /></b></td>     
          <td width="25%" align="center" height="1" class="gridStyle-headertab"><b><bean:message key="port.symbolName" /></b></td>
            <td width="20%" align="center" height="1" class="gridStyle-headertab"><b><bean:message key="port.lastTradedPrice" /></b></td>
           <td width="25%" align="center" height="1" class="gridStyle-headertab"><b><bean:message key="port.mcvValue" /></b></td>
           <td width="20%" align="center" height="1" class="gridStyle-headertab"><b><bean:message key="port.sharesCalculated" /></b></td>           
           </tr>  
           <%     
           	StringBuffer str=null;
            str = indexCalculatorCollection.addStocksIncCalculatorTable(indexCalculatorCollection.table,request,option);     
           			       
			if(str!=null){%>
		<%=str.toString()%>
         <% } %>                 
           </table>
          </td>
          </tr>    
        </tr>
        </table> 
  </table> 
   </td>
   </tr>
   </table>
 </html:form>  
 </html>         