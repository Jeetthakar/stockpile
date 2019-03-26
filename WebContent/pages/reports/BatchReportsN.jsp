<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="harrier.income.com.report.*"%>
<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.text.ParseException" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@ page  import="app.*"%><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
 response.setHeader("Cache-Control","no-cache");
 response.setHeader("Pragma","no-cache");
 response.setHeader("Expires","0");
%>
<jsp:useBean id="batchReportBeanN" scope="session" class="harrier.income.com.report.BatchReportFormN"/>
<html:html>
<html:errors />
  <head>
    <html:base/>
    <title>
    </title>
    <link href="StyleSheet.css" rel="stylesheet" type="text/css">
    <script language="javascript" src="../Script/codethatcalendarstd.js">
    </script>
    <script language="javascript" src="box_ex.js">
    </script>
    <script language="javascript" src="../Script/date_script.js"></script>
    <script language="javascript">
       var c2 = new CodeThatCalendar(caldef2);
    </script>
    <!-- <script type="text/javascript" src="./sorttable.js"></script>
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
     </style> -->
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
  <body>
    <%int dataCount1=0; 
     String sbrd1=null;
     %>
    <html:form action="/batchReportActionN">  
      <link href="./StyleSheet.css" rel="stylesheet" type="text/css">
      
      <table width="100%" cellspacing="0" cellpadding="0" >
        <tr>
		   <td class="subHeader" width="800" align="center" colspan="2" nowrap="nowrap">
            <font size="3" face="Arial">
              <b><bean:message key="BatchReport.title"/></b>
            </font>
          </td> 
        </tr>
      </table>
      <p>
      </p>
      <table width="100%" align="center">
		<tr>
          <td width="800" align="center">
             <font size="2" color="blue" face="Verdana">
              <bean:message key="BatchReport.prefrence" />
            </font>
         
            <html:select property="selectUser"  size="1" styleId="sIE" onchange="go1()">
              <html:optionsCollection property="selectUserCollection" name="batchReportBeanN"/>
            </html:select>
          </td>
        </tr>
         
	  </table>
	  <p>
      </p>	
      <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </p>   
      <bean:size id="dataCount" name="batchReportBeanN" property="tableData"/>
	<logic:notEqual value="0" name="dataCount">
	  	  <table border="1" cellspacing="0" cellpadding="0" id="checkTable"> 
        <tr>&nbsp;<input type="checkbox" name="selectAll" onclick="checkAll()"/>
       
