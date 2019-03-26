<%-- <%@ page import = "org.jfree.chart.demo.servlet.CPieChart" %> --%>
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
<%@ page  import="app.*"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>

<html>
<head>
<html:base/>
</head>
    
        <p align="center">
         <font size="3"  face="Arial Black">
	 Stock Wise Weightage
	</p> <p align="left">
	 <form action="indexcompose.jsp">
	  <font size="2" color="black" face="Arial">
	 <% 
	 		String chkchecked=request.getParameter("check");
	 		log.info("chkchecked is "+chkchecked);
	 %>
	  
        <font size="2" color="black" face="Arial">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Index 
         Name&nbsp;:&nbsp;&nbsp;&nbsp;              
        <%
     //   org.jfree.chart.demo.servlet.ComposeIndex ci=new org.jfree.chart.demo.servlet.ComposeIndex();
       ComposeIndex ci = ConnectInit.getComposeIndex();
        org.jfree.chart.demo.servlet.FieldSort sort=new org.jfree.chart.demo.servlet.FieldSort();
        ci.setVector_indexlist(chkchecked);        
        String var,str="",field,col;
        int fieldno,colno;
        field=(String)request.getParameter("FieldNo");
         col=(String)request.getParameter("ColNo");
        if(col==null)
        {
        	col="12";
        }       
        if(field==null)
        {
        	field="11";
        }
        fieldno=Integer.parseInt(field);
        colno=Integer.parseInt(col);
        var=request.getParameter("D1");
        String pr1 = (String)request.getParameter("Pr");
        log.info(".....Pr value............."+pr1);
    	Vector v3 = ci.getVector_indexlist();    	
    	Iterator i=v3.iterator();  
    	
	     %> 
	     <input type="hidden" name="Pr" value="<%=pr1%>" >  
	     
	     <% try{
		    if(pr1.equals(null))
    	       { pr1 = "N";}
        	   }catch(Exception e){pr1 = "N";}
           
           if(!pr1.equals("Y"))
           { %>	
        <select size="1" name="D1" >
          <option value="0">Not Selected</option>
          
           <%  int requestID= 0;
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
                        </select> &nbsp;&nbsp;&nbsp; 
                        <% if(var != null)
                         
							
							
                        { String astr = "/Income/pages/indexcompose.jsp?Pr=Y&D1="+var;
                        	//log.info("VAR......................."+var);
                        	//MakeExcel g = new MakeExcel();
							//try
							//{
							//   	g.create_file(var,1);
							//}catch(Exception e){}
							String temp_path = "../pages/FileDownload.jsp?var="+var+"&type=1&filename=IndexCompositionReport.xls";
							String str_url = "../pages/EmailReport.jsp?switch_type=1&cas=1&rname=IndexCompositionReport.xls&varid="+var;
							if (!pr1.equals("Y"))
	    					{
					   %> 
                        	<font size="1" face="Arial" color="#0000FF">
                        	<a href="javascript:popprinter('<%=astr%>');">Printer friendly</a>
                        	&nbsp;&nbsp;&nbsp;&nbsp;
                        	
							<a href=<%= temp_path %>>
							Download Excel</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a href= <%= str_url %>>
							Email Report</a>
							</font>
                        <% } }%>
         <br></br>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    <% if((request.getParameter("check"))!=null && (request.getParameter("check")).equals("checked")){ %>
	 	  <input type="checkbox" name="check" value="checked" onclick="return test1()" checked />&nbsp;
	  
	   <% }else{ %>
	   <input type="checkbox" name="check" value="checked" onclick="return test1()"  />&nbsp;
	  <% } %>	   	   
	   Show Sectoral Indices</font>
	     &nbsp;&nbsp;&nbsp; <input type="submit" value="View" name="B1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><br><br>
	 </p> <p align="center">
	 <%}%>
	 
	 <%
 		   String name = "";
           if(pr1.equals("Y"))
           {
           		int var_int = Integer.parseInt(var);
           		name = ci.get_index_name(var_int);
           %><%= name%>
     <%}%>
	 
  <%	if(var==null)
	{
	%>
	<table border="0" align="center" width="631" height="222" cellspacing="0" cellpadding="0">
          <tr>
          <td  bgcolor="#cacaca" align="center" valign="middle">
              <p style="margin-left: 9"><font face="Arial" color="blue" size="5">Select
            Index Name to View  Data  </a></font></p>
            </td>
            </tr>
            </table>
      <% }else {  
	
	ci.setVector_tabledata(var);
	Vector v1 = ci.getVector_tabledata();
	Object ci2 = null;
	session.setAttribute("ci2",new Vector(v1));
	if(v1.size()==0)
	{
	%>
	<table border="0" align="center" width="631" height="222" cellspacing="0" cellpadding="0">
          <tr>
          <td  bgcolor="#cacaca" align="center" valign="middle">
              <p style="margin-left: 9"><font face="Arial" color="blue" size="5">No
              Data Available For Criteria You Have Selected</a></font></p>
            </td>
            </tr>
            </table>
      <% }else {     
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.UK);
	
	Date dDate = null;
	try {
		dDate = sdf.parse("01-Aug-2002");
	} catch (ParseException e) {
		//  Leave at null
	}
// 	CPieChart.ReaddataCompose(v1);
// 	String filename = CPieChart.generatePieChart(dDate, session, new PrintWriter(out),1);
// 	String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
// 	log.info("graphURL is"+graphURL);
	//	log.info("filename is"+filename);
%>       
		
<%-- 		<img src="<%= graphURL %>" width=700 height=800 border=0 usemap="#<%= filename %>"> --%>
		
      <br></br>
      	<%
      	  int dir1=0,dir2=0,dir3=0,dir4=0,dir5=0,dir6=0,dir7=0,dir8=0,dir9=0,dir10=0,dir11=0;
      	  Vector v = ci.getVector_tabledata();	    
	      if(fieldno==1)
	      {
	      		dir1=sort.getcount1();
	      		v=sort.SetOrderSort(v,1,12);	      		             	
          }
          if(fieldno==2){
                dir2=sort.getcount2();
                v=sort.SetOrderSortNo(v,2,12);
          }
          if(fieldno==3){
          		dir3=sort.getcount3();
                v=sort.SetOrderSortNo(v,3,12);
          }
          if(fieldno==4){
                dir4=sort.getcount4();
                v=sort.SetOrderSortNo(v,4,12);
          }
          if(fieldno==5){
                dir5=sort.getcount5();
                v=sort.SetOrderSortNo(v,5,12);
          }
          if(fieldno==6){
                dir6=sort.getcount6();
                v=sort.SetOrderSort(v,6,12);
          }
          if(fieldno==7){
               dir7=sort.getcount7();
                v=sort.SetOrderSortNo(v,7,12);
          }
          if(fieldno==8){
                dir8=sort.getcount8();
                v=sort.SetOrderSortNo(v,8,12);
          }
          if(fieldno==9){
               dir9=sort.getcount9();
               v=sort.SetOrderSortNo(v,9,12);
          }
           if(fieldno==10){
               dir9=sort.getcount10();
               v=sort.SetOrderSortNo(v,10,12);
          }
           if(fieldno==11){
               dir9=sort.getcount11();
               v=sort.SetOrderSortDate(v,11,12);
          }
          log.info("Size of table   "+v.size()); 
      
      if (pr1.equals("Y"))
      {
      log.info("======== Y"); 
      %>
        <table border="0" align="center" width="94%" cellspacing="0" cellpadding="5">
         
          <tr>
            <td width="18%" bgcolor="#cacaca" align="center" valign="middle"><a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=1&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">Stock
              Name</a></font>
              <%  
				if(fieldno==1){
				if(dir1%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
            <td width="8%" bgcolor="#cacaca" align="right" valign="middle"><a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=2&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">Total
              Shares</font></a>
              <%  
				if(fieldno==2){
				if(dir2%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
            <td width="5%" bgcolor="#cacaca" align="right" valign="middle"><a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=3&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">IWF</a></font>
            <%  
				if(fieldno==3){
				if(dir3%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
         	 <td width="5%" bgcolor="#cacaca" align="right" valign="middle"><a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=4&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">Mkt. Lot</a></font>
            <%  
				if(fieldno==4){
				if(dir4%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
            <td width="8%" bgcolor="#cacaca" align="right" valign="middle"><a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=5&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">Price
              (LTP)</font></a>
              <%  
				if(fieldno==5){
				if(dir5%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
            <td width="5%" bgcolor="#cacaca" align="center" valign="middle"><a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=6&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">Curr.</font></a>
            <%  
				if(fieldno==6){
				if(dir6%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
         		 <td width="5%" bgcolor="#cacaca" align="center" valign="middle"><a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=7&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">Curr. Exch. Rate</font></a>
            <%  
				if(fieldno==7){
				if(dir7%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
            <td width="10%" bgcolor="#cacaca" align="center" valign="middle"><a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=8&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">Market
              cap</a></font>
              <%  
				if(fieldno==8){
				if(dir8%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
            <td width="10%" bgcolor="#cacaca" align="center" valign="middle"><a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=9&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">Adjusted
              Market</a> Cap</font>
              <%  
				if(fieldno==9){
				if(dir9%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
            <td width="8%" bgcolor="#cacaca" align="right" valign="middle"><a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=10&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">Weightage</font></a>
            <%  
				if(fieldno==10){
				if(dir10%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
            <td width="12%" bgcolor="#cacaca" align="center" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=11&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">Date             
              </font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <%  
				if(fieldno==11){
				if(dir11%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
          </tr>
          <% 
          }   //-------------------------Y
          else if (!pr1.equals("Y"))
          { 
          %>
          <table border="0" align="center" width="94%" cellspacing="0" cellpadding="5">
          <tr>
            <td width="18%" bgcolor="#cacaca" align="center" valign="middle"><a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=1&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">Stock
              Name</a></font>
              <%  
				if(fieldno==1)
				{
					if(dir1%2==0)
					{  %>
				 		<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<%  }else
           			{ %>
           		  		<img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <% } 
         		 } %></td>
            <td width="8%" bgcolor="#cacaca" align="right" valign="middle"><a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=2&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">Total
              Shares</font></a>
              <%  
				if(fieldno==2){
				if(dir2%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
            <td width="5%" bgcolor="#cacaca" align="right" valign="middle"><a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=3&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">IWF</a></font>
            <%  
				if(fieldno==3){
				if(dir3%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
         	 <td width="5%" bgcolor="#cacaca" align="right" valign="middle"><a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=4&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">Mkt. Lot</a></font>
            <%  
				if(fieldno==4){
				if(dir4%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
            <td width="8%" bgcolor="#cacaca" align="right" valign="middle"><a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=5&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">Price
              (LTP)</font></a>
              <%  
				if(fieldno==5){
				if(dir5%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
            <td width="5%" bgcolor="#cacaca" align="center" valign="middle"><a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=6&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">Curr.</font></a>
            <%  
				if(fieldno==6){
				if(dir6%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
         		 <td width="5%" bgcolor="#cacaca" align="center" valign="middle"><a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=7&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">Curr. Exch. Rate</font></a>
            <%  
				if(fieldno==7){
				if(dir7%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
            <td width="10%" bgcolor="#cacaca" align="center" valign="middle"><a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=8&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">Market
              cap</a></font>
              <%  
				if(fieldno==8){
				if(dir8%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
            <td width="10%" bgcolor="#cacaca" align="center" valign="middle"><a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=9&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">Adjusted
              Market</a> Cap</font>
              <%  
				if(fieldno==9){
				if(dir9%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
            <td width="8%" bgcolor="#cacaca" align="right" valign="middle"><a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=10&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">Weightage</font></a>
            <%  
				if(fieldno==10){
				if(dir10%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
            <td width="12%" bgcolor="#cacaca" align="center" valign="middle" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="indexcompose.jsp?D1=<%=var%>&B1=view&FieldNo=11&ColNo=12" onmouseover="window.status='';return true"><font face="Arial" color="blue" size="2">Date                  
              </font></a>
              <%  
				if(fieldno==11){
				if(dir11%2==0){  %>
				 	<img border="0" src="images/down.jpg" width="13" align="middle" height="12">            
           		<% }else{ %>
           		  <img border="0" src="images/up.jpg" width="13" align="middle" height="12">
         		 <%  } 
         		 } %></td>
          </tr>
          <%   // --------------------------N
          } 

          
		      i=v.iterator(); 
          	  int count=0;
          	  double total=0.00;
          	  while(i.hasNext())
          	  {  
           	  	count++;
           		String id=(String)i.next();
           		if(count%2!=0)
           		{	
           			String temp="../pages/stockmaster2.jsp?s_stockid="+id; 
          %>
          <tr>
            <td width="18%" align="left" height="37">
           <% if(!pr1.equals("Y"))
           { %>
              <p style="margin-left: 5; margin-right: 5"><font face="Arial" color="blue" size="1"><a href="<%= temp %>"><%= i.next() %></a> </font></p>
           <% }if(pr1.equals("Y"))
           { %>
              <p style="margin-left: 5; margin-right: 5"><font face="Arial" color="blue" size="1"><%= i.next() %></font></p>
           <%}%>
            </td>
            <td width="8%" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= i.next() %></font></p>
            </td>
            <td width="5%" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= i.next() %></font></p>
            </td>
            <td width="5%" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= i.next() %></font></p>
            </td>
            <td width="8%" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= i.next() %></font></p>
            </td>
            <td width="5%" align="left">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= i.next() %></font></p>
            </td>
            <td width="5%" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= i.next() %></font></p>
            </td>             
            <td width="10%" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= i.next() %></font></p>
            </td>              
            <td width="10%" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= i.next() %></font></p>
            </td>
            
            <td width="8%" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= i.next() %>&nbsp;%</font></p>
            </td>
            <td width="12%" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= i.next() %></font></p>
            </td>
          </tr>
          
          <%  }else
          	   { 
          			String temp1="../pages/stockmaster2.jsp?s_stockid="+id;
          %>
           <tr>
            <td width="18%" bgcolor="#eeeeee" align="left" height="37">
             <% if(!pr1.equals("Y"))
           		{ %>
              <p style="margin-left: 5; margin-right: 5"><font face="Arial" color="blue" size="1"><a href="<%= temp1 %>"><%= i.next() %></a> </font></p>
             <% }if(pr1.equals("Y"))
           		{ %>
           	  <p style="margin-left: 5; margin-right: 5"><font face="Arial" color="blue" size="1"><%= i.next() %></font></p>
           	  <%}%>
            </td>
            <td width="8%" bgcolor="#eeeeee" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= i.next() %></font></p>
            </td>
            <td width="5%" bgcolor="#eeeeee" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= i.next() %></font></p>
            </td>
            <td width="5%" bgcolor="#eeeeee" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= i.next() %></font></p>
            </td>
            <td width="8%" bgcolor="#eeeeee" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= i.next() %></font></p>
            </td>
            <td width="5%" bgcolor="#eeeeee" align="left">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= i.next() %></font></p>
            </td>
            <td width="5%" bgcolor="#eeeeee" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= i.next() %></font></p>
            </td>                    
            <td width="10%" bgcolor="#eeeeee" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= i.next() %></font></p>
            </td>                    
            <td width="10%" bgcolor="#eeeeee" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= i.next() %></font></p>
            </td>
            
            <td width="8%" bgcolor="#eeeeee" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= i.next() %>&nbsp;%</font></p>
            </td>
            <td width="12%" bgcolor="#eeeeee" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"><%= i.next() %></font></p>
            </td>
          </tr>
           <% }%>
	<%	} %>
			<tr>
            <td width="18%" bgcolor="#cacaca" height="37">
              <p style="margin-left: 5; margin-right: 5"><font face="Arial" color="blue" size="1"></a> </font></p>
            </td>
            <td width="4%" bgcolor="#cacaca" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"></font></p>
            </td>
            <td width="4%" bgcolor="#cacaca" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"></font></p>
            </td>
            <td width="9%" bgcolor="#cacaca" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"></font></p>
            </td>
            <td width="3%" bgcolor="#cacaca" align="center">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"></font></p>
            </td>                
            <td width="9%" bgcolor="#cacaca" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"></font></p>
            </td> 
            <td width="9%" bgcolor="#cacaca" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"></font></p>
            </td> 
            <td width="9%" bgcolor="#cacaca" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"></font></p>
            </td>         
            <td width="9%" bgcolor="#cacaca" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="3" face="Arial">Total </font></p>
            </td>
            <% 
            		String tot=ci.getTotal();        		
             %>
            <td width="6%" bgcolor="#cacaca" align="right">
              <p style="margin-left: 5; margin-right: 5"><font size="2" face="Arial"><%=tot%> %</font></p>
            </td>
            <td width="12%" bgcolor="#cacaca" align="center">
              <p style="margin-left: 5; margin-right: 5"><font size="1" face="Arial"></font></p>
            </td>
          </tr>
        </table>
        	<%	}}
        	 %> 
        	
        	
        	
        	
        
      </td>
    </tr>
   </form>
  </body>
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
</body>
<script  language="javascript">
function test1(){
	document.forms[0].submit();
}
var newwindow;
function popprinter(url)
{
	newwindow=window.open(url,'name','height=600,width=700,scrollbars=yes,left=170,top=130,menubar=yes,status=yes');
	if (window.focus) {newwindow.focus()}
}

</script>
</html>