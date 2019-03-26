<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="app.*" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
	LogonForm form = null;
	if(request.isRequestedSessionIdValid())	
		form = (LogonForm)session.getAttribute("user");
	if(form==null ||(!request.isRequestedSessionIdValid()))
		response.sendRedirect("userlogintemp.jsp");
	
%>


<html >
<head>
	<html:base/>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<!--
#foldheader{cursor:pointer;cursor:hand ; font-weight:bold ;
list-style-image:url(fold.gif)}
#foldinglist{list-style-image:url(list.gif)}
//-->

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
if (foldercontent.style.display == "none"){
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
<!--</script>
</head>-->

<!--<body topmargin="0" leftmargin="0">

<table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="99"><img border="0" src="images/head2.jpg" width="780" height="106"></td>
  </tr>
  <tr>
    <td width="100%" height="15">
      <p align="right"><a href="http://www.harrierincome.com/#" onmouseout="MM_showHideLayers('tvlayer','','hide')" onmouseover="MM_showHideLayers('tvlayer','','show')" height="32" width="32"><img border="0" name="tv" src="images/productmenu_tv.gif" width="143" height="16"></a>
      <a href="http://www.harrierincome.com/#" onmouseout="MM_showHideLayers('audiolayer','','hide')" onmouseover="MM_showHideLayers('audiolayer','','show')"><img border="0" name="audio" src="images/productmenu_audio.gif" width="143" height="16"></a><a href="http://www.harrierincome.com/#" onmouseout="MM_showHideLayers('computerlayer','','hide')" onmouseover="MM_showHideLayers('computerlayer','','show')"><img border="0" name="computer" src="images/productmenu_computer.gif" width="143" height="16"></a>&nbsp;&nbsp;&nbsp;&nbsp;
      <a href="http://www.harrierincome.com/#" onmouseout="MM_showHideLayers('householdlayer','','hide')" onmouseover="MM_showHideLayers('householdlayer','','show')"><img border="0" name="household" src="images/productmenu_household.gif" width="143" height="16"></a></td>
  </tr>
</table>
<table border="0" cellPadding="0" cellSpacing="0" height="40" width="781">
  <tbody>
    <tr>
      <td width="3" background="file:///E:/iit/images/gray.jpg" height="148"><img height="3" src="images/spacer.gif" width="3"></td>
      <td bgColor="#FFFFFF" vAlign="top" width="773" height="148"><script language="JavaScript">-->
<!--
function gothere(form) {

	var index=form.dest.selectedIndex;
	
	if (index !=0) {
	var remote=form.dest.options[index].value.substring(0,1);
	var url=form.dest.options[index].value.substring(1);
	
		if (remote == "a")
		window.open(url,"newwindow","toolbar=yes,location=yes,derectories=yes,status=yes,menubar=yes,scrollbar=yes,resizable=yes");
		
		else
		this.location=(url);
	}
}


</script>
        <script language="JavaScript">//-->
<!--
function MM_findObj(n, d) { //v4.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && document.getElementById) x=document.getElementById(n); return x;
}

function MM_showHideLayers() { //v3.0
  var i,p,v,obj,args=MM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) if ((obj=MM_findObj(args[i]))!=null) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v='hide')?'hidden':v; }
    obj.visibility=v; }
}
//-->
<!--</script>
        <div id="tvlayer" style="HEIGHT: 50px; LEFT: 143px; POSITION: absolute; TOP: 81px; VISIBILITY: hidden; WIDTH: 88px; Z-INDEX: 1">
          <a href="http://www.harrierincome.com/#" onmouseout="MM_showHideLayers('tvlayer','','hide')" onmouseover="MM_showHideLayers('tvlayer','','show')"><span style="left: 57; position: absolute; top: 41"><img border="0" src="images/layer_tv.gif" useMap="#Map" width="107" height="100"></span></a>
          <map name="Map">
          <area coords="15, 19, 58, 34" href="stockdetails.htm" shape="RECT">
          <area coords="11, 37, 86, 54" href="indexmasters.htm" shape="RECT">
          <area coords="10, 73, 86, 88" href="indexcompos2.htm" shape="RECT">
          <area href="http://" onmouseout="MM_showHideLayers('tvlayer','','hide')" onmouseover="MM_showHideLayers('tvlayer','','show')" shape="default">
          <area href="http://" onmouseout="MM_showHideLayers('tvlayer','','hide')" onmouseover="MM_showHideLayers('tvlayer','','show')" coords="0, 0, 10000, 10000" shape="rect"></map>
        </div>
        <div id="audiolayer" style="HEIGHT: 50px; LEFT: 143px; POSITION: absolute; TOP: 97px; VISIBILITY: hidden; WIDTH: 88px; Z-INDEX: 1">
          <a href="http://www.harrierincome.com/#" onmouseout="MM_showHideLayers('audiolayer','','hide')" onmouseover="MM_showHideLayers('audiolayer','','show')"><span style="left: 199; position: absolute; top: 25"><img border="0" src="images/layer_audio.gif" useMap="#Map2" width="107" height="100"></span></a>
          <map name="Map2">
          <area coords="9, 115, 63, 124" href="http://www.iitmsindia.com" shape="RECT">
          <area coords="9, 11, 80, 21" href="wedo.htm" shape="RECT">
          <area coords="10, 30, 73, 51" href="product.htm" shape="RECT">
          <area coords="7, 57, 75, 87" href="infometro.htm" shape="RECT"></map>
        </div>
        <div id="computerlayer" style="HEIGHT: 77px; LEFT: 143px; POSITION: absolute; TOP: 112px; VISIBILITY: hidden; WIDTH: 88px; Z-INDEX: 1">
          <a href="http://www.harrierincome.com/#" onmouseout="MM_showHideLayers('computerlayer','','hide')" onmouseover="MM_showHideLayers('computerlayer','','show')"><span style="left: 355; position: absolute; top: 9"><img rectangle=" (9,53) (68,64) http://www.harrierincome.com" border="0" src="images/layer_computer.gif" usemap="#Map3" width="107" height="311"></span>
          </a><a href="http://www.harrierincome.com/#" onmouseout="MM_showHideLayers('computerlayer','','hide')" onmouseover="MM_showHideLayers('computerlayer','','show')"><map name="Map3">
          <area coords="3, 18, 104, 306" href="corporateaction.htm" shape="RECT">
          <area href="http://" onmouseout="MM_showHideLayers('computerlayer','','hide')" onmouseover="MM_showHideLayers('computerlayer','','show')" shape="default">
          <area href="http://" onmouseout="MM_showHideLayers('computerlayer','','hide')" onmouseover="MM_showHideLayers('computerlayer','','show')" coords="0, 0, 10000, 10000" shape="rect"></map></a>
        </div>
        <div id="householdlayer" style="height: 50; left: 143; position: absolute; top: 128; visibility: hidden; width: 88; z-index: 1">
          <a href="http://www.harrierincome.com/#" onmouseout="MM_showHideLayers('householdlayer','','hide')" onmouseover="MM_showHideLayers('householdlayer','','show')"><span style="position: absolute; left: 2; top: -119"><map name="Map4">
          <area coords="4, 33, 104, 54" href="indexcompos3.htm" shape="RECT">
          <area href="http://www.harrierincome.com/#" onmouseout="MM_showHideLayers('householdlayer','','hide')" onmouseover="MM_showHideLayers('householdlayer','','show')" shape="default">
          <area href="http://www.harrierincome.com/#" onmouseout="MM_showHideLayers('householdlayer','','hide')" onmouseover="MM_showHideLayers('householdlayer','','show')" coords="0, 0, 10000, 10000" shape="rect"></map><span style="position: absolute; left: 497; top: 112"><img border="0" src="images/layer_household.gif" useMap="#Map4" width="107" height="146">
          </span></span></a>
        </div>
        <p>&nbsp;</p>
        <table border="0" width="100%" cellspacing="0" cellpadding="0" height="436">
          <tr>
            <td width="17%" bgcolor="#E6E6E6" height="30"><font face="Arial" size="1"><b>Market
              / Exchange</b></font></td>
            <td width="13%" bgcolor="#E6E6E6" height="30"><select size="1" name="D1">
                <option>Infosys</option>
              </select></td>
            <td width="44%" bgcolor="#E6E6E6" height="30">
              <p align="center"><font size="2" face="Arial"><b>Corporate Action</b></font></td>
            <td width="9%" bgcolor="#E6E6E6" height="30">
              <p align="right"><font size="1" face="Arial"><b>Stock&nbsp;&nbsp;&nbsp;
              </b></font></td>
            <td width="17%" bgcolor="#E6E6E6" height="30"><select size="1" name="D1">
                <option>Infosys</option>
              </select></td>
          </tr>
          <tr>
            <td width="30%" colspan="2" bgcolor="#E6E6E6" height="52" align="center">
              <p align="center"><b><font face="Arial" size="1">-------AFFECTED
              INDICES-----</font></b></td>
            <td width="44%" bgcolor="#E6E6E6" height="52" align="center">
              <p align="center"><b><font face="Arial" size="1">---------STOCK
              DETAILS--------</font></b></td>
            <td width="26%" colspan="2" bgcolor="#E6E6E6" height="52" align="center">
              <p align="center"><b><font face="Arial" size="1">----CORPORATE
              ACTION---</font></b></td>
          </tr>
          <tr>
            <td width="30%" colspan="2" bgcolor="#E6E6E6" valign="top" height="354">
              <p align="center"><font size="2" face="Arial"><b>INDEX</b></font>
              <table border="0" width="91%" bordercolorlight="#000000" cellspacing="0" cellpadding="0" bordercolordark="#000000">
                <tr>
                  <td width="100%">

