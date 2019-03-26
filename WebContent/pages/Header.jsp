<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %><html>
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title></title>
<html:base/>
</head>

<meta name="Microsoft Theme" content="none"> 
	<!--"../Script/codethatcalendarstd.js"-->
	<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script> 

<body topmargin="0" leftmargin="0">

<table border="0" cellspacing="0" cellpadding="0" height="100">
  <tr>
    <td width="100%" height="80"><img border="0" src="images/head2.jpg" width="780" height="78"></td>
  </tr>
  <tr>
    <td width="100%" height="42" valign="top">
      <p align="right"><a href="/Income/pages/NewIndexDefine.jsp" onmouseout="MM_showHideLayers('tvlayer','','hide')" onmouseover="MM_showHideLayers('tvlayer','','show')" height="32" width="32">
      <img border="0" name="tv" src="images/productmenu_tv.gif" width="143" height="16"></a> <a href="NewIndexDefine.jsp" onmouseout="MM_showHideLayers('audiolayer','','hide')" onmouseover="MM_showHideLayers('audiolayer','','show')">
      <img border="0" name="audio" src="images/productmenu_audio.gif" width="143" height="16"></a> <a href="NewIndexDefine.jsp" onmouseout="MM_showHideLayers('computerlayer','','hide')" onmouseover="MM_showHideLayers('computerlayer','','show')">
      <img border="0" name="computer" src="images/productmenu_computer.gif" width="143" height="16"></a>&nbsp;&nbsp;&nbsp;&nbsp;
      <a href="NewIndexDefine.jsp" onmouseout="MM_showHideLayers('householdlayer','','hide')" onmouseover="MM_showHideLayers('householdlayer','','show')">
      <img border="0" name="household" src="images/productmenu_household.gif" width="143" height="16"></a></td>
  </tr>
</table>
<html:errors/>
<table border="0" cellPadding="0" cellSpacing="0" height="40" width="784">
  <tbody>
    <tr>
      <td width="3" background="file:///E:/iit/images/gray.jpg" height="148" valign="top">
        <table border="0" width="778" cellspacing="0" cellpadding="0">
          <tr>
            <td width="1" valign="top">
              <table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
            <td width="100%"><!--<iframe name="cwindow" style="border:1px solid white" width=150 height=365 src="/Income/pages/Tree.jsp"></iframe>--></td>
                </tr>
                <tr>
                  <td width="100%"></td>
                </tr>
              </table>
            </td>
      <td bgColor="#FFFFFF" vAlign="top" width="762" height="148"><script language="JavaScript">

      </script>
      <script language="JavaScript">
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

//-->

      </script>
<script language="JavaScript">
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

