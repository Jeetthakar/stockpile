
<%@page import="com.harrier.initializeation.ConnectInit"%><%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page language="java" import="app.*,java.sql.*" %>
<%@ page import="java.util.*"%>

<%@ page import="org.apache.log4j.PropertyConfigurator"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger logger = Logger.getLogger(this.getClass());
logger.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<html>
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Child Index Composition</title>
<html:base/>
</head>
<% 
System.out.println("1");
String indexid=request.getParameter("newIndexID"); 
String inname="";
String [] classid=new String[236];
String [] chname=new String[236];
boolean fflag=false;
boolean fflag1=false;
System.out.println("2");
if(indexid==null){
indexid=(String)request.getAttribute("newIndexID"); 
}
System.out.println("3");
String newIndexID=request.getParameter("newIndexID");
request.setAttribute("newIndexID",newIndexID);
if(indexid==null){
System.out.println("4");
indexid=newIndexID;
indexid=(String)session.getAttribute("newIndexID"); 
}else{
System.out.println("5");
session.setAttribute("newIndexID",indexid);
request.setAttribute("newIndexID",indexid);
}

 
 %>
 <%
 System.out.println("\n\n\n");
 String[] ee=request.getParameterValues("indexname");
 System.out.println("6");
 System.out.println("ee :"+ee);
 if(ee!=null){
 chname=new String[ee.length];
 classid=new String[ee.length];
 
 System.out.println("7");
		for(int i=0;i<ee.length;i++){
			System.out.println("e.nextElement() :"+ee[i]); 
		}  
		System.out.println("8");
 System.out.println("\n\n\n");	
 }	
 %>
<% 
String button=null; 
String errors="";
String printerrors="";
String param;
System.out.println("9");
try{
  button = request.getParameter("B3");
}catch(Exception e){

}
if(button!=null ){
	Connection con1 = null;
	Connect c1 = null;
	Statement st;
//	c1 = new Connect();
	c1 = ConnectInit.getConnect();
	con1 = (Connection) application.getAttribute("DataBase.Connection"); 
	if(con1 == null)
	{
		con1 = c1.getConnection();
		application.setAttribute("DataBase.Connection",con1);
	}	     
 	String query2 = null;
 	query2 =ConnectInit.queries.getProperty("auto_generate_child_index"); 
 	System.out.println("query2 in getBaseValues is :"+query2);
 	st = con1.createStatement();
 	 ResultSet rst=st.executeQuery(query2);
 	 String childindexname="";
 	 int i=0;
 	 float ibasevalue=0;
 	 Statement st1=con1.createStatement();
 	 String q1="select index_name from index_master where index_id='"+indexid+"'";
 	 System.out.println("q1 :"+q1);
 	 ResultSet rst1=null;
 	 try{
 	  System.out.println("q1 :"+q1);
rst1=st1.executeQuery(q1);
 System.out.println("q1 :"+q1);
}catch(Exception e){
System.out.println("e :"+e);
}
String iname1="";
if(rst1.next()){
iname1=rst1.getString("index_name");
inname=iname1;
}
 	int c=0; 
 	System.out.println("q2 :"+q1);
 	ee=request.getParameterValues("indexname");
 	
 	while(rst.next() && ee!=null && ee.length!=0){ 
 	fflag=true;
 				 fflag1=true;
  		childindexname=iname1+"."+rst.getString("level_name")+"."+rst.getString("class_name");                 
  		chname[c]=new String(childindexname);
  		 System.out.println("While accepting  childindexname:"+rst.getString("industry_classification_code"));
  		param=ee[i];
  		classid[i]=rst.getString("class_id");
 		 try{
 		 System.out.print("1");
 		 System.out.println("param :"+ee[i]);
 				 i++;
 				 ibasevalue=new Float(param).floatValue();
 				 
 				 System.out.println("c :"+c++);
 				 if(ibasevalue<=0){
 				 fflag=true;
 				 fflag1=true;
 					 errors=errors+"<LI> Base Value For Index "+childindexname+" is invalid</LI>";
  				}
  
  		}catch(Exception e){
  		 fflag=true;
  		 fflag1=true;
  			errors=errors+"<LI> Base Value For Index "+childindexname+" is invalid</LI>";
 		 }
 		 if(!errors.trim().equals("")){
 				 printerrors="<H3><FONT color=\"red\">Validation Error</FONT></H3>You must correct the following error(s) before proceeding:<UL>";
 				 printerrors=printerrors+errors;
 		 }                   
	}	        	  		
}
%>


<% 
//Logger logger =Logger.getLogger("GetBaseValues.jsp");
//logger.debug(" f1 in jsp page by static way is :"+Connect.resourceurl);
PropertyConfigurator.configure(Connect.resourceurl+"resources/log4j.properties");
logger.info("indexid :  "+indexid);
System.out.print("a");
try{
Object form=request.getAttribute("form");
NewIndexForm form1 = (NewIndexForm)form;
Object applycode=request.getAttribute("applyCode");
}catch(Exception e){
logger.info("error in getting form attributes from request"+e);
}
System.out.print("b");	
		Connection con = null;
				Connect c = null;
				
				button=null;
				String oldIndexId;
				String exchangeCode=null;
				String stockid = null;
		org.jfree.chart.demo.servlet.FieldSort fsort=new org.jfree.chart.demo.servlet.FieldSort();
