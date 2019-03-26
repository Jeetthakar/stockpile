
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
<html>
<head>
<html:base/>
</head>
<body onload="initialize()" >
<link href="StyleSheet.css" rel="stylesheet" type="text/css">
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
		          				<b>	<bean:message key="IndexCompareOHCL.title" /></b>
		          				</font>
		         	</td> 
	          </tr>
</table>
         <form action="IndexCompareOHLC.jsp">
          <table width="656" >
         	<tr>
         		<td width="120">&nbsp;</td>
         		<td width="86" align="left" nowrap="nowrap">
         
          <% 
	 		String chkchecked=request.getParameter("check");
	 		 String fromdate = request.getParameter("from");
        String toDate  =request.getParameter("to");
	 		log.info("chkchecked is "+chkchecked);
	 	//	 org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
	ComposeIndex ci=ConnectInit.getComposeIndex();
	 %>
	   
	    <%
			String[] var=request.getParameterValues("D1");
			String url = "";
			if(var != null)
			{
				for(int i=0;i < var.length;i++){
						
						url = url + "&D1=" + var[i];
				}
				
			}
	%>
	<%String pr = null;
         pr = request.getParameter("Pr");   // Check if clicked on printer friendly link.
    	try{
		    if(pr.equals(null))
    	       { pr = "N";} 
        	   }catch(Exception e){pr = "N";}
		  if(!pr.equals("Y"))  
           { %>
          <font size="2" face="Arial">
           <bean:message key="Index.select" />
        </td>
        <% } 
        else{
               	String str1=request.getParameter("D1");
                String str2=request.getParameter("D2");
               	%>
               
               	<table border="0" width="600"  cellspacing="0" cellpadding="3"  height="30">
       
        <tr>
        	<td align="left" width="10" nowrap="nowrap">
        		&nbsp;
          	</td>
        	<td align="right" width="500" nowrap="nowrap" >
     <p align="left"><font size="2">
     	<font face="Arial" size="2" ><b><bean:message key="corporate.Fdate" /></b></font>&nbsp;<font face="Arial" size="2" color="blue"><%= fromdate%></font> &nbsp;&nbsp; &nbsp;&nbsp;<font face="Arial" size="2" ><b><bean:message key="corporate.Tdate" /></b></font>&nbsp;&nbsp;<font face="Arial" size="2" color="blue"><%=toDate%></font>
     </p></font>
     </p><p align="left"> 
     </td>
         </tr>
         </table> 	
             
              <% 	}
               	%>
        
      <%
        
        log.info("before firing query");
       AdjustDecimal ad = ConnectInit.getAdjustDecimal();
     //   org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
        org.jfree.chart.demo.servlet.IndexCompareOHLC comp=new org.jfree.chart.demo.servlet.IndexCompareOHLC();
        org.jfree.chart.demo.servlet.FieldSort sort=new org.jfree.chart.demo.servlet.FieldSort();
        ci.setVector_indexlist(chkchecked); 
        log.info("1"); 
        int colcount=0;
        String strcolcount=(String)request.getParameter("clmncount");
      	if(strcolcount!=null){
      		colcount=(int)Integer.parseInt(strcolcount);  
      	}    
        String field,col;
        int fieldno,colno;
        field=(String)request.getParameter("FieldNo");
         col=(String)request.getParameter("ColNo");
        if(col==null)
        {
        	col="5";
        }       
        if(field==null)
        {
        	field="0";
        }
        fieldno=Integer.parseInt(field);
        colno=Integer.parseInt(col);
       if(var!=null && var.length>0)
        {
       		 log.info("array length is "+var.length);
        	for(int n=0;n<var.length;n++){
        		log.info(var[n]);
      	    }
        }
        
       	Vector v = ci.getVector_indexlist();    	
    	Iterator i=v.iterator();  
    	%>
    	
		 
		<% if(!pr.equals("Y"))  
           { %>       
	     <td width="423" nowrap="nowrap"> 
        <select  name="D1"  size="3" multiple id="Rem">         
           <%      int requestID= 0;
                     try{
	                      requestID = Integer.parseInt(request.getParameter("D1"));
                     	}catch(Exception e){}
    
                     while(i.hasNext())
                      {   
                        int id1 = Integer.parseInt(i.next().toString()); 
                        boolean flag=false;
                        if(var!=null && var.length>0){
                        	for(int l=0;l<var.length;l++){
                        		int id=(int)Integer.parseInt(var[l]);
                        		if(id==id1){
                        			flag=true;
                        			break;
                        		}
                        	}
                        } 
                        if(flag==true){                    
                       %>
                        <option selected value="<%= id1 %>"><%= i.next() %></option>
                        <% }else{ %>                  
 	                   	<option value="<%= id1 %>"><%= i.next() %></option>
                       <% }
						  } %>
        		</select>
        		 </select>
         		</td>
         	</tr>
         </table>        
              <br/>          
         <table width="844" >
         	<tr>
         		<td width="149" align="right" nowrap="nowrap"> 
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
             <bean:message key="corporate.Fdate" />
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
                          <bean:message key="corporate.Tdate" />
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
                     <input type="submit" value='<bean:message key="Reports.View"/>' name="B1" onclick="return viewFunc();">
		</td>
		</tr>
		</table>
				</p>
		<% 		if(var != null)
				{
					try{
						String astr = null;
                           	astr = "./IndexCompareOHLC.jsp?Pr=Y"+url+"&from="+fromdate+"&to="+toDate; 
                        	String excel = "../pages/FileDownload.jsp?type=10"+url+"&from="+fromdate+"&to="+toDate+"&filename=IndexCompareOHLC.xls";
							String str_url = "../pages/EmailReport.jsp?switch_type=10&cas=10&rname=IndexCompareOHLC.xls&from="+fromdate+"&to="+toDate;
                        %> 
                        <p></p>
                        <table width="900" cellpadding="0" cellspacing="0">
                        <tr>
                        <br/>
                        <td width="350" nowrap="nowrap" align="right">
                        <font size="1">
                        	<a href="javascript:popprinter('<%=astr%>');"><bean:message key="LatestDivisor.printerf" /></a>
                        	
                        </td>
                        <td width="110" nowrap="nowrap">	
							
							 <font size="1">
							 <a href=<%= excel %>>
							<bean:message key="LatestDivisor.downloade" /></a>
							</font>
							</td>
						 <td width="367" nowrap="nowrap">	
							 <font size="1">
							<a href= <%= str_url %>>
							<bean:message key="LatestDivisor.emailr" /></a>
							</font>
							</td>	
						</tr>
						
					</table>
					<p></p>
							
          <%			}catch(Exception e){log.info("refress the page.");}
          		}		
          	}%>
