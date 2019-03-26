<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.text.ParseException" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@ page import = "java.lang.*" %>
<%@ page import = "java.text.DecimalFormat"%>
<%@page import="org.jfree.chart.demo.servlet.ComposeIndex"%>
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@page import="org.jfree.chart.demo.servlet.AdjustDecimal"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.util.*" %>
<%@ page language="java" import="app.*"%><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
			LogonForm form = null;
			if(request.isRequestedSessionIdValid())	{	
				form = (LogonForm)session.getAttribute("user");
			}
			if(form==null ||(!request.isRequestedSessionIdValid())){
				response.sendRedirect("userlogintemp.jsp");
			}
%>
<html >
<head>
	<title>Change In Stock Details</title>
    <style fprolloverstyle>A:hover {color: red; font-weight: bold}
</style>
</head>
<link href="StyleSheet.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script>
	<table width="1000" cellspacing="0" cellpadding="0" >
        		<tr>
		          	<td width="250" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          			<font size="3" face="Arial">
		          				<b><bean:message key="ChangesDetail.title" /></b>
		          			</font>
		         	</td> 
	          </tr>
	</table> 
	<p></p>
<form action="ChangeInStockDetail.jsp">
	<table width="656">
         	<tr>
	  			<td width="212" nowrap="nowrap" align="right">
				   <font size="2" face="Arial">
	 					 <bean:message key="ChangesDetail.mktexhname" />&nbsp;:
	 			  </font>
        		</td> 
	 <%
	 		 log.info("before firing query");
      //  org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
       // org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
        ComposeIndex ci = ConnectInit.getComposeIndex();
        AdjustDecimal ad = ConnectInit.getAdjustDecimal();
        org.jfree.chart.demo.servlet.FieldSort sort=new org.jfree.chart.demo.servlet.FieldSort();
       String var; 
        String var3;       
              
        String str=request.getParameter("refFlag");
        log.info("refFlag is.........."+str);
			String sid="0";
			int requestID3= 0;
			if(str!=null && str.equals("1")){
				 try{
				requestID3=Integer.parseInt(ci.reset_stkevent());
				}catch(Exception e){}
				var3=ci.reset_stkevent(); 
			}	
			else{
        		  try{
	                      requestID3 = Integer.parseInt(request.getParameter("D3"));
                     	}catch(Exception e){}
                     	var3=request.getParameter("D3"); 
          
        	} 
        	var=request.getParameter("D1");  
       
        log.info("1");       
       
         ci.setVector_ExchangeList(); 
    	Vector v3 = ci.getVector_ExchangeList();    	
    	Iterator i3=v3.iterator(); 
    	
	 %>
	 <td width="434" nowrap="nowrap" align="left" height="30">
	 <select size="1" name="D3" onchange="document.forms[0].submit();return true">
          <option value="0"><bean:message key="ChangesDetail.exhname" /></option>
          
           <%    
                    
                     while(i3.hasNext())
                      {   
                        int id3 = Integer.parseInt(i3.next().toString());
                         if(id3 == requestID3)
                          {%>
                        	<option selected value="<%=id3%>"><%=i3.next()%></option>
                       	<%}else { %>
 	                       	<option value="<%=id3%>"><%= i3.next() %></option>
                       <% }
						}      
                         %>
                        </select> 
         </td>
         	</tr>
         </table> 
		<table width="656">
		 <tr>
	  			<td width="212" nowrap="nowrap" align="right">
				   <font size="2" face="Arial"><bean:message key="stockmaster.stockName" />&nbsp;:
				    </font>
        		</td> 
      <%
        log.info("1");       
        
         String fromdate = request.getParameter("from");
        String toDate  =request.getParameter("to");   
         ci.setVector_stocklistexcwise(var3); 
    	Vector v2 = ci.getVector_stocklistexcwise();     	
    	Iterator i2=v2.iterator();  
    	//var=request.getParameter("D1"); 
	     %>          
        <td width="434" nowrap="nowrap" align="left" height="30">
        <select size="1" name="D1">
          <option value="0"><bean:message key="IndexCompose.option" /></option>
          
           <%                        
                        int requestID= 0;
                     try{
	                      requestID = Integer.parseInt(request.getParameter("D1"));
                     	}catch(Exception e){}
    
                     while(i2.hasNext())
                      {   
                        int id = Integer.parseInt(i2.next().toString());
                         if(id == requestID)
                          {%>
                        	<option selected value="<%=id%>"><%=i2.next()%></option>
                       	<%}else { %>
 	                       	<option value="<%=id%>"><%= i2.next() %></option>
                       <% }
						}
                         %>
                            </select> 
         </td>
         	</tr>
         </table>       
     <table width="894" height="40">
         	<tr>
         		<td width="212" align="right" nowrap="nowrap">
         		<font size="2" face="Arial">
            		 <bean:message key="corporate.Fdate" />&nbsp;:
            	</font>
             </td> 
             <td width="80" nowrap="nowrap">
		<%	 if(fromdate==null){ %>		<input readOnly name="from" size="10"> <%}else{%> 
			<input readOnly name="from" value="<%=fromdate%>" size="10">  <% } %>
                                <td width="36" nowrap="nowrap">  
                                	<input onclick="c2.popup('from');" type="button" value="...">
                         		</td>
                              	<td width="53" nowrap="nowrap"><font size="2" face="Arial">
                          			<bean:message key="corporate.Tdate" />&nbsp;: 
                          		</td>
                           		<td width="78" nowrap="nowrap"> 
                         <%	 if(toDate==null){ %>	<input readOnly name="to" size="10">  <% }else{ %> <input readOnly name="to" value="<%=toDate%>" size="10">  <% } %>
                               </td>
                                <td width="38" nowrap="nowrap">
                               <input onclick="c2.popup('to');" type="button" value="...">
                               </td>
                                 <td width="90" nowrap="nowrap">
            							<font size="2" color="black" face="Arial">
            							<bean:message key="ChangesDetail.selcrit" />&nbsp;:
            					  </font>
            					  </td>
      <%             
        log.info("Inside setVector_stockchangeTypelist()");
        ci.setVector_stockchangeTypelist();
       	Vector v1 = ci.getVector_stockchangeTypelist();    	
    	Iterator i1=v1.iterator();  
    	
	     %>   
                <td width="152" nowrap="nowrap">
                <select size="1" name="D2">
          <option value="0"><bean:message key="ChangesDetail.selcrit" /></option>
          
           <%       
                        String requestID1= "";
                     try{
	                      requestID1 = request.getParameter("D2");
                     	}catch(Exception e){}
    
                     while(i1.hasNext())
                      {   
                        String criteria=(String)i1.next();
                         if(criteria.equals(requestID1))
                          {%>
                        	<option selected value="<%=criteria%>"><%=criteria%></option>
                       	<%}else { %>
 	                       	<option value="<%=criteria%>"><%= criteria %></option>
                       <% }
						} 
                         %>
                        </select>
                        </td>
                        <td width="120" nowrap="nowrap">
                      			<input type="submit" value="View" name="B1" onclick="return validation();">
                      	 </td>
          </tr>
          </table> 
          <p></p>   
				

 <table  border="0" width="900" cellpadding="0" cellspacing="0">
  <tr>
	 <td width="150" nowrap="nowrap">&nbsp;</td>
	 <td width="770" nowrap="nowrap">
