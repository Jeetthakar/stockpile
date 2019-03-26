<%@ page  import="app.*"%>
<%@ page  import="java.util.*"%>
<%@page import="com.harrier.initializeation.ConnectInit"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
			LogonForm form =(LogonForm)session.getAttribute("user");	
			AcessControl asc=ConnectInit.getAcessControl();
		//	AcessControl asc=new AcessControl();			
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Menus Home</title>
<script type="text/javascript" language="JavaScript1.2" src="stm31.js"></script><style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style></head>
<form id="frm">
	<%
	Vector uname=new Vector();
	Vector Mname=new Vector();
	uname.clear();	
	Mname.clear();
	
	
	if(form!=null)
	{
		
		uname=asc.getUseActivitiesId(form);	//get all the user activity codes for which user has access.
		Mname=asc.getMenuId(form);	//get all the menu header codes for which user has access.
	}
	
	for(int i=0;i<uname.size();i++){
			String rid=(String)uname.get(i);
			log.info("rid is "+rid);
	%>
	 <input type="hidden" name="roleid" value="<%= rid %>"> <!-- passing all the activity code in jsp page through hidden parameter-->
	 <% } 
	 for(int i=0;i<Mname.size();i++){
			String rid1=(String)Mname.get(i);
			
	%>
	 <input type="hidden" name="menuid" value="<%= rid1 %>"> <!-- passing all the activity code in jsp page through hidden parameter-->
	 <% } %> 
	 <input type="hidden" name="chk" value="0">
	</form>   
