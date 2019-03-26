
<%@page import="org.apache.log4j.Logger"%><%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page language="java" import="app.*,java.sql.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.lang.*,java.util.*" %>
<%@ page import = "java.io.*" %><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<html>
<head>
 <link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />
<%--<link href="../pages/StyleSheet.css" rel="stylesheet" type="text/css">--%>
<title>Query Builder</title>
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
</head> 

<body>

<form action="QueryBuilder.jsp">
<%--<input type="button" value=" CHECK " name="CHECH"  onclick="return columnValueCheck()">--%>
<table border="0" width="100%" height="1" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" colspan="3" height="29" valign="top" align="left">
      <p style="margin-top: 0; margin-bottom: 0"><b><font face="Arial" size="2">&nbsp;&nbsp;Query
      Design</font></b></p>
      <hr>
    </td>
  </tr>
  <tr>
  <% 	
 // Logger Logging = Logger.getLogger("QueryBuilder.jsp"); 
  String press1 = new String("10");
  		String press2 = new String("20");
  		int file_s = 2;
  		String filename = "";
  		try{
  			file_s = Integer.parseInt(request.getParameter("file_query"));
  			filename = request.getParameter("fname");
  			//Logging.debug("inside Jsp file path is >>>>>>  "+filename);
  		}catch(Exception y) {file_s = 2;}
  		int p2 = Integer.parseInt(press2);
  		try{
     	press1 = new String(request.getParameter("tab_press"));
     	}catch(Exception e){//Logging.debug("hfkhfhsdfh 1  "+e);
     		press1 = new String("10");
  			press2 = new String("20");}
  			
     	String press2_t = null;
     	String Query = "Select ", Query_tables = "";
     	press2_t = request.getParameter("col_add");
     	if(press2_t !=null)
     	   press2 = press2_t;
     	String tab_arr = null;
     	String [] tab_arr1 = null;
     	tab_arr1 = request.getParameterValues("add_tables");
     	String columnNameCheck=null;
     	columnNameCheck=request.getParameter("D5");
     	
     	if(tab_arr1 != null)
     	{
     		int num_col4 = tab_arr1.length;
     	for(int j = 0; j< num_col4; j++)
		{  
		   if(j == 0)
		   	Query_tables = Query_tables + " " + tab_arr1[j] + " ";
		   else
		   	Query_tables = Query_tables + "," + tab_arr1[j] + " ";
		}
		
        }
  %>
    <td width="51%" colspan="2" height="114" valign="top" align="left">
    
      <p style="margin-top: 0; margin-bottom: 6"><font face="Arial" size="2">&nbsp;&nbsp;&nbsp;
      Select tables&nbsp;&nbsp;&nbsp; <select name="add_tables"  multiple="True" size="5" >
       <%         org.jfree.chart.demo.servlet.QueryBuilder ip= new org.jfree.chart.demo.servlet.QueryBuilder();
					Vector vi  = ip.setTab_list();
        			Iterator i = vi.iterator();
        			String val = null; 
 					try
 					{
   						while(i.hasNext())
       					{     
       						val = i.next().toString().trim();
       %>
	                       	<option value="<%=val%>" ><%=val%></option>
	       		<%  	}
           			}catch(Exception e){//Logging.error("Error in select  "+e);
           			}
           		
				%>
      </select>&nbsp; <input type="button" value="   Add     " name="add_table" tabindex="2" onclick="tabpress();">
    
     
    
     </font></p>
     <p style="margin-top: 0; margin-bottom: 6"><font face="Arial" size="2">&nbsp;&nbsp;&nbsp;</font>
     
      <% 
    	if(!press1.equals("10") && !press2.equals("1"))
       {  
    %> 
    
       <script language="JavaScript">
       		document.forms[0].add_tables.disabled = true;
       		document.forms[0].add_table.disabled = true;
       </script>
      <p style="margin-top: 0; margin-bottom: 2"><font face="Arial" size="2">&nbsp;&nbsp;Select columns&nbsp;&nbsp;            
     <select name="add_tables1"  multiple="True" size="5" >
    <option value="*">*</option>
     <%       	Vector vi2 = ip.setcol_list(tab_arr1);
     			session.setAttribute("vec",vi2);
        			Iterator i2 = vi2.iterator();
        			String val2 = null; 
        			
 					try
 					{
   						while(i2.hasNext())
       					{     
       						val2 = i2.next().toString().trim();
       %>
	                       	<option value="<%=val2%>" ><%=val2%></option>
	       		<%  	}
           			}catch(Exception e){//Logging.error("Error in select2  "+e);
           			}
           		
				%>
     </select>
     </font>
      <input type="button" value="    Add   " name="Addcol" onclick="columns_add();">
     <% } %> 
      <input type="hidden" name="col_add" value="<%= press2%>" >
     
     </td>
    <td width="49%" height="114" valign="top" align="left">
     
      </td>
  </tr>
  <tr>
    <td width="100%" height="1" valign="top" align="left" colspan="3">
      <p style="margin-top: 0; margin-bottom: 0"><b><font size="2" face="Arial">&nbsp;Where</font></b></p>
      <hr>
    </td>
  </tr>
  <tr>
    <td width="100%" height="4" valign="top" align="left" colspan="3"><font face="Arial" size="2">&nbsp;&nbsp;&nbsp;
      Column&nbsp;
     <% 
     	if(press1 != null && press2.equals("1"))
        {	
        	//String [] col_arr3 = null;
        	//col_arr3 = request.getParameterValues("add_tables1");
        	Vector ve2 = (Vector)session.getAttribute("vec");
        //	Logging.debug("vectorrrrr...  "+ve2.size());
        	Iterator i7 = ve2.iterator();
        	//int num_col = 0;
        	//num_col = col_arr3.length;  
        	 %>
      <select size="1" name="D5" >
       
        <% String val7 = null; 
 					try
 					{
   						while(i7.hasNext())
       					{     
       						val7 = i7.next().toString().trim();
       %>
	                       	<option value="<%=val7%>" ><%=val7%></option>
	       		<%  	}
           			}catch(Exception e){//Logging.error("Error in select2  "+e);
           			}
           		
				%>
					      	
      </select>
      <% } else{//Logging.error("at else.......  "+press2); %>
      <select size="1" name="D5">
        <option>Add Columns</option>	      	
      </select>
      <% } %>
      </font>&nbsp;&nbsp; <font face="Arial" size="2">
      Operator 
      <select size="1" name="D4">
        <option>Conditions</option>
        <option value="=">=</option>
        <option value=">=">>=</option>
        <option value="=<"><=</option>
        <option value="!=">!=</option>
        <option value="in">IN</option>
        <option value="exist">EXIST</option>
      </select></font>&nbsp; <font face="Arial" size="2">
      Value 
      <% 
     // if (columnNameCheck!=null){log.info("================columnNameCheck"+columnNameCheck);}
     	if(press1 != null && press2.equals("1"))
        {	
        	Vector ve8 = (Vector)session.getAttribute("vec");
        	Iterator i8 = ve8.iterator(); 
      %>
      <%--<select size="1" name="D6">--%>
       
         <% String val8 = null; 
 					try
 					{
   						while(i8.hasNext())
       					{     
       						val8 = i8.next().toString().trim();
       %>                  
	                       
	                       
	       		<%  	}
           			}catch(Exception e){//Logging.error("Error in select2  "+e);
         			}
           		
				%>	      	
      <%--commented</select>--%>
      <% } else{ %>
      <%--<select size="1" name="D6">
        <option>Add Columns</option>	      	
      </select>
      --%>
      <% } %>
        <input type="text" name="T1" size="10"><input type="button" value="   Add     " name="add_table2" onclick="addwhere();"></font></td>
  </tr>
  <tr>
    <td width="100%" height="1" valign="top" align="left" colspan="3">
      <p style="margin-top: 0; margin-bottom: 0"><font face="Arial" size="2"><b>&nbsp;</b></font></p>
      <p style="margin-top: 0; margin-bottom: 0"><font face="Arial" size="2"><b>&nbsp;Sort
      by</b></font></p>
      <hr>
      <p style="margin-top: 0; margin-bottom: 0"><font face="Arial" size="2">&nbsp;&nbsp;&nbsp;
      Column&nbsp;
       <% 
     	if(press1 != null && press2.equals("1"))
        {	
        	String [] col_arr3 = null;
        	col_arr3 = request.getParameterValues("add_tables1");
        	int num_col3 = 0;
        	num_col3 = col_arr3.length;
        	Vector ve98 = (Vector)session.getAttribute("vec");
        	Iterator i98 = ve98.iterator(); 
        	%>
      <select size="1" name="D7">
       
        <%
           
           for(int j = 0; j< num_col3; j++)
		   {  
		   if(j == 0)
		   Query = Query + " " + col_arr3[j] + " ";
		   else
		   Query = Query + "," + col_arr3[j] + " ";
		   }
		   String val98=null;
		   try
		   {
		   while(i98.hasNext())
		   {
		   val98=i98.next().toString().trim();
		    %>
			<%--<option value="<%=col_arr3[j]%>" ><%=col_arr3[j]%></option>--%>
			<option value="<%=val98%>" ><%=val98%></option>
        <% }
        }catch(Exception e){
		//Logging.error("Error in select2  "+e);
		
		}
        Query = Query + " from "+  request.getParameter("tables_t"); 
        %>		      	
      </select>
      <% } else{ %>
      <select size="1" name="D7">
        <option>Add Columns</option>	      	
      </select>
      <% } %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Order By</font>
      <select size="1" name="D9">
        <option value = "asc" selected>Ascending</option>
        <option value="desc">Descending</option>
      </select><font face="Arial" size="2"><input type="button" value="   Add     " name="add_table1" onclick="addsort();"></font>
      <p style="margin-top: 0; margin-bottom: 0">&nbsp;</td>
  </tr>
  <tr>
    <td width="100%" height="101" valign="top" align="left" colspan="2">
      <p style="margin-top: 0; margin-bottom: 0"><b><font face="Arial" size="2">&nbsp;</font></b><b><font face="Arial" size="2">SQL Syntax</font></b></p>
      <hr>
      <%String fquery = ""; 
        String ff = new String("m");
        //ff = ip.retquery( request.getParameter("userfile")).toString();
      	if(file_s == 1)
      	{
      		fquery = ip.retquery( filename);
      		fquery = fquery.replace('ÿ',' ');
    //  		Logging.debug("query from file  "+fquery.replace('ÿ',' '));
      	}		 	
      %>
      <p style="margin-top: 0; margin-bottom: 0"><font face="Arial" size="2">&nbsp;&nbsp;&nbsp;
      <%if(file_s == 1)
      	{ %>
      		<textarea rows="5" name="S4" cols="160"><%=fquery%></textarea>
      <%}else{ %>
      		<textarea rows="5" name="S4" cols="160"><%=Query%></textarea>
      <% } %>
      </font></p>
      <p style="margin-top: 0; margin-bottom: 0">&nbsp;&nbsp;&nbsp; 
      <input type="button" value="Execute" name="exec_query" onclick="final_execute();"></td>
      
      
      
      
  </tr>
  <tr>
    <td width="100%" height="34" valign="top" align="left" colspan="3">
      <p style="margin-top: 0; margin-bottom: 0">
    <% if(request.getParameter("final_exec") != null && !request.getParameter("final_exec").equals(""))
    
       { String exec1 = request.getParameter("S4"); 
    	 // String excel_path = "../pages/FileDownloadDyna.jsp?type=1";
    	 // String query_path = "../pages/FileDownloadDyna.jsp?type=2";
     	 String excel_path = "../FileDownloadDyna.jsp?type=1";
     	 String query_path = "../FileDownloadDyna.jsp?type=2";
     	session.setAttribute("query1",exec1);
     	org.jfree.chart.demo.servlet.DynamicRerpots ip2= new org.jfree.chart.demo.servlet.DynamicRerpots();
     	int countcol = ip2.columncount(exec1);
    //  	Logging.debug("column count is :  "+countcol);
      	if(countcol != 0)
        {
    %>
    	<font size="1">&nbsp;&nbsp;&nbsp;&nbsp;<a href= <%= excel_path %>>Download CSV file</a></font> &nbsp;&nbsp;&nbsp;
    	<font size="1"><a href= <%= query_path %>>Save Query file</a></font>
    	<p>
    	<font size="2" color="blue">&nbsp;&nbsp;
    	<%= (String)session.getAttribute("query1")%>
    	</font>
    	</p>
    	<table border="1" width="90%" bgcolor="white"  align="center" cellpadding="2" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
    		  <tr>
   <% 
     	
      	ArrayList arrcolumns = ip2.getColumns(exec1);
      	ArrayList arrdata = ip2.getData(exec1);
      	
      	if(arrcolumns != null)
      	{
      		//ArrayList arrdata = ip.getData(exec);
      		Iterator itr = arrcolumns.iterator();
	       	int k=0;
	       	Iterator itr22 = arrdata.iterator();
	       	int k2=1;
	       	while(itr.hasNext()){
	       		k++;
   %>
      
      
	            		    <td width="15%" nowrap="nowrap" align="center"><%=itr.next() %></td>
	
	
	 <% 	} //end while
	  
	 %>
	  	</tr>
	  	<tr>
	 <%   while(itr22.hasNext()){
	      		
	 	  if(k2 == (countcol+1)){
	 	 
	 %>						<tr>
	 						<td width="15%" nowrap="nowrap" align="center">
	 							<font face="Arial" color="blue" size="2"><%=itr22.next()%></font>
	 						</td>
	 						
	 <%  }else if((k2)%countcol == 0){ 
	 %>						
	 						<td width="15%" nowrap="nowrap" align="center">
	 							<font face="Arial" color="blue" size="2"><%=itr22.next()%></font>
	 						</td>
	 						</tr>
	 						
	 <% }else{
	 %>
	 						<td width="15%" nowrap="nowrap" align="center">
	 							<font face="Arial" color="blue" size="2"><%=itr22.next()%></font>
	 						</td>
	 
	 <%		} k2++; 
	     } // end while
	     }
	 %>
	 	</table>
    	<% } else { %>
           <font face="Arial" color="red" size="3" >Error In SQL : <%=ip2.getError((String)session.getAttribute("query1"))%> </font>
        <% } }%>
    </td>
  </tr>
   <input type="hidden" name="where_t" value="none" >
   <input type="hidden" name="order_t" value="none" >
   <input type="hidden" name="final_exec" value="" >
