
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
<body onload="initialize()" >

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
	
	 <table width="1000" cellspacing="0" cellpadding="0" >
        <tr>
		  	<td width="300" class="subHeader" nowrap="nowrap">
		   		&nbsp;
		   	</td>
		   	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		 		<font size="3" face="Arial">
		 			<b>Inclusion&nbsp;Exclusion&nbsp;Between&nbsp;Dates </b>
		 		</font>
		   	</td> 
	     </tr>
	</table>
	

	 <p align="left">
     <form action="Inclusion_Exclusion.jsp">
      	<table border="0"  height="40" >
     	   <tr>
        		<td align="left" width="50" nowrap="nowrap">
        			&nbsp;
          		</td>
 				<td align="right" width="88" nowrap="nowrap" >
      				
	 				<% 
	     				String pr = null;
         				pr = request.getParameter("Pr");
	     				try{
		    				if(pr.equals(null))
    	       				{ pr = "N";}
        	   }catch(Exception e){pr = "N";}
           
           if(!pr.equals("Y"))
           { %>	       
        <font size="2" color="black" face="Arial">Index&nbsp;Name:
        </font>
        </td>
        
          	
        
        
      <%   } %>
      <%
        String fromdate = request.getParameter("from");
        log.info("check date...."+fromdate );
        String toDate  =request.getParameter("to");
    //    org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
      ComposeIndex ci = ConnectInit.getComposeIndex();
       // ci.setVector_indexlist("check"); 
        String chkchecked=null;
             chkchecked=request.getParameter("check"); 
        	ci.setVector_indexlist(chkchecked); 
         String var=null,var3;
        //var=request.getParameter("D1");     
            
       	Vector v = ci.getVector_indexlist();    	
    	Iterator i=v.iterator(); 
    	String[] var1=request.getParameterValues("D1"); 
       String s11=null;
       if(var1!=null)
         { 
         s11=var1[0]+"," ;
           
           int x=var1.length;
         for(int counter=1;counter<var1.length-1;counter++)
         {
           
          s11=s11+var1[counter];
          s11=s11+",";
         } 
         s11=s11+var1[--x];              
        }
         log.info("hdsfgduhf"+s11);
    	
	     %>   
      <% if(!pr.equals("Y"))
         { %>
        <td width="50" nowrap="nowrap" align="left" height="30" valign="middle">  				
					<input type="text" name="cnt_sel" size="19" value = "" tabindex="2" ONKEYUP="autoComplete(this,this.form.D1,'text',true)">	        
              </td> 
        
         <td width="304" nowrap="nowrap" align="left" height="30" valign="middle" > 
        	<select size="4" name="D1" multiple="multiple" onmousedown="GetCurrentListValues(this);" onchange="FillListValues(this);" >
                  
           <%    int requestID= 0;
                     try{
	                      requestID = Integer.parseInt(request.getParameter("D1"));
                     	}catch(Exception e){}
    
                     while(i.hasNext())
                      {   
                        int id = Integer.parseInt(i.next().toString());
                        %>
                        	<option selected value="<%=id%>"><%=i.next()%></option>
                       	
                       <% 
						}     
                         %>
                        </select>    
                        <td width="160" nowrap="nowrap" align="center">
           		&nbsp; <a href="javascript:SelectAllList(document.forms[0].D1);">
          		<font face="Arial" color="blue" size="2">Select all</font></a>
          		&nbsp; <a href="javascript:DeselectAllList(document.forms[0].D1);">
          		<font face="Arial" color="blue" size="2">Deselect all</font></a>
          </td>  
              </td>
          	
        </tr>
       
      
        </table>           
        
        
        <!--second table-->
        
                       
      <!--3 table-->                 
           <table border="0" width="100%"  cellspacing="0" cellpadding="0" height="40" >
        	<tr>
	        	
	          	<td align="left" width="10" nowrap="nowrap">
	        		
	          	</td>
	          	 <% if((request.getParameter("check"))!=null && (request.getParameter("check")).equals("checked")){ %>
	   <td width="90" align="right" nowrap="nowrap">
	   <input type="checkbox" name="check" value="checked" onclick="return test2()" checked />&nbsp;
	  
	   <% }else{ log.info("check this box"); %>
	   <td width="90" align="right" nowrap="nowrap">
	   <input type="checkbox" name="check" value="checked" onclick="return test2()"  />&nbsp;
	  <% } %>	   	   
	  </td>
	  <td width="160" nowrap="nowrap" align="left">	
	   <font size="2" face="Arial">  
	  Show&nbsp;Sectoral&nbsp;Indices</font>
	  </td>
          	   	
			 	 <td width="70" align="right" nowrap="nowrap" ><font size="2" face="Arial" >
              From &nbsp;Date: </font>
            </td> 
			 	<td width="58" nowrap="nowrap">
               <% if(fromdate==null){ %><input  name="from" onBlur="checkdate(this)" size="10"><% }else{ %><input  name="from" value='<%=fromdate%>' onBlur="checkdate(this)"  size="10"><% } %>
                                
                              <td width="58" nowrap="nowrap"><font size="2" face="Arial">
                          To&nbsp;Date&nbsp;: 
                          </td>
                           <td width="69" nowrap="nowrap">
                        <%  if(toDate==null){ %><input  name="to" onBlur="checkdate(this)" size="10"><% }else{ %><input  name="to" value='<%=toDate%>' onBlur="checkdate(this)" size="10"><% } %>
                                </td>
                                <td width="30" nowrap="nowrap">                          
                                 </td>
				
             
                <td align="left" nowrap="nowrap" width="230">   
                      <input type="submit" value="View" name="B1" onclick="return viewFunc();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
                </tr>
        </table>
		<% } %>
       <% 		if(var1 != null)
				{
					if(!pr.equals("Y")){
					try{
						String astr = null;						
                       	astr = "/Income/pages/reports/Inclusion_Exclusion.jsp?Pr=Y&D1="+var+"&from="+fromdate+"&to="+toDate;                         	
                       	String str_url = "../pages/EmailReport.jsp?switch_type=32&rname=Inclusion_Exclusion&varid=null&from="+fromdate+"&to="+toDate;
                    	String temp_path = "../pages/FileDownload.jsp?var="+var+"&type=32&filename=Inclusion_Exclusion.xls&from="+fromdate+"&to="+toDate;
                        %>
                        </br>
                        <table width="900" >
                        	<tr>
                       		 <td width="575" nowrap="nowrap" align="right">
                        	<font size="1"><a href="javascript:print();">
                        	Printer&nbsp;friendly</a></font>   
                        			</font>
                       			&nbsp;&nbsp;
							 <font size="1"><a href=<%= temp_path %>>Download&nbsp;Excel</a></font>
							 	&nbsp;&nbsp;
							 <font size="1"><a href= <%= str_url %>>Email&nbsp;Report</a></font>
							
							</td>
							</tr>
						</table>
					
     <%					}catch(Exception e){log.info("refresh the page.");}
          			}
          		}		
     %>
     <% if(pr.equals("Y")){ %>
    <table border="0" width="100%"  cellspacing="0" cellpadding="3"  height="30" align="left">
       <tr>
        	<td align="left" width="43" nowrap="nowrap">
        		&nbsp;
          	</td>
        	<td align="left" width="844" nowrap="nowrap" >
     			<font face="Arial" size="2" ><b>Index Name&nbsp;:</b></font>&nbsp;<font face="Arial" size="2" color="blue"><%= ci.getIndexName(var)%></font>
     		</td>
         </tr>
        <tr>
        	<td align="left" width="43" nowrap="nowrap">
        		&nbsp;
          	</td>
        	<td align="right" width="844" nowrap="nowrap" >
     <p align="left"><font size="2">
     	<font face="Arial" size="2" ><b>From date&nbsp;&nbsp;&nbsp;&nbsp;:</b></font>&nbsp;<font face="Arial" size="2" color="blue"><%= fromdate%></font> &nbsp;&nbsp; &nbsp;&nbsp;<font face="Arial" size="2" ><b>To date:</b></font>&nbsp;<font face="Arial" size="2" color="blue"><%=toDate%></font>
     </p></font>
     </p><p align="left"> 
     </td>
         </tr>
         </table>   
     <% }else{ %>
     </p><p align="center"> 
     <% } %>
              </form>
           </td>
         </tr>
         </table>   
       
     <table  border="0" width="800" cellpadding="0" cellspacing="0">
  <tr>
	 <td width="25" nowrap="nowrap">&nbsp;</td>
	 <td width="780" nowrap="nowrap">	          
 <% 
	if(var1==null )
	{
			
	
	%>
	<table border="0" align="left" width="680" height="222" cellspacing="0" cellpadding="0">
          <tr>
          <td  bgcolor="#cacaca" align="center" valign="middle" width="631">
              <p style="margin-left: 9"><font face="Arial" color="blue" size="5">
              Select&nbsp;Index&nbsp;Name&nbsp;And&nbsp;Date&nbsp;to&nbsp;View&nbsp;Data</a></font></p>
            </td>
            <td width="49">&nbsp;</td>
            </tr>
            </table>
           
      <%  }    %>
    
	<p></p>
	<%
	          		 fromdate = request.getParameter("from");
       				 toDate  =request.getParameter("to");	
       				//  String in  =request.getParameter("D1");      				
	          	 if(var1!=null)
	          	 {   %>
      <table border="0" width="556"  align="left"  cellpadding="0" cellspacing="0" id="tabular">
	     <tr>								      
		    <td>	      
	     
	       		<table border="1" width="556" bgcolor="white"  align="left" cellpadding="0" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
	          	 		<tr>
	          	 		    <td width="8%" nowrap="nowrap" align="center">Index Name</td>
	            		    <td width="8%" nowrap="nowrap" align="center">Event Date</td>
	            		    <td width="10%" nowrap="nowrap" align="center">Scrip name</td>
	            		    <td width="5%" nowrap align="center">MCap(In Millions)</td>
	            		    <td width="10%" nowrap="nowrap" align="center">Reason</td>
	            		     <td width="10%" nowrap="nowrap" align="center">Description</td>
	            			
	          			</tr>
	          	<%
	          		 
	      		 Vector vec =ci.getInclusionExclusionVec(s11,fromdate,toDate,session);	 
	      		 log.info(s11);
	      		 log.info("gjsdfse"+vec);	      		           		 
					//Object ci2 = null;
					//session.setAttribute("ci2",new Vector(arr));
	          		Iterator itr=vec.iterator();
	          		int k=0;
	          		
	          		while(itr.hasNext()){
	          				
	          				k++;
	          	%>		
	          			<tr>
	          				<td width="8%" nowrap="nowrap" align="center"><font face="Arial" color="blue" size="2"><%=itr.next()%></td></font>
	          				<td width="8%" nowrap="nowrap" align="center"><font face="Arial" color="blue" size="2"><%=itr.next()%></td></font>
	          				<% String id=(String)itr.next();
	          				   String temp="../pages/masters/stockmaster2.jsp?s_stockid="+id; 	          				   
	          				%>
	            		    <td width="10%" nowrap="nowrap" align="left"><font face="Arial" color="blue" size="2"><a href="<%= temp %>"><%=itr.next()%></a></td></font>
	            		    <td width="5%" nowrap align="right"><font face="Arial" color="blue" size="2"><%=itr.next()%></td></font>
	            		    <td width="10%" nowrap="nowrap" align="right"><font face="Arial" color="blue" size="2"><%=itr.next()%></td></font>
	            			 <td width="10%" nowrap="nowrap" align="left"><font face="Arial" color="blue" size="2"><%=itr.next()%></td></font>
	            			 
	          			</tr>
	          			
	          			<%}
	          			if(k==0){%>
	          			</table>
		    							</td>
	    							</tr>
	    						</td>
	    							</tr>	
        						</table>
		     			<table border="0" width="100%"  cellpadding="2" cellspacing="0" align="left">
					       <tr>
	     					 <td width="120" nowrap="nowrap">
	      						&nbsp;
	     					 </td>
	      					 <td>
	       						<table border="1" width="90%" align="left" cellpadding="2" cellspacing="0" id="t1"  bordercolor="aliceblue" height="100">
				     			<tr>																																
			            			<td width="45%" bgcolor="white" align="center" valign="middle"><font face="Arial" color="black" size="6">No&nbsp;data&nbsp;available</font></td>
			            		</tr>
			            		</table>
			            	 </td>
			              </tr>
			             </table>
	          			<%}
	          			%>
          </td>
       </table>
</td>
</tr>
</table>
</table>
<% } %>
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

