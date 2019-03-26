<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="java.util.*,subscription.form.subscriptionForm" pageEncoding="UTF-8"%>
<jsp:useBean id="tempform" scope="session" class="subscription.form.subscriptionForm"/>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <style type="text/css">
<!--
.style1 {	color: #990000;
	font-family: Tahoma;
	font-size: 11px;
}
.defaultText {	font-family: Tahoma;
	font-size: 11px;
	color: #000000;
}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(images/page-bg.jpg);
}
.bodytexthome {
	font-family: Tahoma;
	font-size: 11px;
	color: #1A0602;
}
.outerTableFeatureHdg{
	border-bottom:1px solid #314573;
	border-left:1px solid #314573;
	border-right:1px solid #314573;
	border-top:1px solid #314573;
	color:#000000;
	font-family: Tahoma;
	font-size: 11px;
}
.style2 {color: #072A6F}
.featureHDG {
	color: #072A6F;
	font-size: 11px;
	font-family: Tahoma;
}
.footertext {
	color: #990000;
	font-family: Tahoma;
	font-size: 11px;
}

A.registerLink:link {
	color: #760618; 	font-size: 11px;	font-family: Tahoma; text-decoration:none; font-weight: bold;
}
A.registerLink:visited {
	color: #760618; 	font-size: 11px;	font-family: Tahoma; text-decoration:none; font-weight: bold;
}
A.registerLink:hover {
	color: #760618; 	font-size: 11px;	font-family: Tahoma; text-decoration:none; font-weight: bold;
}

.labels {
	font-family: Tahoma;
	font-size: 11px;
	color: #084583;
}
.redstar {color: #FF0000}
.redtext {font-family: Tahoma;
	font-size: 11px;
	color: #FF0000;
}




-->
</style>
    
    <title>subscription</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <script language="javascript">
    
  </script> 
  </head>
  
  <body onload="window.close();">
  
  
  
  
<H4></H4><TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
  <TR>
    <TD></TD>
  </TR>
  <TR>
    <TD align="center" valign="top"><TABLE width="800" border="0" cellspacing="0" cellpadding="0" height="100%" align="center">
      <TR>
        <TD height="100%" align="center" valign="top" style="background-image:url(../images/bg1.jpg); background-repeat:repeat-x;"><!-- Body here --><TABLE width="731" border="0" cellspacing="0" cellpadding="0">
            <TR>
              <TD><TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                  <TR>
                    <TD background="../images/top-bg.jpg"></TD>
                  </TR>
                  <TR>
                    <TD height="100%"><TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                        <TR>
                          <TD width="5"><IMG src="../images/tl.jpg" width="5" height="5"></TD>
                          <TD background="../images/hbar-table2.jpg"><IMG src="../images/hbar-table2.jpg" width="40" height="5"></TD>
                          <TD width="5"><IMG src="../images/tr.jpg" width="5" height="5"></TD>
                        </TR>
                        <TR>
                          <TD background="../images/vbar-table2.jpg"><IMG src="../images/vbar-table2.jpg" width="5" height="10"></TD>
                          <TD bgcolor="#83A8DB" height="100%"><TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
                              <TR>
                                <TD><TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <TR>
                                      <TD width="42"><IMG src="../images/tag.jpg" width="42" height="42"></TD>
                                      <TD valign="baseline" background="../images/tag-bg.jpg" style="padding-left:140px;"></TD>
                                    </TR>
                                </TABLE></TD>
                              </TR>
                              <TR>
                                <TD style="padding-top:15px;" height="100%"><TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
                                    <TR>
                                      <TD width="7" height="7"><IMG src="../images/tl-inner.jpg" width="7" height="7"></TD>
                                      <TD background="../images/hbar-inner.jpg"><IMG src="../images/hbar-inner.jpg" width="10" height="7"></TD>
                                      <TD width="7"><IMG src="../images/tr-inner.jpg" width="7" height="7"></TD>
                                    </TR>
                                    <TR>
                                      <TD background="../images/vbar-inner.jpg" height="350"><IMG src="../images/vbar-inner.jpg" width="7" height="10"></TD>
                                      <TD align="center" valign="top" bgcolor="#DFE3EF"><TABLE width="97%" border="0" cellspacing="0" cellpadding="0">
                                        <TR>
                                          <TD><TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                                              <TR>
                                                <TD class="bodytext">Thank you for your subscription order
												</TD>
                                                <TD align="right" class="redtext">   </TD>
                                                <TD></TD>
                                                <TD align="right"><A href="#"></A></TD>
                                              </TR>
                                          </TABLE></TD>
                                        </TR>
                                        <TR>
                                          <TD><HR color="#0B3081">    
                                          
                                          
                                          
                                        </TR>
                                        <TR>
                                          <TD><TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                                              <TR>
                                              
                                              </TR>
                                          </TABLE> </TD>
                                        </TR>
                                        <TR>
                                          <TD>&nbsp;</TD>
                                        </TR>
                                      </TABLE></TD>
                                      <TD background="../images/vbar-inner-2.jpg"><IMG src="../images/vbar-inner-2.jpg" width="7" height="10"></TD>
                                    </TR>
                                    <TR>
                                      <TD><IMG src="../images/bl-inner.jpg" width="7" height="7"></TD>
                                      <TD background="../images/hbar-inner-2.jpg"><IMG src="../images/hbar-inner-2.jpg" width="10" height="7"></TD>
                                      <TD><IMG src="../images/br-inner.jpg" width="7" height="7"></TD>
                                    </TR>
                                </TABLE></TD>
                              </TR>
                          </TABLE></TD>
                          <TD background="../images/vbar-table2.jpg"><IMG src="../images/vbar-table2.jpg" width="5" height="10"></TD>
                        </TR>
                        <TR>
                          <TD><IMG src="../images/bl.jpg" width="5" height="5"></TD>
                          <TD background="../images/hbar-table2.jpg"><IMG src="../images/hbar-table2.jpg" width="40" height="5"></TD>
                          <TD><IMG src="../images/br.jpg" width="5" height="5"></TD>
                        </TR>
                    </TABLE></TD>
                  </TR>
              </TABLE></TD>
            </TR>
            <TR>
              <TD>&nbsp;</TD>
            </TR>
          </TABLE>
            </TD>
      </TR>
      <TR>
        <TD height="100%" bgcolor="#003063"><!-- Footer here --><IMG src="../images/footer-bg.jpg">
            </TD>
      </TR>
      <TR>
        <TD height="100%" align="center"><SPAN class="style1">Copyright 2008, All Rights Reserved </SPAN></TD>
      </TR>
    </TABLE>
      <MAP name="MapMap">
        <AREA shape="rect" coords="317,3,361,18" href="#" alt="next page">
      </MAP></TD>
  </TR>
</TABLE>
<map name="Map">
  <area shape="rect" coords="254,5,298,20" href="confirm.html" alt="next page">
</map></body>
</html>













