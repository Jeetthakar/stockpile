
<%@page import="com.harrier.initializeation.ConnectInit"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--*** page created by Ganesh Ikhar ,02-04-07 -->
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="app.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import=" java.sql.Statement" %>
<%@ page import=" java.io.*"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="org.apache.struts.util.LabelValueBean" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>

<!-- following are the default  menu or submenu items to be seen by every user ,-->
<!-- no matter  what is his/her role -->

<!--Activity code     :  Main menu/ Sub menu Name -->
<!--   M1             :  1  Masters      -->
<!--   M2             :  2  Admin        -->
<!--   M3             :  3  Index  -->
<!--   M4             :  4  Industry_Classification -->
<!--   M5             :  5  Corporate_Action  -->
<!--   M6             :  6  Corporate_Action/Stock_Events    -->
<!--(removed now)M7   :  7  Corporate_Action/Stock_Events/Dividend       -->
<!--   M8             :  8  Corporate_Action/Index_Events -->
<!--   M9             :  9  Analytics -->
<!--   M10            :  10 Index ; It is also in Analytics/Index -->
<!--   M11            :  11 Analytics/Index/Tis_Calculator -->
<!--   M12            :  12 Analytics/Stocks -->
<!--   M13            :  13 Analytics/Weightages-->
<!--   M17            :  14 Financial_Analysis -->
<!--   M15            :  15 Analytics/Batch_Report -->
<!--   M16            :  16 Analytics/Query_Report -->
<!--   M14            :  17 Logout(This menu is not implemented as Main menu in new design ;instead present as a separate link on the page)-->
<!--   M18            :  18 Admin/Add_Masters -->
<!--   M19            :  19 Masters/Access_Control-->
<!--   M20            :  20 Ajax View  It is divided into two parts Ajax View1 And Ajax View 2 having Activity Code M20-->

<%
					
			LogonForm form =(LogonForm)session.getAttribute("user");
		//	AcessControl asc=new AcessControl();
			AcessControl asc= ConnectInit.getAcessControl();
			 if((form==null)||(session==null)){
			 //log.info("going to simple jsp");
		    %>
		   <jsp:forward page="simpleJsp1.jsp"></jsp:forward>
		    <% }
				

%>
<%
		Vector activityVector=new Vector();// use to get All the activities for the user 
				
		activityVector.clear();            //clear the previous activities for the user
		
		
		
		String userName1=new String();
		String userName2=new String();
		if(form!=null){
		userName1=form.getUsername();
		//userName1=form.getFname();
		//userName2 contains UserName to be diplayed on the Welcome page.
		//As usename1 contain special symbols at the end, hench that symbol is drop down 
		//userName2=userName1.substring(0,(userName1.length()-1));
	   userName2=form.getFname();   
	    activityVector=asc.getActivitiesForUser2(form);//get list of activities
	   
	    }
		
		
		
		



						