</table>
 <input type="hidden" name="tab_press" value="<%= press1%>" >
     <input type="hidden" name="pres_tables" value="<%= tab_arr1%>" >
     <input type="hidden" name="tables_t" value="<%= Query_tables%>" >
     <input type="hidden" name="file_query" value="2" >
</form>
<p>
     <form name="formTwo" ENCTYPE="multipart/form-data" action="FileUploadQB.jsp?DynaBrowse=2" method="POST">
       &nbsp;&nbsp; Select query file  <input type="file" name="userfile" size="30"  >
       <input type="submit" value="Submit" name="sub" tabindex="2" onClick="return checkFile()">
       
     </form>
</p>


</body>
<script language="JavaScript">
<!-- USE TO CHECK COLUMN NAME FOR WHERE CONDITION -->
function columnValueCheck(){
var checkColumn=document.forms[0].D5.value;
alert("column name "+checkColumn);
 //document.forms[0].submit();
}
<!-- function used to check whether query file is selected when submit button is click -->
function checkFile(){
var fileName=document.forms[1].userfile.value;

if(fileName==""){
alert(" Please select the query file ");
return false;
}else{

return true;
}

}
<!--END function checkFile()   END -->
function tabpress()
{
	var tabb=document.forms[0].add_tables.value;
	if(tabb=="")
	alert("Please select a Column name");
	else{
	document.forms[0].tab_press.value = '1';
	document.forms[0].pres_tables.value = document.forms[0].add_tables.value ;
	document.forms[0].col_add.value = '10';
	document.forms[0].submit();	
	}
}
function final_execute()
{
	document.forms[0].tab_press.value = '10';
	document.forms[0].col_add.value = '20';
	
	document.forms[0].final_exec.value = 'exec';
	document.forms[0].submit();	

}
function columns_add()
{
	var val=document.forms[0].add_tables1.value;
	if(val=="")
	alert("Please select a Column name");
	else{
	document.forms[0].col_add.value = '1';
	document.forms[0].tab_press.value = '1';
	document.forms[0].submit();	
	}
	
}
function addwhere()
{
	if(document.forms[0].T1.value == '')
	{
		if(document.forms[0].where_t.value == 'none')
		{
			document.forms[0].S4.value = document.forms[0].S4.value + ' where ' + ''+ document.forms[0].D5.value + ' ' +  document.forms[0].D4.value + ' ' + document.forms[0].D6.value;
			document.forms[0].where_t.value = 'OK';
		}else
		document.forms[0].S4.value = document.forms[0].S4.value + ' and ' + document.forms[0].D5.value + ' ' +  document.forms[0].D4.value + ' ' + document.forms[0].D6.value;
	}else
	{
		if(document.forms[0].where_t.value == 'none')
		{
			document.forms[0].S4.value = document.forms[0].S4.value + ' where ' + document.forms[0].D5.value + ' ' +  document.forms[0].D4.value + ' ' + document.forms[0].T1.value;
			document.forms[0].where_t.value = 'OK';
		}else
		document.forms[0].S4.value = document.forms[0].S4.value + ' and ' + document.forms[0].D5.value + ' ' +  document.forms[0].D4.value + ' ' + document.forms[0].T1.value;
	}
}
function addsort()
{
	if(document.forms[0].order_t.value == 'none')
		{
			document.forms[0].S4.value = document.forms[0].S4.value + ' Order by ' + document.forms[0].D7.value + ' ' + document.forms[0].D9.value;
			document.forms[0].order_t.value = 'OK';
		}else
			document.forms[0].S4.value = document.forms[0].S4.value + ' , ' + document.forms[0].D7.value + ' ' + document.forms[0].D9.value;
	
}


function KeyPress()
{
	alert ('New File');
}

function tabpressfile()
{
	document.forms[0].tab_press.value = '10';
	document.forms[0].col_add.value = '20';
	document.forms[0].file_query.value = '1';
	document.forms[1].submit();	
}
</script>
</html>




