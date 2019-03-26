
<%@page import="com.harrier.initializeation.ConnectInit"%><!--*** page created by Ganesh Ikhar ,02-04-07 -->
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="app.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import=" java.sql.Statement"%>
<%@ page import=" java.io.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="org.apache.struts.util.LabelValueBean"%>
<%@ page import="harrier.com.menu.Menu_Submenu_ActivityData"%>
<%@ page import="harrier.com.menu.Menu"%>
<%@ page import="harrier.com.menu.SubMenu"%>
<%@ page import="harrier.com.menu.Activity"%>
<%@page import="org.apache.log4j.Logger"%>
<%
	Logger log = Logger.getLogger(this.getClass());
	log.info("inside " + this.getClass().getSimpleName() + " page");
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
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String userName1 = new String();
	String userName2 = new String();
	LogonForm form = (LogonForm) session.getAttribute("user");
	//	AcessControl asc=new AcessControl();
	AcessControl asc = ConnectInit.getAcessControl();
	//	System.out.println("0 = "+session.getAttribute("user"));
	if ((form == null) || (session == null)) {
		//	System.out.println("01 = "+session.getAttribute("user"));
%>
<jsp:forward page="simpleJsp1.jsp"></jsp:forward>
<%
	}
	if (form != null) {
		userName1 = form.getUsername();
		userName2 = form.getFname();
		//	System.out.println("02 = "+session.getAttribute("user"));
	}
	String role_id = (String) session.getAttribute("role_id");
	ArrayList menusubact = new Menu_Submenu_ActivityData()
			.getMenuCollection(role_id);

	//	System.out.println("going to menu");
	//	session.setAttribute("user",userName1);
	//	session.setAttribute("fname",userName2);
	//	System.out.println(userName1);
	//	System.out.println(userName2);
%>


<html>
<head>
<title>New UI Enhancement Menu</title>
<link rel="stylesheet" type="text/css" href="common.css">
<link rel="stylesheet" type="text/css" href="top.css">
<link rel="stylesheet" type="text/css" href="text.css">
<style>
.text {
	font-family: arial;
	font-size: 14px;
	color: 0000CC;
	vertical-align: top;
}
</style>

<style>
.black_12_menu {
	font-family: Arial;
	font-size: 12px;
	color: #0A3C6D;
	text-decoration: none;
	font-weight: bold;
}

.black_12_menu:hover {
	font-family: Arial;
	font-size: 12px;
	color: #0A3C6D;
	text-decoration: underline;
	font-weight: bold;
}

.moderator_space {
	width: 222px;
	margin: 0;
	padding-left: 660;
}
</style>

