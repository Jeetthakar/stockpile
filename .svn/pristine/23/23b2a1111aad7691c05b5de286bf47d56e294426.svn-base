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
<%@ page language="java" import="app.*"%><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");

LogonForm form = null;
			if(request.isRequestedSessionIdValid())	{	
				form = (LogonForm)session.getAttribute("user");
			}
			if(form==null ||(!request.isRequestedSessionIdValid())){
				response.sendRedirect("../userlogintemp.jsp");
			}
		%>
<html>
<head>
<html:base/>
</head>
<link href="/Income/pages/StyleSheet.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../Script/codethatcalendarstd.js"></script>

<SCRIPT LANGUAGE="JavaScript" SRC="../Script/autocomplete.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="../Script/date_script.js"></SCRIPT>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script>
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
	<body onload="initialize()" >
	<table width="1000" cellspacing="0" cellpadding="0" >
       <tr>
        <td width="300" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		</td>
		<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		     <font size="3" face="Arial">
		      <b>Index&nbsp;_&nbsp;Name&nbsp;_&nbsp;Scrip</b>
		     </font>
		</td> 
	   </tr>
	</table> 
	<%
      	org.jfree.chart.demo.servlet.FieldSort sort=new org.jfree.chart.demo.servlet.FieldSort();
     //   org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();    
       ComposeIndex ci = ConnectInit.getComposeIndex(); 
              String var=request.getParameter("D1");    
        	  String chkchecked=request.getParameter("check");        	       		
       		  String[] var1=request.getParameterValues("D2");
       		  String str=request.getParameter("refFlag");
        if(str!=null && str.equals("1"))
				var=ci.reset_stkevent();
       	String s11=null;
      if(var1!=null)
         { 
         s11=var1[0]+"," ;
           int x=var1.length;
         for(int i=1;i<var1.length-1;i++)
         {
           s11=s11+var1[i];
           s11=s11+",";
         } 
         s11=s11+var1[--x];              
        }
  		String pr = null;
        				pr = request.getParameter("Pr");
	     				try{
		    				if(pr.equals(null))
    	       				{ pr = "N";}
        	   }catch(Exception e){pr = "N";}
      log.info("before firing query"+pr); 
     %>  
	<form action="Index_Name_Scrip.jsp">
   <%  if(!pr.equals("Y"))
           { %>	   
	<table width="656">
      <tr>
	  	<td width="116" nowrap="nowrap" align="left">&nbsp;</td>
	  <tr>
	  	<td width="150" nowrap="nowrap" align="left">&nbsp;</td>
	  	<td width="118" nowrap="nowrap" align="right">
		   <font size="2" face="Arial">Select&nbsp;Stock&nbsp;Exchange: 
		    </font>
        </td>     
        <td width="504" nowrap="nowrap" align="left" height="50">   
        	<select size="1" name="D1" onchange="indiChange();" id="sel">
          		<option value="0" selected>Not Selected</option>			 
     		 		<%= ci.getExchangeList(var)%>		
             </select> 
             </td>
          </tr>  
          </table>
  <%       
        	Vector vforex=ci.getStockExwise(var);
        	Iterator i1=vforex.iterator();    
        	 log.info("check.."+i1.hasNext());   
  %>        
          <table border="0">
          <tr>
          <td width="150" >
          </td>
          <td width="50" nowrap="nowrap" align="left" valign="middle">
        	<font face="Arial" size="2">
      		<input type="text" name="cnt_sel" size="15" value="" tabindex="2" onfocus="updateSelection()" onkeyup="autoComplete_MulSel(this,this.form.D2,'text',true)">
        	</font>
          </td>   
		  <td width="204" nowrap="nowrap" align="left" height="50">     
			 <font face="Arial" size="2">      
        	 <select size="4" name="D2" multiple="multiple" onmousedown="GetCurrentListValues(this);" onchange="FillListValues(this);" >
        <%   		 while(i1.hasNext())
                      {  
                        int id1 = Integer.parseInt(i1.next().toString()); %>
                       	<option value="<%= id1 %>"><%= i1.next() %></option> 
		<% } %>
             </select> 
           </font> 
         </td>
        <td width="50" align="right" nowrap="nowrap">
         <% if((request.getParameter("check"))!=null && (request.getParameter("check")).equals("checked")){ 
         log.info("in the loop");%>
	   <input type="checkbox" name="check" value="checked"  checked />&nbsp;
	   <% }else{ 
	   log.info("out of the loop");
	   %>
	   <input type="checkbox" name="check" value="checked"   />&nbsp;
	  <% } %>	   	   
	  </td>
	  <td width="144" nowrap="nowrap">	
	   <font size="2" face="Arial" color="blue">  
	  Select All</font>
	  	&nbsp; <a href="javascript:DeselectAllList(document.forms[0].D2);">
          		<font face="Arial" color="blue" size="2">Deselect all</font></a>
          </td>
         <td width="90" nowrap="nowrap" align="center">
            <input type="submit" value="View" name="B1" onclick="return go();">
          </td>
         	</tr>
        </table>                      
    <%
     }
  
	if(var1==null && chkchecked==null )
	{
			
	
	%>
	<table border="0" align="center" width="680" height="222" cellspacing="0" cellpadding="0">
          <tr>
          <td  bgcolor="#cacaca" align="center" valign="middle" width="631">
              <p style="margin-left: 9"><font face="Arial" color="blue" size="5">
              Select&nbsp;Stock&nbsp;Name&nbsp;to&nbsp;View&nbsp;Data</a></font></p>
            </td>
            <td width="49">&nbsp;</td>
            </tr>
            </table>
           
      <%  }else {                               
                        
			
			 if(!pr.equals("Y"))
           {   
				try{
					String astr = null;  						
					astr = "/Income/pages/Index_Name_Scrip.jsp?Pr=Y&D1="+var+"&D3="+var1;  			                    	
                    String temp_path = "../pages/FileDownload.jsp?type=31&var="+var;
                    String str_url = "../pages/EmailReport.jsp?switch_type=31&cas=31&rname=Index_Nmae_Scrip.xls&varid="+var;
                    log.info("abhi"+var); 
         %>
                    </br>
                        <table width="900" height="40" border="0" >
                        	<tr>
                       		 <td width="575" nowrap="nowrap" align="right" valign="top">
                        	<font size="1"><a href="javascript:print();">
                        	Printer&nbsp;friendly</a></font>                        	</font>
                       			&nbsp;&nbsp;
							 <font size="1"><a href=<%= temp_path %>>Download&nbsp;Excel</a></font>
							 	&nbsp;&nbsp;
							 <font size="1"><a href= <%= str_url %>>Email&nbsp;Report</a></font>
							</td>
							</tr>
						</table>
     <%					}catch(Exception e){log.info("refresh the page.");}
          }	
         if(pr.equals("Y")||pr.equals("N"))
     {  		
          	Vector vec=ci.getIndexscripname(s11,var,request);	
            if(vec.size()==0)  
         		 {	
     %>
      <table border="0" class="gridStyle" align="center" width="631" height="222" cellspacing="0" cellpadding="0">
          <tr>
          <td  class="gridStyle-message" align="center" valign="middle">
              <p style="margin-left: 9"><b>No Data Available For Criteria You Have Selected</b></font></p>
            </td>
            </tr>
            </table>
      <% }else {
      %>
      <table border="0" width="100%"  cellpadding="2" cellspacing="0" id="tabular"  >
	      <tr>
	      <td width="120" nowrap="nowrap" align="right">
	      &nbsp;
	      </td>
	      <td>
	       	<table border="1" width="60%" bgcolor="white"  align="left" cellpadding="0" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
	         	<tr>
	              	<td width="15%" nowrap="nowrap" align="center">Index Id</td>
	            	<td width="15%" nowrap="nowrap" align="center">Index Name</td>
	            	<td width="15%" nowrap="nowrap" align="center">Scrip Id</td>
	            	<td width="15%" nowrap="nowrap" align="center">Scrip Name</td>
	            </tr>
  <%  
            Object ci2 = null;
              		    session.setAttribute("ci2",new Vector(vec));   
            Iterator i11=vec.iterator();           
            while(i11.hasNext())
            {
  %> 
             <tr>
                <% String id=(String)i11.next(); %>                             
	          	<td width="15%" nowrap="nowrap" align="center"><font face="Arial" color="blue" size="2"><%=id%></td></font>
	            <td width="15%" nowrap="nowrap" align="left"><font face="Arial" color="blue" size="2"><a href="../pages/indexcompose.jsp?D1=<%=id%>&B1=View" onmouseover="window.status='';return true"><%=i11.next()%></a></td></font>
	            <% String id1=(String)i11.next(); 
	               String temp="../pages/stockmaster2.jsp?s_stockid="+id1; 	
	            %> 
	            <td width="15%" nowrap="nowrap" align="right"><font face="Arial" color="blue" size="2"><%=id1%></td></font>
	            <td width="15%" nowrap="nowrap" align="left"><font face="Arial" color="blue" size="2"><a href="<%= temp %>"><%=i11.next()%></a></td></font>
	         </tr>
	<%} %>
	      </table>	
	   </table>  			
<% }}} %>   			
<p></p>
</form>
</body>
<script language="JavaScript">			
	function indiChange(){	
	document.forms[0].submit();
	return false;
	}	
	function initialize() {
	var length = document.forms[0].D2.length;
	arrSelectedIndex = new Array( length) ;
	arrOldValues = new Array(length);
	for (var counter= 0; counter < length; counter++) {
		arrSelectedIndex[counter] = 0;
		arrOldValues[counter] = 0;
	}
}	

