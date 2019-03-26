<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="app.*"%>
<%@ page  import="app.*"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Locale,java.util.*" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<jsp:useBean id="PatBean" scope="session" class="app.PatForm"/>
<html:html>

<head>
 	<html:base/>
	<title></title>
	<link href="StyleSheet.css" rel="stylesheet" type="text/css">
	<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
	<script language="javascript" src="../Script/date_script.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script>

</head>

<%

	if(request.getParameter("new")!=null && request.getParameter("new").equals("yes")){
		PatBean.reset1();
		log.info("in new"+request.getParameter("new"));
		PatBean.setCheck("no");
	}
%>
<body onload="initialize()">
<html:form action="/Pat">
<html:errors />
   <table width="100%"  border="0" cellpadding="2" cellspacing="3" bgcolor="#D8D8D8">
    	<tr>
    		<td align="center" class="subHeader" nowrap="nowrap"><strong>FINANCIAL CALCULATIONS</strong></td>
  		</tr>
	
	 	<tr>
    		<td align="center">
    			<table width="30%"  border="0" cellspacing="0" cellpadding="2">
      				<tr>
        				<td align="left"><font size="2" face="Arial"><bean:message key="Capitalctuniverse.ssexchange" /></font>
        				</td>
        				<td align="left">
        					<html:select property="selectExchange" size="1" onchange = "test();" styleId="Rem" style="width:200px">
							<html:optionsCollection property="exchangeCollection" name="PatBean"/>
							</html:select>
						</td>
      				</tr>
      				<tr>
        				<td align="left"><font size="2" face="Arial"><bean:message key="StockDetailFromDate.Stock" /></font>
        				</td>
        				<td align="left"> 
            				<html:select name="PatBean" property="selectStock" styleId="Rem" style="width:200px">
							<html:optionsCollection property="stockCollection" name="PatBean"/>
							</html:select>
						</td>
      				</tr>
      				<tr>
        				<td align="left"><font size="2" face="Arial">Computation Indicator</font>
        				</td>
        				<td align="left">
       						<html:select name="PatBean" property="selectfinance" styleId="Rem" style="width:200px">
							<html:optionsCollection property="finance" name="PatBean"/>
							</html:select>
						</td>				
      				</tr>
      				<tr>
	      				<td align = "left"><font size ="2" face = "Arial">As on :</font>
	      				</td>
	      				<td align = "left"> 
	      					<html:text size="10" name="PatBean" property="mvcdate" onblur="checkdate(this)"	></html:text>
	      				</td>
	  				</tr>      
      				<tr align="center">
        				<td colspan="2">
        					<html:submit onclick="return go1();" ><bean:message key="Reports.View" /></html:submit>
        				</td>
      				</tr>
    			</table>
    		</td>
  		</tr>
  	</table>
    		    
   			<!-- table started%%%%%%%%%%%%%%%%%%%%%%%%%%%5 -->
          	<logic:equal  value="yes" parameter="compute" >
          	<%
          	 app.PatForm brf=(app.PatForm)session.getAttribute("PatBean");
     			Vector v11= brf.getTableData();
     			int pid=Integer.parseInt(brf.getSelectfinance());
     			
     			if (v11 == null || (v11.size()==0)) {

			%>

			<table border="0" align="center" class="gridStyle" width="100%" height="222" cellspacing="0" cellpadding="0">
				<tr>
					<td class="gridStyle-message" align="center" valign="middle">
						<p style="margin-left: 9">
							<b>No Data Found for the criteria You Selected</b>
						</p>
					</td>
				</tr>
			</table>

			<%}
         else {
          	Iterator i11=v11.iterator(); %>
          	<div id="contentstart">
			<table class="sorted" ID="sortTabletable" width="100%" border="1" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
				<thead>
					<tr>
			
          		    <th nowrap="nowrap" align="center" id="xDate" class="gridStyle-header">
          			<span><b>Choice</b></span></th>
          			<th nowrap="nowrap" align="center" id="xDate" class="gridStyle-header">
          			<span><b><bean:message key="pat.xdate" /></b></span></th>
          			<th nowrap="nowrap" align="center" id="numOfmon" class="gridStyle-header">
          			<span><b><bean:message key="Pat1.Number" /></b></span></th>
          			
		          	<th nowrap="nowrap" align="center" id="pat" class="gridStyle-header">
		          	<span><b><bean:message key="Pat1.value" /></b></span></th>
        		  	<th nowrap="nowrap" align="center" id="netWrth" class="gridStyle-header">
      		    	<span><b><bean:message key="Pat1.Net" /></b></span></th>
        		  	<th nowrap="nowrap" align="center" id="pdUpCap" class="gridStyle-header">
          			<span><b><bean:message key="Pat1.paidup" /></b></span></th>
        		  	<th nowrap="nowrap" align="center" id="sales" class="gridStyle-header">
          			<span><b><bean:message key="Pat1.Sales" /></b></span></th>
          			<th nowrap="nowrap" align="center" id="totDebt" class="gridStyle-header">
          			<span><b><bean:message key="Pat1.Debt" /></b></span></th>
          			<th nowrap="nowrap" align="center" id="interest" class="gridStyle-header">
          			<span><b><bean:message key="Pat1.Interest" /></b></span></th>
          			<th nowrap="nowrap" align="center" id="tax" class="gridStyle-header">
          			<span><b><bean:message key="Pat1.Tax" /></b></span></th>
        		  	<th nowrap="nowrap" align="center" id="divi" class="gridStyle-header">
        		  	<span><b><bean:message key="StockDivident.Dividend" /></b></span></th>
     			</tr> 
     			</thead>
		<tbody>
     			<%
     				int i=0;
     				int num2222=0;
     				while (i11.hasNext()) {               
     				 
     				 i++;%>
     
     		<%
     		if(num2222%2==0){
     		num2222++;%>
     		<tr bgcolor="#cacaca">
     		
     				 <td  nowrap="nowrap" align="center" bgcolor="#cacaca" class="gridStyle-odd">
     				 <% if(pid ==1){ %>
                  <html:checkbox    property='<%= ""+i %>'/>
                     <% } 
                       if(pid == 2){ %>
                  <html:checkbox   disabled="true"  property='<%= ""+i %>'/>   
                  <% } %>  
              &nbsp;
            </td>
            <%
            		log.info("kapil"+i);
          			String xDate = (String) i11.next();
          			log.info("xDateisisisisiissi" + xDate);
          			if(i==1){
          			String xDate1=xDate;
          			session.setAttribute("xDate1", (Object) xDate1);
					log.info("xDate1" + xDate1);
					}
					if(i==2){
          			String xDate2=xDate;
          			session.setAttribute("xDate2", (Object) xDate2);
					log.info("xDate2" + xDate2);
					}
					if(i==3){
          			String xDate3=xDate;
          			session.setAttribute("xDate3", (Object) xDate3);
					log.info("xDate3" + xDate3);
					}
					if(i==4){
          			String xDate4=xDate;
          			session.setAttribute("xDate4", (Object) xDate4);
					log.info("xDate4" + xDate4);
					}
					if(i==5){
          			String xDate5=xDate;
          			session.setAttribute("xDate5", (Object) xDate5);
					log.info("xDate5" + xDate5);
					}
					if(i==6){
          			String xDate6=xDate;
          			session.setAttribute("xDate6", (Object) xDate6);
					log.info("xDate6" + xDate6);
					}
					if(i==7){
          			String xDate7=xDate;
          			session.setAttribute("xDate7", (Object) xDate7);
					log.info("xDate7" + xDate7);
					}
					if(i==8){
          			String xDate8=xDate;
          			session.setAttribute("xDate8", (Object) xDate8);
					log.info("xDate8" + xDate8);
					}
          			%>
     				 <td width="12%" align="left" bgcolor="#cacaca" class="gridStyle-odd" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= (String)xDate %></p>
            </td><%
          			String num = (String) i11.next();
          			if(i==1){
          			String num1=num;
          			session.setAttribute("num1", (Object) num1);
					log.info("num1" + num1);
					}
					if(i==2){
          			String num2=num;
          			session.setAttribute("num2", (Object) num2);
					log.info("num2" + num2);
					}
					if(i==3){
          			String num3=num;
          			session.setAttribute("num3", (Object) num3);
					log.info("num3" + num3);
					}
					if(i==4){
          			String num4=num;
          			session.setAttribute("num4", (Object) num4);
					log.info("num4" + num4);
					}
					if(i==5){
          			String num5=num;
          			session.setAttribute("num5", (Object) num5);
					log.info("num5" + num5);
					}
					if(i==6){
          			String num6=num;
          			session.setAttribute("num6", (Object) num6);
					log.info("num6" + num6);
					}
					if(i==7){
          			String num7=num;
          			session.setAttribute("num7", (Object) num7);
					log.info("num7" + num7);
					}
					if(i==8){
          			String num8=num;
          			session.setAttribute("num8", (Object) num8);
					log.info("num8" + num8);
					}
          			%>
     				<td width="12%" align="left" bgcolor="#cacaca" class="gridStyle-odd" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= (String)num %></p>
            </td><%
          			String pat = (String) i11.next();
          			if(i==1){
          			String pat1=pat;
          			session.setAttribute("pat1", (Object) pat1);
					log.info("pat1" + pat1);
					}
					if(i==2){
          			String pat2=pat;
          			session.setAttribute("pat2", (Object) pat2);
					log.info("pat2" + pat2);
					}
					if(i==3){
          			String pat3=pat;
          			session.setAttribute("pat3", (Object) pat3);
					log.info("pat3" + pat3);
					}
					if(i==4){
          			String pat4=pat;
          			session.setAttribute("pat4", (Object) pat4);
					log.info("pat4" + pat4);
					}
					if(i==5){
          			String pat5=pat;
          			session.setAttribute("pat5", (Object) pat5);
					log.info("pat5" + pat5);
					}
					if(i==6){
          			String pat6=pat;
          			session.setAttribute("pat6", (Object) pat6);
					log.info("pat6" + pat6);
					}
					if(i==7){
          			String pat7=pat;
          			session.setAttribute("pat7", (Object) pat7);
					log.info("pat7" + pat7);
					}
					if(i==8){
          			String pat8=pat;
          			session.setAttribute("pat8", (Object) pat8);
					log.info("pat8" + pat8);
					}
          			%>
          			<td width="12%" align="left" bgcolor="#cacaca" class="gridStyle-odd" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= (String)pat %></p>
            </td>
          			
          			<td width="12%" align="left" bgcolor="#cacaca" class="gridStyle-odd" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= i11.next() %></p>
            </td>
          			<td width="12%" align="left" bgcolor="#cacaca" class="gridStyle-odd" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= i11.next() %></p>
            </td>
         		 	<td width="12%" align="left" bgcolor="#cacaca" class="gridStyle-odd" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= i11.next() %></p>
            </td>
       			   <td width="12%" align="left" bgcolor="#cacaca" class="gridStyle-odd" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= i11.next() %></p>
            </td>
     		     	<td width="12%" align="left" bgcolor="#cacaca" class="gridStyle-odd" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= i11.next() %></p>
            </td>
     		     	<td width="12%" align="left" bgcolor="#cacaca" class="gridStyle-odd" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= i11.next() %></p>
            </td>
        		  	
      		    	
      		    	<td width="12%" align="left" bgcolor="#cacaca" class="gridStyle-odd" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= i11.next() %></p>
            </td>
    			</tr>
    			<%}
    			else{
    		num2222++;
    			log.info("else running,,,naresh");
    			%>
     		<tr bgcolor="white">
     		
     				 <td  nowrap="nowrap" align="center" class="gridStyle-even" bgcolor="white" >
     				 <% if(pid ==1){ %>
                  <html:checkbox    property='<%= ""+i %>'/>
                     <% } 
                       if(pid == 2){ %>
                  <html:checkbox   disabled="true"  property='<%= ""+i %>'/>   
                  <% } %>  
                  
              &nbsp;
            </td>
            <%
          			String xDate = (String) i11.next();
          			log.info("xDateisisisisiissi" + xDate);
          			if(i==1){
          			String xDate1=xDate;
          			session.setAttribute("xDate1", (Object) xDate1);
					log.info("xDate1" + xDate1);
					}
					if(i==2){
          			String xDate2=xDate;
          			session.setAttribute("xDate2", (Object) xDate2);
					log.info("xDate2" + xDate2);
					}
					if(i==3){
          			String xDate3=xDate;
          			session.setAttribute("xDate3", (Object) xDate3);
					log.info("xDate3" + xDate3);
					}
					if(i==4){
          			String xDate4=xDate;
          			session.setAttribute("xDate4", (Object) xDate4);
					log.info("xDate4" + xDate4);
					}
					if(i==5){
          			String xDate5=xDate;
          			session.setAttribute("xDate5", (Object) xDate5);
					log.info("xDate5" + xDate5);
					}
					if(i==6){
          			String xDate6=xDate;
          			session.setAttribute("xDate6", (Object) xDate6);
					log.info("xDate6" + xDate6);
					}
					if(i==7){
          			String xDate7=xDate;
          			session.setAttribute("xDate7", (Object) xDate7);
					log.info("xDate7" + xDate7);
					}
					if(i==8){
          			String xDate8=xDate;
          			session.setAttribute("xDate8", (Object) xDate8);
					log.info("xDate8" + xDate8);
					}
          			%>
     				 <td width="12%" align="left" bgcolor="white" class="gridStyle-even" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= (String)xDate %></p>
            </td><%
          			String num = (String) i11.next();
          			if(i==1){
          			String num1=num;
          			session.setAttribute("num1", (Object) num1);
					log.info("num1" + num1);
					}
					if(i==2){
          			String num2=num;
          			session.setAttribute("num2", (Object) num2);
					log.info("num2" + num2);
					}
					if(i==3){
          			String num3=num;
          			session.setAttribute("num3", (Object) num3);
					log.info("num3" + num3);
					}
					if(i==4){
          			String num4=num;
          			session.setAttribute("num4", (Object) num4);
					log.info("num4" + num4);
					}
					if(i==5){
          			String num5=num;
          			session.setAttribute("num5", (Object) num5);
					log.info("num5" + num5);
					}
					if(i==6){
          			String num6=num;
          			session.setAttribute("num6", (Object) num6);
					log.info("num6" + num6);
					}
					if(i==7){
          			String num7=num;
          			session.setAttribute("num7", (Object) num7);
					log.info("num7" + num7);
					}
					if(i==8){
          			String num8=num;
          			session.setAttribute("num8", (Object) num8);
					log.info("num8" + num8);
					}
          			%>
     				<td width="12%" align="left" bgcolor="white" class="gridStyle-even" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= (String)num %></p>
            </td><%
          			String pat = (String) i11.next();
          			if(i==1){
          			String pat1=pat;
          			session.setAttribute("pat1", (Object) pat1);
					log.info("pat1" + pat1);
					}
					if(i==2){
          			String pat2=pat;
          			session.setAttribute("pat2", (Object) pat2);
					log.info("pat2" + pat2);
					}
					if(i==3){
          			String pat3=pat;
          			session.setAttribute("pat3", (Object) pat3);
					log.info("pat3" + pat3);
					}
					if(i==4){
          			String pat4=pat;
          			session.setAttribute("pat4", (Object) pat4);
					log.info("pat4" + pat4);
					}
					if(i==5){
          			String pat5=pat;
          			session.setAttribute("pat5", (Object) pat5);
					log.info("pat5" + pat5);
					}
					if(i==6){
          			String pat6=pat;
          			session.setAttribute("pat6", (Object) pat6);
					log.info("pat6" + pat6);
					}
					if(i==7){
          			String pat7=pat;
          			session.setAttribute("pat7", (Object) pat7);
					log.info("pat7" + pat7);
					}
					if(i==8){
          			String pat8=pat;
          			session.setAttribute("pat8", (Object) pat8);
					log.info("pat8" + pat8);
					}
          			%>
          			<td width="12%" align="left" bgcolor="white" class="gridStyle-even" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= (String)pat %></p>
            </td>
          			
          			<td width="12%" align="left" bgcolor="white" class="gridStyle-even" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= i11.next() %></p>
            </td>
          			<td width="12%" align="left" bgcolor="white" class="gridStyle-even" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= i11.next() %></p>
            </td>
         		 	<td width="12%" align="left" bgcolor="white" class="gridStyle-even" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= i11.next() %></p>
            </td>
       			   <td width="12%" align="left" bgcolor="white" class="gridStyle-even" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= i11.next() %></p>
            </td>
     		     	<td width="12%" align="left" bgcolor="white" class="gridStyle-even" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= i11.next() %></p>
            </td>
     		     	<td width="12%" align="left" bgcolor="white" class="gridStyle-even" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= i11.next() %></p>
            </td>
        		  	
      		    	
      		    	<td width="12%" align="left" bgcolor="white" class="gridStyle-even" nowrap="nowrap">
              <p style="margin-left: 5; margin-right: 5"><%= i11.next() %></p>
            </td>
    			</tr>
    			<%}%>
  			
			<%}%>
				</tbody>
			</table>
			
			<table  align="center" cellpadding="0" cellspacing="0" >
   				<tr>
	 				
   					 <td width="70" nowrap="nowrap" align="left"> 		
            <html:button property="submitButton" onclick="check1();">
              <bean:message key="defineIndex30"/>
            </html:button>
          </td>
   					<td width="80"  height="35" nowrap="nowrap" align="center">
					<html:button property="resetButton" onclick="reset1();"> 
		       		<bean:message key="indexUpdate.reset"/>
             		</html:button></td>
					<td width="80"  height="35"  align="right" nowrap="nowrap">
   						<html:button onclick="history.go(-1)" property="b11" tabindex="12">
         					<bean:message key="indexUpdate.cancel"/>
      					</html:button> 
   					</td>
   					
  				</tr>
  			
  	</table>
	</div>
	  		<%}%>
	  		</logic:equal>
 <%
		app.PatForm brf1=(app.PatForm)session.getAttribute("PatBean");
		String sub=request.getParameter("submitButton");
		String pid1=brf1.getSelectfinance();
		log.info(" before pid1 in the one value" + pid1);
		%>
		<logic:equal value="yes" property="check" name="PatBean">
		<% if(pid1.equals("1")){%>
		
  		<table class="sorted" ID="sortTabletable" width="100%" border="1" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
			<tr>
			<!-- <table border="1" width="100%" align="right" cellpadding="2" cellspacing="0" id="t1" class="sortable" bordercolor="#EFEFEF">
 		      	<tr> -->
          		<td width="12%" align="left" bgcolor="#cacaca" class="gridStyle-odd" nowrap="nowrap">
          	    <p style="margin-left: 5; margin-right: 5">Total Number of months</p>
               	</td>
          		<td width="12%" align="left" bgcolor="#cacaca" class="gridStyle-odd" nowrap="nowrap">
          		<p style="margin-left: 5; margin-right: 5"><%= brf1.getTotNum() %></p>
                </td></tr><tr>
            <td width="12%" align="left" bgcolor="white" class="gridStyle-even" nowrap="nowrap">
            <p style="margin-left: 5; margin-right: 5">Latest xDate is</p>
            </td>
            <td width="12%" align="left" bgcolor="white" class="gridStyle-even" nowrap="nowrap">
            <p style="margin-left: 5; margin-right: 5"><%= brf1.getXDate1() %></p>
            </td></tr><tr>
            <td width="12%" align="left" bgcolor="#cacaca" class="gridStyle-odd" nowrap="nowrap">
            <p style="margin-left: 5; margin-right: 5">Market Cap value is</p>
            </td>
            <td width="12%" align="left" bgcolor="#cacaca" class="gridStyle-odd" nowrap="nowrap">
            <p style="margin-left: 5; margin-right: 5"><%= brf1.getMcv() %></p>
            </td></tr><tr>
            <td width="12%" align="left" bgcolor="white" class="gridStyle-even" nowrap="nowrap">
            <p style="margin-left: 5; margin-right: 5">Total PAT is</p>
            </td>
            <td width="12%" align="left" bgcolor="white" class="gridStyle-even" nowrap="nowrap">
            <p style="margin-left: 5; margin-right: 5"><%= brf1.getTotPat1() %></p>
            </td></tr><tr>
            <td width="12%" align="left" bgcolor="#cacaca" class="gridStyle-odd" nowrap="nowrap">
            <p style="margin-left: 5; margin-right: 5">The Calculated P/E is</p>
            </td>
            <td width="12%" align="left" bgcolor="#cacaca" class="gridStyle-odd" nowrap="nowrap">
            <p style="margin-left: 5; margin-right: 5"><%= brf1.getPe() %></p>
            <% } %> 
            </td></tr>
          </table> 
         <% if(pid1.equals("2")){%>
         <table class="sorted" ID="sortTabletable" width="100%" border="1" align="center" cellpadding="0" cellspacing="0" bgcolor="#EFEFEF">
			<tr>
			<td width="12%" align="left" bgcolor="#cacaca" class="gridStyle-odd" nowrap="nowrap">
          	    <p style="margin-left: 5; margin-right: 5">Latest xDate is</p>
               	</td>
          		<td width="12%" align="left" bgcolor="#cacaca" class="gridStyle-odd" nowrap="nowrap">
          		<p style="margin-left: 5; margin-right: 5"><%= brf1.getDate1() %></p>
               
            </td></tr>
            <td width="12%" align="left" bgcolor="white" class="gridStyle-even" nowrap="nowrap">
            <p style="margin-left: 5; margin-right: 5">Market Value on Selected Date is</p>
            </td>
            <td width="12%" align="left" bgcolor="white" class="gridStyle-even" nowrap="nowrap">
            <p style="margin-left: 5; margin-right: 5"><%= brf1.getMcv() %></p>
            </td></tr><tr>
            <td width="12%" align="left" bgcolor="white" class="gridStyle-even" nowrap="nowrap">
            <p style="margin-left: 5; margin-right: 5">P/B Ratio is</p>
            </td>
            <td width="12%" align="left" bgcolor="white" class="gridStyle-even" nowrap="nowrap">
            <p style="margin-left: 5; margin-right: 5"><%= brf1.getPb() %></p>
             <% } %> 
            </td></tr><tr>
          </table> 
         </logic:equal>
            
            
   			<!-- table started%%%%%%%%%%%%%%%%%%%%%%%%%%%5 -->
   			
   	<html:hidden property="resetvalue" value="no"></html:hidden>		
   	<html:hidden property="hiddenVar1" value="no"></html:hidden>			
	<html:hidden property="resetval" value="no"></html:hidden>
	<html:hidden property="compute" value="no"></html:hidden>
	<html:hidden property="show1" value="no"></html:hidden>
	<html:hidden property="check" value="no"  ></html:hidden>
	<html:hidden property="vanish" value="no"></html:hidden>
	
  </html:form>
  
