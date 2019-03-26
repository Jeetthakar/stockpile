<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.text.ParseException" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>
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
		LogonForm form = (LogonForm)session.getAttribute("user");
		//	AcessControl asc=new AcessControl();
			AcessControl asc=ConnectInit.getAcessControl();
			boolean flag=false;
			if(form == null){
			  response.sendRedirect("login1.jsp");
			}else{
						Vector uname=new Vector();	
						uname=asc.getUseActivitiesId(form);	
						flag=asc.HasAcess("2",uname);
                  		log.info("flag is "+flag);	
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
		          				<b><bean:message key="TradeVolumeInd.title" /></b>
		          			</font>
		         	</td> 
	          </tr>
	</table> 
	<form action="TradedVolumeInd_exch.jsp">
	<table width="656">
     <%
      	org.jfree.chart.demo.servlet.FieldSort sort=new org.jfree.chart.demo.servlet.FieldSort();
      //  org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
        ComposeIndex ci = ConnectInit.getComposeIndex();
        String fromdate,toDate,field,col;
        int fieldno,colno;
        field=(String)request.getParameter("FieldNo");
         col=(String)request.getParameter("ColNo");
        if(col==null)
        {
        	col="3";
        }       
        if(field==null)
        {
        	field="2";
        }
        fieldno=Integer.parseInt(field);
        colno=Integer.parseInt(col);
        fromdate = request.getParameter("from");
        toDate  =request.getParameter("to");
        log.info("before firing query"); 
        String var=null;
        String str1=request.getParameter("refFlag");
        log.info("refFlag is.........."+str1);
			String sid="0";
			if(str1!=null && str1.equals("1")){
				var=ci.reset_stkevent();
			}	
			else{
        		var=request.getParameter("D1");       
        	}
        
         String var1=request.getParameter("filter");       
        String var3,trd_volume;
        var3=request.getParameter("D2");       
         trd_volume=request.getParameter("traded_volume");  
        
   %>   
   
     <tr>
	  			<td width="116" nowrap="nowrap" align="left">&nbsp;</td>
	  			<td width="118" nowrap="nowrap" align="left">
				   <font size="2" face="Arial"><bean:message key="TradeVolumeInd.Filter" />: 
				    </font>
        		</td>     
        		 <td width="504" nowrap="nowrap" align="left" height="30">
		<select size="1" name="filter" onchange="document.forms[0].submit();return true">          
          <option value="0" ><bean:message key="StockPerformance.notsel" /></option>	
           <% if(var1!=null && var1.equals("1")){%>
           		 <option value="1" selected="true"><bean:message key="TradeVolumeInd.Wise" /></option>		
           <% }else{%>
            <option value="1" ><bean:message key="TradeVolumeInd.Wise" /></option>	
           	<% } %>	 
           <% if(var1!=null && var1.equals("2")){%>
           		<option value="2" selected="true"><bean:message key="TradeVolumeInd.Index" /></option>	
           <% }else{%>
           	<option value="2"><bean:message key="TradeVolumeInd.Index" /></option>
           	<% } %>	            
        </select>
         </td>
         	</tr>
         <tr>
	  			<td width="116" nowrap="nowrap" align="left">&nbsp;</td>
	  			<td width="118" nowrap="nowrap" align="left">
				   <font size="2" face="Arial"><bean:message key="TradeVolumeInd.Cut" />: 
				    </font>
        		</td>     
        		 <td width="504" nowrap="nowrap" align="left" height="30"> 
              <% if(trd_volume==null){ %><input type="text" name="traded_volume" value="" size="20"/>
              <% }else{ %><input type="text" name="traded_volume" value="<%=trd_volume%>" size="20"/> <% } %>
           </td>
           	</tr>
         <%        			
          if(var1!=null && var1.equals("1")){%>           
          <tr>
	  			<td width="116" nowrap="nowrap" align="left">&nbsp;</td>
	  			<td width="118" nowrap="nowrap" align="right">
				   <font size="2" face="Arial"><bean:message key="TradeVolumeInd.Stock" />:
				   </font>
				 </td>
			<td width="504" nowrap="nowrap" align="left" height="30"> 
			   
        	<select size="1" name="D1">
          		<option value="0" selected><bean:message key="StockPerformance.notsel" /></option>			 
     		 		<%= ci.getExchangeList(var)%>		
             </select> 
             </td>
          </tr>  
            <% } %>             
             <% 
             String chkchecked=null;
             chkchecked=request.getParameter("check"); 
        	ci.setVector_indexlist(chkchecked); 
             	Vector v12 = ci.getVector_indexlist();    	
    	Iterator i1=v12.iterator();  
             if(var1!=null && var1.equals("2")){%>           
            <tr>
	  			<td width="116" nowrap="nowrap" align="left">&nbsp;</td>
	  			<td width="118" nowrap="nowrap" align="left">
				   <font size="2" face="Arial"><bean:message key="TradeVolumeInd.Name" />:  
				   </font>  
				 </td>  
				 <td width="504" nowrap="nowrap" align="left" height="30">      
        	<select size="1" name="D2">
          <option value="0"><bean:message key="StockPerformance.notsel" /></option>          
           <%        int requestID= 0;
                     try{
	                      if(request.getParameter("D2")!=null){
	                      	requestID = Integer.parseInt(request.getParameter("D2"));
	                      }else{
	                      		requestID=Integer.parseInt("0");
	                      }
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
        <p></p>
        
           
        <table width="844" height="40">
         	<tr>
         		
        <% if((request.getParameter("check"))!=null && (request.getParameter("check")).equals("checked")){ %>
	   <td width="144" align="right" nowrap="nowrap">
	   <input type="checkbox" name="check" value="checked" onclick="return test2()" checked />&nbsp;
	  
	   <% }else{ %>
	   <td width="144" align="right" nowrap="nowrap">
	   <input type="checkbox" name="check" value="checked" onclick="return test2()"  />&nbsp;
	  <% } %>	   	   
	  </td>
	  <td width="130" nowrap="nowrap">	
	   <font size="2" face="Arial">  
	  <bean:message key="IndexReturnVolatility.Show" /></font>
	  </td>
	 
            <% } 
            var1=request.getParameter("filter");  
            if(var1!=null && var1.equals("2")){ %>
            	
       <% }else{    %>  
            </tr>
            </table>
             <p></p>
             <table width="844" height="40">
         	<tr>
           <td width="100" align="right" nowrap="nowrap">&nbsp;</td> 
   <% } %>
         <td width="82" nowrap="nowrap" align="right"><font size="2" face="Arial" >
              <bean:message key="corporate.Fdate" />:
             </td> 
             <td width="80" nowrap="nowrap" >
               <% if(fromdate==null){ %><input readOnly name="from" size="10" value=""><% }else{ %><input readOnly name="from" value="<%=fromdate%>" size="10"><% } %>
                               <td width="24" nowrap="nowrap"> 
                                <input onclick="c2.popup('from');" type="button" value="...">
                         </td>
                              <td width="65" nowrap="nowrap" align="right"><font size="2" face="Arial">
                           <bean:message key="corporate.Tdate" />: 
                          </td>
                           <td width="78" nowrap="nowrap" >
                        <%  if(toDate==null){ %><input readOnly name="to" size="10" value=""><% }else{ %><input readOnly name="to" value="<%=toDate%>" size="10"><% } %>
                                </td>
                                <td width="62" nowrap="nowrap">
                                <input onclick="c2.popup('to');" type="button" value="...">
                      </td>
                                 <td width="290" nowrap="nowrap">
                     <input type="submit" value='<bean:message key="Reports.View"/>' name="B1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
      </td>
          </tr>
          </table> 
          <p></p>  
        <table  border="0" width="900" cellpadding="0" cellspacing="0">
  <tr>
	 <td width="120" nowrap="nowrap">&nbsp;</td>
	 <td width="770" nowrap="nowrap">
       <%
        if(var==null && var3==null)  
         {   
       %>
       <table border="0" class="gridStyle" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
          <tr>
          <td  class="gridStyle-message" align="center" valign="middle">
             <b> <p style="margin-left: 9"><bean:message key="TradeVolumeInd.View" /></b></p>
            </td>
            </tr>
            </table>
      <% }else{ %>      
       		<%	
       			log.info("before firing query");
       		    ci.setVector_traded_volume(fromdate,toDate,var,var3,trd_volume);  
       			Vector v = ci.getVector_traded_volume();
       			log.info("after firing query vector size is "+v.size());
      			var1=request.getParameter("filter");  
       			 if(v.size()==0)  
         		 {
       %>
       
       <table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
          <tr>
          <td  bgcolor="#cacaca" align="center" valign="middle">
             <b><p style="margin-left: 9"><font size="5" color="blue" face="Arial"><bean:message key="StockPerformance.selDataAvai" /></b></p>
            </td>
            </tr>
            </table>
      <% }else{ 
      			int dir=0,dir1=0,dir2=0,dir3=0,dir4=0,dir5=0,dir6=0;
       			if(fieldno==1){
          			dir=sort.getcount();
                 	v=sort.SetOrderSort(v,1,3);
                }
          		if(fieldno==2){
          			dir1=sort.getcount1();
                 	v=sort.SetOrderSortNo(v,2,3);
                }
                          		
      %>      		
        <table border="0" class="gridStyle" width="75%" cellspacing="0" cellpadding="5">
          <tr>
            <td width="35%" class="gridStyle-header" align="center" valign="middle"><a href="../pages/TradedVolumeInd_exch.jsp?filter=<%=var1%>&B1=View&traded_volume=<%=trd_volume%>&D1=<%=var%>&D2=<%=var3%>&from=<%=fromdate%>&to=<%=toDate%>&FieldNo=1&ColNo=3" onmouseover="window.status='';return true"><bean:message key="stockmaster.stockName" /></a> 
              <%  
				if(fieldno==1){
				if(dir%2==0){  %>
				 <img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           <% }else{ %>
             <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
          <%  } 
          } %></td>
           <td width="30%" class="gridStyle-header" align="center" valign="middle"><a href="../pages/TradedVolumeInd_exch.jsp?filter=<%=var1%>&B1=View&traded_volume=<%=trd_volume%>&D1=<%=var%>&D2=<%=var3%>&from=<%=fromdate%>&to=<%=toDate%>&FieldNo=2&ColNo=3" onmouseover="window.status='';return true"><bean:message key="TradeVolumeInd.Traded" /></a>
               <%  
				if(fieldno==2){
				if(dir1%2==0){  %>
				 <img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           <% }else{ %>
             <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
          <%  } 
          } %></td>                   	         
          </tr>
    
          <% 
          Iterator i=v.iterator(); 
          log.info("1");
           int count=0;
           while(i.hasNext())
           {  
            	count++;
            	String id=(String)i.next();
            	String temp="../pages/stockmaster2.jsp?s_stockid="+id; 
           		if(count%2!=0)
           		{	
           			
          %>     
        <tr>
            <td width="35%" class="gridStyle-odd">
            <% 
            		if(flag==true){            		
            %>
              <p style="margin-left: 5; margin-right: 5"><a href="<%=temp%>" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="1"><%= i.next() %></a> </p>
           <% }else{ %>
            <p style="margin-left: 5; margin-right: 5"><%= i.next() %> </p>
           <% } %>
            </td>
            <td width="20%" align="right" class="gridStyle-odd">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric((String)i.next()) %></p>
            </td>                   
         </tr>
          <% }else{ %> 
         		 <tr>
            <td width="35%" class="gridStyle-even">
             <% 
            		if(flag==true){            		
            %>
              <p style="margin-left: 5; margin-right: 5"><a href="<%=temp%>" onmouseover="window.status='';return true"><%= i.next() %></a> </p>
           <% }else{ %>
            <p style="margin-left: 5; margin-right: 5"><%= i.next() %> </p>
           <% } %>
            </td>
            <td width="20%" class="gridStyle-even" align="right">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric((String)i.next()) %></p>
            </td>            
         </tr>
          <% }
          }
         }
         }%>           
      </td>
    </tr>
    </table>
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

</body><script language="javascript">
function test2()
{
	document.forms[0].submit();
}
function test1(){
	document.forms[0].submit();
}
</script>
<script language="javascript">

</script>
</html>