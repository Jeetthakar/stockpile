<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page language="java" import="app.*"%>
<%@ page import="harrier.income.com.report.*" %>
<%@ page import = "java.io.PrintWriter" %><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");
%>
<jsp:useBean id="ReportPerBean" scope="session" class="harrier.income.com.report.ReportPerForm"/>
<html:html>
<head>
		 	<html:base/>
			<title></title>
			<link href="StyleSheet.css" rel="stylesheet" type="text/css">
			<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
			<script language="javascript" src="box_ex.js"></script>
			<script language="javascript">
				var c2 = new CodeThatCalendar(caldef2);
			</script>
	</head>
	
<body onload="initialize()" >
<html:form action="/ReportPre">
	<html:errors/>
  <logic:equal name="ReportPerBean" value="yes" scope="session" property="hiddenVar">
      <logic:equal name="ReportPerBean" value="no" scope="session" property="hidVar">
     
	  	<font face="Arial" size="3" color="Red">
			<html:radio property="radioButton" name="ReportPerBean" value="continue" onclick="return rad_check()" /><bean:message key="Report.continue" /> 
			<html:radio property="radioButton" name="ReportPerBean" value="stop" onclick="return rad_check()"/><bean:message key="Report.stop" />
		</font>
		<jsp:setProperty name="ReportPerBean" property="hiddenVar" value="no"/>
	 </logic:equal>	
  </logic:equal>  
	
<!-- 	<logic:equal name="ReportPerBean" value="Update" scope="session" property="updateButton">
		<font face="Arial" size="3" color="Red">
			<html:radio property="radioButton" name="ReportPerBean" value="continue" onclick="return rad_check()" /><bean:message key="Report.continue" /> 
			<html:radio property="radioButton" name="ReportPerBean" value="stop" onclick="return rad_check()"/><bean:message key="Report.stop" />
		</font>
		<jsp:setProperty name="ReportPerBean" property="updateButton" value=""/>
	</logic:equal>          
-->
	 
