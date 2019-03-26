
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
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
			<script type="text/javascript" src="./sorttable.js"></script>
				<style type="text/css">	
				/* Sortable tables */
				table.sortable a.sortheader {
		    		background-color:#eee;
		   	 		font-size: 13px; 
		    		font-family: Arial;
		    		color: black;
		    		text-decoration: none;
		    		display: block;
				}
				table.sortable span.sortarrow {
		    		color: black;
		    		text-decoration: none;
				}
				</style>
	</head>
	<table width="1000" cellspacing="0" cellpadding="0" >
		<tr>
			<td width="250" class="subHeader" nowrap="nowrap">
					&nbsp;
			</td>
			<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
					<font size="3" face="Arial">
						<b><bean:message key="MovingIndex.title" /></b>
					</font>
			</td> 
		 </tr>
	</table>				
	
 	<html:form action="MovingIndexValue">   
 				<table border="0" width="100%"  cellspacing="0" cellpadding="3"  height="30">
    			 <tr>
    	   		 	<td align="left" width="102" nowrap="nowrap">
     	 	  				&nbsp;
       				</td>
        			<td align="right" width="88" nowrap="nowrap" >
    	    				 <font size="2" color="black" face="Arial">
    	    				 	<bean:message key="LatestDivisor.iname" />&nbsp;: 
    	    				 </font>
        			</td>
        			<td align="left" nowrap="nowrap" width="724">
	       					 <html:select property="selectIndex" size="1">
	        					   <html:optionsCollection property="indexCollection"/>
	       		 			 </html:select>
        			</td>
          	
      			 </tr>
		    </table>  
<!-- Secod Table for Chechk box of Sectoral Index, combo box of Avg. Interval Span , combo box of Select Chart: -->
		  
		    <table border="0" width="100%"  cellspacing="0" cellpadding="3" height="50">
		         <tr>
	            		<td align="left" width="102" nowrap="nowrap">
	        					&nbsp;&nbsp;
	          			</td>
	 					<td align="left" width="20" nowrap="nowrap" >
			        			 <font face="Arial" size="2">
			        				<html:checkbox property="sectorIndexCheck" onclick="test1()" /> 
			        			</font>	
		  		  		</td> 
		                <td align="left" nowrap="nowrap" width="141">  
		      			        <font face="Arial" size="2">
						  	       <bean:message key="IndexComparision.showsi" />
			 					</font>
	          			</td>
	         		 	<td align="left" nowrap="nowrap" width="30">
		            			<font face="Arial" size="2">
			 						<bean:message key="IndexDivisor.aispan" />
			 					</font>
	          			</td>
	          			<td align="left" width="57" nowrap="nowrap">
		          				 <html:select property="selectSpan" size="1">
		        					   <html:optionsCollection property="spanCollection"/>
		       		 			 </html:select>
	                    </td>
			         	<td align="left" nowrap="nowrap" width="20">
		        		    	<font face="Arial" size="2">  
		                			<bean:message key="IndexDivisor.schart" />  
		                        </font>
	                    </td>
	             		<td align="left" nowrap="nowrap" width="328">   
			             		<html:select property="selectChart" size="1">
			        					   <html:optionsCollection property="chartCollection"/>
			       		 		</html:select>           
	                    </td>
      		  	 </tr>
        	</table> 
