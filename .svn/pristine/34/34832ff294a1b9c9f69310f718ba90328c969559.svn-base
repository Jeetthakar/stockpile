
<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.text.ParseException" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@page import="org.jfree.chart.demo.servlet.ComposeIndex"%>
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="app.*"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<html>
<head>
<html:base/>
</head>
<link href="../pages/StyleSheet.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script>

<form action="IndexDivisor.jsp">
<script type="text/javascript" src="./sorttable.js"></script>
         <style type="text/css">
/* Sortable tables */
table.sortable a.sortheader {
    background-color:#eee;
    font-size: 13px; 
    font-family: Arial;
    color: blue;
    text-decoration: none;
    display: block;
}
table.sortable span.sortarrow {
    color: black;
    text-decoration: none;
}
</style>
	
	 <table width="1000" cellspacing="0" cellpadding="0" >
        		<tr>
		          	<td width="300" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          			<font size="3" face="Arial">
		          				<b><bean:message key="IndexDivisor.title" /></b>
		          			</font>
		         	</td> 
	          </tr>
</table>
	

	 </p><p align="left">
         <form action="IndexDivisor.jsp">
         	<table border="0" width="100%"  cellspacing="0" cellpadding="3"  height="30">
        <tr>
        	<td align="left" width="115" nowrap="nowrap">
        		&nbsp;
          	</td>
          	
        	<td align="right" width="88" nowrap="nowrap" >
      <% 
	 		String mavg=request.getParameter("check_mavg");
	 		String chkchecked=request.getParameter("check");
	 %>
	 <% 
	     String pr = null;
         pr = request.getParameter("Pr");
	     try{
		    if(pr.equals(null))
    	       { pr = "N";}
        	   }catch(Exception e){pr = "N";}
           
           if(!pr.equals("Y"))
           { %>	       
        <font size="2" color="black" face="Arial"><bean:message key="LatestDivisor.iname" />
        </font>
        </td>
          	<td align="left" nowrap="nowrap" width="724">
        
        
      <%   } %>
      <%
        String fromdate = request.getParameter("from");
        String toDate  =request.getParameter("to");
    //    org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
      ComposeIndex ci = ConnectInit.getComposeIndex();
        ci.setVector_indexlist(chkchecked); 
         String var,var1,var3;
        var=request.getParameter("D1");
        var1=request.getParameter("D2");
        var3=request.getParameter("D3");
            
       	Vector v = ci.getVector_indexlist();    	
    	Iterator i=v.iterator();  
    	
	     %>   
      <% if(!pr.equals("Y"))
         { %>
        <select size="1" name="D1">
          <option value="0"><bean:message key="IndexCompose.option" /></option>
          
           <%    int requestID= 0;
                     try{
	                      requestID = Integer.parseInt(request.getParameter("D1"));
                     	}catch(Exception e){}
    
                     while(i.hasNext())
                      {   
                        int id = Integer.parseInt(i.next().toString());
                         if(id == requestID)
                          {%>
                        	<option selected value="<%=id%>"><%=i.next()%></option>
                       	<%}else { %>
 	                       	<option value="<%=id%>"><%= i.next() %></option>
                       <% }
						}     
                         %>
                        </select>      
              </td>
          	
        </tr>
        </table>           
        
        
        <!--second table-->
        <table border="0" width="100%"  cellspacing="0" cellpadding="3" height="50">
        <tr>
        	<td align="left" width="120" nowrap="nowrap">
        		&nbsp;
          	</td>
          	
        	<td align="left" width="32" nowrap="nowrap" >
        		<font face="Arial" size="2">
	 		
                <% if((request.getParameter("check"))!=null && (request.getParameter("check")).equals("checked")){ %>
	  				 <input type="checkbox" name="check" value="checked" onclick="return test1()" checked />&nbsp;
	  		   <% }else{ %>
	 			  <input type="checkbox" name="check" value="checked" onclick="return test1()"  />&nbsp;
				 <% } %>	
				  </td>
              <td align="left" nowrap="nowrap" width="141">  
              <font face="Arial" size="2">
				  	      	   
	 	 <bean:message key="IndexComparision.showsi" />
	 	 </font>
          	</td>
          	<td align="left" nowrap="nowrap" width="138">
            	<font face="Arial" size="2">
	 	<bean:message key="IndexDivisor.aispan" />
	 	</font>
          	</td>
          	<td align="left" width="57" nowrap="nowrap">
        <select size="1" name="D2">
          <option value="30">30</option>
          
           <%    int requestID1= 0;
                     try{
	                      requestID = Integer.parseInt(request.getParameter("D2"));
                     	}catch(Exception e){}
    
                    for(int span=1;span<30;span++)
                      {   
                        int id1 = span;
                         if(id1 == requestID)
                          {%>
                        	<option selected value="<%=id1%>"><%=id1%></option>
                       	<%}else { %>
 	                       	<option value="<%=id1%>"><%= id1 %></option>
                       <% }
						}     
                         %>          
                        </select>
                         </td>


          	<td align="left" nowrap="nowrap" width="91">
            	<font face="Arial" size="2">  
                       <bean:message key="IndexDivisor.schart" /> 
                        
                         </font>
             
             </td>
             <td align="left" nowrap="nowrap" width="328"> 
        	<select size="1" name="D3">
          		<%= ci.getChartList(var3)%>		
             </select> 
              	</td>

        </tr>
        </table>
                       
      <!--3 table-->                 
           <table border="0" width="100%"  cellspacing="0" cellpadding="3" >
        	<tr>
	        	<td align="left" width="120" nowrap="nowrap">
	        		&nbsp;
	          	</td>
          	
	        	<td align="left" width="32" nowrap="nowrap" >
	        		<font face="Arial" size="2">                      
	 	<% if((request.getParameter("check_mavg"))!=null && (request.getParameter("check_mavg")).equals("checked")){ %>
	  					 <input type="checkbox" name="check_mavg" value="checked"  checked />&nbsp;
	  		   <% }else{ %>
	 			  <input type="checkbox" name="check_mavg" value="checked"   />&nbsp;
				 <% } %>	
				  </td>
				 <td align="left" nowrap="nowrap" width="138">
            		<font face="Arial" size="2">
                 <bean:message key="IndexDivisor.vmavg" /> 				
				 </font>
			 	</td>
			 	<td align="left" nowrap="nowrap" width="70">
            		<font face="Arial" size="2">
         <%   if(fromdate==null){ %>
            <bean:message key="corporate.Fdate" />  </font>  </td>	<td align="left" nowrap="nowrap" width="78"> <input readOnly name="from" size="10">
            <% }else{ %>
             <bean:message key="corporate.Fdate" /> </font>  </td><td align="left" nowrap="nowrap" width="78"> <input readOnly name="from" value='<%=fromdate%>' size="10" >
                <% } %> 
                                </td>
                    <td   align="left" nowrap="nowrap" width="40">    
               
                           
                  <input onclick="c2.popup('from');" type="button" value="...">
                  </td>
                  
                  <td align="left" nowrap="nowrap" width="56">  
                   <font face="Arial" size="2">
                  <%   if(toDate==null){ %>    
                        <bean:message key="corporate.Tdate" /></font></td><td align="left" nowrap="nowrap" width="79"><font face="Arial" size="2">
                         <input readOnly name="to" size="10">
                       <% }else{ %>
                        <bean:message key="corporate.Tdate" /></font>   </td>      <td align="left" nowrap="nowrap" width="79">   <font face="Arial" size="2">
                                  <input readOnly name="to" value='<%=toDate%>' size="10">
                         <% } %> 
                                
                      </td>        
				<td align="left" nowrap="nowrap" width="39"> 
                                
                                
                                <input onclick="c2.popup('to');" type="button" value="...">
                      </td>
                      
                      
                       <td align="left" nowrap="nowrap" width="230">   
                      <input type="submit" value='<bean:message key="Reports.View"/>' name="B1" onclick="return viewFunc();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     </td>
                       </tr>
        </table>
		<% } %>
       <% 		if(var != null)
				{
					if(!pr.equals("Y")){
					try{
						String astr = null;
                           	astr = "./IndexDivisor.jsp?Pr=Y&D1="+var+"&from="+fromdate+"&to="+toDate+"&D2="+var1+"&D3="+var3; 
                        	String str_url = "../pages/EmailReport.jsp?switch_type=11&cas=11&rname=IndexMovement&varid="+var+"&from="+fromdate+"&to="+toDate;
                        	String temp_path = "../pages/FileDownload.jsp?var="+var+"&type=19&filename=IndexDivisor.xls&from="+fromdate+"&to="+toDate;
                        %>
                        </br>
                        <table width="900" >
                        	<tr>
                       		 <td width="575" nowrap="nowrap" align="right">
                        	<font size="1"><a href="javascript:popprinter('<%=astr%>');"><bean:message key="LatestDivisor.printerf" /></a></font>                        	</font>
                       			&nbsp;&nbsp;
							 <font size="1"><a href=<%= temp_path %>><bean:message key="LatestDivisor.downloade" /></a></font>
							 	&nbsp;&nbsp;
							 <font size="1"><a href= <%= str_url %>><bean:message key="LatestDivisor.emailr" /></a></font>
							
							</td>
						</tr>
					</table>
					
     <%					}catch(Exception e){log.info("refresh the page.");}
          			}
          		}		
     %>
     <% if(pr.equals("Y")){ %>
    <table border="0" width="100%"  cellspacing="0" cellpadding="3"  height="30">
       <tr>
        	<td align="left" width="43" nowrap="nowrap">
        		&nbsp;
          	</td>
        	<td align="left" width="844" nowrap="nowrap" >
     			<font face="Arial" size="2" ><b><bean:message key="LatestDivisor.iname" /></b></font>&nbsp;<font face="Arial" size="2" color="blue"><%= ci.getIndexName(var)%></font>
     		</td>
         </tr>
        <tr>
        	<td align="left" width="43" nowrap="nowrap">
        		&nbsp;
          	</td>
        	<td align="right" width="844" nowrap="nowrap" >
     <p align="left"><font size="2">
     	<font face="Arial" size="2" ><b><bean:message key="corporate.Fdate" /> &nbsp;&nbsp;&nbsp;&nbsp;:</b></font>&nbsp;<font face="Arial" size="2" color="blue"><%= fromdate%></font> &nbsp;&nbsp; &nbsp;&nbsp;<font face="Arial" size="2" ><b><bean:message key="corporate.Tdate" />:</b></font>&nbsp;<font face="Arial" size="2" color="blue"><%=toDate%></font>
     </p></font>
     </p><p align="left"> 
     </td>
         </tr>
         </table>   
     <% }else{ %>
     </p><p align="center"> 
     <% } %>
              </form>
           </td>
         </tr>
         </table>   
       
     <table  border="0" width="900" cellpadding="0" cellspacing="0">
  <tr>
	 <td width="120" nowrap="nowrap">&nbsp;</td>
	 <td width="780" nowrap="nowrap">	          
 <% 
	if(var==null || var.equals("0"))
	{
			
	
	%>
	<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
          <tr>
          <td  bgcolor="#84AADE" align="center" valign="middle">
              <p style="margin-left: 9"><font face="Arial" color="blue" size="5">
              <bean:message key="IndexCompareOHCL.messaged" /></a></font></p>
            </td>
            </tr>
            </table>
           
      <%  }else if(!(var3.equals("0"))){     %>
<%--     <% ci.DisplayChart("inddivisor",request,mavg,new PrintWriter(out));    --%>
<!--        String filename=ci.getFilename(); -->
<!--        String graphURL=ci.getGraphURL();  %> -->
<%--       <img src="<%= graphURL %>" width="700" height="500" border="0" usemap="#<%= filename %>"> --%>
   
<% } %>
	<p></p>
      <table border="0" width="100%"  cellpadding="2" cellspacing="0" >
	      <tr>
	      <td width="120" nowrap="nowrap">
	      &nbsp;
	      </td>
	      <td>
	       		<table border="1" width="60%" bgcolor="white"  align="left" cellpadding="2" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
	          	 		<tr>
	            		    <td width="15%" nowrap="nowrap" align="center"><bean:message key="IndexDivisor.trddate" /></td>
	            		    <td width="15%" nowrap="nowrap" align="center"><bean:message key="IndexCompareOHCL.close" /></td>
	            		    <td width="15%" nowrap="nowrap" align="center"><bean:message key="IndexDivisor.mcp" /></td>
	            		    <td width="15%" nowrap="nowrap" align="center"><bean:message key="indcurrwise.divisor" /></td>
	            			<!--<td width="15%" nowrap="nowrap" align="center">P/E</td>
	            			<td width="15%" nowrap="nowrap" align="center">P/B</td>
	                        <td width="15%" nowrap="nowrap" align="center">Dividend Yield</td>-->
	          			</tr>
	          	<%org.jfree.chart.demo.servlet.Indexwise_pe_pb ip= new org.jfree.chart.demo.servlet.Indexwise_pe_pb();
	          		 fromdate = request.getParameter("from");
       				 toDate  =request.getParameter("to");	
       				  String in  =request.getParameter("D1");
       				  log.info("im.getTradingDate(): "+fromdate+toDate+in);
	          		ArrayList arr=ip.getTableDate(in,fromdate,toDate,"indexDivisor");
	          		
					Object ci2 = null;
					session.setAttribute("ci2",new Vector(arr));
	          		Iterator itr=arr.iterator();
	          		int k=0;
	          		while(itr.hasNext()){
	          				sysconfig.model.indexMovement im = (sysconfig.model.indexMovement)itr.next();
	          				k++;
	          	%>		
	          			<tr>
	          				<td width="15%" nowrap="nowrap" align="center"><font face="Arial" color="black" size="2"><%=im.getTradingDate()%></td></font>
	            		    <td width="15%" nowrap="nowrap" align="right"><font face="Arial" color="black" size="2"><%=im.getClose()%></td></font>
	            		    <td width="15%" nowrap="nowrap" align="right"><font face="Arial" color="black" size="2"><%=im.getMCap()%></td></font>
	            		    <td width="15%" nowrap="nowrap" align="right"><font face="Arial" color="black" size="2"><%=im.getDivisor()%></td></font>
	            			<!--<td width="15%" nowrap="nowrap" align="right"><font face="Arial" color="black" size="2"><%=im.getPe()%></td></font>
	            			<td width="15%" nowrap="nowrap" align="right"><font face="Arial" color="black" size="2"><%=im.getPb()%></td></font>
	                        <td width="15%" nowrap="nowrap" align="right"><font face="Arial" color="black" size="2"><%=im.getDivYield()%></td></font>-->
	          			</tr>
	          			
	          			<%}
	          			if(k==0){%>
	          			</table>
		    							</td>
	    							</tr>
        						</table>
		     			<table border="0" width="100%"  cellpadding="2" cellspacing="0" align="left">
					       <tr>
	     					 <td width="120" nowrap="nowrap">
	      						&nbsp;
	     					 </td>
	      					 <td>
	       						<table border="1" width="60%" align="left" cellpadding="2" cellspacing="0" id="t1"  bordercolor="aliceblue" height="100">
				     			<tr>																																
			            			<td width="45%" bgcolor="white" align="center" valign="middle"><font face="Arial" color="black" size="6"><bean:message key="LatestDivisor.nodata" /> </font></td>
			            		</tr>
			            		</table>
			            	 </td>
			              </tr>
			             </table>
	          			<%}
	          			%>
          </td>
       </table>
</td>
</tr>
</table>
</td>
</tr>
</table>
<table border="0" width="100%" cellspacing="1" cellpadding="0">
  <tr>
    <td width="28%" valign="top">
      <p></p>
    </td>
    <td width="72%" valign="top">
      <p>
    </td>
  </tr>
</table>
</body>
<script language="javascript">
function test1(){
	document.forms[0].submit();
}
function viewFunc(){
	
	var i = 0;
	var fields = new Array();
	if(document.forms[0].D1.value=="0"){
		fields[i++] = "Index Name is required" ;
		
	}
	if(document.forms[0].from.value==""){
		fields[i++] = "From Date is required" ;
				
	}
	if(document.forms[0].to.value==0){
		fields[i++] = "To Date is required";
				
	}
	if (fields.length > 0) {
                   alert(fields.join('\n'));
                   return false;
         }
         else {
         	return true;
         }
}
function popprinter(url)
{
	newwindow=window.open(url,'name','height=500,width=900,scrollbars=yes,left=80,top=130,menubar=yes,status=yes');
	if (window.focus) {newwindow.focus()}
}
</script>
</html>