<!-- 	<p align="left">  -->
	<table width="1000" cellspacing="0" cellpadding="0" > <!-- Table for Displaying Title -->
        		<tr>		          	
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap"> 
		          			<font size="3" face="Verdana">
		          				<b><bean:message key="Report.prefererance" /></b>
		          			</font>
		         	</td>
	          	</tr>
        	
	</table> 
	<p align="left">
      <table border="0" width="1000"  cellspacing="0" cellpadding="3"  > <!-- Selectbox for displaying the Index List -->

      <tr>
         	<td align="left" width="121%" nowrap="nowrap" >
        	<font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.Prefrence" />
        	</font>
        	</td>
        	<td align="left" nowrap="nowrap" width="771">
        	    <html:select size="1" property="selectprefrence" >  
			       		<html:optionsCollection name="ReportPerBean" property="prefrencecollection"  />
            	</html:select>
            	<font size="1" face="Arial" color="#0000FF">
            	
				<%--<a href="Continents.jsp?pr=t">--%>
				<a href="ReportPreName.jsp">
				<bean:message key="Masters.AddPreferences" /></a>&nbsp;&nbsp;&nbsp;&nbsp;
	      		<a href="RemoveReportPre.jsp">Remove Preferences</font>
            </td>          	
        </tr>
      
        <tr>
            <td align="left" width="121" nowrap="nowrap" >
        	<font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.select" />
        	</font>
        	</td>
        	<td align="left" nowrap="nowrap" width="771">
        	 <html:select size="1" property="selectReport" onchange="return final1();">  
			       		<html:optionsCollection name="ReportPerBean" property="indexCollection"  />
            	</html:select>
	      		
            </td>          	
        </tr>
     
     
     
      <tr>
			<td align="left" nowrap="nowrap" width="50" class="tab" >
					 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					 &nbsp;&nbsp;&nbsp;&nbsp;  
				<fieldset style="width: 202px; height: 52px; padding: 2">
				<legend><bean:message key="Date.opption"/></legend>
				<html:radio property="date" value="day"   onclick="check()"  /><bean:message key="Date.day"/>&nbsp;&nbsp; 
				<html:radio property="date" value="month"  onclick="check()" /><bean:message key="Date.month"/>&nbsp;&nbsp;
				<html:radio property="date" value="year"  onclick="check()" /><bean:message key="Date.year"/>
				</fieldset>
			</td>
			<td align="left" nowrap="nowrap" width="51">
			<html:select size="3" property="difference">  
	       		<html:optionsCollection name="ReportPerBean" property="diffCollection" />
    	     </html:select>
			</td>
	      		     
	  </tr>
	  <tr>
					
					<td nowrap="nowrap" align="left" width="121"><font size="2" color="black" face="Verdana">
	              		&nbsp;&nbsp;&nbsp;
	              		<bean:message key="Date.curent"/></font>
	            	</td> 
            		<td nowrap="nowrap"  align="left" width="50">
			           	<html:text property="to" size="10"  readonly="readonly">
                   		</html:text>
	            	</td>
		</tr>
		
		<html:hidden name="ReportPerBean" property="hideFilter" />
		<!--  this code takes care of hiding the filter option on selection of Index ... reports of report pref...-->	
		<logic:notEqual name="ReportPerBean" property="hideFilter" value="notHide" >
			<logic:notEqual value="selectReportVal" parameter="selectReportFlag">
			<logic:equal property="selectReport" name="ReportPerBean" value="null"> 
				<tr>						
	            	<td align="left" width="121" nowrap="nowrap" >
        	     	   <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.filter" />
        	        	</font>
        	        </td>
        	        <td align="left"  width="200" nowrap="nowrap" >
        	            <html:select size="1"  onchange="return final();"  property="filter" >  
			       		<html:optionsCollection name="ReportPerBean" property="filterCollection"  />
                        </html:select></td>
                    <td  nowrap="nowrap" align="left" width="121"><font size="2" color="black" face="Verdana">
                </tr>
             </logic:equal>
            </logic:notEqual>  		 
          </logic:notEqual>
          
          <!--  this code displays the Filter component on selection of report whose id is 14:Traded Volume -->
          <logic:equal property="selectReport" name="ReportPerBean" value="14"> 
          		<tr>						
	            	<td align="left" width="121" nowrap="nowrap" >
        	     	   <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.filter" />
        	        	</font>
        	        </td>
        	        <td align="left"  width="200" nowrap="nowrap" >
        	            <html:select size="1" property="filter" onchange="return final();" >  
			       		<html:optionsCollection name="ReportPerBean" property="filterCollection"  />
                        </html:select></td>
                    <td  nowrap="nowrap" align="left" width="121"><font size="2" color="black" face="Verdana">
                </tr>
          </logic:equal> 
          <!--  this code displays the Filter component on selection of report whose id is 15:Stock Dividend -->
          <logic:equal property="selectReport" name="ReportPerBean" value="15"> 
          		<tr>						
	            	<td align="left" width="121" nowrap="nowrap" >
        	     	   <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.filter" />
        	        	</font>
        	        </td>
        	        <td align="left"  width="200" nowrap="nowrap" >
        	             <html:select size="1" property="filter" onchange="return final();" >  
			       		<html:optionsCollection name="ReportPerBean" property="filterCollection"  />
                        </html:select></td>
                    <td  nowrap="nowrap" align="left" width="121"><font size="2" color="black" face="Verdana">
                </tr>
          </logic:equal> 
          
          
          <!--  this code takes care of hiding the filter option on selection of capital change of report pref...-->
          <logic:notEqual name="ReportPerBean" property="hideFilter" value="notHide" >
			<logic:notEqual value="capitalChangeVal" parameter="capitalChangeFlag">
			  <logic:notEqual value="selectReportVal" parameter="selectReportFlag">
				<logic:equal property="selectReport" name="ReportPerBean" value="null">
          		 <tr>						
	            	<td align="left" width="121" nowrap="nowrap" >
        	     	  <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.filter" />
        	        	</font>
        	        </td>
        	        <td align="left"  width="200" nowrap="nowrap" >
        	            <html:select size="1"  onchange="return final();"  property="filter" >  
			       		<html:optionsCollection name="ReportPerBean" property="filterCollection"  />
                        </html:select></td>
                    <td  nowrap="nowrap" align="left" width="121"><font size="2" color="black" face="Verdana">
                </tr>
               </logic:equal> 
             </logic:notEqual>
            </logic:notEqual>
              		 
          </logic:notEqual>
          
                
	          
	  <!--  this code takes care of hiding the exchange list option on selection of Index ... reports -->        
	 <logic:notEqual name="ReportPerBean" property="hideFilter" value="notHide" >
	   <logic:notEqual value="selectReportVal" parameter="selectReportFlag">
       	<logic:equal property="selectReport" name="ReportPerBean" value="null">
			<tr>	
	       	  <td align="left" width="221" nowrap="nowrap" >
        	   	 <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.exc" />
        	   	 </font>
        		</td>
        	   <td align="left" nowrap="nowrap" width="771" >
        	    
        	    <html:select size="1" property="exe" >  
			      <html:optionsCollection name="ReportPerBean" property="exeCollection"/>
                </html:select>&nbsp;&nbsp; <html:button property="view" value="view" onchange="return checkview()" > </html:button>
               </td>
          
	        </tr>
	       </logic:equal> 
        </logic:notEqual>
      </logic:notEqual>
      
      <!--  this code takes care of showing the exchange list option on selection of capital change -->      
    
      <logic:equal name="ReportPerBean" property="selectReport" value="5">
      		<tr>	
	       	  <td align="left" width="221" nowrap="nowrap" >
        	   	 <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.exc" />
        	   	 </font>
        		</td>
        	   <td align="left" nowrap="nowrap" width="771" >
        	    
        	    <html:select size="1" property="exe" >  
			      <html:optionsCollection name="ReportPerBean" property="exeCollection"/>
                </html:select>
               </td>
          
	       </tr>
      </logic:equal>
      
       <!--this code displays the exchange on selection of report Traded Volume (id:14) and exchange wise filter(id:2) -->   
       <logic:equal name="ReportPerBean" property="selectReport" value="14"> 
         <logic:equal name="ReportPerBean" property="filter" value="2"> 
        	<tr>	
	       	  <td align="left" width="221" nowrap="nowrap" >
        	   	 <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.exc" />
        	   	 </font>
        		</td>
        	   <td align="left" nowrap="nowrap" width="771" >
        	    
        	    <html:select size="1" property="exe" onchange="return onSelectionOfExchange();" >  
			      <html:optionsCollection name="ReportPerBean" property="exeCollection"/>
                </html:select>
               </td>
          
	       </tr>
      	</logic:equal>
      </logic:equal>
      <!--this code displays the exchange on selection of report Stock Dividend (id:15) and exchange wise filter(id:2) -->   
       <logic:equal name="ReportPerBean" property="selectReport" value="15"> 
        	<logic:equal name="ReportPerBean" property="filter" value="2"> 
        		<tr>	
	       	  <td align="left" width="221" nowrap="nowrap" >
        	   	 <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.exc" />
        	   	 </font>
        		</td>
        	   <td align="left" nowrap="nowrap" width="771" >
        	    
        	    <html:select size="1" property="exe" onchange="return onSelectionOfExchange();" >  
			      <html:optionsCollection name="ReportPerBean" property="exeCollection"/>
                </html:select>
               </td>
          
	       </tr>
      		</logic:equal>
      </logic:equal>
	       
	  <!--  this code takes care of hiding the index list on selection of not selected opt of report pref  -->   
	  <!--this code also hides the index for some reports those ids are 10,11,12,13,14,15--> 
	  <logic:notEqual name="ReportPerBean" property="hideFilter" value="notHide">    
		<logic:notEqual name="ReportPerBean" property="selectReport" value="10">
		<logic:notEqual name="ReportPerBean" property="selectReport" value="11">   
		<logic:notEqual name="ReportPerBean" property="selectReport" value="12">   
		<logic:notEqual name="ReportPerBean" property="selectReport" value="13"> 
		<logic:notEqual name="ReportPerBean" property="selectReport" value="14"> 
		<logic:notEqual name="ReportPerBean" property="selectReport" value="15">    
	 	           
	       <tr>	
	       	  <td align="left" width="221" nowrap="nowrap" >
        	   	 <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.index" />
        	   	 </font>
        		</td>
        	   <td align="left" nowrap="nowrap" width="771" >
        	    
        	    <html:select size="1" property="index" >  
			       <html:optionsCollection name="ReportPerBean" property="indCollection"/>
                </html:select>
               </td>
          
	       </tr>
	   </logic:notEqual>
	   </logic:notEqual>
	   </logic:notEqual>
	   </logic:notEqual>
	   </logic:notEqual>
	   </logic:notEqual>
      </logic:notEqual>
      <!--  this code takes care of showing the index list on selection of Stock Details of report pref  --> 
      <logic:equal value="stockDetailsVal" parameter="stockDetailsFlag">
      	   <tr>	
	       	  <td align="left" width="221" nowrap="nowrap" >
        	   	 <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.index" />
        	   	 </font>
        		</td>
        	   <td align="left" nowrap="nowrap" width="771" >
        	    <html:select size="1" property="index" > 
			       <html:optionsCollection name="ReportPerBean" property="indCollection"/>
                </html:select>&nbsp;&nbsp; <html:submit property="viewButton" value="View" onclick="return checkview() ;"/> 
               </td>
          
	       </tr>
      </logic:equal>
       <logic:notEqual value="stockDetailsVal" parameter="stockDetailsFlag">
          <logic:equal value="4" name="ReportPerBean" property="selectReport">
      	   <tr>
	       	  <td align="left" width="221" nowrap="nowrap" >
        	   <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.index" />
        	   	 </font>
        		</td>
        	   <td align="left" nowrap="nowrap" width="771" >
        	   
        	    <html:select size="1" property="index" >  
			   <html:optionsCollection name="ReportPerBean" property="indCollection"/>
                </html:select>&nbsp;&nbsp; <html:submit property="viewButton" value="View" onclick="return checkview() ;"/> 
               </td>
          
	       </tr>
        </logic:equal>	
      </logic:notEqual>
      
      <!-- start:  this code takes care of displaying the multiple selection index list for some report preference starts with Index... and there ids are 10,11,12,13 -->
      <logic:equal name="ReportPerBean" property="selectReport" value="10">    
	 	           
	       <tr>	
	       	  <td align="left" width="221" nowrap="nowrap" >
        	   	 <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.index" />
        	   	 </font>
        		</td>
        	   <td align="left" nowrap="nowrap" width="771" >
        	    
        	    <html:select size="4" property="indices" multiple="true">  
			      <html:optionsCollection name="ReportPerBean" property="indCollection"/>
                </html:select>
               </td>
          
	       </tr>
	   
      </logic:equal>
      <logic:equal name="ReportPerBean" property="selectReport" value="11">    
	 	           
	       <tr>	
	       	  <td align="left" width="221" nowrap="nowrap" >
        	   	 <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.index" />
        	   	 </font>
        		</td>
        	   <td align="left" nowrap="nowrap" width="771" >
        	    
        	      <html:select size="4" property="indices" multiple="true">   
			      <html:optionsCollection name="ReportPerBean" property="indCollection"/>
                </html:select>
               </td>
          
	       </tr>
	   
      </logic:equal>
      <logic:equal name="ReportPerBean" property="selectReport" value="12">    
	 	           
	       <tr>	
	       	  <td align="left" width="221" nowrap="nowrap" >
        	   	 <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.index" />
        	   	 </font>
        		</td>
        	   <td align="left" nowrap="nowrap" width="771" >
        	    
        	      <html:select size="4" property="indices" multiple="true">
			      <html:optionsCollection name="ReportPerBean" property="indCollection"/>
                </html:select>
               </td>
          
	       </tr>
	   
      </logic:equal>
      <logic:equal name="ReportPerBean" property="selectReport" value="13">    
	 	           
	       <tr>	
	       	  <td align="left" width="221" nowrap="nowrap" >
        	   	 <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.index" />
        	   	 </font>
        		</td>
        	   <td align="left" nowrap="nowrap" width="771" >
        	    
        	      <html:select size="4" property="indices" multiple="true">  
			    	  <html:optionsCollection name="ReportPerBean" property="indCollection"/>
                </html:select>
               </td>
          
	       </tr>
	   
      </logic:equal>
  	             
      