</script>
        <div id="tvlayer" style="HEIGHT: 50px; LEFT: 143px; POSITION: absolute; TOP: 81px; VISIBILITY: hidden; WIDTH: 88px; Z-INDEX: 1">
          <a href="http://www.harrierincome.com/#" onmouseout="MM_showHideLayers('tvlayer','','hide')" onmouseover="MM_showHideLayers('tvlayer','','show')"><span style="left: 45; position: absolute; top: 40">
          <img border="0" src="images/layer_tv.gif" useMap="#Map" width="107" height="100"></span></a>
          <map name="Map">
          <area coords="13, 21, 61, 34" href="/Income/pages/stockdetails.htm" shape="RECT">
          <area coords="8, 39, 83, 53" href="/Income/pages/NewIndexDefine.jsp" shape="RECT">
          <area coords="6, 75, 82, 90" href="/Income/pages/DisplayIndexes.jsp" shape="RECT">
          <area href="http://" onmouseout="MM_showHideLayers('tvlayer','','hide')" onmouseover="MM_showHideLayers('tvlayer','','show')" shape="default">
          <area href="http://" onmouseout="MM_showHideLayers('tvlayer','','hide')" onmouseover="MM_showHideLayers('tvlayer','','show')" coords="0, 0, 10000, 10000" shape="rect"></map>
        </div>
        <div id="audiolayer" style="HEIGHT: 50px; LEFT: 143px; POSITION: absolute; TOP: 97px; VISIBILITY: hidden; WIDTH: 88px; Z-INDEX: 1">
          <a href="http://www.harrierincome.com/#" onmouseout="MM_showHideLayers('audiolayer','','hide')" onmouseover="MM_showHideLayers('audiolayer','','show')"><span style="left: 189; position: absolute; top: 25">
          <map name="Map2">
          <area coords="9, 115, 63, 124" href="http://www.iitmsindia.com" shape="RECT">
          <area href="/Income/pages/tableofstock.htm" shape="rect" coords="14, 61, 101, 77">
          <area href="/Income/pages/diary.htm" shape="rect" coords="13, 80, 98, 98">
          <area href="/Income/pages/newusers.jsp" shape="rect" coords="11, 9, 88, 25">
          <area href="/Income/pages/IndexHome.jsp" shape="rect" coords="12, 29, 102, 43">
          </map><img border="0" src="images/layer_audio.gif" useMap="#Map2" width="107" height="100"></span><p></a>&nbsp;&nbsp;
        </div>
        <div id="computerlayer" style="HEIGHT: 77px; LEFT: 143px; POSITION: absolute; TOP: 112px; VISIBILITY: hidden; WIDTH: 88px; Z-INDEX: 1">
          <a href="http://www.harrierincome.com/#" onmouseout="MM_showHideLayers('computerlayer','','hide')" onmouseover="MM_showHideLayers('computerlayer','','show')"><span style="left: 348; position: absolute; top: 12">
          <img rectangle=" (9,53) (68,64) http://www.harrierincome.com" border="0" src="images/layer_computer.gif" usemap="#Map3" width="107" height="311"></span>
          <map name="Map3">
          <area coords="3, 18, 104, 306" href="/Income/pages/NCorporateAction.jsp" shape="RECT">
          <area href="http://" onmouseout="MM_showHideLayers('computerlayer','','hide')" onmouseover="MM_showHideLayers('computerlayer','','show')" shape="default">
          <area href="http://" onmouseout="MM_showHideLayers('computerlayer','','hide')" onmouseover="MM_showHideLayers('computerlayer','','show')" coords="0, 0, 10000, 10000" shape="rect"></map></a>
        </div>
        <div id="householdlayer" style="height: 50; left: 143; position: absolute; top: 128; visibility: hidden; width: 88; z-index: 1">
          <a href="http://www.harrierincome.com/#" onmouseout="MM_showHideLayers('householdlayer','','hide')" onmouseover="MM_showHideLayers('householdlayer','','show')"><span style="position: absolute; left: 506; top: -4"><span style="position: absolute; left: 2; top: -119"><map name="Map4">
          <area coords="12, 21, 100, 53" href="/Income/pages/indwiseweight.jsp" shape="RECT">
          <area coords="12, 180, 100, 214" href="/Income/pages/variindasend.htm" shape="RECT">
		  <area coords="8, 163, 96, 181" href="/Income/pages/volatselect.htm" shape="RECT">
          <area coords="9, 145, 97, 161" href="/Income/pages/coloratuion.htm" shape="RECT">
          
          <area href="/Income/pages/IndexComposition.jsp" onmouseout="MM_showHideLayers('householdlayer','','hide')" onmouseover="MM_showHideLayers('householdlayer','','show')" shape="default">
          <area href="/Income/pages/movingindexvalue.jsp" onmouseout="MM_showHideLayers('householdlayer','','hide')" onmouseover="MM_showHideLayers('householdlayer','','show')" coords="0, 0, 10000, 10000" shape="rect"></map></span>
          <area href="/Income/pages/IndexComposition.jsp" shape="rect" coords="12, 219, 105, 234">
          <area href="/Income/pages/movingindexvalue.jsp" shape="rect" coords="11, 238, 105, 253">
         
          <img border="0" src="images/layer_household.gif" useMap="#Map4" width="107" height="288">
          </span>
          <p></a>
       </div>
