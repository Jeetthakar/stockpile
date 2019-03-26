<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import="app.*"%>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<%
		LogonForm form = null;
		if(request.isRequestedSessionIdValid())	{	
			form = (LogonForm)session.getAttribute("user");
		}
		if(form==null ||(!request.isRequestedSessionIdValid())){
			response.sendRedirect("userlogintemp.jsp");
		}
		String fr2=request.getParameter("from");
%>
<html>
<head>
<html:base/>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<link rel="stylesheet" type="text/css" href="./StyleSheet.css"  title="Default" />   
<title>CA</title>
</head>

<body class="b2">
<jsp:useBean id="corporate" scope="session" class="app.Corporate"/> 
<logic:notEmpty property="hash_stock_error" name="corporate">
<FONT face="Arial" size="2" color="red">This Corporate Action Cannot be applied as some stocks doesn't have the closing value.</FONT>
</logic:notEmpty>     
<br>
<logic:notEmpty property="hash_error" name="corporate">
<font face="Arial" size="2" color="red"><b>	
<%=corporate.getStr().toString()%></b>
cannot be added as there is no closing value.</font>
</logic:notEmpty>

<logic:equal value="1" name="corporate" property="succ_butt">
<font face="Arial" size="3" color="green"><b>
<bean:message key="corporate.Success"/>
</b></font>
<jsp:setProperty name="corporate" property="succ_butt" value="" />
</logic:equal>

<html:form action="/indexCom_Action">
<html:errors/>
  <p align="center"><font size="4" face="Arial Black">
  <bean:message key="corporate.Indcomhead1" />
  </font></p>

<table width="100%" class="sample">
 <tr><td width="100%"> 
 <table border="0" width="100%" cellspacing="0" cellpadding="0" height="176">
 
  <tr>
