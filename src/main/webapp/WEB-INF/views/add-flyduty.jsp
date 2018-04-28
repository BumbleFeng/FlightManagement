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
	width: 510px;
	display: block;
}

.center {
	text-align: center;
}

label {
	display: inline-block;
	width: 140px;
	text-align: right;
}
</style>
<title>Add FlyDuty</title>
</head>
<body>
	<div class="container" style="padding: 50px 80px 20px;">
		<div>
			Welcome :
			<sec:authentication property="name" />
			<a href="${cp}/logout" >Logout</a>
		</div>
		<h1>Add FlyDuty</h1>
		<div class="span-20">
			<form action="addFlyDuty.htm" method="post">
				<div ng-app="myApp" ng-controller="myCtrl">
					<div>
						<label>Call Sign:</label> <select name="callSign"
							ng-model="selectedCallSign"
							ng-options="x.callSign for x in flightList">
						</select>
					</div>
					<div>
						<label>Departure Terminal:</label> {{selectedCallSign.departureAirport.airportCode}} <select name="departureTerminal"
							ng-model="selectedDepartureTerminal"
							ng-options="y.terminalCode for y in selectedCallSign.departureAirport.terminals">
						</select>
					</div>
					<div>
						<label>Arrival Terminal:</label>  {{selectedCallSign.arrivalAirport.airportCode}} <select name="arrivalTerminal"
							ng-model="selectedArrivalTerminal"
							ng-options="z.terminalCode for z in selectedCallSign.arrivalAirport.terminals">
						</select>
					</div>
					<div>
						<label>Aircraft :</label> <select name="aircraft"
							ng-model="selectedAircraft"
							ng-options="a.aircraftCode for a in aircraftList">
						</select> {{selectedAircraft.model}}
					</div>
				</div>
				<div class="block">
					<label>Date :</label> <input type="text" name="date" id="datepicker"
						required="required">
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<div class="block center">
					<input type="submit" value="add">
				</div>
				<p></p>
			</form>
		</div>
		<div class="span-24">
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
				<c:forEach items="${flyDuties}" var="flyDuty">
					<tr>
						<td>${flyDuty.flight.callSign}</td>
						<td>${flyDuty.date}</td>
						<td>${flyDuty.departureTerminal.airport.airportCode}&nbsp;${flyDuty.departureTerminal.terminalCode}</td>
						<td>${flyDuty.arrivalTerminal.airport.airportCode}&nbsp;${flyDuty.arrivalTerminal.terminalCode}</td>
						<td>${flyDuty.actualDeparture}</td>
						<td>${flyDuty.actualArrival}</td>
						<td>${flyDuty.status}</td>
					</tr>
				</c:forEach>
			</table>
			<a href="airline.htm">[Go Back]</a>
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
				minDate : 0,
				numberOfMonths : 2,
				changeMonth : true,
				changeYear : true,
				showButtonPanel : true
			});
		});
	</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
	<script>
		var app = angular.module('myApp', []);
		app.controller('myCtrl', function($scope, $http) {
			$http.get("flightList.htm").then(function(response) {
				$scope.flightList = response.data;
			});
			$http.get("aircraftList.htm").then(function(response) {
				$scope.aircraftList = response.data;
			});
		});
	</script>
</body>
</html>