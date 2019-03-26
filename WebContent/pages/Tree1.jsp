
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import = "java.io.*,java.util.*" %>
<%@ page import = "java.sql.*" %>
<%@ page import ="app.*"%>
<%@ page import ="com.tree.*"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>






<%
		//	Logger Logging = Logger.getLogger("Tree1.jsp");
			LogonForm form = (LogonForm)session.getAttribute("user");
			AcessControl asc=new AcessControl();
			 if((form==null)||(session==null)){
			 log.info("going to tree1 jsp");
		    %>
		   <jsp:forward page="simpleJsp.jsp"></jsp:forward>
		   
		    <% }
	
			boolean flagindex=false;
			boolean flagstock=false;
			boolean flagindcap=false;
			long start,end;
			
			if(form != null){
				log.info("going to tree1 jsp after refresh");
				//Tree tr=new Tree();
				//tr.returnUserid(session);
				
				//ExternalTree et = new ExternalTree();
				
				//et.setUserid1(userid);
            	Vector uname=new Vector();	
				uname=asc.getUseActivitiesId(form);	
				flagstock=asc.HasAcess("2",uname);
				flagindex=asc.HasAcess("1",uname);
				flagindcap=asc.HasAcess("8",uname);
				log.info("inside if");
                	
			}		
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
#foldheader1{cursor:pointer;cursor:hand ; font-weight:bold ;
list-style-image:url(fold1.gif)}
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

<body topmargin="0" leftmargin="0" bgcolor="#DEE3EF" vlink="#0000FF">
<% 
/*
if(Connect.cache==null){
com.tree.RefreshCache refreshCache=new com.tree.RefreshCache();
Connect.cache=refreshCache.getCache();
Connect.cache.refresh();
} 
*/
%>


<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%">

<ul>
   <li id="foldheader">
    <p align="left" style="margin-left: -9"><font face="Arial" size="2">Harrier</font></li>
  	 <font face="Arial" size="1"><a href="TreeRefresh.jsp">Refresh</a></font>
  	 <ul id="foldinglist">
       	
       	<li id="foldheader">
          <p align="left" style="margin-left: -30"><font size="1" face="Arial"><bean:message key="Indices"/></font></li>
             <ul id="foldinglist" style="display:none" style=&{head};>		
 <%		Connection con = null;
		StringBuffer buff = new StringBuffer();
		ResultSet rst =null;
	
		Connect c = new Connect();
		
		 	StringBuffer stringBuffer;
		
		if(con == null)
		 {
		 	con = c.getdbConnection();
		 	
		 }
				
 	//Change for index
 	com.tree.Tree tree=new com.tree.Tree();
 	String userid1 = session.getAttribute("userid").toString();
 	tree.returnUserid(userid1);
 	ExternalTree externalTree=new ExternalTree();  
    
    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(c.resourceurl+"temp"));
    outputStream.writeObject(externalTree.ht);
   	outputStream.close();
 	
 	//Upto here 
 	
 	start=System.currentTimeMillis(); 	
 	log.info("start time"+start);
	ObjectInputStream inputStream = new ObjectInputStream(
    new FileInputStream(c.resourceurl+"temp"));
    Hashtable hashtable = (Hashtable) inputStream.readObject();
   inputStream.close();
  //Change for index 
 // StringBuffer treeStringBuffer;
	//com.tree.Tree tree;
	//String userid1 = session.getAttribute("userid").toString();
	//log.info("user when login"+userid1);
	//tree = new com.tree.Tree();
    //tree.constructTree("tree_index_query_online",userid1);
    //Object[][] node= tree.getIndexArray();
    //treeStringBuffer=tree.drawTreeIndex(node);
    //String string=new String(treeStringBuffer);
    //hashtable.put("index",string); 
  //Upto here 
String TreeString=(String)hashtable.get("index");
//Logging.debug("Drawing Indices Tree");
		%> 
		
		<%=TreeString%></ul>
        <li id="foldheader">
          <p align="left" style="margin-left: -30"><font size="1" face="Arial"><bean:message key="corporate.stock"/></font></li>
            <ul id="foldinglist">
       	
       	<li id="foldheader">
          <p align="left" style="margin-left: -40"><font size="1" face="Arial">Equity</font></li>
            <ul id="foldinglist" style="display:none" style=&{head};>	
           <!--   <UL id=foldinglist style="DISPLAY: none; head: "> -->

       <% 
       String userid="1";
   //    Logging.debug("Completed Drawing Indices Tree");
  //     Logging.debug("Drawing Stocks Tree");
	   rst = c.getClientList(con,"tree_stocks");
						
		boolean changed=true;	
		String temp1="";
		String temp="";
		int folder= 0;
		String stock_detail=null;
		String stock_bond_detail=null;
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
 				
 				if(folder!=0) {%>
 				</ul>
 				<% }
 				}
	   		if(changed && temp!="")
	    	 {	
	    	 
	    		 	folder++;
	     		changed =false;
        	 %>
         <li id="foldheader">      
         <p align="left" style="margin-left: -60"><font size="1" face="Arial"><%=temp%></font></li>
 		 <ul id="foldinglist" style="display:none" style=&{head};> 
		  <% } if(temp!=""){%>
            <li>
			
				<% 
		  			stock_detail="../pages/masters/stockmaster2.jsp?s_stockid="+rst.getString(3); 
		  			if(flagstock==true){
		  		%>
			        <p align="left" style="margin-left: -75"><a href="<%= stock_detail  %>" Target="frmMain" onmouseover="window.status='go!!';return true"><font size="1" face="Arial"><%= rst.getString(1) %></font></a>
			     <% }else{ %>   
			     	<p align="left" style="margin-left: -75"><font size="1" color="blue" face="Arial"><%= rst.getString(1) %></font>
			     <% } %>
     		</li>
 		<%	}%>
 		
 			<% }  %> 
   </ul>
	</ul>  
	
	
	 
	</ul>
	
	
		
   </ul>	
   <%
//   Logging.debug("Finished Drawing Classification Tree");
 	end= System.currentTimeMillis(); 
    log.info("end time"+end);
 		%> 
  </td>
 </tr>
 </table>
</body>
 <% 
 	try{
 		if(con!=null)
 		{
 			con.close();
 			log.info("con close"+con);
 			
 			}
 		}catch(Exception e){
		log.info("Unable to close connection in Tree1.jsp "+e.getMessage());
 		//	Logging.error("Unable to close connection in Tree1.jsp "+e.getMessage());
 		}	
 		
 		
 %>
 
 
  


</html>
