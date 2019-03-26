
<%@page import="com.harrier.initializeation.ConnectInit"%><%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.util.Calendar,java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<jsp:useBean id="newusersForm" scope="session" class="app.NewusersForm"/>

<%@ page language="java" import="app.*"%>
<%
			LogonForm form = null;
			if(request.isRequestedSessionIdValid())	{	
				form = (LogonForm)session.getAttribute("user");
				String locale=session.getAttribute("locale").toString();
			//	AcessControl asc=new AcessControl();
				AcessControl asc =  ConnectInit.getAcessControl();
				asc.setLocale(locale);
			}
			
			if(form==null ||(!request.isRequestedSessionIdValid())){
				response.sendRedirect("../userlogintemp.jsp");
			}
			
		
%>
<html>
<html:base/>
<head>
	<title><bean:message key="newUsers.Signin"/></title>
<META http-equiv="Content-Type" content="text/html; charset=windows-1251">
    <style>
PRE 
{
	BORDER-RIGHT: #f0f0f0 1px solid; 
	BORDER-TOP: #f0f0f0 1px solid; 
    BORDER-LEFT: #f0f0f0 1px solid; 
	BORDER-BOTTOM: #f0f0f0 1px solid; 
    MARGIN-LEFT: 20px; 
   	MARGIN-RIGHT: 20px; 
    PADDING-LEFT: 10px; 
   	PADDING-RIGHT: 10px; 
    PADDING-TOP: 10px; 
   	PADDING-BOTTOM: 10px; 
	FONT-SIZE: 8pt;  
	BACKGROUND-COLOR: #f8f8f8 	
}

p
{
    FONT-SIZE: 8pt;
    MARGIN-TOP: 7px;
    MARGIN-LEFT: 7px;
    MARGIN-RIGHT: 7px;
    FONT-FAMILY: Verdana, Arial;
    TEXT-ALIGN: justify
}

</style>

        <meta name="Microsoft Theme" content="none">
	
	<script language="javascript" src="./codethatcalendarstd.js"></script>
	<script language="javascript" src="box_ex.js"></script>
	
	<!-- START CHANGE :-THIS FUNCTION IS USED TO ALLOW BIRTHDAY FIELD TO TAKE VALUES FROM 1900 TO 2025 -->
	
	<script language="javascript">
	var caldef2 =
{
	firstday : 0,
	dtype : 'MM/dd/yyyy',
	width : 250,
	windoww : 300,
	windowh : 170,
	border_width : 0,
	border_color : '#0000d3',
	dn_css : 'clsDayName',
	cd_css : 'clsCurrentDay',
	wd_css : 'clsWorkDay',
	we_css : 'clsWeekEnd',
	wdom_css : 'clsWorkDayOtherMonth',
	weom_css : 'clsWeekEndOtherMonth',
	headerstyle :
	{
		type : "comboboxes",
		css : 'clsWorkDayOtherMonth',
		yearrange : [1900, 2025]
	},
	monthnames : ["January", "February", "March", "April",
	"May", "June", "July", "August", "September",
	"October", "November", "December"],
	daynames : ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"]
}; 
<!-- CHNAGES END -->
	
		var c2 = new CodeThatCalendar(caldef2);
	</script>
</head>
<body topmargin="0" leftmargin="0">
<html:form action="/NewusersSubmit" focus="id"  onsubmit="return validateNewusersForm(this);">

