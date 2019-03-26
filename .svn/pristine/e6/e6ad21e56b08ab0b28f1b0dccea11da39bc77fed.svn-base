<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.text.ParseException" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@page import="org.jfree.chart.demo.servlet.AdjustDecimal"%>
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@page import="org.jfree.chart.demo.servlet.ComposeIndex"%>
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
		          				<b><bean:message key="IndexWiseWeight.title" /></b>
		          			</font>
		         	</td> 
	          </tr>
	</table> 
	 
	 <form action="indwiseweight.jsp">  
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
       ComposeIndex ci = ConnectInit.getComposeIndex();
     //   org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
        ci.setVector_indexlist(chkchecked); 
        log.info("1");       
        String var,field,col;
        int fieldno,colno;
        field=(String)request.getParameter("FieldNo");
         col=(String)request.getParameter("ColNo");
        if(col==null)
        {
        	col="3";
        }       
        if(field==null)
        {
        	field="0";
        }
        var=request.getParameter("D1");
        fieldno=Integer.parseInt(field);
        colno=Integer.parseInt(col);       
        ci.setVector_indweighttable(var);
    	Vector v = ci.getVector_indexlist();    	
    	Iterator i=v.iterator();      	
	    String pr = null;
        pr = request.getParameter("Pr");
        %>
        <% try{					// Check if Cliked on printer friendly url
		    if(pr.equals(null))
    	       { pr = "N";}
        	   }catch(Exception e){pr = "N";}
           
           if(!pr.equals("Y"))
           { %>	   
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
                        
           <table width="844" height="40">
         	<tr>
         	<td width="140" align="right" nowrap="nowrap">	
        <% if((request.getParameter("check"))!=null && (request.getParameter("check")).equals("checked")){ %>
	   <input type="checkbox" name="check" value="checked" onclick="return test1()" checked />&nbsp;
	  
	   <% }else{ %>
	   <input type="checkbox" name="check" value="checked" onclick="return test1()"  />&nbsp;
	  <% } %>	   	   
	   </td>
	  <td width="213" nowrap="nowrap">	
	   <font size="2" face="Arial">  
	  <bean:message key="IndexReturnVolatility.Show" /></font>
	  </td>
	  <td width="477" nowrap="nowrap">
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
		 	 <td width="213" nowrap="nowrap" align="left">	
		   	<font size="2" face="Arial"><bean:message key="IndexWiseWeight.Show" /></font>
	 		 </td>
         </tr>
		</table>
		<p></p>
     <%}%>
 	 <% String astr = null;
                        if(var != null)
                        { 
                        	
                        	astr = "./indwiseweight.jsp?Pr=Y&D1="+var+"&B1=View&checkChart="+chkChart; 
                        	String temp_path = "../pages/FileDownload.jsp?var="+var+"&type=3&filename=IndWiseWeightage.xls";
							String str_url = "../pages/EmailReport.jsp?switch_type=3&cas=3&rname=IndWiseWeightage.xls&varid="+var;
                        %>
                        	<table width="800" cellpadding="0" cellspacing="0">
                        <tr>
                        <td width="280" nowrap="nowrap" align="right">&nbsp;
                        </td>
                        <td width="105" nowrap="nowrap" align="left">
                        	<font size="1">
                        	<a href="javascript:popprinter('<%=astr%>');"><bean:message key="IndexPerformance.printerf" /></a>
                        		</font>
                        </td>
                        <td width="110" nowrap="nowrap">
                           	<font size="1">
                        	
							<a href=<%= temp_path %>>
							<bean:message key="IndexPerformance.downloade" /></a>
							</font>
						</td>
						<td width="289" nowrap="nowrap">
                           	<font size="1">	
							<a href= <%= str_url %>>
							<bean:message key="IndexPerformance.emailr" /></a>
                        	<%}%>
                        </font>
						</td>
						</tr>
					</table>
					<br/>
 	
 	<table  border="0" width="900" cellpadding="0" cellspacing="0">
  <tr>
	 <td width="120" nowrap="nowrap">&nbsp;</td>
	 <td width="770" nowrap="nowrap">
 	
 	<%     String name = "";
           if(pr.equals("Y"))
           {
           		int var_int = Integer.parseInt(var);
           		name = ci.get_index_name(var_int);
           %><%= name%>
           <%}%>
 </form>
 
 <% if(var==null)
	{
	%>
	<table border="0" class="gridStyle" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
          <tr>
          <td  class="gridStyle-message" align="center" valign="middle">
              <p style="margin-left: 9"><b><bean:message key="IndexWiseWeight.Parameters" /> </b></p>
            </td>
            </tr>
            </table>
      <% }else {  
  
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.UK);
	
	Date dDate = null;
	try {
		dDate = sdf.parse("01-Aug-2002");
	} catch (ParseException e) {
		//  Leave at null
	}
	Vector v1 = ci.getVector_indweighttable();
	if((v1.size()==0))
	{
	%>
	<table border="0" class="gridStyle" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
          <tr>
          <td  class="gridStyle-message" align="center" valign="middle">
              <p style="margin-left: 9"><b><bean:message key="StockPerformance.selDataAvai" /></b></p>
            </td>
            </tr>
            </table>
      <% }else {
      if(chkChart!=null && chkChart.equals("checked")){    
// 	CPieChart.ReaddataIndweight(v1);
// 	String filename = CPieChart.generatePieChart(dDate, session, new PrintWriter(out),2);
// 	String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
// 	log.info("graphURL is"+graphURL);
// 		log.info("filename is"+filename);
		
%>   
    
<%-- 	<img src="<%= graphURL %>" width=700 height=500 border=0 usemap="#<%= filename %>"> --%>
      <br> 
      <%}
      		  log.info("1");
          // org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
          AdjustDecimal ad = ConnectInit.getAdjustDecimal();
          org.jfree.chart.demo.servlet.FieldSort sort=new org.jfree.chart.demo.servlet.FieldSort();
          v.clear();
          int dir=0,dir1=0,dir2=0;
          v = ci.getVector_indweighttable();
          if(fieldno==0)
          {
          	dir=sort.getcount();
          	v=sort.SetOrderSort(v,0,3);
          }
          if(fieldno==1)
          {
          	dir1=sort.getcount1();
          	v=sort.SetOrderSortNo(v,1,3);
          }
          if(fieldno==2)
          {
          	dir2=sort.getcount2();
          	v=sort.SetOrderSortNo(v,2,3);
          }
		  Object ci2 = null;
		  session.setAttribute("ci2",new Vector(v));        
	      log.info("Size of table   "+v.size()); 
      %>
       <p></p>
       <b> 
       <table width="656">
         	<tr>
	  			<td width="370" nowrap="nowrap" align="right">
				   <font size="3" face="Arial">
       							<b><bean:message key="IndexWiseWeight.Wise" /></b>
       							</font>
       							</td>
       							</tr>
       							</table>
         
        <table cellSpacing="0" class="gridStyle" align="left" cellPadding="0" width="70%" borderColorLight="#000000" border="0">
          <tbody>
            <tr>
              <td width="70%">
                <table cellSpacing="0" class="gridStyle" cellPadding="3" width="100%" border="0">
                  <tbody>
                    <tr>
                    <% if(!pr.equals("Y"))
       					{%>
                       <td width="30%" class="gridStyle-header"><a href="indwiseweight.jsp?D1=<%=var%>&B1=View&FieldNo=0&ColNo=3" onmouseover="window.status='';return true"><bean:message key="IndexWiseWeight.Name" /></a>
                       <%} if(pr.equals("Y"))
       					{%>
       				   <td width="30%" class="gridStyle-header"><bean:message key="IndexWiseWeight.Name" />
       				 <%}%>
                     <%  
						if(fieldno==0){
						if(dir%2==0){  %>
				 			<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           				<% }else{ %>
           					  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         				 <%  } 
         				 } %></td>
         			  <% if(!pr.equals("Y"))
       					{%>	 
                      <td align="right" width="20%" class="gridStyle-header"><a href="indwiseweight.jsp?D1=<%=var%>&B1=View&FieldNo=1&ColNo=3" onmouseover="window.status='';return true"><bean:message key="DisplayIndexes1.Market" /> </a>
                      <% }if(pr.equals("Y"))
       					{%>
     				  <td align="right" width="20%" class="gridStyle-header"><bean:message key="IndexWiseWeight.Market" /> 
                        cap.
                       <%}%>
                      <%  
						if(fieldno==1){
						if(dir1%2==0){  %>
				 			<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           				<% }else{ %>
           					  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         				 <%  } 
         				 } %></td>
         			<% if(!pr.equals("Y"))
       					{%>	 
                      <td align="right" width="20%" class="gridStyle-header"><a href="indwiseweight.jsp?D1=<%=var%>&B1=View&FieldNo=2&ColNo=3" onmouseover="window.status='';return true">Weightage
                        (%)</a>
                     <% }if(pr.equals("Y"))
       					{%>	
       				  <td align="right" width="20%" class="gridStyle-header"><bean:message key="IndexWiseWeight.Weightage" /> 
                        (%)
                     <%}%>
                      <%  
						if(fieldno==2){
						if(dir2%2==0){  %>
				 			<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           				<% }else{ %>
           					  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         				 <%  } 
         				 } %></td>
                    </tr>
    
        <%         
	       log.info("1");
           i=v.iterator(); 
           log.info("1");
           double mctot=0.0,wtot=0.0;
           int count=0;
           while(i.hasNext())
           {  
            log.info("1");
            count++;
            	if(count%2!=0)
            	{
          %>
     		
          <tr>
            <td width="30%" class="gridStyle-odd">
            <% if(!pr.equals("Y"))
       		   {%>
              <p style="margin-left: 5; margin-right: 5"><a href="indwiseweight.jsp?D1=<%=var%>&B1=View" onmouseover="window.status='';return true"><%= i.next() %></a> </p>
            <% }if(pr.equals("Y"))
       			  {%>
       		  <p style="margin-left: 5; margin-right: 5"><%= i.next() %> </p>
       		  <%}%>
            </td>
            <% 
           		 	
            		String num=(String)i.next();
            		num=ad.indexcompose(num);
            		String per=(String)i.next();
            		per=ad.indexcompose(per);
            		mctot=mctot+Double.parseDouble(num);
            		wtot=wtot+Double.parseDouble(per);            		
            %>
            <td width="20%" align="right" class="gridStyle-odd">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(num) %></p>
            </td>
            <td width="20%" align="right" class="gridStyle-odd">
              <p style="margin-left: 5; margin-right: 5"><%= per %></p>
            </td>
            
          </tr>
          <% 
          	}else{
          	%>
          			 <tr>
            <td width="30%" class="gridStyle-even">
            <% if(!pr.equals("Y"))
       		   {%>
              <p style="margin-left: 5; margin-right: 5"><a href="indwiseweight.jsp?D1=<%=var%>&B1=View" onmouseover="window.status='';return true"><%= i.next() %></a> </p>
            <% }if(pr.equals("Y"))
     		   {%>
     		  <p style="margin-left: 5; margin-right: 5"><%= i.next() %></p>
       		<%}%>  
            </td>
            <% 
           		 	
            		String num=(String)i.next();
            		num=ad.indexcompose(num);
            		String per=(String)i.next();
            		per=ad.indexcompose(per);
            		mctot=mctot+Double.parseDouble(num);
            		wtot=wtot+Double.parseDouble(per);            		
            %>
            <td width="20%" align="right" class="gridStyle-even">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(num) %></p>
            </td>
            <td width="20%" align="right" class="gridStyle-even">
              <p style="margin-left: 5; margin-right: 5"><%= per %></p>
            </td>
            
          </tr>
          <%} %>
          
          <% } %>
          <%
          		String tno1;
          		double tp;
          		if(mctot>0)
          		{
          			tno1=ad.shareholdingpatt(mctot);
          			tno1=ad.indexcompose(tno1);
          			String tp1=ad.twodigitdeci(wtot);
					tp=Double.parseDouble(tp1);
					if(tp>=99.9)
					{
						tp=100.00;
					}
          		}else
          		{
          			tno1="0.00";
          			tp=0.00;
          		}
           %>
      		<tr>
               <td width="30%" bgColor="#f4f4f4" align="right">
                  <p style="margin-left: 9"><b><font face="Arial" size="2"><bean:message key="StockPerformance.Total" /> </b></p>
               </td>
               <td align="right" width="20%" bgColor="#f4f4f4">
                 <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(tno1) %></b></p>
               </td>
               <td align="right" width="20%" bgColor="#f4f4f4">
                  <p style="margin-left: 5; margin-right: 5"><b><font face="Arial" size="2"><%= tp %>%</b></p>
               </td>
             </tr>
        </table>
       <% } 
       }%> 
       </form>
      </td>
    </tr>
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

</body>
<script language="javascript">
function test1(){
	document.forms[0].submit();
}
function popprinter(url)
{
	newwindow=window.open(url,'name','height=600,width=700,scrollbars=yes,left=170,top=130,menubar=yes,status=yes');
	if (window.focus) {newwindow.focus()}
}
</script>

</html>