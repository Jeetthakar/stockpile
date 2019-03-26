<!-- Report Preferences------------------------- -->
<!---- Neha --------------------------------- -->

<%@ page language="java" import="java.text.*" %>
<%@ page import="java.util.*" %>

<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>

<html>
<head></head>
<%
     Date curr=new Date();      
     SimpleDateFormat ft=new SimpleDateFormat("dd-MM-yyyy");
     String to=ft.format(curr);
  
     int diff=120;
     
          
       long newd=diff*24*60*60*1000L;
          
         
          
     java.util.Date s4= new java.text.SimpleDateFormat("dd-MM-yyyy").parse(to);
     long t2=s4.getTime();
     long t1=t2-newd; 
     Date s1=new Date(t1);
     SimpleDateFormat ft1=new SimpleDateFormat("dd-MM-yyyy");
     String from=ft1.format(s1);
     log.info("todate"+to);
     log.info("fromdate"+from);
     log.info("newd====>"+newd);
     log.info("t1====>"+t1);
     log.info("t2====>"+t2);
     
    
     
 %>

<body bgcolor="#aabbcc" >
<form action=""  name="f1" method="post">
<p>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 </p>
<p>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font face="arial-helvitica" size="4" color="black" ><b><i>Report Preferences</b></i></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 </p>
 <p>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 </p>
 <table  border="0" cellspacing="0" cellpadding="0" width="100%" >
  
  <tr>
<td width="25%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font face="arial-helvitica" size="3"><b>Select Report</b></font>  </td>
<td width="25%" >
<select  name="report" style="width=175">
<option value="Report1">Report1</option>
<option value="Report2">Report2</option>
<option value="Report3">Report3</option>
<option value="Report4">Report4</option>
				</select>
</td>
</tr>

<tr>
<td width="25%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font face="arial-helvitica" size="3"><b>Select Date</b></font>  </td>
<td width="25%">&nbsp;&nbsp;<input type="radio" name="selectedItem" value="abc">&nbsp;&nbsp; <font face="arial-helvitica" size="3"><b>Day</b></font>&nbsp;&nbsp;<input type="radio" name="selectedItem" value="def">&nbsp;&nbsp;<font face="arial-helvitica" size="3"><b>Month</b></font> 
&nbsp;&nbsp;<input type="radio" name="selectedItem" value="kgj" >&nbsp;&nbsp; <font face="arial-helvitica" size="3"><b>Year</b></font> </td> 
<td width="25%" >

<select name="difference"  style="">
	<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
</select>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font face="arial-helvitica" size="3"><b>Day Difference</b></font>	
</td>				
 <td width="25%" ><input type="text" name="diff"  size="3"  value="<%=diff%>" /></td>
 
</tr>

<tr>
<td width="25%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font face="arial-helvitica" size="3"><b>Select Filter</b></font>  </td>
<td width="25%" >
<select name="selectfilter" style="width=175" >
		<option value="Exchange">Exchange</option>
        <option value="Indexwise">Indexwise</option>      
</select>			
 
</td>
</tr>

<tr>
<td width="25%" >&nbsp;&nbsp;&nbsp;&nbsp; </td>
<td width="25%" >&nbsp;&nbsp;&nbsp;&nbsp; </td>
</tr>
<tr>
<td width="25%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font face="arial-helvitica" size="3"><b>Select Exchange/Index </b></font>  </td>
<td width="25%" >
<select name="selecttype" style="width=175" size="3" >
		<option value=""></option>
        <option value=""></option>
 </select>			
 </td>
</tr>

<tr>
<td width="25%" >&nbsp;&nbsp;&nbsp;&nbsp; </td>
<td width="25%" >&nbsp;&nbsp;&nbsp;&nbsp; </td>
</tr>

<tr>
<td width="25%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  </td>
<td width="25%" >&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="submit"  value="submit" >&nbsp;&nbsp;&nbsp;&nbsp; 
<input type="button" name="exit" value="exit" ></td>

</tr>

<tr>
<td width="25%" >&nbsp;&nbsp;&nbsp;&nbsp;<font face="arial-helvitica" size="3"><b>From Date</b></font><input type="text" name="formdate"  size="10"  readonly="readonly"  value="<%=from%>" ></td> 
<td width="25%" >&nbsp;&nbsp;&nbsp;&nbsp;<font face="arial-helvitica" size="3"><b>Current Date</b></font><input type="text" name="currdate"  size="10"  readonly="readonly"  value="<%=to%>" ></td> 
</tr>
</table>
</form>
 </body>
</html>