%>
<html>
<head>
<title>New UI Enhancement Menu</title>
<link rel="stylesheet" type="text/css" href="common.css">
<link rel="stylesheet" type="text/css" href="top.css">
<link rel="stylesheet" type="text/css" href="text.css">
<style>
.text {font-family:arial; font-size:14px; color:0000CC; vertical-align:top;}
</style>
</head>
<body alink="blue" vlink="blue" link="blue" NOWRAP noWrap="true" NORESIZE resize="none" onLoad="initialise()">

 <style>
 .black_12_menu {font-family: Arial;font-size:12px; color:#0A3C6D; text-decoration: none; font-weight:bold;}
 .black_12_menu:hover {font-family: Arial;font-size:12px; color:#0A3C6D; text-decoration: underline; font-weight:bold;}
 .moderator_space { width:222px; margin:0;padding-left:660;}
 </style>
 <script>
 
 var m_selected = "1";  // default value set 
 
 var menuOldSelected=1;  //Use for previously selected Main menu
 var menuNewSelected=1;  //Use for new selected Main menu, initialise it to one
 
 var hr = "12";
 var min = "44";
 </script>
 
 <!-- Welcome userName and Logout strip -->
 <!-- bean message for internationalization -->
<!-- <bean:message key="newUsers.Signup"/> -->
<%
 String actCode=new String();  // use to get Activity Code from Activity table for User
 String actName=new String();  // use to get Activity Name from Activity table for User
 
 //use this HashMap to save activity_code and activity_name base on activity_code. 
 //So that we can retrive activity code to check which <div> should be visible to user.
 HashMap actCodeName=new HashMap(); 
  actCodeName.clear();
 
 for(int i=0;i<activityVector.size();i++){
		//actCode=((Mname.get(i)).toString()).trim();
		actCode=((activityVector.get(i)).toString()).trim();
		i++;
		//actName=((Mname.get(i)).toString()).trim();
		actName=((activityVector).toString());
		log.info("In Activity Code is===== :  "+ actCode +" And Activity Name====  "+actName);
		//actCodeName.add(new LabelValueBean(actCode,actName));
		actCodeName.put(actCode,actName);
	}
   // log.info("************************end====***************");
  //  log.info("************************Size of Vector**************"+uname.size());
	//log.info("************************Size of HashMap**************"+actCodeName.size());
	log.info("====================="+actCodeName.get("3"));
 
 %>



<TABLE   width="100%"  >
<tr >
<td align="center" NOWRAP noWrap="true" width="20%"  ><img src="hs.jpg" ></td>
<td noWrap="true" width="65%"    align="left"><font color="#B22222" size="3"><b><bean:message key="User.Welcome"/> <%= userName2%> !</b> </font> </td>

<td NOWRAP noWrap="true" width="15%" align="right" ><img  src="Logout11.JPEG"> <font color="#B22222" size="2"><b><a href="../userlogintemp.jsp?action=logout" Target="frmMain"><bean:message key="User.Logout"/></a></b></font>&nbsp&nbsp</td>  

</tr>
<!-- ******************************************* All menu items are put it into following td ********** --> 
<tr><td  colspan="3">
<!-- Main menu start Here -->
<div class="nav_bg6" align="center" NOWRAP noWrap="true"  NORESIZE resize="none">
	<div id="MainMenu" style="display:block;color:black;	 width:776px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
		
		<!-- Activity code=M1 for Admin-->
		<div id="imgML1" class="floatL nav_curve2"></div>
		<div id="mainMenu1" onmouseover="show(1)" onmouseout="mouse_out(this)"  class="floatL subNav"><bean:message key="Menu.Admin"/><span class="top_tdB1 subNav"></span></div>
		<div id="imgMR1" class="floatL nav_curve2"></div>
		<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_bg6.jpg" ></div>
		
		<!-- Activity code=M2 for Masters-->
		<div id="imgML2" class="floatL nav_curve2"></div>
		<div id="mainMenu2" onmouseover="show(2)" onmouseout="mouse_out(this)"  class="floatL subNav"><bean:message key="Menu.Master"/><span class="top_tdB1 subNav"></span></div>
		<div id="imgMR2" class="floatL nav_curve2"></div>
		<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_bg6.jpg" ></div>
		
		<!-- Activity code=M3 for Index-->
		<div id="imgML3" class="floatL nav_curve2"></div>
		<div id="mainMenu3" onmouseover="show(3)" onmouseout="mouse_out(this)"  class="floatL subNav"><bean:message key="Menu.Index"/><span class="top_tdB1 subNav"></span></div>
		<div id="imgMR3" class="floatL nav_curve2"></div>
		<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_bg6.jpg" ></div>
		
		<!-- Activity code=M4 for Industry Classification-->
		<div id="imgML4" class="floatL nav_curve2"></div>
		<div id="mainMenu4" onmouseover="show(4)" onmouseout="mouse_out(this)"  class="floatL subNav"><bean:message key="Menu.Industry_Classification"/><span class="top_tdB1 subNav"></span></div>
		<div id="imgMR4" class="floatL nav_curve2"></div>
		<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_bg6.jpg" ></div>
		
		<!-- Activity code=M5 for Corporate Action-->
		<div id="imgML5" class="floatL nav_curve2"></div>
		<div id="mainMenu5" onmouseover="show(5)" onmouseout="mouse_out(this)" class="floatL subNav"><bean:message key="Menu.Corporate_Action"/><span class="top_tdB1 subNav"></span></div>
		<div id="imgMR5" class="floatL nav_curve2"></div>
		<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_bg6.jpg" ></div>
		
		<!-- Activity code=M9 for Analytics-->
		<div id="imgML6" class="floatL nav_curve2"></div>
		<div id="mainMenu6" onmouseover="show(6)" onmouseout="mouse_out(this)" class="floatL subNav"><bean:message key="Menu.Analytics"/></div>
		<div id="imgMR6" class="floatL nav_curve2"></div>
		<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_bg6.jpg" ></div>
		
		<!-- Activity code=M17 for Financial Analysis-->
		<!-- --> 
		<div id="imgML7" class="floatL nav_curve2"></div>
		<div id="mainMenu7"  onmouseover="show(7)" onmouseout="mouse_out(this)"  class="floatL subNav"><bean:message key="Menu.Financial_Analysis"/></div>
		<div id="imgMR7" class="floatL nav_curve2"></div>
		
		</div>
</div>



<!-- use class="nav_bg4" for pink color strip -->
<!-- use class="floatL nav_curve1" for pink color vertical strip-->
<!--USE img src="top_nav_divider1.jpg"  for pink color divider-->
 <div class="nav_bg4"  NOWRAP noWrap="true">
 
 <!-- Admin's submenu starts here -->
  
 <div id="show1" align="center" style="display:none;color=black; width:776px; height:28px; cursor: pointer;">
    <!-- Below div is used to create space on left hand side -->
   
    <div id="imgL331" class="floatL nav_curve1"></div>
    <div id="imgL331" class="floatL nav_curve1"></div>
    <div id="imgL331" class="floatL nav_curve1"></div>
    <div id="imgL331" class="floatL nav_curve1"></div>
    <div id="imgL331" class="floatL nav_curve1"></div>
    <div id="imgL331" class="floatL nav_curve1"></div>
    <div id="imgL331" class="floatL nav_curve1"></div>
     
     <!-- Activity code=M19 for Access Control-->
 	<div id="imgL1" class="floatL nav_curve1"></div>
 	<div id="menuid1"  onmouseover="show_popup(1)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><bean:message key="Admin.Access_Control"/></div>
 	<div id="imgR1" class="floatL nav_curve1"></div>
 	
 	
 	<!-- Activity code=73 for System Configuration-->
 	<%
 	 	if(actCodeName.get("73")!=null){
    
 	%>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	<div id="imgL2" class="floatL nav_curve1"></div>
 	<div id="menuid2" onmouseover="show_popup(2)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><a href="../pages/masters/sysConfigView.jsp?from=menu" target="frmMain"><bean:message key="Admin.System_Configuration"/></a></div>
 	<div id="imgR2" class="floatL nav_curve1"></div>
 	
 	<% 
 	}
 	%>
 
 
 	<!-- Activity code=33 for Import file-->
 	<%
 	 	if(actCodeName.get("33")!=null){
    
 	%>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	<div id="imgL3" class="floatL nav_curve1"></div>
 	<div id="menuid3" onmouseover="show_popup(3)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><a href="../pages/ImportNewStock.jsp" target="frmMain"><bean:message key="Admin.Import_File"/></a></div>
 	<div id="imgR3" class="floatL nav_curve1"></div>
 	<% 
 	}
 	%>
 	
 	<!-- Activity code=116 for Index OHLC-->
 	<%
 	 	if(actCodeName.get("116")!=null){
    
 	%>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	<div id="imgL4" class="floatL nav_curve1"></div>
 	<div id="menuid4" onmouseover="show_popup(4)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><a href="../pages/DatFileUpload.jsp " target="frmMain"><bean:message key="Admin.Index_OHLC"/></a></div>
 	<div id="imgR4" class="floatL nav_curve1"></div>
  	<% 
 	}
 	%>
</div><!--  Admin's submenu end -->

    
<!-- Master submenu starts here -->
   <div id="show2" style="display:none;color=black; width:776px; height:28px; cursor: pointer;">
 	<div id="imgL332" class="floatL nav_curve1"></div>
 	<div id="imgL332" class="floatL nav_curve1"></div>
 	<div id="imgL332" class="floatL nav_curve1"></div>
 	<div id="imgL332" class="floatL nav_curve1"></div>
 	<div id="imgL332" class="floatL nav_curve1"></div>
 	<div id="imgL332" class="floatL nav_curve1"></div>
 	<div id="imgL332" class="floatL nav_curve1"></div>
 	
 	<!-- Activity code=M18 for Add Masters-->
 	<div id="imgL5" class="floatL nav_curve1"></div>
 	<div id="menuid5" onmouseover="show_popup(5)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><bean:message key="Master.Add_Master"/></a></div>
 	<div id="imgR5" class="floatL nav_curve1"></div>
 	
 	
 	<!-- Activity code=17 for Currency Conversion-->
 	
 	
 	<%
 	 	if(actCodeName.get("17")!=null){
    
 	%>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	<div id="imgL6" class="floatL nav_curve1"></div>
 	<div id="menuid6" onmouseover="show_popup(6)" onmouseout="javascript:m_show=0;"   style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><a href="../pages/masters/frtocurrency.jsp?from=menu" target="frmMain"><bean:message key="Master.Currency_Conversion"/></a></div>
 	<div id="imgR6" class="floatL nav_curve1"></div>
 	<%
 	}
 	%>
 	
 	<!-- Activity code=2 for Add Stock-->
 	<%
 	 	if(actCodeName.get("2")!=null){
    
 	%>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	<div id="imgL7" class="floatL nav_curve1"></div>
 	<div id="menuid7" onmouseover="show_popup(7)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><a href="../pages/masters/stockmaster2.jsp?from=menu" target="frmMain"><bean:message key="Master.Add_Stock"/></a></div>
 	<div id="imgR7" class="floatL nav_curve1"></div>
 	<% 
 	}
 	%>
 	
 	<!-- Activity code=112 for Compliance Master-->
 	<%
 	 	if(actCodeName.get("112")!=null){
    
 	%>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	<div id="imgL8" class="floatL nav_curve1"></div>
 	<div id="menuid8" onmouseover="show_popup(8)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><a href="../pages/masters/IndComplianceMaster.jsp?from=menu" target="frmMain"><bean:message key="Master.Compliance_Master"/></a></div>
 	<div id="imgR8" class="floatL nav_curve1"></div>
    <% 
 	}
 	%>
 	
 	<!-- Activity code=113 for Commodities-->
 	<%
 	 	if(actCodeName.get("113")!=null){
    
 	%>
    <div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	<div id="imgL9" class="floatL nav_curve1"></div>
 	<div id="menuid9" onmouseover="show_popup(9)" onmouseout="javascript:m_show=0;" style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><a href="../pages/masters/commodities.jsp?from=menu" target="frmMain"><bean:message key="Master.Commodities"/></a></div>
 	<div id="imgR9" class="floatL nav_curve1"></div>
 	<% 
 	}
 	%>
 	
 	<!-- Activity code=114 for Bonds /Fixed Income-->
 	<%
 	 	if(actCodeName.get("114")!=null){
    
 	%>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	<div id="imgL10" class="floatL nav_curve1"></div>
 	<div id="menuid10" onmouseover="show_popup(10)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><a href="../pages/masters/StockMasterBonds.jsp?from=menu" target="frmMain"><bean:message key="Master.Bonds_Fixed_Income"/></a></div>
 	<div id="imgR10" class="floatL nav_curve1"></div>
 	<% 
 	}
 	%>    
</div><!-- Master's submenu end -->

<!-- Index submenu starts here -->
   <div id="show3" style="display:none;color=black; width:776px; height:28px; cursor: pointer;">
 	<div id="imgL333" class="floatL nav_curve1"></div>
 	<div id="imgL333" class="floatL nav_curve1"></div>
 	<div id="imgL333" class="floatL nav_curve1"></div>
 	<div id="imgL333" class="floatL nav_curve1"></div>
 	<div id="imgL333" class="floatL nav_curve1"></div>
 	<div id="imgL333" class="floatL nav_curve1"></div>
 	<div id="imgL333" class="floatL nav_curve1"></div>
 	
 	<!-- Activity code=78 for Index Update-->
 	<%
 	 	if(actCodeName.get("78")!=null){
    
 	%>
 	<div id="imgL11" class="floatL nav_curve1"></div>
 	<div id="menuid11" onmouseover="show_popup(11)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><a href="../pages/indexUpdateView.jsp" target="frmMain"><bean:message key="Index.Index_Update"/></a></div>
 	<div id="imgR11" class="floatL nav_curve1"></div>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	<% 
 	}
 	%>   
 	<!-- Activity code=8 for Compute Index-->
 	<%
 	 	if(actCodeName.get("8")!=null){
    
 	%>
 	<div id="imgL12" class="floatL nav_curve1"></div>
 	<div id="menuid12" onmouseover="show_popup(12)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><a href="../pages/IndexHome.jsp?new=yes?ref_flag=1" target="frmMain"><bean:message key="Index.Compute_Index"/></a></div>
 	<div id="imgR12" class="floatL nav_curve1"></div>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	<% 
 	}
 	%> 
 	<!-- Activity code=7 for Add Captured Indices-->
 	<%
 	 	if(actCodeName.get("7")!=null){
    
 	%>
 	<div id="imgL13" class="floatL nav_curve1"></div>
 	<div id="menuid13" onmouseover="show_popup(13)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><a href="../pages/masters/AddCapturedIndex.jsp" target="frmMain"><bean:message key="Index.Add_Captured_Indices"/></a></div>
 	<div id="imgR13" class="floatL nav_curve1"></div>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	<% 
 	}
 	%> 
 	<!-- Activity code=1 for Add Index -->
 	<%
 	 	if(actCodeName.get("1")!=null){
    
 	%>
 	<div id="imgL14" class="floatL nav_curve1"></div>
 	<div id="menuid14" onmouseover="show_popup(14)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><a href="../pages/NewIndexDefine.jsp?new=yes" target="frmMain"><bean:message key="Index.Add_Index"/></a></div>
 	<div id="imgR14" class="floatL nav_curve1"></div>
   <% 
 	}
 	%> 
 	 	
</div><!-- Index submenu end -->

<!-- Industry Classification submenu starts here -->
   <div id="show4" style="display:none;color=black; width:776px; height:28px; cursor: pointer;">
 	<div id="imgL334" class="floatL nav_curve1"></div>
 	<div id="imgL333" class="floatL nav_curve1"></div>
 	<div id="imgL333" class="floatL nav_curve1"></div>
 	<div id="imgL334" class="floatL nav_curve1"></div>
 	<div id="imgL333" class="floatL nav_curve1"></div>
 	<div id="imgL333" class="floatL nav_curve1"></div>
 	<div id="imgL333" class="floatL nav_curve1"></div>
 	<!-- Activity code=89 for Classes-->
 	<%
 	 	if(actCodeName.get("89")!=null){
    
 	%>
 	<div id="imgL18" class="floatL nav_curve1"></div>
 	<div id="menuid18" onmouseover="show_popup(18)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><a href="../pages/masters/classes.jsp?from=menu" target="frmMain"><bean:message key="Industry.Classification_Classes"/></a></div>
 	<div id="imgR18" class="floatL nav_curve1"></div>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	<% 
 	}
 	%>
 	
 	<!-- Activity code=90 for Classification Level-->
 	<%
 	 	if(actCodeName.get("90")!=null){
    
 	%>
 	<div id="imgL19" class="floatL nav_curve1"></div>
 	<div id="menuid19" onmouseover="show_popup(19)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><a href="../pages/masters/classificationLevel.jsp?from=menu" target="frmMain"><bean:message key="Industry.Classification_Classification_Level"/></a></div>
 	<div id="imgR19" class="floatL nav_curve1"></div>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	<% 
 	}
 	%>
 	
 	<!-- Activity code=91 for Company Classification-->
 	<%
 	 	if(actCodeName.get("91")!=null){
    
 	%>
 	<div id="imgL20" class="floatL nav_curve1"></div>
 	<div id="menuid20" onmouseover="show_popup(20)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><a href="../pages/masters/company.jsp?from=menu " target="frmMain"><bean:message key="Industry.Classification_Company_Classification"/></a></div>
 	<div id="imgR20" class="floatL nav_curve1"></div>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	<% 
 	}
 	%>
 	<!-- Activity code=92 for Classification Standards-->
 	<%
 	 	if(actCodeName.get("92")!=null){
    
 	%>
 	<div id="imgL21" class="floatL nav_curve1"></div>
 	<div id="menuid21" onmouseover="show_popup(21)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><a href="../pages/masters/industryClassificationMaster.jsp?from=menu " target="frmMain"><bean:message key="Industry.Classification_Classification_Standards"/></a></div>
 	<div id="imgR21" class="floatL nav_curve1"></div>
    <div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
    <% 
 	}
 	%>
 	<!-- Activity code=115 for GICS Classification-->
 	<%
 	 	if(actCodeName.get("115")!=null){
    
 	%>
 	<div id="imgL22" class="floatL nav_curve1"></div>
 	<div id="menuid22" onmouseover="show_popup(22)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><a href="../pages/GICSFileDialog.jsp " target="frmMain"><bean:message key="Industry.Classification_GICS_Classification"/></a></div>
 	<div id="imgR22" class="floatL nav_curve1"></div>
 	<% 
 	}
 	%>
 	
 	 	
</div><!-- Industry Classification submenu end -->

<!-- Corporate Action submenu starts here -->
   <div id="show5" style="display:none;color=black; width:776px; height:28px; cursor: pointer;">
 	<div id="imgL335" class="floatL nav_curve1"></div>
 	<div id="imgL335" class="floatL nav_curve1"></div>
 	<div id="imgL335" class="floatL nav_curve1"></div>
 	<div id="imgL335" class="floatL nav_curve1"></div>
 	<div id="imgL335" class="floatL nav_curve1"></div>
 	<div id="imgL335" class="floatL nav_curve1"></div>
 	<div id="imgL335" class="floatL nav_curve1"></div>
 	
 	<!-- Activity code=M6 for Stock Events-->
 	<div id="imgL23" class="floatL nav_curve1"></div>
 	<div id="menuid23" onmouseover="show_popup(23)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><bean:message key="Corporate.Action_Stock_Events"/></div>
 	<div id="imgR23" class="floatL nav_curve1"></div>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	
 	<!-- Activity code=M8 for Index Events-->
 	<div id="imgL24" class="floatL nav_curve1"></div>
 	<div id="menuid24" onmouseover="show_popup(24)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><bean:message key="Corporate.Action_Index_Events"/></div>
 	<div id="imgR24" class="floatL nav_curve1"></div>
 	
 	<!-- Activity code=3 for Diary--> 	
 	<%
 	if(actCodeName.get("3")!=null){
 	%>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	<div id="imgL25" class="floatL nav_curve1"></div>
 	<div id="menuid25" onmouseover="show_popup(25)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><a href="../pages/CorporateDiary.jsp?ref_flag=1 " target="frmMain"><bean:message key="Corporate.Action_Diary"/></a></div>
 	<div id="imgR25" class="floatL nav_curve1"></div>
 	<%}
 	%>
 	
 		 	
</div><!-- Corporate action submenu end -->

<!-- Analytics submenu starts here -->
   <div id="show6" style="display:none;color=black; width:776px; height:28px; cursor: pointer;">
 	<div id="imgL336" class="floatL nav_curve1"></div>
 	<div id="imgL336" class="floatL nav_curve1"></div>
 	<div id="imgL336" class="floatL nav_curve1"></div>
 	<div id="imgL336" class="floatL nav_curve1"></div>
 	<div id="imgL336" class="floatL nav_curve1"></div>
 	<div id="imgL336" class="floatL nav_curve1"></div>
 	<div id="imgL336" class="floatL nav_curve1"></div>
 	
 	<!-- Activity code=M10 for Index-->
 	<div id="imgL26" class="floatL nav_curve1"></div>
 	<div id="menuid26" onmouseover="show_popup(26)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><bean:message key="Analytics.Index"/></div>
 	<div id="imgR26" class="floatL nav_curve1"></div>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	
 	<!-- Activity code=M12 for Stocks-->
 	<div id="imgL27" class="floatL nav_curve1"></div>
 	<div id="menuid27" onmouseover="show_popup(27)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><bean:message key="Analytics.Stocks"/></div>
 	<div id="imgR27" class="floatL nav_curve1"></div>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	
 	<!-- Activity code=M13 for Weightages -->
 	<div id="imgL28" class="floatL nav_curve1"></div>
 	<div id="menuid28" onmouseover="show_popup(28)" onmouseout="javascript:m_show=0;" style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><bean:message key="Analytics.Weightage"/></div>
 	<div id="imgR28" class="floatL nav_curve1"></div>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	
 	
 	<!-- Activity code=M15 for Batch Report-->
 	<div id="imgL29" class="floatL nav_curve1"></div>
 	<div id="menuid29" onmouseover="show_popup(29)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><bean:message key="Analytics.Batch_Reports"/></div>
 	<div id="imgR29" class="floatL nav_curve1"></div>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	
 	<!-- Activity code=M16 for Query Report-->	
 	<div id="imgL30" class="floatL nav_curve1"></div>
 	<div id="menuid30" onmouseover="show_popup(30)" onmouseout="javascript:m_show=0;" style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><bean:message key="Analytics.Query_Reports"/></div>
 	<div id="imgR30" class="floatL nav_curve1"></div>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	
 	<!-- Activity code=M20 for Ajax View1-->	
 	<div id="imgL31" class="floatL nav_curve1"></div>
 	<div id="menuid31" onmouseover="show_popup(31)" onmouseout="javascript:m_show=0;" style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><bean:message key="Analytics.Ajax_View1"/></div>
 	<div id="imgR31" class="floatL nav_curve1"></div>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	
 	<!-- Activity code=M20 for Ajax View2-->	
 	<div id="imgL32" class="floatL nav_curve1"></div>
 	<div id="menuid32" onmouseover="show_popup(32)" onmouseout="javascript:m_show=0;" style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><bean:message key="Analytics.Ajax_View2"/></div>
 	<div id="imgR32" class="floatL nav_curve1"></div>
 	
 			 	
</div><!-- Analytics submenu end -->

<!-- Financial Analysis starts here -->
   <div id="show7" style="display:none;color=black; width:970px; height:28px; cursor: pointer;" >
 	<div id="imgL337" class="floatL nav_curve1"></div>
 	<div id="imgL337" class="floatL nav_curve1"></div>
 	<div id="imgL337" class="floatL nav_curve1"></div>
 	<div id="imgL337" class="floatL nav_curve1"></div>
 	<div id="imgL337" class="floatL nav_curve1"></div>
 	<div id="imgL337" class="floatL nav_curve1"></div>
 	<div id="imgL337" class="floatL nav_curve1"></div>
 	
 	<!-- Activity code=152 for P / E Computation -->
 		<%
 	if(actCodeName.get("152")!=null){
 	%>
 	<div id="imgL33" class="floatL nav_curve1"></div>
 	<div id="menuid33" onmouseover="show_popup(33)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><a href="../pages/Pat.jsp?new=yes" target="frmMain"><bean:message key="Financial.Analysis_P_E_Computation"/></a></div>
 	<div id="imgR33" class="floatL nav_curve1"></div>
 	<div style="padding:0 3 0 6px;" class="floatL"><img src="top_nav_divider.jpg" alt="" width="4" height="20" hspace="0" vspace="2" border="0"></div>
 	<%}
 	%>
 	<!-- Activity code=153 for Financial Entry-->	
 	<%
 	if(actCodeName.get("153")!=null){
 	%>
 	<div id="imgL34" class="floatL nav_curve1"></div>
 	<div id="menuid34" onmouseover="show_popup(34)" onmouseout="javascript:m_show=0;"  style="padding:7 4 8 4px; cursor:pointer;" class="floatL"><a href="../pages/FinanceResult.jsp?from=menu" target="frmMain"><bean:message key="Financial.Analysis_Financial_Entry"/></a></div>
 	<div id="imgR34" class="floatL nav_curve1"></div>
 	<%}
 	%>
 	
 	
</div><!-- Financial Analysis submenu end -->

 </div>
 
 <div class="nav_bg3" NOWRAP noWrap="true">
 	<!-- Sub-sub menu for Admin/Access Control starts here -->
 	  <div id="popup1" style="display:none;color=white; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
 		<!-- <SPAN STYLE="color: blue">-->
 		
 		<!-- Activity code=6 for New User-->
 		<%
 	 	if(actCodeName.get("6")!=null){
    
 	    %>
 		<div id="submenu1.1.1" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/newusers.jsp" target="frmMain"><bean:message key="Access.Control_New_User"/></a><span class="top_tdB1 subNav" STYLE="color: white">|</span></div>
 	   <%
 	   }
 	   %>
 	   <!-- Activity code=53 for Activities-->
 	   <%
 	 	if(actCodeName.get("53")!=null){
    
 	    %> 	   
 	    <div id="submenu1.1.2" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/masters/activitiesView.jsp" target="frmMain"><bean:message key="Access.Control_Activities"/></a><span class="top_tdB1 subNav">|</span></div>
 		<%
 	   }
 	   %>
 	   <!-- Activity code=75 for New Roles-->
 	   <%
 	 	if(actCodeName.get("75")!=null){
    
 	    %>
 		<div id="submenu1.1.3" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/masters/rolesView.jsp?from=menu" target="frmMain"><bean:message key="Access.Control_New_Roles"/></a><span class="top_tdB1 subNav">|</span></div>
 		 <%
 	   }
 	   %>
 	    <!-- Activity code=76 for New Role Activities-->
 	     <%
 	 	if(actCodeName.get("76")!=null){
    
 	    %>
 		<div id="submenu1.1.4" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href=" ../pages/masters/roleActivitiesView.jsp?from=menu" target="frmMain"><bean:message key="Access.Control_Role_Activities"/></a></div>
 		 <%
 	   }
 	   %>
 		</div>
 		
 
 	<div id="popup2" style="display:none; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
 		<!-- System Configuration  NO SUB MENU  -->
 		
 	</div>
 	
 	<div id="popup3" style="display:none; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
 		<!--  Import File  NO SUB MENU --> 
 		
 	</div>

 	
 	<div id="popup4" style="display:none; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
 	     <!-- Index OHLC  NO SUB MENU -->
 		
 	</div>
 	
 	 	
 <!-- sub-sub menu for Admin ends here -->
 
 <!-- sub sub menu for Master -->
    <div id="popup5" style="display:none;color=white; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
    <!--  Add Master sub menu -->
         <!-- Activity code=86 for Currency-->
          <%
 	 	if(actCodeName.get("86")!=null){
     	    %>
        <div id="submenu2.1.0" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/masters/AddCurrency.jsp?from=menu " target="frmMain"><bean:message key="Add.Master_Currency"/></a><span class="top_tdB1 subNav" STYLE="color: white">|</span></div>
          <%
 	     }
 	     %> 
         <!-- Activity code=9 for Company-->
         <%
 	 	if(actCodeName.get("9")!=null){
     	    %>
        <div id="submenu2.1.1" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/masters/AddNewCompany.jsp?from=menu " target="frmMain"><bean:message key="Add.Master_Company"/></a><span class="top_tdB1 subNav" STYLE="color: white">|</span></div>
 	     <%
 	     }
 	     %> 
 	     <!-- Activity code=10 for Stock Exchange-->
 	     <%
 	 	if(actCodeName.get("10")!=null){
     	    %>
 	    <div id="submenu2.1.2" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href ="../pages/masters/AddStockExchange.jsp?from=menu" target="frmMain" ><bean:message key="Add.Master_Stock_Exchange"/></a><span class="top_tdB1 subNav">|</span></div>
 		<%
 	     }
 	     %>  
 		 <!-- Activity code=11 for Country-->
 		 <%
 	 	if(actCodeName.get("11")!=null){
     	  %>
 		<div id="submenu2.1.3" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href ="../pages/masters/countries.jsp?from=menu" target="frmMain" ><bean:message key="Add.Master_Country"/></a><span class="top_tdB1 subNav">|</span></div>
 		<%
 	     }
 	     %>
 		 <!-- Activity code=12 for Identifier Codes-->
 		  <%
 	 	if(actCodeName.get("12")!=null){
     	  %>
 		<div id="submenu2.1.4" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href ="../pages/masters/IdentifierCodes.jsp?from=menu" target="frmMain" ><bean:message key="Add.Master_Identifier_Codes"/></a><span class="top_tdB1 subNav">|</span></div>
      <%
 	     }
 	     %>
         <!-- Activity code=13 for Index Type-->
          <%
 	 	if(actCodeName.get("13")!=null){
     	  %>
        <div id="submenu2.1.5" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href =" ../pages/masters/IndexType.jsp?from=menu" target="frmMain" ><bean:message key="Add.Master_Index_Type"/></a><span class="top_tdB1 subNav" STYLE="color: white">|</span></div>
 	    <%
 	     }
 	     %>
 	     <!-- Activity code=14 for Rating Codes -->
 	      <%
 	 	if(actCodeName.get("14")!=null){
     	  %>
 	    <div id="submenu2.1.6" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href ="../pages/masters/RatingCodes.jsp?from=menu" target="frmMain" ><bean:message key="Add.Master_Rating_Codes"/></a><span class="top_tdB1 subNav">|</span></div>
 		 <%
 	     }
 	     %>
 		 <!-- Activity code=15 for Stock Type-->
 		  <%
 	 	if(actCodeName.get("15")!=null){
     	  %>
 		<div id="submenu2.1.7" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href ="../pages/masters/StockType.jsp?from=menu" target="frmMain" ><bean:message key="Add.Master_Stock_Type"/></a><span class="top_tdB1 subNav">|</span></div>
 		 <%
 	     }
 	     %>
 		 <!-- Activity code=16 for Time Zone -->
 		  <%
 	 	if(actCodeName.get("16")!=null){
     	  %>
 		<div id="submenu2.1.8" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href ="../pages/masters/TimeZone.jsp?from=menu" target="frmMain" ><bean:message key="Add.Master_Time_Zone"/></a><span class="top_tdB1 subNav">|</span></div>
 	      <%
 	     }
 	     %>
 	     <!-- Activity code=77 for Exchange Holidays-->
 	       <%
 	 	if(actCodeName.get("77")!=null){
     	  %>
 	    <div id="submenu2.1.9" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href ="../pages/masters/ExchangeHolidays.jsp?from=menu" target="frmMain" ><bean:message key="Add.Master_Exchange_Holidays"/></a><span class="top_tdB1 subNav">|</span></div>
 	      <%
 	     }
 	     %>
 	     <!-- Activity code=57 for New Corporate Action -->
 	     <%
 	 	if(actCodeName.get("57")!=null){
     	  %> 
 	    <div id="submenu2.1.10" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href ="../pages/masters/CorporateActionMaster.jsp" target="frmMain" ><bean:message key="Add.Master_New_Corporate_Action"/></a></div>
 		<%
 	     }
 	     %>
 	</div>
    
    <div id="popup6" style="display:none; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
    <!--  Currency Conversion no sub menu -->
    </div>
    
    <div id="popup7" style="display:none; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
    <!--  Add Stock no sub menu -->
    </div>
    <div id="popup8" style="display:none; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
    <!--  Compliance Master no sub menu -->
    </div>
    <div id="popup9" style="display:none; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
    <!--  Commodities no sub menu -->
    </div>
    <div id="popup10" style="display:none; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
    <!--  Bonds / Fixed Income no sub menu -->
    </div>
        
   <!-- sub-sub menu for Master ends here --> 
   <!-- sub sub menu for Index -->
 	<div id="popup11" style="display:none; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
  	<!--  Index Update no sub menu -->
    </div>
    <div id="popup12" style="display:none; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
  	<!--  Compute Index no sub menu -->
    </div>
    <div id="popup13" style="display:none; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
  	<!--  Add Captured Index no sub menu -->
    </div>
    <div id="popup14" style="display:none; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
  	<!--  Add Index no sub menu -->
    </div>
   
  <!-- sub-sub menu for Index ends here --> 
 	
 	<!-- sub sub menu for Industry Classification -->
 	<div id="popup18" style="display:none; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
  	<!--  Classes no sub menu -->
    </div>
    <div id="popup19" style="display:none; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
  	<!--  Classification Level no sub menu -->
    </div>
    <div id="popup20" style="display:none; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
  	<!--  Company Classification no sub menu -->
    </div>
    <div id="popup21" style="display:none; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
  	<!--  Classification Standards no sub menu -->
    </div>
    <div id="popup22" style="display:none; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
  	<!--  GICS Classification no sub menu -->
    </div>
  <!-- sub-sub menu for Industry Classification ends here --> 
 
  <!-- sub sub menu for Corporate Action -->
   <div id="popup23" style="display:none;color=white; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
  	<!--  Stock Events sub menu start here-->
  		<!-- Activity code=M7 for stock Divident ../pages/reports/StockDividentS.jsp?ajax1=yes -->	
  		<!--   Activity code=18 for cash Divident-->
  		<%
 	 	if(actCodeName.get("18")!=null){
     	  %>	
 		<div id="submenu5.1.1" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/NCorporateAction.jsp?corp_name=cashdividend&flag=New&ref_flag=1" target="frmMain"><bean:message key="Stock.Events_Cash_Divident"/></a><span class="top_tdB1 subNav">|</span></div>
 	    <%
 	     }
 	     %>
 	     <!--   Activity code=19 for stock Divident/Bonus-->
  		<%
 	 	if(actCodeName.get("19")!=null){
     	  %>	
 		<div id="submenu5.1.0" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/NCorporateAction.jsp?corp_name=stockdividend/bonus&flag=New&ref_flag=1" target="frmMain"><bean:message key="Stock.Events_Stock_Divident_Bonus"/></a><span class="top_tdB1 subNav">|</span></div>
 	    <%
 	     }
 	     %>
 	    <!-- Activity code=22 for Repurchase of share-->
 	    <%
 	 	if(actCodeName.get("22")!=null){
     	  %>
 	    <div id="submenu5.1.2" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/NCorporateAction.jsp?corp_name=sharesbuyback&flag=New&ref_flag=1" target="frmMain"><bean:message key="Stock.Events_Repurchase_of_share"/></a><span class="top_tdB1 subNav">|</span></div>
 		<%
 	     }
 	     %>
 		<!-- Activity code=23 for Reverse Split-->
 		 <%
 	 	if(actCodeName.get("23")!=null){
     	  %>
 		<div id="submenu5.1.3" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/NCorporateAction.jsp?corp_name=reversesplit&flag=New&ref_flag=1 " target="frmMain"><bean:message key="Stock.Events_Reverse_Split"/></a><span class="top_tdB1 subNav">|</span></div>
 		<%
 	     }
 	     %>
 		<!-- Activity code=24 for Rights Offering-->
 		<%
 	 	if(actCodeName.get("24")!=null){
     	  %>
 		<div id="submenu5.1.4" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/NCorporateAction.jsp?corp_name=rightsoffering&flag=New&ref_flag=1" target="frmMain"><bean:message key="Stock.Events_Rights_Offering"/></a><span class="top_tdB1 subNav">|</span></div>
 		<%
 	     }
 	     %>
 		<!-- Activity code=25 for Share Issurance-->
 		<%
 	 	if(actCodeName.get("25")!=null){
     	  %>
 		<div id="submenu5.1.5" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/NCorporateAction.jsp?corp_name=shareissuance&flag=New&ref_flag=1 " target="frmMain"><bean:message key="Stock.Events_Share_Issurance"/></a><span class="top_tdB1 subNav">|</span></div>
 		<%
 	     }
 	     %>
 		<!-- Activity code=26 for Spin Off-->
 		<%
 	 	if(actCodeName.get("26")!=null){
     	  %>
 		<div id="submenu5.1.6" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/NCorporateAction.jsp?corp_name=spin-off&flag=New&ref_flag=1" target="frmMain"><bean:message key="Stock.Events_Spin_Off"/></a><span class="top_tdB1 subNav">|</span></div>
		<%
 	     }
 	     %>
		<!-- Activity code=27 for Split-->
		<%
 	 	if(actCodeName.get("27")!=null){
     	  %>
		<div id="submenu5.1.7" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)" class="floatL subNav"><a href="../pages/NCorporateAction.jsp?corp_name=split&flag=New&ref_flag=1" target="frmMain"><bean:message key="Stock.Events_Split"/></a><span class="top_tdB1 subNav">|</span></div>
 		<%
 	     }
 	     %>
 		<!-- Activity code=28 for Warrant Conversion-->
 		<%
 	 	if(actCodeName.get("28")!=null){
     	  %>
 		<div id="submenu5.1.8" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/NCorporateAction.jsp?corp_name=warrantconversion&flag=New&ref_flag=1" target="frmMain"><bean:message key="Stock.Events_Warrant_Conversion"/></a><span class="top_tdB1 subNav">|</span></div>
 		<%
 	     }
 	     %>
 		<!-- Activity code=87 for ADR Issurance-->
 		<%
 	 	if(actCodeName.get("87")!=null){
     	  %>
 		<div id="submenu5.1.9" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/NCorporateAction.jsp?corp_name=adrissue&flag=New&ref_flag=1" target="frmMain"><bean:message key="Stock.Events_ADR_Issurance"/></a></div>
         <%
 	     }
 	     %>
 </div>
    
     <div id="popup24" style="display:none;color=white; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
  	<!--  Index Events  sub menu -->
  	    <!-- Activity code=29 for Addition Of Stock-->
  	   <%
 	 	if(actCodeName.get("29")!=null){
     	  %>
  	    <div id="submenu5.2.1" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/IndexEvents.jsp?ref_flag=1&corp_name=addstock" target="frmMain"><bean:message key="Index.Event_Addition_Of_Stock"/></a><span class="top_tdB1 subNav">|</span></div>
 	    <%
 	     }
 	     %>
 	   <!-- Activity code=30 for Change In IWF-->
       <%
 	 	if(actCodeName.get("30")!=null){
     	  %>
        <div id="submenu5.2.2" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/IndexEvents.jsp?ref_flag=1&corp_name=changeiwf " target="frmMain"><bean:message key="Index.Events_Change_In_IWF"/></a><span class="top_tdB1 subNav">|</span></div>
 		 <%
 	     }
 	     %>
 		<!-- Activity code=31 for Deletion Of Stock-->
 		<%
 	 	if(actCodeName.get("31")!=null){
     	  %>
 		<div id="submenu5.2.3" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/IndexEvents.jsp?ref_flag=1&corp_name=deletestock " target="frmMain"><bean:message key="Index.Events_Deletion_Of_Stock"/></a><span class="top_tdB1 subNav">|</span></div>
 		 <%
 	     }
 	     %>
 		<!-- Activity code=32 for Rebasing-->
 		<%
 	 	if(actCodeName.get("32")!=null){
     	  %>
 		<div id="submenu5.2.4" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/IndexEvents.jsp?ref_flag=1&corp_name=rebasing " target="frmMain"><bean:message key="Index.Events_Rebasing"/><span class="top_tdB1 subNav">|</span></div>
 		 <%
 	     }
 	     %>
 		<!-- Activity code=111 for Stock Merger-->
 		<%
 	 	if(actCodeName.get("111")!=null){
     	  %>
 		<div id="submenu5.2.5" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/StockMerger.jsp?ref_flag=1 " target="frmMain"><bean:message key="Index.Events_Stock_Merger"/><span class="top_tdB1 subNav">|</span></div>
 		 <%
 	     }
 	     %>
 		<!-- Activity code=88 for Change In Index Currency-->
 		<%
 	 	if(actCodeName.get("88")!=null){
     	  %>
 		<div id="submenu5.2.6" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/IndexEvents.jsp?ref_flag=1&corp_name=8 " target="frmMain"><bean:message key="Index.Events_Change_In_Index_Currency"/></div>
		 <%
 	     }
 	     %>
    </div>
     
     <div id="popup25" style="display:none; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
  	<!--  Diary no sub menu -->
    </div>
    
  <!-- sub-sub menu for Corporate Action ends here --> 
  
   <!-- sub sub menu for Analytics  -->
   <div id="popup26" style="display:none;color=white; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
  	<!--  Index  sub menu -->
  	<!-- Activity code=80 for Index Calculator (CODE=80) -->
  	<%
 	 	if(actCodeName.get("80")!=null){
 	 	
     	  %>
  	    <div id="submenu6.1.1" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/IndexCalculator.jsp" target="frmMain"><bean:message key="Index.CalCulator"/></a> <span class="top_tdB1 subNav">|</span></div>
 	   <%
 	     }
 	     %>
 	  <!-- Activity code=36 for Index Latest Divisor -->  
 	    <%
 	 	if(actCodeName.get("36")!=null){
 	 	
     	  %>
 	    <div id="submenu6.1.2" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/LatestIndexDivisors.jsp" target="frmMain"><bean:message key="Index.Latest_Divisor"/></a><span class="top_tdB1 subNav">|</span></div>
 	 <%
 	     }
 	     %>
 	<!-- Activity code=37 for Index Compare -->	
 		<%
 	 	if(actCodeName.get("37")!=null){
 	 	
     	  %>
 		<div id="submenu6.1.3" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/IndexCompare1S.jsp?FlagForReset=yes&ajax1=yes" target="frmMain"><bean:message key="Index.Compare"/></a><span class="top_tdB1 subNav">|</span></div>
 	<%
 	     }
 	     %>
 	<!-- Activity code=38 for Report Index Compare  OHLC -->	
 		<%
 	 	if(actCodeName.get("38")!=null){
 	 	
     	  %>
 		<div id="submenu6.1.4" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/IndexCompareOHLC.jsp" target="frmMain"><bean:message key="Index.OHLC"/></a><span class="top_tdB1 subNav">|</span></div>
 	    <%
 	     }
 	     %>
 	<!-- Activity code=39 for Index Composition -->	
 		<%
 	 	if(actCodeName.get("39")!=null){
 	 	
     	  %>
 		<div id="submenu6.1.5" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/IndexComposeS.jsp?ajax1=yes" target="frmMain"><bean:message key="Index.Composition"/></a><span class="top_tdB1 subNav">|</span></div>
 	   <%
 	     }
 	     %>
 	<!-- Activity code=40 for Index Movement -->
 		<%
 	 	if(actCodeName.get("40")!=null){
 	 	
     	  %>
 		<div id="submenu6.1.6" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/MoveIndexValueS.jsp?ajax1=yes" target="frmMain"><bean:message key="Index.Movement"/></a><span class="top_tdB1 subNav">|</span></div>
 	   <%
 	     }
 	     %>
 	<!-- Activity code=41 for Index Performance -->	
 	<%
 	 	if(actCodeName.get("41")!=null){
 	 	
     	  %>
 		<div id="submenu6.1.7" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/IndexPerformanceS.jsp?ajax2=yes" target="frmMain"><bean:message key="Index.Performance"/></a><span class="top_tdB1 subNav">|</span></div>
 	 <%
 	     }
 	     %>
 	 <!-- Activity code=43 for Index Returns/Volatility -->
  <%
 	 	if(actCodeName.get("43")!=null){
 	 	
     	  %>
 	    <div id="submenu6.1.8" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/IndexReturns_VolatilityS.jsp?ajax1=yes" target="frmMain"><bean:message key="Index.Returns_Volatility"/></a><span class="top_tdB1 subNav">|</span></div>
 	<%
 	     }
 	     %>
 	<!-- Activity code=44 for Index Correletion-->
 		 <%
 	 	if(actCodeName.get("44")!=null){
 	 	
     	  %>
 		<div id="submenu6.1.9" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/IndexCorrelation.jsp" target="frmMain"><bean:message key="Index.Correletion"/></a><span class="top_tdB1 subNav">|</span></div>
 	<%
 	     }
 	     %>
 	<!-- Activity code=55 for Index List -->	
 		 <%
 	 	if(actCodeName.get("55")!=null){
 	 	
     	  %>
 		<div id="submenu6.1.10" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/DisplayIndexS.jsp?FromLogin=No" target="frmMain"><bean:message key="Index.Index_List"/></a><span class="top_tdB1 subNav">|</span></div>
 	<%
 	     }
 	     %>
 	<!-- Activity code=83 for Index PE/PB -->	
 		 <%
 	 	if(actCodeName.get("83")!=null){
 	 	
     	  %>
 		<div id="submenu6.1.11" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/Indexpe_pbS.jsp?ajax1=yes" target="frmMain"><bean:message key="Index.PE_PB"/></a><span class="top_tdB1 subNav">|</span></div>
 	<%
 	     }
 	     %>
 	<!-- Activity code=95 for Index Currency Wise -->	
 		 <%
 	 	if(actCodeName.get("95")!=null){
 	 	  %>
 		<div id="submenu6.1.12" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/IndexCurrencyWiseS.jsp" target="frmMain"> <bean:message key="Index.Currency_Wise"/></a><span class="top_tdB1 subNav">|</span></div>
 		<%
 	     }
 	     %>
 		<!-- Activity code=35 for Index Divisor  -->
 		 <%
 	 	if(actCodeName.get("35")!=null){
 	 	  %>
 	 	  <div id="submenu5.1.13" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/Index_DivisorS.jsp?ajax1=yes" target="frmMain"><bean:message key="Index.Divisor"/></a></div>
 	 	  <%
 	     }
 	     %>		
    </div>
    
    <div id="popup27" style="display:none;color=white; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
  	<!--  Stock sub menu -->
  	    <!-- Activity code=46 for Capital Change -->
  	    <%
 	 	if(actCodeName.get("46")!=null){
 	 	
 	 	  %>
  	    <div id="submenu6.2.1" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/CapitalChangeToUniverseS.jsp?refFlag=1&ajax1=yes" target="frmMain"><bean:message key="Stock.Capital_Change"/></a><span class="top_tdB1 subNav">|</span></div>
 	     <%
 	     }
 	     %>
 	     <!-- Activity code=56 for Stock List -->
 	    <%
 	 	if(actCodeName.get("56")!=null){
 	 	
 	 	  %>
 	    <div id="submenu6.2.2" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/StockListS.jsp?refFlag=1" target="frmMain"><bean:message key="Stock.Stock_List"/><span class="top_tdB1 subNav">|</span></div>
 		 <%
 	     }
 	     %>
 		<!-- Activity code=79 for Inactive Stock -->
 		<%
 	 	if(actCodeName.get("79")!=null){
 	 	
 	 	  %>
 		<div id="submenu6.2.3" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/InactiveStockListS.jsp?refFlag=1&ajax1=yes" target="frmMain"><bean:message key="Stock.Inactive_Stock"/><span class="top_tdB1 subNav">|</span></div>
 		<%
 	     }
 	     %>
 		<!-- Activity code=84 for Stock Divident -->
 		<%
 	 	if(actCodeName.get("84")!=null){
 	 	
 	 	  %>
 		<div id="submenu6.2.4" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/StockDividentS.jsp?ajax1=yes" target="frmMain"><bean:message key="Stock.Stock_Divident"/></a><span class="top_tdB1 subNav">|</span></div>
 		<%
 	     }
 	     %>
 		<!-- Activity code=85 for Traded Volume -->
 		<%
 	 	if(actCodeName.get("85")!=null){
 	 	
 	 	  %>
 		<div id="submenu6.2.5" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/TradedVolumeInd_exchS.jsp?refFlag=1&ajax1=yes" target="frmMain"><bean:message key="Stock.Traded_Volume"/></a><span class="top_tdB1 subNav">|</span></div>
 		<%
 	     }
 	     %>
 		<!-- Activity code=96 for Stock Details Between  Dates -->
 		<%
 	 	if(actCodeName.get("96")!=null){
 	 	
 	 	  %>
 		<div id="submenu6.2.6" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/StockHighLowS.jsp?refFlag=1&ajax1=yes" target="frmMain"><bean:message key="Stock.Stock_Details_Bet_Dates"/></a></div>
 		<%
 	     }
 	     %>
 	    
    </div>
    <div id="popup28" style="display:none;color=white; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
  	<!--  Weightages sub menu -->
  	    <!-- Activity code=49 for Stock Weightage -->
  	    <%
 	 	if(actCodeName.get("49")!=null){
 	 	
 	 	  %>
  	    <div id="submenu6.3.1" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/PageNotImplemented.jsp" target="frmMain"><bean:message key="Stock.Weightage_Stock_Weightage"/></a><span class="top_tdB1 subNav">|</span></div>
 	    <%
 	     }
 	     %>
 	    <!-- Activity code=50 for Company Weightage -->
 	    <%
 	 	if(actCodeName.get("50")!=null){
 	 	
 	 	  %>
 	    <div id="submenu6.3.2" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/CompanyWiseWeightageS.jsp?ajax1=yes" target="frmMain"><bean:message key="Stock.Weightage_Company_Weightage"/></a><span class="top_tdB1 subNav">|</span></div>
 		
 		<%
 	     }
 	     %>
 	     <!-- Activity code=51 for Industry Weightage -->
 		<%
 	 	if(actCodeName.get("51")!=null){
 	 	
 	 	  %>
 		<div id="submenu6.3.3" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/IndWiseWeightS.jsp?ajax1=yes" target="frmMain"><bean:message key="Stock.Weightage_Industry_Weightage"/></a><span class="top_tdB1 subNav">|</span></div>
 		<%
 	     }
 	     %>
 		<!-- Activity code=52 for Stock Contribution to Change Index -->
 		<%
 	 	if(actCodeName.get("52")!=null){
 	 	
 	 	  %>
 		<div id="submenu6.3.4" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/StockContriToIndexChangeS.jsp?ajax1=yes" target="frmMain"><bean:message key="Stock.Weightage_Stock_Contribution_to_Change_Index"/></a></div>
 		<%
 	     }
 	     %>
    </div>
    
    <div id="popup29" style="display:none;color=white; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
  	<!--  Batch Report sub menu -->
  	    <!-- Activity code=149 for Batch Report Updation-->
  	    <%
 	 	if(actCodeName.get("149")!=null){
 	 	
 	 	  %>
  	    <div id="submenu6.4.1" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/ReportPre.jsp" target="frmMain"><bean:message key="Batch.Report_Updation"/></a><span class="top_tdB1 subNav">|</span></div>
 	    <%
 	     }
 	     %>
 	    <!-- Activity code=150 for Batch Report Execution -->
 	    <%
 	 	if(actCodeName.get("150")!=null){
 	 	
 	 	  %>
 	    <div id="submenu6.4.2" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/BatchReportsN.jsp" target="frmMain"><bean:message key="Batch.Report_Execution"/></a></div>
 		<%
 	     }
 	     %>
    </div>
   
    <div id="popup30" style="display:none;color=white; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
  	<!--  Query Report sub menu -->
  	    <!-- Activity code=156 for DynaReport 1 -->
  	    <%
 	 	if(actCodeName.get("156")!=null){
 	 	
 	 	  %>
  	    <div id="submenu6.5.1" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/DynaReport.jsp" target="frmMain"><bean:message key="Query.Report_DynaReport1"/></a><span class="top_tdB1 subNav">|</span></div>
 	   <%
 	     }
 	     %>
 	    <!-- Activity code=157 for DynaReport 2 -->
 	    <%
 	 	if(actCodeName.get("157")!=null){
 	 	
 	 	  %>
 	    <div id="submenu6.5.2" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/QueryBuilder.jsp" target="frmMain"><bean:message key="Query.Report_DynaReport2"/></a></div>
 		<%
 	     }
 	     %>
    </div>
    
     <div id="popup31" style="display:none;color=white; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
     <!--  Ajax View1 sub menu -->
     <!-- Activity code=37  for Ajax index Compare -->
  	    <%
 	 	if(actCodeName.get("37")!=null){
 	 	
 	 	  %>
  	    <div id="submenu6.6.1" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/IndexCompare1S.jsp?FlagForReset=yes&ajax1=no" target="frmMain"><bean:message key="AjaxView1.Index_Compare"/></a><span class="top_tdB1 subNav">|</span></div>
 	   <%
 	     }
 	     %>
 	    <!-- Activity code=39 for Ajax Index composition -->
 	    <%
 	 	if(actCodeName.get("39")!=null){
 	 	
 	 	  %>
 	    <div id="submenu6.6.2" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/IndexComposeS.jsp?ajax1=no" target="frmMain"><bean:message key="AjaxView1.Index_Composition"/></a><span class="top_tdB1 subNav">|</span></div>
 		<%
 	     }
 	     %>
 	     <!-- Activity code=40 for Ajax index movement -->
  	    <%
 	 	if(actCodeName.get("40")!=null){
 	 	
 	 	  %>
  	    <div id="submenu6.6.3" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/MoveIndexValueS.jsp?ajax1=no" target="frmMain"><bean:message key="AjaxView1.Index_Movement"/></a><span class="top_tdB1 subNav">|</span></div>
 	   <%
 	     }
 	     %>
 	     <!-- Activity code=41 for Ajax index performance -->
  	    <%
 	 	if(actCodeName.get("41")!=null){
 	 	
 	 	  %>
  	    <div id="submenu6.6.4" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/IndexPerformanceS.jsp?ajax2=no" target="frmMain"><bean:message key="AjaxView1.Index_Performance"/></a><span class="top_tdB1 subNav">|</span></div>
 	   <%
 	     }
 	     %>
 	     <!-- Activity code=43 for Ajax index return & volatility -->
  	    <%
 	 	if(actCodeName.get("43")!=null){
 	 	
 	 	  %>
  	    <div id="submenu6.6.5" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/IndexReturns_VolatilityS.jsp?ajax1=no" target="frmMain"><bean:message key="AjaxView1.Index_Return_Volatility"/></a><span class="top_tdB1 subNav">|</span></div>
 	   <%
 	     }
 	     %>
 	     <!-- Activity code=46 for Ajax capital change -->
  	    <%
 	 	if(actCodeName.get("46")!=null){
 	 	
 	 	  %>
  	    <div id="submenu6.6.6" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/CapitalChangeToUniverseS.jsp?refFlag=1&ajax1=no" target="frmMain"><bean:message key="AjaxView1.Capital_Change"/></a><span class="top_tdB1 subNav">|</span></div>
 	   <%
 	     }
 	     %>
 	     <!-- Activity code=50 for Ajax Company weightage -->
  	    <%
 	 	if(actCodeName.get("50")!=null){
 	 	
 	 	  %>
  	    <div id="submenu6.6.7" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/CompanyWiseWeightageS.jsp?ajax1=no" target="frmMain"><bean:message key="AjaxView1.Company_Weightage"/></a><span class="top_tdB1 subNav">|</span></div>
 	   <%
 	     }
 	     %>
 	     <!-- Activity code=51 for Ajax Industry weightage -->
  	    <%
 	 	if(actCodeName.get("51")!=null){
 	 	
 	 	  %>
  	    <div id="submenu6.6.8" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/IndWiseWeightS.jsp?ajax1=no" target="frmMain"><bean:message key="AjaxView1.Industry_Weightage"/></a></div>
 	   <%
 	     }
 	     %>
      </div>
      
    <div id="popup32" style="display:none;color=white; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
     <!--  Ajax View2 sub menu -->
     <!-- Activity code=79 for Ajax Inactive Stock -->
  	    <%
 	 	if(actCodeName.get("79")!=null){
 	 	
 	 	  %>
  	    <div id="submenu6.7.1" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/InactiveStockListS.jsp?refFlag=1&ajax1=no" target="frmMain"><bean:message key="AjaxView2.Inactive_Stock"/></a><span class="top_tdB1 subNav">|</span></div>
 	   <%
 	     }
 	     %>
 	    <!-- Activity code=52  for Ajax Stock Contribution To Change In Index -->
 	    <%
 	 	if(actCodeName.get("52")!=null){
 	 	
 	 	  %>
 	    <div id="submenu6.7.2" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/StockContriToIndexChangeS.jsp?ajax1=no" target="frmMain"><bean:message key="AjaxView2.Stock_Contribution_To_Change_In_Index"/></a><span class="top_tdB1 subNav">|</span></div>
 		<%
 	     }
 	     %>
      
      <!-- Activity code=83  for Ajax Index wise pe/pb -->
 	    <%
 	 	if(actCodeName.get("83")!=null){
 	 	
 	 	  %>
 	    <div id="submenu6.7.3" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/Indexpe_pbS.jsp?ajax1=no" target="frmMain"><bean:message key="AjaxView2.Index_Wise_PE/PB"/></a><span class="top_tdB1 subNav">|</span></div>
 		<%
 	     }
 	     %>
      
      <!-- Activity code=84  for Ajax stock divident -->
 	    <%
 	 	if(actCodeName.get("84")!=null){
 	 	
 	 	  %>
 	    <div id="submenu6.7.4" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/StockDividentS.jsp?ajax1=no" target="frmMain"><bean:message key="AjaxView2.Stock_Divident"/></a><span class="top_tdB1 subNav">|</span></div>
 		<%
 	     }
 	     %>
      
      <!-- Activity code=85  for Ajax Traded volume -->
 	    <%
 	 	if(actCodeName.get("85")!=null){
 	 	
 	 	  %>
 	    <div id="submenu6.7.5" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/TradedVolumeInd_exchS.jsp?refFlag=1&ajax1=no" target="frmMain"><bean:message key="AjaxView2.Traded_Volume"/></a><span class="top_tdB1 subNav">|</span></div>
 		<%
 	     }
 	     %>
      
      <!-- Activity code=35  for Ajax Index Divisor -->
 	    <%
 	 	if(actCodeName.get("35")!=null){
 	 	
 	 	  %>
 	    <div id="submenu6.7.6" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/Index_DivisorS.jsp?ajax1=no" target="frmMain"><bean:message key="AjaxView2.Index_Divisor"/></a><span class="top_tdB1 subNav">|</span></div>
 		<%
 	     }
 	     %>
     
      <!-- Activity code=96  for Ajax Stock Details between dates-->
 	    <%
 	 	if(actCodeName.get("96")!=null){
 	 	
 	 	  %>
 	    <div id="submenu6.7.7" onmouseover="mouse_over(this)" onmouseout="mouse_out(this)"  class="floatL subNav"><a href="../pages/reports/StockHighLowS.jsp?refFlag=1&ajax1=no" target="frmMain"><bean:message key="AjaxView2.Stock_Details_BetWeen_Dates"/></a></div>
 		<%
 	     }
 	     %>
      
   <!-- sub-sub menu for Analytics ends here --> 
   
   <!-- sub sub menu for Financial Analysis -->
   <div id="popup33" style="display:none; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
  	<!--  P/E Computation no sub menu-->
    </div>
    <div id="popup34" style="display:none; width:970px; height:28px; cursor: pointer;" onmouseover="javascript:m_show=1;" onmouseout="javascript:m_show=0;">
  	<!--  Financial Entry no sub menu -->
    </div>
   <!-- sub-sub menu for Financial Analysis ends here --> 
 </div>
 
<!-- This div  is below div-3 THIS IS NOT NEEDED IN ACTUAL UI, INFACT NOT USING THIS DIV IS GOOD FOR UI -->
<!-- Use , if mouse goes below div3 then desplay default menu and its default submenus -->
<!-- 
<div id="defaultPage" style="display:block;width:1000" onmouseover="show(1)">     </div>
-->
 	








<!-- *********************************************************************** -->

</td>

 
</tr>
</TABLE>
 



<script language="javascript">
//window.location.target="frmMain";
var m_show = 0;
var m_popupid = 0;
var m_color = "black";// "FFFFFF";
var m_hcolor ="black";  // "000000";
var m_scolor = "0E3F6E";
//var m_selected = "2"
// setInterval is commented to avoid apperance of unnecesary (green) vertical images.
//setInterval("hide_popup()",10);
function initialise(){

document.getElementById("mainMenu1").style.color = "black";

//show_popup(1);
//display Master's menu as default opening menu items
//alert("1");
show(1);
}
/* commented =========================
function init()
{
	
	document.getElementById("popup"+m_selected).style.display = "block";
 	document.getElementById("menuid"+m_selected).style.color = m_hcolor;
 	document.getElementById("menuid"+m_selected).style.textDecoration = "none";
 	document.getElementById("menuid"+m_selected).style.backgroundImage = "url('http://202.87.40.52/images/mc_common/nav_on_bg.jpg')";
 	document.getElementById("imgL"+m_selected).style.backgroundImage = "url('http://202.87.40.52/images/mc_common/nav_curve1.jpg')";
	document.getElementById("imgR"+m_selected).style.backgroundImage = "url('http://202.87.40.52/images/mc_common/nav_curve2.jpg')";
}
================================= end comment */
// show(m) used for which sub menu should be dispaly for Main menu 'm'
//******************************Show() function starts here *************************************
function show(m){
//m==1 for Master
 menuOldSelected=menuNewSelected;   // interChange old and new valuees of menu selected
 menuNewSelected=m;
 
//alert("2");
if(menuOldSelected!=menuNewSelected){

document.getElementById("mainMenu"+menuNewSelected).style.backgroundImage = "url('nav_on_bg3.jpg')";
document.getElementById("imgML"+menuNewSelected).style.backgroundImage = "url('nav_curve9.jpg')";
document.getElementById("imgMR"+menuNewSelected).style.backgroundImage = "url('nav_curve10.jpg')";
document.getElementById("show"+menuNewSelected).style.display = "block";
document.getElementById("mainMenu"+menuNewSelected).style.color = "black";

document.getElementById("mainMenu"+menuOldSelected).style.backgroundImage = "none";
document.getElementById("imgML"+menuOldSelected).style.backgroundImage = "url('top_nav_bg6.jpg')";
document.getElementById("imgMR"+menuOldSelected).style.backgroundImage = "url('top_nav_bg6.jpg')";
document.getElementById("show"+menuOldSelected).style.display = "none";
//document.getElementById("mainMenu"+menuOldSelected).style.color = "black";
}
// initialy  m=1 then 
else{
//alert("3");
document.getElementById("mainMenu"+menuNewSelected).style.backgroundImage = "url('nav_on_bg3.jpg')";
//alert("4");
document.getElementById("imgML"+menuNewSelected).style.backgroundImage = "url('nav_curve9.jpg')";
//alert("5");
document.getElementById("imgMR"+menuNewSelected).style.backgroundImage = "url('nav_curve10.jpg')";
//alert("6");

document.getElementById("show"+menuNewSelected).style.display = "block";
//alert("7");
document.getElementById("mainMenu"+menuNewSelected).style.color = "black";

}
//Following code is used to call appropriate show_popup() functions depending on the value of m
if(m==1){
//alert("8");
show_popup(1); // open default Add master submenu for Master
}
//m==2 for Admin
else if(m==2){
show_popup(5); // open default New User submenu for Admin
}
//m==3 for Index
else if(m==3){
show_popup(11);
}
//m==4 for Industry Classification
else if(m==4){
show_popup(18);
}
//m==5 for Corporate Action
else if(m==5){
show_popup(23);
}
//m==6 for  Analytics
else if(m==6){
show_popup(26);
}
else if(m==7){
//alert("================="+m);
show_popup(33);
}
}
//************************************ Show() function ends here*********************************


function show_popup(m_id)
 {
 	if(m_popupid > 0)
 	{
 		if(m_popupid != m_selected)
 		{
			//alert("inside if"+m_id);
			
			
			document.getElementById("popup"+m_popupid).style.display = "none";
			//alert("============1");
 			document.getElementById("menuid"+m_popupid).style.color = m_color;
 			//alert("============2");
			document.getElementById("menuid"+m_popupid).style.textDecoration = "none";
			//alert("============3");
			//alert("1");
			//document.getElementById("menuid"+m_popupid).style.backgroundImage = "url('http://202.87.40.52/images/mc_common/off_bg.jpg')";
			document.getElementById("menuid"+m_popupid).style.backgroundImage = "none";
			//alert("2");
			//document.getElementById("imgL"+m_id).style.backgroundImage = "url('http://202.87.40.52/images/mc_common/on_curve_1.jpg')";
			document.getElementById("imgL"+m_id).style.backgroundImage ="url('nav_curve11.jpg')";
			//alert("3");
			//document.getElementById("imgR"+m_id).style.backgroundImage = "url('http://202.87.40.52/images/mc_common/on_curve_2.jpg')";
			document.getElementById("imgR"+m_id).style.backgroundImage = "url('nav_curve12.jpg')";
			//alert("4");
			//document.getElementById("imgL"+m_popupid).style.backgroundImage = "url('http://202.87.40.52/images/mc_common/off_bg.jpg')";
            //document.getElementById("imgL"+m_popupid).style.backgroundImage = "url('top_nav_bg6.jpg')"; 
            document.getElementById("imgL"+m_popupid).style.backgroundImage = "url('top_nav_bg4.jpg')"; 				
			//alert("5");
			//document.getElementById("imgR"+m_popupid).style.backgroundImage = "url('http://202.87.40.52/images/mc_common/off_bg.jpg')";
			document.getElementById("imgR"+m_popupid).style.backgroundImage = "url('top_nav_bg4.jpg')";
 		}
 	}
	
 	m_popupid = m_id;
 	document.getElementById("popup"+m_selected).style.display = "none";
	document.getElementById("popup"+m_popupid).style.display = "block";
	
	
	//m_color is for sub menu like Add master
 	document.getElementById("menuid"+m_popupid).style.color = m_hcolor;
 	document.getElementById("menuid"+m_popupid).style.textDecoration = "none";
    
    
 	//document.getElementById("menuid"+m_popupid).style.backgroundImage = "url('http://202.87.40.52/images/mc_common/nav_on_bg.jpg')";
 	document.getElementById("menuid"+m_popupid).style.backgroundImage = "url('nav_on_bg4.jpg')";
 	
 	
 	//document.getElementById("imgL"+m_popupid).style.backgroundImage = "url('http://202.87.40.52/images/mc_common/nav_curve1.jpg')";
	//document.getElementById("imgR"+m_popupid).style.backgroundImage = "url('http://202.87.40.52/images/mc_common/nav_curve2.jpg')";
	document.getElementById("imgL"+m_popupid).style.backgroundImage = "url('nav_curve11.jpg')";
	document.getElementById("imgR"+m_popupid).style.backgroundImage = "url('nav_curve12.jpg')";
 	
 	m_show = 1;
 
   // if menuid is other than 1 then do not display background image of menuid1 
 	if(m_id!=1){
  	document.getElementById("menuid1").style.backgroundImage = "none"
 	document.getElementById("imgL1").style.backgroundImage = "none"
	document.getElementById("imgR1").style.backgroundImage = "none"
 	document.getElementById("menuid1").style.color = "black";
 	}	
	
 }

function hide_popup()
 {
 	
 	if(m_show == 0 && m_popupid > 0)
 	{
 		if(m_popupid != m_selected)
 		{
 	       
			document.getElementById("popup"+m_selected).style.display = "block";
	 		document.getElementById("popup"+m_popupid).style.display = "none";
 			document.getElementById("menuid"+m_popupid).style.color = m_color;
 			document.getElementById("menuid"+m_popupid).style.backgroundImage = "url('http://202.87.40.52/images/mc_common/nav_off_bg.jpg')";
 			document.getElementById("imgL"+m_popupid).style.backgroundImage = "url('http://202.87.40.52/images/mc_common/nav_off_bg.jpg')";
			document.getElementById("imgR"+m_popupid).style.backgroundImage = "url('http://202.87.40.52/images/mc_common/nav_off_bg.jpg')";
 		}
 		document.getElementById("menuid"+m_popupid).style.textDecoration= "none";
 		m_popupid = 0;
 	}
 }

function mouse_over(m_obj)
{
	m_obj.style.textDecoration= "underline";
	if((m_obj.id).substring(0,8) != "submenu")
	{
		//m_obj.style.backgroundImage = "url('http://202.87.40.52/images/mc_common/nav_on_bg.jpg')";
		//alert("imgL"+((m_obj.id).substring(7,8)));
		//document.getElementById("imgL"+((m_obj.id).substring(7,8))).style.backgroundImage = "url('http://202.87.40.52/images/mc_common/nav_curve1.jpg')";
		//document.getElementById("imgR"+((m_obj.id).substring(7,8))).style.backgroundImage = "url('http://202.87.40.52/images/mc_common/nav_curve2.jpg')";
	}
}

 
function mouse_out(m_obj)
{
	m_obj.style.textDecoration= "none";
}

function mouse_click(m_obj)
{
	if(m_obj.id == "menuid1")
		window.location = "http://www.moneycontrol.com/newsindia/";
	else if(m_obj.id == "menuid2")
		window.location = "http://www.moneycontrol.com/stocksmarketsindia/";
	else if(m_obj.id == "menuid3")
		window.location = "http://www.moneycontrol.com/mutualfundindia/";
	else if(m_obj.id == "menuid4")
		window.location = "http://www.moneycontrol.com/india/bestportfoliomanager/12/10/stockinvestments";
	else if(m_obj.id == "menuid5")
		window.location = "http://www.moneycontrol.com/mccode/services/homebody.php";
	else if(m_obj.id == "menuid6")
		window.location = "http://www.moneycontrol.com/messageboardblogindia/";
	else if(m_obj.id == "menuid7")
		window.location = "http://www.moneycontrol.com/budget2007/pre_budget/";
	else if(m_obj.id == "menuid8")
		window.location = "http://www.moneycontrol.com/personalfinanceindia/";
	else if(m_obj.id == "menuid9")
		window.location = "http://www.moneycontrol.com/trendsindia/";
	else if(m_obj.id == "menuid10"){
		//window.location.target="frmMain";
		//window.location.href="Frame.jsp";
		//window.location= "login1.jsp";
		//window.location.target="frmMain";
		alert("window.location.target");
		
		
		}
		
		//window.open("login1.jsp");
		//window.location = "login1.jsp";	
//**********************changes are made for linking the page with button click**************
	else if(m_obj.id == "menuid11"){
		//window.location = "Frame.jsp";
		//window.location = "http://www.moneycontrol.com/pf/queries/queries.php";
		//'../pages/IndexHome.jsp?new=yes?ref_flag=1'
		}


	/*else if(m_obj.id == "submenu1.1")
		window.location = "/india/news/stockmarket/business/"+hr+"/"+min+"/3";
	else if(m_obj.id == "submenu1.2")
		window.location = "/india/news/stockmarket/markets/"+hr+"/"+min+"/11";
	else if(m_obj.id == "submenu1.3")
		window.location = "/india/news/stockmarket/economy/"+hr+"/"+min+"/26";
	else if(m_obj.id == "submenu1.4")
		window.location = "/india/news/stockmarket/politics/"+hr+"/"+min+"/25";
	else if(m_obj.id == "submenu1.6")
		window.location = "/india/news/stockmarket/interviews/"+hr+"/"+min+"/33";
	else if(m_obj.id == "submenu1.7")
	{
		window.open("http://www.indiaearnings.com/");
		//window.location = "/india/news/stockmarket/companyresults/"+hr+"/"+min+"/27";
	}
	else if(m_obj.id == "submenu1.8")
		window.location = "/india/news/stockmarket/sports/"+hr+"/"+min+"/103";
	else if(m_obj.id == "submenu1.9")
		window.location = "/india/news/stockmarket/worldnews/"+hr+"/"+min+"/104";
	else if(m_obj.id == "submenu1.10")
		window.location = "/india/news/stockmarket/currentaffairs/"+hr+"/"+min+"/105";
	else if(m_obj.id == "submenu1.11")
		window.location = "/india/news/stockmarket/pressreleases/"+hr+"/"+min+"/40";
	else if(m_obj.id == "submenu1.12")
		window.open("/survey/football_survey.php?con=full");*/

	else if(m_obj.id == "submenu2.1")
		window.location = "http://www.moneycontrol.com/stocksmarketsindia/";
	else if(m_obj.id == "submenu2.2")
		window.location = "http://www.moneycontrol.com/mccode/outlook/";
	else if(m_obj.id == "submenu2.3")
		window.location = "http://www.moneycontrol.com/ipo/";
	else if(m_obj.id == "submenu2.4")
		window.location = "http://www.moneycontrol.com/technicals/";
	else if(m_obj.id == "submenu2.5")
	{
		window.open("http://www.indiaearnings.com/");
		
	}	
	else if(m_obj.id == "submenu2.6")
		window.location = "http://www.moneycontrol.com/stocks/marketstats/index.php";
	else if(m_obj.id == "submenu2.7")
		window.location = "http://www.moneycontrol.com/mccode/tools/";	
	
	else if(m_obj.id == "submenu3.1")
		window.location = "http://www.moneycontrol.com/mutualfundindia/";
	else if(m_obj.id == "submenu3.2")
		window.location = "http://www.moneycontrol.com/easymf/learn/";
	else if(m_obj.id == "submenu3.3")
		window.location = "http://www.moneycontrol.com/easymf/evaluate/";
	else if(m_obj.id == "submenu3.4")
		window.location = "http://www.moneycontrol.com/easymf/order_forms/order_forms.php";
	else if(m_obj.id == "submenu3.5")
		window.location = "http://www.moneycontrol.com/easymf/track/";
	else if(m_obj.id == "submenu3.6")
		window.location = "http://www.moneycontrol.com/easymf/tools/";
	else if(m_obj.id == "submenu3.7")
		window.location = "http://www.moneycontrol.com/easymf/interact/";	

	/*else if(m_obj.id == "submenu4.1")
		window.location = "/easymf/evaluate/";
	else if(m_obj.id == "submenu4.2")
		window.location = "/easymf/evaluate/";*/

	else if(m_obj.id == "submenu5.1")
		window.location = "http://www.moneycontrol.com/mccode/services/homebody.php";
	else if(m_obj.id == "submenu5.2")
		window.open("http://www.easymf.com/");
	else if(m_obj.id == "submenu5.3")
		window.open("http://www.poweryourtrade.com/");
	else if(m_obj.id == "submenu5.4")
		window.location = "http://www.moneycontrol.com/pf/queries/queries.php";
	else if(m_obj.id == "submenu5.5")
		window.open ("http://www.moneycontrol.com/cnbctv18/cnbcbestseller/");
	//else if(m_obj.id == "submenu5.6")
		//window.location = "http://www.moneycontrol.com/mccode/services/homebody.php";
	else if(m_obj.id == "submenu5.7")
		window.open("http://homeshop18.com/");

		
	
	/*else if(m_obj.id == "submenu6.1")
		window.location = "/india/messageboardblog/msgboard/"+hr+"/"+min+"/category/Stocks";
	else if(m_obj.id == "submenu6.2")
		window.location = "/india/messageboardblog/viewtradingtips/"+hr+"/"+min+"/stocktips";	
	else if(m_obj.id == "submenu6.13")
		window.open("/cnbctv18/cnbcbestseller/");*/
	

	else if(m_obj.id == "submenu7.1")
		window.location = "http://www.moneycontrol.com/mccode/games/homebody.php";
	else if(m_obj.id == "submenu7.2")
		window.open("http://iiy.moneycontrol.com");
	else if(m_obj.id == "submenu7.3")
		window.open("http://portfolio.moneycontrol.com/sensexchallenge/greatsensex.php");
	

	else if(m_obj.id == "submenu8.1")
		window.location = "http://www.moneycontrol.com/personalfinanceindia/";
	else if(m_obj.id == "submenu8.2")
		window.location = "http://www.moneycontrol.com/taxpage/";
	else if(m_obj.id == "submenu8.3")
		window.location = "http://www.moneycontrol.com/pf/queries/queries.php";
	else if(m_obj.id == "submenu8.4")
		window.location = "http://www.moneycontrol.com/insuranceindia/";
	else if(m_obj.id == "submenu8.5")
		window.location = "http://www.moneycontrol.com/property/";
	else if(m_obj.id == "submenu8.6")
		window.location = "http://www.moneycontrol.com/loans/index.php";
	else if(m_obj.id == "submenu8.7")
		window.location = "http://www.moneycontrol.com/cards/homepage/index.php";
	else if(m_obj.id == "submenu8.8")
		window.location = "http://www.moneycontrol.com/fixedincome/index.php";
	else if(m_obj.id == "submenu8.9")
		window.location = "http://www.moneycontrol.com/mccode/expert/";	
	else if(m_obj.id == "submenu8.10")
		window.location = "http://www.moneycontrol.com/planning_desk/index.php";


	

	/*else if(m_obj.id == "submenu9.1")
		window.open("/cnbctv18/cnbcbestseller/");
	else if(m_obj.id == "submenu10.1")
		window.open("http://www.tv18online.com","tv18");
	else if(m_obj.id == "submenu10.2")
		window.open("http://cnbc-tv18.moneycontrol.com/cnbc/homebodycnbc.php","cnbc_tv18");
	else if(m_obj.id == "submenu10.3")
		window.open("http://www.tv18online.com/awaaz.html","awaaz");
	else if(m_obj.id == "submenu10.4")
		window.open("http://www.southasiaworld.tv","saw");
	else if(m_obj.id == "submenu10.5")
		window.open("http://www.ibnlive.com","ibnlive");
	else if(m_obj.id == "submenu10.7")
		window.open("http://cnbcwatch.moneycontrol.com","cnbcwatch");
	else if(m_obj.id == "submenu10.8")
		window.open("http://poweryourtrade.com","poweryourtrade");*/
}


var IE = document.all?true:false;

function closesitecap()
{
	document.getElementById("sitecap").style.visibility="hidden";
}

function load_ad(m_id, ad_id, m_type, m_width, m_height)
{
	if(!IE)
		var objXml = new XMLHttpRequest();
	else
		var objXml = new ActiveXObject("Microsoft.XMLHTTP");
//alert("123");

	m_referrer = document.referrer;
	m_referrer = m_referrer.substring(7,257);
	m_referrer = m_referrer.replace(/ /g,"");
	m_referrer = m_referrer.replace(/&/g,"|");

	m_url = document.URL;
	m_url = m_url.substring(7,257);
	m_url = m_url.replace(/ /g,"");
	m_url = m_url.replace(/&/g,"|");
	
	if (m_url == "")
	{
		m_url = "N/A";
	}

	datafile = "/adserver/ad_random.php?m_id="+m_id+"&ad_id="+ad_id+"&m_height="+m_height+"&m_width="+m_width+"&m_url="+m_url+"&m_referrer="+m_referrer;
	objXml.open("GET", datafile, true);
	objXml.setRequestHeader("Cache-Control","no-cache"); 

	objXml.onreadystatechange=function() 
		{
			if (objXml.readyState==4) 
			{
			if (objXml.status!=404)
				{	
				   if((objXml.responseText).length > 0)
					{
						//document.write(objXml.responseText);
						document.getElementById(m_id).innerHTML = objXml.responseText;
					}
				}
				//else
				//	alert(objXml.status);
			}
		}
	objXml.send(null);
}

</script>

</body>
</html>