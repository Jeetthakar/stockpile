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
<title>Untitled Document</title>
<style type="text/css">
<!--
body {
	background-image: url(../../images/page-bg.jpg);
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
.steptext {	font-family: Tahoma;
	font-size: 11px;
	color: #FF0000;
}
.style2 {font-family: Tahoma; font-size: 11px; color: #003063; font-weight: bold; }
.style2 {color: #003333}
-->
</style></head>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Untitled Document</title>
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
.steptext {	font-family: Tahoma;
	font-size: 11px;
	color: #FF0000;
}
.style2 {font-family: Tahoma; font-size: 11px; color: #003063; font-weight: bold; }
.style2 {color: #003333}
-->
</style></head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><!--<img src="../images/stockpile_banner.jpg" width="1000" height="41">--></td>
  </tr>
  <tr>
    <td align="center" valign="top"><table width="800" border="0" cellspacing="0" cellpadding="0" height="100%" align="center">
      <tr>
        <td height="100%" align="center" valign="top" style="background-image:url(../images/bg1.jpg); background-repeat:repeat-x;"><table width="731" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td background="../images/top-bg.jpg"><img src="../images/createac-lbl.jpg" width="310" height="39" /></td>
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
                                      <td valign="baseline" background="../images/tag-bg.jpg" style="padding-left:140px;"><img src="../images/step22.jpg" width="368" height="23" border="0" usemap="#MapMap" /></td>
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
                                      <td bgcolor="#DFE3EF"><table width="100%" border="0" cellspacing="7" cellpadding="7">
                                          <tr>
                                            <td class="bodytext">Thanks, You are almost done. To complete your registration process, please confirm your email address.</td>
                                          </tr>
                                          <tr>
                                            <td class="style2">How to confirm your email address?</td>
                                          </tr>
                                          <tr>
                                            <td><table width="100%" border="0" cellspacing="5" cellpadding="5">
                                                <tr>
                                                  <td><table width="100%" border="0" cellspacing="5" cellpadding="5">
                                                      <tr>
                                                        <td width="41"><img src="../images/mail1.jpg" width="41" height="43" /></td>
                                                        <td rowspan="2"><table width="100%" border="0" cellspacing="5" cellpadding="0">
                                                            <tr>
                                                              <td class="steptext">Step 1: Goto your email</td>
                                                            </tr>
                                                            <tr>
                                                              <td class="bodytext">We sent an email to you at <%=session.getAttribute("emailid") %></td>
                                                            </tr>
                                                        </table></td>
                                                      </tr>
                                                      <tr>
                                                        <td></td>
                                                      </tr>
                                                  </table></td>
                                                </tr>
                                                <tr>
                                                  <td><table width="100%" border="0" cellspacing="5" cellpadding="5">
                                                      <tr>
                                                        <td width="41"><img src="../images/mail2.jpg" width="41" height="43" /></td>
                                                        <td rowspan="2"><table width="100%" border="0" cellspacing="5" cellpadding="0">
                                                            <tr>
                                                              <td class="steptext">Step 2: Click on the Link</td>
                                                            </tr>
                                                            
                                                        </table></td>
                                                      </tr>
                                                      <tr>
                                                        <td></td>
                                                      </tr>
                                                  </table></td>
                                                </tr>
                                                <tr>
                                                  <td>
																													<table width="100%" border="0" cellspacing="5" cellpadding="5">
																														<tr>
																															<td width="41">
																																<img src="../images/mail3.jpg" width="41" height="43" />
																															</td>
																															<td rowspan="2">
																																<table width="100%" border="0" cellspacing="5" cellpadding="0">
																																	<tr>
																																		<td class="steptext">
																																			Step 3: Enter your Userid and Password
																																		</td>
																																	</tr>
																																	<tr>
																																		<td class="bodytext">
																																			You will need your Userid and Password everytime you use the system.
																																		</td>
																																	</tr>																														
																																	
																																	<tr>
																																	</tr>
																																	<tr>
																																		
																																		<td class="steptext" >
																																		
																																		<!--
																																		<a href="http://www.yahoo.com" target="_blank" onclick="javascript:window.close();" >Go to Your Mail Box Now!</a>
																																		 	-->
																																		 	
																																		 <a href='<%="http://"+(String)session.getAttribute("usermail")%>' onclick="javascript:window.close();" target="_blank"  >Go to Your Mail Box Now!
																																	</a>	
																																		</td>
																																		
																																		
																																	</tr>
																																	
																																</table>
																															</td>
																														</tr>
																														<tr>
																															<td></td>
																														</tr>
																													</table>
																													<P>
																													
																													</P>
																													<P>
																													</P>
																													<P>
																													</P>
																												</td>
                                                </tr>
                                            </table></td>
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
        <area shape="rect" coords="126,3,220,20" href="reg_form.html" alt="next page">
      </map></td>
  </tr>
</table>
<map name="Map">
  <area shape="rect" coords="126,3,220,20" href="reg_form.html" alt="next page">
</map></body>
</html>
