<%@ page language="java" import="app.*,java.sql.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.lang.*,app.*,java.util.*" %>
<%@ page import = "java.io.*" %>
<%@ page import = "org.jfree.chart.demo.servlet.DynamicRerpots" %>
<%@page import="org.apache.log4j.Logger"%><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<html>
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" /> 
<title>Dyanamic Report Generation</title>
</head>
<body>
<form action="DynaReport.jsp">
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

<table border="0" width="100%" cellspacing="0" cellpadding="0" height="204">
<%
//	Logger Logging = Logger.getLogger("DynaReport.jsp");
	String exec = null;
     exec = request.getParameter("sqltext");   
     if(exec==null){
     exec="";
     }
  //   Logging.debug("exec====="+exec);
     String filename = request.getParameter("fname");
  //   Logging.debug("File name is1 "+filename);
     int file_s = 2;
     String file_val=request.getParameter("file_query");
  //   Logging.debug("File_s  "+file_val);
     if(filename!=null){
     file_s = 1;
     }
     String fquery=null;
  //   Logging.debug("File_s  "+file_s);
     if(file_s==1){
     	org.jfree.chart.demo.servlet.QueryBuilder ip= new org.jfree.chart.demo.servlet.QueryBuilder();
     //	Logging.debug("File name is "+filename);
     	fquery = ip.retquery( filename);
     	fquery = fquery.replace('ÿ',' ');
    // 	Logging.debug("query from file  "+fquery.replace('ÿ',' '));
     	exec=fquery;
     }
     else
     	fquery=exec;
     %>
     
  <tr>
    <td valign="bottom" align="left" height="40" colspan=4>
    <p align="left" style="margin: 0"><b> &nbsp;&nbsp;Dynamic Report Generation </b></p>
      <hr>
    </td>
    <%--<td width="25%" valign="bottom" align="left" height="40"><p><hr></p>
    </td>
    <td width="11%" valign="bottom" align="left" height="40">
     
    </td>--%>
  </tr>
  
  
  
  <tr>
    <td width="65%" valign="top" align="left" height="116">
      <p style="margin: 0" align="left">&nbsp;&nbsp; <b>SQL Text</b>&nbsp;&nbsp;
