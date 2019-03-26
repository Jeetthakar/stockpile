<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.text.ParseException" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@ page import = "harrier.income.com.report.*" %>
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@page import="org.jfree.chart.demo.servlet.ComposeIndex"%>
<%@page import="org.jfree.chart.demo.servlet.AdjustDecimal"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="app.*"%>
<%@ page  import="harrier.income.com.report.*" %><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");

%>
<jsp:useBean id="batchReportBean" scope="session" class="harrier.income.com.report.BatchReportForm"/>
<html:html>
	<head>
		 <html:base/>
		 <link href="./StyleSheet.css" rel="stylesheet" type="text/css">
		 <script language="javascript" src="../Script/codethatcalendarstd.js"></script>
		 <script language="javascript" src="../ScriptI/date_script.js"></script>
		 <script language="javascript" src="box_ex.js"></script>
		 <script language="javascript">
			var c2 = new CodeThatCalendar(caldef2);
		 </script> 
		 <script type="text/javascript" src="./sorttable.js"></script>
		 <style type="text/css">
		 
		 
		 /*Eric Meyer's based CSS tab*/

#tablist{
padding: 3px 0;
margin-left: 0;
margin-bottom: 0;
margin-top: 0.1em;
font: bold 12px Verdana;
}

#tablist li{
list-style: none;
display: inline;
margin: 0;
}

}
#tablist li a{
text-decoration: none;
padding: 3px 0.5em;
margin-left: 3px;
border: 1px solid #778;
border-bottom: none;
background: white;
}

#tablist li a:link{
color:red;
background:"LavenderBlush";
}
#tablist li a:visited{
color: navy;
background:LavenderBlush
}

#tablist li a:hover{
color: #000000;
background: #C1C1FF;
border-color: #227;
}