<!-- end:  this code takes care of displaying the multiple selection index list for some report preference starts with Index... and there ids are 10,11,12,13 -->
  
   <!--this code displays the index on selection of report Traded Volume (id:14) and index wise filter(id:1) -->   
       <logic:equal name="ReportPerBean" property="selectReport" value="14"> 
        	<logic:equal name="ReportPerBean" property="filter" value="1"> 
        		<tr>	
	       	  <td align="left" width="221" nowrap="nowrap" >
        	   	 <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.index" />
        	   	 </font>
        		</td>
        	   <td align="left" nowrap="nowrap" width="771" >
        	    
        	      <html:select size="1" property="index" onchange="return onSelectionOfIndex();">  
			    	  <html:optionsCollection name="ReportPerBean" property="indCollection"/>
                </html:select>
               </td>
          
	       </tr>
        	</logic:equal>  
       </logic:equal> 
       
    <!--this code displays the index on selection of report Stock Dividend (id:15) and index wise filter(id:1) -->   
       <logic:equal name="ReportPerBean" property="selectReport" value="15"> 
        	<logic:equal name="ReportPerBean" property="filter" value="1"> 
        		<tr>	
	       	  <td align="left" width="221" nowrap="nowrap" >
        	   	 <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.index" />
        	   	 </font>
        		</td>
        	   <td align="left" nowrap="nowrap" width="771" >
        	    
        	      <html:select size="1" property="index" onchange="return onSelectionOfIndex();" >  
			    	  <html:optionsCollection name="ReportPerBean" property="indCollection"/>
                </html:select>
               </td>
          
	       </tr>
        	</logic:equal>  
       </logic:equal> 
      	         
	  <!--  this code takes care of hiding the stock list on selection of Index ... reports  of report pref.-->  
	  <!-- this stock collection is not used here -->     
	 <logic:notEqual name="ReportPerBean" property="hideFilter" value="notHide" >    
	 	<logic:notEqual value="selectReportVal" parameter="selectReportFlag">         
       	 <logic:equal property="selectReport" name="ReportPerBean" value="null">
     	  <tr>
            <td align="left" width="221" nowrap="nowrap" >
        	   <%out.println("test--->1");%> <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.stock" />
        	    </font>
        	</td>
            
            <td align="left" nowrap="nowrap" width="771"  >
        		<html:select size="4" property="selectStock" >  
			    	<html:optionsCollection name="ReportPerBean" property="stockCollection"/>
           		</html:select>
            </td>
         </tr>
         </logic:equal>
       </logic:notEqual>
     </logic:notEqual>   
       
      <!--  this code takes care of displaying the stock collection on selection of index and clicking on view button-->  
	  
 	<logic:equal name="ReportPerBean" property="viewButton" value="View" >         
     	  <tr>
            <td align="left" width="221" nowrap="nowrap" >
        	  <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.stock" />
        	    </font>
        	</td>
            
            <td align="left" nowrap="nowrap" width="771"  >
			
        		<html:select size="4" property="selectedStocks" multiple="true">  
			    	<html:optionsCollection name="ReportPerBean" property="stockListOnIndexSelection"/>
           		</html:select>
           		
            </td>
         </tr>
       
     </logic:equal>   
     
     <!--  this code displays the stock list on selection of index for report traded volume(14) and Stock Dividend(15) -->
     
     
 	 <logic:equal name="ReportPerBean" property="selectReport" value="14"> 
        <logic:equal name="ReportPerBean" property="filter" value="1"> 
           	<logic:notEqual name="ReportPerBean" property="index" value='<%=""%>'>  
		     	  <tr>
		            <td align="left" width="221" nowrap="nowrap" >
		        	  <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.stock" />
		        	    </font>
		        	</td>
		            
		            <td align="left" nowrap="nowrap" width="771"  >
		
		        		<html:select size="1" property="stockOfIndex">  
					    	<html:optionsCollection name="ReportPerBean" property="stockListOnIndexSelection"/>
		           		</html:select>
		            </td>
		         </tr>
		     </logic:notEqual>  
       	</logic:equal>
     </logic:equal>
          
     <logic:equal name="ReportPerBean" property="selectReport" value="15"> 
        <logic:equal name="ReportPerBean" property="filter" value="1"> 
           	<logic:notEqual name="ReportPerBean" property="index" value='<%=""%>'>  
		     	  <tr>
		            <td align="left" width="221" nowrap="nowrap" >
		        	  <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.stock" />
		        	    </font>
		        	</td>
		            
		            <td align="left" nowrap="nowrap" width="771"  >
		
		        		<html:select size="1" property="stockOfIndex">  
					    	<html:optionsCollection name="ReportPerBean" property="stockListOnIndexSelection"/>
		           		</html:select>
		            </td>
		         </tr>
		     </logic:notEqual>  
       	</logic:equal>
     </logic:equal>  
     
   <!--  this code displays the stock list on selection of exchange for report traded volume(14) and Stock Dividend(15) -->   
     
      <logic:equal name="ReportPerBean" property="selectReport" value="14"> 
        <logic:equal name="ReportPerBean" property="filter" value="2"> 
        
           	<logic:notEqual name="ReportPerBean" property="exe" value='<%=""%>'>  
           
		     	  <tr>
		            <td align="left" width="221" nowrap="nowrap" >
		        	  <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.stock" />
		        	    </font>
		        	</td>
		        
		            <td align="left" nowrap="nowrap" width="771"  >
			 
		        		<html:select size="1" property="stockOfExchange">  
					    	<html:optionsCollection name="ReportPerBean" property="stockListOnExchangeSelection"/>
		           		</html:select>
		            </td>
		         </tr>
		     
		     </logic:notEqual>  
       	</logic:equal>
     </logic:equal>
          
     <logic:equal name="ReportPerBean" property="selectReport" value="15"> 
        <logic:equal name="ReportPerBean" property="filter" value="2"> 
        	<logic:notEqual name="ReportPerBean" property="exe" value='<%=""%>'>
           	 
		     	  <tr>
		            <td align="left" width="221" nowrap="nowrap" >
		        	  <font size="2" color="black" face="Verdana">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="Report.stock" />
		        	    </font>
		        	</td>
		            
		            <td align="left" nowrap="nowrap" width="771"  >
				
		        		<html:select size="1" property="stockOfExchange">  
					    	<html:optionsCollection name="ReportPerBean" property="stockListOnExchangeSelection"/>
		           		</html:select>
		            </td>
		         </tr>
		      
		     </logic:notEqual>   
       	</logic:equal>
     </logic:equal>  
     
     
       
      </table>
     
     <br> 
     &nbsp;&nbsp;&nbsp;&nbsp; <html:button property="button" value="Submit" onclick="return check1();"/>&nbsp;&nbsp;&nbsp;&nbsp;
       <html:submit property="resetButton"  value="Reset"/>&nbsp;&nbsp;&nbsp;&nbsp;
       
       <!--  update button will be displayed when user checks the continue radio button -->
       <logic:equal value="continue" parameter="radioButton">
      		 <html:submit property="updateButton" value="Update" onclick="return checkOnUpdate();"/>
      </logic:equal>
      
      <html:hidden property="fvalue" value="" />
      <html:hidden property="hiddenVar" value="no"/>
      <html:hidden property="hidVar" value="no"/>
     <html:hidden property="indicesHidVar" value="no"/>
      <html:hidden property="resetFilterHidVar" value="no"/>
      <html:hidden property="resetSomeObj" value="no"/>
     
     
      <logic:equal value="continue" parameter="radioButton">
          	
     	<font size="3" color="black" face="Verdana">
	     <br><br>         		
       	<font face="Arial" color="black" size="3">
    		 <b><bean:message key="existing.parameter"/></b>
		</font>
		<br>
	         <table>     
	         
	           	<logic:iterate id="ReportPreDetail" name="ReportPerBean" property="tableData">
	          		<tr>
					
    		   		<td class="gridStyle-odd" width="15%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
    				   	<font face="Arial" color="blue" size="2">
    				   		<b> <bean:write name="ReportPreDetail" property="filePaths1"/></b>
    				   		
						</font>
				  	</td>
				
				  	
	   		   		<td class="gridStyle-odd" width="15%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
    				   	<font face="Arial" color="blue" size="2">
    				   		<b> <bean:write name="ReportPreDetail" property="index_name1"/></b>
    				   		
						</font>
				  	</td>
				  	
				  	<td class="gridStyle-odd" width="15%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
    				   	<font face="Arial" color="blue" size="2">
    				   		<b> <bean:write name="ReportPreDetail" property="fdate"/></b>
    				   		
						</font>
				  	</td>
				  	
				  	<td class="gridStyle-odd" width="15%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
    				   	<font face="Arial" color="blue" size="2">
    				   		<b> <bean:write name="ReportPreDetail" property="tdate"/></b>
    				   		
						</font>
				  	</td>
				  	<td class="gridStyle-odd" width="15%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
    				   	<font face="Arial" color="black" size="2">
    				   		<b> <bean:write name="ReportPreDetail" property="list1"/></b>
    				   		
						</font>
				  	</td>
				  	<td class="gridStyle-odd" width="15%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
    				   	<font face="Arial" color="blue" size="2">
    				   		<b> <bean:write name="ReportPreDetail" property="list2"/></b>
    				   		
						</font>
				  	</td>
				  		  	
			   </tr>
			   
			 
			   </table>
			  
	          </logic:iterate>
	          
	         
	          <%
	          	session.setAttribute("ReportPerBean",null);
	          %>
	     	   
	     	    
	     	     
	     	  
     	
     </logic:equal>
       
     </html:form>
 