<table border="0" cellspacing="0" cellpadding="0">
  <!--<tr>
    <td width="100%" height="99">
    <p style="margin-left: 0"><img border="0" src="images/head2.jpg" width="780" height="106"></p>
    </td>
  </tr>-->
  <tr>
    <td width="100%" height="99">
    <html:errors/>
      <table cellSpacing="0" cellPadding="0" width="781" border="0">
        <tbody>
          <tr>
            <td class="content" colSpan="2" width="779">
              <table cellSpacing="0" cellPadding="0" width="100%" border="0">
                <tbody>
                  <tr>
                    <td align="left" width="1%">
                      <p style="margin-left: 9"></td>
                    <td vAlign="bottom" noWrap align="right" width="100%">
                      <table cellSpacing="0" cellPadding="0" width="100%" border="0">
                        <tbody>
                          <tr>
                            <td vAlign="bottom" noWrap align="right" width="99%">
                              <p style="margin-left: 9"><br>
                              <hr align="center">
                            </td>
                          </tr>
                        </tbody>
                      </table>
                    </td>
                  </tr>
                </tbody>
              </table>
            </td>
          </tr>
          <tr>
            <td class="content" colSpan="2" height="6" width="779">
              <p style="margin-left: 9"></p>
            </td>
          </tr>
        </tbody>
      </table>
      <table cellSpacing="0" cellPadding="4" width="779" border="0">
        <tbody>
          <tr bgColor="#b6c7e5">
            <td vAlign="center" width="195" bgcolor="#EEEEEE">
              <p style="margin-left: 9"><font face="Arial" color="black" size="+1"><b>
              <bean:message key="newUsers.Signup"/>&nbsp;</b></font></p>
            </td>
            <td noWrap align="right" width="554" bgcolor="#EEEEEE">
              <p style="margin-left: 9" align="right"><b><font face="Arial" color="black" size="-1">
              <bean:message key="newUsers.AlreadyRegistered"/></font></b>&nbsp;&nbsp; <font face="Arial, Helvetica, sans-serif" size="-1"><b><html:link forward="logon"><bean:message key="newUsers.Signin"/></html:link></b></font></p>
            </td>
          </tr>
        </tbody>
      </table>
      <table height="1" cellSpacing="0" cellPadding="0" width="779" bgColor="#ffffff" border="0">
        <tbody>
          <tr>
            <td width="777" height="21">
              <hr align="center">
            </td>
          </tr>
          <tr>
            <td width="777" height="1">
              <table border="0" width="100%" cellspacing="0" cellpadding="2">
             
             <!--START: CHANGES MADE HERE FOR MENTIONING COMPULSORY FIELDS-->
             <tr>
			
			<td  align="center" colspan=6 class=f13><FONT COLOR="#E30102"><bean:message key="newUsers.CompulsaryField"/> <FONT FACE="Verdana" SIZE =-2 COLOR="#E30102"></FONT></FONT></td>
		     </tr>
		     <!--END:CHANGES-->
                <tr>
                  <td width="24%">
                    <p align="right" style="margin-left: 9"><FONT FACE="Verdana" SIZE =-2 COLOR="#E30102">*</FONT><bean:message key="newUsers.UserId"/></td>
                  <td width="42%">
                    <p style="margin-left: 9"><html:text property="id" size="20"/>
                    </p>
                  </td>
                  <td width="34%" rowspan="5" bgcolor="#EEEEEE">
                    <p style="margin-left: 9; margin-right: 12" align="justify"><font size="2"><b>
                      <bean:message key="newUsers.Choosingyour"/></b><br>
                      <bean:message key="newUsers.Youwill"/>
                     </font></td>
                </tr>
                <tr>
                  <td width="24%">
                    <p style="margin-left: 9"></td>
                  <td width="60%">
                    <p style="margin-left: 9"><font size="2">
                     <bean:message key="newUsers.Mustbe"/></font></p>
                  </td>
                </tr>
                <tr>
                  <td width="24%">
                    <p align="right" style="margin-left: 9"><FONT FACE="Verdana" SIZE =-2 COLOR="#E30102">*</FONT><bean:message key="newUsers.Password"/></td>
                  <td width="42%">
                    <p style="margin-left: 9"><!--webbot bot="Validation"
                    B-Value-Required="TRUE" I-Minimum-Length="6"
                    I-Maximum-Length="10" -->
                    <html:password property="pw1" maxlength="10" size="20" redisplay="false"/></p>
                  </td>
                </tr>
                
                <tr>
                  <td width="24%">
                    <p style="margin-left: 9"></td>
                  <td width="60%">
                    <p style="margin-left: 9"><font size="2">
                      <bean:message key="newUsers.Mustbe"/></font></p>
                  </td>
                </tr>
                <tr>
                  <td width="24%">
                    <p align="right" style="margin-left: 9"><FONT FACE="Verdana" SIZE =-2 COLOR="#E30102">*</FONT><bean:message key="newUsers.Re"/></td>
                  <td width="42%">
                    <p style="margin-left: 9"><html:password property="pw" maxlength="10" size="20" redisplay="false"/></p>
                  </td>
                </tr>
                <tr>
                  <td width="100%" colspan="3">
              <hr align="center">
                  </td>
                </tr>
                <tr>
                  <td width="66%" colspan="2">
                    <p style="margin-left: 9"><bean:message key="newUsers.Ifyou"/></p>
                  </td>
                  <td width="34%" rowspan="4" bgcolor="#EEEEEE">
                    <p style="margin-left: 9; margin-right: 12" align="justify"><b><font size="2"><bean:message key="newUsers.Recalling"/></font></b><font size="2"><br>
                     <bean:message key="newUsers.Thisinformation"/></font></td>
                </tr>
                <tr>
                  <td width="24%" align="right">
                    <p style="margin-left: 9"><bean:message key="newUsers.security"/></p>
                  </td>
                  <td width="42%">
                    <p style="margin-left: 9"><html:select size="1" property="seqq"  >
                    
                        <html:option value="" ><bean:message key="Admin.SelQues"/></html:option>
                        <html:option value="What is your pets name?"><bean:message key="Admin.Q1"/></html:option>
                         <html:option value="What was the name of your first school?"><bean:message key="Admin.Q2"/></html:option>
                         <html:option value="Who was your childhood hero?"><bean:message key="Admin.Q3"/></html:option>
                         <html:option value="What is your favorite past-time?"><bean:message key="Admin.Q4"/></html:option>
                         <html:option value="What is your all-time favorite sports team?"><bean:message key="Admin.Q5"/></html:option>
                         <html:option value="What is your fathers middle name?"><bean:message key="Admin.Q6"/></html:option>
                         <html:option value="What was your high school mascot?"><bean:message key="Admin.Q7"/></html:option>
                         <html:option value="What make was your first car or bike?"><bean:message key="Admin.Q8"/></html:option>
                         <html:option value="Where did you first meet your spouse?"><bean:message key="Admin.Q9"/></html:option>
                         </html:select>
                  </p>
                  </td>
                </tr>
                <tr>
                  <td width="24%" align="right">
                    <p style="margin-left: 9"><bean:message key="newUsers.youranswer"/></p>
                  </td>
                  <td width="42%">
                    <p style="margin-left: 9"><html:text size="30" property="ans"/></p>
                  </td>
                </tr>  
                <tr>
                  <td width="24%" align="right">
                    <p style="margin-left: 9"><bean:message key="newUsers.bday"/></p>
                  </td>
                  <td width="42%">
                    <p style="margin-left: 9"><html:text property="id2" readonly="true" size="20"/><input onclick="c2.popup('id2');" tabIndex="6" type="button" value="...">
                    <bean:message key="newUsers.format"/></p>
                  </td>
                </tr>
                <tr>
                  <td width="100%" colspan="3">
              <hr align="center">
                  </td>
                </tr>
                <tr>
                  <td width="24%" align="right">
                    <p style="margin-left: 9"><FONT FACE="Verdana" SIZE =-2 COLOR="#E30102">*</FONT>
                      <bean:message key="newUsers.first"/></td>
                  <td width="42%">
                    <p style="margin-left: 9"><html:text size="12" property="fn"/>&nbsp;&nbsp;
                   <bean:message key="newUsers.mid"/>&nbsp; <html:text size="11" property="mn"/></td>
                  <td width="34%" rowspan="11" bgcolor="#EEEEEE" valign="top">
                    <p style="margin-left: 9; margin-right: 12" align="justify"><font size="2"><b><bean:message key="newUsers.personel"/></b> <bean:message key="newUsers.thisCan"/></font>
                  </td>
                </tr>
                <tr>
                  <td width="24%" align="right">
                    <p style="margin-left: 9"><FONT FACE="Verdana" SIZE =-2 COLOR="#E30102">*</FONT><bean:message key="newUsers.last"/></td>
                  <td width="42%">
                    <p style="margin-left: 9"><html:text size="12" property="ln"/>&nbsp;&nbsp;
                    <bean:message key="newUsers.gender"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      <html:select size="1" property="gender">
                        <html:option value="f"><bean:message key="newUsers.female"/></html:option>
                      	<html:option value="m"><bean:message key="newUsers.male"/></html:option>
                        
                      </html:select>
                  </td>
                </tr>
                <tr>
                  <td width="24%" align="right">
                    <p style="margin-left: 9"><bean:message key="newUsers.branch"/></td>
                  <td width="42%">
                    <p style="margin-left: 9"><html:text size="12" property="branch"/></td>
                </tr>
                <tr>
                  <td width="24%" align="right">
                    <p style="margin-left: 9"><bean:message key="newUsers.add1"/></td>
                  <td width="42%">
                    <p style="margin-left: 9"><html:text size="46" property="addr1"/></td>
                </tr>
                <tr>
                  <td width="24%" align="right">
                    <p style="margin-left: 9"><bean:message key="newUsers.add2"/></td>
                  <td width="42%">
                    <p style="margin-left: 9"><html:text  size="46" property="addr2"/></td>
                </tr>
                <tr>
                  <td width="24%" align="right">
                    <p style="margin-left: 9"><bean:message key="newUsers.city"/></td>
                  <td width="42%">
                    <p style="margin-left: 9"><html:text  size="12" property="city1"/>&nbsp;
                    ZIP Code:&nbsp; <html:text  size="15" property="zip1"/></td>
                </tr>
                <tr>
                  <td width="24%" align="right">
                    <p style="margin-left: 9"> <bean:message key="newUsers.country"/></td>
                  <td width="42%">
                    <p style="margin-left: 9">
                    <html:select size="1" property="country">
                       <html:optionsCollection name="newusersForm" property="countryIdCollection" /> 
                    </html:select></td>
                </tr>
                <tr>
                  <td width="24%" align="right">
                    <p style="margin-left: 9"> <bean:message key="newUsers.if"/></td>
                  <td width="42%">
                    <p style="margin-left: 9">
                    	<html:select size="1" property="clientName">
                        	<html:optionsCollection name="newusersForm" property="clientCollection" /> 
                        </html:select>
                  </td>
                </tr>
                <tr>
                  <td width="24%" align="right">
                    <p style="margin-left: 9"> <bean:message key="newUsers.phone"/></td>
                  <td width="42%">
                    <p style="margin-left: 9"><html:text size="12" property="phone"/>  <bean:message key="newUsers.mob"/> <html:text size="11" property="mobile"/></td>
                </tr>
                <tr>
                  <td width="24%" align="right">
                    <p style="margin-left: 9"><FONT FACE="Verdana" SIZE =-2 COLOR="#E30102">*</FONT> <bean:message key="newUsers.email"/></td>
                  <td width="42%">
                    <p style="margin-left: 9"><html:text size="24" property="email"/></td>
                </tr>
                <tr>
                  <td width="24%" align="right">
                    <p style="margin-left: 9"> <bean:message key="newUsers.desig"/></td>
                  <td width="42%">
                    <p style="margin-left: 9">
                    <html:select size="1" property="designation">
                        <html:option value="" ><bean:message key="AddUser.SpecifyDesg"/></html:option>
                      	<html:option value="m"><bean:message key="AddUser.Executive"/></html:option>
                        <html:option value="f"><bean:message key="AddUser.Manager"/></html:option>
                        <html:option value="f"><bean:message key="AddUser.Administrator"/></html:option>
                      </html:select>
                                        
                </tr>
                 <tr>
                  <td width="24%" align="right">
                    <p style="margin-left: 9"><bean:message key="userRoles.selectRole(s)"/>:</td>
                  <td width="42%">
                    <p style="margin-left: 9">
                    <html:select property="selectRole" >
              			<html:optionsCollection name="newusersForm" property="roleCollection"  />
            		</html:select>
                    </td>
                </tr>
                <tr>
                  <td width="100%" colspan="3">
              <hr align="center">
                  </td>
                </tr>
                <tr>
                  <td width="100%" colspan="3">
                    <p style="margin-left: 9"><bean:message key="newUsers.submitting"/></td>
                </tr>
                <tr>
                  <td width="24%"></td>
                  <td width="42%"><center><input type="submit" value='<bean:message key="defineIndex30"/>'></center>
                  </td>
                  <td width="34%"></td>
                </tr>
                <tr>
                  <td width="100%" colspan="3">
      <hr align="center">
                  </td>
                </tr>
              </table>
              <p>&nbsp;
            </td>
          </tr>
          <tr>
            <td class="content" width="777" height="1">
              <table border="0">
                <tbody>
                </tbody>
              </table>
            </td>
          </tr>
        </tbody>
      </table>
     
      
    </td>
  </tr>
