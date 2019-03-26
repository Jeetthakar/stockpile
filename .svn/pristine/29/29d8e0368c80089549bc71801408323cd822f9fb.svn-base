<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<jsp:useBean id="regiform" scope="session"
	class="subscription.form.subscribeUserForm" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<meta http-equiv="Content-Type"
			content="text/html; charset=iso-8859-1" />

		<title>Harrier</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<META content="MSHTML 6.00.2900.2180" name=GENERATOR>
		<LINK href="htl accreation_files/global.css" type=text/css
			rel=stylesheet></LINK>
		<LINK href="htl accreation_files/global_reg.css" type=text/css
			rel=stylesheet></LINK>
		<LINK href="htl accreation_files/global_p.css" type=text/css
			rel=stylesheet></LINK>

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

.redstar {
	color: #FF0000
}

.redtext {
	font-family: Tahoma;
	font-size: 14px;
	color: #FF0000;
}

.bp_invalid {
	BACKGROUND: 'red';
	COLOR: 'white';
}

.bp_valid {
	COLOR: 'green';
}

.dojoDialog {
	BORDER-RIGHT: blue 1px solid;
	PADDING-RIGHT: 6px;
	BORDER-TOP: blue 1px solid;
	PADDING-LEFT: 6px;
	BACKGROUND: #ffffff;
	PADDING-BOTTOM: 6px;
	BORDER-LEFT: blue 1px solid;
	WIDTH: 100px;
	PADDING-TOP: 6px;
	BORDER-BOTTOM: blue 1px solid;
	HEIGHT: 10px;
	moz-border-radius: 5px
}
-->
</style>
	</head>

	<script language="javascript" src="box_ex.js"></script>
	<script language="javascript" src="../Script/codethatcalendarstd.js"></script>

	<script type='text/javascript'
		src='/Stockpile/dwr/interface/GraphMethods.js'></script>
	<script type='text/javascript' src='/Stockpile/dwr/engine.js'></script>
	<script type='text/javascript' src='/Stockpile/dwr/util.js'></script>

	<!-- Changes for Register user -->

	<SCRIPT src="htl accreation_files/dojo.js" type='text/javascript'></SCRIPT>
	<SCRIPT src="htl accreation_files/flights_web_mmtDetails.js"
		type='text/javascript'></SCRIPT>


	<SCRIPT type=text/javascript><!--




		dojo.require("dojo.widget.Dialog");
        dojo.require("dojo.event.*");
        dojo.require("dojo.io.*");
        // Load Dojo's code relating to widget managing functions
        dojo.require("dojo.widget.*");
        // Load Dojo's code relating to the Button widget
        dojo.require("dojo.widget.HtmlWidget");
		dojo.require("dojo.widget.TitlePane");
        dojo.hostenv.writeIncludes();
		var dlgUserValidation;
		function userValidationInitProgress() {
			dlgUserValidation = dojo.widget.byId("LoginDialogContentBlocking");
		}		

