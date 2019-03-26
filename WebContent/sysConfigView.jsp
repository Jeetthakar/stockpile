<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import="sysconfig.model.*,java.util.*, org.apache.struts.util.*" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>

<%
sysconfig.action.SysConfigForm configForm=new sysconfig.action.SysConfigForm();
String resetButtonValue=configForm.getResetButton();
%>

<html:html>

  <head>
    <title>
      <bean:message key="typeForm.title" />
    </title>
  </head>
  <body>
  	 <html:form action="/SysConfig-action" >
  	 
  	 <%
  		String button=request.getParameter("operation");
                if(button!=null && !(button.equals("Reset")))
		         {
 	%>
 <html:errors/>   
 	<% } %>
    	
    	 <table border="0" width="100%" bgcolor="#D8D8D8" cellspacing="0" cellpadding="3" height="406">
        <tr>
           <%
             		Vector options = new Vector(10);
					Vector time=new Vector(30,30);
					Vector stocksEx=new Vector(150,50);
					Vector countries=new Vector(300,100);
					Vector currencies=new Vector(200,50);
             		
             		SysConfig configModel=new SysConfig();
	        		options=configModel.getIndustryClassification();
	        		time=configModel.getTime();
	        		stocksEx=configModel.getStocksEx();
	        		currencies=configModel.getCurrencies();
	        		countries=configModel.getCountries();
			        pageContext.setAttribute("options", options);
			        pageContext.setAttribute("time",time);
					pageContext.setAttribute("stocksEx",stocksEx);
					pageContext.setAttribute("currencies",currencies);
					pageContext.setAttribute("countries",countries);
				
             %>
             
        </tr>
        <tr>
        <th align="left">
            <font face="Arial" size="2"><bean:message key="sysConfigForm.computeInterval" />
          	</font></th>
          <td align="left">
            <font face="Arial" size="2">
             <%
				String cInterval="";
				if(request.getParameter("computationInterval")!=null && !(button!=null && button.equals("Reset")) ) 
				{
						cInterval=request.getParameter("computationInterval");
              	}   %>
             <html:text property="computationInterval" size="15" maxlength="15" value="<%=cInterval%>"/>
          (in sec)  </font></td>
                   
          <th align="right">
           <font face="Arial" size="2">&nbsp&nbsp&nbsp&nbsp&nbsp; <bean:message key="sysConfigForm.intraDay" />
          </font></th>
          <td align="left">
            <font face="Arial" size="2">
            <%
				String archival=request.getParameter("intraDayArchivalInterval");
				if(archival!=null && !(button!=null && button.equals("Reset"))) 
				{
			 %>
            		<html:text property="intraDayArchivalInterval" size="15" maxlength="15" value="<%=archival%>"/>
             <%	}else{%>
            		<html:text property="intraDayArchivalInterval" size="15" maxlength="15" value=""/>
            <%}	%>
                     
           </font></td>
          
        </tr>
        <tr>
          <th align="left">
            <font face="Arial Narrow" size="2"><font face="Arial"><bean:message key="sysConfigForm.moniterRefreshRate" /></font>
          </font></th>
          <td align="left">
            <font face="Arial" size="2">
             <%
				String mRate=request.getParameter("monitorRefreshRate");
				if(mRate!=null && !(button!=null && button.equals("Reset"))) 
				{
			 %>
            		<html:text property="monitorRefreshRate" size="15" maxlength="15" value="<%=mRate%>" />
             <%	}else{%>
            		<html:text property="monitorRefreshRate" size="15" maxlength="15" value="" />
            <%}	%>
          		(in sec)</font></td>
          
           <th align="right">
           <font face="Arial" size="2">&nbsp&nbsp&nbsp&nbsp&nbsp; <bean:message key="sysConfigForm.industryClassification" />
          </font></th>
          <td align="left">
	          <font face="Arial" size="2">
	         <%
				if(button!=null && button.equals("Reset")) 
				{
			 %>
				 <html:select property="selectICID" size="1" >
               		 <html:options collection="options" property="value" labelProperty="label" /></font>
	          	</html:select>
			 <%	}else{%>
          		 <html:select property="industryClassificationId" size="1" >
               		 <html:options collection="options" property="value" labelProperty="label" /></font>
	          	 </html:select>
          	<%}	%>
        </td></tr>
        <tr>
          <th align="left">
            <font face="Arial" size="2"><bean:message key="sysConfigForm.precisionValue" />
          </font></th>
          <td align="left">
          <font face="Arial" size="2">
          	<%
				String pValue=request.getParameter("precisionValue");
				if(pValue!=null && !(button!=null && button.equals("Reset"))) 
				{
			 %>
            		<html:text property="precisionValue" size="15" maxlength="15" value="<%=pValue%>" />
             <%	}else{%>
            		<html:text property="precisionValue" size="15" maxlength="15" value=""/>
            <%}	%>
          		(in sec)</font></td>
                    
             <th align="right">
           <font face="Arial" size="2">&nbsp&nbsp&nbsp&nbsp&nbsp; <bean:message key="sysConfigForm.timeZone" />
          </font></th>
          <td align="left" width="100">
          	<font face="Arial" size="2">
			<%
				if(button!=null && button.equals("Reset")) 
				{
			 %>
				<html:select property="selectTID" size="1" >
               		 <html:options collection="time" property="value" labelProperty="label" /></font>
               	</html:select>
	          			
	          <%}else{%>
				<html:select property="timeZoneId" size="1" >
               		 <html:options collection="time" property="value" labelProperty="label" /></font>
               	</html:select>
	          		
			<%}	%>	
	       </font></td>
            
        </tr>
        <tr>
        </tr><tr>
          <th align="left">
            <font face="Arial" size="2"><bean:message key="sysConfigForm.ropf" />
          </font></th>
          <td align="left">
            <font face="Arial" size="2">
            	<%
				String rFeed=request.getParameter("rateOfPriceFeed");
				if(rFeed!=null && !(button!=null && button.equals("Reset"))) 
				{
			 	%>
            		<html:text property="rateOfPriceFeed" size="15" maxlength="15" value="<%=rFeed%>" />
             	<%	}else{%>
            		<html:text property="rateOfPriceFeed" size="15" maxlength="15" value=""/>
            	<%}	%>
            
            
            
          </font></td>
           <th align="right">
           <font face="Arial" size="2">&nbsp&nbsp&nbsp&nbsp&nbsp; <bean:message key="sysConfigForm.customerLogo" />
          </font></th>
          <td align="left">
          	<font face="Arial" size="2">
  		  		<html:file property="customerLogoPath" value="cPath" size="16"/></font></td>
           </tr>
        <tr>
          <th align="left">
            <font face="Arial" size="2"><bean:message key="sysConfigForm.customerName" />
          </font></th>
          <td align="left">
            <font face="Arial" size="2">
            <%
				String cName=request.getParameter("customerName");
				if(cName!=null && !(button!=null && button.equals("Reset"))) 
				{
			%>
				<html:text property="customerName" size="15" maxlength="15" value="<%=cName%>" />
			<%	}else{%>
				<html:text property="customerName" size="15" maxlength="15" value="" />
			<%} 
			%>
            
            
          </font></td>
           <th align="right">
           <font face="Arial" size="2">&nbsp&nbsp&nbsp&nbsp&nbsp; <bean:message key="sysConfigForm.dateFormat" />
          </font></th>
          <td align="left">
          	<font face="Arial" size="2">
          	<%
				String str=request.getParameter("operation");
				
				if(str!=null && str.equals("Save")) 
				{log.info("Enetr in reset========="+str);	
								
				 %>
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
          		</html:select> (Saperator)
          		
          		 <%	}else {
          		log.info("inside loop://///"+request.getParameter("date")); 
				log.info("Enetr in save========="+str);  
            %>
            	<html:select property="selectDate" size="1">
          			<html:option value="d">d</html:option>
            		<html:option value="dd">dd</html:option>
            		<html:option value="yy">yy</html:option>
          	   		<html:option value="yyyy">yyyy</html:option>
          	   		<html:option value="mm">mm</html:option>
          	    	<html:option value="mmm">mmm</html:option>
            	</html:select>
            	<html:select property="selectMonth" size="1">
          	    	<html:option value="mm">mm</html:option>
          	    	<html:option value="mmm">mmm</html:option>
          	    	<html:option value="d">d</html:option>
            		<html:option value="dd">dd</html:option>
            		<html:option value="yy">yy</html:option>
          	   		<html:option value="yyyy">yyyy</html:option>
          		</html:select>
          		<html:select property="selectYear" size="1">
          	    	<html:option value="yyyy">yyyy</html:option>
          	    	<html:option value="yy">yy</html:option>
          	    	<html:option value="d">d</html:option>
                	<html:option value="dd">dd</html:option>
                	<html:option value="mm">mm</html:option>
          	    	<html:option value="mmm">mmm</html:option>
           		</html:select>
           		<html:select property="selectValidate" size="1">
          	    	<html:option value="-">-</html:option>
          	    	<html:option value="/">/</html:option>
          		</html:select> (Saperator)
            <%}%>
               	 
            	
           
          	
        </tr>
        <tr>
          <th align="left">
            <font face="Arial" size="2"><bean:message key="sysConfigForm.namevertical" />
          </font></th>
          <td align="left">
            <font face="Arial" size="2">
            	<%
				str=request.getParameter("operation");
				if(str!=null && str.equals("Save")) 
				{
				%>
				<html:select property="nameLogoVerticalAlign" size="1">
				  <html:option value="Top">Top</html:option>
				  <html:option value="Bottom">Bottom</html:option>
				  <html:option value="Center">Center</html:option>
				</html:select>
				<%	}else{%>
				<html:select property="selectLVA" size="1">
				  <html:option value="Top">Top</html:option>
				  <html:option value="Bottom">Bottom</html:option>
				  <html:option value="Center">Center</html:option>
				</html:select>
				<%} 
				%>
        
          </font></td>
           <th align="right">
           <font face="Arial" size="2">&nbsp&nbsp&nbsp&nbsp&nbsp; <bean:message key="sysConfigForm.marketLot" />
          </font></th>
          <td align="left">
          	<font face="Arial" size="2">
          		<%
				String mLot=request.getParameter("marketLot");
				if(mLot!=null && !(button!=null && button.equals("Reset"))) 
				{
				%>
				<html:text property="marketLot" size="15" maxlength="15" value="<%=mLot%>"/>
				<%	}else{%>
				<html:text property="marketLot" size="15" maxlength="15" value=""/>
				<%} 
				%>
         
        </tr>
        <tr>
          <th align="left">
            <font face="Arial" size="2"><bean:message key="sysConfigForm.nameHorizontal" />
          </font></th>
          <td align="left">
            <font face="Arial" size="2">
            	<%
				str=request.getParameter("operation");
				if(str!=null && str.equals("Save")) 
				{
				%>
				<html:select property="nameLogoHorizontalAlign" size="1">
				  <html:option value="Left">Left</html:option>
				  <html:option value="Right">Right</html:option>
				  <html:option value="Center">Center</html:option>
				</html:select>
				<%	}else{%>
				<html:select property="selectLHA" size="1">
				  <html:option value="Left">Left</html:option>
				  <html:option value="Right">Right</html:option>
				  <html:option value="Center">Center</html:option>
				</html:select>
				<%} 
				%>
            
            
          </font></td>
           <th align="right">
           <font face="Arial" size="2">&nbsp&nbsp&nbsp&nbsp&nbsp; <bean:message key="sysConfigForm.stockExId" />
          </font></th>
          <td align="left">
          <font face="Arial" size="2">
          <%
				if(button!=null && button.equals("Reset")) 
				{
			 %>
				<html:select property="selectSEID" size="1" >
					<html:options collection="stocksEx" property="value" labelProperty="label" />
	       		</html:select></td></font>
	          			
	          <%}else{%>
				<html:select property="stockExId" size="1" >
					<html:options collection="stocksEx" property="value" labelProperty="label" />
	       		</html:select></td></font>
	          		
			<%}	%>	
         
        </tr>
        
        <tr>
          <th align="left">
            <font face="Arial" size="2"><bean:message key="sysConfigForm.maxComp" />
          </font></th>
          <td align="left">
          <font face="Arial" size="2">
          		<%
				String mComapnies=request.getParameter("maxNoOfCompanies");
				if(mComapnies!=null && !(button!=null && button.equals("Reset"))) 
				{
				%>
				<html:text property="maxNoOfCompanies" size="15" maxlength="15" value="<%=mComapnies%>"/>
				<%	}else{%>
				<html:text property="maxNoOfCompanies" size="15" maxlength="15" value=""/>
				<%} 
				%>
         
           <th align="right">
           <font face="Arial" size="2">&nbsp&nbsp&nbsp; <bean:message key="sysConfigForm.currencyId" />
          </font></th>
          <td align="left">
          <font face="Arial" size="2">
          	<%
				if(button!=null && button.equals("Reset")) 
				{
			 %>
			<html:select property="selectCUID" size="1" >
				<html:options collection="currencies" property="value" labelProperty="label" />
	       	</html:select>
	          			
	          <%}else{%>
			<html:select property="currencyId" size="1" >
				<html:options collection="currencies" property="value" labelProperty="label" />
	       	</html:select>
	          		
			<%}	%>	
         
	       </font></td>
        </tr>
        
        <tr>
          <th align="left">
     	</th></tr><tr>
          <th align="left">
            <font face="Arial" size="2"><bean:message key="sysConfigForm.alertPercentage" />
          </font></th>
          <td align="left">
          <font face="Arial" size="2">
          		<%
				String aPercentage=request.getParameter("alertPercentage");
				if(aPercentage!=null && !(button!=null && button.equals("Reset"))) 
				{
				%>
				<html:text property="alertPercentage" size="15" maxlength="15" value="<%=aPercentage%>"/>
				<%	}else{%>
				<html:text property="alertPercentage" size="15" maxlength="15" value=""/>
				<%} 
				%>
          
            (%)</font></td>
           <th align="right">
           <font face="Arial" size="2">&nbsp&nbsp&nbsp&nbsp&nbsp; <bean:message key="sysConfigForm.countryId" />
          </font></th>
          <td align="left">
          	<font face="Arial" size="2">
          	<%
				if(button!=null && button.equals("Reset")) 
				{
			 %>
			 	<html:select property="selectCOID" size="1" >
					<html:options collection="countries" property="value" labelProperty="label" />
		      	</html:select>
	          			
	          <%}else{%>
			 	<html:select property="countryId" size="1" >
					<html:options collection="countries" property="value" labelProperty="label" />
		      	</html:select>
	          		
			<%}	%>	
          
		      </font>
          	</td>
        </tr>
        
        <tr>
        </tr><tr>
          <th align="left">
            <font face="Arial" size="2"><bean:message key="sysConfigForm.rejectionPercentage" />
          </font></th>
          <td align="left">
          <font face="Arial" size="2">
          		<%
				String rPercentage=request.getParameter("rejectionPercentage");
				if(rPercentage!=null && !(button!=null && button.equals("Reset"))) 
				{
				%>
				<html:text property="rejectionPercentage" size="15" maxlength="15" value="<%=rPercentage%>"/>
				<%	}else{%>
				 <html:text property="rejectionPercentage" size="15" maxlength="15" value=""/>
				<%} 
				%>
          
         
            (%)</font></td>
           <th align="right">
           <font face="Arial" size="2">&#160; <bean:message key="sysConfigForm.dateDifference" />
          </font></th>
          <td align="left">
          	<font face="Arial" size="2">
          		<%
				String dd=request.getParameter("dateDifference");
				if(dd!=null && !(button!=null && button.equals("Reset"))) 
				{
				%>
				<html:text property="dateDifference" size="15" maxlength="15" value="<%=dd%>"/>(days)
				<%	}else{%>
				 <html:text property="dateDifference" size="15" maxlength="15" value=""/>(days)
				<%} 
				%>
          	
          </font></td>
        </tr>
          <th align="left">
            <font face="Arial" size="2"><bean:message key="sysConfigForm.adjustStockPrice" />
          	</font></th>
         	<td align="left">
          		<font face="Arial" size="2">
          		<%
				String adjustSp=request.getParameter("adjustStockPrice");
				if(adjustSp!=null && !(button!=null && button.equals("Reset"))) 
				{
				%>
				<html:text property="adjustStockPrice" size="15" maxlength="15" value="<%=adjustSp%>"/>
          				(y/n)</font></td>
				<%	}else{%>
				<html:text property="adjustStockPrice" size="15" maxlength="15" value=""/>
          				(y/n)</font></td>
				<%}%>
        
        	</tr> 
        
	   </p><td>
		       <br/>
		       <br/>
		       <br/>
		       <br/>
		        &#160&#160&#160&#160&#160&#160;<td align="left"><html:submit property="saveButton" value="Save  " onclick="return saveFunc()"/>
		        <input type="hidden" name="operation">
				&#160&#160&#160&#160&#160&#160;<html:submit property="resetButton" value="Reset" onclick="return resetFunc()"/> 
				
				
		  </html:form>
    
    </body>
    <script>
    function resetFunc(){
    	document.forms[0].operation.value="Reset";
    	return true;
    }
    function saveFunc(){
       	document.forms[0].operation.value="Save";
 		return true;
    }
     
    </script>
  
</html:html>