<td width="100%"  colspan="3" bgcolor="#FFFFFF" height="1" align="center">
  </td>
  </tr>		   
  <tr>
  
 <tr>
 <td width="44%" align="center" bgcolor="#D8D8D8" height="23">
 <p><font face="Arial" size="2">
 <bean:message key="corporate.Indname" />
 </font>	
 <font face="Arial" size="2" color="red"><%=corporate.getI_name() %></font></p>
 </td>
 <td width="56%" bgcolor="#D8D8D8" height="23">
  <p><font face="Arial" size="2">
 <bean:message key="corporate.Apply" />
 </font>	 
 <font face="Arial" size="2" color="red"><%=corporate.getApply_date() %></font></p>
 </td>
 <td width="44%" bgcolor="#D8D8D8" height="23"/>	                        
  </tr>  
  
  <tr>
  <td width="56%" bgcolor="#D8D8D8" height="19" colspan="2">
  <p align="center">&nbsp;</td>
  <td width="44%" bgcolor="#D8D8D8" height="19">
  &nbsp;</td>
  </tr>

	<tr> 
	<td width="100%" colspan="3" bgcolor="#D8D8D8" height="19">
	<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1">
	
    <tr>
    <td width="7%" align="center" bgcolor="#FFFFFF"><html:checkbox property="c_Cad" onclick="CheckAll(this)" /></td>
    <td width="24%" align="center" bgcolor="#FFFFFF"><font size="1" face="Arial Black">
     <bean:message key="corporate.stock" />
     </font></td>
    <td width="9%" align="center" bgcolor="#FFFFFF"><font size="1" face="Arial Black">
         <bean:message key="corporate.Iwf" />
     </font></td>    
    <td width="9%" align="center" bgcolor="#FFFFFF"><font size="1" face="Arial Black">
             <bean:message key="corporate.Closeval" />
    </font></td>                        
    <td width="8%" align="center" bgcolor="#FFFFFF"><font size="1" face="Arial Black">
   <bean:message key="sysConfigForm.currencyId" />
   </font></td>
    <td width="11%" align="center" bgcolor="#FFFFFF"><font size="1" face="Arial Black">
    <bean:message key="corporate.Tis" />
    </font></td>
    <td width="11%" align="center" bgcolor="#FFFFFF"><font size="1" face="Arial Black">
       <bean:message key="sysConfigForm.marketLot" />
      </font></td>
   	<td width="11%" align="center" bgcolor="#FFFFFF"><font size="1" face="Arial Black">
   	   <bean:message key="corporate.Date" />
   	</font></td>
   </tr>      
    
 <%		   
	StringBuffer str = ListTypeClass.addRowsInTable1(corporate);			
	if(str!=null){%>
	<%=str.toString()%>
	<% }			
	else { %>					
   		<tr>
        <td width="7%" align="center">&nbsp;</td>
        <td width="24%" align="center">
        <p>&nbsp;</p>
        </td>
        <td width="9%" align="center">&nbsp;</td>
        <td width="9%" align="center">&nbsp;</td>
         <td width="8%" align="center">&nbsp;</td>
          <td width="11%" align="center">&nbsp;</td>
           <td width="11%" align="center">&nbsp;</td>
        <td width="11%" align="center">&nbsp;
          </td>
        </tr>
   <%}%>
   </table>
  </td>
  </tr>	
  
  <tr>
    <td width="100%" colspan="3" bgcolor="#D8D8D8" height="19">
      <p align="center">&nbsp;</p>
    </td>
  </tr>	
  
 <tr>
    <td width="159%" colspan="3" bgcolor="#D8D8D8" height="1">
      <p align="center">
	  <html:hidden property="compo_button"/>	
      <html:submit value="Add" onclick="return test1()" />
      <html:submit value="Remove" onclick="return test2()"/>
      <html:submit value="Refresh" />
  </tr>  
  
   <tr>
	<td width="100%" colspan="3" bgcolor="#D8D8D8" height="99">
	  &nbsp;
  <table border="1" width="100%" bordercolor="#000000" cellspacing="0" cellpadding="0" >
   <tr>
  <td width="100%">
    <table border="1" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="5%" align="center" bgcolor="#FFFFFF"><html:checkbox property="c_Cad1" onclick="CheckAll2(this)" /></td>
    <td width="16%" align="center" bgcolor="#FFFFFF"><font size="1" face="Arial">
           <bean:message key="corporate.stock" />
     </font></td>
    <td width="9%" align="center" bgcolor="#FFFFFF"><font size="1" face="Arial">
        <bean:message key="corporate.Iwf" />
    </font></td>
    <td width="10%" align="center" bgcolor="#FFFFFF"><font size="1" face="Arial">
        <bean:message key="corporate.Closeval" />
    </font></td>                         
    <td width="12%" align="center" bgcolor="#FFFFFF"><font size="1" face="Arial">
       <bean:message key="sysConfigForm.currencyId" />
      </font></td>
    <td width="13%" align="center" bgcolor="#FFFFFF"><font size="1" face="Arial">
       <bean:message key="corporate.Outstand" />
    </font></td>
    <td width="11%" align="center" bgcolor="#FFFFFF"><font size="1" face="Arial">
    <bean:message key="corporate.Mrkcap" />
    </font></td>
    <td width="10%" align="center" bgcolor="#FFFFFF"><font size="1" face="Arial">
     <bean:message key="corporate.Adjmkt" />
     </font></td>
    <td width="26%" align="center" bgcolor="#FFFFFF"><font size="1" face="Arial">
     <bean:message key="corporate.Weight" />
     </font></td>
  </tr>