function checkCheckBox()
{

document.forms[0].reset();
alert('you must agree the terms and condition');

}
function checkName()
{
		var flag=0;
		var iChars = ".()!@#$%^&*=-[]\\\';,/{}|\":<>?0123456789";
		var mChars = "!@#.()$%^&*+=[]\\\';,/{}|\":<>?0123456789";
		
		
		if(document.forms[0].firstname.value!='')
		{
			if (iChars.indexOf(document.forms[0].firstname.value.charAt(0)) != -1) 
			{
  				flag=1;
  				document.getElementById("chkfirstname").style.display="block";
				document.forms[0].firstname.value='';
				document.forms[0].firstname.focus();
  				
  			}
  			
			else 
  			{
  				for (var i = 0; i < document.forms[0].firstname.value.length; i++) 
  				{
  					if ((mChars.indexOf(document.forms[0].firstname.value.charAt(i)) != -1) )
  					{
  						flag=1;
  						//alert ("Special character or alphabet not allowed between Mobile Number.");
  						document.getElementById("chkfirstname").style.display="block";
						//document.forms[0].firstName.value='';
						document.forms[0].firstname.focus();
						return false;
  					}
  				}
  			}
  			
  			if(flag==0)
  				document.getElementById("chkfirstname").style.display="none";
  			
		}	
		
		if(document.forms[0].lastname.value!='')
		{
			if (iChars.indexOf(document.forms[0].lastname.value.charAt(0)) != -1) 
			{
  				flag=1;
  				//alert ("Invalid Mobile No.");
  				document.getElementById("chklastname").style.display="block";
				document.forms[0].lastname.value='';
				document.forms[0].lastname.focus();
  				
  			}
  			
			else 
  			{
  				for (var i = 0; i < document.forms[0].lastname.value.length; i++) 
  				{
  					if ((mChars.indexOf(document.forms[0].lastname.value.charAt(i)) != -1) )
  					{
  						flag=1;
  						//alert ("Special character or alphabet not allowed between Mobile Number.");
  						document.getElementById("chklastname").style.display="block";
						//document.forms[0].lastName.value='';
						document.forms[0].lastname.focus();
						return false;
  					}
  				}
  			}
			
  			if(flag==0)
  				document.getElementById("chklastname").style.display="none";
  			
		}	
		
		if(document.forms[0].city.value!='')
		{
			if (iChars.indexOf(document.forms[0].city.value.charAt(0)) != -1) 
			{
  				flag=1;
  				//alert ("Invalid Mobile No.");
  				document.getElementById("chkcity").style.display="block";
				document.forms[0].city.value='';
				document.forms[0].city.focus();
  				
  			}
  			
			else 
  			{
  				for (var i = 0; i < document.forms[0].city.value.length; i++) 
  				{
  					if ((mChars.indexOf(document.forms[0].city.value.charAt(i)) != -1) )
  					{
  						flag=1;
  						//alert ("Special character or alphabet not allowed between Mobile Number.");
  						document.getElementById("chkcity").style.display="block";
						//document.forms[0].firstName.value='';
						document.forms[0].city.focus();
						return false;
  					}
  				}
  			}
  			
  			if(flag==0)
  				document.getElementById("chkcity").style.display="none";
  			
		}	
		
		if(document.forms[0].state.value!='')
		{
			if (iChars.indexOf(document.forms[0].state.value.charAt(0)) != -1) 
			{
  				flag=1;
  				//alert ("Invalid Mobile No.");
  				document.getElementById("chkstate").style.display="block";
				document.forms[0].city.value='';
				document.forms[0].city.focus();
  				
  			}
  			
			else 
  			{
  				for (var i = 0; i < document.forms[0].state.value.length; i++) 
  				{
  					if ((mChars.indexOf(document.forms[0].state.value.charAt(i)) != -1) )
  					{
  						flag=1;
  						//alert ("Special character or alphabet not allowed between Mobile Number.");
  						document.getElementById("chkstate").style.display="block";
						//document.forms[0].firstName.value='';
						document.forms[0].state.focus();
						return false;
  					}
  				}
  			}
  			
  			if(flag==0)
  				document.getElementById("chkstate").style.display="none";
  			
		}	
		
		if(document.forms[0].country.value!='')
		{
			if (iChars.indexOf(document.forms[0].country.value.charAt(0)) != -1) 
			{
  				flag=1;
  				//alert ("Invalid Mobile No.");
  				document.getElementById("chkcountry").style.display="block";
				document.forms[0].country.value='';
				document.forms[0].country.focus();
  				
  			}
  			
			else 
  			{
  				for (var i = 0; i < document.forms[0].country.value.length; i++) 
  				{
  					if ((mChars.indexOf(document.forms[0].country.value.charAt(i)) != -1) )
  					{
  						flag=1;
  						//alert ("Special character or alphabet not allowed between Mobile Number.");
  						document.getElementById("chkcountry").style.display="block";
						//document.forms[0].firstName.value='';
						document.forms[0].country.focus();
						return false;
  					}
  				}
  			}
  			
  			if(flag==0)
  				document.getElementById("chkcountry").style.display="none";
  			
		}	
		
		
		
		
		
		
		
		
		
}











function pwdcheck()
{
	var lent=document.forms[0].password.value.length;
	
	if(document.forms[0].password.value!='')
	{
		if(document.forms[0].password.value.length<6)
		{
			//alert("Length of Password="+lent);
			document.getElementById("userpassmax").style.display="block";
			document.forms[0].password.value='';
			document.forms[0].password.focus();
			
		}
		else
	{
		document.getElementById("userpassmax").style.display="none";
     }
	}	
	
}


function submitForm(){


if(document.forms[0].userid.value=='' && document.forms[0].password.value=='' && 
document.forms[0].lastname.value=='' && 
document.forms[0].firstname.value=='' && 
document.forms[0].add1.value=='' && 
document.forms[0].userid.value==''&& 
document.forms[0].city.value=='' && 
document.forms[0].zipcode.value==''&& 
document.forms[0].state.value=='' && 
document.forms[0].country.value==''

 )
{

document.forms[0].reset();
 return false;
}





if(document.forms[0].userid.value=='' || document.forms[0].password.value==''||
document.forms[0].lastname.value==''||
document.forms[0].firstname.value==''||
document.forms[0].add1.value==''||
document.forms[0].userid.value==''||
document.forms[0].city.value==''||
document.forms[0].zipcode.value==''||
document.forms[0].state.value==''||
document.forms[0].country.value==''

 )

{
	if(document.forms[0].userid.value=='')
	{
      document.getElementById("chkuserid").style.display="block";
	} 
	if(document.forms[0].password.value=='')
	{
 document.getElementById("chkpassword").style.display="block";
	} 
	if(document.forms[0].lastname.value=='')
	{
	 document.getElementById("chklastname").style.display="block";
	} 
	if(document.forms[0].firstname.value=='')
	{
	 document.getElementById("chkfirstname").style.display="block";
	} 
	if(document.forms[0].add1.value=='')
	{
	 document.getElementById("chkadd1").style.display="block";
	} 
	if(document.forms[0].userid.value=='')
	{
	 document.getElementById("chkuserid").style.display="block";
	} 
	if(document.forms[0].city.value=='')
	{
	 document.getElementById("chkcity").style.display="block";
	} 
	if(document.forms[0].state.value=='')
	{
	 document.getElementById("chkstate").style.display="block";
	} 
		if(document.forms[0].zipcode.value=='')
	{
	 document.getElementById("chkzipcode").style.display="block";
	} 
		if(document.forms[0].country.value=='')
	{
	 document.getElementById("chkcountry").style.display="block";
	} 
	
       return false;
}
	
	else
	{
	return true;
	
	}
	
}


