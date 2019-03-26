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
<%@ page language="java" import="app.*"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
			LogonForm form = (LogonForm)session.getAttribute("user");
			  if(form == null)
			response.sendRedirect("login1.jsp");
%>
<html >
<head>
<html:base/>
</head>
<link href="StyleSheet.css" rel="stylesheet" type="text/css">
	<table width="1000" cellspacing="0" cellpadding="0" >
        		<tr>
		          	<td width="250" class="subHeader" nowrap="nowrap">
		          		&nbsp;
		          	</td>
		          	<td  class="subHeader" width="665" align="left" colspan="2" nowrap="nowrap">
		          			<font size="3" face="Arial">
		          				<b><bean:message key="SharePattern.title" /></b>
		          			</font>
		         	</td> 
	          </tr>
	</table> 
	
	 
	 
    <form action="ShareHoldingPattern.jsp">
     <table width="737" cellspacing="0" cellpadding="0" >
				   
    <%  
     String chkChart="";
         chkChart=request.getParameter("checkChart");
    String pr = null;
         pr = request.getParameter("Pr");   // Check if clicked on printer friendly link.
    	try{
		    if(pr.equals(null))
    	       { pr = "N";} 
        	   }catch(Exception e){pr = "N";} %>
    <%  if(!pr.equals("Y"))
            { %>	
           <td width="230" nowrap="nowrap" align="right">
           <font size="2" face="Arial">
           				 <bean:message key="SharePattern.title" />:
           			</font>
        		</td> 
           
    <% } %>
		
        <%
     //   org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
       ComposeIndex ci = ConnectInit.getComposeIndex();
        ci.setVector_companylist();        
        String var;
        var=request.getParameter("D1");
        log.info("\tvar is "+var);         
    	Vector v = ci.getVector_companylist();    	
    	Iterator i=v.iterator();  
    	// org.jfree.chart.demo.servlet.AdjustDecimal ad=new org.jfree.chart.demo.servlet.AdjustDecimal();
	     AdjustDecimal ad = ConnectInit.getAdjustDecimal(); 
	     %>  
	<%  if(!pr.equals("Y"))
        { %>  
        <td width="412" nowrap="nowrap" align="left" height="30">
        &nbsp;
        <select size="1" name="D1">
          <option value="0"><bean:message key="IndexCompose.option" /></option>
          
           <% int requestID= 0;
                     try{
	                      requestID = Integer.parseInt(request.getParameter("D1"));
                     	}catch(Exception e){}
    
                     while(i.hasNext())
                      {   
                        int id = Integer.parseInt(i.next().toString());
                         if(id == requestID)
                          {%>
                        	<option selected value="<%=id%>"><%=i.next()%></option>
                       	<%}else { %>
 	                       	<option value="<%=id%>"><%= i.next() %></option>
                       <% }
						}                         
                         %>
                        </select>
                        </td>
                        <td width="114" nowrap="nowrap" align="left" height="30">
                     		<input type="submit" value='<bean:message key="Reports.View"/>' name="B1">
                     	</td>
                     	
                     	<tr>
	         </tr>
      </table>
	         <table width="400">
	         <tr>
	         <td width="118" align="right" nowrap="nowrap">
	             <%
	             
	              if(chkChart!=null && chkChart.equals("checked")){ %>
		  	 <input type="checkbox" name="checkChart" value="checked"  checked />&nbsp;	  
		  	 <% }else{ %>
		   	<input type="checkbox" name="checkChart" value="checked"  />&nbsp;
		 	 <% } %>	   	   
		    </td>
		 	 <td width="272" nowrap="nowrap" align="left">	
		   	<font size="2" face="Arial"><bean:message key="IndexCompose.schart" /></font>
	 		 </td>
         </tr>
         </table>
       <% } %>
     
  <p></p>
       <% 		if(var != null)
				{
					if(!pr.equals("Y")){
					try{
						String astr = null;
                           	astr = "/Income/pages/ShareHoldingPattern.jsp?Pr=Y&D1="+var+"&checkChart="+chkChart; 
                        	String excel = "../pages/FileDownload.jsp?type=18&filename=ShareHoldingPattern.xls&var="+var;
							String str_url = "../pages/EmailReport.jsp?switch_type=18&cas=18&rname=ShareHoldingPattern.xls";
                        %> <table width="800" cellpadding="0" cellspacing="0">
                        	<tr>
                        		<td width="160" nowrap="nowrap" align="right">&nbsp;
                        		</td>
                        		<td width="105" nowrap="nowrap" align="left">
                        			<font size="1">
                        				<a href="javascript:popprinter('<%=astr%>');"><bean:message key="LatestDivisor.printerf" /></a>
                        	</font>
                        		</td>
                        		<td width="110" nowrap="nowrap">
                           			<font size="1">
										<a href=<%= excel %>>
											<bean:message key="LatestDivisor.downloade" /></a>
							</font>
								</td>
						 		<td width="289" nowrap="nowrap">
                           			<font size="1">	
							<a href= <%= str_url %>>
							<bean:message key="LatestDivisor.emailr" />
							</font>
								</td>
							</tr>
						</table>
					<br/>
     <%					}catch(Exception e){log.info("refresh the page.");}
          			}
          		}		
     %>
       <table  border="0" width="900" cellpadding="0" cellspacing="0">
  <tr>
	 <td width="100" nowrap="nowrap">&nbsp;</td>
	 <td width="770" nowrap="nowrap">
       <%	if(var==null)
			{
	%>
	
	<table border="0" align="left" width="550" height="222" class="gridStyle" cellspacing="0" cellpadding="0">
          <tr>
          <td align="center" class="gridStyle-message" valign="middle">
              <p style="margin-left: 9">
              <bean:message key="SharePattern.selcomp" /> </a></p>
            </td>
            </tr>
            </table>
      <% }else {  	
			ci.setVector_shareholdingPattern(var); 
			Vector v1 = ci.getVector_shareholdingPattern();
			Object ci2 = null;
			session.setAttribute("ci2",new Vector(v1));
	        log.info("Size of table   "+v1.size()); 
	if(v1.size()==0)
	{
	%>
	<table border="0" align="left" width="550" height="222" class="gridStyle" cellspacing="0" cellpadding="0">
          <tr>
          <td  class="gridStyle-message" align="center" valign="middle">
              <p style="margin-left: 9"><bean:message key="IndexCompareOHCL.ndata" /></a></p>
            </td>
            </tr>
            </table>
      <% }else {  
      if(chkChart!=null && chkChart.equals("checked")){ 
       			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.UK);
	
			Date dDate = null;
			try {
				dDate = sdf.parse("01-Aug-2002");
			} catch (ParseException e) {
				//  Leave at null
			}
// 			CPieChart.ShareHoldPattReaddata(v1);
// 			String filename = CPieChart.generatePieChart(dDate, session, new PrintWriter(out),3);
// 			String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
// 			log.info("graphURL is"+graphURL);
// 			log.info("filename is"+filename);
       		
       %>      
        </b>
        <p align="left">
<%--         <img src="<%= graphURL %>" width=500 height=400 border=0 usemap="#<%= filename %>"> --%>
        </p>
        <p align="left">
        <br></br>  
        <%}String coname=ci.getCompanyName(var);%>      
	 <b><bean:message key="SharePattern.sholdpat" /> <%= coname %></b>
	 <br></br>
        <table border="1" width="65%" class="gridStyle" cellspacing="0" cellpadding="5" align="left">
        
          <tr>
          <p align="center">
            <td width="15%" class="gridStyle-headertab" align="center" valign="middle"><b><bean:message key="SharePattern.cate" /></b></td>
            <td width="20%" class="gridStyle-headertab" align="center" valign="middle"><b><bean:message key="SharePattern.subcate" /></b></td>
            <td width="15%" class="gridStyle-headertab" align="center" valign="middle"><b><bean:message key="SharePattern.nosecu" /></b></td>
            <td width="15%" class="gridStyle-headertab" align="center" valign="middle"><b>% <bean:message key="SharePattern.holding" /></b></td>     
          </p>
          </tr>
    
          <% 
           log.info("1");
          Iterator i1=v1.iterator(); 
           log.info("1");
           int count=0;
           while(i1.hasNext())
           {  
            	log.info("1");
            	count++;
            	String str=(String)i1.next();
            	double tnoheld=0.0,tpheld=0.0,noheld=0.0,pheld=0.0,noheld1=0.0,pheld1=0.0,noheld2=0.0,pheld2=0.0; 
          %>   
          
          	<tr>
          	<p align="center">
          		<td width="15%" height="1" class="gridStyle-blue" align="center" valign="middle"><bean:message key="SharePattern.prohold" /></a></td>
          		<td width="20%" height="1" class="gridStyle-blue" align="center" valign="middle">  </a></td>
          		<td width="15%" height="1" class="gridStyle-blue" align="center" valign="middle">  </a></td>
          		<td width="15%" height="1" class="gridStyle-blue" align="center" valign="middle">  </a></td>
          	</p>
             </tr>
            
             <tr>
             <p align="center">
                <td width="15%" height="1" class="gridStyle-odd" align="left" valign="middle"> </td>
            	<td width="20%" height="1" align="left" class="gridStyle-even">
             <p style="margin-left: 5; margin-right: 5"><%= i1.next() %></p>
             </p>
            </td>
            <% 
            		String num=(String)i1.next();
            		String per=(String)i1.next();
            		noheld=noheld+Double.parseDouble(num);
            		pheld=pheld+Double.parseDouble(per);
            %>
            <td width="15%" align="right" height="1" class="gridStyle-even">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(num) %></p>
            </td>
            <td width="15%" align="right" height="1" class="gridStyle-even">
              <p style="margin-left: 5; margin-right: 5"><%= per %> </p>
            </td>
           
         </tr>
         <% 
        		String str1=(String)i1.next();
         %>
          <tr>
            <td width="15%" height="1" class="gridStyle-odd" align="left" valign="middle"> </td>
            <td width="20%" height="1" align="left" class="gridStyle-even">
              <p style="margin-left: 5; margin-right: 5"><%= i1.next() %></p>
            </td>
            <% 
            		String num1=(String)i1.next();
            		String per1=(String)i1.next();
            		noheld=noheld+Double.parseDouble(num1);
            		pheld=pheld+Double.parseDouble(per1);
            %>
            <td width="15%" align="right" height="1" class="gridStyle-even">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(num1) %></p>
            </td>
            <td width="15%" align="right" height="1" class="gridStyle-even">
              <p style="margin-left: 5; margin-right: 5"><%= per1 %> </p>
            </td>           
         </tr>
         	<% 
        		String str2=(String)i1.next();
         %>
          <tr>
            <td width="15%" height="1" class="gridStyle-odd" align="left" valign="middle"> </td>
            <td width="20%" height="1" align="left" class="gridStyle-even">
              <p style="margin-left: 5; margin-right: 5"><%= i1.next() %></p>
            </td>
            <% 
            		String num2=(String)i1.next();
            		String per2=(String)i1.next();
            		noheld=noheld+Double.parseDouble(num2);
            		pheld=pheld+Double.parseDouble(per2);
            %>
            <td width="15%" align="right" height="1" class="gridStyle-even">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(num2) %></p>
            </td>
            <td width="15%" align="right" height="1" class="gridStyle-even">
              <p style="margin-left: 5; margin-right: 5"><%= per2 %> </p>
            </td>           
         </tr>
          <%
         	String no=ad.shareholdingpatt(noheld); 
			String p=ad.twodigitdeci(pheld);       	
			
         %>
         <tr>
          		<td width="15%" height="1" class="gridStyle-blue" align="center" valign="middle">  </a></td>
          		<td width="20%" height="1" class="gridStyle-blue" align="center" valign="middle"> <bean:message key="SharePattern.subtotal" />  </a></td>
          		<td width="15%" height="1" class="gridStyle-blue" align="center" valign="middle"> <%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(no) %> </a></td>
          		<td width="15%" height="1" class="gridStyle-blue" align="center" valign="middle"> <%= p %> %</a></td>
             </tr>
         <%
         	  	String str3=(String)i1.next();
            	
          %>   
          
          	<tr>
          		<td width="15%" class="gridStyle-blue" height="1" align="center" valign="middle"><bean:message key="SharePattern.instinv" /></a></td>
          		<td width="20%" class="gridStyle-blue" height="1" align="center" valign="middle">  </a></td>
          		<td width="15%" class="gridStyle-blue" height="1" align="center" valign="middle">  </a></td>
          		<td width="15%" class="gridStyle-blue" height="1" align="center" valign="middle">  </a></td>
             </tr>
           <tr>
            <td width="15%" class="gridStyle-odd" height="1" align="left" valign="middle"></td>
            <td width="20%" align="left" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><%= i1.next() %></p>
            </td>
            <% 
            		String num3=(String)i1.next();
            		String per3=(String)i1.next();
            		noheld1=noheld1+Double.parseDouble(num3);
            		pheld1=pheld1+Double.parseDouble(per3);
            %>
            <td width="15%" align="right" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(num3) %></p>
            </td>
            <td width="15%" align="right" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><%= per3 %> </p>
            </td>           
         </tr>
         	<% 
        		String str4=(String)i1.next();
         %>
          <tr>
            <td width="15%" class="gridStyle-odd" height="1" align="left" valign="middle"> </td>
            <td width="20%" align="left" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><%= i1.next() %></p>
            </td>
            <% 
            		String num4=(String)i1.next();
            		String per4=(String)i1.next();
            		noheld1=noheld1+Double.parseDouble(num4);
            		pheld1=pheld1+Double.parseDouble(per4);
            %>
            <td width="15%" align="right" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(num4) %></p>
            </td>
            <td width="15%" align="right" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><%= per4 %> </p>
            </td>           
         </tr>
         		<% 
        		String str5=(String)i1.next();
         %>
          <tr>
            <td width="15%" class="gridStyle-odd" height="1" align="left" valign="middle"> </td>
            <td width="20%" align="left" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><%= i1.next() %></p>
            </td>
            <% 
            		String num5=(String)i1.next();
            		String per5=(String)i1.next();
            		noheld1=noheld1+Double.parseDouble(num5);
            		pheld1=pheld1+Double.parseDouble(per5);
            %>
            <td width="15%" align="right" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(num5) %></p>
            </td>
            <td width="15%" align="right" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><%= per5 %> </p>
            </td>           
         </tr>
         <%
         
			String no1=ad.shareholdingpatt(noheld1);
			String p1=ad.twodigitdeci(pheld1);
		
         %>
         	<tr>
          		<td width="15%" class="gridStyle-blue" height="1" align="center" valign="middle">  </a></td>
          		<td width="20%" class="gridStyle-blue" height="1" align="center" valign="middle"> <bean:message key="SharePattern.subtotal" /></a></td>
          		<td width="15%" class="gridStyle-blue" height="1" align="center" valign="middle"> <%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(no1) %> </a></td>
          		<td width="15%" class="gridStyle-blue" height="1" align="center" valign="middle"> <%= p1 %> % </a></td>
             </tr>
         		 <%
         	  	String str6=(String)i1.next();
            	
          %>   
          
          	<tr>
          		<td width="15%" class="gridStyle-blue" height="1" align="center" valign="middle"><bean:message key="SharePattern.other" /></a></td>
          		<td width="20%" class="gridStyle-blue" height="1" align="center" valign="middle">  </a></td>
          		<td width="15%" class="gridStyle-blue" height="1" align="center" valign="middle">  </a></td>
          		<td width="15%" class="gridStyle-blue" height="1" align="center" valign="middle">  </a></td>
             </tr>
           <tr>
            <td width="15%" class="gridStyle-odd" height="1" align="left" valign="middle">  </td>
            <td width="20%" align="left" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><%= i1.next() %></p>
            </td>
            <% 
            		String num6=(String)i1.next();
            		String per6=(String)i1.next();
            		noheld2=noheld2+Double.parseDouble(num6);
            		pheld2=pheld2+Double.parseDouble(per6);
            %>
            <td width="15%" align="right" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(num6) %></p>
            </td>
            <td width="15%" align="right" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><%= per6 %> </p>
            </td>           
         </tr>
         	<% 
        		String str7=(String)i1.next();
         %>
          <tr>
            <td width="15%" class="gridStyle-odd" height="1" align="left" valign="middle">  </td>
            <td width="20%" align="left" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><%= i1.next() %></p>
            </td>
             <% 
            		String num7=(String)i1.next();
            		String per7=(String)i1.next();
            		noheld2=noheld2+Double.parseDouble(num7);
            		pheld2=pheld2+Double.parseDouble(per7);
            %>
            <td width="15%" align="right" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(num7) %></p>
            </td>
            <td width="15%" align="right" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><%= per7 %> </p>
            </td>           
         </tr>
         		<% 
        		String str8=(String)i1.next();
         %>
          <tr>
            <td width="15%" class="gridStyle-odd" height="1" align="left" valign="middle">  </td>
            <td width="20%" align="left" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><%= i1.next() %></p>
            </td>
             <% 
            		String num8=(String)i1.next();
            		String per8=(String)i1.next();
            		noheld2=noheld2+Double.parseDouble(num8);
            		pheld2=pheld2+Double.parseDouble(per8);
            %>
            <td width="15%" align="right" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(num8) %></p>
            </td>
            <td width="15%" align="right" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><%= per8 %> </p>
            </td>           
         </tr>
         			<% 
        		String str9=(String)i1.next();
         %>
          <tr>
            <td width="15%" class="gridStyle-odd" height="1" align="left" valign="middle">  </td>
            <td width="20%" align="left" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><%= i1.next() %></p>
            </td>
             <% 
            		String num9=(String)i1.next();
            		String per9=(String)i1.next();
            		noheld2=noheld2+Double.parseDouble(num9);
            		pheld2=pheld2+Double.parseDouble(per9);
            %>
            <td width="15%" align="right" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(num9) %></p>
            </td>
            <td width="15%" align="right" class="gridStyle-even" height="1">
              <p style="margin-left: 5; margin-right: 5"><%= per9 %> </p>
            </td>           
         </tr>
          <%
         	String no2=ad.shareholdingpatt(noheld2);
			String p2=ad.twodigitdeci(pheld2);
		
         %>
         		<tr>
          		<td width="15%" class="gridStyle-blue" height="1" align="center" valign="middle">  </a></td>
          		<td width="20%" class="gridStyle-blue" height="1" align="center" valign="middle"><bean:message key="SharePattern.subtotal" /> </a></td>
          		<td width="15%" class="gridStyle-blue" height="1" align="center" valign="middle"> <%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(no2) %> </a></td>
          		<td width="15%" class="gridStyle-blue" height="1" align="center" valign="middle"> <%= p2 %> % </a></td>
             </tr>
              <% 
            		tnoheld=noheld+noheld1+noheld2;
            		tpheld=pheld+pheld1+pheld2;
            		String tno1=ad.shareholdingpatt(tnoheld);
            		String tp1=ad.twodigitdeci(tpheld);
            		double tpfin=Double.parseDouble(tp1);
					if(tpfin>=99.9)
					{
					 	tp1="100.00";
					}
              %>
             <tr>
          		<td width="15%" class="gridStyle-blue" height="1" align="center" valign="middle">  </a></td>
          		<td width="20%" class="gridStyle-blue" height="1" align="center" valign="middle"> <bean:message key="SharePattern.gtot" /></a></td>
          		<td width="15%" class="gridStyle-blue" height="1" align="center" valign="middle"> <%= org.jfree.chart.demo.servlet.AdjustDecimal.ArrangeAsNumeric(tno1) %> </a></td>
          		<td width="15%" class="gridStyle-blue" height="1" align="center" valign="middle"> <%= tp1  %> %</a></td>
             </tr>
          <% } 
          }
          }%> 
         
           </form>
      </td>
    </tr>
  </tbody>
</table>
</td>
</tr>
</table>
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
<script language="JavaScript">
function popprinter(url)
{
	newwindow=window.open(url,'name','height=500,width=900,scrollbars=yes,left=80,top=130,menubar=yes,status=yes');
	if (window.focus) {newwindow.focus()}
}
</script>
</body>

</html>