</head>
<body alink="blue" vlink="blue" link="blue" NOWRAP noWrap="true"
	NORESIZE resize="none" onLoad="initialise()">


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
		userName2 = userName2.substring(0, 1).toUpperCase()
				+ userName2.substring(1);
	%>
	<TABLE width="100%">
		<tr>
			<td align="center" NOWRAP noWrap="true" width="20%"><img
				src="hs.jpg"></td>
			<td noWrap="true" width="30%" align="left"><font color="#B22222"
				size="3"><b><bean:message key="User.Welcome" /> <%=userName2%>!</b>
			</font></td>
			<td NOWRAP noWrap="true" width="35%" align="right"><font
				color="#B22222" size="2"><b><a
						href="../pages/contactus.html" Target="frmMain">Contact Us</a> </b> </font></td>
			<td NOWRAP noWrap="true" width="7%" align="right"><font
				color="#B22222" size="2"><b><a
						href="../pages/useraccountinfo.jsp" Target="frmMain"><bean:message
								key="User.MyProfile" /> </a> </b> </font></td>
			<td noWrap="true" width="4%" align="right"><a
				href="./reports/DisplayIndexS.jsp" Target="frmMain"><img
					border="no" src="../images/homeicon3.JPG" /> </a> <!-- href="/Stockpile/pages/reports/DisplayIndexS.jsp" changed by Pranay-->
			</td>
			<td NOWRAP noWrap="true" width="4%" align="right"><img
				src="Logout11.JPEG"> <font color="#B22222" size="2"><b><a
						href="userlogintemp.jsp?action=logout" Target="frmMain"><bean:message
								key="User.Logout" /> </a> </b> </font>&nbsp&nbsp</td>

		</tr>
		<!-- ******************************************* All menu items are put it into following td ********** -->
		<tr>
			<td colspan="6">
				<!-- Main menu  = menuid  from database start Here -->
				<div class="nav_bg6" align="center" NOWRAP noWrap="true" NORESIZE
					resize="none">
					<div id="mainMenu"
						style="display: block; color: black; width: 776px; height: 28px; cursor: pointer;"
						onmouseover="javascript:m_show=1;"
						onmouseout="javascript:m_show=0;">
						<%
							for (int i = 0; i < menusubact.size(); i++) {
								Menu menu = (Menu) menusubact.get(i);
						%>
						<div id="imgML<%=i + 1%>" class="floatL nav_curve2"></div>
						<%
							if (menu.getMenu_Url() == null) {
						%>
						<div id="mainMenu<%=i + 1%>" onmouseover="show(<%=i + 1%>)"
							onmouseout="mouse_out(this)" class="floatL subNav">
							<%
								out.println(menu.getMenu_Name());
							%><span class="top_tdB1 subNav"></span>
						</div>
						<%
							} else {
						%>
						<div id="mainMenu<%=i + 1%>" onmouseover="show(<%=i + 1%>)"
							onmouseout="mouse_out(this)" class="floatL subNav">
							<a href="<%=menu.getMenu_Url()%>"> <%
 	out.println(menu.getMenu_Name());
 %>
							</a><span class="top_tdB1 subNav"></span>
						</div>
						<%
							}
						%>
						<div id="imgMR<%=i + 1%>" class="floatL nav_curve2"></div>
						<div style="padding: 0 3 0 6px;" class="floatL">
							<img src="top_nav_bg6.jpg">
						</div>
						<%
							}
						%>
					</div>
				</div> <!-- use class="nav_bg4" for pink color strip --> <!-- use class="floatL nav_curve1" for pink color vertical strip-->
				<!--USE img src="top_nav_divider1.jpg"  for pink color divider-->
				<div class="nav_bg4" NOWRAP noWrap="true">

					<!--  submenus starts here -->

					<%
						int counterForSubmenuids_Popups = 0;
						for (int i = 0; i < menusubact.size(); i++) {
							Menu menu = (Menu) menusubact.get(i);
					%>

					<!--   Below div each for all submenus of a menu  -->
					<div id="show<%=i + 1%>" align="center"
						style="display: none; color =black; width: 776px; height: 28px; cursor: pointer;">

						<!-- Below div is used to create space on left hand side -->
						<%
							for (int k = 0; k < 6; k++) {
						%>
						<div id="imgL33<%=i + 1%>" class="floatL nav_curve1"></div>
						<%
							}
						%>

						<%
							if (menu.getSubMenuList() != null) {
									for (int j = 0; j < menu.getSubMenuList().size(); j++) {
										SubMenu submenu = (SubMenu) menu.getSubMenuList()
												.get(j);
						%>


						<%
							if (j != 0) {
						%>
						<div style="padding: 0 3 0 6px;" class="floatL">
							<img src="top_nav_divider.jpg" alt="" width="4" height="20"
								hspace="0" vspace="2" border="0">
						</div>
						<%
							}
						%>
						<div id="imgL<%=counterForSubmenuids_Popups + 1%>"
							class="floatL nav_curve1"></div>
						<%
							if (submenu.getSubMenu_Url() == null) {
						%>

						<div id="submenuid<%=counterForSubmenuids_Popups + 1%>"
							onmouseover="show_popup(<%=counterForSubmenuids_Popups + 1%>)"
							onmouseout="javascript:m_show=0;"
							style="padding: 7 4 8 4px; cursor: pointer;" class="floatL">
							<%
								out.println(submenu.getSubMenu_Name());
							%>
						</div>
						<%
							} else {
						%>

						<div id="submenuid<%=counterForSubmenuids_Popups + 1%>"
							onmouseover="show_popup(<%=counterForSubmenuids_Popups + 1%>)"
							onmouseout="javascript:m_show=0;"
							style="padding: 7 4 8 4px; cursor: pointer;" class="floatL">
							<a href="<%=submenu.getSubMenu_Url()%>" target="frmMain"><%=submenu.getSubMenu_Name()%></a>
						</div>
						<%
							}
						%>
						<div id="imgR<%=counterForSubmenuids_Popups + 1%>"
							class="floatL nav_curve1"></div>



						<%
							counterForSubmenuids_Popups++;
									} // for loop of submenu ends here
						%>
					</div>
					<!--  end of Below div each for all submenus of a menu  -->
					<%
						counterForSubmenuids_Popups++;
							} //if
						} // for loop of menu ends here
					%>
				</div> <!--  activities  -->

				<div class="nav_bg3" noWrap="true">
					<!-- Sub-sub menu for Admin/Access Control starts here -->

					<%
						int counterForPopups = 0;
						for (int i = 0; i < menusubact.size(); i++) {
							Menu menu = (Menu) menusubact.get(i);

							if (menu.getSubMenuList() != null) {
								for (int j = 0; j < menu.getSubMenuList().size(); j++) {
									SubMenu submenu = (SubMenu) menu.getSubMenuList()
											.get(j);
					%>
					<div id="popup<%=counterForPopups + 1%>"
						style="display: none; color =white; width: 970px; height: 28px; cursor: pointer;"
						onmouseover="javascript:m_show=1;"
						onmouseout="javascript:m_show=0;">
						<%
							if (submenu.getActivityList() != null) {
											for (int k = 0; k < submenu.getActivityList()
													.size(); k++) {
												Activity activity = (Activity) submenu
														.getActivityList().get(k);

												if (k != (submenu.getActivityList().size() - 1)) {
						%>
						<div id="submenu1.1.1" onmouseover="mouse_over(this)"
							onmouseout="mouse_out(this)" class="floatL subNav">
							<a href="<%=activity.getActivity_Url()%>" target="frmMain"><%=activity.getActivity_Name()%></a><span
								class="top_tdB1 subNav" STYLE="color: white">|</span>
						</div>

						<%
							} else {
						%>
						<div id="submenu1.1.1" onmouseover="mouse_over(this)"
							onmouseout="mouse_out(this)" class="floatL subNav">
							<a href="<%=activity.getActivity_Url()%>" target="frmMain"><%=activity.getActivity_Name()%></a>
						</div>
						<%
							}
											}
										}
										counterForPopups++;
						%>
					</div>
					<%
						} // eof for of submenu	
								counterForPopups++;
							}
						}// eof for of menu
					%>
				</div>

			</td>


		</tr>
	</TABLE>




	<script>
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