function userNotExistSubmitForm(){
	document.forms[0].action="acccreate.do?method=saveRegistration&userEmailId="+document.forms[0].txtemail.value+"&city="+document.forms[0].txtCity.value+"&mobile="+document.forms[0].txtMobile.value;
	document.forms[0].submit();
}


function retypecheck(pass,man,db)
{
		
        

		if(document.forms[0].password.value!=document.forms[0].repassword.value)
		{
		document.getElementById("reenter").style.display="block";
		document.forms[0].txtRetypeEmail.value='';
		document.forms[0].password.value='';
		document.forms[0].password.focus();
		
		}
        else
        {
          document.getElementById("reenter").style.display="none";
        }

}

function validateEmail(addr,man,db) {
strEmail = addr.value;
	if(strEmail!="")
	{
			if (addr.value == '' && man) {
			  if (db){   
			  	
			  	document.getElementById("invalid").style.display="block";
 			   	document.getElementById("nodata").style.display="none";
			  }
			  addr.value="";
			   addr.focus();
			   return false;
			   
			}
			var invalidChars = '\/\'\\ ";:?!()[]\{\}^|';
			for (i=0; i<invalidChars.length; i++) {
			   if (addr.value.indexOf(invalidChars.charAt(i),0) > -1) {
				  if (db){   
					document.getElementById("invalid").style.display="block";
					document.getElementById("nodata").style.display="none";
				  }
				   addr.value="";
				   addr.focus();
				
				  return false;
				  
			   }
			}
			for (i=0; i<addr.value.length; i++) {
			   if (addr.value.charCodeAt(i)>127) {
				  if (db){   
					document.getElementById("invalid").style.display="block";
					document.getElementById("nodata").style.display="none";
				  }
				 addr.value="";
				   addr.focus();
				  return false;
				  
			   }
			}
			
			var atPos = addr.value.indexOf('@',0);
			if (atPos == -1) {
			  if (db){   
			  	document.getElementById("invalid").style.display="block";
 			   	document.getElementById("nodata").style.display="none";
			  }
			   addr.value="";
			   addr.focus();
			   return false;
				
			}
			if (atPos == 0) {
			  if (db){   
			  	document.getElementById("invalid").style.display="block";
 			   	document.getElementById("nodata").style.display="none";
			  }
				 addr.value="";
			   addr.focus();
			   return false;
			  
			}
			if (addr.value.indexOf('@', atPos + 1) > - 1) {
			  if (db){   
			  	document.getElementById("invalid").style.display="block";
 			   	document.getElementById("nodata").style.display="none";
			  }
				 addr.value="";
			   addr.focus();
			   return false;
			}
			if (addr.value.indexOf('.', atPos) == -1) {
			  if (db){   
			  	document.getElementById("invalid").style.display="block";
 			   	document.getElementById("nodata").style.display="none";
			  }
				addr.value="";
			   addr.focus();
			   return false;
			}
			if (addr.value.indexOf('@.',0) != -1) {
			  if (db){   
			  	document.getElementById("invalid").style.display="block";
 			   	document.getElementById("nodata").style.display="none";
			  }
				addr.value="";
			   addr.focus();
			   return false;
			}
			if (addr.value.indexOf('.@',0) != -1){
			  if (db){   
			  	document.getElementById("invalid").style.display="block";
 			   	document.getElementById("nodata").style.display="none";
			  }
			   addr.value="";
			   addr.focus();
			   return false;
			}
			if (addr.value.indexOf('..',0) != -1) {
			  if (db){   
			  	document.getElementById("invalid").style.display="block";
 			   	document.getElementById("nodata").style.display="none";
			  }
				addr.value="";
			   addr.focus();
			   return false;
			}
			var suffix = addr.value.substring(addr.value.lastIndexOf('.')+1);
			if (suffix.length != 2 && suffix != 'com' && suffix != 'net' && suffix != 'org' && suffix != 'edu' && suffix != 'int' && suffix != 'mil' && suffix != 'gov' & suffix != 'arpa' && suffix != 'biz' && suffix != 'aero' && suffix != 'name' && suffix != 'coop' && suffix != 'info' && suffix != 'pro' && suffix != 'museum' && suffix != 'in') {
			  if (db){   
			  	document.getElementById("invalid").style.display="block";
 			   	document.getElementById("nodata").style.display="none";
			  }
				addr.value="";
			   addr.focus();
			   return false;
			}
			
 }
  document.getElementById("invalid").style.display="none";
			return true;
}

