
<%@page import="java.sql.ResultSet"%>
<%@page import="com.harrier.initializeation.ConnectInit"%><%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Please Question</title>
</head>
<body >
<form action="pages/yourpass.jsp" ">
 
 <%
 log.info("question.jsp page");
 String que=" ";
 String name=" ";
            String id=request.getParameter("id");
            session.setAttribute("id",id);
           // String temp=request.getParameter("id2");             
           // String id2=temp.substring(3,5)+"-"+temp.substring(0,2)+"-"+temp.substring(6,10);
           String birthDay=request.getParameter("birthDay");
           if((birthDay.equals("1"))||(birthDay.equals("2"))||(birthDay.equals("3"))||(birthDay.equals("4"))||(birthDay.equals("5"))||(birthDay.equals("6"))||(birthDay.equals("7"))||(birthDay.equals("8"))||(birthDay.equals("9")))
		    {
	          birthDay="0"+birthDay.toString();
	        }
           String birthMonth=request.getParameter("birthMonth");
            if((birthMonth.equals("1"))||(birthMonth.equals("2"))||(birthMonth.equals("3"))||(birthMonth.equals("4"))||(birthMonth.equals("5"))||(birthMonth.equals("6"))||(birthMonth.equals("7"))||(birthMonth.equals("8"))||(birthMonth.equals("9")))
			{
		      birthMonth="0"+birthMonth.toString();
		     }
           String birthYear=request.getParameter("birthYear");
           String id2=birthDay+"-"+birthMonth+"-"+birthYear;
            session.setAttribute("id2",id2);
            String zip=request.getParameter("zip");
            session.setAttribute("zip",zip);
            String country=request.getParameter("country");
            session.setAttribute("country",country);
          //  new app.Connect().getConnection();
           // java.sql.ResultSet rst=new app.Connect().forgotpass("forgot_password",id,country,id2,zip);
             ResultSet rst = ConnectInit.getConnect().forgotpass("forgot_password",id,country,id2,zip);
                if(rst.next()){
                    que=rst.getString("security_question"); 
                     session.setAttribute("que",que);  
                    name=rst.getString("first_name");      
            }
%>
             



<table border="1" width="100%" height="399">
  <tr>
     <td width="100%" height="99">&nbsp;</td>
  </tr>
  <tr>
    <td width="100%" height="278">
      
        <p align="left"><font color="#0000FF">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <u> Hi <font color="red"><%= name %></font>! Try to answer this question</p></font></u>
        <p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        Your Question is :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= que %></p>
        <p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        Answer&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="text" name="ans" size="20"></p>
      <p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
      <input type="submit" value="Submit" name="B1">
      <input type="reset" value="Reset" name="B2"></p>
      </form>
      <p>&nbsp;</td>
  </tr>
</table>

</body>

</html>
