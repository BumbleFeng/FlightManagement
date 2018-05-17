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
<title>Add Flight</title>
</head>
<body>
	<div class="container" style="padding: 50px 80px 20px;">
		<div>
			Welcome :
			<sec:authentication property="name" />
			From : ${airlineCode} <a href="${cp}/logout" >Logout</a>
		</div>
		<h1>Add Flight</h1>
		<div class="span-20">
			<form action="addFlight.htm" method="post">
				<div class="block">
					<label>Call Sign:</label> <input type="text" name="callSign"
						required="required" />&nbsp;<b style="color: red"></b>
				</div>
				<div ng-app="myApp" ng-controller="myCtrl">
					<div>
						<label>Departure Airport:</label> <select name="departureAirport"
							ng-model="selectedDepartureAirport"
							ng-options="x.airportCode for x in airportList">
						</select> {{selectedDepartureAirport.city}}
					</div>
					<div>
						<label>Arrival Airport:</label> <select name="arrivalAirport"
							ng-model="selectedArrivalAirport"
							ng-options="y.airportCode for y in airportList">
						</select> {{selectedArrivalAirport.city}}
					</div>
				</div>
				<div class="block">
					<label>Scheduled Departure:</label> <input type="text"
						name="scheduledDeparture" required="required" placeholder="HH:MM" />
				</div>
				<div class="block">
					<label>Flight Hour:</label> <input type="text" name="flightHour"
						required="required" placeholder="HH:MM" />
				</div>
				<div class="block">
					<label>Mileage:</label> <input type="text" name="mileage"
						required="required"
						onkeyup="this.value=this.value.replace(/\D/g,'')" />
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<div class="block center">
					<input type="submit" value="Add">
				</div>
				<p></p>
			</form>
		</div>
		<div class="span-20">
			<table>
				<tr>
					<td>Call Sign</td>
					<td>Departure Airport</td>
					<td>Arrival Airport</td>
					<td>Scheduled Departure</td>
					<td>Flight Hour</td>
					<td>Scheduled Arrival</td>
					<td>Mileage</td>
				</tr>
				<c:forEach items="${flights}" var="flight">
					<tr>
						<td>${flight.callSign}</td>
						<td>${flight.departureAirport.airportCode}</td>
						<td>${flight.arrivalAirport.airportCode}</td>
						<td>${flight.scheduledDeparture}</td>
						<td>${flight.flightHour}</td>
						<td>${flight.scheduledDeparture.plusMinutes(flight.scheduledArrival)}</td>
						<td>${flight.mileage}</td>
					</tr>
				</c:forEach>
			</table>
			<a href="airline.htm">[Go Back]</a>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$("input:first").keyup(function() {
				txt = $("input:first").val();
				if (txt.length >= 2) {
					$.ajax({
						type : "POST",
						url : "checkFlight.htm",
						data : {
							code : txt,
							"${_csrf.parameterName}" : "${_csrf.token}"
						},
						success : function(result) {
							$("b").html(result);
						}
					});
				}
			});
		});
	</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
	<script>
		var app = angular.module('myApp', []);
		app.controller('myCtrl', function($scope, $http) {
			$http.get("airportList.htm").then(function(response) {
				$scope.airportList = response.data;
			});
		});
	</script>
</body>
</html>