function popprinter(url)
{
	newwindow=window.open(url,'name','height=500,width=900,scrollbars=yes,left=80,top=130,menubar=yes,status=yes');
	if (window.focus) {newwindow.focus()}
}

function print() {
  		//alert("in");
  		var name;
  		var val=document.forms[0].D1.value;
  		var lhsSelect=document.getElementById("sel");
  		var len=lhsSelect.length;
  		//alert(len);
  		for(var i=0;i<len;i++)
  		{
  		  if(val==lhsSelect.options[i].value){
  		  //alert("match");
  		  //alert(document.forms[0].D1.options[i].innerHTML);
  		  var name=document.forms[0].D1.options[i].innerHTML;
  		  }
  		}
  		
  		/* var indexName,select,fDate,tDate;
       
		var val1=document.forms[0].select.selectedIndex;
        select=sele.options[val1].innerHTML;
        alert("select"+select);
        
        //var lhsSelect=document.getElementById("index");
		//var val=document.forms[0].D1.selectedIndex;
		//indexName=lhsSelect.options[val].innerHTML;
		
		fDate=document.forms[0].fromDate.value;
		tDate=document.forms[0].toDate.value;
		alert("from date"+fDate);
		alert("to date"+tDate);
		
		*/
		var newWind = window.open('','','height=500,width=900,scrollbars=yes,left=80,top=130,menubar=yes,status=yes');
		
		newWind.document.write("<table width=\"1000\"  cellspacing=\"0\" cellpadding=\"0\"  ><tr><td width=\"250\"  nowrap=\"nowrap\">&nbsp;</td><td  width=\"438\" align=\"left\" colspan=\"2\" nowrap=\"nowrap\"><font size=\"3\" face=\"Arial\"><b>Index&nbsp;_&nbsp;Name&nbsp;_Scrip </b></font></td></tr></table><p></p>");
	    newWind.document.write("<table>");
	   newWind.document.write("<tr height=\"60\"><td width=\"385\" align=\"right\"><b>");
		newWind.document.write("Stock Exchange Name&nbsp;:&nbsp;");
		newWind.document.write("</b></td><td ><font face=\"Arial\" size=\"2\" color=\"blue\">"+name+"</td><tr><td width=\"137\" align=\"right\"><b>");
    /* 	 newWind.document.write("<tr><td width=\"180\" align=\"right\"><b>");
		newWind.document.write("From Date&nbsp;:&nbsp;");
		newWind.document.write("</b></td><td align=\"left\"><font face=\"Arial\" size=\"2\" color=\"blue\">"+fDate+"</td></tr>");
		newWind.document.write("<tr><td width=\"180\" align=\"right\"><b>");
		newWind.document.write("To Date&nbsp;:&nbsp;");
		newWind.document.write("</b></td><td align=\"left\"><font face=\"Arial\" size=\"2\" color=\"blue\">"+tDate+"</td></tr>");
	*/	
		newWind.document.write("</table>");
		newWind.document.write("<table>");
	   	/*if(!(select=="0")){
	   			alert("in");
						try{
								var table=document.getElementById("image");	   
						}
						catch(e){
								alert("Error occured : " + e.description);
						}
			    if (table.rows && table.rows.length > 0) {
		       		 var firstRow = table.rows[0];
		 		}
		    	
		    	if (firstRow) {
				    // We have a first row: assume it's the header, and make its contents clickable links
				   // alert("before table2");
				    for (var i=0;i<firstRow.cells.length;i++) {
				        	var cell = firstRow.cells[i];
					     	newWind.document.write("<tr><td align=\"left\">");
					     	newWind.document.write(cell.innerHTML);
				 			newWind.document.write("</td></tr>");
							newWind.document.write("</table>");
							newWind.document.write("<table>");
					}	
				}
		}*/
	//alert("before table3");
					try{
								var table1=document.getElementById("tabular");   
						}
						catch(e){
								alert("Error occured : " + e.description);
						}
		
		//newWind.document.write("<table><tr><td>&nbsp;</td><td>");
	    if (table1.rows && table1.rows.length > 0) {
       		 var firstRow1 = table1.rows[0];
       		
    	}
   		 if (firstRow1){
			// We have a first row: assume it's the header, and make its contents clickable links
		    for (var i=0;i<firstRow1.cells.length;i++) {
		        var cell = firstRow1.cells[i];
			    newWind.document.write("<tr><td width=\"214\">&nbsp;</td><td align=\"left\" width=\"431\">");
			    newWind.document.write(cell.innerHTML);
		 		newWind.document.write("</td></tr>");
			}	
		}
		newWind.document.write("</table>");
		if (window.focus) {newWind.focus()}
}
function go(){
	
	var i = 0;
	var fields = new Array();
	var objFrom=document.forms[0].from;
	var objTo=document.forms[0].to;
	if(document.forms[0].D1.value==0){
		fields[i++] = "Stock Exchange is required" ;
	}
	if(document.forms[0].D2.value==0 && document.forms[0].check.value==0){
		fields[i++] = "Stock Name is required" ;
	}
	if (fields.length > 0) {
                   alert(fields.join('\n'));
                   return false;
         }
         else {
         	return true;
         }
}
</script>
</html>			
			