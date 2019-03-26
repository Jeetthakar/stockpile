
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@page import="org.jfree.chart.demo.servlet.ComposeIndex"%> <%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import = "java.util.*" %>
<%@ page import ="java.text.SimpleDateFormat" %>
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
		//	AcessControl asc=new AcessControl();
			AcessControl asc=ConnectInit.getAcessControl();
			boolean flag=false;
			if(form==null ||(!request.isRequestedSessionIdValid())){
				response.sendRedirect("userlogintemp.jsp");
			}else{
						Vector uname=new Vector();	
						uname=asc.getUseActivitiesId(form);	
						flag=asc.HasAcess("39",uname);
                  		log.info("flag is "+flag);	
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
		          	<td width="260" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          			<font size="3" face="Arial">
		          				<b><bean:message key="IndexPerformance.title"/></b>
		          			</font>
		         	</td> 
	          </tr>
	 </table>
			
		<form action="IndexPerformance.jsp" onsubmit="return validate();">
		<table width="656" >
         	<tr>
         		<td width="365" align="right" nowrap="nowrap">
         		<font face="Arial" size="2">
				<% 
			//	org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
         ComposeIndex ci = ConnectInit.getComposeIndex(); 
         org.jfree.chart.demo.servlet.FieldSort sort=new org.jfree.chart.demo.servlet.FieldSort();
         String field,col;
        int fieldno,colno;
        field=(String)request.getParameter("FieldNo");
         col=(String)request.getParameter("ColNo");
        if(col==null)
        {
        	col="6";
        }       
        if(field==null)
        {
        	field="1";
        }
        fieldno=Integer.parseInt(field);
        colno=Integer.parseInt(col);
        String sdate=request.getParameter("date");
        
		%>
		<%String pr = null;
         pr = request.getParameter("Pr");   // Check if clicked on printer friendly link.
    	try{
		    if(pr.equals(null))
    	       { pr = "N";} 
        	   }catch(Exception e){pr = "N";}
		  if(!pr.equals("Y"))  
           { %>
		 <bean:message key="IndexPerformance.SelDate"/>:
		 </font>
		 </td>
		  <td width="89" nowrap="nowrap">
		<% if((sdate==null)||(sdate.equals("null"))){%> <input readOnly name="date" size="10">
		<% }else{ %>  <input readOnly name="date" value="<%=sdate%>" size="10">   <% } %>
		</td>
		 <td width="96" nowrap="nowrap">
                                <input onclick="c2.popup('date');" type="button" value="...">
                </td>
                 <td width="100" nowrap="nowrap">
     <input type="submit" value='<bean:message key="Reports.View"/>'  name="B1"></font>
     </td>
     </tr>
     </table>
     <br><br>
     	<% 		if(sdate != null)
				{
					try{
						String astr = null;
                           	astr = "../pages/IndexPerformance.jsp?Pr=Y&date="+sdate; 
                        	String excel = "../pages/FileDownload.jsp?type=12&from="+sdate+"&filename=IndexPerformance.xls";
							String str_url = "../pages/EmailReport.jsp?switch_type=12&cas=12&rname=IndexPerformance.xls&from="+sdate;
                        %> <table width="900" cellpadding="0" cellspacing="0">
                        <tr>
                        <br/>
                        	<td width="351" nowrap="nowrap" align="right">
                        		<font size="1">
                        			<a href="javascript:popprinter('<%=astr%>');"><bean:message key="IndexPerformance.printerf"/></a>
                        			
                        		</font>
                        	</td>
                        	 <td width="128" nowrap="nowrap" align="center">
								
								<font size="1">
									<a href=<%= excel %>><bean:message key="IndexPerformance.downloade"/></a>
								</font>
							 </td>
							<td width="421" nowrap="nowrap" align="left">
								<font size="1">	
									<a href= <%= str_url %>>
									<bean:message key="IndexPerformance.emailr"/></a>
								</font>
							 </td>
							</tr>
						</table>
						<p></p>
          <%			}catch(Exception e){log.info("refress the page.");}
          		}		
          	%>
         <% } %>
		</center>
		
		 <% if(pr.equals("Y")){ %>
    <table border="0" width="100%"  cellspacing="0" cellpadding="3"  height="30">
       <tr>
        	<td align="left" width="160" nowrap="nowrap">
        		&nbsp;
          	</td>
        	<td align="left" width="644" nowrap="nowrap" >
     			<font face="Arial" size="2" ><b><bean:message key="corporate.Fdate"/>:</b></font>&nbsp;<font face="Arial" size="2" color="blue"><%= sdate%></font>
     		</td>
         </tr>
       
         </table>  
         <p></p> 
     <% }%>
	  <table  border="0" width="900" cellpadding="0" cellspacing="0">
  <tr>
	 <td width="80" nowrap="nowrap">&nbsp;</td>
	 <td width="770" nowrap="nowrap">	 	
		 <%
		 if(sdate==null)
		 {
	 %>
         <table border="0" align="center" class="gridStyle" width="631" height="222"  cellspacing="0" cellpadding="0">
          <tr>
          <td  class="gridStyle-message" align="center" valign="middle">
              <p style="margin-left: 9"><bean:message key="IndexPerformance.selectdata"/></a></p>
            </td>
            </tr>
            </table>
          <%  }else{
		 
		 
            ci.setIndex_performance(sdate); 
		java.util.Vector v = ci.getIndex_performance();
		log.info("Size of vector is "+v.size());
		if(v.size()==0)
		{
        %>
         <table border="0" align="center" class="gridStyle" width="631" height="222"  cellspacing="0" cellpadding="0">
          <tr>
          <td  class="gridStyle-message" align="center" valign="middle">
              <p style="margin-left: 9"><bean:message key="IndexPerformance.nodata"/></a></p>
            </td>
            </tr>
            </table>
          <%  }else{
		 int dir=0,dir1=0,dir2=0,dir3=0,dir4=0,dir5=0;
          
        Object ci2 = null;
		session.setAttribute("ci2",new Vector(v));    
		log.info(v.size());
		Iterator i=v.iterator();  
		
          %>
        <% if(pr.equals("Y")){ %>
       
        <table border="0" align="center" width="80%" class="gridStyle" cellspacing="0" cellpadding="3">
          <tr>
          	<td width="20%" class="gridStyle-header" align="left" valign="middle"><bean:message key="indexcompose.indexname"/></a>
              <%  
				if(fieldno==1){
				if(dir1%2==0){  %>
				 <img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
          	 <% }else{ %>
            		 <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
        	  <%  } 
        	  } %></td>
            <td width="15%" class="gridStyle-header" align="right" valign="middle"><bean:message key="IndexPerformance.montho"/></a>
            <%  
				if(fieldno==2){
				if(dir2%2==0){  %>
				 <img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
          	 <% }else{ %>
            		 <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
        	  <%  } 
        	  } %></td>
            <td width="15%" class="gridStyle-header" align="right" valign="middle"><bean:message key="IndexPerformance.montht"/></a>
            <%  
				if(fieldno==3){
				if(dir3%2==0){  %>
				 <img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
          	 <% }else{ %>
            		 <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
        	  <%  } 
        	  } %></td>
            <td width="15%" class="gridStyle-header" align="right" valign="middle"><bean:message key="IndexPerformance.months"/></a>
			<%  
				if(fieldno==4){
				if(dir4%2==0){  %>
				 <img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
          	 <% }else{ %>
            		 <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
        	  <%  } 
        	  } %></td>
            <td width="15%" class="gridStyle-header"align="right" valign="middle"><bean:message key="IndexPerformance.year"/></a>
            <%  
				if(fieldno==5){
				if(dir5%2==0){  %>
				 <img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
          	 <% }else{ %>
            		 <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
        	  <%  } 
        	  } %></td>
           </tr>
<%}else {%>
<table border="0" align="center" width="80%" class="gridStyle" cellspacing="0" cellpadding="3">
          <tr>
          	<td width="20%" class="gridStyle-header" align="left" valign="middle"><a href="../pages/IndexPerformance.jsp?date=<%=sdate%>&B1=View&FieldNo=1&ColNo=6" onmouseover="window.status='';return true"><bean:message key="indexcompose.indexname"/></a>
              <%  
				if(fieldno==1){
				if(dir1%2==0){  %>
				 <img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
          	 <% }else{ %>
            		 <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
        	  <%  } 
        	  } %></td>
            <td width="15%" class="gridStyle-header" align="right" valign="middle"><a href="../pages/IndexPerformance.jsp?date=<%=sdate%>&B1=View&FieldNo=2&ColNo=6" onmouseover="window.status='';return true"><bean:message key="IndexPerformance.montho"/></a>
            <%  
				if(fieldno==2){
				if(dir2%2==0){  %>
				 <img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
          	 <% }else{ %>
            		 <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
        	  <%  } 
        	  } %></td>
            <td width="15%" class="gridStyle-header" align="right" valign="middle"><a href="../pages/IndexPerformance.jsp?date=<%=sdate%>&B1=View&FieldNo=3&ColNo=6" onmouseover="window.status='';return true"><bean:message key="IndexPerformance.montht"/></a>
            <%  
				if(fieldno==3){
				if(dir3%2==0){  %>
				 <img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
          	 <% }else{ %>
            		 <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
        	  <%  } 
        	  } %></td>
            <td width="15%" class="gridStyle-header" align="right" valign="middle"><a href="../pages/IndexPerformance.jsp?date=<%=sdate%>&B1=View&FieldNo=4&ColNo=6" onmouseover="window.status='';return true"><bean:message key="IndexPerformance.months"/></a>
			<%  
				if(fieldno==4){
				if(dir4%2==0){  %>
				 <img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
          	 <% }else{ %>
            		 <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
        	  <%  } 
        	  } %></td>
            <td width="15%" class="gridStyle-header"align="right" valign="middle"><a href="../pages/IndexPerformance.jsp?date=<%=sdate%>&B1=View&FieldNo=5&ColNo=6" onmouseover="window.status='';return true"><bean:message key="IndexPerformance.year"/></a>
            <%  
				if(fieldno==5){
				if(dir5%2==0){  %>
				 <img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
          	 <% }else{ %>
            		 <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
        	  <%  } 
        	  } %></td>
           </tr>
<%}%>
          <% 
          String temp1;
         int count=0;
           while(i.hasNext())
           {  
           		temp1="../pages/indexcompose.jsp?D1="+i.next().toString();
               log.info(temp1);
                count++;
           		if(count%2!=0)
           		{
                   %>
          <tr>
           <td width="20%" align="left" class="gridStyle-odd">
           <% if(flag==true){ 
           		if(pr.equals("Y")){	%>
              		<p style="margin-left: 5; margin-right: 5" ><%=i.next()%></a></p>
              	<%}
              	else{%>
              		<p style="margin-left: 5; margin-right: 5"><a href="<%= temp1 %>"><%=i.next()%></a></p>
              <%	}%>
           <% }else{ %>
           	<p style="margin-left: 5; margin-right: 5"><%=i.next()%></p>
           <% } %>
            </td>
           <td width="15%" align="right" class="gridStyle-odd">
              <p style="margin-left: 5; margin-right: 5"><%= (String)i.next() %> 
           </td>
           <td width="15%" align="right" class="gridStyle-odd">
              <p style="margin-left: 5; margin-right: 5"><%= (String)i.next() %></p>
            </td>
           <td width="15%" align="right" class="gridStyle-odd">
              <p style="margin-left: 5; margin-right: 5"><%= (String)i.next() %></p>
            </td>
          <td width="15%" align="right" class="gridStyle-odd">
              <p style="margin-left: 5; margin-right: 5"><%= (String)i.next() %></p>
            </td>           
          </tr>
          <% } else{
          %>
         	 <tr>
         	 <td width="20%" class="gridStyle-even" align="left">
               <% if(flag==true){ 
               		if(pr.equals("Y")){	%>
              		 <p style="margin-left: 5; margin-right: 5"><%=i.next()%></a></p>
              	<%}
              	else{%>
              		 <p style="margin-left: 5; margin-right: 5"><a href="<%= temp1 %>"><%=i.next()%></a></p>
              <%	}%>
             
           <% }else{ %>
           	<p style="margin-left: 5; margin-right: 5"><%=i.next()%></p>
           <% } %>
            </td>
            <td width="15%" class="gridStyle-even" align="right">
              <p style="margin-left: 5; margin-right: 5"><%= (String)i.next() %> 
            </td>            
            <td width="15%" class="gridStyle-even" align="right">
              <p style="margin-left: 5; margin-right: 5"><%= (String)i.next() %></p>
            </td>
            <td width="15%" class="gridStyle-even" align="right">
              <p style="margin-left: 5; margin-right: 5"><%= (String)i.next() %></p>
            </td>
            <td width="15%" class="gridStyle-even" align="right">
              <p style="margin-left: 5; margin-right: 5"><%= (String)i.next() %></p>
            </td>            
          </tr>
            <% }
            }
          %>        
       		 </table>
        	<% }
        	} %>
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
<script language="JavaScript">
function popprinter(url)
{
	newwindow=window.open(url,'name','height=500,width=900,scrollbars=yes,left=80,top=130,menubar=yes,status=yes');
	if (window.focus) {newwindow.focus()}
}
function validate(){
	
	if(document.forms[0].date.value==""){
		alert("Select Date is required");
		return false;
	}
	else return true;
}
</script>
</html>