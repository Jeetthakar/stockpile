<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:useBean id="regiform" scope="session" class="subscription.form.subscribeUserForm"/>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
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
	font-size: 11px;
	color: #FF0000;
}
.style2 {font-family: Tahoma; font-size: 11px; color: #003063; font-weight: bold; }
-->
</style></head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><img src="../images/stockpile_banner.jpg" width="1000" height="41"></td>
  </tr>
  <tr>
    <td align="center" valign="top"><table width="800" border="0" cellspacing="0" cellpadding="0" height="100%" align="center">
      <tr>
        <td height="100%" align="center" valign="top" style="background-image:url(../images/bg1.jpg); background-repeat:repeat-x;"><table width="731" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td background="../images/top-bg.jpg"><img src="../images/confirmemail-lbl.jpg" width="310" height="39" /></td>
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
                                      <td valign="baseline" background="../images/tag-bg.jpg" style="padding-left:140px;"><img src="../images/step33.jpg" width="368" height="23" border="0" usemap="#MapMap" /></td>
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
                                      <td align="center" valign="top" bgcolor="#DFE3EF"><table width="55%" border="0" cellspacing="7" cellpadding="7">
                                          <tr>
                                            <td colspan="2" class="bodytext">Please enter your password to confirm your email address.</td>
                                          </tr>
                                          <html:form  action="/confirmuser">
                                          <tr>
                                            <td class="style2">Email / User ID: </td>
                                            <td>
                                            <html:text property="userid"  style="border: 1px solid rgb(8, 69, 131)" ></html:text>
                                            </td>
                                          </tr>
                                          <tr>
                                            <td width="33%" class="style2">Password:</td>
                                            <td width="67%">
                                            <html:password property="password"  size="22" style="border: 1px solid rgb(8, 69, 131)"></html:password> 
                                            </td>
                                          </tr>
                                          
                                          <tr>
                                           <td colspan="2" class="bodytext">
                                            <%
                                                if(session.getAttribute("userbeen")!=null)
                                                {
                                               if(session.getAttribute("exist")!=null)
                                               {
                                               out.print("<span class='redtext'>Error:</span>");
                                               out.print("<span class='labels'>");
                                               out.print(session.getAttribute("exist"));
                                               out.print("</span>");
                                               }
                                               if(session.getAttribute("datafail")!=null)
                                               {
                                               out.print("<span class='redtext'>Error:</span>");
                                               out.print("<span class='labels'>");
                                               out.print(session.getAttribute("datafail"));
                                               out.print("</span>");
                                               }
                                               if(session.getAttribute("noexist")!=null)
                                               {
                                               out.print("<span class='redtext'>Error:</span>");
                                               out.print("<span class='labels'>");
                                               out.print(session.getAttribute("noexist"));
                                               out.print("</span>");
                                               }
                                               
                                               session.setAttribute("userbeen",null);
                                               session.setAttribute("exist",null);
                                               session.setAttribute("datafail",null);
                                              session.setAttribute("noexist",null);
											}
                                      %>
                                           </td>
                                          </tr>
                                          <tr>          
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                          </tr>
                                          <tr>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                          </tr>
                                          <tr>
                                            <td>&nbsp;</td>
                                            <td align="right" style="padding-left:150px;">
                                            <html:image src="../images/confirm.jpg" ></html:image><P></P><P></P><P></P>
                                           </td>
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
                          <td background="../images/vbar-table2.jpg"><img src="images/vbar-table2.jpg" width="5" height="10" /></td>
                        </tr>
                        <tr>
                          <td><img src="images/bl.jpg" width="5" height="5" /></td>
                          <td background="images/hbar-table2.jpg"><img src="images/hbar-table2.jpg" width="40" height="5" /></td>
                          <td><img src="images/br.jpg" width="5" height="5" /></td>
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
 