</table>
	
</html:form>
<script type="text/javascript" language="Javascript1.1"> 

<!-- Begin 

     var bCancel = false; 

    function validateNewusersForm(form) {                                                                   
        if (bCancel) 
      return true; 
        else{ 
        
        //BIRTH DATE SHOULD NOT BE GREATER THAN CURRENT DATE -->for this
        //another fuction chechBirthDay() is added
        //document.forms[0].elements[5].focus();
        var BirthDay=form.elements[5].value;
       //IF BIRTH DATE IS EMPTY THEN PERFORM OTHER VALIDATION
        if(BirthDay==""){
        //alert("birth date is empty");
         return validateMaxLength(form) && validateRequired(form) && validateMinLength(form) && validateMask(form) && validateInteger(form) && validateEmail(form) ; 
        }//end if
        //ELSE CHECK WHETHER BIRTH DATE GREATER THAN CURRENT DATE
        else{
       //alert(BirthDay+"check");
       var returnBirthDayResult= chechBirthDay(BirthDay);
      //alert(returnBirthDayResult);
      //IF BIRTH DATE IS VALID THEN DO OTHER VALIDATION
      if(returnBirthDayResult){
       return validateMaxLength(form) && validateRequired(form) && validateMinLength(form) && validateMask(form) && validateInteger(form) && validateEmail(form) ; 
       }
       else{
       alert("Birthday should not be greater than current date"); 
       document.forms[0].elements[5].focus();
       return false;
       }
     }//end else
        
        
      
   }//end upper else
   }//end function 
