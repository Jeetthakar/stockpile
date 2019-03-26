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
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="./StyleSheet.css" rel="stylesheet" type="text/css">
</head>
<!--<body bgcolor="#FFFFFF" text="#000000" TopMargin=0 LeftMargin=0 MarginHeight=0 MarginWidth=0>-->
<meta name="Microsoft Theme" content="none"> 
	<!--"../Script/codethatcalendarstd.js"-->
	<script language="javascript" src="../Script/codethatcalendarstd.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script> 
<body topmargin="0" leftmargin="0" class="b1">
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
			System.out.println("rid is "+rid);
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
<!--<img border="0" src="images/head2.jpg" width="100%" height="75%">-->
<!--<table border="0" cellspacing="0" cellpadding="0" height="150">
  <tr>
   <td width="100%" height="108"><img border="0" src="images/head2.jpg" width="1007" height="124"></td>
  </tr>
</table>-->
<!-- <script language="JavaScript1.2" src="coolmenus31bak28.js"></script> -->
<script language="JavaScript1.2" src="coolmenus31.js"></script>

<script Language="Javascript">
/*****************************************************************************
Default browsercheck - Leave this one
******************************************************************************/
function menuload(){
	alert("in menuload in menu2.jsp");	
	document.forms[0].elements.length=0;
	alert("AFTER ELEMENT ZERO IN MENU");		
}

function lib_bwcheck(){ //Browsercheck (needed) 
this.ver=navigator.appVersion; 
this.agent=navigator.userAgent 
this.dom=document.getElementById?1:0 
this.ie5=(this.ver.indexOf("MSIE 5")>-1 && this.dom)?1:0; 
this.ie6=(this.ver.indexOf("MSIE 6")>-1 && this.dom)?1:0; 
this.ie7=(this.ver.indexOf("MSIE 7")>-1 && this.dom)?1:0; 
this.ie4=(document.all && !this.dom)?1:0; 
this.ie=this.ie4||this.ie5||this.ie6 || this.ie7 
this.mac=this.agent.indexOf("Mac")>-1 
this.opera5=this.agent.indexOf("Opera 5")>-1 
this.ns6=(this.dom && parseInt(this.ver) >= 5) ?1:0; 
this.ns7=(this.dom && parseInt(this.ver) >= 5) ?1:0; 
this.ns4=(document.layers && !this.dom)?1:0; 
this.bw=(this.ie7 || this.ie6 || this.ie5 || this.ie4 || this.ns4 || this.ns6 || this.opera5 || this.dom) 
return this 
} 


var bw=new lib_bwcheck() //Making browsercheck object

var mDebugging=2 //General debugging variable. Set to 0 for no debugging, 1 for alerts or 2 for status debugging.
var arr=new Array();


	
	
oCMenu=new makeCoolMenu("oCMenu") //Making the menu object. Argument: menuname
oCMenu.useframes=1 //Do you want to use the menus as coolframemenu or not? (in frames or not) - Value: 0 || 1
oCMenu.frame="frmMain" //The name of your main frame (where the menus should appear). Leave empty if you're not using frames - Value: "main_frame_name"

oCMenu.useclick=0 //If you want the menu to be activated and deactivated onclick only set this to 1. - Value: 0 || 1

/*If you set this to 1 you will get a "hand" cursor when moving over the links in NS4.
NOTE: This does not apply to the submenus if the menu is used in frames due some mayor problems with NS4*/
oCMenu.useNS4links=1

//After adding the "hover effect" for netscape as well, all styles are lost. But if you want padding add it here.
oCMenu.NS4padding=2

//If you have select boxes close to your menu the menu will check for that and hide them if they are in the way of the menu.
//This feature does unfortunatly not work in NS4!
oCMenu.checkselect=2

/*If you choose to have this code inside a linked js, or if your using frames it's important to set these variables.
This will help you get your links to link to the right place even if your files are in different folders.
The offlineUrl variable is the actual path to the directory where you js file are locally.
This is just so you can test it without uploading. Remember to start it with file:/// and only use slashes, no backward slashes!
Also remember to end with a slash                                                                                                 */
oCMenu.offlineUrl="" //Value: "path_to_menu_file_offline/"
//The onlineUrl variable is the online path to your script. Place in the full path to where your js file is. Remember to end with a slash.
oCMenu.onlineUrl="http://client2/datacode/" //Value: "path_to_menu_file_online/"