#tablist li a.current{
background: lightyellow;
}
</style>
	
	
		 <!-- 
		 <style type="text/css">
			/* Sortable tables */
			table.sortable a.sortheader {
    			background-color:#eee;
   	 			font-size: 13px; 
    			font-family: Verdana;
    			color: black;
    			text-decoration: none;
    			display: block;
			}
			table.sortable span.sortarrow {
    			color: black;
    			text-decoration: none;
			}
		 </style>
		 -->
	</head>  	
	<body>
			
		    
		    <p> </p><BR><BR><BR>	
  		    <ul id="tablist">
  		    		<li><a href="#" onClick="return indcompare()"><bean:message key="BatchReport.IndexCompare" /></a></li>
					<li><a href="#" onClick="return indreturnvol()"><bean:message key="BatchReport.IndexReturnsandVolatility" /></a></li>
					<li><a href="#" onClick="return indexcorel()"><bean:message key="BatchReport.IndexCo-relation" /></a></li>
					<li><a href="#" onClick="return indexcomohlc()"><bean:message key="BatchReport.IndexCompareOHLC" /></a></li>
					
			</ul>  
	   
	 <TABLE border="1" color="black" width="100%">
		<TR>
		<TD bgcolor="LavenderBlush" valign="top" width="100%" height="500">
	    
		 
		    
		    
		<div id=indcompare1 style="display:inline"> 
		
	  		    
		    <table width="1000" cellspacing="0" cellpadding="0" >
        		<tr>
		          	<td width="250" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          		<font size="3" face="Verdana">
		          			<b><bean:message key="IndexComparision.title" /> </b>
		          		</font>
		         	</td> 
	          </tr>
			</table> 
			<%				     		    
			     	   	   	String filename=null;
   		    		   //     GraphMethods gm=new GraphMethods();
   	    				 GraphMethodsPf gm = ConnectInit.getGraphMethodsPf();
   	    				    filename=gm.getGraphChartIndexCompare(session,new PrintWriter(out));
   	    				    String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
   	    				    log.info("graph url is :"+graphURL);
   	    	%>
   	    	
			<logic:equal value="0" name="dataCount" >
	  		  					<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
	  	   	 						<tr>
          							<td width="99"></td>
	  			   					<td class="gridStyle-message" align="center" valign="middle">
          							<p style="margin-left: 9"><bean:message key="IndexCompareOHCL.ndata" /></p>
          							</td>
        						</tr>
       							</table>
       		</logic:equal>
	
			<logic:notEqual value="0" name="dataCount">
				   <br><br>
				   <table align="center">
						   <tr> <td width="162" nowrap="nowrap">
					    		<img src="<%= graphURL %>" height="600"  width="800" >
						    </td></tr>
				   </table> 
			</logic:notEqual>	
		</div>
		
		
		
		<div id="indreturnvol1" style="display:none">
		 	
			
			<table width="1000" cellspacing="0" cellpadding="0" > <!-- Table for Displaying Title -->
        			<tr>
		          		<td width="335" class="subHeader" nowrap="nowrap">
		          			&nbsp;
		          		</td>
		          		<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          				<font size="3" face="Verdana">
		          					<b><bean:message key="IndexReturnVolatility.title" /></b>
		          				</font>
		         		</td> 
	          		</tr>
			</table>
			<bean:define id="details" name="batchReportBean" property="final_Vector"/>
	  		<bean:size id="dataCount" name="batchReportBean" property="final_Vector"/>
		 	<logic:equal value="0" name="dataCount" >
	  		  					<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
	  	   	 						<tr>
          							<td width="99"></td>
	  			   					<td class="gridStyle-message" align="center" valign="middle">
          							<p style="margin-left: 9"><bean:message key="IndexCompareOHCL.ndata" /></p>
          							</td>
        						</tr>
       							</table>
       		</logic:equal>
	
			<logic:notEqual value="0" name="dataCount">
       		 
            	
	      		<table class="sorted" ID="sortTable" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
				<thead>
					<tr>
      					<!-- <table border="1" width="60%" align="center" cellpadding="3" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">	      		 			     			       				
							<tr> -->
								<!-- Table Heading -->
				            	<th  nowrap="nowrap" align="center" id="indexname" class="gridStyle-header">
				            	<span><b><bean:message key="indexcompose.indexname" /></b></span></th>
				            	<th  nowrap="nowrap" align="center" id="Periodic" class="gridStyle-header">
				            	<span><b><bean:message key="StockPerformance.Periodic" /></b></span></th>
				           		<th  nowrap="nowrap" align="center" id="Volatility" class="gridStyle-header">
				           		<span><b><bean:message key="IndexReturnVolatility.Volatility" /></b></span></th>
	         			   </tr>
	         		</thead>
					<tbody>
	           		<!-- Iterate over the table data -->		
	      			<logic:iterate id="details" name="batchReportBean" property="final_Vector">
		     			<tr>				
			      			<td nowrap="nowrap" align="center" class="gridStyle-odd" axis="sstring" headers="indexname"
           			title='<bean:write name="details" property="strr2"/>'>
			          			<font face="Verdana" color="black" size="2">  
			          				<bean:write name="details" property="strr2"/>
			       				</font>
			    			</td>
			          							
			      			<td  nowrap="nowrap" align="center" class="gridStyle-odd" axis="number" headers="Periodic"   
	  		title='<bean:write name="details" property="strr3"/>'>
			          			<font face="Verdana" color="black" size="2">  
			          				<bean:write name="details" property="strr3"/>
			          			</font>
			    			</td>
			          							
			    			<td  nowrap="nowrap" align="center" class="gridStyle-odd" axis="number" headers="Volatility"   
	  		title='<bean:write name="details" property="strr4"/>'>
			      				<font face="Verdana" color="black" size="2">  
			          				<bean:write name="details" property="strr4"/>  
			          			</font>
			    			</td>
			          			
						</tr>
	   				</logic:iterate>
	   				</tbody>
	   			</table>
	   			
          	 </logic:notEqual>
    	  

		 	
		</div>
		
		
		
		
		<div id="indexcorel1" style="display:none">
		<p align="center">
			<table width="1000" cellspacing="0" cellpadding="0" >
        		<tr>
		          	<td width="335" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          			<font size="3" face="Arial">
		          				<b><bean:message key="IndexCorrelation.title" /> </b>
		          			</font>
		         	</td> 
	          </tr>
			</table>
       		
       <%
       		BatchReportForm brf1=new BatchReportForm();
	 		Vector vec1=new Vector();
	 		brf1.setSelectUser("4");
	 		vec1=brf1.getCorelParam();
	 		log.info("cirel1"+vec1);
	 		String fromdate1 = (String)vec1.get(0);
         	log.info("cirel2"+vec1);
        	String toDate1 =(String)vec1.get(1);
        	log.info("cirel3"+vec1);
        	
        	String[] var1=(String [])vec1.get(2);
        	//String[] var1={(String)vec1.get(2),(String)vec1.get(3)};
			log.info("corel123"+vec1);
         	Object arr1 = null;
         	session.setAttribute("arr1",var1);
         	String url = "";
			if(var1 != null)
			{
				for(int i=0;i < var1.length;i++){
					url = url + "&D1=" + var1[i];
				}
			}
         	
	  %>
	 
	 	<table width="656" >
         	<tr>
         		<td width="202" nowrap="nowrap" align="right">
	
	 
       <font size="2" face="Arial">
           
        </td>
                
        <%
                    org.jfree.chart.demo.servlet.CalculateCorrelation cc=new org.jfree.chart.demo.servlet.CalculateCorrelation();
        %> 
	       	</tr>
        </table>        
		<br></br> 
        <table  border="0" width="900" cellpadding="0" cellspacing="0">
  		<tr>
			 <td width="130" nowrap="nowrap">&nbsp;</td>
			 <td width="770" nowrap="nowrap">	
	<% 
	if(var1==null)
	{
	%>
		<table border="0" align="left" width="631" height="222" class="gridStyle" cellspacing="0" cellpadding="0">
          <tr>
          <td  align="center" valign="middle" class="gridStyle-message">
              <p style="margin-left: 9"><b><bean:message key="IndexCompareOHCL.ndata" /></b> </p>
            </td>
            </tr>
         </table>
     <% }else {  %>
		<table border="0" align="left" width="90%" class="gridStyle" cellspacing="0" cellpadding="5">
          <tr>
          <td width="15%" class="gridStyle-header" align="center" valign="middle"></td>
         <% 	Vector vid=cc.getId_name(var1); 	
         		Object ci1 = null;
				session.setAttribute("ci1",new Vector(vid));
          		Iterator i_id=vid.iterator();  
          	    while(i_id.hasNext())
                { 
                	String id=(String)i_id.next();
         %>
            <td width="15%" class="gridStyle-header" align="center" valign="middle"><%= i_id.next() %></td>
             <% } %>
           </tr>
           <% 
           		Vector cor=cc.getCalculatedCorrelation(fromdate1,toDate1);
           		Object ci2 = null;
				session.setAttribute("ci2",new Vector(cor));
           		Iterator i_cor=cor.iterator();      	
	            while(i_cor.hasNext())
                {  %>
                <tr>
                   	<td width="15%"  align="left" valign="left" class="gridStyle-blue"><%= i_cor.next() %></td>
           		 <%	
                		Iterator i_id1=vid.iterator();           		 	
	           			 while(i_id1.hasNext())
                		{ 
                			String id1=(String)i_id1.next();   
                			String id2=(String)i_id1.next();              			
                	%>
                	<td width="15%"  align="center" valign="right" class="gridStyle-blue"><%= i_cor.next() %></td>
           			<% } %>
           		</tr>
           	<% } %>
        	 </table>
		 <%	} %>      
      	   </td>
      	 </tr>
     
	   </table>  

		</div>
		
		
		
		<div id="indcompohlc1" style="display:none">
		
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
         	<% 
	 		BatchReportForm brf2=new BatchReportForm();
	 		brf2.setSelectUser("4");
	 		Vector vect=new Vector();
	 		vect=brf2.getOhlcParam();
	 		
	 		String fromdate = (String)vect.get(0);
        	String toDate =(String)vect.get(1);
        	
	 	//	org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
		ComposeIndex ci = ConnectInit.getComposeIndex();
			String[] var=(String [])vect.get(2);
						
			String url1 = "";
			if(var != null)
			{
				for(int i=0;i < var.length;i++){
						
						url1 = url1 + "&D1=" + var[i];
				}
				
			}
	%>
	
          
        
        <%
           
        log.info("before firing query");
      AdjustDecimal ad = ConnectInit.getAdjustDecimal(); 
    //    org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
        org.jfree.chart.demo.servlet.IndexCompareOHLC comp=new org.jfree.chart.demo.servlet.IndexCompareOHLC();
        org.jfree.chart.demo.servlet.FieldSort sort=new org.jfree.chart.demo.servlet.FieldSort();
       
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
      
    	%>
    	
		
	<table  border="0" width="900" cellpadding="0" cellspacing="0">
  	<tr>
	 <td width="10" nowrap="nowrap">&nbsp;</td>
	 <td width="870" nowrap="nowrap">		
				
				<% if((var==null)){ %>
				<table border="0" align="center" width="631" height="222" cellspacing="0" cellpadding="0">
          <tr>
          <br>
          <td  bgcolor="#cacaca" align="center" valign="middle" nowrap="nowrap">
              <p style="margin-left: 9"><b><font face="Arial" color="blue" size="5">
             <bean:message key="IndexCompareOHCL.messaged" /></b></font></p>
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
             <bean:message key="IndexCompareOHCL.ndata" /></font></p>
            </td>
            </tr>
          </table>
      <% }else {  
      				int dir=0;
      				log.info("before arrange vector compare OHLC");
      				Vector v11=comp.ArrangeVectorCompareOHLC(v3); 
      				Object ci2h = null;
					session.setAttribute("ci2h",new Vector(v11));
      				Vector vid=comp.getVector_vid();
      				int column=(vid.size()*4)+1;
      				String clmno=new Integer(column).toString();
      				String idcorr="D1=";
      				if(vid.size()!=0){
	 				  for(int w=(vid.size()-1);w>=0;w--){
						if(w==(vid.size()-1)){
							idcorr="D1="+(String)vid.get(w);
						} else {
						
								idcorr=idcorr+"&D1="+(String)vid.get(w);	
								
						}
					  }	
					}
					if(fieldno==0) {
				 		dir=sort.getcount();
				 		v11=sort.SetOrderSortDate(v11,0,column);
				 	}						
      	%>   
	 <p >
 
	 
        <table border="0" width="96%" class="gridStyle" cellspacing="1" cellpadding="2">
         <tbody> 
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
         
            <td width="7%" class="gridStyle-header" align="center" valign="middle" nowrap="nowrap"><a href="IndexCompareOHLC.jsp?<%= idcorr %>&from=<%=fromdate%>&to=<%=toDate%>&B1=View&FieldNo=1&ColNo=<%=column%>" onmouseover="window.status='';return true"><bean:message key="corporate.Date" />
             
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
       <% 	}
			}
			}
		}
    	%> 

  	</tbody>
	</table>
         
     </div> 
					
		
		
		</TD>
 	  </TR>
 	</TABLE> 		
	
	<html:form action="/batchReportAction">
	<html:hidden property="indexName" name="batchReportBean" />
	<html:hidden property="defaultVal" ></html:hidden>
	<html:hidden property="compute" value="no"></html:hidden>
				
	 
