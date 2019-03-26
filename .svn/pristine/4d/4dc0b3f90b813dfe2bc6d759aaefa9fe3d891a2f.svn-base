<%@ page import = "java.util.*" %>
<%@ page import = "java.sql.*" %>
<%@ page import ="app.*"%>
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@page import="org.apache.log4j.Logger"%>
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
<title>Tree</title>
<style>
<!--
#foldheader{cursor:pointer;cursor:hand ; font-weight:bold ;
list-style-image:url(fold.gif)}
#foldinglist{list-style-image:url(list.gif)}
//-->
</style>
<script language="JavaScript1.2">
<!--

//Smart Folding Menu tree- By Dynamic Drive (rewritten 03/03/02)
//For full source code and more DHTML scripts, visit http://www.dynamicdrive.com
//This credit MUST stay intact for use

var head="display:''"
img1=new Image()
img1.src="fold.gif"
img2=new Image()
img2.src="open.gif"

var ns6=document.getElementById&&!document.all
var ie4=document.all&&navigator.userAgent.indexOf("Opera")==-1

function checkcontained(e){
var iscontained=0
cur=ns6? e.target : event.srcElement
i=0
if (cur.id=="foldheader")
iscontained=1
else
while (ns6&&cur.parentNode||(ie4&&cur.parentElement)){
if (cur.id=="foldheader"||cur.id=="foldinglist"){
iscontained=(cur.id=="foldheader")? 1 : 0
break
}
cur=ns6? cur.parentNode : cur.parentElement
}

if (iscontained){
var foldercontent=ns6? cur.nextSibling.nextSibling : cur.all.tags("UL")[0]
if (foldercontent.style.display=="none"){
foldercontent.style.display=""
cur.style.listStyleImage="url(open.gif)"
}
else{
foldercontent.style.display="none"
cur.style.listStyleImage="url(fold.gif)"
}
}
}

if (ie4||ns6)
document.onclick=checkcontained

//-->
</script>
<style fprolloverstyle>A:hover {color: red; font-weight: bold}
</style>

</head>

<body topmargin="0" leftmargin="0" bgcolor="#F4F4F4" vlink="#0000FF">


<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%">

<ul>
   <li id="foldheader">
    <p align="left" style="margin-left: -9"><font face="Arial" size="2">Harrier</font></li>
  	 <ul id="foldinglist">
       	
       	<li id="foldheader">
          <p align="left" style="margin-left: -30"><font size="1" face="Arial">Indices</font></li>
             <ul id="foldinglist" style="display:none" style=&{head};>		
 <%		Connection con = null;
		StringBuffer buff = new StringBuffer();
		ResultSet rst =null;
	//	Connect c = new Connect();
		Connect c = ConnectInit.getConnect();
		con = (Connection) application.getAttribute("DataBase.Connection"); 
		if(con == null)
		 {
		 	con = c.getConnection();
		 	application.setAttribute("DataBase.Connection",con);
		 }
				
 		rst = c.getClientList("tree_index");
						
		boolean changed=true;	
		String temp1="";
		String temp = "";
		int folder= 0;
		String stock_detail=null;	
		int count=0;
	 while(rst.next())
	  {
 	 		count++;
 	 			temp = rst.getString(2);
 	 			if(count==1)
 	 			{
 	 				temp1=temp;
 	 			}
 	 		log.info("index name is "+temp ); 	 	
     		if(!temp.trim().equals(temp1.trim()))
	     		{	
	      			temp1=temp;
          			changed=true;
 					log.info("inside if");
 				if(folder!=0) {%>
 				</ul>
 				<% }
 				}
 	 
	   	if(changed && temp!= "")
	     {	
	     	log.info("Inside first if");
	     	folder++;
	     	changed =false;
	     	String id =rst.getString(4);
			log.info("index id is "+id);

         %>
         <li id="foldheader">      
         <p align="left" style="margin-left: -60"><font size="1" face="Arial"><a href="./indexcompose.jsp?D1=<%=id%>&B1=View" Target="frmMain"><%=temp%></a></font></li>
 		 <ul id="foldinglist" style="display:none" style=&{head};> 
		<%}%>
             <% if(temp!=""){%>
            <li>
				<% 
		  			
		  			stock_detail="./stockmaster2.jsp?s_stockid="+rst.getString(3); 
		  		%>
			        <p align="left" style="margin-left: -75"><a href="<%= stock_detail  %>" Target="frmMain" onmouseover="window.status='go!!';return true"><font size="1" face="Arial"><%=rst.getString(1)%></font></a>
     		</li>
 		<%	}%>
 		<%   }   %> 
   
  	 </ul>
	</ul>
        <li id="foldheader">
          <p align="left" style="margin-left: -30"><font size="1" face="Arial">Stocks</font></li>
             <ul id="foldinglist" style="display:none" style=&{head};>
       <% 
				
				 rst = c.getClientList("tree_stocks");
						
		changed=true;	
		temp1="";
		temp="";
		folder= 0;
		stock_detail=null;
		int count1=0;
		 while(rst.next())
		  {
 		 	count1++;
 		 	temp = rst.getString(2);
 	 		if(count1==1)
 	 		{
 	 			temp1=temp;
 	 		}
 	 		if(!temp.trim().equals(temp1.trim()))
	  	  {	
	    		temp1=temp;
        		changed=true;
 				log.info("inside if");
 				if(folder!=0) {%>
 				</ul>	
 				<% }
 				}
	   		if(changed && temp!="")
	    	 {	
	    	 	log.info("Inside first if");
	    		 	folder++;
	     		changed =false;
        	 %>
         <li id="foldheader">      
         <p align="left" style="margin-left: -60"><font size="1" face="Arial"><%=temp%></font></li>
 		 <ul id="foldinglist" style="display:none" style=&{head};> 
		  <% } if(temp!=""){%>
            <li>
			
				<% 
		  			stock_detail="./stockmaster2.jsp?s_stockid="+rst.getString(3); 
		  		%>
			        <p align="left" style="margin-left: -75"><a href="<%= stock_detail  %>" Target="frmMain" onmouseover="window.status='go!!';return true"><font size="1" face="Arial"><%= rst.getString(1) %></font></a>
     		</li>
 		<%	}%>
 		
 			<% }  %> 
   </ul>
	</ul>   
  </td>
 </tr>
</table>

</body>

</html>