<font face="Arial" color="blue" size="2">&nbsp;&nbsp;Select All Reports</font>
        </tr></logic:notEqual>
        <%int i=0,j=10,k=20,l=30,m=40,n=50,p=60,r=70;
         %>
        <logic:iterate id="BatchReportDetails" name="batchReportBeanN" property="tableData">
          <%
           BatchReportDetails brd1=(BatchReportDetails)BatchReportDetails;
           sbrd1= brd1.getFilePaths().trim();
           //i++;j++;k++;l++;m++;n++;p++;r++;
           if(sbrd1.equals("Index Composition")){i=1;l=i+30;}
           if(sbrd1.equals("Index Divisor")){i=2;j=i+10;k=i+20;l=i+30;}
           if(sbrd1.equals("Index In Different Currency")){i=3;l=i+30;}
           if(sbrd1.equals("Index Wise PE,PB")){i=4;j=i+10;k=i+20;l=i+30;}
           
           if(sbrd1.equals("Stock Details")){i=5;j=i+10;k=i+20;l=i+30;m=i+40;}
           if(sbrd1.equals("Capital Change")){i=6;j=i+10;k=i+20;n=i+50;}
           if(sbrd1.equals("Company Weightage")){i=7;k=i+20;l=i+30;}
           if(sbrd1.equals("Industry Weightage")){i=8;l=i+30;}
           
           if(sbrd1.equals("Stock Contribution To Change In Index")){i=9;j=i+10;k=i+20;l=i+30;}
           if(sbrd1.equals("Index Comparison")){i=100;j=i+10;k=i+20;r=71;}
           if(sbrd1.equals("Index Compare OHLC")){i=101;j=i+10;k=i+20;r=72;}
           if(sbrd1.equals("Index Correlation")){i=102;j=i+10;k=i+20;r=74;}
           if(sbrd1.equals("Index Returns And Volatility")){i=103;j=i+10;k=i+20;r=73;}
           
           if(sbrd1.equals("Traded Volume")){i=104;j=i+10;k=i+20;p=62;l=i+30;n=i+50;}
           if(sbrd1.equals("Stock Dividend")){i=105;j=i+10;k=i+20;p=61;l=i+30;n=i+50;}
           
           if(sbrd1.equals("Index Movement")){i=106;j=i+10;k=i+20;l=i+30;}// for index movement
          %>
          <tr>
            <td  nowrap="nowrap" align="center" >
              <html:checkbox  property='<%= ""+i %>'/>
              &nbsp;
            </td>
						
            <td class="gridStyle-odd" width="20%" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
              <font face="Arial" color="blue" size="2">
                <bean:write name="BatchReportDetails" property="filePaths"/>
              </font>
            </td>
            <%
             if(!((sbrd1.equals("Industry Weightage"))||(sbrd1.equals("Index Composition"))||(sbrd1.equals("Index In Different Currency")))){
            if(!(sbrd1.equals("Company Weightage"))){%>
            <td width="118" nowrap="nowrap">
             <font size="2" color="blue" face="Verdana">
                <bean:message key="corporate.Fdate" />
              </font>
    	      <html:text property='<%= ""+j %>'  size="10"/>
           <input onclick="c2.popup('<%= ""+j %>');"  type="button" value="..."/>
            </td>
			<%}%>
            <td width="118" nowrap="nowrap">
              <font size="2" color="blue" face="Verdana">
                <bean:message key="corporate.Tdate" />
              </font>
    	       
              <html:text property='<%= ""+k %>'  size="10"/>
			<input onclick="c2.popup('<%= ""+k %>');"  type="button" value="..."/>
             
            </td>
            <%}if((sbrd1.equals("Traded Volume"))||(sbrd1.equals("Stock Dividend"))){
            /////////////////////////////////////////////////////////////////////////////////////////
            %>
            <td width="125" nowrap="nowrap" align="left">
				 <font size="2" color="blue" face="Verdana"><bean:message key="TradeVolumeInd.Filter" />: 
				</font>
       
        		<html:select size="1" property='<%= ""+p %>' onchange="document.forms[0].submit();return true" >
        			<html:optionsCollection property="selectIndiCollection" name="batchReportBeanN"/>
        		</html:select>
        	
        	</td>
        	<logic:notEqual value="0" parameter='<%= ""+p %>'>
	<!-- Table to display exch name or index Name -->

			<td width="170" nowrap="nowrap">
				<logic:equal value="1" parameter='<%= ""+p %>'>
					 <font size="2" color="blue" face="Verdana"><bean:message key="TradeVolumeInd.Stock" />: </font>
				</logic:equal>
				<logic:equal value="2" parameter='<%= ""+p %>'>
					 <font size="2" color="blue" face="Verdana"><bean:message key="TradeVolumeInd.Name" /></font>
				</logic:equal>
		
			<logic:equal value="2" parameter='<%= ""+p %>'>

				<html:select property='<%= ""+l %>'  size="1"  styleId="sIE">
                <html:optionsCollection property="selectIndexCollection" name="batchReportBeanN"/>
              </html:select>

			</logic:equal>
			<logic:equal value="1" parameter='<%= ""+p %>'>
 			<html:select property='<%= ""+n %>'  size="1"   styleId="sIE">
                <html:optionsCollection property="selectExchgCollection" name="batchReportBeanN"/>
              </html:select>
			</logic:equal>
		</logic:notEqual>
            <%
            ////////////////////////////////////////////////////////////
            }
             if((sbrd1.equals("Capital Change"))){
             %>
            <td class="gridStyle-odd" width="118" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
              <font size="2" color="blue" face="Verdana">
                <bean:message key="Masters.SelectExch"/>
              </font>&nbsp;
              <html:select property='<%= ""+n %>'  size="1"  onchange="indiChange();" styleId="sIE">
                <html:optionsCollection property="selectExchgCollection" name="batchReportBeanN"/>
              </html:select>
              
            </td>
            <%}
             if(!((sbrd1.equals("Capital Change"))||(sbrd1.equals("Index Comparison"))||(sbrd1.equals("Index Compare OHLC"))||(sbrd1.equals("Index Returns And Volatility"))||(sbrd1.equals("Index Correlation"))||(sbrd1.equals("Stock Dividend"))||(sbrd1.equals("Traded Volume")))){
            %>
            <td class="gridStyle-odd" width="88" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
              <font size="2" color="blue" face="Verdana">
                <bean:message key="Index.select"/>
              </font>&nbsp;
              <html:select property='<%= ""+l %>'  size="1"  onchange="indiChange1();" styleId="sIE">
                <html:optionsCollection property="selectIndexCollection" name="batchReportBeanN"/>
              </html:select>
              
            </td>  
            <%}
            if((sbrd1.equals("Index Comparison"))||(sbrd1.equals("Index Compare OHLC"))||(sbrd1.equals("Index Returns And Volatility"))||(sbrd1.equals("Index Correlation"))){
            %>
            <td class="gridStyle-odd" width="88" nowrap="nowrap" align="left" bgcolor="#EFEFEF">
              <font size="2" color="blue" face="Verdana">
                <bean:message key="Index.select"/>
              </font>&nbsp;
              <html:select property='<%= ""+r %>'  size="3" multiple="true" styleId="sIE">
                <html:optionsCollection property="selectIndexCollection" name="batchReportBeanN"/>
              </html:select>
              
            </td>  <td width="303" align="right" nowrap="nowrap" >
            <%}
             if((sbrd1.equals("Stock Details"))){%>
            <td width="88" nowrap="nowrap" align="right">
              <font size="2" color="blue" face="Verdana">
                <bean:message key="StockHighLow.sstock" />
              </font>
        		
              <html:select property='<%= ""+m %>' size="4" onchange=" return go();" multiple="true"  styleId="Rem" >
                <html:optionsCollection property="stockCollection" name="batchReportBeanN"/>
              </html:select>
            </td>  
            <%}
            
            
             if((sbrd1.equals("Index Divisor"))){%>
             <td width="303" align="right" nowrap="nowrap" >
             <%}
            
            
            %>
           </tr>
        </logic:iterate>
      </table>
      <table align="center">
        <p>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </p>     
        <tr>
	   	  <td align="left">
            
            <html:submit property="executeButton" onclick="check();">
              <bean:message key="Batch.executeButton"/>
            </html:submit>
          </td>
		    
          <td width="80" nowrap="nowrap" align="right"> 		
            <html:submit property="resetButton" >
              <bean:message key="indexUpdate.reset"/>
            </html:submit>
          </td>
        </tr>	
		
      </table> 		
      <br>
      <html:hidden property="compute" value="no">
      </html:hidden>
      <html:hidden property="vanish" value="no">
      </html:hidden>
      <html:hidden property="fin" value="no">
      </html:hidden>
      <html:hidden property="checkradio" value="no">
      </html:hidden>
	</html:form>
  </body>
  <script  language="javascript">