</body>
 <script language="javascript">

function go1() {
var pat_value = document.forms[0].selectStock.value;
var pat_value1 = document.forms[0].selectExchange.value;
var fin_value = document.forms[0].selectfinance.value;
var datemvc = document.forms[0].mvcdate.value;
if (pat_value1 == "0")
 {
 alert( "Please Select the Exchange");
 return false;
 }
 if (pat_value == "0")
 {
 alert( "Please Select the Stock");
 return false;
 }
 if (fin_value == "0")
 {
 alert( "Please Select the Computation type");
 return false;
 } 
 if (datemvc == "")
 {
 alert( "Pleas Enter the date to which P/E");
 return false;
 } 
 document.forms[0].compute.value="yes";
 document.forms[0].check.value="no";
 document.forms[0].submit();

}

function check1()
{
 document.forms[0].compute.value="yes";
 document.forms[0].hiddenVar1.value="yes";
 document.forms[0].submit();
 }


function test()
{
document.forms[0].vanish.value="yes";
document.forms[0].check.value="no";
document.forms[0].submit();
}

function reset1()
{
document.forms[0].resetvalue.value="yes";
document.forms[0].resetval.value="yes";
document.forms[0].submit();
}
function initialize()
{
document.forms[0].check.value="no";
}

</script>
</html:html>