function initialize() {
	var length = document.forms[0].D1.length;
	arrSelectedIndex = new Array( length) ;
	arrOldValues = new Array(length);
	for (var counter= 0; counter < length; counter++) {
		arrSelectedIndex[counter] = 0;
		arrOldValues[counter] = 0;
	}
	DeselectAllList(document.forms[0].D1);
}
function test1(){
	document.forms[0].submit();
}
function viewFunc(){
	
	var i = 0;
	var fields = new Array();
	var objFrom=document.forms[0].from;
	var objTo=document.forms[0].to;
	if(document.forms[0].D1.value==0){
		fields[i++] = "Index Name is required" ;
		
	}
	if(document.forms[0].from.value==""){
		fields[i++] = "From Date is required" ;
				
	}
	if(document.forms[0].to.value==0){
		fields[i++] = "To Date is required";
				
	}
	var val=checkValidDates(objFrom,objTo);	
	if(val==false)
	return false;
	
	if (fields.length > 0) {
                   alert(fields.join('\n'));
                   return false;
         }
         else {
         	return true;
         }
}
function popprinter(url)
{
	newwindow=window.open(url,'name','height=500,width=900,scrollbars=yes,left=80,top=130,menubar=yes,status=yes');
	if (window.focus) {newwindow.focus()}
}
function test2()
{
	document.forms[0].submit();
}
function print() {
  
  		
  		//alert("in"); 	  		
  		 
       var indexName,select,fDate,tDate;
       
	  /*	var val1=document.forms[0].select.selectedIndex;
        select=sele.options[val1].innerHTML;
        alert("select"+select);
        
        //var lhsSelect=document.getElementById("index");
		//var val=document.forms[0].D1.selectedIndex;
		//indexName=lhsSelect.options[val].innerHTML;
	*/	
		
		fDate=document.forms[0].from.value;
		tDate=document.forms[0].to.value;
		//alert("from date"+fDate);
		//alert("to date"+tDate);
		
		
		var newWind = window.open('','','height=500,width=900,scrollbars=yes,left=80,top=130,menubar=yes,status=yes');		
		newWind.document.write("<table width=\"1000\"  cellspacing=\"0\" cellpadding=\"0\"  ><tr><td width=\"250\"  nowrap=\"nowrap\">&nbsp;</td><td  width=\"438\" align=\"left\" colspan=\"2\" nowrap=\"nowrap\"><font size=\"3\" face=\"Arial\"><b>Inclusion&nbsp;Exclusion&nbsp;</b></font></td></tr></table><p></p>");
	    newWind.document.write("<table border=\"0\" >");
	  // newWind.document.write("<tr height=\"20\"><td width=\"180\" align=\"right\"><b>");
	 	//newWind.document.write("Stock Exchange/Index &nbsp;:&nbsp;");
		//newWind.document.write("</b></td><td nowrap=\"nowrap\"><font face=\"Arial\" size=\"2\" color=\"blue\">"+name+"</td><tr><td width=\"137\" align=\"right\"><b>");
    	 newWind.document.write("<tr><td width=\"180\" align=\"right\"><b>");
		newWind.document.write("From Date&nbsp;:&nbsp;");
		newWind.document.write("</b></td><td width=\"180\" align=\"left\"><font face=\"Arial\" size=\"2\" color=\"blue\">"+fDate+"</td></tr>");
		newWind.document.write("<tr><td width=\"180\" align=\"right\"><b>");
		newWind.document.write("To Date&nbsp;:&nbsp;");
		newWind.document.write("</b></td><td align=\"left\"><font face=\"Arial\" size=\"2\" color=\"blue\">"+tDate+"</td></tr>");
		
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
			    newWind.document.write("<tr><td width=\"180\">&nbsp;</td><td align=\"left\" width=\"331\">");
			    newWind.document.write(cell.innerHTML);
		 		newWind.document.write("</td></tr>");
				
			}	
		}
		newWind.document.write("</table>");
		if (window.focus) {newWind.focus()}
		
	
}
</script>
</html>