%>
    <jsp:useBean id="stockCollection" scope="session" class="app.StockDetailsCollection"/> 
    <jsp:useBean id="corporateact" scope="session" class="app.Corporate"/>     
         
         <% 
        //come from corporate_diary
			
				
			try{
			//	c = new Connect();
				c=ConnectInit.getConnect();
				con = (Connection) application.getAttribute("DataBase.Connection"); 
				if(con == null)
				 {
				 	con = c.getConnection();
				 	application.setAttribute("DataBase.Connection",con);
				 }	     	   
			}catch(Exception e){} %>
				        <table border="0" width="100%" cellspacing="0" cellpadding="0" height="176">
         <br>
         <% 
         logger.debug("ccc"+printerrors +" fflag:"+fflag);
         if(fflag1){
         if(printerrors!=null && !printerrors.trim().equals("") && printerrors.length()>1){
         System.out.println("d");
         %>
         <%= printerrors %>         <%
         }else {
          System.out.println("e");
         CallableStatement cs;
        logger.debug("con :"+con);
         cs=con.prepareCall("{ call public.define_and_compose_single_child_index(?, ?, ?) }");
         Hashtable indexesnotCreated=new Hashtable();
         for(int i=0;i<ee.length;i++){
         try{
         System.out.println("for index "+chname[i]+" values are indexid="+indexid+"  classid["+i+"] :"+classid[i]+"  ee["+i+"] :"+ee[i]);
           cs.setInt(1,new Integer(indexid).intValue());
            cs.setInt(2,new Integer(classid[i]).intValue());
             cs.setFloat(3,new Float(ee[i]).floatValue());
             boolean f=cs.execute(); 
              logger.debug("Child index created well with flag "+f);
              if(f==false){
              indexesnotCreated.put(chname[i],chname[i]);
              } 
               
             }catch(Exception exception){
            logger.debug("unable to create child index "+chname[i] +"due to "+exception);
             }      
         }
         String urlfornextpage = response.encodeURL("/pages/IndexHome.jsp?D1="+indexid+"&B2=Compute&&childcompute=yes");
	    session.setAttribute("indexesnotcreated",indexesnotCreated);
	     %>   <jsp:forward page="<%=urlfornextpage%>"/>  	<% 	   
         }}System.out.println("f");
         %>
         
          <tr>
            <td width="100%" bgcolor="#FFFFFF" height="1" align="center">
              <p><font size="4" face="Arial Black">&nbsp;&nbsp; Child Index Composition</font>
              </td>
          </tr>		   
		   
            
      <form action='<%=response.encodeURL("GetBaseValues.jsp")%>' method="POST">
<!--      <input type="hidden" name="indexName" value="<request.getParameter('indexName')%>")>-->

    	   <br>
           <tr>
		
	
	
            <td width="44%" align="left" bgcolor="#D8D8D8" height="23">
              <p><font face="Arial" size="2">Parent INDEX
              Name: </font><font face="Arial" size="2" color="red"><%= inname %></font></p>
			



<%  
Statement st=con.createStatement();
ResultSet rst=st.executeQuery("select index_name,base_value from index_master where index_id='"+indexid+"'");
String iname="";
String basevalue="";
if(rst.next()){
iname=rst.getString("index_name");
basevalue=rst.getString("base_value");
request.setAttribute("iname",iname);
}
rst.close();

%>
			
		</tr>

          <tr>
            <td width="100%" bgcolor="#D8D8D8" height="99">
              &nbsp;

              <table border="1" width="71%" bordercolorlight="#000000" cellspacing="0" cellpadding="0" bordercolordark="#000000">
                <tr>
                  <td width="100%">
                    <table border="1" width="100%" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="5%" align="center" bgcolor="#FFFFFF"><b>Sr. No.</b></td>
                        <td width="23%" align="center" bgcolor="#FFFFFF"><font size="1" face="Arial"><b>Index
                          Name</b></font></td>
                        <td width="5%" align="center" bgcolor="#FFFFFF"><font size="1" face="Arial"><b>Base
                          Value</b></font></td>
                      </tr>
                      <%  
 String query = null;
 query =ConnectInit.queries.getProperty("auto_generate_child_index"); 
  rst=st.executeQuery(query);
  String childindexname="";
  int i=0;
 while(rst.next()){ 
  childindexname=iname+"."+rst.getString("level_name")+"."+rst.getString("class_name");                 
  System.out.println("While putting value is "+rst.getString("industry_classification_code"));
                      %>
                      <tr>
                        <td width="5%" align="right" bgcolor="#FFFFFF">&nbsp;<%= i %></td>
                        <td width="23%" align="left" bgcolor="#FFFFFF">&nbsp;&nbsp;<%= childindexname %></td>
                        <% if(ee!=null && ee[i]!=null && !ee[i].trim().equals("")){ 
                        System.out.println("h");
                        %>
                        <td width="5%" align="center" bgcolor="#FFFFFF"><input type="text" name="indexname"  size="10" align='left' value='<%= ee[i] %>' ></td>
                        <% }else{
                        System.out.println("i");
                         %>
                        <td width="5%" align="center" bgcolor="#FFFFFF"><input type="text" name="indexname"  size="10" align='left' value='<%= basevalue %>' ></td>
                       <% } %>
                      </tr>

  <% i++ ; } %>           
                    </table>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
            <td width="100%" bgcolor="#D8D8D8" height="40">
              <table border="0" width="100%" height="74">
                <tr>
                  <td width="50%" height="70"><input type="submit" value="Save and Continue" name="B3" ></td>
                  <td width="50%" height="70">&nbsp;</td>
                </tr>
              </table>
 </td>
          </tr>
         
        
        <tr>
       </form>
			
		<form enctype = "multipartform">
		  </tr>        
        </table>
        </form>
        <p align="right">&nbsp;
      </td>
    </tr>
</tbody>
</table>
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="2%"></td>
    <td width="98%"></td>
  </tr>
</table>
<script language="javascript">

function test4()
 { 
 	document.forms[0].B3.value="Submit";	
 	
  	return true;
 } 

</script>


</html>