<p align="center"><iframe name="cwindow" style="border:1px dotted purple" width=150 height=250 src="doc.htm"></iframe>

                  </td>
                </tr>

              </table>-->

<!-- FORM START HERE -->

              
              <table border="0" width="100%" cellspacing="0" cellpadding="2">
                <tr>
                  <td width="100%" colspan="2" bgcolor="#BFBFBF">
                    <p align="center"><b><font face="Arial" size="1">AFFECTED
                    INDEX PROPERTIES </font></b></td>
                </tr>
                <tr>
                  <td width="50%" bgcolor="#E6E6E6"><font size="1" face="Arial">Last
                    Value</font></td>
                  <td width="50%" align="center" bgcolor="#E6E6E6"><html:text property="" name="f_lastValue" size="5"/> </td>
                </tr>
                <tr>
                  <td width="50%" bgcolor="#E6E6E6"><font size="1" face="Arial">Old
                    MKt Cap</font></td>
                  <td width="50%" align="center" bgcolor="#E6E6E6"><html:text property="" name="f_oldMarketCap" size="5"/></td>
                </tr>
                <tr>
                  <td width="50%" bgcolor="#E6E6E6"><font size="1" face="Arial">Old
                    Divisor</font></td>
                  <td width="50%" align="center" bgcolor="#E6E6E6"><html:text property="" type="text" name="d_oldDivisor" size="5"/></td>
                </tr>
                <tr>
                  <td width="50%" bgcolor="#E6E6E6"><font size="1" face="Arial">New
                    MKt Cap</font></td>
                  <td width="50%" align="center" bgcolor="#E6E6E6"><html:text property="" name="f_newMarketCap" size="5"/></td>
                </tr>
                <tr>
                  <td width="50%" bgcolor="#E6E6E6"><font size="1" face="Arial">New
                    Divisor</font></td>
                  <td width="50%" align="center" bgcolor="#E6E6E6"><html:text property="" name="d_newDivisor" size="5"/></td>
                </tr>
              </table>
            </td>
            
            <td width="44%" bgcolor="#E6E6E6" valign="top" height="354">
              <table border="0" width="101%" cellspacing="0" cellpadding="2">
                <tr>
                  <td width="100%" bgcolor="#BFBFBF" colspan="4" align="center"><b><font face="Arial" size="1">Codes</font></b></td>
                </tr>
                <tr>
                  <td width="34%" bgcolor="#E6E6E6"><font size="1" face="Arial">&nbsp;SEDOL</font></td>
                  <td width="17%" bgcolor="#E6E6E6"><html:text property="" name="sedol" size="5"/></td>
                  <td width="26%" bgcolor="#E6E6E6"><font size="1" face="Arial">&nbsp;ISIN</font></td>
                  <td width="24%" bgcolor="#E6E6E6"><html:text property="" name="isin" size="5"/></td>
                </tr>
                <tr>
                  <td width="34%" bgcolor="#E6E6E6"><font size="1" face="Arial">&nbsp;RIC</font></td>
                  <td width="17%" bgcolor="#E6E6E6"><html:text property="" name="ric" size="5"/></td>
                  <td width="26%" bgcolor="#E6E6E6"><font size="1" face="Arial">&nbsp;CUSIP</font></td>
                  <td width="24%" bgcolor="#E6E6E6"><html:text property="" name="cusip" size="5"/></td>
                </tr>
                <tr>
                  <td width="34%" bgcolor="#E6E6E6"><font size="1" face="Arial">&nbsp;Exchange
                    Code</font></td>
                  <td width="17%" bgcolor="#E6E6E6"><html:text property="" name="s_exchangeCode" size="5"/></td>
                  <td width="26%" bgcolor="#E6E6E6"><font size="1" face="Arial">&nbsp;TICKER</font></td>
                  <td width="24%" bgcolor="#E6E6E6"><html:text property="" name="ticker" size="5"/></td>
                </tr>
                <tr>
                  <td width="34%"><font color="#D8D8D8">.</font></td>
                  <td width="17%"></td>
                  <td width="26%"></td>
                  <td width="24%"></td>
                </tr>
                <tr>
                  <td width="34%" bgcolor="#E6E6E6"><font size="1" face="Arial">&nbsp;Stock-Id</font></td>
                  <td width="17%" bgcolor="#E6E6E6"><html:text property="" name="l_stockId" size="5"/></td>
                  <td width="26%" bgcolor="#E6E6E6"><font size="1" face="Arial">&nbsp;Listing
                    Date</font></td>
                  <td width="24%" bgcolor="#E6E6E6"><html:text property="" name="d_listingDate" size="5"/></td>
                </tr>
                <tr>
                  <td width="34%" bgcolor="#E6E6E6"><font size="1" face="Arial">&nbsp;Stock
                    Name</font></td>
                  <td width="17%" bgcolor="#E6E6E6"><html:text property="" name="s_stockName" size="5"/></td>
                  <td width="26%" bgcolor="#E6E6E6"><font size="1" face="Arial">&nbsp;IWF
                    Of Stock</font></td>
                  <td width="24%" bgcolor="#E6E6E6"><html:text property="" name="f_iwfOfStock" size="5"/></td>
                </tr>
                <tr>
                  <td width="34%" bgcolor="#E6E6E6"><font size="1" face="Arial">&nbsp;Company</font></td>
                  <td width="17%" bgcolor="#E6E6E6"><html:text property="" name="s_company" size="5"/></td>
                  <td width="26%" bgcolor="#E6E6E6"><font size="1" face="Arial">&nbsp;Issued
                    Shares</font></td>
                  <td width="24%" bgcolor="#E6E6E6"><html:text property="" name="i_issuedShares" size="5"/></td>
                </tr>
                <tr>
                  <td width="34%" bgcolor="#E6E6E6"><font size="1" face="Arial">&nbsp;Stock
                    Exchange</font></td>
                  <td width="17%" bgcolor="#E6E6E6"><html:text property="" name="s_stockExchange" size="5"/></td>
                  <td width="26%" bgcolor="#E6E6E6"><font size="1" face="Arial">&nbsp;Country</font></td>
                  <td width="24%" bgcolor="#E6E6E6"><html:text property="" name="s_country" size="5"/></td>
                </tr>
                <tr>
                  <td width="34%" bgcolor="#E6E6E6"><font size="1" face="Arial">&nbsp;Face
                    Value</font></td>
                  <td width="17%" bgcolor="#E6E6E6"><html:text property="" name="d_faceValue" size="5"/></td>
                  <td width="26%" bgcolor="#E6E6E6"><font size="1" face="Arial">&nbsp;Rating
                    Code</font></td>
                  <td width="24%" bgcolor="#E6E6E6"><html:text property="" name="i_ratingCode" size="5"/></td>
                </tr>
                <tr>
                  <td width="34%" bgcolor="#E6E6E6"><font size="1" face="Arial">&nbsp;Sub-Industry</font></td>
                  <td width="17%" bgcolor="#E6E6E6"><input type="text" name="T20" size="5"/></td>
                  <td width="26%" bgcolor="#E6E6E6"><font size="1" face="Arial">&nbsp;ADR
                    Ratio</font></td>
                  <td width="24%" bgcolor="#E6E6E6"><input type="text" name="T21" size="5"/></td>
                </tr>
                <tr>
                  <td width="50%" colspan="2" bgcolor="#E6E6E6"><font size="1" face="Arial"><input type="radio" value="V1" checked name="R1">Growth
                    Stocks</font></td>
                  <td width="50%" colspan="2" bgcolor="#E6E6E6"><font size="1" face="Arial"><input type="radio" value="V1" checked name="R1">Value
                    Stocks</font></td>
                </tr>
              </table>
            </td>
            <td width="26%" colspan="2" bgcolor="#E6E6E6" valign="top" height="354">
              <table border="0" width="100%" cellspacing="0" cellpadding="2" height="157">
                <tr>
                  <td width="50%" height="30" bgcolor="#D6D6D6"><font size="1" face="Arial">&nbsp;Corporate
                    Action</font></td>
                  <td width="50%" height="30" align="center" bgcolor="#D6D6D6"><font size="1" face="Arial"><select size="1" name="D1">
                      <option>Action</option>
                    </select></font></td>
                </tr>
                <tr>
                  <td width="50%" height="25" bgcolor="#D6D6D6"><font size="1" face="Arial">&nbsp;Amount</font></td>
                  <td width="50%" height="25" align="center" bgcolor="#D6D6D6"><input type="text" name="T7" size="7"/></td>
                </tr>
                <tr>
                  <td width="50%" height="25" bgcolor="#D6D6D6"><font size="1" face="Arial">&nbsp;Percentage</font></td>
                  <td width="50%" height="25" align="center" bgcolor="#D6D6D6"><input type="text" name="T8" size="7"/></td>
                </tr>
                <tr>
                  <td width="50%" height="25" bgcolor="#D6D6D6"><font size="1" face="Arial">&nbsp;Ratio</font></td>
                  <td width="50%" height="25" align="center" bgcolor="#D6D6D6"><input type="text" name="T9" size="7"/></td>
                </tr>
                <tr>
                  <td width="50%" height="27" align="center" bgcolor="#D6D6D6"><input type="submit" value="Apply" name="B1"></td>
                  <td width="50%" height="27" align="center" bgcolor="#D6D6D6"><input type="submit" value="Commit" name="B2"></td>
                </tr>
                <tr>
                  <td width="50%" height="25" align="center" bgcolor="#D6D6D6"><input type="submit" value="Dairy" name="B3"></td>
                  <td width="50%" height="25" align="center" bgcolor="#D6D6D6"><input type="reset" value="Cancle" name="B4"></td>
                </tr>
                <tr>
                  <td width="100%" height="25" colspan="2">&nbsp;
                    <table border="0" width="100%" cellspacing="0" cellpadding="2">
                      <tr>
                        <td width="100%" colspan="2" bgcolor="#BFBFBF">
                          <p align="center"><b><font face="Arial" size="1">CORPORATE
                          ACTION EFFECT</font></b></td>
                      </tr>
                      <tr>
                        <td width="50%" bgcolor="#E6E6E6"><font size="1" face="Arial">Shares
                          Added / Reduced </font></td>
                        <td width="50%" bgcolor="#E6E6E6"><font size="1" face="Arial"><input type="text" name="T27" size="7"/></font></td>
                      </tr>
                      <tr>
                        <td width="50%" bgcolor="#E6E6E6"><font size="1" face="Arial">New
                          Outstanding Shares</font></td>
                        <td width="50%" bgcolor="#E6E6E6"><font size="1" face="Arial"><input type="text" name="T28" size="7"/></font></td>
                      </tr>
                      <tr>
                        <td width="50%" bgcolor="#E6E6E6"><font size="1" face="Arial">Adjusted
                          Price</font></td>
                        <td width="50%" bgcolor="#E6E6E6"><font size="1" face="Arial"><input type="text" name="T29" size="7"/></font></td>
                      </tr>
                    </table>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
	
        <p align="right">&nbsp;
      </td>
    </tr>
  </tbody>
</table>


</body>

</html>