oCMenu.pagecheck=1//Do you want the menu to check whether any of the subitems are out of the bouderies of the page and move them in again (this is not perfect but it hould work) - Value: 0 || 1
oCMenu.checkscroll=1//Do you want the menu to check whether the page have scrolled or not? For frames you should always set this to 1. You can set this to 2 if you want this feature only on explorer since netscape doesn't support the window.onscroll this will make netscape slower (only if not using frames) - Value: 0 || 1 || 2
oCMenu.resizecheck=1//Do you want the page to reload if it's resized (This should be on or the menu will crash in Netscape4) - Value: 0 || 1
oCMenu.wait=800 //How long to wait before hiding the menu on mouseout. Netscape 6 is a lot slower then Explorer, so to be sure that it works good enough there you should not have this lower then 500 - Value: milliseconds

//Background bar properties
oCMenu.usebar=0 //If you want to use a background-bar for the top items set this on - Value: 1 || 0
oCMenu.barcolor="Navy" //The color of the background bar - Value: "color"
oCMenu.barwidth="menu" //The width of the background bar. Set this to "menu" if you want it to be the same width as the menu. (this will change to match the border if you have one) - Value: px || "%" || "menu"
oCMenu.barheight="menu" //The height of the background bar. Set this to "menu" if you want it to be the same height as the menu. (this will change to match the border if you have one) - Value: px || "%" || "menu"
oCMenu.barx=0 //The left position of the bar. Set this to "menu" if you want it be the same as the left position of the menu. (this will change to match the border if you have one)  - Value: px || "%" || "menu"
oCMenu.bary=0 //The top position of the bar Set this to "menu" if you want it be the same as the top position of the menu. (this will change to match the border if you have one)  - Value: px || "%" || "menu"
oCMenu.barinheritborder=0 //Set this to 1 if you want the bar to have the same border as the top menus - Value: 0 || 1

//Placement properties
oCMenu.rows=1 //This controls whether the top items is supposed to be laid out in rows or columns. Set to 0 for columns and 1 for row - Value 0 || 1
//if(screen.availWidth >= 1024) {
	oCMenu.fromleft=150 //This is the left position of the menu. (Only in use if menuplacement below is 0 or aligned) (will change to adapt any borders) - Value: px || "%"
	oCMenu.fromtop=0	//This is the left position of the menu. (Only in use if menuplacement below is 0 or aligned) (will change to adapt any borders) - Value: px || "%"
//}
//else{
//	oCMenu.fromleft=481 //This is the left position of the menu. (Only in use if menuplacement below is 0 or aligned) (will change to adapt any borders) - Value: px || "%"
//	oCMenu.fromtop=0 	//This is the left position of the menu. (Only in use if menuplacement below is 0 or aligned) (will change to adapt any borders) - Value: px || "%"
//}
oCMenu.pxbetween=0 //How much space you want between each of the top items. - Value: px || "%"

/*You have several different ways to place the top items.
You can have them right beside eachother (only adding the pxbetween variable)
oCMenu.menuplacement=0

You can have them aligned to one of the sides - This is mostly when not using frames, but can be used in both conditions
Values: (If you get strange results check the fromleft,fromtop and pxbetween variables above)
For menus that are placed in columns (align=left or align=right (se below)) you can align them to the "right" or "center"
For menus that are placed in rows (align=top or align=bottom (se below)) you can align them to the "bottom", "center" or "bottomcenter"
oCMenu.menuplacement="center"

You can also set them directly in pixels: (Remember to have as many array members as you have top items)
oCMenu.menuplacement=new Array(10,20,400,20)

Or you can place in percentage: (remember to use the ' ' around the numbers)


Choose one of those options to get the desired results.
*/
oCMenu.menuplacement="center"

/*
Now we are ready for the properties of each level. For those of that have used the old
coolmenus for coolframemenu I will try and explain how this works like this:
level[0] = top items
level[1] = sub items
level[2] = sub2 items
level[3] = sub3 items and so on....
All menus will inherit the properties, and all properties does only HAVE to be spesifed on the top level.
If a level doesn't have on property spesified it will look for it on the last level that was spesified,
if it still doesn't exist it will get the properties from level[0]

Which means that if you set the background color on level[0] to "black" and doesn't spesify any more levels or doesn't
spesify the background color on the last level you spesified ALL menus will get the color from level[0]

Did that make sense at all? This can be a little hard to understand, look at the different examples on my site
and play with and I am sure you'll get what I mean.
*/