<!-- Third Table for Chechk box of View Moving Average , text box for "from date" and "to date": and viwe button-->       
           <table border="0" width="100%"  cellspacing="0" cellpadding="3" height="10">
        		<tr>
			        	  <td align="left" width="120" nowrap="nowrap">
			        	    	&nbsp;
			              </td>
		          	  	  <td align="left" width="40" nowrap="nowrap" >
			        	    	<font face="Arial" size="2">    
			        	    		 <html:checkbox property="check_moving_avg"/> 
		       				    	 &nbsp;
		       				    </font>	 
			 			  </td>
						  <td align="left" nowrap="nowrap" width="140">
		               			<font face="Arial" size="2">
									 <bean:message key="IndexDivisor.vmavg" />
					 			</font>
					 	  </td>
					 	  <td align="left" nowrap="nowrap" width="90">
		            			<font face="Arial" size="2">
		          		     		 <bean:message key="corporate.Fdate" /> &nbsp;: 
		          		    	</font> 
		          		  </td>	
		          		  <td align="left" nowrap="nowrap" width="78"> 
		         				<html:text property="move_from" readonly="true"/>
		                  </td>
		                  <td align="left" nowrap="nowrap" width="35">    
		                      	<html:button property="from_button" onclick="c2.popup('move_from');" value="..." />
		                  </td>
		                  <td align="left" nowrap="nowrap" width="70">  
		                  	 	<font face="Arial" size="2">
		                    	      <bean:message key="corporate.Tdate" />&nbsp;:
		                  	 	</font>     
		                  </td>        
		                  <td align="left" nowrap="nowrap" width="79">      
		                  	 	<font face="Arial" size="2">
		                        	<html:text property="move_to" readonly="true"/>
		                        </font>   
		                  </td>        
						  <td align="left" nowrap="nowrap" width="39"> 
		                            <html:button property="to_button" onclick="c2.popup('move_to');" value="..." />
		                  </td>
		                  <td align="left" nowrap="nowrap" width="230">   
		                      	<html:submit onclick="go();" >
		                      		<bean:message key="Reports.View"/>
		                      	</html:submit>
		                  </td>
		                 
             	</tr>
           </table>
           <!-- if compute ="yes" display links -->
         <logic:equal  value="yes" parameter="compute" >	
				<table>
						<tr>
				 			<td  width="212" align="right" nowrap="nowrap"> 
			         		</td>
			         	  
					 		<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 			
				 					<a href="javascript:PrintThisPage();" >
				 						<font size="1" >
				 							<bean:message key="LatestDivisor.printerf" />
				 						</font>
				 					</a>&nbsp;
					 		   
				 					<a href="" >
				 						<font size="1" >
				 							<bean:message key="LatestDivisor.downloade" />
				 						</font>
				 					</a>&nbsp;
						 		
					 				<a href="" >
					 					<font size="1" >
					 						<bean:message key="LatestDivisor.emailr" />
					 					</font>
					 				</a>&nbsp;
					 		</td> 	
					  	</tr>
				</table>
		   </logic:equal> 
	  
		<!--   <logic:notEqual value="yes" parameter="compute" >
				 <table border="0" align="left" width="700" height="222" cellspacing="0" cellpadding="0">
	  				<tr>
        				<td width="99">
        				</td>
						<td class="gridStyle-message" align="center" valign="middle">
          					<p style="margin-left: 9">
          						<bean:message key="StockHighLow.smess" />
          					</p>
          				</td>
      				</tr>
      			  </table>
     	   </logic:notEqual>  -->
	    	<logic:equal  value="yes" parameter="varTableData" >
   	  		  
	  			<bean:size id="dataCount" name="MovingIndexValueBean" property="indexMovingMovingTable"/>
	  				<logic:equal value="0" name="dataCount" >
	  					<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
	  	  	 				 <tr>
			          			<td width="99">
			          			</td>
	  		  					<td class="gridStyle-message" align="center" valign="middle">
          							<p style="margin-left: 9">
          								<bean:message key="IndexCompareOHCL.ndata" />
          							</p>
          						</td>
        	  				</tr>
      	 				</table>
        			</logic:equal>
          	        <logic:notEqual value="0" name="dataCount">
      							<table border="1" width="100%" align="right" cellpadding="2" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
        							<tr>
<!-- Table Heading -->					<td nowrap="nowrap" align="center">
          									<bean:message key="IndexDivisor.trddate" />
          								</td>
          								<td align="center">
          									<bean:message key="IndexCompareOHCL.close" />
          								</td>
		        						<td align="center">
		        							<bean:message key="MovingIndex.mcapimil" />
		        						</td>
        								<td nowrap="nowrap" align="center">
        									<bean:message key="indcurrwise.divisor" />
        								</td>
          							</tr>   		
<!-- Iterate over the table data -->
          							<logic:iterate id="details" property="indexMovingMovingTable" name="MovingIndexValueBean">
							          	<tr>
							     			<td valign="middle" bgcolor="white" align="left" >
							        			<font face="Arial" color="blue"><bean:write name="details" property="tradingDate"/></font>
							          		</td>
							          		<td align="right" bgcolor="white" valign="middle">
							          			<font face="Arial" color="blue"><bean:write name="details" property="close"/></font>
							          		</td>
							          		<td bgcolor="white" align="right" valign="middle">
							          			<font face="Arial" color="blue"><bean:write name="details" property="mCap"/></font>
							          		</td>
							          		<td bgcolor="white" align="right" valign="middle">
							          			<font face="Arial" color="blue"><bean:write name="details" property="divisor"/></font>
							         	 	</td>
							       		</tr>
    								</logic:iterate>
          						</table> 
        			</logic:notEqual>
   			</logic:equal>
            <html:hidden property="varTableData" value="no"></html:hidden> 
   </html:form>	
<!-- javascript for this JSP page -->
   <script language="javascript">
			function test1(){
				document.forms[0].submit();
				
			}
			function viewFunc(){
				//alert(document.forms[0].D1.value);
				if(document.forms[0].D1.value==0){
					alert("Index Name is required");
					return false;
				}
				else if(document.forms[0].from.value==""){
					alert("From Date is required");
							return false;
				}
				else if(document.forms[0].to.value==0){
					alert("To Date is required");
							return false;
				}
				else return true;
				//alert(document.forms[0].from.value);
				//alert(document.forms[0].to.value);
			}
			function popprinter(url){
				
				newwindow=window.open(url,'name','height=500,width=900,scrollbars=yes,left=10,top=30,menubar=yes,status=yes');
				if (window.focus) {
				//newwindow.document.write(url);
				newwindow.focus()}
			}
			function go(){
			 	alert('INside go()');
				document.forms[0].varTableData.value="yes";
				
			}
	</script>	
</html:html>    