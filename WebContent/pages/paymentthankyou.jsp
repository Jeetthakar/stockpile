<%@page import="subscription.dao.SubscriptionDao"%>
<%@page import="subscription.form.subscriptionForm"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		String url = request.getRequestURI();
		StringBuffer sb = request.getRequestURL();
		String queryString = request.getQueryString();
		String transactionID = request.getParameter("TransactionID");
		String responseMessage = request.getParameter("ResponseMessage");
		System.out.println("URL *** " + url);
		System.out.println("sb *** " + sb);
		System.out.println("queryString *** " + queryString);
	%>
	<center>
	<br><br>
		<!-- 	insert data in database of the current transaction.-->
		<%
			subscriptionForm sub = (subscriptionForm) session
					.getAttribute("subscribe_for");
			System.out.println("PG response page Sub id *** "
					+ sub.getSubscriprion_id());
			String userId = (String) session.getAttribute("userid");
			System.out.println("PG response page userId *** " + userId);
			if (responseMessage.equalsIgnoreCase("Transaction Successful")) {
				new SubscriptionDao().AdduserSubscription(userId,
						sub.getSubscriprion_id(), null, transactionID);
		%>
		<img src="../images/payment_success.png" width="100" height="100">
		<br> <br>
		<h1 style="color: #02e16a">Thank You!</h1>
		<p>
			Your payment of
			<%=request.getParameter("Amount")%>
			has been processed successfully.
		</p>


		<%
			} else if (responseMessage
					.contains("Transaction Failed")) {
		%>	<img src="../images/payment_failed.jpg" width="100" height="100">
		<br> <br>

		<p>
			Your payment of
			<%=request.getParameter("Amount")%>
			failed.
		</p>
		<%
			}
		%>
		Transaction ID is
		<%=transactionID%>
		<!-- 		<table> -->
		<!-- 			<tr> -->
		<!-- 				<td>Payment ID :</td> -->
		<%-- 				<td><%=request.getParameter("PaymentID")%></td> --%>
		<!-- 			</tr> -->

		<!-- 			<tr> -->
		<!-- 				<td>Amount :</td> -->
		<%-- 				<td><%=request.getParameter("Amount")%></td> --%>
		<!-- 			</tr> -->

		<!-- 			<tr> -->
		<!-- 				<td>Transaction ID :</td> -->
		<%-- 				<td><%=request.getParameter("TransactionID")%></td> --%>
		<!-- 			</tr> -->

		<!-- 			<tr> -->
		<!-- 				<td>Request ID :</td> -->
		<%-- 				<td><%=request.getParameter("RequestID")%></td> --%>
		<!-- 			</tr> -->


		<!-- 		</table> -->
	</center>
</body>
</html>