//added function start
//DATE FORMAT :- (eg)11/15/2006  (mm/dd/yyyy)

function chechBirthDay(BDay){
//alert(BDay+"check inside function");
var Cmm,Cdd,Cyy;
//USE SUBSTRING FUCTION
Cmm=BDay.substring(0,2);
Cdd=BDay.substring(3,5)
Cyy=BDay.substring(6,10);

//alert("month"+Cmm);
//alert("day"+Cdd);
//alert("year"+Cyy);
//CREATE DATE VARIABLE USING month,day and year 
var dateToCheck=new Date();
	dateToCheck.setYear(Cyy);
	dateToCheck.setMonth(Cmm-1);
	dateToCheck.setDate(Cdd);
	
	//alert("date created"+dateToCheck);
	//alert("created"+dateToCheck.getTime());

//DEFINE CURRENT DATE
var now=new Date();
//alert(now);
//alert("now"+now.getTime()); //GET TOTAL MILLISECONDS OF THE CURRENT DATE
//COMPARE NOW WITH CREATED DATE
//86400000 milliseconds IS EQUIVALET TO ONE DAY
//IT IS USED SO THAT CURRENT DAY CAN BE USED AS BIRTH DATE
if(now.getTime()>=(dateToCheck.getTime())){
			//alert("Birth Day is valid");
			return true;
		}
		else{
			//alert("Birth Day is invalid");
			return false;
		}
//return true;
}
//end added function



    function maxlength () { 
     this.aa = new Array("id", "User Id: can not be greater than 10 characters.", new Function ("varName", "this.maxlength='10'; this.minlength='6';  return this[varName];"));
     this.ab = new Array("pw1", "Password: can not be greater than 10 characters.", new Function ("varName", "this.maxlength='10'; this.minlength='6';  return this[varName];"));
     this.ac = new Array("pw", "Re-type Password: can not be greater than 10 characters.", new Function ("varName", "this.maxlength='10'; this.minlength='6';  return this[varName];"));
    } 

    function required () { 
    
     this.aa = new Array("id", "User Id: is required.", new Function ("varName", "this.maxlength='10'; this.minlength='6';  return this[varName];"));
     this.ab = new Array("pw1", "Password: is required.", new Function ("varName", "this.maxlength='10'; this.minlength='6';  return this[varName];"));
     this.ac = new Array("pw", "Re-type Password: is required.", new Function ("varName", "this.maxlength='10'; this.minlength='6';  return this[varName];"));
     // this.ad = new Array("seqq", "Security Question: is required.", new Function ("varName", " return this[varName];"));
     // this.ae = new Array("ans", "Your Answer: is required.", new Function ("varName", " return this[varName];"));
    //  this.af = new Array("id2", "Birthday: is required.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("fn", "First Name: is required.", new Function ("varName", " return this[varName];"));
      this.ah = new Array("ln", "Last Name: is required.", new Function ("varName", " return this[varName];"));
    // this.ah = new Array("gender", "Gender: is required.", new Function ("varName", " return this[varName];"));
   //  this.ai = new Array("addr1", "Address 1: is required.", new Function ("varName", " return this[varName];"));
   //  this.aj = new Array("city1", "City: is required.", new Function ("varName", "this.mask=/^[a-zA-Z]+$/;  return this[varName];"));
  //   this.ak = new Array("zip1", "ZIP Code: is required.", new Function ("varName", " return this[varName];"));
  //   this.al = new Array("country", "Country: is required.", new Function ("varName", " return this[varName];"));
     this.am = new Array("email", "Email Id: is required.", new Function ("varName", " return this[varName];"));
      
    } 

    function minlength () { 
     this.aa = new Array("id", "User Id: can not be less than 6 characters.", new Function ("varName", "this.maxlength='10'; this.minlength='6';  return this[varName];"));
     this.ab = new Array("pw1", "Password: can not be less than 6 characters.", new Function ("varName", "this.maxlength='10'; this.minlength='6';  return this[varName];"));
     this.ac = new Array("pw", "Re-type Password: can not be less than 6 characters.", new Function ("varName", "this.maxlength='10'; this.minlength='6';  return this[varName];"));
    } 

    function mask () { 
     this.aa = new Array("city1", "City: is invalid.", new Function ("varName", "this.mask=/^[a-zA-Z]+$/;  return this[varName];"));
    } 

    function IntegerValidations () { 
     this.aa = new Array("zip1", "ZIP Code: must be an integer.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("mobile", "Mobile Number: must be an integer.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("phone", "Phone Number: must be an integer.", new Function ("varName", " return this[varName];"));
    } 

    function email () { 
     this.aa = new Array("email", "Email Id: is an invalid e-mail address.", new Function ("varName", " return this[varName];"));
    } 



function validateMaxLength(form) {
                var isValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oMaxLength = new maxlength();
                for (x in oMaxLength) {
                    var field = form[oMaxLength[x][0]];
                    
                    if (field.type == 'text' ||
                        field.type == 'textarea') {
                        
                        var iMax = parseInt(oMaxLength[x][2]("maxlength"));
                        if (field.value.length > iMax) {
                            if (i == 0) {
                                focusField = field;
                            }
                            fields[i++] = oMaxLength[x][1];
                            isValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return isValid;
            }
function validateRequired(form) {
				
                var isValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oRequired = new required();
                for (x in oRequired) {
                	var field = form[oRequired[x][0]];
                	
                    if (field.type == 'text' ||
                        field.type == 'textarea' ||
                        field.type == 'file' ||
                        field.type == 'select-one' ||
                        field.type == 'radio' ||
                        field.type == 'password' ||
                         field.type == 'select-multiple') {
                        
                        var value = '';
						// get field's value
						if (field.type == "select-one") {
							var si = field.selectedIndex;
							if (si >= 0) {
								value = field.options[si].value;
							}
						} else {
							value = field.value;
						}
                        if(field.type == 'select-multiple' ){
                      
                        	if( trim(value).length == 0 || value==""){
	                        	if (i == 0) {
		                            focusField = field;
		                        }
		                        fields[i++] = oRequired[x][1];
		                        isValid = false;
		                    }
                        }
                        	
                        else if ( value=="" || trim(value).length == 0 || value==0 || value==0.0 || value=="value0" ) {
                        
	                        if (i == 0) {
	                            focusField = field;
	                        }
	                        fields[i++] = oRequired[x][1];
	                        isValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return isValid;
            }
            
            // Trim whitespace from left and right sides of s.
            function trim(s) {
                return s.replace( /^\s*/, "" ).replace( /\s*$/, "" );
            }
function validateInteger(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oInteger = new IntegerValidations();
                for (x in oInteger) {
                	var field = form[oInteger[x][0]];

                    if (field.type == 'text' ||
                        field.type == 'textarea' ||
                        field.type == 'select-one' ||
                        field.type == 'radio') {
                        
                        var value = '';
						// get field's value
						if (field.type == "select-one") {
							var si = field.selectedIndex;
						    if (si >= 0) {
							    value = field.options[si].value;
						    }
						} else {
							value = field.value;
						}
                        
                        if (value.length > 0) {
                        
                            if (!isAllDigits(value)) {
                                bValid = false;
                                if (i == 0) {
	                                focusField = field;
	                            }
						        fields[i++] = oInteger[x][1];
						        
                            } else {
	                            var iValue = parseInt(value);
	                            if (isNaN(iValue) || !(iValue >= -2147483648 && iValue <= 2147483647)) {
	                                if (i == 0) {
	                                    focusField = field;
	                                }
	                                fields[i++] = oInteger[x][1];
	                                bValid = false;
	                           }
                           }
                       }
                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return bValid;
            }

            function isAllDigits(argvalue) {
                argvalue = argvalue.toString();
                var validChars = "0123456789";
                var startFrom = 0;
                if (argvalue.substring(0, 2) == "0x") {
                   validChars = "0123456789abcdefABCDEF";
                   startFrom = 2;
                } else if (argvalue.charAt(0) == "0") {
                   validChars = "01234567";
                   startFrom = 1;
                } else if (argvalue.charAt(0) == "-") {
                    startFrom = 1;
                }
                
                for (var n = startFrom; n < argvalue.length; n++) {
                    if (validChars.indexOf(argvalue.substring(n, n+1)) == -1) return false;
                }
                return true;
            }
function validateRejection(form) {
            	return true;
                
            }
function validateFloat(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oFloat = new FloatValidations();
                for (x in oFloat) {
                	var field = form[oFloat[x][0]];
                	
                    if (field.type == 'text' ||
                        field.type == 'textarea' ||
                        field.type == 'select-one' ||
                        field.type == 'radio') {
                        
                    	var value = '';
						// get field's value
						if (field.type == "select-one") {
							var si = field.selectedIndex;
							if (si >= 0) {
							    value = field.options[si].value;
							}
						} else {
							value = field.value;
						}
                        
                        if (value.length > 0) {
                            // remove '.' before checking digits
                            var tempArray = value.split('.');
                            var joinedString= tempArray.join('');

                            if (!isAllDigits(joinedString)) {
                                bValid = false;
                                if (i == 0) {
                                    focusField = field;
                                }
                                fields[i++] = oFloat[x][1];

                            } else {
	                            var iValue = parseFloat(value);
	                            if (isNaN(iValue)) {
	                                if (i == 0) {
	                                    focusField = field;
	                                }
	                                fields[i++] = oFloat[x][1];
	                                bValid = false;
	                            }
                            }
                        }
                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return bValid;
            }
function validateEmail(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oEmail = new email();
                for (x in oEmail) {
                    if ((form[oEmail[x][0]].type == 'text' ||
                         form[oEmail[x][0]].type == 'textarea') &&
                        (form[oEmail[x][0]].value.length > 0)) {
                        if (!checkEmail(form[oEmail[x][0]].value)) {
                            if (i == 0) {
                                focusField = form[oEmail[x][0]];
                            }
                            fields[i++] = oEmail[x][1];
                            bValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                    focusField.focus();
                    alert(fields.join('\n'));
                }
                return bValid;
            }

            /**
             * Reference: Sandeep V. Tamhankar (stamhankar@hotmail.com),
             * http://javascript.internet.com
             */
            function checkEmail(emailStr) {
               if (emailStr.length == 0) {
                   return true;
               }
               var emailPat=/^(.+)@(.+)$/;
               var specialChars="\\(\\)<>@,;:\\\\\\\"\\.\\[\\]";
               var validChars="\[^\\s" + specialChars + "\]";
               var quotedUser="(\"[^\"]*\")";
               var ipDomainPat=/^(\d{1,3})[.](\d{1,3})[.](\d{1,3})[.](\d{1,3})$/;
               var atom=validChars + '+';
               var word="(" + atom + "|" + quotedUser + ")";
               var userPat=new RegExp("^" + word + "(\\." + word + ")*$");
               var domainPat=new RegExp("^" + atom + "(\\." + atom + ")*$");
               var matchArray=emailStr.match(emailPat);
               if (matchArray == null) {
                   return false;
               }
               var user=matchArray[1];
               var domain=matchArray[2];
               if (user.match(userPat) == null) {
                   return false;
               }
               var IPArray = domain.match(ipDomainPat);
               if (IPArray != null) {
                   for (var i = 1; i <= 4; i++) {
                      if (IPArray[i] > 255) {
                         return false;
                      }
                   }
                   return true;
               }
               var domainArray=domain.match(domainPat);
               if (domainArray == null) {
                   return false;
               }
               var atomPat=new RegExp(atom,"g");
               var domArr=domain.match(atomPat);
               var len=domArr.length;
               if ((domArr[domArr.length-1].length < 2) ||
                   (domArr[domArr.length-1].length > 3)) {
                   return false;
               }
               if (len < 2) {
                   return false;
               }
               return true;
            }
function validateMask(form) {
                var isValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oMasked = new mask();
                for (x in oMasked) {
                    var field = form[oMasked[x][0]];
                    
                    if ((field.type == 'text' || 
                         field.type == 'textarea') && 
                         (field.value.length > 0)) {
                        
                        if (!matchPattern(field.value, oMasked[x][2]("mask"))) {
                            if (i == 0) {
                                focusField = field;
                            }
                            fields[i++] = oMasked[x][1];
                            isValid = false;
                        }
                    }
                }
                
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return isValid;
            }

            function matchPattern(value, mask) {
               return mask.exec(value);
            }
function validateIntRange(form) {
                var isValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oRange = new intRange();
                for (x in oRange) {
                    var field = form[oRange[x][0]];
                    
                    if ((field.type == 'text' ||
                         field.type == 'textarea') &&
                        (field.value.length > 0)) {
                        
                        var iMin = parseInt(oRange[x][2]("min"));
                        var iMax = parseInt(oRange[x][2]("max"));
                        var iValue = parseInt(field.value);
                        if (!(iValue >= iMin && iValue <= iMax)) {
                            if (i == 0) {
                                focusField = field;
                            }
                            fields[i++] = oRange[x][1];
                            isValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                    focusField.focus();
                    alert(fields.join('\n'));
                }
                return isValid;
            }
function validateMinLength(form) {
                var isValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oMinLength = new minlength();
                for (x in oMinLength) {
                    var field = form[oMinLength[x][0]];
                    
                    if (field.type == 'text' ||
                        field.type == 'textarea') {
                        
                        var iMin = parseInt(oMinLength[x][2]("minlength"));
                        if ((trim(field.value).length > 0) && (field.value.length < iMin)) {
                            if (i == 0) {
                                focusField = field;
                            }
                            fields[i++] = oMinLength[x][1];
                            isValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return isValid;
            }
function validateAlertLessRejection(form) {
                var isValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oAlertLessRejection = new alertLessRejection();
                oRejection			= new rejection();
                
               for(x in oRejection){
               		var rejectionValue = form[oRejection[x][0]].value;
               		var floatRejectionValue=parseFloat(rejectionValue);
               		
               }
              
                for (x in oAlertLessRejection) {
                	var field = form[oAlertLessRejection[x][0]];
                	
                    if (field.type == 'text' ||
                        field.type == 'textarea') {
                        
                        var value = field.value;
						floatAlertValue = parseFloat(value);
					                     
                        if (floatAlertValue==floatRejectionValue || floatAlertValue>floatRejectionValue ) {
                        
	                        if (i == 0) {
	                            focusField = field;
	                        }
	                        fields[i++] = oAlertLessRejection[x][1];
	                        isValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return isValid;
            }
            
            // Trim whitespace from left and right sides of s.
            function trim(s) {
                return s.replace( /^\s*/, "" ).replace( /\s*$/, "" );
            }

//End --> 
</script>
</body>
</html>