<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="cp" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${cp}/resources/blueprint/screen.css"
	type="text/css" media="screen, projection">
<link rel="stylesheet" href="${cp}/resources/blueprint/print.css"
	type="text/css" media="print">
<link rel="stylesheet"
	href="${cp}/resources/blueprint/plugins/fancy-type/screen.css"
	type="text/css" media="screen, projection">
<style type="text/css">
table, th, td {
	border: 1px solid black;
}

.block {
	width: 420px;
	display: block;
}

.center {
	text-align: center;
}

label {
	display: inline-block;
	width: 20px;
	text-align: right;
}
</style>
<title>User Orders</title>
</head>
<body>
	<div class="container" style="padding: 50px 80px 20px;">
		<div>
			Welcome :
			<sec:authentication property="name" />
			<a href="${cp}/logout">Logout</a>&nbsp; <a
				href="${cp}/searchFlights.htm">Go Back</a>
		</div>
		<h1>User Orders</h1>
		<div class="container">
			<c:forEach items="${userOders}" var="userOrder">
				<div class="span-20">
					<table>
						<tr>
							<td>Order Id:</td>
							<td>${userOrder.id}</td>
							<td>Order Time:</td>
							<td>${userOrder.orderedTime}</td>
							<td>Order Price:</td>
							<td>${userOrder.price}</td>
						</tr>
					</table>
				</div>
				<c:forEach items="${userOrder.itineraries}" var="itinerary">
					<div class="span-24">
						<div class="span-2">
							<label>${itinerary.passenger.firstName}&nbsp;${itinerary.passenger.lastName}</label>
						</div>
						<div class="span-18">
							<table>
								<c:forEach items="${itinerary.tickets}" var="ticket">
									<tr>
										<td>${ticket.flyDuty.flight.callSign}</td>
										<td>${ticket.flyDuty.date}</td>
										<td>${ticket.flyDuty.flight.departureAirport.airportCode}&emsp;${ ticket.flyDuty.departureTerminal.terminalCode}</td>
										<td>${ticket.flyDuty.actualDeparture }</td>
										<td>${ticket.flyDuty.flight.arrivalAirport.airportCode}&emsp;${ ticket.flyDuty.arrivalTerminal.terminalCode}</td>
										<td>${ticket.flyDuty.actualArrival}</td>
										<td>${ticket.seatClass}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</c:forEach>
			</c:forEach>
		</div>
	</div>
</body>
</html>