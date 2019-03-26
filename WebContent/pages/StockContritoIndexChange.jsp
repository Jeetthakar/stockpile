
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
<%
			LogonForm form = null;
			if(request.isRequestedSessionIdValid())	{	
				form = (LogonForm)session.getAttribute("user");
			}
			if(form==null ||(!request.isRequestedSessionIdValid())){
				response.sendRedirect("userlogintemp.jsp");
			}
%>
<html>
<head>
<html:base/>
</head>
<link href="./StyleSheet.css" rel="stylesheet" type="text/css">
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
		          				<b><bean:message key="StockContritoIndexChange.title" /></b>
		          			</font>
		         	</td> 
	          </tr>
	</table> 
	
    <form action="StockContritoIndexChange.jsp">
    
	 <% 
	 		 String chkChart="";
         	chkChart=request.getParameter("checkChart");
	 		String chkchecked=request.getParameter("check");
	 		log.info("chkchecked is "+chkchecked);
	 %>
	    <table width="656">
         	<tr>
	  			<td width="114" nowrap="nowrap" align="right">
				   <font size="2" face="Arial">&nbsp;
     		 		</font>
            	</td> 
	  			<td width="84" nowrap="nowrap" align="left">
				   <font size="2" face="Arial"><bean:message key="indexcompose.indexname" />:
     		 		</font>
            	</td>   
      <%
        
        log.info("before firing query");
   //     org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
     ComposeIndex ci = ConnectInit.getComposeIndex();
        org.jfree.chart.demo.servlet.FieldSort sort=new org.jfree.chart.demo.servlet.FieldSort();
        ci.setVector_indexlist(chkchecked); 
        log.info("1");       
        String var,fromdate,toDate,field,col;
          int fieldno,colno;
        field=(String)request.getParameter("FieldNo");
         col=(String)request.getParameter("ColNo");
        if(col==null)
        {
        	col="4";
        }       
        if(field==null)
        {
        	field="0";
        }
        fieldno=Integer.parseInt(field);
        colno=Integer.parseInt(col);
        var=request.getParameter("D1");
        fromdate=request.getParameter("from");
        toDate=request.getParameter("to");
        if(var==null ){
        var="1";
        }
       // ci.setVector_stockcotriIndexchange(var,fromdate,toDate);
       	Vector v = ci.getVector_indexlist();    	
    	Iterator i=v.iterator();  
    	
	     %>   
       <td width="444" nowrap="nowrap" align="left" height="30">
        <select size="1" name="D1">
          <option value="0"><bean:message key="StockPerformance.notsel" /></option>
          
           <%     int requestID= 0;
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
         <table width="844" >
         	<tr>
         		<td width="140" align="right" nowrap="nowrap"> 
        <% if((request.getParameter("check"))!=null && (request.getParameter("check")).equals("checked")){ %>
	  	 <input type="checkbox" name="check" value="checked" onclick="return test1()" checked />&nbsp;
	  <% }else{ %>
	   <input type="checkbox" name="check" value="checked" onclick="return test1()"  />&nbsp;
	  <% } %>	   	   
	    </td>
	  <td width="144" nowrap="nowrap">	
	   <font size="2" face="Arial">  
	   <bean:message key="StockPerformance.showsel" /></font>
	 </td>
	  <td width="69" nowrap="nowrap"><font size="2" face="Arial" >
              <bean:message key="corporate.Fdate" />:
             </td> 
             <td width="78" nowrap="nowrap">	   
      <% if(fromdate==null){%>
            <input readOnly name="from" size="10">
            <% }else{ %>
            <input readOnly name="from" value="<%=fromdate%>" size="10">
                
                <% } %>   
                </td>
                             <td width="37" nowrap="nowrap">
                <input onclick="c2.popup('from');" type="button" value="...">
                 </td>
                              <td width="58" nowrap="nowrap"><font size="2" face="Arial">
                          <bean:message key="corporate.Tdate" />: 
                          </td>
                           <td width="79" nowrap="nowrap">
                    <% if(toDate==null){%> 
                          <input readOnly name="to" size="10">
                          <% }else{ %> 
                                <input readOnly name="to" value="<%=toDate %>" size="10">
                       <% } %>   
                       </td>
                                <td width="30" nowrap="nowrap">
                                <input onclick="c2.popup('to');" type="button" value="...">
                      </td>
                                 <td width="162" nowrap="nowrap">
                     <input type="submit" value='<bean:message key="Reports.View"/>' name="B1">
       </td>
		</tr>
		
		<tr>
	         <td width="140" align="right" nowrap="nowrap">
	             <%
	             
	              if(chkChart!=null && chkChart.equals("checked")){ %>
		  	 <input type="checkbox" name="checkChart" value="checked"  checked />&nbsp;	  
		  	 <% }else{ %>
		   	<input type="checkbox" name="checkChart" value="checked"  />&nbsp;
		 	 <% } %>	   	   
		    </td>
		 	 <td width="144" nowrap="nowrap" align="left">	
		   	<font size="2" face="Arial"><bean:message key="IndexWiseWeight.Show" /></font>
	 		 </td>
         </tr>
		</table>
				</p>
