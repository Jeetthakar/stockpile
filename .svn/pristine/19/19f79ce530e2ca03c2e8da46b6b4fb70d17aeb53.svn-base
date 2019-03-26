<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="app.*,java.util.*" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<link href="/Income/pages/StyleSheet.css" rel="stylesheet" type="text/css">
<jsp:useBean id="IndexOHLCBean" scope="session" class="app.IndexOHLCForm"/>
<html:html>
<html:base/>
<head>    
<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" /> 
<SCRIPT LANGUAGE="JavaScript" SRC="../Script/autocomplete.js"></SCRIPT>  
<SCRIPT LANGUAGE="JavaScript" src="../Script/date_script.js"></script>
<script language="javascript" src="box_ex.js"></script>	
		<title>
      		Import INDEXOHLC File
    	</title>
    	
<script language="javascript" src="/Income/Script/codethatcalendarstd.js"></script>
<script language="javascript" src="box_ex.js"></script>	
<SCRIPT LANGUAGE="JavaScript" src="../Script/date_script1.js"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="../Script/date_script.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="../Script/autocomplete.js"></SCRIPT>
		
	<script language="javascript">
		var c2 = new CodeThatCalendar(caldef2);
	</script>		
</head>

<%

	if(request.getParameter("new")!=null && request.getParameter("new").equals("yes")){
		IndexOHLCBean.reset();
		log.info("in new"+request.getParameter("new"));
		
	}
%>

<body>
<html:form action="/IndexOHLC" enctype="multipart/form-data" method="post" >


		<font  size="4"><b></font><p align="center" >Import INDEXOHLC File </p></b></font>
		<hr>
		
		<table class="tab" >
		
		<tr>
			<td><font face="Arial" size="2" >
        			<b><span id="name">Index Name</span>&nbsp;&nbsp;&nbsp;&nbsp;</b>
				</font>
				<input type="text" name="cnt_sel" size="15" tabindex="2" onkeyup="autoComplete(this,this.form.indName,'text',true)">
			       	<br>
			       	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    <html:select size="5" property="indName" onchange="this.form.cnt_sel.value=this.options[this.selectedIndex].text;document.forms[0].varview.value=''" onclick="changeFields();">  
			       		<html:optionsCollection name="IndexOHLCBean" property="indexCollection"  />
            	</html:select>
			</td>
		</tr>
		</table>
		<br>
		
		<table class="tab" >
		<tr>
			<td><font face="Arial" size="2" >
				<b><span id="name">Select File</span>&nbsp;&nbsp;&nbsp;&nbsp;</b>
			</td>
			<td>
				<html:select property="fileType"  name="IndexOHLCBean">
					<html:optionsCollection property="fileTypeCollections" name="IndexOHLCBean" />
				</html:select>
			</td>
		</tr>
  			
  			<tr>
				<td><font face="Arial" size="2" >
					<b><span id="name">Attach File</span>&nbsp;&nbsp;&nbsp;&nbsp;</b>
				</td>
				<td>
					<html:file property="theFile"></html:file>
				</td>
			</tr>
			
			<tr>
				<td>
					<html:hidden property="hiddenVar" name="IndexOHLCBean" value="no" />
					<html:submit property="b1"  value="Show" onclick="setValue();" ></html:submit>
				</td>
				<td>
					<html:button property="b2" onclick="setval1();" value="Save">  </html:button>
					<html:hidden property="b2var" name="IndexOHLCBean" value="no" ></html:hidden>
				</td>
			</tr>
		</table>
<logic:equal value="yes" property="hiddenVar" name="IndexOHLCBean">	
<%
StringBuffer str = IndexOHLCBean.getDisp_buffer();
%>
<table cellSpacing="0" cellPadding="2" width="100%" class="gridStyle" border="1">
		<%
			try{
					if(str!=null){

		%>
					<%=str.toString()%>				
		<%			}else{
		%>
		<tr><td class="tab"></td>
		</tr>
		<%			}
				}catch(Exception e){}%>
			
		</table>
</logic:equal>	

<logic:equal value="yes" property="b2var" name="IndexOHLCBean">
	<table class="tab" border="1"> 	
			
			<tr>
			<%
			try{
			log.info("inside try block off save method ");
					if(IndexOHLCBean.getSave_buffer()!=null){	%>
					<%=IndexOHLCBean.getSave_buffer() %>
					<%	 	}
				}catch(Exception e){log.info("error****************"+e.getMessage());}
			
			%>
			</tr>
	</table>
	</logic:equal>
	
</html:form>
</body>

<script language="javascript"> 

function setValue(){
 var field_value = document.forms[0].indName.value;
 var file_value = document.forms[0].theFile.value;
 if(field_value == "" && file_value == "")
 {
 alert("Pleas Select the Index and File");
 return false;
 }
 else if (file_value == "")
 {
 alert("Pleas Select the Index and File");
 return false;
 }
	document.forms[0].hiddenVar.value="yes";
	document.forms[0].submit(); 
	
}
function setval1(){
var field_value = document.forms[0].indName.value;
 var file_value = document.forms[0].theFile.value;
 if(field_value == "" && file_value == "")
 {
 alert("Pleas Select the Index and File");
 return false;
 }
 

	document.forms[0].b2var.value="yes";
	document.forms[0].submit();
}
function changeFields(){
		document.forms[0].operation.value="Reset";
		document.forms[0].select_rname.value=document.forms[0].selectRName.value
		var val1=document.forms[0].selectRName.value;
		
  	 	if(val1=="value0"){
  	 		var rhsSelect = document.getElementById("Rem");
			var nodesToRemove = new Array();
			var numToRemove = 0;
			
			for(var index = 0; index < rhsSelect.options.length; index++)
			{
				
				nodesToRemove[ numToRemove++ ] = rhsSelect.options[ index ];
				var elementValue = rhsSelect.options[index].value;
				
					
			}
			for ( var index = 0; index < nodesToRemove.length; index++ )
			{
				rhsSelect.removeChild(nodesToRemove[index]);
			
			}

  	 		return false;
  	 	}
    	document.forms[0].submit();
}
</script>
</html:html>