<table  border="0" width="900" cellpadding="0" cellspacing="0">
  <tr>
	 <td width="10" nowrap="nowrap">&nbsp;</td>
	 <td width="870" nowrap="nowrap">		
				
				<% if((var==null)){ %>
				<table border="0" align="center" width="631" height="222" cellspacing="0" cellpadding="0">
          <tr>
          <br/>
          <td  bgcolor="#cacaca" align="center" valign="middle" nowrap="nowrap">
              <p style="margin-left: 9"><b><font face="Arial" color="blue" size="5">
             <bean:message key="IndexCompareOHCL.messaged" /></b></p></font>
            </td>
            </tr>
            </table>
      <% }else {  						
       				 	comp.setVector_compareOHLC1(var,fromdate,toDate);
						Vector v3=comp.getVector_compareOHLC();	
						log.info("vector v3 size is "+v3.size());	
						
						if((v3.size())==0)
						{
						
				%>
				<br/>
				<table border="0" align="center" class="gridStyle" width="631" height="222" cellspacing="0" cellpadding="0">
          <tr >
          <td  bgcolor="#cacaca" align="center" valign="middle" nowrap="nowrap">
              <p style="margin-left: 9"><font face="Arial" color="blue" size="5">
             <bean:message key="IndexCompareOHCL.ndata" /></a></font></p>
            </td>
            </tr>
            </table>
      <% }else {  
      				int dir=0;
      				log.info("before arrange vector compare OHLC");
      				Vector v11=comp.ArrangeVectorCompareOHLC(v3); 
      				Object ci2 = null;
					session.setAttribute("ci2",new Vector(v11));
      				Vector vid=comp.getVector_vid();
      				int column=(vid.size()*4)+1;
      				String clmno=new Integer(column).toString();
      				String idcorr="D1=";
      				if(vid.size()!=0){
	 				for(int w=(vid.size()-1);w>=0;w--){
						if(w==(vid.size()-1)){
							idcorr="D1="+(String)vid.get(w);
						}else{
								idcorr=idcorr+"&D1="+(String)vid.get(w);								
						}
					}	
					}
					if(fieldno==0)
					{
				 		dir=sort.getcount();
				 		v11=sort.SetOrderSortDate(v11,0,column);
				 	}						
      	%>   
	 </p><p >
 
	 
        <table border="0" width="96%" class="gridStyle" cellspacing="1" cellpadding="2">
          
          <tr>
            <td width="12%" class="gridStyle-header"  colspan="1"  align="left" valign="middle" nowrap="nowrap">
              </td>
           <%   for(int p=0;p<(vid.size());p++){  
           			String indexname=ci.getIndexName((String)vid.get(p));
           			Object vec_ind = null;
					session.setAttribute("vec_ind",new Vector(vid));
              %>
            <td width="28%" class="gridStyle-header" colspan="4" align="center" valign="middle" nowrap="nowrap"><%=indexname%>
              </td>
              <% } %>            
          </tr>
        
         <tr>
          <%if(!pr.equals("Y")) {%> 
            <td width="7%" class="gridStyle-header" align="center" valign="middle" nowrap="nowrap"><a href="IndexCompareOHLC.jsp?<%= idcorr %>&from=<%=fromdate%>&to=<%=toDate%>&B1=View&FieldNo=1&ColNo=<%=column%>" onmouseover="window.status='';return true"><bean:message key="corporate.Date" />
             <%}else{%>
             <td width="7%" class="gridStyle-header" align="center" valign="middle" nowrap="nowrap"><bean:message key="corporate.Date" />
             <%}%>
              </a>
			 <%  
				if(colcount==0){
				dir=sort.getcount();
				if(dir%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="10" align="middle" height="14">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="10" align="middle" height="14">
         		 <%  } 
         		 } %></td>
			
         		  <%   for(int p=0;p<vid.size();p++){    %>
            <td width="7%" class="gridStyle-header" align="center" valign="middle" nowrap="nowrap"><bean:message key="IndexCompareOHCL.open" /></td>
            <td width="7%" class="gridStyle-header" align="center" valign="middle" nowrap="nowrap"><bean:message key="IndexCompareOHCL.high" /></td>
            <td width="7%" class="gridStyle-header" align="center" valign="middle" nowrap="nowrap"><bean:message key="IndexCompareOHCL.low" /></td>
            <td width="7%" class="gridStyle-header" align="center" valign="middle" nowrap="nowrap"><bean:message key="IndexCompareOHCL.close" /></td>
             <%  }  %>				
          </tr>         
          <%   
          		log.info("size of v11 in jsp page "+v11.size()+" vid size is "+vid.size());
          		 Iterator i11=v11.iterator();               
				int count=0;
           while(i11.hasNext())
           {  
          			count++;
            	if(count%2!=0)
            	{
          %> 
          	<tr>  
          	 <td width="12%" align="left" class="gridStyle-odd" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= i11.next() %></p>
            </td>
            <%   for(int p=0;p<(vid.size());p++){  %>
            <td width="7%" align="right" class="gridStyle-odd" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric((String)i11.next()) %></p>
            </td>
            <td width="7%" align="right" class="gridStyle-odd" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric((String)i11.next()) %></p>
            </td>
            <td width="7%" align="right" class="gridStyle-odd" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric((String)i11.next()) %></p>
            </td>
            <td width="7%" align="right" class="gridStyle-odd" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric((String)i11.next()) %></p>
            </td>             
            <% 	}  %>
            <tr>  
            <%	}else{ %>	            	
          	 <td width="12%" class="gridStyle-even" align="left" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5" ><%= i11.next() %></p>
            </td>
            <%   for(int p=0;p<(vid.size());p++){  %>
            <td width="7%" class="gridStyle-even" align="right" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5" ><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric((String)i11.next()) %></p>
            </td>
            <td width="7%" class="gridStyle-even" align="right" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric((String)i11.next()) %></p>
            </td>
            <td width="7%" class="gridStyle-even" align="right" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric((String)i11.next()) %></p>
            </td>
            <td width="7%" class="gridStyle-even" align="right" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric((String)i11.next()) %></p>
            </td> 
           
            <% } %>	
             </tr> 
       <% }
		}
	}
	}
    %> 
    </form>   
  </tbody>
