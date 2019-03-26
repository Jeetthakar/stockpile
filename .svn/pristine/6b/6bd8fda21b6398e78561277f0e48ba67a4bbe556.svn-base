
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page language="java" import="app.*,java.sql.*,java.util.*,java.text.*" %><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<% 
		String option=null;option=request.getParameter("opt");
		String var=request.getParameter("index_id");  
		String locale=session.getAttribute("locale").toString();
			//	AcessControl asc=new AcessControl();
			AcessControl asc=ConnectInit.getAcessControl();	
				asc.setLocale(locale);
%>
<html>
<jsp:useBean id="indexCalculatorForm" scope="session" class="app.IndexCalculatorForm"/> 
 <jsp:useBean id="indexCalculatorCollection" scope="session" class="app.IndexCalculatorCollection"/> 
 <html:form  action="/indexCalculatorAction">
   
<link href="./StyleSheet.css" rel="stylesheet" type="text/css">
<table width="1000" cellspacing="0" cellpadding="0" >
        		<tr>
		          	<td width="335" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          			<font size="3" face="Arial">
		          				<b><bean:message key="IndexCalculator.title"/></b>
		          			</font>
		         	</td> 
	          </tr>
	</table>
<p></p>

 
<html:errors/>
<table width="656">
         	<tr>
         		  
 <%  		
   		if(var==null){
   			var="1";
   		}  %>
   		<td width="81" nowrap="nowrap" align="left" height="30">
				</td>
         		<td width="125" nowrap="nowrap" align="right" height="40">
				   <font size="2" face="Arial">
           				<bean:message key="port.selectIndex" />
           			</font>
        		</td> 
	                  <td width="407" nowrap="nowrap" align="left" height="40">
	                  	<html:select property="index_id" size="1" onchange="document.forms[0].submit();return true">
              			<html:optionsCollection name="indexCalculatorForm" property="indexListCollection" />
            		</html:select>	
           </td>
          </tr>
         </table>              <%                       
                        if(var!=null)
                        {
                        	log.info("var is "+var);
                        	indexCalculatorCollection.addStocksInIndexCalculatorTablePrice(var,request);
                        }
                         %>  
                                         

 	<table border="0" width="662" class="gridStyle" bordercolorlight="#000000" cellspacing="0" cellpadding="0" bordercolordark="#000000">
 	<tr>
           <td width="218" align="right" nowrap="nowrap">
            <font size="2" face="Arial">
            	<bean:message key="port.indexValueCalculated" />&nbsp;
          	</font>
          	</td>
 			<td width="266" nowrap="nowrap">         	
          		<html:text property="indexValue" size="20"  />	            		           
            </td>
 			<td width="65" nowrap="nowrap">       
            <html:submit property="b1"  ><bean:message key="defineIndex30"/></html:submit>
              </td>
 			<td width="86" nowrap="nowrap">                      
            <html:submit property="b1"  ><bean:message key="indexUpdate.reset"/></html:submit>          
          </td>
         </tr>
   </table>
   <p></p>
     <table  border="0" width="900" cellpadding="0" cellspacing="0">
  <tr>
	 <td width="75" nowrap="nowrap">&nbsp;</td>
	 <td width="770" nowrap="nowrap">	
	 
      <table border="1" class="gridStyle" width="80%" >
      	
         <tr>   
         <td width="10%" align="center" height="1" class="gridStyle-header"><bean:message key="port.srno" /></td>     
          <td width="30%" align="center" height="1" class="gridStyle-header"><bean:message key="port.symbolName" /></td>
           <td width="20%" align="center" height="1" class="gridStyle-header"><bean:message key="port.lastTradedPrice" /></td>
           <td width="20%" align="center" height="1" class="gridStyle-header"><bean:message key="port.myRates" /></td>           
           </tr>  
           <%     
           	StringBuffer str=null;
           if(option!=null && option.equals("Reset")){      		
           		 str = indexCalculatorCollection.addRowsIncCalculatorTablePrice(indexCalculatorCollection.table,request,option);     
           	}else{
           		 str = indexCalculatorCollection.addRowsIncCalculatorTablePrice(indexCalculatorCollection.table,request,option);         		       
           	}    		       
			if(str!=null){%>
		<%=str.toString()%>
         <% } %>                 
           </table>
          </td>
          </tr>    
         </tr>
        </table> 
  </table> 
  </td></tr>
  </table>
  
 </html:form>
        
 </html>         