
<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.text.ParseException" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@page import="org.jfree.chart.demo.servlet.ComposeIndex"%>
<%@page import="org.jfree.chart.demo.servlet.AdjustDecimal"%>
<%@page import="org.apache.log4j.Logger"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page  import="app.*"%>
<%@ page  import="harrier.income.com.report.*"%>
<%@ page import = "java.util.*" %>
<%@ page import = "java.io.*" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
// app.Connect con = new app.Connect();
 Connect con = ConnectInit.getConnect();
 response.setHeader("Cache-Control","no-cache");
 response.setHeader("Pragma","no-cache");
 response.setHeader("Expires","0");
 %>
<html:html>
  <html:base/>
  <head>


    <link rel="stylesheet" type="text/css" href="./StyleSheet.css"  />
    <script language="javascript" src="../Script/codethatcalendarstd.js">
    </script>
    <script language="javascript" src="box_ex.js">
    </script>
			
   
    <style type="text/css">

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
       
       #tablist li a{
       text-decoration: none;
       padding: 3px 0.5em;
       margin-left: 3px;
       border: 1px solid #778;
       border-bottom: none;
       background: white;
       }

       #tablist li a:link, #tablist li a:visited{
       color: navy;
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
    <script type="text/javascript" src="../Script/Event.js">
    </script>
    <script type="text/javascript" src="../Script/SortedTable.js">
    </script>
    <style type="text/css">

       /* table styles*/
       .sorted td, th {border:0;padding:2px 6px;margin:0;border-right:1px solid #336;
       border-bottom:1px solid #336;background-color: #dddddd;color: #black;
       font-size: 10px;padding-left: 2px;}
       td[axis='number'], td[axis='date'] {text-align:right;}
       th {background-color:#cacaca;padding:2px 20px;color: blue;font-size: 12px;
       vertical-align: baseline;line-height: 18px;}
       tfoot td {border-top:0px solid #003;}
       thead th {border-bottom:1px solid #003;}
       .sortedminus {background-color:#ecc;}
       .sortedplus {background-color:#cec;}
    </style>
  </head>
  
<% 
//Logger Logging = Logger.getLogger("PreIndexSelectReportN.jsp");
  String ajax1="";
  ArrayList list=new ArrayList();
			try{
				ajax1=request.getParameter("FromLogin");
					
					if(ajax1.equals("yes")){
					%>
					<body onLoad="menuload();">
				 <% }else{ %>
	 	<body> 
	 	<% } %>
	 	<% 				
			}catch (Exception e) {
				// TODO: handle exception
			//	Logging.error(" Error :"+e.getMessage());
			}	
	
%>
  <body >
    <jsp:useBean id="batchReportBeanN" scope="session" class="harrier.income.com.report.BatchReportFormN"/> 

    <html:form  action="/batchReportActionN">
      
     <%	if(ajax1.equals("yes")){
		
				%> 
      <ul id="tablist">
					
					<li><a href="?FromLogin=yes#" onClick="list();">Index List</a></li>
					<li><a href="?FromLogin=yes#" onClick="movement();">Index Movement</a></li>
					<li><a href="?FromLogin=yes#" onClick="compare();" ><bean:message key="Index_Compare"/></a></li>
					
					
	    </ul> 
	    <TABLE border="2" color="black" width="100%">
		<TR>
		<TD bgcolor="#FFFAFA" width="100%" valign="top" height="500">
		<!------Index List start ------------- -->
			<div id="list" style="display:none" > 
	  		<table width="1000" cellspacing="0" cellpadding="0" >
				    		<tr>
					          	<td width="250" class="subHeader" nowrap="nowrap">
					          		&nbsp;
					          	</td>
					          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
					          		<font size="3" face="Verdana">
					          			<b><bean:message key="DisplayIndexes1.title"/> </b>
					          		</font>
					         	</td> 
				          </tr>
						</table> 
	  		<table class="sorted" ID="sortTable" 
			border="1" align="center" cellpadding="3" cellspacing="0" bgcolor="#EFEFEF">
				<thead>
					<tr>
	
           	<th align="center"  nowrap="nowrap" id="indName"><span><b><bean:message key="indexcompose.indexname"/>
           	</b></span></th>
           	<th align="center" nowrap="nowrap" id="update"><span><b><bean:message key="indexUpdate.Value"/>
           	</b></span></th>
           	 <th align="center" nowrap="nowrap" id="status">&nbsp;</th> 
          	<th align="center"  nowrap="nowrap" id="open"><span><b><bean:message key="DisplayIndexes1.Open"/>
          	</b></span></th>
      		<th align="center"  nowrap="nowrap" id="high"><span><b><bean:message key="DisplayIndexes1.High"/>
      		</b></span></th>
         	<th  align="center"  nowrap="nowrap" id="low"><span><b><bean:message key="DisplayIndexes1.Low"/>
         	</b></span></th>
          	<th align="center"  nowrap="nowrap" id="last"><b><bean:message key="DisplayIndexes1.Last"/>
          	</b></span></th>
        	<th align="center"  nowrap="nowrap" id="change"><span><b><bean:message key="DisplayIndexes1.Change"/>
        	</b></span></th>
         	<th align="center" nowrap="nowrap" id="market"><span><b><bean:message key="DisplayIndexes1.Market"/>
         	</b></span></th>
         	<th  align="center" nowrap="nowrap" id="divisor"><span><b><bean:message key="indcurrwise.divisor"/>
         	</b></span></th>
          	<th align="center"  nowrap="nowrap" id="currency"><span><b><bean:message key="stockmaster.currency"/>
          	</b></span></th>
          	<th align="center"  nowrap="nowrap" id="date"><span><b><bean:message key="corporate.Date"/>
          	</b></span></th>
        </tr>
		</thead>
		<tbody>
		<logic:iterate id="try2" property="details" name="batchReportBeanN">
		
		<tr>
		
           	<td align="left" nowrap="nowrap" axis="sstring" headers="indName"
           			title='<bean:write name="try2" property="indexname"/>'>
            	
          			<a href='./IndexComposeS.jsp?index=<bean:write name="try2" property="indexid"/>&compute=yes&ajax1=yes'>
          			
          			<bean:write name="try2" property="indexname"/></a>
          		
         	</td>
         	<td align="right" axis="number" headers="update" nowrap="nowrap" 
         			title='<bean:write name="try2" property="current"/>'>
          		
          		<bean:write name="try2" property="current"/>
          	</td>
          	<td align="right" axis="sstring" headers="status" nowrap="nowrap">
          		
          		<logic:equal value="up" name="try2" property="status">
          			<img border="0" src="images/up.gif" width="13" align="middle" height="12">
          		</logic:equal>          		
          		<logic:equal value="down" name="try2" property="status">
          			<img border="0" src="images/down.gif" width="13" align="middle" height="12">
          		</logic:equal>
          		<logic:equal value="mid" name="try2" property="status">
          			<img border="0" src="images/mid.gif" width="13" align="middle" height="12">
          		</logic:equal>
          		
          	</td>  
      		<td align="right" axis="number" headers="open" nowrap="nowrap"
      				title='<bean:write name="try2" property="indexopen"/>'>
          		
          		<bean:write name="try2" property="indexopen"/>
          	</td>
          	<td align="right" axis="number" headers="high" nowrap="nowrap"
          			title='<bean:write name="try2" property="indexhigh"/>'>
          		
          		<bean:write name="try2" property="indexhigh"/>
          	</td>
          	<td align="right" axis="number" headers="low" nowrap="nowrap"
          			title='<bean:write name="try2" property="indexlow"/>'>
          		
          		<bean:write name="try2" property="indexlow"/>
          	</td>
          	<td align="right" axis="number" headers="last" nowrap="nowrap"
          			title='<bean:write name="try2" property="indexclsv"/>'>
       			
          		<bean:write name="try2" property="indexclsv"/>
          	</td>
          	<td align="right" axis="number" headers="change" nowrap="nowrap"
          			title='<bean:write name="try2" property="vachange"/>'>
          		
          		<bean:write name="try2" property="vachange"/>
          	</td>
          	<td  align="right" axis="number" headers="market" nowrap="nowrap"
          			title='<bean:write name="try2" property="tmcv"/>'>
        		
         		<bean:write name="try2" property="tmcv"/>
          	</td>
          	<td  align="right" axis="number" headers="divisor" nowrap="nowrap"
          			title='<bean:write name="try2" property="divisor"/>'>
          		
          		<bean:write name="try2" property="divisor"/>
          	</td>
          	<td  align="right" axis="sstring" headers="currency" nowrap="nowrap"
          			title='<bean:write name="try2" property="currency"/>'>
          		
          		<bean:write name="try2" property="currency"/>
          	</td>
          	<td  align="right" nowrap="nowrap" axis="date" headers="date"
          			title='<bean:write name="try2" property="indexdate"/>'>
          		
          		<bean:write name="try2" property="indexdate"/> 
          	</td>
          </tr>
          
          </logic:iterate>
        </tbody>  
	</table>
	        </div>
	        
	     <!------Index Compare start ------------- --> 
	     <div id="compare" style="display:inline">
	  		
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
						    try {
							String[] var1={"",""};
							String[] var2={"",""};
							Connection connection = null;
				 			PreparedStatement pst = null;
				 			ResultSet rs = null;
				 			String ids=null;
				 			String str2=null;
				 			String str1=null;
				 			Date curr=new Date();      
	     					SimpleDateFormat ft=new SimpleDateFormat("dd-MM-yyyy");
	      					String toDate=ft.format(curr);
	     
	     					long newd=30*24*60*60*1000L;
	     					java.util.Date s4= new java.text.SimpleDateFormat("dd-MM-yyyy").parse(toDate);
	     					long t2=s4.getTime();
	     					long t1=t2-newd; 
	     					Date s1=new Date(t1);
	     					SimpleDateFormat ft1=new SimpleDateFormat("dd-MM-yyyy");
	    					String fromdate=ft1.format(s1); 
	    				 	
	     					PrintWriter pw=response.getWriter();
	    					
			 	
						 				try {
						 					if (connection == null)
						 						connection = con.getdbConnection();
						 					try {
						 						String query = ConnectInit.queries.getProperty("Select_from_prefrenctial_detail");
						 						pst = connection.prepareStatement(query);
						 						pst.setString(1, "6");
						 						pst.setString(2, "Index Comparison");
						 						rs = pst.executeQuery();
						 						while (rs.next()) {
						 							ids = rs.getString(1);
						 						}
						 						str1=ids.substring(0,4);
						 						str2=ids.substring(5,ids.length());
						 						var2[0]=str1;
						 						var2[1]=str2;
						 						var1=var2;
						 						session.setAttribute("indexids",var1);
									 			session.removeAttribute("sfdate");
									 			session.setAttribute("sfdate",fromdate);
									 			session.removeAttribute("stdate");
									 			session.setAttribute("stdate",toDate);
						 					} catch (Exception e) {
						 						// TODO: handle exception
						 					//	Logging.error(" Error : " + e.getMessage());
						 					}
						 				} catch (Exception e) {
						 				//	Logging.error(" Error : " + e.getMessage());
						 				} finally {
						 					try {
						 						if (connection != null)
						 							connection.close();
						 					} catch (Exception ee) {
						 						
						 					//	Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
						 					}
						 				}
						    }
						 		
						 			
				 			catch(Exception e){
				 				
				 			}			     		    
			     	   	   	String filename=null;
   		    		      //  GraphMethodsPf gm=new GraphMethodsPf();
   	    				    GraphMethodsPf gm= ConnectInit.getGraphMethodsPf();
   	    				    filename=gm.getGraphChartIndexCompare01(session,new PrintWriter(out));
   	    				    String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
   	    				    //log.info("graph url is :"+graphURL);
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
								    		<img src="<%= graphURL %>" height="270"  width="500" >
									    </td></tr>
								  </table> 
						</logic:notEqual>	
					
	
	        </div>
	             	<!------Index Movement start ------------- -->
      <div id="movement" style="display:none">
      
			 <table width="1000" cellspacing="0" cellpadding="0" >
				    		<tr>
					          	<td width="250" class="subHeader" nowrap="nowrap">
					          		&nbsp;
					          	</td>
					          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
					          		<font size="3" face="Verdana">
					          			<b>Index Movement </b>
					          		</font>
					         	</td> 
				          </tr>
						</table> 
			
			
     				
        				<table  border="0" width="50%" align="center" cellpadding="0" cellspacing="0">
        					<tr>
        						<td>
        							<%
        							
        							
        						    try{
							Connection connection = null;
				 			PreparedStatement pst = null;
				 			ResultSet rs = null;
				 			String var=null;
				 			Date curr=new Date();      
	     					SimpleDateFormat ft=new SimpleDateFormat("dd-MM-yyyy");
	      					String to=ft.format(curr);
	     
	     					long newd=30*24*60*60*1000L;
	     					java.util.Date s4= new java.text.SimpleDateFormat("dd-MM-yyyy").parse(to);
	     					long t2=s4.getTime();
	     					long t1=t2-newd; 
	     					Date s1=new Date(t1);
	     					SimpleDateFormat ft1=new SimpleDateFormat("dd-MM-yyyy");
	    					String from=ft1.format(s1);
	     					PrintWriter pw=response.getWriter();
						 				try {
						 					if (connection == null)
						 						connection = con.getdbConnection();
						 					try {
						 						String query = ConnectInit.queries.getProperty("Select_from_prefrenctial_detail");
						 						pst = connection.prepareStatement(query);
						 						pst.setString(1, "5");
						 						pst.setString(2, "Index Composition");
						 						rs = pst.executeQuery();
						 						while (rs.next()) {
						 							var = rs.getString(1);
						 						}
						 					session.setAttribute("varIndexId",var);
        						      		session.setAttribute("filename","IndexMovement.xls");
        						      		session.setAttribute("to",to);
        						     	 	session.setAttribute("from",from);
        						      		session.setAttribute("varCheckAvg","checked");
        						      		session.setAttribute("varSpan","15");
        						      		session.setAttribute("varChart","1");
        						      		session.setAttribute("chartType","maverage");
						 					} catch (Exception e) {
						 						// TODO: handle exception
						 				//		Logging.error(" Error : " + e.getMessage());
						 					}
						 				} catch (Exception e) {
						 				//	Logging.error(" Error : " + e.getMessage());
						 				} finally {
						 					try {
						 						if (connection != null)
						 							connection.close();
						 					} catch (Exception ee) {
						 						try {
						 							if (connection != null)
						 								connection.close();
						 						} catch (Exception ex) {
						 						//	Logging.error(" Error : Unable to close Connection "+ ex.getMessage());
						 						}
						 				//		Logging.error(" Error : Unable to close Connection "+ ee.getMessage());
						 					}
						 				}
						 		
						 			}
				 			catch(Exception e){
				 				
				 			}
        							GraphMethodsPf objGM =ConnectInit.getGraphMethodsPf();
        							//	GraphMethodsPf objGM = new GraphMethodsPf();
        								//log.info("Before calling getGraphchart");
        						 		objGM.getGraphChart1(session,new PrintWriter(out),"maverage");
        						 		//objGM.getGraphChart1(request,new PrintWriter(out));
        						 	 	String filename1=objGM.getFilename();
     							  		//String graphURL1=objGM.getGraphURL();
     							  		String graphURL1= request.getContextPath() + "/servlet/DisplayChart?filename=" + filename1;   
     							  		//log.info("After calling getGraphchart");
     							  	%>
    							   <img id ="strutsgraph" src="<%= graphURL1 %>" width="500" height="270" border="0">
    							   <!-- usemap="#< %= filename %>" -->     
    							</td>
	 						</tr>
        				</table>
        		
			
			</div>	
        		
   
        	
	        </TD></TR>
   </TABLE>     	
        	
      <% } %>
       <%	if(ajax1.equals("no")){%>
       <p>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </p>
      <table width="800" cellspacing="0" cellpadding="0" >
	   		<tr>
			 <td nowrap="nowrap" align="right">&nbsp;&nbsp;&nbsp;&nbsp; 
			  <b>Export To</b>
                 <font size="1" face="Verdana" color="blue">
                   	 <a href="FileDownloadXmlAll.jsp">all in one xml</a>
                      &nbsp;&nbsp;
                     <a href="FileDownloadExcelAll.jsp"><bean:message key="Batch.all_in_one_excel"/></a>
                      &nbsp;&nbsp;
					 <a href="PdfReportBatchAll.jsp"><bean:message key="Batch.all_in_one_Pdf"/></a>					 
					  &nbsp;&nbsp;	
			 		 <!-- <a href="EmailReportBatchAll.jsp"><bean:message key="Batch.all_in_one_emailreport"/></a>	
				 	-->
            </td>
			</tr>
	 </table>
      <ul id="tablist">
        <%
         harrier.income.com.report.BatchReportFormN abc=(harrier.income.com.report.BatchReportFormN)session.getAttribute("batchReportBeanN");
        
         String user1=abc.getSelectUser();
         //log.info("USER ISISISIS="+user1);
         
  		 if(abc.is1())
         {
         %>		
        <li><a href="?FromLogin=no#" onClick="one1()"><bean:message key="PreIndex.idxcmp" /></a></li>
        <%		
         }	
         if(abc.is2())
         {
         %>    
        <li><a href="?FromLogin=no#" onClick="two1()"><bean:message key="PreIndex.idxdiv" /></a></li>
        <%
         }
         if(abc.is3())
         {
         %>		
        <li><a href="?FromLogin=no#" onClick="three1()"><bean:message key="IndexCurrencyWise.title" /></a></li>
        <%			
         }
         if(abc.is4())
         {
         %>		
        <li><a href="?FromLogin=no#" onClick="four1()"><bean:message key="PreIndex.idxpepb" /></a></li>
        <%		
         }
        
		 if(abc.is6())
         {%>    
        <li><a href="?FromLogin=no#" onClick="six1()"><bean:message key="Capitalctuniverse.title" /></a></li>
        <%
         }
         if(abc.is5())
         {
         %>		
        <li><a href="?FromLogin=no#" onClick="five1()"><bean:message key="StockDetailFromDate.title" /></a></li>
        <%			
         }
      
         if(abc.is7())
         {%>    
        <li><a href="?FromLogin=no#" onClick="seven1()"><bean:message key="Company_Weightage" /></a></li>
        <%
         }
         if(abc.is8())
         {
         %>		
        <li><a href="?FromLogin=no#" onClick="eight1()"><bean:message key="Industry_Weightage" /></a></li>
        <%			
         }
         if(abc.is9())
         {
         %>		
        <li><a href="?FromLogin=no#" onClick="nine1()"><bean:message key="Stock_Contribution_to_Change_In_Index" /></a></li>
        <%		
         }
         if(abc.is100())
         {%>    
        <li><a href="?FromLogin=no#" onClick="ten1()"><bean:message key="Index_Compare" /></a></li>
        <%
         }
         if(abc.is101())
         {
         %>		
        <li><a href="?FromLogin=no#" onClick="eleven1()"><bean:message key="Index_Compare_OHLC" /></a></li>
        <%			
         }
         if(abc.is102())
         {
         %>		
        <li><a href="?FromLogin=no#" onClick="twelve1()"><bean:message key="Index_Correlation" /></a></li>
        <%		
         }
         if(abc.is103())
         {
         %>		
        <li><a href="?FromLogin=no#" onClick="thirteen1()"><bean:message key="Index_Returns_And_Volatility" /></a></li>
        <%		
         }
      
        
    	if(abc.is105())
         {%>    
        <li><a href="?FromLogin=no#" onClick="fifteen1()"><bean:message key="Stock_Dividend" /></a></li>
        
        
        <%		
         }
              
    	if(abc.is106())
         {%>    
        <li><a href="?FromLogin=no#" onClick="sixteen1()"><bean:message key="Index_Movement" /></a></li>
        
        <%
         }
         
         if(abc.is104())
         {
         %>		
        <li><a href="?FromLogin=no#" onClick="fourteen1()"><bean:message key="Traded_Volume" /></a></li>
        <%			
         }
         %>
         <%	if(!(ajax1.equals("yes"))){
				%>
             <li><html:submit property="backButton" ><bean:message key="corporate.Back"/>
            </html:submit></li>
       <%			
         }
         %> 
      </ul>  
      <TABLE  border="2" color="black" width="100%">
        <TR> 
          <TD bgcolor="#FFFAFA" width="100%" valign="top" height="500"> 
         <%
         harrier.income.com.report.BatchReportFormN brf=(harrier.income.com.report.BatchReportFormN)session.getAttribute("batchReportBeanN");
            
              if(abc.is2())
             { 
         %>
            <!------Index Divisor start ------------- -->
            
           
            <div id="two" style="display:none">
            
            <%
			    	
	    	brf.getTableData2();
	    		
		    String var19=brf.get32();
			session=request.getSession();
			session.setAttribute("var19",var19);
			session.setAttribute("selectIndex19",brf.getIndex_name11());
			String iename19=brf.getIndex_name11();
			session.setAttribute("ci2divisor",brf.getVExcel());
			String fdate19=brf.get12();
			String tdate19=brf.get22();
			//session.setAttribute("fdate19",fdate19);
			//session.setAttribute("tdate19",tdate19);
			session.setAttribute("iename19",iename19);
			
             String temp_path19 = "./FileDownloadBatchFinal.jsp?&type=19&from="+fdate19+"&to="+tdate19+"&filename=IndexDivisor.xls";
             String email_url19 = "./EmailReportBatchFinal.jsp?switch_type=19&from="+fdate19+"&to="+tdate19+"&cas=19&rname=IndexDivisor.xls";
             String xml_path19 = "./FileDownloadXml.jsp?var="+var19+"&type=19&from="+fdate19+"&to="+tdate19+"&filename=IndexDivisor.xml";
	    	 String pdf_path19 = "./FileDownloadPdf.jsp?var="+var19+"&type=19&from="+fdate19+"&to="+tdate19+"&filename=IndexDivisor.pdf";
	    	
	    	AddFactData  data3=new AddFactData(var19,"19",iename19,fdate19,tdate19,null);          
			list.add(data3);
             
	  		%>
	  			
	  					
	
	  		<table width="1000" cellspacing="0" cellpadding="0" > <!-- Table for Displaying Title -->
	  
	  		<tr>
			 <td nowrap="nowrap" align="right">&nbsp;&nbsp;&nbsp;&nbsp; 
			  
                 <font size="1" face="Verdana" color="blue">
                   
                     <a href="javascript:PrintThisPage19();" ><bean:message key="LatestDivisor.printerf" /></a>
                     &nbsp;&nbsp;
                    
                     
                     <a href='<%= temp_path19 %>'><bean:message key="LatestDivisor.downloade" /></a>     
					 &nbsp;&nbsp;
					 <a href='<%= xml_path19 %>'><bean:message key="PreIndex.Export_to_Xml" /></a>     
					 &nbsp;&nbsp;
					 <a href='<%= pdf_path19 %>'><bean:message key="PreIndex.Export_to_Pdf" /></a>
					 &nbsp;&nbsp;
					 <a href='<%= email_url19 %>'><bean:message key="LatestDivisor.emailr"/></a>    
				</font>
            </td>
			</tr>
			            		
        	<tr>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          			<font size="3" face="Verdana">
		          				<b><bean:message key="IndexDivisor.title" /></b>
		          			</font>
		         	</td>
	       	</tr>
	 	 	</table>
	  		
              <bean:define id="details" name="batchReportBeanN" property="tableData2"/>
              <logic:empty property="tableData2" name="batchReportBeanN">
	  		
	  			
                <table  border="0" width="550" height="250" cellpadding="0" cellspacing="0" align="center" class="sortable">	
                  <tr>
                  </tr>
                  <tr>
                    <td  bgcolor="#cacaca" align="center" valign="middle" nowrap="nowrap">
                      <p style="margin-left: 9">
                        <b><font face="Verdana" color="blue" size="4">
                            <bean:message key="IndexCompareOHCL.ndata" />
                          </font></b>
                    </td>
                  </tr>
                </table>
              </logic:empty>
              <logic:notEmpty property="tableData2"  name="batchReportBeanN"> 
	     
                <table border="0" width="85%"  align="left" cellpadding="2" cellspacing="0" >
     	  		
                  <tr>
                    <td>
                      <table class="sorted" ID="sortTable" 
                       border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
                        <thead>
                          <tr>
                            <!-- <table border="1" width="50%" bgcolor="white"  align="center" cellpadding="2" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
                             <tr> -->
                            <th width="15%" nowrap="nowrap" align="center" id="trddate">
                              <span><b><bean:message key="IndexDivisor.trddate" /></b></span>
                            </th>
                            <th width="15%" nowrap="nowrap" align="center" id="close">
                              <span><b><bean:message key="IndexCompareOHCL.close" /></b></span>
                            </th>
                            <th width="15%" nowrap="nowrap" align="center" id="mcp">
                              <span><b><bean:message key="IndexDivisor.mcp" /></b></span>
                            </th>
                            <th width="15%" nowrap="nowrap" align="center" id="divisor">
                              <span><b><bean:message key="indcurrwise.divisor" /></b></span>
                            </th>
                          </tr>
                        </thead>
                        <tbody>
                          <logic:iterate id="details" name="batchReportBeanN" property="tableData2">
                            <tr>
                              <td width="15%" nowrap="nowrap" align="center" axis="date" headers="trddate"
                               title='<bean:write name="details" property="tradingDate"/>'>
                                <font face="Verdana" color="blue" size="2">
                                  <bean:write name="details" property="tradingDate"/>
                                </font>
                              </td>
                              <td width="15%" nowrap="nowrap" align="right" axis="number" headers="close"   
                               title='<bean:write name="details" property="close"/>'>
                                <font face="Verdana" color="blue" size="2">
                                  <bean:write name="details" property="close"/>
                                </font>
                              </td>
                              <td width="15%" nowrap="nowrap" align="right" axis="number" headers="mcp"   
                               title='<bean:write name="details" property="mcap"/>'>
                                <font face="Verdana" color="blue" size="2">
                                  <bean:write name="details" property="mcap"/>
                                </font>
                              </td>
                              <td width="15%" nowrap="nowrap" align="right" axis="number" headers="divisor"   
                               title='<bean:write name="details" property="divisor"/>'>
                                <font face="Verdana" color="blue" size="2">
                                  <bean:write name="details" property="divisor"/>
                                </font>
                              </td>
                            </tr>
                          </logic:iterate>
                        </tbody>
                      </table>
                    </td>
                  </tr>          	
                </table>
         
              </logic:notEmpty>
     
            </div>
            <%
             }
             if(abc.is3())
             {
             %>		
            <!----------- Start of Index in different currencies --------- -->
            <div id="three" style="display:none">	
			<jsp:useBean id="indexCalculatorCollection" scope="session" class="harrier.income.com.report.IndexCalculatorCollection"/>
   		<table width="1000" cellspacing="0" cellpadding="0" >
        		<tr>
		          	
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          		<font size="3" face="Verdana">
		          			<b><bean:message key="IndexCurrencyWise.title" /> </b>
		          		</font>
		         	</td> 
	          	</tr>
		</table> 
		<table border="0" width="100%" class="gridStyle" cellspacing="0" cellpadding="0">
   			<tr> 
   			<% 	
			//harrier.income.com.report.BatchReportFormN brf1=(harrier.income.com.report.BatchReportFormN)session.getAttribute("batchReportBeanN");
  			String var=brf.getCurrencyParam();
			if(var==null){
   			var="1";
   		}                       
            if(var!=null)
            {
                    	
                       	indexCalculatorCollection.addStocksInIndexCalculatorTablePrice(var,request);
            }
                %>  
                                         
   </tr>  
                       <%                                              
                        if(var!=null)
                        {
                        	indexCalculatorCollection.addInIndexCurrencyWise(var,request);
                        }else{ var="1";  }
                         %>  
                         <br>                
  
        	<tr><td><br></br></td></tr>
       	</table>
    	<table border="1" width="100%" class="gridStyle" cellspacing="0" cellpadding="0">
 			   
    		<tr>
       		  <td width="100%">
      			<table border="1" width="100%" class="gridStyle" cellspacing="0" cellpadding="0">
      	
         			<tr>   
         				<td width="5%" align="center" class="griStyle-header"><input type="checkbox" name="currid" onclick="CheckAll(this)"/></td>     
          				<td width="30%" align="center" class="gridStyle-header"><bean:message key="indcurrwise.currencyName"/></td>
           				<td width="23%" align="center" class="gridStyle-header"><bean:message key="indcurrwise.tmcv" /></td>
           				<td width="22%" align="center" class="gridStyle-header"><bean:message key="indcurrwise.divisor" /></td>           
           				<td width="20%" align="center" class="gridStyle-header"><bean:message key="indcurrwise.indValue" /></td>    
           			</tr> 
           			<%     
           	StringBuffer str=null;
           		 str = indexCalculatorCollection.addRowsInIndexCurrencyWise(indexCalculatorCollection.table,request);     
           	if(str!=null){%>
		<%=str.toString()%>
         <% } %> 
           		</table>
           	  </td>
           	</tr>
       </table>
       
   </div>	
		
   	   	  
            <%			
             }
             if(abc.is1())
             {
             %>	
            <!--------Start of Index Composition ------- -->	
            <div id="one" style="display:inline"> 
	
			 <%
              
             brf.getTabledata3();
             String var1=brf.get31();
             session=request.getSession();
             String iename1=brf.getIndex_name11();
             
             session.setAttribute("iename1",iename1);
             
			 session.setAttribute("ci2Composition",brf.getVw());
			 	  	  		
		  	 String excel_path1 = "./FileDownloadBatchFinal.jsp?var="+var1+"&type=1&filename=IndexCompositionReport.xls";
		  	 String str_url1 = "./EmailReportBatchFinal.jsp?switch_type=1&cas=1&rname=IndexCompositionReport.xls&varid="+var1;
	    	 String xml_path1 = "./FileDownloadXml.jsp?var="+var1+"&type=1&filename=IndexCompositionReport.xml";
	    	 String pdf_path1 = "./FileDownloadPdf.jsp?var="+var1+"&type=1&filename=IndexCompositionReport.pdf";
	    	
	    	 AddFactData  data1=new AddFactData(var1,"1",iename1,null,null,null);          
			 list.add(data1);
	    	
	    	%>
	  		
          <table align="right">
	  		<tr>
			 <td colspan="2" nowrap="nowrap" align="left">&nbsp;&nbsp;&nbsp;&nbsp; 
                 <font size="1" face="Verdana" color="blue">
                     <!-- Printer friendly -->
                     <a href="javascript:PrintThisPage1();" ><bean:message key="LatestDivisor.printerf" /></a>
                     &nbsp;&nbsp;
                     <!-- DownLoad Excel -->
                     <a href='<%= excel_path1 %>'><bean:message key="LatestDivisor.downloade" /></a>
					 &nbsp;&nbsp;
					 <a href='<%= xml_path1 %>'><bean:message key="PreIndex.Export_to_Xml" /></a>
					 &nbsp;&nbsp;
					 <a href='<%= pdf_path1 %>'><bean:message key="PreIndex.Export_to_Pdf" /></a>
					 &nbsp;&nbsp;
					 <!-- Email Report -->
					 <a href='<%= str_url1 %>' ><bean:message key="LatestDivisor.emailr" /></a>
					 
				</font>
            </td>
		   </tr>
	   	 </table>   
            	
         
              <table width="1000" cellspacing="0" cellpadding="0" >
                <tr>
                  <td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
                    <font size="3" face="Verdana">
                      <b><bean:message key="IndexCompose.title" /></b> 
                    </font>
                  </td>
                </tr>
              </table>
              <bean:define id="try1" name="batchReportBeanN" property="tabledata3"/>
              <logic:empty property="tabledata3" name="batchReportBeanN">
	  		
                <table  border="0" width="550" height="250" cellpadding="0" cellspacing="0" align="center" class="sortable">	
                  <tr>
                  </tr>
                  <tr>
                    <td  bgcolor="#cacaca" align="center" valign="middle" nowrap="nowrap">
                      <p style="margin-left: 9">
                        <b><font face="Verdana" color="blue" size="4">
                            <bean:message key="IndexCompareOHCL.ndata" />
                          </font></b>
                    </td>
                  </tr>
                </table>
              </logic:empty>
              <logic:notEmpty property="tabledata3"  name="batchReportBeanN"> 
        	
                <table border="0" width="800"  cellpadding="2" cellspacing="0"  align="center" id="tabular" >
                  <tr>
                    <td>
                      <table class="sorted" ID="sortTable" 
                       border="1" align="center" cellpadding="3" cellspacing="0" bgcolor="#EFEFEF">
                        <thead>
                          <tr>
         	
                            <th align="center"  nowrap="nowrap"  id="stockName" class="gridStyle-header">
                              <span><b><bean:message key="stockmaster.stockName" /></b></span>
                            </th>
                            <th align="right" nowrap="nowrap" id="tshares" class="gridStyle-header">
                              <span><b><bean:message key="IndexCompose.tshares" /></b></span>
                            </th>
                            <th align="right" nowrap="nowrap" id="iwf" class="gridStyle-header">
                              <span><b><bean:message key="stockmaster.iwf" /></b></span>
                            </th>
                            <th align="right" nowrap="nowrap" id="mlot" class="gridStyle-header">
                              <span><b><bean:message key="IndexCompose.mlot" /></b></span>
                            </th>
                            <th align="right" nowrap="nowrap" id="priceltp" class="gridStyle-header">
                              <span><b><bean:message key="IndexCompose.priceltp" /></b></span>
                            </th>
                            <th align="right" nowrap="nowrap" id="pricelast" class="gridStyle-header">
                              <span><b><bean:message key="IndexCompose.pricelast" /></b></span>
                            </th>
                            <th align="center" nowrap="nowrap" id="currency" class="gridStyle-header">
                              <span><b><bean:message key="IndexCompose.currency" /></b></span>
                            </th>
                            <th align="center" nowrap="nowrap" id="cerate" class="gridStyle-header">
                              <span><b><bean:message key="IndexCompose.cerate" /></b></span>
                            </th>
                            <th align="center" nowrap="nowrap" id="mcap" class="gridStyle-header">
                              <span><b><bean:message key="IndexCompose.mcap" /></b></span>
                            </th>
                            <th align="center" nowrap="nowrap" id="amarket" class="gridStyle-header">
                              <span><b><bean:message key="IndexCompose.amarket" /></b></span>
                            </th>
                            <th align="right" nowrap="nowrap" id="weight" class="gridStyle-header">
                              <span><b><bean:message key="IndexCompose.weight" /></b></span>
                            </th>
                            <th align="center" nowrap="nowrap" id="Date" class="gridStyle-header">
                              <span><b><bean:message key="corporate.Date" /></b></span>
                            </th>
                          </tr>
                        </thead>
                        <tbody>
                          <!-- Iterate over the table data -->
                          <logic:iterate id="try1" property="tabledata3" name="batchReportBeanN">
                            <tr>
                              <td nowrap="nowrap"   align="left" class="gridStyle-odd" axis="sstring" headers="stockName"
                               title='<bean:write name="try1" property="stockname1"/>'>
                                <font face="Verdana" size="2" color="blue">
                                  <a href='../masters/stockmaster2.jsp?s_stockid=<bean:write name="try1" property="stockid"/>'>
                                    <bean:write name="try1" property="stockname1"/></a></font>
                              </td>
                              <td nowrap="nowrap" class="gridStyle-odd" align="right" nowrap="nowrap" axis="number" headers="tshares"
                               title='<bean:write name="try1" property="tis"/>'>
                                <font face="Verdana" size="2" color="blue">
                                  <bean:write name="try1" property="tis"/>
                                </font>
                              </td>
                              <td nowrap="nowrap" class="gridStyle-odd" align="right" nowrap="nowrap" axis="number" headers="iwf"
                               title='<bean:write name="try1" property="iwf"/>'>
                                <font face="Verdana" size="2" color="blue">
                                  <bean:write name="try1" property="iwf"/>
                                </font>
                              </td>
                              <td nowrap="nowrap" class="gridStyle-odd" align="right" nowrap="nowrap" axis="number" headers="mlot"
                               title='<bean:write name="try1" property="market"/>'>
                                <font face="Verdana" size="2" color="blue">
                                  <bean:write name="try1" property="market"/>
                                </font>
                              </td>
                              <td nowrap="nowrap" class="gridStyle-odd" align="right" nowrap="nowrap" axis="number" headers="priceltp"
                               title='<bean:write name="try1" property="adjusted"/>'>
                                <font face="Verdana" size="2" color="blue">
                                  <bean:write name="try1" property="adjusted"/>
                                </font>
                              </td>
                              <td nowrap="nowrap" class="gridStyle-odd" align="right" nowrap="nowrap" axis="number" headers="pricelast"
                               title='<bean:write name="try1" property="last"/>'>
                                <font face="Verdana" size="2" color="blue">
                                  <bean:write name="try1" property="last"/>
                                </font>
                              </td>
                              <td nowrap="nowrap" class="gridStyle-odd" align="left" nowrap="nowrap" axis="sstring" headers="currency"
                               title='<bean:write name="try1" property="currency"/>'>
                                <font face="Verdana" size="2" color="blue">
                                  <bean:write name="try1" property="currency"/>
                                </font>
                              </td>
                              <td nowrap="nowrap" class="gridStyle-odd" align="right" nowrap="nowrap" axis="number" headers="cerate"
                               title='<bean:write name="try1" property="curr_exch_convIratecomp"/>'>
                                <font face="Verdana" size="2" color="blue">
                                  <bean:write name="try1" property="curr_exch_convIratecomp"/>
                                </font>
                              </td>
                              <td nowrap="nowrap" class="gridStyle-odd" align="right" nowrap="nowrap" axis="number" headers="mcap"
                               title='<bean:write name="try1" property="mcv"/>'>
                                <font face="Verdana" size="2" color="blue">
                                  <bean:write name="try1" property="mcv"/>
                                </font>
                              </td>
                              <td nowrap="nowrap" class="gridStyle-odd" align="right" nowrap="nowrap" axis="number" headers="amarket"
                               title='<bean:write name="try1" property="mcv"/>'>
                                <font face="Verdana" size="2" color="blue">
                                  <bean:write name="try1" property="mcv"/>
                                </font>
                              </td>
                              <td nowrap="nowrap" class="gridStyle-odd" align="right" nowrap="nowrap" axis="number" headers="weight"
                               title='<bean:write name="try1" property="strweightage"/>'>
                                <font face="Verdana" size="2" color="blue">
                                  <bean:write name="try1" property="strweightage"/>
                                </font>
                              </td>
                              <td nowrap="nowrap" class="gridStyle-odd" align="right" nowrap="nowrap" axis="date" headers="Date"
                               title='<bean:write name="try1" property="stockprice"/>'>
                                <font face="Verdana" size="2" color="blue">
                                  <bean:write name="try1" property="stockprice"/>
                                </font>
                              </td>
                            </tr>
                          </logic:iterate>
                          <tr>
                            <td colspan="10" nowrap="nowrap" align="right"  class="gridStyle-odd"  >
                              <font face="Verdana" size="2" color="blue">
                                <bean:message key="StockPerformance.Total" />
                              </font>
                            </td> 
                            <td  align="right" nowrap="nowrap" class="gridStyle-odd" >
                              <font face="Verdana" size="2" color="blue">
                                <bean:write name="batchReportBeanN" property="total"/>000
                              </font>
                            </td>
                            <td width="100" align="right" nowrap="nowrap" class="gridStyle-odd" >
                            </td>
                          </tr>
                        </tbody>
                      </table> 
                    </td>
                  </tr>
                  
                </table>
              </logic:notEmpty>
            </div>	
			
					
			
			 <%			
             }
             if(abc.is106())
             {
             %>	
            <!--------Start of Index Movement ------- -->	
            <div id="sixteen" style="display:none"> 
	
			 <table width="1000" cellspacing="0" cellpadding="0" >
				    		<tr>
					          	<td width="250" class="subHeader" nowrap="nowrap">
					          		&nbsp;
					          	</td>
					          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
					          		<font size="3" face="Verdana">
					          			<b>Index Movement </b>
					          		</font>
					         	</td> 
				          </tr>
			</table> 
			<table  border="0" width="50%" align="center" cellpadding="0" cellspacing="0">
        			<tr>
        				<td>
        					<%
        				
        							
        								//GraphMethodsPf objGMIM = new GraphMethodsPf();
        								GraphMethodsPf objGMIM = ConnectInit.getGraphMethodsPf();
        								//log.info("Before calling getGraphchart");
        						 		//objGMIM.getGraphChart1(request,new PrintWriter(out));
        						 		objGMIM.getGraphChart1(session,new PrintWriter(out),"maverage");
        						 	 	String filename1=objGMIM.getFilename();
     							  		//String graphURLIM=objGMIM.getGraphURL(); 
     							  		String graphURLIM= request.getContextPath() + "/servlet/DisplayChart?filename=" + filename1;  
     							  		//log.info("After calling getGraphchart");
     							  	%>
    							   <img id ="strutsgraph1" src="<%= graphURLIM %>" width="500" height="270" border="0">
    							   <!-- usemap="#< %= filename %>" -->     
    							</td>
	 						</tr>
        				</table>
        		
			
              
            </div>	
			
			
			
			
			
            <%		
             }
             if(abc.is7())
             {
             ////////////////////company wise weightage/////////////////////////////////////////////////////////
             %> 
            <div id="seven" style="display:none">
             
            <%
            			brf.getCompanyWeightage();
            			session.setAttribute("ci2Cwv",brf.getCompanyWeightageVector());
            			String var2=brf.get37();
            			String from2=brf.get27();
            			String iename2=brf.getIndex_name11();
            			session.setAttribute("iename2",iename2);
                       	String excel2 = "./FileDownloadBatchFinal.jsp?&type=2&filename=CompanyWiseWeightage.xls";
                        String xml_path2 = "./FileDownloadXml.jsp?var="+var2+"&type=2&filename=CompanyWiseWeightage.xml";
	    				String pdf_path2 = "./FileDownloadPdf.jsp?var="+var2+"&type=2&filename=CompanyWiseWeightage.pdf";
	    				String str_url2 = "./EmailReportBatchFinal.jsp?switch_type=2&cas=1&rname=CompanyWiseWeightage.xls";
   				
   				AddFactData  data4=new AddFactData(var2,"2",iename2,from2,null,null);          
				list.add(data4);
   			
   			%>
   			<table cellpadding="0" cellspacing="0" align="center">
   			<tr>
   			<td width="480">
   			</td>
   			<td>
   			
   			<a href="javascript:PrintThisPage2();" ><font size="1"><bean:message key="LatestDivisor.printerf" /></font></a> 
   			<a href="<%=excel2%>" ><font size="1" > <bean:message key="LatestDivisor.downloade" /></font></a>
   			<a href="<%=xml_path2%>" ><font size="1" > <bean:message key="PreIndex.Export_to_Xml" /></font></a>
   			<a href="<%= pdf_path2 %>" > <font size="1" > <bean:message key="PreIndex.Export_to_Pdf" /></font></a>
   			
   			 <a href="<%= str_url2 %>" > <font size="1" > <bean:message key="LatestDivisor.emailr" /></font></a>
   			</td>	
   			</tr>
   			</table>	
             <table width="1000" cellspacing="0" cellpadding="0" >
        		<tr>
		          	<td width="250" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          		<font size="3" face="Verdana">
		          			<b><bean:message key="CompanyWiseWeight.title" /></b>
		          		</font>
		         	</td> 
	          </tr>
		    </table> 
             
            <bean:define id="cw" name="batchReportBeanN" property="companyWeightage"/>
            <logic:empty property="companyWeightage" name="batchReportBeanN">
          
            
          
              <table  border="0" width="600" height="300" cellpadding="0" cellspacing="0" align="center" class="sortable">	
                
                <tr>
                  <td  bgcolor="#cacaca" align="center" valign="middle" nowrap="nowrap">
                    <p style="margin-left: 9">
                      <b><font face="Verdana" color="blue" size="5">
                          <bean:message key="IndexCompareOHCL.ndata" />
                        </font>
                      </b>
                    </p>
                  </td>
                </tr>
              </table>
            </logic:empty>
            <logic:notEmpty property="companyWeightage"  name="batchReportBeanN">
        	
               <table  border="0" width="800"  cellpadding="2" cellspacing="0"  align="center" id="tabular" >
		    		  <tr>
						<td>
							<table border="1" width="80%" align="center" cellpadding="2" cellspacing="0" id="t1" class="sortable" >
								<tr>     
									<td class="gridStyle-header" width="10%" bgcolor="#EFEFEF" align="center">
										<bean:message key="CompanyWiseWeight.compname" />								
									</td>
									<td class="gridStyle-header" width="10%" bgcolor="#EFEFEF" align="center">
										<bean:message key="IndexCompose.mcap" />					
									</td>
									<td class="gridStyle-header" width="10%" bgcolor="#EFEFEF" align="center">
										<bean:message key="IndexCompose.weight" />
									</td>
				    	    	</tr>
								<logic:iterate id="cw" property="companyWeightage"  name="batchReportBeanN">
								   <tr>
				    		   		<td class="gridStyle-odd" width="10%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
				    				   	<font face="Verdana" color="blue" size="2">
							  			 		<bean:write name="cw" property="company_name"/>		 			
										</font>
								  	</td>
	    	   						<td class="gridStyle-odd" width="10%" nowrap="nowrap" align="right" bgcolor="#EFEFEF">
    								   	<font face="Verdana" color="blue" size="2">
					  					 	<bean:write name="cw" property="mcap"/>		
										</font>
							  		</td>
								    <td class="gridStyle-odd" width="10%" nowrap="nowrap" align="right" bgcolor="#EFEFEF">
    								   	<font face="Verdana" color="blue" size="2">
								  			 	<bean:write name="cw" property="weightage"/>		 				  	
										</font>
						 			 </td>
								</tr>
							</logic:iterate>
							</table>
    	 				</td>
					</tr>
				</table>
            </logic:notEmpty>
	           </div>
            <% }  
             if(abc.is8())
             {
             ////////////////////Industry  weightage/////////////////////////////////////////////////////////
             %>
             <div id="eight" style="display:none">
             
             
			<%
			brf.getIndweighttable();
	  		String var3=brf.get38();
	  		String iename3=brf.getIndex_name11();
	  		session.setAttribute("ci2Iww",brf.getViw());
	  		session.setAttribute("iename3",iename3);
	  		String excel_path3 = "./FileDownloadBatchFinal.jsp?var="+var3+"&type=3&filename=IndWiseWeightage.xls";
	  		String str_url3 = "./EmailReportBatchFinal.jsp?switch_type=3&cas=3&rname=IndWiseWeightage.xls&varid="+var3;
	  		String xml3 = "./FileDownloadXml.jsp?var="+var3+"&type=3&filename=IndWiseWeightage.xml";  
   	   		String pdf3 = "./FileDownloadPdf.jsp?var="+var3+"&type=3&filename=IndWiseWeightage.pdf";  
   	   		
   	   		AddFactData  data10=new AddFactData(var3,"3",iename3,null,null,null);          
			list.add(data10);
   	   			
	  		%>
	  		
	  		
           <table width="1000" cellspacing="0" cellpadding="0" >
           
           	<tr>
           	<td width="480">
   			</td>
			 <td nowrap="nowrap" align="right">&nbsp;&nbsp;&nbsp;&nbsp; 
			  
                 <font size="1" face="Verdana" color="blue">
                   
                     <a href="javascript:PrintThisPage3();" ><bean:message key="LatestDivisor.printerf" /></a>
                     &nbsp;&nbsp;
                                     
                     <a href='<%= excel_path3 %>'><bean:message key="LatestDivisor.downloade" /></a>     
					 &nbsp;&nbsp;
					<a href='<%= xml3 %>'><bean:message key="PreIndex.Export_to_Xml" /></a>     
					 &nbsp;&nbsp;
					 
					 <a href='<%= pdf3 %>'><bean:message key="PreIndex.Export_to_Pdf"/></a>     
					 <a href='<%= str_url3 %>'><bean:message key="LatestDivisor.emailr"/></a>    
				</font>
            </td>
			</tr>
        		<tr>
		          	<td width="250" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          		<font size="3" face="Verdana">
		          			<b><bean:message key="IndexWiseWeight.title" /></b>
		          		</font>
		         	</td> 
	          </tr>
			</table>
		   <bean:define id="try3" name="batchReportBeanN" property="indweighttable"/>
           <logic:empty property="indweighttable" name="batchReportBeanN">
              <table border="1" width="631" height="222" cellspacing="0" cellpadding="0">
                <tr>
                  <td  bgcolor="#cacaca" align="center" valign="middle">
                    <p style="margin-left: 9">
                      <font face="Verdana" color="blue" size="5">
                        <bean:message key="IndexCompareOHCL.ndata" />
                      </font>
                    </p>
                  </td>
                </tr>
              </table>
            </logic:empty>
		    <logic:notEmpty property="indweighttable" name="batchReportBeanN">
                <table border="0" width="800"  cellpadding="2" cellspacing="0"  align="center" id="tabular" >
                  <tr>
                    <td>
                      <table class="sorted" ID="sortTable" 
                       border="1" align="center" cellpadding="3" cellspacing="0" bgcolor="#EFEFEF">
                        <thead>
                          <tr>
                            <!-- <table border="1" width="80%" align="center" cellpadding="2" cellspacing="0" id="t1" class="sortable" bordercolor="white">      
                             <tr> -->
                            <th  class="gridStyle-header" id="Name" width="30%"  align="center" >
                              <span><b><bean:message key="IndexWiseWeight.Name" /></b></span>
                            </th>
                            <th width="20%" class="gridStyle-header" id="Market" align="center" >
                              <span><b><bean:message key="IndexWiseWeight.Market" /></b></span> 
                               cap.
                            </th>
                            <th  class="gridStyle-header" id="Weightage" width="20%"  align="center"  nowrap="nowrap">
                              <span><b><bean:message key="IndexWiseWeight.Weightage" />(%) </b></span>
                            </th>
                          </tr>
                        </thead>
                        <tbody>
                          <logic:iterate id="try3" property="indweighttable" name="batchReportBeanN">
                            <tr>
                              <td class="gridStyle-odd" width="30%" align="left" height="37" bgcolor="#EFEFEF" nowrap="nowrap" axis="sstring" headers="Name"
                               title='<bean:write name="try3" property="industryname"/>'>
                                <font face="Verdana" size="2" color="blue">
                                  <bean:write name="try3" property="industryname"/>
                                </font>
                              </td>
                              <td class="gridStyle-odd" width="20%" align="right" bgcolor="#EFEFEF" nowrap="nowrap" axis="number" headers="Market"
                               title='<bean:write name="try3" property="marketcap"/>'>
                                <font face="Verdana" size="2" color="blue">
                                  <bean:write name="try3" property="marketcap"/>
                                </font>
                              </td>
                              <td class="gridStyle-odd" width="20%" align="right" bgcolor="#EFEFEF" nowrap="nowrap" axis="number" headers="Weightage"
                               title='<bean:write name="try3" property="weightage"/>'>
                                <font face="Verdana" size="2" color="blue">
                                  <bean:write name="try3" property="weightage"/>%</font>
                              </td>
                            </tr>
                          </logic:iterate>
                        </tbody>  		
            	        <tr>
                          <td class="gridStyle-header" width="30%"  align="left" height="37" nowrap="nowrap">
                            <font face="Verdana" size="2" color="blue">
                              <bean:message key="StockPerformance.Total" />
                            </font>
                          </td>
                          <td class="gridStyle-header" width="20%" align="right" nowrap="nowrap" >
                            <font face="Verdana" size="2" color="blue">
                              <bean:write name="batchReportBeanN" property="val"/>
                            </font>
                          </td>
                          <td class="gridStyle-header" width="20%" align="right" nowrap="nowrap" >
                            <font face="Verdana" size="2" color="blue">
                              <bean:write name="batchReportBeanN" property="total"/>%</font>
                          </td>
	                    </tr>
                      </table> 
            	  
                    </td>
                  </tr>
                </table>
             
            </logic:notEmpty>
            </div>	
            <%}
            if(abc.is9())
             {
             ////////////////////Stock Contribution to change in Index/////////////////////////////////////////////////////////
             %>
             <div id="nine" style="display:none">
             <%
             	brf.getStockcotriIndexchange();
             	
	  			String var5=brf.get39();
	  			String iename5=brf.getIndex_name11();
	  			String to5 = brf.get29();
	  			String from5 = brf.get19();
	  			session.setAttribute("ci2Scc",brf.getVi());
	  			session.setAttribute("iename5",iename5);
	  			String excel_path5 = "./FileDownloadBatchFinal.jsp?var="+var5+"&type=5&filename=StockContribution.xls&from="
	  				+from5+"&to="+to5;
	  			String xml5 = "./FileDownloadXml.jsp?var="+var5+"&from="+from5+"&to="+to5+"&type=5&filename=StockContribution.xml";  
   	   			String pdf5 = "./FileDownloadPdf.jsp?var="+var5+"&from="+from5+"&to="+to5+"&type=5&filename=StockContribution.pdf";  
   	   			String str_url5 = "./EmailReportBatchFinal.jsp?switch_type=5&cas=5&varid="+var5+"&rname=StockContribution.xls&from="
	  				+from5+"&to="+to5;
	  				
	  			AddFactData  data9=new AddFactData(var5,"5",iename5,from5,to5,null);          
				list.add(data9);
	  	  %>
		
			<p align="center">
       	
        	<table width="1000" cellspacing="0" cellpadding="0" >
        	
        	<tr>
        	<td width="480">
   			</td>
			 <td nowrap="nowrap" align="right">&nbsp;&nbsp;&nbsp;&nbsp; 
			  
                 <font size="1" face="Verdana" color="blue">
                   
                     <a href="javascript:PrintThisPage5();" ><bean:message key="LatestDivisor.printerf" /></a>
                     &nbsp;&nbsp;
                    
                     
                     <a href='<%= excel_path5 %>'><bean:message key="LatestDivisor.downloade" /></a>     
					 &nbsp;&nbsp;
					 <a href='<%= xml5 %>'><bean:message key="PreIndex.Export_to_Xml" /></a>     
					 &nbsp;&nbsp;
					 
					 <a href='<%= pdf5 %>'><bean:message key="PreIndex.Export_to_Pdf"/></a>    
					 <a href='<%= str_url5 %>'><bean:message key="LatestDivisor.emailr"/></a>    
				</font>
            </td>
			</tr>
        		<tr>
		          	<td width="250" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          			<font size="3" face="Verdana">
		          				<b><bean:message key="StockContritoIndexChange.title" /></b>
		          			</font>
		         	</td> 
	            </tr>
			</table> 
         	<bean:define id="try4" name="batchReportBeanN" property="stockcotriIndexchange"/>
       		<logic:empty property="stockcotriIndexchange" name="batchReportBeanN">
	  		  					<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
	  	   	 						<tr>
          							<td width="99"></td>
	  			   					<td class="gridStyle-message" align="center" valign="middle">
          							<p style="margin-left: 9"><bean:message key="IndexCompareOHCL.ndata" /></p>
          							</td>
        						</tr>
       							</table>
       	</logic:empty>
       		
       		<logic:notEmpty property="stockcotriIndexchange"  name="batchReportBeanN">
		
       
       		<table border="1" width="70%" align="center" cellpadding="2" cellspacing="0" id="t1" class="sortable">
          	<tr>
            	<td class="gridStyle-header" width="25%"  align="center" valign="middle" bgcolor="#EFEFEF" nowrap="nowrap"><bean:message key="StockWisePe.Name"/>
            	</td>
            	<td  class="gridStyle-header" width="15%"  align="center" valign="middle" bgcolor="#EFEFEF" nowrap="nowrap"><bean:message key="StockContritoIndexChange.Index"/>
               	</td>
            	<td  class="gridStyle-header" width="15%"  align="center" valign="middle" bgcolor="#EFEFEF" nowrap="nowrap"><bean:message key="StockContritoIndexChange.Stock"/>
             	</td>
            	<td  class="gridStyle-header" width="15%"  align="center" valign="middle" bgcolor="#EFEFEF" nowrap="nowrap"><bean:message key="IndexWiseWeight.Weightage"/>
             	</td>
          	</tr>
        	<logic:iterate id="try4" property="stockcotriIndexchange" name="batchReportBeanN">
        	<tr>
            	<td class="gridStyle-odd" width="25%" align="left" height="37" bgcolor="#EFEFEF" >
             	<font face="Verdana" size="2" color="blue">
              	<bean:write name="try4" property="stockname"/>
               	</font>      
            	</td>
           		<td class="gridStyle-odd" width="15%"  align="right" bgcolor="#EFEFEF" nowrap="nowrap">
              	<font face="Verdana" size="2" color="blue">
              	<bean:write name="try4" property="indexmarket"/>
            	</font>
            	</td>
            	<td class="gridStyle-odd" width="15%"  align="right" bgcolor="#EFEFEF" nowrap="nowrap">
              	<font face="Verdana" size="2" color="blue">
              	<bean:write name="try4" property="stockmarket"/>
            	</font>
            	</td>
            	<td class="gridStyle-odd" width="15%"  align="right" bgcolor="#EFEFEF" nowrap="nowrap">
              	<font face="Verdana" size="2" color="blue">
             	<bean:write name="try4" property="weightage"/> 
            	</font>
            	</td>
         	</tr>
        	</logic:iterate>
      		</table>
       
      	</logic:notEmpty>  
 
		</div>
		<%}
		      if(abc.is100())
             {
             ////////////////////Index Comparision    /////////////////////////////////////////////////////////
             %>
             <div id="ten" style="display:none"> 
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
						String[] indexids =(String[])session.getAttribute("indexids");
						String fdate= session.getAttribute("sfdate").toString();
						String tdate= session.getAttribute("stdate").toString();
						session.setAttribute("indexids",indexids);
									 			session.removeAttribute("sfdate");
									 			session.setAttribute("sfdate",fdate);
									 			session.removeAttribute("stdate");
									 			session.setAttribute("stdate",tdate);
							//session.setAttribute("indexids");
							//session.getAttribute("sfdate",);
							//session.getAttribute("stdate");			     		    
			     	   	   	String filename=null;
   		    		      //  GraphMethodsPf gm=new GraphMethodsPf();
   	    				   GraphMethodsPf gm =ConnectInit.getGraphMethodsPf(); 
   	    				    filename=gm.getGraphChartIndexCompare01(session,new PrintWriter(out));
   	    				    String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
   	    				    //log.info("graph url is :"+graphURL);
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
								    		<img src="<%= graphURL %>" height="270"  width="500" >
									    </td></tr>
								  </table> 
						</logic:notEqual>	
					</div>
	
			<%}
		   if(abc.is103())
             {
             ////////////////////Index Returns and Volatility    /////////////////////////////////////////////////////////
             %>
             <div id="thirteen" style="display:none">
			
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
						
						<table align="right"> 
					 		<td valign="baseline">
					 			<a href="javascript:PrintThisPage14();"> 
					 				<font size="1" face="Verdana" color="blue"><bean:message key="IndexPerformance.printerf"/></font>
					 			</a>
					 		</td>
					 		<!-- Excel Links  -->		
					 		<%
					 		
				   				session.setAttribute("indexList",request.getParameterValues("indexList"));
				   				session.setAttribute("from",request.getParameter("from"));
				   				session.setAttribute("to",request.getParameter("to"));
				   				Vector v14=brf.getVExcel();
				   				session.setAttribute("ci2IRV",v14);
				   				String from = brf.get113();
							    String to = brf.get123();
							    
							    String[] indexList = brf.get73();
						        String email_url14 = "./EmailReportBatchFinal.jsp?switch_type=14&cas=14&from="+from+"&to="+to+"&rname=IndexReturn.xls";
					            String path_pdf14 = "./FileDownloadPdf.jsp?&type=14&from="+from+"&to="+to+"&filename=IndexReturn.pdf";
			               	    String xml14 = "./FileDownloadXml.jsp?type=14&from="+from+"&to="+to+"&filename=IndexReturn.xml";
							    String excel_path14 = "./FileDownloadBatchFinal.jsp?&type=14&from="+from+"&to="+to+"&filename=IndexReturn.xls";
							
							AddFactData  data11=new AddFactData(null,"14",null,from,to,null);          
							list.add(data11);
							
							%>								
				 			<td valign="baseline"><a href="<%= excel_path14 %>" ><font size="1" face="Verdana" color="blue" ><bean:message key="IndexPerformance.downloade" /></font></a></td>
				 			<td valign="baseline" > <a href="<%= email_url14 %>" ><font size="1" face="Verdana" color="blue" ><bean:message key="IndexPerformance.emailr" /></font></a></td>
				 			<td valign="baseline" > <a href="<%= xml14 %>" ><font size="1" face="Verdana" color="blue" ><bean:message key="PreIndex.Export_to_Xml" /></font></a></td>
				 			
				 			<td valign="baseline" > <a href="<%= path_pdf14 %>" ><font size="1" face="Verdana" color="blue" ><bean:message key="LatestDivisor.pdfr" /></font></a></td>	
						</table>
						
						<br><br>
						<bean:define id="details5" name="batchReportBeanN" property="final_Vector"/>
				  		
					 	<logic:empty property="final_Vector" name="batchReportBeanN">
					 	
				  		  					<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
				  	   	 						<tr>
			          							<td width="99"></td>
				  			   					<td class="gridStyle-message" align="center" valign="middle">
			          							<p style="margin-left: 9"><bean:message key="IndexCompareOHCL.ndata" /></p>
			          							</td>
			        						</tr>
			       							</table>
			       		</logic:empty>
						<logic:notEmpty property="final_Vector"  name="batchReportBeanN">
						
			            	
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
				      			<logic:iterate id="details5" name="batchReportBeanN" property="final_Vector">
					     			<tr>				
						      			<td nowrap="nowrap" align="center" class="gridStyle-odd" axis="sstring" headers="indexname"
			           			title='<bean:write name="details5" property="strr2"/>'>
						          			<font face="Verdana" color="black" size="2">  
						          				<bean:write name="details5" property="strr2"/>
						       				</font>
						    			</td>
						          							
						      			<td  nowrap="nowrap" align="center" class="gridStyle-odd" axis="number" headers="Periodic"   
						  		title='<bean:write name="details5" property="strr3"/>'>
								          			<font face="Verdana" color="black" size="2">  
								          				<bean:write name="details5" property="strr3"/>
								          			</font>
								    			</td>
								          							
								    			<td  nowrap="nowrap" align="center" class="gridStyle-odd" axis="number" headers="Volatility"   
						  		title='<bean:write name="details5" property="strr4"/>'>
								      				<font face="Verdana" color="black" size="2">  
								          				<bean:write name="details5" property="strr4"/>  
								          			</font>
								    			</td>
								          			
											</tr>
						   				</logic:iterate>
						   				</tbody>
						   			</table>
						   			
					         </logic:notEmpty>    	  						 	
					</div>
		<%}
		
		if(abc.is102())
             {
             ////////////////////Index Correlation   /////////////////////////////////////////////////////////
             %>
				<div id="twelve" style="display:none"> 
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
       		String fromdate1 = brf.get112();//(String)vec1.get(0);
         	String toDate1 =brf.get122();//(String)vec1.get(1);
        	String[] var1=brf.get74();//{(String)vec1.get(2),(String)vec1.get(3),(String)vec1.get(4),(String)vec1.get(5)};
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
	 
	 	<table width="656">
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
		<% 		if(var1 != null)
				{
					//if(!pr.equals("Y")){
					try{
						String astr15 = null;
                           	astr15 = "./IndexCorrelation.jsp?Pr=Y"+url+"&from="+fromdate1+"&to="+toDate1; 
                        	String excel15 = "./FileDownloadBatchFinal.jsp?type=15&from="+fromdate1+"&to="+toDate1+"&filename=IndexCorrelation.xls";
							String str_url15 = "./EmailReportBatchFinal.jsp?switch_type=15&cas=15&rname=IndexCorrelation.xls&from="+fromdate1+"&to="+toDate1;
                       		String xml15 = "./FileDownloadXml.jsp?type=15"+url+"&from="+fromdate1+"&to="+toDate1+"&filename=IndexCorrelation.xml";
							
                       		String pdf15 = "./FileDownloadPdf.jsp?&from="+fromdate1+"&to="+toDate1+"&type=15&filename=IndexCorrelation.pdf";  
   	   			
   	   						AddFactData  data12=new AddFactData(null,"15",null,fromdate1,toDate1,null);          
							list.add(data12);
   	   			
                        %>
                        <table width="800" cellpadding="0" cellspacing="0">
                        <tr>
                        <td width="285" nowrap="nowrap" align="right">&nbsp;
                        </td>
                        <td width="105" nowrap="nowrap" align="left">
                        	<font size="1">
                        		<a href="javascript:popprinter15('<%=astr15%>');"><bean:message key="IndexPerformance.printerf" /></a>
                        	</font>
                        </td>
                        <td width="110" nowrap="nowrap">
                           	<font size="1">
                           		<a href=<%= excel15 %>><bean:message key="IndexPerformance.downloade" /></a>
							</font>
						</td>
						<td width="105" nowrap="nowrap">
                           	<font size="1">	
								<a href= <%= str_url15 %>><bean:message key="IndexPerformance.emailr" /></a>
							</font>
						</td>
						<td width="105" nowrap="nowrap">
                           	<font size="1">	
								<a href= <%= xml15 %>><bean:message key="PreIndex.Export_to_Xml" /></a>
							</font>
						</td>
						<td width="189" nowrap="nowrap">
                           	<font size="1">	
								<a href= <%= pdf15 %>><bean:message key="PreIndex.Export_to_Pdf" /></a>
							</font>
						</td>
						</tr>
					</table>
					<br/>
          <%			}catch(Exception e){log.info("refresh the page.");}
          			//}
          		}		
          %>
		
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
    <%}
		    if(abc.is101())
             {
             ////////////////////Index Compare OHLC      /////////////////////////////////////////////////////////
             %>
             <div id="eleven" style="display:none">
		
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
	 		String fromdate =brf.get111();
        	String toDate =brf.get121();//(String)vect.get(1);

	 	//	org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
		ComposeIndex ci = ConnectInit.getComposeIndex();
			String[] var=brf.get72();//{(String)vect.get(2),(String)vect.get(3)};
			
			
			String url1 = "";
			if(var != null)
			{
				for(int i=0;i < var.length;i++){
						
						url1 = url1 + "&D1=" + var[i];
				}
				
			}
	
			
    //    org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
      AdjustDecimal ad = ConnectInit.getAdjustDecimal();
        org.jfree.chart.demo.servlet.IndexCompareOHLC comp=new org.jfree.chart.demo.servlet.IndexCompareOHLC();
        org.jfree.chart.demo.servlet.FieldSort sort=new org.jfree.chart.demo.servlet.FieldSort();
       
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
             <bean:message key="IndexCompareOHCL.messaged" /></font></b></p>
           </td>
          </tr>
      </table>
      <% }else {  						
       				 	comp.setVector_compareOHLC1(var,fromdate,toDate);
						Vector v3=comp.getVector_compareOHLC();	
						
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
	 <p >
	 	<% 		if(var != null)
				{
					try{
							String astr = null;
                          	astr = "../IndexCompareOHLC.jsp?Pr=Y"+url1+"&from="+fromdate+"&to="+toDate; 
                        	String excel10 = "./FileDownloadBatchFinal.jsp?type=10&from="+fromdate+"&to="+toDate+"&filename=IndexCompareOHLC.xls";
							String str_url10 = "./EmailReportBatchFinal.jsp?switch_type=10&cas=10&rname=IndexCompareOHLC.xls&from="+fromdate+"&to="+toDate;
                        	String pdf10 = "./FileDownloadPdf.jsp?from="+fromdate+"&to="+toDate+"&type=10&filename=IndexCompareOHLC.pdf";  
   	   						String xml10 = "./FileDownloadXml.jsp?from="+fromdate+"&to="+toDate+"&type=10&filename=IndexCompareOHLC.xml";  
   	   			
   	   					AddFactData  data13=new AddFactData(null,"10",null,fromdate,toDate,null);          
						list.add(data13);
	   
   	   			
                        %> 
                        <p></p>
                        <table width="900" cellpadding="0" cellspacing="0">
                        <tr>
                        
                        <td width="350" nowrap="nowrap" align="right">
                        <font size="1">
                        	<a href="javascript:popprinter10('<%=astr%>');"><bean:message key="LatestDivisor.printerf" /></a>
                        	
                        </td>
                        <td width="110" nowrap="nowrap">	
							
							 <font size="1">
							 <a href=<%= excel10 %>>
							<bean:message key="LatestDivisor.downloade" /></a>
							</font>
							</td>
						 <td width="110" nowrap="nowrap">	
							 <font size="1">
							<a href= <%= str_url10 %>>
							<bean:message key="LatestDivisor.emailr" /></a>
							</font>
						</td>
						<td width="100" nowrap="nowrap">	
							 <font size="1">
							<a href= <%= xml10 %>>
							<bean:message key="PreIndex.Export_to_Xml" /></a>
							</font>
						</td>		
						<td width="267" nowrap="nowrap">	
							 <font size="1">
							<a href= <%= pdf10 %>>
							<bean:message key="PreIndex.Export_to_Pdf" /></a>
							</font>
						</td>	
						</tr>
						
					</table>
					<p></p>
							
          <%			}catch(Exception e){log.info("refress the page.");}
          		}		
          %>
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
  	</tbody>
	</table>
    </div> 

		<%}
		   
		   if(abc.is5())
             {
             //////////////////// Stock Details  /////////////////////////////
             %>
           <div id="five" style="display:none">
           
           <%
		
			brf.getTableDatas();
			String stk_name6= (String)brf.getStock_name();//stock name
			
	  		String[] var6= brf.get45();	//stockid
	  		String to6 = brf.get25();
	  		String from6 = brf.get15();
	  		String 	IndexName6 = brf.getIndex_name11();// index name
	  		session=request.getSession();
			session.setAttribute("ci2SDetails",brf.getVecStockDetails());
			session.setAttribute("iename6",IndexName6);
	  		String excel_path6 = "./FileDownloadBatchFinal.jsp?var="+var6[0]+"&type=6&filename=StockHighLow.xls&from="
	  				+from6+"&to="+to6+"&indName="+IndexName6+"&stkName="+stk_name6;
	  				log.info("8");
	  		String xml6 = "./FileDownloadXml.jsp?var="+var6[0]+"&from="+from6+"&to="+to6+"&type=6&filename=StockDetails.xml";  
   	   		String pdf6 = "./FileDownloadPdf.jsp?var="+var6[0]+"&from="+from6+"&to="+to6+"&type=6&filename=StockDetails.pdf";  
   	 		String str_url6 = "./EmailReportBatchFinal.jsp?switch_type=6&cas=6&rname=StockHighLow.xls&from="
	  				+from6+"&to="+to6+"&varid="+IndexName6;	
	  				
	  		AddFactData  data5=new AddFactData(var6[0],"6",IndexName6,from6,to6,null);          
			list.add(data5);	
			
	  		%>

           <table  align="right">
               	<td><font size="1" face="Verdana" color="blue"><a href="javascript:PrintThisPage6();"><bean:message key="IndexPerformance.printerf" /></a></font></td>
	            <td><font size="1" face="Verdana" color="blue"><a href="<%=excel_path6%>"> <bean:message key="IndexPerformance.downloade" /></a> </font></td>
	            <td><font size="1" face="Verdana" color="blue"><a href="<%=xml6%>"> <bean:message key="PreIndex.Export_to_Xml" /></a> </font></td>
				<td><font size="1" face="Verdana" color="blue"><a href="<%=pdf6%>"> <bean:message key="PreIndex.Export_to_Pdf" /></a> </font></td>
			 
				<td ><font size="1" face="Verdana" color="blue"><a href="<%=str_url6%>" ><bean:message key="IndexPerformance.emailr" /></a></font></td>
		   </table>
		  
	  		<table cellspacing="0" cellpadding="0" >
    				<tr>
							<td width="200" class="subHeader" nowrap="nowrap">&nbsp;</td>
		   					<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		   					<font size="3" face="Verdana">
		        			<b><bean:message key="StockHighLow.title" /></b>
		         			</font>
		   					</td> 
	  				</tr>
			</table> 
			
	 	 	<bean:define id="details3" name="batchReportBeanN" property="tableDatas"/>
	  		<bean:size id="dataCount3" name="batchReportBeanN" property="tableDatas"/>		
	  		<logic:equal value="0" name="dataCount3" >
	  		  				<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
	  	   	 					<tr>
          							<td width="99"></td>
	  			   					<td class="gridStyle-message" align="center" valign="middle">
          							<p style="margin-left: 9"><bean:message key="IndexCompareOHCL.ndata" /></p>
          							</td>
        						</tr>
       						</table>
       		</logic:equal>
           	<logic:notEqual value="0" name="dataCount3">
          					
				<table class="sorted" ID="sortTable" 
					border="1" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
				<thead>
				<tr>
				
          			<th nowrap="nowrap" align="center" id="stockName" class="gridStyle-header">
          			<span><b><bean:message key="stockmaster.stockName" /></b></span></th>
          			<th nowrap="nowrap" align="center" id="ovalue" class="gridStyle-header">
          			<span><b><bean:message key="StockHighLow.ovalue" /></b></span></th>
		          	<th nowrap="nowrap" align="center" id="cloval" class="gridStyle-header">
		          	<span><b><bean:message key="StockHighLow.cloval" /></b></span></th>
        		  	<th nowrap="nowrap" align="center" id="lowval" class="gridStyle-header">
        		  	<span><b><bean:message key="StockHighLow.lowval" /></b></span></th>
          			<th nowrap="nowrap" align="center" id="hival" class="gridStyle-header">
          			<span><b><bean:message key="StockHighLow.hival" /></b></span></th>
          			<th nowrap="nowrap" align="center" id="trdvolume" class="gridStyle-header">
          			<span><b><bean:message key="StockHighLow.trdvolume" /></b></span></th>
      		    	<th nowrap="nowrap" align="center" id="trdvalue" class="gridStyle-header">
      		    	<span><b><bean:message key="StockHighLow.trdvalue" /></b></span></th>
          			<th nowrap="nowrap" align="center" id="mcp" class="gridStyle-header">
          			<span><b><bean:message key="IndexDivisor.mcp" /></b></span></th>
          			<th nowrap="nowrap" align="center" id="numotrd" class="gridStyle-header">
          			<span><b><bean:message key="StockHighLow.numotrd" /></b></span></th>
          			<th nowrap="nowrap" align="center" id="Date" class="gridStyle-header">
          			<span><b><bean:message key="corporate.Date" /></b></span></th>
     			</tr> 
     			</thead>
     			
				<tbody>
     			<logic:iterate id="details3" name="batchReportBeanN" property="tableDatas">
     			<tr>
     				<td valign="middle" bgcolor="white" align="left" class="gridStyle-odd" axis="sstring" headers="stockName"
           			title='<bean:write name="details3" property="stockName"/>'>
        				<font face="Verdana" size="2" color="blue">
        				<bean:write name="details3" property="stockName"/></font>
          			</td>
          			<td align="right" bgcolor="white" valign="middle" class="gridStyle-odd" axis="number" headers="ovalue"   
	  		title='<bean:write name="details3" property="openVal"/>'>
          				<font face="Verdana" size="2" color="blue">
          				<bean:write name="details3" property="openVal"/></font>
          			</td>
          			<td bgcolor="white" align="right" valign="middle" class="gridStyle-odd" axis="number" headers="cloval"   
	  		title='<bean:write name="details3" property="closeVal"/>'>
          				<font face="Verdana" size="2" color="blue">
          				<bean:write name="details3" property="closeVal"/></font>
          			</td>
          			<td bgcolor="white" align="right" valign="middle" class="gridStyle-odd" axis="number" headers="lowval"   
	  		title='<bean:write name="details3" property="lowVal"/>'>
          				<font face="Verdana" size="2" color="blue">
          				<bean:write name="details3" property="lowVal"/></font>
         		 	</td>
       			   	<td bgcolor="white" align="right" valign="middle" class="gridStyle-odd" axis="number" headers="hival"   
	  		title='<bean:write name="details3" property="highVal"/>'>
       			   		<font face="Verdana" size="2" color="blue">
       			   		<bean:write name="details3" property="highVal"/></font>
     		     	</td>
     		     	<td bgcolor="white" align="right" valign="middle" class="gridStyle-odd" axis="number" headers="trdvolume"   
	  		title='<bean:write name="details3" property="tradedVol"/>'>
     		     		<font face="Verdana" size="2" color="blue">
     		     		<bean:write name="details3" property="tradedVol"/></font>
       			   	</td>
         		 	<td bgcolor="white" align="right" valign="middle" class="gridStyle-odd" axis="number" headers="trdvalue"   
	  		title='<bean:write name="details3" property="tradedVal"/>'>
         		 		<font face="Verdana" size="2" color="blue">
         		 		<bean:write name="details3" property="tradedVal"/></font>
         		 	</td>
        		  	<td bgcolor="white" align="right" valign="middle" class="gridStyle-odd" axis="number" headers="mcp"   
	  		title='<bean:write name="details3" property="mcv"/>'>
        		  		<font face="Verdana" size="2" color="blue">
        		  		<bean:write name="details3" property="mcv"/></font>
        		  	</td>
      		    	<td bgcolor="white" align="right" valign="middle" class="gridStyle-odd" axis="number" headers="numotrd"   
	  		title='<bean:write name="details3" property="noOfTrades"/>'>
      		    		<font face="Verdana" size="2" color="blue">
      		    		<bean:write name="details3" property="noOfTrades"/></font>
      		    	</td>
      		    	<td bgcolor="white" align="center" valign="middle" nowrap="nowrap" class="gridStyle-odd" axis="date" headers="Date"   
	  		title='<bean:write name="details3" property="date"/>'>
      		    		<font face="Verdana" size="2" color="blue">
      		    		<bean:write name="details3" property="date"/></font>
       			   	</td>
    			</tr>
				</logic:iterate> 
				</tbody>
			</table>
			
			</logic:notEqual>
			</div>
			
           
   		<%	
   			}
		   if(abc.is6())
             {
             ////////////////////Capital Change to Universe      /////////////////////
             %>
            <div id="six" style="display:none">
      		
      		<%
      		
           	brf.getTableDatac();
	  		//String stk_name16= request.getParameter("stockName");
	  		String stk_name16="A.C.C.";
	  		String var16=brf.get56();
	  		String iename16 =brf.getExchange_name();
	  		String to16 = brf.get26();
	  		String from16 = brf.get16();
	  		request.getSession().setAttribute("ci2Cap",brf.getCapitalChangeVec());
	  		session.setAttribute("iename16",iename16);
	  		String excel_path16 = "./FileDownloadBatchFinal.jsp?var="+var16+"&type=16&filename=CapitalChangeToUniverse.xls&from="
	  				+from16+"&to="+to16+"&stkName="+stk_name16;
	  		String str_url16 = "./EmailReportBatchFinal.jsp?switch_type=16&cas=16&rname=CapitalChangeToUniverse.xls&from="
	  				+from16+"&to="+to16+"&varid="+var16;
	  		String xml_path16 = "./FileDownloadXml.jsp?var="+var16+"&type=16&from="+from16+"&to="+to16+"&filename=CapitalChangeToUniverse.xml";
	    	String pdf_path16 = "./FileDownloadPdf.jsp?var="+var16+"&type=16&from="+from16+"&to="+to16+"&filename=CapitalChangeToUniverse.pdf";
	    	
	    	AddFactData  data7=new AddFactData(var16,"16",iename16,from16,to16,null);          
			list.add(data7);
	    			
	  		%>
	  		
	  		<table align="right">
	  		<tr>
			 <td colspan="2" nowrap="nowrap" align="left">&nbsp;&nbsp;&nbsp;&nbsp; 
                 <font size="1" face="Verdana" color="blue">
                     <!-- Printer friendly -->
                     <a href="javascript:PrintThisPage16();" ><bean:message key="LatestDivisor.printerf" /></a>
                     &nbsp;&nbsp;
                     <!-- DownLoad Excel -->
                     <a href='<%= excel_path16 %>'><bean:message key="LatestDivisor.downloade" /></a>
					 &nbsp;&nbsp;
					 <!-- DownLoad Xml -->
					 <a href='<%= xml_path16 %>'><bean:message key="PreIndex.Export_to_Xml" /></a>
					 &nbsp;&nbsp;
					 <!-- DownLoad Pdf -->
					 <a href='<%= pdf_path16 %>'><bean:message key="PreIndex.Export_to_Pdf" /></a>
					 &nbsp;&nbsp;
					 <!-- Email Report -->
					 <a href='<%= str_url16 %>' ><bean:message key="LatestDivisor.emailr" /></a>
				</font>
            </td>
		   </tr>
	   	 </table>   
        	
	 		<table width="600" cellspacing="0" cellpadding="0" >
        	
        						
					<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
					<font size="3" face="Verdana">
		    		<b><bean:message key="Capitalctuniverse.title" /></b>
					</font>
					</td> 
			
			</table> 
						<bean:define id="details4" name="batchReportBeanN" property="tableDatac"/>
	  					<bean:size id="dataCount4" name="batchReportBeanN" property="tableDatac"/>	
						<logic:equal value="0" name="dataCount4" >
	  		  					<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
	  	   	 						<tr>
          							<td width="99"></td>
	  			   					<td class="gridStyle-message" align="center" valign="middle">
          							<p style="margin-left: 9"><bean:message key="IndexCompareOHCL.ndata" /></p>
          							</td>
        						</tr>
       							</table>
       			        </logic:equal>
						<logic:notEqual value="0" name="dataCount4">
        					<table class="sorted" ID="sortTable" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
							<thead>
								<tr>
    							<!-- <table border="1" width="100%" align="center" cellpadding="3" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
        						<tr> -->
        		
        						<!-- Table Heading -->
          						<th nowrap="nowrap" align="center"  id="stockName" class="gridStyle-header">
          						<span><b><bean:message key="stockmaster.stockName" /></b></span></th>
          						<th align="center" nowrap="nowrap" id="faceValue" class="gridStyle-header">
          						<span><b><bean:message key="stockmaster.faceValue" /></b></span></th>
		        				<th align="center" nowrap="nowrap" id="totishares" class="gridStyle-header">
		        				<span><b><bean:message key="Capitalctuniverse.totishares" /></b></span></th>
        						<th nowrap="nowrap" align="center" id="mcapvalue" class="gridStyle-header">
        						<span><b><bean:message key="Capitalctuniverse.mcapvalue" /></b></span></th>
          						<th align="center" nowrap="nowrap" id="invwf" class="gridStyle-header">
          						<span><b><bean:message key="Capitalctuniverse.invwf" /></b></span></th>
          						<th nowrap="nowrap" align="center" id="Action" class="gridStyle-header">
          						<span><b><bean:message key="corporate.Action" /></b></span></th>
      		    				<th nowrap="nowrap" align="center" id="Applied" class="gridStyle-header">
      		    				<span><b><bean:message key="corporate.Applied" /></b></span></th>
          						</tr>   		
          					</thead>
							<tbody>
          						<!-- Iterate over the table data -->
          						<logic:iterate id="details4" name="batchReportBeanN" property="tableDatac">
          							<tr>
     									<td valign="middle" bgcolor="white" align="left" class="gridStyle-odd" axis="sstring" headers="stockName"
           									title='<bean:write name="details4" property="stockName"/>'>
        									<font face="Verdana" size="2" color="blue"><bean:write name="details4" property="stockName"/></font>
          								</td>
          								<td align="right" nowrap="nowrap" bgcolor="white" valign="middle" class="gridStyle-odd" axis="number" headers="faceValue"
           									title='<bean:write name="details4" property="faceVal"/>'>
          									<font face="Verdana" size="2" color="blue"><bean:write name="details4" property="faceVal"/></font>
          								</td>
          								<td bgcolor="white" nowrap="nowrap" align="right" valign="middle" class="gridStyle-odd" axis="number" headers="totishares"
           									title='<bean:write name="details4" property="tis"/>'>
          									<font face="Verdana" size="2" color="blue"><bean:write name="details4" property="tis"/></font>
          	
          								</td>
          								<td bgcolor="white" align="right" nowrap="nowrap" valign="middle" class="gridStyle-odd" axis="number" headers="mcapvalue"
           									title='<bean:write name="details4" property="mcv"/>'>
          									<font face="Verdana" size="2" color="blue"><bean:write name="details4" property="mcv"/></font>
         	 							</td>
       		   							<td bgcolor="white" align="right" nowrap="nowrap" valign="middle" class="gridStyle-odd" axis="number" headers="invwf"
           									title='<bean:write name="details4" property="iwf"/>'>
       		 								<font face="Verdana" size="2" color="blue"><bean:write name="details4" property="iwf"/></font>
     		    						</td>
     		   							<td bgcolor="white" align="left" nowrap="nowrap" valign="middle" class="gridStyle-odd" axis="sstring" headers="Action"
           									title='<bean:write name="details4" property="corpAction"/>'>
     		   								<font face="Verdana" size="2" color="blue"><bean:write name="details4" property="corpAction"/></font>
       		   							</td>
         	 							<td bgcolor="white" align="right" nowrap="nowrap" valign="middle" class="gridStyle-odd" axis="date" headers="Applied"
           									title='<bean:write name="details4" property="date"/>'>
         	 								<font face="Verdana" size="2" color="blue"><bean:write name="details4" property="date"/></font>
         								</td> 	
          
    								</tr>
    							</logic:iterate>
             			</tbody>  
        				</table>
   			    	</logic:notEqual>
   			 </div>
      		
			<%}
		   if(abc.is104())
             {
             //////////////////// Traded Volume     /////////////////////////////////////////////////////////
             %>
             <div id="fourteen" style="display:none">
             <%
		brf.getTableDataTr();
		String indExch25= "";
	  	String var25=brf.get62();
	  	String to25 = brf.get124();
	  	String from25 = brf.get114();
	  	String iename25=null;
	  	session=request.getSession();
	  	
		session.setAttribute("ci2Tr",brf.getTrdVolVec());
	  	if(var25.equals("1")){
	  		indExch25 = brf.get154();
	  		brf.setExchange_name(indExch25);
	  		iename25=brf.getExchange_name();
	  		
	  	}
		else{
			indExch25 = brf.get134();
			brf.setIndex_name11(indExch25);
			iename25=brf.getIndex_name11();
			
		}
		session.setAttribute("iename25",iename25);
	  	String excel_path25 = "./FileDownloadBatchFinal.jsp?var="+var25+"&type=25&filename=TradedVolume.xls&from="
	  			+from25+"&to="+to25+"&indExch="+indExch25;
	  	
	    String xml_path25 = "./FileDownloadXml.jsp?var="+var25+"&type=25&filename=TradedVolume.xml&from="
	  				+from25+"&to="+to25+"&indExch="+indExch25;
	    String pdf_path25 = "./FileDownloadPdf.jsp?var="+var25+"&type=25&filename=TradedVolume.pdf&from="
	  				+from25+"&to="+to25+"&indExch="+indExch25;
	    String str_url25 = "./EmailReportBatchFinal.jsp?switch_type=25&cas=25&varid="+var25+"&rname=TradedVolume.xls&from="
	  				+from25+"&to="+to25+"&indExch="+indExch25;	
	  				
	  	AddFactData  data8=new AddFactData(var25,"25",iename25,from25,to25,null);          
		list.add(data8);				
		
	%>
	
	
	
		<table width="900" cellspacing="0" cellpadding="0" >
			<tr>
				<!-- Links  -->
				<td width="300">
   				</td>
	 			<td colspan="2" nowrap="nowrap" align="right">&nbsp;&nbsp;&nbsp; 
	 			<!-- Printer friendly -->
	 			<a href="javascript:PrintThisPage25()" ><font size="1" face="Verdana" color="blue"> <bean:message key="LatestDivisor.printerf" /></font></a>
	 			&nbsp;
	 			<!-- DownLoad Excel -->
	 			<a href='<%= excel_path25 %>'  ><font size="1" face="Verdana" color="blue"> <bean:message key="LatestDivisor.downloade" /></font></a>
	 			&nbsp;&nbsp;
	 			<a href='<%= xml_path25 %>'  ><font size="1" face="Verdana" color="blue"> <bean:message key="PreIndex.Export_to_Xml" /></font></a>
	 			&nbsp;&nbsp;
	 			<!-- Email Report -->
	 			<a href='<%= pdf_path25 %>' ><font size="1" face="Verdana" color="blue"> <bean:message key="PreIndex.Export_to_Pdf" /></font></a>
	 			
	 			<!-- Email Report -->
	 			<a href='<%= str_url25 %>' ><font size="1" face="Verdana" color="blue"> <bean:message key="LatestDivisor.emailr" /></font></a>
	 			</td> 	
	 		</tr>
        	<tr>
		        <td width="250" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          		<font size="3" face="Verdana">
		          			<b><bean:message key="TradeVolumeInd.title" /></b>
		          		</font>
		         </td> 
	         </tr>
		</table> 
		<bean:define id="details1" name="batchReportBeanN" property="tableDataTr"/>
	  	<bean:size id="dataCount1" name="batchReportBeanN" property="tableDataTr"/> 
	  	
		<logic:equal value="0" name="dataCount1" >
	  		  					<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
	  	   	 						<tr>
          							<td width="99"></td>
	  			   					<td class="gridStyle-message" align="center" valign="middle">
          							<p style="margin-left: 9"><bean:message key="IndexCompareOHCL.ndata" /></p>
          							</td>
        						</tr>
       							</table>
       	</logic:equal>
	
		 <logic:notEqual value="0" name="dataCount1">
        
    	<table class="sorted" ID="sortTable" 
			border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
			<thead>
				<tr>
    	
        		
          		<th nowrap="nowrap" align="center"  id="stockName" class="gridStyle-header">
          		<span><b><bean:message key="stockmaster.stockName" /></b></span></th>
          		<th align="center"  id="Traded" class="gridStyle-header">
          		<span><b><bean:message key="TradeVolumeInd.Traded" /></b></span></th>
		   	</tr>   		
          	</thead>
		<tbody>
          
          	<logic:iterate id="details1" name="batchReportBeanN" property="tableDataTr">
          	
          	<tr>
          	  
          	 <td valign="middle" bgcolor="white" align="left"  class="gridStyle-odd" axis="sstring" headers="stockName"
           			title='<bean:write name="details1" property="stockName"/>'>
        			<a href='../masters/stockmaster2.jsp?s_stockid=<bean:write name="details1" property="stockId"/>'>
        			<font face="Arial" size="2" color="blue"><bean:write name="details1" property="stockName"/>
        			</font></a>
        		</td>
          		<td align="right" bgcolor="white" valign="middle" class="gridStyle-odd" axis="number" headers="Traded"
           			title='<bean:write name="details1" property="tradedVol"/>'>
          			<font face="Arial" size="2" color="blue"><bean:write name="details1" property="tradedVol"/></font>
          			
          		</td>
         	</tr>
    		</logic:iterate>
            </tbody> 
        </table> 
        
    	</logic:notEqual>
		</div>	
         
	    <% }
		   if(abc.is105())
             {
             ////////////////////Stock Dividend       //////////
         %>
           <div id="fifteen" style="display:none">
				
		 
		 
	<%     
		brf.getTableDataSD();
		String indExch22= "";
	  	String var22=brf.get61();
	  	String to22 = brf.get125();
	  	String from22 = brf.get115();
	  	String iename22=null;
	  	session=request.getSession();
		session.setAttribute("ci2SD",brf.getStkDividentVec());
	  	if(var22.equals("1")){
	  		indExch22 = brf.get155();
	  		brf.setExchange_name(indExch22);
	  		iename22=brf.getExchange_name();
	  		
	  	} else{
	  		indExch22 = brf.get135();
	  		brf.setIndex_name11(indExch22);
	  		iename22  = brf.getIndex_name11();
	  			  		
	  	}
	  	session.setAttribute("iename22",iename22);
	  	String excel_path22 = "./FileDownloadBatchFinal.jsp?var="+var22+"&type=22&filename=StockDivident.xls&from="
	  			+from22+"&to="+to22+"&indExch="+indExch22;
	  	String str_url22 = "./EmailReportBatchFinal.jsp?switch_type=22&cas=22&varid="+var22+"&rname=StockDivident.xls&from="
	  				+from22+"&to="+to22+"&indExch="+iename22;
	  	String xml_path22 = "./FileDownloadXml.jsp?var="+var22+"&from="+from22+"&to="+to22+"&type=22&filename=StockDivident.xml";
	    String pdf_path22 = "./FileDownloadPdf.jsp?var="+var22+"&from="+from22+"&to="+to22+"&type=22&filename=StockDivident.pdf";
	    
	    AddFactData  data6=new AddFactData(var22,"22",iename22,from22,to22,null);          
		list.add(data6);
						
	%>
	
	
	
		<table width="1000" cellspacing="0" cellpadding="0" >
			<tr>
				<!-- Links  -->
				<td width="300">
   				</td>
	 			<td colspan="2" nowrap="nowrap" align="right">&nbsp;&nbsp;&nbsp;&nbsp; 
	 			<!-- Printer friendly -->
	 			<a href="javascript:PrintThisPage22()" ><font size="1" face="Verdana" color="blue"> <bean:message key="LatestDivisor.printerf" /></font></a>
	 			&nbsp;&nbsp;&nbsp;
	 			<!-- DownLoad Excel -->
	 			<a href='<%= excel_path22 %>'  ><font size="1" face="Verdana" color="blue"> <bean:message key="LatestDivisor.downloade" /></font></a>
	 			&nbsp;&nbsp;&nbsp;
	 			<a href='<%= xml_path22 %>'><font size="1" face="Verdana" color="blue"><bean:message key="PreIndex.Export_to_Xml" /></font></a>
					 &nbsp;&nbsp;
				<a href='<%= pdf_path22 %>'><font size="1" face="Verdana" color="blue"><bean:message key="PreIndex.Export_to_Pdf" /></font></a>
					 &nbsp;&nbsp;
	 			<!-- Email Report -->
	 			<a href='<%= str_url22 %>' ><font size="1" face="Verdana" color="blue"> <bean:message key="LatestDivisor.emailr" /></font></a>
	 			</td> 	
	 		</tr>
        	<tr>
		          	<td width="250" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          		<font size="3" face="Verdana">
		          			<b><bean:message key="StockDivident.title"/></b>
		          		</font>
		         	</td> 
	         </tr>
		</table> 
		 
		
		   
		<bean:define id="details2" name="batchReportBeanN" property="tableDataSD"/>
	  	<bean:size id="dataCount2" name="batchReportBeanN" property="tableDataSD"/>
			
	 	<logic:equal value="0" name="dataCount2" >
	  	<table border="0" align="left" width="631" height="222" cellspacing="0" cellpadding="0">
	  	    <tr>
          		<td width="99"></td>
	  		
          		<td class="gridStyle-message" align="center" valign="middle">
          			<p style="margin-left: 9"><bean:message key="IndexCompareOHCL.ndata" /></p>
          		</td>
        	</tr>
       	</table>
   	    </logic:equal>
   
     <logic:notEqual value="0" name="dataCount2">
        
    	<table border="1" width="60%" align="center" cellpadding="3" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
        	
        	<tr>
        		<td nowrap="nowrap" align="center"  class="gridStyle-header"><bean:message key="stockmaster.stockName" /></td>
          		<td nowrap="nowrap" align="center" class="gridStyle-header"><bean:message key="corporate.Faceval" /></td>
          		<td nowrap="nowrap" align="center" class="gridStyle-header"><bean:message key="StockDivident.Share" /></td>
          		<td nowrap="nowrap" align="center" class="gridStyle-header"><bean:message key="StockDivident.Market" /></td>
          		<td nowrap="nowrap" align="center" class="gridStyle-header"><bean:message key="StockDivident.Dividend" /></td>
          		<td nowrap="nowrap" align="center" class="gridStyle-header"><bean:message key="corporate.Applied" /></td>
		   	</tr>   		
          	<logic:iterate id="details2" name="batchReportBeanN" property="tableDataSD">
          	
          	<tr>
     			<td valign="middle" nowrap="nowrap" bgcolor="white" align="left" class="gridStyle-odd" >
        			<a href='../masters/stockmaster2.jsp?s_stockid=<bean:write name="details2" property="stockId"/>'>
        			<font face="Verdana" size="2" color="blue"><bean:write name="details2" property="stockName"/>
        			</font></a>
        		</td>
          		<td align="right" nowrap="nowrap" bgcolor="white" valign="middle" class="gridStyle-odd">
          			<font face="Verdana" size="2" color="blue"><bean:write name="details2" property="faceVal"/></font>
          		</td>
          		<td align="right" nowrap="nowrap" bgcolor="white" valign="middle" class="gridStyle-odd">
          			<font face="Verdana" size="2" color="blue"><bean:write name="details2" property="tis"/></font>
          		</td>
          		<td align="right" nowrap="nowrap" bgcolor="white" valign="middle" class="gridStyle-odd">
          			<font face="Verdana" size="2" color="blue"><bean:write name="details2" property="mcv"/></font>
          		</td>
          		<td align="right" nowrap="nowrap" bgcolor="white" valign="middle" class="gridStyle-odd">
          			<font face="Verdana" size="2" color="blue"><bean:write name="details2" property="amount"/></font>
          		</td>
          		<td align="right" nowrap="nowrap" bgcolor="white" valign="middle" class="gridStyle-odd">
          			<font face="Verdana" size="2" color="blue"><bean:write name="details2" property="date"/></font>
          		</td>      
          	  </tr>
    		</logic:iterate>
        </table> 
       
    	</logic:notEqual>
   			
   		
		</div>	
		
		   <%}
		   if(abc.is4())
             {
             ////////////////////Index Pe Pb       //////////////////////
             %>
             <div id="four" style="display:none">
  			
  			 <%
	  		brf.getIndex_arraylist();
	    	String var20=brf.get34();
	    	String iename20=brf.getIndex_name11();
	    	String fdate20=brf.get14();
			String tdate20=brf.get24();
	    	session=request.getSession();
	    	session.setAttribute("iename20",iename20);
	    	session.setAttribute("var",var20);
			session.setAttribute("ci2pepb",brf.getVExcel20());
			
			String excel20 = "./FileDownloadBatchFinal.jsp?var="+var20+"&from="+fdate20+"&to="+tdate20+"&type=20&filename=IndexPe_Pb.xls";
       		String str_url20 = "./EmailReportBatchFinal.jsp?switch_type=20&from="+fdate20+"&to="+tdate20+"&cas=20&rname=IndexPe_Pb.xls&varid="+var20;
   	        String xml20 = "./FileDownloadXml.jsp?var="+var20+"&from="+fdate20+"&to="+tdate20+"&type=20&filename=IndexPe_Pb.xml";  
   	   		String pdf20 = "./FileDownloadPdf.jsp?var="+var20+"&from="+fdate20+"&to="+tdate20+"&type=20&filename=IndexPe_Pb.pdf";  
   	 
   	 		AddFactData  data2=new AddFactData(var20,"20",iename20,fdate20,tdate20,null);          
			list.add(data2);
   	    %>
   	    
   	   	   
   	   
		<table width="1000" cellspacing="0" cellpadding="0" >
		<tr>    
			<td colspan="2" nowrap="nowrap" align="right">&nbsp;&nbsp;&nbsp;&nbsp; 
                 <font size="1" face="Verdana" color="blue">
                     <!-- Printer friendly -->
                     <a href="javascript:PrintThisPage20();" ><bean:message key="LatestDivisor.printerf" /></a>
                     &nbsp;&nbsp;
                     <!-- DownLoad Excel -->
                     <a href='<%= excel20 %>'><bean:message key="LatestDivisor.downloade" /></a>
					 &nbsp;&nbsp;
					 <!-- DownLoad Xml -->
                     <a href='<%= xml20 %>' ><bean:message key="PreIndex.Export_to_Xml" /> </a>
                     <a href='<%= pdf20 %>'><bean:message key="PreIndex.Export_to_Pdf" /></a>
					 &nbsp;&nbsp;
					 <a href='<%= str_url20 %>' ><bean:message key="LatestDivisor.emailr" /></a>
				  </font>
            </td> 
         </tr>
   	   	 <tr> 
   	   
		     <td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          			<font size="3" face="Verdana">
		          				<b><bean:message key="Indexpe.title" /> </b>
		          			</font>
		     </td>
		 </tr> 
		 </table> 
				
				
              
              <logic:empty property="index_arraylist" name="batchReportBeanN">
                <br><br>
                <table  border="0" width="550" height="250" cellpadding="0" cellspacing="0" align="center" class="sortable">	
                  <tr>
                  </tr>
                  <tr>
                    <td  bgcolor="#cacaca" align="center" valign="middle" nowrap="nowrap">
                      <p style="margin-left: 9">
                        <b><font face="Verdana" color="blue" size="4">
                            <bean:message key="IndexCompareOHCL.ndata" />
                          </font></b>
                    </td>
                  </tr>
                </table>
              </logic:empty>
   	    
              <logic:notEmpty property="index_arraylist"  name="batchReportBeanN"> 
	         
                <table border="0" width="800"  cellpadding="2" cellspacing="0" align="left" id="tabular" >
                  <tr>
                    <td>
                      <table class="sorted" ID="sortTable" 
                       border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
                        <thead>
                          <tr>
                              
                            <th bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Trading" class="gridStyle-header">
                              <span><b><bean:message key="Indexpe.Trading" /></b></span>
                            </th>
                            <th  bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Close" class="gridStyle-header">
                              <span><b><bean:message key="Indexpe.Close" /></b></span>
                            </th>
                            <th  bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Change" class="gridStyle-header">
                              <span><b><bean:message key="DisplayIndexes1.Change" /></b></span>
                            </th>
                            <th  bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Mar" class="gridStyle-header">
                              <span><b><bean:message key="StockPerformance.Mar" /></b></span>
                            </th>
                            <th  bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Traded" class="gridStyle-header">
                              <span><b><bean:message key="Indexpe.Traded" /></b></span>
                            </th>
                            <th  bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Turnover" class="gridStyle-header">
                              <span><b><bean:message key="Indexpe.Turnover" /></b></span>
                            </th>
                            <th  bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Ratio" class="gridStyle-header">
                              <span><b><bean:message key="Indexpe.Ratio" /></b></span>
                            </th>
                            <th bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Ratiop" class="gridStyle-header">
                              <span><b><bean:message key="Indexpe.Ratiop" /></b></span>
                            </th>
                            <th bgcolor="#EFEFEF" align="center" nowrap="nowrap" id="Yield" class="gridStyle-header">
                              <span><b><bean:message key="Indexpe.Yield" /></b></span>
                            </th>
                          </tr>
                        </thead>
                        <tbody>
                          <logic:iterate id="pepb" name="batchReportBeanN" property="index_arraylist">
                            <tr>
                              <td width="10%" nowrap="nowrap" align="left" class="gridStyle-odd" axis="date" headers="Trading"
                               title='<bean:write name="pepb" property="training_date"/>'>
                                <font face="Verdana" color="blue" size="2">
                                  <bean:write name="pepb" property="training_date"/>		 	
                                </font>
                              </td>
                              <td width="10%" nowrap="nowrap" align="right" class="gridStyle-odd" axis="number" headers="Close"   
                               title='<bean:write name="pepb" property="close"/>'>
                                <font face="Verdana" color="blue" size="2">
                                  <bean:write name="pepb" property="close"/>
                                </font>
                              </td>
                              <td width="10%" nowrap="nowrap" align="right" class="gridStyle-odd" axis="number" headers="Change"   
                               title='<bean:write name="pepb" property="change"/>'>
                                <font face="Verdana" color="blue" size="2">
                                  <bean:write name="pepb" property="change"/>
                                </font>
                              </td>
                              <td width="10%" nowrap="nowrap" align="right" class="gridStyle-odd" axis="number" headers="Mar"   
                               title='<bean:write name="pepb" property="mcap"/>'>
                                <font face="Verdana" color="blue" size="2">
                                  <bean:write name="pepb" property="mcap"/>
                                </font>
                              </td>
                              <td width="10%" nowrap="nowrap" align="right" class="gridStyle-odd" axis="number" headers="Traded"   
                               title='<bean:write name="pepb" property="shares_traded"/>'>
                                <font face="Verdana" color="blue" size="2">
                                  <bean:write name="pepb" property="shares_traded"/>
                                </font>
                              </td>
                              <td width="10%" nowrap="nowrap" align="right" class="gridStyle-odd" axis="number" headers="Turnover"   
                               title='<bean:write name="pepb" property="turnover"/>'>
                                <font face="Verdana" color="blue" size="2">
                                  <bean:write name="pepb" property="turnover"/>
                                </font>
                              </td>
                              <td width="10%" nowrap="nowrap" align="right" class="gridStyle-odd" axis="number" headers="Ratio"   
                               title='<bean:write name="pepb" property="peratio"/>'>
                                <font face="Verdana" color="blue" size="2">
                                  <bean:write name="pepb" property="peratio"/>
                                </font>
                              </td>
                              <td width="10%" nowrap="nowrap" align="right" class="gridStyle-odd" axis="number" headers="Ratiop"   
                               title='<bean:write name="pepb" property="pbratio"/>'>
                                <font face="Verdana" color="blue" size="2">
                                  <bean:write name="pepb" property="pbratio"/>
                                </font>
                              </td>
                              <td width="10%" nowrap="nowrap" align="right" class="gridStyle-odd" axis="number" headers="Yield"   
                               title='<bean:write name="pepb" property="dividend"/>'>
                                <font face="Verdana" color="blue" size="2">
                                  <bean:write name="pepb" property="dividend"/>
                                </font>
                              </td>
                            </tr>
		   
                          </logic:iterate>
                        </tbody>
                      </table>
                    </td>
                  </tr>    
                </table>
	
              </logic:notEmpty>
            </div>	
   
        <%}%>
          </TD>
        </TR>
      </TABLE>	
      </ul>
    	
    	
      <%
        
    	session.setAttribute("getdata",list);
       }
       %>
      <html:hidden property="dataCount"></html:hidden>
	  
    </html:form> 
  </body>
  <script  language="javascript">
  
 
  
function initSort() {
     mySorted = new SortedTable();
     mySorted.colorize = function() {
	
     for (var i=0;i<this.elements.length;i++) {
     if (i%2){
     this.changeClass(this.elements[i],'even','odd');
     } else {
     this.changeClass(this.elements[i],'odd','even');
     }
     }
	
     }
     mySorted.onsort = mySorted.colorize;
     mySorted.onmove = mySorted.colorize;
     mySorted.colorize();
     }	
 
     var newwindow;
function one1() 
     {
     changeDisplay("one","inline");
     changeDisplay("two","none");
     changeDisplay("three","none");
     changeDisplay("four","none");
     changeDisplay("five","none");
     changeDisplay("six","none");
     changeDisplay("seven","none");
     changeDisplay("eight","none");
     changeDisplay("nine","none");
     changeDisplay("ten","none");
     changeDisplay("eleven","none");
     changeDisplay("twelve","none");
     changeDisplay("thirteen","none");
     changeDisplay("fourteen","none");
     changeDisplay("fifteen","none");
     changeDisplay("sixteen","none");
	 }
function two1() 
     {
     changeDisplay("one","none");
     changeDisplay("two","inline");
     changeDisplay("three","none");
     changeDisplay("four","none");
     changeDisplay("five","none");
     changeDisplay("six","none");
     changeDisplay("seven","none");
     changeDisplay("eight","none");
     changeDisplay("nine","none");
     changeDisplay("ten","none");
     changeDisplay("eleven","none");
     changeDisplay("twelve","none");
     changeDisplay("thirteen","none");
     changeDisplay("fourteen","none");
     changeDisplay("fifteen","none");
     changeDisplay("sixteen","none");
	 }
function three1() 
     {
     changeDisplay("one","none");
     changeDisplay("two","none");
     changeDisplay("three","inline");
     changeDisplay("four","none");
     changeDisplay("five","none");
     changeDisplay("six","none");
     changeDisplay("seven","none");
     changeDisplay("eight","none");
     changeDisplay("nine","none");
     changeDisplay("ten","none");
     changeDisplay("eleven","none");
     changeDisplay("twelve","none");
     changeDisplay("thirteen","none");
     changeDisplay("fourteen","none");
     changeDisplay("fifteen","none");
     changeDisplay("sixteen","none");
	 }
	 function four1() 
     {
     changeDisplay("one","none");
     changeDisplay("two","none");
     changeDisplay("three","none");
     changeDisplay("four","inline");
     changeDisplay("five","none");
     changeDisplay("six","none");
     changeDisplay("seven","none");
     changeDisplay("eight","none");
     changeDisplay("nine","none");
     changeDisplay("ten","none");
     changeDisplay("eleven","none");
     changeDisplay("twelve","none");
     changeDisplay("thirteen","none");
     changeDisplay("fourteen","none");
     changeDisplay("fifteen","none");
     changeDisplay("sixteen","none");
	 }
	 function five1() 
     {
     changeDisplay("one","none");
     changeDisplay("two","none");
     changeDisplay("three","none");
     changeDisplay("four","none");
     changeDisplay("five","inline");
     changeDisplay("six","none");
     changeDisplay("seven","none");
     changeDisplay("eight","none");
     changeDisplay("nine","none");
     changeDisplay("ten","none");
     changeDisplay("eleven","none");
     changeDisplay("twelve","none");
     changeDisplay("thirteen","none");
     changeDisplay("fourteen","none");
     changeDisplay("fifteen","none");
     changeDisplay("sixteen","none");
     }
	 function six1() 
     {
     changeDisplay("one","none");
     changeDisplay("two","none");
     changeDisplay("three","none");
     changeDisplay("four","none");
     changeDisplay("five","none");
     changeDisplay("six","inline");
     changeDisplay("seven","none");
     changeDisplay("eight","none");
     changeDisplay("nine","none");
     changeDisplay("ten","none");
     changeDisplay("eleven","none");
     changeDisplay("twelve","none");
     changeDisplay("thirteen","none");
     changeDisplay("fourteen","none");
     changeDisplay("fifteen","none");
     changeDisplay("sixteen","none");
     }
function seven1() 
     {
     changeDisplay("one","none");
     changeDisplay("two","none");
     changeDisplay("three","none");
     changeDisplay("four","none");
     changeDisplay("five","none");
     changeDisplay("six","none");
     changeDisplay("seven","inline");
     changeDisplay("eight","none");
     changeDisplay("nine","none");
     changeDisplay("ten","none");
     changeDisplay("eleven","none");
     changeDisplay("twelve","none");
     changeDisplay("thirteen","none");
     changeDisplay("fourteen","none");
     changeDisplay("fifteen","none");
     changeDisplay("sixteen","none");
	 }
function eight1() 
     {
     changeDisplay("one","none");
     changeDisplay("two","none");
     changeDisplay("three","none");
     changeDisplay("four","none");
     changeDisplay("five","none");
     changeDisplay("six","none");
     changeDisplay("seven","none");
     changeDisplay("eight","inline");
     changeDisplay("nine","none");
     changeDisplay("ten","none");
     changeDisplay("eleven","none");
     changeDisplay("twelve","none");
     changeDisplay("thirteen","none");
     changeDisplay("fourteen","none");
     changeDisplay("fifteen","none");
     changeDisplay("sixteen","none");
	 }
function nine1() 
     {
     changeDisplay("one","none");
     changeDisplay("two","none");
     changeDisplay("three","none");
     changeDisplay("four","none");
     changeDisplay("five","none");
     changeDisplay("six","none");
     changeDisplay("seven","none");
     changeDisplay("eight","none");
     changeDisplay("nine","inline");
     changeDisplay("ten","none");
     changeDisplay("eleven","none");
     changeDisplay("twelve","none");
     changeDisplay("thirteen","none");
     changeDisplay("fourteen","none");
     changeDisplay("fifteen","none");
     changeDisplay("sixteen","none");
	 }


 function ten1() 
     {
     
     changeDisplay("one","none");
     changeDisplay("two","none");
     changeDisplay("three","none");
     changeDisplay("four","none");
     changeDisplay("five","none");
     changeDisplay("six","none");
     changeDisplay("seven","none");
     changeDisplay("eight","none");
     changeDisplay("nine","none");
     changeDisplay("ten","inline");
     changeDisplay("eleven","none");
     changeDisplay("twelve","none");
     changeDisplay("thirteen","none");
     changeDisplay("fourteen","none");
     changeDisplay("fifteen","none");
     changeDisplay("sixteen","none");
 
	 }
function eleven1() 
     {
     
     changeDisplay("one","none");
     changeDisplay("two","none");
     changeDisplay("three","none");
     changeDisplay("four","none");
     changeDisplay("five","none");
     changeDisplay("six","none");
     changeDisplay("seven","none");
     changeDisplay("eight","none");
     changeDisplay("nine","none");
     changeDisplay("ten","none");
     changeDisplay("eleven","inline");
     changeDisplay("twelve","none");
     changeDisplay("thirteen","none");
     changeDisplay("fourteen","none");
     changeDisplay("fifteen","none");
     changeDisplay("sixteen","none");
     
}
function twelve1() 
     {
     //alert(document.getElementById('three4').innerHTML);
     changeDisplay("one","none");
     changeDisplay("two","none");
     changeDisplay("three","none");
     changeDisplay("four","none");
     changeDisplay("five","none");
     changeDisplay("six","none");
     changeDisplay("seven","none");
     changeDisplay("eight","none");
     changeDisplay("nine","none");
     changeDisplay("ten","none");
     changeDisplay("eleven","none");
     changeDisplay("twelve","inline");
     changeDisplay("thirteen","none");
     changeDisplay("fourteen","none");
     changeDisplay("fifteen","none");
     changeDisplay("sixteen","none");
     
}
function thirteen1() 
     {
    changeDisplay("one","none");
     changeDisplay("two","none");
     changeDisplay("three","none");
     changeDisplay("four","none");
     changeDisplay("five","none");
     changeDisplay("six","none");
     changeDisplay("seven","none");
     changeDisplay("eight","none");
     changeDisplay("nine","none");
     changeDisplay("ten","none");
     changeDisplay("eleven","none");
     changeDisplay("twelve","none");
     changeDisplay("thirteen","inline");
     changeDisplay("fourteen","none");
     changeDisplay("fifteen","none");
     changeDisplay("sixteen","none");
     
}
function fourteen1() 
     {
     changeDisplay("one","none");
     changeDisplay("two","none");
     changeDisplay("three","none");
     changeDisplay("four","none");
     changeDisplay("five","none");
     changeDisplay("six","none");
     changeDisplay("seven","none");
     changeDisplay("eight","none");
     changeDisplay("nine","none");
     changeDisplay("ten","none");
     changeDisplay("eleven","none");
     changeDisplay("twelve","none");
     changeDisplay("thirteen","none");
     changeDisplay("fourteen","inline");
     changeDisplay("fifteen","none");
     changeDisplay("sixteen","none");
}
function fifteen1() 
     {
     changeDisplay("one","none");
     changeDisplay("two","none");
     changeDisplay("three","none");
     changeDisplay("four","none");
     changeDisplay("five","none");
     changeDisplay("six","none");
     changeDisplay("seven","none");
     changeDisplay("eight","none");
     changeDisplay("nine","none");
     changeDisplay("ten","none");
     changeDisplay("eleven","none");
     changeDisplay("twelve","none");
     changeDisplay("thirteen","none");
     changeDisplay("fourteen","none");
     changeDisplay("fifteen","inline");
     changeDisplay("sixteen","none");
}

function sixteen1() 
     {
     changeDisplay("one","none");
     changeDisplay("two","none");
     changeDisplay("three","none");
     changeDisplay("four","none");
     changeDisplay("five","none");
     changeDisplay("six","none");
     changeDisplay("seven","none");
     changeDisplay("eight","none");
     changeDisplay("nine","none");
     changeDisplay("ten","none");
     changeDisplay("eleven","none");
     changeDisplay("twelve","none");
     changeDisplay("thirteen","none");
     changeDisplay("fourteen","none");
     changeDisplay("fifteen","none");
     changeDisplay("sixteen","inline");
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
     
     function PrintThisPage1() 
	{ 
		
   		var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
       	sOption+="scrollbars=yes,left=50,top=25"; 
		//var iname=document.forms[0].index_name2.value;
   		var sWinHTML = document.getElementById('one').innerHTML; 
   		//var printHead =document.getElementById('PageTitle').innerHTML;
   		//var moreDet = document.getElementById('hiddenTable1').innerHTML;
   		
   		var winprint=window.open("","",sOption); 
       	winprint.document.open(); 
       	winprint.document.write('<html><body>'); 
       	//winprint.document.write(printHead);
       	winprint.document.write('<br>');
       	winprint.document.write('&nbsp;&nbsp;&nbsp;'); 
      	winprint.document.write('&nbsp;&nbsp;&nbsp;'); 
      	winprint.document.write('<br>');
       	//winprint.document.write(moreDet);
       	winprint.document.write('<br>');
       	winprint.document.write(sWinHTML); 
       	if(checkChart=="on"){
              	var chrt=document.getElementById('chart').innerHTML;
               	winprint.document.write(chrt); 
        }
       	winprint.document.write('</body></html>'); 
       	
       	winprint.document.close(); 
       	winprint.focus(); 
	}

    function PrintThisPage19() 
	{ 
	var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
    sOption+="scrollbars=yes,width=850,height=550,left=100,top=25"; 
	var sWinHTML = document.getElementById('two').innerHTML; 
   //	var printHead =document.getElementById('PageTitle').innerHTML;
	//var moreDet = document.getElementById('hiddenTable').innerHTML;
   	var winprint=window.open("","",sOption); 
    winprint.document.open(); 
    winprint.document.write('<html><body>'); 
    //winprint.document.write(printHead);
    winprint.document.write('<br>');
   // winprint.document.write(moreDet); 
 	winprint.document.write('<br>');
    winprint.document.write('<br>');
    winprint.document.write('<br>');
    winprint.document.write('<br>');
    winprint.document.write('<br>');
    winprint.document.write(sWinHTML);         
    winprint.document.write('</body></html>'); 
   	winprint.document.close(); 
    winprint.focus(); 
	} 
	
	function PrintThisPage20() 
	{ 
 		//var ttle =document.getElementById('title').innerHTML;
   		var tble = document.getElementById('four').innerHTML;
   		
   		var winprint=window.open("","",""); 
      	winprint.document.open(); 
        //winprint.document.write(ttle);
      	winprint.document.write('<br>');
      	winprint.document.write('<br>');
	   	winprint.document.write('<br>');
       	winprint.document.write(tble); 
       	winprint.document.close(); 
       	winprint.focus(); 
	}
    
    	
	function PrintThisPage2() 
	{ 
  		//var chk=document.forms[0].check2.value;
  		//var dt=document.forms[0].date.value;
 		//var iname=document.forms[0].indexName.value;
  		//var ttle =document.getElementById('title').innerHTML;
   		var tble = document.getElementById('seven').innerHTML;
   		var winprint=window.open("","",""); 
      	winprint.document.open(); 
       	//winprint.document.write(ttle);
      	winprint.document.write('<br>');
      	winprint.document.write('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'); 
     	winprint.document.write('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'); 
     	winprint.document.write('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'); 
      	//winprint.document.write('<bean:message key="corporate.Date"/>');
      	winprint.document.write(':&nbsp;&nbsp;&nbsp;');       	
       	//winprint.document.write(dt); 
     	winprint.document.write('&nbsp;&nbsp;&nbsp;'); 
      	winprint.document.write('&nbsp;&nbsp;&nbsp;'); 
      	//winprint.document.write('<bean:message key="defineIndex7"/>');
      	winprint.document.write(':&nbsp;&nbsp;&nbsp;'); 
       	//winprint.document.write(iname);
	   	winprint.document.write('<br><br>');
       	winprint.document.write(tble); 
  		
       	winprint.document.close(); 
       	winprint.focus(); 
	}
	
	
function PrintThisPage22() { 
	var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
    sOption+="scrollbars=yes,width=750,height=600,left=100,top=25"; 
	var sWinHTML = document.getElementById('fifteen').innerHTML; 
   //	var printHead =document.getElementById('PageTitle').innerHTML;
   //	var moreDet = document.getElementById('hiddenTable').innerHTML;
   	var winprint=window.open("","",sOption); 
    winprint.document.open(); 
    winprint.document.write('<html><body>'); 
    //winprint.document.write(printHead);
    winprint.document.write('<br>');
    //winprint.document.write(moreDet); 
    winprint.document.write(sWinHTML); 
    //winprint.document.write(moreDet);          
    winprint.document.write('</body></html>'); 
    winprint.document.close(); 
    winprint.focus(); 
}
	

function PrintThisPage6() { 
	var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
    sOption+="scrollbars=yes,width=950,height=1900,left=100,top=25"; 

   	var sWinHTML = document.getElementById('five').innerHTML; 
   	//var printHead =document.getElementById('PageTitle').innerHTML;
   //	var moreDet = document.getElementById('hiddenTable').innerHTML;
   	//var showChart = document.getElementById('chart').innerHTML;
   		
    var winprint=window.open("","",sOption); 
    winprint.document.open(); 
    winprint.document.write('<html><body>'); 
    //winprint.document.write(printHead);
    winprint.document.write('<br>');
    //winprint.document.write(moreDet); 
    winprint.document.write('<br>');
    //winprint.document.write(showChart);
    winprint.document.write(sWinHTML);         
    winprint.document.write('</body></html>'); 
    winprint.document.close(); 
    winprint.focus(); 
}
	
	function PrintThisPage25() { 
	var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
    sOption+="scrollbars=yes,width=750,height=600,left=100,top=25"; 

   	var sWinHTML = document.getElementById('fourteen').innerHTML; 
   	//var printHead =document.getElementById('PageTitle').innerHTML;
   	//var moreDet = document.getElementById('hiddenTable').innerHTML;
   		
    var winprint=window.open("","",sOption); 
    winprint.document.open(); 
    winprint.document.write('<html><body>'); 
    //winprint.document.write(printHead);
    winprint.document.write('<br>');
    //winprint.document.write(moreDet); 
    	
    winprint.document.write(sWinHTML); 
    //winprint.document.write(moreDet);          
    winprint.document.write('</body></html>'); 
    winprint.document.close(); 
    winprint.focus(); 
	}
	
	function PrintThisPage16() 
	{ 
 		var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
       	sOption+="scrollbars=yes,width=750,height=600,left=100,top=25"; 
		var sWinHTML = document.getElementById('six').innerHTML; 
   		//var printHead =document.getElementById('PageTitle').innerHTML;
   		//var moreDet = document.getElementById('hiddenTable').innerHTML;
   		var winprint=window.open("","",sOption); 
       	winprint.document.open(); 
       	winprint.document.write('<html><body>'); 
       	//winprint.document.write(printHead);
       	winprint.document.write('<br>');
       	//winprint.document.write(moreDet); 
       	winprint.document.write('<br>');
       	winprint.document.write(sWinHTML); 
       	//winprint.document.write(moreDet);          
       	winprint.document.write('</body></html>'); 
       	winprint.document.close(); 
       	winprint.focus(); 
	}
	
	function PrintThisPage3() 
	{ 
		//print for industrywise weightage
   		var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
       	sOption+="scrollbars=yes,width=750,height=600,left=100,top=25"; 
		
   		var sWinHTML = document.getElementById('eight').innerHTML; 
   		//var moreDet = document.getElementById('hiddenTable').innerHTML;
   		var winprint=window.open("","",sOption); 
       	winprint.document.open(); 
       	winprint.document.write('<html><body>'); 
       	winprint.document.write('<br>');
       	winprint.document.write('&nbsp;&nbsp;&nbsp;'); 
      	winprint.document.write('&nbsp;&nbsp;&nbsp;'); 
       	//winprint.document.write(moreDet);
       	winprint.document.write('<br><br>');
       	winprint.document.write('<p align="center">');
       	winprint.document.write(sWinHTML); 
        winprint.document.write('</p>');
       	winprint.document.write('</body></html>'); 
       	winprint.document.close(); 
       	winprint.focus(); 
	}
	
	
	function PrintThisPage5() 
	{ 
   		//var showChart = document.getElementById('chart').innerHTML;
   		var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
       	sOption+="scrollbars=yes,width=750,height=600,left=100,top=25"; 
		//var dt=document.forms[0].from.value;
		//var df=document.forms[0].to.value;
		//var iname=document.forms[0].indexName.value;
   		var sWinHTML = document.getElementById('nine').innerHTML; 
   		//var printHead =document.getElementById('PageTitle').innerHTML;
   		//var moreDet = document.getElementById('hiddenTable').innerHTML;
   		var winprint=window.open("","",sOption); 
       	winprint.document.open(); 
       	winprint.document.write('<html><body>'); 
       	//winprint.document.write(printHead);
       	winprint.document.write('<br>');
    	//winprint.document.write(moreDet);
       	winprint.document.write('<br>');
       	//winprint.document.write(showChart);
       	winprint.document.write(sWinHTML); 
        winprint.document.write('</body></html>'); 
       	winprint.document.close(); 
       	winprint.focus(); 
	}
	function popprinter15(url)
	{
		newwindow=window.open(url,'name','height=500,width=900,scrollbars=yes,left=80,top=130,menubar=yes,status=yes');
		if (window.focus) {newwindow.focus()}
	}
	
	function popprinter10(url)
	{
		newwindow=window.open(url,'name','height=500,width=900,scrollbars=yes,left=80,top=130,menubar=yes,status=yes');
		if (window.focus) {newwindow.focus()}
	}
	function downloadExcel(){
	document.forms[0].download.value="on";
	} 
	function PrintThisPage14() 
	{ 
				
		var sOption="toolbar=yes,location=no,directories=yes,menubar=yes,"; 
    	sOption+="scrollbars=yes,width=850,height=550,left=100,top=25"; 

   		var sWinHTML = document.getElementById('thirteen').innerHTML; 
   		//var printHead =document.getElementById('PageTitle').innerHTML;
	   //	var moreDet = document.getElementById('hiddenTable').innerHTML;
   		
    	var winprint=window.open("","",sOption); 
    	winprint.document.open(); 
    	winprint.document.write('<html><body>'); 
    	//winprint.document.write(printHead);
    	winprint.document.write('<br>');
    	//winprint.document.write(moreDet); 
 		winprint.document.write('<br>');
    	winprint.document.write('<br>');
    	winprint.document.write('<br>');
    	winprint.document.write('<br>');
    	winprint.document.write('<br>');
    	
    	winprint.document.write(sWinHTML);         
    
    	winprint.document.write('</body></html>'); 
   		winprint.document.close(); 
    	winprint.focus(); 
	}
    function list() 
	{
	  
	 changeDisplay("compare","none");
	 changeDisplay("movement","none");	
	 changeDisplay("list","inline");	
	}
	
	function compare()
	{
	  changeDisplay("list","none");
	  changeDisplay("compare","inline");
	  changeDisplay("movement","none");	
	  
	}
	function movement() 
	{
	 changeDisplay("list","none");
	 changeDisplay("compare","none");
	 changeDisplay("movement","inline");
	}
	function menuload(){

	top.topFrame.location.reload();	
	top.treeFrame.location.reload();
	
    }
     </script>    
</html:html>