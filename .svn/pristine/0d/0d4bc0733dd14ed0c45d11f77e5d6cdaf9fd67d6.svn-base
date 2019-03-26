<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import = "java.text.SimpleDateFormat,subscription.form.CancelForm" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "app.*" %><%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
 CancelForm c=(CancelForm)session.getAttribute("tempbeen");
 String from=null;
 String to=null;
 if(c!=null)
{
 from=c.getFromdate();
 to=c.getTodate();
}
%>
<jsp:useBean id="regiform" scope="session" class="subscription.form.CancelForm"/>
<jsp:useBean id="regiform1" scope="session" class="subscription.form.CancelForm"/>

<jsp:setProperty name="regiform" property="fromdate" value="<%=from%>" />
<jsp:setProperty name="regiform" property="todate" value="<%=to%>" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 
 <head>
 <link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />   
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>step 3</title>
<style type="text/css">
<!--
body {
	background-image: url(../images/page-bg.jpg);
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.style1 {
	color: #990000;
	font-family: Tahoma;
	font-size: 11px;
}
.labels {
	font-family: Tahoma;
	font-size: 11px;
	color: #084583;
}
.redstar {color: #FF0000}
.bodytext {	font-family: Tahoma;
	font-size: 11px;
	color: #003063;
}
.redtext {	font-family: Tahoma;
	font-size: 14px;
	color: #FF0000;
}
.redtext1 {	font-family: Tahoma;
	font-size: 11px;
	color: #FF0000;
}

.bodytextMaroon {	font-family: Tahoma;
	font-size: 11px;
	color: #800404;
}

.style2 {font-family: Tahoma; font-size: 11px; color: #003063; font-weight: bold; }
-->
</style></head>
<meta name="Microsoft Theme" content="none"/>
 <script language="javascript" src="./codethatcalendarstd.js"></script>
 <script language="javascript" src="box_ex.js"></script>
 <script language="javascript">
 	var c2 = new CodeThatCalendar(caldef2);
 </script> 

 <script> 
 <%String method=null;%>
function cancel(checkbox)
{
 document.forms[0].method.value="cancel";
 return true;
}
function go(checkbox)
{
 document.forms[0].method.value='Go';
 return true;
}

function submitform()
{

document.form2.submit();

}

function chkForm(checkbox)
{
   var max = checkbox.length;
  var chk=0;
for (var i=0; i<max-1; i++) {
  
  if(checkbox[i].checked )
    {  
   
    chk=chk+1 ;
    
    }
    
  }
  
  
  if(chk==0)
{
    alert("Please Select Scheme");
 return false;
 
}
else
{
return true;
}
}













 </script> 
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td></td>
  </tr>
  <tr>
    <td align="center" valign="top"><table width="800" border="0" cellspacing="0" cellpadding="0" height="100%" align="center">
      <tr>
        <td height="100%" align="center" valign="top" style="background-image:url(../images/bg1.jpg); background-repeat:repeat-x;"><table width="731" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td background="../images/top-bg.jpg"><img src="../images/cancelsub.jpg" /></td>
                  </tr>
                  <tr>
                    <td height="100%"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="5"><img src="../images/tl.jpg" width="5" height="5" /></td>
                          <td background="../images/hbar-table2.jpg"><img src="../images/hbar-table2.jpg" width="40" height="5" /></td>
                          <td width="5"><img src="../images/tr.jpg" width="5" height="5" /></td>
                        </tr>
                        <tr>
                          <td background="../images/vbar-table2.jpg" ><img src="../images/vbar-table2.jpg" width="5" height="10" /></td>
                          <td bgcolor="#83A8DB" height="100%"><table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
                              <tr>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                      <td width="42"><img src="../images/tag.jpg" width="42" height="42" /></td>
                                      <td valign="baseline" background="../images/tag-bg.jpg" style="padding-left:140px;"></td>
                                    </tr>
                                </table></td>
                              </tr>
                              <tr>
                                <td style="padding-top:15px;" height="100%"><table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
                                    <tr>
                                      <td width="7" height="7"><img src="../images/tl-inner.jpg" width="7" height="7" /></td>
                                      <td background="../images/hbar-inner.jpg"><img src="../images/hbar-inner.jpg" width="10" height="7" /></td>
                                      <td width="7"><img src="../images/tr-inner.jpg" width="7" height="7" /></td>
                                    </tr>
                                    <tr>
                                    
                                      <td background="../images/vbar-inner.jpg" height="350"><img src="../images/vbar-inner.jpg" width="7" height="10" /></td>
                                      <td align="left" valign="top" bgcolor="#DFE3EF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                          
                                          <html:form  action="/cancelsubscription">
                                          
                                           <tr>
                                            <td class="style2" align="center" >Check Out User Cancelation Here!</td>
                                           <td align="right"> <a href="javascript: void(0)" onclick="window.open('https://www.paypal.com/cgi-bin/webscr?cmd=_home-merchant&nav=2','windowname1'); 
                                            return false;"><span class='redtext1'>Check Out From Pay Pal!</span> </a>
                                            </td>
                                          </tr>
                                        <tr>
                                            <td class="style2" align="center">&nbsp;</td>
                                          </tr>
                                          <tr>
                                           <td align="center">
                                           
                                            <table width="100%" border="0"  cellspacing="0" cellpadding="0">
                                             <tr>
                                             <td align="center" class="style2">
                                            From :
                                            <html:text  property="fromdate" name="regiform" size="8" />
                                            <input onclick="c2.popup('fromdate');"  type="button" value="..."   />
                                            &nbsp;&nbsp;To  :
                                            <html:text  property="todate" name="regiform" size="8" />
                                            <input onclick="c2.popup('todate');"  type="button" value="..."   />
                                            &nbsp;&nbsp;<html:submit property="method" value="Go"   />
                                             </td>
                                          </tr>
                                          
                                          </table>
                                         
                                          </td>
                                          </tr>
                                           </html:form>
                                          <tr>
                                          <td >&nbsp; </td> 
                                          </tr>
                                            <html:form action="/cancelsubscription?method=cancel">
                                          <tr>
                                          <td align="center">
                                           
		                                            <table width="70%" border="0"  cellspacing="0" cellpadding="0">
		                                             <tr>
		                                                  <td bgcolor="#A2B2D8" class="bodytextMaroon" style="padding-left:5px;"><b>Select</b></td>
                                                          <td bgcolor="#A2B2D8" class="bodytextMaroon" style="padding-left:5px;"><b>User Name</b></td>
                                                          <td bgcolor="#A2B2D8" class="bodytextMaroon" style="padding-left:5px;"><b>Order Id</b></td>
                                                          <td bgcolor="#A2B2D8" class="bodytextMaroon" style="padding-left:5px;"><b>Date</b> </td>
		                                            </tr>
		                                           
		                                             <bean:define id="temp" name="regiform1" scope="session" property="useraccountinfo"/> 
		                                             <logic:iterate id="temp" property="useraccountinfo"  name="regiform">
		                                             <tr>
		                                             <td>
		                                             <html:multibox name="temp"   property="order_id_list">
                                                    <bean:write name="temp" property="orderid" />
                                                    </html:multibox>
                                                    </td>
		                                             <td bgcolor="#D9DFED" class="bodytextMaroon" >
                                                      <bean:write  name="temp" property="username"   />
		                                             </td>
		                                             <td bgcolor="#D9DFED" class="bodytextMaroon">
		                                               <bean:write  name="temp" property="orderid"   />
		                                               </td>
		                                             <td bgcolor="#D9DFED" class="bodytextMaroon" >
		                                               <bean:write name="temp" property="canceldate"   />
		                                             </td>
		                                             </tr>
		                                        </logic:iterate>
											  <tr>
											  <td>&nbsp;</td>
                                              </tr>
											  
											  
											  
											  
											   <tr>
		                                         <td >
		                                         
		                                        
		                                         <%
		                                         
		                                          if(from!=null)
		                                            {
		                                          out.print("<input type='submit' onclick='return chkForm(this.form)' value='cancel'> </input>");
		                                           }
		                                         %>	
		                                         
		                                      
		                                          </td>
		                                        <td></td>
		                                        <td></td>
		                                        <td></td>
		                                        </tr>
		                                      
		                                       
		                                            </table>
		                                  </td>          
		                                  </tr>
                                            
                                          <tr>
                                         
                                           <td  class="style2"></td>
                                            <td align="right"></td> </tr>
                                          <tr>
                                          
                                           <td colspan="2" align="center" class="bodytext">
                                            
                                           
                                            <%
                                                if(session.getAttribute("userbeen")!=null)
                                                {
                                               if(session.getAttribute("done")!=null)
                                               {
                                            
                                               out.print("<span class='redtext'>");
                                               out.print(session.getAttribute("done"));
                                               out.print("</span>");
                                               }
                                               if(session.getAttribute("datafail")!=null)
                                               {
                                               
                                               out.print("<span class='redtext'>");
                                               out.print(session.getAttribute("datafail"));
                                               out.print("</span>");
                                               }
                                               if(session.getAttribute("notexist")!=null)
                                               {
                                               
                                               out.print("<span class='redtext'>");
                                               out.print(session.getAttribute("notexist"));
                                               out.print("</span>");
                                               }
                                               
                                              session.setAttribute("done",null);
                                              session.setAttribute("datafail",null);
                                              session.setAttribute("userbeen",null);
                                              session.setAttribute("notexist",null);
											}
                                      %>
                                           </td>
                                          </tr>
                                          <tr>          
                                            <td align="center"></td>
                                             <td>&nbsp;</td>
                                          </tr>
                                          <tr>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                          </tr>
                                          <tr>
                                            <td>&nbsp;</td>
                                            
                                          
                                          </tr>
                                          <tr>
                                          <td></td>
                                          </tr>
                                          
                                          
                                          </html:form>
		                                           
                                           
                                      </table></td>
                                      
                                      
                                      <td background="../images/vbar-inner-2.jpg"><img src="../images/vbar-inner-2.jpg" width="7" height="10" /></td>
                                    </tr>
                                    <tr>
                                      <td><img src="../images/bl-inner.jpg" width="7" height="7" /></td>
                                      <td background="../images/hbar-inner-2.jpg"><img src="../images/hbar-inner-2.jpg" width="10" height="7" /></td>
                                      <td><img src="../images/br-inner.jpg" width="7" height="7" /></td>
                                    </tr>
                                </table></td>
                              </tr>
                          </table></td>
                          <td background="../images/vbar-table2.jpg"><img src="../images/vbar-table2.jpg" width="5" height="10" /></td>
                        </tr>
                        <tr>
                          <td><img src="../images/bl.jpg" width="5" height="5" /></td>
                          <td background="../images/hbar-table2.jpg"><img src="../images/hbar-table2.jpg" width="40" height="5" /></td>
                          <td><img src="../images/br.jpg" width="5" height="5" /></td>
                        </tr>
                    </table></td>
                  </tr>
                  
              </table></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
            </tr>
          </table>
            <!-- Body here --></td>
      </tr>
      <tr>
        <td  height="100%" bgcolor="#003063"><img src="../images/footer-bg.jpg">
            <!-- Footer here --></td>
      </tr>
      <tr>
        <td  height="100%" align="center"><span class="style1">Copyright 2008, All Rights Reserved </span></td>
      </tr>
    </table>
      <map name="MapMap">
        <area shape="rect" coords="254,5,298,20" href="done.html" alt="next page">
      </map></td>
  </tr>
</table>
<map name="Map">
  <area shape="rect" coords="254,5,298,20" href="confirm.html" alt="next page">
</map></body>
</html>
 