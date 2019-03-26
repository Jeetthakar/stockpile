
<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.text.ParseException" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@page import="org.jfree.chart.demo.servlet.AdjustDecimal"%>
<%@page import="org.jfree.chart.demo.servlet.ComposeIndex"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page  import="app.*"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
			LogonForm form = null;
		//	AcessControl asc=new AcessControl();
			AcessControl asc=ConnectInit.getAcessControl();
			if(request.isRequestedSessionIdValid())	{	
				form = (LogonForm)session.getAttribute("user");
				
				Vector uname=new Vector();
				
				uname=asc.getUseActivitiesId(form);	
				boolean flag=asc.HasAcess("2",uname);
            log.info("flag is "+flag);
			}

			
			boolean flag=false;
			if(form==null ||(!request.isRequestedSessionIdValid())){
				response.sendRedirect("userlogintemp.jsp");
			}
			
			else{
							
			}	
			
						
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
      <table width="1000" cellspacing="0" cellpadding="0" >
        		<tr>
		          	<td width="335" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          			<font size="3" face="Arial">
		          				<b><bean:message key="StockPerformance.title" /> </b>
		          			</font>
		         	</td> 
	          </tr>
	</table>
 
	 <form action="StockPerformanceReport.jsp">
	 <% 
	 		String chkchecked=request.getParameter("check");
	 		log.info("chkchecked is "+chkchecked);
	 %>
	 		
        <%
       // org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
       // org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
        ComposeIndex ci = ConnectInit.getComposeIndex();
        AdjustDecimal ad = ConnectInit.getAdjustDecimal();
        org.jfree.chart.demo.servlet.FieldSort sort=new org.jfree.chart.demo.servlet.FieldSort();
        org.jfree.chart.demo.servlet.CalculateBeta calbeta=new org.jfree.chart.demo.servlet.CalculateBeta();
        ci.setVector_indexlist(chkchecked);        
        String var,str="",field,col;
        int fieldno,colno;
        field=(String)request.getParameter("FieldNo");
         col=(String)request.getParameter("ColNo");
        if(col==null)
        {
        	col="9";
        }       
        if(field==null)
        {
        	field="8";
        }
        fieldno=Integer.parseInt(field);
        colno=Integer.parseInt(col);
        String fromdate = request.getParameter("from");
        String toDate  =request.getParameter("to");
        log.info("before firing query");
        var=request.getParameter("D1");       
    	Vector v3 = ci.getVector_indexlist();    	
    	Iterator i1=v3.iterator();  
    	
	     %>   
	    <%String pr = null;
         pr = request.getParameter("Pr");   // Check if clicked on printer friendly link.
    	try{
		    if(pr.equals(null))
    	       { pr = "N";} 
        	   }catch(Exception e){pr = "N";}
		  if(!pr.equals("Y"))  
           { %>
            <table width="656" >
         	<tr>
         		<td width="204" align="right" nowrap="nowrap">
         		<font face="Arial" size="2">
	   				<bean:message key="indexcompose.indexname" />:        
	   			</font>
	   			</td>    
         <td width="400" nowrap="nowrap" align="left">
        <select size="1" name="D1">
          <option value="0"><b><bean:message key="StockPerformance.notsel" /></option>
          
           <%  int requestID= 0;
                     try{
	                      requestID = Integer.parseInt(request.getParameter("D1"));
                     	}catch(Exception e){}
    
                     while(i1.hasNext())
                      {   
                        int id = Integer.parseInt(i1.next().toString());
                         if(id == requestID)
                          {%>
                        	<option selected value="<%=id%>"><%=i1.next()%></option>
                       	<%}else { %>
 	                       	<option value="<%=id%>"><%= i1.next() %></option>
                       <% }
						}         
                         %>
                        </select>
                       </td>
                       </tr>
                       </table>
                        <br></br>
                        
    
		<table width="844" >
         	<tr>
         		<td width="150" align="right" nowrap="nowrap">
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
	    <%  if(fromdate==null){%>
           	<input readOnly name="from"  size="10">
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
                         <%  if(toDate==null){%>
                           <input readOnly name="to"  size="10">
                          <% }else{ %>
                        	 <input readOnly name="to" value="<%=toDate%>" size="10">
                               <% } %>
                               </td>
                                <td width="30" nowrap="nowrap">
                               <input onclick="c2.popup('to');" type="button" value="...">
                               </td>
                                 <td width="162" nowrap="nowrap">
                      <input type="submit" value='<bean:message key="Reports.View"/>' name="B1">
                      </td>
                      </tr>
                      </table>
       <br></br>
       <% } %>
       <% if(pr.equals("Y")){ %>
    <table border="0" width="100%"  cellspacing="0" cellpadding="3"  height="30">
       <tr>
        	<td align="left" width="120" nowrap="nowrap">
        		&nbsp;
          	</td>
        	<td align="left" width="700" nowrap="nowrap" >
     			<font face="Arial" size="2" ><b><bean:message key="corporate.Indname" />:</b></font>&nbsp;<font face="Arial" size="2" color="blue"><%= ci.getIndexName(var)%></font>
     		</td>
         </tr> 
      </table>
      <p></p>
	  <%}%> 	
       
       <% 		if(var != null)
				{
					if(!pr.equals("Y"))
					{
					try{
						String astr = null;
                           	astr = "../pages/StockPerformanceReport.jsp?Pr=Y&D1="+var+"&from="+fromdate+"&to="+toDate+"&FieldNo="+field+"&ColNo="+col; 
                        	String excel = "../pages/FileDownload.jsp?type=13&from="+fromdate+"&to="+toDate+"&filename=StockPerformanceReport.xls&var="+var;
							String str_url = "../pages/EmailReport.jsp?switch_type=13&cas=13&varid="+var+"&rname=StockPerformanceReport.xls&from="+fromdate+"&to="+toDate;
							//field=(String)request.getParameter("FieldNo");
        					// col=(String)request.getParameter("ColNo");
                        %> <table width="900" cellpadding="0" cellspacing="0">
                        <tr>
                        <br/>
                        <td width="420" nowrap="nowrap" align="right">
                        <font size="1">
                           	<a href="javascript:popprinter('<%=astr%>');"><b><bean:message key="IndexPerformance.printerf" /></a></font>
                        	
                        </td>
                        <td width="130" nowrap="nowrap">
                           	&nbsp;&nbsp;
                           	<font size="1">
							<a href=<%= excel %>>
							<bean:message key="IndexPerformance.downloade" /></a>
							</font></td>
						 <td width="415" nowrap="nowrap">
						
                           	<font size="1">	
							<a href= <%= str_url %>>
							<bean:message key="IndexPerformance.emailr" /></a>
							</font>
							</td>
						</tr>
					</table>
					<br/>
            <%			}catch(Exception e){log.info("refresh the page.");}
            		}
          		}		
          	%>
	 <table  border="0" width="900" cellpadding="0" cellspacing="0">
  <tr>
	 <td width="130" nowrap="nowrap">&nbsp;</td>
	 <td width="770" nowrap="nowrap">	 
	
	<% 
	if(var==null)
	{
	%>
	<table border="0" align="left" class="gridStyle" width="631" height="222" cellspacing="0" cellpadding="0">
          <tr>
          <td  class="gridStyle-message" align="center" valign="middle">
              <p style="margin-left: 9"><b><bean:message key="StockPerformance.selData" /> </b></p>
            </td>
            </tr>
            </table>
      <% }else {  
	
	ci.setVector_stock_performance(var,fromdate,toDate);
	calbeta.populateValuesBetaCalc(var,fromdate,toDate);	 
	Vector v1 = ci.getVector_stock_performance();	
	if(v1.size()==0)
	{
	%>
	<table border="0" align="left" class="gridStyle" width="631" height="222" cellspacing="0" cellpadding="0">
          <tr>
          <td  class="gridStyle-message" align="center" valign="middle">
              <p style="margin-left: 9"><b>No
              <bean:message key="StockPerformance.selDataAvai" /></b></p>
            </td>
            </tr>
            </table>
      <% }else {  
      		int dir1=0,dir2=0,dir3=0,dir4=0,dir5=0,dir6=0,dir7=0,dir8=0;
      		Vector v = ci.getVector_stock_performance();
            Vector v11=calbeta.combineVector(v1);	     
          log.info("Size of table   "+v.size()); 
          if(fieldno==1)
	      {
	      		dir1=sort.getcount1();
	      		v11=sort.SetOrderSort(v11,1,9);	      		             	
          }
          if(fieldno==2){
                dir2=sort.getcount2();
                v=sort.SetOrderSortNo(v11,2,9);
          }
          if(fieldno==3){
                dir3=sort.getcount3();
                v11=sort.SetOrderSortNo(v11,3,9);
          }
          if(fieldno==4){
                dir4=sort.getcount4();
                v11=sort.SetOrderSortNo(v11,4,9);
          }
          if(fieldno==5){
                dir5=sort.getcount5();
                v11=sort.SetOrderSortNo(v11,5,9);
          }
          if(fieldno==6){
                dir6=sort.getcount6();
                v11=sort.SetOrderSortNo(v11,6,9);
          }
          if(fieldno==7){
                dir7=sort.getcount7();
                v11=sort.SetOrderSortNo(v11,7,9);
          }
          if(fieldno==8){
                dir8=sort.getcount8();
                v11=sort.SetOrderSortNo(v11,8,9);
          }
          Object ci2 = null;
		  session.setAttribute("ci2",new Vector(v11));
         %>		
        <table border="0" align="left" width="94%" class="gridStyle" cellspacing="0" cellpadding="5">
         <% if(pr.equals("Y")){ %> 
          
          <tr>
            <td width="20%" class="gridStyle-header" align="center" valign="middle"><bean:message key="stockmaster.stockName" />
              <%  
				if(fieldno==1){
				if(dir1%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         		 <%  } 
         		 } %></td>
            <td width="12%" class="gridStyle-header" align="right" valign="middle"><bean:message key="StockPerformance.Issued" />
              <%  
				if(fieldno==2){
				if(dir2%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         		 <%  } 
         		 } %></td>
            <td width="12%" class="gridStyle-header" align="right" valign="middle"><bean:message key="StockPerformance.Mar" /></a>
            <%  
				if(fieldno==3){
				if(dir3%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         		 <%  } 
         		 } %></td>
            <td width="10%" class="gridStyle-header" align="right" valign="middle"><bean:message key="StockPerformance.Weightage" />
              <%  
				if(fieldno==4){
				if(dir4%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         		 <%  } 
         		 } %></td>
            <td width="10%" class="gridStyle-header" align="center" valign="middle"><bean:message key="StockPerformance.Beta" />
            <%  
				if(fieldno==5){
				if(dir5%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         		 <%  } 
         		 } %></td>
            <td width="10%" class="gridStyle-header" align="center" valign="middle"><bean:message key="StockPerformance.R2" />
             <%  
				if(fieldno==6){
				if(dir6%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         		 <%  } 
         		 } %></td>
            <td width="10%" class="gridStyle-header" align="center" valign="middle"><bean:message key="StockPerformance.Average" />
             (%) </a>
             <%  
				if(fieldno==7){
				if(dir7%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         		 <%  } 
         		 } %></td>
            <td width="10%" class="gridStyle-header" align="right" valign="middle"><bean:message key="StockPerformance.Periodic" />
          </tr>
         <%}else {%>
          <tr>
            <td width="20%" class="gridStyle-header" align="center" valign="middle"><a href="StockPerformanceReport.jsp?D1=<%=var%>&from=<%=fromdate%>&to=<%=toDate%>&B1=view&FieldNo=1&ColNo=9" onmouseover="window.status='';return true"><bean:message key="stockmaster.stockName" />
              <%  
				if(fieldno==1){
				if(dir1%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         		 <%  } 
         		 } %></td>
            <td width="12%" class="gridStyle-header" align="right" valign="middle"><a href="StockPerformanceReport.jsp?D1=<%=var%>&from=<%=fromdate%>&to=<%=toDate%>&B1=view&FieldNo=2&ColNo=9" onmouseover="window.status='';return true"><bean:message key="StockPerformance.Issued" />
              <%  
				if(fieldno==2){
				if(dir2%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         		 <%  } 
         		 } %></td>
            <td width="12%" class="gridStyle-header" align="right" valign="middle"><a href="StockPerformanceReport.jsp?D1=<%=var%>&from=<%=fromdate%>&to=<%=toDate%>&B1=view&FieldNo=3&ColNo=9" onmouseover="window.status='';return true"><bean:message key="StockPerformance.Mar" /></a>
            <%  
				if(fieldno==3){
				if(dir3%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         		 <%  } 
         		 } %></td>
            <td width="10%" class="gridStyle-header" align="right" valign="middle"><a href="StockPerformanceReport.jsp?D1=<%=var%>&from=<%=fromdate%>&to=<%=toDate%>&B1=view&FieldNo=4&ColNo=9" onmouseover="window.status='';return true"><bean:message key="StockPerformance.Weightage" />
              <%  
				if(fieldno==4){
				if(dir4%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         		 <%  } 
         		 } %></td>
            <td width="10%" class="gridStyle-header" align="center" valign="middle"><a href="StockPerformanceReport.jsp?D1=<%=var%>&from=<%=fromdate%>&to=<%=toDate%>&B1=view&FieldNo=5&ColNo=9" onmouseover="window.status='';return true"><bean:message key="StockPerformance.Beta" />
            <%  
				if(fieldno==5){
				if(dir5%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         		 <%  } 
         		 } %></td>
            <td width="10%" class="gridStyle-header" align="center" valign="middle"><a href="StockPerformanceReport.jsp?D1=<%=var%>&from=<%=fromdate%>&to=<%=toDate%>&B1=view&FieldNo=6&ColNo=9" onmouseover="window.status='';return true"><bean:message key="StockPerformance.R2" />
             <%  
				if(fieldno==6){
				if(dir6%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         		 <%  } 
         		 } %></td>
            <td width="10%" class="gridStyle-header" align="center" valign="middle"><a href="StockPerformanceReport.jsp?D1=<%=var%>&from=<%=fromdate%>&to=<%=toDate%>&B1=view&FieldNo=7&ColNo=9" onmouseover="window.status='';return true"><bean:message key="StockPerformance.Average" />
             (%) </a>
             <%  
				if(fieldno==7){
				if(dir7%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         		 <%  } 
         		 } %></td>
            <td width="10%" class="gridStyle-header" align="right" valign="middle"><a href="StockPerformanceReport.jsp?D1=<%=var%>&from=<%=fromdate%>&to=<%=toDate%>&B1=view&FieldNo=8&ColNo=9" onmouseover="window.status='';return true"><bean:message key="StockPerformance.Periodic" />
          </tr>
         
         <%}%>
       <%    
          int count=0;
          String id=null;
          double total=0.00,beta=0.00,rsq=0.00,avgdailyvol=0.00,mr=0.00;
          Iterator i=v11.iterator();
           while(i.hasNext())
           {  
           	count++;
           	if(count%2!=0)
           	{
           		id=(String)i.next();
           		String stock_detail="../pages/stockmaster2.jsp?s_stockid="+id; 
          %>
          <tr>
            <td width="20%" height="37" class="gridStyle-odd">
            <%  if(flag==true){  %>
            <% if(!pr.equals("Y")) { %>            	
              <p style="margin-left: 5; margin-right: 5"><a href="<%= stock_detail %>" onmouseover="window.status='';return true"><%= i.next() %></a> </p>
               <% }else { %>
  			  <p style="margin-left: 5; margin-right: 5"><%= i.next() %> </p>
  			  <% } %>
           <% }else{ %>
           	<p style="margin-left: 5; margin-right: 5"><%= i.next() %> </p>
           <% } %>
            </td>
            <td width="12%" align="right" class="gridStyle-odd">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric((String)i.next()) %></p>
            </td>
            <td width="12%" align="right" class="gridStyle-odd">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric((String)i.next()) %></p>
            </td>
             <% 
            		String tp1=(String)i.next();
            		total+=(double)Double.parseDouble(tp1);
            		tp1=ad.indexcompose(tp1);           		
            %>
            <td width="10%" align="right" class="gridStyle-odd">
              <p style="margin-left: 5; margin-right: 5"><%= tp1 %>&nbsp;%</p>
            </td>
            <td width="10%" align="center" class="gridStyle-odd">
              <p style="margin-left: 5; margin-right: 5"><%= i.next() %></p>
            </td>
            <td width="10%" align="right" class="gridStyle-odd">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric((String)i.next()) %></p>
            </td>
             <td width="10%" align="right" class="gridStyle-odd">
              <p style="margin-left: 5; margin-right: 5"><%= i.next() %>&nbsp;%</p>
            </td>
            <td width="10%" align="right" class="gridStyle-odd">
              <p style="margin-left: 5; margin-right: 5"><%= i.next() %>&nbsp;%</p>
            </td>
           
          </tr>
          
          <% }else{ 
          		id=(String)i.next();
          		String stock_detail1="../pages/stockmaster2.jsp?s_stockid="+id; 
          %>
           <tr>
            <td width="20%" class="gridStyle-even" height="37">
                <%  if(flag==true){  %>
                <% if(!pr.equals("Y")) { %>   
                   <p style="margin-left: 5; margin-right: 5"><a href="<%= stock_detail1 %>" onmouseover="window.status='';return true"><%= i.next() %></a> </p>
                <% }else { %>
                   <p style="margin-left: 5; margin-right: 5"><%= i.next() %></p>
                <% } %>   
           <% }else{ %>
           	<p style="margin-left: 5; margin-right: 5"><%= i.next() %> </p>
           <% } %>
            </td>
            <td width="12%" class="gridStyle-even" align="right">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric((String)i.next()) %></p>
            </td>
            <td width="12%" class="gridStyle-even" align="right">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric((String)i.next()) %></p>
            </td>
             <% 
            		String tp2=(String)i.next();
            		total+=(double)Double.parseDouble(tp2);
            		tp2=ad.indexcompose(tp2);           		
            %>
            <td width="10%" class="gridStyle-even" align="right">
              <p style="margin-left: 5; margin-right: 5"><%= tp2 %>&nbsp;%</p>
            </td>
            <td width="10%" class="gridStyle-even" align="center">
              <p style="margin-left: 5; margin-right: 5"><%= i.next() %></p>
            </td>
           <td width="10%" class="gridStyle-even" align="right">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric((String)i.next()) %></p>
            </td> 
            <td width="10%" class="gridStyle-even" align="right">
              <p style="margin-left: 5; margin-right: 5"><%= i.next() %>&nbsp;%</p>
            </td>
          <td width="10%" class="gridStyle-even" align="right">
              <p style="margin-left: 5; margin-right: 5"><%= i.next() %>&nbsp;%</p>
            </td>
          </tr>
           <% }%>
	<%	} %>
			<tr>
            <td width="20%" bgcolor="#cacaca" height="37">
              <p style="margin-left: 5; margin-right: 5"></a><bean:message key="StockPerformance.Total" /> </p>
            </td>
            <td width="12%" bgcolor="#cacaca" align="right">
              <p style="margin-left: 5; margin-right: 5"></p>
            </td>
            <td width="12%" bgcolor="#cacaca" align="right">
              <p style="margin-left: 5; margin-right: 5"></p>
            </td>
             <%		
            		if(total>=99.9)
            		{
            			total=100.00;
            		}
            		log.info("total is "+total);
            		String total1=new Double(total).toString();
            		String tot=ad.indexcompose(total1);
            %>
            <td width="10%" bgcolor="#cacaca" align="right">
              <p style="margin-left: 5; margin-right: 5"><%=tot%></p>
            </td>
            <td width="10%" bgcolor="#cacaca" align="center">
              <p style="margin-left: 5; margin-right: 5"></p>
            </td>
                
            <td width="10%" bgcolor="#cacaca" align="right">
              <p style="margin-left: 5; margin-right: 5"></p>
            </td> 
                    
            <td width="10%" bgcolor="#cacaca" align="right">
              <p style="margin-left: 5; margin-right: 5"></p>
            </td>
           
            <td width="10%" bgcolor="#cacaca" align="right">
              <p style="margin-left: 5; margin-right: 5"></p>
            </td>
          </tr>
        </table>
        	<%	}
        	} %> 
      </td>
    </tr>
    </form>  
  </tbody>
</table>
 </tr>
 </td>
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
<script  language="javascript">
function test1()
{
	document.forms[0].submit();
}
function popprinter(url)
{
	newwindow=window.open(url,'name','height=500,width=900,scrollbars=yes,left=80,top=130,menubar=yes,status=yes');
	if (window.focus) {newwindow.focus()}
}
</script>
</body>

</html>