</body>
 
<script language="javascript">

function initialize() {
	
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
	//if((document.forms[0].from.value)=="")
	//document.forms[0].from.value= td+ s + mnth + s + yr;
	if((document.forms[0].to.value)=="")
	document.forms[0].to.value= td+ s + mnth + s + yr;
	initSort();
}	


function final()
{
   document.forms[0].submit();
   return true;
  
}
function final1()
{
	var selectedReport = document.forms[0].selectReport.value;
	if(selectedReport=='14'||selectedReport=='15')
	{
		document.forms[0].resetFilterHidVar.value="yes";
		
	}
	document.forms[0].resetSomeObj.value="yes";
	
  /* if(selectedReport=='14' ||selectedReport=='15'){
   		alert(document.forms[0].filter.value);
   		//document.forms[0].filter.value="0";
   }
   */
   document.forms[0].submit();
   return true;
  
  
}
function checkview()
{
	//var selrep = document.forms[0].selectReport.value;
	
	var selrep = document.forms[0].selectReport.value;
	var selectReport=document.forms[0].selectReport.value;
	document.forms[0].hidVar.value="yes";
	//alert("hid var is set yes");
	if(selectReport!= '0'){
		var selectIndex=document.forms[0].index.value;
		if(selectIndex==''){
			alert("Please select the Index field then click on \'View\' Button");
			return false;
		}
	}
	document.forms[0].submit();
		 return true;
	

	
	
/*	if((selrep=='1') || (selrep=='2') || (selrep=='3'))
	{
		alert("For Selecte Report only Index value Required");
		return false;
	}else{
		 document.forms[0].submit();
		 return true;
	}
*/	
	
}
function check()
{
 	
	document.forms[0].submit();

}
function check1()
{
    document.forms[0].hiddenVar.value="yes";
    
    // get the value of property for validation
	var selectPref=document.forms[0].selectprefrence.value;
	var selectReport=document.forms[0].selectReport.value;
	var dayDiff=document.forms[0].difference.value;
	//var toDate=document.forms[0].to.value;
	//var selectExch=document.forms[0].exe.value;
	
	
	if(selectPref=='0'){
		document.forms[0].hiddenVar.value="no";
		alert("Please select the Preference.");
		return false;
	}
	if(selectReport=='0'){
		document.forms[0].hiddenVar.value="no";
		alert("Please select the Report.");
		return false;
	}
	//alert("day diff---->"+dayDiff);
	
	
	if((selectReport != '6'))
	{
		document.forms[0].hiddenVar.value="yes";
		if(selectReport != '7')
		{
			document.forms[0].hiddenVar.value="yes";
			if(selectReport != '8')
			{
				document.forms[0].hiddenVar.value="yes";
				if(selectReport != '9')
				{	
				document.forms[0].hiddenVar.value="yes";
					if(dayDiff=='')
					{
						//alert("date-22--->"+selectReport);
						document.forms[0].hiddenVar.value="no";
						alert("Please select the \"From Date\" field.");
						return false;
					}
						
				}
			}	
		}
	}
	
	
	
	
	
/*	if(dayDiff=='')
	{
		//6:Index in Different Currency
		//7:Company Weightage
		//8:Industry Weightage
		//9:Stock Contribution To Change In Index
		alert("date---->"+selectReport);	
		if((selectReport!='6')
		{
			if(selectReport!='7')
			{
				if(selectReport!='8')
				{
					if(selectReport!='9'))
					{	
						alert("date-22--->"+selectReport);
						document.forms[0].hiddenVar.value="no";
						alert("Please select the \"From Date\" field.");
						return false;
						
					}
				}	
			}
		}
		
	}
	*/
	if(selectReport!= '0'){
		if(selectReport!='5')
		{
			// this is new addition
			if(selectReport!='10')
			{
				if(selectReport!='11')
				{
					if(selectReport!='12')
					{	
						if(selectReport!='13')
						{	
							if(selectReport!='14')
							{	
								if(selectReport!='15')
								{	
									var selectIndex=document.forms[0].index.value;
									if(selectIndex=='')
									{
										document.forms[0].hiddenVar.value="no";
										alert("Please select the Index field.");
										return false;
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	/*	if(selectExch==''){
		document.forms[0].hiddenVar.value="no";
		alert("Please select the Exchange/Index.");
		return false;
	}*/
	
	//var selrep = document.forms[0].selectReport.value;
	
	// this code takes care of validating the submit button for view button and stocks
	var selectReport2=document.forms[0].selectReport.value;
	//alert("gfhgh-----"+selectReport2);
	if(selectReport2=='4')
	{
		//alert("gfhgh---2--"+selectReport2);
		var selectIndexVar=document.forms[0].index.value;
		
		if(selectIndexVar!='')
		{	
				var viewButtonVar=document.forms[0].viewButton.value;
				if(viewButtonVar.toString()=='View')
				{
					//alert("view button-222-->"+viewButtonVar);
					selectedArr = document.forms[0].selectedStocks; 
					//alert(selectedArr);// comment by lokesh to restrict undefined alert
								
					if(selectedArr==undefined)
					{	
						document.forms[0].hiddenVar.value="no";
						alert("Please click on View Button and select the Stock(s)");
						return false;
					}
								
				}
			
			
		}
		
	}
	
	// this code validate the indices field for reports having ids->10,11,12,13
	var selectReport3=document.forms[0].selectReport.value;
	if(selectReport3=='10')
	{
		document.forms[0].indicesHidVar.value="yes";	

	}
	if(selectReport3=='11')
	{
		document.forms[0].indicesHidVar.value="yes";
	}
	if(selectReport3=='12')
	{
		document.forms[0].indicesHidVar.value="yes";
	}
	if(selectReport3=='13')
	{
		document.forms[0].indicesHidVar.value="yes";
	}
	
	// this code takes care of validating the report 14,and 15
	
	var selectReport4=document.forms[0].selectReport.value;	
	
	if(selectReport4=='14' ||selectReport4=='15' )
	{
		var filterVal=document.forms[0].filter.value;
		if(filterVal=='0')
		{
			document.forms[0].hiddenVar.value="no";
			alert("Please select Filter");
			return false;
		}
		else{
			if(filterVal=='1')//1:index wise
			{
				var indexVal=document.forms[0].index.value;
				if(indexVal==''){
					document.forms[0].hiddenVar.value="no";
					alert("Please select the Index");
					return false;
				}else{
					var stocksOfIndexVal=document.forms[0].stockOfIndex.value;
					if(stocksOfIndexVal==''||stocksOfIndexVal=='0')
					{
						document.forms[0].hiddenVar.value="no";
						alert("Please select the Stock");
						return false;
					}
					
				}
					
			}else if(filterVal=='2')//1:Exchange wise
			{
				var exchangeVal=document.forms[0].exe.value;
				if(exchangeVal==''){
					document.forms[0].hiddenVar.value="no";
					alert("Please select the Exchange");
					return false;
				}else{
					var stockOfExchangeVal=document.forms[0].stockOfExchange.value;
					if(stockOfExchangeVal=='0')
					{
						document.forms[0].hiddenVar.value="no";
						alert("Please select the Stock");
						return false;
					}
					
				}
					
			}
		}
		
	
	}
	
	document.forms[0].submit();
	return true;

}
function rad_check()
{
	document.forms[0].submit();
	return true;
}
function resetButtonClicked()
{	
	    document.forms[0].submit();
}
function checkOnUpdate(){
	//document.forms[0].hiddenVar.value="yes";
	var selectPref=document.forms[0].selectprefrence.value;
	var selectReport=document.forms[0].selectReport.value;
	var dayDiff=document.forms[0].difference.value;
	//var selectExch=document.forms[0].exe.value;
	
	if(selectPref=='0'){
		document.forms[0].hiddenVar.value="no";
		alert("Please select the Preference.");
		return false;
	}
	if(selectReport=='0'){
		document.forms[0].hiddenVar.value="no";
		alert("Please select the Report.");
		return false;
	}
	
/*	if(dayDiff==''){
		if(selectReport!='6')
		{
			document.forms[0].hiddenVar.value="no";
			alert("Please select the \"From Date\" field.");
			return false;
		}
	}
*/	

if((selectReport != '6'))
	{
		
		if(selectReport != '7')
		{
			
			if(selectReport != '8')
			{
				
				if(selectReport != '9')
				{	
				
					if(dayDiff=='')
					{
						//alert("date-22--->"+selectReport);
						document.forms[0].hiddenVar.value="no";
						alert("Please select the \"From Date\" field.");
						return false;
					}
						
				}
			}	
		}
	}



	if(selectReport!= '0'){
		
		var selectIndex=document.forms[0].index.value;
		if(selectIndex==''){
			document.forms[0].hiddenVar.value="no";
			alert("Please select the Index field.");
			return false;
		}
	}
	
	/*if(selectExch==''){
		document.forms[0].hiddenVar.value="no";
		alert("Please select the Exchange/Index.");
		return false;
	}*/
}
function onSelectionOfIndex()
{
	
	document.forms[0].submit();
	return true;
}
function onSelectionOfExchange()
{
	document.forms[0].submit();
	return true;
}
/*
function onSelectionOfPreference()
{
	document.forms[0].submit();
	return true;
}
*/

			
</script>	
</html:html>