//TOP LEVEL PROPERTIES - ALL OF THESE MUST BE SPESIFIED FOR LEVEL[0]
oCMenu.level[0]=new Array() //Add this for each new level
oCMenu.level[0].width= //The default width for each level[0] (top) items. You can override this on each item by spesifying the width when making the item. - Value: px || "%"
oCMenu.level[0].height=20 //The default height for each level[0] (top) items. You can override this on each item by spesifying the height when making the item. - Value: px || "%"
oCMenu.level[0].bgcoloroff="#314573" //The default background color for each level[0] (top) items. You can override this on each item by spesifying the backgroundcolor when making the item. - Value: "color"
oCMenu.level[0].bgcoloron="#84AADE" //The default "on" background color for each level[0] (top) items. You can override this on each item by spesifying the "on" background color when making the item. - Value: "color"
oCMenu.level[0].textcolor="white" //The default text color for each level[0] (top) items. You can override this on each item by spesifying the text color when making the item. - Value: "color"
oCMenu.level[0].hovercolor="white" //The default "on" text color for each level[0] (top) items. You can override this on each item by spesifying the "on" text color when making the item. - Value: "color"
oCMenu.level[0].style="padding:2px; font-family:arial,helvetica; font-size:11px; font-weight:Bold;text-align:center" //The style for all level[0] (top) items. - Value: "style_settings"
oCMenu.level[0].border=1 //The border size for all level[0] (top) items. - Value: px
oCMenu.level[0].bordercolor="#314573" //The border color for all level[0] (top) items. - Value: "color"
oCMenu.level[0].offsetX=10 //The X offset of the submenus of this item. This does not affect the first submenus, but you need it here so it can be the default value for all levels. - Value: px
oCMenu.level[0].offsetY=0 //The Y offset of the submenus of this item. This does not affect the first submenus, but you need it here so it can be the default value for all levels. - Value: px
oCMenu.level[0].NS4font="verdena,arial,helvetica"
oCMenu.level[0].NS4fontSize="2"

/*New: Added animation features that can be controlled on each level.*/
oCMenu.level[0].clip=0 //Set this to 1 if you want the submenus of this level to "slide" open in a animated clip effect. - Value: 0 || 1
oCMenu.level[0].clippx=0 //If you have clip spesified you can set how many pixels it will clip each timer in here to control the speed of the animation. - Value: px
oCMenu.level[0].cliptim=0 //This is the speed of the timer for the clip effect. Play with this and the clippx to get the desired speed for the clip effect (be carefull though and try and keep this value as high or possible or you can get problems with NS4). - Value: milliseconds
//Filters - This can be used to get some very nice effect like fade, slide, stars and so on. EXPLORER5.5+ ONLY - If you set this to a value it will override the clip on the supported browsers
oCMenu.level[0].filter=0 //VALUE: 0 || "filter specs"

/*And last but not least the align variable.

This spesifies how the submenus of this level comes out.
Values:
"bottom": The sub menus of this level will come out on the top of this item
"top": The sub menus of this level will come out on the bottom of this item
"left": The sub menus of this level will come out on the right of this item
"right": The sub menus of this level will come out on the left of this item

In generally "left" and "right" works best for menus in columns and "top" and "bottom" works best for menus in rows.
But by all means feel free to play with it.

If you have set pagecheck to 1 above this is what the pagecheck will change when reaching the bounderies of the page.
If it reaches the right boundery and it's aligned left it will change the align to right and so on.
*/
oCMenu.level[0].align="bottom" //Value: "top" || "bottom" || "left" || "right"

//EXAMPLE SUB LEVEL[1] PROPERTIES - You have to spesify the properties you want different from LEVEL[0] - If you want all items to look the same just remove this
oCMenu.level[1]=new Array() //Add this for each new level (adding one to the number)
oCMenu.level[1].bgcoloroff="#314573" //The default background color for each level[0] (top) items. You can override this on each item by spesifying the backgroundcolor when making the item. - Value: "color"
oCMenu.level[1].bgcoloron="#84AADE"
oCMenu.level[1].width=100
oCMenu.level[1].height=20
oCMenu.level[1].style="padding:2px; font-family:arial,helvetica; font-size:11px; font-weight:Bold; font-color:black;"
oCMenu.level[1].align="middle"
oCMenu.level[1].offsetX=0
oCMenu.level[1].offsetY=5
oCMenu.level[1].border=1
oCMenu.level[1].arrow="Bullet.jpg"
oCMenu.level[1].arrowheight=10
oCMenu.level[1].arrowWidth=10
oCMenu.level[1].bordercolor="#ffffff"


