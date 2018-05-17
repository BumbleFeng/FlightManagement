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
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
	width: 120px;
	text-align: right;
}
</style>
<title>Book Tickets</title>
</head>
<body>
	<div class="container" style="padding: 50px 80px 20px;">
		<div>
			Welcome :
			<sec:authentication property="name" />
			<a href="${cp}/logout">Logout</a>&nbsp;
			<a href="${cp}/searchFlights.htm">Go Back</a>
		</div>
		<h1>Book Tickets</h1>
		<div>
			<table>
				<tr>
					<td>Call Sign</td>
					<td>Date</td>
					<td>Departure Airport</td>
					<td>Arrival Airport</td>
					<td>Departure Time</td>
					<td>Arrival Time</td>
					<td>Status</td>
				</tr>
				<tr>
					<td>${flyDuty.flight.callSign}</td>
					<td>${flyDuty.date}</td>
					<td>${flyDuty.departureTerminal.airport.airportCode}&nbsp;${flyDuty.departureTerminal.terminalCode}</td>
					<td>${flyDuty.arrivalTerminal.airport.airportCode}&nbsp;${flyDuty.arrivalTerminal.terminalCode}</td>
					<td>${flyDuty.actualDeparture}</td>
					<td>${flyDuty.actualArrival}</td>
					<td>${flyDuty.status}</td>
				</tr>
			</table>
		</div>
		<form action="orderTickets.htm" method="post">
			<div class="span-15">
				<label>Select Class:</label> <select name="class">
					<option value="F">First Class: $${flyDuty.firstclassPrice}&nbsp;Remians: ${flyDuty.firstclassRemain}</option>
					<option value="B">Business class: $${flyDuty.businessPrice}&nbsp;Remians: ${flyDuty.businessRemain}</option>
					<option value="E">Economy Class: $${flyDuty.economyPrice}&nbsp;Remians: ${flyDuty.economyRemain}</option>
				</select>
			</div>
			<div class="span-15">
				<label>Select Passenger:</label>
				<c:forEach items="${passengers}" var="passenger">
					<input type="checkbox" name="realIds" value="${passenger.realId}">
					<b>${passenger.firstName}&nbsp;${passenger.lastName}</b>
				</c:forEach>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div style="padding: 60px 300px 0px;">
				<input type="submit" value="Place Order">
			</div>
			<p></p>
		</form>
		<div class="span-10">
			<form action="bookTickets.htm" method="post">
				<div class="block">
					<label>Real ID:</label> <input id="id" type="text" name="realId"
						maxlength="3" required="required" />&nbsp;<b style="color: red"></b>
				</div>
				<div class="block">
					<label>First Name:</label> <input type="text" name="firstName"
						required="required" />
				</div>
				<div class="block">
					<label>Last Name:</label> <input type="text" name="lastName"
						required="required" />
				</div>
				<div class="block">
					<label>Birthday:</label> <input type="text" name="birthday"
						id="datepicker" required="required">
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<div  class="block center">
					<input type="submit" value="Add Passenger">
				</div>
				<p></p>
			</form>
		</div>
	</div>
		<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat: "yy-mm-dd",
			numberOfMonths : 2,
			changeMonth : true,
			changeYear : true,
			showButtonPanel : true
		});
	});
</script>
	<script>
		$(document).ready(function() {
			$("#id").keyup(function() {
				txt = $("#id").val();
				if (txt.length >= 3) {
					$.ajax({
						type : "POST",
						url : "checkRealId.htm",
						data : {
							realId : txt,
							"${_csrf.parameterName}" : "${_csrf.token}"
						},
						success : function(result) {
							$("b:last").html(result);
						}
					});
				}
			});
		});
	</script>
</body>
</html>