<%	log.info(var); 
if(var==null)
	{
	%>
	 <table border="0" align="left" class="gridStyle" width="631" height="222" cellspacing="0" cellpadding="0">
          <tr>
          <td  class="gridStyle-message" align="center" valign="middle">
              <p style="margin-left: 9"><b> <bean:message key="ChangesDetail.selapar" /></b></p>
            </td>
            </tr>
            </table>
      <% }else{ 
      			 log.info("1");       
        String var1,field,col;
        
        var1=request.getParameter("D2");
         int fieldno,colno;
        field=(String)request.getParameter("FieldNo");
         col=(String)request.getParameter("ColNo");
       if(var1.equals("market_lot"))
       {
        if(col==null)
        {
        	col="4";
        }       
        if(field==null)
        {
        	field="3";
        }
        }else{if(col==null)
       		 {
        			col="3";
       		 }       
       		 if(field==null)
        		{
        			field="2";
       			 }
        }
        fieldno=Integer.parseInt(field);
        colno=Integer.parseInt(col);
				ci.setVectorstockchangeDetail(var,fromdate,toDate);
       		 Vector v=ci.getVectorstockchangeDetail(); 
       %>
				<% if((v.size()==0)||(var1.equals("stock_name")))
	{
	%>
	<table border="0" align="left" class="gridStyle" width="631" height="222" cellspacing="0" cellpadding="0">
          <tr>
          <td  class="gridStyle-message" align="center" valign="middle">
              <p style="margin-left: 9"><font face="Arial" color="blue" size="5">
              <bean:message key="IndexCompareOHCL.ndata" /></a></font></p>
            </td>
            </tr>
            </table>
      <% }else{ %>
        <p></p>
        <table  border="0" width="900" cellpadding="0" cellspacing="0">
  		<tr>
	 		<td width="100" nowrap="nowrap">&nbsp;</td>
	 		<td width="770" nowrap="nowrap" align="left">
         		<font size="2"  face="Arial"><b>
        			<bean:message key="ChangesDetail.chgsdet" />&nbsp; <%=var1 %></b>
         		</font>
        	</td>
        </tr>
        </table>
       	
       <!-- <table border="0" width="75%"  cellspacing="0" cellpadding="0"> -->
        <table border="0" width="55%" class="gridStyle" align="left" cellpadding="2" cellspacing="0" >
          <tr>
           <% 
          		if(var1.equals("market_lot"))
       			{
       			%>
            <td width="25%" class="gridStyle-header" align="center" valign="middle"><a href="ChangeInStockDetail.jsp?D3=<%=var3%>&D1=<%=var%>&D2=<%=var1%>&from=<%=fromdate%>&to=<%=toDate%>&B1=View&FieldNo=0&ColNo=4" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2"><bean:message key="stockmaster.stockName" /></a></font></td>
            <% }else{ %>
            <td width="25%" class="gridStyle-header" align="center" valign="middle"><a href="ChangeInStockDetail.jsp?D3=<%=var3%>&D1=<%=var%>&D2=<%=var1%>&from=<%=fromdate%>&to=<%=toDate%>&B1=View&FieldNo=0&ColNo=3" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2"><bean:message key="stockmaster.stockName" /></a></font></td>
            <% } %>
            <%
            log.info("Inside iwf if loop");
             log.info(var1);
            if(var1.equals("iwf"))
       		{ 
       			v=ci.getVectorchangeInStockDetail(v,1);
       			log.info("Inside iwf if loop");
       		%>
            <td width="15%" class="gridStyle-header" align="right" valign="middle"><a href="ChangeInStockDetail.jsp?D3=<%=var3%>&D1=<%=var%>&D2=<%=var1%>&from=<%=fromdate%>&to=<%=toDate%>&B1=View&FieldNo=1&ColNo=3" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2"><bean:message key="ChangesDetail.iwf" /></font></a></td>
             <% }         		
             		if(var1.equals("face_value"))
       				{ 
       				v=ci.getVectorchangeInStockDetail(v,3);
       			%>
            	<td width="15%" class="gridStyle-header" align="right" valign="middle"><a href="ChangeInStockDetail.jsp?D3=<%=var3%>&D1=<%=var%>&D2=<%=var1%>&from=<%=fromdate%>&to=<%=toDate%>&B1=View&FieldNo=1&ColNo=3" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2"><bean:message key="stockmaster.faceValue" /></font></a></td>
             	<% }             	
             	if(var1.equals("growth_or_value"))
       				{
       				v=ci.getVectorchangeInStockDetail(v,6);
        		%>
        		<td width="15%" class="gridStyle-header" align="right" valign="middle"><a href="ChangeInStockDetail.jsp?D3=<%=var3%>&D1=<%=var%>&D2=<%=var1%>&from=<%=fromdate%>&to=<%=toDate%>&B1=View&FieldNo=1&ColNo=3" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2"><bean:message key="ChangesDetail.gwtval" /></font></a></td>
         	  <% }         	  	
           		if(var1.equals("global100"))
       				{
       				v=ci.getVectorchangeInStockDetail(v,8);
           		 %>
            	<td width="15%" class="gridStyle-header" align="right" valign="middle"><a href="ChangeInStockDetail.jsp?D3=<%=var3%>&D1=<%=var%>&D2=<%=var1%>&from=<%=fromdate%>&to=<%=toDate%>&B1=View&FieldNo=1&ColNo=3" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2"><bean:message key="ChangesDetail.global" /></font></a></td>
          		  <% }
          		 if(var1.equals("rating_code"))
       				{
       				v=ci.getVectorchangeInStockDetail(v,7);
           		 %>
            	<td width="15%" class="gridStyle-header" align="right" valign="middle"><a href="ChangeInStockDetail.jsp?D3=<%=var3%>&D1=<%=var%>&D2=<%=var1%>&from=<%=fromdate%>&to=<%=toDate%>&B1=View&FieldNo=1&ColNo=3" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2"><bean:message key="stockmaster.ratingCode" /></font></a></td>
          		 <% }
          		if(var1.equals("market_lot"))
       			{
       				  log.info("before calling method");
       				 v=ci.getVectorchangeInStockDetail1(v,4,9);
       				
           		 %>
            	<td width="8%" class="gridStyle-header" align="right" valign="middle"><a href="ChangeInStockDetail.jsp?D3=<%=var3%>&D1=<%=var%>&D2=<%=var1%>&from=<%=fromdate%>&to=<%=toDate%>&B1=View&FieldNo=1&ColNo=4" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2"><bean:message key="stockmaster.mktLot" /></font></a></td>
            	<td width="15%" class="gridStyle-header" align="right" valign="middle"><a href="ChangeInStockDetail.jsp?D3=<%=var3%>&D1=<%=var%>&D2=<%=var1%>&from=<%=fromdate%>&to=<%=toDate%>&B1=View&FieldNo=2&ColNo=4" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2"><bean:message key="ChangesDetail.pricefmlot" /></font></a></td>
          	 <% }%>
          	  <% 
          		if(var1.equals("market_lot"))
       			{
       			%>
            <td width="15%" class="gridStyle-header" align="right" valign="middle"><a href="ChangeInStockDetail.jsp?D3=<%=var3%>&D1=<%=var%>&D2=<%=var1%>&from=<%=fromdate%>&to=<%=toDate%>&B1=View&FieldNo=3&ColNo=4" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2"><bean:message key="corporate.Date" /></a></font></td>
            <% }else{ %>
            <td width="15%" class="gridStyle-header" align="right" valign="middle"><a href="ChangeInStockDetail.jsp?D3=<%=var3%>&D1=<%=var%>&D2=<%=var1%>&from=<%=fromdate%>&to=<%=toDate%>&B1=View&FieldNo=2&ColNo=3" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2"><bean:message key="corporate.Date" /></a></font></td>
          	<% } %>
          </tr>
       <%	
       	   if(var1.equals("market_lot"))
       	   {
       	   		if(fieldno==0)
          		{
          			v=sort.SetOrderSort(v,0,4);
          		}
         		 if(fieldno==1)
         		 {
          			v=sort.SetOrderSortNo(v,1,4);
         		 }
         		 if(fieldno==3)
         		 {
          			v=sort.SetOrderSortDate(v,2,4);
         		 }
         		 
         		
       	   }else{
       	   if(fieldno==0)
          {
          	v=sort.SetOrderSort(v,0,3);
          }
          if(fieldno==1)
          {
          	v=sort.SetOrderSortNo(v,1,3);
          } 
          if(fieldno==2)
          {
         		v=sort.SetOrderSortDate(v,2,3);
          }        
          }            
          Iterator i=v.iterator(); 
          int count=0;
           while(i.hasNext())
           {  
           		count++;
           		if(count%2!=0)
           		{           
          %>
   			 <tr>
            <td width="25%" height="20" align="left" class="gridStyle-odd">
              <font face="Arial" color="blue" size="1"><%= i.next() %></font>
            </td>
             <% 
          		if(var1.equals("market_lot"))
       			{
       			%>
            <td width="15%" align="right" class="gridStyle-odd">
              <font size="1" face="Arial"><%= i.next() %></font>
            </td>
            <td width="15%" align="right" class="gridStyle-odd">
              <font size="1" face="Arial"><%= i.next() %></font>
            </td>
            <% }else{ %>
            <td width="15%" align="right" class="gridStyle-odd">
              <font size="1" face="Arial"><%= i.next() %></font>
            </td>
            <% } %>
            <td width="15%" align="right" class="gridStyle-odd">
            <font size="1" face="Arial"><%= i.next() %> </font>
            </td>
         </tr>
          
          <% }else{ %>
          <tr>
            <td width="35%" height="20" class="gridStyle-even"  align="left">
              <font face="Arial" color="blue" size="1"><%= i.next() %> </font>
            </td>
            <% 
          		if(var1.equals("market_lot"))
       			{
       			%>
            <td width="15%" align="right" class="gridStyle-even">
              <font size="1" face="Arial"><%= i.next() %></font>
            </td>
            <td width="15%" align="right" class="gridStyle-even">
              <font size="1" face="Arial"><%= i.next() %></font>
            </td>
            <% }else{ %>
            <td width="15%" align="right" class="gridStyle-even">
              <font size="1" face="Arial"><%= i.next() %></font>
            </td>
            <% } %>
            <td width="20%" class="gridStyle-even" align="right">
            <font size="1" face="Arial"><%= i.next() %> </font>
            </td>
         </tr>
         <% }
         } %>
         <% }
        } %>         
         </table>
  </td>
  </tr>
  </table>
   </form>

<table border="0" width="98%" cellspacing="1" cellpadding="0">
  <tr>
    <td width="28%" valign="top">
     </td>
    <td width="72%" valign="top">
     </td>
  </tr>
</table>
</form>
<script language="javascript">
function validation(){
	if(document.forms[0].D2.value=="0"){
		alert("Select Criteria is required");
		return false;
	}
		return true;
}
</script>
</body>
</html>