<table  border="0" width="900" cellpadding="0" cellspacing="0">
  <tr>
	 <td width="119" nowrap="nowrap">&nbsp;</td>
	 <td width="781" nowrap="nowrap">					
        <%
        if((fromdate==null) || (toDate==null))
        {
        %>
        <table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
          <tr>
          <td  bgcolor="#cacaca" align="center" valign="middle">
              <p style="margin-left: 9"><font face="Arial" color="blue" size="5"><bean:message key="IndexWiseWeight.Parameters" /></a></p>
            </td>
            </tr>
            </table>
      <% }else{ 
         ci.setVector_stockcotriIndexchange(var,fromdate,toDate);
         SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.UK);
	
	Date dDate = null;
	try {
		dDate = sdf.parse("01-Aug-2002");
	} catch (ParseException e) {
		//  Leave at null
	}
	Vector v2 = ci.getVector_stockcotriIndexchange();
	if((v2.size()==0))
	{
	%>
	<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
          <tr>
          <td  bgcolor="#cacaca" align="center" valign="middle">
              <p style="margin-left: 9"><font face="Arial" color="blue" size="5"><bean:message key="StockPerformance.selDataAvai" /></a></p>
            </td>
            </tr>
            </table>
      <% }else{ 
	if(chkChart!=null && chkChart.equals("checked")){ 
// 	CPieChart.StockcontriReaddata(v2);
// 	String filename = CPieChart.generatePieChart(dDate, session, new PrintWriter(out),1);
// 	String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
// 	log.info("graphURL is"+graphURL);
// 		log.info("filename is"+filename);
%>    
    
<%-- 	<img src="<%= graphURL %>" width=700 height=800 border=0 usemap="#<%= filename %>"> --%>
     
      <br>
      <%}
      		 log.info("1");         
	      int dir=0,dir1=0,dir2=0,dir3=0;
	      Vector v3 = ci.getVector_stockcotriIndexchange();
	      log.info("Size of table   "+v3.size()); 
	        log.info("1");
	       if(fieldno==0)
          {
          	dir=sort.getcount();
          	v3=sort.SetOrderSort(v3,0,4);
          }
          if(fieldno==1)
          {
          	dir1=sort.getcount1();
          	v3=sort.SetOrderSortNo(v3,1,4);
          }
          if(fieldno==2)
          {
          	dir2=sort.getcount2();
          	v3=sort.SetOrderSortNo(v3,2,4);
          }  
          if(fieldno==3)
          {
          	dir3=sort.getcount3();
          	v3=sort.SetOrderSortNo(v3,3,4);
          }   
      %>
        <p align="center">
        <bean:message key="StockContritoIndexChange.Wise" />
       <table border="0" class="gridStyle" width="70%" cellspacing="0" cellpadding="5">
          <tr>
            <td width="25%" class="gridStyle-header" align="center" valign="middle"><a href="StockContritoIndexChange.jsp?D1=<%=var%>&from=<%=fromdate%>&to=<%=toDate%>&B1=View&FieldNo=0&ColNo=4" onmouseover="window.status='';return true"> <bean:message key="StockWisePe.Name" /></a>
               <%  
				if(fieldno==0){
				if(dir%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         		 <%  } 
         		 } %></td>
            <td width="15%" class="gridStyle-header" align="center" valign="middle"><a href="StockContritoIndexChange.jsp?D1=<%=var%>&from=<%=fromdate%>&to=<%=toDate%>&B1=View&FieldNo=1&ColNo=4" onmouseover="window.status='';return true"><bean:message key="StockContritoIndexChange.Index" /></a>
               <%  
				if(fieldno==1){
				if(dir1%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         		 <%  } 
         		 } %></td>
            <td width="15%" class="gridStyle-header" align="center" valign="middle"><a href="StockContritoIndexChange.jsp?D1=<%=var%>&from=<%=fromdate%>&to=<%=toDate%>&B1=View&FieldNo=2&ColNo=4" onmouseover="window.status='';return true"><bean:message key="StockContritoIndexChange.Stock" /></a>
             <%  
				if(fieldno==2){
				if(dir2%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         		 <%  } 
         		 } %></td>
            <td width="15%" class="gridStyle-header" align="center" valign="middle"><a href="StockContritoIndexChange.jsp?D1=<%=var%>&from=<%=fromdate%>&to=<%=toDate%>&B1=View&FieldNo=3&ColNo=4" onmouseover="window.status='';return true"><bean:message key="IndexWiseWeight.Weightage" /></a>
               <%  
				if(fieldno==3){
				if(dir3%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         		 <%  } 
         		 } %></td>
          </tr>
    
          <% 
           Iterator i3=v3.iterator(); 
           log.info("1");
           int count=0;
           while(i3.hasNext())
           {  
            	log.info("1");
            	count++;
            	if(count%2!=0)
            	{
          %>
     
        <tr>
            <td width="25%" class="gridStyle-odd">
              <p style="margin-left: 5; margin-right: 5"><%= i3.next() %></a> </p>
            </td>
            <td width="15%" align="right" class="gridStyle-odd">
              <p style="margin-left: 5; margin-right: 5"><%= i3.next() %></p>
            </td>
            <td width="15%" align="right" class="gridStyle-odd">
              <p style="margin-left: 5; margin-right: 5"><%= i3.next() %></p>
            </td>
            <td width="15%" align="right" class="gridStyle-odd">
              <p style="margin-left: 5; margin-right: 5"><%= i3.next() %> </p>
            </td>
         </tr>
          <% }else{ %> 
         		<tr>
            <td width="25%" class="gridStyle-even">
              <p style="margin-left: 5; margin-right: 5"><%= i3.next() %></a> </p>
            </td>
            <td width="15%" class="gridStyle-even" align="right">
              <p style="margin-left: 5; margin-right: 5"><%= i3.next() %></p>
            </td>
            <td width="15%" class="gridStyle-even" align="right">
              <p style="margin-left: 5; margin-right: 5"><%= i3.next() %></p>
            </td>
            <td width="15%" class="gridStyle-even" align="right">
              <p style="margin-left: 5; margin-right: 5"><%= i3.next() %> </p>
            </td>
         </tr>
          <% }
          }%> 
           
      </td>
    </tr>
    <% }
    } %>
    </form>
  </tbody>
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
<script language="javascript">
function test1(){
	document.forms[0].submit();
}
</script>
</body>
</html>