function go() {
	var i = 0;
    var fields = new Array();
    if(document.forms[0].selectIndex.value==0){
 		fields[i++] = "Select Index is required";
	}
    if(document.forms[0].from.value==""){
		fields[i++] = "From Date is required";
	}
	if(document.forms[0].to.value==""){
		fields[i++] = "To Date is required";
	}
	if (fields.length > 0) {
        alert(fields.join('\n'));
        return false;
    }
    else {
        var x=document.forms[0].checkChart.checked;
		if(x==true){
			document.forms[0].checkChart.value="on";
		}else{
			document.forms[0].checkChart.value="yes";
		}
        document.forms[0].compute.value="yes";
		document.forms[0].defaultVal.value="no";
        retrieveURL('/Stockpile/batchReportAction.do.do?ask=COMMAND_NAME_1','batchReportBeanN');
    }
}
function go1()
     {
     document.forms[0].submit();
     }
     function check()
     {
     document.forms[0].compute.value="no";
     }
function done()
     {
     //document.forms[0].vanish.value="yes";
     document.forms[0].fin.value="yes";
     }
     function checkAll()
{
      var rowCount = checkTable.rows.length;
      for (var i=1; i < rowCount; i++)
      {
      	
         checkTable.rows(i).cells(0).childNodes(0).checked=document.forms[0].selectAll.checked;
      }
}

function indiChange(){
	document.forms[0].submit();
	 }
	 function indiChange1(){
	document.forms[0].submit();
	 }
  </script>  
</html:html>