<%      if(file_s==1){%>
      <textarea  name="sqltext"  rows="6" cols="80" tabindex="1" ><%= fquery
%></textarea>
<%}
if(file_s==2){%>
<textarea  name="sqltext"  rows="6" cols="80" tabindex="1" ><%= exec%></textarea>
<%} %>
</td>
    <td width="25%" height="10" valign="top" align="left">
     </td>
    <td width="11%" height="116">
      <p style="margin: 0"></td>
    
  </tr>
  
    
    <tr>
    <td    colspan=4 align=left>
    <table width=79% align=left>
    	<tr>
    		<td><input type="submit" value="Execute" name="exe_but" tabindex="3"  onClick="return checkExecute() "></td>
    		<td><input type="button" value="Clear Text" name="clr_but" tabindex="2" onClick="return ClearText()"></td>
    		<td><p style:margin=100"></p></td></form>
    		<td align=left>
    		<form name="formTwo" ENCTYPE="multipart/form-data" action="FileUploadQB1.jsp?DynaBrowse=1" method="POST">
            </td>
    		<td align=left><b>Select query file:</b><input type="file" name="userfile" size="40"  ><input type="submit" value="Submit" name="sub" tabindex="2" onClick="return checkFile()"></td>
    		<td align=left>
     		</td>
		</form>
    	</tr>	
    </table>
   <td>     
    <%
  if(exec != null)
     {
     	session.setAttribute("query1",exec);
  %>
  
  <td  height="10%">
  </td>
     
     </tr>
  	<tr>
  		<table border="1" width="90%" bgcolor="white"  align="center" 
			cellpadding="2" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
  		</tr>
  	
  		<%--
  		<p>
  		
     <form name="formTwo" ENCTYPE="multipart/form-data" action="FileUploadQB1.jsp?DynaBrowse=1" method="POST">
       &nbsp;&nbsp; Select query file  <input type="file" name="userfile" size="30"  >
       <input type="submit" value="Submit" name="sub" tabindex="2" onClick="return checkFile()">
     </form>
     
</p>
--%>
</tr>
  <% } 
  else{%>
  
 <% } 
 	 if(file_s==2){
     org.jfree.chart.demo.servlet.DynamicRerpots ip= new org.jfree.chart.demo.servlet.DynamicRerpots();
      if(exec != null && !exec.equals(""))
      {
      	// String excel_path = "../pages/FileDownloadDyna.jsp?type=1";
     	// String query_path = "../pages/FileDownloadDyna.jsp?type=2";
     	String excel_path = "../FileDownloadDyna.jsp?type=1";
     	String query_path = "../FileDownloadDyna.jsp?type=2";
     	
     %>
   	  <font color="blue" size="1"><a href= <%= excel_path %>>Download CSV file</a></font>
      <font color="blue" size="1"><a href= <%= query_path %>>Save Query file</a></font>
      <font color="red" style="bold" size="3">

      <tr><p><hr></p></tr>
      <tr>
      <%      	
      	ArrayList arrcolumns = ip.getColumns(exec);
      	ArrayList arrdata = ip.getData(exec);
      	int countcol = ip.columncount(exec);
      //	Logging.debug(" Array size"+arrcolumns.size());
		int array_size=arrcolumns.size();
		if(arrcolumns != null)
      	{
      		Iterator itr = arrcolumns.iterator();
	       	int k=0;
	       	Iterator itr2 = arrdata.iterator();
	       	int k2=1;
	       	while(itr.hasNext()){
	       		k++;
   %>
   <td width="15%" nowrap="nowrap" align="center"><%=itr.next() %></td>	
	<% 	} 
	  %>
	  	</tr>
	  	<tr>
	 <%   while(itr2.hasNext()){
	      if(k2 == (countcol+1)){
	 //	  Logging.debug("value of k : "+k2);
	 %>						
	 <tr>
	 <td width="15%" nowrap="nowrap" align="center">
	 <font face="Arial" color="blue" size="2"><%=itr2.next()%></font>
	 </td>
	 <%  }else if((k2)%countcol == 0){ 
	 %>						
	<td width="15%" nowrap="nowrap" align="center">
	<font face="Arial" color="blue" size="2"><%=itr2.next()%></font>
	</td>
	</tr>
	 <% }else{
	 %>
	 <td width="15%" nowrap="nowrap" align="center">
	 <font face="Arial" color="blue" size="2"><%=itr2.next()%></font>
	 </td>
	 <%} k2++; }%>
	 	</tr>
	 	</table>
	 	</font>
    </td>
    <td width="11%" height="21">
      <p style="margin: 0">
    </td>
  </tr>
  </table>
  	<% } 
	 if(array_size==0){%>
          <font face="Arial" color="red" size="3" >Error In SQL : <%=ip.getError((String)session.getAttribute("query1"))%> </font>
     <% }}}%>
</body>
<script language="JavaScript">

function checkExecute(){
var textArea=document.forms[0].sqltext.value;

var trimText=trimAll(textArea);

if(trimText==""){
alert("please write query in text Area Or select Query File");

return false;
}else {
	var textQuery=trimText.substring(0,6);
	if((textQuery=="select")||(textQuery=="SELECT")||(textQuery=="Select")){
	return true;
	}else{
	alert("only select query is allowed");
	return false;
	}
}

}



function trimAll(sString) 
{
	while (sString.substring(0,1) == ' ')
	{
	sString = sString.substring(1, sString.length);
	}
	while (sString.substring(sString.length-1, sString.length) == ' ')
	{
	sString = sString.substring(0,sString.length-1);
	}
return sString;
}

function checkFile(){
var fileName=document.forms[1].userfile.value;

if(fileName==""){
alert(" Please select the query file ");
return false;
}else{

return true;
}

}


function test()
{
	document.forms[0].sqltext.value=document.forms[0].sqltext.value;
}

function ClearText(){
document.forms[0].sqltext.value="";
}
</script>
</html>