<%       
    StringBuffer str1 = ListTypeClass.addRowsInSecondTable1(corporate,request);
    if(str1!=null){%>
		<%=str1.toString()%>	             
    <%}
    else { %>
	  <tr>
	    <td width="5%" align="center">&nbsp;</td>
	    <td width="16%" align="center">&nbsp;
	      </td>
	    <td width="9%" align="center"><font size="1" face="Arial">
	    <html:text property="T1" value="" size="11"/></font></td>
	    <td width="10%" align="center">&nbsp;</td>
	    <td width="12%" align="center">&nbsp;</td>
	    <td width="13%" align="center">&nbsp;</td>
	    <td width="11%" align="center">&nbsp;</td>
	    <td width="10%" align="center">&nbsp;</td>
	    <td width="26%" align="center">&nbsp;</td>
	  </tr>
      <%}%>         	
      </table>
      </td>
      </tr>
      </table>
      </td>
      </tr>
  
  <logic:equal value="Close" scope="request" parameter="close">
   	<jsp:setProperty name="corporate" property="tmcv" value=""/>
	<jsp:setProperty name="corporate" property="newtmcv" value=""/>
	<jsp:setProperty name="corporate" property="newTmcv" value=""/>
	<jsp:setProperty name="corporate" property="divisor" value=""/>
	<jsp:setProperty name="corporate" property="newdivisor" value=""/>
  
  		<tr>
		<td bgcolor="#D8D8D8"  height="20" width="56%">
		<b><font face="Arial" size="2" color="red">Select the radio button as Parent Index doesn't have the today's closing value.
		</font></b>
		</td>
		<td bgcolor="#D8D8D8"  height="20" width="44%">
		</td>
		<td bgcolor="#D8D8D8"  height="20"/>
  		</tr>  	 		
  		
  		<tr>
		<td bgcolor="#D8D8D8"  height="20" width="56%">
		<b><font face="Arial" size="1"><html:radio property="ind_comp" value="i" onclick="return radtest()">Compute Index</html:radio>		
		<html:radio property="ind_comp" value="c" onclick="return radtest()">Relculate Index Parameter</html:radio>
		</font></b>
		</td>
		<td bgcolor="#D8D8D8"  height="20" width="44%">
		</td>
		<td bgcolor="#D8D8D8"  height="20"/>
  		</tr>  	
  </logic:equal>
  
  <tr>
	<td bgcolor="#BFBFBF"  height="20" width="56%">
	<p align="center"><b><font face="Arial" size="1">
	<bean:message key="corporate.Indhead1" />                    
	      &nbsp;</font></b></p>                 		
	</td>
	<td bgcolor="#BFBFBF"  height="20" width="44%"><p align="right">
	<font face="Arial" size="1">                      
	<bean:message key="corporate.Indhead2" />                    
	 </font></p>						
	</td>		
	<td bgcolor="#BFBFBF"  height="20">
	</td>		
	</tr>
	<tr>
	<td bgcolor="#D8D8D8" height="5" width="56%"/>
	<td bgcolor="#D8D8D8" height="5" width="44%"/>
	<td bgcolor="#D8D8D8" height="5"/>
	</tr>
	<tr>
	 <td width="56%" bgcolor="#D8D8D8" align="center" height="20">		
	  </td>
	  <td width="44%" bgcolor="#D8D8D8" align="left" height="20">
		<p align="right">
		<b><font face="Arial" size="1">
			  <bean:message key="corporate.Index" />
		</font></b>			
		<html:select  size="1" property="affect" onchange="return affect_test()">		
		<html:optionsCollection name="corporate" property="affindCollection" />		
		</html:select></p>	          
	  </td>		          
	  <td bgcolor="#D8D8D8" height="20"/>              
	  </tr> 	
	<tr>
	<td bgcolor="#D8D8D8" height="5" width="56%"/>
	<td bgcolor="#D8D8D8" height="5" width="44%"/>
	<td bgcolor="#D8D8D8" height="5"/>
	</tr>    	  
	
	<tr>
	<td width="56%" bgcolor="#D8D8D8" align="center">
	  <font face="Arial" size="1">
	 	  <bean:message key="corporate.oldtmcv" />&nbsp;&nbsp;
	 </font><html:text property="tmcv" disabled="true"/>  
	  </td>
	  <td width="44%" bgcolor="#D8D8D8" align="right">
	  <font face="Arial" size="1">
	  	  <bean:message key="corporate.oldtmcv" />
	  </font><html:text property="tmcv1" disabled="true"/>            
	  </td>		          
	  <td bgcolor="#D8D8D8"/>
	  </tr>  
	   
	  <tr>
	  <td width="56%" bgcolor="#D8D8D8" align="center">
	  <font face="Arial" size="1">
	    <bean:message key="corporate.newtmcv" />&nbsp;
	  </font><html:text  property="newTmcv"  disabled="true"/>  
      </td>
      <td width="44%" bgcolor="#D8D8D8" align="right">
	  <font face="Arial" size="1">
	  	  <bean:message key="corporate.newtmcv" />
	  </font><html:text property="newtmcv1" disabled="true"/>            
      </td>		          
      <td bgcolor="#D8D8D8"/>          
      </tr>	  
      
	 <tr>
	 <td width="56%" bgcolor="#D8D8D8" align="center">
	  <font face="Arial" size="1">
	  <bean:message key="corporate.olddiv" />&nbsp;
	  </font><html:text property="divisor" disabled="true"/>  
      </td>
      <td width="44%" bgcolor="#D8D8D8" align="right">
	  <font face="Arial" size="1">
	   <bean:message key="corporate.olddiv" />
	  </font><html:text property="divisor1"  disabled="true"/>            
      </td>		          
      <td bgcolor="#D8D8D8"/>              
      </tr>     
       
	  <tr>
	  <td width="56%" bgcolor="#D8D8D8" align="center">
	  <font face="Arial" size="1">
	  <bean:message key="corporate.newdiv" />
	  </font>
	  <html:text property="newdivisor"  disabled="true"/>  
      </td>
      <td width="44%" bgcolor="#D8D8D8" align="right">
	  <font face="Arial" size="1">
	  <bean:message key="corporate.newdiv" />
	  </font><html:text property="newdivisor1"  disabled="true"/>            
      </td>		          
      <td bgcolor="#D8D8D8"/>              
      </tr>    
      
    <tr>
    <td width="56%" bgcolor="#D8D8D8" align="right"><html:submit  value="Apply" onclick="return test5()"/></td>
    <logic:equal value="1" name="corporate" property="commit_butt">
    <td width="44%" bgcolor="#D8D8D8"><html:submit value="Commit" onclick="return test6()"/></td>
    </logic:equal>
	<logic:notEqual value="1" name="corporate" property="commit_butt">
    <td width="44%" bgcolor="#D8D8D8"><html:submit value="Commit" onclick="return test6()" disabled="true"/></td>
	</logic:notEqual>    
    <td bgcolor="#D8D8D8">
    </tr>                   
       		

  </table>
  <html:hidden property="tmcv" value="<%=corporate.getTmcv()%>" />
  <html:hidden property="newTmcv" value="<%=corporate.getNewTmcv() %>" />
  <html:hidden property="divisor" value="<%=corporate.getDivisor() %>" />    
  <html:hidden property="newdivisor" value="<%=corporate.getNewdivisor() %>" />
  <html:hidden property="ind_comp" value='<%=request.getParameter("ind_comp") %>' />
  <html:hidden property="tmcv1" value="<%=corporate.getTmcv1() %>" />
  <html:hidden property="newtmcv1" value="<%=corporate.getNewtmcv1()%>" />
  <html:hidden property="divisor1" value="<%=corporate.getDivisor1()%>" />    
  <html:hidden property="newdivisor1" value="<%=corporate.getNewdivisor1()%>" />      
  <%corporate.setHash2(corporate.getHash2()); %>
  </td></tr></table>
  </html:form>       
