
<%@page import="org.apache.log4j.Logger"%>
<html>
	<head>
		<title>Harrier StockPile</title>

	</head>
	<body>
		<%
			Logger log = Logger.getLogger(this.getClass());
			log.info("Logging in jsp started working Pranay...");
			log.info("inside "+this.getClass().getSimpleName()+" page");
			response.encodeURL("/Stockpile/pages/FrameDemo.html");
			String url1 = "/pages/FrameDemo.html";
			log.info("url1 is : "+ url1);
		%>
		<jsp:forward page="<%= url1 %>" />



	</body>
</html>