function AJAXInteraction(url, callback) {

    var req = init();
    req.onreadystatechange = processRequest;
        
    function init() {
      if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
      } else if (window.ActiveXObject) {
        return new ActiveXObject("Microsoft.XMLHTTP");
      }
    }
    
    function processRequest () {
      // readyState of 4 signifies request is complete
      if (req.readyState == 4) {
        // status of 200 signifies sucessful HTTP call
        if (req.status == 200) {
          if (callback) callback(req.responseXML);
        }
      }
    }

    this.doGet = function f() {
      req.open("GET", url, true);
      req.send(null);
    }
}

function validateUserId() {

    var target = document.getElementById("txtemail");
	//alert(target.value)
    var url = "acccreate?id=" + encodeURIComponent(target.value);    
    var target = document.getElementById("txtemail");
    var ajax = new AJAXInteraction(url, validateCallback);
    ajax.doGet();
}

function validateCallback(responseXML) {
  /* var msg = responseXML.getElementsByTagName("valid")[0].firstChild.nodeValue;
   if (msg == "false"){
       var mdiv = document.getElementById("userIdMessage");
      document.getElementById("userIdMessage").style.display="block";
	   document.getElementById("txtemail").focus()
	   // set the style on the div to invalid
     //  mdiv.className = "bp_invalid";
      // mdiv.innerHTML = "Invalid User Id";
       //var submitBtn = document.getElementById("submit_btn");
       //submitBtn.disabled = true;
    } else {
       var mdiv = document.getElementById("userIdMessage");
        document.getElementById("userIdMessage").style.display="none";
	   // set the style on the div to valid
      // mdiv.className = "bp_valid";
      // mdiv.innerHTML = "Valid User Id";
     //  var submitBtn = document.getElementById("submit_btn");
      // submitBtn.disabled = false;
    }  */
}


var passid="8735"
function appears()
{
if(document.forms[0].txtAppears.value!='')
if(document.forms[0].txtAppears.value!="8735")
{
document.getElementById("textmatch").style.display="block";
document.forms[0].txtAppears.value='';
document.forms[0].txtAppears.focus();
}
else
document.getElementById("textmatch").style.display="none";
}
function bodyOnload(){
	dojo.addOnLoad(userValidationInitProgress);
}
function divclose(name){
        var divObj;
        divObj= document.getElementById(name);
        divObj.style.display = "none";
        }
function whyLink(evt)
{
     evt = (evt) ? evt : ((window.event) ? window.event : "")
    //var evt = window.event;
    if (evt) {
        var elem = (evt.target) ? evt.target : evt.srcElement
        var x = evt.clientX - 30;
        var y = evt.clientY + document.body.scrollTop-94;
        var divObj = document.getElementById("whyLink");
        divObj.style.display = "block";
        divObj.style.position = "absolute";
        divObj.style.left = x;
        divObj.style.top = y;
        //divObj.innerHTML = "<table width='300'height='25' class='results-popupdiv' cellspacing=0 cellpadding=0 style='border:1px solid #3396FF'><tr><td align='left'><font color='blue' //style='font-size:12'><Strong>Why ?</Strong></font></td><td align='right'><a href='javascript:divclose(\"whyLink\")' //style='font-size:12'><b>close[X]</b></a></td></tr><tr><td align='left' colspan='2'><font size='1'  family='verdana'>Lorem libero pharetra enim, vitae //semper sem leo vel metus. Duis auctor lacinia tellus. Integer euismod neque venenatis libero. Nam sem justo, mollis //nec, aliquam at, fringilla ac, velit. Nunc tempor lectus a orci. Nulla arcu. Duis feugiat rutrum velit. Class aptent //taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. Pellentesque eget orci quis sapien //egestas placerat. Suspendisse <br></font></td></tr></table>" 
    //window.location.href = "include/flights_web_taxes.jsp?taxvalue='+taxvalue+'"
	divObj.innerHTML = "<table width='300' bgcolor='#3396FF' cellspacing=0 cellpadding=0 style='border:1px solid #3396FF'><tr><td><table width='300' height='25' cellspacing='0' cellpading='0' bgcolor='#FFFFFF'><tr bgcolor='#3396FF'><td align='left'><font color='white'><B>Why?</B></font></td><td align='right'><a href='javascript:divclose(\"whyLink\")' style='font-size:12'><font color='white'><B>close[X]</B></font></b></a></td></tr><tr><td align='left' colspan='2' style='text-align:justify'><font size='1'  family='verdana'>Whenever you visit MakeMyTrip.com, you will be automatically signed in. To protect your account information, we do not recommend signing up for this feature if you are using a shared or public computer.</font></td></tr></table></td></tr></table>" 

}
}