</table>
</td>
</tr>
</table>

<table border="0" width="100%" cellspacing="1" cellpadding="0">
  <tr>
    <td width="28%" valign="top">
      </td>
    <td width="72%" valign="top">
     </td>
  </tr>
</table>
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
	if((document.forms[0].from.value)=="")
	document.forms[0].from.value= td+ s + mnth + s + yr;
	if((document.forms[0].to.value)=="")
	document.forms[0].to.value= td+ s + mnth + s + yr;
}	
function checkdatecurrent(objName)
{
	var datefield = objName;
	var strMonth;
	var strYear;
	var strDate;
	var strDateArray;
	var intElement;
	var strSeparatorArray = new Array("-"," ","/",".");
	strDate = datefield.value;
    var intday;var int_td;var int_mnth;var int_yr;
	var int_month;
	var intYear;
	var today = new Date();
	var td = today.getDate();
	var mnth = today.getMonth(); 
	mnth=mnth+1;
	var yr = today.getFullYear();
	int_td=   parseInt(td, 10); 
	int_mnth=  parseInt(mnth, 10); 
	int_yr=   parseInt(yr, 10); 
	for (intElement = 0; intElement < strSeparatorArray.length; intElement++) {
	if (strDate.indexOf(strSeparatorArray[intElement]) != -1) {
			strDateArray=strDate.split(strSeparatorArray[intElement]);
			if (strDateArray.length != 3) {
				err = 1;
				alert(" DateArray length < 1: err :" + err);
				return false;
			}
			else {
			strDay = strDateArray[0];
			strMonth = strDateArray[1];
			strYear = strDateArray[2];
			}
		  }
		}
		intday = parseInt(strDay, 10);
		int_month=parseInt(strMonth,10);
		intYear= parseInt(strYear,10);
		if(intYear>int_yr)
		{
			return false;
		}
		if((intYear==int_yr)&&(int_month>int_mnth))
		{
			return false;
		}
		if((intYear==int_yr)&&(int_month==int_mnth)&&(intday>int_td))
		{
			return false;
		}
		else {
			return true;
	   }
}
function test1(){
	document.forms[0].submit();
}
function popprinter(url)
{
	newwindow=window.open(url,'name','height=500,width=900,scrollbars=yes,left=80,top=130,menubar=yes,status=yes');
	if (window.focus) {newwindow.focus()}
}
function viewFunc(){
	var objTo=document.forms[0].to;
	var i = 0;var count=0;
	var fields = new Array();
	var lhsSelect = document.getElementById("Rem");
	for(var index = 0; index < lhsSelect.options.length; index++)
	{
		if ( lhsSelect.options[index].selected == false )
		{
			continue;
		}
		else{
			count++;
			if(count==2)break;
		}
	}
	if(count < 2){
		fields[i++] ="Select Index is required";
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
    if((checkdatecurrent(objTo))==false)	
	{
	alert("ToDate should be Less Than CurrentDate.");
	objTo.focus();
	objTo.select();
	return false;
	}
         else {
         	return true;
         }
}
</script>
</html>