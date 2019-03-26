<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="org.apache.log4j.Logger"%>
<% 
Logger log = Logger.getLogger(this.getClass());
log.info("inside "+this.getClass().getSimpleName()+" page");	
 %>
<html:html>
		 <link href="./StyleSheet.css" rel="stylesheet" type="text/css">
	<body>
			 Generating ...........
			 <% app.MakeExcel mkex=new app.MakeExcel();
			     //mkex.create_file();
			  %>
	
</body>
	<script language="Javascript">
		function submit(){
			document.forms[0].submit();
		}
	</script>
</html:html>