function whyLinkNumber(evt)
{
     evt = (evt) ? evt : ((window.event) ? window.event : "")
    //var evt = window.event;
    if (evt) {
        var elem = (evt.target) ? evt.target : evt.srcElement
        var x = evt.clientX - 30;
        var y = evt.clientY + document.body.scrollTop+4;
        var divObj = document.getElementById("whyLink");
        divObj.style.display = "block";
        divObj.style.position = "absolute";
        divObj.style.left = x;
        divObj.style.top = y;
        //divObj.innerHTML = "<table width='300'height='25' class='results-popupdiv' cellspacing=0 cellpadding=0 style='border:1px solid #3396FF'><tr><td align='left'><font color='blue' //style='font-size:12'><Strong>Why ?</Strong></font></td><td align='right'><a href='javascript:divclose(\"whyLink\")' //style='font-size:12'><b>close[X]</b></a></td></tr><tr><td align='left' colspan='2'><font size='1'  family='verdana'>Lorem libero pharetra enim, vitae //semper sem leo vel metus. Duis auctor lacinia tellus. Integer euismod neque venenatis libero. Nam sem justo, mollis //nec, aliquam at, fringilla ac, velit. Nunc tempor lectus a orci. Nulla arcu. Duis feugiat rutrum velit. Class aptent //taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. Pellentesque eget orci quis sapien //egestas placerat. Suspendisse <br></font></td></tr></table>" 
    //window.location.href = "include/flights_web_taxes.jsp?taxvalue='+taxvalue+'"
	divObj.innerHTML = "<table width='300' bgcolor='#3396FF' cellspacing=0 cellpadding=0 style='border:1px solid #3396FF'><tr><td><table width='300' height='25' cellspacing='0' cellpading='0' bgcolor='#FFFFFF'><tr bgcolor='#3396FF'><td align='left'><font color='white'><B>Why?</B></font></td><td align='right'><a href='javascript:divclose(\"whyLink\")' style='font-size:12'><font color='white'><B>close[X]</B></font></b></a></td></tr><tr><td align='left' colspan='2' style='text-align:justify'><font size='1'  family='verdana'>By entering the code you see in the box, you help us ensure that our registration service is not being misused. If no image appears, please make sure that your browser allows for images to be displayed and try again.</font></td></tr></table></td></tr></table>" 

}
}

function whyLinkEmail(evt)
{
     evt = (evt) ? evt : ((window.event) ? window.event : "")
    //var evt = window.event;
    if (evt) {
        var elem = (evt.target) ? evt.target : evt.srcElement
        var x = evt.clientX - 30;
        var y = evt.clientY + document.body.scrollTop+4;
        var divObj = document.getElementById("whyLink");
        divObj.style.display = "block";
        divObj.style.position = "absolute";
        divObj.style.left = x;
        divObj.style.top = y;
        //divObj.innerHTML = "<table width='300'height='25' class='results-popupdiv' cellspacing=0 cellpadding=0 style='border:1px solid #3396FF'><tr><td align='left'><font color='blue' //style='font-size:12'><Strong>Why ?</Strong></font></td><td align='right'><a href='javascript:divclose(\"whyLink\")' //style='font-size:12'><b>close[X]</b></a></td></tr><tr><td align='left' colspan='2'><font size='1'  family='verdana'>Lorem libero pharetra enim, vitae //semper sem leo vel metus. Duis auctor lacinia tellus. Integer euismod neque venenatis libero. Nam sem justo, mollis //nec, aliquam at, fringilla ac, velit. Nunc tempor lectus a orci. Nulla arcu. Duis feugiat rutrum velit. Class aptent //taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. Pellentesque eget orci quis sapien //egestas placerat. Suspendisse <br></font></td></tr></table>" 
    //window.location.href = "include/flights_web_taxes.jsp?taxvalue='+taxvalue+'"
	divObj.innerHTML = "<table width='300' bgcolor='#3396FF' cellspacing=0 cellpadding=0 style='border:1px solid #3396FF'><tr><td><table width='300' height='25' cellspacing='0' cellpading='0' bgcolor='#FFFFFF'><tr bgcolor='#3396FF'><td align='left'><font color='white'><B>Why?</B></font></td><td align='right'><a href='javascript:divclose(\"whyLink\")' style='font-size:12'><font color='white'><B>close[X]</B></font></b></a></td></tr><tr><td align='left' colspan='2' style='text-align:justify'><font size='1'  family='verdana'>This will be the address to log on to MakeMyTrip.com. We will also use this to correspond with you when you make a booking and keep you updated.</font></td></tr></table></td></tr></table>" 

}
}
			function getUserPassword(userLoginId){
				var cookie = document.cookie.split(';');
				//alert(cookie);
				var individual_cookie=new Array();
				for(var i=0;i<cookie.length;i++){
					individual_cookie=cookie[i].split(',');
					for(var j=0;j<individual_cookie.length;j++){
						loginDtls=individual_cookie[j].split('=');
						for(var k=0;k<loginDtls.length;k++){
							if(loginDtls[k].toString()==userLoginId.toString()
								|| loginDtls[k].toString()==(" "+userLoginId.toString())){
								//alert(loginDtls[k+1]);
								document.forms[0].password.value=loginDtls[k+1];
							}// end of if loginDtls
						}// end of for loop var k
					}// end of for loop var j
				}// end of for loop var i
			}// end of fun 
