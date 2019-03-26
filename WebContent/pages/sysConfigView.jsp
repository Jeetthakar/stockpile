<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import="sysconfig.model.*,java.util.*, org.apache.struts.util.*,java.lang.*" %>
<jsp:useBean id="sysConfigBean" scope="session" class="sysconfig.action.SysConfigForm" />
<%@ page language="java" import="app.*"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
			LogonForm form = (LogonForm)session.getAttribute("user");
			  if(form == null)
			response.sendRedirect("login1.jsp");
%>
<html:html>
<html:errors /> 
 	<head>
 	<html:base/>
 	

 	<META Http-Equiv="Cache-Control" Content="no-cache">
	<META Http-Equiv="Pragma"        Content="no-cache">
	<META Http-Equiv="Expires"       Content="0">
	<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
	<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
	<meta name="ProgId" content="FrontPage.Editor.Document">
	<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />  
    	<title>
      		<bean:message key="typeForm.title" />
    	</title>
	</head>
  
	<body  class="b2">	
	<%
		if(request.getParameter("from")!=null && request.getParameter("from").equals("menu")){
			sysConfigBean.reset();
		String status = sysConfigBean.getCheck_flag();
		}
	%>
  	 	<html:form action="/SysConfig-action"  onsubmit="return validateSysConfigBean(this);" >
		<table width="100%" class="sample">
        	<tr><td width="100%" nowrap="nowrap">
  		
 	   	
        <tr>
           	<td class="subHeader" ><b>&nbsp;<bean:message key="typeForm.title"/></b></td>
        	<td class="subHeader" ></td>  
        	<td class="subHeader" ></td> 
        	<td class="subHeader" ></td>           
           
        </tr>
      
        	<tr><td width="110%" nowrap="nowrap">
       <table border="0" width="110%"  cellspacing="0" cellpadding="3" height="406">
        <tr>
        	<td align="left" width="181">
        		<font face="Arial" size="2">
        			<bean:message key="sysConfigForm.computeInterval" />
          		</font>
          	</td>
          	<td align="left" nowrap>
            	<font face="Arial" size="2">
            	<logic:present name="sysConfigBean">
            		<logic:equal value="0" property="computationInterval" name="sysConfigBean">
	            			<html:text property="computationInterval" size="12" maxlength="15" value=""  />
	            	</logic:equal>
	              	<logic:notEqual value="0" property="computationInterval" name="sysConfigBean">
	            			<html:text property="computationInterval" size="12" maxlength="15" name="sysConfigBean"  />
	            	</logic:notEqual>
            	</logic:present>		
          		</font>
          		
          	</td>
            
            <td align="left" nowrap="nowrap" width="181">
           		<font face="Arial" size="2">
           			<bean:message key="sysConfigForm.industryClassification" />
          		</font>
           </td>
           <td align="left" nowrap="nowrap">
	          <font face="Arial" size="2">
	            	<html:select property="industryClassificationId" size="1" >
              			<html:optionsCollection name="sysConfigBean" property="industryClassificationCollection" />
            		</html:select>	
           </td>
        </tr>
        
        <tr>
          	<td align="left" nowrap="nowrap">
            	<font face="Arial" size="2">
            		<bean:message key="sysConfigForm.moniterRefreshRate" /></font>
          	</td>
          	<td align="left" nowrap="nowrap">
            	<font face="Arial" size="2">
            	
            	<bean:define id="nam" type="java.lang.String" name="sysConfigBean"  property="currencyId"/>
            	<%log.info("pranay:" +nam);%>
            	<logic:present name="sysConfigBean">
            		<logic:equal value="0" property="monitorRefreshRate" name="sysConfigBean">
	            			<html:text property="monitorRefreshRate" size="12" maxlength="15" value=""  />
	            	</logic:equal>
	              	<logic:notEqual value="0" property="monitorRefreshRate" name="sysConfigBean">
	            			<html:text property="monitorRefreshRate" size="12" maxlength="15" name="sysConfigBean"  />
	            	</logic:notEqual>
            	</logic:present>	
             	(sec)
          		</font>
          	</td>
           <td align="left" nowrap="nowrap">
           		<font face="Arial" size="2">
           			<bean:message key="sysConfigForm.timeZone" />
          		</font>
          	  </td>
          	 <td align="left" width="100" nowrap="nowrap">
          		<font face="Arial" size="2">
					<html:select property="timeZoneId" size="1" >
               		 	<html:optionsCollection name="sysConfigBean" property="timeZoneCollection" />
            		</html:select>	
	          
	       		</font>
	         </td>       
           
        	</tr>
             
        <tr>
          <td align="left" nowrap="nowrap">
            <font face="Arial" size="2">
            	<bean:message key="sysConfigForm.precisionValue" />
         	 </font>
          </td>
          <td align="left" nowrap="nowrap">
          	<font face="Arial" size="2">
          	    <logic:present name="sysConfigBean">
            		<logic:equal value="0" property="precisionValue" name="sysConfigBean">
	            			<html:text property="precisionValue" size="12" maxlength="15" value=""  />
	            	</logic:equal>
	              	<logic:notEqual value="0" property="precisionValue" name="sysConfigBean">
	            			<html:text property="precisionValue" size="12" maxlength="15" name="sysConfigBean"  />
	            	</logic:notEqual>
            	</logic:present>	
           		(sec)
          	</font>
          </td>
            <td align="left" nowrap="nowrap">
           		<font face="Arial" size="2">
           			<bean:message key="sysConfigForm.stockExId" />
          		</font>
            </td>
          	<td align="left" nowrap="nowrap">
          		<font face="Arial" size="2">
		        		<html:select property="stockExId" size="1" >
							<html:optionsCollection name="sysConfigBean" property="stockExCollection" />
            			</html:select>	
	          	
        	</td>
        </tr>
        
        <tr>
          <td align="left" nowrap="nowrap">
            <font face="Arial" size="2">
            	<bean:message key="sysConfigForm.ropf" />
          	</font>
          </td>
          <td align="left" nowrap="nowrap">
            <font face="Arial" size="2">
            	<logic:present name="sysConfigBean">
            		<logic:equal value="0" property="rateOfPriceFeed" name="sysConfigBean">
	            			<html:text property="rateOfPriceFeed" size="12" maxlength="15" value=""  />
	            	</logic:equal>
	              	<logic:notEqual value="0" property="rateOfPriceFeed" name="sysConfigBean">
	            			<html:text property="rateOfPriceFeed" size="12" maxlength="15" name="sysConfigBean"  />
	            	</logic:notEqual>
            	</logic:present>	
           </font>
          </td>
       	  <td align="left" nowrap="nowrap">
          		<font face="Arial" size="2">
           	       		   <bean:message key="sysConfigForm.currencyId" />
           		</font>
          </td>
          
          <td align="left" nowrap="nowrap">
          <font face="Arial" size="2">
          	<html:select property="currencyId" size="1" >
				<html:optionsCollection name="sysConfigBean" property="currencyIdCollection" />
            </html:select>	
	         <a href="../pages/masters/AddCurrency.jsp"><bean:message key="sysConfigForm.newcurrency"/></a href>
         	
	       </font></td>
        </tr>
       
        <tr>
          <td align="left" nowrap="nowrap">
           		<font face="Arial" size="2">
           			<bean:message key="sysConfigForm.intraDay" />
          		</font>
          </td>
          <td align="left" nowrap="nowrap">
            	<font face="Arial" size="2">
            	<logic:present name="sysConfigBean">
            		<logic:equal value="0" property="intraDayArchivalInterval" name="sysConfigBean">
	            			<html:text property="intraDayArchivalInterval" size="12" maxlength="15" value=""  />
	            	</logic:equal>
	              	<logic:notEqual value="0" property="intraDayArchivalInterval" name="sysConfigBean">
	            			<html:text property="intraDayArchivalInterval" size="12" maxlength="15" name="sysConfigBean"  />
	            	</logic:notEqual>
            	</logic:present>	
            	
           		</font>
           </td>
         	<td align="left" nowrap="nowrap">
           	<font face="Arial" size="2">
           		<bean:message key="sysConfigForm.countryId" />
          	</font>
           </td>
           <td align="left" nowrap="nowrap">
          	<font face="Arial" size="2">
          	 	<html:select property="countryId" size="1" >
					<html:optionsCollection name="sysConfigBean" property="countryIdCollection" />
            	</html:select>
	      	 	<a href="../pages/masters/countries.jsp"><bean:message key="sysConfigForm.newcountry"/></a href>
		     </font>
          </td>
          
        </tr>
        
        <tr>
          <td align="left" nowrap="nowrap">
           	<font face="Arial" size="2">
           		<bean:message key="sysConfigForm.marketLot" />
          	</font>
          </td>
          <td align="left" nowrap="nowrap">
          	<font face="Arial" size="2">
          		<logic:present name="sysConfigBean">
            		<logic:equal value="0" property="marketLot" name="sysConfigBean">
	            			<html:text property="marketLot" size="12" maxlength="15" value=""  />
	            	</logic:equal>
	              	<logic:notEqual value="0" property="marketLot" name="sysConfigBean">
	            			<html:text property="marketLot" size="12" maxlength="15" name="sysConfigBean"  />
	            	</logic:notEqual>
            	</logic:present>	
          </td>
          <td align="left" nowrap="nowrap">
           	<font face="Arial" size="2">
           		<bean:message key="sysConfigForm.dateFormat" />
          	</font>
          </td>
          <td align="left" nowrap="nowrap">
          	<font face="Arial" size="2">
          
          		<html:select property="date" size="1">
            		<html:option value="d">d</html:option>
            		<html:option value="dd">dd</html:option>
            		<html:option value="yy">yy</html:option>
          	   		<html:option value="yyyy">yyyy</html:option>
          	   		<html:option value="mm">mm</html:option>
          	    	<html:option value="mmm">mmm</html:option>
            	</html:select>
            	<html:select property="month" size="1">
          	    	<html:option value="mm">mm</html:option>
          	    	<html:option value="mmm">mmm</html:option>
          	    	<html:option value="d">d</html:option>
            		<html:option value="dd">dd</html:option>
            		<html:option value="yy">yy</html:option>
          	   		<html:option value="yyyy">yyyy</html:option>
          		</html:select>
          		<html:select property="year" size="1">
          	    	<html:option value="yyyy">yyyy</html:option>
          	    	<html:option value="yy">yy</html:option>
          	    	<html:option value="d">d</html:option>
                	<html:option value="dd">dd</html:option>
                	<html:option value="mm">mm</html:option>
          	    	<html:option value="mmm">mmm</html:option>
           		</html:select>
           		<html:select property="validator" size="1">
          	    	<html:option value="-">-</html:option>
          	    	<html:option value="/">/</html:option>
          		</html:select> (<bean:message key="sysConfigForm.separator"/>)
          
        </tr>
        
        <tr>
          <td align="left" nowrap="nowrap">
           	<font face="Arial" size="2"> 
           		<bean:message key="sysConfigForm.dateDifference" />
          	</font>
           </td>
          <td align="left" nowrap="nowrap"> 
          	<font face="Arial" size="2">
          		<logic:present name="sysConfigBean">
            		<logic:equal value="0" property="dateDifference" name="sysConfigBean">
	            			<html:text property="dateDifference" size="12" maxlength="15" value=""  />
	            	</logic:equal>
	              	<logic:notEqual value="0" property="dateDifference" name="sysConfigBean">
	            			<html:text property="dateDifference" size="12" maxlength="15" name="sysConfigBean"  />
	            	</logic:notEqual>
            	</logic:present>	
           </font>
           </td>
           <td align="left" nowrap="nowrap">
            <font face="Arial" size="2">
            	<bean:message key="sysConfigForm.customerName" />
          	</font>
          </td>
          <td align="left" nowrap="nowrap">
            <font face="Arial" size="2" >
	            <logic:present name="sysConfigBean">
	            		<logic:empty property="customerName" name="sysConfigBean">
		            			<html:text property="customerName" size="12" maxlength="15" value=""  />
		            	</logic:empty>
		              	<logic:notEmpty  property="customerName" name="sysConfigBean">
		            			<html:text property="customerName" size="12" maxlength="15" name="sysConfigBean"  />
		            	</logic:notEmpty>
	            </logic:present>	
           
           </font>
          </td>
        </tr>
        
        <tr>
          <td align="left" nowrap="nowrap">
            <font face="Arial" size="2">
            	<bean:message key="sysConfigForm.maxComp" />
          	</font>
          </td>
          
          <td align="left" nowrap="nowrap">
          	<font face="Arial" size="2">
          	<logic:present name="sysConfigBean">
            		<logic:equal value="0" property="maxNoOfCompanies" name="sysConfigBean">
	            			<html:text property="maxNoOfCompanies" size="12" maxlength="15" value=""  />
	            	</logic:equal>
	              	<logic:notEqual value="0" property="maxNoOfCompanies" name="sysConfigBean">
	            			<html:text property="maxNoOfCompanies" size="12" maxlength="15" name="sysConfigBean"  />
	            	</logic:notEqual>
            </logic:present>	
          		
              	</font>
           	</td>
           	<td align="left" nowrap="nowrap">
          		<font face="Arial" size="2">
          	 		<bean:message key="sysConfigForm.customerLogo" />
          		</font>
          	</td>
        
          	<td align="left" nowrap="nowrap">
          		<font face="Arial" size="2">
  		  			<html:file property="customerLogoPath" value="cPath" size="12" />
  		  		</font>
  		  	</td> 
          
        </tr>
       
        <tr>
      	  <td align="left" nowrap="nowrap">
            <font face="Arial" size="2">
           		<bean:message key="sysConfigForm.alertPercentage" />
          	</font>
          </td>
          <td align="left" nowrap="nowrap">
          	<font face="Arial" size="2">
          		<logic:present name="sysConfigBean">
            		<logic:equal value="0" property="alertPercentage" name="sysConfigBean">
	            			<html:text property="alertPercentage" size="12" maxlength="15" value=""  />
	            	</logic:equal>
	              	<logic:notEqual value="0" property="alertPercentage" name="sysConfigBean">
	            			<html:text property="alertPercentage" size="12" maxlength="15" name="sysConfigBean"  />
	            	</logic:notEqual>
            	</logic:present>	
          	 	(%)
            </font>
           </td>
           <td align="left" nowrap="nowrap">
            <font face="Arial" size="2">
            	<bean:message key="sysConfigForm.namevertical" />
          	</font>  
          </td>
          <td align="left">
            <font face="Arial" size="2" nowrap="nowrap">
            	<html:select property="nameLogoVerticalAlign" size="1">
				  <html:option value="Top">Top</html:option>
				  <html:option value="Bottom">Bottom</html:option>
				  <html:option value="Center">Center</html:option>
				</html:select>
			        
         	 </font>
         </td>
        </tr>
        
        <tr>
          <td align="left">
            <font face="Arial" size="2" nowrap="nowrap">
            	<bean:message key="sysConfigForm.rejectionPercentage" />
          	</font>
          </td>
          <td align="left" nowrap="nowrap">
          	<font face="Arial" size="2">
          		<logic:present name="sysConfigBean">
            		<logic:equal value="0" property="rejectionPercentage" name="sysConfigBean">
	            			<html:text property="rejectionPercentage" size="12" maxlength="15" value=""  />
	            	</logic:equal>
	              	<logic:notEqual value="0" property="rejectionPercentage" name="sysConfigBean">
	            			<html:text property="rejectionPercentage" size="12" maxlength="15" name="sysConfigBean"  />
	            	</logic:notEqual>
            	</logic:present>	
            (%)
            </font>
           </td>
           <td align="left" nowrap="nowrap">
            <font face="Arial" size="2">
            	      	<bean:message key="sysConfigForm.nameHorizontal" />
          	</font>
           </td>
           <td align="left" nowrap="nowrap">
            <font face="Arial" size="2">
         		<html:select property="nameLogoHorizontalAlign" size="1">
				  <html:option value="Left">Left</html:option>
				  <html:option value="Right">Right</html:option>
				  <html:option value="Center">Center</html:option>
				</html:select>
            </font>
           </td>
           
         </tr>
         
         <tr>
          <td align="left" nowrap="nowrap">
            <font face="Arial" size="2">
            	<bean:message key="sysConfigForm.adjustStockPrice" />
          	</font>
          </td>
         <td align="left" nowrap="nowrap">
          		<html:select property="adjustStockPrice" size="1">
             		<html:option value="n">No</html:option>
             		<html:option value="y">Yes</html:option>
           		</html:select>
          		
		 </td> 
		 <td align="left" nowrap="nowrap">
            <font face="Arial" size="2">
           		<bean:message key="sysConfigForm.percentage_change_in_share" />
          	</font>
          </td>
          <td align="left" nowrap="nowrap">
          	<font face="Arial" size="2">
          		<logic:present name="sysConfigBean">
            		<logic:equal value="0" property="percentage_change_in_share" name="sysConfigBean">
	            			<html:text property="percentage_change_in_share" size="12" maxlength="15" value=""  />
	            	</logic:equal>
	              	<logic:notEqual value="0" property="percentage_change_in_share" name="sysConfigBean">
	            			<html:text property="percentage_change_in_share" size="12" maxlength="15" name="sysConfigBean"  />
	            	</logic:notEqual>
            	</logic:present>	
          		(%)
            </font>
           </td>
        </tr> 
        
        <tr>
          <td align="left" nowrap="nowrap">
            <font face="Arial" size="2">
            	<bean:message key="sysConfigForm.language" />
          	</font>
          </td>
         <td align="left" nowrap="nowrap">
          		<html:select property="language" size="1" >
              			<html:optionsCollection name="sysConfigBean" property="languageCollection" />
            	</html:select>
         </td> 
		
		 <td align="left" nowrap="nowrap">
            <font face="Arial" size="2">
            	<bean:message key="indexUpdate.selectIndex" />
          	</font>
          </td>
         <td align="left" nowrap="nowrap">
          		<html:select  property="index_id" size="1"  >
              			<html:optionsCollection name="sysConfigBean" property="indexUpdateCollection" />
            		</html:select>	
         </td> 
          
        </tr> 
        
 <tr>
        	<td><font color="#D8D8D8">
        	t<font>
        	</td><td></td><td></td><td></td></tr>
        </table> 	
        <table>
        <tr>
	   	 	<td align="left" width="300">	   	 		
		    </td >
		    <td align="left">
		    		<html:submit property="saveButton" value="Submit" >
		    			<bean:message key="indexUpdate.save" />
		    		</html:submit>
	   	 			<html:reset property="resetButton" value="Reset" > 
	   	 				<bean:message key="indexUpdate.reset"/>
	   	 			</html:reset>
	   	 			<html:button onclick="history.go(-1)" property="">
         			<bean:message key="indexUpdate.cancel"/>
      			</html:button> 
			</td>
		</tr>	
		
	</table> 	
 </html:form>
  
    	<html:javascript formName="sysConfigBean" />
 <script language="JavaScript">
 
 function check2()
{
;
	if(document.forms[0].Submit.value = true)
	  window.document.forms[0].new1.checked = false;
}   	
	</script>
	</body>
	
    
 
</html:html>
