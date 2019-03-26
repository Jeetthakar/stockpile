<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
if((!request.isRequestedSessionIdValid())){
	response.sendRedirect("userlogintemp.jsp");
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>step 4</title>
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
.borderContent {	border-bottom:1px solid #072A6F;
	border-left:0px;
	border-right:0px;
	border-top:0px ;
	font-family: Tahoma;
	font-size: 11px;
	color: #084583;
}
.titles1 {border-top:1px solid #072A6F;
	border-bottom:1px solid #072A6F;
	border-left:0px;
	border-right:0px;
	border-top:1px ;
	font-family: Tahoma;
	font-size: 11px;
	color: #8F213A;
	background-color:#6AAAF1;
	font-weight:bold;
}
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
                    <td background="../images/top-bg.jpg"><img src="../images/features-lbl.jpg" width="310" height="39" /></td>
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
                                      <td valign="baseline" background="../images/tag-bg.jpg" style="padding-left:140px;"><img src="../images/step4.jpg" width="368" height="23" border="0" usemap="#MapMap" /></td>
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
                                      <td align="center" valign="top" bgcolor="#DFE3EF"><table width="97%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                          <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                              <tr>
                                                <td class="bodytext">You are confirmed <%=session.getAttribute("user_name") %>  This is your Username.</td>
                                                <td align="right" class="redtext">Registered Users Continue to login.. &nbsp;&nbsp;&nbsp;</td>
                                                <td><img src="../images/bullet1-blue.jpg" width="7" height="11" /></td>
                                                <td align="right"><html:link page="/index.jsp" ><img src="../images/continue.jpg" width="66" height="20" border="0" /></html:link ></td>
                                              </tr>
                                          </table></td>
                                        </tr>
                                        <tr>
                                          <td><hr color="#0B3081" /></td>
                                        </tr>
                                        <tr>
                                          <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                              <tr>
                                                <td align="center" valign="top"><table width="85%" border="0" cellspacing="0" cellpadding="0">
                                                    <tr class="titles1">
                                                      <td class="titles1">S.No.</td>
                                                      <td class="titles1">Feature</td>
                                                      <td align="center" class="titles1">Free User </td>
                                                      <td align="center" class="titles1">Subscribed User </td>
                                                    </tr>
                                                    <tr class="borderContent">
                                                      <td class="borderContent">1.</td>
                                                      <td class="borderContent">Create Index</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                      <td class="borderContent">&nbsp;</td>
                                                    </tr>
                                                    <tr class="borderContent">
                                                      <td class="borderContent">2.</td>
                                                      <td class="borderContent">Add/delete stocks from index</td>
                                                      <td align="center" class="borderContent">&nbsp;</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr>
                                                    <tr class="borderContent">
                                                      <td class="borderContent">3.</td>
                                                      <td class="borderContent">Change in IWF</td>
                                                      <td align="center" class="borderContent">&nbsp;</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr>
                                                    <tr class="borderContent">
                                                      <td class="borderContent">4.</td>
                                                      <td class="borderContent">Back dated index</td>
                                                      <td align="center" class="borderContent">&nbsp;</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr>
                                                    <tr class="borderContent">
                                                      <td class="borderContent">5.</td>
                                                      <td class="borderContent">Index backtracking</td>
                                                      <td align="center" class="borderContent">&nbsp;</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr>
                                                    <tr class="borderContent">
                                                      <td class="borderContent">6.</td>
                                                      <td class="borderContent">Compliance criteria</td>
                                                      <td align="center" class="borderContent">&nbsp;</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr>
                                                    <tr class="borderContent">
                                                      <td class="borderContent">7.</td>
                                                      <td class="borderContent">Child indices</td>
                                                      <td align="center" class="borderContent">&nbsp;</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr>
                                                    <tr class="borderContent">
                                                      <td class="borderContent">8.</td>
                                                      <td class="borderContent">Sector indices</td>
                                                      <td align="center" class="borderContent">&nbsp;</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr class="borderContent">
                                                    <tr class="borderContent">
                                                      <td class="borderContent">9.</td>
                                                      <td class="borderContent">Index Composition</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr>
                                                    <tr class="borderContent">
                                                      <td class="borderContent">10.</td>
                                                      <td class="borderContent">Index Comparison</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr>
                                                    <tr class="borderContent">
                                                      <td class="borderContent">11.</td>
                                                      <td class="borderContent">Index Movement</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr>
                                                    <tr class="borderContent">
                                                      <td class="borderContent">12.</td>
                                                      <td class="borderContent">Index List</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr>
                                                    <tr class="borderContent">
                                                      <td class="borderContent">13.</td>
                                                      <td class="borderContent">Index Performance</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr>
                                                    <tr class="borderContent">
                                                      <td class="borderContent">14.</td>
                                                      <td class="borderContent">Portfolio Report</td>
                                                      <td align="center" class="borderContent">&nbsp;</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr>
                                                    <tr class="borderContent">
                                                      <td class="borderContent">15.</td>
                                                      <td class="borderContent">Latest Index Divisor</td>
                                                      <td align="center" class="borderContent">&nbsp;</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr>
                                                    <tr class="borderContent">
                                                      <td class="borderContent">16.</td>
                                                      <td class="borderContent">Index Returns and Volatility</td>
                                                      <td align="center" class="borderContent">&nbsp;</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr>
                                                   
                                                    <tr class="borderContent">
                                                      <td class="borderContent">17.</td>
                                                      <td class="borderContent">Index Correlation</td>
                                                      <td align="center" class="borderContent">&nbsp;</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr>
                                                    <tr class="borderContent">
                                                      <td class="borderContent">18.</td>
                                                      <td class="borderContent">Corporate Action Report</td>
                                                      <td align="center" class="borderContent">&nbsp;</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr>
                                                    <tr class="borderContent">
                                                      <td class="borderContent">19.</td>
                                                      <td class="borderContent">Industry weightage in Index</td>
                                                      <td align="center" class="borderContent">&nbsp;</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr>
                                                    <tr class="borderContent">
                                                      <td class="borderContent">20.</td>
                                                      <td class="borderContent">Stock contribution to change in Index</td>
                                                      <td align="center" class="borderContent">&nbsp;</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr>
                                                    <tr class="borderContent">
                                                      <td class="borderContent">21.</td>
                                                      <td class="borderContent">Index Update</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr>
                                                    <tr class="borderContent">
                                                      <td class="borderContent">22.</td>
                                                      <td class="borderContent">Index Divisor</td>
                                                      <td align="center" class="borderContent">&nbsp;</td>
                                                      <td align="center" class="borderContent"><img src="../images/bullet2-red.jpg" width="5" height="5" /></td>
                                                    </tr>
                                                </table></td>
                                                <td align="right" valign="top">
                                                <html:link page="/SubscriptionList.jsp" ><img src="../images/subscribenow.jpg" width="100" height="21" border="0" /></img> </html:link><span class="bodytextMaroon"> 
                                                </td>
                                              </tr>
                                          </table></td>
                                        </tr>
                                        <tr>
                                          <td>&nbsp;</td>
                                        </tr>
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
        <area shape="rect" coords="317,3,361,18" href="#" alt="next page">
      </map></td>
  </tr>
</table>
<map name="Map">
  <area shape="rect" coords="254,5,298,20" href="confirm.html" alt="next page">
</map></body>
</html>