//EXAMPLE SUB LEVEL[2] PROPERTIES - You have to spesify the properties you want different from LEVEL[1] OR LEVEL[0] - If you want all items to look the same just remove this
oCMenu.level[2]=new Array() //Add this for each new level (adding one to the number)
oCMenu.level[2].width=150
oCMenu.level[2].height=20
oCMenu.level[2].style="padding:2px; font-family:verdana,arial,helvetica,tahoma; font-size:9px; font-weight:Normal"
oCMenu.level[2].align="middle"
oCMenu.level[2].offsetX=-20
oCMenu.level[2].offsetY=0
oCMenu.level[2].border=1
oCMenu.level[2].bordercolor="#ffffff"
oCMenu.level[2].NS4font="verdana,arial,helvetica"
oCMenu.level[2].NS4fontSize="1"


	//myCoolMenu.makeMenu(name, parent_name, text, link, target, width, height, regImage, overImage, regClass, overClass , align, rows, nolink, onclick, onmouseover, onmouseout)
		  
  		if(document.forms[0].elements.length!=1){  
  		for(var i=0;i < document.forms[0].elements.length;i+=2)
		{
			var e = document.forms[0].elements[i];//get activity code
			var e_name = document.forms[0].elements[i+1];//get activity name
			//check for activity code allowed for user if true display in menu.
			if (e.name=="menuid" && (e.value=="M1" ))
			{
			 	oCMenu.makeMenu('top0','',e_name.value,'','frmMain',120)
			}
			if (e.name=="menuid" && (e.value=="M2" ))
			{
		  		oCMenu.makeMenu('top1','',e_name.value,'','frmMain',70)
		  	}
		  	if (e.name=="menuid" && (e.value=="M3" ))
			{
		  		oCMenu.makeMenu('top6','',e_name.value,'','frmMain',70)
		  	}
		  	if (e.name=="menuid" && (e.value=="M21" ))
			{
		  		oCMenu.makeMenu('top8','','Fixed Income','','frmMain',100)
		  	}
		  	
		 	
		  	if (e.name=="menuid" && (e.value=="M5" ))
			{
		  		oCMenu.makeMenu('top2','',e_name.value,'','frmMain',100)
		  	}
		  	
		  	
			if (e.name=="menuid" && (e.value=="M8" ))
			{
  		 		oCMenu.makeMenu('sub22','top2',e_name.value,'','frmMain')  		 		
  		 	}
  		 	if (e.name=="menuid" && (e.value=="M9" ))
			{
  		 		oCMenu.makeMenu('top3','',e_name.value,'','frmMain',100)
  		 	}
  		 	if (e.name=="menuid" && (e.value=="M4" ))
			{
		  		oCMenu.makeMenu('top4','',e_name.value,'','frmMain',120)	   	  		
		  	}
  		 	if (e.name=="menuid" && (e.value=="M10" ))
			{
  		 		oCMenu.makeMenu('sub30','top3',e_name.value,'','frmMain')
  		 	}
  		 	
  		 	if (e.name=="menuid" && (e.value=="M11" ))
			{
  		 		oCMenu.makeMenu('sub3011','sub30',e_name.value,'','frmMain')
  		 	}
  		 	if (e.name=="menuid" && (e.value=="M12" ))
			{
  		 		oCMenu.makeMenu('sub31','top3',e_name.value,'','frmMain')
  		 	}
  		 	if (e.name=="menuid" && (e.value=="M13" ))
			{
  		 		oCMenu.makeMenu('sub32','top3',e_name.value,'','frmMain')
  		 	}
  		 	
  		 	if (e.name=="menuid" && (e.value=="M17" ))
			{
  		 		oCMenu.makeMenu('top5','',e_name.value,'','frmMain',100)
  		 	}
  		 	
		  	if (e.name=="menuid" && (e.value=="M14" ))
			{
		  		oCMenu.makeMenu('top7','',e_name.value,'../userlogintemp.jsp?action=logout','frmMain',70)   	  		
		  	}
		  	
  		}  			
  		for(var i=0;i < document.forms[0].elements.length;i+=2)
		{
			var e = document.forms[0].elements[i];//get activity code
			var e_name = document.forms[0].elements[i+1];//get activity name
			//check for activity code allowed for user if true display in menu.
			if (e.name=="roleid" && (e.value=="1")){
  			oCMenu.makeMenu('sub04','top6',e_name.value,'../pages/NewIndexDefine.jsp?new=yes','frmMain',140)
  			}
  			if (e.name=="roleid" && (e.value=="168")){
  			oCMenu.makeMenu('sub689','top6',e_name.value,'../pages/NewIndexDefineRegister.jsp?new=yes','frmMain',140)
  			}
  			if (e.name=="roleid" && (e.value=="7" )){
				oCMenu.makeMenu('sub05','top6',e_name.value,'../pages/masters/AddCapturedIndex.jsp','frmMain',140)
			}
			if (e.name=="roleid" && (e.value=="8" )){
				oCMenu.makeMenu('sub11','top6',e_name.value,'../pages/IndexHome.jsp?new=yes?ref_flag=1','frmMain',140)
			}
			
			if (e.name=="roleid" && (e.value=="78" )){
					oCMenu.makeMenu('sub06','top6',e_name.value,'../pages/indexUpdateView.jsp','frmMain',140)
			}
			if (e.name=="roleid" && (e.value=="165" )){
					oCMenu.makeMenu('sub165','top6','Index Back Tracking','../pages/BackTestIndex.jsp','frmMain',140)
			}
			if (e.name=="roleid" && (e.value=="89" ))
			{
  			oCMenu.makeMenu('subic021','top4',e_name.value,'../pages/masters/classes.jsp?from=menu','frmMain',140)
  			}
  			if (e.name=="roleid" && (e.value=="90" ))
			{
  			oCMenu.makeMenu('subic022','top4',e_name.value,'../pages/masters/classificationLevel.jsp?from=menu','frmMain',140)
  			}
  			if (e.name=="roleid" && (e.value=="91" ))
			{
  			oCMenu.makeMenu('subic023','top4',e_name.value,'../pages/masters/company.jsp?from=menu','frmMain',140)
  			}
  			if (e.name=="roleid" && (e.value=="164" )){
				oCMenu.makeMenu('sub3099','top3','Portfolio Report','../pages/reports/PortfolioReport.jsp?FromLogin=yes','frmMain')	
			}
			if (e.name=="roleid" && (e.value=="178" )){
				oCMenu.makeMenu('sub3599','top3','My Profile','../pages/useraccountinfo.jsp','frmMain')	
			}
  			
  			if (e.name=="roleid" && (e.value=="92" ))
			{
  			oCMenu.makeMenu('subic024','top4',e_name.value,'../pages/masters/industryClassificationMaster.jsp?from=menu','frmMain',140)
  			}
			if (e.name=="roleid" && (e.value=="2" ))
			{
  			oCMenu.makeMenu('sub03','top1',e_name.value,'../pages/masters/stockmaster2.jsp?from=menu','frmMain',120)
  			}
  			//if (e.name=="roleid" && (e.value=="148" ))
			// {
  			// oCMenu.makeMenu('sub001','top0','Report Preferences','../pages/reports/ReportPreName.jsp','frmMain',120)
  			// }
  		
  			
			if (e.name=="roleid" && (e.value=="113" ))
			{
			oCMenu.makeMenu('sub08','top1',e_name.value,'../pages/masters/commodities.jsp?from=menu','frmMain',120)
			}
			if (e.name=="roleid" && (e.value=="114" )){
					oCMenu.makeMenu('sub09','top1',e_name.value,'../pages/fixedincome/StockMasterBonds.jsp?from=menu','frmMain',120)
			}
			if (e.name=="roleid" && (e.value=="112" )){
				oCMenu.makeMenu('sub112','top1',e_name.value,'../pages/masters/IndComplianceMaster.jsp?from=menu','frmMain',120)
			}
				
				if (e.name=="roleid" && (e.value=="73" )){
					oCMenu.makeMenu('sub019','top0',e_name.value,'../pages/masters/sysConfigView.jsp?from=menu','frmMain',120)
				}
				
				if (e.name=="roleid" && (e.value=="17" )){
					oCMenu.makeMenu('sub07','top1',e_name.value,'../pages/masters/frtocurrency.jsp?from=menu','frmMain',120)
				}
  			
						
			if (e.name=="roleid" && (e.value=="33" )){
				oCMenu.makeMenu('sub13','top0',e_name.value,'../pages/ImportNewStock.jsp','frmMain',120)	
			}
			if (e.name=="roleid" && (e.value=="179" )){
				oCMenu.makeMenu('sub13','top0','Cancel Subscription','../pages/cancelsubscription.jsp','frmMain',120)	
			}
			
			if (e.name=="roleid" && (e.value=="115" )){
				oCMenu.makeMenu('sub14','top4',e_name.value,'../pages/GICSFileDialog.jsp','frmMain',140)	
			}
			if (e.name=="roleid" && (e.value=="116" )){
				oCMenu.makeMenu('sub15','top0',e_name.value,'../pages/DatFileUpload.jsp','frmMain',120)	
			}
			if (e.name=="roleid" && (e.value=="3" )){
				oCMenu.makeMenu('sub23','top2',e_name.value,'../pages/CorporateDiary.jsp?ref_flag=1','frmMain')
			}
			
				
				if (e.name=="roleid" && (e.value=="18" )){			
					oCMenu.makeMenu('sub2110','sub211',e_name.value,'../pages/NCorporateAction.jsp?corp_name=cashdividend&flag=New&ref_flag=1','frmMain')
				}
				if (e.name=="roleid" && (e.value=="19" )){
					oCMenu.makeMenu('sub2111','sub211',e_name.value,'../pages/NCorporateAction.jsp?corp_name=stockdividend/bonus&flag=New&ref_flag=1','frmMain')
				}
				if (e.name=="roleid" && (e.value=="20" )){
					oCMenu.makeMenu('sub2112','sub211',e_name.value,'../pages/NCorporateAction.jsp?corp_name=spin-off&flag=New&ref_flag=1','frmMain')			
				}
				
				
				
				
				
				
			
				
				
				if (e.name=="roleid" && (e.value=="29" )){
					oCMenu.makeMenu('sub220','sub22',e_name.value,'../pages/IndexEvents.jsp?ref_flag=1&corp_name=addstock','frmMain')		
				}
				if (e.name=="roleid" && (e.value=="30" )){
					oCMenu.makeMenu('sub221','sub22',e_name.value,'../pages/IndexEvents.jsp?ref_flag=1&corp_name=changeiwf','frmMain')	
				}
				if (e.name=="roleid" && (e.value=="31" )){
					oCMenu.makeMenu('sub222','sub22',e_name.value,'../pages/IndexEvents.jsp?ref_flag=1&corp_name=deletestock','frmMain')
				}
				if (e.name=="roleid" && (e.value=="32" )){
					oCMenu.makeMenu('sub223','sub22',e_name.value,'../pages/IndexEvents.jsp?ref_flag=1&corp_name=rebasing','frmMain')	
				}
				if (e.name=="roleid" && (e.value=="111" )){
					oCMenu.makeMenu('sub224','sub22',e_name.value,'../pages/StockMerger.jsp?ref_flag=1','frmMain')	
				}
				if (e.name=="roleid" && (e.value=="88" )){
					oCMenu.makeMenu('sub225','sub22',e_name.value,'../pages/IndexEvents.jsp?ref_flag=1&corp_name=8','frmMain')	
				}
				if (e.name=="roleid" && (e.value=="80" )){
					//oCMenu.makeMenu('sub30111','sub3011',e_name.value,'../pages/IndexCalculator.jsp','frmMain')
					oCMenu.makeMenu('sub30111','sub3011',e_name.value,'../pages/reports/IndexCalculator.jsp','frmMain')
				}
				if (e.name=="roleid" && (e.value=="81" )){
					//oCMenu.makeMenu('sub30112','sub3011',e_name.value,'../pages/PortpolioTisCalculator.jsp','frmMain')
					oCMenu.makeMenu('sub30112','sub3011',e_name.value,'../pages/reports/PortpolioTisCalculator.jsp','frmMain')
				}
				if (e.name=="roleid" && (e.value=="35" )){				
    				//oCMenu.makeMenu('sub300','sub30',e_name.value,'../pages/IndexDivisor.jsp','frmMain')
    				oCMenu.makeMenu('sub300','sub30',e_name.value,'../pages/reports/Index_DivisorS.jsp?ajax1=no','frmMain')
				}
				if (e.name=="roleid" && (e.value=="36" )){
					//oCMenu.makeMenu('sub301','sub30',e_name.value,'../pages/LatestIndexDivisor.jsp','frmMain',150,25)
					oCMenu.makeMenu('sub301','sub30',e_name.value,'../pages/reports/LatestIndexDivisors.jsp','frmMain',150,25)
				}
				if (e.name=="roleid" && (e.value=="37" )){
					//oCMenu.makeMenu('sub302','sub30',e_name.value,'../pages/IndexCompare1.jsp','frmMain')
					// this code is added by Ganesh to reset some properties on:23.01.07 
					oCMenu.makeMenu('sub302','sub30',e_name.value,'../pages/reports/IndexCompare1S.jsp?FlagForReset=yes&ajax1=no','frmMain')
				}
				if (e.name=="roleid" && (e.value=="38" )){
					oCMenu.makeMenu('sub303','sub30',e_name.value,'../pages/reports/IndexCompareOHLC.jsp','frmMain',150,25)
				}
				
				
				
				
				
				
				
				if (e.name=="roleid" && (e.value=="39" )){
					//oCMenu.makeMenu('sub304','sub30',e_name.value,'../pages/indexcompose.jsp','frmMain')
					oCMenu.makeMenu('sub304','sub30',e_name.value,'../pages/reports/IndexComposeS.jsp?ajax1=no','frmMain')
				}
				if (e.name=="roleid" && (e.value=="40" )){
					//oCMenu.makeMenu('sub305','sub30',e_name.value,'../pages/movingindexvalue.jsp','frmMain')
					oCMenu.makeMenu('sub305','sub30',e_name.value,'../pages/reports/MoveIndexValueS.jsp?ajax1=no','frmMain')
				}
				if (e.name=="roleid" && (e.value=="41" )){
				//oCMenu.makeMenu('sub306','sub30',e_name.value,'../pages/IndexPerformance.jsp','frmMain')
					oCMenu.makeMenu('sub306','sub30',e_name.value,'../pages/reports/IndexPerformanceS.jsp?ajax2=no','frmMain')
				}				
  				//if (e.name=="roleid" && (e.value=="55" )){
  					//oCMenu.makeMenu('sub307','sub30',e_name.value,'../pages/DisplayIndexes1.jsp?FromLogin=No','frmMain')
  					//oCMenu.makeMenu('sub307','sub30',e_name.value,'../pages/reports/DisplayIndexS.jsp?FromLogin=No','frmMain')
  				//}
				
				if (e.name=="roleid" && (e.value=="55" )){
  					//oCMenu.makeMenu('sub307','sub30',e_name.value,'../pages/DisplayIndexes1.jsp?FromLogin=No','frmMain')
  					oCMenu.makeMenu('sub307','sub30',e_name.value,'../pages/reports/IndexListReport.jsp?FromLogin=No','frmMain')
  				}
				
				
				//if (e.name=="roleid" && (e.value=="42" )){
				//	oCMenu.makeMenu('sub308','sub30',e_name.value,'../pages/StockPerformanceReport.jsp','frmMain')
				//}
				if (e.name=="roleid" && (e.value=="43" )){
					//oCMenu.makeMenu('sub309','sub30',e_name.value,'../pages/IndexReturnsVolatility.jsp','frmMain')
					oCMenu.makeMenu('sub309','sub30',e_name.value,'../pages/reports/IndexReturns_VolatilityS.jsp?ajax1=no','frmMain')
				}
				if (e.name=="roleid" && (e.value=="44" )){
					//oCMenu.makeMenu('sub309','sub30',e_name.value,'../pages/IndexReturnsVolatility.jsp','frmMain')
					oCMenu.makeMenu('sub3010','sub30',e_name.value,'../pages/reports/IndexCorrelation.jsp','frmMain')
				}
				if (e.name=="roleid" && (e.value=="83" )){
					//oCMenu.makeMenu('sub3012','sub30',e_name.value,'../pages/Indexpe_pb.jsp','frmMain')
					oCMenu.makeMenu('sub3012','sub30',e_name.value,'../pages/reports/Indexpe_pbS.jsp?ajax1=no','frmMain')
				}
				if (e.name=="roleid" && (e.value=="95" )){
					//oCMenu.makeMenu('sub3013','sub30',e_name.value,'../pages/IndexCurrencyWise.jsp','frmMain')
					oCMenu.makeMenu('sub3013','sub30',e_name.value,'../pages/reports/IndexCurrencyWiseS.jsp','frmMain')
				}	
				if (e.name=="roleid" && (e.value=="99" )){
					oCMenu.makeMenu('sub3014','sub30',e_name.value,'../pages/CalculateTotalReturns.jsp','frmMain')
				}				
				if (e.name=="roleid" && (e.value=="96" )){
					//oCMenu.makeMenu('sub311','sub31',e_name.value,'../pages/StockHighLow1.jsp?refFlag=1','frmMain')
					oCMenu.makeMenu('sub311','sub31',e_name.value,'../pages/reports/StockHighLowS.jsp?refFlag=1&ajax1=no','frmMain')
				} 
				if (e.name=="roleid" && (e.value=="46" )){
					oCMenu.makeMenu('sub312','sub31',e_name.value,'../pages/reports/CapitalChangeToUniverseS.jsp?refFlag=1&ajax1=no','frmMain')
				}
				//if (e.name=="roleid" && (e.value=="47" )){
				//	oCMenu.makeMenu('sub313','sub31',e_name.value,'../pages/ShareHoldingPattern.jsp','frmMain')		
				//}
				//if (e.name=="roleid" && (e.value=="48" )){
				//	oCMenu.makeMenu('sub314','sub31',e_name.value,'../pages/ChangeInStockDetail.jsp?refFlag=1','frmMain')		
				//}
				if (e.name=="roleid" && (e.value=="56" )){			
  					oCMenu.makeMenu('sub315','sub31',e_name.value,'../pages/reports/StockListS.jsp?refFlag=1','frmMain')
  				}
  				if (e.name=="roleid" && (e.value=="79" )){			
  					oCMenu.makeMenu('sub316','sub31',e_name.value,'../pages/reports/InactiveStockListS.jsp?refFlag=1&ajax1=n0','frmMain')
  				}
  				if (e.name=="roleid" && (e.value=="84" )){			
  					oCMenu.makeMenu('sub317','sub31',e_name.value,'../pages/reports/StockDividentS.jsp?ajax1=no','frmMain')
  				}
  				if (e.name=="roleid" && (e.value=="85" )){			
  					oCMenu.makeMenu('sub318','sub31',e_name.value,'../pages/reports/TradedVolumeInd_exchS.jsp?refFlag=1&ajax1=no','frmMain')
  				}
  				/* if (e.name=="roleid" && (e.value=="45" )){			
  					//oCMenu.makeMenu('sub319','sub31',e_name.value,'../pages/stockDetailFromDate.jsp?refFlag=1','frmMain')
  					oCMenu.makeMenu('sub319','sub31',e_name.value,'../pages/reports/StockDetailFromDateS.jsp?refFlag=1','frmMain')
  				} */
  				if (e.name=="roleid" && (e.value=="98" )){			
  					oCMenu.makeMenu('sub3110','sub31',e_name.value,'../pages/stockWisePe_Pb.jsp?refFlag=1','frmMain')
  				}
				if (e.name=="roleid" && (e.value=="49" )){
					oCMenu.makeMenu('sub320','sub32',e_name.value,'../pages/PageNotImplemented.jsp','frmMain')
			//oCMenu.makeMenu('sub320','sub32',e_name.value,'../pages/PageNotImplemented.jsp','frmMain')					
				}
				if (e.name=="roleid" && (e.value=="50" )){
				//oCMenu.makeMenu('sub321','sub32',e_name.value,'../pages/PageNotImplemented.jsp','frmMain')
					//oCMenu.makeMenu('sub321','sub32',e_name.value,'../pages/CompanyWiseWeightageS.jsp','frmMain')
					oCMenu.makeMenu('sub321','sub32',e_name.value,'../pages/reports/CompanyWiseWeightageS.jsp?ajax1=no','frmMain')
					
				}
				if (e.name=="roleid" && (e.value=="51" )){
					oCMenu.makeMenu('sub322','sub32',e_name.value,'../pages/reports/IndWiseWeightS.jsp?ajax1=no','frmMain')	
				}
				if (e.name=="roleid" && (e.value=="52" )){
					//oCMenu.makeMenu('sub323','sub32',e_name.value,'../pages/StockContritoIndexChange.jsp','frmMain',150,30)
					oCMenu.makeMenu('sub323','sub32',e_name.value,'../pages/reports/StockContriToIndexChangeS.jsp?ajax1=no','frmMain',150,30)
  	 			}
  	 			//if (e.name=="roleid" && (e.value=="74" )){
  	 			//	oCMenu.makeMenu('sub33','top3',e_name.value,'../pages/MarketStatusDateWise.jsp','frmMain')
  	 			//}
  	 			
  	 			if (e.name=="roleid" && (e.value=="1223" )){
					oCMenu.makeMenu('sub3016','sub30',e_name.value,'../pages/IndexChange.jsp','frmMain')
				}
			
				
				if (e.name=="roleid" && (e.value=="147" )){			
  					//oCMenu.makeMenu('sub317','sub31',e_name.value,'../pages/changepass.jsp','frmMain'),140e_name.value,
  					//Hard coded value entered by sudhir 16.11.06
  				oCMenu.makeMenu('sub1112','top0','Change Password','../pages/changepass.jsp','frmMain',120)  					
  				}
    	 	/*==========  MAsters -  PAT Computation      ======= */
    	 		if (e.name=="roleid" && (e.value=="153" )){
    	 			oCMenu.makeMenu('sub111','top5','Financial Entry','../pages/FinanceResult.jsp?from=menu','frmMain',120)	
				} 
				
				/*==========  Admin -  PAT Computation      ======= */
				if (e.name=="roleid" && (e.value=="152" )){
					oCMenu.makeMenu('sub1113','top5','PE Computation','../pages/Pat.jsp?new=yes','frmMain',120)		
				} 
				if (e.name=="roleid" && (e.value=="168" )){
					oCMenu.makeMenu('sub1114','top8','Add Index','../pages/fixedincome/FixedIncomeDefineIndex.jsp','frmMain',100)		
				} 
				if (e.name=="roleid" && (e.value=="170" )){
					oCMenu.makeMenu('sub1116','top8','Fixed Income Event','../pages/fixedincome/FixedIncomeNCorporateAction.jsp','frmMain',100)		
				} 
				if (e.name=="roleid" && (e.value=="169" )){
					oCMenu.makeMenu('sub1115','top8','Compute Index','../pages/fixedincome/FixedIncomeIndexHome.jsp','frmMain',100)		
				} 
  		}
  		
		}
		

oCMenu.makeStyle();
oCMenu.construct()		

</script>
</body>
</html>