<body>

		<script type="text/javascript" language="JavaScript1.2">
		<!--
		
		stm_bm(["menu7237",430,"","blank.gif",0,"","",0,3,250,0,1000,1,0,0,"","",0],this);
		stm_bp("p0",[0,4,0,0,3,4,0,0,100,"",-2,"",-2,90,0,0,"#000000","#C1D2E7","",3,0,0,"#000000"]);
		stm_ai("p0i0",[0,"Admin","","",-1,-1,0,"","_self","","","","",0,0,0,"","",0,0,0,0,1,"#E5EAF5",0,"#314573",0,"","",3,3,0,0,"#FFFFF7","#000000","#000000","#FFFFFF","8pt 'Tahoma','Arial'","8pt 'Tahoma','Arial'",0,0]);
		stm_bp("p1",[1,4,0,0,2,3,0,7,100,"progid:DXImageTransform.Microsoft.Fade(overlap=.5,enabled=0,Duration=0.43)",-2,"",-2,67,2,3,"#999999","#FFFFFF","",3,1,1,"#ACA899"]);
		stm_aix("p1i0","p0i0",[0,"Access Control","","",-1,-1,0,"","_self","","","","",0,0,0,"arrow_r.gif","arrow_w.gif",7,7,0,0,1,"#FFFFFF",1,"#003399"]);
		stm_bpx("p2","p1",[1,2,-3,0,2,3,0,0]);
		stm_aix("p2i0","p0i0",[0,"New User","","",-1,-1,0,"http://www.google.com","_self","","Google","","",0,0,0,"","",0,0,0,0,1,"#FFFFFF",0,"#7CA3D7"]);
		stm_aix("p2i1","p2i0",[0,"Activities","","",-1,-1,0,"http://www.yahoo.com","_self","","Yahoo"]);
		stm_aix("p2i2","p2i0",[0,"New Roles","","",-1,-1,0,"http://www.rediff.com","_self","","Rediff"]);
		stm_aix("p2i3","p2i0",[0,"Role Activities","","",-1,-1,0,"#","_self","","dddddd"]);
		stm_ep();
		stm_aix("p1i1","p1i0",[0,"Import File","","",-1,-1,0,"#","_self","","cccccc","","",0,0,0,"","",0,0,0,0,1,"#FFFFFF",0]);
		stm_aix("p1i2","p1i1",[0,"System Configuration","","",-1,-1,0,"#","_self","","bbbbbb"]);
		stm_aix("p1i3","p1i1",[0,"Index OHLC","","",-1,-1,0,"#","_self","","azsdc"]);
		stm_ep();
		stm_aix("p0i1","p0i0",[0,"Masters"]);
		stm_bpx("p3","p1",[]);
		stm_aix("p3i0","p1i1",[0,"Add Masters","","",-1,-1,0,"","_self","","","","",0,0,0,"arrow_r.gif","arrow_w.gif",7,7]);
		stm_bpx("p4","p2",[]);
		stm_aix("p4i0","p2i0",[0,"Stock Exchange","","",-1,-1,0,"#","_self","","eggrggrrgg"]);
		stm_aix("p4i1","p2i0",[0,"Country","","",-1,-1,0,"#","_self","","11331123"]);
		stm_aix("p4i2","p2i0",[0,"Identifier Codes","","",-1,-1,0,"#","_self","","22333323"]);
		stm_aix("p4i3","p2i0",[0,"Index Type","","",-1,-1,0,"#","_self","","343442334"]);
		stm_aix("p4i4","p2i0",[0,"Rating Code","","",-1,-1,0,"#","_self","","45454545"]);
		stm_aix("p4i5","p2i0",[0,"Stock Type","","",-1,-1,0,"#","_self","","34545345"]);
		stm_aix("p4i6","p2i0",[0,"Time Zone","","",-1,-1,0,"#","_self","","121242323"]);
		stm_aix("p4i7","p2i0",[0,"New Corporate Action","","",-1,-1,0,"#","_self","","23232323"]);
		stm_aix("p4i8","p4i7",[0,"Exchange Holidays"]);
		stm_aix("p4i9","p2i0",[0,"Currency","","",-1,-1,0,"#","_self","","32423424"]);
		stm_aix("p4i10","p2i0",[0,"Company","","",-1,-1,0,"#","_self","","2121212"]);
		stm_ep();
		stm_aix("p3i1","p1i1",[0,"Compliance Master","","",-1,-1,0,"#","_self","","11121212"]);
		stm_aix("p3i2","p1i1",[0,"Add Stock","","",-1,-1,0,"#","_self","","12121212"]);
		stm_aix("p3i3","p1i1",[0,"Currency","","",-1,-1,0,"#","_self","","111221212"]);
		stm_aix("p3i4","p1i1",[0,"Commodities","","",-1,-1,0,"#","_self","","111111"]);
		stm_aix("p3i5","p1i1",[0,"Bonus/Fixed Income","","",-1,-1,0,"#","_self","","12345"]);
		stm_ep();
		stm_aix("p0i2","p0i0",[0,"Index"]);
		stm_bpx("p5","p2",[1,4,0]);
		stm_aix("p5i0","p1i1",[0,"Add Captured Indices","","",-1,-1,0,"#","_self","","@QQQ]"]);
		stm_aix("p5i1","p1i1",[0,"Compute Index","","",-1,-1,0,"#","_self","","@QQQ"]);
		stm_aix("p5i2","p5i1",[0,"Index Update"]);
		stm_aix("p5i3","p5i1",[0,"Add Index"]);
		stm_aix("p5i4","p5i1",[0,"Index Back Tracking"]);
		stm_ep();
		stm_aix("p0i3","p0i0",[0,"Industry Classification"]);
		stm_bpx("p6","p5",[]);
		stm_aix("p6i0","p1i1",[0,"Classes","","",-1,-1,0,"#","_self","",""]);
		stm_aix("p6i1","p5i1",[0,"Classification Level"]);
		stm_aix("p6i2","p5i1",[0,"Company Classification"]);
		stm_aix("p6i3","p5i1",[0,"Classification Standard"]);
		stm_aix("p6i4","p5i1",[0,"GICS Classification"]);
		stm_ep();
		stm_aix("p0i4","p0i0",[0,"Corporate Actions"]);
		stm_bpx("p7","p1",[]);
		stm_aix("p7i0","p3i0",[0,"Stock Events"]);
		stm_bpx("p8","p1",[1,2,-3]);
		stm_aix("p8i0","p2i0",[0,"Dividend","","",-1,-1,0,"","_self","","","","",0,0,0,"arrow_r.gif","arrow_w.gif",7,7]);
		stm_bpx("p9","p2",[1,2,-2,-3]);
		stm_aix("p9i0","p0i0",[0,"Cash Dividend","","",-1,-1,0,"#","_self","","Japan","","",0,0,0,"","",0,0,0,0,1,"#FFFFFF",0,"#B0BCCD"]);
		stm_aix("p9i1","p9i0",[0,"Stock Dividend/Bonus"]);
		stm_aix("p9i2","p9i0",[0,"Of other company"]);
		stm_ep();
		stm_aix("p8i1","p2i0",[0,"Repurchase of Shares","","",-1,-1,0,"#","_self","","Australlia"]);
		stm_aix("p8i2","p8i1",[0,"Reverse Split"]);
		stm_aix("p8i3","p8i1",[0,"Rights Offering"]);
		stm_aix("p8i4","p8i1",[0,"Share Insurance"]);
		stm_aix("p8i5","p8i1",[0,"Spin Off"]);
		stm_aix("p8i6","p8i1",[0,"Split"]);
		stm_aix("p8i7","p8i1",[0,"Warrant Conversion"]);
		stm_aix("p8i8","p8i1",[0,"ADR Insurance"]);
		stm_ep();
		stm_aix("p7i1","p3i0",[0,"Index Events"]);
		stm_bpx("p10","p2",[]);
		stm_aix("p10i0","p2i0",[0,"Addition of Stock","","",-1,-1,0,"#","_self","","India"]);
		stm_aix("p10i1","p10i0",[0,"Change in IWF"]);
		stm_aix("p10i2","p10i0",[0,"Deletion of Stock"]);
		stm_aix("p10i3","p10i0",[0,"Rebasing"]);
		stm_aix("p10i4","p10i0",[0,"Stock Merger"]);
		stm_aix("p10i5","p10i0",[0,"Change Index Currency"]);
		stm_ep();
		stm_aix("p7i2","p1i1",[0,"Diary","","",-1,-1,0,"#","_self","","India"]);
		stm_ep();
		stm_aix("p0i5","p0i0",[0,"Analytics"]);
		stm_bpx("p11","p1",[]);
		stm_aix("p11i0","p3i0",[0,"Index"]);
		stm_bpx("p12","p8",[]);
		stm_aix("p12i0","p8i0",[0,"Index/Tis Calculator"]);
		stm_bpx("p13","p9",[]);
		stm_aix("p13i0","p9i0",[0,"Index Calculator","","",-1,-1,0,"http://IndexCalculator.com","_self","","Index Calculator"]);
		stm_aix("p13i1","p9i0",[0,"Portfolio Basket","","",-1,-1,0,"portfoliobasket.htm","_self","","portfoliobasket"]);
		stm_ep();
		stm_aix("p12i1","p2i0",[0,"Latest Index Divisor","","",-1,-1,0,"#","_self","","Harriersys"]);
		stm_aix("p12i2","p12i1",[0,"Index Compare"]);
		stm_aix("p12i3","p12i1",[0,"Index Compare OHLC"]);
		stm_aix("p12i4","p12i1",[0,"Index Composition"]);
		stm_aix("p12i5","p12i1",[0,"Index Movement"]);
		stm_aix("p12i6","p12i1",[0,"Index Performance"]);
		stm_aix("p12i7","p12i1",[0,"Index Returns and Volatility"]);
		stm_aix("p12i8","p12i1",[0,"Index Correlation"]);
		stm_aix("p12i9","p12i1",[0,"Index List"]);
		stm_aix("p12i10","p12i1",[0,"Index wise PE, PB"]);
		stm_aix("p12i11","p12i1",[0,"Index Currency"]);
		stm_aix("p12i12","p12i1",[0,"Index Divisor"]);
		stm_ep();
		stm_aix("p11i1","p3i0",[0,"Stock"]);
		stm_bpx("p14","p2",[]);
		stm_aix("p14i0","p12i1",[0,"Capital Change"]);
		stm_aix("p14i1","p12i1",[0,"Stock List"]);
		stm_aix("p14i2","p12i1",[0,"Inactive Stocks"]);
		stm_aix("p14i3","p12i1",[0,"Stock Dividend"]);
		stm_aix("p14i4","p12i1",[0,"Traded Volume"]);
		stm_aix("p14i5","p2i0",[0,"Stock Details between Dates","","",-1,-1,0,"#","_self","","Stock Details between Dates"]);
		stm_ep();
		stm_aix("p11i2","p3i0",[0,"Weightages"]);
		stm_bpx("p15","p2",[]);
		stm_aix("p15i0","p2i0",[0,"Stock Weightage","","",-1,-1,0,"#","_self","","Stock Weightage"]);
		stm_aix("p15i1","p2i0",[0,"Company Weightage","","",-1,-1,0,"#","_self","","Company Weightage"]);
		stm_aix("p15i2","p2i0",[0,"Industry Weightage","","",-1,-1,0,"#","_self","","Industry Weightage"]);
		stm_aix("p15i3","p2i0",[0,"Stock Contribution to Change in business","","",-1,-1,0,"#","_self","","Stock Contribution to Change in business"]);
		stm_ep();
		stm_aix("p11i3","p3i0",[0,"Batch Report"]);
		stm_bpx("p16","p2",[]);
		stm_aix("p16i0","p2i0",[0,"Batch Report Updation","","",-1,-1,0,"#","_self","","Batch Report Updation"]);
		stm_aix("p16i1","p2i0",[0,"Batch Report Execution","","",-1,-1,0,"#","_self","","Batch Report Execution"]);
		stm_ep();
		stm_aix("p11i4","p3i0",[0,"Query Report"]);
		stm_bpx("p17","p2",[]);
		stm_aix("p17i0","p2i0",[0,"Dyna Report1","","",-1,-1,0,"#","_self","",""]);
		stm_aix("p17i1","p2i0",[0,"Dyna Report2","","",-1,-1,0,"#","_self","","alternatetext"]);
		stm_ep();
		stm_aix("p11i5","p3i0",[0,"Non Ajax View"]);
		stm_bpx("p18","p2",[]);
		stm_aix("p18i0","p17i0",[0,"Index Compare"]);
		stm_aix("p18i1","p17i0",[0,"Index Composition"]);
		stm_aix("p18i2","p17i0",[0,"Index Movement"]);
		stm_aix("p18i3","p17i0",[0,"Index Performance"]);
		stm_aix("p18i4","p17i0",[0,"Index Returns and Volatility"]);
		stm_aix("p18i5","p17i0",[0,"Capital Change"]);
		stm_aix("p18i6","p17i0",[0,"Company Weightage"]);
		stm_aix("p18i7","p17i0",[0,"Stock Contribution to Change in Index"]);
		stm_aix("p18i8","p17i0",[0,"Inactive Stocks"]);
		stm_aix("p18i9","p17i0",[0,"Index wise PE, PB"]);
		stm_aix("p18i10","p17i0",[0,"Stock Dividend"]);
		stm_aix("p18i11","p17i0",[0,"Traded Volume"]);
		stm_aix("p18i12","p17i0",[0,"Index Divisor"]);
		stm_aix("p18i13","p17i0",[0,"Stock Details between Dates"]);
		stm_aix("p18i14","p17i0",[0,"Old Index List"]);
		stm_ep();
		stm_aix("p11i6","p6i0",[0,"Portfolio Report"]);
		stm_ep();
		stm_aix("p0i6","p0i0",[0,"Financials"]);
		stm_bpx("p19","p5",[]);
		stm_aix("p19i0","p6i0",[0,"PE Computation"]);
		stm_aix("p19i1","p6i0",[0,"Financial Entry"]);
		stm_ep();
		stm_aix("p0i7","p0i0",[0,"Logout","","",-1,-1,0,"#"]);
		stm_ep();
		stm_em();
		//-->

		</script>


	
	
	
</body>
</html>