--></SCRIPT>









	<body>

		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<!--<img src="../images/stockpile_banner.jpg" width="1000" height="41"></td>-->
			</tr>
			<tr>
				<td align="center">
					<table width="800" border="0" cellspacing="0" cellpadding="0"
						height="100%" align="center">
						<tr>
							<td height="100%" align="center" valign="top"
								style="background-image: url(images/bg1.jpg); background-repeat: repeat-x;">
								<table width="731" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td background="../images/top-bg.jpg">
														<img src="../images/acsignup-lbl.jpg" width="310"
															height="39" />
													</td>
												</tr>
												<tr>
													<td height="100%">
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td width="5">
																	<img src="../images/tl.jpg" width="5" height="5" />
																</td>
																<td background="../images/hbar-table2.jpg">
																	<img src="../images/hbar-table2.jpg" width="40"
																		height="5" />
																</td>
																<td width="5">
																	<img src="../images/tr.jpg" width="5" height="5" />
																</td>
															</tr>
															<tr>
																<td background="../images/vbar-table2.jpg">
																	<img src="../images/vbar-table2.jpg" width="5"
																		height="10" />
																</td>
																<td bgcolor="#83A8DB" height="100%">
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0" height="100%">
																		<tr>
																			<td>
																				<table width="100%" border="0" cellspacing="0"
																					cellpadding="0">
																					<tr>
																						<td width="42">
																							<img src="../images/tag.jpg" width="42"
																								height="42" />
																						</td>
																						<td valign="baseline"
																							background="../images/tag-bg.jpg"
																							style="padding-left: 140px;">
																							<img src="../images/step11.jpg" width="368"
																								height="23" border="0" usemap="#MapMap" />
																						</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																		<tr>
																			<td height="100%">
																				<table width="100%" border="0" cellspacing="0"
																					cellpadding="0" height="100%">
																					<tr>
																						<td width="7" height="7">
																							<img src="../images/tl-inner.jpg" width="7"
																								height="7" />
																						</td>
																						<td background="../images/hbar-inner.jpg">
																							<img src="../images/hbar-inner.jpg" width="10"
																								height="7" />
																						</td>
																						<td width="7">
																							<img src="../images/tr-inner.jpg" width="7"
																								height="7" />
																						</td>
																					</tr>
																					<tr>
																						<td background="../images/vbar-inner.jpg"
																							height="350">
																							<img src="../images/vbar-inner.jpg" width="7"
																								height="10" />
																						</td>
																						<td align="center" bgcolor="#DFE3EF">
																							<table width="690" border="0" cellspacing="0"
																								cellpadding="0">
																								<tr>
																									<td align="right" valign="top">
																										<!-- Table 1-->
																										<table width="280" border="0" cellspacing="2"
																											cellpadding="0">
																											<html:form action="/submitregiform">

																												<tr>
																													<td>
																														<span class="redstar">*</span>&nbsp;
																														<span class="labels">First Name: </span>
																													</td>
																													<td>
																														<html:text property="firstname"
																															style="border: 1px solid rgb(8, 69, 131)"
																															onblur="checkName();">
																														</html:text>
																													</td>
																												</tr>
																												<tr>
																													<td>
																														<span class="redstar">*</span>&nbsp;
																														<span class="labels">Last Name:</span>
																													</td>
																													<td>
																														<html:text property="lastname"
																															onblur="checkName();"
																															style="border: 1px solid rgb(8, 69, 131)"></html:text>
																													</td>
																												</tr>
																												<tr>
																													<td>
																														<span class="redstar">*</span>&nbsp;
																														<span class="labels">Email ID/User
																															Id:</span>
																													</td>
																													<td>
																														<html:text property="userid"
																															style="border: 1px solid rgb(8, 69, 131)"
																															onblur="validateEmail(this,1,1);">
																														</html:text>
																													</td>

																												</tr>
																												<tr>
																													<td>
																														<span class="redstar">*</span>&nbsp;
																														<span class="labels">Password:</span>
																													</td>
																													<td>
																														<html:password property="password"
																															size="22" onblur="pwdcheck();"
																															style="border: 1px solid rgb(8, 69, 131)"></html:password>
																													</td>
																												</tr>
																												<tr>
																													<td>
																														<span class="redstar">*</span>&nbsp;
																														<span class="labels">Re-Type
																															Password:</span>
																													</td>
																													<td>
																														<html:password property="repassword"
																															size="22" onblur="retypecheck(this,1,1)"
																															style="border: 1px solid rgb(8, 69, 131)"></html:password>
																													</td>
																												</tr>
																												<tr>
																													<td>
																														<span class="redstar">*</span>&nbsp;
																														<span class="labels">Contact No.:</span>
																													</td>
																													<td>
																														<html:text property="contactno"
																															style="border: 1px solid rgb(8, 69, 131)"></html:text>
																													</td>
																												</tr>
																										</table>
																									</td>
																									<td width="5" background="../images/vdot.jpg">
																										<!-- dotted divider-->
																										<img src="../images/vdot.jpg" width="5"
																											height="3" />
																									</td>
																									<td valign="top">
																										<!-- Table 2-->
																										<table width="280" border="0" cellspacing="2"
																											cellpadding="0">
																											<tr>
																												<td>
																													<span class="redstar">*</span>&nbsp;
																													<span class="labels">Address: Line1
																													</span>
																												</td>
																												<td>
																													<html:text property="add1"
																														style="border: 1px solid rgb(8, 69, 131)"></html:text>
																												</td>
																											</tr>
																											<tr>
																												<td class="labels"
																													style="padding-left: 11px;">
																													Address: Line 2
																												</td>
																												<td>
																													<html:text property="add2"
																														style="border: 1px solid rgb(8, 69, 131)"></html:text>
																												</td>
																											</tr>
																											<tr>
																												<td>
																													<span class="redstar">*</span>&nbsp;
																													<span class="labels">City:</span>
																												</td>
																												<td>
																													<html:text property="city"
																														onblur="checkName();"
																														style="border: 1px solid rgb(8, 69, 131)"></html:text>
																												</td>
																											</tr>
																											<tr>
																												<td>
																													<span class="redstar">*</span>&nbsp;
																													<span class="labels">State:</span>
																												</td>
																												<td>
																													<html:text property="state"
																														onblur="checkName();"
																														style="border: 1px solid rgb(8, 69, 131)"></html:text>
																												</td>
																											</tr>
																											<tr>
																												<td>
																													<span class="redstar">*</span>&nbsp;
																													<span class="labels">Zip:</span>
																												</td>
																												<td>
																													<html:text property="zipcode"
																														style="border: 1px solid rgb(8, 69, 131)"></html:text>
																												</td>
																											</tr>
																											<tr>
																												<td class="labels">
																													<span class="redstar">*</span>&nbsp;Country:
																												</td>
																												<td>
																													<html:text property="country"
																														onblur="checkName();"
																														style="border: 1px solid rgb(8, 69, 131)"></html:text>
																												</td>
																											</tr>
																											<tr>
																												<td colspan="2">
																													&nbsp;
																												</td>
																											</tr>
																										</table>
																									</td>
																								</tr>

																								<tr>

																									<td colspan="3" valign="top">
																										<table width="100%" border="0" cellspacing="0"
																											cellpadding="0">
																											<tr>
																												<td align="center" class="bodytextMaroon">
																													<input type="checkbox" checked="true"
																														property="agree"
																														onclick="return checkCheckBox();">
																													<a href="javascript: void(0)"
																														onclick="window.open('popup.html','windowname1','width=500,height=500,scrollbars=yes'); 
                                            return false;"><span
																														class="labels">I agree term and
																															conditions</span> </a>
																													</input>

																												</td>
																											</tr>
																											<tr>
																												<td align="center"
																													style="padding-top: 10px;">
																													<span class="redtext">Note:</span><span
																														class="labels"> Email ID will be
																														used as User ID. Fields marked with <span
																														class="redstar">*</span> are mandatory </span>
																												</td>
																											</tr>
																											<tr>

																												<td align="center"
																													style="padding-top: 10px;">
																													<table border="0" cellspacing="5"
																														cellpadding="0">
																														<tr>
																															<td>
																																<!--   <html:submit property="submitValue" ><img src="../images/submit.jpg" width="66" height="20"></img> </html:submit>-->
																																<td align="center">
																																	<!--<html:image onclick="submitForm();" src="../images/submit.jpg" ></html:image>
                                                        -->
																																	<html:image
																																		onclick="return submitForm();"
																																		src="../images/submit.jpg"></html:image>
																																</td>
																																<td>
																																	<!--  <html:reset><html:image src="../images/cancel.jpg" ></html:image></html:reset>-->
																																	<html:img src="../images/cancel.jpg"
																																		onclick="javascript:document.forms[0].reset(); return false;" />

																																</td>
																														</tr>

																														<tr>
																															</html:form>
																													</table>
																													<%
																														if (session.getAttribute("userbeen") != null) {
																															if (session.getAttribute("exist") != null) {
																																out.print("<span class='redtext'>Error:</span>");
																																out.print("<span class='labels'>");
																																out.print(session.getAttribute("exist"));
																																out.print("</span>");
																															}
																															if (session.getAttribute("datafail") != null) {
																																out.print("<span class='redtext'>Error:</span>");
																																out.print("<span class='labels'>");
																																out.print(session.getAttribute("datafail"));
																																out.print("</span>");
																															}

																															session.setAttribute("userbeen", null);
																															session.setAttribute("exist", null);
																															session.setAttribute("datafail", null);

																														}
																													%>

																												</td>
																											</tr>
																											<tr>

																												<TD class=payment_error_text_new id=invalid
																													style="DISPLAY: none" align="center"
																													height=50>
																													<span class='redtext'>Sorry, Kindly
																														enter a valid Email address.</span>
																												</td>
																												<TD class=payment_error_text_new id=reenter
																													style="DISPLAY: none" align="center"
																													height=50>
																													<span class='redtext'>Sorry, your
																														Password did not match.Kindly re-enter.</span>
																												</TD>
																											</tr>
																											<tr>
																												<TD class=payment_error_text_new
																													id=chkfirstname style="DISPLAY: none"
																													align="center" height=50>
																													<span class='redtext'>Sorry, Kindly
																														enter a valid First Name .</span>
																												</TD>
																											</tr>
																											<tr>
																												<TD class=payment_error_text_new
																													id=chklastname style="DISPLAY: none"
																													align="center" height=50>
																													<span class='redtext'>Sorry, Kindly
																														enter a valid Last Name </span>
																												</TD>
																											</tr>
																											<tr>

																												<TD class=payment_error_text_new
																													id=chkuserid style="DISPLAY: none"
																													align="center" height=50>
																													<span class='redtext'>Sorry, Kindly
																														enter a Email Id/User Id.</span>
																												</TD>
																											</tr>
																											<tr>
																												<TD class=payment_error_text_new
																													id=chkpassword style="DISPLAY: none"
																													align="center" height=50>
																													<span class='redtext'>Sorry, Kindly
																														enter Password.</span>
																												</TD>
																												<TD class=payment_error_text_new
																													id=userpassmax style="DISPLAY: none"
																													align="center" height=50>
																													<span class='redtext'>Sorry,
																														Password must be atleast six character
																														long .</span>
																												</TD>
																											</tr>
																											<tr>
																												<TD class=payment_error_text_new id=chkadd1
																													style="DISPLAY: none" align="center"
																													height=50>
																													<span class='redtext'>Sorry, Kindly
																														enter an Address.</span>
																												</TD>
																											</tr>
																											<tr>
																												<TD class=payment_error_text_new id=chkcity
																													style="DISPLAY: none" align="center"
																													height=50>
																													<span class='redtext'>Sorry, Kindly
																														enter a valid City.</span>
																												</TD>
																											</tr>
																											<tr>
																												<TD class=payment_error_text_new id=chkstate
																													style="DISPLAY: none" align="center"
																													height=50>
																													<span class='redtext'>Sorry, Kindly
																														enter a valid State.</span>
																												</TD>
																											</tr>
																											<tr>
																												<TD class=payment_error_text_new
																													id=chkzipcode style="DISPLAY: none"
																													align="center" height=50>
																													<span class='redtext'>Sorry, Kindly
																														enter zip Code.</span>
																												</TD>
																											</tr>
																											<tr>
																												<TD class=payment_error_text_new
																													id=chkcountry style="DISPLAY: none"
																													align="center" height=50>
																													<span class='redtext'>Sorry, Kindly
																														enter a valid Country.</span>
																												</TD>
																											</tr>
																										</table>
																									</td>

																								</tr>
																							</table>
																							<br>
																							<br>
																							<br>
																						</td>
																						<td background="../images/vbar-inner-2.jpg">
																							<img src="../images/vbar-inner-2.jpg" width="7"
																								height="10" />
																						</td>
																					</tr>
																					<tr>
																						<td>
																							<img src="../images/bl-inner.jpg" width="7"
																								height="7" />
																						</td>
																						<td background="../images/hbar-inner-2.jpg">
																							<img src="../images/hbar-inner-2.jpg" width="10"
																								height="7" />
																						</td>
																						<td>
																							<img src="../images/br-inner.jpg" width="7"
																								height="7" />
																						</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</table>
																</td>
																<td background="../images/vbar-table2.jpg">
																	<img src="../images/vbar-table2.jpg" width="5"
																		height="10" />
																</td>
															</tr>
															<tr>
																<td>
																	<img src="../images/bl.jpg" width="5" height="5" />
																</td>
																<td background="../images/hbar-table2.jpg">
																	<img src="../images/hbar-table2.jpg" width="40"
																		height="5" />
																</td>
																<td>
																	<img src="../images/br.jpg" width="5" height="5" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											&nbsp;
										</td>
									</tr>
								</table>
								<!-- Body here -->
							</td>
						</tr>
						<tr>
							<td height="100%" bgcolor="#003063">
								<img src="../images/footer-bg.jpg" height="40">
								<!-- Footer here -->
							</td>
						</tr>
						<tr>
							<td height="100%" align="center">
								<span class="style1">Copyright 2008, All Rights Reserved
								</span>
							</td>
						</tr>
					</table>
					<map name="MapMap">
						<area shape="rect" coords="5,3,98,20" href="step2.html"
							alt="next page">
					</map>
				</td>
			</tr>
		</table>
		<map name="Map">
			<area shape="rect" coords="5,3,98,20" href="step2.html"
				alt="next page">
		</map>
	</body>
</html>