// show(m) used for which sub menu should be dispaly for Main menu 'm'
//******************************Show() function starts here *************************************
	function show(m){
		//m==1 for Master
		 
		 menuOldSelected=menuNewSelected;   // interChange old and new valuees of menu selected
		 menuNewSelected=m;
		 //alert("menuoldselected : "+menuOldSelected + "menuNewSelected : "+ menuNewSelected+ "m : "+m);
		//alert("2");
		if(menuOldSelected!=menuNewSelected){
			//alert(" menuold ! = menunew");
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
		
		//added by sushant for popping up popup div dynamically on mouse over on menu 		
		<%counterForPopups = 1;
			int j;
			for (int i = 0; i < menusubact.size(); i++) {
				Menu menu = (Menu) menusubact.get(i);%>			
					if(m==<%=i + 1%>){		
						show_popup(<%=counterForPopups%>);																				
					}	
					
		<%if (menu.getSubMenuList() != null) {
					for (j = 0; j < menu.getSubMenuList().size(); j++) {
						SubMenu submenu = (SubMenu) menu.getSubMenuList()
								.get(j);

						counterForPopups++;
					}
					//added by sushant
					counterForPopups++;
				}
			}%>				
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
 			document.getElementById("submenuid"+m_popupid).style.color = m_color;
 			//alert("============2");
			document.getElementById("submenuid"+m_popupid).style.textDecoration = "none";
			//alert("============3");
			//alert("1");
			//document.getElementById("menuid"+m_popupid).style.backgroundImage = "url('http://202.87.40.52/images/mc_common/off_bg.jpg')";
			document.getElementById("submenuid"+m_popupid).style.backgroundImage = "none";
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
	//alert("inside show_popup and m_popid = "+m_popupid);
 	m_popupid = m_id;
 	//alert("m_id :"  +m_id);
 	document.getElementById("popup"+m_selected).style.display = "none";
	document.getElementById("popup"+m_popupid).style.display = "block";
	
	
	//m_color is for sub menu like Add master
 	document.getElementById("submenuid"+m_popupid).style.color = m_hcolor;
 	document.getElementById("submenuid"+m_popupid).style.textDecoration = "none";
    
    
 	//document.getElementById("menuid"+m_popupid).style.backgroundImage = "url('http://202.87.40.52/images/mc_common/nav_on_bg.jpg')";
 	document.getElementById("submenuid"+m_popupid).style.backgroundImage = "url('nav_on_bg4.jpg')";
 	
 	
 	//document.getElementById("imgL"+m_popupid).style.backgroundImage = "url('http://202.87.40.52/images/mc_common/nav_curve1.jpg')";
	//document.getElementById("imgR"+m_popupid).style.backgroundImage = "url('http://202.87.40.52/images/mc_common/nav_curve2.jpg')";
	document.getElementById("imgL"+m_popupid).style.backgroundImage = "url('nav_curve11.jpg')";
	document.getElementById("imgR"+m_popupid).style.backgroundImage = "url('nav_curve12.jpg')";
 	
 	m_show = 1;
 
   // if menuid is other than 1 then do not display background image of menuid1 
 	if(m_id!=1){
  	document.getElementById("submenuid1").style.backgroundImage = "none"
 	document.getElementById("imgL1").style.backgroundImage = "none"
	document.getElementById("imgR1").style.backgroundImage = "none"
 	document.getElementById("submenuid1").style.color = "black";
 	}	
	
 }
 
 function mouse_over(m_obj)
{
	//alert("hiiiii");
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

</script>
	</boby>
</html>