</body>
<SCRIPT language="javascript">
function CheckAll(chk)
{
  for (var i=0;i < document.forms[0].elements.length;i++)
	{
		var e = document.forms[0].elements[i];
		if (e.type == "checkbox" && e.property=="c_Cad")
		{
			e.checked = chk.checked;
		}
	}
}
function CheckAll2(chk)
{
  for (var i=0;i < document.forms[0].elements.length;i++)
	{
		var e = document.forms[0].elements[i];
		if (e.type == "checkbox" && e.property=="c_Cad1")
		{
			e.checked = chk.checked;
		}
	}
}
function test1()
{
	document.forms[0].compo_button.value="Add";  	
	document.forms[0].submit();
}
function test2()
{
	document.forms[0].compo_button.value="Remove";  	
	document.forms[0].submit();
}
function test5()
{
	document.forms[0].compo_button.value="Apply";  	
	document.forms[0].submit();
}
function affect_test()
{
	document.forms[0].compo_button.value="Affect";  	
	document.forms[0].submit();
}
function test6()
{
	document.forms[0].compo_button.value="Commit";  	
	var val=document.forms[0].compo_button.value;	
	document.forms[0].submit();
}
function radtest()
{
	document.forms[0].compo_button.value="Radio";  	
	document.forms[0].submit();
}
</SCRIPT>
</html>      