</html:form>	

    
</body>

<script language="javascript">
var man1=document.getElementById("hiddenTable");
man1.style.display= "none"; 

var selectedtablink="";
var tcischecked=false;



function initialize() {
	
	
}
function indcompare() {
		//alert("indcompare");
		changeDisplay("indcompare1","inline");
		changeDisplay("indreturnvol1","none");
		changeDisplay("indexcorel1","none");
		changeDisplay("indcompohlc1","none");
 		document.forms[0].compute.value="yes";
        document.forms[0].defaultVal.value="no";
       	//alert(document.forms[0].compute.value);
        return false;
}
	

function indreturnvol(){
		//alert("indreturnvol");
		changeDisplay("indreturnvol1","inline");
		changeDisplay("indcompare1","none");
		changeDisplay("indexcorel1","none");
		changeDisplay("indcompohlc1","none");
		document.forms[0].compute.value="yes";
        document.forms[0].defaultVal.value="no";
        //alert(document.forms[0].compute.value);
        return false;
       	
}

function indexcorel(){
		//alert("indexcorel");
		changeDisplay("indexcorel1","inline");
		changeDisplay("indreturnvol1","none");
		changeDisplay("indcompare1","none");
		changeDisplay("indcompohlc1","none");
		document.forms[0].compute.value="yes";
        document.forms[0].defaultVal.value="no";
        //alert(document.forms[0].compute.value);
        return false;
       	
}

function indexcomohlc(){
		//alert("indexcomohlc");
		changeDisplay("indcompohlc1","inline");
		changeDisplay("indexcorel1","none");
		changeDisplay("indreturnvol1","none");
		changeDisplay("indcompare1","none");
		document.forms[0].compute.value="yes";
        document.forms[0].defaultVal.value="no";
        //alert(document.forms[0].compute.value);
        return false;
       	
}
function changeDisplay( elementId, setTo ) {
var theElement;
if( document.getElementById ) {
//DOM

theElement = document.getElementById( elementId );
} else if( document.all ) {
//Proprietary DOM
theElement = document.all[ elementId ];

}
if( !theElement ) {
/* The page has not loaded, or the browser claims to
support document.getElementById or document.all but
cannot actually use either */
return;
}

//Reference the style ...
if( theElement.style ) { theElement = theElement.style; }
if( typeof( theElement.display ) == 'undefined' ) {
//The browser does not allow us to change the display style
//Alert something sensible (not what I have here ...)
window.alert( 'Your browser does not support this' );
return;
}
//Change the display style
theElement.display = setTo;
}
</script>
</html:html>


